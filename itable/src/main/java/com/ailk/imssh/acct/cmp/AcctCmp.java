package com.ailk.imssh.acct.cmp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.StringUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.AmountComponent;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.credit.cmp.CreditCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAsset;
import com.ailk.openbilling.persistence.acct.entity.CaAssetSyn;
import com.ailk.openbilling.persistence.acct.entity.CaBatchSyn;
import com.ailk.openbilling.persistence.acct.entity.CaCredit;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmOrganization;
import com.ailk.openbilling.persistence.cust.entity.CmParty;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmPartyRole;
import com.ailk.openbilling.persistence.cust.entity.CmRouteIdentity;
import com.ailk.openbilling.persistence.itable.entity.IAccount;

/**
 * @Description:账户资料组件
 * @author caohm5
 * @Date 2012-5-17
 * @Date 2012-05-14 wukl 增加免停、免催、免催免停的处理
 * @Date 2012-08-04 wukl Strory #49463 存储账务侧的度量单位
 * @Date 2012-9-18 lijc3 #58170 创建账户前对客户和客户联系人进行挪库操作
 * @Date 2012-11-01 wukl 新增账户时判断若没进行充值、没进行信用度创建，则调用创建信用度接口去创个账本
 */
public class AcctCmp extends AcctQuery {

	private ImsLogger imsLogger = new ImsLogger(AcctCmp.class);
	private String specialTableName = "AD.CA_CUSTOMER_REL";
	/**
	 * @Description:账户接口表数据通过逻辑转换，保存到数据库对应表中
	 * @author caohm5
	 * @Date 2012-05-12
	 * @Date 2012-06-13 免催
	 */
	public void createAccount(IAccount iAccount) {
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		// 账户是否已经发布路由，逻辑由框架进行判断
		if (!routCmp.isCustRouted(iAccount.getCustId())) {
			// 客户也没有发布过路由，则将客户相关信息缓存起来上发mdb
			List<CmCustomer> custList = this.query(CmCustomer.class, new DBCondition(CmCustomer.Field.custId, iAccount.getCustId()));
			context.cacheList(custList);
			routCmp.createAcctRouter(iAccount.getAcctId(), iAccount.getCustId(), iAccount.getRegionCode(), iAccount.getValidDate(),
					iAccount.getExpireDate());
		} else {
			// 如果客户已经发布过路由，则调用另外一个创建主维度的方法
			routCmp.createAcctsRouter(iAccount.getAcctId(), iAccount.getCustId(), iAccount.getRegionCode(), iAccount.getValidDate());
		}
		// 创建账户表
		this.insertCaAccount(iAccount);
		// 创建客户与账户关系表
		this.insertCaCustomerRel(iAccount);
		// 新建一个账户默认给一条永久信用度 值为 0
		this.insertCaCredit(iAccount);
		// 信用度上发
		this.insertCaAssetSyn(iAccount);
		// 创建账单定制信息
		// this.insertCaCustomizedInvoice(iAccount);
	}

