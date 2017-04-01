package com.ailk.imssh.acct.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.acct.cmp.AcctPayCycleCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.itable.entity.IAcctPaycycle;

public class AcctPayCycleHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<IAcctPaycycle> cycleList = (List<IAcctPaycycle>) dataArr[0];
		AcctPayCycleCmp cycleCmp = context.getCmp(AcctPayCycleCmp.class);
		for (IAcctPaycycle cycle : cycleList) {
			
			ITableUtil.setValue2ContextHolder(null, cycle.getAcctId(), null);
			int operType = cycle.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				cycleCmp.createPayCycle(cycle);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				cycleCmp.updatePayCycle(cycle);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				cycleCmp.deletePayCycle(cycle);
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
