package com.ailk.imssh.user.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserReopenCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserReopen;

public class UserReopenHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		UserReopenCmp userReopenCmp = context.getCmp(UserReopenCmp.class);
		List<IUserReopen> reopenList = (List<IUserReopen>) dataArr[0];
		for (IUserReopen iUserReopen : reopenList) {
			ITableUtil.setValue2ContextHolder(null,null,iUserReopen.getUserId());
			int operType = iUserReopen.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				userReopenCmp.createUserReopen(iUserReopen);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
            	userReopenCmp.updateUserReopen(iUserReopen);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
            	userReopenCmp.deleteUserReopen(iUserReopen);
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
		// TODO Auto-generated method stub
		
	}

}
