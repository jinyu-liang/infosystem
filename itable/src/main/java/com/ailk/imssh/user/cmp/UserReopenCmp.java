package com.ailk.imssh.user.cmp;

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
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.itable.entity.IUserReopen;

/**
 * @Description 用户封顶重开转销售品
 * @author yuxz
 * @Date 2016-03-29
 */
public class UserReopenCmp extends BaseCmp {
	/**
	 * 新增封顶重开
	 * @param iUserReopen
	 */
	public void createUserReopen(IUserReopen iUserReopen) {
		Integer offerId = queryUserReopenOfferId(iUserReopen.getReopenType());
		if (offerId == null || offerId == 0) {
			imsLogger.info("*****************no offerId is by configration of reopenType = ", iUserReopen.getReopenType());
			return;
		}
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		CoProd mainProd = prodCmp.queryPrimaryProductByUserId(iUserReopen.getUserId());
		CoProd dmProd = createReopenProd(iUserReopen, offerId, mainProd);
		
		this.insert(dmProd);
	}
	
	
	/**
	 * 创建封顶重开产品
	 * @param iUserReopen
	 * @param offeringId
	 * @param mainProd
	 * @return
	 */
	private CoProd createReopenProd(IUserReopen iUserReopen, Integer offeringId, CoProd mainProd) {
		CoProd coProd = new CoProd();
		coProd.setProductId(DBUtil.getSequence(CoProd.class));
		coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
		coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		coProd.setProductOfferingId(offeringId);
		coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
		coProd.setObjectId(iUserReopen.getUserId());
		coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		coProd.setProdValidDate(iUserReopen.getValidDate());
		coProd.setProdExpireDate(iUserReopen.getExpireDate());
		coProd.setValidDate(iUserReopen.getValidDate());
		coProd.setExpireDate(iUserReopen.getExpireDate());
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		coProd.setBusiFlag(101);
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
	 * 查询封顶重开产品对应的销售品编号
	 * yuxz 2016-03-29
	 * @param reopenType
	 * @return
	 */
	private Integer queryUserReopenOfferId(Integer reopenType) {
		if (reopenType == EnumCodeExDefine.REOPEN_TYPE_GUOMAN) {
			return SysUtil.getInt("REOPEN_OFFER_ID_GUOMAN",EnumCodeExDefine.REOPEN_OFFER_ID_GUOMAN_HN);
		}else if (reopenType == EnumCodeExDefine.REOPEN_TYPE_15G) {
			return SysUtil.getInt("REOPEN_OFFER_ID_15G",EnumCodeExDefine.REOPEN_OFFER_ID_15G_HN);
		}else {
			return null;
		}
		
	}

	/**
	 * 修改产品
	 * yuxz 
	 * @param iUserReopen
	 */
	public void updateUserReopen(IUserReopen iUserReopen) {
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		Integer offerId = queryUserReopenOfferId(iUserReopen.getReopenType());
		if (offerId == null || offerId == 0) {
			imsLogger.info("*****************no offerId is by configration of reopenType = ", iUserReopen.getReopenType());
			return;
		}
		List<CoProd> dmProdList = prodCmp.queryProdByUserIdAndOfferId(iUserReopen.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV, offerId);
		if (CommonUtil.isNotEmpty(dmProdList)) {
			for (CoProd dmProd : dmProdList) {
				dmProd.setProdExpireDate(iUserReopen.getExpireDate());
				dmProd.setExpireDate(iUserReopen.getExpireDate());
				this.updateByCondition(dmProd, new DBCondition(CoProd.Field.productId, dmProd.getProductId()));
			}
		}
		
	}


	public void deleteUserReopen(IUserReopen iUserReopen) {
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		Integer offerId = queryUserReopenOfferId(iUserReopen.getReopenType());
		if (offerId == null || offerId == 0) {
			imsLogger.info("*****************no offerId is by configration of reopenType = ", iUserReopen.getReopenType());
			return;
		}
		List<CoProd> dmProdList = prodCmp.queryProdByUserIdAndOfferId(iUserReopen.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV, offerId);
		if (CommonUtil.isNotEmpty(dmProdList)) {
			Set<Long> idList = new HashSet<Long>();
			for (CoProd dmProd : dmProdList) {
				idList.add(dmProd.getProductId());
			}
			this.deleteByCondition(CoProd.class, context.getCommitDate(), new DBCondition(CoProd.Field.productId, idList, Operator.IN));

			CoProd valid = new CoProd();
			valid.setProdExpireDate(context.getCommitDate());
			updateDirectByCondition(valid, new DBCondition(CoProd.Field.productId, idList, Operator.IN));
		}
	}

	
}
