package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.common.define.UserSwitchEnum;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.itable.entity.IUserSwitch;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;

/**
 * @Description 用户信息服务开关处理组件
 * @author lijc3
 * @Date 2012-7-30
 */
public class UserSwitchCmp extends BaseCmp {

	/**
	 * lijc3 2012-7-30 创建产品
	 * 
	 * @param us
	 * @return no insert
	 */
	public CoProd createSwitchProd(IUserSwitch us, Integer offeringId, CoProd mainProd) {
		CoProd coProd = new CoProd();
		coProd.setProductId(us.getProductId());
		coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
		coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		coProd.setProductOfferingId(offeringId);
		// coProd.setPricingPlanId(0);
		coProd.setBusiFlag(SpecCodeExDefine.USER_SWITCH);
		coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		coProd.setValidDate(us.getValidDate());
		coProd.setExpireDate(us.getExpireDate());
		coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
		coProd.setObjectId(us.getUserId());
		coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		coProd.setProdValidDate(us.getValidDate());
		coProd.setProdExpireDate(us.getExpireDate());
		coProd.setValidDate(us.getValidDate());
		coProd.setExpireDate(us.getExpireDate());
		coProd.setSoNbr(us.getSoNbr());
		coProd.setSoDate(context.getCommitDate());
		coProd.setCreateDate(context.getCommitDate());
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		// CoProd mainProd=prodCmp.queryPrimaryProductByUserId(us.getUserId());
		Map valueMap = new HashMap();
		if (mainProd != null) {
			valueMap.put(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID, mainProd.getProductOfferingId());
		}
		Integer pricePlanId = prodCmp.queryPricePlanId(offeringId, valueMap);
		if (pricePlanId != null) {
			coProd.setPricingPlanId(pricePlanId);
		} else {
			coProd.setPricingPlanId(0);
		}
		return coProd;
	}

	/**
	 * lijc3 2012-7-30 新增信服服务开关
	 * 
	 * @param us
	 */
	public void createUserSwitch(IUserSwitch us) {

		//List<Integer> offerIdList = queryUserSwitchOfferList(us.getServiceCode(), us.getStatus());
		Integer offerId=UserSwitchEnum.getOfferId(us.getServiceCode());
		if (offerId==EnumCodeExDefine.USER_SWITCH_ERROR_OFFERID) {
			imsLogger.info("*****************no offerId is by configration of service_code = ", us.getServiceCode(), "and status = ",
					us.getStatus());
			return;
		}
		List<CoProd> dmProdList = new ArrayList<CoProd>();
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		CoProd mainProd = prodCmp.queryPrimaryProductByUserId(us.getUserId());
		CoProd dmProd = createSwitchProd(us, offerId, mainProd);
		dmProdList.add(dmProd);
		this.insertBatch(dmProdList);
	}

