package com.ailk.imssh.acct.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.acct.cmp.AcctOweDoneCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.itable.entity.IOweCustomDone;

public class AcctOweDoneHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		AcctOweDoneCmp oweCmp = context.getCmp(AcctOweDoneCmp.class);
		
		List<? extends DataObject> dataList = dataArr[0];
		for (int j = 0; j < dataList.size(); j++) {
			IOweCustomDone owe = (IOweCustomDone) dataList.get(j);
			ITableUtil.setValue2ContextHolder(null, owe.getAcctId(), owe.getUserId());
			int operType = owe.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT: 
				oweCmp.createOweCustomDone(owe);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				oweCmp.deleteOweCustomDone(owe);
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
