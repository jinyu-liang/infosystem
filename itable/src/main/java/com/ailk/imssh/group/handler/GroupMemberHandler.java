package com.ailk.imssh.group.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jef.database.Batch;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.contact.cmp.ContactCmp;
import com.ailk.imssh.group.cmp.GroupMemberCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.itable.entity.IContact;
import com.ailk.openbilling.persistence.itable.entity.IGroupMember;

/**
 * @Description:处理群组成员信息
 * @author wangyh3
 * @Date 2012-5-14
 * @Date 2012-11-09 wukl 如果是2:网外号码，则RELATIONSHIP_TYPE 填3
 */
public class GroupMemberHandler extends BaseHandler
{

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
         List<IGroupMember> dataList = (List<IGroupMember>) dataArr[0];
//        List<IGroupMember> dataList = init(dataArr[0]);
        if (CommonUtil.isEmpty(dataList))
        {
            return;
        }
        GroupMemberCmp memberCmp = this.getContext().getCmp(GroupMemberCmp.class);
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        for (IGroupMember member : dataList)
        {
            if (member.getValidDate().before(validDate))
            {
                member.setValidDate(validDate);
            }

            if (member.getUserId() != null && member.getUserId() != 0)
            {
                ITableUtil.setValue2ContextHolder(null, null, member.getUserId());
                memberCmp.deleteByCondition_noInsert(CaResGrpRevs.class, validDate, new DBCondition(
                        CaResGrpRevs.Field.resourceId, member.getUserId()),
                        new DBCondition(CaResGrpRevs.Field.acctId, member.getGroupId()), new DBCondition(
                                CaResGrpRevs.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP), new DBCondition(
                                CaResGrpRevs.Field.expireDate, validDate, Operator.GREAT));
            }

        }
        ITableUtil.cleanRequestParamter();
        memberCmp.deleteByCondition_noInsert(CaAccountRv.class, validDate,
                new DBCondition(CaAccountRv.Field.acctId, context.getAcctId()), new DBCondition(
                        CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP), new DBCondition(
                        CaAccountRv.Field.expireDate, validDate, Operator.GREAT));
    }

    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
         List<? extends DataObject> dataList = dataArr[0];
