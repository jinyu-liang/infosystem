package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.component.billcycle.entity.ChangeBillCycleContext;
import com.ailk.ims.component.billcycle.handler.BillCycleHandler;
import com.ailk.ims.component.helper.AcctHelper;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.BusiCodeDefine;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.acct.entity.CaPaymentPlan;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustExt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmNotificationFlag;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.ImsTermCycle;
import com.ailk.openbilling.persistence.imsintf.entity.BankInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BudgetControl;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.ModifyAcctReq;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SPayChannel;
import com.ailk.openbilling.persistence.imsintf.entity.SPayChannelList;
import com.ailk.openbilling.persistence.sys.entity.SysBankInfo;

/**
 * @Description: 账户组件，用于定义跟账户操作相关的各种方法
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-1 <br>
 *       2011-07-25 wuyujie,增加方法createCaAccount <br>
 *       2011-07-27 wuyujie,账户表增加生效时间和失效时间 <br>
 *       2011-7-30 wukl
 *       增加queryVpnAccount,queryParentAcctIdByGCA,queryBillAcctIdByGCA方法<br>
 *       2011-08-02 wuyujie : 新增createBillCycle等对账期的操作方法<br>
 *       2011-08-04 zengxr Modify for changing of SPayChannel <br>
 *       2011-08-08 zengxr set address1-address4 of SContact <br>
 *       2011-8-11 wangjt add isAccountNotMoreThanOneUser()<br>
 *       2011-09-17 wuyujie : 设置父账户的时候要取真实ID <br>
 *       2011-10-07 yanchuan : 修改帐户及其所归属用户的订购的产品的账期<br>
 *       2011-10-19 yanchuan : 付费渠道结构修改，修改实例化付费渠道<br>
 * @date luojb 2012-3-28 预付费用户设置用户级负账本
 * @date wangjt 2012-3-29 修改isEmpty方法用来判断对象的问题
 * @date wangjt 2012-3-29 上海新建账户不需要创建ca层级、不需要账户预算
 * @date wangjt 2012-3-29 billCycleValid方法名称修改
 * @Date 2012-03-30 lijc3 校验是否传入userId
 * @Date 2012-03-31 wangjt 上海：根据报文中的账期结构创建帐户账期
 * @Date 2012-03-31 wangjt 上海：forceCycle、billSts字段设置为默认值
 * @date 2012-04-02 zengxr No need to convert the value, because ZG will convert
 *       it.
 * @Date 2012-4-3 tangjl5 创建账户时，若内部ID与外部一样时，判断该账户是否已经存在
 * @Date 2012-04-04 zengxr first delete all paychannel, then add new .
 * @Date 2012-04-04 zengxr add vat_name for modify acct basic information
 * @Date 2012-04-12 zengqm 更改帐户时，如果是上海项目，忽略forceCycle
 * @Date 2012-04-16 yangjh CaBillCycleIn的outSoDate获取修改为获取账户有效期
 * @Date 2012-04-25 yangjh sAccountTransform方法中把新建对象放到判断后面
 * @Date 2012-04-26 lijc3 如果修改账户状态为激活，需要修改出账状态为未定义
 * @Date 2012-4-27 tangjl5 Task #44713 从扩张字段中获取税率标志存入CaAccount
 * @Date 2012-5-29 tangjl5 BUG #47032 对userId_list进行空判断
 * @Date 2012-05-29 yangjh User Story #46599 三户新装设置默认信用度
 * @Date 2012-06-13 yangjh 创建修改账期 outSoDate修改
 * @Date 2012-06-18 yangjh createAcctCredit代码优化
 * @Date 2012-06-18 yangjh 设置信用度如果为无穷大 value修改为0 原先为-1帐管会报错
 * @Date 2012-06-20 yangjh credit_value不为空，去掉这个字段是否为空的判断
 * @Date 2012-06-20 yangjh 增加SysGroupCredit->creditValue为负的判断
 * @Date 2012-06-21 yangjh 设置信用度增加失效时间的传入，生效时间传入修改
 * @Date 2012-06-29 tangkun add ORIGINAL_DefaultCPSId
 * @Date 2012-07-11 yangjh 增加账户billSts的修改
 * @Date 2012-07-12 wangdw5 : #51420 _EmailBillTo未保存到数据库中Item10
 * @date 2012-07-18 luojb #49524 增加方法
 * @Date 2012-07-25 yangjh : story ：52730 增加spec_acct_type字段入库上发
 */

public class AccountComponent extends AccountQuery {
	public AccountComponent() {
	}

	public CaAccount createAccount(SAccount account) throws IMSException {
		// 先验证客户是否真实存在
		Long custId = account.getCust_id();

		// 1、客户id必须存在
		context.getComponent(CheckComponent.class).checkCustId(custId);

		// 2、新建账户信息
		CaAccount dmAccount = createCaAccount(account);

		// 3、新建客户账户关系
		createCustomerAccountsRelation(custId, dmAccount.getAcctId());

		// 4、新建账户账期
		this.createBillCycle(account, dmAccount);

		if (ProjectUtil.is_TH_AIS())// 上海不需要
		{
			// 5、新建账户预算
			createAcctBudget(account, dmAccount.getAcctId());
		}

		// 6、新建账户付费渠道信息
		if (account.getPay_channel_list() != null) {
			createAcctPayChannel(account, account.getPay_channel_list(),
					dmAccount.getAcctId());
		}

		// 7、创建定制账单信息
		this.createAcctInvoice(account, dmAccount.getAcctId());

		// 9、创建ca层级(上海不需要)
		if (account.getParent_acct_id() != null && ProjectUtil.is_TH_AIS()) {
			// 2011-09-17 wuyujie : 设置父账户的时候要取真实ID
			CaAccount parentCA = context.get3hTree()
					.loadAcct3hBean(account.getParent_acct_id()).getAccount();
			if (parentCA == null)
				throw IMSUtil.throwBusiException(
						ErrorCodeDefine.NEWREG_PARENT_ACCOUNT_NOTEXIST,
						account.getParent_acct_id(), dmAccount.getAcctId());
			// 创建帐户层级关系 yanchuan 2011-11-24
			AccountRelationComponent arc = context
					.getComponent(AccountRelationComponent.class);
			// 2012-03-24 luojb 增加能否创建层级关系的校验
			arc.addAllowed(parentCA.getAcctId(), dmAccount.getAcctId());
			arc.createGCARelations(parentCA.getAcctId(), dmAccount.getAcctId(),
					EnumCodeDefine.ACCOUNT_ORDINARY_GROUP_ROLE);
		}

		return dmAccount;
	}

	private void createAcctExtParams(SAccount account, long acctId,
			CaCustomInv dmInvoice) {
		// @Date 2012-07-25 yangjh : story ：52730 增加spec_acct_type字段入库上发
		String specialAccnt = null;
		if (account.getList_ext_param() == null) {
			specialAccnt = String.valueOf(0);
			CaAccountExt attr = new CaAccountExt();
			attr.setAcctId(acctId);
			attr.setItem8(specialAccnt);
			insert(attr);
			return;
		}

		ExtendParamList paramList = account.getList_ext_param();
		if (CommonUtil.isEmpty(paramList.getExtendParamList_Item()))
			return;
		String printBill = null;
		String smsContactNum = null;
		String billHandlingCode = null;
		String acctBMCallDetail = null;
		String acctBMSummary = null;
		String emailAcctBMCall = null;
		String emailAcctBMSummary = null;
		String attrAcctEmail = null;
		// @Date 2012-4-27 tangjl5 Task #44713
		// 从扩张字段中获取税率标志存入CaAccount，该扩展字段不存入ca_account_attribute表中
		// String defaultCPSId = null;
		String invoiceType = null;
		String billStyleId = null;
		String faxBillTo = null;
		String faxAccBMCall = null;
		String faxAccBMSum = null;

		// 2011-09-20 增加扩展字段 luojb

		String chargeType = null;
		String smsBillTo = null;
		String billPaymentCurrency = null;
		// 2012-02-03 yanchuan 增加扩展字段
		String emailbillto = null;
		String billname = null;
		String vatname = null;
		// 2012-06-29 tangkun 增加扩展字段
		String originalDefaultCPSId = null;
		String copyBillBudget = null;
		for (ExtendParam param : paramList.getExtendParamList_Item()) {
			if (CommonUtil.isEmpty(param.getParam_name())
					|| CommonUtil.isEmpty(param.getParam_value()))
				continue;
			String name = param.getParam_name();
			String value = param.getParam_value();

			if (ConstantDefine.EXTPARAM_ACCT_PRINTBILL.equals(name)) {
				printBill = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_SMSCONTACTNUM.equals(name)) {
				smsContactNum = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_BILLHANDLINGCODE
					.equals(name)) {
				billHandlingCode = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_ACCTBMCALLDETAIL
					.equals(name)) {
				acctBMCallDetail = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_ACCTBMSUMMARY.equals(name)) {
				acctBMSummary = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_EMAILACCTBMCALL
					.equals(name)) {
				emailAcctBMCall = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_EMAILACCTBMSUMMARY
					.equals(name)) {
				emailAcctBMSummary = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_ATTRACCTEMAIL.equals(name)) {
				attrAcctEmail = value;
			}
			// else if (ConstantDefine.EXTPARAM_ACCT_DEFAULTCPSID.equals(name))
			// {
			// defaultCPSId = value;
			// }
			else if (ConstantDefine.EXTPARAM_ACCT_INVOICETYPE.equals(name)) {
				invoiceType = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_BILLSTYLEID.equals(name)) {
				billStyleId = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_FAXBILLTO.equals(name)) {
				faxBillTo = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_FAXACCBMCALL.equals(name)) {
				faxAccBMCall = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_FAXACCBMSUM.equals(name)) {
				faxAccBMSum = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_SPECIALACCNT.equals(name)) {
				specialAccnt = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_CHARGETYPE.equals(name)) {
				chargeType = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_SMSBILLTO.equals(name)) {
				smsBillTo = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_BILLPAYMENTCURRENCY
					.equals(name)) {
				billPaymentCurrency = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_EMAILBILLTO.equals(name)) {
				emailbillto = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_BILLNAME.equals(name)) {
				billname = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_VATNAME.equals(name)) {
				vatname = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_ORIGINAL_DEFAULTCPSID
					.equals(name)) {
				originalDefaultCPSId = value;
			} else if (ConstantDefine.EXTPARAM_ACCT_COPYBILLBUDGET.equals(name)) {
				copyBillBudget = value;
			}

		}
		if (specialAccnt == null) {
			specialAccnt = String.valueOf(0);
		}
		if (!CommonUtil.isEmpty(printBill))
			dmInvoice.setPrintBill(Integer.valueOf(printBill));

		if (!CommonUtil.isEmpty(billHandlingCode))
			dmInvoice.setMailCode(Long.valueOf(billHandlingCode));

		if (!CommonUtil.isEmpty(invoiceType))
			dmInvoice.setInvoiceType(Integer.valueOf(invoiceType));

		if (!CommonUtil.isEmpty(billStyleId))
			dmInvoice.setBillFormatId(Integer.valueOf(billStyleId));

		CaAccountExt attr = new CaAccountExt();
		attr.setAcctId(acctId);
		attr.setItem1(smsContactNum);
		attr.setItem2(acctBMCallDetail);
		attr.setItem3(acctBMSummary);
		// attr.setItem4(defaultCPSId);
		attr.setItem5(faxBillTo);
		attr.setItem6(chargeType);
		attr.setItem7(smsBillTo);
		// @Date 2012-10-18 yugb :61088 皇室成员不收取一次性费用
		attr.setItem8(specialAccnt);
		attr.setItem9(billPaymentCurrency);
		attr.setItem10(emailbillto);
		attr.setItem11(billname);
		attr.setItem12(vatname);
		attr.setItem13(originalDefaultCPSId);
		attr.setItem14(copyBillBudget);
		insert(attr);

		// 创建联系地址
		Date validDate = IMSUtil.formatDate(account.getValid_date(),
				context.getRequestDate());
		// 创建帐户联系信息
		context.getComponent(ContactComponent.class).createExtContact(acctId,
				EnumCodeDefine.ACCT_CONTACT_TYPE, validDate, emailAcctBMCall,
				faxAccBMCall, emailAcctBMSummary, faxAccBMSum, attrAcctEmail);
	}

