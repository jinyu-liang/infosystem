package com.ailk.imssh.cust.cmp;

import java.util.Date;
import java.util.List;





import jef.database.DataObject;
import jef.tools.StringUtils;

import com.ailk.easyframe.route.entity.br.RouterMain;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.acct.handler.AcctHandler;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.credit.cmp.CreditCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAssetSyn;
import com.ailk.openbilling.persistence.acct.entity.CaBatchSyn;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.itable.entity.IAccount;
import com.ailk.openbilling.persistence.itable.entity.ICustomer;

/**
 * @Description：客户信息实例化过程
 * @author wangjt
 * @Date 2012-5-11
 */
public class CustCmp extends BaseCmp {
	
	private String specialTableName="CD.CM_CUSTOMER";
	/**
	 * 增加或者修改客户信息（通过oper_type区分）
	 */
	public void createOrModifyCust(ICustomer iCustomer) {
		// 如果是插，则先发布账户和客户路由
		if (iCustomer.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
			List<? extends DataObject>[] acctList = context.getIDataObjectList(AcctHandler.class);
			if (CommonUtil.isNotEmpty(acctList)) {
				List<IAccount> acctArr = (List<IAccount>) acctList[0];
				if (CommonUtil.isNotEmpty(acctArr)) {
					for (IAccount iAccount : acctArr) {// 等于1才创建路由
						context.getCmp(RouterCmp.class).createAcctRouter(iAccount.getAcctId(), iAccount.getCustId(),
								iAccount.getRegionCode(), iAccount.getValidDate(), iAccount.getExpireDate());
						break;
					}
				}
			}
		}
		this.insertOrUpdateCmCustomer(iCustomer);
		
	}

	public void deleteCust(ICustomer iCustomer) {
		CmCustomer cmCustomer = new CmCustomer();
		int partitionId = (int)(iCustomer.getCustId()%10000);
		cmCustomer.setExpireDate(iCustomer.getExpireDate());
		this.updateMode2AndCust(cmCustomer, EnumCodeExDefine.OPER_TYPE_DELETE,iCustomer.getValidDate(),dbkey[0], specialTableName,
				new DBCondition(CmCustomer.Field.custId, iCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId,partitionId));
		this.updateMode2AndCust(cmCustomer, EnumCodeExDefine.OPER_TYPE_DELETE,iCustomer.getValidDate(),dbkey[1], specialTableName,
				new DBCondition(CmCustomer.Field.custId, iCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId,partitionId));

	}
	
	

	private void insertOrUpdateCmCustomer(ICustomer iCustomer) {
		Date currentDate = DateUtil.currentDate();
		CmCustomer cmCustomer = new CmCustomer();

		
		cmCustomer.setCustomerType(iCustomer.getSubCustType());
		//if (iCustomer.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
		cmCustomer.setCustomerSegment(-1);
		//}
		// cmCustomer.setCustCreditProfileId(iCustomer.getCustCreditProfileId());
		// cmCustomer.setPerformanceId(iCustomer.getPerformanceId());
		// cmCustomer.setCustomerQuoteid(iCustomer.getCustomerQuoteid());
		cmCustomer.setReadingLanguage(iCustomer.getLanguage());
		cmCustomer.setListeningLanguage(iCustomer.getLanguage());
		cmCustomer.setWritingLanguage(iCustomer.getLanguage());
		// cmCustomer.setRemarks(iCustomer.getRemarks());
		// cmCustomer.setTaxExemptionNo(iCustomer.getTaxExemptionNo());
		cmCustomer.setCustomerName(iCustomer.getCustName());
		cmCustomer.setGroupId(iCustomer.getGroupId()!=null?iCustomer.getGroupId().toString():"");
		cmCustomer.setRegAddress(iCustomer.getCustAddress());
		cmCustomer.setCustManageId(iCustomer.getCustManageIdb());
		cmCustomer.setCustManageIda(iCustomer.getCustManageIda());
		cmCustomer.setGroupContactPhone(iCustomer.getGroupContactPhone());
		cmCustomer.setCreateDate(iCustomer.getCommitDate());
		cmCustomer.setExpireDate(iCustomer.getExpireDate());
		cmCustomer.setSoNbr(context.getSoNbr());
		cmCustomer.setSoDate(iCustomer.getCommitDate());
		cmCustomer.setProvCode(iCustomer.getProvCode());
		cmCustomer.setRegionCode(iCustomer.getRegionCode());
		cmCustomer.setCountyCode(iCustomer.getCountyId());
		cmCustomer.setRegNbr(iCustomer.getRegNbr());
		cmCustomer.setRegType(iCustomer.getRegType());
		cmCustomer.setIssueDate(iCustomer.getIssueDate());
		cmCustomer.setRealNameDate(iCustomer.getRealNameDate());
		cmCustomer.setRealNameSts(iCustomer.getRealNameSts());
		cmCustomer.setBirthDate(iCustomer.getBirthday());
		cmCustomer.setGender(iCustomer.getGender());
		cmCustomer.setCustomerClass(iCustomer.getCustType());// 0:个人，1:集团
		cmCustomer.setSts(EnumCodeDefine.CUSTOMER_STS_ACTIVE);
		cmCustomer.setCountryId(EnumCodeDefine.CUSTOMER_DEFAULT_COUNTRY_ID);

		cmCustomer.setValidDate(iCustomer.getValidDate());
		cmCustomer.setCustId(iCustomer.getCustId());
		cmCustomer.setPartitionId((int)(iCustomer.getCustId()%10000));
		
		if (iCustomer.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
			// 判断客户存在存在，如果存在调整成修改
			insertCustomer(cmCustomer);
		} else {

			updateCustomer(cmCustomer,iCustomer);
		}
	}
	
