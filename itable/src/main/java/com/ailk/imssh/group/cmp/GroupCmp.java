package com.ailk.imssh.group.cmp;

import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.AmountComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.itable.entity.IGroup;

/**
 * @Description：群组资料表I_GROUP 转数据库表
 * @author wangyh3
 * @Date 2012-5-14
 */
public class GroupCmp extends BaseCmp
{
    /**
     * @Description 群组资料表I_GROUP 数据通过逻辑转换，保存到数据库对应表中
     * @author wangyh3
     * @Date 2012-5-14
     * @Date 2012-08-21 wukl 动感5元业务实现方式修改
     */
    public void createGroup(IGroup iGroup)
    {
        // 发布虚账户路由信息
        // 创建群组虚账户路由的时候，只需用创建
        // @Date 2012-08-21 wukl 动感5元业务实现方式修改
        // 动感5元，目前CRM和老系统都是创建群组的方式实现的， 但这个群组不会订购产品，也不会产生费用
        // 此时支付账户填成跟群账户一致
        /*
         * context.getCmp(RouterCmp.class).createAcctsRouter(iGroup.getGroupId(), this.getContext().getCustId(),
         * iGroup.getRegionCode(),iGroup.getValidDate()); this.insertCaAccount(iGroup); if (iGroup.getBillableAcctId() != null &&
         * iGroup.getBillableAcctId().intValue() != 0) { this.insertCaAccountRel(iGroup); }
         */
        this.insertCaAccountGroup(iGroup);
    }

    /**
     * @Description 群组资料表I_GROUP 数据通过逻辑转换，从数据库对应表中删除
     * @author wangyh3
     * @Date 2012-5-14
     */
    public void deleteGroup(IGroup iGroup)
    {
        /*
         * // 删除虚账户 this.deleteByCondition(CaAccount.class, new DBCondition(CaAccount.Field.acctId, iGroup.getGroupId())); //
         * 删除群组信息 // 删除虚账户与支付账户的关联关系 this.deleteByCondition(CaAccountRel.class, new DBCondition(CaAccountRel.Field.acctId,
         * iGroup.getGroupId()), // new DBCondition(CaAccountRel.Field.relAcctId, iGroup.getBillableAcctId()), new
         * DBCondition(CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_CA_GCA_RELATION)); //2014-6-9 删除群组账户路由 *
         */
    	Date expireDate = iGroup.getExpireDate();
		expireDate = jef.tools.DateUtils.monthBegin(expireDate);
		jef.tools.DateUtils.addMonth(expireDate, 1);
        this.deleteMode1(CaAccountGroup.class,expireDate, new DBCondition(CaAccountGroup.Field.acctId, iGroup.getGroupId()));
        /*
         * context.getCmp(RouterCmp.class).deleteAcctRouter(iGroup.getGroupId(), context.getCommitDate());
         */
    }

    /**
     * @Description 群组资料表I_GROUP 数据通过逻辑转换，修改到数据库对应表中
     * @author wangyh3
     * @Date 2012-5-14
     */
    public void modifyGroup(IGroup iGroup)
    {
        this.updateCaAccountGroup(iGroup);
        /*
         * this.updateCaAccount(iGroup); // @Date 2012-08-21 wukl 动感5元业务实现方式修改 // 动感5元，目前CRM和老系统都是创建群组的方式实现的， 但这个群组不会订购产品，也不会产生费用
         * // 此时支付账户填成跟群账户一致 if (iGroup.getBillableAcctId() != null && iGroup.getBillableAcctId().intValue() != 0) {
         * this.updateCaAccountRel(iGroup); } //处理下周期失效的路由 Date curDate=
         * DateUtil.offsetDate(DateUtils.monthBegin(context.getCommitDate()),Calendar.MONTH, 1);
         * if(iGroup.getExpireDate().getTime() == curDate.getTime()){ imsLogger.info("remove group router by next cycle");
         * context.getCmp(RouterCmp.class).deleteAcctRouter(iGroup.getGroupId(),curDate); }
         */

    }

