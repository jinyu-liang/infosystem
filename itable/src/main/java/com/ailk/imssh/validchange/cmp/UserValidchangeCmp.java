package com.ailk.imssh.validchange.cmp;

import com.ailk.ims.common.DBCondition;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.cust.entity.CmUserValidchange;
import com.ailk.openbilling.persistence.itable.entity.IUserValidchange;

public class UserValidchangeCmp extends BaseCmp {
	
	
	public  void operateValidchange(IUserValidchange  iUserValidchange){
		
		int  operType =iUserValidchange.getOperType();
		Long userId  = iUserValidchange.getUserId();
		switch(operType) 
		{
		  case EnumCodeExDefine.OPER_TYPE_INSERT:
			  this.insert(createValidChange(iUserValidchange,false));
			  break;
		  case EnumCodeExDefine.OPER_TYPE_UPDATE:
//			  this.updateByCondition(createValidChange(iUserValidchange,true),
//					  new DBCondition(CmUserValidchange.Field.userId, userId));
			  this.updateMode2(createValidChange(iUserValidchange,false), EnumCodeExDefine.OPER_TYPE_UPDATE, iUserValidchange.getValidDate(), 
					  new DBCondition(CmUserValidchange.Field.userId, userId));
			  
			  break;
		  case EnumCodeExDefine.OPER_TYPE_DELETE:
				this.deleteByCondition(CmUserValidchange.class, 
						new DBCondition(CmUserValidchange.Field.userId, userId));
			  break;
			  default:
				  break;
		
		}
		
	 }
	
	/**
	 * 插入或修改缓存I表数据
	 * @param iUserValidchange
	 * @param isUpdate
	 */
	private CmUserValidchange createValidChange(IUserValidchange iUserValidchange,boolean isUpdate) {
		
		CmUserValidchange  validChange =  new CmUserValidchange();
			 validChange.setUserId(iUserValidchange.getUserId());
			 if(!isUpdate){
				 
				 validChange.setValidDate(iUserValidchange.getValidDate());
				 validChange.setExpireDate(iUserValidchange.getExpireDate());
			 }
			 
			 validChange.setUpdateStaffId(iUserValidchange.getUpdateStaffId());
			 validChange.setUpdateDepartId(iUserValidchange.getUpdateDepartId());
			 validChange.setRemark(iUserValidchange.getRemark());
			 //预留字段
			 validChange.setRsrvNum1(iUserValidchange.getRsrvNum1());
			 validChange.setRsrvNum2(iUserValidchange.getRsrvNum2());
			 validChange.setRsrvNum3(iUserValidchange.getRsrvNum3());
			 validChange.setRsrvNum4(iUserValidchange.getRsrvNum4());
			 validChange.setRsrvNum5(iUserValidchange.getRsrvNum5());
			 
			 validChange.setRsrvStr1(iUserValidchange.getRsrvStr1());
			 validChange.setRsrvStr2(iUserValidchange.getRsrvStr2());
			 validChange.setRsrvStr3(iUserValidchange.getRsrvStr3());
			 validChange.setRsrvStr4(iUserValidchange.getRsrvStr4());
			 validChange.setRsrvStr5(iUserValidchange.getRsrvStr5());
			 
			 validChange.setRsrvDate1(iUserValidchange.getRsrvDate1());
			 validChange.setRsrvDate2(iUserValidchange.getRsrvDate2());
			 validChange.setRsrvDate3(iUserValidchange.getRsrvDate3());
			 
			 validChange.setRsrvTag1(iUserValidchange.getRsrvTag1());
			 validChange.setRsrvTag2(iUserValidchange.getRsrvTag2());
			 validChange.setRsrvTag3(iUserValidchange.getRsrvTag3());
		 
			 validChange.setSoDate(context.getCommitDate());
			 validChange.setUpdateTime(iUserValidchange.getUpdateTime());
	         validChange.setSoNbr(context.getSoNbr());
	        
	         return validChange;
	 }
	
 }
