package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.GroupQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Group3hBean;
import com.ailk.ims.ims3h.IMS3hTree;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SChangeGroupInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.SGroup;
import com.ailk.openbilling.persistence.imsintf.entity.SMember;
import com.ailk.openbilling.persistence.imsintf.entity.SMemberOperList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SpecialNumber;
import com.ailk.openbilling.persistence.imsintf.entity.SpecialNumberList;
import com.ailk.openbilling.persistence.imsintf.entity.SpecialNumberOper;
import com.ailk.openbilling.persistence.imsintf.entity.SpecialNumberOperList;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description: 群相关操作组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Date 2011-8-24 2011-08-24 wuyujie : 新增deleteGroupById方法
 * @Date 2012-3-22 xieqr :增加冲浪E家亲活动群处理
 * @Date 2012-03-30 lijc3 如果phoneId为空,设置为-1
 * @Date 2012-03-30 lijc3 如果phoneId为空 不设置为查询条件
 * @Date 2012-04-02 lijc3 上海需求不需要校验能加入的群个数
 * @Date 2012-04-16 luojb 创建特殊号码群信息
 * @Date 2012-4-26 tangjl5 Task #44705 创建VPN的callscreen产品。
 * @Date 2012-05-02 wuyujie : [44101]PBX_RealPhoneNumber需求改造，传入的是手机号码而非PBX号码
 * @Date 2012-05-03 lijc3 二次议价参数增加price_plan_id
 * @Date 2012-05-12 wangyh3 Task #45545 表CA_ACCOUT_GROUP 中新增pay_acct_id 字段
 * @Date 2012-05-31 luojb 为自动创建群的账户实例化群信息
 * @Date 2012-06-25 yangjh createSpecVpnInfo增加accountType的赋值，避免上发MDB空指针
 * @date 2012-07-06 luojb 删除SMember.espace_flag的使用#50769
 * @Date 2012-07-19 wangdw5 :#52157 member type（对应ROLE）应该采用传入的参数
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @Date 2012-09-19 yugb :[57408]API中字段清空方案
 * @date 2012-10-15 luojb  #57570 群成员反向表
 */
public class VPNComponent extends GroupQuery
{
    public VPNComponent()
    {
    }

    /**
     * 创建特殊号码虚账户,及与群id之间的关系 luojb 2011-11-11
     */
    public long createSpecNbrGCA(Long groupId) throws IMSException
    {
        SAccount account = new SAccount();
        account.setAcct_class(EnumCodeDefine.ACCOUNT_CLASS_GCA);
        account.setAcct_type(EnumCodeDefine.ACCOUNT_TYPE_SPECNBR_GCA);
        account.setBalance_type(EnumCodeDefine.BALANCE_TYPE_USER);

        CaAccount gca = context.getComponent(AccountComponent.class).createCaAccount(account);
        long gcaId = gca.getAcctId();
        context.getComponent(AccountRelationComponent.class).createVpnSpecNbrRelation(groupId, gcaId);
        // 2012-4-16 创建特殊号码群信息 luojb
        createSpecVpnInfo(gca);

        return gcaId;
    }

    /**
     * 创建特殊号码虚账户群信息 luojb 2012-4-16
     * 
     * @param account
     */
    private void createSpecVpnInfo(CaAccount account)
    {
        CaAccountGroup vpn = new CaAccountGroup();
        vpn.setAcctId(account.getAcctId());
        vpn.setValidDate(account.getValidDate());
        vpn.setExpireDate(account.getExpireDate());
        // 2012-06-25 yangjh 增加accountType的赋值，避免上发MDB空指针
        vpn.setAccountType(account.getAccountType());
        vpn.setCreateDate(context.getRequestDate());
        vpn.setSoNbr(context.getSoNbr());
        vpn.setSoDate(context.getRequestDate());
        vpn.setIngroupIncomingcall(1);
        vpn.setIngroupOutgoingcall(1);
        vpn.setOutgroupIncomingcall(1);
        vpn.setOutgroupOutgoingcall(1);
        vpn.setCrossgroupIncomingcall(1);
        vpn.setCrossgroupOutgoingcall(1);
        vpn.setSpecIncomingcall(1);
        vpn.setSpecOutgoingcall(1);

        insert(vpn);
    }

