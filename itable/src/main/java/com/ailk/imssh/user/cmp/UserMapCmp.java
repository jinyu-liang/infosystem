package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.itable.entity.IUserMap;

import jef.database.Condition.Operator;

/**
 * 一卡双芯资料,新增、修改、删除 .
 * 
 * @author chenxd .
 * @date 2013-12-05
 */
public class UserMapCmp extends BaseCmp {

	/**
	 * 新增一卡双芯资料 .
	 * 
	 * @param iUserMap
	 *            表映射对象实体 .
	 */
	public final void createUserMap(final IUserMap iUserMap) {

		final CmUserMap cmUserMap = new CmUserMap();
		cmUserMap.setRegionCode(EnumCodeExDefine.TJ_REGION_CODE);
		cmUserMap.setBusiType(iUserMap.getBusiType());
		cmUserMap.setImsi(iUserMap.getImsi());
		cmUserMap.setImsi2(iUserMap.getImsi2());
		cmUserMap.setMsisdn(iUserMap.getMsisdn());
		cmUserMap.setMsisdn2(iUserMap.getMsisdn2());
		cmUserMap.setResourceId(iUserMap.getUserId());
		cmUserMap.setSoNbr(context.getSoNbr());
		cmUserMap.setSoDate(iUserMap.getCommitDate());
		cmUserMap.setCreateDate(iUserMap.getCommitDate());
		cmUserMap.setExpireDate(iUserMap.getExpireDate());
		cmUserMap.setValidDate(iUserMap.getValidDate());

		super.insert(cmUserMap);
	}

	/**
	 * 获取区域编码 .
	 * 
	 * @param userid
	 *            资源编码
	 * @return 区域编码
	 */
	private Integer getRegoinCode(Long userid) {
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		RouteResult result = routCmp.queryUserRouter(userid);
		return result.getRouteDimension().getRegionCode();

	}

	/**
	 * 修改一卡双芯资料 .
	 * 
	 * @param iUserMap
	 *            表映射对象实体 .
	 */
	public final void modifyUserMap(final IUserMap iUserMap) {

		final CmUserMap cmUserMap = new CmUserMap();
		cmUserMap.setRegionCode(EnumCodeExDefine.TJ_REGION_CODE);
		cmUserMap.setBusiType(iUserMap.getBusiType());
		cmUserMap.setImsi(iUserMap.getImsi());
		cmUserMap.setImsi2(iUserMap.getImsi2());
		cmUserMap.setMsisdn(iUserMap.getMsisdn());
		cmUserMap.setSoNbr(context.getSoNbr());
		cmUserMap.setSoDate(iUserMap.getCommitDate());
		cmUserMap.setCreateDate(iUserMap.getCommitDate());
		cmUserMap.setExpireDate(iUserMap.getExpireDate());
		this.updateMode2(cmUserMap,EnumCodeExDefine.OPER_TYPE_UPDATE,iUserMap.getValidDate(),
				new DBCondition(CmUserMap.Field.resourceId, iUserMap.getUserId()), 
				new DBCondition(CmUserMap.Field.msisdn2, iUserMap.getMsisdn2()));
		
	}

	/**
	 * 删除一卡双芯资料 ,根据UserId .
	 * 
	 * @param iUserMap
	 *            表映射对象实体 .
	 */
	public final void deleteUserMap(final IUserMap iUserMap) {
		CmUserMap cmUserMap = new CmUserMap();
		cmUserMap.setExpireDate(iUserMap.getExpireDate());
		this.updateMode2(cmUserMap,EnumCodeExDefine.OPER_TYPE_DELETE,iUserMap.getValidDate(),
				new DBCondition(CmUserMap.Field.resourceId, iUserMap.getUserId()), 
				new DBCondition(CmUserMap.Field.msisdn2, iUserMap.getMsisdn2()));
	}
}
