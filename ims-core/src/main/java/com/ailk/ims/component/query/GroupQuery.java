package com.ailk.ims.component.query;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Group3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.busi.entity.RsPbxRouteInfo;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.imsintf.entity.SGroup;
import com.ailk.openbilling.persistence.imsintf.entity.SGroupList;
import com.ailk.openbilling.persistence.imsintf.entity.SMember;
import com.ailk.openbilling.persistence.imsintf.entity.SMemberList;
import com.ailk.openbilling.persistence.imsintf.entity.SpecialNumber;
import com.ailk.openbilling.persistence.imsintf.entity.SpecialNumberList;

/**
 * @Description:群组相关的信息查询的方法类
 * @author wangjt
 * @Date 2011-12-21
 * @Date 2012-3-19 按条件查询群信息，增加group_type的返回
 * @Date 2012-04-27 yangjh add queryGroupMemberByUserId and queryGroupMemberByUserIdAndGroupId 
 * @Date 2012-04-27 yangjh queryGroupInfo 增加group_type的封装 
 * @Date 2012-07-19 wangdw5 :#52157 member type（对应ROLE）应该采用传入的参数
 */
public class GroupQuery extends BaseComponent
{
    public GroupQuery()
    {
    }

    /**
     * 查询群和账户表返回Map luojb 2011-12-5
     * 
     * @param groupId
     * @return
     * @throws IMSException
     * @modify by xieqr 2012-3-22 增加了冲浪E家亲，66家庭套餐活动群查询
     * @modify by luojb 2012-07-09 去掉群类型的查询条件
     */
//    public Map queryGroup(Long groupId) throws IMSException
//    {
////    	CaAccountGroup vpn = new CaAccountGroup();
////        vpn.setAcctId(groupId);
//    	CaAccountGroup vpn = querySingle(CaAccountGroup.class,new DBCondition(CaAccountGroup.Field.acctId,groupId));
//        if (vpn == null)
//        {
//            return null;
//        }
//
////        List<Integer> accountTypes = new ArrayList<Integer>();
////        accountTypes.add((int) EnumCodeDefine.ACCOUNT_TYPE_VPN);
////        accountTypes.add((int) EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY);
////        accountTypes.add((int) EnumCodeDefine.ACCOUNT_TYPE_CUG);
////        accountTypes.add((int) EnumCodeDefine.ACCOUNT_TYPE_VPBX);
////        accountTypes.add((int) EnumCodeDefine.ACCOUNT_TYPE_SURF_E_FAMILY);
////        accountTypes.add((int) EnumCodeDefine.ACCOUNT_TYPE_SIX_SIX_FAMILY_PACKAGE);
//
////        CaAccount account = querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, groupId), new DBCondition(
////                CaAccount.Field.accountType, accountTypes, Operator.IN));
//        CaAccount account = querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, groupId));
//        if (account == null)
//        {
//            return null;
//        }
//
//        Map<Class, DataObject> map = new HashMap<Class, DataObject>();
//        map.put(CaAccountGroup.class, vpn);
//        map.put(CaAccount.class, account);
//        return map;
//    }
    /**
     * groupType转换 yugb 2012-05-22
     * 
     * @param groupType
     * @return
     */
    public short changeGroupType(short groupType){
    	switch(groupType){
	    	case 1: return 201;
	    	case 2: return 205;
	    	case 3: return 203;
	    	case 4: return 204;
    	}
    	return 0;
    }
//    /**
//     * 联查返回群账户(201 203 205)实体 luojb 2011-12-6
//     * 
//     * @param groupId
//     * @return
//     * @throws IMSException
//     */
//    public CaAccount queryGroupAccount(Long groupId) throws IMSException
//    {
//        Map map = queryGroup(groupId);
//        if (map == null || map.isEmpty())
//        {
//            return null;
//        }
//        return (CaAccount) map.get(CaAccount.class);
//    }
//    /**
//     * 联查返回vpn实体 luojb 2011-12-6
//     * 
//     * @param groupId
//     * @return
//     * @throws IMSException
//     */
//    public CaAccountGroup queryCaAccountVpn(Long groupId) throws IMSException
//    {
//        Map map = queryGroup(groupId);
//        if (map == null || map.isEmpty())
//        {
//            return null;
//        }
//        return (CaAccountGroup) map.get(CaAccountGroup.class);
//    }

//    /**
//     * luojb 2011-9-26
//     * 
//     * @param groupId
//     * @return
//     * @throws IMSException
//     */
//    public CaAccountGroup queryVpnByGroupId(long groupId) throws IMSException
//    {
//        CaAccountGroup vpn = new CaAccountGroup();
//        vpn.setAcctId(groupId);

//        CaAccountGroup vpn = super.querySingle(CaAccountGroup.class,new DBCondition(CaAccountGroup.Field.acctId,groupId));
//        return vpn;
//    }

    /**
     * 根据群id和phoneId查询成员实体 luojb 2011-11-29
     */
    public CaAccountRv queryAcctRvByGroupIdAndPhoneId(long groupId, String phoneId) throws IMSException
    {
//        CaAccountRv where = new CaAccountRv();
//        where.setAcctId(groupId);
//        where.setPhoneId(phoneId);
//        where.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
        return querySingle(CaAccountRv.class,new DBCondition(CaAccountRv.Field.acctId,groupId),
        		new DBCondition(CaAccountRv.Field.phoneId,phoneId),
        		new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
    }

    /**
     * 
     * yangjh 2012-10-22  查询特殊号码
     * @param groupId
     * @param phoneId
     * @return
     * @throws IMSException
     */
    public CaAccountRv querySpecMemByGroupAndPhone(long groupId, String phoneId) throws IMSException
    {
        return querySingle(CaAccountRv.class,new DBCondition(CaAccountRv.Field.acctId,groupId),
                new DBCondition(CaAccountRv.Field.phoneId,phoneId),
                new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_SPECIALNBR));
    }
    
