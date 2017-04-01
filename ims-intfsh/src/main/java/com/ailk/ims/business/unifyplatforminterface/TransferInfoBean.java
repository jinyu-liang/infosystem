package com.ailk.ims.business.unifyplatforminterface;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.universeplatform.IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_transerInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Transfer;
import com.ailk.openbilling.persistence.imscnsh.entity.TransferOut;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:与统一在线支付平台-支付系统-银联代扣代缴业务接口 的交互信息
 * @author caohm5
 * @date:2012-11-22
 *
 */
public class TransferInfoBean extends BaseBusiBean {
	
	private Transfer req;

    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(Transfer)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
    	TransferOut out=null;
    	try {
    		imsLogger.dumpJsonObject("*********与统一平台代扣代缴的交互信息******************实体入参", req);
    		out=IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client.invokeCommitTransInfoCUP(req);
		} catch (Exception e) {
			imsLogger.error(e, e);
			throw IMSUtil.throwBusiException(ErrorCodeDefine.INVOKE_UNIVERSE_PLAT_METHOD_CODE,e.getMessage());
		}
    	return new Object[]{out};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_transerInfoResponse respn=new Do_transerInfoResponse();
    	TransferOut out=(TransferOut)result[0];
    	respn.setTransferOut(out);
    	return respn;
    	
    }

    @Override
    public void destroy()
    {

    }

}
