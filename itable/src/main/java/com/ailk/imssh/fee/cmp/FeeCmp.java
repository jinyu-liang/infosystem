package com.ailk.imssh.fee.cmp;

import java.util.Date;
import java.util.List;

import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SOtFreeRes;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAllowance;
import com.ailk.openbilling.persistence.acct.entity.CaPocket;
import com.ailk.openbilling.persistence.itable.entity.IFee;
import com.ailk.openbilling.persistence.jd.entity.ImsSnMapping;
import com.ailk.openbilling.persistence.pm.entity.PmFreeUsageProperty;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;

/**
 * @Description：操作I_FEE接口表数据到数据库表
 * @author wangyh3
 * @Date 2012-5-15
 */
public class FeeCmp extends BaseCmp {

	/**
	 * @Description 新增一次性费用
	 * @author wangyh3
	 * @Date 2012-5-15
	 */
	public CaAllowance createOneTimeFee(IFee iFee, SysMeasure measure) {
		/*
		 * ExtDataBusiService bean = SpringShUtil.getExtDataBusiService();
		 * Holder<Long> bosSoNbr = new Holder<Long>();
		 * Holder<Date> bosSoDate = new Holder<Date>(); Holder<Boolean>
		 * flagHolder = new Holder<Boolean>(); CommonParam
		 * commonParam = FeeHelper.buildCommonParam(iFee.getCommitDate(),
		 * context.getDoneCode()); ExtDataDef extDataDef =
		 * FeeHelper.buildExtDataDef(iFee); Integer result =
		 * bean.insertOneTimeFee(commonParam, extDataDef, bosSoNbr, bosSoDate,
		 * flagHolder); if (result != EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "insertOneTimeFee"); } // 保存映射关系
		 * saveSnMapping(commonParam.getOutSoNbr(), commonParam.getOutSoDate(),
		 * bosSoNbr.get(), bosSoDate.get(), iFee.getAcctId(),
		 * EnumCodeExDefine.SN_MAPPING_MODE_CRM_ONETIMEFEE);
		 */
		CaAllowance caAllowance = convertCaAllowance(iFee, measure, false);
		super.insert(caAllowance);
		return caAllowance;
	}

	/**
	 * @Description 新增押金
	 * @author wangyh3
	 * @Date 2012-5-15
	 */
	public void createPocket(IFee iFee) {
		/*
		 * DepositPocketBusiService bean =
		 * SpringExUtil.getDepositPocketBusiService(); Holder<String> holder =
		 * new
		 * Holder<String>(); CommonParam commonParam =
		 * FeeHelper.buildCommonParam(iFee.getCommitDate(),
		 * context.getDoneCode());
		 * Pocket caPocket = FeeHelper.buildPocket(iFee, context.getDoneCode());
		 * caPocket.setCreateDate(this.getContext().getCommitDate());
		 * caPocket.setSoDate(this.getContext().getCommitDate());
		 * Integer result = bean.insertPocket(commonParam, caPocket, holder); if
		 * (result !=
		 * EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "insertPocket"); }
		 */
	}

	/**
	 * @Description 修改押金
	 * @author wangyh3
	 * @Date 2012-5-15
	 */
	public void modifyPocket(IFee iFee) {
		/*
		 * DepositPocketBusiService bean =
		 * SpringExUtil.getDepositPocketBusiService(); Holder<String> holder =
		 * new
		 * Holder<String>(); CommonParam commonParam =
		 * FeeHelper.buildCommonParam(iFee.getCommitDate(),
		 * context.getDoneCode());
		 * Pocket pocketOld = FeeHelper.buildPocket(iFee,
		 * context.getDoneCode()); Pocket pocketNew =
		 * FeeHelper.buildPocket(iFee,
		 * context.getDoneCode());
		 * pocketNew.setSoDate(this.getContext().getCommitDate()); Integer
		 * result =
		 * bean.updatePocket(commonParam, pocketOld, pocketNew, holder); if
		 * (result != EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "updatePocket"); }
		 */
	}

