/**
 * 
 */
package com.ailk.ims.proxy;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.BaseMdbBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.common.InnerClass;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.listener.IDBOperatorListener;
import com.ailk.ims.listener.IMSListenerServiceFlow;
import com.ailk.ims.listener.action.MdbSyncListenerAction;
import com.ailk.ims.listener.action.NotifiListenerAction;
import com.ailk.ims.listener.action.PreorderListenerAction;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.cust.entity.ImsSonbrMapping;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.OneTimeFee;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * @Description
 * @author wangdw5
 * @Date 2012-9-26
 */
public class AcctQueryBusiProxy implements INewBusiProxy
{
    protected ImsLogger imsLogger = new ImsLogger(this.getClass());
    /* (non-Javadoc)
     * @see com.ailk.ims.proxy.INewBusiProxy#createDoneCode(com.ailk.ims.common.IMSContext, java.lang.Object[])
     */
    public void createDoneCode(IMSContext context, Object... input) throws BaseException
    {
        context.setDoneCode(context.getRequestDate().getTime());
    }

    public List<Object> invokeArguments(IMSContext context, Object... args) throws BaseException
    {
        List<Object> input = new ArrayList<Object>();
        // 构建具体业务服务输入参数，即除去SOperInfo之外的其它输入参数
        for (int i = 0; i < args.length; i++)
        {
            Object arg = args[i];

            if (arg instanceof SOperInfo)
            {
                SOperInfo oper = (SOperInfo) arg;
                if (oper.getBusi_code() == null)
                {
                    oper.setBusi_code(context.getMethodConfig().getIntAttribute(BusiUtil.ATTR_BUSI_CODE));
                }
                context.setOper(oper);
            }
            else if (arg instanceof OneTimeFee)
            {
                context.setOneTimeFee((OneTimeFee) arg);
            }
            else
            {
                input.add(arg);
            }
        }
        return input;
    }
    
    public BaseResponse doService(IMSContext context, Object... input) throws BaseException
    {
        BaseResponse response = null;
        BaseBusiBean busiBean = null;
        IDBOperatorListener dbListener = SpringUtil.getDbOperatorListener();
        try
        {
            IMSListenerServiceFlow serviceFlow = new IMSListenerServiceFlow(context);
            dbListener.addActionContainer(serviceFlow);
            context.setListenerServiceFlow(serviceFlow);
            
            String serviceName = context.getServiceName();
            String methodName = context.getMethodName();
            
            busiBean = BusiUtil.getBusiBean(serviceName, methodName);
            
            if (busiBean == null)
            {
                throw new IMSException("no busi bean is configured with <service: " + serviceName + "> <method: " + methodName
                        + ">");
            }
            
            busiBean.setContext(context);
            
            boolean busiRecord = IMSUtil.isBusiRecord(context);

            if (busiRecord&&!ProjectUtil.is_CN_SH())
            {
                imsLogger.info("begin to check busi key", context);
                busiBean.checkBusiKey(input);
                imsLogger.info("finish to check busi key", context);
            }
            
            imsLogger.info("begin to init", context);
            busiBean.init(input);
            imsLogger.info("finish to init", context);
            
            imsLogger.info("begin to validate", context);
            busiBean.validate(input);
            imsLogger.info("finish to validate", context);
            
            imsLogger.info("begin to createMain3hBeans", context);
            List<IMS3hBean> beans = busiBean.createMain3hBeans(input);
            if (CommonUtil.isNotEmpty(beans))
            {
                for (IMS3hBean bean : beans)
                {
                    if (bean instanceof User3hBean || bean instanceof Acct3hBean)
                    {
                        // @Date 2012-07-12 wukl 针对OBJECT_ID查询时设置分表参数
                        ContextHolder.getRequestContext().put("ACCT_ID", bean.getAcctId());
                    }
                    else
                    {
                        // @Date 2012-07-12 wukl 针对OBJECT_ID查询时设置分表参数
                        ContextHolder.getRequestContext().put("CUST_ID", bean.getCustId());
                    }
                }
            }
            context.setMain3hBean(beans);
            imsLogger.info("finish to createMain3hBeans", context);
            
            imsLogger.info("begin to doBusiness", context);
            Object[] busiResult = busiBean.doBusiness(input);
            imsLogger.info("finish to doBusiness", context);
            
            imsLogger.info("begin to createResponse", context);
            response = busiBean.createResponse(busiResult);
            imsLogger.info("finish to init", context);
            
            BaseNode methodNode = context.getMethodConfig();
            Boolean serviceOTC = methodNode.getParent().getBooleanAttribute(ConstantDefine.IS_ONE_TIME_FEE, true);
            Boolean methodOTC = methodNode.getBooleanAttribute(ConstantDefine.IS_ONE_TIME_FEE, true);

            if (!serviceOTC || !methodOTC)
            {
                imsLogger.info("no need to charge onetime fee", context);
            }
            else
            {
                imsLogger.info("begin to charge onetime fee", context);
                // 业务中的一次性费用需要把orgin_so_nbr填为SOper中的外围so_nbr
                ImsSonbrMapping otc_mapping = busiBean.chargeOneTimeFee();
                context.addExtendParam(ConstantDefine.ONETIMEFEE_OBJECT, otc_mapping);
                imsLogger.info("finish to charge onetime fee",  context);
            }
            
            registerListenerActions(serviceFlow, busiBean, input, busiResult, response);
            
            InnerClass.WriteCapInfo wCapIf = busiBean.createWriteCapInfo(input);
            
            if (wCapIf != null)
            {
                try
                {
                    imsLogger.info("begin to count[" + wCapIf.getUri() + "]", context);
                    com.ailk.easyframe.sdl.OLTPServiceContext.writeCap(wCapIf.getUri(), wCapIf.getMap());
                    imsLogger.info("finish to count",  context);
                }
                catch (Throwable e)
                {
                    imsLogger.error("**********************error of count:" + e.getMessage(), e);
                }
            }
            
            busiBean.destroy();
            
            ContextHolder.getRequestContext().remove("ACCT_ID");
            ContextHolder.getRequestContext().remove("RESOURCE_ID");
            ContextHolder.getRequestContext().remove("CUST_ID");
        }
        catch (IMS3hNotFoundException e)
        {
            if (IMSUtil.isInnerSystem(context) && !context.isNotQueryBusi())
            {
                imsLogger.error(e, e);
            }
            else
            {
                throw e;
            }
        }
        catch (Exception e)
        {
            try
            {
                // 代码优化
                busiBean.busiFail(e, input);
            }
            catch (Exception e2)
            {
                // TODO: handle exception
            	imsLogger.error(e2,e2);
            }
            IMSUtil.throwBusiException(e);
        }
        return response;
    }
    
