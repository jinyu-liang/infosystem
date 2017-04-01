package com.ailk.ims.business.pmsinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jef.jre5support.script.LuaScriptUtil;

import org.luaj.vm2.LuaValue;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.LuaConfig;
import com.ailk.ims.component.PmsFeeComponent;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imspgmt.entity.Do_queryWisdomFeeListResponse;
import com.ailk.openbilling.persistence.imspgmt.entity.PdWisdomFeeIn;
import com.ailk.openbilling.persistence.imspgmt.entity.PdWisdomFeeOut;
import com.ailk.openbilling.persistence.imspgmt.entity.PdWisdomFeeReq;
import com.ailk.openbilling.persistence.pd.entity.PmProductOfferRentFee;
import com.ailk.openbilling.persistence.pd.entity.PmSupermarketFreeres;
import com.ailk.openbilling.persistence.pm.entity.PmPriceEvent;

public class QueryWisdomFeeBusiBean extends BaseBusiBean {

	private PdWisdomFeeReq req;
	private PmsFeeComponent pfCmp;

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] arg0) {
		Do_queryWisdomFeeListResponse response = new Do_queryWisdomFeeListResponse();
		response.setPdWisdomFeeOut((List<PdWisdomFeeOut>) arg0[0]);
		return response;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... arg0) throws BaseException {
		List<PdWisdomFeeIn> inList = req.getWisdomFeeList();
		List<PdWisdomFeeOut> outList = new ArrayList<PdWisdomFeeOut>();
		PdWisdomFeeOut out = null;
		for (PdWisdomFeeIn in : inList) {
			if (in.getResCount() != null ) {
				out = getWisdomFee(in);
			} else {
				out = getRentFee(in);
			}
			outList.add(out);
		}
		return new Object[] { outList };
	}

	@Override
	public void init(Object... arg0) throws BaseException {
		req = (PdWisdomFeeReq) arg0[0];
		pfCmp = context.getComponent(PmsFeeComponent.class);
	}

	@Override
	public void validate(Object... arg0) throws BaseException {
		if (null == req || CommonUtil.isEmpty(req.getWisdomFeeList())) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "pdWisdomFeeReq");
		}
		for (PdWisdomFeeIn in : req.getWisdomFeeList()) {
			if (in.getOfferId() == null) {
				IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "PdWisdomFeeIn.offerId");
			}
			if (in.getResCount() != null && in.getResCount() < 0) {
				IMSUtil.throwBusiException(ErrorCodeDefine.RESCOUNT_ISNEGATIVE);
			}
		}

	}

	private PdWisdomFeeOut getWisdomFee(PdWisdomFeeIn in) throws BaseException {
		PdWisdomFeeOut out = convertParamOut(in);
		List<Integer> mainOfferList = new ArrayList<Integer>();
		if (in.getMainOfferId() != null && in.getMainOfferId() != -1) {
			mainOfferList.add(in.getMainOfferId());
		}
		mainOfferList.add(-1);
		PmSupermarketFreeres freeRes = pfCmp.queryPmSupermarketFreeres(in.getOfferId(), mainOfferList);
		if (freeRes == null) {
			IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_OFFER_NOTEXIST, in.getOfferId());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(EnumCodeShDefine.PM_WISDOM_LUA_PARAM_RES_AMOUNT, LuaScriptUtil.toLua(in.getResCount()));
		Integer policyId = freeRes.getPolicyId();
		Object result = null;
		Long fee = null;
		try {
			LuaValue value = LuaConfig.executeLuaFunction(policyId, EnumCodeShDefine.PM_WISDOM_LUA_FUNCTION, paramMap);
			result = LuaScriptUtil.toJava(value);
			fee = Long.parseLong(result.toString());
		} catch (Exception e) {
			IMSUtil.throwBusiException(ErrorCodeDefine.CALCULATE_WISDOM_FEE_ERR);
		}

		out.setFee(fee);
		return out;
	}

	private PdWisdomFeeOut convertParamOut(PdWisdomFeeIn in) {
		PdWisdomFeeOut out = new PdWisdomFeeOut();
		out.setMainOfferId(in.getMainOfferId());
		out.setOfferId(in.getOfferId());
		out.setResCount(in.getResCount());
		return out;
	}

	private PdWisdomFeeOut getRentFee(PdWisdomFeeIn in) {
		PdWisdomFeeOut out = convertParamOut(in);
		PmProductOfferRentFee rentFee = pfCmp.queryPmProductOfferRentFee(in.getOfferId(), EnumCodeShDefine.PM_RENT_FEE_TYPE_MONTH);
		if (rentFee != null) {
			out.setFee(rentFee.getRentFee());
			PmPriceEvent event = pfCmp.queryPriceEvent(rentFee.getItemId());
			if (event != null) {
				out.setName(event.getName());
			}
		} else {
			IMSUtil.throwBusiException(ErrorCodeDefine.NO_RENT_FEE_FOUND, in.getOfferId());
		}
		return out;
	}
}
