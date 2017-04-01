package com.ailk.ims.proxy.ex;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.BaseMdbBean;
import com.ailk.ims.common.BusiRequestInfo;
import com.ailk.ims.common.DBRelRefreshBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.listener.IMSListenerServiceFlow;
import com.ailk.ims.listener.action.MdbSyncListenerAction;
import com.ailk.ims.listener.action.NotifiListenerAction;
import com.ailk.ims.listener.action.PreorderListenerAction;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
/**
 * 适用于CRM发起的webservice请求的正向业务接口。
 * @Description
 * @author wuyj
 * @Date 2012-9-22
 */
@Transactional
public abstract class BaseWSBusiProxy implements IExBusiProxy
{
    public Date createRequestDate() throws Exception
    {
        return DateUtil.currentDate();
    }

    public long createDoneCode(Date requestDate) throws Exception
    {
        return BusiRecordUtil.getReceiveDoneCode(requestDate);
    }

    public BusiRequestInfo createBusiRequest(IMSContext context,Object[] params) throws Exception
    {
        SOperInfo operInfo = (SOperInfo)params[0];//第一个参数必须是SOperInfo
        Object[] inputparams = new Object[params.length - 1];//剩余参数要另外提取出来
        System.arraycopy(params, 1, inputparams, 0, params.length - 1);
        
        BusiRequestInfo requestInfo = new BusiRequestInfo(operInfo,inputparams);
        
        return requestInfo;
    }

    public BaseResponse doProxyService(IMSContext context, Object[] otherParams) throws Exception
    {
        ImsLogger imsLogger = new ImsLogger(this.getClass());
        imsLogger.buildPrefix(context);
        long start = System.currentTimeMillis();
        imsLogger.info("intercepted by " + this.getClass().getName(), context);
        checkOperInfo(context);
        
        BaseResponse resp = doService(context,otherParams);
        imsLogger.debug("exit interceptor ", this.getClass().getName(),
                " time cost : ", System.currentTimeMillis() - start, " ms.");
        
        return resp;
    }
    
    protected void checkOperInfo(IMSContext context) throws IMSException
    {
        SOperInfo oper = context.getOper();
        // 不传busi_code就报错;
        if (oper.getBusi_code() == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "busi_code");
        }
        // 不传so_nbr就报错;
        if (CommonUtil.isEmpty(oper.getSo_nbr()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "so_nbr");
        }
        // 设置默认step_id
        if (oper.getStep_id() == null)
        {
            oper.setStep_id((short) 0);
        }
        // 不传so_mode报错
        if (oper.getSo_mode() == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "so_mode");
        }

        // 如果busi_code传了，验证和调用方法一致！
        if (oper.getBusi_code() != null)
        {
            Integer busi_code = Integer.parseInt(context.getMethodConfig().getAttribute(ConstantDefine.BUSI_CODE).trim());
            Integer come_code = oper.getBusi_code();
            if (!come_code.equals(busi_code))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.BUSI_CODE_NOT_MATCH, come_code, busi_code);
            }
        }
    }

    /**
     * 必须返回一个BaseResponse
     */
    public Object doProxyException(IMSContext context,Class<?> retrunType,Throwable t) throws Exception
    {
        BaseResponse result = context.getBusiFailedResponse();
        if(result == null)
            result = (BaseResponse)retrunType.newInstance();
        CBSErrorMsg errorMsg = IMSUtil.createCBSErrorMsg(context, t);;
        result.setErrorMsg(errorMsg);
        return result;
    }
    
   
    
    
    protected void createBusiRecord(BaseBusiBean busiBean, Object[] input)
    {
        // 创建业务记录总表
        List<CmBusi> busiList = busiBean.createBusiRecord(input);
        if (!CommonUtil.isEmpty(busiList))
        {
            DBUtil.insertBatch(busiList);
        }
        else
        {
            return;// 总表没记录的话，子表不需要再记了.
        }
        // 创建业务记录子表
        List<CmBusiOrder> busiOrderList = busiBean.createBusiOrderRecord(input);
        if (!CommonUtil.isEmpty(busiOrderList))
        {
            DBUtil.insertBatch(busiOrderList);
        }
    }
    
    protected void registerListenerActions(IMSListenerServiceFlow serviceFlow, BaseBusiBean busiBean, Object[] input,
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
    
    
    protected void checkBusiKey(BaseBusiBean busiBean,Object... input)
    {
        if (ProjectUtil.is_Overseas())
        {
            busiBean.checkBusiKey(input);
        }
    }
    
    
    protected void createMain3hBean(IMSContext context,BaseBusiBean busiBean,Object... input)
    {
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
        
    }
    
    protected void checkMain3hBean(IMSContext context)
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
    
    abstract protected BaseResponse doService(IMSContext context, Object... input) throws BaseException;
    
    protected void refreshRel(IMSContext context)
    {
        new DBRelRefreshBean(context).refreshRelations();
    }
    
    protected void oneTimeFee(IMSContext context,ImsLogger logger)
    {
    }
    
    protected void createWriteCapInfo(BaseBusiBean busiBean,ImsLogger logger,Object... input)
    {
        
    }
    
    protected void doBusiFailedAction(IMSContext context,BaseBusiBean busiBean,Exception e,Object... input)
    {
        try
        {
            // 代码优化
            BaseResponse resp = busiBean.busiFail(e, input);
            if(resp != null)
            {
                context.setBusiFailedResponse(resp);
            }
        }
        catch (Exception e2)
        {
            // TODO: handle exception
        	ImsLogger imsLogger = new ImsLogger(BaseWSBusiProxy.class);
        	imsLogger.error(e2,e2);
        }
        IMSUtil.throwBusiException(e);
    }
    
}
