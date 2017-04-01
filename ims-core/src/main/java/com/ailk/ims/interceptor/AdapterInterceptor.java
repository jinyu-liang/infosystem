package com.ailk.ims.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import jef.common.wrapper.Holder;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.component.helper.FirstActiveHelper;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imsxdr.entity.SBusinessInfo;
import com.ailk.openbilling.persistence.imsxdr.entity.SBusinessInteraction;
import com.ailk.openbilling.persistence.imsxdr.entity.SManagerInfo;
import com.ailk.openbilling.persistence.imsxdr.entity.SXdr;
import com.ailk.openbilling.persistence.pm.entity.PmFirstActiveGprsUrl;

/**
 * 适配拦截拦截器，根据不同的服务构造入参和出参
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2 2012-05-29 wuyujie 更改判断定时任务的方式,根据节点配置属性来判断
 */
public class AdapterInterceptor extends BaseMethodInterceptor
{
    private static ImsLogger imsLogger=new ImsLogger(AdapterInterceptor.class);
    public Object invokeMethod(MethodInvocation invocation, IMSContext context) throws Throwable
    {
        Object[] args = invocation.getArguments();
        BaseNode serviceNode = context.getServiceConfig();
        // 2012-05-29 wuyujie 更改判断定时任务的方式,根据节点配置属性来判断
        boolean isTimer = serviceNode.getBooleanAttribute(ConstantDefine.IS_TIMER, false);
        if (isTimer || CommonUtil.isEmpty(args) || args[0].getClass() == CsdlArrayList.class)
        {
            // SDL timer
            Object result = invokeSDLTimerService(invocation);
            return result;
        }

        if (args[0] instanceof com.ailk.openbilling.persistence.imssdl.entity.SOperInfo)
        {
            // sdl服务（EDL2SDL）
            Object result = invokeEDL2SDLService(invocation);
            return result;
        }
        if (args[0] instanceof com.ailk.openbilling.persistence.imssdl.entity.SOperInfo)
        {
            // sdl服务
            Object result = invokeSDLService(invocation);
            return result;
        }

        if (args[0] instanceof Holder)
        {
            // xdr服务
            Object result = invokeXdrService(invocation);
            return result;
        }

        if (args[0] instanceof com.ailk.openbilling.persistence.imsintf.entity.SOperInfo)
        {
            // webservice服务
            Object result = invocation.proceed();
            return result;
        }

        return invocation.proceed();
    }

    public Object invokeSDLTimerService(MethodInvocation invocation) throws Throwable
    {
        ReflectiveMethodInvocation invo = (ReflectiveMethodInvocation) invocation;
        Object[] args = invocation.getArguments();
        SOperInfo oper = buildSDLTimerOper(invo.getMethod());
        IMSContext context = super.getContext(invocation);
        String methodName = invocation.getMethod().getName();
        if (context.getMethodConfig() == null && methodName.startsWith("deal_"))
        {
            // 说明是deal_xxxx方法，因为deal方法在busi.xml里是不会配置的，所以这里需要找到对应的read_xxx方法重新设置进去
            methodName = methodName.replaceFirst("deal_", "read_");
            BaseNode methodNode = BusiUtil.getMethodNode(context.getServiceName(), methodName);
            context.setMethodConfig(methodNode);
        }

        wrapOperInfo(oper, context);
        context.setOper(oper);

        Object[] argsNew = null;
        if (CommonUtil.isEmpty(args))
        {
            argsNew = new Object[1];
            argsNew[0] = oper;
        }
        else
        {
            argsNew = new Object[args.length + 1];
            argsNew[0] = oper;
            System.arraycopy(args, 0, argsNew, 1, args.length);
        }
        invo.setArguments(argsNew);

        BaseResponse response = null;
        response = (BaseResponse) invo.proceed();

        // 从SdlResponse中提取返回值
        if (response instanceof Do_sdlResponse)
        {
            Do_sdlResponse sdlResponse = (Do_sdlResponse) response;
            Long resultCode = sdlResponse.getErrorMsg().getResult_code();
            if (resultCode == null)
            {
                return null;
            }
            return resultCode.intValue();
        }
        return response;
    }

