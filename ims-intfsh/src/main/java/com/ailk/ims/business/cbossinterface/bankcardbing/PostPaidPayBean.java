package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.PaymentRsp;
import com.ailk.ims.cboss.service.impl.Cboss2CrmSVImpl;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_postPaidPayResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.PostPaidPayReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 签约用户缴费接口
 * @Description
 * @author zhangzj3
 * @Date 2013-3-4
 */
public class PostPaidPayBean extends BaseBusiBean
{
    private PostPaidPayReq req = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (PostPaidPayReq) input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException{
        if(req == null){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "PostPaidPayReq");
        }
        if(req.getPaymentRequest()== null){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "PaymentReq");
        }
        if(!CommonUtil.isValid(req.getBip_code())){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bip_code");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {   
        String json = CommonUtilSh.genCbossBusiJson("PaymentReq", req.getPaymentRequest());
        imsLogger.dumpJsonObject("####:PostPaidPay总对总签约用户缴费::::开始调用CBoss接口",json);
        long start = System.currentTimeMillis();
        PaymentRsp paymentRsp = Cboss2CrmSVImpl.postPaidPay(req.getBip_code(),json);
        imsLogger.info(start, "##############CALL CBOSS PAYMENT ################");
        imsLogger.dumpJsonObject("####PostPaidPay总对总签约用户缴费::::结束调用CBoss接口", paymentRsp);
        return new Object[] {paymentRsp};
    }

    @Override
    public BaseResponse createResponse(Object[] input)
    {
        Do_postPaidPayResponse resp = new Do_postPaidPayResponse();
        resp.setPaymentRsp((PaymentRsp) input[0]);
        return resp;
    }

    @Override
    public void destroy()
    {
    }


}
