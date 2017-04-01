package com.ailk.ims.proxy;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.cust.entity.CmBusi;
import com.ailk.openbilling.persistence.cust.entity.CmBusiOrder;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * @Description: 业务流程控制类。此类是单例的，请不要定义任何与当前请求相关的数据！！！
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 */
@Transactional
public class IntfServiceProxy implements IBusiProxy
{
    protected  ImsLogger imsLogger=new ImsLogger(this.getClass());
    public BaseResponse doService(IMSContext context, Object... input) throws BaseException
    {   
        imsLogger.info("################ enter intfServiceProxy");
        //数据库配置参数IM_SHBOSS_REPAIRING，0:正常  1：系统级维护  2:ABM维护 
        imsLogger.info("################ get  IM_SHBOSS_REPAIRING");
        String repairFlag = SysUtil.getString(ConstantDefine.IM_SHBOSS_REPAIRING);
        if(repairFlag != null&&!repairFlag.trim().equals("")){
            if(repairFlag.trim().equals(EnumCodeDefine.SHBOSS_REPAIR)){
                throw IMSUtil.throwBusiException(ErrorCodeDefine.SHBOSS_REPAIR);
            }else if(repairFlag.trim().equals(EnumCodeDefine.SHBOSS_ABM_REPAIR)){
                throw IMSUtil.throwBusiException(ErrorCodeDefine.SHBOSS_ABM_REPAIR);
            }
        }
        imsLogger.info("################ finish get IM_SHBOSS_REPAIRING");
        BaseResponse result = null;
        BaseBusiBean busiBean = null;
        try{
            long start = System.currentTimeMillis();
            imsLogger.info("begin to check oper info", context);
            checkOperInfo(context.getOper(), context);
            imsLogger.info("finish to check oper info", context);
            imsLogger.debug("################get busibean", start, context);
            String serviceName = context.getServiceName();
            String methodName = context.getMethodName();
            
            busiBean = BusiUtil.getBusiBean(serviceName, methodName);
            if (busiBean == null) {
                throw new IMSException("no busi bean is configured with <service: "
                        + serviceName + "> <method: " + methodName + ">");
            }
            busiBean.setContext(context);
            imsLogger.debug("################finish get busibean", start, context);
            imsLogger.debug("################bean.init", start, context);
            busiBean.init(input);
            imsLogger.debug("################finish bean.init", start, context);
            imsLogger.debug("################bean.validate", start, context);
            busiBean.validate(input);
            imsLogger.debug("################finish bean.validate", start, context);
            imsLogger.debug("################create main3hbean", start, context);
            List<IMS3hBean> beans = busiBean.createMain3hBeans(input);
            if (CommonUtil.isNotEmpty(beans)) {
                for (IMS3hBean bean : beans) {
                    // @Date 2012-07-12 wukl 针对OBJECT_ID查询时设置分表参数
                    if (bean instanceof User3hBean) {
                        IMSUtil.setUserRouterParam(bean.getUserId());
                    } else if (bean instanceof Acct3hBean) {
                        IMSUtil.setAcctRouterParam(bean.getAcctId());
                    } else {
                        IMSUtil.setCustRouterParam(bean.getCustId());
                    }
                }
            }
            context.setMain3hBean(beans);
            imsLogger.debug("################finish create main3hbean", start, context);
            imsLogger.debug("################do business", start, context);
            result = busiBean.createResponse(busiBean.doBusiness(input));
            imsLogger.debug("################finish do business", start, context);
            
            // 创建业务记录
            if (IMSUtil.isBusiRecord(context))
            {
                start = System.currentTimeMillis();
                imsLogger.info("begin to create busi record", context);
                createBusiRecord(busiBean, input);
                imsLogger.info("finish to create busi record", start, context);
            }
            imsLogger.info("exit interceptor " + this.getClass().getName(), start, context);
        }catch (Exception e) {
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
            	imsLogger.error(e2,e2);
            }
            IMSUtil.throwBusiException(e);
        }
        return result;
    }
    /**
     * @Description: 检查OperInfo中的字段是否都合法
     * @param oper
     *            wuyj
     */
    private void checkOperInfo(SOperInfo oper, IMSContext context)
            throws IMSException {
        if (IMSUtil.isBusiRecord(context))// 判断是否要check
        {
            // 不传busi_code就报错;
            if (oper.getBusi_code() == null) {
                throw IMSUtil.throwBusiException(
                        ErrorCodeDefine.COMMON_PARAM_ISNULL, "busi_code");
            }
            // 不传so_nbr就报错;
            if (CommonUtil.isEmpty(oper.getSo_nbr())) {
                throw IMSUtil.throwBusiException(
                        ErrorCodeDefine.COMMON_PARAM_ISNULL, "so_nbr");
            }
            // 不传step_id报错
            if (oper.getStep_id() == null) {
                // 2012-03-13 zengxr this field should has default value for
                // normal business action
                oper.setStep_id((short) 0);
                // IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,
                // "step_id");
            }
            // 不传so_mode报错
            if (oper.getSo_mode() == null) {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,
                        "so_mode");
            }

        }
        // 如果busi_code传了，验证和调用方法一致！
        if (oper.getBusi_code() != null) {
            Integer busi_code = Integer.parseInt(context.getMethodConfig()
                    .getAttribute(ConstantDefine.BUSI_CODE).trim());
            Integer come_code = oper.getBusi_code();
            if (!come_code.equals(busi_code)) {
                throw IMSUtil.throwBusiException(
                        ErrorCodeDefine.BUSI_CODE_NOT_MATCH, come_code,
                        busi_code);
            }
        }
    }
    private void createBusiRecord(BaseBusiBean busiBean, Object[] input) {
        // 创建业务记录总表
        List<CmBusi> busiList = busiBean.createBusiRecord(input);
        if (!CommonUtil.isEmpty(busiList)) {
            DBUtil.insertBatch(busiList);
        } else {
            return;// 总表没记录的话，子表不需要再记了.
        }
        // 创建业务记录子表
        List<CmBusiOrder> busiOrderList = busiBean.createBusiOrderRecord(input);
        if (!CommonUtil.isEmpty(busiOrderList)) {
            DBUtil.insertBatch(busiOrderList);
        }
    }   
}