	public CaAccount createCaAccount(SAccount account) throws IMSException {
		ExtendParamList paramList = account.getList_ext_param();
		// @Date 2012-4-3 tangjl5 当内部ID与外部一样时，判断该账户是否已经存在
		if (SysUtil.getBoolean(SysCodeDefine.busi.INNER_OUTER_ID_EQUAL)
				&& CommonUtil.isValid(account.getAcct_id())) {
			try {
				context.get3hTree().loadAcct3hBean(account.getAcct_id())
						.getAccount();
				IMSUtil.throwBusiException(
						ErrorCodeDefine.NEWREG_ACCOUNT_EXISTS,
						account.getAcct_id());
			} catch (IMS3hNotFoundException e) {
				imsLogger.error(e, e);
			}
		}

		CaAccount dmAccount = new CaAccount();
		// caohm5 add 帐户表增加冗余字段客户编号
		dmAccount.setCustId(account.getCust_id());

		// 2011-09-22 wuyujie : 内外id映射修改
		if (account.getAcct_id() == null) {
			dmAccount.setAcctId(DBUtil.getSequence(CaAccount.class));
		} else {
			dmAccount.setAcctId(account.getAcct_id());
		}

		dmAccount.setName(account.getAcct_name());
		// 增加默认的帐户类型 yanchuan 2011-11-22
		if (account.getAcct_type() != null) {
			dmAccount.setAccountType(Integer.valueOf(account.getAcct_type()));
		} else {
			dmAccount.setAccountType(SysUtil
					.getInt(SysCodeDefine.busi.DEFAULT_ACCOUNT_TYPE));
		}

		// 增加默认帐户大类 yanchuan 2011-11-22
		if (account.getAcct_class() != null)
			dmAccount.setAccountClass(account.getAcct_class().intValue());
		else
			dmAccount.setAccountClass(SysUtil
					.getInt(SysCodeDefine.busi.DEFAULT_ACCOUNT_CLASS));
		if (account.getAcct_segment() != null)
			dmAccount.setAccountSegment(account.getAcct_segment().intValue());
		else
			dmAccount
					.setAccountSegment(EnumCodeDefine.ACCOUNT_DEFAULT_ACCOUNT_SEGMENT);
		dmAccount.setAccountStatus(EnumCodeDefine.ACCOUNT_ACTIVE);// 默认为active
		dmAccount.setStsDate(context.getRequestDate());
		if (account.getProv_code() != null)
			dmAccount.setProvinceId(Integer.valueOf(account.getProv_code()));
		else
			dmAccount.setProvinceId(EnumCodeDefine.ACCOUNT_DEFAULT_PROVINCE_ID);

		if (account.getRegion_code() != null)
			dmAccount.setRegionCode(account.getRegion_code().intValue());
		else
			dmAccount.setRegionCode(EnumCodeDefine.ACCOUNT_DEFAULT_REGION_CODE);

		if (account.getCounty_code() != null)
			dmAccount.setCountyCode(account.getCounty_code());
		else
			dmAccount.setCountyCode(EnumCodeDefine.ACCOUNT_DEFAULT_COUNTY_CODE);

		if (account.getCompany() != null)
			dmAccount.setOperatorId(account.getCompany());
		else
			dmAccount.setOperatorId(SysUtil
					.getInt(SysCodeDefine.busi.DEFAULT_ACCOUNT_COMPANY_ID));

		dmAccount.setSoNbr(context.getSoNbr());

		// 获取帐户的强制账期表示(上海设置为非强制账期)
		int forceCycle = EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE;
		if (ProjectUtil.is_TH_AIS()) {
			forceCycle = getFroceCycle(account);
		}
		dmAccount.setForceCycle(forceCycle);

		// 2011-07-27 wuyujie ,账户表增加生效时间和失效时间
		dmAccount.setValidDate(IMSUtil.formatDate(account.getValid_date(),
				context.getRequestDate()));
		dmAccount.setExpireDate(IMSUtil.formatExpireDate(account
				.getExpire_date()));
		dmAccount.setCreateDate(IMSUtil.formatDate(account.getCreate_date(),
				context.getRequestDate()));
		if (context.getOper().getOrg_id() != null) {
			dmAccount.setOrgId(context.getOper().getOrg_id());
		} else {
			dmAccount.setOrgId(0);
		}

		AmountComponent amountCmp = context.getComponent(AmountComponent.class);
		// @Date 2012-7-12 tangjl5 Strory #49447 存储账务侧的度量单位
		if (account.getMeasure_id() != null) {
			Integer measureTypeId = amountCmp.queryMeasureTypeId(account
					.getMeasure_id());
			dmAccount.setMeasureId(amountCmp
					.queryBillingMeasureIdByType(measureTypeId));
		} else {
			dmAccount
					.setMeasureId(amountCmp
							.queryDefaultMeasureIdByType(EnumCodeDefine.MODULE_BILLING));
		}

		if (ProjectUtil.is_CN_SH()) {
			dmAccount.setBillSts(EnumCodeDefine.BILL_STS_ACTIVE);// 上海设置为出账
		} else {
			// 2012-07-11 yangjh story:51368 billSts处理逻辑修改
			if (account.getBill_sts() != null) {
				dmAccount.setBillSts(account.getBill_sts());
			} else {
				if (account.getBalance_type() != null
						&& account.getBalance_type() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE) {
					dmAccount.setBillSts(EnumCodeDefine.BILL_STS_NOT_DEFINED);
				} else if (AcctHelper.isEmptyBillCycle(account.getBill_cycle())
						&& (forceCycle == EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE || forceCycle == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE)) {
					dmAccount.setBillSts(EnumCodeDefine.BILL_STS_NOT_DEFINED);
				} else {
					dmAccount.setBillSts(EnumCodeDefine.BILL_STS_ACTIVE);
				}
			}
		}

		dmAccount.setBillStsDate(context.getRequestDate());

		if (account.getBalance_type() != null)
			dmAccount.setBalanceType(account.getBalance_type());
		else
			dmAccount.setBalanceType(SysUtil
					.getInt(SysCodeDefine.busi.DEFAULT_BALANCE_TYPE));

		// 上海才需要信控标志
		if (ProjectUtil.is_CN_SH() && account.getSign_credit_control() != null) {
			dmAccount.setCreditSignControl(account.getSign_credit_control());
		}

		// @Date 2012-4-27 tangjl5 Task #44713 从扩张字段中获取税率标志存入CaAccount
		dmAccount.setTaxFlag(EnumCodeDefine.TAX_FLAG_TRUE);
		Integer taxFlag = getTaxFalg(account);
		if (null != taxFlag) {
			dmAccount.setTaxFlag(taxFlag);
		}
		// @Date 2012-10-18 yugb :61088 皇室成员不收取一次性费用
		if (paramList != null) {
			for (ExtendParam param : paramList.getExtendParamList_Item()) {
				if (CommonUtil.isEmpty(param.getParam_name())
						|| CommonUtil.isEmpty(param.getParam_value()))
					continue;
				String name = param.getParam_name();
				String value = param.getParam_value();
				if (ConstantDefine.EXTPARAM_ACCT_SPECIALACCNT.equals(name)) {
					dmAccount.setSpecialAccount(CommonUtil
							.string2Integer(value));
				}
			}
		}
		super.insert(dmAccount);

		return dmAccount;
	}

	private void createCustomerAccountsRelation(long custId, long acctId)
			throws IMSException {
		CaCustomerRel dmRelation = new CaCustomerRel();
		dmRelation.setAcctId(acctId);
		dmRelation.setCustId(custId);
		dmRelation
				.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE);

