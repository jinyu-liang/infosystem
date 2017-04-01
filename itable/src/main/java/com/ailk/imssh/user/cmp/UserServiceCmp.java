package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResService;
import com.ailk.openbilling.persistence.cust.entity.CmResServiceStatus;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.itable.entity.IUserServStatus;
import com.ailk.openbilling.persistence.itable.entity.IUserService;

public class UserServiceCmp extends BaseCmp {

	private CoProd getMainProd(Map<Long, CoProd> mainProdMap, Long userId) {
		CoProd dmProd = mainProdMap.get(userId);
		if (dmProd == null) {
			dmProd = context.getCmp(ProdCmp.class).queryPrimaryProductByUserId(userId);
			if (dmProd != null) {
				mainProdMap.put(userId, dmProd);
			}
		}
		return dmProd;
	}

	public void createUserService(IUserService service) {
//		CmResService dmService = this.querySingle(CmResService.class, new DBCondition(CmResService.Field.resourceId, service.getUserId()),
//				new DBCondition(CmResService.Field.serviceId, service.getServiceId()));
//		if (dmService != null) {
//			imsLogger.error("用户编号： ", service.getUserId(), " 已经实例化了服务编号： ", service.getServiceId(), " 不再实例化");
//			return;
//		}
		insertUserService(service);
		//insertServiceProd(service, mainProdMap);
		//int serviceId = service.getServiceId();
		/*
		if(serviceId == EnumCodeExDefine.USER_SERVICE_201306 || serviceId == EnumCodeExDefine.USER_SERVICE_2011 || serviceId == EnumCodeExDefine.USER_SERVICE_2010){
			
			CoProd prod = new CoProd();
			prod.setProductId(service.getServiceInstId());
			prod.setProdTypeId(0);
			prod.setLifecycleStatusId(1);
			prod.setProductOfferingId(serviceId);
			prod.setPricingPlanId(0);
			prod.setBusiFlag(SpecCodeExDefine.PROD_BUSI_FLAG_108);
			prod.setIsMain(0);
			prod.setCreateDate(service.getValidDate());
			prod.setValidDate(service.getValidDate());
			prod.setExpireDate(service.getExpireDate());
			prod.setSoNbr(context.getSoNbr());
			prod.setSoDate(context.getCommitDate());
			prod.setObjectId(service.getUserId());
			prod.setObjectType(0);
			super.insert(prod);
		}
		*/
	}

	private void insertServiceProd(IUserService service, Map<Long, CoProd> mainProdMap) {
		Set<Integer> offerIds = context.getCmp(ProdCmp.class).queryOfferListByServiceIds(service.getServiceId());
		if (CommonUtil.isEmpty(offerIds)) {
			return;
		}

		CoProd mainProd = getMainProd(mainProdMap, service.getUserId());
		List<CoProd> prodList = new ArrayList<CoProd>();
		for (Integer offerId : offerIds) {
			CoProd dmProd = createProd(service.getUserId(), offerId, mainProd, service.getValidDate(), service.getExpireDate());
			prodList.add(dmProd);
		}

		if (CommonUtil.isNotEmpty(prodList)) {
			this.insertBatch(prodList);
		}

	}