    /**
     * 一些逻辑需要在数据库commit之后处理，这里创建action并添加到IMSListenerServiceFlow的actionList中
     * 
     * @param serviceFlow
     * @param busiBean
     * @param input
     * @param busiResult
     * @param resp
     */
    public void registerListenerActions(IMSListenerServiceFlow serviceFlow, BaseBusiBean busiBean, Object[] input,
            Object[] busiResult, BaseResponse resp)
    {
        IMSContext context = serviceFlow.getContext();
        // 注册mdb action
        MdbSyncListenerAction mdbAction = createMdbListenerAction(context);
        serviceFlow.setMdbAction(mdbAction);

        // 注册告警action
        boolean needNotifi = context.getOper().getNotify_flag() == null
                || context.getOper().getNotify_flag() == EnumCodeDefine.NOTIFY_YES;
        if (needNotifi)
        {
            List<IMSNotification> notifications = busiBean.createNotifications(input, busiResult, resp);
            if (CommonUtil.isNotEmpty(notifications))
            {
                NotifiListenerAction action = new NotifiListenerAction();
                action.setNotifications(notifications);
                serviceFlow.addActionBeforeMdb(action);// 绝大部分的告警在mdb上发之前执行
            }
        }

        // 注册通知扣费action
        if (context.getExtendParam(ConstantDefine.PREORDER_PRODUCT_LIST_MAP) != null)
        {
            serviceFlow.addActionAfterMdb(new PreorderListenerAction());
        }

    }
    
    private MdbSyncListenerAction createMdbListenerAction(IMSContext context)
    {
        String serviceName = context.getServiceName();
        String methodName = context.getMethodName();
        BaseMdbBean mdbBean = BusiUtil.getMdbBean(serviceName, methodName);
        if (mdbBean == null)
            return null;
        mdbBean.setContext(context);
        context.setSync(true);// 表示同步，用于区分异步操作

        MdbSyncListenerAction action = new MdbSyncListenerAction();
        action.setMdbBean(mdbBean);
        return action;
    }

}
