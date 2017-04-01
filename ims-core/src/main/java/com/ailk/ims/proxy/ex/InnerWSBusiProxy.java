package com.ailk.ims.proxy.ex;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBRelRefreshBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.listener.IDBOperatorListener;
import com.ailk.ims.listener.IMSListenerServiceFlow;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

public class InnerWSBusiProxy extends BaseWSBusiProxy
{
    
    protected BaseResponse doService(IMSContext context, Object... input) throws BaseException
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
            checkBusiKey(busiBean, input);
            
            // 初始化工作
            busiBean.init(input);

            // 数据合法性校验
            busiBean.validate(input);

            // 创建main3hbean
            createMain3hBean(context, busiBean, input);

            // 执行业务操作
            Object[] busiResult = busiBean.doBusiness(input);

            // 创建应答对象
            response = busiBean.createResponse(busiResult);

            // 非查询类需要关联更新
            new DBRelRefreshBean(context).refreshRelations();
            
            // 创建业务记录
            createBusiRecord(busiBean, input);

            // 注册action
            registerListenerActions(serviceFlow, busiBean, input, busiResult, response);


            // 数据清理工作
            busiBean.destroy();
            
            //@Date 2012-08-28 wukl 处理账管查询用户相关表时分表出错的问题，TODO 待分表参数的新方案改动后，可删除这段代码
            //账管自己查询用户有效期表时，由于先调用了查询用户信息的接口，此时session中设置了分表参数ACCT_ID，这就导致账管查另外用户的有效期出现分表错误；
            IMSUtil.removeRouterParam();
        }
        catch (Exception e)
        {
            doBusiFailedAction(context, busiBean, e, input);
        }
        return response;
    }
    
    public Object doProxyException(IMSContext context,Class<?> retrunType,Throwable t) throws Exception
    {
        throw IMSUtil.throwBusiException(new IMSException(t));
    }
    
}