    /**
     * @Description: 执行Xdr服务
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object invokeXdrService(MethodInvocation invocation) throws Throwable
    {
        ReflectiveMethodInvocation invo = (ReflectiveMethodInvocation) invocation;
        Object[] args = invocation.getArguments();

        Holder holder = (Holder) args[0];
        SXdr sxdr = (SXdr) holder.get();

        // 需要把sdl的SOperInfo结构转换成webservice的SOperInfo结构
        SOperInfo oper = ConvertUtil.xdrOper2JavaOper(sxdr.getManagerInfo().getOperInfo());
        IMSContext context = super.getContext(invocation);
        context.setOper(oper);
        List<Object> argList = new ArrayList<Object>();
        argList.add(oper);
        for (Object arg : args)
        {
            argList.add(arg);
        }

        BaseResponse response = null;
        try
        {
            invo.setArguments(argList.toArray(new Object[argList.size()]));
            response = (BaseResponse) invo.proceed();
        }
        catch (Exception e)
        {
            // xdr首次激活 需要将错误信息设置到xdr的返回结构中
            SManagerInfo managerInfo = sxdr.getManagerInfo();
            sxdr.setManagerInfo(managerInfo);
            SBusinessInfo businessInfo = managerInfo.getBusinessInfo();
            //@Date 2012-11-08 wuyujie : sdl改成EDL方式需要判断对象是否为空
            if(businessInfo == null){
                businessInfo = new SBusinessInfo();
                managerInfo.setBusinessInfo(businessInfo);
            }
            SBusinessInteraction result = businessInfo.getSbusinessinteraction();
            if(result == null)
                result = new SBusinessInteraction();
            businessInfo.setSbusinessinteraction(result);
            CBSErrorMsg error = IMSUtil.createCBSErrorMsg(getContext(invocation), e);
            result.setErrorCode(error.getResult_code().intValue());
            result.setErrorDesc(error.getError_msg());

            // 数据首次激活 再这里设置失败的URL
            if (oper.getSo_mode() == EnumCodeDefine.DATA_ACTIVE)
            {
                PmFirstActiveGprsUrl urls = FirstActiveHelper.getGprsUrlsInContext(context);
                String failureUrl = "";
                if (urls == null)
                {
                    failureUrl = SysUtil.getString(SysCodeDefine.busi.FIRST_ACTIVE_FAILURE_URL);
                }
                else
                {
                    failureUrl = urls.getFailureUrl();
                }
                result.setRedirectUrl(failureUrl);
            }
            throw e;
        }

        // 从SdlResponse中提取返回值
        if (response instanceof Do_sdlResponse)
        {
            Do_sdlResponse sdlResponse = (Do_sdlResponse) response;
            Long resultCode = sdlResponse.getErrorMsg().getResult_code();
            if (resultCode == null)
            {
                return null;
            }
            return sdlResponse.getErrorMsg().getResult_code().intValue();
        }
        return response;
    }

    /**
     * @Description: 执行sdl（EDL2SDL）服务
     */
    private Object invokeEDL2SDLService(MethodInvocation invocation) throws Throwable
    {
        Object[] args = invocation.getArguments();

        // 需要把sdl的SOperInfo结构转换成webservice的SOperInfo结构
        SOperInfo oper = ConvertUtil.edlOper2JavaOper((com.ailk.openbilling.persistence.imssdl.entity.SOperInfo) args[0]);
        IMSContext context = super.getContext(invocation);
        // sdl接口，doneCode作为so_nbr
        oper.setSo_nbr(CommonUtil.long2String(context.getSoNbr()));
        context.setOper(oper);

        invocation.getArguments()[0] = oper;
        BaseResponse response = (BaseResponse) invocation.proceed();

        // 从SdlResponse中提取返回值
        if (response instanceof Do_sdlResponse)
        {
            Do_sdlResponse sdlResponse = (Do_sdlResponse) response;
            Long resultCode = sdlResponse.getErrorMsg().getResult_code();

            if (resultCode == null)
            {
                return null;
            }
            return resultCode.intValue();
        }
        return response;
    }

    /**
     * @Description: 执行sdl服务
     */
    private Object invokeSDLService(MethodInvocation invocation) throws Throwable
    {
        Object[] args = invocation.getArguments();

        // 需要把sdl的SOperInfo结构转换成webservice的SOperInfo结构
        SOperInfo oper = ConvertUtil.sdlOper2JavaOper((com.ailk.openbilling.persistence.imssdl.entity.SOperInfo) args[0]);
        IMSContext context = super.getContext(invocation);
        // sdl接口，doneCode作为so_nbr
        oper.setSo_nbr(CommonUtil.long2String(context.getSoNbr()));
        context.setOper(oper);

        invocation.getArguments()[0] = oper;
        BaseResponse response = (BaseResponse) invocation.proceed();

        // 从SdlResponse中提取返回值
        if (response instanceof Do_sdlResponse)
        {
            Do_sdlResponse sdlResponse = (Do_sdlResponse) response;
            Long resultCode = sdlResponse.getErrorMsg().getResult_code();

            if (resultCode == null)
            {
                return null;
            }
            return resultCode.intValue();
        }
        return response;
    }

    /**
     * @Description: sdl定时器需要构建一个webservice的SOperInfo结构
     */
    private static SOperInfo buildSDLTimerOper(Method method)
    {
        SOperInfo oper = new SOperInfo();
        oper.setSo_nbr("-1");// if so_nbr == -1 not save the busi record
        oper.setSo_mode((short) 99);
        oper.setSo_date(DateUtil.currentString19());
        oper.setStep_id((short) 0);// 一次调用
        return oper;
    }

    /**
     * @Description: 包装公用头信息，一些每填的字段用默认值，主要适用于ts定时任务
     * @author : wuyj
     * @date : 2012-1-11
     */
    private static void wrapOperInfo(SOperInfo oper, IMSContext context)
    {
        if (oper.getBusi_code() == null)
        {
            // 获取默认的busi_code
            Integer busiCode = context.getMethodConfig().getIntAttribute(BusiUtil.ATTR_BUSI_CODE);
            imsLogger.info("set busi_code[" + busiCode + "] to SOperInfo", context);
            oper.setBusi_code(busiCode);
        }
    }
}
