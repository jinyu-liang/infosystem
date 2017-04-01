package com.ailk.imssh.validchange.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.validchange.cmp.UserValidchangeCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserValidchange;

public class UserValidchangeHandler  extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr)
			throws IMSException {
	
		List<? extends DataObject> list = dataArr[0];
		if(CommonUtil.isEmpty(list)){
			return;
		}
		UserValidchangeCmp validchangeCmp = this.getContext().getCmp(UserValidchangeCmp.class);
		 RouterCmp routeCmp = context.getCmp(RouterCmp.class);
		for (int i = 0; i < list.size(); i++) {
			IUserValidchange iUserValidchange = (IUserValidchange) list.get(i);
			Long acctId=routeCmp.queryAcctIdByUserId(iUserValidchange.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId,null);
			validchangeCmp.operateValidchange(iUserValidchange);
		}
		
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
