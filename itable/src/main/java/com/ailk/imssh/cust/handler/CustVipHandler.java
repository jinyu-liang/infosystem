package com.ailk.imssh.cust.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.cust.cmp.CustVipCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.itable.entity.ICustVip;

import jef.database.DataObject;

public class CustVipHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<ICustVip> vipList = (List<ICustVip>) dataArr[0];
		CustVipCmp vipCmp = context.getCmp(CustVipCmp.class);
		RouterCmp baseCmp=this.context.getCmp(RouterCmp.class);
		for (ICustVip vip : vipList) {
			Long acctId=baseCmp.queryAcctIdByUserId(vip.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			int operType = vip.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				vipCmp.createCustVip(vip);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				vipCmp.updateCustVip(vip);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				vipCmp.deleteCustVip(vip);
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
		List<ICustVip> vipList = (List<ICustVip>) paramArr[0];
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		if(CommonUtil.isEmpty(vipList)){
	           return;			
			}
		CustVipCmp vipCmp = context.getCmp(CustVipCmp.class);
		for (ICustVip vip : vipList) {
			int operType = vip.getOperType();
			if(EnumCodeExDefine.OPER_TYPE_DIFF != operType){
				continue;
			}
			 baseCmp.checkUserRouter(vip.getUserId());
				ITableUtil.setValue2ContextHolder(null, null, vip.getUserId());
				vipCmp.deleteByConditionForDiff(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, vip.getUserId()));
				ITableUtil.setValue2ContextHolder(null, null, vip.getUserId());
				vipCmp.createCustVip(vip);
		}
	}
}
