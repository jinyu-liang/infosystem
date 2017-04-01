package com.ailk.ims.business.crminterface.channel;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_qryChannelByOpIdResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SQryChannelByOpIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 查询渠道
 * @Description
 * @author xieqr
 * @Date 2012-12-30
 */
public class QueryChennelByOpIdBean extends BaseBusiBean {
	SQryChannelByOpIdReq req = null;

	@Override
	public void init(Object... input) throws BaseException {
		req = (SQryChannelByOpIdReq) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// 请求参数不能为空
		if (req == null) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "req");
		}
		if (CommonUtil.isEmpty(req.getOp_id())) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "op_id");
		}
		if (CommonUtil.isEmpty(req.getDomain_id())) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "domain_id");
		}
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException {
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		Map<String, String> busiParams = new HashMap<String, String>();
		busiParams.put("OpId", req.getOp_id());
		busiParams.put("DomainId", req.getDomain_id());
		long doneCode = this.getContext().getDoneCode();
		String channelId = HttpJsonToCrmService.getChannelByOpId(String.valueOf(doneCode), busiParams);
		return new Object[] { channelId };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		String channelId = (String) result[0];
		Do_qryChannelByOpIdResponse respn = new Do_qryChannelByOpIdResponse();
		respn.setChannel_id(channelId);
		return respn;
	}

	@Override
	public void destroy() {

	}
}
