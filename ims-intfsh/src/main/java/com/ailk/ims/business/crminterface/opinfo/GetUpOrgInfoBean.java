package com.ailk.ims.business.crminterface.opinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_getUpOrgInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UpOrgInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.UpOrgReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @description:营业操作员登录，获取的是当前操作员对应的上级组织
 * @author caohm5
 * @date:2012-11-29
 *
 */
public class GetUpOrgInfoBean extends BaseBusiBean {


	private UpOrgReq req;

	@Override
	public void init(Object... input) throws BaseException {
		req = (UpOrgReq) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		//请求参数不能为空
		if (null == req) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"UpOrgReq");
		}
		//TODO
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		Map<String, String> busiParams = new HashMap<String, String>();
		busiParams.put("orgId", req.getOrg_id());
		busiParams.put("domainId", req.getDomain_id());
		long doneCode = this.getContext().getDoneCode();
		UpOrgInfo info=HttpJsonToCrmService.getUpOrgInfo(String.valueOf(doneCode), busiParams);
		return new Object[] { info };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		UpOrgInfo info =(UpOrgInfo)result[0];
		Do_getUpOrgInfoResponse respn=new Do_getUpOrgInfoResponse();
		respn.setInfo(info);
		return respn;
	}

	@Override
	public void destroy() {

	}


}
