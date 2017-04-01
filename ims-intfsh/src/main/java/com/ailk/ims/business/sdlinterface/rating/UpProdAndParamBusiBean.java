package com.ailk.ims.business.sdlinterface.rating;

import java.util.Date;
import java.util.List;

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
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SPriceParam;
import com.ailk.openbilling.persistence.imssdl.entity.SUpSellingPriceParam;

/**
 * 
 * @Description 更新产品的失效时间和二次议价参数
 * @author lijc3
 * @Date 2013-12-11
 */
public class UpProdAndParamBusiBean extends BaseBusiBean {

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
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// SUpSellingPriceParam param=(SUpSellingPriceParam)input[0];
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		BaseComponent baseCmp = context.getComponent(BaseComponent.class);
		SUpSellingPriceParam param = (SUpSellingPriceParam) input[0];
		long objectId = param.getObjectId();
		int objectType = param.getObjectType();
		if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV) {
			IMSUtil.setUserRouterParam(objectId);
		} else {
			IMSUtil.setAcctRouterParam(objectId);
		}

		Date validDate = ConvertUtil.sdlLong2JavaDate(param.getValidDate());
		Date expireDate = ConvertUtil.sdlLong2JavaDate(param.getExpireDate());
		List<CoProd> prodList = DBUtil.query(CoProd.class, new DBCondition(
				CoProd.Field.productId, param.getProdId()), new DBCondition(
				CoProd.Field.validDate, validDate));
		if (CommonUtil.isNotEmpty(prodList)) {
			CoProd value = new CoProd();
			value.setExpireDate(expireDate);
			value.setProdExpireDate(expireDate);
			DBUtil.updateByCondition(value, new DBCondition(
					CoProd.Field.productId, param.getProdId()),
					new DBCondition(CoProd.Field.validDate, validDate));

			for (CoProd prod : prodList) {
				prod.setExpireDate(expireDate);
				prod.setProdExpireDate(expireDate);
			}
			context.cacheList(prodList);
		}

		List<SPriceParam> paramList = param.getPriceParam();
		if (CommonUtil.isEmpty(paramList)) {
			return null;
		}
		for (SPriceParam sPriceParam : paramList) {
			CoProdPriceParam dmPriceParam = baseCmp.querySingle(
					CoProdPriceParam.class,
					new DBCondition(CoProdPriceParam.Field.productId, param
							.getProdId()),
					new DBCondition(CoProdPriceParam.Field.paramId, sPriceParam
							.getKey()));
			if (dmPriceParam != null) {
				CoProdPriceParam upValue = new CoProdPriceParam();
				upValue.setParamValue(String.valueOf(sPriceParam.getValue()));
				baseCmp.updateDirectByCondition(upValue, new DBCondition(
						CoProdPriceParam.Field.productId, param.getProdId()),
						new DBCondition(CoProdPriceParam.Field.paramId,
								sPriceParam.getKey()));
				imsLogger.dump("aaaa",
						context.getAllCacheList(CoProdPriceParam.class));
			} else {
				CoProdPriceParam value = new CoProdPriceParam();
				value.setCreateDate(context.getRequestDate());
				value.setExpireDate(ConvertUtil.sdlLong2JavaDate(param
						.getExpireDate()));
				value.setValidDate(ConvertUtil.sdlLong2JavaDate(param
						.getValidDate()));
				value.setObjectId(objectId);
				value.setObjectType(objectType);
				value.setPriceParamId(DBUtil
						.getSequence(CoProdPriceParam.class));
				value.setProductId(param.getProdId());
				value.setSoNbr(context.getDoneCode());
				value.setSoDate(context.getRequestDate());
				value.setParamId(sPriceParam.getKey());
				value.setParamValue(String.valueOf(sPriceParam.getValue()));
				baseCmp.insert(value);
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
