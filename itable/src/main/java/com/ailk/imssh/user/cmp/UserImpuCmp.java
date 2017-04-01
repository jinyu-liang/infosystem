package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.cust.entity.CmUserImpu;
import com.ailk.openbilling.persistence.itable.entity.IUserImpu;

import jef.database.Condition.Operator;

public class UserImpuCmp extends BaseCmp {

	public void createUserImpu(IUserImpu impu) {
		CmUserImpu dmImpu = new CmUserImpu();
		dmImpu.setResourceId(impu.getUserId());
		dmImpu.setExpireDate(impu.getExpireDate());
		dmImpu.setImpuType(impu.getImpuType());
		dmImpu.setRemark(impu.getRemark());
		dmImpu.setSipUrl(impu.getSipUrl());
		dmImpu.setTelUrl(impu.getTelUrl());
		dmImpu.setValidDate(impu.getValidDate());
		dmImpu.setSoDate(context.getCommitDate());
		dmImpu.setCreateDate(context.getCommitDate());
		dmImpu.setSoNbr(context.getSoNbr());
		this.insert(dmImpu);
	}

	public void updateUserImpu(IUserImpu impu) {
		CmUserImpu dmImpu = new CmUserImpu();
		dmImpu.setResourceId(impu.getUserId());
		dmImpu.setExpireDate(impu.getExpireDate());
		dmImpu.setImpuType(impu.getImpuType());
		dmImpu.setRemark(impu.getRemark());
		dmImpu.setSipUrl(impu.getSipUrl());
		dmImpu.setTelUrl(impu.getTelUrl());
		dmImpu.setSoDate(context.getCommitDate());
		dmImpu.setCreateDate(context.getCommitDate());
		dmImpu.setSoNbr(context.getSoNbr());
		this.updateMode2(dmImpu, EnumCodeExDefine.OPER_TYPE_UPDATE, impu.getValidDate(),
				new DBCondition(CmUserImpu.Field.resourceId, impu.getUserId()));
	
		/**
		this.deleteByCondition(CmUserImpu.class,new DBCondition(CmUserImpu.Field.resourceId, impu.getUserId()),
				new DBCondition(CmUserImpu.Field.impuType, impu.getImpuType()),
				new DBCondition(CmUserImpu.Field.validDate,impu.getValidDate()));
		*/
	}

	public void deleteUserImpu(IUserImpu impu) {
		CmUserImpu dmImpu = new CmUserImpu();
		dmImpu.setExpireDate(impu.getExpireDate());
		this.updateMode2(dmImpu, EnumCodeExDefine.OPER_TYPE_DELETE, impu.getValidDate(),
				new DBCondition(CmUserImpu.Field.resourceId, impu.getUserId()));
	
	}
}