	/**
	 * @Description 删除押金
	 * @author wangyh3
	 * @Date 2012-5-15
	 */
	public void deletePocket(IFee iFee) {
		/*
		 * DepositPocketBusiService bean =
		 * SpringExUtil.getDepositPocketBusiService(); Holder<String> holder =
		 * new
		 * Holder<String>(); CommonParam commonParam =
		 * FeeHelper.buildCommonParam(iFee.getCommitDate(),
		 * context.getDoneCode());
		 * Pocket caPocket = FeeHelper.buildPocket(iFee, context.getDoneCode());
		 * Integer result = bean.deletePocket(commonParam,
		 * caPocket, holder); if (result !=
		 * EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "deletePocket"); }
		 */
	}

	/**
	 * @Description 新增预存款（充值，先对科目归并）
	 * @author zenglu
	 * @Date 2012-09-25
	 */
	public void doDepositByCRM(List<IFee> iFeeList) {
		/*
		 * if (iFeeList == null || iFeeList.isEmpty()) { return; } Map<Long,
		 * List<PocketItem>> map = new HashMap<Long,
		 * List<PocketItem>>(); // 把IFee也存起来 Map<Long, IFee> iFeeMap = new
		 * HashMap<Long, IFee>(); for (IFee iFee : iFeeList) {
		 * Long acctId = iFee.getAcctId(); List<PocketItem> pocketItemList = new
		 * ArrayList<PocketItem>(); PocketItem value = new
		 * PocketItem(); value.setAmount(iFee.getAmount());
		 * value.setPocketItem(iFee.getFeeItemId()); //2013-07-16 liming15
		 * 如果是专款，根据接口表数据设置生失效时间 Integer itemCode = iFee.getFeeItemId(); if(59 ==
		 * Integer.parseInt(itemCode.toString().substring(0,
		 * 2))) { value.setValidDate(iFee.getValidDate());
		 * value.setExpireDate(iFee.getExpireDate()); } //end if
		 * (map.get(acctId)
		 * != null) { pocketItemList = map.get(acctId);
		 * pocketItemList.add(value); map.put(acctId, pocketItemList); } else {
		 * pocketItemList.add(value); map.put(acctId, pocketItemList);
		 * iFeeMap.put(acctId, iFee); } } DepositBusiService bean =
		 * SpringShUtil.getDepositBusiService(); // 调帐管的充值接口 Set<Long> keySet =
		 * map.keySet(); for (Long set : keySet) { IFee iFee
		 * = iFeeMap.get(set); List<PocketItem> pocketItemList = map.get(set);
		 * CommonParam commonParam =
		 * FeeHelper.buildCommonParam(iFee.getCommitDate(),
		 * context.getDoneCode());
		 * commonParam.setSpecialBusiFlag(CommonUtil.long2Int(iFee.getBusiSpecId(
		 * )));
		 * commonParam.setChannelId(100);//与外围接口保持一致,CRM统一为100 DepositByCRMIn
		 * depositByCRMIn = new DepositByCRMIn(); //
		 * acct_id和phone_id二选一 lijc3 if (CommonUtil.isValid(iFee.getAcctId())) {
		 * depositByCRMIn.setAcctId(iFee.getAcctId()); } //
		 * else // { // String strPhoneId =
		 * context.get3hTree().loadUser3hBean(iFee.getUserId()).getPhoneId(); //
		 * depositByCRMIn.setPhoneId(strPhoneId); // }
		 * if(CommonUtil.isValid(iFee.getUserId())){
		 * //过户的时候会给新账户充值，但是这个时候用户还在老账户上，之前设置的是新账户id在context中
		 * ITableUtil.cleanRequestParamter(); String strPhoneId =
		 * context.get3hTree().loadUser3hBean(iFee.getUserId()).getPhoneId();
		 * depositByCRMIn.setPhoneId(strPhoneId);
		 * depositByCRMIn.setResourceId(iFee.getUserId()); //设置回分表
		 * if(iFee.getAcctId()!=null){
		 * ITableUtil.setValue2ContextHolder(null, iFee.getAcctId(), null);
		 * }else if(iFee.getUserId()!=null){
		 * ITableUtil.setValue2ContextHolder(null,null, iFee.getUserId()); } }
		 * depositByCRMIn.setOutSpecId(iFee.getBusiSpecId());
		 * depositByCRMIn.setPocketItemList(megertPocketItemList(pocketItemList))
		 * ; Holder<DepositCardOut> depositCardOut = new
		 * Holder<DepositCardOut>(); Integer result =
		 * bean.doDepositFromCRM(commonParam, depositByCRMIn, depositCardOut);
		 * if
		 * (result != EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "doDepositFromCRM"); } // 保存映射关系
		 * saveSnMapping(commonParam.getOutSoNbr(), commonParam.getOutSoDate(),
		 * depositCardOut.get().getSoNbr(), depositCardOut
		 * .get().getSoDate(),
		 * depositCardOut.get().getAcctId(),EnumCodeExDefine.
		 * SN_MAPPING_MODE_CRM_DEPOSIT); }
		 */
	}