    /**
     * 实例化虚账户 wangyh3 2012-5-23
     * 
     * @param iGroup
     */
    @SuppressWarnings("unused")
    private void insertCaAccount(IGroup iGroup)
    {
        CaAccount caAccount = new CaAccount();
        caAccount.setCreateDate(this.getContext().getCommitDate());
        caAccount.setSoDate(this.getContext().getCommitDate());
        caAccount.setAcctId(iGroup.getGroupId());
        caAccount.setAccountType(iGroup.getGroupType());
        caAccount.setAccountClass((int) EnumCodeDefine.ACCOUNT_CLASS_GCA);
        caAccount.setAccountSegment(100);
        caAccount.setAccountStatus(iGroup.getStatus());
        caAccount.setStsDate(iGroup.getCommitDate());
        // caAccount.setPin(iGroup.getPin());
        caAccount.setOrgId(EnumCodeExDefine.ACCOUNT_ORG_ID);
        caAccount.setProvinceId(iGroup.getProvCode());
        caAccount.setRegionCode(iGroup.getRegionCode());
        caAccount.setCountyCode(iGroup.getCountyCode());
        caAccount.setForceCycle(EnumCodeDefine.CUSTOMER_FORCECYCLE_FALSE);
        // caAccount.setRevenueCode(iGroup.getRevenueCode());
        // @Date 2012-08-04 wukl Strory #49463 存储账务侧的度量单位
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        caAccount.setMeasureId(amountCmp.queryBillingMeasureIdByType(amountCmp.queryLocalMeasureTypeId()));
        // caAccount.setRemarks(iGroup.getRemarks());
        caAccount.setValidDate(iGroup.getValidDate());
        caAccount.setExpireDate(iGroup.getExpireDate());
        caAccount.setSoNbr(context.getSoNbr());
        caAccount.setOperatorId(EnumCodeExDefine.ACCOUNT_OPERATOR_CMCC); // 中国移动
        caAccount.setBillSts(EnumCodeDefine.BILL_STS_ACTIVE);
        caAccount.setBalanceType(EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE); // 账户级账本
        // caAccount.setBillStsDate(iGroup.getCommitDate());//虚账户不填
        caAccount.setCreditSignControl(EnumCodeExDefine.GROUP_CREDIT_SIGN_CONTROL_POST_PAID);
        caAccount.setCustId(this.getContext().getCustId());
        caAccount.setTaxFlag(1);
        caAccount.setName(iGroup.getGroupName());

        super.insert(caAccount);
    }

    /**
     * 修改虚账户 wangyh3 2012-5-23
     * 
     * @param iGroup
     */
    @SuppressWarnings("unused")
    private void updateCaAccount(IGroup iGroup)
    {
        CaAccount caAccount = new CaAccount();
        caAccount.setSoDate(this.getContext().getCommitDate());
        caAccount.setAccountStatus(iGroup.getStatus());
        caAccount.setStsDate(iGroup.getCommitDate());
        // caAccount.setValidDate(iGroup.getValidDate());
        caAccount.setExpireDate(iGroup.getExpireDate());
        caAccount.setSoNbr(context.getSoNbr());
        caAccount.setName(iGroup.getGroupName());

        this.updateByCondition(caAccount, new DBCondition(CaAccount.Field.acctId, iGroup.getGroupId()));
    }

