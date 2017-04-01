package com.ailk.imssh.user.cmp;

import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.itable.entity.IUserParam;

public class UserParamCmp extends BaseCmp {
	/**
	 * 新增一卡双芯资料 .
	 * 
	 * @param iUserMap
	 *            表映射对象实体 .
	 */
	public void createUserParam(IUserParam param) {

		CmUserParam dmParam = convertUserParam(param,true);

		super.insertWithAllCache(dmParam,new DBCondition(CmUserParam.Field.resourceId,param.getUserId()));
	}
	
	private CmUserParam convertUserParam(IUserParam param,boolean flag){
		CmUserParam dmParam = new CmUserParam();
		dmParam.setRegionCode(param.getRegionCode());
		dmParam.setSoNbr(context.getSoNbr());
		dmParam.setSoDate(context.getCommitDate());
		dmParam.setCreateDate(context.getCommitDate());
		dmParam.setExpireDate(param.getExpireDate());
		dmParam.setParamValue(param.getParamValue());
		if(flag){
			dmParam.setParamId(param.getParamId());
			dmParam.setResourceId(param.getUserId());
			dmParam.setValidDate(param.getValidDate());

		}
		return dmParam;
	}

	/**
	 * 修改一卡双芯资料 .
	 * 
	 * @param iUserMap
	 *            表映射对象实体 .
	 */
	public void modifyUserParam(IUserParam param) {

		CmUserParam dmParam = convertUserParam(param,false);
		
		if(param.getParamId() == 630000){
			CmUserParam dmParamMode1 = convertUserParam(param,true);
			super.updateMode1(dmParamMode1, new DBCondition(CmUserParam.Field.resourceId, param.getUserId()), new DBCondition(
					CmUserParam.Field.paramId,630000));
		}else{
			super.updateMode2(dmParam,EnumCodeExDefine.OPER_TYPE_UPDATE,param.getValidDate(), new DBCondition(CmUserParam.Field.resourceId, param.getUserId()), new DBCondition(
					CmUserParam.Field.paramId,param.getParamId()));
		}
		
	}

	/**
	 * 删除一卡双芯资料 ,根据UserId .
	 * 
	 * @param iUserMap
	 *            表映射对象实体 .
	 */
	public void deleteUserParam(IUserParam param) {
		
		Date expireDate = getNextMonthBegin(param.getExpireDate());
		CmUserParam dmParam = new CmUserParam();
		dmParam.setValidDate(param.getValidDate());
		if(EnumCodeExDefine.ACCT_PARAM_ID==param.getParamId()){
			this.deleteMode1(CmUserParam.class,expireDate, new DBCondition(CmUserParam.Field.resourceId, param.getUserId()), new DBCondition(
					CmUserParam.Field.paramId, EnumCodeExDefine.ACCT_PARAM_ID));
		}else{
			this.updateMode2(dmParam, EnumCodeExDefine.OPER_TYPE_DELETE,param.getValidDate(), new DBCondition(CmUserParam.Field.resourceId, param.getUserId()), new DBCondition(
					CmUserParam.Field.paramId,param.getParamId()));
		}
		
	}
}