	/*
	 * private List<PocketItem> megertPocketItemList(List<PocketItem>
	 * pocketItemList) { if (CommonUtil.isEmpty(pocketItemList)) {
	 * return null; } Map<Integer, Long> pocketMap = new HashMap<Integer,
	 * Long>(); for (PocketItem pocketItem : pocketItemList) {
	 * Integer item = pocketItem.getPocketItem(); //2013-07-16 liming15
	 * 专款不合并,需要在后面把专款科目补回来 if(59 ==
	 * Integer.parseInt(item.toString().substring(0,2))) continue; //end if
	 * (pocketMap.get(item) != null) { Long value =
	 * pocketMap.get(item); value += pocketItem.getAmount(); pocketMap.put(item,
	 * value); } else { pocketMap.put(item,
	 * pocketItem.getAmount()); } } List<PocketItem> result = new
	 * ArrayList<PocketItem>(); for (Integer itemId :
	 * pocketMap.keySet()) { PocketItem pocketItem = new PocketItem();
	 * pocketItem.setPocketItem(itemId);
	 * pocketItem.setAmount(pocketMap.get(itemId)); result.add(pocketItem); }
	 * //2013-07-16 liming15 将专款科目找回来 for(PocketItem
	 * pocketItem : pocketItemList) { if(59 ==
	 * Integer.parseInt(pocketItem.getPocketItem().toString().substring(0,2))) {
	 * result.add(pocketItem); } } //end return result; }
	 */

	/**
	 * @Description 新增免费资源
	 * @author wangyh3
	 * @Date 2012-5-15
	 */
	public void createFreeResource(IFee iFee) {
		/*
		 * TopupBusiService tpSer = SpringShUtil.getTopupBusiService();
		 * Holder<CommParaOut> out = new Holder<CommParaOut>(null);
		 * CommonParam commonParam =
		 * FeeHelper.buildCommonParam(iFee.getCommitDate(),
		 * context.getDoneCode()); FreeResource
		 * freeResource = FeeHelper.buildFreeResource(iFee);
		 * convertFreeResValueByResItem(freeResource); // BiBusiSpecDef def =
		 * context.getCmp(BaseCmp.class).querySingle(BiBusiSpecDef.class, // new
		 * DBCondition(BiBusiSpecDef.Field.busiType, 812),
		 * new DBCondition(BiBusiSpecDef.Field.busiMode, 1)); // if (def !=
		 * null) // { //
		 * freeResource.setBusiSpecId(def.getBusiSpecId()); // }
		 * freeResource.setRechargeMethod(EnumCodeExDefine.FREE_RESOURCE_METHOD);
		 * try { Integer result =
		 * tpSer.doFreeResourceDeposit(commonParam, freeResource, out); if
		 * (result != EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "doFreeResourceDeposit"); } // 保存映射关系 //
		 * saveSoNbrMapping(commonParam.getOutSoNbr(),
		 * commonParam.getOutSoDate(), out.get().getSoNbr(), //
		 * out.get().getSoDate()); } catch (Exception e) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "doFreeResourceDeposit"); }
		 */
	}

