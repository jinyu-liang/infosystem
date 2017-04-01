package com.ailk.imssh.contact.handler;

import java.util.Date;
import java.util.List;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.contact.cmp.ContactCmp;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.itable.entity.IContact;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

/**
 * 联系人信息组件<br>
 * @Description
 * @author wukl
 * @Date 2012-6-13
 */
public class ContactHandler extends BaseHandler
{
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
    	
    	ContactCmp contactCmp = this.getContext().getCmp(ContactCmp.class);
        List<IContact> contactList = (List<IContact>) dataArr[0];
        ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
        
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
         if (CommonUtil.isNotEmpty(contactList))
         {
             for (IContact contact : contactList)
             {
                 if (contact.getValidDate().before(validDate)) {
                     contact.setValidDate(validDate);
                 }
                 if (contact.getUserId() != null && contact.getUserId() != 0) {
                	 contactCmp.deleteByCondition_noInsert(CmContactMedium.class, validDate, new DBCondition(CmContactMedium.Field.objectId, contact.getUserId()),
                			 new DBCondition(CmContactMedium.Field.objectType,EnumCodeExDefine.USER_CONTACT),
                			 new DBCondition(CmContactMedium.Field.contactType,contact.getContactType()),
                             new DBCondition(CmResource.Field.expireDate, validDate, Operator.GREAT));
                     
                 } else if (contact.getAcctId() != null && contact.getAcctId() != 0) {
             		 contactCmp.deleteByCondition_noInsert(CmContactMedium.class, validDate, new DBCondition(CmContactMedium.Field.objectId, contact.getAcctId()),
             				new DBCondition(CmContactMedium.Field.objectType,EnumCodeExDefine.ACCT_CONTACT),
             				new DBCondition(CmContactMedium.Field.contactType,contact.getContactType()),
                            new DBCondition(CmResource.Field.expireDate, validDate, Operator.GREAT));

                 } else if (contact.getCustId() != null && contact.getCustId() != 0) {
                	 contactCmp.deleteByCondition_noInsert(CmContactMedium.class, validDate, new DBCondition(CmContactMedium.Field.objectId, contact.getCustId()),
                			 new DBCondition(CmContactMedium.Field.objectType,EnumCodeExDefine.CUST_CONTACT),
                			 new DBCondition(CmContactMedium.Field.contactType,contact.getContactType()),
                             new DBCondition(CmResource.Field.expireDate, validDate, Operator.GREAT));
                 }
             }
         }

     }
    
    
    
    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
    	List<IContact> dataList = (List<IContact>)dataArr[0];
        ContactCmp contactCmp = this.getContext().getCmp(ContactCmp.class);
        contactCmp.operateContact(dataList);
    }


	@Override
	public void initData(
			List<? extends DataObject>... dataArr) throws IMSException {

	}



	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		List<IContact> dataList = (List<IContact>)paramArr[0];
		if(CommonUtil.isEmpty(dataList)){
           return;			
		}
		/**
		 * object_type 分 0,1,3 三种情况，object_type=3 不分表
		 * 0 用户联系人
		 * 1 账户联系人
		 * 3 客户联系人
		 * 
		 * **/
		ContactCmp contactCmp = this.getContext().getCmp(ContactCmp.class);
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
	    for (IContact iContact : dataList) {
	    	//物理删除
	    	if(EnumCodeExDefine.OPER_TYPE_DIFF != iContact.getOperType()){
				continue;
			}
	    	 setObjectTypeContextHolder(iContact); 
	    	 if (iContact.getUserId() != null && iContact.getUserId() != 0) {
	    		 baseCmp.checkUserRouter(iContact.getUserId());
	 				contactCmp.deleteByConditionForDiff(CmContactMedium.class,
	 		    			new DBCondition(CmContactMedium.Field.objectId,iContact.getUserId()),
	 		    			new DBCondition(CmContactMedium.Field.objectType,EnumCodeExDefine.USER_CONTACT));
	 		
             } else if (iContact.getAcctId() != null && iContact.getAcctId() != 0) {
            	 baseCmp.checkAcctRouter(iContact.getAcctId());
            		 contactCmp.deleteByConditionForDiff(CmContactMedium.class,
 	 		    			new DBCondition(CmContactMedium.Field.objectId,iContact.getAcctId()),
 	 		    			new DBCondition(CmContactMedium.Field.objectType,EnumCodeExDefine.ACCT_CONTACT));
            	
             } else if (iContact.getCustId() != null && iContact.getCustId() != 0) {
            	 baseCmp.checkCustRouter(iContact.getCustId());
                	 contactCmp.deleteByConditionForDiff(CmContactMedium.class,
      		    			new DBCondition(CmContactMedium.Field.objectId,iContact.getCustId()),
      		    			new DBCondition(CmContactMedium.Field.objectType,EnumCodeExDefine.CUST_CONTACT));
             }
	    	setObjectTypeContextHolder(iContact);
	    	iContact.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
		}
	    contactCmp.operateContact(dataList);
	}



	/**
	 * 设置分表参数
	 * @param iContact
	 */
	private void setObjectTypeContextHolder(IContact iContact) {
		if (iContact.getUserId() != null && iContact.getUserId() != 0)
		{// 创建用户联系人信息
			ITableUtil.setValue2ContextHolder(null, null, iContact.getUserId());
		}
		if (iContact.getAcctId() != null && iContact.getAcctId() != 0)
		{// 创建账户联系人信息
			ITableUtil.setValue2ContextHolder(null, iContact.getAcctId(), null);
		}
		if (iContact.getCustId() != null && iContact.getCustId() != 0)
		{
			ITableUtil.setValue2ContextHolder(iContact.getCustId(), null, null);
			ContextHolder.getRequestContext().put("OBJECT_TYPE", 3);
			
		}
	}
	
}
