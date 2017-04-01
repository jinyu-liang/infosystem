package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.Condition.Operator;
import jef.tools.DateUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.prod.cmp.ProdExCmp;
import com.ailk.openbilling.persistence.acct.entity.CaDepositProduct;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.jd.entity.IUserDepositNotify;
import com.ailk.openbilling.persistence.pm.entity.PmAllotBusiCalc;
import com.ailk.openbilling.persistence.zg.entity.IUserDepositTransfer;

public class UserDepositCmp extends BaseCmp {

	public CoProd createProd(Long userId, Integer offerId, Date validDate, Date expireDate) {
		CoProd dmProd = new CoProd();
		ProdExCmp prodExCmp = context.getCmp(ProdExCmp.class);
		CoProd mainProd = prodExCmp.queryPrimaryProductByUserId(userId);

		Offering3hbean offerBean = context.get3hTree().loadOffering3hBean(offerId.longValue());

		Map valueMap = new HashMap();
		if (mainProd != null) {
			valueMap.put(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID, mainProd.getProductOfferingId());
		}
		Integer pricePlanId = prodExCmp.queryPricePlanId(offerId, valueMap);
		if (pricePlanId != null) {
			dmProd.setPricingPlanId(pricePlanId);
		} else {
			dmProd.setPricingPlanId(0);
		}

		dmProd.setProductId(DBUtil.getSequence(CoProd.class));
		dmProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
		dmProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
		dmProd.setProductOfferingId(offerId);
		dmProd.setBusiFlag(offerBean.getBusiFlag());
		dmProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
		dmProd.setValidDate(validDate);
		dmProd.setExpireDate(expireDate);
		dmProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
		dmProd.setObjectId(userId);
		dmProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
		dmProd.setProdValidDate(validDate);
		dmProd.setProdExpireDate(expireDate);

		return dmProd;
	}

	public List<CoProdPriceParam> createPriceParamByUserDeposti(CoProd dmProd, IUserDepositTransfer transfer, PmAllotBusiCalc calc) {
		List<CoProdPriceParam> paramList = new ArrayList<CoProdPriceParam>();
		// 本月优惠额度
		Long preferentialAmount = getPreferentialAmount(transfer, calc);
		Long remainAmount = getremainAmount(transfer, calc);

		paramList.add(createPriceParam(dmProd, EnumCodeExDefine.PRICE_PARAM_CUR_AMOUNT, String.valueOf(preferentialAmount)));
		paramList.add(createPriceParam(dmProd, EnumCodeExDefine.PRICE_PARAM_REMAIN_ACCOUNT, String.valueOf(remainAmount)));
		String month = getBindDate(transfer, calc);
		if (month != null) {
			paramList.add(createPriceParam(dmProd, EnumCodeExDefine.PRICE_PARAM_BIND_DATE, month));
		}
		return paramList;
	}

	private Long getremainAmount(IUserDepositTransfer transfer, PmAllotBusiCalc calc) {
		Long remainAmount = null;
		
		int promType = calc.getPromType();
		// prom_type是1，3，4 创建的二次议价参数的值算法
		if (promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_PRINCIPAL_PROM
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_COMMON_PRINCIPAL) {
			Long principalFee = (transfer.getPrincipalFee() == null) ? 0 : transfer.getPrincipalFee();
			Long prinConsumed = (transfer.getPrinConsumed() == null) ? 0 : transfer.getPrinConsumed();
			// prom_type是1，3，4 创建的二次议价参数的值算法
			remainAmount = principalFee - prinConsumed;
		} else {
			Long donateFee = (transfer.getDonateFee() == null) ? 0 : transfer.getDonateFee();
			Long donaConsumed = (transfer.getDonaConsumed() == null) ? 0 : transfer.getDonaConsumed();
			remainAmount = donateFee - donaConsumed;
		}
		return remainAmount;
	}

	private Long getPreferentialAmount(IUserDepositTransfer transfer, PmAllotBusiCalc calc) {
		Long preferentialAmount = null;
		int promType = calc.getPromType();
		// prom_type是1，3，4 创建的二次议价参数的值算法
		if (promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_PRINCIPAL_PROM
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_COMMON_PRINCIPAL) {
			// prom_type是1，3，4 创建的二次议价参数的值算法
			if(calc.getNumerator() != 0 && calc.getDenominator() != 0){
				preferentialAmount = transfer.getPrincipalFee() * calc.getNumerator() / calc.getDenominator();
			}else{
				preferentialAmount = 0L;
			}
		} else {
			if(calc.getNumerator() != 0 && calc.getDenominator() != 0){
				preferentialAmount = transfer.getDonateFee() * calc.getNumerator() / calc.getDenominator();
			}else{
				preferentialAmount = 0L;
			}
		}
		return preferentialAmount;
	}

