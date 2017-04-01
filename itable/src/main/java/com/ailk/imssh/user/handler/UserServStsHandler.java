package com.ailk.imssh.user.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserServiceCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResServiceStatus;
import com.ailk.openbilling.persistence.itable.entity.IUserServStatus;

import jef.database.DataObject;

public class UserServStsHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<IUserServStatus> servStsList = (List<IUserServStatus>) dataArr[0];
		UserServiceCmp servCmp = context.getCmp(UserServiceCmp.class);
		RouterCmp cmp=this.context.getCmp(RouterCmp.class);
		for (IUserServStatus servSts : servStsList) {
			//设置分表参数
			Long acctId=cmp.queryAcctIdByUserId(servSts.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			int operType = servSts.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				servCmp.createUserServiceSts(servSts);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				servCmp.updateUserServiceSts(servSts);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				servCmp.deleteUserServiceSts(servSts);
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
	public void handleDiffException(List<? extends DataObject>[] paramArr) throws IMSException {
		List<IUserServStatus> servStsList = (List<IUserServStatus>) paramArr[0];
		UserServiceCmp servCmp = context.getCmp(UserServiceCmp.class);
		if (CommonUtil.isEmpty(servStsList)) {
			return;
		}
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		for (IUserServStatus iUserServStatus : servStsList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserServStatus.getOperType()){
				continue;
			}
			ITableUtil.setValue2ContextHolder(null, null, iUserServStatus.getUserId());
			baseCmp.checkUserRouter(iUserServStatus.getUserId());
			servCmp.deleteByConditionForDiff(CmResServiceStatus.class, 
					new DBCondition(CmResServiceStatus.Field.serviceInstId, iUserServStatus.getServiceInstId()));
			ITableUtil.setValue2ContextHolder(null, null, iUserServStatus.getUserId());
			servCmp.createUserServiceSts(iUserServStatus);
		}
		
	}

}
