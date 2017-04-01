package com.ailk.imssh.user.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserServiceCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResService;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.itable.entity.IUserService;

import jef.database.DataObject;

public class UserServiceHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<IUserService> servList = (List<IUserService>) dataArr[0];
		UserServiceCmp servCmp = context.getCmp(UserServiceCmp.class);
		for (IUserService service : servList) {
	        setValueContext(service);		
			int operType = service.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				servCmp.createUserService(service);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				servCmp.updateUserService(service);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				servCmp.deleteUserService(service);
				break;
			default:
				break;
			}
		}

	}

	/**
	 * 设置分表参数
	 * @param service
	 */
	private void setValueContext(IUserService service) {
		if(service.getUserId() !=null && service.getUserId() !=0){
			RouterCmp cmp=this.context.getCmp(RouterCmp.class);
			Long acctId=cmp.queryAcctIdByUserId(service.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
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
		List<IUserService> servList = (List<IUserService>) paramArr[0];
		UserServiceCmp servCmp = context.getCmp(UserServiceCmp.class);
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		for (IUserService iUserService : servList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserService.getOperType()){
				continue;
			}
            //判断user_id是否已发布路由	
			    baseCmp.checkUserRouter(iUserService.getUserId());
				setValueContext(iUserService);
				servCmp.deleteByConditionForDiff(CmResService.class, new DBCondition(CmResService.Field.serviceInstId, iUserService.getServiceInstId()));
				setValueContext(iUserService);
				servCmp.createUserService(iUserService);
     		  }
			}
     	}
