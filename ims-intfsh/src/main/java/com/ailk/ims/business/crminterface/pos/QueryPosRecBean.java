package com.ailk.ims.business.crminterface.pos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.crminterface.bo.IBOCaPosRecValue;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryPosRecResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:刷卡记录查询的接口
 * @author caohm5
 * @date:2013-01-14
 *
 */
public class QueryPosRecBean extends BaseBusiBean {

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
		List<IBOCaPosRecValue> infoList=HttpJsonToCrmService.getPosRecByOpId(String.valueOf(doneCode), busiParams);
		return new Object[] { infoList };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		List<IBOCaPosRecValue> menuList = (List<IBOCaPosRecValue>) result[0];
		Do_queryPosRecResponse respn=new Do_queryPosRecResponse();
		respn.setPosRecList(menuList);
		return respn;
	}

	@Override
	public void destroy() {

	}

}
