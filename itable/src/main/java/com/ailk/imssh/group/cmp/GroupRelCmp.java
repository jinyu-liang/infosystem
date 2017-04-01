package com.ailk.imssh.group.cmp;

import java.util.ArrayList;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.itable.entity.IAcctRel;

import jef.database.Condition.Operator;

public class GroupRelCmp extends BaseCmp {

	public void createGroupRel(IAcctRel rel) {
		CaAccountRel caAccountRel = new CaAccountRel();
		caAccountRel.setCreateDate(context.getCommitDate());
		caAccountRel.setSoDate(context.getCommitDate());
		caAccountRel.setGroupId(rel.getGroupId());
		caAccountRel.setRelGroupId(rel.getRelGroupId());
		caAccountRel.setRelationshipType(rel.getRelationshipType());
		caAccountRel.setTitleRoleId(rel.getTitleRoleId());
		caAccountRel.setSoNbr(context.getSoNbr());
		caAccountRel.setValidDate(rel.getValidDate());
		caAccountRel.setExpireDate(rel.getExpireDate());
		super.insert(caAccountRel);

		
	}

	public void updateGroupRel(IAcctRel rel) {
		
		CaAccountRel caAccountRel = new CaAccountRel();
		caAccountRel.setCreateDate(context.getCommitDate());
		caAccountRel.setSoDate(context.getCommitDate());
		caAccountRel.setRelationshipType(rel.getRelationshipType());
		caAccountRel.setTitleRoleId(rel.getTitleRoleId());
		caAccountRel.setSoNbr(context.getSoNbr());
		caAccountRel.setExpireDate(rel.getExpireDate());
		caAccountRel.setValidDate(rel.getValidDate());
		this.updateMode2(caAccountRel, EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(), 
				new DBCondition(CaAccountRel.Field.groupId, rel.getGroupId()),
				new DBCondition(CaAccountRel.Field.relGroupId, rel.getRelGroupId()));
		
		 /**
		this.deleteByCondition(CaAccountRel.class, new DBCondition(CaAccountRel.Field.groupId, rel.getGroupId()),
				new DBCondition(CaAccountRel.Field.relGroupId, rel.getRelGroupId()),
				new DBCondition(CaAccountRel.Field.relationshipType, rel.getRelationshipType()),
				new DBCondition(CaAccountRel.Field.validDate, rel.getValidDate()));
		*/
	}

	public void deleteGroupRel(IAcctRel rel) {
		CaAccountRel caAccountRel = new CaAccountRel();
		caAccountRel.setExpireDate(rel.getExpireDate());
		this.updateMode2(caAccountRel, EnumCodeExDefine.OPER_TYPE_DELETE,rel.getValidDate(),
				new DBCondition(CaAccountRel.Field.groupId, rel.getGroupId()),
				new DBCondition(CaAccountRel.Field.relGroupId, rel.getRelGroupId()));
		
	}

}
