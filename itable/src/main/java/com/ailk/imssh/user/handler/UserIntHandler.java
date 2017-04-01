package com.ailk.imssh.user.handler;

import java.util.ArrayList;
import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.openbilling.persistence.cust.entity.CmUserInt;
import com.ailk.openbilling.persistence.itable.entity.IUserInt;

public class UserIntHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr)
			throws IMSException {
		 List<? extends DataObject> dataList = dataArr[0];
		 //不处理物理库，直接放到缓存，等待上发mdb
		 if(CommonUtil.isNotEmpty(dataList)){
			 List<CmUserInt> list = new ArrayList<CmUserInt>();
			 for(DataObject data : dataList){
				 IUserInt iuserCust = (IUserInt)data;
				 CmUserInt userCust = buildUserCust(iuserCust);
				 list.add(userCust);
			 }
			 imsLogger.debug("put data into catch:",dataList.get(0).getClass().getName(),"size:",list.size());
			 context.cacheList(list);
		 }
	}
	
	 private CmUserInt buildUserCust(IUserInt iuserCust){
		 	CmUserInt userCust = new CmUserInt();
	    	userCust.setSerialNumber(iuserCust.getSerialNumber());
	    	userCust.setUserId(iuserCust.getUserId());
	    	userCust.setBrandCode(iuserCust.getBrandCode());
	    	userCust.setVipClassId(iuserCust.getVipClassId());
	    	userCust.setCustName(iuserCust.getCustName());
	    	userCust.setSex(iuserCust.getSex());
	    	userCust.setProductId(iuserCust.getProductId());
	    	userCust.setUserStateCodeset(iuserCust.getUserStateCodeset());
	    	userCust.setLastStopTime(iuserCust.getLastStopTime());
	    	userCust.setUpdateTime(iuserCust.getUpdateTime());
	    	userCust.setValidDate(iuserCust.getValidDate());
	    	userCust.setExpireDate(iuserCust.getExpireDate());
	    	return userCust;
	    	
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
