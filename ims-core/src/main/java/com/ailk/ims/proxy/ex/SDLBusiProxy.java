package com.ailk.ims.proxy.ex;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.BaseMdbBean;
import com.ailk.ims.common.BusiRequestInfo;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.component.BaseLifeCycleComponent;
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
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;

/**
 * 内部sdl接口代理
 * @Description
 * @author luojb
 * @Date 2012-10-25
 */
@Transactional
public class SDLBusiProxy implements IExBusiProxy
{
    public Date createRequestDate() throws Exception
    {
        return DateUtil.currentDate();
    }

    public long createDoneCode(Date requestDate) throws Exception
    {
        return BusiRecordUtil.getReceiveDoneCode(requestDate);
    }
    
    public BusiRequestInfo createBusiRequest(IMSContext context, Object[] params) throws Exception
    {
        SOperInfo oper = ConvertUtil.sdlOper2JavaOper((com.ailk.openbilling.persistence.imssdl.entity.SOperInfo) params[0]);
        Object[] inputparams = new Object[params.length - 1];//剩余参数要另外提取出来
        System.arraycopy(params, 1, inputparams, 0, params.length - 1);
        
        BusiRequestInfo requestInfo = new BusiRequestInfo(oper,inputparams);
        
        return requestInfo;
    }
    
    public Object doProxyService(IMSContext context, Object[] otherParams) throws Exception
    {
        
        BaseResponse resp = doService(context,otherParams);
        if (resp instanceof Do_sdlResponse)
        {
            Do_sdlResponse sdlResponse = (Do_sdlResponse) resp;
            if (sdlResponse.getErrorMsg() == null || sdlResponse.getErrorMsg().getResult_code() == null)
            {
                return 0;//SDL接口定义时要求返回Number类型，不允许为null；
            }
            return sdlResponse.getErrorMsg().getResult_code().intValue();
        }
        return resp;
    }
    
    public BaseResponse doService(IMSContext context, Object... input) throws BaseException
    {
        ImsLogger logger = new ImsLogger(getClass());
        logger.buildPrefix(context);
        
        BaseResponse response = null;
        BaseBusiBean busiBean = null;
        IDBOperatorListener dbListener = SpringUtil.getDbOperatorListener();
        try
        {
            //一个流程对应一个actionContainer，每个container里可以包含多个action 这样保证嵌套的子流程也有自己对应的actionContainer，互不影响
            IMSListenerServiceFlow serviceFlow = new IMSListenerServiceFlow(context);
            dbListener.addActionContainer(serviceFlow);
            context.setListenerServiceFlow(serviceFlow);

            String serviceName = context.getServiceName();
            String methodName = context.getMethodName();

            // 根据busicode获取业务处理类
            busiBean = BusiUtil.getBusiBean(serviceName, methodName);

            if (busiBean == null)
            {
                throw new IMSException("no busi bean is configured with <service: " + serviceName + "> <method: " + methodName
                        + ">");
            }
            busiBean.setContext(context);

            // 业务记录检测
            // 初始化工作
            busiBean.init(input);

            // 数据合法性校验
            busiBean.validate(input);

            // 创建main3hbean
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


            // 非内部接口 验证main3hbean业务状态
            if (!IMSUtil.isInnerSystem(context))
            {
                if (IMSUtil.isNeedCreate3hBean(context) && CommonUtil.isEmpty(context.getMain3hBeanList()))
                {// 检验是否有创建3hbean
                    IMSUtil.throwBusiException("createMain3hBeanList not success!");
                }
                if(!ProjectUtil.is_CN_SH()){
                    context.getComponent(BaseLifeCycleComponent.class).checkMain3hBeanBusiState(context.getMain3hBeanList(),
                            context.getOper().getBusi_code());
                }
            }

            // 执行业务操作
            Object[] busiResult = busiBean.doBusiness(input);

            // 创建应答对象
            response = busiBean.createResponse(busiResult);

            // 非查询类需要关联更新
            if (context.isNotQueryBusi())
            {
//                new DBRelRefreshBean(context).refreshRelations();
            }
            
            logger.info("no need to charge onetime fee", context);

            // 如果有鉴权算费缓存对象，则进行鉴权扣费
				
            // 注册action
            registerListenerActions(serviceFlow, busiBean, input, busiResult, response);


            // 数据清理工作
            busiBean.destroy();
            
            //@Date 2012-08-28 wukl 处理账管查询用户相关表时分表出错的问题，TODO 待分表参数的新方案改动后，可删除这段代码
            //账管自己查询用户有效期表时，由于先调用了查询用户信息的接口，此时session中设置了分表参数ACCT_ID，这就导致账管查另外用户的有效期出现分表错误；
            IMSUtil.removeRouterParam();
        }
        catch (IMS3hNotFoundException e)
        {
            // 内部查询接口不抛3hbean错误
            if (!context.isNotQueryBusi())
            {
                logger.error(e, e);
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
            	 logger.error(e2, e2);
            }
            IMSUtil.throwBusiException(e);
        }
        return response;
    }


    public Integer doProxyException(IMSContext context, Class<?> retrunType, Throwable t) throws Exception
    {
        throw IMSUtil.throwBusiException(new IMSException(t));
    }
    
    private void registerListenerActions(IMSListenerServiceFlow serviceFlow, BaseBusiBean busiBean, Object[] input,
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
