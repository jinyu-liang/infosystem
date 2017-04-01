package com.ailk.imssh.user.cmp;

import com.ailk.ims.common.DBCondition;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareRel;
import com.ailk.openbilling.persistence.itable.entity.IUserShareRel;

public class UserShareRelCmp extends BaseCmp {

	public void createUserShareRel(IUserShareRel rel) {
		CmUserShareRel cmUserShareRel = new CmUserShareRel();
		cmUserShareRel.setCreateDate(context.getCommitDate());
		cmUserShareRel.setSoDate(context.getCommitDate());
		cmUserShareRel.setShareId(rel.getShareId());
		cmUserShareRel.setResourceId(rel.getUserId());
		cmUserShareRel.setViceResourceId(rel.getViceUserId());
		cmUserShareRel.setRoleType(rel.getRoleType());
		cmUserShareRel.setRegionCode(rel.getRegionCode());
		cmUserShareRel.setSoNbr(rel.getSoNbr());
		cmUserShareRel.setValidDate(rel.getValidDate());
		cmUserShareRel.setExpireDate(rel.getExpireDate());
		cmUserShareRel.setRemark(rel.getRemark());
		super.insert(cmUserShareRel);
	}

	public void updateUserShareRel(IUserShareRel rel) {
		CmUserShareRel cmUserShareRel = new CmUserShareRel();
		cmUserShareRel.setCreateDate(context.getCommitDate());
		cmUserShareRel.setSoDate(context.getCommitDate());
		// cmUserShareRel.setResourceId(rel.getUserId());
		cmUserShareRel.setViceResourceId(rel.getViceUserId());
		cmUserShareRel.setRoleType(rel.getRoleType());
		cmUserShareRel.setRegionCode(rel.getRegionCode());
		cmUserShareRel.setSoNbr(rel.getSoNbr());
		cmUserShareRel.setExpireDate(rel.getExpireDate());
		cmUserShareRel.setRemark(rel.getRemark());
		this.updateByCondition(cmUserShareRel, new DBCondition(CmUserShareRel.Field.resourceId, rel.getUserId()), new DBCondition(
				CmUserShareRel.Field.shareId, rel.getShareId()));
	}

	public void deleteUserShareRel(IUserShareRel rel) {
		this.deleteByCondition(CmUserShareRel.class, new DBCondition(CmUserShareRel.Field.resourceId, rel.getUserId()), new DBCondition(
				CmUserShareRel.Field.shareId, rel.getShareId()));
	}

}