	private String getBindDate(IUserDepositTransfer transfer, PmAllotBusiCalc calc) {
		String month = null;
		if (transfer.getBindImei() == 1) {
			if (transfer.getDevUsedSts() == EnumCodeExDefine.DEPOSIT_DEV_STS_0) {
				month = "0";
			} else if (transfer.getDevUsedSts() == EnumCodeExDefine.DEPOSIT_DEV_STS_1) {
				month = DateUtil.formatDate(transfer.getImeiStsDate(), DateUtil.DATE_FORMAT_YYYYMM);
			}
		}
		return month;
	}

	private CoProdPriceParam createPriceParam(CoProd dmProd, Integer paramId, String paramValue) {
		CoProdPriceParam param = new CoProdPriceParam();
		param.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
		param.setCreateDate(dmProd.getCreateDate());
		param.setExpireDate(dmProd.getExpireDate());
		param.setObjectId(dmProd.getObjectId());
		param.setObjectType(dmProd.getObjectType());
		param.setParamId(paramId);
		param.setProductId(dmProd.getProductId());
		param.setParamValue(paramValue);
		param.setSoDate(dmProd.getSoDate());
		param.setSoNbr(dmProd.getSoNbr());
		param.setValidDate(dmProd.getValidDate());

		return param;
	}

	public IUserDepositTransfer queryUserDepositTransfer(IUserDepositNotify notify) {

		return DBUtil.querySingle(IUserDepositTransfer.class, new DBCondition(IUserDepositTransfer.Field.servId, notify.getServId()),
				new DBCondition(IUserDepositTransfer.Field.promoId, notify.getPromoId()), new DBCondition(
						IUserDepositTransfer.Field.condId, notify.getCondId()),new DBCondition(IUserDepositTransfer.Field.validDate,notify.getValidDate()));
	}

	public CaDepositProduct createCaDepositProduct(CoProd dmProd, int promType, IUserDepositTransfer transfer) {
		CaDepositProduct product = new CaDepositProduct();
		product.setCondId(transfer.getCondId());
		product.setPromType(promType);
		product.setExpireDate(dmProd.getExpireDate());
		product.setProductId(dmProd.getProductId());
		product.setPromoId(transfer.getPromoId());
		product.setServId(dmProd.getObjectId());
		product.setSoNbr(dmProd.getSoNbr());
		product.setValidDate(dmProd.getValidDate());
		product.setBusiType(transfer.getBusiType());
		product.setSoValidDate(transfer.getValidDate());
		return product;
	}

	public List<CaDepositProduct> queryCaDepositProduct(Long userId, Integer promId, Integer condId) {
		return this.query(CaDepositProduct.class, new DBCondition(CaDepositProduct.Field.servId, userId), new DBCondition(
				CaDepositProduct.Field.promoId, promId), new DBCondition(CaDepositProduct.Field.condId, condId));
	}

	public void deleteDepositProd(IUserDepositNotify notify) {
		//对于二次议价参数820007为0的情况（本金或赠金剩余划拨金额为0），该产品需要置为失效，失效时间为当月1日0点
		Date expireDate = DateUtils.monthBegin(notify.getCreateDate());
		CaDepositProduct updateDeptProd = new CaDepositProduct();
		updateDeptProd.setExpireDate(expireDate);
		// 删除的时候，根据关系删除产品及二次议价参数
		List<CaDepositProduct> deptProdList = this.updateDirectByCondition(updateDeptProd, new DBCondition(CaDepositProduct.Field.servId,
				notify.getServId()), new DBCondition(CaDepositProduct.Field.promoId, notify.getPromoId()), new DBCondition(
				CaDepositProduct.Field.condId, notify.getCondId()));
		if (CommonUtil.isEmpty(deptProdList)) {
			return;
		}
		Set<Long> prodIdSet = new HashSet<Long>();
		for (CaDepositProduct product : deptProdList) {
			prodIdSet.add(product.getProductId());
		}
		CoProd coProd = new CoProd();
		coProd.setExpireDate(expireDate);
		coProd.setProdExpireDate(expireDate);
		this.updateDirectByCondition(coProd,
				new DBCondition(CoProd.Field.productId, prodIdSet, Operator.IN), new DBCondition(
				CoProd.Field.objectId, notify.getServId()));
		
		CoProdPriceParam updatePriceParam = new CoProdPriceParam();
		updatePriceParam.setExpireDate(expireDate);
		this.updateDirectByCondition(updatePriceParam, 
				new DBCondition(CoProdPriceParam.Field.productId, prodIdSet, Operator.IN));
	}