	/**
	 * 2012-10-20 gaoqc5 #61731 免费资源单位的转换
	 * 
	 * @param freeResource
	 */
	/*
	 * private void convertFreeResValueByResItem(FreeResource freeResource) {
	 * SysMeasure measure =
	 * getSysMeasure(freeResource.getFreeResItem()); if (null != measure) {
	 * imsLogger.debug(" get measure Id:" +
	 * measure.getMeasureId()); if (measure.getMeasureId() ==
	 * EnumCodeDefine.MEASURE_ID_KB) {//
	 * 若单位为kb，则需要将科目金额的值从byte转成kb，调用免费资源接口时单位传kb // long
	 * val=freeResource.getFreeResValue(); // if(val>1024){ long val =
	 * freeResource.getFreeResValue().longValue() / 1024; // 取整数部分，小数部分不用
	 * freeResource.setFreeResValue(val);
	 * imsLogger.debug("is  MEAURE_ID_BYTE,val=" + val); // } } if
	 * (measure.getMeasureId() == EnumCodeExDefine.MEASURE_ID_MINUTE)
	 * {// 如果是秒，那么需要从秒转换成分。 long val =
	 * freeResource.getFreeResValue().longValue() / 60;// 取整数，小数部分不用
	 * freeResource.setFreeResValue(val);
	 * imsLogger.debug("is MEASURE_ID_FEN,val=" + val); } } }
	 */

	public SysMeasure getSysMeasure(Integer freeItemId) {
		PmFreeUsageProperty property = context.getCmp(BaseCmp.class).querySingle(PmFreeUsageProperty.class,
				new DBCondition(PmFreeUsageProperty.Field.assetItemId, freeItemId));
		if (null != property) {
			return context.getCmp(BaseCmp.class).querySingle(SysMeasure.class,
					new DBCondition(SysMeasure.Field.measureId, property.getMeasureId()));
		}
		return null;
	}

	/**
	 * 保存内外部工单号的映射
	 */
	public void saveSnMapping(String outSoNbr, Date outSoDate, Long bosSoNbr, Date bosSoDate, Long accountId, String soMode) {
		ImsSnMapping mapping = new ImsSnMapping();
		mapping.setOutSoMode(soMode);
		mapping.setOutSoNbr(outSoNbr);
		mapping.setOutSoDate(outSoDate);
		mapping.setBosSoNbr(bosSoNbr);
		mapping.setBosSoDate(bosSoDate);
		mapping.setAccountId(accountId);

		super.insert(mapping);
	}

	/**
	 * 预缴转普通预存
	 * 
	 * @Author: wukl
	 * @Date: 2012-11-5
	 * @param iFee
	 */
	public void doTransfer(IFee iFee) {
		/*
		 * CommonParam commonParam =
		 * FeeHelper.buildCommonParam(iFee.getCommitDate(),
		 * context.getDoneCode()); DoTransferIn in =
		 * new DoTransferIn(); in.setOrgAcctId(iFee.getAcctId());
		 * in.setTargetAcctId(iFee.getAcctId());
		 * in.setOrgItemId(iFee.getSrcFeeItemId());
		 * in.setTargetItemId(iFee.getFeeItemId());
		 * in.setAmount(iFee.getAmount());
		 * //2013-4-17 资金转移使用传入的busi_spec_id try {
		 * in.setSpecId(iFee.getBusiSpecId().intValue()); } catch (Exception e)
		 * {
		 * in.setSpecId(EnumCodeShDefine.TRANSFER_TYPE_SELF_SPEC_ID); }
		 * DepositBusiService service =
		 * SpringShUtil.getDepositBusiService(); Holder<DoTransferOut> out = new
		 * Holder<DoTransferOut>(); Integer result =
		 * service.doTransferAmount(commonParam, in, out); if (result !=
		 * EnumCodeExDefine.CALL_INTERFACE_SUCCESS) {
		 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
		 * "doTransfer"); } // 记录内外部工单映射表
		 * saveSnMapping(commonParam.getOutSoNbr(), commonParam.getOutSoDate(),
		 * out.get().getSoNbr(), out.get().getSoDate(),
		 * iFee.getAcctId(),EnumCodeExDefine.SN_MAPPING_MODE_CRM_TRANSFER);
		 */
	}