	private CoProd createProd(Long userId, Integer offerId, CoProd mainProd, Date validDate, Date expireDate) {
		CoProd coProd = new CoProd();
		coProd.setProductId(DBUtil.getSequence(CoProd.class));
		coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
		coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		coProd.setProductOfferingId(offerId);
		coProd.setBusiFlag(context.get3hTree().loadOffering3hBean(offerId.longValue()).getBusiFlag());
		coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		coProd.setValidDate(validDate);
		coProd.setExpireDate(expireDate);
		coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
		coProd.setObjectId(userId);
		coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		coProd.setProdValidDate(validDate);
		coProd.setProdExpireDate(expireDate);
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

	private void insertUserService(IUserService service) {
		CmResService cmResService = new CmResService();
		cmResService.setCreateDate(context.getCommitDate());
		cmResService.setSoDate(context.getCommitDate());
		cmResService.setServiceInstId(service.getServiceInstId());
		cmResService.setResourceId(service.getUserId());
		cmResService.setServiceId(service.getServiceId());
		cmResService.setMainTag(service.getMainTag());
		cmResService.setValidDate(service.getValidDate());
		cmResService.setExpireDate(service.getExpireDate());
		cmResService.setSoNbr(context.getSoNbr());
		cmResService.setRemark(service.getRemark());
		super.insert(cmResService);
	}

	public void updateUserService(IUserService service) {
		
		CmResService cmResService = new CmResService();
		cmResService.setCreateDate(context.getCommitDate());
		cmResService.setSoDate(context.getCommitDate());
		cmResService.setServiceInstId(service.getServiceInstId());
		cmResService.setResourceId(service.getUserId());
		cmResService.setServiceId(service.getServiceId());
		cmResService.setMainTag(service.getMainTag());
		cmResService.setValidDate(service.getValidDate());
		cmResService.setExpireDate(service.getExpireDate());
		cmResService.setSoNbr(context.getSoNbr());
		cmResService.setRemark(service.getRemark());
		this.updateMode3(cmResService, new DBCondition(CmResService.Field.serviceInstId, service.getServiceInstId()));
		
		/**
		this.deleteByCondition(CmResService.class,
				new DBCondition(CmResService.Field.serviceInstId, service.getServiceInstId()),
				new DBCondition(CmResService.Field.validDate,service.getValidDate()));
				*/
		//updateServiceProd(service);
		/*
		int serviceId = service.getServiceId();
		if(serviceId == EnumCodeExDefine.USER_SERVICE_201306 || serviceId == EnumCodeExDefine.USER_SERVICE_2011 || serviceId == EnumCodeExDefine.USER_SERVICE_2010){
			CoProd prod = new CoProd();
			prod.setProductOfferingId(serviceId);
			prod.setCreateDate(service.getValidDate());
			prod.setExpireDate(service.getExpireDate());
			prod.setSoNbr(context.getSoNbr());
			prod.setSoDate(context.getCommitDate());
			super.updateByCondition(prod,new DBCondition(CoProd.Field.productId,service.getServiceInstId()),
					new DBCondition(CoProd.Field.objectId,service.getUserId()),
					new DBCondition(CoProd.Field.busiFlag,SpecCodeExDefine.PROD_BUSI_FLAG_108));
		}*/
	}

	private Set<Long> getProductIdsByServiceId(Long userId, Integer serviceId) {
		Set<Integer> offerIds = context.getCmp(ProdCmp.class).queryOfferListByServiceIds(serviceId);
		if (CommonUtil.isEmpty(offerIds)) {
			return null;
		}
		List<CoProd> prodList = this.query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
				CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoProd.Field.productOfferingId, offerIds,
				Operator.IN));
		Set<Long> prodIds = new HashSet<Long>();
		if (CommonUtil.isNotEmpty(prodList)) {
			for (CoProd prod : prodList) {
				prodIds.add(prod.getProductId());
			}
		}
		return prodIds;
	}

	private void updateServiceProd(IUserService service) {
		Set<Long> prodIds = getProductIdsByServiceId(service.getUserId(), service.getServiceId());
		if (CommonUtil.isNotEmpty(prodIds)) {
			CoProd update = new CoProd();
			update.setExpireDate(service.getExpireDate());
			this.updateByCondition(update, new DBCondition(CoProd.Field.objectId, service.getUserId()), new DBCondition(
					CoProd.Field.productId, prodIds, Operator.IN));
			update = new CoProd();
			update.setProdExpireDate(service.getExpireDate());
			this.updateDirectByCondition(update, new DBCondition(CoProd.Field.objectId, service.getUserId()), new DBCondition(
					CoProd.Field.productId, prodIds, Operator.IN));
		}
	}

	private void deleteServiceProd(IUserService service) {
		Set<Long> prodIds = getProductIdsByServiceId(service.getUserId(), service.getServiceId());
		if (CommonUtil.isNotEmpty(prodIds)) {
			this.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.objectId, service.getUserId()), new DBCondition(
					CoProd.Field.productId, prodIds, Operator.IN));
			CoProd update = new CoProd();
			update.setProdExpireDate(service.getExpireDate());
			this.updateDirectByCondition(update, new DBCondition(CoProd.Field.objectId, service.getUserId()), new DBCondition(
					CoProd.Field.productId, prodIds, Operator.IN));
		}
	}

	public void deleteUserService(IUserService service) {
		this.deleteByCondition(CmResService.class,service.getExpireDate(),
				new DBCondition(CmResService.Field.serviceInstId, service.getServiceInstId()));
		//deleteServiceProd(service);
		/*
		int serviceId = service.getServiceId();
		if(serviceId == EnumCodeExDefine.USER_SERVICE_201306 || serviceId == EnumCodeExDefine.USER_SERVICE_2011 || serviceId == EnumCodeExDefine.USER_SERVICE_2010){

			super.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId,service.getServiceInstId()),
					new DBCondition(CoProd.Field.objectId,service.getUserId()),
					new DBCondition(CoProd.Field.busiFlag,SpecCodeExDefine.PROD_BUSI_FLAG_108));
		}
		*/
	}

	public void createUserServiceSts(IUserServStatus servSts) {
		CmResServiceStatus cmResServiceStatus = new CmResServiceStatus();
		cmResServiceStatus.setCreateDate(context.getCommitDate());
		cmResServiceStatus.setSoDate(context.getCommitDate());
		cmResServiceStatus.setServiceInstId(servSts.getServiceInstId());
		cmResServiceStatus.setResourceId(servSts.getUserId());
		cmResServiceStatus.setServiceId(servSts.getServiceId());
		//cmResServiceStatus.setServStateCode(servSts.getServStateCode());
		cmResServiceStatus.setOsSts(servSts.getOsSts());
		cmResServiceStatus.setOsStsDtl(servSts.getOsStsDtl());
		cmResServiceStatus.setValidDate(servSts.getValidDate());
		
		cmResServiceStatus.setExpireDate(servSts.getExpireDate());
		cmResServiceStatus.setSoNbr(context.getSoNbr());
		cmResServiceStatus.setRemark(servSts.getRemark());
		super.insert(cmResServiceStatus);

	}

	public void updateUserServiceSts(IUserServStatus servSts) {
		
		CmResServiceStatus cmResServiceStatus = new CmResServiceStatus();
		cmResServiceStatus.setCreateDate(context.getCommitDate());
		cmResServiceStatus.setSoDate(context.getCommitDate());
		cmResServiceStatus.setServiceInstId(servSts.getServiceInstId());
		//cmResServiceStatus.setServStateCode(servSts.getServStateCode());
		cmResServiceStatus.setOsStsDtl(servSts.getOsStsDtl());
		cmResServiceStatus.setExpireDate(servSts.getExpireDate());
		cmResServiceStatus.setSoNbr(context.getSoNbr());
		cmResServiceStatus.setRemark(servSts.getRemark());
		//this.updateByCondition(cmResServiceStatus, new DBCondition(CmResService.Field.serviceInstId, servSts.getServiceInstId()));
		this.updateMode2(cmResServiceStatus,EnumCodeExDefine.OPER_TYPE_UPDATE,servSts.getValidDate(),
				new DBCondition(CmResServiceStatus.Field.resourceId, servSts.getUserId()), 
				new DBCondition(CmResServiceStatus.Field.osSts,servSts.getOsSts()),
				new DBCondition(CmResServiceStatus.Field.osStsDtl,servSts.getOsStsDtl()),
				new DBCondition(CmResServiceStatus.Field.serviceId, servSts.getServiceId()));
		
		/**
		this.deleteByCondition(CmResServiceStatus.class, new DBCondition(CmResServiceStatus.Field.serviceInstId, servSts.getServiceInstId()),
				new DBCondition(CmResServiceStatus.Field.validDate,servSts.getValidDate()));
				*/
	}

	public void deleteUserServiceSts(IUserServStatus servSts) {
		CmResServiceStatus cmResServiceStatus = new CmResServiceStatus();
		cmResServiceStatus.setExpireDate(servSts.getExpireDate());

		this.updateMode2(cmResServiceStatus,EnumCodeExDefine.OPER_TYPE_DELETE,servSts.getValidDate(),
				new DBCondition(CmResServiceStatus.Field.resourceId, servSts.getUserId()), 
				new DBCondition(CmResServiceStatus.Field.osSts,servSts.getOsSts()),
				new DBCondition(CmResServiceStatus.Field.osStsDtl,servSts.getOsStsDtl()),
				new DBCondition(CmResServiceStatus.Field.serviceId, servSts.getServiceId()));
	}

}