	/**
	 * lijc3 2012-7-30 查询信息服务对应的销售品
	 * 
	 * @param us
	 * @return
	 */
	public List<Integer> queryUserSwitchOfferList(Integer servCode, Integer status) {
		List<PmProductOfferSpecChar> specCharList = null;
		if (servCode == 0) {
			// 信息服务总开关，先按单个开关处理，如果单个开关有配置，则按单个开关处理，如果没有配置总开关，则订购所有的开关
			specCharList = this.query(PmProductOfferSpecChar.class, new DBCondition(PmProductOfferSpecChar.Field.specCharId,
					SpecCodeExDefine.USER_SWITCH_SERV_CODE), new DBCondition(PmProductOfferSpecChar.Field.value, servCode));
			if (CommonUtil.isEmpty(specCharList)) {
				specCharList = this.query(PmProductOfferSpecChar.class, new DBCondition(PmProductOfferSpecChar.Field.specCharId,
						SpecCodeExDefine.USER_SWITCH_SERV_CODE));
			}
		} else {
			specCharList = this.query(PmProductOfferSpecChar.class, new DBCondition(PmProductOfferSpecChar.Field.specCharId,
					SpecCodeExDefine.USER_SWITCH_SERV_CODE), new DBCondition(PmProductOfferSpecChar.Field.value, servCode));
		}
		if (CommonUtil.isEmpty(specCharList)) {
			return null;
		}
		// 获取所有的开和关对应的销售品
		Set<Integer> allOfferIdList = new HashSet<Integer>();
		for (PmProductOfferSpecChar specChar : specCharList) {
			allOfferIdList.add(specChar.getProductOfferingId());
		}
		// 查出相应状态的销售品
		specCharList = this.query(PmProductOfferSpecChar.class, new DBCondition(PmProductOfferSpecChar.Field.productOfferingId,
				allOfferIdList, Operator.IN), new DBCondition(PmProductOfferSpecChar.Field.specCharId,
				SpecCodeExDefine.USER_SWITCH_OPEN_FLAG), new DBCondition(PmProductOfferSpecChar.Field.value, status));
		if (CommonUtil.isEmpty(specCharList)) {
			return null;
		}
		List<Integer> offerList = new ArrayList<Integer>();
		for (PmProductOfferSpecChar specChar : specCharList) {
			offerList.add(specChar.getProductOfferingId());
		}
		return offerList;
	}

	/**
	 * lijc3 2012-7-30 删除信息服务开关产品
	 * 
	 * @param us
	 */
	public void deleteUserSwitch(IUserSwitch us) {
//		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
//		List<Integer> offerIdList = new ArrayList<Integer>();
//		Integer offerId=UserSwitchEnum.getOfferId(us.getServiceCode());
//		offerIdList.add(offerId);
//		if (offerId==EnumCodeExDefine.USER_SWITCH_ERROR_OFFERID) {
//			imsLogger.info("*****************no offerId is by configration of service_code = ", us.getServiceCode(), "and status = ",
//					us.getStatus());
//			return;
//		}
//		List<CoProd> dmProdList = prodCmp.queryProdByUserIdAndOfferId(us.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV, offerIdList);
//		if (CommonUtil.isNotEmpty(dmProdList)) {
//			Set<Long> idList = new HashSet<Long>();
//			for (CoProd dmProd : dmProdList) {
//				idList.add(dmProd.getProductId());
//			}
//			this.deleteByCondition(CoProd.class, context.getCommitDate(), new DBCondition(CoProd.Field.productId, idList, Operator.IN));
//
//			CoProd valid = new CoProd();
//			valid.setProdExpireDate(context.getCommitDate());
//			updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, idList, Operator.IN));
//		}
		CoProd valid = new CoProd();
		valid.setExpireDate(us.getExpireDate());
		valid.setProdExpireDate(us.getExpireDate());
		updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId,us.getProductId()));
		
		
		this.deleteByCondition(CoProd.class,us.getExpireDate(), new DBCondition(CoProd.Field.productId, us.getProductId()),
				new DBCondition(CoProd.Field.validDate,us.getExpireDate(),Operator.LESS_EQUALS));
		DBUtil.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId,  us.getProductId()),
				new DBCondition(CoProd.Field.validDate, us.getExpireDate(),Operator.GREAT));	
		}

	public void updateUserSwitch(IUserSwitch userSwitch) {
		Integer offerId=UserSwitchEnum.getOfferId(userSwitch.getServiceCode());
		if (offerId==EnumCodeExDefine.USER_SWITCH_ERROR_OFFERID) {
			imsLogger.info("*****************no offerId is by configration of service_code = ", userSwitch.getServiceCode(), "and status = ",
					userSwitch.getStatus());
			return;
		}
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		CoProd mainProd = prodCmp.queryPrimaryProductByUserId(userSwitch.getUserId());
		CoProd dmProd = createSwitchProd(userSwitch, offerId, mainProd);
		super.updateMode3(dmProd, new DBCondition(CoProd.Field.productId, userSwitch.getProductId()));
	}
}
