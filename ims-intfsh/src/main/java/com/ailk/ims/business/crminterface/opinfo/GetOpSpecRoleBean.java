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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_getOpSpecRoleResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.OpSpecRole;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:客服操作员登录，获取的是当前操作员所在的特殊角色
 * @author caohm5
 * @date:2012-11-29
 *
 */
public class GetOpSpecRoleBean extends BaseBusiBean {


	private String opId;

	@Override
	public void init(Object... input) throws BaseException {
		opId = (String) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		//请求参数不能为空
		if (CommonUtil.isEmpty(opId)) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"op_id");
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
		busiParams.put("opId", opId);
		long doneCode = this.getContext().getDoneCode();
		OpSpecRole info=HttpJsonToCrmService.getOpSpecRole(String.valueOf(doneCode), busiParams);
		return new Object[] { info };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		OpSpecRole info =(OpSpecRole)result[0];
		Do_getOpSpecRoleResponse respn=new Do_getOpSpecRoleResponse();
		respn.setInfo(info);
		return respn;
	}

	@Override
	public void destroy() {

	}


}
