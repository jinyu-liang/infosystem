package com.ailk.imssh.user.handler;

import java.util.ArrayList;
import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.openbilling.persistence.cust.entity.CmUserSvcInt;
import com.ailk.openbilling.persistence.itable.entity.IUserSvcInt;

public class UserSvcIntHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr)
			throws IMSException {
		 List<? extends DataObject> dataList = dataArr[0];
		 //不处理物理库，直接放到缓存，等待上发mdb
		 if(CommonUtil.isNotEmpty(dataList)){
			 List<CmUserSvcInt> list = new ArrayList<CmUserSvcInt>();
			 for(DataObject data : dataList){
				 IUserSvcInt userSvc = (IUserSvcInt)data;
				 CmUserSvcInt cmUserSvc = createUserSvc(userSvc);
				 list.add(cmUserSvc);
			 }
			 imsLogger.debug("put data into catch:",dataList.get(0).getClass().getName(),"size:",list.size());
			 context.cacheList(list);
		 }

	}
	
	private CmUserSvcInt createUserSvc(IUserSvcInt userSvcInt){
		CmUserSvcInt cmUserSvc = new CmUserSvcInt();
		cmUserSvc.setUserId(userSvcInt.getUserId());
		cmUserSvc.setUserIdA(userSvcInt.getUserIdA());
		cmUserSvc.setPackageId(userSvcInt.getPackageId());
		cmUserSvc.setMainTag(userSvcInt.getMainTag());
		cmUserSvc.setServiceId(userSvcInt.getServiceId());
		cmUserSvc.setStartDate(userSvcInt.getStartDate());
		cmUserSvc.setEndDate(userSvcInt.getEndDate());
		cmUserSvc.setUpdateTime(userSvcInt.getUpdateTime());
		cmUserSvc.setValidDate(userSvcInt.getValidDate());
		cmUserSvc.setExpireDate(userSvcInt.getExpireDate());
		return cmUserSvc;
	}

	
	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr)
			throws IMSException {
		// TODO Auto-generated method stub

	}

}
