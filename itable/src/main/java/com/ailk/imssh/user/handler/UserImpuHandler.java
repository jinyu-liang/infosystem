package com.ailk.imssh.user.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserImpuCmp;
import com.ailk.openbilling.persistence.cust.entity.CmUserImpu;
import com.ailk.openbilling.persistence.itable.entity.IUserImpu;

import jef.database.DataObject;

public class UserImpuHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<IUserImpu> impuList = (List<IUserImpu>) dataArr[0];
		UserImpuCmp impuCmp = context.getCmp(UserImpuCmp.class);
		RouterCmp cmp=this.context.getCmp(RouterCmp.class);
		for (IUserImpu impu : impuList) {
			Long acctId=cmp.queryAcctIdByUserId(impu.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			int operType = impu.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				impuCmp.createUserImpu(impu);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				impuCmp.updateUserImpu(impu);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				impuCmp.deleteUserImpu(impu);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {

	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) throws IMSException {
		List<IUserImpu> impuList = (List<IUserImpu>) paramArr[0];
		if(CommonUtil.isEmpty(impuList)){
	           return;			
		 }
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		UserImpuCmp impuCmp = context.getCmp(UserImpuCmp.class);
		for (IUserImpu iUserImpu : impuList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserImpu.getOperType()){
				continue;
			}
			    baseCmp.checkUserRouter(iUserImpu.getUserId());
				ITableUtil.setValue2ContextHolder(null, null, iUserImpu.getUserId());
					impuCmp.deleteByConditionForDiff(CmUserImpu.class, new DBCondition(CmUserImpu.Field.resourceId, iUserImpu.getUserId()), new DBCondition(
							CmUserImpu.Field.impuType, iUserImpu.getImpuType()));
				ITableUtil.setValue2ContextHolder(null, null, iUserImpu.getUserId());
				impuCmp.createUserImpu(iUserImpu);
		}
		
	}

}
