package com.ailk.imssh.credit.cmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.easyframe.route.entity.br.RouterResource;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.acctpayrel.helper.CharValueHelper;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAssetSyn;
import com.ailk.openbilling.persistence.acct.entity.CaBatchSyn;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.itable.entity.ICreditServ;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecTypeGroups;

/**
 * @Description:用户信用服务
 * @author yuxz
 * @Date 2016-05-21
 */
public class CreditServCmp extends BaseCmp
{
	
	public void createCreditServ(ICreditServ iCreditServ) {
//		Integer offerId = queryUserCreditServOfferId(SpecCodeExDefine.USER_CREDIT_SERV);
//		if (offerId == null) {
//			imsLogger.info("*****************no offerId is by configration of 370 credit_serv_product");
//			return;
//		}
		Integer offerId = iCreditServ.getCreditServId();
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		CoProd mainProd = prodCmp.queryPrimaryProductByUserId(iCreditServ.getUserId());
		CoProd dmProd = createCreditServProd(iCreditServ, offerId, mainProd);
		this.insert(dmProd);
		insertCreditServProdCharValueList(iCreditServ,dmProd);
	}

	

	private void insertCreditServProdCharValueList(ICreditServ iCreditServ, CoProd dmProd) {
		CharValueHelper helper = new CharValueHelper(CoProdCharValue.class, dmProd.getProductId(), 0L, iCreditServ.getValidDate(),
				iCreditServ.getExpireDate(), iCreditServ.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		List<DataObject> list = new ArrayList<DataObject>();
		if(iCreditServ.getCreditServId() == EnumCodeExDefine.CREDIT_SERV_7901){
			list.add(helper.getNew(SpecCodeDefine.CREDIT_LIMIT_TIME, iCreditServ.getLimitTime()));
		}
		list.add(helper.getNew(SpecCodeDefine.CREDIT_SERV_ID, iCreditServ.getCreditServId()));
		list.add(helper.getNew(SpecCodeDefine.CREDIT_VALUE, iCreditServ.getCreditValue()));
		this.insertBatch(list);
	}



	private CoProd createCreditServProd(ICreditServ iCreditServ, Integer offerId, CoProd mainProd) {
		CoProd coProd = new CoProd();
		coProd.setProductId(DBUtil.getSequence(CoProd.class));
		coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
		coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		coProd.setProductOfferingId(offerId);
		// coProd.setPricingPlanId(0);
		coProd.setBusiFlag(SpecCodeExDefine.USER_SWITCH_TJ);
		coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		coProd.setValidDate(iCreditServ.getValidDate());
		coProd.setExpireDate(iCreditServ.getExpireDate());
		coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
		coProd.setObjectId(iCreditServ.getUserId());
		coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		coProd.setProdValidDate(iCreditServ.getValidDate());
		coProd.setProdExpireDate(iCreditServ.getExpireDate());
		coProd.setValidDate(iCreditServ.getValidDate());
		coProd.setExpireDate(iCreditServ.getExpireDate());
		ProdCmp prodCmp = context.getCmp(ProdCmp.class);
		Map valueMap = new HashMap();
		if (mainProd != null) {
			valueMap.put(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID, mainProd.getProductOfferingId());
		}
		Integer pricePlanId = prodCmp.queryPricePlanId(offerId, valueMap);
		if (pricePlanId != null) {
			coProd.setPricingPlanId(pricePlanId);
		} else {
			coProd.setPricingPlanId(0);
		}
		return coProd;
	}

	private Integer queryUserCreditServOfferId(int userCreditServ) {
		PmProductSpecTypeGroups prodSpecIdGroup = this.querySingle(PmProductSpecTypeGroups.class, new DBCondition(PmProductSpecTypeGroups.Field.specTypeId,
				SpecCodeExDefine.USER_CREDIT_SERV));
		if (prodSpecIdGroup == null) {
			imsLogger.error("***the 370 product_offering not exist***");
			return null;
		}
		PmProductOffering offerId = this.querySingle(PmProductOffering.class, new DBCondition(PmProductOffering.Field.prodSpecId,
				prodSpecIdGroup.getProdSpecId()));
		if (offerId == null) {
			imsLogger.error("***the 370 product_offering not exist***");
			return null;
		}
		
		return offerId.getProductOfferingId();
	}



	public void modifyCreditServ(ICreditServ iCreditServ) {
		//Long productId = getProductIdByCreditServId(iCreditServ);
		// update 不更新coprod表的记录
		List<CoProd> dmProdList = new ArrayList<CoProd>();
		HashSet<Long> prodIds = new HashSet<Long>();
		CoProd coProd = new CoProd();
		coProd.setExpireDate(iCreditServ.getExpireDate());
		dmProdList = super.updateByCondition(coProd, iCreditServ.getExpireDate(),new DBCondition(CoProd.Field.productOfferingId,iCreditServ.getCreditServId()),
				new DBCondition(CoProd.Field.objectId,iCreditServ.getUserId()),new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV));
		if(CommonUtil.isEmpty(dmProdList)){
			return;
		}
	
		for(CoProd prod : dmProdList){
			if(prodIds.contains(prod.getProductId())){
				continue;
			}else{
				prodIds.add(prod.getProductId());
			}
			
		}
		CharValueHelper helper = new CharValueHelper(CoProdCharValue.class, null, 0L, iCreditServ.getValidDate(),
				iCreditServ.getExpireDate(), iCreditServ.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		updateByCondition(helper.getUpdate(iCreditServ.getCreditValue()), new DBCondition(CoProdCharValue.Field.productId, prodIds,Operator.IN),
				new DBCondition(CoProdCharValue.Field.groupId, 0L), new DBCondition(CoProdCharValue.Field.specCharId,
						SpecCodeDefine.CREDIT_VALUE));
		updateByCondition(helper.getUpdate(iCreditServ.getCreditServId()), new DBCondition(CoProdCharValue.Field.productId, prodIds,Operator.IN),
				new DBCondition(CoProdCharValue.Field.groupId, 0L), new DBCondition(CoProdCharValue.Field.specCharId,
						SpecCodeDefine.CREDIT_SERV_ID));
		if (iCreditServ.getCreditServId() == EnumCodeExDefine.CREDIT_SERV_7901) {
			updateByCondition(helper.getUpdate(iCreditServ.getLimitTime()), new DBCondition(CoProdCharValue.Field.productId, prodIds,Operator.IN),
					new DBCondition(CoProdCharValue.Field.groupId, 0L), new DBCondition(CoProdCharValue.Field.specCharId,
							SpecCodeDefine.CREDIT_LIMIT_TIME));
		}
	}

	private Long getProductIdByCreditServId(ICreditServ iCreditServ) {
		Integer offerId = queryUserCreditServOfferId(SpecCodeExDefine.USER_CREDIT_SERV);
		List<CoProd> dmProdList = new ArrayList<CoProd>();
		List<Long> prodIds = new ArrayList<Long>();
		dmProdList = this.query(CoProd.class, new DBCondition(CoProd.Field.productOfferingId,offerId),
				new DBCondition(CoProd.Field.objectId,iCreditServ.getUserId()),new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV));
		if(CommonUtil.isEmpty(dmProdList)){
			return null;
		}
		for (CoProd dmProd : dmProdList) {
			prodIds.add(dmProd.getProductId());
		}
		CoProdCharValue charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeExDefine.CREDIT_SERV_ID),
				new DBCondition(CoProdCharValue.Field.productId,prodIds,Operator.IN),new DBCondition(CoProdCharValue.Field.value,iCreditServ.getCreditServId()));
		if(charValue == null){
			return null;
		}
		
		return charValue.getProductId();
		
	}



	public void deleteCreditServ(ICreditServ iCreditServ) {
		//Long productId = getProductIdByCreditServId(iCreditServ);
		ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
	    //prodCmp.deleteProdById(productId, iCreditServ.getExpireDate());
	    //prodCmp.modifyProdValid(productId, iCreditServ.getExpireDate());
	    List<CoProd> prodList = prodCmp.deleteByCondition(CoProd.class, iCreditServ.getExpireDate(),new DBCondition(CoProd.Field.productOfferingId,iCreditServ.getCreditServId()),
				new DBCondition(CoProd.Field.objectId,iCreditServ.getUserId()),new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV));
	    List<Long> productIds = new ArrayList<Long>();
	    if(!CommonUtil.isEmpty(prodList)){
		    for(CoProd prod : prodList){
		    	productIds.add(prod.getProductId());
		    }
	    }
		this.deleteByCondition(CoProdCharValue.class, iCreditServ.getExpireDate(), new DBCondition(CoSplitCharValue.Field.productId, productIds,Operator.IN));
	}
    
	
	public void insertCassetSyn(ICreditServ icreditServ){
	    
	        RouterCmp routerCmp = context.getCmp(RouterCmp.class);
	        RouterResource resource = routerCmp.querySegAcctByUser(icreditServ.getUserId());
	        	CaBatchSyn caAssetSyn = new CaBatchSyn();
		        caAssetSyn.setSynId(DBUtil.getSequence(CaAssetSyn.class)); 
		        caAssetSyn.setAcctId(resource.getAcctId());
		        caAssetSyn.setAcctFlag(0);
				caAssetSyn.setBizeType(0);
				caAssetSyn.setBizeInfo(CreditCmp.BIZE_INFO);
		      
		        caAssetSyn.setCreateDate(context.getCommitDate());
		        caAssetSyn.setValidDate(icreditServ.getValidDate());
		        caAssetSyn.setExpireDate(icreditServ.getExpireDate());
		        caAssetSyn.setSts(0);
		        caAssetSyn.setStsDate(context.getCommitDate());
		        caAssetSyn.setSoNbr(context.getSoNbr());
		        caAssetSyn.setSyncFlag(CreditCmp.SYNC_FLAG);
		         
		  
		        caAssetSyn.setNotifyFlag(CreditCmp.NOTIFY_FLAG);
		        if(icreditServ.getCreditServId().intValue() == CreditCmp.CREDIT_SERV_7901 || icreditServ.getCreditServId().intValue() == CreditCmp.CREDIT_SERV_7913){
		        	caAssetSyn.setSyncType(CreditCmp.SYNC_TYPE_CREDIT_SERV_SPEC);
		        }else{
		        	caAssetSyn.setSyncType(CreditCmp.SYNC_TYPE_CREDIT_SERV);
		        }
		        
		        caAssetSyn.setSoDate(context.getCommitDate());

		        caAssetSyn.setRegionCode(EnumCodeExDefine.TJ_REGION_CODE);
		        super.insert(caAssetSyn);
	         
	}
}