    /**
     * @Description: 查出CaAccountRv实体，这个实体记录roupid群下的resourceId用户的成员信息。
     * @Date 2011-10-13 hunan 把方法名queryMember改为queryAcctRvByGrpIdAndUserId ，否则容易与查询群成员产生歧义。
     */
    public CaAccountRv queryAcctRvByGrpIdAndUserId(long groupId, long resourceId) throws IMSException
    {
//        CaAccountRv rv = new CaAccountRv();
//        rv.setAcctId(groupId);
//        rv.setResourceId(resourceId);
//        rv.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
        CaAccountRv rv = super.querySingle(CaAccountRv.class,new DBCondition(CaAccountRv.Field.acctId,groupId),
        		new DBCondition(CaAccountRv.Field.resourceId,resourceId),
        		new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        return rv;
    }

    /**
     * @Description: 通过userid 查出vpn群某个成员的信息实体CaAccountRv
     * @Author hunan
     * @Date 2011-10-13
     */
    public CaAccountRv queryVpnMemberAcctRvByUserId(long resourceId) throws IMSException
    {
        List<CaAccountRv> rvList = queryJoinedVpnsByUserId(resourceId);
        if (CommonUtil.isEmpty(rvList))
        {
            return null;
        }
        return rvList.get(0);
    }

    /**
     * @Description: 通过userid 和 groupid查出vpn群某个成员的信息实体CaAccountRv
     * @Author wangdw5 2012-03-21
     */
    public CaAccountRv queryVpnMemberAcctRvByUserIdAndGroupId(long resourceId, Long groupId) throws IMSException
    {
        List<CaAccountRv> rvList = groupId != null ? queryJoinedVpnsByUserIdAndGroupId(resourceId, groupId)
                : queryJoinedVpnsByUserId(resourceId);
        if (CommonUtil.isEmpty(rvList))
        {
            return null;
        }
        return rvList.get(0);
    }
    
    /**
     * @Description: 通过userid 和 groupid查出群某个成员的信息实体CaAccountRv
     * @Author yangjh 2012-04-27
     */
    public CaAccountRv queryGroupMemberByUserIdAndGroupId(long resourceId,Long groupId) throws IMSException
    {
        CaAccountRv rvList = this.querySingle(CaAccountRv.class,
                new DBCondition(CaAccountRv.Field.acctId,groupId),
                new DBCondition(CaAccountRv.Field.resourceId,resourceId),
                new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        if(rvList == null){
        	  return null;
        }
        return rvList;
    }
    
    /**
     * 根据用户id，查出用户所在所有群里的信息
     * luojb 2012-10-16
     * @param resourceId
     * @return
     * @throws IMSException
     */
    public List<CaAccountRv> queryGroupMemberByUserId(long resourceId) throws IMSException
    {
        // 2012-10-16 luojb #57570 群成员反向表
        List<CaResGrpRevs> revList = this.query(CaResGrpRevs.class,
                new DBCondition(CaResGrpRevs.Field.resourceId,resourceId),
                new DBCondition(CaResGrpRevs.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        if(CommonUtil.isEmpty(revList))
        {
            return null;
        }
        
        List<CaAccountRv> rvList = null;
        if(revList.size() == 1)
        {
            rvList = this.query(CaAccountRv.class,
                    new DBCondition(CaAccountRv.Field.acctId,revList.get(0).getAcctId()),
                    new DBCondition(CaAccountRv.Field.resourceId,resourceId),
                    new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        }else
        {
            List<Long> groupIds = new ArrayList<Long>();
            for(CaResGrpRevs rev:revList)
            {
                groupIds.add(rev.getAcctId());
            }
            rvList = this.query(CaAccountRv.class,
                    new DBCondition(CaAccountRv.Field.acctId,groupIds,Operator.IN),
                    new DBCondition(CaAccountRv.Field.resourceId,resourceId),
                    new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        }
        return rvList;
    }

    /**
     * 查询用户所在group的CaAccountRv wangdw5 2012-03-21
     */
    public List<CaAccountRv> queryJoinedVpnsByUserIdAndGroupId(long resourceId, long groupId) throws IMSException
    {
        return queryJoinedGroupsByUserIdAndGroupType(resourceId, groupId, EnumCodeDefine.ACCOUNT_TYPE_VPN);
    }

    /**
     * 查询用户所在group的CaAccountRv wangdw5 2012-03-21
     */
    private List<CaAccountRv> queryJoinedGroupsByUserIdAndGroupType(long resourceId, long groupId, short groupType)
            throws IMSException
    {
        List<CaAccountRv> rvList = query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.resourceId, resourceId),
                new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(CaAccountRv.Field.relationshipType,
                        EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        if (CommonUtil.isEmpty(rvList))
            return null;
        List<CaAccountRv> removeList = new ArrayList<CaAccountRv>();
        for (CaAccountRv rv : rvList)
        {
//            Map map = queryGroup(rv.getAcctId());
//            if (map == null)
//                continue;
//            CaAccount account = (CaAccount) map.get(CaAccount.class);
        	//2012-08-29 linzt 整理组件方法
        	CaAccount account=null;
        	try
        	{
        		account=context.get3hTree().loadGroup3hBean(rv.getAcctId()).getAccount();
        	}
        	catch(IMS3hNotFoundException e)
        	{
        		continue;
        	}
            if (account.getAccountType().shortValue() != groupType)
            {
                removeList.add(rv);
            }
        }
        if (CommonUtil.isNotEmpty(removeList))
            rvList.removeAll(removeList);
        return rvList;
    }

    /**
     * 查询用户加入的所有vpn luojb 2011-12-2
     */
    public List<CaAccountRv> queryJoinedVpnsByUserId(long resourceId) throws IMSException
    {
        return queryJoinedGroupsByUserIdAndGroupType(resourceId, EnumCodeDefine.ACCOUNT_TYPE_VPN);
    }

    /**
     * 查询用户加入的所有community luojb 2011-12-2
     */
    public List<CaAccountRv> queryJoinedCommsByUserId(long resourceId) throws IMSException
    {
        return queryJoinedGroupsByUserIdAndGroupType(resourceId, EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY);
    }

    /**
     * 查询用户加入的所有冲浪E家亲活动群 xieqr 2012-3-22
     */
    public List<CaAccountRv> queryJoinedSurfEFamilyByUserId(long resourceId) throws IMSException
    {
        return queryJoinedGroupsByUserIdAndGroupType(resourceId, EnumCodeDefine.ACCOUNT_TYPE_SURF_E_FAMILY);
    }

    /**
     * 查询用户加入的所有66家庭包活动群 xieqr 2012-3-22
     */
    public List<CaAccountRv> queryJoinedSixSixFamilyPackageByUserId(long resourceId) throws IMSException
    {
        return queryJoinedGroupsByUserIdAndGroupType(resourceId, EnumCodeDefine.ACCOUNT_TYPE_SIX_SIX_FAMILY_PACKAGE);
    }

    /**
     * 查询用户加入的所有某类型群 luojb 2011-12-2
     */
    private List<CaAccountRv> queryJoinedGroupsByUserIdAndGroupType(long resourceId, short groupType) throws IMSException
    {
        // 2012-10-16 luojb #57570 群成员反向表
        List<CaResGrpRevs> revList = this.query(CaResGrpRevs.class,
                new DBCondition(CaResGrpRevs.Field.resourceId,resourceId),
                new DBCondition(CaResGrpRevs.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        if(CommonUtil.isEmpty(revList))
            return null;
        List<Long> groupIds = new ArrayList<Long>();
        for (CaResGrpRevs rev : revList)
        {
            Long groupId = rev.getAcctId();
            //2012-08-29 linzt 整理组件方法
            try
            {
                CaAccount account=context.get3hTree().loadGroup3hBean(groupId).getAccount();
                if (account.getAccountType().shortValue() == groupType)
                {
                    groupIds.add(groupId);
                }
            }
            catch(IMS3hNotFoundException e)
            {
                continue;
            }
        }
        if(CommonUtil.isEmpty(groupIds))
            return null;
        
        List<CaAccountRv> rvList = query(CaAccountRv.class, 
                new DBCondition(CaAccountRv.Field.acctId, groupIds,Operator.IN),
                new DBCondition(CaAccountRv.Field.resourceId, resourceId),
                new DBCondition(CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));

        return rvList;
    }

    /**
     * 查询特殊号码虚账户id luojb 2011-11-11
     */
    public Long querySpecNbrGCAId(Long groupId) throws IMSException
    {
    	CaAccountRel rel = querySingle(CaAccountRel.class,new DBCondition(CaAccountRel.Field.groupId,groupId),
        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_VPN_SPECNBR_GCA_RELATION));
        if (rel == null)
        {
            return null;
        }
        return rel.getRelGroupId();
    }

    /**
     * 查询群联系电话 luojb 2011-9-28
     */
    public String queryGroupContactPhone(Long groupId) throws IMSException
    {
    	CaAccountGroup vpn = querySingle(CaAccountGroup.class,new DBCondition(CaAccountGroup.Field.acctId,groupId));
        if (vpn == null)
        {
            return null;
        }
        return vpn.getContactPhone();
    }

    /**
     * @Description: 查询群信息
     * @Date 2011-10-14
     * @author hunan
     */
    public SGroup queryGroupInfo(Long groupId) throws IMSException
    {
//        Map map = queryGroup(groupId);
//        if (CommonUtil.isEmpty(map))
//        {
//            return null;
//        }
//      CaAccount acct = (CaAccount) map.get(CaAccount.class);
//      CaAccountGroup vpn = (CaAccountGroup) map.get(CaAccountGroup.class);
    	CaAccount acct = null;
    	CaAccountGroup vpn =null;
    	try{
    		Group3hBean groupBean=context.get3hTree().loadGroup3hBean(groupId);
    		acct =groupBean.getAccount();
    		vpn=groupBean.getGroup();
    	}
    	catch(IMS3hNotFoundException e)
    	{
    		return null;
    	}
        SGroup group = new SGroup();
        group.setGroup_id(groupId);
        // CaCustomerRel custRel = (CaCustomerRel) map.get(CaCustomerRel.class);
        // group.setCust_id(custRel.getCustId());

        group.setStatus(acct.getAccountStatus());
        group.setGroup_type(CommonUtil.IntegerToShort(acct.getAccountType()));

        if (vpn.getClipFlag() != null)
        {
            group.setCLIP_flag(vpn.getClipFlag().intValue());
        }
        group.setContact_phone(vpn.getContactPhone());
        if (vpn.getEspaceFlag() != null)
        {
            group.setEspace_flag(vpn.getEspaceFlag().intValue());
        }

        if (vpn.getMaxusers() != null)
        {
            group.setMax_group_number(vpn.getMaxusers().intValue());
        }
        if (vpn.getOcsRoutingFlag() != null)
        {
            group.setOcs_routing_flag(vpn.getOcsRoutingFlag().intValue());
        }
        if (vpn.getOutgoingCsFlag() != null)
        {
            group.setOutGoing_cs_flag(vpn.getOutgoingCsFlag().intValue());
        }
        if (vpn.getExpireDate() != null)
        {
            group.setExpire_date(DateUtil.formatDate(vpn.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        if (vpn.getValidDate() != null)
        {
            group.setValid_date(DateUtil.formatDate(vpn.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        if (CommonUtil.isValid(vpn.getGroupName()))
        {
            group.setGroup_name(vpn.getGroupName());
        }
        // 2011-12-07 新增的字段
        if (vpn.getCrossgroupIncomingcall() != null)
        {
            group.setCrossGroupIncomingCall(vpn.getCrossgroupIncomingcall());
        }
        if (vpn.getCrossgroupOutgoingcall() != null)
        {
            group.setCrossGroupOutgoingCall(vpn.getCrossgroupOutgoingcall());
        }
        if (vpn.getIngroupIncomingcall() != null)
        {
            group.setInGroupIncomingCall(vpn.getIngroupIncomingcall());
        }
        if (vpn.getIngroupOutgoingcall() != null)
        {
            group.setInGroupOutgoingCall(vpn.getIngroupOutgoingcall());
        }
        if (vpn.getSpecIncomingcall() != null)
        {
            group.setSpecIncomingCall(vpn.getSpecIncomingcall());
        }
        if (vpn.getSpecOutgoingcall() != null)
        {
            group.setSpecOutgoingCall(vpn.getSpecOutgoingcall());
        }
        if (vpn.getOcsRoutingFlag() != null)
        {
            group.setOcs_routing_flag(vpn.getOcsRoutingFlag().intValue());
        }
        if (vpn.getOutgoingCsFlag() != null)
        {
            group.setOutGoing_cs_flag(vpn.getOutgoingCsFlag().intValue());
        }

        //caohm5 add 增加用户的用户编号
        CaAccountRv rv=queryMainPhoneId(groupId);
        if(rv!=null){
        	group.setMain_phone_id(rv.getPhoneId());
        	group.setMain_resource_id(rv.getResourceId());
        }
        
//        group.setBillable_acct_id(this.queryBillAcctId(groupId));
        //2012-08-29 linzt 整理组件方法
        Group3hBean groupBean=context.get3hTree().loadGroup3hBean(groupId);   
        group.setBillable_acct_id(groupBean.getAcctId());
        group.setParent_group_id(groupBean.getParentGroupId());
        SpecialNumberList numberList = this.querySpecialNumberList(groupId);
        group.setSpecail_number_list(numberList);
        return group;
    }

    /**
     * crm查询群信息 luojb 2012-4-7
     * 
     * @param groupId
     * @param mainPhone
     * @param contactPhone
     * @param sts
     * @return
     * @throws IMSException
     */
    public SGroupList queryGroupInfoByConds(Long groupId, String mainPhone, String contactPhone, Short sts) throws IMSException
    {
        return queryGroupInfoByConds(groupId, mainPhone, contactPhone, sts, null, true);
    }

    /**
     * GUI查询群信息 luojb 2012-4-7
     * 
     * @param groupId
     * @param memberPhone
     * @param contactPhone
     * @param sts
     * @param groupType
     * @return
     * @throws IMSException
     */
    public SGroupList queryGroupInfo4GUIByConds(Long groupId, String memberPhone, String contactPhone, Short sts, Short groupType)
            throws IMSException
    {
        return queryGroupInfoByConds(groupId, memberPhone, contactPhone, sts, groupType, false);
    }

    /**
     * @author hunan
     * @Date 2011-12-01
     */
    public SGroupList queryGroupInfoByConds(Long groupId, String phone, String contactPhone, Short sts, Short groupType,
            boolean isMainPhone) throws IMSException
    {
        // 用groupId和contactPhone查询vpn表，取groupIds
        SGroupList groupList = new SGroupList();
        List<Long> groupIds = new ArrayList<Long>();
        List<CaAccountGroup> vpns = null;
        if (groupId != null || CommonUtil.isNotEmpty(contactPhone))
        {
//            CaAccountGroup vpn = new CaAccountGroup();
            List<DBCondition> cond  = new ArrayList<DBCondition>();
            if (groupId != null)
            	cond.add(new DBCondition(CaAccountGroup.Field.acctId,groupId));
                //vpn.setAcctId(groupId);
            if (CommonUtil.isNotEmpty(contactPhone))
            	cond.add(new DBCondition(CaAccountGroup.Field.contactPhone,contactPhone));
                //vpn.setContactPhone(contactPhone);
            vpns = query(CaAccountGroup.class,cond.toArray(new DBCondition[cond.size()]));
            if (CommonUtil.isNotEmpty(vpns))
            {
                for (CaAccountGroup v : vpns)
                {
                    groupIds.add(v.getAcctId());
                }
            }
            else
            {
                // 如果传入了groupId，没有查到，直接就返回
                if (groupId != null)
                    return groupList;
            }
        }
        // 用mainPhone（带上groupIds）到rv表查询，过滤不符合数据
        List<Long> newGroupIds = new ArrayList<Long>();
        List<CaAccountRv> rvs = null;
        if (CommonUtil.isNotEmpty(phone))
        {
            List<DBCondition> conditions = new ArrayList<DBCondition>();
            if (isMainPhone)
                conditions.add(new DBCondition(CaAccountRv.Field.titleRoleId, EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE));
            conditions.add(new DBCondition(CaAccountRv.Field.phoneId, phone));
            if (CommonUtil.isNotEmpty(groupIds))
            {
                conditions.add(new DBCondition(CaAccountRv.Field.acctId, groupIds, Operator.IN));
            }
            rvs = query(CaAccountRv.class, conditions.toArray(new DBCondition[conditions.size()]));
            if (CommonUtil.isNotEmpty(rvs))
            {
                for (CaAccountRv rv : rvs)
                {
                    newGroupIds.add(rv.getAcctId());
                }
            }
        }
        else
        {
            if (CommonUtil.isNotEmpty(groupIds))
                newGroupIds.addAll(groupIds);
        }
        // 到这里newGroupIds为空的话，说明用groupId、contactPhone、mainPhone条件没有查询到，返回空
        if (CommonUtil.isEmpty(newGroupIds))
            return groupList;

        List<DBCondition> conditions = new ArrayList<DBCondition>();
        conditions.add(new DBCondition(CaAccount.Field.acctId, newGroupIds, Operator.IN));
        if (sts != null)
            conditions.add(new DBCondition(CaAccount.Field.accountStatus, sts));
        if (groupType != null)
        {
        	// modify by wangyh3 20120427 群类型兼容
//            Integer accountType = null;
//            if (groupType == EnumCodeDefine.ACCOUNT_TYPE_VPN)
//                accountType = (int) EnumCodeDefine.ACCOUNT_TYPE_VPN;
//            else if (groupType == EnumCodeDefine.ACCOUNT_TYPE_CUG)
//                accountType = (int) EnumCodeDefine.ACCOUNT_TYPE_CUG;
//            else if (groupType == EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY)
//                accountType = (int) EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY;
//            else if (groupType == EnumCodeDefine.ACCOUNT_TYPE_VPBX)
//                accountType = (int) EnumCodeDefine.ACCOUNT_TYPE_VPBX;
//            if (accountType != null)
                conditions.add(new DBCondition(CaAccount.Field.accountType, groupType));
        }
        List<CaAccount> accounts = query(CaAccount.class, conditions.toArray(new DBCondition[conditions.size()]));
        if (accounts == null)
            return groupList;// 没有查询到，返回空队列

        List<SGroup> groups = new ArrayList<SGroup>();
        for (CaAccount account : accounts)
        {
            Long gid = account.getAcctId();

            CaAccountGroup vpn = null;
            if (groupIds.contains(gid))
            {
                try
                {
                    vpn = IMSUtil.matchDataObject(vpns, new CacheCondition(CaAccountGroup.Field.acctId, gid)).get(0);
                }
                catch (Exception e)
                {
                    IMSUtil.throwBusiException(e);
                }
            }
            else
            {
//                vpn = queryVpnByGroupId(gid);
//                if (vpn == null)
//                    continue;
            	try
            	{
            		vpn=context.get3hTree().loadGroup3hBean(gid).getGroup();
            	}
            	catch(IMS3hNotFoundException e)
            	{
            		continue;
            	}
            }

            CaAccountRv rv = null;
            if (newGroupIds.contains(gid) && CommonUtil.isNotEmpty(rvs))
            {
                try
                {
                    rv = IMSUtil.matchDataObject(rvs, new CacheCondition(CaAccountRv.Field.acctId, gid)).get(0);
                }
                catch (Exception e)
                {
                    IMSUtil.throwBusiException(e);
                }
            }
            else
            {
                rv = queryMainRv(gid);
            }

//            Long billAcctId = queryBillAcctId(gid);
          //2012-08-29 linzt 整理组件方法
            Group3hBean groupBean=context.get3hTree().loadGroup3hBean(gid);
            Long  billAcctId =groupBean.getAcctId();
            SGroup group = new SGroup();
            group.setGroup_id(gid);
            group.setStatus(account.getAccountStatus());

            if (vpn.getClipFlag() != null)
            {
                group.setCLIP_flag(vpn.getClipFlag().intValue());
            }
            group.setContact_phone(vpn.getContactPhone());
            if (vpn.getEspaceFlag() != null)
            {
                group.setEspace_flag(vpn.getEspaceFlag().intValue());
            }

            if (vpn.getMaxusers() != null)
            {
                group.setMax_group_number(vpn.getMaxusers().intValue());
            }
            if (vpn.getOcsRoutingFlag() != null)
            {
                group.setOcs_routing_flag(vpn.getOcsRoutingFlag().intValue());
            }
            if (vpn.getOutgoingCsFlag() != null)
            {
                group.setOutGoing_cs_flag(vpn.getOutgoingCsFlag().intValue());
            }
            if (vpn.getExpireDate() != null)
            {
                group.setExpire_date(DateUtil.formatDate(vpn.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            if (vpn.getValidDate() != null)
            {
                group.setValid_date(DateUtil.formatDate(vpn.getValidDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
            }
            if (CommonUtil.isValid(vpn.getGroupName()))
            {
                group.setGroup_name(vpn.getGroupName());
            }

            if (rv != null)
                group.setMain_phone_id(rv.getPhoneId());

            group.setBillable_acct_id(billAcctId);
            group.setParent_group_id(groupBean.getParentGroupId());
            SpecialNumberList numberList = this.querySpecialNumberList(gid);
            group.setSpecail_number_list(numberList);
            group.setInGroupIncomingCall(vpn.getIngroupIncomingcall());
            group.setInGroupOutgoingCall(vpn.getIngroupOutgoingcall());
            group.setCrossGroupIncomingCall(vpn.getCrossgroupIncomingcall());
            group.setCrossGroupOutgoingCall(vpn.getCrossgroupOutgoingcall());
            group.setOutGroupIncomingCall(vpn.getOutgroupIncomingcall());
            group.setOutGroupOutgingCall(vpn.getOutgroupOutgoingcall());
            group.setSpecIncomingCall(vpn.getSpecIncomingcall());
            group.setSpecOutgoingCall(vpn.getSpecOutgoingcall());
            // 2012-3-19 增加group_type返回
            Integer accountType = account.getAccountType();
            Short returnGroupType = accountType.shortValue();
            group.setGroup_type(returnGroupType);
            groups.add(group);
        }
        groupList.setGroup_items(groups.toArray(new SGroup[groups.size()]));
        return groupList;
    }

    /**
     * @Description:通过群编号查找群的特殊号码。
     * @Date 2011-11-11 （需求变更后更改的）
     * @param groupId
     * @author hunan
     * @return
     */
    private SpecialNumberList querySpecialNumberList(Long groupId)
    {

        Long specNbrAcctId = querySpecNbrGCAId(groupId);
        if (specNbrAcctId == null)
            return null;
        List<CaAccountRv> rvList = query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, specNbrAcctId),
                new DBCondition(CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_SPECIALNBR));
        if (CommonUtil.isEmpty(rvList))
            return null;
        List<SpecialNumber> numbers = new ArrayList<SpecialNumber>();
        for (CaAccountRv rv : rvList)
        {
            SpecialNumber number = new SpecialNumber();
            number.setPhone_id(rv.getPhoneId());
            numbers.add(number);
        }
        SpecialNumberList result = new SpecialNumberList();
        result.setSpecialNumberList_Item(numbers.toArray(new SpecialNumber[numbers.size()]));
        return result;
    }

    // ljc change private to public
    public List<CaAccountRv> queryAcctRvByGroupId(Long groupId)
    {
//        CaAccountRv caAccountRv = new CaAccountRv();
//        caAccountRv.setAcctId(groupId);
//        caAccountRv.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
        // 2011-09-26 hunan :delete
        // caAccountRv.setTitleRoleId(EnumCodeDefine.TITLEROLEID_VPN_NORMAL);//
        // 查询组成员信息是否需要根据组标识
        List<CaAccountRv> result = query(CaAccountRv.class,new DBCondition(CaAccountRv.Field.acctId,groupId),
        		new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));

        return result;
    }

    public Long queryMainUserIdByAcctId(Long acctId)
    {
        CaAccountRv caAccountRv = queryMainRv(acctId);
        if (caAccountRv == null)
        {
            return null;
        }
        return caAccountRv.getResourceId();
    }

    public CaAccountRv queryMainPhoneId(Long acctId)
    {
        CaAccountRv caAccountRv = queryMainRv(acctId);
        if (caAccountRv == null)
        {
            return null;
        }
        return caAccountRv;
    }

    public CaAccountRv queryMainRv(Long acctId)
    {
        CaAccountRv caAccountRv = querySingle(CaAccountRv.class,new DBCondition(CaAccountRv.Field.acctId,acctId),
        		new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP),
        		new DBCondition(CaAccountRv.Field.titleRoleId,EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE));
        return caAccountRv;
    }

    public SMemberList queryGroupMembers(Long groupId)
    {
        SMemberList sMemberList = new SMemberList();

        List<CaAccountRv> acctRvs = queryAcctRvByGroupId(groupId);
        if (CommonUtil.isEmpty(acctRvs))
        {
            return null;
        }

        SMember[] members = new SMember[acctRvs.size()];
        for (int i = 0; i < acctRvs.size(); i++)
        {
            CaAccountRv caAccountRv = acctRvs.get(i);
            members[i] = sMemberTransform(caAccountRv);
        }
        sMemberList.setSMemberList_Item(members);

        return sMemberList;
    }

    /**
     * 查询特殊号码的用户，如果是外部号码，则移除对应三户bean luojb 2011-11-17
     * 
     * @param phoneId
     * @return
     */
    public Long querySpecNbrUser(String phoneId)
    {
        Long userId = null;
        try
        {
            User3hBean bean = context.get3hTree().loadUser3hBean(phoneId);
            userId = bean.getUserId();// 特殊处理:这里报错，说明phone查不到用户，说明是外部号码
        }
        catch (Exception e)
        {
            ;
        }
        return userId;
    }

    /**
     * @Description:通过小号，和群编号查找群下的某个 member info
     * @param shortPhoneId 群内小号
     * @author hunan
     * @param groupId
     * @return
     */
    public SMember queryMemberInfo(String shortPhoneId, Long groupId)
    {
        CaAccountRv rv = super.querySingle(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId),
                new DBCondition(CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP), new DBCondition(
                        CaAccountRv.Field.isdn, shortPhoneId));
        SMember sMember = this.sMemberTransform(rv);
        return sMember;
    }

    /**
     * @Description: 通过群编号 查询群包含的所有内群的信息
     * @param groupId
     * @return
     * @author hunan
     * @Date 2011-09-08
     */
    public SGroupList queryGroupCUGInfo(Long groupId)
    {
        SGroupList groupList = new SGroupList();
        if (groupId == null)
        {
            return groupList;
        }
        List<CaAccountRel> caAcctRelList = super.query(CaAccountRel.class, new DBCondition(CaAccountRel.Field.groupId, groupId),
                new DBCondition(CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_BELONG));
        if (CommonUtil.isEmpty(caAcctRelList))
        {
            return null;
        }
        List<SGroup> groups = new ArrayList<SGroup>();
        for (CaAccountRel acctRel : caAcctRelList)
        {
            if (acctRel == null)
            {
                continue;
            }
            SGroup group = this.queryGroupInfo(acctRel.getRelGroupId());
            groups.add(group);
        }
        // SGroup[] groupArr = new SGroup[groups.size()];
        // for (int i = 0; i < groups.size(); i++)
        // {
        // groupArr[i] = groups.get(i);
        // }
        groupList.setGroup_items(groups.toArray(new SGroup[groups.size()]));
        return groupList;
    }

//    /**
//     * 获取群付费账户 luojb 2011-9-28
//     * 
//     * @param groupId
//     * @return
//     * @throws IMSException
//     */
//    public Long queryBillAcctId(Long groupId) throws IMSException
//    {
//     //   CaAccountRel caRel = new CaAccountRel();
//   //     caRel.setAcctId(groupId);
//   //     caRel.setRelationshipType(EnumCodeDefine.ACCOUNT_CA_GCA_RELATION);
//       CaAccountRel caRel = querySingle(CaAccountRel.class,new DBCondition(CaAccountRel.Field.acctId,groupId),
//        		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_CA_GCA_RELATION));
//        if (caRel == null)
//        {
//            return null;
//        }
//        return caRel.getRelAcctId();
//    }

    /**
     * 通过用户编号查询群组账户编号 liuyang 2011-10-14
     */
    public CaAccountRv queryAccountRv(Long resourceId)
    {
//        CaAccountRv rvCon = new CaAccountRv();
//        rvCon.setResourceId(resourceId);
//        rvCon.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
        CaAccountRv rv = super.querySingle(CaAccountRv.class,new DBCondition(CaAccountRv.Field.resourceId,resourceId),
        		new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        if (rv == null)
        {
            return null;
        }
        return rv;
    }

    /**
     * 查询pbx号码对应用户 luojb 2011-11-8
     */
    public CmResIdentity queryPBXUserId(String pbxNumber) throws IMSException
    {
//        RsPbxRouteInfo pbxInfo = new RsPbxRouteInfo();
//        pbxInfo.setInTrunk(pbxNumber);
        RsPbxRouteInfo pbxInfo = querySingle(RsPbxRouteInfo.class,new DBCondition(RsPbxRouteInfo.Field.inTrunk,pbxNumber));
        if (pbxInfo == null)
            return null;
//        CmResIdentity iden = new CmResIdentity();
//        iden.setIdentity(pbxInfo.getBillId());
        CmResIdentity iden = querySingle(CmResIdentity.class,new DBCondition(CmResIdentity.Field.identity,pbxInfo.getBillId()));
        return iden;
    }

    /**
     * @Date hunan 修改封装sMember实体时。可以传入phoneId
     */
    public SMember sMemberTransform(CaAccountRv caAccountRv)
    {
        if (null == caAccountRv)
        {
            return null;
        }

        SMember sMember = new SMember();

        sMember.setPhone_id(caAccountRv.getPhoneId());

        sMember.setShort_phone_id(caAccountRv.getIsdn());
        //@Date 2012-07-19 wangdw5 :#52157 member type（对应ROLE）应该采用传入的参数
        if(caAccountRv.getTitleRoleId() != null)
        {
	        if(caAccountRv.getTitleRoleId() == EnumCodeDefine.TITLEROLEID_VPN_NORMAL)
	        {
	        	sMember.setRole(EnumCodeDefine.BSSBROKER_ROLE_VPN_NORMAL);
	        }
	        else if(caAccountRv.getTitleRoleId() == EnumCodeDefine.TITLEROLEID_VPN_MANAGER)
	        {
	        	sMember.setRole(EnumCodeDefine.BSSBROKER_ROLE_VPN_MANAGER);
	        }
	        else
	        	sMember.setRole(caAccountRv.getTitleRoleId());
        }
        if (caAccountRv.getNumberType() != null)
        {
            sMember.setNumber_type(caAccountRv.getNumberType().intValue());
        }
        // 2011-10-10 hunan add:
        sMember.setStatus(caAccountRv.getStatus());
        if (caAccountRv.getClipFlag() != null)
        {
            sMember.setClip_flag(caAccountRv.getClipFlag().intValue());
        }

        sMember.setDisplay_number(caAccountRv.getDisplayNumber());
        if (caAccountRv.getExpireDate() != null)
        {
            sMember.setExpire_date(DateUtil.formatDate(caAccountRv.getExpireDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }

        // 2011-12-07 新增8个字段
        if (caAccountRv.getCrossgroupIncomingcall() != null)
        {
            sMember.setCrossGroupIncomingCall(caAccountRv.getCrossgroupIncomingcall());
        }
        if (caAccountRv.getCrossgroupOutgoingcall() != null)
        {
            sMember.setCrossGroupOutgoingCall(caAccountRv.getCrossgroupOutgoingcall());
        }
        if (caAccountRv.getIngroupIncomingcall() != null)
        {
            sMember.setInGroupIncomingCall(caAccountRv.getIngroupIncomingcall());
        }
        if (caAccountRv.getIngroupOutgoingcall() != null)
        {
            sMember.setInGroupOutgoingCall(caAccountRv.getIngroupOutgoingcall());
        }
        if (caAccountRv.getOutgroupIncomingcall() != null)
        {
            sMember.setOutGroupIncomingCall(caAccountRv.getOutgroupIncomingcall());
        }
        if (caAccountRv.getOutgroupOutgoingcall() != null)
        {
            sMember.setOutGroupOutgingCall(caAccountRv.getOutgroupOutgoingcall());
        }
        // if(caAccountRv.getCallinright() != null)
        // {
        // sMember.set
        // }
        // if(caAccountRv.getCalloutright() != null)
        // {
        // sMember
        // }
        if (caAccountRv.getSpecIncomingcall() != null)
        {
            sMember.setSpecIncomingCall(caAccountRv.getSpecIncomingcall());
        }
        if (caAccountRv.getSpecOutgoingcall() != null)
        {
            sMember.setSpecOutgoingCall(caAccountRv.getSpecOutgoingcall());
        }

        return sMember;
    }

    /**
     * 根据账户id，用户id查询集团关系 luojb 2012-2-20
     * 
     * @param acctId
     * @param userId
     * @return
     * @throws IMSException
     */
    public CaAccountRv queryRvHierarchy(Long acctId, Long userId) throws IMSException
    {
        return queryRv(acctId, userId, EnumCodeDefine.ACCOUNT_RELATION_RV_HIERARCHY);
    }

    /**
     * 根据用户id查询集团关系 luojb 2012-2-20
     * 
     * @param userId
     * @return
     * @throws IMSException
     */
    public CaAccountRv queryRvHierarchy(Long userId) throws IMSException
    {
        return queryRvHierarchy(null, userId);
    }

    /**
     * 根据用户id查询群关系 luojb 2012-2-20
     * 
     * @param userId
     * @return
     * @throws IMSException
     */
    public CaAccountRv queryRvGroup(Long userId) throws IMSException
    {
        return queryRvGroup(null, userId);
    }

    /**
     * 根据群id，用户id查询群关系 luojb 2012-2-20
     * 
     * @param acctId
     * @param userId
     * @return
     * @throws IMSException
     */
    public CaAccountRv queryRvGroup(Long acctId, Long userId) throws IMSException
    {
        return queryRv(acctId, userId, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
    }

    /**
     * 根据账户id，用户id查询自动群关系 luojb 2012-2-20
     * 
     * @param acctId
     * @param userId
     * @return
     * @throws IMSException
     */
    public CaAccountRv queryRvAutoGroup(Long acctId, Long userId) throws IMSException
    {
        return queryRv(acctId, userId, EnumCodeDefine.ACCOUNT_RELATION_RV_AUTOGROUP);
    }

    /**
     * 根据用户id查询自动群关系 luojb 2012-2-20
     * 
     * @param userId
     * @return
     * @throws IMSException
     */
    public CaAccountRv queryRvAutoGroup(Long userId) throws IMSException
    {
        return queryRvAutoGroup(null, userId);
    }

    private CaAccountRv queryRv(Long acctId, Long userId, Integer relationshipType) throws IMSException
    {
//        CaAccountRv rv = new CaAccountRv();
        List<DBCondition> cond  = new ArrayList<DBCondition>();
//        rv.setResourceId(userId);
        cond.add(new DBCondition(CaAccountRv.Field.resourceId,userId));
        if (acctId != null)
        {
//          rv.setAcctId(acctId);
        	cond.add(new DBCondition(CaAccountRv.Field.acctId,acctId));
        }
        if (relationshipType != null)
        {
            // rv.set
        	//2012-04-27 yangjh  luojb让添加这个
//        	rv.setRelationshipType(relationshipType);
        	cond.add(new DBCondition(CaAccountRv.Field.relationshipType,relationshipType));
        }

        return querySingle(CaAccountRv.class,cond.toArray(new DBCondition[cond.size()]));
    }

    /**
     * 根据userId 返回 所有群成员列表 xieqr 2012-2-23
     * 
     * @param userId
     * @return
     * @throws IMSException
     */
    public List<CaAccountRv> queryGroupAcctRv(Long userId) throws IMSException
    {
//        CaAccountRv rvCon = new CaAccountRv();
//        rvCon.setResourceId(userId);
//        rvCon.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
        return super.query(CaAccountRv.class,new DBCondition(CaAccountRv.Field.resourceId,userId),
        		new DBCondition(CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
    }
    /**
     * 根据userId,title_role_id 返回 所有群成员列表 xieqr 2012-4-24
     * 
     * @param userId
     * @return
     * @throws IMSException
     */
    public List<CaAccountRv> queryGroupAcctRvByUserIdAndRoleId(Long userId,Integer titleRoleId) throws IMSException
    {
//        CaAccountRv rvCon = new CaAccountRv();
//        rvCon.setResourceId(userId);
//        rvCon.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
//        rvCon.setTitleRoleId(null);
        
        List<CaAccountRv> list = query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.resourceId, userId), new DBCondition(
                CaAccountRv.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP),
                new DBCondition(CaAccountRv.Field.titleRoleId, titleRoleId,Operator.NOT_EQUALS));
        
        //EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE  不是主号码的成员都认为是付号码
        return list;
    }
    /**
     * 根据用户编号，群类型查询CaAccountRv 实体 xieqr 2012-2-23
     * 
     * @param userId
     * @param acctType
     * @return
     * @throws IMSException
     */
    public CaAccountRv queryGroupAcctRv(Long userId, Integer acctType) throws IMSException
    {
        List<CaAccountRv> list = queryGroupAcctRvByUserIdAndRoleId(userId,EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE);
        CaAccountRv tmp = null;
        if (CommonUtil.isEmpty(list))
        {
            return null;
        }
        for (CaAccountRv rv : list)
        {
            Long acctId = rv.getAcctId();
            CaAccount acct = querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId), new DBCondition(
                    CaAccount.Field.accountType, acctType));
            if (acct != null && acct.getAcctId() != null)
            {
                tmp = rv;
                break;
            }
        }
       return tmp;

    }



}
