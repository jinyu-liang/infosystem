package com.ailk.imssh.user.handler;

import java.util.List;

import com.ailk.easyframe.route.entity.br.RouterIdentity;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserMapCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserMap;

import jef.database.DataObject;

/**
 * 一卡双芯资料,新增、修改、删除 .
 * 
 * @author chenxd
 * @date 2013-12-05
 */
public class UserMapHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub
		List<? extends DataObject> dataList = dataArr[0];

		UserMapCmp userMapCmp = this.getContext().getCmp(UserMapCmp.class);
		RouterCmp cmp = this.getContext().getCmp(RouterCmp.class);
		for (int j = 0; j < dataList.size(); j++) {
			IUserMap iUserMap = (IUserMap) dataList.get(j);
			Long acctId=cmp.queryAcctIdByUserId(iUserMap.getUserId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			int operType = iUserMap.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				userMapCmp.createUserMap(iUserMap);
				cmp.createUserVCardRouter(iUserMap.getUserId(), iUserMap.getMsisdn2(), null, iUserMap.getValidDate(),
						iUserMap.getExpireDate());
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				userMapCmp.modifyUserMap(iUserMap);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				userMapCmp.deleteUserMap(iUserMap);
				cmp.deleteUserVCardRouter(iUserMap.getUserId(), iUserMap.getMsisdn2(), null, iUserMap.getExpireDate());
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
		//发布路由不处理
		imsLogger.info("**********************************");
		
	}

}
