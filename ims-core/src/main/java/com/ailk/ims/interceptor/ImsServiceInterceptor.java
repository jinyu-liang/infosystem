package com.ailk.ims.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.init.IdMapInitBean;
import com.ailk.ims.proxy.IBusiProxy;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.OneTimeFee;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * 服 务拦截器，所有服务都会拦截下来，公用信息在 这里进行统一的包装构建
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 * @Date 2011-8-16 tangjl5 response添加sdl接口的判断，response构造为Do_sdlResponse()对象
 * @Date 2012-3-29 wangjt:修改isEmpty方法用来判断对象的问题
 * @Date 2012-3-29 wangjt:上海项目，不需要内外部ID映射
 * @Date 2012-12-17 wukl: On_Site Defect #67735 REDO模块加解锁用户IMS接口没有上发MDB
 */
public class ImsServiceInterceptor extends BaseMethodInterceptor
{
    private ImsLogger imsLogger=new ImsLogger(this.getClass());

    public Object invokeMethod(MethodInvocation invocation, IMSContext context) throws Throwable
    {
            long start = System.currentTimeMillis();
            BaseNode methodNode = context.getMethodConfig();
            imsLogger.dump("service config", context.getServiceConfig().getAttributes());
            imsLogger.dump("method config", methodNode.getAttributes());

            boolean isInner = methodNode.getParent().getBooleanAttribute(BusiUtil.ATTR_ISINNER,false);

            imsLogger.info("begin to check oper info", context);
            checkOperInfo(context.getOper(), context);
            imsLogger.info("finish to check oper info", start, context);

            List<Object> input = ajdustInputArguments(invocation.getArguments(), context);

            // wangjt:上海项目，不需要set内外部id
            if (!isInner && ProjectUtil.is_Overseas())
            {
                start = System.currentTimeMillis();
                imsLogger.info("begin to adjust outerId to innerId", context);
                adjustOuterId2InnerId(context, input);
                imsLogger.info("finish to adjust outerId to innerId", start, context);
            }

            start = System.currentTimeMillis();
            imsLogger.info("begin to instance busi proxy", context);
            IBusiProxy busiProxy = instanceBusiProxy(context);
            imsLogger.info("finish to instance busi proxy", start, context);

            start = System.currentTimeMillis();
            imsLogger.info("begin to do proxy service", context);
            BaseResponse response = busiProxy.doService(context, input.toArray(new Object[input.size()]));
            imsLogger.info("finish to do proxy service", start, context);

            // 调整返回参数的outer字段(wangjt:上海项目不需要)
            if (!isInner && ProjectUtil.is_Overseas())
            {
                start = System.currentTimeMillis();
                imsLogger.info("begin to adjust innerId to outerId", context);
                IdMapInitBean.adjustOuterId(response, context);
                imsLogger.info("finish to adjust innerId to outerId", start, context);
            }
            return response;
    }

    private IBusiProxy instanceBusiProxy(IMSContext context)
    {
        IBusiProxy busiProxy = null;
        String proxyBeanId = context.getMethodConfig().getParent().getAttribute(ConstantDefine.SERVICE_PROXY_BEAN_ID);
        imsLogger.debug("proxy bean id : " + proxyBeanId, context);
        if (CommonUtil.isEmpty(proxyBeanId))
        {
            // 默认获取webserce业务代理类
            busiProxy = SpringUtil.getServiceProxy();
        }
        else
        {
            busiProxy = (IBusiProxy) SpringUtil.getBean(proxyBeanId);
        }
        imsLogger.debug("final proxy bean : " + busiProxy.getClass().getName(), context);

        return busiProxy;

    }

    /**
     * @Description: 调整输入参数，提取出公用入参，放到context里
     * @author : wuyj
     * @date : 2011-12-9
     */
    private List<Object> ajdustInputArguments(Object[] args, IMSContext context)
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

    /**
     * @Description: 内外id映射调整
     * @author : wuyj
     * @date : 2011-12-9
     */
    private void adjustOuterId2InnerId(IMSContext context, List<Object> input)
    {
        if (CommonUtil.isEmpty(input))
            return;
        for (Object obj : input)
        {
            IdMapInitBean.adjustInnerId(obj, context);
        }
    }

    /**
     * @Description: 检查OperInfo中的字段是否都合法
     * @param oper wuyj
     */
    private void checkOperInfo(SOperInfo oper, IMSContext context) throws IMSException
    {
        if (IMSUtil.isBusiRecord(context))// 判断是否要check
        {
            // 不传busi_code就报错;
            if (oper.getBusi_code() == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "busi_code");
            }
            // 不传so_nbr就报错;
            if (CommonUtil.isEmpty(oper.getSo_nbr()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "so_nbr");
            }
            // 不传step_id报错
            if (oper.getStep_id() == null)
            {
                // 2012-03-13 zengxr this field should has default value for normal business action
                oper.setStep_id((short) 0);
                // IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "step_id");
            }
            // 不传so_mode报错
            if (oper.getSo_mode() == null)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "so_mode");
            }

        }

        // 如果busi_code传了，验证和调用方法一致！
        if (oper.getBusi_code() != null)
        {
            Integer busi_code = Integer.parseInt(context.getMethodConfig().getAttribute(ConstantDefine.BUSI_CODE).trim());
            Integer come_code = oper.getBusi_code();
            if (!come_code.equals(busi_code))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.BUSI_CODE_NOT_MATCH, come_code, busi_code);
            }
        }
    }
}
