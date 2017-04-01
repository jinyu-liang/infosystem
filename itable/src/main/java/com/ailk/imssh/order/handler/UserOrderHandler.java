package com.ailk.imssh.order.handler;

import java.util.ArrayList;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.order.cmp.UserOrderCmp;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.itable.entity.IUserOrder;

import jef.database.DataObject;

/**
 * @Description
 * @author dingjiang3
 * @Date 2013-11-26
 */
public class UserOrderHandler extends BaseHandler {

	private List<Long> userIdList = new ArrayList<Long>();
	private List<Long> userIdDiList = new ArrayList<Long>();

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<? extends DataObject> list = dataArr[0];
		if (CommonUtil.isEmpty(list)) {
			return;
		}
		UserOrderCmp cmp = this.getContext().getCmp(UserOrderCmp.class);
		RouterCmp routerCmp=this.context.getCmp(RouterCmp.class);
		for (int index = 0; index < list.size(); index++) {
			IUserOrder iUserOrder = (IUserOrder) list.get(index);
			Long acctId=routerCmp.queryAcctIdByUserId(iUserOrder.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			switch (iUserOrder.getOperType()) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				cmp.createUserOrder(iUserOrder);
				break;

			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				cmp.updateUserOrder(iUserOrder);
				break;

			case EnumCodeExDefine.OPER_TYPE_DELETE:
				cmp.deleteUserOrder(iUserOrder);
				break;

			default:
				break;
			}
			// 插入计费通知表 通知计费上发mdb 一个用户只添加一次
			if (!userIdList.contains(iUserOrder.getUserId())) {
				cmp.createIsmpOper(iUserOrder.getUserId(), iUserOrder.getRegionCode(),0);
				userIdList.add(iUserOrder.getUserId());
			}

		}
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<?
	 *      extends jef.database.DataObject>[])
	 */
	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) throws IMSException  {
		List<? extends DataObject> userOrderList = paramArr[0];
		if (CommonUtil.isEmpty(userOrderList)) {
			return;
		}
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		UserOrderCmp cmp = this.getContext().getCmp(UserOrderCmp.class);
		for (IUserOrder iUserOrder : (List<IUserOrder>)userOrderList ) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserOrder.getOperType()){
				continue;
			}
			ITableUtil.setValue2ContextHolder(null, null, iUserOrder.getUserId());
			baseCmp.checkUserRouter(iUserOrder.getUserId());
			cmp.deleteByConditionForDiff(CmUserOrder.class, new DBCondition(CmUserOrder.Field.resourceId, iUserOrder.getUserId()));
			ITableUtil.setValue2ContextHolder(null, null, iUserOrder.getUserId());
			cmp.createUserOrder(iUserOrder);
		}
		// 插入计费通知表 通知计费上发mdb 一个用户只添加一次
		 IUserOrder iUserOrder = (IUserOrder) userOrderList.get(0);
		 cmp.createIsmpOper(iUserOrder.getUserId(), iUserOrder.getRegionCode(),0);
		 userIdDiList.add(iUserOrder.getUserId());
		
	}

}
