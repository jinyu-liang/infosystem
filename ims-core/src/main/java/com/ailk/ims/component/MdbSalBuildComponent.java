package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAccount;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctContact;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctSplitPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SBudgetInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SBudgetInfoDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SCustomer;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SCustomizedList;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SEmailInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SExemption;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroup;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupExterior;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupRela;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SIdentity;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SIdentityBound;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SIdentityPwd;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SImsiSerialNoRel;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SNbrInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromCharValue;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromComposite;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromPriceParam;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SRejectList;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SResourceMap;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSpecShareDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUsagePay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUser;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPayDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctRel;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCell;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCust;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserGroup;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserGroupTier;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserHome;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserMarket;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserMonitor;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserOrderConfirm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserPbx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserPort;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserRela;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserResPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserServiceStatus;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserSvc;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SValidChange;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.ims.cache.OfferCacheBean;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ColCodeDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.AmountUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.MdbUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPeriod;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.bill.entity.CaMdbBillCycle;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResServiceStatus;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserMarket;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrderConfirm;
import com.ailk.openbilling.persistence.cust.entity.CmUserPbx;
import com.ailk.openbilling.persistence.cust.entity.CmUserPortability;
import com.ailk.openbilling.persistence.cust.entity.CmUserValidchange;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdBundleComposite;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoProdShareAlloc;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CmUserInt;
import com.ailk.openbilling.persistence.cust.entity.CmUserSvcInt;
import com.ailk.openbilling.persistence.imssdl.entity.SUserValidityChg;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * MDB Sal Build组件
 * 
 * @Description
 * @author tangkun
 * @Date 2012-6-20
 * @Date 2012-06-25 yangjh特殊号码没有pay_acct_id 避免空指鐿加判政
 * @Date 2012-06-25 yangjh buildGroupProm getObject_id改成product_id
 * @Date 2012-07-04 yangjh 增加servId的上
 * @Date 2012-07-04 yangjh SBudgetInfo增加object_id和object_type
 * @date 2012-07-10 luojb #50229 删除co_prod_valid, co_prod 增加prod_valid_date，prod_expire_date
 * @2012-08-04 wukl 转化方法调整,对long类型使用string2Integer导致异常
 * @date 2012-8-7 tangjl 个性化群产品上发时，servID应该使用coprod表中的objectId
 * @Date 2012-09-05 lijc3 亲情号码上发使用CO_FN_CHAR_VALUE的生失效时间
 * @Date 2012-09-12 zengxr 58970 set budget.measure_id in MDB
 * @date 2012-10-26 wukl 上海批开用户时填成最大值，跟割接保持同
 * @Date 2012-11-14 wukl上海群类型上发时不需要转
 * @2012-11-30 wukl 账处要用到统付标识，而上线前夕MDB不愿扩字段，考虑2808 特征值上海没用，则用来存放统付标
 */
public class MdbSalBuildComponent extends BaseComponent
{

    public SUserCycle buildUserCycle(CmResLifecycle cmResourceLifecycle,boolean diffException)
    {
        SUserCycle userCycle = new SUserCycle();

        userCycle.set_servId(cmResourceLifecycle.getResourceId());
        StringBuffer userSts = new StringBuffer();
        // 如果OsSts为空则表10
        if (null != cmResourceLifecycle.getOsSts())
            userSts.append(cmResourceLifecycle.getOsSts());
        else
            userSts.append("10");
        if (null != cmResourceLifecycle.getReratingFlag())
            userSts.append(cmResourceLifecycle.getReratingFlag());
        else
            userSts.append("0");
        if (null != cmResourceLifecycle.getUnbillingFlag())
            userSts.append(cmResourceLifecycle.getUnbillingFlag());
        else
            userSts.append("0");
        if (null != cmResourceLifecycle.getUserrequestFlag())
            userSts.append(cmResourceLifecycle.getUserrequestFlag());
        else
            userSts.append("0");
        if (null != cmResourceLifecycle.getFrauldFlag())
            userSts.append(cmResourceLifecycle.getFrauldFlag());
        else
            userSts.append("0");
        if (null != cmResourceLifecycle.getSts())
        {
            // 截取后两
            userSts.append(String.valueOf(cmResourceLifecycle.getSts()).substring(2));
        }
        if (userSts.length() > 0)
            userCycle.set_userCycle(CommonUtil.string2Integer(userSts.toString()));
        userCycle.set_expireDate(this.toMdbExpireDate(cmResourceLifecycle.getExpireDate()));
        userCycle.set_validDate(this.toMdbValidDate(cmResourceLifecycle.getValidDate()));
        // usercycle上发标记为设置为2

//        if (context.isSync())
//        {
//            userCycle.set_syncFlag(2);
//        }else if(diffException){
//        	userCycle.set_syncFlag(0);
//        }
//        else
//        {
//            userCycle.set_syncFlag(0);
//        }
       /*****20161013修改*****/ 
        if(diffException||context.isManualFlag()){
        	userCycle.set_syncFlag(0);
        }else{
        	userCycle.set_syncFlag(2);
        }
        
        
        // 2012-04-03 zengxr getNextSts null
        if (cmResourceLifecycle.getNextSts() == null)
        {
            userCycle.set_nextCycle(userCycle.get_userCycle());
        }
        else
        {
            // @Date 2012-4-19 tangjl5 若用户的下一个生命周期状态不斿位，组装憿位传给mdb
            if (String.valueOf(cmResourceLifecycle.getNextSts()).length() < 8)
            {
                StringBuffer userNextSts = new StringBuffer();
                // 如果OsSts为空则表10
                if (null != cmResourceLifecycle.getOsSts())
                    userNextSts.append(cmResourceLifecycle.getOsSts());
                else
                    userNextSts.append("10");
                if (null != cmResourceLifecycle.getReratingFlag())
                    userNextSts.append(cmResourceLifecycle.getReratingFlag());
                else
                    userNextSts.append("0");
                if (null != cmResourceLifecycle.getUnbillingFlag())
                    userNextSts.append(cmResourceLifecycle.getUnbillingFlag());
                else
                    userNextSts.append("0");
                if (null != cmResourceLifecycle.getUserrequestFlag())
                    userNextSts.append(cmResourceLifecycle.getUserrequestFlag());
                else
                    userNextSts.append("0");
                if (null != cmResourceLifecycle.getFrauldFlag())
                    userNextSts.append(cmResourceLifecycle.getFrauldFlag());
                else
                    userNextSts.append("0");
                if (null != cmResourceLifecycle.getSts())
                {
                    userNextSts.append(String.valueOf(cmResourceLifecycle.getNextSts()).substring(2));
                }
                userCycle.set_nextCycle(CommonUtil.string2Integer(userNextSts.toString()).intValue());
            }
            else
            {
                userCycle.set_nextCycle(cmResourceLifecycle.getNextSts());
            }
        }

        // @Date 2012-06-14 wukl 增加生命周期状态停机位的上
        userCycle.set_stsDtl(cmResourceLifecycle.getOsStsDtl());

        userCycle.set_nextExpireDate(this.toMdbExpireDate(cmResourceLifecycle.getNextStsExpiredate()));
        // 设置默认
        return userCycle;
    }

    /**
     * @Description: 构造SUserGroup
     * @param rdl
     * @author: tangkun
     * @Date: 2012-6-18
     */
    public SUserGroup buildCorpUserGroup(CaAccountRv caAccountRv,boolean diffException)
    {
        SUserGroup sUserGroup = new SUserGroup();

        sUserGroup.set_authCode(MdbUtil.parseGroupAuthCode(caAccountRv));
        sUserGroup.set_servId(caAccountRv.getResourceId());
        sUserGroup.set_serviceAcctId(caAccountRv.getAcctId());
        sUserGroup.set_validDate(this.toMdbValidDate(caAccountRv.getValidDate()));
        sUserGroup.set_expireDate(this.toMdbExpireDate(caAccountRv.getExpireDate()));
        sUserGroup.set_userCycle(caAccountRv.getStatus());
        sUserGroup.set_tpId(caAccountRv.getTpId()==null?0:caAccountRv.getTpId());
        if(diffException||context.isManualFlag()){
            sUserGroup.set_syncFlag(0);
        }else{
            sUserGroup.set_syncFlag(2);
        }
        short titleRoleId = 42;
        if (caAccountRv.getTitleRoleId() != null)
        {
            titleRoleId = caAccountRv.getTitleRoleId().shortValue();
        }
        sUserGroup.set_titleRoleId(titleRoleId);
        sUserGroup.set_phoneId(caAccountRv.getPhoneId());
        return sUserGroup;
    }

    public SAcctBillCycle buildBillCycle(CaBillingPlan billingPlan,boolean diffException)
    {
        OfferCacheBean cacheBean = SpringUtil.getOfferCacheBean();
        SAcctBillCycle imAcct = new SAcctBillCycle();
        imAcct.set_acctId(billingPlan.getAcctId());
        imAcct.set_billingPlanId(billingPlan.getBillingPlanId());
        List<CaBillingCycleSpec> cycleList = cacheBean.queryAcctCycleSpec(billingPlan.getCycleSpecId());
        if (CommonUtil.isNotEmpty(cycleList))
        {
            List<CaBillingPeriod> periodList = cacheBean.queryAcctBillPeriodList(cycleList.get(0).getPeriodId());
            if (CommonUtil.isNotEmpty(periodList))
            {
                CaBillingPeriod period = periodList.get(0);
                imAcct.set_cycleType(period.getPeriodType());
                imAcct.set_cycleUnit(period.getPeriodUnit());
            }
        }
        imAcct.set_expireDate(this.toMdbExpireDate(billingPlan.getExpireDate()));
        imAcct.set_validDate(this.toMdbValidDate(billingPlan.getValidDate()));
        // 2012-06-05 yangjh 增加firstBillDate上发
        imAcct.set_firstBillDate(this.toMdbValidDate(billingPlan.getFirstBillDate()));
        // TODO 2012-05-31 解决打包异常
        imAcct.set_cycleSpecId(billingPlan.getCycleSpecId());
        imAcct.set_syncFlag(0);

        return imAcct;
    }

    /**
     * @param caMdbBillCycle
     * @return
     */
    public SAcctBillCycle buildMdbBillCycle(CaMdbBillCycle caMdbBillCycle,boolean diffException)
    {
        OfferCacheBean cacheBean = SpringUtil.getOfferCacheBean();
        SAcctBillCycle imAcct = new SAcctBillCycle();
        imAcct.set_acctId(caMdbBillCycle.getAcctId());
        imAcct.set_billingPlanId(caMdbBillCycle.getBillPlanId());
        List<CaBillingCycleSpec> cycleList = cacheBean.queryAcctCycleSpec(caMdbBillCycle.getCycleSpecId());
        if (CommonUtil.isNotEmpty(cycleList))
        {
            List<CaBillingPeriod> periodList = cacheBean.queryAcctBillPeriodList(cycleList.get(0).getPeriodId());
            if (CommonUtil.isNotEmpty(periodList))
            {
                CaBillingPeriod period = periodList.get(0);
                imAcct.set_cycleType(period.getPeriodType());
                imAcct.set_cycleUnit(period.getPeriodUnit());
            }
        }
        imAcct.set_expireDate(this.toMdbExpireDate(caMdbBillCycle.getExpireDate()));
        imAcct.set_validDate(this.toMdbValidDate(caMdbBillCycle.getValidDate()));
        imAcct.set_firstBillDate(this.toMdbValidDate(caMdbBillCycle.getFirstBillDate()));
        imAcct.set_cycleSpecId(caMdbBillCycle.getCycleSpecId());
        imAcct.set_syncFlag(0);
        MdbUtil.setNull2Default(imAcct);

        return imAcct;
    }

