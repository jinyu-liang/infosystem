package com.ailk.imssh.credit.handler;

import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.credit.cmp.CreditCmp;
import com.ailk.openbilling.persistence.itable.entity.ICredit;

/**
 * @Description:用户账户信用度
 * @author caohm5
 * @Date 2012-5-11
 */
public class CreditHandler extends BaseHandler {

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(List<? extends DataObject>... dataArr) {
		 List<ICredit> creditList = (List<ICredit>) dataArr[0];
		 CreditCmp creditCmp = this.getContext().getCmp(CreditCmp.class);
		 creditCmp.operateCredit(creditList);
	}

    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
