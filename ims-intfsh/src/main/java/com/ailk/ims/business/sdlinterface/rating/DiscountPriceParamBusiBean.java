package com.ailk.ims.business.sdlinterface.rating;

import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SPriceParam;
import com.ailk.openbilling.persistence.imssdl.entity.SUpSellingPriceParam;

/**
 * 
 * @Description 跨月优惠
 * @author yuxz
 * @Date 2016-06-29
 */
public class DiscountPriceParamBusiBean extends BaseBusiBean {

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Object... input) throws BaseException {
		SUpSellingPriceParam param = (SUpSellingPriceParam) input[0];
		imsLogger.dump("SUpdatePriceParam", param);

	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException {
		// SUpSellingPriceParam param=(SUpSellingPriceParam)input[0];
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		BaseComponent baseCmp = context.getComponent(BaseComponent.class);
		ImsLogger imsLogger = new ImsLogger(ImsLogger.class);
		SUpSellingPriceParam param = (SUpSellingPriceParam) input[0];
		List<SPriceParam> paramList = param.getPriceParam();
		if (CommonUtil.isEmpty(paramList)) {
			return null;
		}
		long objectId = param.getObjectId();
		int objectType = param.getObjectType();
		if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV) {
			IMSUtil.setUserRouterParam(objectId);
		} else {
			IMSUtil.setAcctRouterParam(objectId);
		}
		for (SPriceParam sPriceParam : paramList) {
			CoProdPriceParam dmPriceParam = baseCmp.querySingle(CoProdPriceParam.class, new DBCondition(
					CoProdPriceParam.Field.productId, param.getProdId()), new DBCondition(
					CoProdPriceParam.Field.paramId, sPriceParam.getKey()));
			if (dmPriceParam == null) {
				CoProdPriceParam value = new CoProdPriceParam();
				value.setCreateDate(context.getRequestDate());
				value.setExpireDate(ConvertUtil.sdlLong2JavaDate(param.getExpireDate()));
				value.setValidDate(ConvertUtil.sdlLong2JavaDate(param.getValidDate()));
				value.setObjectId(objectId);
				value.setObjectType(objectType);
				value.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
				value.setProductId(param.getProdId());
				value.setSoNbr(context.getDoneCode());
				value.setSoDate(context.getRequestDate());
				value.setParamId(sPriceParam.getKey());
				value.setParamValue(String.valueOf(sPriceParam.getValue()));
				baseCmp.insert(value);
			} else if(dmPriceParam.getValidDate().getTime() == ConvertUtil.sdlLong2JavaDate(param.getValidDate()).getTime()){
				imsLogger.debug("跨月优惠修正**********" );
				CoProdPriceParam upValue = new CoProdPriceParam();
				upValue.setParamValue(String.valueOf(sPriceParam.getValue()));
				baseCmp.updateDirectByCondition(upValue,
						new DBCondition(CoProdPriceParam.Field.productId, param.getProdId()), new DBCondition(
								CoProdPriceParam.Field.paramId, sPriceParam.getKey()),
						new DBCondition(CoProdPriceParam.Field.validDate, 
								ConvertUtil.sdlLong2JavaDate(param.getValidDate())));
				imsLogger.dump("aaaa", context.getAllCacheList(CoProdPriceParam.class));
			}else {
				
				imsLogger.debug("跨月优惠正常处理**********" );
				CoProdPriceParam upValue = new CoProdPriceParam();
				upValue.setExpireDate(ConvertUtil.sdlLong2JavaDate(param.getExpireDate()));
				upValue.setValidDate(ConvertUtil.sdlLong2JavaDate(param.getValidDate()));
				upValue.setSoNbr(context.getDoneCode());
				upValue.setSoDate(context.getRequestDate());
				upValue.setParamId(sPriceParam.getKey());
				upValue.setParamValue(String.valueOf(sPriceParam.getValue()));
				baseCmp.updateByCondition(upValue,ConvertUtil.sdlLong2JavaDate(param.getValidDate()),
						new DBCondition(CoProdPriceParam.Field.productId, param.getProdId()), new DBCondition(
								CoProdPriceParam.Field.paramId, sPriceParam.getKey()));
				imsLogger.dump("bbbb", context.getAllCacheList(CoProdPriceParam.class));
			}
		}
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		// TODO Auto-generated method stub
		return new Do_sdlResponse();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