    /**
     * @Description: 构造SUserGroupTier
     * @param rdl
     * @author: tangkun
     * @Date: 2012-6-18
     */
    public SUserGroupTier buildUserGroupTier(CaAccountRel caRel,boolean diffException)
    {
        SUserGroupTier groupTier = new SUserGroupTier();
        groupTier.set_sacctId(caRel.getGroupId());
        groupTier.set_relSacctId(caRel.getRelGroupId());
        groupTier.set_titleRoleId(Short.valueOf(String.valueOf(caRel.getTitleRoleId())));
        groupTier.set_validDate(this.toMdbValidDate(caRel.getValidDate()));
        groupTier.set_expireDate(this.toMdbExpireDate(caRel.getExpireDate()));
        if(diffException||context.isManualFlag()){
            groupTier.set_syncFlag(0);
        }else{
            groupTier.set_syncFlag(2);
        }

        return groupTier;
    }

    public SUserGroup buildVpnUserGroup(CaAccountRv rv, Integer groupType,boolean diffException)
    {
        SUserGroup group = new SUserGroup();
        group.set_servId(rv.getResourceId());
        group.set_serviceAcctId(rv.getAcctId());

        group.set_userCycle(rv.getStatus());
        /*
         * if (groupType == null) { CaAccount account = context.getComponent(AccountComponent
         * .class).queryAccountById(rv.getAcctId()); Integer accountType = account.getAccountType(); if (accountType ==
         * EnumCodeDefine.ACCOUNT_TYPE_VPN) { groupType = 1;// vpn } else if (accountType == EnumCodeDefine.ACCOUNT_TYPE_CUG) {
         * groupType = 2;// cug } else if (accountType == EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY) { groupType = 3;// community } }
         */
        group.set_tpId(rv.getTpId());
        group.set_authCode(MdbUtil.parseGroupAuthCode(rv));
        group.set_validDate(this.toMdbValidDate(rv.getValidDate()));
        group.set_expireDate(this.toMdbExpireDate(rv.getExpireDate()));
        short titleRoleId = 42;
        if (rv.getTitleRoleId() != null)
        {
            titleRoleId = rv.getTitleRoleId().shortValue();
        }
        group.set_titleRoleId(titleRoleId);
        if(diffException){
            group.set_syncFlag(0);
        }else{
            group.set_syncFlag(context.getSyncFlag(rv.getValidDate()));	
        }

        return group;
    }

    public SGroup buildGroup(CaAccountGroup vpn,boolean diffException)
    {
        SGroup group = new SGroup();
        group.set_serviceAcctId(vpn.getAcctId());
        group.set_authCode(MdbUtil.parseGroupAuthCode(vpn));

        group.set_validDate(this.toMdbValidDate(vpn.getValidDate()));
        group.set_expireDate(this.toMdbExpireDate(vpn.getExpireDate()));
   
        // @Date 2012-6-7 tangjl Bug #47944 groupType,accountType未上
        Integer accountType = vpn.getAccountType();
        Integer groupType = accountType;
        // @Date 2012-11-14 wukl上海群类型上发时不需要转
        if (!ProjectUtil.is_CN_SH())
        {
            if (accountType == EnumCodeDefine.ACCOUNT_TYPE_VPN)
            {
                groupType = 1;// vpn
            }
            else if (accountType == EnumCodeDefine.ACCOUNT_TYPE_CUG)
            {
                groupType = 2;// cug
            }
            else if (accountType == EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY)
            {
                groupType = 3;// community
            }
            else if (accountType == EnumCodeDefine.ACCOUNT_TYPE_SPECNBR_GCA)
            {
                groupType = 4;
            }
        }

        group.set_groupType(groupType);
        // 2012-06-25 yangjh特殊号码没有pay_acct_id 避免空指鐿加判政
//        if (CommonUtil.isValid(vpn.getPayAcctId()))
//        {
//            group.set_payAcctId(vpn.getPayAcctId());
//        }
        group.set_syncFlag(0);

        return group;
    }

