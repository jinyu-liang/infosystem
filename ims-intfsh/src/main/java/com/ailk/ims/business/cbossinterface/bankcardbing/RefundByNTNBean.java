package com.ailk.ims.business.cbossinterface.bankcardbing;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.RefundsRsp;
import com.ailk.ims.cboss.service.impl.Cboss2CrmSVImpl;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_refundByNTNResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.RefundByNTNReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 总对总冲正接口
 * @Description
 * @author luowq
 * @Date 2013-5-17
 */

public class RefundByNTNBean extends BaseBusiBean {

	 private RefundByNTNReq req = null;

	    @Override
	    public void init(Object... input) throws BaseException
	    {
	        req = (RefundByNTNReq) input[0];
	    }

	    @Override
	    public void validate(Object... input) throws BaseException{
	        if(req == null){
	            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "req");
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
	        String json = CommonUtilSh.genCbossBusiJson("RefundsReq",req.getRefundReq());
	        imsLogger.dumpJsonObject("####:总对总冲正接口::::开始调用CBoss接口",json);
	        long start = System.currentTimeMillis();
	        RefundsRsp refundsRsp = Cboss2CrmSVImpl.refundByNTN(json);
	        imsLogger.info(start, "##############CALL CBOSS PAYMENT ################");
	        imsLogger.dumpJsonObject("####总对总冲正接口::::结束调用CBoss接口", refundsRsp);
	        return new Object[] {refundsRsp};
	    }

	    @Override
	    public BaseResponse createResponse(Object[] input)
	    {
	    	Do_refundByNTNResponse resp = new Do_refundByNTNResponse();
	    	resp.setRefundByNTNRsp((RefundsRsp)input[0]);
	        return resp;
	    }

	    @Override
	    public void destroy()
	    {
	    }

}
