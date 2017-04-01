package com.ailk.imssh.acct.cmp;

import com.ailk.ims.common.DBCondition;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAcctPaycycle;
import com.ailk.openbilling.persistence.itable.entity.IAcctPaycycle;

public class AcctPayCycleCmp extends BaseCmp {

	public void createPayCycle(IAcctPaycycle cycle) {
		CaAcctPaycycle caAcctPaycycle = new CaAcctPaycycle();
		caAcctPaycycle.setCreateDate(context.getCommitDate());
		caAcctPaycycle.setSoDate(context.getCommitDate());
		caAcctPaycycle.setSoNbr(context.getSoNbr());
		caAcctPaycycle.setAcctId(cycle.getAcctId());
		caAcctPaycycle.setPayCycle(cycle.getPayCycle());
		caAcctPaycycle.setRegionCode(cycle.getRegionCode());
		caAcctPaycycle.setValidDate(cycle.getValidDate());
		caAcctPaycycle.setExpireDate(cycle.getExpireDate());
		caAcctPaycycle.setRemark(cycle.getRemark());
		super.insert(caAcctPaycycle);
	}

	public void updatePayCycle(IAcctPaycycle cycle) {
		CaAcctPaycycle caAcctPaycycle = new CaAcctPaycycle();
		caAcctPaycycle.setCreateDate(context.getCommitDate());
		caAcctPaycycle.setSoDate(context.getCommitDate());
		caAcctPaycycle.setSoNbr(context.getSoNbr());
		caAcctPaycycle.setPayCycle(cycle.getPayCycle());
		caAcctPaycycle.setRegionCode(cycle.getRegionCode());
		caAcctPaycycle.setExpireDate(cycle.getExpireDate());
		caAcctPaycycle.setRemark(cycle.getRemark());
		this.updateByCondition(caAcctPaycycle, new DBCondition(CaAcctPaycycle.Field.acctId,cycle.getAcctId()));
		
	}

	public void deletePayCycle(IAcctPaycycle cycle) {
		this.deleteByCondition(CaAcctPaycycle.class, new DBCondition(CaAcctPaycycle.Field.acctId,cycle.getAcctId()));
	}

}