	private void insertCustomer(CmCustomer cmCustomer){
		//操作1库
		CmCustomer dmCustDB1 = DBUtil.querySingleByDbKey(CmCustomer.class, dbkey[0],new DBCondition(CmCustomer.Field.custId, cmCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));
		if (dmCustDB1 != null) {
			super.updateWithInsertBykey(cmCustomer, null, null, dbkey[0], specialTableName, 
					new DBCondition(CmCustomer.Field.custId, cmCustomer.getCustId()),
					new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));
		} else {
		
			DBUtil.insertWithName(cmCustomer,dbkey[0], specialTableName);
		}
		//操作2库
		CmCustomer dmCustDB2 = DBUtil.querySingleByDbKey(CmCustomer.class, dbkey[1],new DBCondition(CmCustomer.Field.custId, cmCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));
		if (dmCustDB2 != null) {
			super.updateWithInsertBykey(cmCustomer, null, null, dbkey[1], specialTableName, 
					new DBCondition(CmCustomer.Field.custId, cmCustomer.getCustId()),
					new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));
		} else {
		
			DBUtil.insertWithName(cmCustomer,dbkey[1], specialTableName);
		}
	}
	
	private void updateCustomer(CmCustomer cmCustomer,ICustomer iCustomer){
		this.updateWithInsertBykey(cmCustomer, null, null, dbkey[0], specialTableName, 
				new DBCondition(CmCustomer.Field.custId, iCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));
		this.updateWithInsertBykey(cmCustomer, null, null, dbkey[1], specialTableName, 
				new DBCondition(CmCustomer.Field.custId, iCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));

	}
	
	/**
	 * 客户等级有信控插客户表单独更新
	 * 
	 * @return
	 */
	public final boolean isOnlyUpdateCustSegment() {
		/**
		if (context.getBusiCode() != null && context.getBusiCode() == EnumCodeExDefine.CUST_SEG_BUSI_CODE) {
			return true;
		}
		*/
		String soNbr = StringUtils.toString(context.getSoNbr());
    	if(soNbr.substring(soNbr.length()-2, soNbr.length()-1).equals(ConstantDefine.CC_UPDATE)){
    		return true;
    	}
        return false;
	}

	/**
	 * 更新客户等级
	 * 
	 * @param iCustomer
	 */
	public final void updateCmCustomerCustSegment(final ICustomer iCustomer) {

		CmCustomer cmCustomer = new CmCustomer();

		cmCustomer.setCustomerSegment(iCustomer.getCustSegment());
		cmCustomer.setSoNbr(context.getSoNbr());
		cmCustomer.setPartitionId((int)(iCustomer.getCustId()%10000));
		updateCustSegment(cmCustomer,iCustomer);
		createCassetSyn(iCustomer);
	}
	
	private void updateCustSegment(CmCustomer cmCustomer,ICustomer iCustomer){
		this.updateNoCacheByKey(null, cmCustomer,context.getRequestDate(),dbkey[0], specialTableName,
				new DBCondition(CmCustomer.Field.custId, iCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));
		this.updateNoCacheByKey(null, cmCustomer,context.getRequestDate(),dbkey[1], specialTableName,
				new DBCondition(CmCustomer.Field.custId, iCustomer.getCustId()),
				new DBCondition(CmCustomer.Field.partitionId, cmCustomer.getPartitionId()));

	}
	
	private void createCassetSyn(ICustomer icustomer){

			RouterCmp routerCmp = context.getCmp(RouterCmp.class);
			RouterMain account = routerCmp.querySegAcctByCust(icustomer.getCustId());
	       
	        	CaBatchSyn caAssetSyn = new CaBatchSyn();
		        caAssetSyn.setSynId(DBUtil.getSequence(CaAssetSyn.class)); 
		        caAssetSyn.setAcctId(account.getAcctId());
		        caAssetSyn.setAcctFlag(0);
				caAssetSyn.setBizeType(0);
				caAssetSyn.setBizeInfo(CreditCmp.BIZE_INFO);
		      
		        caAssetSyn.setCreateDate(context.getCommitDate());
		        caAssetSyn.setValidDate(icustomer.getValidDate());
		        caAssetSyn.setExpireDate(icustomer.getExpireDate());
		        caAssetSyn.setSts(0);
		        caAssetSyn.setStsDate(context.getCommitDate());
		        caAssetSyn.setSoNbr(context.getSoNbr());
		        caAssetSyn.setSyncFlag(CreditCmp.SYNC_FLAG);
		         
		  
		        caAssetSyn.setNotifyFlag(CreditCmp.NOTIFY_FLAG);
		        caAssetSyn.setSyncType(CreditCmp.SYNC_TYPE_CUST);
		        caAssetSyn.setSoDate(context.getCommitDate());

		        caAssetSyn.setRegionCode(EnumCodeExDefine.TJ_REGION_CODE);
		        super.insert(caAssetSyn);
	         
	}
}
