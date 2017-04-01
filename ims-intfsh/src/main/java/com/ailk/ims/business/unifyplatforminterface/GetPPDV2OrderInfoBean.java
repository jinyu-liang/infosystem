package com.ailk.ims.business.unifyplatforminterface;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.universeplatform.IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_getPPDV2OrderInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.GetPPDV2OrderInfoOut;
import com.ailk.openbilling.persistence.imscnsh.entity.GetPPDV2OrderInfoReq;
import com.ailk.openbilling.persistence.imscnsh.entity.PPDV2SubOrderInfoOut;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:交易记录查询（统一支付平台）
 * @author caohm5
 * @date:2012-12-10
 *
 */
public class GetPPDV2OrderInfoBean extends BaseBusiBean {

	
	private GetPPDV2OrderInfoReq req;

    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(GetPPDV2OrderInfoReq)input[0];
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
		GetPPDV2OrderInfoOut out = null;
		//校验三户数据
    	try {
    		imsLogger.dumpJsonObject("*********手机支付本地订单信息查询******************实体入参", req);
    		out =IServiceMgmtSoapServicePortType_IServiceMgmtSoapServiceHttpPort_Client.invokeGetPPDV2OrderInfo(req);
		} catch (Exception e) {
			imsLogger.error(e, e);
//			throw IMSUtil.throwBusiException(ErrorCodeDefine.INVOKE_UNIVERSE_PLAT_METHOD_CODE,e.getMessage());
		}
    	return new Object[]{out};
    	
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_getPPDV2OrderInfoResponse respn=new Do_getPPDV2OrderInfoResponse();
		List<GetPPDV2OrderInfoOut> outList= null;
		GetPPDV2OrderInfoOut out = (GetPPDV2OrderInfoOut)result[0];
		if(out!=null){
			outList = new ArrayList<GetPPDV2OrderInfoOut>();
			outList.add(out);
		}
		respn.setOutList(outList);
    	return respn;
    	
    }

    @Override
    public void destroy()
    {

    }


	
	
}