//        List<IGroupMember> dataList = init(dataArr[0]);
        GroupMemberCmp memberCmp = this.getContext().getCmp(GroupMemberCmp.class);
        List<CaAccountRv> rvList = new ArrayList<CaAccountRv>();
        List<CaResGrpRevs> grpRevsList = new ArrayList<CaResGrpRevs>();
        Map<Long, Set<Long>> memberMap = new HashMap<Long, Set<Long>>();
        Map<Long, Set<String>> exteriorMap = new HashMap<Long, Set<String>>();
        Map<String, List<Date>> userIdValidDateMap = new HashMap<String, List<Date>>();
        Map<String, List<Date>> phoneIdValidDateMap = new HashMap<String, List<Date>>();
        RouterCmp baseCmp= this.context.getCmp(RouterCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {
            IGroupMember iMember = (IGroupMember) dataList.get(j);
            Long acctId=baseCmp.queryAcctIdByUserId(iMember.getGroupId());
           // ITableUtil.setValue2ContextHolder(null, acctId, null);
            ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, acctId);
            addMemberToMap(iMember, memberMap, exteriorMap,userIdValidDateMap,phoneIdValidDateMap);
            int operType = iMember.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT: // 新增
                rvList.add(createCaAccountRv(iMember));
                
                //user_id不为空并且不为0
                if (iMember.getNumberType() !=2 && iMember.getUserId() != null && iMember.getUserId() != 0)
                {   
                    
                    grpRevsList.add(insertCaResGrpRevs(iMember));
                }

                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE: // 修改
                memberCmp.updateGroupMember(iMember);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE: // 删除
                memberCmp.deleteGroupMember(iMember);
                break;
            default:
                break;
            }
        }
        memberCmp.insertBatch(rvList);

        if (CommonUtil.isNotEmpty(grpRevsList))
        {
            try
            {   
            	CaResGrpRevs caResGrpRevs=grpRevsList.get(0);
                Long  acctId=baseCmp.queryAcctIdByUserId(caResGrpRevs.getResourceId());
                ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, acctId);
                Batch<CaResGrpRevs> batch = DBUtil.getDBClient().startBatchInsert(CaResGrpRevs.class);
                batch.add(grpRevsList);
                batch.setGroupForPartitionTable(true);
                batch.commit();
            }
            catch (Exception e)
            {
                throw IMSUtil.throwBusiException(e);
            }
        }
        
        // 处理路由信息

        context.getCmp(RouterCmp.class).publicGroupMemberRoute(memberMap, exteriorMap,userIdValidDateMap,phoneIdValidDateMap);
        ITableUtil.cleanRequestParamter();

        // 设置回来
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
    }

    private CaAccountRv createCaAccountRv(IGroupMember iGroupMember)
    {
        CaAccountRv caAccountRv = new CaAccountRv();
        caAccountRv.setCreateDate(this.getContext().getCommitDate());
        caAccountRv.setSoDate(this.getContext().getCommitDate());
        caAccountRv.setAcctId(iGroupMember.getGroupId());
        // 网外号码填0
        if (iGroupMember.getUserId() != null)
        {
            caAccountRv.setResourceId(iGroupMember.getUserId());
        }
        else
        {
            caAccountRv.setResourceId(0L);
        }
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
        caAccountRv.setValidDate(iGroupMember.getValidDate());
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
        return caAccountRv;
    }

    private CaResGrpRevs insertCaResGrpRevs(IGroupMember iGroupMember)
    {
        CaResGrpRevs caResGrpRevs = new CaResGrpRevs();
        caResGrpRevs.setResourceId(iGroupMember.getUserId());// 群成员编号
        caResGrpRevs.setAcctId(iGroupMember.getGroupId());// 群账号
        caResGrpRevs.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);
        caResGrpRevs.setCreateDate(iGroupMember.getCommitDate());
        caResGrpRevs.setValidDate(iGroupMember.getValidDate());
        caResGrpRevs.setExpireDate(iGroupMember.getExpireDate());
        caResGrpRevs.setSoNbr(context.getSoNbr());
        caResGrpRevs.setSoDate(iGroupMember.getCommitDate());
        return caResGrpRevs;

    }

    /**
     * lijc3 2013-7-17 保存所有的群组和成员信息
     * 
     * @param groupId
     * @param userId
     * @param memberMap
     */
    private void addMemberToMap(IGroupMember iMember, Map<Long, Set<Long>> memberMap, Map<Long, Set<String>> exteriorMap,Map<String, List<Date>>  userIdValidDateMap,Map<String, List<Date>>  phoneIdValidDateMap)
    {
        Long groupId = iMember.getGroupId();
        Long userId = iMember.getUserId();
        String phoneId = iMember.getPhoneId();
        if (iMember.getNumberType().intValue() == 2)
        {
            Set<String> phoneSet = exteriorMap.get(groupId);
            if (phoneSet == null)
            {
                phoneSet = new HashSet<String>();
                exteriorMap.put(groupId, phoneSet);
            }
            phoneSet.add(phoneId);
            addPhoneIdvalidDateToMap(iMember, phoneIdValidDateMap);
        }
        else
        {
            Set<Long> userSet = memberMap.get(groupId);
            if (userSet == null)
            {
                userSet = new HashSet<Long>();
                memberMap.put(groupId, userSet);
            }
            userSet.add(userId);
            addUserIdvalidDateToMap(iMember, userIdValidDateMap);
        }
        context.setValidDate(iMember.getValidDate());
    }
    
    private void addUserIdvalidDateToMap(IGroupMember iMember, Map<String, List<Date>>  validDateMap)
    {
    	Long groupId=iMember.getGroupId();
    	Long userId=iMember.getUserId();
    	String groupIdAnduserId=groupId.toString()+userId.toString();
    	Date validDate=iMember.getValidDate();
    	List<Date>  dateList=validDateMap.get(groupIdAnduserId);
    	if(CommonUtil.isEmpty(dateList)){
    		dateList=new ArrayList<Date>();
    	}
    	dateList.add(validDate);
    	validDateMap.put(groupIdAnduserId, dateList);
    }
    private void addPhoneIdvalidDateToMap(IGroupMember iMember,Map<String, List<Date>>  validDateMap)
    {
    	Long groupId=iMember.getGroupId();
    	String phoneId=iMember.getPhoneId();
    	String groupIdAndphoneId=groupId.toString()+phoneId;
    	Date validDate=iMember.getValidDate();
    	List<Date>  dateList=validDateMap.get(groupIdAndphoneId);
    	if(CommonUtil.isEmpty(dateList)){
    		dateList=new ArrayList<Date>();
    	}
    	dateList.add(validDate);
    	validDateMap.put(groupIdAndphoneId, dateList);
    }
    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    	/*
        List<? extends DataObject> dataList = dataArr[0];
        List<IGroupMember> iMemberList = new ArrayList<IGroupMember>();
        for (int i = 0; i < dataList.size(); i++)
        {

            IGroupMember iGroupMember = (IGroupMember) dataList.get(i);
            iGroupMember.setGroupId(ITableUtil.convertGroupId(iGroupMember.getGroupId()));
            iMemberList.add(iGroupMember);
        }
        */
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		//发布路由不处理
	}
}