    /**
     * @Description: 用户小区信息表CUserCell
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUserCell buildSUserCell(List<CoProdCharValue> charValueList, CoProd prod,boolean diffException)
    {
        SUserCell userCell = new SUserCell();
        userCell.set_promNo(prod.getProductId());
        userCell.set_validDate(this.toMdbValidDate(prod.getValidDate()));
        userCell.set_expireDate(this.toMdbExpireDate(prod.getExpireDate()));
        if (prod.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            userCell.set_servId(prod.getObjectId());
        }

        for (CoProdCharValue charValue : charValueList)
        {
            if (charValue.getSpecCharId() == SpecCodeDefine.HOME_ZONE_CELL)
            {
                userCell.set_cellCode(CommonUtil.string2Integer(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.HOME_ZONE_CELL_OPPOSITE)
            {
                userCell.set_oppCellCode(CommonUtil.string2Integer(charValue.getValue()));
            }
        }
        if(diffException){
            userCell.set_syncFlag(0);
        }else{
            userCell.set_syncFlag(context.getSyncFlag(prod.getValidDate()));	
        }
        return userCell;
    }

    /**
     * @Description: 用户关系号码表CUserRela
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public List<SUserRela> buildSUserRela(List<CoFnCharValue> charValueList,boolean diffException)
    {
        List<SUserRela> relaList = new ArrayList<SUserRela>();

        for (CoFnCharValue charValue : charValueList)
        {
            if (charValue.getSpecCharId() == SpecCodeDefine.FRIENDNUMBER_NUMBER)
            {
                SUserRela userRela = new SUserRela();
                userRela.set_promNo(charValue.getProductId());
                userRela.set_validDate(this.toMdbValidDate(charValue.getValidDate()));
                userRela.set_expireDate(this.toMdbExpireDate(charValue.getExpireDate()));
                if (charValue.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                {
                    userRela.set_servId(charValue.getObjectId());
                }
                if (SysUtil.getInt("default_syncmdb_without_zero",1) == 1){
        			String str = charValue.getValue();
                    String newStr = str.replaceAll("^(0+)", "");
                    userRela.set_oppNumber(newStr);
        		}else{
        			userRela.set_oppNumber(charValue.getValue());
        		}
                if(diffException||context.isManualFlag()){
                    userRela.set_syncFlag(0);
                }else{
                	userRela.set_syncFlag(2);
                }
                relaList.add(userRela);
            }
        }

        return relaList;
    }

    /**
     * 群间产品、群特殊号码产品 对端关系表CGroupRela luojb 2012-7-31
     * 
     * @param charValueList
     * @param prod
     * @return
     */
    public SGroupRela buildSGroupRela(List<CoProdCharValue> charValueList, Integer busiFlag,boolean diffException)
    {
        SGroupRela groupRela = new SGroupRela();
        groupRela.set_promNo(charValueList.get(0).getProductId());
        groupRela.set_promType(busiFlag);
        groupRela.set_serviceAcctId(charValueList.get(0).getObjectId());
        // @Date 2012-09-28 yangjh : 增加valie_date expire_date赋
        groupRela.set_validDate(this.toMdbValidDate(charValueList.get(0).getValidDate()));
        groupRela.set_expireDate(this.toMdbValidDate(charValueList.get(0).getExpireDate()));

        for (CoProdCharValue charValue : charValueList)
        {
            groupRela.set_validDate(IMSUtil.getMdbDate(charValue.getValidDate()));
            groupRela.set_expireDate(IMSUtil.getMdbDate(charValue.getExpireDate()));
            if (charValue.getSpecCharId() == SpecCodeDefine.INTER_GROUP_ID)
            {
                groupRela.set_oppServiceAcctId(CommonUtil.string2Long(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.INTER_GROUP_AUTH)
            {
                groupRela.set_authCode(CommonUtil.string2Integer(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.OUTNET_PHONE_ID)
            {
                groupRela.set_oppServiceAcctId(CommonUtil.string2Long(charValue.getValue()));
                int authcode = context.getComponent(VPNComponent.class).parseGroupAuthCode(
                        context.get3hTree().loadGroup3hBean(charValueList.get(0).getObjectId()).getGroup());
                groupRela.set_authCode(authcode);
                break;
            }
        }
        if(diffException||context.isManualFlag()){
            groupRela.set_syncFlag(0);
        }else{
        	groupRela.set_syncFlag(2);
        }
        return groupRela;
    }

    /**
     * @Description: 用户自定义控制表CCustomizedList
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SCustomizedList buildCCustomizedList(List<CoProdCharValue> charValueList, Integer offerId,boolean diffException)
    {
        SCustomizedList customized = new SCustomizedList();
        customized.set_objectId(charValueList.get(0).getObjectId());
        customized.set_objectType(charValueList.get(0).getObjectType());
        customized.set_promOfferId(offerId);
        customized.set_promNo(charValueList.get(0).getProductId());
        customized.set_validDate(this.toMdbValidDate(charValueList.get(0).getValidDate()));
        customized.set_expireDate(this.toMdbExpireDate(charValueList.get(0).getExpireDate()));
        for (CoProdCharValue charValue : charValueList)
        {
            if (charValue.getSpecCharId() == SpecCodeDefine.SPECIAL_NUMBER_NUMBER)
            {
                customized.set_oppNumber(charValue.getValue());
            }
        }
        if(diffException){
            customized.set_syncFlag(0);
        }else{
            customized.set_syncFlag(context.getSyncFlag(charValueList.get(0).getValidDate()));
        }
        customized.set_classId(-999);
        customized.set_oppNumber("-999");
        customized.set_scenarioId(-999);
        return customized;
    }

    /**
     * @Description: 黑白名单接续控制主表CRejectList
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SRejectList buildSReject(List<CoProdCharValue> charValueList,boolean diffException)
    {
        SRejectList reject = new SRejectList();
        reject.set_objectId(charValueList.get(0).getObjectId());
        reject.set_objectType(charValueList.get(0).getObjectType());
        for (CoProdCharValue charValue : charValueList)
        {
            reject.set_validDate(this.toMdbValidDate(charValue.getValidDate()));
            reject.set_expireDate(this.toMdbExpireDate(charValue.getExpireDate()));
            if (charValue.getSpecCharId() == SpecCodeDefine.MCS_NUMBER_NUMBER)
            {
                reject.set_oppNumber(charValue.getValue());
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.MCS_NUMBER_TYPE)
            {
                reject.set_listType(CommonUtil.string2Integer(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.MCS_NUMBER_RULE)
            {
                reject.set_rejectRuleId(CommonUtil.string2Integer(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.MCS_NUMBER_SEVICE)
            {
                reject.set_serviceId(CommonUtil.string2Integer(charValue.getValue()));
            }
        }
        if(diffException){
            reject.set_syncFlag(0);
        }else{
        reject.set_syncFlag(context.getSyncFlag(charValueList.get(0).getValidDate()));
        }
        return reject;
    }

    /**
     * @Description: 话单代付表CUsagePay
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUsagePay buildSUsagePay(List<CoProdCharValue> charValueList,boolean diffException)
    {
        SUsagePay pay = new SUsagePay();
        pay.set_promNo(charValueList.get(0).getProductId());
        pay.set_validDate(this.toMdbValidDate(charValueList.get(0).getValidDate()));
        pay.set_expireDate(this.toMdbExpireDate(charValueList.get(0).getExpireDate()));
        // 2012-07-24 luojb
        pay.set_payServId(charValueList.get(0).getObjectId());
        // replace null set
        CsdlArrayList<SNbrInfo> nbrInfoList = new CsdlArrayList<SNbrInfo>(SNbrInfo.class);
        pay.set_oppNumberList(nbrInfoList);
        pay.set_bepaidServId(-999);
        pay.set_scenarioId(0);
        pay.set_segId(0);
        pay.set_priority(0);
        for (CoProdCharValue charValue : charValueList)
        {
            if (charValue.getSpecCharId() == SpecCodeDefine.RU_USER_ID)
            {
                pay.set_bepaidServId(CommonUtil.string2Integer(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.REGUIDE_USAGE_POLICY_ID)
            {
                pay.set_scenarioId(CommonUtil.string2Integer(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.RU_TIME_PERIOD)
            {
                pay.set_segId(CommonUtil.string2Integer(charValue.getValue()));
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.RU_B_NUMBER)
            {
                SNbrInfo info = new SNbrInfo();
                info.set_oppNumber(charValue.getValue());
                nbrInfoList.add(info);
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.RU_PRIORITY)
            {
                pay.set_priority(CommonUtil.string2Integer(charValue.getValue()));
            }
        }
        if(diffException){
            pay.set_syncFlag(0);
        }else{
        pay.set_syncFlag(context.getSyncFlag(charValueList.get(0).getValidDate()));
        }
        return pay;
    }

    /**
     * @Description: SLA规则表CSlaRule
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    /*
     * public SSlaRule buildSSlaRule(List<CoProdCharValue> charValueList, CoProd prod) { SSlaRule rule = new SSlaRule();
     * rule.set_promNo(prod.getProductId()); rule.set_expireDate(this.toMdbExpireDate(prod.getExpireDate()));
     * rule.set_validDate(this.toMdbValidDate(prod.getValidDate())); for (CoProdCharValue charValue : charValueList) { if
     * (charValue.getSpecCharId() == SpecCodeDefine.BUDGET_RULE) {
     * rule.set_slaRuleId(CommonUtil.string2Integer(charValue.getValue())); break; } } rule.set_syncFlag(context.getSyncFlag());
     * MdbUtil.setNull2Default(rule); return rule; }
     */

    /**
     * @Description: 预算控制明细蟿CBudgetInfoDtl
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     * @Date 2012-08-24 wangdw5 : #56843 BudgetInfoDtl根据GROUPID分组进行上发
     */
    public List<SBudgetInfoDtl> budilBudgetInfoDtl(List<CoBudgetCharValue> charValues,boolean diffException)
    {
        // List<CoBudgetCharValue> charValues = null;
        // try
        // {
        // charValues = IMSUtil.matchDataObject(charValueList,
        // new CacheCondition(CoBudgetCharValue.Field.groupId,0,Operator.LESS));
        // }
        // catch (Exception e)
        // {
        // }
        if (CommonUtil.isEmpty(charValues))
            return null;
        List<SBudgetInfoDtl> dtls = new ArrayList<SBudgetInfoDtl>();
        Map<Long, SBudgetInfoDtl> dtlMap = new HashMap<Long, SBudgetInfoDtl>();
        for (CoBudgetCharValue charValue : charValues)
        {
            SBudgetInfoDtl dtl = dtlMap.get(charValue.getGroupId());
            if (dtl == null)
            {
                dtl = new SBudgetInfoDtl();
                dtl.set_objectId(charValue.getObjectId());
                dtl.set_objectType(charValue.getObjectType());
                dtl.set_promNo(charValue.getProductId());
                dtl.set_expireDate(this.toMdbExpireDate(charValue.getExpireDate()));
                dtl.set_validDate(this.toMdbValidDate(charValue.getValidDate()));
                // CUserCycle \CExemption \CBudgetInfoDtl特殊的设置为2
                if(diffException||context.isManualFlag()){
                	dtl.set_syncFlag(0);
                }
                else
                {
                    dtl.set_syncFlag(2);
                }

                // replace null set
                dtl.set_remindRuleId(-999);
                dtl.set_threshold(-999);
                dtl.set_action(-999);
                dtl.set_notifyType(-999);
                dtl.set_contactNumber("-999");
                dtl.set_contactAddress("-999");
                dtlMap.put(charValue.getGroupId(), dtl);
                dtls.add(dtl);
            }
            switch (charValue.getSpecCharId().intValue())
            {
            case SpecCodeDefine.BUDGET_NOTI_RULE_NOTI:
                dtl.set_remindRuleId(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.BUDGET_THRESHOLD_VALUE:
                dtl.set_threshold(CommonUtil.string2Integer(charValue.getValue()));
                //使用阀值对应的记录的生效时间和失效时间
                dtl.set_validDate(this.toMdbValidDate(charValue.getValidDate()));
                dtl.set_expireDate(this.toMdbExpireDate(charValue.getExpireDate()));
                break;
            case SpecCodeDefine.BUDGET_ACTION:
                dtl.set_action(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_TYPE:
                dtl.set_notifyType(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_PHONE:
                dtl.set_contactNumber(charValue.getValue());
                break;
            case SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_ADDR:
                dtl.set_contactAddress(charValue.getValue());
                break;
            case SpecCodeDefine.BUDGET_AMOUNT:
                if (ProjectUtil.is_CN_SH())
                {
                    dtl.set_intervalThreshold(CommonUtil.string2Long(charValue.getValue()));
                }
                break;
            }
        }

        return dtls;
    }

    /**
     * @Description: 预算控制信息
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SBudgetInfo buildSBudgetInfo(List<CoBudgetCharValue> charValueList,boolean diffException)
    {
    	/*
        List<CoBudgetCharValue> charValues = null;
        try
        {
            charValues = (List<CoBudgetCharValue>) IMSUtil.matchDataObject(charValueList, new CacheCondition(
                    CoBudgetCharValue.Field.groupId, 0));
        }
        catch (Exception e)
        {
        }

        if (CommonUtil.isEmpty(charValues))
            return null;
*/

        SBudgetInfo budget = new SBudgetInfo();
        Date validDate = charValueList.get(0).getValidDate();
        Date expireDate = charValueList.get(0).getExpireDate();
        Long objectId = charValueList.get(0).getObjectId();
        Integer objectType = charValueList.get(0).getObjectType();
        Long productId = charValueList.get(0).getProductId();
        // 2012-07-04 yangjh SBudgetInfo增加object_id和object_type

        budget.set_budgetObjId(objectId);
        budget.set_budgetObjType(objectType);
        budget.set_promNo(productId);
        // replace null set
        budget.set_budgetRuleId(0);
        budget.set_effectObjType(-999);
        budget.set_condition(0);
        budget.set_billType(-1);
        budget.set_measureId(-999);
        budget.set_expireDate(this.toMdbExpireDate(expireDate));
        budget.set_validDate(this.toMdbValidDate(validDate));
        if(diffException||context.isManualFlag()){
            budget.set_syncFlag(0);

        }else{
        budget.set_syncFlag(2);
        }
        for (CoBudgetCharValue charValue : charValueList)
        {
            switch (charValue.getSpecCharId().intValue())
            {
            case SpecCodeDefine.BUDGET_RULE:
                budget.set_budgetRuleId(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.BUDGET_OBJECT_TYPE:
                budget.set_effectObjType(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.BUDGET_VALIDATE_CONDITION:
                budget.set_condition(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.BUDGET_PAYMODE:
                budget.set_billType(CommonUtil.string2Integer(charValue.getValue()));
                break;
            // 2012-09-12 zengxr set budget.measure_id in MDB
            case SpecCodeDefine.BUDGET_MEASURE_ID:
                budget.set_measureId(CommonUtil.string2Integer(charValue.getValue()));
                break;
                //使用阀值对应记录的生失效时间
            case SpecCodeDefine.BUDGET_THRESHOLD_VALUE:
            	budget.set_expireDate(this.toMdbExpireDate(charValue.getExpireDate()));
                budget.set_validDate(this.toMdbValidDate(charValue.getValidDate()));
            	break;
            }
        }
        
        return budget;
    }

    /**
     * @Description: 用户资源分账信息
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUserResPay buildSUserResPay(List<CoProdCharValue> charValueList,boolean diffException)
    {
        SUserResPay userResPay = new SUserResPay();
        userResPay.set_promNo(charValueList.get(0).getProductId());
        userResPay.set_validDate(this.toMdbValidDate(charValueList.get(0).getValidDate()));
        userResPay.set_expireDate(this.toMdbExpireDate(charValueList.get(0).getExpireDate()));
        if(diffException){
            userResPay.set_syncFlag(0);
        }else{
        userResPay.set_syncFlag(context.getSyncFlag(charValueList.get(0).getValidDate()));
        }
        if (charValueList.get(0).getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            userResPay.set_payAcctId(charValueList.get(0).getObjectId());
        }
        // replace null set
        userResPay.set_freeResItem(-999);
        userResPay.set_payServId(-999);
        userResPay.set_paidObjId(-999);
        userResPay.set_paidObjType(-999);
        userResPay.set_payRuleId(-999);
        userResPay.set_payThreshold(-999);
        userResPay.set_priority(-999);

        for (CoProdCharValue charValue : charValueList)
        {
            switch (charValue.getSpecCharId().intValue())
            {
            case SpecCodeDefine.RC_OBJECT_ID:
                userResPay.set_paidObjId(CommonUtil.string2Long(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_OBJECT_TYPE:
                userResPay.set_paidObjType(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_BUSI_SERVICE_ID:
                userResPay.set_payRuleId(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_AMOUNT:
                userResPay.set_payThreshold(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_PRIORITY:
                userResPay.set_priority(CommonUtil.string2Integer(charValue.getValue()));
                break;
            }
        }
        return userResPay;
    }

    /**
     * @Description: 账户分账信息
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SAcctSplitPay buildSAcctSplitPay(List<CoSplitCharValue> charValueList,boolean diffException)
    {
        Long productId = charValueList.get(0).getProductId();
        Date validDate = charValueList.get(0).getValidDate();
        Date expireDate = charValueList.get(0).getExpireDate();
        Long payAcctId = charValueList.get(0).getObjectId();

        SAcctSplitPay acctSplitPay = new SAcctSplitPay();
        acctSplitPay.set_expireDate(this.toMdbExpireDate(expireDate));
        acctSplitPay.set_validDate(this.toMdbValidDate(validDate));
        acctSplitPay.set_promNo(productId);
        if(diffException||context.isManualFlag()){
            acctSplitPay.set_syncFlag(0);
        }else{
        	acctSplitPay.set_syncFlag(2);
        }
        // 2012-07-24 luojb
        acctSplitPay.set_payMeasureId(AmountUtil.getDefaultDbMeasureId());
        //acctSplitPay.set_payAcctId(payAcctId);

        // replace null set
        //acctSplitPay.set_paidPromNo(-999);
       // acctSplitPay.set_paidObjId(-999);\
        acctSplitPay.set_paidObjId(charValueList.get(0).getObjectId());
       // acctSplitPay.set_paidObjType(-999);
        acctSplitPay.set_paidObjType(0);
        acctSplitPay.set_priority(-999);
        acctSplitPay.set_payRuleId(-999);
        acctSplitPay.set_payThreshold(-999);
        acctSplitPay.set_payNumerator(-999);
        acctSplitPay.set_payDenominator(-999);
        acctSplitPay.set_payRemindRule(-999);
        acctSplitPay.set_payRemindFailRule(-999);
        acctSplitPay.set_paidRemindRule(-999);
        acctSplitPay.set_paidRemindFailRule(-999);
        acctSplitPay.set_payMeasureId(-999);
        acctSplitPay.set_ownAcctId(-999);
        acctSplitPay.set_billType(-999);
        acctSplitPay.set_bindType(-999);
        setAcctSplitPay(charValueList, acctSplitPay);
        return acctSplitPay;
    }

    private SAcctSplitPay setAcctSplitPay(List<CoSplitCharValue> charValueList, SAcctSplitPay acctSplitPay)
    {
        for (CoSplitCharValue charValue : charValueList)
        {
            switch (charValue.getSpecCharId().intValue())
            {
            // @2012-08-04 wukl 转化方法调整,对long类型使用string2Integer导致异常
            case SpecCodeDefine.SPLIT_PRODUCT_ID:
                acctSplitPay.set_paidPromNo(CommonUtil.string2Long(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_OBJECT_ID:
                acctSplitPay.set_paidObjId(CommonUtil.string2Long(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_OBJECT_TYPE:
                acctSplitPay.set_paidObjType(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_PRIORITY:
                acctSplitPay.set_priority(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_PRICE_RULE_ID:
                acctSplitPay.set_payRuleId(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_AMOUNT:
                acctSplitPay.set_payThreshold(CommonUtil.string2Long(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_NUMERATOR:
                acctSplitPay.set_payNumerator(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_DENOMINATOR:
                acctSplitPay.set_payDenominator(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_PAY_SUCC_NOTI:
                // 上海payremindrule使用存放split_method
                if (!ProjectUtil.is_CN_SH())
                {
                    acctSplitPay.set_payRemindRule(CommonUtil.string2Integer(charValue.getValue()));
                }
                break;
            case SpecCodeDefine.SPLIT_PAY_FAIL_BAR:
                acctSplitPay.set_payRemindFailRule(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI:
                acctSplitPay.set_paidRemindRule(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR:
                acctSplitPay.set_paidRemindFailRule(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_MEASURE_ID:
                acctSplitPay.set_payMeasureId(CommonUtil.string2Integer(charValue.getValue()));
                break;
            // 2012-08-28 lijc3 增加11816特征值上
            case SpecCodeDefine.SPLIT_PAID_ACCT_ID:
                acctSplitPay.set_ownAcctId(CommonUtil.string2Long(charValue.getValue()));
                break;

            // @2012-11-30 wukl 账处要用到统付标识，而上线前夕MDB不愿扩字段，考虑12808
            // 特征值上海没用，则用来存放统付标
            case SpecCodeDefine.SPLIT_METHOD:
                if (ProjectUtil.is_CN_SH())
                {
                    acctSplitPay.set_payRemindRule(CommonUtil.string2Integer(charValue.getValue()));
                }
                break;
            
                //add by songcc 2014-2-12
            case SpecCodeDefine.SPLIT_ACCT_MAIN_USER_ID:
                
                acctSplitPay.set_switchServId(CommonUtil.string2Long(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_FILL_TYPE:
                acctSplitPay.set_billType(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.SPLIT_BIND_TYPE:
                acctSplitPay.set_bindType(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.PAY_ACCT_ID:
                acctSplitPay.set_payAcctId(CommonUtil.string2Long(charValue.getValue()));
                break;
            }
        }
        return acctSplitPay;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param coProdCharValue
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SPromCharValue buildSPromCharValue(CoProdCharValue coProdCharValue,boolean diffException)
    {
        SPromCharValue sProdCharValue = new SPromCharValue();

        sProdCharValue.set_objectId(coProdCharValue.getObjectId());
        sProdCharValue.set_objectType(coProdCharValue.getObjectType());
        sProdCharValue.set_expireDate(this.toMdbExpireDate(coProdCharValue.getExpireDate()));
        sProdCharValue.set_validDate(this.toMdbValidDate(coProdCharValue.getValidDate()));
        if(diffException||context.isManualFlag()){
            sProdCharValue.set_syncFlag(0);
	
        }else{
        	sProdCharValue.set_syncFlag(2);
        }
        sProdCharValue.set_groupId(coProdCharValue.getGroupId());

        sProdCharValue.set_specCharId(coProdCharValue.getSpecCharId());
        sProdCharValue.set_promNo(coProdCharValue.getProductId());
        sProdCharValue.set_value(coProdCharValue.getValue());
        return sProdCharValue;
    }

    /**
     * @Description: 用户资金账户分账信息明细
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUserAcctPayDtl buildSUserAcctPayDtl(List<CoProdCharValue> charValueList,boolean diffException)
    {
        SUserAcctPayDtl acctPayDtl = new SUserAcctPayDtl();

        acctPayDtl.set_expireDate(this.toMdbExpireDate(charValueList.get(0).getExpireDate()));
        acctPayDtl.set_validDate(this.toMdbValidDate(charValueList.get(0).getValidDate()));
        acctPayDtl.set_promNo(charValueList.get(0).getProductId());
        acctPayDtl.set_condition(0);
        if(diffException){
            acctPayDtl.set_syncFlag(0);	
        }else{
            acctPayDtl.set_syncFlag(context.getSyncFlag(charValueList.get(0).getValidDate()));	
        }
        Integer rcNumerator = null;
        Integer rcDenominator = null;
        // replace null set
        acctPayDtl.set_payRuleId(-999);
        acctPayDtl.set_payThreshold(-999);
        acctPayDtl.set_payNumerator(-999);
        acctPayDtl.set_payDenominator(-999);
        acctPayDtl.set_payRemindThreshold(-999);
        acctPayDtl.set_payRemindRule(-999);
        acctPayDtl.set_paidRemindThreshold(-999);
        acctPayDtl.set_paidRemindRule(-999);
        acctPayDtl.set_measureId(-999);
        acctPayDtl.set_payRemindFailRule(-999);
        acctPayDtl.set_paidRemindFailRule(-999);
        for (CoProdCharValue charValue : charValueList)
        {
            switch (charValue.getSpecCharId().intValue())
            {
            case SpecCodeDefine.RC_BUSI_SERVICE_ID:
                acctPayDtl.set_payRuleId(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_AMOUNT:
                acctPayDtl.set_payThreshold(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_NUMERATOR:
                acctPayDtl.set_payNumerator(CommonUtil.string2Integer(charValue.getValue()));
                rcNumerator = CommonUtil.string2Integer(charValue.getValue());
                break;
            case SpecCodeDefine.RC_DENOMINATOR:
                acctPayDtl.set_payDenominator(CommonUtil.string2Integer(charValue.getValue()));
                rcDenominator = CommonUtil.string2Integer(charValue.getValue());
                break;
            case SpecCodeDefine.RC_WARN_MAXVALUE:
                acctPayDtl.set_payRemindThreshold(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_PAY_SUCC_NOTI:
                acctPayDtl.set_payRemindRule(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_TARGET_WARN_MAXVALUE:
                acctPayDtl.set_paidRemindThreshold(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_BEPAID_SUCC_NOTI:
                acctPayDtl.set_paidRemindRule(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_MEASURE_ID:
                acctPayDtl.set_measureId(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_PAY_FAIL_BAR:
                acctPayDtl.set_payRemindFailRule(CommonUtil.string2Integer(charValue.getValue()));
                break;
            case SpecCodeDefine.RC_BEPAID_FAIL_BAR:
                acctPayDtl.set_paidRemindFailRule(CommonUtil.string2Integer(charValue.getValue()));
                break;
            }
        }

        if (rcNumerator != null && rcDenominator != null && rcNumerator != rcDenominator.intValue())
        {
            acctPayDtl.set_payType(EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE);
        }
        else
        {
            acctPayDtl.set_payType(EnumCodeDefine.PAY_PART_TYPE_AMOUNT);
        }
        return acctPayDtl;
    }

    /**
     * @Description: 用户资金账户分账信息
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUserAcctPay buildCUserAcctPay(List<CoProdCharValue> charValueList,boolean diffException)
    {
        SUserAcctPay userAcctPay = new SUserAcctPay();
        userAcctPay.set_promNo(charValueList.get(0).getProductId());
        if(diffException){
            userAcctPay.set_syncFlag(0);	
        }else{
        userAcctPay.set_syncFlag(context.getSyncFlag(charValueList.get(0).getValidDate()));
        }
        userAcctPay.set_validDate(this.toMdbValidDate(charValueList.get(0).getValidDate()));
        userAcctPay.set_expireDate(this.toMdbExpireDate(charValueList.get(0).getExpireDate()));
        if (charValueList.get(0).getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            userAcctPay.set_payAcctId(charValueList.get(0).getObjectId());
        }
        // replace null set
        userAcctPay.set_paidObjId(-999);
        userAcctPay.set_paidObjType(-999);
        userAcctPay.set_priority(-999);
        userAcctPay.set_segment(-999);

        for (CoProdCharValue charValue : charValueList)
        {
            if (charValue.getSpecCharId() == SpecCodeDefine.RC_OBJECT_ID)
            {
                userAcctPay.set_paidObjId(CommonUtil.string2Long(charValue.getValue()));
            }

            if (charValue.getSpecCharId() == SpecCodeDefine.RC_OBJECT_TYPE)
            {
                userAcctPay.set_paidObjType(CommonUtil.string2Integer(charValue.getValue()));
            }

            if (charValue.getSpecCharId() == SpecCodeDefine.RC_PRIORITY)
            {
                userAcctPay.set_priority(CommonUtil.string2Integer(charValue.getValue()));
            }

            if (charValue.getSpecCharId() == SpecCodeDefine.RC_TIME_PERIOD)
            {
                userAcctPay.set_segment(CommonUtil.string2Integer(charValue.getValue()));
            }
        }
        return userAcctPay;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param info
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUserHome buildSUserHome(CoProdCellInfo info,boolean diffException)
    {
        SUserHome home = new SUserHome();
        // 2012-07-04 yangjh 增加servId的上
        if (info.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            home.set_servId(info.getObjectId());
        }
        home.set_expireDate(this.toMdbExpireDate(info.getExpireDate()));
        home.set_lacId(info.getLacId());
        home.set_promNo(info.getProductId());
        home.set_sacId(info.getSacId());
        if(diffException||context.isManualFlag()){
            home.set_syncFlag(0);
        }else{
            home.set_syncFlag(2);	
        }
        home.set_validDate(this.toMdbValidDate(info.getValidDate()));
        return home;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param coProdPriceParam
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SPromPriceParam buildSPromPriceParam(CoProdPriceParam coProdPriceParam,boolean diffException)
    {
        SPromPriceParam sProdPriceParam = new SPromPriceParam();
        sProdPriceParam.set_objectId(coProdPriceParam.getObjectId());
        sProdPriceParam.set_objectType(coProdPriceParam.getObjectType());
        sProdPriceParam.set_expireDate(this.toMdbExpireDate(coProdPriceParam.getExpireDate()));

        if(diffException||context.isManualFlag()){
            sProdPriceParam.set_syncFlag(0);

        }else{
        	 sProdPriceParam.set_syncFlag(2);
        }
        sProdPriceParam.set_paramKeyId(coProdPriceParam.getParamId());
        sProdPriceParam.set_paramValue(Long.parseLong(coProdPriceParam.getParamValue()));
        //sProdPriceParam.set_paramValue(this.parsePriceParamValue(coProdPriceParam.getParamId(), coProdPriceParam.getParamValue()));
        // sProdPriceParam.set_pricePlanId(coProdPriceParam.getPricingPlanId());
        sProdPriceParam.set_promNo(coProdPriceParam.getProductId());
        sProdPriceParam.set_validDate(this.toMdbValidDate(coProdPriceParam.getValidDate()));
        return sProdPriceParam;
    }

    /**
     * 特殊的两个产品二次议价参数，上发需要*10000 #61584 luojb 2012-10-17
     * 
     * @param coProdPriceParam
     * @return
     */
    public long parsePriceParamValue(Integer paramId, String paramValue)
    {
        double value = CommonUtil.string2Double(paramValue);
        if (CommonUtil.isIn(ColCodeDefine.SPEC_CODE_PRICE_PARAM, paramId))
        {
            return CommonUtil.double2Long(value * 10000);
        }
        return CommonUtil.double2Long(value);
    }

    /**
     * 特殊的两个产品二次议价参数，从mdb读取后要转换回来 #61584 luojb 2012-10-17
     * 
     * @param priceParam
     * @return
     */
    public String getPriceParamValue(SPromPriceParam priceParam)
    {
        long paramId = priceParam.get_paramKeyId();
        long value = priceParam.get_paramValue();
        if (CommonUtil.isIn(ColCodeDefine.SPEC_CODE_PRICE_PARAM, (int) paramId))
        {
            return CommonUtil.long2String(value / 10000);
        }
        return CommonUtil.long2String(value);
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param coProdBillingCycle
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SPromBillCycle buidlSPromBillCycle(CoProdBillingCycle coProdBillingCycle,boolean diffException)
    {
        SPromBillCycle sProdBillCycle = new SPromBillCycle();
        if (null != coProdBillingCycle.getCycleType())
        {
            sProdBillCycle.set_cycleType(coProdBillingCycle.getCycleType().shortValue());
        }

        if (null != coProdBillingCycle.getCycleUnit())
        {
            sProdBillCycle.set_cycleUnit(coProdBillingCycle.getCycleUnit().shortValue());
        }
        // 2012-5-14 zhangzj3 PromBillCycle.m_nDeductMode 字段不再实例
        // sProdBillCycle.set_deductMode(coProdBillingCycle.getDeductFlag());
        sProdBillCycle.set_expireDate(this.toMdbExpireDate(coProdBillingCycle.getExpireDate()));
        sProdBillCycle.set_firstBillDate(this.toMdbValidDate(coProdBillingCycle.getFirstBillDate()));
        sProdBillCycle.set_validDate(this.toMdbValidDate(coProdBillingCycle.getValidDate()));

        // 直接使用CO_PROD_BILLING_CYCLE.CYCLE_SYNC_FLAG lijc3
        sProdBillCycle.set_cycleFlag(coProdBillingCycle.getCycleSyncFlag());
        if(diffException||context.isManualFlag()){
            sProdBillCycle.set_syncFlag(0);	
        }else{
        	sProdBillCycle.set_syncFlag(2);
        }
        sProdBillCycle.set_promNo(coProdBillingCycle.getProductId());
        sProdBillCycle.set_objectId(coProdBillingCycle.getObjectId());
        sProdBillCycle.set_objectType(CommonUtil.IntegerToShort(coProdBillingCycle.getObjectType()));
        sProdBillCycle.set_cycleCount(coProdBillingCycle.getCycleNum());
        return sProdBillCycle;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param cmResServ
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUserServiceStatus buildSUserServiceStatus(CmResServCycle cmResServ,boolean diffException)
    {
        SUserServiceStatus userService = new SUserServiceStatus();
        userService.set_servId(cmResServ.getResourceId());
        userService.set_serviceId(cmResServ.getServiceSpecId());
        userService.set_validDate(this.toMdbValidDate(cmResServ.getValidDate()));
        userService.set_expireDate(this.toMdbExpireDate(cmResServ.getExpireDate()));
        userService.set_createDate(this.toMdbValidDate(cmResServ.getCreateDate()));
        if(diffException||context.isManualFlag()){
            userService.set_syncFlag(0);	
        }else{
        	userService.set_syncFlag(2);
        }
        userService.set_usedFlag(cmResServ.getUsedFlag());
        return userService;
    }
    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param CmResServiceStatus
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SUserServiceStatus buildSUserServiceStatus(CmResServiceStatus cmResServ,boolean diffException)
    {
        SUserServiceStatus userService = new SUserServiceStatus();
        userService.set_servId(cmResServ.getResourceId());
        userService.set_serviceId(cmResServ.getServiceId());
        //userService.set_serviceCycle(cmResServ.getServStateCode());
        userService.set_osSts(cmResServ.getOsSts());
        userService.set_osStsDtl(cmResServ.getOsStsDtl());
        userService.set_validDate(this.toMdbValidDate(cmResServ.getValidDate()));
        userService.set_expireDate(this.toMdbExpireDate(cmResServ.getExpireDate()));
        userService.set_createDate(this.toMdbValidDate(cmResServ.getCreateDate()));
        if(diffException||context.isManualFlag()){
            userService.set_syncFlag(0);	
        }else{
        	userService.set_syncFlag(2);
        }
        userService.set_usedFlag(-1);
        return userService;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    /*
     * public SPromPrice buildSPromPrice(CoProd coProd) { SPromPrice sProdPrice = new SPromPrice();
     * sProdPrice.set_objectId(coProd.getObjectId()); sProdPrice.set_objectType(coProd.getObjectType());
     * sProdPrice.set_pricePlanId(coProd.getPricingPlanId()); sProdPrice.set_promNo(coProd.getProductId());
     * sProdPrice.set_validDate(this.toMdbExpireDate(coProd.getValidDate()));
     * sProdPrice.set_expireDate(this.toMdbExpireDate(coProd.getExpireDate())); sProdPrice.set_syncFlag(context.getSyncFlag());
     * MdbUtil.setNull2Default(sProdPrice); return sProdPrice; }
     */

    /**
     * @Description: 账户级产
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SAcctProm buildSAcctProm(CoProd coProd,boolean diffException)
    {
        SAcctProm acctProm = new SAcctProm();
        acctProm.set_acctId(coProd.getObjectId());
        if (coProd.getBillingType() != null)
        {
            acctProm.set_billMode(coProd.getBillingType());
        }
        // 2012-07-05 yangjh add
        if (!MdbUtil.isSpecialProd(coProd.getPricingPlanId()))
        {
            acctProm.set_pricePlanId(coProd.getPricingPlanId());
        }
        acctProm.set_expireDate(this.toMdbExpireDate(coProd.getExpireDate()));
        // 非空判断，防止转换报空指针异
        if (null != coProd.getIsMain())
        {
            acctProm.set_isMain(coProd.getIsMain());
        }
        acctProm.set_promClass(coProd.getProdTypeId());
        acctProm.set_promCycle(coProd.getLifecycleStatusId());
        acctProm.set_promNo(coProd.getProductId());
        acctProm.set_promOfferId(coProd.getProductOfferingId());
        acctProm.set_promType(coProd.getBusiFlag());
        if(diffException||context.isManualFlag()){
            acctProm.set_syncFlag(0);
        }else{
        acctProm.set_syncFlag(2);
        }
        acctProm.set_validDate(this.toMdbValidDate(coProd.getValidDate()));
        // 2012-07-10 luojb
        acctProm.set_promValidDate(this.toMdbValidDate(coProd.getProdValidDate()));
        acctProm.set_promExpireDate(this.toMdbExpireDate(coProd.getProdExpireDate()));
        return acctProm;
    }

    /**
     * @Description: 普通群订购的产
     * @param coProd
     * @param prodOffering
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SGroupProm buildGroupProm(CoProd coProd,CoProdCharValue charValue,boolean diffException)
    {
        SGroupProm groupProm = new SGroupProm();
        // 普通群订购(CO_PROD->OBJECT_ID when OBJECT_TYPE == 2)
       
        // 普通群订购,默认
        // [49494]将群的servId改为负的群id 严川
        long servId = 0l;
        long serviceAcctId = 0l;

        if(coProd.getProdTypeId() == EnumCodeDefine.PROD_TYPE_GROUP_1){
        	
        	 servId = coProd.getObjectId();
        	 serviceAcctId = Long.parseLong(charValue.getValue());
        	 
        }else if(coProd.getProdTypeId() == EnumCodeDefine.PROD_TYPE_GROUP_2){
        	 servId =  coProd.getObjectId() *(-1);
        	 serviceAcctId = coProd.getObjectId();
        }
        groupProm.set_serviceAcctId(serviceAcctId);
        groupProm.set_servId(servId);
        if (coProd.getBillingType() != null)
        {
            groupProm.set_billMode(coProd.getBillingType());
        }
        // 2012-07-05 yangjh add
        if (!MdbUtil.isSpecialProd(coProd.getPricingPlanId()))
        {
            groupProm.set_pricePlanId(coProd.getPricingPlanId());
        }
        groupProm.set_expireDate(this.toMdbExpireDate(coProd.getExpireDate()));
        // 非空判断，防止转换报空指针异
        if (null != coProd.getIsMain())
        {
            groupProm.set_isMain(coProd.getIsMain());
        }
        groupProm.set_promClass(coProd.getProdTypeId());
        groupProm.set_promCycle(coProd.getLifecycleStatusId());
        // 2012-06-25 yangjh getObject_id改成product_id
        groupProm.set_promNo(coProd.getProductId());
        groupProm.set_promOfferId(coProd.getProductOfferingId());
        groupProm.set_promType(coProd.getBusiFlag());
        if(diffException||context.isManualFlag()){
            groupProm.set_syncFlag(0);	
        }else{
        	groupProm.set_syncFlag(2);
        }
        groupProm.set_validDate(this.toMdbValidDate(coProd.getValidDate()));
        // 2012-07-10 luojb
        groupProm.set_promValidDate(this.toMdbValidDate(coProd.getProdValidDate()));
        groupProm.set_promExpireDate(this.toMdbExpireDate(coProd.getProdExpireDate()));
        return groupProm;
    }

    /**
     * @Description: 群个性化产品订购
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SGroupProm buildPersonalGroupProm(CoProd coProd, PmProductOffering prodOffering,boolean diffException)
    {
        SGroupProm groupProm = new SGroupProm();

        // 群个性化订购(CO_PROD->OBJECT_ID when OBJECT_TYPE == 0)
        if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            // @Date 2012-8-7 tangjl 个性化群产品上发时，servID应该使用coprod表中的objectId
            groupProm.set_servId(coProd.getObjectId());
        }
        // 群个性化从规13001,13101,13201获取
        Integer key = CommonUtil.string2Integer(coProd.getBusiFlag() + "01");
        List<CoProdCharValue> charValueList = context.getAllCacheList(CoProdCharValue.class, new CacheCondition(
                CoProdCharValue.Field.productId, coProd.getProductId()),
                new CacheCondition(CoProdCharValue.Field.specCharId, key));
        if (CommonUtil.isEmpty(charValueList))
        {
            IMSUtil.removeRouterParam();
            if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
            {
                IMSUtil.setUserRouterParam(coProd.getObjectId());
            }
            else
            {
                IMSUtil.setAcctRouterParam(coProd.getObjectId());
            }
            charValueList = DBUtil.query(CoProdCharValue.class,
                    new DBCondition(CoProdCharValue.Field.productId, coProd.getProductId()), new DBCondition(
                            CoProdCharValue.Field.specCharId, key));
        }
        if (CommonUtil.isEmpty(charValueList))
        {
        	groupProm.set_promType(SpecCodeDefine.GROUP_IN);
        	groupProm.set_servId(coProd.getObjectId() * (-1));
        }
        if (CommonUtil.isNotEmpty(charValueList))
        {
        	Long service_AcctId = CommonUtil.string2Long(charValueList.get(0).getValue());
            groupProm.set_serviceAcctId(service_AcctId);
            if (service_AcctId.equals(coProd.getObjectId())) {
            	imsLogger.debug("**********135************");
            	groupProm.set_promType(SpecCodeDefine.GROUP_IN);
            	groupProm.set_servId(coProd.getObjectId() * (-1));
			}else{
				groupProm.set_promType(coProd.getBusiFlag());
			}
        }

        if (coProd.getBillingType() != null)
        {
            groupProm.set_billMode(coProd.getBillingType());
        }
        // 2012-07-05 yangjh add
        if (!MdbUtil.isSpecialProd(coProd.getPricingPlanId()))
        {
            groupProm.set_pricePlanId(coProd.getPricingPlanId());
        }
        groupProm.set_expireDate(this.toMdbExpireDate(coProd.getExpireDate()));
        // 非空判断，防止转换报空指针异
        if (null != coProd.getIsMain())
        {
            groupProm.set_isMain(coProd.getIsMain());
        }
        groupProm.set_promClass(prodOffering.getOfferTypeId());
        groupProm.set_promCycle(coProd.getLifecycleStatusId());
        groupProm.set_promNo(coProd.getProductId());
        groupProm.set_promOfferId(coProd.getProductOfferingId());
        if(diffException||context.isManualFlag()){
            groupProm.set_syncFlag(0);	
        }else{
        groupProm.set_syncFlag(context.getSyncFlag(coProd.getValidDate()));
        }
        groupProm.set_validDate(this.toMdbValidDate(coProd.getValidDate()));
        // 2012-07-10 luojb
        groupProm.set_promValidDate(this.toMdbValidDate(coProd.getProdValidDate()));
        groupProm.set_promExpireDate(this.toMdbExpireDate(coProd.getProdExpireDate()));
        return groupProm;
    }

    public SGroupExterior buildGroupExterior(CaAccountRv rv,boolean diffException)
    {
        SGroupExterior groupExterior = new SGroupExterior();
        //手机号码左边去0
        if (SysUtil.getInt("default_syncmdb_without_zero",1) == 1)
        {
            String str = rv.getPhoneId();
            String newStr = str.replaceAll("^(0+)", "");
            groupExterior.set_phoneNumber(newStr);
        }
        else
        {
            groupExterior.set_phoneNumber(rv.getPhoneId());
        }
        groupExterior.set_serviceAcctId(rv.getAcctId());
        groupExterior.set_titleRoleId(Short.valueOf(String.valueOf(rv.getTitleRoleId())));
        groupExterior.set_validDate(this.toMdbValidDate(rv.getValidDate()));
        groupExterior.set_expireDate(this.toMdbExpireDate(rv.getExpireDate()));
        if(diffException||context.isManualFlag()){
            groupExterior.set_syncFlag(0);
        }else{
        	groupExterior.set_syncFlag(2);
        }
        return groupExterior;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param coProd
     * @param prodOffering
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    /**
     * @param coProd
     * @param prodOffering
     * @return
     */
    public SUserProm buildSUserProm(CoProd coProd,boolean diffException)
    {
        SUserProm userProm = new SUserProm();
        if (coProd.getBillingType() != null)
        {
            userProm.set_billMode(coProd.getBillingType());
        }
        // 2012-07-05 yangjh add
        if (!MdbUtil.isSpecialProd(coProd.getPricingPlanId()))
        {
            userProm.set_pricePlanId(coProd.getPricingPlanId());
        }
        userProm.set_expireDate(this.toMdbExpireDate(coProd.getExpireDate()));
        userProm.set_isMain(coProd.getIsMain());
        userProm.set_promClass(coProd.getProdTypeId());
        userProm.set_promNo(coProd.getProductId());
        userProm.set_promOfferId(coProd.getProductOfferingId());
        userProm.set_promType(coProd.getBusiFlag());
        userProm.set_servId(coProd.getObjectId());
        userProm.set_validDate(this.toMdbValidDate(coProd.getValidDate()));
        if(diffException||context.isManualFlag()){
            userProm.set_syncFlag(0);
	
        }else{
        	userProm.set_syncFlag(2);
        }
        userProm.set_promCycle(coProd.getLifecycleStatusId());
        // 2012-07-10 luojb
        userProm.set_promValidDate(this.toMdbValidDate(coProd.getProdValidDate()));
        userProm.set_promExpireDate(this.toMdbExpireDate(coProd.getProdExpireDate()));
        return userProm;
    }

    public SUserGroup buildVpnSpecNbrUserGroup(CaAccountRv rv,boolean diffException)
    {
        SUserGroup userGroup = new SUserGroup();

        userGroup.set_serviceAcctId(rv.getAcctId());
        userGroup.set_tpId(rv.getTpId());
        userGroup.set_validDate(this.toMdbValidDate(rv.getValidDate()));
        userGroup.set_expireDate(this.toMdbExpireDate(rv.getExpireDate()));
        if(diffException){
            userGroup.set_syncFlag(0);
 	
        }else{
        userGroup.set_syncFlag(context.getSyncFlag(rv.getValidDate()));
        }
        // replace null set
        userGroup.set_authCode(-999);
        userGroup.set_servId(-999);
        userGroup.set_userCycle(-999);
        return userGroup;
    }

    /**
     * @author yanchuan 2012-6-18
     * @describe 构建客户的XDL对象
     * @param cmCustomer
     * @param cmIndividual
     * @return
     */
    public SCustomer buildCustomer(CmCustomer cmCustomer,boolean diffException)
    {
        SCustomer sImCustomer = new SCustomer();

        sImCustomer.set_custId(cmCustomer.getCustId());
        sImCustomer.set_custType(cmCustomer.getCustomerType());
        if (null != cmCustomer.getRegionCode())
            sImCustomer.set_regionCode(cmCustomer.getRegionCode());
        sImCustomer.set_custClass(cmCustomer.getCustomerClass());
        if (null != cmCustomer.getCustomerSegment())
            sImCustomer.set_custSegment(cmCustomer.getCustomerSegment());
        sImCustomer.set_custStatus(cmCustomer.getSts());

        sImCustomer.set_createDate(this.toMdbValidDate(cmCustomer.getCreateDate()));
        sImCustomer.set_validDate(this.toMdbValidDate(cmCustomer.getValidDate()));
        sImCustomer.set_expireDate(this.toMdbExpireDate(cmCustomer.getExpireDate()));

        // replace null set
        sImCustomer.set_birthday(-999);
        sImCustomer.set_gender(-999);
        sImCustomer.set_occupation(-999);

        if (cmCustomer.getBirthDate() != null)
        {
            sImCustomer.set_birthday(this.toMdbValidDate(cmCustomer.getBirthDate()));
        }
        if (cmCustomer.getGender() != null)
            sImCustomer.set_gender(cmCustomer.getGender());
        if (cmCustomer.getOccupationId() != null)
        {
            sImCustomer.set_occupation(cmCustomer.getOccupationId());
        }
        if(diffException){
            sImCustomer.set_syncFlag(0);	
        }else{
        sImCustomer.set_syncFlag(context.getSyncFlag(cmCustomer.getValidDate()));
        }
        return sImCustomer;
    }

    /**
     * @author yanchuan 2012-04-26
     * @Description: 构建账户XDL对象
     */
    public SAccount buildAccount(CaAccount caAccount,boolean diffException)
    {
        SAccount sImAccount = new SAccount();

        sImAccount.set_acctId(caAccount.getAcctId());
        // @Date 2012-5-31 tangjl5 Bug #47201 解决空指针问
        if (caAccount.getRegionCode() != null)
            sImAccount.set_regionCode(caAccount.getRegionCode());
        if (caAccount.getCustId() != null)
            sImAccount.set_custId(caAccount.getCustId());
        if (caAccount.getCountyCode() != null)
            sImAccount.set_countyCode(caAccount.getCountyCode());
        // sImAccount.set_operatorId(); // 目前该字段暂不使yanchuan 2011-12-13
        if (caAccount.getOperatorId() != null)
            sImAccount.set_operatorId(caAccount.getOperatorId());
        if (null != caAccount.getBillSts())
            sImAccount.set_billSts(caAccount.getBillSts());
        sImAccount.set_billStsDate(this.toMdbValidDate(caAccount.getBillStsDate()));
        if (null != caAccount.getTaxFlag())
            sImAccount.set_taxFlag(caAccount.getTaxFlag());

        sImAccount.set_validDate(this.toMdbValidDate(caAccount.getValidDate()));
        sImAccount.set_expireDate(this.toMdbExpireDate(caAccount.getExpireDate()));
        sImAccount.set_createDate(this.toMdbValidDate(caAccount.getCreateDate()));
        sImAccount.set_payType(CommonUtil.int2Short(caAccount.getPaymentType()));
        if (caAccount.getAccountType() != null)
        {
            sImAccount.set_accountType((int) caAccount.getAccountType());
        }
        // @Date 2012-09-25 yugb :59271 三户信息查询优化需
        if (caAccount.getBalanceType() != null)
        {
            sImAccount.set_balanceType(CommonUtil.int2Short(caAccount.getBalanceType()));
        }
        if (caAccount.getMeasureId() != null)
        {
            sImAccount.set_measureId(caAccount.getMeasureId());
        }

        // 将帐户类型ACCOUNT_CLASS上发 yanchuan 2011-11-08

        sImAccount.set_accountClass(caAccount.getAccountClass());

        if (caAccount.getCreditSignControl() != null)
        {
            sImAccount.set_creditCtrl(caAccount.getCreditSignControl());
        }
        else
        {
            sImAccount.set_creditCtrl(EnumCodeDefine.USER_PAYMODE_PREPAID);
        }

        if (caAccount.getAccountSegment() != null)
        {
            sImAccount.set_accountSegment(caAccount.getAccountSegment());
        }
        sImAccount.set_syncFlag(0);
        // @Date 2012-10-17 yugb :上发mdb如果为空，则设置999
        MdbUtil.setNull2Default(sImAccount);
        return sImAccount;
    }

    public SUser buildUser(CmResource cmResource,boolean diffException)
    {
        SUser sImUser = new SUser();
        sImUser.set_billType(cmResource.getBillingType());
        sImUser.set_createDate(this.toMdbValidDate(cmResource.getCreateDate()));
        sImUser.set_expireDate(this.toMdbExpireDate(cmResource.getExpireDate()));
        // @date 2012-10-26 wukl 上海批开用户时填成最大值，跟割接保持同
        sImUser.set_firstUseTime(this.toMdbExpireDate(cmResource.getActiveDate()));
        // 2012-5-18 yangjh add set_flhFlag and set_continueFlag
        // 广西版本转换意义
        /*
         * sImUser.set_flhFlag(cmResource.getFlhFlag()); sImUser.set_continueFlag(cmResource.getContinueFlag());
         */
        sImUser.set_flhFlag(cmResource.getUserCode());
        sImUser.set_continueFlag(cmResource.getResourceSpecId());

        if (cmResource.getRegionCode() != null)
        {
            sImUser.set_regionCode(cmResource.getRegionCode());
        }
        if (cmResource.getCountyCode() != null)
        {
            sImUser.set_countyCode(cmResource.getCountyCode());
        }
        sImUser.set_servId(cmResource.getResourceId());
        sImUser.set_validDate(this.toMdbValidDate(cmResource.getValidDate()));
        sImUser.set_brand(cmResource.getBrandId());
        if (cmResource.getListeningLanguage() != null)
        {
            sImUser.set_langListening(cmResource.getListeningLanguage());
        }
        if (cmResource.getReadingLanguage() != null)
        {
            sImUser.set_langReading(cmResource.getReadingLanguage());
        }

        if (cmResource.getWritingLanguage() != null)
        {
            sImUser.set_langWriting(cmResource.getWritingLanguage());
        }

        if (cmResource.getResSegment() != null)
        {
            sImUser.set_userSegment(cmResource.getResSegment());
        }
        
        if (cmResource.getPaymentMode() != null) {
        	 sImUser.set_payMode(CommonUtil.int2Short(cmResource.getPaymentMode()));
		}
        sImUser.set_syncFlag(0);
        return sImUser;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param cmIdentity
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SImsiSerialNoRel buildSImsiSerialNoRel(CmResIdentity cmIdentity,boolean diffException)
    {
        SImsiSerialNoRel sImsiSerialNoRel = new SImsiSerialNoRel();
        // 2012-06-25 yangjh buildSImsiSerialNoRel 增加serialNo是否为空的判
        if (cmIdentity.getSerialNo() != null)
        {
            sImsiSerialNoRel.set_serialNo(cmIdentity.getSerialNo());
        }
        sImsiSerialNoRel.set_imsi(new Long(cmIdentity.getRelIdentity()));
        sImsiSerialNoRel.set_expireDate(this.toMdbExpireDate(cmIdentity.getExpireDate()));
        sImsiSerialNoRel.set_validDate(this.toMdbValidDate(cmIdentity.getValidDate()));
        if(diffException){
            sImsiSerialNoRel.set_syncFlag(0);
	
        }else{
        sImsiSerialNoRel.set_syncFlag(context.getSyncFlag(cmIdentity.getValidDate()));
        }
        return sImsiSerialNoRel;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作
     * @param identity
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public SIdentity buildIdentityRdl(CmResIdentity identity,boolean diffException)
    {
        SIdentity sIdentity = new SIdentity();
       
        sIdentity.set_expireDate(this.toMdbExpireDate(identity.getExpireDate()));
        // 调整为配置项配置 配置为1不带0上发，为0带0上发
        if (SysUtil.getInt("default_syncmdb_without_zero",1) == 1){
			// @Date 2012-11-19 wukl 上海要求上发的手机号码左去零
			String str = identity.getIdentity();
			String newStr = str.replaceAll("^(0+)", "");
			//long numIden = Long.parseLong(identity.getIdentity());
			sIdentity.set_identity(newStr);
		}else
		{
			sIdentity.set_identity(identity.getIdentity());
		}
        // @Date 2012-10-29 yangjh : User Story #62685 imsi字段上发
        sIdentity.set_imsi(identity.getRelIdentity());
        sIdentity.set_identityType(identity.getIdentityType());
        if (null == identity.getIdentityAttr())
        {
            sIdentity.set_identityAttr(EnumCodeDefine.DEFAULT_NULL_VALUE);
        }
        else
        {
            sIdentity.set_identityAttr(CommonUtil.int2Short(identity.getIdentityAttr()));
        }
        sIdentity.set_servId(identity.getResourceId());
        if(diffException||context.isManualFlag()){
        	sIdentity.set_syncFlag(0);
        }else {
            sIdentity.set_syncFlag(2);

        }
        sIdentity.set_validDate(this.toMdbValidDate(identity.getValidDate()));
        return sIdentity;
    }

    public SIdentityPwd buildIdentityPwd(CmResIdentity cmResourceIdentity,boolean diffException)
    {
        SIdentityPwd sIdentityPwd = new SIdentityPwd();
        sIdentityPwd.set_servId(cmResourceIdentity.getResourceId());
        sIdentityPwd.set_identity(cmResourceIdentity.getIdentity());
        sIdentityPwd.set_identityType(cmResourceIdentity.getIdentityType());
        sIdentityPwd.set_password(cmResourceIdentity.getResourcePasswd());
        sIdentityPwd.set_expireDate(this.toMdbExpireDate(cmResourceIdentity.getExpireDate()));
        sIdentityPwd.set_validDate(this.toMdbValidDate(cmResourceIdentity.getValidDate()));
        if(diffException){
            sIdentityPwd.set_syncFlag(0);
        }else{
        sIdentityPwd.set_syncFlag(context.getSyncFlag(cmResourceIdentity.getValidDate()));
        }
        return sIdentityPwd;
    }

    public SIdentityBound buildIdentityVsImei(CmIdentityVsImei cmIdentityVsImei,boolean diffException)
    {
        SIdentityBound simIdentityBound = new SIdentityBound();
        simIdentityBound.set_servId(cmIdentityVsImei.getResourceId());
        simIdentityBound.set_identity(cmIdentityVsImei.getIdentity());
        simIdentityBound.set_identityType(cmIdentityVsImei.getIdentityType());

        if (cmIdentityVsImei.getImei() != null)
        {
            simIdentityBound.set_imei(cmIdentityVsImei.getImei());
        }

        simIdentityBound.set_expireDate(this.toMdbExpireDate(cmIdentityVsImei.getExpireDate()));
        simIdentityBound.set_validDate(this.toMdbValidDate(cmIdentityVsImei.getValidDate()));
        if(diffException||context.isManualFlag()){
            simIdentityBound.set_syncFlag(0);
        }else{
        simIdentityBound.set_syncFlag(2);
        }
        return simIdentityBound;
    }

    public SAcctCycle buildAcctCycle(CaAccount caAccount,boolean diffException)
    {
        SAcctCycle sImAcctCycle = new SAcctCycle();

        sImAcctCycle.set_acctId(caAccount.getAcctId());
        if (null != caAccount.getAccountStatus())
            sImAcctCycle.set_acctCycle(caAccount.getAccountStatus());

        sImAcctCycle.set_validDate(this.toMdbValidDate(caAccount.getValidDate()));
        sImAcctCycle.set_expireDate(this.toMdbExpireDate(caAccount.getExpireDate()));
        if(diffException||context.isManualFlag()){
            sImAcctCycle.set_syncFlag(0);	
        }else{
            sImAcctCycle.set_syncFlag(2);
        }

        return sImAcctCycle;
    }


    /**
     * @Description: 构造SUserAcctRel
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-19
     */
    public SUserAcctRel buildUserAcctRel(CaAccountRes accountRel,boolean diffException)
    {
        SUserAcctRel sUserAcctRel = new SUserAcctRel();
        sUserAcctRel.set_servId(accountRel.getResourceId());
        sUserAcctRel.set_acctId(accountRel.getAcctId());
        sUserAcctRel.set_changeFlag(accountRel.getChgFlag());
        sUserAcctRel.set_payAcctId(accountRel.getResAcctId());
        sUserAcctRel.set_validDate(this.toMdbValidDate(accountRel.getValidDate()));
        sUserAcctRel.set_expireDate(this.toMdbExpireDate(accountRel.getExpireDate()));
        if (accountRel.getTitleRoleId() != null)
            sUserAcctRel.set_titleRoleId(accountRel.getTitleRoleId());
        // 需要考虑过户，这里需要写死为2
        if(diffException||context.isManualFlag()){
            sUserAcctRel.set_syncFlag(0);

        }
        else
        {
            sUserAcctRel.set_syncFlag(2);
        }

        return sUserAcctRel;
    }

    public SAcctContact buildAcctContact(CmContactMedium medium,boolean diffException)
    {
        SAcctContact contact = new SAcctContact();
        contact.set_contactType(medium.getContactType().shortValue());
        contact.set_objectId(medium.getObjectId());
        contact.set_objectType(medium.getObjectType().shortValue());
        if (SysUtil.getInt("default_syncmdb_without_zero",1) == 1){ // 根据配置，号码上发mdb不带0
			String str = medium.getMobile();
			String newStr = "";
			if (str != null) {
				newStr = str.replaceAll("^(0+)", "");
			}
            contact.set_mobile(newStr);
		}else{
			contact.set_mobile(medium.getMobile());
		}
//        contact.set_contactType(medium.getContactType());
        contact.set_validDate(this.toMdbValidDate(medium.getValidDate()));
        contact.set_expireDate(this.toMdbExpireDate(medium.getExpireDate()));
        contact.set_syncFlag(0);
        return contact;
    }

    public SSpecShareDtl buildSpecShareDtl(CoProdShareAlloc coProdShareAlloc,boolean diffException)
    {
        SSpecShareDtl sSpecShareDtl = new SSpecShareDtl();
        sSpecShareDtl.set_servId(coProdShareAlloc.getResourceId());
        sSpecShareDtl.set_amount(CommonUtil.long2Int(coProdShareAlloc.getAmount()));
        sSpecShareDtl.set_promNo(coProdShareAlloc.getProductId());
        if (null != coProdShareAlloc.getBillFlag())
            sSpecShareDtl.set_billFlag(coProdShareAlloc.getBillFlag());
        sSpecShareDtl.set_itemId(coProdShareAlloc.getItemId());
        sSpecShareDtl.set_validDate(this.toMdbValidDate(coProdShareAlloc.getValidDate()));
        sSpecShareDtl.set_expireDate(this.toMdbExpireDate(coProdShareAlloc.getExpireDate()));
        if(diffException||context.isManualFlag()){
            sSpecShareDtl.set_syncFlag(0);
        }else{
        	sSpecShareDtl.set_syncFlag(2);
        }
        return sSpecShareDtl;

    }

    /**
     * 从缓存中获取对象列表，包装成mdb需要的对象列表
     * 
     * @author yangjh 2012-6-9
     */
    public SEmailInfo buildEmailInfo(CmResource cmRes, CmContactMedium medium,boolean diffException)
    {
        SEmailInfo emailInfo = new SEmailInfo();
        if (medium != null)
        {
            emailInfo.set_email(medium.getEmailAddress());
            emailInfo.set_expireDate(this.toMdbExpireDate(medium.getExpireDate()));
            emailInfo.set_validDate(this.toMdbExpireDate(medium.getValidDate()));
            emailInfo.set_objectId(medium.getObjectId());
            emailInfo.set_objectType(EnumCodeDefine.ACCT_EMAIL_ADDRESS);
            if(diffException){
                emailInfo.set_syncFlag(0);
	
            }else{
            emailInfo.set_syncFlag(context.getSyncFlag(medium.getValidDate()));
            }
            }

        if (cmRes != null)
        {

            emailInfo.set_email(cmRes.getEmail());
            emailInfo.set_expireDate(this.toMdbExpireDate(cmRes.getExpireDate()));
            emailInfo.set_validDate(this.toMdbExpireDate(cmRes.getValidDate()));
            emailInfo.set_objectId(cmRes.getResourceId());
            emailInfo.set_objectType(EnumCodeDefine.USER_EMAIL_ADDRESS);
            if(diffException){
                emailInfo.set_syncFlag(0);
	
            }else{
            emailInfo.set_syncFlag(context.getSyncFlag(cmRes.getValidDate()));
            }
            }
        return emailInfo;
    }

    public SUserServiceStatus buildUserServiceStatus(CmResServCycle resServCycle,boolean diffException)
    {
        SUserServiceStatus sUserServiceStatus = new SUserServiceStatus();
        sUserServiceStatus.set_servId(resServCycle.getResourceId());
        sUserServiceStatus.set_serviceId(resServCycle.getServiceSpecId());
        // 非空判断，防止转换报空指针异
        sUserServiceStatus.set_validDate(this.toMdbValidDate(resServCycle.getValidDate()));
        sUserServiceStatus.set_expireDate(this.toMdbExpireDate(resServCycle.getExpireDate()));
        sUserServiceStatus.set_createDate(this.toMdbValidDate(resServCycle.getCreateDate()));
       if(diffException){
           sUserServiceStatus.set_syncFlag(0);
   
       }else{
        sUserServiceStatus.set_syncFlag(context.getSyncFlag(resServCycle.getValidDate()));
       }
        // 非空判断，防止转换报空指针异
        if (null != resServCycle.getUsedFlag())
        {
            sUserServiceStatus.set_usedFlag(resServCycle.getUsedFlag());
        }
        // fill all fields, no need to null set.
        return sUserServiceStatus;
    }

    public SUserMonitor buildUserMonitor(CmResourceMonitor cmres,boolean diffException)
    {
        SUserMonitor sim = new SUserMonitor();
        sim.set_servId(cmres.getResourceId());
        sim.set_serviceId(cmres.getServiceId());
        sim.set_policyId(cmres.getPolicyId());
        sim.set_monitorType(cmres.getMonitorType());
        sim.set_validDate(this.toMdbValidDate(cmres.getValidDate()));
        sim.set_expireDate(this.toMdbExpireDate(cmres.getExpireDate()));
        if(diffException||context.isManualFlag()){
            sim.set_syncFlag(0);
	
        }else{
        	sim.set_syncFlag(2);
        }
        return sim;
    }

    public SExemption buildExemption(CaNotifyExempt caNotifyExempt,boolean diffException)
    {
        SExemption exemption = new SExemption();
        exemption.set_objectId(caNotifyExempt.getObjectId());
        Integer exemptType = caNotifyExempt.getExemptionType();
        if(caNotifyExempt.getRecType() == EnumCodeDefine.NOTIFY_EXEMPTION_REC_NODEALUSER){
        	exemptType = exemptType + 32768 ; // 2^15次代表串的第十六位表示业务类型 上发mdb的时候
        } else if(caNotifyExempt.getRecType() == EnumCodeDefine.NOTIFY_EXEMPTION_REC_SPECUSER){
        	exemptType = exemptType + 16384 ; // 2^14次代表串的第十五位表示业务类型 上发mdb的时候
        }
        exemption.set_exemptType(exemptType);
        exemption.set_objectType(caNotifyExempt.getObjectType());
        exemption.set_expireDate(this.toMdbExpireDate(caNotifyExempt.getExpireDate()));
        exemption.set_validDate(this.toMdbValidDate(caNotifyExempt.getValidDate()));
        // @Date 2012-08-14 yangjh : story057 免催免停 增加billing_type的上
        exemption.set_billType(caNotifyExempt.getBillingType());
        // 号码上发是否带0
        /*if (SysUtil.getInt("default_syncmdb_without_zero",1) == 1){ // 根据配置，号码上发mdb不带0
			String str = caNotifyExempt.getOfficePhone();
            String newStr = str.replaceAll("^(0+)", "");
            exemption.set_officePhone(newStr);
		}else{
			exemption.set_officePhone(caNotifyExempt.getOfficePhone());
		}*/
        exemption.set_officePhone(caNotifyExempt.getOfficePhone());
        // CUserCycle \CExemption \CBudgetInfoDtl特殊的设置为2
        if(diffException||context.isManualFlag()){
            exemption.set_syncFlag(0);
	
        }
        else
        {
            exemption.set_syncFlag(2);
        }
        return exemption;
    }

    public SUserOrderConfirm buildUserOrderConfirm(CmUserOrderConfirm cmUserOrderConfirm,boolean diffException)
    {
        SUserOrderConfirm confirm = new SUserOrderConfirm();

        if (cmUserOrderConfirm.getAlarmDoneCode() != null)
        {
            confirm.set_alarmDoneCode(cmUserOrderConfirm.getAlarmDoneCode());
        }
        confirm.set_alarmTime(this.toMdbValidDate(cmUserOrderConfirm.getAlarmTime()));
        confirm.set_billFlag(cmUserOrderConfirm.getBillFlag());
        confirm.set_billProp(cmUserOrderConfirm.getBillProp());
        confirm.set_busiType(cmUserOrderConfirm.getBusiType());
        confirm.set_confirmResult(cmUserOrderConfirm.getConfirmResult());
        confirm.set_confirmTime(this.toMdbValidDate(cmUserOrderConfirm.getConfirmTime()));
        confirm.set_expireDate(this.toMdbValidDate(cmUserOrderConfirm.getExpireDate()));
        if (null != cmUserOrderConfirm.getExtendFlag())
            confirm.set_extendFlag(cmUserOrderConfirm.getExtendFlag());
        confirm.set_msisdn(cmUserOrderConfirm.getIdentity());
        confirm.set_operatorCode(cmUserOrderConfirm.getOperatorCode());
        if (null != cmUserOrderConfirm.getRegionCode())
            confirm.set_regionCode(cmUserOrderConfirm.getRegionCode());
        confirm.set_servId(cmUserOrderConfirm.getResourceId());
        confirm.set_seqNo(cmUserOrderConfirm.getSequenceNo());
        confirm.set_servType(cmUserOrderConfirm.getServType());
        confirm.set_spCode(cmUserOrderConfirm.getSpCode());
        confirm.set_thirdMsisdn(cmUserOrderConfirm.getThirdMsisdn());
        confirm.set_validDate(this.toMdbValidDate(cmUserOrderConfirm.getValidDate()));
       if(diffException||context.isManualFlag()){
           confirm.set_syncFlag(0);
   
       }else{
    	   confirm.set_syncFlag(2);
       }
        // replace null set
        confirm.set_serviceCode(cmUserOrderConfirm.getServiceCode());
        confirm.set_doneCode(cmUserOrderConfirm.getSoNbr());

        return confirm;
    }

    public SPromBillCycle buildProdBillCycle(CoProdBillingCycle billCycle,boolean diffException)
    {
        SPromBillCycle cycle = new SPromBillCycle();
        cycle.set_cycleFlag(billCycle.getCycleSyncFlag());
        cycle.set_cycleType(billCycle.getCycleType());
        cycle.set_cycleUnit(billCycle.getCycleUnit());
        cycle.set_firstBillDate(IMSUtil.getMdbDate(billCycle.getFirstBillDate()));
        cycle.set_promNo(billCycle.getProductId());
        cycle.set_objectId(billCycle.getObjectId());
        cycle.set_objectType(billCycle.getObjectType());
        cycle.set_validDate(IMSUtil.getMdbDate(billCycle.getValidDate()));
        cycle.set_expireDate(IMSUtil.getMdbDate(billCycle.getExpireDate()));
        if(diffException){
            cycle.set_syncFlag(0);

        }else{
        cycle.set_syncFlag(context.getSyncFlag(billCycle.getValidDate()));
        }
        return cycle;
    }

    /**
     * 构造打包产品关系上发结构SPromComposite luojb 2012-8-18
     * 
     * @param bundleComp
     * @return
     */
    public SPromComposite buildPromComposite(CoProdBundleComposite bundleComp,boolean diffException)
    {
        SPromComposite promComp = new SPromComposite();
        promComp.set_objectId(bundleComp.getObjectId());
        promComp.set_objectType(bundleComp.getObjectType());
        promComp.set_parentPromNo(CommonUtil.long2Int(bundleComp.getParentProductNo()));
        promComp.set_promNo(CommonUtil.long2Int(bundleComp.getProductId()));
        if(diffException){
            promComp.set_syncFlag(0);
	
        }else{
        promComp.set_syncFlag(context.getSyncFlag(context.getRequestDate()));
        }
        return promComp;
    }
    public SUserPbx buildSuserPbx(CmUserPbx cmUserPbx,boolean diffException)
    {
        //modify by songcc  2014-3-11
        SUserPbx sUserPbx = new SUserPbx();
        sUserPbx.set_mscId(cmUserPbx.getMscId());
        sUserPbx.set_servId(cmUserPbx.getResourceId());
        sUserPbx.set_areaCode(cmUserPbx.getAreaCode());
        sUserPbx.set_inTrunk(cmUserPbx.getInTrunk());
        sUserPbx.set_outTrunk(cmUserPbx.getOutTrunk());
        sUserPbx.set_accessNum(cmUserPbx.getAccessNumber());
        sUserPbx.set_matchAccessNum(cmUserPbx.getMatchingAccessNumber());
        sUserPbx.set_validDate(IMSUtil.getMdbDate(cmUserPbx.getValidDate()));
        sUserPbx.set_expireDate(IMSUtil.getMdbDate(cmUserPbx.getExpireDate()));
        if(diffException||context.isManualFlag()){
            sUserPbx.set_syncFlag(0);
	
        }else{
        	sUserPbx.set_syncFlag(2);
        }
        return sUserPbx;
    }
    public SUserPort buildSuserPort(CmUserPortability cmUserPortability,boolean diffException)
    {
        SUserPort sUserPort = new SUserPort();
        sUserPort.set_servId(cmUserPortability.getResourceId());
        sUserPort.set_regionCode(cmUserPortability.getRegionCode());
        sUserPort.set_outRegion(cmUserPortability.getOutRegion());
        sUserPort.set_inRegion(cmUserPortability.getInRegion());
        sUserPort.set_validDate(IMSUtil.getMdbDate(cmUserPortability.getValidDate()));
        sUserPort.set_expireDate(IMSUtil.getMdbDate(cmUserPortability.getExpireDate()));
        if(diffException||context.isManualFlag()){
            sUserPort.set_syncFlag(0);

        }else{
        sUserPort.set_syncFlag(2);
        }
        return sUserPort;
    }
    public SResourceMap buildSResourceMap(CmUserMap cmUserMap,boolean diffException)
    {
        SResourceMap sResourceMap = new SResourceMap();
        sResourceMap.set_identity(cmUserMap.getMsisdn());
        sResourceMap.set_imsi(cmUserMap.getImsi());
        sResourceMap.set_regionCode(cmUserMap.getRegionCode());
        sResourceMap.set_servId(cmUserMap.getResourceId());
        sResourceMap.set_validDate(IMSUtil.getMdbDate(cmUserMap.getValidDate()));
        sResourceMap.set_expireDate(IMSUtil.getMdbDate(cmUserMap.getExpireDate()));
        if(diffException||context.isManualFlag()){
            sResourceMap.set_syncFlag(0);
        }else{
        sResourceMap.set_syncFlag(2);
        }
        sResourceMap.set_busiType(cmUserMap.getBusiType());
        return sResourceMap;
    }
    public SUserMarket buildSUserMarket(CmUserMarket cmUserMarket,boolean diffException)
    {
        SUserMarket sUserMarket = new SUserMarket();
        sUserMarket.set_servId(cmUserMarket.getResourceId());
        sUserMarket.set_busiType(cmUserMarket.getBusiType());
        sUserMarket.set_spCode(cmUserMarket.getSpCode());
        sUserMarket.set_serviceCode(cmUserMarket.getServiceCode());
        sUserMarket.set_operatorCode(cmUserMarket.getOperatorCode());
        sUserMarket.set_spType(cmUserMarket.getSpType());
        sUserMarket.set_promNo(cmUserMarket.getProductId());
        sUserMarket.set_validDate(IMSUtil.getMdbDate(cmUserMarket.getValidDate()));
        sUserMarket.set_expireDate(IMSUtil.getMdbDate(cmUserMarket.getExpireDate()));
        if(diffException||context.isManualFlag()){
            sUserMarket.set_syncFlag(0);
	
        }else{
        sUserMarket.set_syncFlag(2);
        }
        return sUserMarket;
    }
    
    public SValidChange buildUserValidChange(CmUserValidchange validChange,boolean diffException){
    	SValidChange sValidChange = new SValidChange();
    	sValidChange.set_userId(validChange.getUserId());
    	sValidChange.set_validDate(IMSUtil.getMdbDate(validChange.getValidDate()));
    	sValidChange.set_expireDate(IMSUtil.getMdbDate(validChange.getExpireDate()));
    	if(diffException||context.isManualFlag()){
        	sValidChange.set_syncFlag(0);
	
    	}else{
    		sValidChange.set_syncFlag(2);
    	}
    	return sValidChange;
    }
    
    public SUserCust buildUserCust(CmUserInt userInt,boolean diffException){
    	SUserCust userCust = new SUserCust();
    	userCust.set_serialNumber(userInt.getSerialNumber());
    	userCust.set_userId(userInt.getUserId());
    	userCust.set_brandCode(userInt.getBrandCode());
    	userCust.set_vipClassId(userInt.getVipClassId());
    	userCust.set_custName(userInt.getCustName());
    	userCust.set_sex(userInt.getSex());
    	userCust.set_productId(userInt.getProductId());
    	userCust.set_userStateCodeset(userInt.getUserStateCodeset());
    	userCust.set_lastStopTime(userInt.getLastStopTime() != null?userInt.getLastStopTime().getTime()/1000:0l);
    	userCust.set_updateTime(IMSUtil.getMdbDate(userInt.getLastStopTime()));
    	userCust.set_syncFlag(0);
    	return userCust;
    	
    }
    
    public SUserSvc buildUserSvc(CmUserSvcInt userSvcInt,boolean diffException){
    	SUserSvc userSvc = new SUserSvc();
    	userSvc.set_userId(userSvcInt.getUserId());
    	userSvc.set_serviceId(userSvcInt.getServiceId());
    	userSvc.set_mainTag(userSvcInt.getMainTag());
    	userSvc.set_startDate(IMSUtil.getMdbDate(userSvcInt.getStartDate()));
    	userSvc.set_endDate(IMSUtil.getMdbDate(userSvcInt.getEndDate()));
    	userSvc.set_updateTime(IMSUtil.getMdbDate(userSvcInt.getUpdateTime()));
    	userSvc.set_syncFlag(0);
    	return userSvc;
    }
}
