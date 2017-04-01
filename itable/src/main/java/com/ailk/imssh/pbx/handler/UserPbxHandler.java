package com.ailk.imssh.pbx.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.pbx.cmp.UserPbxCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserPbx;

/**
 * @Description
 * @author dingjiang3
 * @Date 2013-11-26
 */
public class UserPbxHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<? extends DataObject> list = dataArr[0];
		UserPbxCmp cmp = this.getContext().getCmp(UserPbxCmp.class);
		RouterCmp routerCmp = this.getContext().getCmp(RouterCmp.class);
		// cmp.beforeHandle(list);
		for (int index = 0; index < list.size(); index++) {
			IUserPbx userPbx = (IUserPbx) list.get(index);
			ITableUtil.setValue2ContextHolder(null, null, userPbx.getUserId());
			switch (userPbx.getOperType().intValue()) {
			case EnumCodeExDefine.OPER_TYPE_INSERT: 
				cmp.createUserPbx(userPbx);
				routerCmp.publicPbxRoute(userPbx);
				break;
			
			case EnumCodeExDefine.OPER_TYPE_UPDATE: 
				cmp.updateUserPbx(userPbx);
				break;

			case EnumCodeExDefine.OPER_TYPE_DELETE: 
				cmp.deleteUserPbx(userPbx);
				routerCmp.publicPbxRoute(userPbx);
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
