package com.ailk.imssh.acct.handler;

import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.acct.cmp.AcctCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.itable.entity.IAccount;

/**
 * @Description:账户基本信息
 * @author caohm5
 * @Date 2012-5-11
 */
public class AcctHandler extends BaseHandler {

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		
		List<IAccount> dataList = (List<IAccount>) dataArr[0];
		if (CommonUtil.isEmpty(dataList)) {
			return;
		}
		AcctCmp acctCmp = this.getContext().getCmp(AcctCmp.class);
		Date validDate = DateUtils.monthBegin(context.getCommitDate());
		for (IAccount acct : dataList) {
			if (acct.getValidDate().before(validDate)) {
				acct.setValidDate(validDate);
			}
		}
		ITableUtil.setValue2ContextHolder(null, context.getAcctId(), context.getUserId());
		acctCmp.deleteByCondition_noInsert(CaAccount.class, validDate, new DBCondition(CaAccount.Field.acctId, context.getAcctId()),
				new DBCondition(CaAccount.Field.expireDate, validDate, Operator.GREAT));

	}

	@Override
	public void handle(List<? extends DataObject>... dataArr) {

		List<? extends DataObject> dataList = dataArr[0];

		AcctCmp acctCmp = this.getContext().getCmp(AcctCmp.class);

		//acctCmp.beforeHandle(dataList);

		for (int j = 0; j < dataList.size(); j++) {
			IAccount iAccount = (IAccount) dataList.get(j);
			ITableUtil.setValue2ContextHolder(null, iAccount.getAcctId(), null);
			int operType = iAccount.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT: {
				List<CaAccount> dmList = acctCmp.query(CaAccount.class, new DBCondition(CaAccount.Field.acctId, iAccount.getAcctId()));
				if (CommonUtil.isNotEmpty(dmList)) {
					acctCmp.updateAccount(iAccount);
				} else {
					acctCmp.createAccount(iAccount);
				}
			}
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				
				acctCmp.updateAccount(iAccount);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				acctCmp.deleteAccount(iAccount);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO 发布路由不处理
		
	}

}
