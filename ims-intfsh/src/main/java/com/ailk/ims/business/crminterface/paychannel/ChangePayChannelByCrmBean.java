package com.ailk.ims.business.crminterface.paychannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.ChangePayChReq;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_changePayChannelByCrmResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/***
 * @description:修改银行代扣为现金支付方式接口
 * @author caohm5
 * @date:2012-12-12
 *
 */
public class ChangePayChannelByCrmBean extends BaseBusiBean {

	private ChangePayChReq req;

	@Override
	public void init(Object... input) throws BaseException {
		req = (ChangePayChReq) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		if (null == req){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "ChangePayChReq");
        }
		if (CommonUtil.isEmpty(req.getService_num())) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"service_num");
		}
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		Map<String, String> busiParams = new HashMap<String, String>();
		busiParams.put("ServiceNum", req.getService_num());
		long doneCode = this.getContext().getDoneCode();
		String soNbr=HttpJsonToCrmService.changePayChannelByCrm(String.valueOf(doneCode), busiParams);
		return new Object[] { soNbr };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		String soNbr=(String)result[0];
		Do_changePayChannelByCrmResponse respn=new Do_changePayChannelByCrmResponse();
		respn.setSo_nbr(soNbr);
		return respn;
	}

	@Override
	public void destroy() {

	}



}