	public CaAllowance convertCaAllowance(IFee iFee, SysMeasure measure, boolean isUpdateOper) {

		CaAllowance caAllowance = new CaAllowance();
		caAllowance.setAssetId(DBUtil.getSequence(CaPocket.class));
		caAllowance.setAllowanceItem(iFee.getFeeItemId());
		caAllowance.setAllowanceType(1);
		caAllowance.setAcctId(iFee.getAcctId());
		caAllowance.setProductId(iFee.getProductId() == null ? 0L : iFee.getProductId());
		caAllowance.setProductOfferingId(0);
		caAllowance.setResourceId(iFee.getUserId());
		caAllowance.setAllowanceValue(iFee.getAmount());
		if (!isUpdateOper) {
			caAllowance.setValidDate(iFee.getValidDate());
			caAllowance.setCreateDate(iFee.getCommitDate());
		}
		caAllowance.setExpireDate(iFee.getExpireDate());
		caAllowance.setSoDate(iFee.getCommitDate());
		caAllowance.setSoNbr(context.getSoNbr());

		/*
		 * 广西不需要处理
		 * if (null != measure)
		 * {
		 * imsLogger.debug(" get measure Id:" + measure.getMeasureId());
		 * if (measure.getMeasureId() == EnumCodeDefine.MEASURE_ID_KB)
		 * {// 若单位为kb，则需要将科目金额的值从byte转成kb，调用免费资源接口时单位传kb
		 * // long val=freeResource.getFreeResValue();
		 * // if(val>1024){
		 * long val = caAllowance.getAllowanceValue().longValue() / 1024; //
		 * 取整数部分，小数部分不用
		 * caAllowance.setAllowanceValue(val);
		 * imsLogger.debug("is  MEAURE_ID_BYTE,val=" + val);
		 * // }
		 * }
		 * if (measure.getMeasureId() == EnumCodeExDefine.MEASURE_ID_MINUTE)
		 * {// 如果是秒，那么需要从秒转换成分。
		 * long val = caAllowance.getAllowanceValue().longValue() / 60;//
		 * 取整数，小数部分不用
		 * caAllowance.setAllowanceValue(val);
		 * imsLogger.debug("is MEASURE_ID_FEN,val=" + val);
		 * }
		 * }
		 */
		return caAllowance;
	}

	public SOtFreeRes convertSOtFreeRes(CaAllowance caAllowance, SysMeasure measure) {
		SOtFreeRes sOtFreeRes = new SOtFreeRes();
		sOtFreeRes.set_acctId(caAllowance.getAcctId());
		sOtFreeRes.set_objectId(caAllowance.getResourceId());
		sOtFreeRes.set_assetId(caAllowance.getAssetId());
		// sOtFreeRes.set_oldAmount(caAllowance.get)
		sOtFreeRes.set_amount(caAllowance.getAllowanceValue());
		// sOtFreeRes.set_usedValue(caAllowance.get)
		sOtFreeRes.set_productId(caAllowance.getProductId());
		// sOtFreeRes.set_oldValidDate()
		// sOtFreeRes.set_oldExpireDate(value)
		sOtFreeRes.set_validDate(ConvertUtil.javaDate2SdlLong(caAllowance.getValidDate()));
		sOtFreeRes.set_expireDate(ConvertUtil.javaDate2SdlLong(caAllowance.getExpireDate()));
		// sOtFreeRes.set_freezeFee(value)
		// sOtFreeRes.set_freezeTime(value)
		// sOtFreeRes.set_forwardCycle(value)
		sOtFreeRes.set_itemCode(caAllowance.getAllowanceItem());
		sOtFreeRes.set_measureId(measure.getMeasureId());
		sOtFreeRes.set_objectType((short) 0);
		sOtFreeRes.set_freeresType(caAllowance.getAllowanceType().shortValue());
		sOtFreeRes.set_checkFlag((short) 1);
		sOtFreeRes.set_clearFlag((short) 0);

		return sOtFreeRes;
	}

}
