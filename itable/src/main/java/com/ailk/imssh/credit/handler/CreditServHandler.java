package com.ailk.imssh.credit.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.credit.cmp.CreditServCmp;
import com.ailk.openbilling.persistence.itable.entity.ICreditServ;

/**
 * @Description:用户信用服务
 * @author yuxz
 * @Date 2016-5-23
 */
public class CreditServHandler extends BaseHandler {

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handle(List<? extends DataObject>... dataArr) {

		// TODO Auto-generated method stub
		List<? extends DataObject> dataList = dataArr[0];
		RouterCmp routeCmp = context.getCmp(RouterCmp.class);
		CreditServCmp creditServCmp = this.getContext().getCmp(CreditServCmp.class);
		for (int j = 0; j < dataList.size(); j++) {
			ICreditServ iCreditServ = (ICreditServ) dataList.get(j);
			long acctId = routeCmp.queryAcctIdByUserId(iCreditServ.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			int operType = iCreditServ.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				creditServCmp.createCreditServ(iCreditServ);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				creditServCmp.modifyCreditServ(iCreditServ);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				creditServCmp.deleteCreditServ(iCreditServ);
				break;
			default:
				break;
			}
			creditServCmp.insertCassetSyn(iCreditServ);
		}
	
	}

    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException {
    	
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
