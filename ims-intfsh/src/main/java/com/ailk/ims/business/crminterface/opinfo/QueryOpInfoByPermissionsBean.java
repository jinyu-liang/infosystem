package com.ailk.ims.business.crminterface.opinfo;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryOpInfoByPermissionsResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.OpInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.PermissionsReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:根据权限编号查询拥有该权限的所有人员
 * @author caohm5
 * @date:2012-07-13
 * 
 */
public class QueryOpInfoByPermissionsBean extends BaseBusiBean {

	private PermissionsReq req;

	@Override
	public void init(Object... input) throws BaseException {
		req = (PermissionsReq) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		//请求参数不能为空
		if (null == req) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"privEntityId");
		}
		//两者至少有一个
		if(CommonUtil.isEmpty(req.getEntity_id())&&CommonUtil.isEmpty(req.getPriv_id())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL,"entity_id and priv_id");
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
		busiParams.put("opId", req.getOp_id());
		long doneCode = this.getContext().getDoneCode();
		List<OpInfo> opIdList=HttpJsonToCrmService.getPermissions(String.valueOf(doneCode), busiParams);
		return new Object[] { opIdList };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		List<OpInfo> opIdList =(List<OpInfo>)result[0];
		Do_queryOpInfoByPermissionsResponse respn=new Do_queryOpInfoByPermissionsResponse();
		respn.setOpInfoList(opIdList);
		return respn;
	}

	@Override
	public void destroy() {

	}

}
