package com.ailk.imssh.cust.cmp;

import jef.database.Condition.Operator;

import java.util.ArrayList;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.cust.entity.CmCustVip;
import com.ailk.openbilling.persistence.itable.entity.ICustVip;

public class CustVipCmp extends BaseCmp {

	public void createCustVip(ICustVip vip) {
		CmCustVip cmCustVip = new CmCustVip();
		cmCustVip.setSoDate(context.getCommitDate());
		cmCustVip.setCreateDate(context.getCommitDate());
		cmCustVip.setResourceId(vip.getUserId());
		cmCustVip.setCustId(vip.getCustId());
		cmCustVip.setUsecustId(vip.getUsecustId());
		cmCustVip.setVipState(vip.getVipState());
		cmCustVip.setVipClass(vip.getVipClass());
		cmCustVip.setVipType(vip.getVipType());
		cmCustVip.setCustManagerId(vip.getCustManagerId());
		cmCustVip.setValidDate(vip.getValidDate());
		cmCustVip.setExpireDate(vip.getExpireDate());
		cmCustVip.setSoNbr(context.getSoNbr());
//		cmCustVip.setRemark(vip.getRemark());
		super.insert(cmCustVip);

	}

	public void updateCustVip(ICustVip vip) {
		
		CmCustVip cmCustVip = new CmCustVip();
		cmCustVip.setSoDate(context.getCommitDate());
		cmCustVip.setCreateDate(context.getCommitDate());
		cmCustVip.setCustId(vip.getCustId());
		cmCustVip.setUsecustId(vip.getUsecustId());
		cmCustVip.setVipState(vip.getVipState());
		cmCustVip.setVipClass(vip.getVipClass());
		cmCustVip.setVipType(vip.getVipType());
		cmCustVip.setCustManagerId(vip.getCustManagerId());
		cmCustVip.setExpireDate(vip.getExpireDate());
		cmCustVip.setSoNbr(context.getSoNbr());
	    this.updateMode2(cmCustVip,EnumCodeExDefine.OPER_TYPE_UPDATE,vip.getValidDate(), new DBCondition(CmCustVip.Field.resourceId, vip.getUserId()));
	}

	public void deleteCustVip(ICustVip vip) {
		CmCustVip cmCustVip = new CmCustVip();
		cmCustVip.setExpireDate(vip.getExpireDate());
	    this.updateMode2(cmCustVip,EnumCodeExDefine.OPER_TYPE_DELETE,vip.getValidDate(), new DBCondition(CmCustVip.Field.resourceId, vip.getUserId()));


	}

}
