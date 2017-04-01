package com.ailk.imssh.cust.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.cust.cmp.CustGroupCmp;
import com.ailk.openbilling.persistence.cust.entity.CmCustGroup;
import com.ailk.openbilling.persistence.itable.entity.ICustGroup;

import jef.database.DataObject;

public class CustGroupHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<ICustGroup> groupList = (List<ICustGroup>) dataArr[0];
		CustGroupCmp custGroupCmp = context.getCmp(CustGroupCmp.class);
		for (ICustGroup group : groupList) {
			//该表不分表
			int operType = group.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				custGroupCmp.createCustGroup(group);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				custGroupCmp.updateCustGroup(group);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				custGroupCmp.deleteCustGroup(group);
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
	public void handleDiffException(List<? extends DataObject>[] paramArr) throws IMSException{
		List<ICustGroup> dataList = (List<ICustGroup>)paramArr[0];
		if(CommonUtil.isEmpty(dataList)){
	           return;			
			}
		CustGroupCmp custGroupCmp = context.getCmp(CustGroupCmp.class);
		for (ICustGroup iCustGroup : dataList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iCustGroup.getOperType()){
				continue;
			}
			custGroupCmp.deleteByConditionForDiff(CmCustGroup.class, new DBCondition(CmCustGroup.Field.custId, iCustGroup.getCustId()));
			custGroupCmp.createCustGroup(iCustGroup);
		}
		
	}

}
