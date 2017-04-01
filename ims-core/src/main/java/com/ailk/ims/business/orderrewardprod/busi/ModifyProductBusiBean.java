package com.ailk.ims.business.orderrewardprod.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SChangeProdInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SParamInfo;

/**
 * @Description 供上海后台调用的产品相关的操作方法 默认销售品ID没有传入，订购108类型的销售品
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lijingcheng
 * @Date 2012-5-17
 */
public class ModifyProductBusiBean extends BaseCancelableBusiBean {

	private BaseProductComponent prodCmp;

	public void cancel(CancelOrderInfo cancelInfo) {

	}

	@Override
	public void init(Object... input) throws BaseException {
		prodCmp = context.getComponent(BaseProductComponent.class);
	}

	@Override
	public void validate(Object... input) throws BaseException {
		CsdlArrayList<SChangeProdInfo> infoList = (CsdlArrayList<SChangeProdInfo>) input[0];
		imsLogger.dump("SChangeProdInfo", infoList);
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException {
		/*
		 * CsdlArrayList<SChangeProdInfo> infoList =
		 * (CsdlArrayList<SChangeProdInfo>) input[0];
		 * List<IMS3hBean> list = new ArrayList<IMS3hBean>();
		 * if (CommonUtil.isNotEmpty(infoList)) {
		 * for (SChangeProdInfo info : infoList) {
		 * if (info.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV) {
		 * User3hBean userBean =
		 * context.get3hTree().loadUser3hBean(info.getObjectId());
		 * list.add(userBean);
		 * } else {
		 * Acct3hBean acctBean =
		 * context.get3hTree().loadAcct3hBean(info.getObjectId());
		 * list.add(acctBean);
		 * }
		 * }
		 * }
		 * return list;
		 */
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		CsdlArrayList<SChangeProdInfo> infoList = (CsdlArrayList<SChangeProdInfo>) input[0];
		if (CommonUtil.isNotEmpty(infoList)) {

			BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
			for (SChangeProdInfo info : infoList) {
				if (info.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV) {
					IMSUtil.setUserRouterParam(Long.valueOf(info.getObjectId()));
				} else {
					IMSUtil.setAcctRouterParam(Long.valueOf(info.getObjectId()));
				}
				CoProd mainProd = prodCmp.queryPrimaryProductByUserId((long) info.getObjectId());

				Date expireDate = info.getExpireDate();
				if (expireDate == null) {
					expireDate = IMSUtil.getDefaultExpireDate();
				}
				Date validDate = info.getValidDate();
				if (validDate == null) {
					validDate = context.getRequestDate();
				}
				Integer maniOfferId = null;
				if (mainProd != null) {
					maniOfferId = mainProd.getProductOfferingId();
				}
				Integer pricePlanId = prodCmp.queryPricePlanId(info.getOfferId(), null, null, null, null, null, null, maniOfferId, null,
						null);
				// 2012-07-25 luojb #51386 增加count
				CoProd dmProd = createCoProd4Sh(info.getOfferId(), (long) info.getObjectId(), (int) info.getObjectType(), validDate,
						expireDate, pricePlanId);
				prodCmp.insert(dmProd);

				if (CommonUtil.isNotEmpty(info.getParamList())) {
					for (SParamInfo param : info.getParamList()) {
						CoProdPriceParam value = new CoProdPriceParam();
						value.setCreateDate(context.getRequestDate());
						value.setExpireDate(param.getExpireDate());
						value.setValidDate(param.getValidDate());
						value.setObjectId(info.getObjectId());
						value.setObjectType(info.getObjectType());
						value.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
						value.setProductId(dmProd.getProductId());
						value.setSoNbr(context.getSoNbr());
						value.setSoDate(context.getRequestDate());
						value.setParamId(param.getParamId());
						value.setParamValue(String.valueOf(param.getValue()));
						prodCmp.insert(value);
					}
				}
/*
				int operType = info.getOperType();
				SProductOrder order = packageSProductOrder(info);
				if (operType == EnumCodeDefine.CHANGE_PROD_ADD) {
					SProductOrderList orderList = new SProductOrderList();
					orderList.setItem(new SProductOrder[] { order });
					prodCmp.addProductInfo(orderList);
				} else if (operType == EnumCodeDefine.CHANGE_PROD_DELETE) {
					prodCmp.deleteProdOrder(order);
				} else {
					prodCmp.modifyProduct(order, null);
				}
*/
			}
		}
		return null;
	}

	private CoProd createCoProd4Sh(int offerId, long objectId, int objectType, Date validDate, Date expireDate, Integer pricePlanId) {
		Offering3hbean offerBean = context.get3hTree().loadOffering3hBean((long) offerId);
		CoProd prod = new CoProd();
		prod.setBillingType(offerBean.getBillingType());
		prod.setBusiFlag(offerBean.getBusiFlag());
		prod.setCreateDate(context.getRequestDate());
		prod.setExpireDate(expireDate);
		prod.setIsMain(offerBean.getOffering().getIsMain());
		prod.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		prod.setObjectId(objectId);
		prod.setObjectType(objectType);
		prod.setProdExpireDate(expireDate);
		prod.setProdTypeId(offerBean.getOffering().getOfferTypeId());
		prod.setProductId(DBUtil.getSequence(CoProd.class));
		prod.setProdValidDate(validDate);
		prod.setProductOfferingId(offerId);
		prod.setValidDate(validDate);
		prod.setProdValidDate(validDate);
		prod.setSoNbr(context.getSoNbr());
		prod.setSoDate(context.getRequestDate());
		prod.setPricingPlanId(pricePlanId == null ? 0 : pricePlanId);
		return prod;
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		return new Do_sdlResponse();
	}

	@Override
	public void destroy() {
		prodCmp = null;
	}

	private SProductOrder packageSProductOrder(SChangeProdInfo info) {
		int operType = info.getOperType();
		SProductOrder order = new SProductOrder();
		if (operType == EnumCodeDefine.CHANGE_PROD_ADD) {
			order.setProduct_id(DBUtil.getSequence(CoProd.class));
		} else {
			order.setProduct_id(info.getProductId());
		}
		int objectType = info.getObjectType();
		if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV) {
			order.setUser_id(info.getObjectId());
		} else {
			order.setAcct_id(info.getObjectId());
		}
		order.setBilling_type((short) EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		if (CommonUtil.isValid(info.getOfferId())) {
			order.setOffer_id((long) info.getOfferId());
		} else {
			Integer offerId = prodCmp.queryOfferingId(108);
			imsLogger.info("**************offer_id by busi_flag is " + offerId);
			offerId = offerId == null ? 0 : offerId;
			order.setOffer_id(offerId.longValue());
		}
		order.setValid_date(IMSUtil.formatDate(context.getRequestDate()));
		order.setValid_type((short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE);
		order.setExpire_date(IMSUtil.formatDate(IMSUtil.getDefaultExpireDate()));
		List<SParamInfo> list = info.getParamList();
		if (CommonUtil.isNotEmpty(list)) {
			List<SProductParam> paramList = new ArrayList<SProductParam>();
			for (SParamInfo sf : list) {
				SProductParam param = new SProductParam();
				param.setParam_id(sf.getParamId());
				param.setParam_value(sf.getValue());
				param.setValid_date(IMSUtil.formatDate(context.getRequestDate()));
				param.setExpire_date(IMSUtil.formatDate(IMSUtil.getDefaultExpireDate()));
				paramList.add(param);
			}
			SProductParamList pl = new SProductParamList();
			pl.setSProductParamList_Item(paramList.toArray(new SProductParam[paramList.size()]));
			order.setParam_list(pl);
		}
		return order;
	}

}
