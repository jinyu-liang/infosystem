package com.ailk.imssh.group.cmp;

import jef.database.Condition.Operator;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.itable.entity.IGroupMember;

/**
 * @Description 群组成员表I_GROUP_MEMBER 转数据库表
 * @author wangyh3
 * @Date 2012-5-14
 */
public class GroupMemberCmp extends BaseCmp
{

    /**
     * @Description 群组成员表I_GROUP_MEMBER 数据通过逻辑转换，从数据库对应表中删除
     * @author wangyh3
     * @Date 2012-5-14
     */
    public void deleteGroupMember(IGroupMember iGroupMember)
    {   
    	
    	CaResGrpRevs caResGrpRevs=new CaResGrpRevs();
    	caResGrpRevs.setExpireDate(iGroupMember.getExpireDate());
    	CaAccountRv  caAccountRv=new CaAccountRv();
    	caAccountRv.setExpireDate(iGroupMember.getExpireDate());
        try
        {
            if (iGroupMember.getUserId() != null && iGroupMember.getUserId() != 0&&iGroupMember.getNumberType()!=2)
            {
                // 删除下周期的数据
//                if (iGroupMember.getValidDate().after(context.getCommitDate()))
//                {
//                    this.deleteByCondition(CaAccountRv.class,
//                            new DBCondition(CaAccountRv.Field.acctId, iGroupMember.getGroupId()), new DBCondition(
//                                    CaAccountRv.Field.resourceId, iGroupMember.getUserId()), new DBCondition(
//                                    CaAccountRv.Field.validDate,iGroupMember.getValidDate()));
//
//                    this.deleteByCondition(CaResGrpRevs.class,
//                            new DBCondition(CaResGrpRevs.Field.acctId, iGroupMember.getGroupId()), new DBCondition(
//                                    CaResGrpRevs.Field.resourceId, iGroupMember.getUserId()), new DBCondition(
//                                    		CaResGrpRevs.Field.validDate,iGroupMember.getValidDate()));
//                }
//                else
//                {
                    // 主键：ACCT_ID, RESOURCE_ID, SO_NBR, VALID_DATE, PHONE_ID
            	RouterCmp cmp=this.context.getCmp(RouterCmp.class);
            	Long acctId=cmp.queryAcctIdByUserId(iGroupMember.getUserId());
                ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, acctId);
            	this.updateMode2(caResGrpRevs, EnumCodeExDefine.OPER_TYPE_DELETE,iGroupMember.getValidDate(),
                  		new DBCondition(CaResGrpRevs.Field.acctId, iGroupMember.getGroupId()),
                          new DBCondition(CaResGrpRevs.Field.resourceId, iGroupMember.getUserId())); 
            	 acctId=cmp.queryAcctIdByUserId(iGroupMember.getGroupId());
                ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, acctId);
            	  this.updateMode2(caAccountRv, EnumCodeExDefine.OPER_TYPE_DELETE,iGroupMember.getValidDate(),
                  		new DBCondition(CaResGrpRevs.Field.acctId, iGroupMember.getGroupId()),
                          new DBCondition(CaResGrpRevs.Field.resourceId, iGroupMember.getUserId()));
            	  
                   
               // }
            }
            else
            {
//                if (iGroupMember.getValidDate().after(context.getCommitDate()))
//                {
            	
             
            	
            	  this.updateMode2(caAccountRv, EnumCodeExDefine.OPER_TYPE_DELETE,iGroupMember.getValidDate(),
                  		new DBCondition(CaResGrpRevs.Field.acctId, iGroupMember.getGroupId()),
                          new DBCondition(CaAccountRv.Field.phoneId, iGroupMember.getPhoneId()));
            	  
            	
//                }
//                else
//                {
//                    this.deleteByCondition(CaAccountRv.class,
//                            new DBCondition(CaAccountRv.Field.acctId, iGroupMember.getGroupId()), new DBCondition(
//                                    CaAccountRv.Field.phoneId, iGroupMember.getPhoneId()), new DBCondition(
//                                    CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_SPECIALNBR));
//                }
            }
        }
        catch (Exception e)
        {
            imsLogger.error("************delete group member error");
        }
    }

    /**
     * @Description 群组成员表I_GROUP_MEMBER 数据通过逻辑转换，修改到数据库对应表中
     * @author wangyh3
     * @Date 2012-5-14
     */
    public void updateGroupMember(IGroupMember iGroupMember)
    {
        modifyGroupMember(iGroupMember);

        // 修改反向表
        
        if (iGroupMember.getUserId() != null && iGroupMember.getUserId() != 0&&iGroupMember.getNumberType()!=2)
        {
            try
            {   
            	RouterCmp cmp=this.context.getCmp(RouterCmp.class);
            	Long acctId=cmp.queryAcctIdByUserId(iGroupMember.getUserId());
                ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, acctId);
                modifyCaResGrpRevs(iGroupMember);
            }
            catch (Exception e)
            {
                imsLogger.error("this user_id can not found ! " + iGroupMember.getUserId());
            }
        }
        
    }

    private void modifyCaResGrpRevs(IGroupMember iGroupMember)
    {
    	/**
        CaResGrpRevs caResGrpRevs = new CaResGrpRevs();
        caResGrpRevs.setResourceId(iGroupMember.getUserId());// 群成员编号
        caResGrpRevs.setAcctId(iGroupMember.getGroupId());// 群账号
        caResGrpRevs.setExpireDate(iGroupMember.getExpireDate());
        caResGrpRevs.setSoNbr(iGroupMember.getSoNbr());
        caResGrpRevs.setSoDate(iGroupMember.getCommitDate());

        this.updateByCondition(caResGrpRevs, new DBCondition(CaResGrpRevs.Field.acctId, iGroupMember.getGroupId()),
                new DBCondition(CaResGrpRevs.Field.resourceId, iGroupMember.getUserId()), new DBCondition(
                        CaResGrpRevs.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
         */
        CaResGrpRevs caResGrpRevs = new CaResGrpRevs();
        caResGrpRevs.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
        caResGrpRevs.setCreateDate(iGroupMember.getCommitDate());
        caResGrpRevs.setValidDate(iGroupMember.getValidDate());
        caResGrpRevs.setExpireDate(iGroupMember.getExpireDate());
        caResGrpRevs.setSoNbr(context.getSoNbr());
        caResGrpRevs.setSoDate(iGroupMember.getCommitDate());
        this.updateMode2(caResGrpRevs, EnumCodeExDefine.OPER_TYPE_UPDATE,iGroupMember.getValidDate(),
        		new DBCondition(CaResGrpRevs.Field.acctId, iGroupMember.getGroupId()),
                new DBCondition(CaResGrpRevs.Field.resourceId, iGroupMember.getUserId()));
    	
        
    }

    public void modifyGroupMember(IGroupMember iGroupMember)
    {	
    	
    	   CaAccountRv caAccountRv = new CaAccountRv();
           caAccountRv.setCreateDate(this.getContext().getCommitDate());
           caAccountRv.setSoDate(this.getContext().getCommitDate());

           caAccountRv.setIsdn(iGroupMember.getShortPhoneId());
           caAccountRv.setTitleRoleId(iGroupMember.getRoleId());
           // caAccountRv.setCalloutright(iGroupMember.getCalloutright());
           // caAccountRv.setCallinright(iGroupMember.getCallinright());
           // caAccountRv.setClipFlag(iGroupMember.getClipFlag());
           // caAccountRv.setEspaceFlag(iGroupMember.getEspaceFlag());
           caAccountRv.setNumberType(CommonUtil.IntegerToLong(iGroupMember.getNumberType()));
           // @Date 2012-11-09 wukl 如果是2:网外号码，则RELATIONSHIP_TYPE 填3
           if (iGroupMember.getNumberType().intValue() == 2)
           {
               caAccountRv.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_SPECIALNBR);
           }
           else
           {
               caAccountRv.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
           }
           // caAccountRv.setDisplayNumber(iGroupMember.getDisplayNumber());
           // caAccountRv.setRemarks(iGroupMember.getRemarks());
           caAccountRv.setSoNbr(context.getSoNbr());
           caAccountRv.setExpireDate(iGroupMember.getExpireDate());
           if(iGroupMember.getPhoneId() == null || "".equals(iGroupMember.getPhoneId()))
           {
               caAccountRv.setPhoneId("0");
           }else
           {
               caAccountRv.setPhoneId(iGroupMember.getPhoneId());
           }
           caAccountRv.setIngroupOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_INGROUP_OUTGOINGCALL);
           caAccountRv.setIngroupIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_INGROUP_INCOMINGCALL);
           caAccountRv.setCrossgroupOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_CROSSGROUP_OUTGOINGCALL);
           caAccountRv.setCrossgroupIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_CROSSGROUP_INCOMINGCALL);
           caAccountRv.setSpecOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_SPEC_OUTGOINGCALL);
           caAccountRv.setSpecIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_SPEC_INCOMINGCALL);
           caAccountRv.setOutgroupOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_OUTGROUP_OUTGOINGCALL);
           caAccountRv.setOutgroupIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_OUTGROUP_INCOMINGCALL);
           caAccountRv.setStatus(iGroupMember.getStatus());
           caAccountRv.setTpId(iGroupMember.getElementId());
        // 如果是网外号码，更新条件也需要设置为3
        if (iGroupMember.getNumberType().intValue() == 2)
        {
        	
        	this.updateMode2(caAccountRv,EnumCodeExDefine.OPER_TYPE_UPDATE,iGroupMember.getValidDate(),
        			new DBCondition(CaAccountRv.Field.acctId, iGroupMember.getGroupId()),
                    new DBCondition(CaAccountRv.Field.phoneId, iGroupMember.getPhoneId()));
            
        }
        else
        {
        	this.updateMode2(caAccountRv,EnumCodeExDefine.OPER_TYPE_UPDATE,iGroupMember.getValidDate(),
        			new DBCondition(CaAccountRv.Field.acctId, iGroupMember.getGroupId()),
                    new DBCondition(CaAccountRv.Field.resourceId, iGroupMember.getUserId()));
        }
        
    	
    }
    
    
}