	public void createDepositProdList(IUserDepositNotify notify, IUserDepositTransfer transfer) {
		List<PmAllotBusiCalc> calcList = DBUtil.query(PmAllotBusiCalc.class,
				new DBCondition(PmAllotBusiCalc.Field.busiType, notify.getBusiType()));
		if (CommonUtil.isEmpty(calcList)) {
			return;
		}
		boolean hasSpecialPrincipal = false; // 是否含有特殊本金产品
		// 判断是否含有特殊本金产品
		for (PmAllotBusiCalc calc : calcList) {
			if (calc.getPromType() == EnumCodeExDefine.DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL) {
				hasSpecialPrincipal = true;
				break;
			}
		}

		List<CoProd> prodList = new ArrayList<CoProd>();
		List<CoProdPriceParam> listParam = new ArrayList<CoProdPriceParam>();
		List<CaDepositProduct> listDepProd = new ArrayList<CaDepositProduct>();

		// 转换活动
		for (PmAllotBusiCalc calc : calcList) {
			if (calc.getBookRuleId() != 0) {
				continue;// 等于0的才需要转换成产品
			}
			int promType = calc.getPromType();
			Date validDate = getProdValidDateByDeposit(transfer, promType, hasSpecialPrincipal);
			Date expireDate = getProdExpireDateByDeposit(transfer, promType, hasSpecialPrincipal);
			// 创建产品
			CoProd dmProd = createProd(transfer.getServId(), calc.getProductOfferingId(), validDate, expireDate);
			// 创建二次议价参数
			List<CoProdPriceParam> paramList = createPriceParamByUserDeposti(dmProd, transfer, calc);
			// 判断 如果820007值为0 不生成优惠产品
			if(paramList.get(1).getParamValue().equals("0")) {
				continue;
			}
			prodList.add(dmProd);
			listParam.addAll(paramList);
			// 创建关系
			CaDepositProduct product = createCaDepositProduct(dmProd, promType, transfer);
			listDepProd.add(product);
		}
		this.insertBatch(prodList);
		this.insertBatch(listParam);
		this.insertBatch(listDepProd);
	}

	private Date getProdValidDateByDeposit(IUserDepositTransfer transfer, int promType, boolean hasSpecialPrincipal) {
		
		Date validDate = null;
		
		if (promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_PRINCIPAL_PROM
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_COMMON_PRINCIPAL) {// 本金类
			if (transfer.getValidDate().after(transfer.getPrinBeginDate())) {
				validDate = transfer.getValidDate();
			} else {
				validDate = transfer.getPrinBeginDate();
			}
			
			// 特殊本金产品，最后一月生效
			if (promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL) {
				validDate = DateUtils.monthBegin(transfer.getPrinEndDate());
			}

		} else {// 赠金
			if (transfer.getValidDate().after(transfer.getDonaBeginDate())) {
				validDate = transfer.getValidDate();
			} else {
				validDate = transfer.getDonaBeginDate();
			}
		}	
		return validDate;
	}

	private Date getProdExpireDateByDeposit(IUserDepositTransfer transfer, int promType, boolean hasSpecialPrincipal) {
		
		Date expireDate = null;

		if (promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_PRINCIPAL_PROM
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL
				|| promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_COMMON_PRINCIPAL) {// 本金类
								
			if (transfer.getPrinEndDate().getTime() == DateUtils.monthBegin(transfer.getPrinEndDate()).getTime()){
				expireDate = transfer.getPrinEndDate();
			} else {
				expireDate = DateUtils.monthBegin(transfer.getPrinEndDate());
				DateUtils.addMonth(expireDate, 1);
			}
		
			// 本金优惠产品，并且有特殊本金产品，然后该产品需要最后一月的上月失效
			if (promType == EnumCodeExDefine.DEPOSIT_PROM_TYPE_PRINCIPAL_PROM && hasSpecialPrincipal) {
				expireDate = DateUtils.monthBegin(transfer.getPrinEndDate());
			}

		} else {// 赠金
			
			if (transfer.getDonaEndDate().getTime() == DateUtils.monthBegin(transfer.getDonaEndDate()).getTime()){
				expireDate = transfer.getDonaEndDate();
			} else {
				expireDate = DateUtils.monthBegin(transfer.getDonaEndDate());
				DateUtils.addMonth(expireDate, 1);
			}
		}
		return expireDate;
	}
	

