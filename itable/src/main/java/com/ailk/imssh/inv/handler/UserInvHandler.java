package com.ailk.imssh.inv.handler;

import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.inv.cmp.UserInvCmp;
import com.ailk.openbilling.persistence.cust.entity.CaResourceInv;
import com.ailk.openbilling.persistence.itable.entity.IUserInv;

/**
 * @Description
 * @author qaoqc5
 * @Date 2012-11-9
 */
public class UserInvHandler extends BaseHandler {

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		List<IUserInv> list = (List<IUserInv>) dataArr[0];
		if (CommonUtil.isEmpty(list)) {
			return;
		}
		UserInvCmp cmp = this.getContext().getCmp(UserInvCmp.class);
		Date validDate = DateUtils.monthBegin(context.getCommitDate());
		if (CommonUtil.isNotEmpty(list)) {
			for (IUserInv user : list) {
				if (user.getValidDate().before(validDate)) {
					user.setValidDate(validDate);
				}
			}
		}
		ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
		cmp.deleteByCondition_noInsert(CaResourceInv.class, validDate,
				new DBCondition(CaResourceInv.Field.resourceId, context.getUserId()), new DBCondition(CaResourceInv.Field.expireDate,
						validDate, Operator.GREAT));

	}

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<? extends DataObject> list = dataArr[0];
		UserInvCmp cmp = this.getContext().getCmp(UserInvCmp.class);
		cmp.beforeHandle(list);
		for (int index = 0; index < list.size(); index++) {
			IUserInv userInv = (IUserInv) list.get(index);
			switch (userInv.getOperType()) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				cmp.createUserInv(userInv);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				cmp.updateUserInv(userInv);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				cmp.deleteUserInv(userInv);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<?
	 *      extends jef.database.DataObject>[])
	 */
	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
