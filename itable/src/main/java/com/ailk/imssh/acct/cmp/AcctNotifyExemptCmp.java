package com.ailk.imssh.acct.cmp;

import java.util.Collections;
import java.util.Date;
import java.util.List;



import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.DbUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.itable.entity.INotifyExempt;

/**
 * @Description:免催免停信息
 * @author zenglu
 * @Date 2012-09-20
 */
public class AcctNotifyExemptCmp extends BaseCmp
{
    
    public void beforeHandler(List<? extends DataObject> dataList)
    {
        INotifyExempt iNotifyExempt = (INotifyExempt) dataList.get(0);
        Date expireDate = iNotifyExempt.getCommitDate();
        Long objectId = iNotifyExempt.getUserId();
        Integer objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        if(objectId == null || objectId == 0){
           objectId = iNotifyExempt.getAcctId();
           objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;        
        }
        
        delete_noValid(CaNotifyExempt.class, new DBCondition(
                CaNotifyExempt.Field.objectId, objectId), new DBCondition(CaNotifyExempt.Field.objectType, objectType),
                new DBCondition(CaNotifyExempt.Field.validDate, expireDate, Operator.GREAT), new DBCondition(
                        CaNotifyExempt.Field.expireDate, expireDate, Operator.GREAT));
    }
    
    public void insertNotifyExempt(INotifyExempt iNotifyExempt){
    	CaNotifyExempt caNotifyExempt = createNotifyExempt(iNotifyExempt,false);
    	super.insert(caNotifyExempt);
    }
    
    private CaNotifyExempt createNotifyExempt(INotifyExempt iNotifyExempt,boolean isUpdate)
    {
        CaNotifyExempt caNotifyExempt = new CaNotifyExempt();
        caNotifyExempt.setCreateDate(context.getCommitDate());
        caNotifyExempt.setSoDate(context.getCommitDate());
        caNotifyExempt.setSoNbr(context.getSoNbr());
        if(isUpdate){
        	 caNotifyExempt.setValidDate(context.getRequestDate());
        }else{
        	caNotifyExempt.setValidDate(iNotifyExempt.getValidDate());
        }
        
        caNotifyExempt.setExpireDate(iNotifyExempt.getExpireDate());
        // 区分用户级还是账户级
        if (null != iNotifyExempt.getUserId()&& iNotifyExempt.getUserId().longValue() != 0)
        {
            caNotifyExempt.setObjectId(iNotifyExempt.getUserId());
            caNotifyExempt.setObjectType(EnumCodeExDefine.PROD_OBJECTTYPE_DEV);
        }
        else
        {
            caNotifyExempt.setObjectId(iNotifyExempt.getAcctId());
            caNotifyExempt.setObjectType(EnumCodeExDefine.PROD_OBJECTTYPE_ACCOUNT);
        }
        caNotifyExempt.setRecType(iNotifyExempt.getRecType());
        caNotifyExempt.setExemptionType(this.getDBExemptionType(iNotifyExempt.getUrgeStopFlag()));
        caNotifyExempt.setExempChannelId(0);
        // 免催免停表增加bill_type字段 默认1 //不是预后付费的含义
        caNotifyExempt.setBillingType(EnumCodeExDefine.NOTIFY_EXEMPTION_BILLING_TYPE_POSTPAID);
        // 免催免停表增加office_phone字段
        caNotifyExempt.setOfficePhone(iNotifyExempt.getOfficePhone());
        
        return caNotifyExempt;
    }

    /**
     * 删除免催免停信息
     * 
     * @Author: wukl
     * @Date: 2012-11-12
     * @param iNotifyExempt
     */
    public void deleteNotifyExempt(INotifyExempt iNotifyExempt)
    {
          Long objectId = iNotifyExempt.getUserId();
          if(objectId == null || objectId.longValue() == 0){
              objectId = iNotifyExempt.getAcctId();
          }
        
        this.deleteByCondition(CaNotifyExempt.class, new DBCondition(
                CaNotifyExempt.Field.objectId, objectId), new DBCondition(CaNotifyExempt.Field.recType, iNotifyExempt.getRecType()));
    }

    /**
     * 修改免催免停信息
     * 
     * @Author: wukl
     * @Date: 2012-11-12
     * @param iNotifyExempt
     */
    public void updateNotifyExempt(INotifyExempt iNotifyExempt)
    {
        CaNotifyExempt caNotifyExempt = new CaNotifyExempt();
        caNotifyExempt.setSoDate(context.getCommitDate());
        caNotifyExempt.setSoNbr(context.getSoNbr());
        caNotifyExempt.setExpireDate(iNotifyExempt.getExpireDate());
        // 区分用户级还是账户级
        if (null != iNotifyExempt.getUserId() && iNotifyExempt.getUserId().longValue() != 0)
        {
            caNotifyExempt.setObjectId(iNotifyExempt.getUserId());
            caNotifyExempt.setObjectType(EnumCodeExDefine.PROD_OBJECTTYPE_DEV);
        }
        else
        {
            caNotifyExempt.setObjectId(iNotifyExempt.getAcctId());
            caNotifyExempt.setObjectType(EnumCodeExDefine.PROD_OBJECTTYPE_ACCOUNT);
        }
        caNotifyExempt.setExemptionType(this.getDBExemptionType(iNotifyExempt.getUrgeStopFlag()));
        caNotifyExempt.setOfficePhone(iNotifyExempt.getOfficePhone());
        List<CaNotifyExempt> updateList = super.updateByCondition(caNotifyExempt, new DBCondition(CaNotifyExempt.Field.objectId, caNotifyExempt.getObjectId()),new DBCondition(CaNotifyExempt.Field.recType, iNotifyExempt.getRecType()));
        if(CommonUtil.isEmpty(updateList)){
        	imsLogger.debug("===========================no vaild CaNotifyExempt,begin insert operate============================");
        	CaNotifyExempt newCaNotifyExempt = createNotifyExempt(iNotifyExempt,true);
        	super.insert(newCaNotifyExempt);
        }

    }
    
    private Integer getDBExemptionType(String UrgeStopFlag) {
		// 截取前十位,将其转成十进制数值返回,
		return Integer.valueOf(UrgeStopFlag.substring(0, 10).toString(), 2);
	} 
}