	/**
	 * @Description:信用度上发
	 * @author yuxz
	 * @Date 2016-03-25
	 */
	private void insertCaAssetSyn(IAccount iAccount) {
		CaAssetSyn caAssetSyn = new CaAssetSyn();
		caAssetSyn.setSynId(DBUtil.getSequence(CaAssetSyn.class)); // 获取表的Sequence
		caAssetSyn.setAssetId(0L);
		caAssetSyn.setAcctId(iAccount.getAcctId());
		caAssetSyn.setCreateDate(context.getCommitDate());
		SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date validDate = parseDate.parse("1900-01-01");
			caAssetSyn.setValidDate(validDate); // 永久信用度开始时间取最早时间
		} catch (ParseException e) {
			imsLogger.error("*****1900年转换错误******");
		}
		caAssetSyn.setExpireDate(iAccount.getExpireDate());
		caAssetSyn.setSts(0);
		caAssetSyn.setStsDate(context.getCommitDate());
		caAssetSyn.setSoNbr(context.getSoNbr());
		caAssetSyn.setSyncFlag(CreditCmp.SYNC_FLAG);
		// 信用度上发触发信控
		caAssetSyn.setNotifyFlag(CreditCmp.NOTIFY_FLAG);
		caAssetSyn.setSyncType(CreditCmp.SYNC_TYPE);
		caAssetSyn.setSoDate(context.getCommitDate());
		// 获取账户信息，以便获取地区编号
		caAssetSyn.setRegionCode(iAccount.getRegionCode());
		super.insert(caAssetSyn);
	}

	/**
	 * @Description:账户默认永久信用度
	 * @author yuxz
	 * @Date 2016-03-25
	 */
	private void insertCaCredit(IAccount iAccount) {
		CaCredit caCredit = new CaCredit();
		CaAccount account = context.get3hTree().loadAcct3hBean(iAccount.getAcctId()).getAccount();
		caCredit.setAcctId(iAccount.getAcctId());
		caCredit.setAssetId(DBUtil.getSequence(CaAsset.class));
		caCredit.setBillingType(Integer.valueOf(1));// billing_type 默认1
		caCredit.setCreateDate(iAccount.getCommitDate());
		caCredit.setCreditAmount(Long.valueOf(0)); // 默认账户永久信用度 0
		caCredit.setResourceId(Long.valueOf(0));
		caCredit.setCreditItem(Integer.valueOf(10002)); // 账户永久信用度科目 10002
		caCredit.setExpireDate(iAccount.getExpireDate());
		caCredit.setMeasureId(account.getMeasureId());
		caCredit.setSoDate(iAccount.getCommitDate());
		caCredit.setSoNbr(context.getSoNbr());
		SimpleDateFormat parseDate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date validDate = parseDate.parse("1900-01-01");
			caCredit.setValidDate(validDate); // 永久信用度开始时间取最早时间
		} catch (ParseException e) {
			imsLogger.error("*****1900年转换错误******");
		}
		caCredit.setCreditFlag(Integer.valueOf(0));
		super.insert(caCredit);
	}

	/**
	 * @Description:账单定制信息资料
	 * @author caohm5
	 * @Date 2012-05-14
	 */
	@SuppressWarnings("unused")
	private void insertCaCustomizedInvoice(IAccount iAccount) {
		CaCustomInv caCustomInv = new CaCustomInv();
		caCustomInv.setCreateDate(context.getCommitDate());
		caCustomInv.setSoDate(context.getCommitDate());
		caCustomInv.setAcctId(iAccount.getAcctId());
		caCustomInv.setInvoiceType(EnumCodeExDefine.ACCOUNT_INVOICETYPE_NORMAL);
		// 2013-05-20 账单格式
		if (iAccount.getAcctType() != null && iAccount.getAcctType() == EnumCodeExDefine.ACCOUNT_TYPE_103) {
			caCustomInv.setBillFormatId(EnumCodeExDefine.FORMAT_ID_GROUP);
		} else {
			caCustomInv.setBillFormatId(EnumCodeExDefine.FORMAT_ID_NORMAL);
		}
		caCustomInv.setSummaryDeliver(iAccount.getBillDispatching());
		caCustomInv.setPrintBill(EnumCodeExDefine.BILL_PRINT_YES);
		if (iAccount.getBillLanguageId() != null) {
			caCustomInv.setLanguageId(iAccount.getBillLanguageId());
		} else {
			caCustomInv.setLanguageId(EnumCodeExDefine.BILL_DEFAULT_LANGUAGE);
		}
		// caCustomizedInvoice.setInvoiceCdrCode(iAccount.getInvoiceCdrCode());
		caCustomInv.setDueDay(iAccount.getDueDay());
		caCustomInv.setSoNbr(context.getSoNbr());
		caCustomInv.setValidDate(iAccount.getValidDate());
		caCustomInv.setExpireDate(iAccount.getExpireDate());
		super.insert(caCustomInv);

	}

	/**
	 * @Description:客户与账户关系资料
	 * @author caohm5
	 * @Date 2012-05-14
	 */
	private void insertCaCustomerRel(IAccount iAccount) {
		CaCustomerRel caCustomerRel = convertCustomerRel(iAccount);
		DBUtil.insertWithName(caCustomerRel, dbkey[0], specialTableName);
    	DBUtil.insertWithName(caCustomerRel, dbkey[1], specialTableName);
	}
	
	private CaCustomerRel convertCustomerRel(IAccount iAccount){
		CaCustomerRel caCustomerRel = new CaCustomerRel();
		caCustomerRel.setCreateDate(context.getCommitDate());
		caCustomerRel.setSoDate(context.getCommitDate());
		caCustomerRel.setCustId(iAccount.getCustId());
		caCustomerRel.setAcctId(iAccount.getAcctId());
		caCustomerRel.setRelationshipType(EnumCodeExDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE);
		caCustomerRel.setSoNbr(context.getSoNbr());
		caCustomerRel.setValidDate(iAccount.getValidDate());
		caCustomerRel.setExpireDate(iAccount.getExpireDate());
		caCustomerRel.setPartitionId((int)(iAccount.getCustId()%10000));
		return caCustomerRel;
	}
	
	/**
	 * @Description:账户资料
	 * @author caohm5
	 * @Date 2012-05-12
	 */
	private void insertCaAccount(IAccount iAccount) {
		AmountComponent amountCmp = context.getComponent(AmountComponent.class);

		CaAccount caAccount = new CaAccount();
		caAccount.setCreateDate(context.getCommitDate());
		caAccount.setSoDate(context.getCommitDate());
		caAccount.setAcctId(iAccount.getAcctId());
		caAccount.setAccountType(iAccount.getAcctType());
		caAccount.setAccountClass(iAccount.getAcctClass());
		/*
		 * ICredit iCredit = (ICredit)
		 * context.getSingleIDataObjectList(CreditHandler.class);
		 * // gaoqc5 2012-09-21 账户的信用等级从 I_CREDIT 中的 credit_level字段获取,为了测试，暂时保留
		 * 
		 * if (iCredit != null)
		 * {
		 * caAccount.setAccountSegment(iCredit.getCreditLevel());
		 * }
		 */
		/**
		 * @author songcc
		 * @Date 2013-12-30
		 * @Description 创建用户时，不设置默认信用度
		 */
		/*
		 * else
		 * {
		 * caAccount.setAccountSegment(100);
		 * //第一次创建账户，若没进行充值、没进行信用度创建，则调用创建信用度接口去创个账本
		 * //预后付属性变更时，会传下周期新增数据过来，此时也没有充值、信用度创建，这种情况是不需要去设置默认信用度的
		 * 
		 * }
		 */
		CaAccount dmAcct = this.querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, iAccount.getAcctId()));
		if (dmAcct != null) {
			caAccount.setAccountSegment(dmAcct.getAccountSegment());
		} else {
			caAccount.setAccountSegment(iAccount.getAcctSegment());
		}
		caAccount.setAccountStatus(iAccount.getStatus());
		caAccount.setStsDate(context.getCommitDate());
		// caAccount.setPin(iAccount.getPin());
		caAccount.setOrgId(EnumCodeExDefine.ACCOUNT_ORG_ID);
		caAccount.setProvinceId(iAccount.getProvCode());
		// 携号跨区特殊处理
		// if(acctinfo!=null){
		// caAccount.setRegionCode(acctinfo.getRegionCode());
		// }else{
		caAccount.setRegionCode(iAccount.getRegionCode());
		// }
		caAccount.setCountyCode(iAccount.getCountyCode());
		caAccount.setForceCycle(EnumCodeExDefine.CUSTOMER_FORCECYCLE_FALSE);
		// caAccount.setRevenueCode(iAccount.getRevenueCode());
		// @Date 2012-08-04 wukl Strory #49463 存储账务侧的度量单位
		if (iAccount.getMeasureId() != null && iAccount.getMeasureId() == 1) {
			Integer measureTypeId = amountCmp.queryMeasureTypeId(EnumCodeExDefine.MEASURE_ID_FEN);
			caAccount.setMeasureId(amountCmp.queryMeasureIdByTypeAndModule(measureTypeId, EnumCodeDefine.MODULE_BILLING));
		} else {
			caAccount.setMeasureId(amountCmp.queryBillingMeasureIdByType(amountCmp.queryLocalMeasureTypeId()));
		}
		// caAccount.setRemarks(iAccount.getRemarks());
		caAccount.setValidDate(iAccount.getValidDate());
		caAccount.setExpireDate(iAccount.getExpireDate());
		caAccount.setSoNbr(context.getSoNbr());
		caAccount.setOperatorId(EnumCodeExDefine.ACCOUNT_OPERATOR_ID);
		caAccount.setBillSts(EnumCodeExDefine.BILL_STS_ACTIVE);
		caAccount.setBillStsDate(context.getCommitDate());

		caAccount.setBalanceType(EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE);// wangjt：默认为账户级账本
		caAccount.setCreditSignControl(iAccount.getCreditSignControl());
		caAccount.setCustId(iAccount.getCustId());
		caAccount.setTaxFlag(EnumCodeDefine.TAX_FLAG_TRUE);// 上海不需要
		caAccount.setName(iAccount.getAcctName());
		caAccount.setPaymentType(iAccount.getPaymentType());
		super.insertWithAllCache(caAccount,new DBCondition(CaAccount.Field.acctId, iAccount.getAcctId()));
	}

	/**
	 * @Description:删除账户资料数据
	 * @author caohm5
	 * @Date 2012-05-14
	 */
	public void deleteAccount(IAccount iAccount) {
		// 删除账户资料
		Date expireDate = iAccount.getExpireDate();
		expireDate = jef.tools.DateUtils.monthBegin(expireDate);
		jef.tools.DateUtils.addMonth(expireDate, 1);
		this.deleteMode1(CaAccount.class,expireDate, new DBCondition(CaAccount.Field.acctId, iAccount.getAcctId()));
		// 删除客户与账户关系资料
		int partitionId = (int)(iAccount.getCustId()%10000);
		deleteCustomerRel(expireDate,iAccount.getAcctId(),iAccount.getCustId(),partitionId);
		
		this.deleteByCondition(CaCredit.class, expireDate,new DBCondition(CaCredit.Field.acctId, iAccount.getAcctId()), new DBCondition(
				CaCredit.Field.creditItem, Integer.valueOf(10002)));
		this.insertCaAssetSyn(iAccount); // 信用度删除

		// 删除账单定制信息资料
		// this.deleteByCondition(CaCustomInv.class, new
		// DBCondition(CaCustomInv.Field.acctId, iAccount.getAcctId()));
	}
	
	private void deleteCustomerRel(Date expireDate,long acctId,long custId,int partitionId){
		this.deleteByName_noInsert(CaCustomerRel.class, dbkey[0], specialTableName, expireDate,
				new DBCondition(CaCustomerRel.Field.acctId, acctId), new DBCondition(CaCustomerRel.Field.custId, custId), 
				new DBCondition(CaCustomerRel.Field.relationshipType,EnumCodeExDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE),
				new DBCondition(CaCustomerRel.Field.partitionId,partitionId));
    	
		this.deleteByName_noInsert(CaCustomerRel.class, dbkey[1], specialTableName, expireDate,
				new DBCondition(CaCustomerRel.Field.acctId, acctId), new DBCondition(CaCustomerRel.Field.custId, custId), 
				new DBCondition(CaCustomerRel.Field.relationshipType,EnumCodeExDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE),
				new DBCondition(CaCustomerRel.Field.partitionId,partitionId));
	}

	/**
	 * @Description:更新账户资料
	 * @author caohm5
	 * @Date 2012-05-14
	 */
	public void updateAccount(IAccount iAccount) {
		CaAccount dmAcct = context.get3hTree().loadAcct3hBean(iAccount.getAcctId()).getAccount();
		if (dmAcct.getCustId() != iAccount.getCustId().longValue()) {
			
			//变更路由
			imsLogger.info("账户对应的客户发生变化，更新zd.sys_rt_account");
			context.getCmp(RouterCmp.class).createAcctsRouter(iAccount.getAcctId(), iAccount.getCustId(), iAccount.getRegionCode(), context.getCommitDate());
			// 修改AD.CA_CUSTOMER_REL
			CaCustomerRel rel = convertCustomerRel(iAccount);
			updateCustRel(rel);
		}
		// 更新账户表
		this.updateCaAccount(iAccount);

	}
	
	private void updateCustRel(CaCustomerRel rel){
		this.updateWithInsertBykey(rel, null, null, dbkey[0], specialTableName, 
				new DBCondition(CaCustomerRel.Field.acctId,rel.getAcctId()),
				new DBCondition(CaCustomerRel.Field.partitionId,rel.getPartitionId()));
    	this.updateWithInsertBykey(rel, null, null, dbkey[1], specialTableName, 
    			new DBCondition(CaCustomerRel.Field.acctId,rel.getAcctId()),
    			new DBCondition(CaCustomerRel.Field.partitionId,rel.getPartitionId()));
	}

	/**
	 * @Description:更新账单定制信息
	 * @author caohm5
	 * @Date 2012-05-14
	 */
	@SuppressWarnings("unused")
	private void updateCaCustomizedInvoice(IAccount iAccount) {
		CaCustomInv caCustomInv = new CaCustomInv();
		// caCustomizedInvoice.setCreateDate(context.getCreateDate());
		caCustomInv.setSoDate(context.getCommitDate());
		caCustomInv.setAcctId(iAccount.getAcctId());
		// caCustomizedInvoice.setInvoiceType(EnumCodeExDefine.ACCOUNT_INVOICETYPE_NORMAL);
		// caCustomizedInvoice.setBillFormatId(iAccount.getBillFormatId());
		if (iAccount.getAcctType() != null && iAccount.getAcctType() == EnumCodeExDefine.ACCOUNT_TYPE_103) {
			caCustomInv.setBillFormatId(EnumCodeExDefine.FORMAT_ID_GROUP);
		} else {
			caCustomInv.setBillFormatId(EnumCodeExDefine.FORMAT_ID_NORMAL);
		}
		caCustomInv.setSummaryDeliver(iAccount.getBillDispatching());
		if (iAccount.getBillDispatching() != null) {
			caCustomInv.setPrintBill(EnumCodeExDefine.BILL_PRINT_NO);
		} else {
			caCustomInv.setPrintBill(EnumCodeExDefine.BILL_PRINT_YES);
		}
		caCustomInv.setLanguageId(iAccount.getBillLanguageId());
		// caCustomizedInvoice.setInvoiceCdrCode(iAccount.getInvoiceCdrCode());
		caCustomInv.setDueDay(iAccount.getDueDay());
		caCustomInv.setSoNbr(context.getSoNbr());
		// caCustomizedInvoice.setValidDate(iAccount.getValidDate());
		caCustomInv.setExpireDate(iAccount.getExpireDate());
		this.updateByCondition(caCustomInv, new DBCondition(CaCustomInv.Field.acctId, iAccount.getAcctId()));
	}

	/**
	 * @Description:更新账户表
	 * @author caohm5
	 * @Date 2012-05-14
	 */
	private void updateCaAccount(IAccount iAccount) {
		CaAccount caAccount = new CaAccount();
		// caAccount.setCreateDate(context.getCreateDate());
		caAccount.setSoDate(context.getCommitDate());
		caAccount.setAcctId(iAccount.getAcctId());
		caAccount.setAccountType(iAccount.getAcctType());
		caAccount.setAccountClass(iAccount.getAcctClass());
		// account_segment只能通过表I_CREDIT来修改
		// 广西版本从i_account表更新
		// caAccount.setAccountSegment(iAccount.getAcctSegment());
		caAccount.setAccountStatus(iAccount.getStatus());
		caAccount.setStsDate(context.getCommitDate());
		// caAccount.setPin(iAccount.getPin());
		caAccount.setOrgId(EnumCodeExDefine.ACCOUNT_ORG_ID);
		caAccount.setProvinceId(iAccount.getProvCode());
		// 携号跨区不更新region_code
		caAccount.setRegionCode(iAccount.getRegionCode());
		caAccount.setCountyCode(iAccount.getCountyCode());
		// caAccount.setForceCycle(EnumCodeExDefine.CUSTOMER_FORCECYCLE_FALSE);
		// caAccount.setRevenueCode(iAccount.getRevenueCode());
		// caAccount.setMeasureId(iAccount.getMeasureId());
		// caAccount.setRemarks(iAccount.getRemarks());
		caAccount.setValidDate(iAccount.getValidDate());
		caAccount.setExpireDate(iAccount.getExpireDate());
		caAccount.setSoNbr(context.getSoNbr());
		// caAccount.setOperatorId(EnumCodeExDefine.ACCOUNT_OPERATOR_ID);
		// caAccount.setBillSts(EnumCodeExDefine.BILL_STS_ACTIVE);
		caAccount.setBillStsDate(context.getCommitDate());

		caAccount.setBalanceType(EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE);
		caAccount.setCreditSignControl(iAccount.getCreditSignControl());
		caAccount.setCustId(iAccount.getCustId());
		// 上海不需要
		// caAccount.setTaxFlag(iAccount.getTaxFlag());
		caAccount.setTaxFlag(0);
		caAccount.setName(iAccount.getAcctName());
		caAccount.setPaymentType(iAccount.getPaymentType());
		caAccount.setCreateDate(context.getCommitDate());
		this.updateMode1(caAccount,  new DBCondition(CaAccount.Field.acctId,iAccount.getAcctId()));
	}

	/**
	 * 该方法主要针对下周期生效的数据未生效前又进行变更的场景；此时先删除未来生效的数据
	 * 
	 * @Author: wukl
	 * @Date: 2012-9-7
	 * @param dataList
	 */
	public void beforeHandle(List<? extends DataObject> dataList) {
		IAccount iAccount = (IAccount) dataList.get(0);
		// 若搜索的数据少于等于一条，并且路由中不存在数据，则不处理

		// 将账户相关的表中未来生效数据置为失效
		Date expireDate = context.getCommitDate();
		expireDate = jef.tools.DateUtils.monthBegin(expireDate);
		jef.tools.DateUtils.addMonth(expireDate, 1);

		// 过户时索引中的账户为过户下家，这样就会错误的从新的分表中查询，所以先清一把SESSION中的分表参数
		ITableUtil.setValue2ContextHolder(iAccount.getCustId(), iAccount.getAcctId(), null);

		List<CaAccount> acctList = delete_noValid(CaAccount.class, new DBCondition(CaAccount.Field.acctId, iAccount.getAcctId()),
				new DBCondition(CaAccount.Field.validDate, expireDate, Operator.GREAT_EQUALS), new DBCondition(CaAccount.Field.expireDate,
						expireDate, Operator.GREAT));
		if (CommonUtil.isNotEmpty(acctList)) {
			delete_noValid(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.acctId, iAccount.getAcctId()), new DBCondition(
					CaCustomerRel.Field.validDate, expireDate, Operator.GREAT_EQUALS), new DBCondition(CaCustomerRel.Field.expireDate,
					expireDate, Operator.GREAT));
		}
		/*
		 * delete_noValid(CaCustomInv.class, new DBCondition(
		 * CaCustomInv.Field.acctId, iAccount.getAcctId()), new
		 * DBCondition(CaCustomInv.Field.validDate, expireDate,
		 * Operator.GREAT), new DBCondition(CaCustomInv.Field.expireDate,
		 * expireDate, Operator.GREAT));
		 */
	}

	/**
	 * lijc3 2012-9-18 创建账户前对客户和客户联系人进行挪库操作，不需要缓存
	 * 
	 * @param iAccount
	 */
	public void changeCustBeforeCreateAcct(IAccount iAccount) {
		Long custId = iAccount.getCustId();
		// 先删除客户数据
		imsLogger.debug("###########delete all infomation of customer by cust_id = ", custId);
		@SuppressWarnings("unchecked")
		List<CmParty> partyList = (List<CmParty>) DBUtil.deleteByConditionWithReturn(CmParty.class, new DBCondition(CmParty.Field.partyId,
				custId));
		List<CmPartyIdentity> identityList = (List<CmPartyIdentity>) DBUtil.deleteByConditionWithReturn(CmPartyIdentity.class,
				new DBCondition(CmPartyIdentity.Field.partyId, custId));
		List<CmPartyRole> partyRoleList = (List<CmPartyRole>) DBUtil.deleteByConditionWithReturn(CmPartyRole.class, new DBCondition(
				CmPartyRole.Field.partyId, custId));
		List<CmCustomer> custList = (List<CmCustomer>) DBUtil.deleteByConditionWithReturn(CmCustomer.class, new DBCondition(
				CmCustomer.Field.custId, custId));
		List<CmIndividual> indivList = (List<CmIndividual>) DBUtil.deleteByConditionWithReturn(CmIndividual.class, new DBCondition(
				CmIndividual.Field.partyId, custId));
		List<CmIndividualName> indivNameList = (List<CmIndividualName>) DBUtil.deleteByConditionWithReturn(CmIndividualName.class,
				new DBCondition(CmIndividualName.Field.partyId, custId));
		List<CmOrganization> orgList = (List<CmOrganization>) DBUtil.deleteByConditionWithReturn(CmOrganization.class, new DBCondition(
				CmOrganization.Field.partyId, custId));
		List<CmOrgName> orgNameList = (List<CmOrgName>) DBUtil.deleteByConditionWithReturn(CmOrgName.class, new DBCondition(
				CmOrgName.Field.partyId, custId));
		List<CmContactMedium> mediumList = (List<CmContactMedium>) DBUtil.deleteByConditionWithReturn(CmContactMedium.class,
				new DBCondition(CmContactMedium.Field.objectId, custId), new DBCondition(CmContactMedium.Field.objectType,
						EnumCodeDefine.CUST_CONTACT_TYPE));
		// 发布路由
		imsLogger.debug("############begin to create router############");
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		// 发布路由的时候需要先判断客户账户路由是否存在，如果存在，则不需要发布
		boolean flag = routCmp.isCustRouted(custId);
		if (!flag) {
			routCmp.createAcctRouter(iAccount.getAcctId(), iAccount.getCustId(), iAccount.getRegionCode(), iAccount.getValidDate(),
					iAccount.getExpireDate());
		}
		imsLogger.debug("############after create router, insert all infomation into new DB");
		if (CommonUtil.isNotEmpty(partyList)) {
			DBUtil.insertBatch(partyList);
		}
		if (CommonUtil.isNotEmpty(identityList)) {
			DBUtil.insertBatch(identityList);
		}
		if (CommonUtil.isNotEmpty(partyRoleList)) {
			DBUtil.insertBatch(partyRoleList);
		}
		if (CommonUtil.isNotEmpty(custList)) {
			DBUtil.insertBatch(custList);
		}
		if (CommonUtil.isNotEmpty(indivList)) {
			DBUtil.insertBatch(indivList);
		}
		if (CommonUtil.isNotEmpty(indivNameList)) {
			DBUtil.insertBatch(indivNameList);
		}
		if (CommonUtil.isNotEmpty(orgList)) {
			DBUtil.insertBatch(orgList);
		}
		if (CommonUtil.isNotEmpty(orgNameList)) {
			DBUtil.insertBatch(orgNameList);
		}
		if (CommonUtil.isNotEmpty(mediumList)) {
			DBUtil.insertBatch(mediumList);
		}
	}
	
	/**
	 * 信控更新
	 * @return
	 */
	public final boolean isOnlyUpdateAcctSegment()
    {
    	String soNbr = StringUtils.toString(context.getSoNbr());
    	if(soNbr.substring(soNbr.length()-2, soNbr.length()-1).equals(ConstantDefine.CC_UPDATE)){
    		return true;
    	}
        return false;
    }
	/**
	 * 信控更新，只更新信用等级
	 * @param iAccount
	 */
	public void onlyUpdateAcctSegment(IAccount iAccount){
		CaAccount caAccount = new CaAccount();
		caAccount.setAcctId(iAccount.getAcctId());
		caAccount.setAccountSegment(iAccount.getAcctSegment());
		this.updateByCondition(caAccount,new DBCondition(CaAccount.Field.acctId, iAccount.getAcctId()));
	}
	/*
	 * XXX
	 * public void doCancelReturnMailByAcctId(Long acctId)
	 * {
	 * ReturnMailAcctCancelIn acctIn = new ReturnMailAcctCancelIn();
	 * acctIn.setAcctId(acctId);
	 * imsLogger.debug(
	 * "*****************begin to call ReturnMailAcctCancelIn *****************"
	 * );
	 * imsLogger.debug("ReturnMailAcctCancelIn****acctId=", acctIn.getAcctId());
	 * 
	 * long startTime = System.currentTimeMillis();
	 * try
	 * {
	 * Integer result =
	 * SpringShUtil.getOweBusiService().cancelReturnMailByAcct(acctIn);
	 * 
	 * if (result != EnumCodeExDefine.CALL_INTERFACE_SUCCESS)
	 * {
	 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
	 * "ReturnMailAcctCancelIn");
	 * }
	 * }
	 * catch (Exception e)
	 * {
	 * IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,
	 * "ReturnMailAcctCancelIn");
	 * }
	 * 
	 * imsLogger.debug("按帐户取消退信标识: ", (System.currentTimeMillis() - startTime));
	 * 
	 * }
	 */
}