		insert(dmRelation);
	}

	/**
	 * @Description: 创建账户账期，如果传入的账期为空，则需要根据情况是否执行客户的强制账期
	 */
	private void createBillCycle(SAccount account, CaAccount dmAccount) {
		// 为空，或者都有值，表示合法
		if (!AcctHelper.billCycleValid(account.getBill_cycle())) {
			throw IMSUtil
					.throwBusiException(ErrorCodeDefine.NEWREG_BILL_CYCLE_NOT_FULL);
		}

		// 如果是single balance 帐户则需要特殊处理，默认不出账 yanchuan 2012-03-07
		if (dmAccount.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE) {
			BillCycleHandler billCycleHandler = ChangeBillCycleContext
					.instanceHandler(dmAccount.getAcctId(), null, null, null,
							null, context);
			billCycleHandler.createBillCycle();
		}
		// 帐户使用所属客户的账期
		else if (dmAccount.getForceCycle() == EnumCodeDefine.FORCECYCLE_BY_CUSTOMER) {
			CmCustExt custExt = (CmCustExt) context
					.getExtendParam(ConstantDefine.CMCUSTEXT_OBJECT);
			if (custExt == null)
				custExt = context.getComponent(CustomerComponent.class)
						.queryCustExt(account.getCust_id());
			// 如果关联客户有强制账期，则使用客户的账期
			if (custExt != null && custExt.getCycleSpecId() != null) {
				BillCycleHandler billCycleHandler = ChangeBillCycleContext
						.instanceHandler(dmAccount.getAcctId(),
								custExt.getCycleSpecId(), null, null, null,
								context);
				billCycleHandler.createBillCycle();
			} else
				throw IMSUtil
						.throwBusiException(ErrorCodeDefine.NEWREG_CYCLE_SEPC_ID_ERROR);
		}
		// 帐户使用其父帐户的账期
		else if (dmAccount.getForceCycle() == EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT) {
			// 如果父帐户是强制账期，子帐户与父帐户进行账期同步
			Acct3hBean acctBean = context.get3hTree().loadAcct3hBean(
					account.getParent_acct_id());
			CaAccount parentAcct = acctBean.getAccount();
			if (parentAcct.getBillSts() != null
					&& parentAcct.getBillSts() == EnumCodeDefine.BILL_STS_ACTIVE) {
				// context.getComponent(BillingPlanComponent.class).synAcctBillCycle(parentAcct.getAcctId(),
				// dmAccount);
			} else {
				throw IMSUtil.throwBusiException(
						ErrorCodeDefine.NEWREG_PARENT_ACCOUNT_IS_NOT_ACCTIVE,
						dmAccount.getAcctId(), account.getParent_acct_id());
			}
		} else {
			if (dmAccount.getForceCycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE
					&& account.getBill_cycle() == null) {
				throw IMSUtil.throwBusiException(
						ErrorCodeDefine.NEWREG_BILL_CYCLE_IS_NULL,
						dmAccount.getAcctId());
			}
			// 根据报文中的账期结构创建帐户账期
			BillCycleHandler billCycleHandler = ChangeBillCycleContext
					.instanceHandler(dmAccount.getAcctId(), null,
							account.getBill_cycle(), null, null, context);
			billCycleHandler.createBillCycle();
		}

	}

	/*
	 * private CaAsset createAsset(long acctId, int assetType) throws
	 * IMSException { CaAsset dmAsset = new CaAsset();
	 * dmAsset.setAssetId(DBUtil.getSequence(CaAsset.class));
	 * dmAsset.setAcctId(acctId); dmAsset.setAssetType(new Long(assetType));
	 * insert(dmAsset); return dmAsset; }
	 */

	/**
	 * 创建账户预算
	 * 
	 * @author wangjt 2012-3-26
	 */
	private void createAcctBudget(SAccount sAccount, Long acctId)
			throws IMSException {
		if (!CommonUtil.isValid(sAccount.getBudget())) {
			return;
		}

		AmountComponent amountCmp = context.getComponent(AmountComponent.class);
		CaAccount acct = context.get3hTree().loadAcct3hBean(acctId)
				.getAccount();
		Double budget = amountCmp.parseRatingAmount(
				amountCmp.getImsMeasureId(acct.getMeasureId()),
				sAccount.getBudget(), acctId);
		BudgetComponent bgtCmp = context.getComponent(BudgetComponent.class);
		BudgetControl budgetControl = new BudgetControl();
		budgetControl.setBudget(budget);
		budgetControl.setMeasure_id(amountCmp.getRatingMeasureId(
				amountCmp.getImsMeasureId(acct.getMeasureId()), acctId));
		// 公用检查和设置
		bgtCmp.checkAndSetBudgetControl(budgetControl);

		bgtCmp.setInputValidDate(IMSUtil.formatDate(sAccount.getValid_date(),
				context.getRequestDate()));
		bgtCmp.setInputExpireDate(IMSUtil.formatExpireDate(sAccount
				.getExpire_date()));

		bgtCmp.createAcctBudget(budgetControl, acctId);
	}

	/**
	 * 创建付费渠道
	 * 
	 * @author wuyujie
	 * @update yanchuan 2011-10-19
	 */
	public void createAcctPayChannel(SAccount account,
			SPayChannelList payChannels, Long acctId) throws IMSException {
		if (payChannels == null
				|| CommonUtil.isEmpty(payChannels.getSPayChannelList_Item())) {
			return;
		}

		CaPaymentPlan dmPayPlan = new CaPaymentPlan();
		dmPayPlan.setPaymentPlanId(DBUtil.getSequence(CaPaymentPlan.class));
		dmPayPlan.setAcctId(acctId);

		insert(dmPayPlan);
		long planId = dmPayPlan.getPaymentPlanId();

		for (int i = 0; i < payChannels.getSPayChannelList_Item().length; i++) {
			SPayChannel paychannel = (SPayChannel) payChannels
					.getSPayChannelList_Item()[i];
			CaPayChannel dmPayChannel = new CaPayChannel();
			dmPayChannel.setPaymentPlanId(planId);
			dmPayChannel.setSoNbr(context.getSoNbr());
			if (paychannel.getPayment_type() != null)
				dmPayChannel.setPaymentMethod((int) paychannel
						.getPayment_type());

			long assetId = -1;
			if (paychannel.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_DEBIT
					|| paychannel.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_CREDIT) {
				// assetId = createAsset(acctId,
				// EnumCodeDefine.ACCOUNT_ASSETTYPE_BANKCAPITAL).getAssetId();
				CaBankFund dmBankFund = new CaBankFund();
				// @Date 2012-09-06 yangjh : bug58249 ca_asset表已经删掉 不需要实例化
				assetId = DBUtil.getSequence(CaBankFund.class);
				dmBankFund.setAssetId(assetId);
				dmBankFund.setAcctId(acctId);
				dmBankFund.setSts(1);
				if (paychannel.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_DEBIT) {
					dmBankFund
							.setFundType((int) EnumCodeDefine.CARD_TYPE_DEBIT);
					dmBankFund.setBankId(CommonUtil.string2Integer(paychannel
							.getBank_id()));
					dmBankFund.setBankAcctNo(paychannel.getBank_acct_nbr());
				} else if (paychannel.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_CREDIT) {
					// bank,instance bank fund asset
					// 2011-08-04 zengxr Modify for changing of SPayChannel
					dmBankFund
							.setFundType((int) EnumCodeDefine.CARD_TYPE_CREDIT);
					dmBankFund.setBankId(CommonUtil.string2Integer(paychannel
							.getCard_issuing_bank()));
					dmBankFund.setBankAcctNo(paychannel.getCard_no());
				}
				dmBankFund.setCreateDate(context.getRequestDate());
				dmBankFund.setFundItem(0);
				dmBankFund.setBankAcctLimit((long) 0);
				insert(dmBankFund);
			} else
				assetId = 0; // fill in 0 if pay by cash
			dmPayChannel.setValidDate(context.getRequestDate());
			dmPayChannel.setExpireDate(IMSUtil.getDefaultExpireDate());
			dmPayChannel.setAssetId(assetId);
			dmPayChannel.setPriority(0);
			insert(dmPayChannel);
		}
	}

	/**
	 * 创建定制账单信息
	 */
	private void createAcctInvoice(SAccount account, Long acctId)
			throws IMSException {
		CaCustomInv dmInvoice = new CaCustomInv();
		dmInvoice.setAcctId(acctId);
		dmInvoice.setInvoiceType(EnumCodeDefine.ACCOUNT_INVOICETYPE_NORMAL);
		// 增加默认公司编号 yanhcuan 2011-11-22
		// 去掉默认公司编号 yanhcuan 2012-02-27
		// if (!CommonUtil.isEmpty(account.getCompany()))
		// dmInvoice.setCompanyId(CommonUtil.long2Int(account.getCompany()));
		// else
		// dmInvoice.setCompanyId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_ACCOUNT_COMPANY_ID));
		if (account.getBill_dispatching() != null) {
			dmInvoice.setMailCode((long) account.getBill_dispatching());
		}
		if (null != account.getBill_dispatching()
				&& account.getBill_dispatching() == 0)
			dmInvoice.setPrintBill(0);// no print
		else
			dmInvoice.setPrintBill(1);
		if (account.getBill_language_id() != null)
			dmInvoice.setLanguageId((int) account.getBill_language_id());
		else
			dmInvoice.setLanguageId(SysUtil
					.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
		if (account.getDue_day() != null)
			dmInvoice.setDueDay((int) account.getDue_day());

		createAcctExtParams(account, acctId, dmInvoice);

		dmInvoice.setSoNbr(context.getSoNbr());

		insert(dmInvoice);
	}

	/**
	 * 增加免催免停信息
	 * 
	 * @author liuyang8 Date：2011-09-10
	 */

	public void createNotiExpt(CaNotifyExempt noti) {
		noti.setSoNbr(context.getSoNbr());
		this.insert(noti);

	}

	/**
	 * @Description: 新建银行资产信息
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author liuyang
	 * @Date 2011-7-27 liuyang,增加方法createBankFund
	 * @Date 2011-8-4 liuyang,将createBankFund改名为addBankFund
	 */
	private void addBankFund(SPayChannel payChannel, Long acctId, Long assetId) {

		CaBankFund dmBankFund = new CaBankFund();
		dmBankFund.setAssetId(assetId);
		dmBankFund.setAcctId(acctId);
		dmBankFund.setSts(EnumCodeDefine.STS_ACTIVE);
		dmBankFund.setValidDate(DateUtil.currentDate());
		// bank info
		if (payChannel.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_DEBIT) {
			dmBankFund.setFundType((int) EnumCodeDefine.CARD_TYPE_DEBIT);
			dmBankFund.setBankId(CommonUtil.string2Integer(payChannel
					.getBank_id()));
			dmBankFund.setBankAcctNo(payChannel.getBank_acct_nbr());
		} else if (payChannel.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_CREDIT) {
			// bank,instance bank fund asset
			// 2011-08-04 zengxr Modify for changing of SPayChannel
			dmBankFund.setFundType((int) EnumCodeDefine.CARD_TYPE_CREDIT);
			dmBankFund.setBankId(CommonUtil.string2Integer(payChannel
					.getCard_issuing_bank()));
			dmBankFund.setBankAcctNo(payChannel.getCard_no());
		}
		dmBankFund.setFundItem(0);
		dmBankFund.setBankAcctLimit((long) 0);
		// 判断是否需要下周期生效 yanchuan 2012-03-16
		Date valid_date = getNextCycleValidDate(acctId);
		if (valid_date != null)
			dmBankFund.setValidDate(valid_date);
		else
			dmBankFund.setCreateDate(context.getRequestDate());
		insert(dmBankFund);
	}

	/**
	 * @Description:新建付费渠道信息
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author liuyang
	 * @Date 2011-7-27 2011-07-27 liuyang,增加方法createPayChannel
	 * @Date 2011-8-4 liuyang,将createPayChannel改名为addPayChannel
	 */

	private void addPayChannel(SPayChannel payChannel, Long acctId,
			Long assetId, Long planId) {
		CaPayChannel dmPayChannel = new CaPayChannel();
		dmPayChannel.setPaymentPlanId(planId);
		dmPayChannel.setSoNbr(context.getSoNbr());
		if (payChannel.getPayment_type() != null) {
			dmPayChannel.setPaymentMethod(Integer.valueOf(payChannel
					.getPayment_type()));
		}
		dmPayChannel.setExpireDate(IMSUtil.getDefaultExpireDate());
		dmPayChannel.setAssetId(assetId);
		dmPayChannel.setPriority(0);
		// 判断是否需要下周期生效 yanchuan 2012-03-16
		Date valid_date = getNextCycleValidDate(acctId);
		if (valid_date != null)
			dmPayChannel.setValidDate(valid_date);
		else
			dmPayChannel.setValidDate(context.getRequestDate());
		super.insert(dmPayChannel);
	}

	/**
	 * @Description: 更改账户表信息
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author liuyang
	 * @Date 2011-7-27 2011-07-27 liuyang,增加方法modifyAcctBasicInfo
	 * @Date 2012-09-18 yugb :[57408]API中字段清空方案
	 */
	public void modifyAcctBasicInfo(SAccount account) {
		CaAccount dmAccount = new CaAccount();
		if (account == null)
			return;
		if (account.getAcct_id() == null)
			throw IMSUtil
					.throwBusiException(ErrorCodeDefine.MODIFY_ACCOUNT_ID_NOT_EXITS);
		// @Date 2012-09-18 yangjh : 更新3Hbean中的数据，避免后续set negative balance
		// 取的是老数据
		// Acct3hBean bean =
		// context.get3hTree().loadAcct3hBean(account.getAcct_id());
		// 需要更新的信息
		if (!CommonUtil.isEmpty(account.getAcct_name()))
			dmAccount.setName(account.getAcct_name());
		else if (IMSUtil.isClean(account.getAcct_name()))
			dmAccount.setName(null);
		// 根据bssborker文档描述，该字段已不修改 yanchuan 2012-02-27
		// if (!CommonUtil.isEmpty(account.getAcct_segment()))
		// dmAccount.setAccountSegment(account.getAcct_segment().intValue());

		if (account.getProv_code() != null) {
			if (IMSUtil.isClean(account.getProv_code()))
				dmAccount.setProvinceId(null);
			else
				dmAccount.setProvinceId(account.getProv_code().intValue());
		}
		if (account.getRegion_code() != null) {
			if (IMSUtil.isClean(account.getRegion_code()))
				dmAccount.setRegionCode(null);
			else
				dmAccount.setRegionCode(account.getRegion_code().intValue());
		}

		if (account.getCompany() != null) {
			if (IMSUtil.isClean(account.getCompany()))
				dmAccount.setOperatorId(null);
			else
				dmAccount.setOperatorId(account.getCompany());
		}
		// dmAccount.setValidDate(context.getRequestDate());
		dmAccount.setSoNbr(context.getSoNbr());
		// @Date 2012-7-12 tangjl5 Strory #49447 存储账务侧的度量单位
		AmountComponent amountCmp = context.getComponent(AmountComponent.class);
		if (account.getMeasure_id() != null) {
			if (IMSUtil.isClean(account.getMeasure_id()))
				dmAccount.setMeasureId(null);
			else {
				Integer measureTypeId = amountCmp.queryMeasureTypeId(account
						.getMeasure_id());
				dmAccount.setMeasureId(amountCmp.queryMeasureIdByTypeAndModule(
						measureTypeId, EnumCodeDefine.MODULE_BILLING));
			}
		} else {
			dmAccount
					.setMeasureId(amountCmp
							.queryDefaultMeasureIdByType(EnumCodeDefine.MODULE_BILLING));
		}

		if (account.getForce_cycle() != null) {
			if (IMSUtil.isClean(account.getForce_cycle()))
				dmAccount.setForceCycle(null);
			else
				dmAccount.setForceCycle(account.getForce_cycle().intValue());
		}
		// 2012-07-11 yangjh 增加账户billSts的修改
		if (account.getBill_sts() != null) {
			if (IMSUtil.isClean(account.getBill_sts()))
				dmAccount.setBillSts(null);
			else {
				dmAccount.setBillSts(account.getBill_sts());
				dmAccount.setBillStsDate(context.getRequestDate());
			}
		}
		// CaAccount dmAccountCon = new CaAccount();
		// dmAccountCon.setAcctId(account.getAcct_id());

		// 2012-10-16 luojb On_Site Defect #61460
		if (account.getCounty_code() != null)
			dmAccount.setCountyCode(account.getCounty_code());

		// @Date 2012-4-27 tangjl5 Task #44713 从扩张字段中获取税率标志存入CaAccount
		Integer taxFlag = getTaxFalg(account);
		if (null != taxFlag) {
			dmAccount.setTaxFlag(taxFlag);
		}
		super.updateByCondition(dmAccount, new DBCondition(
				CaAccount.Field.acctId, account.getAcct_id()));

	}

	/**
	 * @Description: Task #44713 从扩张字段中获取税率标志
	 * @param account
	 * @return
	 * @author: tangjl5
	 * @Date: 2012-4-27
	 */
	public Integer getTaxFalg(SAccount account) {
		Integer taxFlag = null;
		ExtendParamList paramList = account.getList_ext_param();
		if (paramList != null
				&& CommonUtil.isNotEmpty(paramList.getExtendParamList_Item())) {
			for (ExtendParam param : paramList.getExtendParamList_Item()) {
				if (CommonUtil.isEmpty(param.getParam_name())
						|| CommonUtil.isEmpty(param.getParam_value()))
					continue;
				String name = param.getParam_name();
				String value = param.getParam_value();
				if (ConstantDefine.EXTPARAM_ACCT_DEFAULTCPSID.equals(name)) {
					taxFlag = CommonUtil.string2Integer(value);
					break;
				}
			}
		}

		return taxFlag;
	}

	/**
	 * @Description: 更改账户表的强制账期标志
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author yanchuan
	 * @Date 2011-8-15 yanchuan,增加方法modifyAcctForceCycle
	 */
	public void modifyAcctForceCycle(Long acct_id, Integer force_cycle) {
		CaAccount dmAccount = new CaAccount();
		dmAccount.setForceCycle(force_cycle);

		super.updateByCondition(dmAccount, new DBCondition(
				CaAccount.Field.acctId, acct_id));
	}

	/**
	 * @Description: 批量更改账户表的强制账期标志
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author yanchuan
	 * @Date 2011-8-15 yanchuan,增加方法modifyAcctForceCycle
	 */
	private void modifyAcctForceCycle(List<CaAccount> accounts) {
		if (CommonUtil.isEmpty(accounts))
			return;
		List<Long> acctIds = new ArrayList<Long>();
		for (CaAccount account : accounts) {
			if (account == null || !CommonUtil.isValid(account.getAcctId()))
				continue;
			if (account.getForceCycle() != null
					&& (account.getForceCycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE || account
							.getForceCycle() == EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT)) {
				acctIds.add(account.getAcctId());
			}
		}
		if (CommonUtil.isEmpty(acctIds))
			return;
		CaAccount dmAccount = new CaAccount();
		dmAccount.setForceCycle(EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE);
		super.updateByCondition(dmAccount, new DBCondition(
				CaAccount.Field.acctId, acctIds, Operator.IN));
	}

	public void modifyAcctResTitleRoleId(Long acct_id, Long resID,
			Integer titleRoleId) {
		CaAccountRes acctRes = new CaAccountRes();
		acctRes.setTitleRoleId(titleRoleId);
		// CaAccountRes dmAccountCon = new CaAccountRes();
		// dmAccountCon.setResAcctId(acct_id);
		// dmAccountCon.setResourceId(resID);
		// dmAccountCon.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);
		super.updateByCondition(acctRes, new DBCondition(
				CaAccountRes.Field.acctId, acct_id), new DBCondition(
				CaAccountRes.Field.resourceId, resID), new DBCondition(
				CaAccountRes.Field.relationshipType,
				EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
	}

	/**
	 * @Description: 修改付费渠道信息
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author liuyang
	 * @update yanchuan 因payChanel结构修改，需修改相关代码
	 * @Date 2011-7-27 2011-07-27 liuyang,增加方法modifyPayChannelInfo
	 */
	public void modifyPayChannelInfo(SAccount account,
			SPayChannelList payChannels) {
		// 如果CaPaymentPlan为空则需要创建支付渠道，否则按情况处理
		if (payChannels == null
				|| CommonUtil.isEmpty(payChannels.getSPayChannelList_Item())) {
			return;
		}
		// 2012-04-04 zengxr first delete all paychannel, then add new .
		List<CaPayChannel> payChannelList = queryPayChannelByAcctId(account
				.getAcct_id());
		if (CommonUtil.isEmpty(payChannelList)) {
			createAcctPayChannel(account, payChannels, account.getAcct_id());
		} else {
			Date valid_date = getNextCycleValidDate(account.getAcct_id());

			for (CaPayChannel paycl : payChannelList) {
				if (paycl.getPaymentMethod() == EnumCodeDefine.ACCOUNT_PAYTYPE_DEBIT
						|| paycl.getPaymentMethod() == EnumCodeDefine.ACCOUNT_PAYTYPE_CREDIT) {
					this.delBankFund(paycl.getAssetId(), valid_date);
				}
				this.delPayChannel(paycl.getPaymentPlanId(), paycl
						.getPaymentMethod().shortValue(), valid_date);
			}
			Long planId = payChannelList.get(0).getPaymentPlanId();
			for (int i = 0; i < payChannels.getSPayChannelList_Item().length; i++) {
				SPayChannel payChannelReq = payChannels
						.getSPayChannelList_Item()[i];
				// 修改增加与删除支付渠道 yanchuan 2011-10-19
				if (payChannelReq == null)
					continue;
				// 如果没有这个付费方式需要增加,否则需要先删后填
				CaPayChannel payCard = queryPayChannel(planId,
						payChannelReq.getPayment_type());
				boolean flag = false;// 是否需要查询银行资产表标识
				long assetId = 0;
				if (payChannelReq.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_DEBIT
						|| payChannelReq.getPayment_type() == EnumCodeDefine.ACCOUNT_PAYTYPE_CREDIT) {
					flag = true;
				}
				if (payCard == null) {
					if (flag == true) {
						// assetId = this.createAsset(account.getAcct_id(),
						// EnumCodeDefine.ACCOUNT_ASSETTYPE_BANKCAPITAL)
						// .getAssetId();
						// @Date 2012-09-06 yangjh : bug58249 ca_asset表已经删掉
						// 不需要实例化
						assetId = DBUtil.getSequence(CaBankFund.class);
						this.addBankFund(payChannelReq, account.getAcct_id(),
								assetId);
					}
					this.addPayChannel(payChannelReq, account.getAcct_id(),
							assetId, planId);
				} else {
					if (flag == true) {
						assetId = payCard.getAssetId();
						this.addBankFund(payChannelReq, account.getAcct_id(),
								assetId);
					}
					this.addPayChannel(payChannelReq, account.getAcct_id(),
							assetId, planId);
				}

			}

		}
	}

	/**
	 * @Description: 修改用户发票信息
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author liuyang
	 * @Date 2011-7-27 2011-07-27 liuyang,增加方法modifyCustomizedInvoIce
	 */
	public void modifyCustomizedInvoIce(SAccount account,
			Map<String, String> extendParams) {
		CaCustomInv invoice = null;
		List<CaCustomInv> invoices = queryCustomizedInvoIces(account
				.getAcct_id());
		if (CommonUtil.isNotEmpty(invoices) && invoices.size() > 1)
			IMSUtil.throwBusiException(ErrorCodeDefine.NEXT_CYCLE_EFFECTIVE,
					account.getAcct_id());
		else if (CommonUtil.isNotEmpty(invoices))
			invoice = invoices.get(0);
		CaCustomInv dmInvoice = new CaCustomInv();
		if (account.getDue_day() != null)
			dmInvoice.setDueDay((int) account.getDue_day());
		if (account.getBill_language_id() != null)
			dmInvoice.setLanguageId((int) account.getBill_language_id());
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_PRINTBILL)))
			dmInvoice.setPrintBill(Integer.valueOf(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_PRINTBILL)));
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_BILLHANDLINGCODE)))
			dmInvoice.setMailCode(Long.valueOf(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_BILLHANDLINGCODE)));
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_BILLSTYLEID)))
			dmInvoice.setBillFormatId(Integer.valueOf(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_BILLSTYLEID)));
		// 2011-11-07 修改帐户扩展表中生效日期为帐户下个账期开始时间
		String invoicetype = extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_INVOICETYPE);
		Date vaild_date = this.getNextCycleValidDate(account.getAcct_id());
		if (vaild_date == null) {
			if (invoice != null
					&& invoice.getInvoiceType() != null
					&& invoice.getInvoiceType().intValue() == EnumCodeDefine.ACCOUNT_INVOICETYPE_CENTRALCARD) {
				Date valid_date = this.queryAcctNextCycleStart(account
						.getAcct_id());
				dmInvoice.setValidDate(valid_date);
			}
		}
		if (!CommonUtil.isEmpty(invoicetype))
			dmInvoice.setInvoiceType(Integer.valueOf(invoicetype));
		dmInvoice.setSoNbr(context.getSoNbr());
		super.updateByCondition(dmInvoice, vaild_date, new DBCondition(
				CaCustomInv.Field.acctId, account.getAcct_id()));
	}

	/**
	 * @Description: 修改帐户扩展属性表
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author yanchuan
	 * @Date 2011-8-18 增加方法ModifyAcctAttribute
	 */
	public void modifyAcctAttribute(SAccount account,
			Map<String, String> extendParams) {
		if (account == null || CommonUtil.isEmpty(extendParams)
				|| extendParams.size() <= 0) {
			return;
		}

		CaAccountExt attr = new CaAccountExt();
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_SMSCONTACTNUM)))
			attr.setItem1(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_SMSCONTACTNUM));
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_ACCTBMCALLDETAIL)))
			attr.setItem2(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_ACCTBMCALLDETAIL));
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_ACCTBMSUMMARY)))
			attr.setItem3(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_ACCTBMSUMMARY));
		// if
		// (!CommonUtil.isEmpty(extendParams.get(ConstantDefine.EXTPARAM_ACCT_DEFAULTCPSID)))
		// attr.setItem4(extendParams.get(ConstantDefine.EXTPARAM_ACCT_DEFAULTCPSID));
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_FAXBILLTO)))
			attr.setItem5(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_FAXBILLTO));
		// 2012-04-04 zengxr add vat_name
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_VATNAME)))
			attr.setItem12(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_VATNAME));
		// 2012-06-29 tangkun add ORIGINAL_DefaultCPSId
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_ORIGINAL_DEFAULTCPSID)))
			attr.setItem13(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_ORIGINAL_DEFAULTCPSID));
		// 2012-07-05 tangkun add copyBillBudget
		if (!CommonUtil.isEmpty(extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_COPYBILLBUDGET)))
			attr.setItem14(extendParams
					.get(ConstantDefine.EXTPARAM_ACCT_COPYBILLBUDGET));
		// 2011-10-04 增加扩展字段 yanchuan
		String specialAccnt = extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_SPECIALACCNT);
		String chargeType = extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_CHARGETYPE);
		String smsBillTo = extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_SMSBILLTO);
		// @Date 2012-07-12 wangdw5 : #51420 _EmailBillTo未保存到数据库中Item10
		String emailBillTo = extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_EMAILBILLTO);
		String billPaymentCurrency = extendParams
				.get(ConstantDefine.EXTPARAM_ACCT_BILLPAYMENTCURRENCY);

		if (!CommonUtil.isEmpty(chargeType)) {
			attr.setItem6(chargeType);
		}
		if (!CommonUtil.isEmpty(smsBillTo)) {
			attr.setItem7(smsBillTo);
		}
		if (!CommonUtil.isEmpty(specialAccnt)) {
			attr.setItem8(specialAccnt);
		}
		if (!CommonUtil.isEmpty(billPaymentCurrency)) {
			attr.setItem9(billPaymentCurrency);
		}
		if (!CommonUtil.isEmpty(emailBillTo)) {
			attr.setItem10(emailBillTo);
		}
		// CaAccountAttribute attrCon = new CaAccountAttribute();
		// attrCon.setAcctId(account.getAcct_id());

		// if not exist insert a new record
		CaAccountExt data = super
				.querySingle(CaAccountExt.class, new DBCondition(
						CaAccountExt.Field.acctId, account.getAcct_id()));
		if (data == null) {
			attr.setAcctId(account.getAcct_id());
			super.insert(attr);
		} else {
			super.updateByCondition(attr, new DBCondition(
					CaAccountExt.Field.acctId, account.getAcct_id()));
		}
	}

	/**
	 * 修改账期
	 * 
	 * @author yanchuan 2011-11-18
	 */
	public void modifyForceCycle(SAccount account, CaAccount caAccount) {

		ProductCycleComponent prodCycleCmp = context
				.getComponent(ProductCycleComponent.class);
		AccountRelationComponent accReCmp = context
				.getComponent(AccountRelationComponent.class);
		SBillCycle billCycle = account.getBill_cycle();
		if (caAccount == null) {
			return;
		}
		// 不需要修改账期
		if (billCycle == null && account.getForce_cycle() == null) {
			return;
		}
		long acct_id = caAccount.getAcctId();

		// Long cust_id = queryCustIdByAcctId(acct_id);
		// 查询帐户的帐期编号
		Long oldCycleSpecId = queryAcctCycle(acct_id);
		Long new_cycleSpecId = null;
		if (billCycle != null && AcctHelper.billCycleValid(billCycle)) {
			new_cycleSpecId = CommonUtil.IntegerToLong(queryBillCycleSpecId(
					billCycle.getCycle_type(), billCycle.getCycle_unit(),
					billCycle.getFirst_bill_day()));
			// 账期一致不需要去修改帐户账期了
			if (oldCycleSpecId != null && new_cycleSpecId != null
					&& oldCycleSpecId.intValue() != new_cycleSpecId) {
				// createBillCycle(billCycle, caAccount,
				// EnumCodeDefine.ACCOUNT_BILLCYCLE_MODIFY,
				// EnumCodeDefine.BILL_CYCLE_MODIFY);
				modifyAcctBillCycle(new_cycleSpecId, caAccount);
				// 修改帐户及其所归属用户的订购的产品的账期
				prodCycleCmp.changeProdBillCycle(acct_id);
			}
		}
		if (new_cycleSpecId == null && oldCycleSpecId != null) {
			new_cycleSpecId = oldCycleSpecId;
			// oldCycleSpecId = CommonUtil.IntegerToLong(new_cycleSpecId);
		}
		if (ProjectUtil.is_CN_SH()) {// 如果是上海项目，忽略强制帐期
			return;
		}

		if (caAccount.getForceCycle() != null
				&& (caAccount.getForceCycle().intValue() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE
						|| caAccount.getForceCycle().intValue() == EnumCodeDefine.FORCECYCLE_BY_CUSTOMER || caAccount
						.getForceCycle().intValue() == EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT)) {
			// 修改改帐户及其关联子帐户的强制账期标志
			if (account.getForce_cycle() == null) {
				return;
			}

			if (account.getForce_cycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE) {
				List<Long> acctIdList = accReCmp.queryAllSubCa(acct_id);
				if (CommonUtil.isEmpty(acctIdList)) {
					return;
				}
				for (Long acctId : acctIdList) {
					modifyAcctForceCycle(acctId,
							EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE);
				}
			}
			// 该帐户已是强制账期
			else if (account.getForce_cycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE) {
				throw IMSUtil
						.throwBusiException(ErrorCodeDefine.FORCE_CYCLE_ALREADY_EXISTS);
			}
		} else {
			// 需要修改帐户关联的子帐户
			if (account.getForce_cycle() != null
					&& account.getForce_cycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE) {

				if (new_cycleSpecId == null) {
					return;
				}
				List<Long> acctIdList = accReCmp.queryAllSubCa(acct_id);
				if (!CommonUtil.isEmpty(acctIdList))
					for (Long acctId : acctIdList) {
						CaAccount sonAccount = null;
						try {
							sonAccount = context.get3hTree()
									.loadAcct3hBean(acctId).getAccount();
						} catch (IMS3hNotFoundException e) {
							continue;
						}

						modifyAcctForceCycle(acctId,
								EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT);
						// 修改账期
						modifyAcctBillCycle(new_cycleSpecId, sonAccount);
						// 修改帐户及其所归属用户的订购的产品的账期
						prodCycleCmp.changeProdBillCycle(acctId);
					}
			}
		}
	}

	/**
	 * @author yanchuan 2012-5-25
	 * @describe 修改帐户账期
	 * @param specId
	 * @param dmAccount
	 */
	public void modifyAcctBillCycle(Long specId, CaAccount dmAccount) {
		if (dmAccount == null) {
			return;
		}
		// 判断帐户是否存在代付或者被代付关系 yanchuan 2012-05-25
		SplitComponent splitCmp = context.getComponent(SplitComponent.class);
		if (splitCmp.isSplit(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT,
				dmAccount.getAcctId())
				|| splitCmp.isBeSplit(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT,
						dmAccount.getAcctId())) {
			IMSUtil.throwBusiException(
					ErrorCodeDefine.BILL_CYCLE_ACCOUNT_HAS_SPLIT,
					dmAccount.getAcctId());
		}
		// 修改账期
		BillCycleHandler billCycleHandler = ChangeBillCycleContext
				.instanceHandler(dmAccount.getAcctId(), specId, null, null,
						null, context);
		billCycleHandler.changeBillCycle();
	}

	/**
	 * @Description: 判断账户下是否还有非termianl用户
	 * @param acctId
	 * @return
	 * @author: tangjl5
	 * @Date: 2012-7-26
	 */
	public boolean isAcctHavaUsers(Long acctId) {
		// 根据帐户查询用户
		List<Long> userId_list = context.getComponent(UserComponent.class)
				.queryUserIdsByAcctID(acctId);
		if (CommonUtil.isEmpty(userId_list)) {
			return false;
		}
		Long[] userIds = userId_list.toArray(new Long[userId_list.size()]);
		List<CmResLifecycle> resLifeCycle_list = this
				.query(CmResLifecycle.class, new DBCondition(
						CmResLifecycle.Field.resourceId, userIds, Operator.IN),
						new DBCondition(CmResLifecycle.Field.sts,
								EnumCodeDefine.LIFECYCLE_TERMINAL,
								Operator.NOT_EQUALS), new DBCondition(
								CmResLifecycle.Field.recSts,
								EnumCodeDefine.IS_VALID_DATA));
		// @Date 2012-5-29 tangjl5 BUG #47032 对userId_list进行空判断
		if (CommonUtil.isEmpty(resLifeCycle_list)) {
			return false;
		}
		return true;
	}

	/**
	 * 修改帐户状态 2011-09-06
	 * 
	 * @author yanchuan
	 * @param account
	 * @param status
	 * @update 2011-11-14 修改modifyAcctStatus方法
	 */
	public void modifyAcctStatus(ModifyAcctReq acctReq) {
		CaAccount dmAccount = new CaAccount();
		dmAccount.setAccountStatus(acctReq.getNew_state().intValue());
		dmAccount.setStsDate(IMSUtil.formatDate(acctReq.getAction_date()));
		// @Date 2012-04-26 lijc3 如果修改账户状态为激活，需要修改出账状态为未定义
		if (dmAccount.getBillSts() != null
				&& dmAccount.getBillSts() == EnumCodeDefine.BILL_STS_TERMINATE) {
			dmAccount.setBillSts(EnumCodeDefine.BILL_STS_NOT_DEFINED);
			dmAccount.setBillStsDate(IMSUtil.formatDate(acctReq
					.getAction_date()));
		}
		// CaAccount conAccount = new CaAccount();
		// conAccount.setAcctId(acctReq.getAcct_id());
		this.updateByCondition(dmAccount, new DBCondition(
				CaAccount.Field.acctId, acctReq.getAcct_id()));
	}

	/**
	 * 修改免催免停信息
	 * 
	 * @author liuyang8 Date：2011-09-10
	 */
	public void modifyNotiExpt(CaNotifyExempt noti, Long objectId,
			Integer objectType, Long soNbrIms) {
		this.updateByCondition(noti, new DBCondition(
				CaNotifyExempt.Field.objectId, objectId), new DBCondition(
				CaNotifyExempt.Field.objectType, objectType), new DBCondition(
				CaNotifyExempt.Field.soNbr, soNbrIms));

	}

	public void chgAcctStatus(Long acctId, Integer status) {
		CaAccount caAccount = new CaAccount();
		caAccount.setAccountStatus(status);
		updateByCondition(caAccount, new DBCondition(CaAccount.Field.acctId,
				acctId));
	}

	/**
	 * @Description: 根据用户ID查询修改其从属账号
	 * @param userId
	 * @param newAcctId
	 * @throws IMSException
	 * @param chgFlag
	 *            用于区分用户及账户关系变更的动作来源(changeOwner\changeAccount)
	 * @author 添加changeType字段 2011-11-16 wukl
	 */
	public void chgBelongAcctResByUserID(Long userId, Long newAcctId,
			Integer chgFlag) throws IMSException {
		try {
			CaAccount caAccount = context.get3hTree().loadAcct3hBean(newAcctId)
					.getAccount();
		} catch (IMS3hNotFoundException e) {
			IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTEXIST, "id="
					+ newAcctId);
		}

		CaAccountRes value = new CaAccountRes();
		value.setAcctId(newAcctId);
		value.setChgFlag(chgFlag);

		// CaAccountRes condition = new CaAccountRes();
		// condition.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);
		// condition.setResourceId(userId);

		// 若是从 mange single balance过来的接口，需要将用户设置为账户的主号码
		if (context.getOper().getBusi_code().intValue() == EnumCodeDefine.MANAGE_SINGLE_BALANCE_BUSI_BEAN) {
			value.setTitleRoleId(EnumCodeDefine.TITLEROLEID_SINGLE_BALANCE_MASTER_NUMBER);
		}

		updateByCondition(value, new DBCondition(
				CaAccountRes.Field.relationshipType,
				EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING),
				new DBCondition(CaAccountRes.Field.resourceId, userId));
		context.get3hTree().remove3hBean(
				context.get3hTree().loadUser3hBean(userId));
	}

	/**
	 * @Description: 修改用户的billable账号
	 * @param userId
	 * @param chgFlag
	 *            用于区分用户及账户关系变更的动作来源(changeOwner\changeAccount)
	 * @param relationshipType
	 * @param newAcctId
	 * @throws IMSException
	 * @author 添加changeType字段 2011-11-16 wukl
	 */
	public void chgBillAcctResByUserID(Long userId, Long billAcctId,
			Integer chgFlag) throws IMSException {
		CaAccountRes value = new CaAccountRes();
		value.setResAcctId(billAcctId);
		if (chgFlag != null) {
			value.setChgFlag(chgFlag);
		}

		// CaAccountRes condition = new CaAccountRes();
		// condition.setResourceId(userId);

		updateByCondition(value, new DBCondition(CaAccountRes.Field.resourceId,
				userId));
	}

	/**
	 * @Description: 修改用户的支付账户和归属账户,changeOwner分两次进行修改支付账户和归属账户时会出现主键冲突
	 * @param userId
	 * @param billAcctId
	 * @param chgFlag
	 * @throws IMSException
	 * @author: tangjl5
	 * @Date: 2012-2-16
	 */
	public void chgBillAndBelongAcctResByUserID(Long userId, Long billAcctId,
			Long belongAcctId, Integer chgFlag) throws IMSException {
		CaAccountRes value = new CaAccountRes();
		value.setResAcctId(billAcctId);
		value.setAcctId(belongAcctId);
		if (chgFlag != null) {
			value.setChgFlag(chgFlag);
		}

		// 若是从 mange single balance过来的接口，需要将用户设置为账户的主号码
		if (context.getOper().getBusi_code().intValue() == EnumCodeDefine.MANAGE_SINGLE_BALANCE_BUSI_BEAN) {
			if (context.get3hTree().loadAcct3hBean(belongAcctId).getAccount()
					.getBalanceType() == EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE) {
				value.setTitleRoleId(EnumCodeDefine.TITLEROLEID_SINGLE_BALANCE_MASTER_NUMBER);
			} else {
				value.setTitleRoleId(SysUtil
						.getInt(SysCodeDefine.busi.DEFAULT_USER_TITLE_ROLEID));
			}
		} else if (context.getOper().getBusi_code().intValue() == EnumCodeDefine.MANAGE_SINGLE_BALANCEMBR_BUSI_BEAN) {
			value.setTitleRoleId(null);
		}

		// CaAccountRes condition = new CaAccountRes();
		// condition.setResourceId(userId);
		// condition.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);

		updateByCondition(value, new DBCondition(CaAccountRes.Field.resourceId,
				userId), new DBCondition(CaAccountRes.Field.relationshipType,
				EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
	}

	/**
	 * @Description: 删除银行资产信息
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author liuyang
	 * @Date 2011-7-27 2011-07-27 liuyang,增加方法delBankFund
	 */
	private void delBankFund(Long assetId, Date expireDate) {
		super.deleteByCondition_noInsert(CaBankFund.class, expireDate,
				new DBCondition(CaBankFund.Field.assetId, assetId));
	}

	/**
	 * @Description: 删除付费渠道信息
	 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
	 * @Author liuyang
	 * @Date 2011-7-27 2011-07-27 liuyang,增加方法delPayChannel
	 */
	private void delPayChannel(Long planId, Short payment_type, Date expireDate) {
		DBCondition conds[] = {
				new DBCondition(CaPayChannel.Field.paymentPlanId, planId),
				new DBCondition(CaPayChannel.Field.paymentMethod, payment_type) };
		super.deleteByCondition_noInsert(CaPayChannel.class, expireDate, conds);
	}

	/**
	 * @Description: 根据acctID对账户进行销户
	 * @param acctID
	 */
	public void deleteAccount(Long acctID) {
		deleteByCondition(CaAccount.class, new DBCondition(
				CaAccount.Field.acctId, acctID));
	}

	/**
	 * @Description: 根据用户Id删除用户账户的关系
	 * @param userId
	 * @author: tangjl5
	 * @Date: 2012-1-3
	 */
	public void deleteAcctResRelByUserId(Long userId) {
		deleteByCondition(CaAccountRes.class, new DBCondition(
				CaAccountRes.Field.resourceId, userId));
	}

	/**
	 * 删除免催免停信息
	 * 
	 * @author liuyang8 Date：2011-09-10
	 */
	public void deleteNotiExpt(Long objectId, Integer objectType) {
		this.deleteByCondition(CaNotifyExempt.class, new DBCondition(
				CaNotifyExempt.Field.objectId, objectId), new DBCondition(
				CaNotifyExempt.Field.objectType, objectType));
	}

	/**
	 * @Description 删除银行信息
	 * @author fangyw
	 * @Date 2011-10-10
	 */
	public void deleteCaBankInfo(BankInfo bankInfo) {
		super.deleteByCondition(
				SysBankInfo.class,
				new DBCondition(SysBankInfo.Field.bankNo, String
						.valueOf(bankInfo.getBank_code())));

	}

	/**
	 * @Description 给用户取消对应的业务通知设置
	 * @author fangyw
	 * @Date 2011-10-19
	 */
	public void delNotification(Long objectId, short level, Short notiType,
			Short notifyChannel) {
		super.deleteByCondition(CmNotificationFlag.class, new DBCondition(
				CmNotificationFlag.Field.objectId, objectId), new DBCondition(
				CmNotificationFlag.Field.notifyType, notiType),
				new DBCondition(CmNotificationFlag.Field.objectType, level));
	}

	/**
	 * 删除帐户
	 */
	public void delAcct(Long acctId) {
		this.deleteByCondition(CaAccount.class, new DBCondition(
				CaAccount.Field.acctId, acctId));

		BaseProductComponent prodCmp = context
				.getComponent(BaseProductComponent.class);
		List<CoProd> coProdList = prodCmp.queryProdListByAcctId(acctId);
		if (CommonUtil.isEmpty(coProdList)) {
			return;
		}
		prodCmp.deleteProductInfo(
				prodCmp.transferCoProdList2Complex(coProdList), null);

	}

	/**
	 * wangjt 2011-11-26 判断一个账户是否可以terminate
	 */
	public boolean acctCanTerminate(Long acctId) {
		// @Date 2012-08-01 tangjl5 :Bug # 52819 若是single
		// balanlce接口进来，则不判断账户是否处于层级关系中，直接删除
		if (context.getOper().getBusi_code() != BusiCodeDefine.MANAGE_SINGLE_BALANCE) {
			// 如果在层级关系中，不可以terminate
			boolean hasBeenIn = context.getComponent(
					AccountRelationComponent.class).hasBeenIn(acctId);
			if (hasBeenIn) {
				return false;
			}
		}

		List<CaAccountRes> list = query(CaAccountRes.class, new DBCondition(
				CaAccountRes.Field.acctId, acctId));
		// 如果没有和用户关联，则可以terminate
		return CommonUtil.isEmpty(list);
	}

	/**
	 * @Description: 判断账户下的是否只有一个或0个用户(包括归属用户和付费用户等)，除生命周期是terminal状态的用户
	 * @param acctId
	 * @return
	 * @author: tangjl5
	 * @Date: 2011-11-9
	 */
	public boolean isAccountNotMoreThanOneUserExptTeminal(Long acctId) {

		// 根据帐户查询用户
		List<Long> userId_list = context.getComponent(UserComponent.class)
				.queryUserIdsByAcctID(acctId);
		if (CommonUtil.isEmpty(userId_list)) {
			return true;
		}
		Long[] userIds = userId_list.toArray(new Long[userId_list.size()]);
		List<CmResLifecycle> resLifeCycle_list = this
				.query(CmResLifecycle.class, new DBCondition(
						CmResLifecycle.Field.resourceId, userIds, Operator.IN),
						new DBCondition(CmResLifecycle.Field.sts,
								EnumCodeDefine.LIFECYCLE_TERMINAL,
								Operator.NOT_EQUALS), new DBCondition(
								CmResLifecycle.Field.recSts,
								EnumCodeDefine.IS_VALID_DATA));
		// @Date 2012-5-29 tangjl5 BUG #47032 对userId_list进行空判断
		if (CommonUtil.isEmpty(userId_list)) {
			return true;
		} else {
			if (resLifeCycle_list == null || resLifeCycle_list.size() < 1) {
				return true;
			}
			long firstUserId = resLifeCycle_list.get(0).getResourceId();
			for (int i = 1; i < resLifeCycle_list.size(); i++) {
				if (resLifeCycle_list.get(i).getResourceId() != firstUserId) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * @Description 取消用户所进行通知设置
	 * @author fangyw
	 * @param notifications
	 * @Date 2011-10-19
	 */
	public void createNotification(List<CmNotificationFlag> notifications) {
		super.insertBatch(notifications);
	}

	// /**
	// * @Description 修改用户订阅的通知设置信息
	// * @author fangyw
	// * @Date 2011-10-19
	// */
	// public void modifyNotification(CmNotificationFlag notificationsChange,
	// CmNotificationFlag notification)
	// {
	// super.updateByCondition(notificationsChange, notification);
	// }

	/**
	 * 判断账户是否是虚账户 luojb 2011-11-10
	 */
	public boolean isGCAAccount(Long acctId) throws IMSException {
		CaAccount account = context.get3hTree().loadAcct3hBean(acctId)
				.getAccount();
		if (account.getAccountClass().intValue() == EnumCodeDefine.ACCOUNT_CLASS_GCA) {
			return true;
		}
		return false;
	}

	/**
	 * 终止账户(修改账户为close状态，同时删除账户的产品)
	 */
	public void terminateAccount(Long acctId) {
		// update CaAccount
		this.chgAcctStatus(acctId, EnumCodeDefine.SERVICE_CYCLESTS_CLOSE);
		this.deleteProdByAcctId(acctId);

	}

	/**
	 * @author yanchuan 2012-3-12
	 * @describe 删除 帐户下的产品
	 * @param acctId
	 */
	public void deleteProdByAcctId(Long acctId) {
		BaseProductComponent prodCmp = context
				.getComponent(BaseProductComponent.class);
		List<CoProd> coProdList = prodCmp.queryProdListByAcctId(acctId);
		if (CommonUtil.isEmpty(coProdList)) {
			return;
		}
		prodCmp.deleteProductInfo(
				prodCmp.transferCoProdList2Complex(coProdList), null);
	}

	/**
	 * 将内部实体CaAccount转换为接口实体SAccount返回给外部接口
	 * 
	 * @Date 2011-11-21 hunan : 将yindm写的封装方法提取到组建中
	 */
	public SAccount sAccountTransform(CaAccount ca, CaCustomInv custInv,
			CaAccountExt acctAttri, CaCustomerRel rel) {
		// SAccount sAcct = new SAccount();
		if (ca == null) {
			return null;
		}
		SAccount sAcct = new SAccount();
		if (rel != null && rel.getCustId() != null) {
			sAcct.setCust_id(rel.getCustId());
		}
		sAcct.setAcct_id(ca.getAcctId());

		if (CommonUtil.isNotEmpty(ca.getName()))
			sAcct.setAcct_name(ca.getName());

		if (ca.getAccountStatus() != null)
			sAcct.setStatus(CommonUtil.IntegerToShort(ca.getAccountStatus()));

		if (ca.getForceCycle() != null) {
			sAcct.setForce_cycle(CommonUtil.IntegerToShort(ca.getForceCycle()));
		}

		if (ca.getAccountType() != null) {
			sAcct.setAcct_type(CommonUtil.IntegerToShort(ca.getAccountType()));
		}

		Date createDate = ca.getCreateDate();
		if (createDate != null)
			sAcct.setCreate_date(DateUtil.formatDate(createDate,
					DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

		Date validDate = ca.getValidDate();
		if (validDate != null)
			sAcct.setValid_date(DateUtil.formatDate(validDate,
					DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

		Date expireDate = ca.getExpireDate();
		if (expireDate != null)
			sAcct.setExpire_date(DateUtil.formatDate(expireDate,
					DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));

		if (ca.getAccountClass() != null)
			sAcct.setAcct_class(CommonUtil.IntegerToShort(ca.getAccountClass()));

		if (ca.getAccountSegment() != null)
			sAcct.setAcct_segment(CommonUtil.IntegerToShort(ca
					.getAccountSegment()));

		List<BillCycleComplex> billCycleList = context.getComponent(
				AccountComponent.class).queryBillCycle(ca.getAcctId());

		// 当前时间账户的账期只有一个
		if (!CommonUtil.isEmpty(billCycleList)) {
			SBillCycle billCycle = new SBillCycle();
			BillCycleComplex billCycleComplex = (BillCycleComplex) billCycleList
					.get(0);
			billCycle.setCycle_type(billCycleComplex.getCycleType());
			billCycle.setCycle_unit(billCycleComplex.getCycleUnit());
			billCycle.setFirst_bill_day(billCycleComplex.getFirstBillDate());
			sAcct.setBill_cycle(billCycle);
		}

		if (null != custInv) {
			if (null != custInv.getMailCode()) {
				sAcct.setBill_dispatching(custInv.getMailCode().shortValue());
			}

			if (null != custInv.getDueDay()) {
				sAcct.setDue_day(CommonUtil.IntegerToShort(custInv.getDueDay()));
			}

			if (null != custInv.getLanguageId()) {
				sAcct.setBill_language_id(CommonUtil.IntegerToShort(custInv
						.getLanguageId()));
			}
		}

		BudgetComponent bdgCmp = context.getComponent(BudgetComponent.class);
		sAcct.setBudget(CommonUtil.long2Double(bdgCmp.getBudgetValueByAcctId(ca
				.getAcctId())));
		sAcct.setList_ext_param(packageAcctParam(acctAttri, custInv, ca));

		sAcct.setParent_acct_id(context.getComponent(
				AccountRelationComponent.class).queryFatherAcctId(
				ca.getAcctId()));
		if (null != ca.getMeasureId())
			sAcct.setMeasure_id(ca.getMeasureId());
		sAcct.setProv_code(CommonUtil.IntegerToShort(ca.getProvinceId()));
		sAcct.setRegion_code(CommonUtil.IntegerToShort(ca.getRegionCode()));
		// yanchuan 2012-02-03 增加返回blance_type
		sAcct.setBalance_type(ca.getBalanceType());
		return sAcct;
	}

	/**
	 * @Date 2011-11-21 hunan :提取yindm写的方法
	 */
	private ExtendParamList packageAcctParam(CaAccountExt acctAttri,
			CaCustomInv custInv, CaAccount ca) {
		ExtendParamList paramList = new ExtendParamList();
		List<ExtendParam> paramArr = new ArrayList<ExtendParam>();
		ExtendParam param = new ExtendParam();

		param.setParam_name("next_bill_start_date");
		param.setParam_value(String.valueOf(context.getComponent(
				AccountComponent.class).queryAcctNextCycleStart(ca.getAcctId())));
		paramArr.add(param);

		if (null == acctAttri) {
			return null;
		}
		if (null != acctAttri.getItem1()) {
			ExtendParam param1 = new ExtendParam();
			param1.setParam_name(ConstantDefine.EXTPARAM_ACCT_SMSCONTACTNUM);
			param1.setParam_value(acctAttri.getItem1());
			paramArr.add(param1);
		}
		if (null != acctAttri.getItem2()) {
			ExtendParam param2 = new ExtendParam();
			param2.setParam_name(ConstantDefine.EXTPARAM_ACCT_ACCTBMCALLDETAIL);
			param2.setParam_value(acctAttri.getItem2());
			paramArr.add(param2);
		}
		if (null != acctAttri.getItem3()) {
			ExtendParam param3 = new ExtendParam();
			param3.setParam_name(ConstantDefine.EXTPARAM_ACCT_ACCTBMSUMMARY);
			param3.setParam_value(acctAttri.getItem3());
			paramArr.add(param3);
		}
		if (null != acctAttri.getItem4()) {
			ExtendParam param4 = new ExtendParam();
			param4.setParam_name(ConstantDefine.EXTPARAM_ACCT_DEFAULTCPSID);
			param4.setParam_value(acctAttri.getItem4());
			paramArr.add(param4);
		}

		if (null != acctAttri.getItem5()) {
			ExtendParam param5 = new ExtendParam();
			param5.setParam_name(ConstantDefine.EXTPARAM_ACCT_FAXBILLTO);
			param5.setParam_value(acctAttri.getItem5());
			paramArr.add(param5);
		}

		if (null != acctAttri.getItem6()) {
			ExtendParam param6 = new ExtendParam();
			param6.setParam_name(ConstantDefine.EXTPARAM_ACCT_CHARGETYPE);
			param6.setParam_value(acctAttri.getItem6());
			paramArr.add(param6);
		}

		if (null != acctAttri.getItem7()) {
			ExtendParam param7 = new ExtendParam();
			param7.setParam_name(ConstantDefine.EXTPARAM_ACCT_SMSBILLTO);
			param7.setParam_value(acctAttri.getItem7());
			paramArr.add(param7);
		}
		if (null != acctAttri.getItem8()) {
			ExtendParam param8 = new ExtendParam();
			param8.setParam_name(ConstantDefine.EXTPARAM_ACCT_SPECIALACCNT);
			param8.setParam_value(acctAttri.getItem8());
			paramArr.add(param8);
		}
		// 2012-06-29 tangkun add ORIGINAL_DefaultCPSId
		if (null != acctAttri.getItem13()) {
			ExtendParam param13 = new ExtendParam();
			param13.setParam_name(ConstantDefine.EXTPARAM_ACCT_ORIGINAL_DEFAULTCPSID);
			param13.setParam_value(acctAttri.getItem13());
			paramArr.add(param13);
		}
		// 2012-07-05 tangkun add copyBillBudget
		if (null != acctAttri.getItem14()) {
			ExtendParam param14 = new ExtendParam();
			param14.setParam_name(ConstantDefine.EXTPARAM_ACCT_COPYBILLBUDGET);
			param14.setParam_value(acctAttri.getItem14());
			paramArr.add(param14);
		}

		if (null != custInv) {
			if (null != custInv.getPrintBill()) {
				ExtendParam param9 = new ExtendParam();
				param9.setParam_name(ConstantDefine.EXTPARAM_ACCT_BILLPAYMENTCURRENCY);
				param9.setParam_value(String.valueOf(custInv.getPrintBill()));
				paramArr.add(param9);
			}

			if (null != custInv.getMailCode()) {
				ExtendParam param10 = new ExtendParam();
				param10.setParam_name(ConstantDefine.EXTPARAM_ACCT_EMAILBILLTO);
				param10.setParam_value(String.valueOf(custInv.getMailCode()));
				paramArr.add(param10);
			}

			if (null != custInv.getInvoiceType()) {
				ExtendParam param11 = new ExtendParam();
				param11.setParam_name(ConstantDefine.EXTPARAM_ACCT_BILLNAME);
				param11.setParam_value(String.valueOf(custInv.getInvoiceType()));
				paramArr.add(param11);
			}

			if (null != custInv.getBillFormatId()) {
				ExtendParam param12 = new ExtendParam();
				param12.setParam_name(ConstantDefine.EXTPARAM_ACCT_VATNAME);
				param12.setParam_value(String.valueOf(custInv.getBillFormatId()));
				paramArr.add(param12);
			}

			if (!CommonUtil.isEmpty(paramArr)) {
				paramList.setExtendParamList_Item(paramArr
						.toArray(new ExtendParam[paramArr.size()]));
			}
		}

		return paramList;
	}

	/**
	 * @yanchuan 2012-1-12 修改账户出账标识
	 * @param isCloseAcct
	 *            是否需要close帐户状态
	 */
	public void dealAcctBillSts(CaAccount account, Integer oldBillSts,
			Integer newBillSts, boolean isCloseAcct) {
		CaAccount dmAccount = new CaAccount();
		dmAccount.setBillSts(newBillSts);
		dmAccount.setBillStsDate(context.getRequestDate());
		if (isCloseAcct) {
			dmAccount.setAccountStatus(EnumCodeDefine.SERVICE_CYCLESTS_CLOSE);
		}
		// @Date 2012-10-31 yugb :On_Site Defect #63028
		else if (account != null
				&& account.getAccountStatus() == EnumCodeDefine.ACCOUNT_TERMINATE) {
			dmAccount.setAccountStatus(EnumCodeDefine.ACCOUNT_ACTIVE);
		}
		if (newBillSts != null && newBillSts != EnumCodeDefine.BILL_STS_ACTIVE) {
			dmAccount.setForceCycle(EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE);
		}

		this.updateByCondition(dmAccount, new DBCondition(
				CaAccount.Field.acctId, account.getAcctId()));

		// @Date 2012-11-02 yugb :On_Site Defect #63493
		if (oldBillSts != null && newBillSts != null
				&& oldBillSts != EnumCodeDefine.BILL_STS_ACTIVE
				&& newBillSts == EnumCodeDefine.BILL_STS_ACTIVE) {
			// yanchuan 2012-01-13 沿用原账户账期，将账户账期改为出账
			BillCycleHandler billCycleHandler = ChangeBillCycleContext
					.instanceHandler(dmAccount.getAcctId(), null, null, null,
							null, context);
			billCycleHandler.changeBillCycle();
		}

		// yanxl 2012-10-31将数据写入接口表
		if (newBillSts != null
				&& newBillSts == EnumCodeDefine.BILL_STS_TERMINATE) {
			ImsTermCycle imsTermCycle = new ImsTermCycle();
			imsTermCycle.setAcctId(account.getAcctId());
			imsTermCycle.setActionDate(context.getRequestDate());
			imsTermCycle.setBillSts(EnumCodeDefine.BILL_STS_TERMINATE);
			imsTermCycle.setCreateDate(context.getRequestDate());
			imsTermCycle.setSts((int) EnumCodeDefine.SYNC_DEAL_STS_INITIAL);
			imsTermCycle.setSoNbr(context.getSoNbr());

			ImsTermCycle itbct = DBUtil.querySingle(
					ImsTermCycle.class,
					new DBCondition(ImsTermCycle.Field.acctId, account
							.getAcctId()));
			if (itbct != null) {
				DBUtil.updateByCondition(imsTermCycle, new DBCondition(
						ImsTermCycle.Field.acctId, account.getAcctId()));
			} else {
				DBUtil.insert(imsTermCycle);
			}
		}

	}

	/**
	 * @author yanhuan 2012-1-14 判断断支付帐户下是否还有后付费或者混合模式用户(true:有，false：没有)
	 * @param acctId
	 * @return
	 */
	public boolean isExistPostpaidUserExceptUserId(Long acctId, Long userId) {
		UserQuery userQry = context.getComponent(UserQuery.class);
		List<CmResource> resList = userQry.queryUserByBillAcctId(acctId);
		Long postUserId = null;
		if (CommonUtil.isNotEmpty(resList)) {

			for (CmResource resource : resList) {
				if (resource.getBillingType() == null)
					continue;
				if (resource.getBillingType() == EnumCodeDefine.USER_PAYMODE_POSTPAID
						&& resource.getResourceId() != userId.longValue()) {
					postUserId = resource.getResourceId();
					break;
				} else if (resource.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID
						&& resource.getResourceId() != userId.longValue()) {
					postUserId = resource.getResourceId();
					break;
				} else if (resource.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID
						&& resource.getResourceId() != userId.longValue()) {
					postUserId = resource.getResourceId();
					break;
				}
			}
			if (postUserId != null) {
				CoProd prod = context.getComponent(ProductQuery.class)
						.queryPrimaryProductByUserId(postUserId);
				Long prospectOfferId = CommonUtil.IntegerToLong(SysUtil
						.getInt(SysCodeDefine.busi.DEFAULT_PROSPECT_OFFERING));
				if (prod != null
						&& prospectOfferId != null
						&& prospectOfferId.intValue() == prod
								.getProductOfferingId()) {
					return false;
				}
				if (userId != null && postUserId.longValue() != userId)
					return true;
				else if (userId == null)
					return true;
			}
		}
		return false;
	}

	/**
	 * @author 2012-07-18 luojb #49524判断断支付帐户下是否还有预付费或者混合模式用户(true:有，false：没有)
	 * @param acctId
	 * @return
	 */
	public boolean isExistPrepaidUserExceptUserId(Long acctId, Long userId) {
		UserQuery userQry = context.getComponent(UserQuery.class);
		List<CmResource> resList = userQry.queryUserByBillAcctId(acctId);
		if (CommonUtil.isNotEmpty(resList)) {

			for (CmResource resource : resList) {
				if (resource.getBillingType() == null)
					continue;
				if (resource.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID
						&& resource.getResourceId() != userId.longValue()) {
					return true;
				} else if (resource.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID
						&& resource.getResourceId() != userId.longValue()) {
					return true;
				} else if (resource.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID
						&& resource.getResourceId() != userId.longValue()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 校验是否输入有效的变更账户 wukl 2012-2-28 (适用于部分接口，使用时请斟酌)
	 * 
	 * @param newAcctId
	 * @param account
	 */
	public void checkNewAcctId(Long newAcctId, SAccount account) {
		if (account == null) {
			if (CommonUtil.isValid(newAcctId)) {
				// 账户对象不传，新账户ID传，则验证新账户ID是否已存在
				context.getComponent(CheckComponent.class).checkAcctId(
						newAcctId);
			} else {
				IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,
						"acct_id");
			}
			return;

		}

		if (!CommonUtil.isValid(account.getAcct_id())) {// 账户ID不能为空
			IMSUtil.throwBusiException(ErrorCodeDefine.PAYMODE_SACCOUNT_ACCTID_INVALID);
		}
	}

	/**
	 * @author yanchuan 2012-3-8
	 * @describe 判断帐户是否还在出账（判断层级关系及vpn）(true:表示还在出账，false： 表示不出账)
	 * @param acctId
	 */
	private boolean isActiveBillSts(Long parentAcctId, List<Long> acctIds) {
		AccountRelationComponent acctRelCmp = context
				.getComponent(AccountRelationComponent.class);
		// List<Long> acctIds = acctRelCmp.queryAllSubCa(parentAcctId);
		// 判断子账户的bill_sts是否为出账状态
		if (!CommonUtil.isEmpty(acctIds)) {
			Long acct_ids[] = acctIds.toArray(new Long[acctIds.size()]);
			List<CaAccount> accounts = query(CaAccount.class, new DBCondition(
					CaAccount.Field.acctId, acct_ids, Operator.IN),
					new DBCondition(CaAccount.Field.billSts,
							EnumCodeDefine.BILL_STS_ACTIVE));
			if (CommonUtil.isNotEmpty(accounts))
				return true;
		}
		// 判断该帐户作为群的支付帐户时，群是否有订购后付费产品
		List<Long> GCAAcctIds = acctRelCmp.queryGCAAcctIds(parentAcctId);
		if (CommonUtil.isEmpty(GCAAcctIds))
			return false;
		ProductQuery productQry = context.getComponent(ProductQuery.class);
		for (Long groupId : GCAAcctIds) {
			List<CoProd> coProds = productQry.queryProdListByGroupId(groupId);
			if (CommonUtil.isEmpty(coProds))
				continue;
			for (CoProd coProd : coProds) {
				if (coProd == null)
					continue;
				if (coProd.getBillingType() != null
						&& coProd.getBillingType() == EnumCodeDefine.USER_PAYMODE_POSTPAID) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @author yanchuan 2012-3-8
	 * @decribe 将账户的bill_sts状态由1改为2
	 * @param account
	 * @param old_bill_sts
	 * @param new_bill_sts
	 * @param isCloseAcct
	 *            是否将帐户的状态置为close
	 */
	public void terminateAcctBillCycle(CaAccount account, Integer old_bill_sts,
			Integer new_bill_sts, boolean isCloseAcct) {
		Long parentAcctId = account.getAcctId();
		List<Long> acctIds = context.getComponent(
				AccountRelationComponent.class).queryAllSubCa(parentAcctId);
		if (!isActiveBillSts(parentAcctId, acctIds)) {
			this.dealAcctBillSts(account, old_bill_sts, new_bill_sts,
					isCloseAcct);
			List<CaAccount> acctList = new ArrayList<CaAccount>();
			if (!CommonUtil.isEmpty(acctIds)) {
				for (Long acctId : acctIds) {
					CaAccount caAccount = null;
					try {
						caAccount = context.get3hTree().loadAcct3hBean(acctId)
								.getAccount();
					} catch (IMS3hNotFoundException e) {
						continue;
					}
					acctList.add(caAccount);
				}
			}

			if (account.getForceCycle() != null
					&& (account.getForceCycle() == EnumCodeDefine.CUSTOMER_FORCECYCLE_TRUE || account
							.getForceCycle() == EnumCodeDefine.FORCECYCLE_BY_PARENT_ACCOUNT)) {
				this.modifyAcctForceCycle(acctList);
			}
		} else if (isCloseAcct == true) {
			this.chgAcctStatus(account.getAcctId(),
					EnumCodeDefine.SERVICE_CYCLESTS_CLOSE);
		}

	}

	/**
	 * @yanchuan 2012-3-15
	 * @describe 获取缓存中的下周期生效日
	 */
	private Date getNextCycleValidDate(Long acctId) {
		return (Date) context
				.getExtendParam(ConstantDefine.NEXT_CYCLE_VALID_DATE + acctId);
	}

	/**
	 * 根据用户及账户 获取支付账户的OwnerType wukl 2012-3-21
	 */
	public Integer getOwnerType(Integer ownerType, Long userId, String phoneId,
			Long acctId) {

		IMS3hBean bean = context.get3hTree().load3hBean(null, acctId, userId,
				phoneId);
		Integer balanceType = null;
		try {
			balanceType = bean.getAccount().getBalanceType();
		} catch (IMS3hNotFoundException e) {
			// @Date 2012-10-26 yugb :User Story #62519 如果用户的状态是失效状态,则不需要抛异常
			if (bean.isUser3hBean() && !((User3hBean) bean).isTerminalOrPool())
				IMSUtil.throw3hBeanNotFoundException(
						ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST,
						bean.getUserId());
		}
		// @Date 2012-07-24 tangjl5 :Bug # 52687 do_setNegativeBalance，single
		// balance账户下设置用户级负余额，设置成功
		if (ownerType != null && balanceType != ownerType.intValue()) {
			throw IMSUtil.throwBusiException(
					ErrorCodeDefine.SSETNEGATIVEBALANCE_PARAM_WRONG, ownerType,
					balanceType);
		}

		return balanceType;
	}

	/**
	 * 
	 * @param ca
	 * @return //@Date 2012-10-08 yugb :60590 返回账单寄送地址
	 */
	public List<SContact> getSContactAddress(CaAccount ca) {
		if (ca == null) {
			return null;
		}
		if (SysUtil.getBoolean(SysCodeDefine.busi.IM_SCONTACT_ADDRESS_FLAG)) {
			List<SContact> sContactList = new ArrayList<SContact>();
			List<CmContactMedium> cmContactMediumList = context.getComponent(
					ContactComponent.class).queryAcctContactMediums(
					ca.getAcctId(),
					(short) EnumCodeDefine.CONTACT_TYPE_ACCOUNTADDRESS);
			if (CommonUtil.isEmpty(cmContactMediumList))
				return null;
			for (CmContactMedium cmContactMedium : cmContactMediumList) {
				SContact sContact = new SContact();
				sContact.setAddress(cmContactMedium.getAddress());
				sContact.setAddress1(cmContactMedium.getExtAddress1());
				sContact.setAddress2(cmContactMedium.getExtAddress2());
				sContact.setAddress3(cmContactMedium.getExtAddress3());
				sContact.setAddress4(cmContactMedium.getExtAddress4());
				sContactList.add(sContact);
			}
			return sContactList;
		}
		return null;

	}

	/**
	 * 查询账户历史数据
	 * 
	 * @param userId
	 * @return
	 */
	public CaAccount queryHistoryCaAcctRes(Long userId) {
		List<CaAccountRes> caAccountResList = DBUtil.query(CaAccountRes.class,
				new OrderCondition[] { new OrderCondition(false,
						CaAccountRes.Field.soNbr) }, null, new DBCondition(
						CaAccountRes.Field.resourceId, userId));
		if (CommonUtil.isNotEmpty(caAccountResList)) {
			CaAccountRes caAccountRes = caAccountResList.get(0);
			CaAccount account = querySingle(CaAccount.class, new DBCondition(
					CaAccount.Field.acctId, caAccountRes.getAcctId()));
			return account;
		}
		return null;
	}

	/**
	 * 查询客户历史数据
	 * 
	 * @param acctId
	 * @return
	 */
	public CmCustomer queryHistoryCaCustRel(Long acctId) {
		List<CaCustomerRel> caCustomerRelList = DBUtil.query(
				CaCustomerRel.class, new OrderCondition[] { new OrderCondition(
						false, CaCustomerRel.Field.soNbr) }, null,
				new DBCondition(CaCustomerRel.Field.acctId, acctId));
		if (CommonUtil.isNotEmpty(caCustomerRelList)) {
			CaCustomerRel caCustomerRel = caCustomerRelList.get(0);
			CmCustomer customer = querySingle(
					CmCustomer.class,
					new DBCondition(CmCustomer.Field.custId, caCustomerRel
							.getCustId()));
			return customer;
		}
		return null;
	}

}
