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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryOpInfoByPermisssionsExtResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.OpInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.PermissionReqExt;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:根据权限编号查询拥有该权限的所有人员(优化)
 * @author caohm5
 * @date:2012-11-21
 *
 */
public class QueryOpInfoByPermissionsExtBean extends BaseBusiBean {


	private PermissionReqExt req;

	@Override
	public void init(Object... input) throws BaseException {
		req = (PermissionReqExt) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		//请求参数不能为空
		if (null == req) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"privEntityId");
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
		busiParams.put("entId", req.getEntity_id());
		busiParams.put("privId", req.getPriv_id());
		busiParams.put("orgId", req.getOrg_id());
		busiParams.put("domainId", req.getDomain_id());
		long doneCode = this.getContext().getDoneCode();
		List<OpInfo> opIdList=HttpJsonToCrmService.getPermissionsExt(String.valueOf(doneCode), busiParams);
		return new Object[] { opIdList };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		List<OpInfo> opIdList =(List<OpInfo>)result[0];
		Do_queryOpInfoByPermisssionsExtResponse respn=new Do_queryOpInfoByPermisssionsExtResponse();
		respn.setOpInfoList(opIdList);
		return respn;
	}

	@Override
	public void destroy() {

	}


	
}