    private CaAccountGroup convertCaAccountGroup(IGroup iGroup){
    	CaAccountGroup caAccountGroup = new CaAccountGroup();
    	caAccountGroup.setCreateDate(this.getContext().getCommitDate());
        caAccountGroup.setSoDate(this.getContext().getCommitDate());
        caAccountGroup.setAcctId(iGroup.getGroupId());
        caAccountGroup.setAccountType(iGroup.getGroupType());
        caAccountGroup.setGroupName(iGroup.getGroupName());
        // CRM传入的maxusers目前为null
        // caAccountGroup.setMaxusers(iGroup.getMaxMemberNumber().longValue());
        Integer memberNumber = iGroup.getMaxMemberNumber();
        if (memberNumber == null)
        {
            memberNumber = SysUtil.getInt(SysCodeDefine.busi.GROUP_MEMBER_DEFAULT_MAX_NUMBER);
            if (memberNumber == null)
            {
                memberNumber = 1000;
            }
        }
        caAccountGroup.setMaxusers(memberNumber.longValue());
        // caAccountGroup.setMaxclosenum(iGroup.getMaxclosenum());
        // caAccountGroup.setMaxoutnum(iGroup.getMaxoutnum());
        // caAccountGroup.setMemberNumber(iGroup.getMemberNumber());
        // caAccountGroup.setClipFlag(iGroup.getClipFlag());
        // caAccountGroup.setOutgoingCsFlag(iGroup.getOutgoingCsFlag());
        // caAccountGroup.setOcsRoutingFlag(iGroup.getOcsRoutingFlag());
        // caAccountGroup.setEspaceFlag(iGroup.getEspaceFlag());
        caAccountGroup.setContactPhone(iGroup.getContactPhone());
        if (iGroup.getFreeresShareFlag() != null)
        {
            caAccountGroup.setFreeresShareFlag(iGroup.getFreeresShareFlag());
        }
        else
        {
            caAccountGroup.setFreeresShareFlag(1);
        }
        caAccountGroup.setIngroupOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_INGROUP_OUTGOINGCALL);
        caAccountGroup.setIngroupIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_INGROUP_INCOMINGCALL);
        caAccountGroup.setCrossgroupOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_CROSSGROUP_OUTGOINGCALL);
        caAccountGroup.setCrossgroupIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_CROSSGROUP_INCOMINGCALL);
        caAccountGroup.setSpecOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_SPEC_OUTGOINGCALL);
        caAccountGroup.setSpecIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_SPEC_INCOMINGCALL);
        caAccountGroup.setOutgroupOutgoingcall(EnumCodeExDefine.GROUP_DEFAULT_OUTGROUP_OUTGOINGCALL);
        caAccountGroup.setOutgroupIncomingcall(EnumCodeExDefine.GROUP_DEFAULT_OUTGROUP_INCOMINGCALL);
        // caAccountGroup.setRemarks(iGroup.getRemarks());
        caAccountGroup.setSoNbr(context.getSoNbr());
        caAccountGroup.setValidDate(iGroup.getValidDate());
        caAccountGroup.setExpireDate(iGroup.getExpireDate());
        return caAccountGroup;
    }
    
    /**
     * 实例化群组信息 wangyh3 2012-5-23
     * 
     * @param iGroup
     */
    private void insertCaAccountGroup(IGroup iGroup)
    {
        CaAccountGroup caAccountGroup = convertCaAccountGroup(iGroup);
        
        super.insertWithAllCache(caAccountGroup,new DBCondition(CaAccountGroup.Field.acctId, iGroup.getGroupId()));
    }

    /**
     * 修改群组信息 wangyh3 2012-5-23
     * 
     * @param iGroup
     */
    private void updateCaAccountGroup(IGroup iGroup)
    {

    	CaAccountGroup caAccountGroup = convertCaAccountGroup(iGroup);
        
        this.updateMode1(caAccountGroup, new DBCondition(CaAccountGroup.Field.acctId, iGroup.getGroupId()));
    }



    /**
     * 方法主要针对下周期生效的数据未生效前又进行变更的场景；此时先删除未来生效的数据 wukl 2012-11-13
     * 
     * @param dataList
     */
    public void beforeHandle(List<? extends DataObject> dataList)
    {
        IGroup iGroup = (IGroup) dataList.get(0);
        Date expireDate = context.getCommitDate();

        // 路由中没有，表示还没用创建，直接返回
        if (!context.getCmp(RouterCmp.class).isUserRouted(iGroup.getGroupId()))
        {
            return;
        }
        ITableUtil.setValue2ContextHolder(null, null, iGroup.getGroupId());

        delete_noValid(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId, iGroup.getGroupId()), new DBCondition(
                CaAccountGroup.Field.validDate, expireDate, Operator.GREAT), new DBCondition(CaAccountGroup.Field.expireDate,
                expireDate, Operator.GREAT));
        /*
         * delete_noValid(CaAccount.class, new DBCondition(CaAccount.Field.acctId, iGroup.getGroupId()), new
         * DBCondition(CaAccount.Field.validDate, expireDate, Operator.GREAT), new DBCondition( CaAccount.Field.expireDate,
         * expireDate, Operator.GREAT)); delete_noValid(CaAccountRel.class, new DBCondition( CaAccountRel.Field.acctId,
         * iGroup.getGroupId()), new DBCondition(CaAccountRel.Field.validDate, expireDate, Operator.GREAT), new
         * DBCondition(CaAccountRel.Field.expireDate, expireDate, Operator.GREAT));
         */
    }
}