    /**
     * 创建群信息 luojb 2011-9-26
     */
    public CaAccountGroup createGroupInfo(SGroup groupInfo) throws IMSException
    {
        AccountRelationComponent caRelCmp = context.getComponent(AccountRelationComponent.class);
        Long billAcctId = groupInfo.getBillable_acct_id();
        long groupId = groupInfo.getGroup_id();
        Short groupType = groupInfo.getGroup_type();
        String validDate = groupInfo.getValid_date();
        String expireDate = groupInfo.getExpire_date();
        Integer clipFlag = groupInfo.getCLIP_flag();
        String contactPhone = groupInfo.getContact_phone();
        Integer espaceFlage = groupInfo.getEspace_flag();
        Integer ocsRoutingFlag = groupInfo.getOcs_routing_flag();
        Integer outGoingCSFlag = groupInfo.getOutGoing_cs_flag();

        Integer maxUserNbr = groupInfo.getMax_group_number();
        Integer maxCugNbr = SysUtil.getInt(SysCodeDefine.busi.CUG_DEFAULT_MAX_NUMBER);
        String mainPhone = groupInfo.getMain_phone_id();
        Long parentGroupId = groupInfo.getParent_group_id();
        Long mainPhoneUserId = null;
        // 验证主号码
        if (mainPhone != null)
        {
            // 改用3hbean
            mainPhoneUserId = this.getContext().get3hTree().loadUser3hBean(mainPhone).getUserId();
        }

        // modify by wangyh3 20120427 群类型兼容
        CaAccount groupCA = null;
        if ((groupType == EnumCodeDefine.ACCOUNT_TYPE_CUG) && ProjectUtil.is_TH_AIS())
        {// 内部群
        	 //2012-08-28 linzt 整理组件方法
        	 Group3hBean parentGroup=context.get3hTree().loadGroup3hBean(parentGroupId);
        	 CaAccountGroup fatherVpn = parentGroup.getGroup();
        	 CaAccount fatherAccount =parentGroup.getAccount();
        	 Integer fatherGroupType = fatherAccount.getAccountType();
            if (fatherGroupType == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CREATEGROUP_PARENTGROUP_TYPE_UNKNOWN, parentGroupId);
            }
            // 父群是否已经是内部群
            if (fatherGroupType.shortValue() == EnumCodeDefine.ACCOUNT_TYPE_CUG)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CREATEGROUP_PARENTGROUP_IS_CUG, parentGroupId);
            }
            Long fatherMaxCugNbr = fatherVpn.getMaxclosenum();
            if (fatherMaxCugNbr != null)
            {
                List<Long> cugList = caRelCmp.queryAllCUG(parentGroupId);
                if (!CommonUtil.isEmpty(cugList) && cugList.size() == fatherMaxCugNbr.intValue())
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.CREATEGROUP_TOUCH_MAX_CUG, parentGroupId, fatherMaxCugNbr);
                }
            }
            // 失效时间不超过父群
            Date fatherExpireDate = fatherVpn.getExpireDate();
            if (CommonUtil.isValid(expireDate))
            {
                Date expire = IMSUtil.formatExpireDate(expireDate);
                if (expire.after(fatherExpireDate))
                {
                    expireDate = DateUtil.formatDate(fatherExpireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
                }
            }
            else
            {
                expireDate = DateUtil.formatDate(fatherExpireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
            }

            groupCA = caRelCmp.createGroup(groupId, billAcctId, validDate, expireDate, groupType);

            // 主号码必须是父群内成员
            if (mainPhoneUserId != null && !isMemberInGroup(mainPhoneUserId, parentGroupId))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN_CUG, mainPhoneUserId,
                        parentGroupId, groupCA.getAcctId());
            }
            // 内部群不能再有内部群
            maxCugNbr = 0;
            // 内部群最大成员数与父群保持一致
            maxUserNbr = fatherVpn.getMaxusers().intValue();

            // 创建内部群关系
            caRelCmp.createCUGRelation(parentGroupId, groupCA.getAcctId(), validDate, expireDate);
        }
        else
        {
            if (mainPhoneUserId != null && ProjectUtil.is_TH_AIS())
            {
                checkJoinGroup(mainPhoneUserId, mainPhone, groupType);
            }
            groupCA = caRelCmp.createGroup(groupId, billAcctId, validDate, expireDate, groupType);
        }

        // 创建群信息
        CaAccountGroup vpn = new CaAccountGroup();
        vpn.setAcctId(groupId);

        // 最大群成员数
        if (maxUserNbr != null)
        {
            vpn.setMaxusers(maxUserNbr.longValue());
        }
        else
        {
            vpn.setMaxusers(CommonUtil.IntegerToLong(SysUtil.getInt(SysCodeDefine.busi.GROUP_MEMBER_DEFAULT_MAX_NUMBER)));
        }
        // @Date 2012-6-11 tangjl5 设置groupType
        vpn.setAccountType(CommonUtil.ShortToInteger(groupType));

        // 最大内部群数
        if (maxCugNbr != null)
        {
            vpn.setMaxclosenum(maxCugNbr.longValue());
        }

        // 设置群主号码
        if (mainPhoneUserId != null)
        {
            setMainPhone(groupId, mainPhoneUserId, mainPhone, validDate, expireDate);
            vpn.setMemberNumber(1L);
        }
        else
        {
            vpn.setMemberNumber(0L);
        }
        if (CommonUtil.isValid(groupInfo.getGroup_name()))
        {
            vpn.setGroupName(groupInfo.getGroup_name());
        }
        //LIJC add
        vpn.setFreeresShareFlag(1);
        vpn.setCreateDate(context.getRequestDate());
        vpn.setSoDate(IMSUtil.formatDate(context.getOper().getSo_date()));
        vpn.setSoNbr(context.getSoNbr());
        vpn.setValidDate(IMSUtil.formatDate(validDate, context.getRequestDate()));
        vpn.setExpireDate(IMSUtil.formatExpireDate(expireDate));

        // 2011-09-17 luojb 增加字段
        if (clipFlag != null)
        {
            vpn.setClipFlag(CommonUtil.IntegerToLong(clipFlag));
        }
        if (contactPhone != null)
        {
            vpn.setContactPhone(contactPhone);
        }
        if (espaceFlage != null)
        {
            vpn.setEspaceFlag(CommonUtil.IntegerToLong(espaceFlage));
        }
        if (ocsRoutingFlag != null)
        {
            vpn.setOcsRoutingFlag(CommonUtil.IntegerToLong(ocsRoutingFlag));
        }
        if (outGoingCSFlag != null)
        {
            vpn.setOutgoingCsFlag(CommonUtil.IntegerToLong(outGoingCSFlag));
        }

        // 增加呼叫控制字段2011-12-06
        if (groupInfo.getInGroupIncomingCall() != null)
            vpn.setIngroupIncomingcall(groupInfo.getInGroupIncomingCall());
        else
            vpn.setIngroupIncomingcall(1);
        if (groupInfo.getInGroupOutgoingCall() != null)
            vpn.setIngroupOutgoingcall(groupInfo.getInGroupOutgoingCall());
        else
            vpn.setIngroupOutgoingcall(1);
        if (groupInfo.getOutGroupIncomingCall() != null)
            vpn.setOutgroupIncomingcall(groupInfo.getOutGroupIncomingCall());
        else
            vpn.setOutgroupIncomingcall(1);
        if (groupInfo.getOutGroupOutgingCall() != null)
            vpn.setOutgroupOutgoingcall(groupInfo.getOutGroupOutgingCall());
        else
            vpn.setOutgroupOutgoingcall(1);
        if (vpn.getCrossgroupIncomingcall() != null)
            vpn.setCrossgroupIncomingcall(vpn.getCrossgroupIncomingcall());
        else
            vpn.setCrossgroupIncomingcall(1);
        if (groupInfo.getCrossGroupOutgoingCall() != null)
            vpn.setCrossgroupOutgoingcall(groupInfo.getCrossGroupOutgoingCall());
        else
            vpn.setCrossgroupOutgoingcall(1);
        if (groupInfo.getSpecIncomingCall() != null)
            vpn.setSpecIncomingcall(groupInfo.getSpecIncomingCall());
        else
            vpn.setSpecIncomingcall(1);
        if (groupInfo.getSpecOutgoingCall() != null)
            vpn.setSpecOutgoingcall(groupInfo.getSpecOutgoingCall());
        else
            vpn.setSpecOutgoingcall(1);

        // wangyh3 2012-05-12 #45545 表CA_ACCOUT_GROUP 中新增pay_acct_id 字段
        //vpn.setPayAcctId(billAcctId);

        insert(vpn);
        return vpn;
    }

    /**
     * 设置主号码 luojb 2011-9-26
     */
    private void setMainPhone(long groupId, long userId, String mainPhoneId, String validDate, String expireDate)
            throws IMSException
    {
        SMember member = new SMember();
        member.setRole(EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE);
        member.setShort_phone_id(SysUtil.getString(SysCodeDefine.busi.GROUP_MANAGER_SHORT_PHONE));
        member.setValid_date(validDate);
        member.setExpire_date(expireDate);
        member.setPhone_id(mainPhoneId);
        member.setNumber_type(CommonUtil.ShortToInteger(EnumCodeDefine.VPN_NUMBER_TYPE_BOS));
        addMember(groupId, member, userId);
    }

    /**
     * 计算并更新群成员数 luojb 2011-9-26
     */
    private void calMemberNumber(CaAccountGroup vpn, long count) throws IMSException
    {
        long curMemNbr = vpn.getMemberNumber();
        vpn.setMemberNumber(curMemNbr + count);
        vpn.setSoNbr(context.getSoNbr());
        vpn.setSoDate(context.getRequestDate());
        vpn.setValidDate(context.getRequestDate());

        // CaAccountGroup where = new CaAccountGroup();
        // where.setAcctId(vpn.getAcctId());
        updateByCondition(vpn, new DBCondition(CaAccountGroup.Field.acctId, vpn.getAcctId()));

        // 更新群产品的计价参数
        updateGroupProdPriceParam(vpn.getAcctId(), curMemNbr + count);
    }

    /**
     * 群成员数变更后，更新群产品计价参数 luojb 2011-12-7
     */
    private void updateGroupProdPriceParam(long groupId, long count)
    {
        ProductQuery pq = context.getComponent(ProductQuery.class);
        List<CoProd> prodList = pq.queryProdListByGroupId(groupId);
        if (CommonUtil.isEmpty(prodList))
            return;
        List<CoProdPriceParam> insertBatch = new ArrayList<CoProdPriceParam>();
        for (CoProd prod : prodList)
        {
            Long prodId = prod.getProductId();
            CoProdPriceParam param = pq
                    .queryProdPriceParam(prod.getProductId(), SpecCodeDefine.GROUP_MEMBER_NUMBER_PROD_PARAM_ID);
            if (param == null)
            {
                CoProdPriceParam insertParam = new CoProdPriceParam();
                // @Date 2012-05-03 lijc3 二次议价参数增加price_plan_id
                //@Date 2012-10-08  gaoqc5 #60541  co_prod_price_param表的price_plan_id字段去掉
//                insertParam.setPricingPlanId(prod.getPricingPlanId());
                insertParam.setProductId(prodId);
                insertParam.setParamId(SpecCodeDefine.GROUP_MEMBER_NUMBER_PROD_PARAM_ID);
                insertParam.setParamValue(CommonUtil.long2String(count));
                insertParam.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
                insertParam.setCreateDate(context.getRequestDate());
                insertParam.setSoNbr(context.getSoNbr());
                insertParam.setSoDate(context.getRequestDate());
                insertParam.setValidDate(context.getRequestDate());
                insertParam.setExpireDate(prod.getExpireDate());
                insertParam.setObjectId(prod.getObjectId());
                insertParam.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_GCA);
                insertBatch.add(insertParam);
            }
            else
            {
                // CoProdPriceParam where = new CoProdPriceParam();
                // where.setProductId(prodId);
                // where.setParamId(param.getParamId());
                // where.setObjectId(groupId);
                // where.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_GCA);

                CoProdPriceParam updateParam = new CoProdPriceParam();
                updateParam.setParamValue(CommonUtil.long2String(count));
                updateParam.setSoNbr(context.getSoNbr());
                updateParam.setValidDate(context.getRequestDate());

                updateByCondition(updateParam, new DBCondition(CoProdPriceParam.Field.paramId, param.getParamId()),
                        new DBCondition(CoProdPriceParam.Field.productId, prodId), new DBCondition(
                                CoProdPriceParam.Field.objectId, groupId), new DBCondition(CoProdPriceParam.Field.objectType,
                                EnumCodeDefine.PROD_OBJECTTYPE_GCA));
            }
        }
        insertBatch(insertBatch);

    }

    /**
     * luojb 2011-9-26
     */
    private boolean isShortPhoneExist(long groupId, String shortPhone) throws IMSException
    {
        return querySingle(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
                CaAccountRv.Field.isdn, shortPhone)) != null;
    }

    /**
     * 修改群成员信息
     * 
     * @author luojb
     */
    private void modMember(long groupId, SMember member, long userId) throws IMSException
    {
        CaAccountRv rvOld = queryAcctRvByGrpIdAndUserId(groupId, userId);
        if (rvOld == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_MEMBER_NOTEXIST, member.getPhone_id(), groupId);
        }

        CaAccountRv rvUpdate = new CaAccountRv();
        String shortPhoneId = member.getShort_phone_id();
        String oldShortPhoneId = rvOld.getIsdn();
        // 短号有传入且与旧的不同，则修改（空串标识删除短号）
        if (shortPhoneId != null && !shortPhoneId.equals(oldShortPhoneId))
        {
            if(!shortPhoneId.equals("") && isShortPhoneExist(groupId, shortPhoneId))
                IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_SHORTPHONE_EXIST, shortPhoneId, groupId);
            rvUpdate.setIsdn(shortPhoneId);
        }
        
        // 变更成员角色
        Integer titleRoleId = member.getRole();
        if (titleRoleId != null)
        {
            // 管理员变更为普通成员
            if (titleRoleId == EnumCodeDefine.MANAGEGROUPMEMBER_COMMON)
            {
                if (rvOld.getTitleRoleId().equals(EnumCodeDefine.TITLEROLEID_VPN_MANAGER))
                {
                    rvUpdate.setTitleRoleId(EnumCodeDefine.TITLEROLEID_VPN_NORMAL);
                }
            }
            else if (titleRoleId == EnumCodeDefine.MANAGEGROUPMEMBER_MANAGER)
            {
                // 普通成员变更为管理员 是否要限制管理员个数 2011-09-22 luojb
                if (rvOld.getTitleRoleId().equals(EnumCodeDefine.TITLEROLEID_VPN_NORMAL))
                {
                    rvUpdate.setTitleRoleId(EnumCodeDefine.TITLEROLEID_VPN_MANAGER);
                }
            }
        }
        Integer clipFlag = member.getClip_flag();
        String displayNumber = member.getDisplay_number();
        if (clipFlag != null)
            rvUpdate.setClipFlag(CommonUtil.IntegerToLong(clipFlag));
        if (displayNumber != null)
            rvUpdate.setDisplayNumber(displayNumber);

        // 增加呼叫控制字段2011-12-06
        if (member.getInGroupIncomingCall() != null)
            rvUpdate.setIngroupIncomingcall(member.getInGroupIncomingCall());
        if (member.getInGroupOutgoingCall() != null)
            rvUpdate.setIngroupOutgoingcall(member.getInGroupOutgoingCall());
        if (member.getOutGroupIncomingCall() != null)
            rvUpdate.setOutgroupIncomingcall(member.getOutGroupIncomingCall());
        if (member.getOutGroupOutgingCall() != null)
            rvUpdate.setOutgroupOutgoingcall(member.getOutGroupOutgingCall());
        if (member.getCrossGroupIncomingCall() != null)
            rvUpdate.setCrossgroupIncomingcall(member.getCrossGroupIncomingCall());
        if (member.getCrossGroupOutgoingCall() != null)
            rvUpdate.setCrossgroupOutgoingcall(member.getCrossGroupOutgoingCall());
        if (member.getSpecIncomingCall() != null)
            rvUpdate.setSpecIncomingcall(member.getSpecIncomingCall());
        if (member.getSpecOutgoingCall() != null)
            rvUpdate.setSpecOutgoingcall(member.getSpecOutgoingCall());

        updateByCondition(rvUpdate, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
                CaAccountRv.Field.resourceId, userId), new DBCondition(CaAccountRv.Field.relationshipType,
                EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
    }

    /**
     * 增加群成员
     */
    private void addMember(long groupId, SMember member, long userId) throws IMSException
    {
        CaAccountRv rv = new CaAccountRv();
        Date validDate = IMSUtil.formatDate(member.getValid_date(), context.getRequestDate());
        Date expireDate = IMSUtil.formatExpireDate(member.getExpire_date());

        rv.setAcctId(groupId);
        rv.setResourceId(userId);
        rv.setPhoneId(member.getPhone_id());
        rv.setNumberType(CommonUtil.IntegerToLong(member.getNumber_type()));
        rv.setIsdn(member.getShort_phone_id());
        rv.setTitleRoleId(member.getRole());
        rv.setCreateDate(context.getRequestDate());
        rv.setValidDate(validDate);
        rv.setExpireDate(expireDate);
        rv.setSoNbr(context.getSoNbr());
        rv.setSoDate(context.getRequestDate());
        // 2011-09-27 luojb增加群成员状态
        rv.setStatus(EnumCodeDefine.ACCOUNT_ACTIVE);
        rv.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);

        // 2011-09-17 luojb 增加字段
        Integer clipFlag = member.getClip_flag();
        String displayNumber = member.getDisplay_number();

        if (clipFlag != null)
            rv.setClipFlag(CommonUtil.IntegerToLong(clipFlag));
        if (displayNumber != null)
            rv.setDisplayNumber(displayNumber);

        // 增加呼叫控制字段2011-12-06
        if (member.getInGroupIncomingCall() != null)
            rv.setIngroupIncomingcall(member.getInGroupIncomingCall());
        if (member.getInGroupOutgoingCall() != null)
            rv.setIngroupOutgoingcall(member.getInGroupOutgoingCall());
        if (member.getOutGroupIncomingCall() != null)
            rv.setOutgroupIncomingcall(member.getOutGroupIncomingCall());
        if (member.getOutGroupOutgingCall() != null)
            rv.setOutgroupOutgoingcall(member.getOutGroupOutgingCall());
        if (member.getCrossGroupIncomingCall() != null)
            rv.setCrossgroupIncomingcall(member.getCrossGroupIncomingCall());
        if (member.getCrossGroupOutgoingCall() != null)
            rv.setCrossgroupOutgoingcall(member.getCrossGroupOutgoingCall());
        if (member.getSpecIncomingCall() != null)
            rv.setSpecIncomingcall(member.getSpecIncomingCall());
        if (member.getSpecOutgoingCall() != null)
            rv.setSpecOutgoingcall(member.getSpecOutgoingCall());

        insert(rv);
        // 2012-10-15 luojb  #57570 创建群成员反向同步表信息
        insertGrpRevs(rv);
    }
    
    /**
     * 群成员反向同步表
     * luojb 2012-10-15
     * @param rv
     */
    private void insertGrpRevs(CaAccountRv rv)
    {
        CaResGrpRevs rev = new CaResGrpRevs();
        rev.setAcctId(rv.getAcctId());
        rev.setCreateDate(rv.getCreateDate());
        rev.setExpireDate(rv.getExpireDate());
        rev.setRelationshipType(rv.getRelationshipType());
        rev.setResourceId(rv.getResourceId());
        rev.setSoDate(rv.getSoDate());
        rev.setSoNbr(rv.getSoNbr());
        rev.setValidDate(rv.getValidDate());
        
        insert(rev);
    }
    
    /**
     * 群成员反向同步表
     * luojb 2012-10-15
     * @param rvList
     */
    private void insertGrpRevsList(List<CaAccountRv> rvList)
    {
        if(CommonUtil.isEmpty(rvList))
            return;
        List<CaResGrpRevs> revList = new ArrayList<CaResGrpRevs>();
        for(CaAccountRv rv:rvList)
        {
            CaResGrpRevs rev = new CaResGrpRevs();
            rev.setAcctId(rv.getAcctId());
            rev.setCreateDate(rv.getCreateDate());
            rev.setExpireDate(rv.getExpireDate());
            rev.setRelationshipType(rv.getRelationshipType());
            rev.setResourceId(rv.getResourceId());
            rev.setSoDate(rv.getSoDate());
            rev.setSoNbr(rv.getSoNbr());
            rev.setValidDate(rv.getValidDate());
            revList.add(rev);
        }
        insertBatch(revList);
    }

    public void addSpecNumberList(Long gcaId, String[] specNbrList, String validDate, String expireDate)
    {
        if (CommonUtil.isEmpty(specNbrList))
        {
            return;
        }
        List<CaAccountRv> rvList = new ArrayList<CaAccountRv>();
        for (String phoneId : specNbrList)
        {
            rvList.add(parseSpecNumberRv(gcaId, phoneId, validDate, expireDate));
        }
        insertBatch(rvList);
    }

    /**
     * 添加特殊号码 luojb 2011-11-11
     */
    public void addSpecNumberList(Long gcaId, SpecialNumberList specNbrList, String validDate, String expireDate)
    {
        if (specNbrList == null || CommonUtil.isEmpty(specNbrList.getSpecialNumberList_Item()))
        {
            return;
        }
        List<CaAccountRv> rvList = new ArrayList<CaAccountRv>();
        Set<String> phoneSet = new HashSet<String>();
        for (SpecialNumber specNbr : specNbrList.getSpecialNumberList_Item())
        {
            String phoneId = specNbr.getPhone_id();
            if (phoneSet.contains(phoneId))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPPROD_SPECNBR_SAME, phoneId);
            }
            phoneSet.add(phoneId);
            rvList.add(parseSpecNumberRv(gcaId, phoneId, validDate, expireDate));
        }
        insertBatch(rvList);
    }

    /**
     * 添加一个特殊号码 luojb 2011-11-11
     */
    private CaAccountRv parseSpecNumberRv(Long gcaId, String phoneId, String validDate, String expireDate) throws IMSException
    {
        Long userId = querySpecNbrUser(phoneId);
        Date valid = IMSUtil.formatDate(validDate);
        Date expire = IMSUtil.formatExpireDate(expireDate);

        CaAccountRv rv = new CaAccountRv();
        rv.setAcctId(gcaId);
        if (userId != null)
        {
            rv.setResourceId(userId);
        }
        else
        {
            rv.setResourceId(-1L);// 外部号码没有用户编号默认设置为-1
        }
        rv.setPhoneId(phoneId);
        rv.setStatus(EnumCodeDefine.ACCOUNT_ACTIVE);
        rv.setValidDate(valid);
        rv.setExpireDate(expire);
        rv.setCreateDate(context.getRequestDate());
        rv.setSoDate(context.getRequestDate());
        rv.setSoNbr(context.getSoNbr());
        rv.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_SPECIALNBR);

        return rv;
    }

    /**
     * 删除一个特殊号码 luojb 2011-11-11
     */
    private void delSpecNumber(Long gcaId, String phoneId) throws IMSException
    {
        // CaAccountRv rv = new CaAccountRv();
        // rv.setAcctId(gcaId);
        // rv.setPhoneId(phoneId);

        deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, gcaId), new DBCondition(
                CaAccountRv.Field.phoneId, phoneId));
    }

    /**
     * 管理特殊号码 luojb 2011-11-11
     */
    public void operSpecNumber(Long groupId, SpecialNumberOperList spNbrOperList, String validDate, String expireDate)
            throws IMSException
    {
        // 查询特殊号码虚账户
        Long gcaId = querySpecNbrGCAId(groupId);
        Set<String> addSpecNbrSet = new HashSet<String>();
        for (SpecialNumberOper oper : spNbrOperList.getSpecialNumberOperList())
        {
            Short operType = oper.getOper_type();
            String phoneId = oper.getPhone_id();

            if (operType == null)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "operType");
            }
            else if (operType.equals(EnumCodeDefine.OPER_TYPE_ADD))
            {
                if (addSpecNbrSet.contains(phoneId))
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPPROD_SPECNBR_SAME, phoneId);
                }
                addSpecNbrSet.add(phoneId);
                if (gcaId == null)
                {
                    gcaId = createSpecNbrGCA(groupId);
                }
                //@Date 2012-10-22 yangjh : On_Site Defect #62149 修改查询特殊号码调用方法
                if (isSpecMemInGroup(phoneId, gcaId))
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPPROD_SPECNBR_EXIST, phoneId, groupId);
                }
            }
            else if (operType.equals(EnumCodeDefine.OPER_TYPE_DELETE))
            {
                if (gcaId == null)
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPINFO_SPEC_NBR_NOT_EXIST, groupId, phoneId);
                }
                if (!isSpecMemInGroup(phoneId, gcaId))
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPPROD_SPECNBR_NOTEXIST, phoneId, groupId);
                }
                delSpecNumber(gcaId, phoneId);
            }
            else
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "operType", "0,1");
            }
        }
        addSpecNumberList(gcaId, addSpecNbrSet.toArray(new String[addSpecNbrSet.size()]), validDate, expireDate);
    }

    /**
     * 删除群成员 luojb 2011-9-26
     */
    private void delMember(long groupId, long userId) throws IMSException
    {
        List<Long> groupIds = context.getComponent(AccountRelationComponent.class).queryAllCUG(groupId);
        if (CommonUtil.isEmpty(groupIds))
        {
            groupIds = new ArrayList<Long>();
        }
        groupIds.add(groupId);
        deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, groupIds, Operator.IN), new DBCondition(
                CaAccountRv.Field.resourceId, userId), new DBCondition(CaAccountRv.Field.relationshipType,
                EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        //2012-10-15 luojb  #57570 删除群成员反向表数据
        delGrpRevs(userId, groupIds.toArray(new Long[groupIds.size()]));
        
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = new ArrayList<CoProd>();
        List<CoProd> inProdList = prodCmp.queryCoProdByObjId(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV,
                SpecCodeDefine.GROUP_IN_PERSON);
        List<CoProd> outProdList = prodCmp.queryCoProdByObjId(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV,
                SpecCodeDefine.GROUP_OUT_PERSON);
        List<CoProd> noPortProdList = prodCmp.queryCoProdByObjId(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV,
                SpecCodeDefine.GROUP_NO_PORT_PERSON);

        if (!CommonUtil.isEmpty(inProdList))
            prodList.addAll(inProdList);
        if (!CommonUtil.isEmpty(outProdList))
            prodList.addAll(outProdList);
        if (!CommonUtil.isEmpty(noPortProdList))
            prodList.addAll(noPortProdList);
        if (!CommonUtil.isEmpty(prodList))
        {
            for (CoProd coProd : prodList)
            {
                prodCmp.deleteProd(coProd);
            }
        }
    }
    
    /**
     * 删除群成员反向表数据
     * luojb 2012-10-15
     * @param userId
     * @param groupIds
     */
    private void delGrpRevs(Long userId,Long... groupIds)
    {
        if(userId == null)
            return ;
        deleteByCondition(CaResGrpRevs.class,
                new DBCondition(CaResGrpRevs.Field.resourceId, userId),
                new DBCondition(CaResGrpRevs.Field.acctId, groupIds,Operator.IN),
                new DBCondition(CaResGrpRevs.Field.relationshipType,EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
    }

    /**
     * 修改群成员状态 luojb 2011-9-26
     */
    public void changeMemberSts(Long groupId, Long userId, Integer sts) throws IMSException
    {
        // CaAccountRv where = new CaAccountRv();
        // where.setAcctId(groupId);
        // where.setResourceId(userId);
        // where.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP);

        CaAccountRv value = new CaAccountRv();
        value.setStatus(sts);
        value.setValidDate(context.getRequestDate());
        value.setSoDate(context.getRequestDate());
        value.setSoNbr(context.getSoNbr());

        updateByCondition(value, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
                CaAccountRv.Field.resourceId, userId), new DBCondition(CaAccountRv.Field.relationshipType,
                EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
    }

    /**
     * 管理群成员
     * 
     * @author luojb 修改 2011-09-17 SMember结构改变 删除了userId 增加了保存字段 增加了产品列表
     */
    public ArrayList<Long> manageMember(long groupId, SMemberOperList memberList) throws IMSException
    {
        ArrayList<Long> inputUserIds = new ArrayList<Long>();
        short operType = memberList.getOperType();
        long count = 0;
        // 查询群类型
        //linzt 2012-08-29 整理组件方法
        Group3hBean groupBean=context.get3hTree().loadGroup3hBean(groupId);
        CaAccountGroup groupVpn = groupBean.getGroup();
        CaAccount groupAccount = groupBean.getAccount();
        Integer groupType = groupAccount.getAccountType();

        // groupBean可直接获取父群，会判断群类型
        Long parentGroupId = groupBean.getParentGroupId();

        Date groupExpireDate = groupVpn.getExpireDate();

        for (SMember member : memberList.getSMemberList_Item())
        {
            String phoneId = member.getPhone_id();
            String shortPhoneId = member.getShort_phone_id();
            Long userId = null;
            // 2011-11-08 增加群成员号码类型验证，处理pbx成员
            if (member.getNumber_type() == null)
            {
                member.setNumber_type(CommonUtil.ShortToInteger(EnumCodeDefine.VPN_NUMBER_TYPE_BOS));
            }
            Integer numberType = member.getNumber_type();

            if (numberType.shortValue() == EnumCodeDefine.VPN_NUMBER_TYPE_BOS)
            {
                // luojb 2011-10-11 改为checkComponent验证方法
                User3hBean user3hBean = context.get3hTree().loadUser3hBean(phoneId);
                userId = user3hBean.getUserId();
            }
            else if (numberType.shortValue() == EnumCodeDefine.VPN_NUMBER_TYPE_PBX)
            {
                // 2012-05-02 wuyujie : [44101]PBX_RealPhoneNumber需求改造，传入的是手机号码而非PBX号码
            	//2012-08-28 linzt 整理组件方法  采用load3hBean
                userId = context.get3hTree().loadUser3hBean(phoneId).getUserId();

                // pbx不支持短号
                member.setShort_phone_id(null);
                shortPhoneId = null;

            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "numberType", "0,2");
            }

            if (operType == EnumCodeDefine.OPER_TYPE_ADD)
            {
                // 成员数上限验证
                if (groupVpn.getMaxusers().longValue() == groupVpn.getMemberNumber().longValue() + count)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_FULL, groupVpn.getMaxusers());
                }
                if (isMemberInGroup(userId, groupId))
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_MEMBER_EXIST, phoneId, userId);
                }
                // 如果用户没有加入父群，则不允许加入内部群
                if (parentGroupId != null && !isMemberInGroup(userId, parentGroupId))
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN_CUG, userId, parentGroupId,
                            groupId);
                }
                else if (parentGroupId == null)
                {
                    // 根据配置一个成员能加入某类型群数 校验是否能加入该群
                    checkJoinGroup(userId, phoneId, groupType.shortValue());
                }
                if (shortPhoneId != null && isShortPhoneExist(groupId, shortPhoneId))
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_SHORTPHONE_EXIST, shortPhoneId, groupId);
                }
                // @Date 2012-07-19 wangdw5 :#52157 member type（对应ROLE）应该采用传入的参数
                if (member.getRole() != null)
                {
                    if (member.getRole() == EnumCodeDefine.BSSBROKER_ROLE_VPN_NORMAL)
                        member.setRole(EnumCodeDefine.TITLEROLEID_VPN_NORMAL);
                    if (member.getRole() == EnumCodeDefine.BSSBROKER_ROLE_VPN_MANAGER)
                        member.setRole(EnumCodeDefine.TITLEROLEID_VPN_MANAGER);
                }
                else
                    member.setRole(EnumCodeDefine.TITLEROLEID_VPN_NORMAL);

                // 失效期不能超过群的失效期
                if (CommonUtil.isValid(member.getExpire_date()))
                {
                    if (IMSUtil.formatExpireDate(member.getExpire_date()).after(groupExpireDate))
                    {
                        member.setExpire_date(DateUtil.formatDate(groupExpireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                    }
                }
                else
                {
                    member.setExpire_date(DateUtil.formatDate(groupExpireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
                }
                addMember(groupId, member, userId);
                count++;
            }
            else if (operType == EnumCodeDefine.OPER_TYPE_DELETE)
            {
                if (!isMemberInGroup(userId, groupId))
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_MEMBER_NOTEXIST, userId, groupId);
                }
                delMember(groupId, userId);
                count--;
            }
            else if (operType == EnumCodeDefine.OPER_TYPE_MODIFY)
            {
                modMember(groupId, member, userId);
            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.OPERTYPE_UNDEFINED, operType);
            }

            inputUserIds.add(userId);
        }
        // 计算当前群成员数
        calMemberNumber(groupVpn, count);

        return inputUserIds;
    }

    /**
     * 修改群信息 luojb 2011-9-26
     * 
     * @param req
     * @throws IMSException
     * @Date 2012-09-19 yugb :[57408]API中字段清空方案
     */
    public void changeGroupInfo(SChangeGroupInfoReq req) throws IMSException
    {
        long groupId = req.getGroup_id();
        Long billAcctId = req.getBillable_acct_id();
        String mainPhone = req.getMain_phone_id();
        Integer maxMemNbr = req.getMax_group_number();
        // String validDate = req.getValid_date();
        boolean changeFlag = false;

        Group3hBean bean = context.get3hTree().loadGroup3hBean(groupId);
        CaAccountGroup oldVpn = bean.getGroup();
        // 修改群信息
        CaAccountGroup vpn = new CaAccountGroup();

        // 修改最大群成员数
        if (maxMemNbr != null)
        {
        	if(IMSUtil.isClean(maxMemNbr)){
        		 vpn.setMaxusers(maxMemNbr.longValue());
                 changeFlag = true;
        	}else{
	            // 查找当前群成员数
	            Long curMemNbr = oldVpn.getMemberNumber();
	            Long oldMaxNbr = oldVpn.getMaxusers();
	            // 若传入的最大成员数不小于当前成员数则修改
	            if (oldMaxNbr == null || (maxMemNbr.longValue() != oldMaxNbr.longValue() && maxMemNbr.longValue() >= curMemNbr))
	            {
	                vpn.setMaxusers(maxMemNbr.longValue());
	                changeFlag = true;
	            }
        	}
        }

        if (req.getClip_flag() != null)
        {
        	if(IMSUtil.isClean(req.getClip_flag())){
        		  vpn.setClipFlag(null);
	              changeFlag = true;
        	}else{
	            if (oldVpn.getClipFlag() == null || oldVpn.getClipFlag() != null
	                    && req.getClip_flag().longValue() != oldVpn.getClipFlag())
	            {
	                vpn.setClipFlag(CommonUtil.IntegerToLong(req.getClip_flag()));
	                changeFlag = true;
	            }
        	}
        }
        if(IMSUtil.isClean(req.getContact_phone())){
        	vpn.setContactPhone(null);
            changeFlag = true;
        }
        else if (!CommonUtil.isEmpty(req.getContact_phone()) && !req.getContact_phone().equals(oldVpn.getContactPhone()))
        {
            vpn.setContactPhone(req.getContact_phone());
            changeFlag = true;
        }
        if (req.getEspace_flag() != null)
        {
        	if (IMSUtil.isClean(req.getEspace_flag())){
        		vpn.setEspaceFlag(null);
                changeFlag = true;
        	}
        	else if (oldVpn.getEspaceFlag() == null || oldVpn.getEspaceFlag() != null
                    && req.getEspace_flag().longValue() != oldVpn.getEspaceFlag())
            {
                vpn.setEspaceFlag(CommonUtil.IntegerToLong(req.getEspace_flag()));
                changeFlag = true;
            }
        }
        if (req.getOcs_routing_flag() != null)
        {
        	if(IMSUtil.isClean(req.getOcs_routing_flag()))
        	{
           	 	vpn.setOcsRoutingFlag(null);
                changeFlag = true;
        	}
        	else if (oldVpn.getOcsRoutingFlag() == null || oldVpn.getOcsRoutingFlag() != null
                    && req.getOcs_routing_flag().longValue() != oldVpn.getOcsRoutingFlag())
            {
                vpn.setOcsRoutingFlag(CommonUtil.IntegerToLong(req.getOcs_routing_flag()));
                changeFlag = true;
            }
        }

        boolean outGoingCsFlag = false;
        if (req.getOutgoing_cs_flag() != null)
        {
        	if(IMSUtil.isClean(req.getOutgoing_cs_flag()))
        	{
           	 	vpn.setOutgoingCsFlag(null);
                changeFlag = true;
        	}
        	else if (oldVpn.getOutgoingCsFlag() == null || oldVpn.getOutgoingCsFlag() != null
                    && req.getOutgoing_cs_flag().longValue() != oldVpn.getOutgoingCsFlag())
            {
                vpn.setOutgoingCsFlag(CommonUtil.IntegerToLong(req.getOutgoing_cs_flag()));
                changeFlag = true;
                outGoingCsFlag = true;
            }
        }

        // 增加呼叫控制字段2011-12-06
        boolean authCodeFlag = false;
        if(IMSUtil.isClean(req.getInGroupIncomingCall())){
        	changeFlag = true;
        	authCodeFlag = true;
        	vpn.setIngroupIncomingcall(null);
        	oldVpn.setIngroupIncomingcall(null);
        	 
        }
        else if (req.getInGroupIncomingCall() != null && !req.getInGroupIncomingCall().equals(oldVpn.getIngroupIncomingcall()))
        {
        	changeFlag = true;
            authCodeFlag = true;
            vpn.setIngroupIncomingcall(req.getInGroupIncomingCall());
            oldVpn.setIngroupIncomingcall(req.getInGroupIncomingCall());
        }
        if(IMSUtil.isClean(req.getInGroupOutgoingCall())){
       	 changeFlag = true;
            authCodeFlag = true;
            vpn.setIngroupOutgoingcall(req.getInGroupOutgoingCall());
       	 oldVpn.setIngroupOutgoingcall(req.getInGroupOutgoingCall());
       }
        else if (req.getInGroupOutgoingCall() != null && !req.getInGroupOutgoingCall().equals(oldVpn.getIngroupOutgoingcall()))
        {
        	changeFlag = true;
            authCodeFlag = true;
            vpn.setIngroupOutgoingcall(req.getInGroupOutgoingCall());
            oldVpn.setIngroupOutgoingcall(req.getInGroupOutgoingCall());
        }
        if(IMSUtil.isClean(req.getOutGroupIncomingCall())){
       	 vpn.setOutgroupIncomingcall(null);
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setOutgroupIncomingcall(null);
       }
        else  if (req.getOutGroupIncomingCall() != null && !req.getOutGroupIncomingCall().equals(oldVpn.getOutgroupIncomingcall()))
        {
            vpn.setOutgroupIncomingcall(req.getOutGroupIncomingCall());
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setOutgroupIncomingcall(req.getOutGroupIncomingCall());
        }
        if (IMSUtil.isClean(req.getOutGroupOutgingCall())){
        	vpn.setOutgroupOutgoingcall(null);
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setOutgroupOutgoingcall(null);
        }
        else if (req.getOutGroupOutgingCall() != null && !req.getOutGroupOutgingCall().equals(oldVpn.getOutgroupOutgoingcall()))
        {
            vpn.setOutgroupOutgoingcall(req.getOutGroupOutgingCall());
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setOutgroupOutgoingcall(req.getOutGroupOutgingCall());
        }
        if (IMSUtil.isClean(req.getCrossGroupIncomingCall())){
       	 vpn.setCrossgroupIncomingcall(null);
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setCrossgroupIncomingcall(null);
       }
        else if (req.getCrossGroupIncomingCall() != null
                && !req.getCrossGroupIncomingCall().equals(oldVpn.getCrossgroupIncomingcall()))
        {
            vpn.setCrossgroupIncomingcall(req.getCrossGroupIncomingCall());
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setCrossgroupIncomingcall(req.getCrossGroupIncomingCall());
        }
        if(IMSUtil.isClean(req.getCrossGroupOutgoingCall())){
      	  vpn.setCrossgroupOutgoingcall(null);
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setCrossgroupOutgoingcall(null);
        }
        else if (req.getCrossGroupOutgoingCall() != null
                && !req.getCrossGroupOutgoingCall().equals(oldVpn.getCrossgroupOutgoingcall()))
        {
            vpn.setCrossgroupOutgoingcall(req.getCrossGroupOutgoingCall());
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setCrossgroupOutgoingcall(req.getCrossGroupOutgoingCall());
        }
        if(IMSUtil.isClean(req.getSpecIncomingCall())){
        	vpn.setSpecIncomingcall(null);
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setSpecIncomingcall(null);
        }
        else if (req.getSpecIncomingCall() != null && !req.getSpecIncomingCall().equals(oldVpn.getSpecIncomingcall()))
        {
            vpn.setSpecIncomingcall(req.getSpecIncomingCall());
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setSpecIncomingcall(req.getSpecIncomingCall());
        }
        if(IMSUtil.isClean(req.getSpecOutgoingCall())){
        	vpn.setSpecOutgoingcall(null);
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setSpecOutgoingcall(null);
        }
        else if (req.getSpecOutgoingCall() != null && !req.getSpecOutgoingCall().equals(oldVpn.getSpecOutgoingcall()))
        {
            vpn.setSpecOutgoingcall(req.getSpecOutgoingCall());
            changeFlag = true;
            authCodeFlag = true;
            oldVpn.setSpecOutgoingcall(req.getSpecOutgoingCall());
        }

        // 修改付费帐户
        if (billAcctId != null)
        {
            // luojb 2011-10-11 调用checkComponent验证方法
            // wangyh3 2012-05-12 Task #45545 因前台的代码也要用到，所以该校验提前
            Long oldBillAcctId = bean.getBillAcctId();
            if (billAcctId.longValue() != oldBillAcctId)
            {
                // 2012-05-12 wangyh3 Task #45545 表CA_ACCOUT_GROUP 中新增pay_acct_id 字段
                //vpn.setPayAcctId(context.get3hTree().loadAcct3hBean(billAcctId).getAcctId());
                changeFlag = true;
                
                CaAccountRel caRel = new CaAccountRel();
                caRel.setRelGroupId(billAcctId);

                updateByCondition(caRel, new DBCondition(CaAccountRel.Field.groupId, groupId), new DBCondition(
                        CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_CA_GCA_RELATION));
            }
        }
        
        if (changeFlag)
        {
            vpn.setSoDate(context.getRequestDate());
            vpn.setSoNbr(context.getSoNbr());
            vpn.setValidDate(context.getRequestDate());

            updateByCondition(vpn, new DBCondition(CaAccountGroup.Field.acctId, groupId));
        }

        // 修改主号码
        if (mainPhone != null)
        {
            // luojb 2011-10-11 调用checkComponent验证方法
            User3hBean user3hBean = context.get3hTree().loadUser3hBean(mainPhone);
            Long userId = user3hBean.getUserId();
            Long oldUserId = queryMainUserIdByAcctId(groupId);
            // 新旧主号码不相同进行更新
            if (!userId.equals(oldUserId))
            {
                CaAccountRv rv = queryAcctRvByGrpIdAndUserId(groupId, userId);
                if (rv == null)
                { // 新号码不在群内
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_MEMBER_NOTEXIST, mainPhone, groupId);
                }
                else
                {
                    // 旧主号码变更为普通号码
                    if (oldUserId != null)
                    {
                        this.upMemberRole(oldUserId, EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE, EnumCodeDefine.TITLEROLEID_VPN_NORMAL, groupId);
                    }
                    // 新号码是群内普通号码
                    this.upMemberRole(userId, EnumCodeDefine.TITLEROLEID_VPN_NORMAL, EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE, groupId);
                    changeFlag = true;
                }
            }
        }

        if (outGoingCsFlag)
        {
            // @Date 2012-4-26 tangjl5 Task #44705 创建VPN的callscreen产品。
            if (req.getOutgoing_cs_flag() == EnumCodeDefine.CHANGE_GROUP_INFO_SUPPORT_CALL_SCREAN)
            {
                orderCallScreanForVpn(groupId, context.getRequestDate(), oldVpn.getExpireDate());
            }
            // @Date 2012-4-26 tangjl5 Task #44705 删除群订购的callscrean产品
            else if (req.getOutgoing_cs_flag() == EnumCodeDefine.CHANGE_GROUP_INFO_NOT_SUPPORT_CALL_SCREAN)
            {
                List<CoProd> prods = context.getComponent(ProductQuery.class).queryProdByGrpIdAndBusiFlag(groupId,
                        SpecCodeDefine.MCS_NUMBER);
                if (CommonUtil.isNotEmpty(prods))
                {
                    context.getComponent(BaseProductComponent.class).deleteProd(prods.get(0));
                }
            }
        }

        // 修改群间产品鉴权特征值2011-12-08
        if (authCodeFlag)
        {
            BaseProductComponent pc = context.getComponent(BaseProductComponent.class);
            List<CoProdCharValue> charValueList = pc.queryInterGroupProds(groupId, SpecCodeDefine.INTER_GROUP_AUTH);
            if (CommonUtil.isNotEmpty(charValueList))
            {
                for (CoProdCharValue charValue : charValueList)
                {
                    CoProdCharValue value = new CoProdCharValue();
                    value.setValue(CommonUtil.int2String(parseGroupAuthCode(oldVpn)));

                    updateByCondition(value, new DBCondition(CoProdCharValue.Field.productId, charValue.getProductId()),
                            new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.INTER_GROUP_AUTH), new DBCondition(
                                    CoProdCharValue.Field.objectId, groupId), new DBCondition(CoProdCharValue.Field.objectType,
                                    EnumCodeDefine.PROD_OBJECTTYPE_GCA));

                    CoProd prod = new CoProd();
                    prod.setProductId(charValue.getProductId());

                    updateByCondition(prod, new DBCondition(CoProd.Field.productId, charValue.getProductId()), new DBCondition(
                            CoProd.Field.objectId, groupId), new DBCondition(CoProd.Field.objectType,
                            EnumCodeDefine.PROD_OBJECTTYPE_GCA));
                }
            }
        }

        if (!changeFlag)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGEGROUPINFO_NOTHING_CHANGED);
        }

    }
    
    /**
     * 更新成员角色
     * luojb 2012-8-10
     * @param userId
     * @param orgTitleRoleId
     * @param titleRoleId
     * @param groupId
     */
    public void upMemberRole(Long userId,Integer orgTitleRoleId,Integer titleRoleId,Long groupId)
    {
        CaAccountRv oldRv = new CaAccountRv();
        oldRv.setTitleRoleId(titleRoleId);
        
        updateByCondition(oldRv, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
                CaAccountRv.Field.titleRoleId, EnumCodeDefine.TITLEROLEID_VPN_MAINPHONE), new DBCondition(
                CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
    }
    
    public int parseGroupAuthCode(CaAccountGroup vpn)
    {
        Integer inIn = vpn.getIngroupIncomingcall();
        Integer inOut = vpn.getIngroupOutgoingcall();
        Integer outIn = vpn.getOutgroupIncomingcall();
        Integer outOut = vpn.getOutgroupOutgoingcall();
        Integer crossIn = vpn.getCrossgroupIncomingcall();
        Integer crossOut = vpn.getCrossgroupOutgoingcall();
        Integer specIn = vpn.getSpecIncomingcall();
        Integer specOut = vpn.getSpecOutgoingcall();

        int authCode = 0;
        authCode = inOut * 10000000 + inIn * 1000000 + crossOut * 100000 + crossIn * 10000 + specOut * 1000 + specIn * 100
                + outOut * 10 + outIn;

        return authCode;
    }

    /**
     * 是否是内部群关系 luojb 2011-9-26
     */
    public boolean isGroupRelated(Long orgGroupId, Long groupId) throws IMSException
    {
        if (groupId == null)
            return false;

        CaAccountRel caRel = querySingle(CaAccountRel.class, new DBCondition(CaAccountRel.Field.groupId, orgGroupId),
                new DBCondition(CaAccountRel.Field.relGroupId, groupId), new DBCondition(CaAccountRel.Field.relationshipType,
                        EnumCodeDefine.ACCOUNT_BELONG));

        return caRel != null;
    }

    /**
     * 成员是否加入该group
     * 
     * @author luojb
     */
    public boolean isMemberInGroup(Long userId, Long groupId)
    {
        if (userId == null || groupId == null)
            return false;
        CaAccountRv rv = queryAcctRvByGrpIdAndUserId(groupId, userId);
        return null != rv;
    }

    /**
     * 成员是否加入该group
     * 
     * @author luojb
     */
    public boolean isMemberInGroup(String phoneId, Long groupId)
    {
        if (CommonUtil.isEmpty(phoneId) || groupId == null)
            return false;
        CaAccountRv rv = queryAcctRvByGroupIdAndPhoneId(groupId, phoneId);
        return rv != null;
    }


    /**
     * 
     * yangjh 2012-10-22  判断特殊号码是否加入group
     * @param phoneId
     * @param groupId
     * @return
     */
    public boolean isSpecMemInGroup(String phoneId, Long groupId)
    {
        if (CommonUtil.isEmpty(phoneId) || groupId == null)
            return false;
        CaAccountRv rv = querySpecMemByGroupAndPhone(groupId, phoneId);
        return rv != null;
    }
    
    /**
     * @Description: TODO(根据groupTye验证是否群上限,强制要求配置"max_join_group_"+groupType参数入SD.SYS_PARAMETER)
     * @param userId
     * @param phoneId
     * @author: wangdw5
     * @Date: 2012-6-19
     */
    private void checkJoinGroup(Long userId, String phoneId, Short groupType)
    {
        if (userId == null || groupType == null)
        {
            return;
        }
        String check_max = SysCodeDefine.busi.MAX_JOIN_GROUP.concat(CommonUtil.short2String(groupType));
        Integer amount = SysUtil.getInt(check_max);
        if (amount == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_MAX_JOIN_GROUP, check_max);
        }
        else if (amount == CommonUtil.string2Integer(ConstantDefine.INT_UNIFIED_CONFIGURATION))
        {
            return;
        }
        else
        {
            List<CaAccountRv> rv = queryJoinedVpnsByUserId(userId);
            if (rv != null && rv.size() == amount)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN, userId, phoneId, amount,
                        groupType);
            }
        }
    }

    // /**
    // * 判断用户是否能加入vpn luojb 2011-12-2
    // */
    // private void checkJoinVpn(Long userId, String phoneId)
    // {
    // if (userId == null)
    // {
    // return;
    // }
    // // vpn只能加入一个，配置里是一个，没有配置默认取一个
    // int amount = SysUtil.getInt(SysCodeDefine.busi.JOIN_GROUP_AMOUNT_VPN, 1);
    // List<CaAccountRv> rv = queryJoinedVpnsByUserId(userId);
    // if (rv != null && rv.size() == amount)
    // {
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN, userId, phoneId, amount, "vpn");
    // }
    // }

    // /**
    // * 成员是否已加入community
    // *
    // * @author luojb
    // */
    // private void checkJoinCommunity(Long userId, String phoneId)
    // {
    // Integer amount = SysUtil.getInt(SysCodeDefine.busi.JOIN_GROUP_AMOUNT_COMMUNITY);
    // // 没有配置表示无限制
    // if (amount == null)
    // {
    // return;
    // }
    // List<CaAccountRv> rv = queryJoinedCommsByUserId(userId);
    // if (rv != null && rv.size() == amount)
    // {
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN, userId, phoneId, amount, "community");
    // }
    // }

    // /**
    // * 成员是否已加入VPBX
    // *
    // * @author luojb
    // */
    // private void checkJoinVPBX(Long userId, String phoneId)
    // {
    // if (userId == null)
    // {
    // return;
    // }
    // Integer amount = SysUtil.getInt(SysCodeDefine.busi.JOIN_GROUP_AMOUNT_VPBX);
    // // 没有配置表示无限制
    // if (amount == null)
    // {
    // return;
    // }
    // List<CaAccountRv> rv = queryJoinedCommsByUserId(userId);
    // if (rv != null && rv.size() == amount)
    // {
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN, userId, phoneId, amount, "VPBX");
    // }
    // }

    // /**
    // * 判断用户是否能加入冲浪E家亲群 xieqr 2012-3-22
    // */
    // private void checkJoinSurfEFamily(Long userId, String phoneId)
    // {
    // if (userId == null)
    // {
    // return;
    // }
    // // vpn只能加入一个，配置里是一个，没有配置默认取一个
    // int amount = SysUtil.getInt(SysCodeDefine.busi.JOIN_GROUP_AMOUNT_VPN, 1);
    // List<CaAccountRv> rv = queryJoinedSurfEFamilyByUserId(userId);
    // if (rv != null && rv.size() == amount)
    // {
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN, userId, phoneId, amount, "冲浪E家亲");
    // }
    // }

    // /**
    // * 判断用户是否能加入66家庭包套餐活动群 xieqr 2012-3-22
    // */
    // private void checkJoinSixSixFamilyPackage(Long userId, String phoneId)
    // {
    // if (userId == null)
    // {
    // return;
    // }
    // // vpn只能加入一个，配置里是一个，没有配置默认取一个
    // int amount = SysUtil.getInt(SysCodeDefine.busi.JOIN_GROUP_AMOUNT_VPN, 1);
    // List<CaAccountRv> rv = queryJoinedSixSixFamilyPackageByUserId(userId);
    // if (rv != null && rv.size() == amount)
    // {
    // throw IMSUtil.throwBusiException(ErrorCodeDefine.MANAGEGROUPMEMBER_CANNOT_JOIN, userId, phoneId, amount, "66家庭包活动群");
    // }
    // }

    /**
     * @Description: 删除一个群
     * @param groupId ,群Id
     * @param includeChildren ，是否删除该群下所有子群 added by wuyj
     * @update 增加了特殊号码虚账户的删除，特殊号码的删除luojb
     */
    public void deleteGroupById(long groupId)
    {
        String[] types = new String[] { String.valueOf(EnumCodeDefine.ACCOUNT_CA_GCA_RELATION),
                String.valueOf(EnumCodeDefine.ACCOUNT_BELONG), String.valueOf(EnumCodeDefine.ACCOUNT_VPN_SPECNBR_GCA_RELATION) };

        // 查询出当前群以及子群的id 还有特殊号码虚账户id
        List<CaAccountRel> groupList = super.query(CaAccountRel.class, new DBCondition(CaAccountRel.Field.groupId, groupId),
                new DBCondition(CaAccountRel.Field.relationshipType, types, Operator.IN));

        // 组织群Id
        Set<String> groupIds = new HashSet<String>();
        groupIds.add(String.valueOf(groupId));
        for (CaAccountRel rel : groupList)
        {
            if (EnumCodeDefine.ACCOUNT_BELONG == rel.getRelationshipType().intValue())
            {
                // 表示是主群与子群的关系，rel_acct_id是子群id
                Long cugId = rel.getRelGroupId();
                groupIds.add(String.valueOf(cugId));

                // 查询cug的特殊号码虚账户
                CaAccountRel caRel = querySingle(CaAccountRel.class, new DBCondition(CaAccountRel.Field.groupId, cugId),
                        new DBCondition(CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_VPN_SPECNBR_GCA_RELATION));
                if (caRel != null)
                {
                    groupIds.add(String.valueOf(caRel.getRelGroupId()));
                    // 直接删除掉特殊号码
                    this.deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, caRel.getRelGroupId()));
                }
            }
            else if (EnumCodeDefine.ACCOUNT_CA_GCA_RELATION == rel.getRelationshipType().intValue())
            {
                ;
            }
            else if (EnumCodeDefine.ACCOUNT_VPN_SPECNBR_GCA_RELATION == rel.getRelationshipType().intValue())
            {
                // 群id与特殊号码虚账户id关系
                groupIds.add(String.valueOf(rel.getRelGroupId()));
                // 直接删除掉特殊号码
                this.deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, rel.getRelGroupId()));

            }
        }
        String[] arrGroupIds = groupIds.toArray(new String[groupIds.size()]);

        // 以下情况不能删除：
        // 1.群内有成员
        // 2.群的内部群内有成员
        List<CaAccountRv> members = query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, arrGroupIds, Operator.IN),
                new DBCondition(CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
        if (!CommonUtil.isEmpty(members))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.DELGROUP_EXIST_MEMBERS);
        }

        // 删除ca_account表所有群记录
        this.deleteByCondition(CaAccount.class, new DBCondition(CaAccount.Field.acctId, arrGroupIds, Operator.IN));

        // 删除ca_account_vpn所有群记录
        this.deleteByCondition(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId, arrGroupIds, Operator.IN));

        // 删除ca_account_rel中的群对应关系记录,比如父群与子群关系(0)，群与付费账户关系(2)，群与特殊号码虚账户关系(3)
        this.deleteByCondition(CaAccountRel.class,
                new DBCondition(CaAccountRel.Field.groupId, groupIds.toArray(new String[groupIds.size()]), Operator.IN),
                new DBCondition(CaAccountRel.Field.relationshipType, types, Operator.IN));

        // 若传入的群就是cug，删除ca_account_rel中和父群的关系
        this.deleteByCondition(CaAccountRel.class, new DBCondition(CaAccountRel.Field.relGroupId, groupId), new DBCondition(
                CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_BELONG));

        // 删除群下的特殊号码
        this.deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, arrGroupIds, Operator.IN),
                new DBCondition(CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_SPECIALNBR));

        // 删除群的产品

        List<CoProd> groupProds = super.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.objectId, arrGroupIds,
                Operator.IN), new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_GCA));
        if (!CommonUtil.isEmpty(groupProds))
        {
            // 组织产品Id
            Vector<String> prodIds = new Vector<String>();
            for (CoProd prod : groupProds)
            {
                prodIds.add(String.valueOf(prod.getProductId()));
            }
            String[] arrProds = prodIds.toArray(new String[prodIds.size()]);

            // 删除co_prod_char_value
            super.deleteByCondition(CoProdCharValue.class,
                    new DBCondition(CoProdCharValue.Field.productId, arrProds, Operator.IN), new DBCondition(
                            CoProd.Field.objectId, arrGroupIds, Operator.IN), new DBCondition(CoProd.Field.objectType,
                            EnumCodeDefine.PROD_OBJECTTYPE_GCA));

            // 删除co_prod_billing_cyle
            super.deleteByCondition(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, arrProds,
                    Operator.IN), new DBCondition(CoProd.Field.objectId, arrGroupIds, Operator.IN), new DBCondition(
                    CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_GCA));

            // 删除co_prod_price_param
            super.deleteByCondition(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, arrProds,
                    Operator.IN), new DBCondition(CoProd.Field.objectId, arrGroupIds, Operator.IN), new DBCondition(
                    CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_GCA));
        }
    }

    /**
     * 创建特殊号码产品的特征值 luojb 2011-11-19
     * 
     * @param gcaId:特殊号码虚账户id
     * @param prod：特殊号码产品结构
     */
    public void buildSpecNbrProdCharValue(Long gcaId, SProductOrder prod)
    {
        if (gcaId == null)
        {
            gcaId = createSpecNbrGCA(prod.getAcct_id());
        }
        SProductParam param = new SProductParam();
        param.setParam_id(SpecCodeDefine.OUTNET_PHONE_ID);
        param.setParam_value(gcaId.toString());

        SProductParamList paramList = prod.getParam_list();
        if(paramList == null)
        {
            paramList = new SProductParamList();
            prod.setParam_list(paramList);
        }
        List<SProductParam> params = CommonUtil.parseArray2List(paramList.getSProductParamList_Item());
        if (params == null)
        {
            params = new ArrayList<SProductParam>();
        }
        params.add(param);
        paramList.setSProductParamList_Item(params.toArray(new SProductParam[params.size()]));
        
    }

    public void buildGroupTypeReguidPriceParam(Integer accountType, SProductOrder prod)
    {
        Integer groupType = null;
        if (accountType.intValue() == EnumCodeDefine.ACCOUNT_TYPE_VPN)
            groupType = EnumCodeDefine.MDB_GROUP_TYPE_VPN;
        else if (accountType.intValue() == EnumCodeDefine.ACCOUNT_TYPE_CUG)
            groupType = EnumCodeDefine.MDB_GROUP_TYPE_CUG;
        else if (accountType.intValue() == EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY)
            groupType = EnumCodeDefine.MDB_GROUP_TYPE_COMMUNITY;
        else if (accountType.intValue() == EnumCodeDefine.ACCOUNT_TYPE_VPBX)
            groupType = EnumCodeDefine.MDB_GROUP_TYPE_VPBX;
        buildReguidPriceParamGroupType(CommonUtil.IntegerToShort(groupType), prod);
    }

    /**
     * 构建群成员数计价参数 luojb 2012-1-18
     */
    public void buildReguidPriceParamMemNum(Long memNum, SProductOrder prod)
    {
        // ais项目，计算放入,上海项目crm传入这里不处理
        if (ProjectUtil.is_TH_AIS())
            buildReguidPriceParam(SpecCodeDefine.GROUP_MEMBER_NUMBER_PROD_PARAM_ID, CommonUtil.long2String(memNum), prod);

    }

    public void buildReguidPriceParamGroupType(Short groupType, SProductOrder prod)
    {
        buildReguidPriceParam(SpecCodeDefine.GROUP_TYPE_PROD_PARAM_ID, CommonUtil.short2String(groupType), prod);
    }

    private void buildReguidPriceParam(Integer paramId, String value, SProductOrder prod)
    {
        ExtendParamList extendParamList = prod.getReguid_price_param();

        ExtendParam extendParam = new ExtendParam();
        extendParam.setParam_name(CommonUtil.int2String(paramId));
        extendParam.setParam_value(value);

        if (extendParamList == null || CommonUtil.isEmpty(extendParamList.getExtendParamList_Item()))
        {
            extendParamList = new ExtendParamList();
            ExtendParam[] extendParamItem = new ExtendParam[1];
            extendParamItem[0] = extendParam;
            extendParamList.setExtendParamList_Item(extendParamItem);
        }
        else
        {
            ExtendParam[] extendParamItemOld = extendParamList.getExtendParamList_Item();
            ExtendParam[] extendParamItemNew = new ExtendParam[extendParamItemOld.length + 1];
            for (int i = 0; i < extendParamItemOld.length; i++)
            {
                extendParamItemNew[i] = extendParamItemOld[i];
            }
            extendParamItemNew[extendParamItemOld.length] = extendParam;
            extendParamList.setExtendParamList_Item(extendParamItemNew);
        }

        prod.setReguid_price_param(extendParamList);
    }

    /**
     * 检查用户是否加入自动群，返回没有加入的列表 luojb 2012-1-11
     */
    public List<Long> autoUsersNotIn(List<Long> userIds)
    {
        List<Long> inUserIds = autoUsersIn(userIds);
        if (CommonUtil.isNotEmpty(inUserIds))
            userIds.removeAll(inUserIds);
        return userIds;
    }

    /**
     * 检查用户是否加入自动群，返回加入的列表 luojb 2012-1-11
     */
    public List<Long> autoUsersIn(List<Long> userIds)
    {
        if (CommonUtil.isEmpty(userIds))
        {
            return null;
        }

        Integer[] relType = new Integer[] { EnumCodeDefine.ACCOUNT_RELATION_RV_AUTOGROUP,
                EnumCodeDefine.ACCOUNT_RELATION_RV_HIERARCHY };
        List<CaAccountRv> resultList = query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.relationshipType, relType,
                Operator.IN), new DBCondition(CaAccountRv.Field.resourceId, userIds, Operator.IN));
        if (CommonUtil.isEmpty(resultList))
        {
            return null;
        }

        List<Long> inUserIds = new ArrayList<Long>();
        for (CaAccountRv res : resultList)
        {
            inUserIds.add(res.getResourceId());
        }
        return inUserIds;
    }

    // /**
    // * 校验用户是否已加入其他账户的自动群 luojb 2012-1-11
    // */
    // private void autoCheckCaRelation(List<Long> userIds)
    // {
    //
    // List<Long> resList = autoUsersIn(userIds);
    //
    // if (CommonUtil.isNotEmpty(resList))
    // {
    // IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_USER_HASBEENIN, resList.get(0));
    // }
    // }

    /**
     * 去除掉和账户本身已经有关系的用户 luojb 2012-1-14
     */
    public List<Long> autoUsersNotInSelf(Long acctId, List<Long> userIds)
    {
        if (CommonUtil.isEmpty(userIds))
            return null;
        Integer[] relType = new Integer[] { EnumCodeDefine.ACCOUNT_RELATION_RV_AUTOGROUP,
                EnumCodeDefine.ACCOUNT_RELATION_RV_HIERARCHY };
        List<CaAccountRv> resultList = query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.relationshipType, relType,
                Operator.IN), new DBCondition(CaAccountRv.Field.resourceId, userIds, Operator.IN), new DBCondition(
                CaAccountRv.Field.acctId, acctId));
        if (CommonUtil.isEmpty(resultList))
            return userIds;
        List<Long> inUserIds = new ArrayList<Long>();
        for (CaAccountRv res : resultList)
        {
            inUserIds.add(res.getResourceId());
        }
        userIds.removeAll(inUserIds);
        return userIds;
    }

    /**
     * 用户加入自动群 luojb 2012-1-11
     */
    public void autoJoinMembers(Long acctId, List<Long> userIds)
    {
        List<CaAccountRv> rvList = new ArrayList<CaAccountRv>();
        IMS3hTree ims3hTree = context.get3hTree();
        for (Long userId : userIds)
        {
            User3hBean userBean = null;
            // 自动创建关系的用户都不是业务操作的，如果是无效的用户数据直接舍弃，不抛错
            try
            {
                userBean = ims3hTree.loadUser3hBean(userId);
            }
            catch (Exception e)
            {
                continue;
            }

            CaAccountRv rv = this.addMemberAutoGroup(acctId, userBean.getUserId(), userBean.getPhoneId());
            rvList.add(rv);
        }
        insertBatch(rvList);

        // 创建ca_account_group信息
        // 2012-08-29 linzt 整理组件方法
        try
        {
        	context.get3hTree().loadGroup3hBean(acctId);
        }
        catch(IMS3hNotFoundException e)
        {
        	autoCreateGroup(acctId);
        }
    }

    public void autoJoinMember(Long acctId, Long userId, String phoneId)
    {
        CaAccountRv rv = this.addMemberAutoGroup(acctId, userId, phoneId);
        insert(rv);
        
        // 创建ca_account_group信息
        //2012-08-29 linzt 整理组件方法
        try
        {
        	context.get3hTree().loadGroup3hBean(acctId);
        }
        catch(IMS3hNotFoundException e)
        {
        	autoCreateGroup(acctId);
        }
        
    }

    public void autoJoinMembersForNewReg(List<CaAccountRes> resList)
    {
        if (CommonUtil.isEmpty(resList))
            return;
        List<CaAccountRv> insertList = new ArrayList<CaAccountRv>();
        Set<Long> acctIdSet = new HashSet<Long>();
        for (CaAccountRes res : resList)
        {
            User3hBean userBean = context.get3hTree().loadUser3hBean(res.getResourceId());
            CaAccountRv rv = this.addMemberAutoGroup(res.getResAcctId(), res.getResourceId(), userBean.getPhoneId());
            insertList.add(rv);
            acctIdSet.add(res.getAcctId());
        }
        insertBatch(insertList);
        // 2012-10-15 luojb  #57570  创建群成员反向同步表数据
        insertGrpRevsList(insertList);

        // 创建ca_account_group信息
        List<Long> acctIds = new ArrayList<Long>(acctIdSet);
        if (acctIds.size() == 1)
        {
            //2012-08-29 linzt 整理组件方法
            try
            {
            	context.get3hTree().loadGroup3hBean(acctIds.get(0));
            }
            catch(IMS3hNotFoundException e)
            {
            	autoCreateGroup(acctIds.get(0));
            }
        }
        else
        {
            List<CaAccountGroup> existAcctGroups = query(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId,
                    acctIds, Operator.IN));
            if (CommonUtil.isNotEmpty(existAcctGroups))
            {
                for (CaAccountGroup group : existAcctGroups)
                {
                    acctIdSet.remove(group.getAcctId());
                }
                acctIds = new ArrayList<Long>(acctIdSet);
            }
            if (acctIds.size() > 0)
                autoCreateGroup(acctIds.toArray(new Long[acctIds.size()]));

        }
    }

    /**
     * 为自动创建群的账户实例化群信息 luojb 2012-5-26
     * 
     * @param acctIds
     */
    private void autoCreateGroup(Long... acctIds)
    {
        if (CommonUtil.isEmpty(acctIds))
            return;
        List<CaAccountGroup> groups = new ArrayList<CaAccountGroup>();
        for (Long acctId : acctIds)
        {
            if (!CommonUtil.isValid(acctId))
                continue;
            CaAccountGroup group = new CaAccountGroup();
            group.setAcctId(acctId);
            group.setAccountType(CommonUtil.ShortToInteger(EnumCodeDefine.ACCOUNT_TYPE_AUTO_GROUP));
            //group.setPayAcctId(acctId);
            group.setCreateDate(context.getRequestDate());
            group.setSoDate(context.getRequestDate());
            group.setSoNbr(context.getSoNbr());
            group.setValidDate(context.getRequestDate());
            group.setExpireDate(IMSUtil.getDefaultExpireDate());
            group.setCrossgroupIncomingcall(1);
            group.setCrossgroupOutgoingcall(1);
            group.setSpecIncomingcall(1);
            group.setSpecOutgoingcall(1);
            group.setIngroupIncomingcall(1);
            group.setIngroupOutgoingcall(1);
            group.setOutgroupIncomingcall(1);
            group.setOutgroupOutgoingcall(1);
            groups.add(group);
        }
        insertBatch(groups);
    }

    /**
     * 查询账户为其支付账户的用户
     */
    public List<Long> autoQueryUsers(Long acctId)
    {
        // 自动创建群处理：把账户的所有归属用户和支付关系用户都加入 2012-01-12
        AccountQuery aq = context.getComponent(AccountQuery.class);

        List<CaAccountRes> userList = aq.queryBillAcctResByAcctId(acctId);

        if (CommonUtil.isEmpty(userList))
        {
            return null;
        }

        List<Long> userIds = new ArrayList<Long>();
        for (CaAccountRes res : userList)
        {
            userIds.add(res.getResourceId());
        }
        return userIds;
    }

    public void autoDelGroupRes(Long acctId, Long userId)
    {
        if (acctId == null || userId == null)
            return;
        deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, acctId), new DBCondition(
                CaAccountRv.Field.resourceId, userId), new DBCondition(CaAccountRv.Field.relationshipType,
                EnumCodeDefine.ACCOUNT_RELATION_RV_AUTOGROUP));
    }

    public void autoDelGroupResByAcct(Long acctId)
    {
        if (acctId == null)
            return;
        deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, acctId), new DBCondition(
                CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_AUTOGROUP));
    }

    /**
     * @Description: TODO(删除用户所在的所有群)
     * @param userId
     * @author: wangdw5
     * @Date: 2012-6-18
     */
    public void deleteUserAllGroupRes(Long userId)
    {
        if (userId == null)
            return;
        deleteByCondition(CaAccountRv.class, new DBCondition(CaAccountRv.Field.resourceId, userId));
    }

    public boolean checkAcctRelAndGroupProd(Long acctId)
    {
        CaAccountRel parentRel = context.getComponent(AccountRelationComponent.class).queryCaAccountRel(acctId,
                EnumCodeDefine.ACCOUNT_GCA_RELATION);
        List<CaAccountRel> subRels = null;
        if (parentRel == null)
            subRels = context.getComponent(AccountRelationComponent.class).querySubCaRels(acctId);
        if (parentRel == null && CommonUtil.isEmpty(subRels))
        {
            List<CoProd> prodList = context.getComponent(ProductQuery.class).queryProdListByGroupId(acctId, SpecCodeDefine.CIRCLE_CALL_SHARING_PROM);
            if (CommonUtil.isEmpty(prodList))
                return false;
        }
        return true;
    }

    public void mngAutoGroupRes(Long oldBillAcctId, Long newBillAcctId, Long userId)
    {
        if (oldBillAcctId.longValue() == newBillAcctId.longValue())
            return;
        // 删除旧的自动群关系
        autoDelGroupRes(oldBillAcctId, userId);

        // 检查是否要建立新的群关系
        if (checkAcctRelAndGroupProd(newBillAcctId))
        {
            User3hBean userBean = context.get3hTree().loadUser3hBean(userId);
            autoJoinMember(newBillAcctId, userId, userBean.getPhoneId());
        }
    }

    /**
     * 更新产品状态，返回需要通知扣费的产品列表 luojb 2012-2-17
     * 
     * @param groupProdList
     * @param sts
     */
    public List<CoProd> upProdsSts(List<CoProd> groupProdList, Integer sts)
    {
        if (CommonUtil.isEmpty(groupProdList))
            return null;
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<Long> upStsProdIds = new ArrayList<Long>();
        List<CoProd> authProdList = new ArrayList<CoProd>();
        for (CoProd dmProd : groupProdList)
        {
            Integer prodSts = prodCmp.parseProdStsFrom3Sts(dmProd.getLifecycleStatusId());
            if (prodSts.intValue() == sts.intValue())
                continue;
            upStsProdIds.add(dmProd.getProductId());
            // 预扣费产品激活时，需要通知扣费
            if (dmProd.getBillingType() == EnumCodeDefine.PROD_BILLTYPE_PREPAID
                    && sts.intValue() == EnumCodeDefine.ACCOUNT_ACTIVE)
            {
                PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class)
                        .queryPmCompsiteDeductRuleByOfferId(dmProd.getProductOfferingId(), dmProd.getBillingType().intValue());
                Integer deductFlag = deductRule.getDeductFlag();
                if (deductFlag != null && deductFlag.intValue() == EnumCodeDefine.FEE_PREPAID_DEDUCT)
                    authProdList.add(dmProd);
            }
        }
        // 更新状态
        prodCmp.modifyProdStatus(upStsProdIds, sts);

        return authProdList;
    }

    public CaAccountRv addMemberHierarchy(Long acctId, Long userId, String phoneId, Integer titleRoleId)
    {
        return parseCaAccountRv(acctId, userId, phoneId, EnumCodeDefine.ACCOUNT_RELATION_RV_HIERARCHY, titleRoleId);
    }

    public CaAccountRv addMemberAutoGroup(Long acctId, Long userId, String phoneId)
    {
        return parseCaAccountRv(acctId, userId, phoneId, EnumCodeDefine.ACCOUNT_RELATION_RV_AUTOGROUP, null);
    }

    /**
     * 封装自动群关系和层级关系，valid_date取请求时间，expire_date取默认失效时间 luojb 2012-2-21
     * 
     * @param acctId
     * @param userId
     * @param phoneId
     * @param relationshipType
     * @param titleRoleId
     * @return
     */
    private CaAccountRv parseCaAccountRv(Long acctId, Long userId, String phoneId, Integer relationshipType, Integer titleRoleId)
    {
        CaAccountRv rv = new CaAccountRv();
        rv.setAcctId(acctId);
        rv.setResourceId(userId);
        // @Date 2012-03-30 lijc3 如果phoneId为空,设置为-1
        if (CommonUtil.isEmpty(phoneId))
        {
            rv.setPhoneId(String.valueOf(-1));
        }
        else
        {
            rv.setPhoneId(phoneId);
        }
        rv.setRelationshipType(relationshipType);
        rv.setTitleRoleId(titleRoleId);
        rv.setCreateDate(context.getRequestDate());
        rv.setStatus(EnumCodeDefine.ACCOUNT_ACTIVE);
        rv.setSoDate(context.getRequestDate());
        rv.setSoNbr(context.getSoNbr());

        return rv;
    }

    /**
     * 删除集团层级成员 luojb 2012-2-20
     */
    public void deleteMemberHierarchy(Long acctId, Long userId, String phoneId)
    {
        List<DBCondition> con = new ArrayList<DBCondition>();
        // CaAccountRv rv = new CaAccountRv();
        con.add(new DBCondition(CaAccountRv.Field.acctId, acctId));
        // rv.setAcctId(acctId);
        // rv.setResourceId(userId);
        con.add(new DBCondition(CaAccountRv.Field.resourceId, userId));
        // @Date 2012-03-30 lijc3 如果phoneId为空 不设置为查询条件
        if (CommonUtil.isNotEmpty(phoneId))
        {
            con.add(new DBCondition(CaAccountRv.Field.phoneId, phoneId));
            // rv.setPhoneId(phoneId);
        }
        // rv.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RV_HIERARCHY);
        con.add(new DBCondition(CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_HIERARCHY));
        deleteByCondition(CaAccountRv.class, con.toArray(new DBCondition[con.size()]));
    }

    /**
     * @Description 加入群
     * @Author lijingcheng
     * @param acctId
     * @param userId
     * @param phoneId
     * @param titleRoleId
     * @param relationshipType
     * @param validDate
     * @param expireDate
     * @return
     */
    public void addMemberAutoGroup(Long acctId, Long userId, String phoneId, Integer titleRoleId, Integer relationshipType,
            Date validDate, Date expireDate, CaAccountGroup vpn)
    {
        CaAccountRv where = this.querySingle(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId, acctId),
                new DBCondition(CaAccountRv.Field.resourceId, userId));
        if (where != null)
        {
            // 已经存在于这个群
            return;
        }
        CaAccountRv rv = new CaAccountRv();
        rv.setAcctId(acctId);
        rv.setResourceId(userId);
        // @Date 2012-03-30 lijc3 phoneId为空设置为-1
        if (CommonUtil.isEmpty(phoneId))
        {
            rv.setPhoneId(String.valueOf(-1));
        }
        else
        {
            rv.setPhoneId(phoneId);
        }
        rv.setRelationshipType(relationshipType);
        rv.setTitleRoleId(titleRoleId);
        rv.setCreateDate(context.getRequestDate());
        rv.setStatus(EnumCodeDefine.ACCOUNT_ACTIVE);
        rv.setSoDate(context.getRequestDate());
        rv.setSoNbr(context.getSoNbr());
        rv.setValidDate(validDate);
        rv.setExpireDate(expireDate);
        this.insert(rv);
        //2012-10-15 luojb  #57570 群成员反向表
        this.insertGrpRevs(rv);

        CaAccountGroup value = new CaAccountGroup();
        value.setMemberNumber(vpn.getMemberNumber().longValue() + 1);
        // CaAccountGroup where2 = new CaAccountGroup();
        // where2.setAcctId(vpn.getAcctId());
        this.updateByCondition(value, new DBCondition(CaAccountGroup.Field.acctId, vpn.getAcctId()));
    }

    /**
     * @Description 退出群
     * @Author lijingcheng
     * @param acctId
     * @param userId
     * @param vpn
     */
    public void delMemberOutGroup(Long acctId, Long userId, CaAccountGroup vpn, Date expireDate)
    {
        expireDate = expireDate == null ? context.getRequestDate() : expireDate;
        CaAccountRv where = new CaAccountRv();
        CaAccountRv value = new CaAccountRv();
        value.setExpireDate(expireDate);
        where.setAcctId(acctId);
        where.setResourceId(userId);
        this.updateByCondition(value, new DBCondition(CaAccountRv.Field.acctId, acctId), new DBCondition(
                CaAccountRv.Field.resourceId, userId));
        
        //2012-10-15 luojb  #57570 群成员反向表
        this.delGrpRevs(userId, acctId);
        
        CaAccountGroup value2 = new CaAccountGroup();
        value2.setMemberNumber(vpn.getMemberNumber().longValue() - 1);
        value2.setExpireDate(expireDate);
        // CaAccountGroup where2 = new CaAccountGroup();
        // where2.setAcctId(vpn.getAcctId());
        this.updateByCondition(value2, new DBCondition(CaAccountGroup.Field.acctId, vpn.getAcctId()));
    }

    /**
     * @Description 更改群成员状态
     * @Author lijingcheng
     * @param userId
     * @param groupId
     * @param sts
     */
    public void changeGroupMemberSts(Long userId, Long groupId, Integer sts)
    {
        // CaAccountRv where = new CaAccountRv();
        CaAccountRv value = new CaAccountRv();
        value.setStatus(sts);
        // where.setAcctId(groupId);
        // where.setResourceId(userId);

        this.updateByCondition(value, new DBCondition(CaAccountRv.Field.acctId, groupId), new DBCondition(
                CaAccountRv.Field.resourceId, userId));
    }

    /**
     * @Description: 创建VPN的callscreen产品
     * @param groupInfo
     * @return
     * @author: tangjl5
     * @Date: 2012-4-28
     */
    public SProductOrder[] orderCallScreanForVpn(Long groupId, Date validDate, Date expireDate)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        PmProductOffering offering = prodCmp.queryOfferingByBusiFlag(SpecCodeDefine.MCS_NUMBER);
        if (null == offering)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.LIFECYCLE_LACK_UNIDENTITY_OFFERING, SpecCodeDefine.MCS_NUMBER);
        }
        
        SProductOrder prodOrder = new SProductOrder();
        prodOrder.setAcct_id(groupId);
        prodOrder.setOffer_id(offering.getProductOfferingId().longValue());
        prodOrder.setValid_date(DateUtil.formatDate(validDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        prodOrder.setExpire_date(DateUtil.formatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        SProductOrder[] prodOrderArr = new SProductOrder[] { prodOrder };
        SProductOrderList orderList = new SProductOrderList();
        orderList.setItem(prodOrderArr);
        imsLogger.info("---------start to order callScreen product[offeringId="+offering.getProductOfferingId()+"] for group["+groupId+"]");
        prodCmp.addProductInfo(orderList);
        imsLogger.info("---------finish order callScreen product[offeringId="+offering.getProductOfferingId()+"] for group["+groupId+"]");
        return prodOrderArr;
    }
}
