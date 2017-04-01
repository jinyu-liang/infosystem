package com.ailk.ims.business.unifyplatforminterface;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.universeplatform.IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.CommitRefundOut;
import com.ailk.openbilling.persistence.imscnsh.entity.CommitRefundReq;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_commitRefundResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:退费交易提交接口
 * @author caohm5
 * @date:2012-12-10
 *
 */
public class CommitRefundBean extends BaseBusiBean {

	
	private CommitRefundReq req;

    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(CommitRefundReq)input[0];
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
    	CommitRefundOut out=null;
    	try {
    		imsLogger.dumpJsonObject("*********退费交易提交接口******************实体入参", req);
    		out=IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client.invokeCommitRefundCUP(req);
		} catch (Exception e) {
			imsLogger.error(e, e);
			throw IMSUtil.throwBusiException(ErrorCodeDefine.INVOKE_UNIVERSE_PLAT_METHOD_CODE,e.getMessage());
		}
    	return new Object[]{out};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_commitRefundResponse respn=new Do_commitRefundResponse();
    	CommitRefundOut out=(CommitRefundOut)result[0];
    	respn.setOut(out);
    	return respn;
    	
    }

    @Override
    public void destroy()
    {

    }


	
}