	public void updateDepositProdList(IUserDepositNotify notify, IUserDepositTransfer transfer) {

		ProdExCmp prodCmp = context.getCmp(ProdExCmp.class);
		List<CaDepositProduct> depositList = queryCaDepositProduct(notify.getServId(), notify.getPromoId(), notify.getCondId());

		if (CommonUtil.isEmpty(depositList)) {
			return;
		}
		boolean hasSpecialPrincipal = false; // 是否含有特殊本金产品
		// 判断是否含有特殊本金产品
		for (CaDepositProduct calc : depositList) {
			if (calc.getPromType() == EnumCodeExDefine.DEPOSIT_PROM_TYPE_SPECIAL_PRINCIPAL) {
				hasSpecialPrincipal = true;
				break;
			}
		}

		for (CaDepositProduct product : depositList) {
			CoProd dmProd = prodCmp.loadProdWithNull(product.getProductId(), product.getServId());
			if (dmProd == null) {
				// 如果活动对应关系表对应的产品已失效，则将关系表置为失效，失效时间为当月1日
				product.setExpireDate(DateUtils.monthBegin(notify.getCreateDate()));
				this.updateDirectByCondition(product, new DBCondition(CaDepositProduct.Field.servId,
						notify.getServId()), new DBCondition(CaDepositProduct.Field.promoId, notify.getPromoId()), new DBCondition(
						CaDepositProduct.Field.condId, notify.getCondId()));
				continue;
			}
			int promType = product.getPromType();
			
			// 获取生失效时间
			Date expireDate = getProdExpireDateByDeposit(transfer, promType, hasSpecialPrincipal);
			PmAllotBusiCalc calc = DBUtil.querySingle(PmAllotBusiCalc.class,
					new DBCondition(PmAllotBusiCalc.Field.busiType, product.getBusiType()), new DBCondition(
							PmAllotBusiCalc.Field.productOfferingId, dmProd.getProductOfferingId()), new DBCondition(
							PmAllotBusiCalc.Field.promType, product.getPromType()));
			if (calc == null) {
				imsLogger.error("*****没有查到活动配置");
				continue;
			}
			Long preferentialAmount = getPreferentialAmount(transfer, calc);
			Long remainAmount = getremainAmount(transfer, calc);
			String month = getBindDate(transfer, calc);
			
			imsLogger.debug("--------remainAmount-----------"+ remainAmount);
			// 820007对应的值为0 产品要置为失效 
			if(remainAmount != null && remainAmount <= 0 ){
				imsLogger.debug("--------剩余划拨金额为0 产品置为失效---------"+ remainAmount);
				expireDate = DateUtils.monthBegin(notify.getCreateDate());
				CoProd coProd = new CoProd();
				coProd.setExpireDate(expireDate);
				coProd.setProdExpireDate(expireDate);
	            //更新失效时间
	            this.updateDirectByCondition(coProd, 
	            		new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
	            				CoProd.Field.objectId, notify.getServId()));
			}else {
				CoProd coProd = new CoProd();
				// 不更新生效时间20151120
				coProd.setExpireDate(expireDate);
				coProd.setProdExpireDate(expireDate);
	            this.updateDirectByCondition(coProd, 
	            		new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
	            				CoProd.Field.objectId, notify.getServId()));
			}
			
			// 只更新二次议价参数
			CoProdPriceParam updatePriceParam = new CoProdPriceParam();
			updatePriceParam.setParamValue(String.valueOf(preferentialAmount));
			updatePriceParam.setExpireDate(expireDate);
			this.updateDirectByCondition(updatePriceParam,
					new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(
							CoProdPriceParam.Field.paramId, EnumCodeExDefine.PRICE_PARAM_CUR_AMOUNT), new DBCondition(
							CoProdPriceParam.Field.objectId, dmProd.getObjectId()));
			updatePriceParam = new CoProdPriceParam();
			updatePriceParam.setParamValue(String.valueOf(remainAmount));
			updatePriceParam.setExpireDate(expireDate);
			this.updateDirectByCondition(updatePriceParam,
					new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(
							CoProdPriceParam.Field.paramId, EnumCodeExDefine.PRICE_PARAM_REMAIN_ACCOUNT), new DBCondition(
							CoProdPriceParam.Field.objectId, dmProd.getObjectId()));
			
			updatePriceParam = new CoProdPriceParam();
			if (CommonUtil.isNotEmpty(month)) {
				CoProdPriceParam param = this.querySingle(CoProdPriceParam.class,
						new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(
								CoProdPriceParam.Field.paramId, EnumCodeExDefine.PRICE_PARAM_BIND_DATE), new DBCondition(
								CoProdPriceParam.Field.objectId, dmProd.getObjectId()));
				if (param != null) {
					updatePriceParam.setParamValue(month);
					updatePriceParam.setExpireDate(expireDate);
					this.updateDirectByCondition(updatePriceParam,
							new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()), new DBCondition(
									CoProdPriceParam.Field.paramId, EnumCodeExDefine.PRICE_PARAM_BIND_DATE), new DBCondition(
									CoProdPriceParam.Field.objectId, dmProd.getObjectId()));
				} else {
					this.insert(createPriceParam(dmProd, EnumCodeExDefine.PRICE_PARAM_BIND_DATE, month));
				}
			}
			
		}

	}
}
