package com.ailk.imssh.contact.cmp;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmContactMediumPartition;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmRouteIdentity;
import com.ailk.openbilling.persistence.itable.entity.IContact;

/**
 * 联系人资料I表与模型表之间的转化
 * 
 * @author wukl
 * @Date 2012-6-11
 * @Date 2012-07-11 wukl http://hztrac.asiainfo-linkage.com/trac/ticket/49206相关修改
 * @Date 2012-11-15 wukl 数据库中该字段的长度为32位，而CRM受理过来的是64位
 */
public class ContactCmp extends BaseCmp
{
	private String specialTableName = "CD.CM_CONTACT_MEDIUM";
	/**
	 * 
	 * @param contactList
	 
	public void operateContact(List<IContact> contactList)
    {
		List<IContact> addList = new ArrayList<IContact>();
		List<IContact> updateList = new ArrayList<IContact>();
		List<IContact> deleteList = new ArrayList<IContact>();
		
        for (IContact IContact : contactList)
        {
            switch (IContact.getOperType())
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                addList.add(IContact);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
            	updateList.add(IContact);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                deleteList.add(IContact);
                break;
            default:
                imsLogger.error("联系人处理，I表操作类型不正确。OPER_TYPE:" + String.valueOf(IContact.getOperType()));
                break;
            }
        }
    	// 更新前要先把所有的记录置为失效，再插入新纪录
        for (IContact iContact : updateList) {
        	//客户联系人存储的时候，需要将OBJECT_TYPE设置进去
        	 imsLogger.info("修改联系人之前先清空旧的联系人："+iContact.getSoNbr());
            setObjectTypeContextHolder(iContact);
            deleteContact(iContact);
		}
        for (IContact iContact : addList) {
        	//客户联系人存储的时候，需要将OBJECT_TYPE设置进去
            setObjectTypeContextHolder(iContact);
        	createContact(iContact);
		}
        for (IContact iContact : updateList) {
        	//客户联系人存储的时候，需要将OBJECT_TYPE设置进去
        	 imsLogger.info("新增修改之后的联系人："+iContact.getSoNbr());
            setObjectTypeContextHolder(iContact);
        	createContact(iContact);
		}
        for (IContact iContact : deleteList) {
        	//客户联系人存储的时候，需要将OBJECT_TYPE设置进去
            setObjectTypeContextHolder(iContact);
            deleteContact(iContact);
		}
    }
	*/
	public void operateContact(List<IContact> contactList){
		for (IContact iContact : contactList)
        {
            switch (iContact.getOperType())
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
            	setObjectTypeContextHolder(iContact);
            	createContact(iContact);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
            	setObjectTypeContextHolder(iContact);
            	modifyContact(iContact);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
            	setObjectTypeContextHolder(iContact);
                deleteContact(iContact);
                break;
            default:
                imsLogger.error("联系人处理，I表操作类型不正确。OPER_TYPE:" + String.valueOf(iContact.getOperType()));
                break;
            }
        }
	}
	
    /**
     * 新增联系人信息<br>
     * wukl 2012-5-22
     * 
     * @param iContact
     */
    public void createContact(IContact iContact)
    {
    	
    	if(iContact.getContactType() == EnumCodeDefine.CONTACT_CUST){
    		CmContactMediumPartition contactMedium = convertContactPartition(iContact);
    		createCustContact(contactMedium);
    	}else{
    		CmContactMedium contactMedium = convertContact(iContact);
    		this.insertWithAllCache(contactMedium,new DBCondition(CmContactMedium.Field.objectId,contactMedium.getObjectId()),
        			new DBCondition(CmContactMedium.Field.objectType,contactMedium.getObjectType()));
    	}
    	
    }
    
    private void createCustContact(CmContactMediumPartition contactMedium){
    	DBUtil.insertWithName(contactMedium, dbkey[0], specialTableName);
    	DBUtil.insertWithName(contactMedium, dbkey[1], specialTableName);
    }

    /**
     * 修改联系人信息<br>
     * wukl 2012-6-13
     * 
     * @param iContact
     */
    public void modifyContact(IContact iContact)
    {
    	
    	
    	if(iContact.getContactType() == EnumCodeDefine.CONTACT_CUST){
    		CmContactMediumPartition contactMedium = convertContactPartition(iContact);
    		modifyCustContact(contactMedium);
    	}else{
    		CmContactMedium contactMedium = convertContact(iContact);
    		this.updateMode1(contactMedium, new DBCondition(CmContactMedium.Field.objectId,contactMedium.getObjectId()),
        			new DBCondition(CmContactMedium.Field.objectType,contactMedium.getObjectType()));
    	}
    }
    
    private void modifyCustContact(CmContactMediumPartition contactMedium){
    
    	ContextHolder.getRequestContext().put("DB_KEY", dbkey[0]);
    	this.updateWithInsertBykey(contactMedium, null, null, dbkey[0], specialTableName,
    			new DBCondition(CmContactMediumPartition.Field.objectId,contactMedium.getObjectId()),
    			new DBCondition(CmContactMediumPartition.Field.objectType,contactMedium.getObjectType()),
    			new DBCondition(CmContactMediumPartition.Field.partitionId,contactMedium.getPartitionId()));
    	
    	ContextHolder.getRequestContext().put("DB_KEY", dbkey[1]);
    	this.updateWithInsertBykey(contactMedium, null, null, dbkey[1], specialTableName,
    			new DBCondition(CmContactMediumPartition.Field.objectId,contactMedium.getObjectId()),
    			new DBCondition(CmContactMediumPartition.Field.objectType,contactMedium.getObjectType()),
    			new DBCondition(CmContactMediumPartition.Field.partitionId,contactMedium.getPartitionId()));
    }

    /**
     * 删除联系人信息<br>
     * wukl 2012-6-13
     * 
     * @param iContact
     */
    public void deleteContact(IContact iContact)
    {
    	
    	
    	Date expireDate = getNextMonthBegin(iContact.getExpireDate());
    	if(iContact.getContactType() == EnumCodeDefine.CONTACT_CUST){
    		int partitionId = (int)(iContact.getCustId()%10000);
    		deleteCustContact(iContact.getCustId(),expireDate,partitionId);
        }else{
        	int objectType= 0;
        	if (iContact.getContactType() == EnumCodeDefine.CONTACT_FGPRELAPHONE || iContact.getContactType() == EnumCodeDefine.CONTACT_GRUA)
            {
        		objectType = EnumCodeDefine.DEV_CONTACT_TYPE;
            }else if(iContact.getContactType() == EnumCodeDefine.CONTACT_GROUP){
            	objectType = EnumCodeDefine.CONTACT_GROUP;
            }

        	this.deleteMode1(CmContactMedium.class,expireDate, new DBCondition(CmContactMedium.Field.objectId, iContact.getUserId()),
        			new DBCondition(CmContactMedium.Field.objectType,objectType));
        }
    
    	
    }

    private void deleteCustContact(long custId,Date expireDate,int partitionId){
    	this.deleteByName_noInsert(CmContactMediumPartition.class, dbkey[0], specialTableName, expireDate, 
    			new DBCondition(CmContactMediumPartition.Field.objectId, custId),
    			new DBCondition(CmContactMediumPartition.Field.objectType,EnumCodeDefine.CUST_CONTACT_TYPE),
    			new DBCondition(CmContactMediumPartition.Field.expireDate, context.getRequestDate(),Operator.GREAT),
    			new DBCondition(CmContactMediumPartition.Field.partitionId, partitionId));
    	
    	this.deleteByName_noInsert(CmContactMediumPartition.class, dbkey[1], specialTableName, expireDate,
    			new DBCondition(CmContactMediumPartition.Field.objectId, custId),
    			new DBCondition(CmContactMediumPartition.Field.objectType,EnumCodeDefine.CUST_CONTACT_TYPE),
    			new DBCondition(CmContactMediumPartition.Field.expireDate, context.getRequestDate(),Operator.GREAT),
    			new DBCondition(CmContactMediumPartition.Field.partitionId, partitionId));
    }
    
    private CmContactMediumPartition convertContactPartition(IContact iContact){
    	CmContactMediumPartition cmContactMedium = new CmContactMediumPartition();
    	if (iContact.getOperType() == EnumCodeExDefine.OPER_TYPE_UPDATE) {
         	cmContactMedium.setCreateDate(iContact.getValidDate());
 		 }else{
 			cmContactMedium.setCreateDate(context.getCommitDate());
 		 }
         
         cmContactMedium.setSoDate(context.getCommitDate());
         cmContactMedium.setContactMediumId(Long.valueOf(0));//联系人编号默认0
         cmContactMedium.setObjectId(iContact.getCustId());
         cmContactMedium.setObjectType(EnumCodeDefine.CUST_CONTACT_TYPE);
         cmContactMedium.setContactType(iContact.getContactType());
         cmContactMedium.setMobile(iContact.getMobilePhone());
         cmContactMedium.setTelephone(iContact.getHomePhone());
         cmContactMedium.setFax(iContact.getFax());
         cmContactMedium.setEmailAddress(iContact.getEmail());
         cmContactMedium.setAddress(iContact.getAddress());
         cmContactMedium.setPostCode(iContact.getPostCode());
         cmContactMedium.setValidDate(iContact.getValidDate());
         cmContactMedium.setExpireDate(iContact.getExpireDate());
         cmContactMedium.setSoNbr(iContact.getSoNbr());
         cmContactMedium.setCountryId(iContact.getCountryId());
         cmContactMedium.setOfficePhone(iContact.getOfficePhone());
         cmContactMedium.setContactName(iContact.getContactName());
         cmContactMedium.setProvinceCode(iContact.getProvCode());
         cmContactMedium.setCountyCode(EnumCodeExDefine.COUNTRY_CODE_DEFAULT);//2100
         cmContactMedium.setRegionCode(iContact.getRegionCode());
         cmContactMedium.setPartitionId((int)(iContact.getCustId()%10000));
    	 return cmContactMedium;
    	
    }
    
    private CmContactMedium convertContact(IContact iContact){
    	 CmContactMedium cmContactMedium = new CmContactMedium();
    	 
         if (iContact.getOperType() == EnumCodeExDefine.OPER_TYPE_UPDATE) {
         	cmContactMedium.setCreateDate(iContact.getValidDate());
 		 }else{
 			cmContactMedium.setCreateDate(context.getCommitDate());
 		 }
         
         cmContactMedium.setSoDate(context.getCommitDate());
         cmContactMedium.setContactMediumId(Long.valueOf(0));//联系人编号默认0
         
         if (iContact.getContactType() == EnumCodeDefine.CONTACT_FGPRELAPHONE || iContact.getContactType() == EnumCodeDefine.CONTACT_GRUA)
         {
        	 cmContactMedium.setObjectId(iContact.getUserId());
             cmContactMedium.setObjectType(EnumCodeDefine.DEV_CONTACT_TYPE);
         }
         else if (iContact.getContactType() == EnumCodeDefine.CONTACT_CUST)
         {
        	 cmContactMedium.setObjectId(iContact.getCustId());
             cmContactMedium.setObjectType(EnumCodeDefine.CUST_CONTACT_TYPE);
             
         }else if(iContact.getContactType() == EnumCodeDefine.CONTACT_GROUP){
        	 cmContactMedium.setObjectId(iContact.getUserId());
        	 cmContactMedium.setObjectType(EnumCodeDefine.CONTACT_GROUP);
         }
         
         cmContactMedium.setContactType(iContact.getContactType());
         cmContactMedium.setMobile(iContact.getMobilePhone());
         cmContactMedium.setTelephone(iContact.getHomePhone());
         cmContactMedium.setFax(iContact.getFax());
         cmContactMedium.setEmailAddress(iContact.getEmail());
         cmContactMedium.setAddress(iContact.getAddress());
         cmContactMedium.setPostCode(iContact.getPostCode());
         cmContactMedium.setValidDate(iContact.getValidDate());
         cmContactMedium.setExpireDate(iContact.getExpireDate());
         cmContactMedium.setSoNbr(iContact.getSoNbr());
         cmContactMedium.setCountryId(iContact.getCountryId());
         cmContactMedium.setOfficePhone(iContact.getOfficePhone());
         cmContactMedium.setContactName(iContact.getContactName());
         cmContactMedium.setProvinceCode(iContact.getProvCode());
         cmContactMedium.setCountyCode(EnumCodeExDefine.COUNTRY_CODE_DEFAULT);//2100
         cmContactMedium.setRegionCode(iContact.getRegionCode());
         
    	 return cmContactMedium;
    }
    
   

    protected void setObjectTypeContextHolder(IContact iContact){
		if (iContact.getContactType() == EnumCodeDefine.CONTACT_GROUP || iContact.getContactType() == EnumCodeDefine.CONTACT_FGPRELAPHONE || iContact.getContactType() == EnumCodeDefine.CONTACT_GRUA)
        {// 创建用户联系人信息CONTACT_GROUP:1,4,2
			RouterCmp routeCmp = context.getCmp(RouterCmp.class);
			Long acctId=routeCmp.queryAcctIdByUserId(iContact.getUserId());
	        ITableUtil.setValue2ContextHolder(null, acctId,null);
        }
        else if (iContact.getContactType() == EnumCodeDefine.CONTACT_CUST)
        {// 创建客户联系人信息
        	//ITableUtil.setValue2ContextHolder(iContact.getCustId(), null, null);
        	ContextHolder.getRequestContext().put("OBJECT_TYPE", 3);
        	imsLogger.debug("=========cust contact to CD.CM_OCNTACT_MEDIUM");
        }
	}

}
