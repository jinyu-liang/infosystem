package com.ailk.imssh.cust.cmp;

import java.util.ArrayList;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.cust.entity.CmCustGroup;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmRouteIdentity;
import com.ailk.openbilling.persistence.itable.entity.ICustGroup;

import jef.database.Condition.Operator;

public class CustGroupCmp extends BaseCmp {
	private String specialTableName="CD.CM_CUST_GROUP";
	
	private CmCustGroup convertCustGroup(ICustGroup group,boolean flag){
		CmCustGroup cmCustGroup = new CmCustGroup();
		cmCustGroup.setSoDate(context.getCommitDate());
		cmCustGroup.setCreateDate(context.getCommitDate());

		cmCustGroup.setGroupId(group.getGroupId());
		cmCustGroup.setGroupType(group.getGroupType());
		cmCustGroup.setMemberCustId(group.getMemberCustId());
		cmCustGroup.setUsecustId(group.getUsecustId());
		cmCustGroup.setRemoveTag(group.getRemoveTag());
		cmCustGroup.setSoNbr(context.getSoNbr());
		cmCustGroup.setExpireDate(group.getExpireDate());
		cmCustGroup.setRemark(group.getRemark());

		if(flag){
			cmCustGroup.setResourceId(group.getUserId());
			cmCustGroup.setCustId(group.getCustId());
			cmCustGroup.setValidDate(group.getValidDate());

		}
		return cmCustGroup;
	}
	
	
	public void createCustGroup(ICustGroup group) {
		CmCustGroup cmCustGroup = convertCustGroup(group,true);
		DBUtil.insertWithName(cmCustGroup, dbkey[0], specialTableName);
    	DBUtil.insertWithName(cmCustGroup, dbkey[1], specialTableName);

	}

	public void updateCustGroup(ICustGroup group) {
		
		CmCustGroup cmCustGroup = convertCustGroup(group,false);
		//更新1库
//		List<CmCustGroup> updateDb1List = updateNoCacheByKey(null,cmCustGroup, null, null, dbkey[0], specialTableName,new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
//				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()),
//				new DBCondition(CmCustGroup.Field.validDate, group.getValidDate()),
//				new DBCondition(CmCustGroup.Field.validDate, CmCustGroup.Field.expireDate,Operator.LESS_EQUALS));

	
		this.updateMode2AndCust(cmCustGroup, EnumCodeExDefine.OPER_TYPE_UPDATE,group.getValidDate(),dbkey[0], specialTableName, 
				new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()));
		
		this.updateMode2AndCust(cmCustGroup, EnumCodeExDefine.OPER_TYPE_UPDATE,group.getValidDate(),dbkey[1], specialTableName, 
				new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()));
//		if(updateDb1List==null){
//			DBUtil.insertWithName(cmCustGroup, dbkey[0], specialTableName);
//		}
//		DBUtil.deleteByDbKey(CmCustGroup.class, dbkey[0], specialTableName, new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
//				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()),
//				new DBCondition(CmCustGroup.Field.validDate, group.getValidDate()),
//				new DBCondition(CmCustGroup.Field.validDate, CmCustGroup.Field.expireDate,Operator.GREAT));
//		
		//更新2库
//		List<CmCustGroup> updateDb2List = updateNoCacheByKey(null,cmCustGroup, null, null, dbkey[1], specialTableName,new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
//				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()),
//				new DBCondition(CmCustGroup.Field.validDate, group.getValidDate()),
//				new DBCondition(CmCustGroup.Field.validDate, CmCustGroup.Field.expireDate,Operator.LESS_EQUALS));
//		if(updateDb2List==null){
//			DBUtil.insertWithName(cmCustGroup, dbkey[1], specialTableName);
//		}
//		DBUtil.deleteByDbKey(CmCustGroup.class, dbkey[1], specialTableName, new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
//				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()),
//				new DBCondition(CmCustGroup.Field.validDate, group.getValidDate()),
//				new DBCondition(CmCustGroup.Field.validDate, CmCustGroup.Field.expireDate,Operator.GREAT));
		/**
		this.deleteByCondition(CmCustGroup.class, context.getRequestDate(), 
				new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
				new DBCondition(CmCustGroup.Field.validDate, group.getValidDate()));
		*/
	}

	public void deleteCustGroup(ICustGroup group) {
		CmCustGroup cmCustGroup=new CmCustGroup();
		cmCustGroup.setExpireDate(group.getExpireDate());
		this.updateMode2AndCust(cmCustGroup, EnumCodeExDefine.OPER_TYPE_DELETE,group.getValidDate(),dbkey[1], specialTableName, 
				new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()));
		this.updateMode2AndCust(cmCustGroup, EnumCodeExDefine.OPER_TYPE_DELETE,group.getValidDate(),dbkey[0], specialTableName, 
				new DBCondition(CmCustGroup.Field.custId, group.getCustId()),
				new DBCondition(CmCustGroup.Field.resourceId, group.getUserId()));
	}
	
	
}
