package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.List;

import jef.database.DataObject;

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
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromCharValue;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromComposite;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromPriceParam;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SRejectList;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SResourceMap;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturnEx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSpecShare;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSpecShareDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SSyncIvrAllInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUsagePay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUser;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPayDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctRel;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCell;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCust;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCustInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserEnterprise;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserGroup;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserGroupTier;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserHome;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserMarket;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserMonitor;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserOrderConfirm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserParam;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserPbx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserPort;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserRela;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserResPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserService;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserServiceStatus;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserShareProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserSvc;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserUserRel;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SValidChange;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlHashMap;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ListMapMdbRdl;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.MdbUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
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
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdBundleComposite;
import com.ailk.openbilling.persistence.cust.entity.CoProdShareAlloc;
import com.ailk.openbilling.persistence.cust.entity.CmUserInt;
import com.ailk.openbilling.persistence.cust.entity.CmUserSvcInt;

/**
 * MDB Sal方式组件
 * 
 * @Description
 * @author wuyj
 * @Date 2012-6-16
 */
public class MdbSalComponent extends BaseComponent
{

    private MdbSalBuildComponent getMdbSalBuildComp()
    {
        return context.getComponent(MdbSalBuildComponent.class);
    }

    /*
     * public synchronized static MdbSalBuildComponent getMdbSalBuildComp(){ if(salBuildComp == null){ salBuildComp = new
     * MdbSalBuildComponent(); } return salBuildComp; }
     */

    /**
     * @Description: 构造SUserCycle
     * @param rdl
     * @author: tangjl5
     * @Date: 2012-6-18
     */
    public ListMapMdbRdl buildSUserCycleList()
    {
    	//2012-07-17 yangjh SReturn->SReturnEx
        ListMapMdbRdl userCycleRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERLIFECYCLE, SReturnEx.class);
        List<DataObject> sImUserCycleLists = context.getAllCacheList(CmResLifecycle.class);
        if (null != sImUserCycleLists && sImUserCycleLists.size() > 0)
        {
            for (DataObject sImUserCycleObject : sImUserCycleLists)
            {
                CmResLifecycle resUserCycle = (CmResLifecycle) sImUserCycleObject;
                SUserCycle userCycle = context.getComponent(MdbSalBuildComponent.class).buildUserCycle(resUserCycle,context.isDiffException());
                userCycleRdl.addRecord(userCycle.get_servId(), userCycle);

            }
        }
        return userCycleRdl;
    }

    public ListMapMdbRdl buildUserGroupList()
    {
        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERGROUP, SReturn.class);
        List<CaAccountRv> resList = context.getAllCacheList(CaAccountRv.class, new CacheCondition(
                CaAccountRv.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_AUTOGROUP));
        if (CommonUtil.isEmpty(resList))
            return null;
        for (CaAccountRv res : resList)
        {
            SUserGroup group = getMdbSalBuildComp().buildCorpUserGroup(res,context.isDiffException());
            rdl.addRecord(group.get_servId(), group);
        }
        return rdl;
    }

    /**
     * wangyh3 2012-6-20
     * 
     * @param result
     * @return
     */
    public ListMapMdbRdl buildUserGroupListNoCondition()
    {
        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERGROUP, SReturn.class);
        List<CaAccountRv> resList = context.getAllCacheList(CaAccountRv.class);
        if (CommonUtil.isEmpty(resList))
            return null;
        for (CaAccountRv res : resList)
        {
            SUserGroup group = buildCorpUserGroup(res);
            rdl.addRecord(group.get_servId(), group);
        }
        return rdl;
    }

    /**
     * @Description: 构造SUserGroup
     * @param rdl
     * @author: tangkun
     * @Date: 2012-6-18
     */
    public SUserGroup buildCorpUserGroup(CaAccountRv caAccountRv)
    {
        //SUSER_GROUP里的user_cycle字段上发 lijc3
        SUserGroup sUserGroup = new SUserGroup();
        sUserGroup.set_userCycle(caAccountRv.getStatus());
        sUserGroup.set_servId(caAccountRv.getResourceId());
        sUserGroup.set_serviceAcctId(caAccountRv.getAcctId());
        sUserGroup.set_validDate(this.toMdbValidDate(caAccountRv.getValidDate()));
        sUserGroup.set_expireDate(this.toMdbExpireDate(caAccountRv.getExpireDate()));
        sUserGroup.set_authCode(MdbUtil.parseGroupAuthCode(caAccountRv));
        sUserGroup.set_syncFlag(context.getSyncFlag(caAccountRv.getValidDate()));
        //@Date 2013-12-17 yugb :上发titleRoleId
        sUserGroup.set_titleRoleId(CommonUtil.int2Short(caAccountRv.getTitleRoleId()));
        MdbUtil.setNull2Default(sUserGroup);

        return sUserGroup;
    }

    /**
     * 从缓存中获取对象列表，包装成mdb需要的对象列表
     * 
     * @author tangkun 2012-6-18
     * @update yanchuan 改造方法，使其调用方便 2012-06-20
     */
    public ListMapMdbRdl buildImAcctBillCycleList()
    {
        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCTBILLCYCLE, SReturn.class);
        List<CaBillingPlan> billingPlan_list = context.getAllCacheList(CaBillingPlan.class);
        List<CaMdbBillCycle> caMdbBillCycleList = new ArrayList<CaMdbBillCycle>();
        if (!CommonUtil.isEmpty(billingPlan_list))
        {
            for (CaBillingPlan billingPlan : billingPlan_list)
            {
                SAcctBillCycle acctBillCycle = getMdbSalBuildComp().buildBillCycle(billingPlan,context.isDiffException());
                rdl.addRecord(acctBillCycle.get_acctId(), acctBillCycle);
            }
        }
      //@Date 2012-10-25 yugb :缓存里取不到就从context.addExtendParam(ConstantDefine.BILL_CYCLE_KEY_NAME, cycleList)缓存里获取
        if(CommonUtil.isEmpty(billingPlan_list))
        {
        	caMdbBillCycleList = (List<CaMdbBillCycle> )context.getExtendParam(ConstantDefine.BILL_CYCLE_KEY_NAME);
        	 if (!CommonUtil.isEmpty(caMdbBillCycleList))
        	 {
        		 for (CaMdbBillCycle caMdbBillCycle : caMdbBillCycleList)
                 {
                     SAcctBillCycle acctBillCycle = getMdbSalBuildComp().buildMdbBillCycle(caMdbBillCycle,context.isDiffException());
                     rdl.addRecord(acctBillCycle.get_acctId(), acctBillCycle);
                 }
        	 }
        }
        return rdl;
    }

    /**
     * @Description: 构造SUserGroupTier
     * @param rdl
     * @author: yanchuan
     * @Date: 2012-6-20
     */
    public ListMapMdbRdl buildUserGroupTierList()
    {
        ListMapMdbRdl groupTierrdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERGROUPTIER, SReturn.class);
        List<CaAccountRel> rels = context.getAllCacheList(CaAccountRel.class);
        if (CommonUtil.isEmpty(rels))
            return null;
        for (CaAccountRel rel : rels)
        {
            SUserGroupTier userGroupTier = getMdbSalBuildComp().buildUserGroupTier(rel,context.isDiffException());
            groupTierrdl.addRecord(userGroupTier.get_sacctId(), userGroupTier);
        }
        return groupTierrdl;
    }

    /**
     * @Description: 构造SUserGroup
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-18
     */
    public ListMapMdbRdl buildSUserVpnGroupList()
    {
        ListMapMdbRdl userGroupRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERGROUP, SReturn.class);
        List<CaAccountRv> caRvList = context.getAllCacheList(CaAccountRv.class);
        if (!CommonUtil.isEmpty(caRvList))
        {
            for (CaAccountRv caRv : caRvList)
            {
                SUserGroup userGroup = getMdbSalBuildComp().buildVpnUserGroup(caRv, null,context.isDiffException());
                userGroupRdl.addRecord(userGroup.get_servId(), userGroup);
            }
        }
        return userGroupRdl;
    }

    /**
     * @Description: 构造SGroup
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-18
     */
    public ListMapMdbRdl buildSGroupList()
    {
        ListMapMdbRdl groupInfoRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUPINFO, SReturn.class);
        // 鉴权
        List<CaAccountGroup> vpns = context.getAllCacheList(CaAccountGroup.class);
        if (!CommonUtil.isEmpty(vpns))
        {
            for (CaAccountGroup vpn : vpns)
            {
                SGroup group = getMdbSalBuildComp().buildGroup(vpn,context.isDiffException());
                groupInfoRdl.addRecord(group.get_serviceAcctId(), group);
            }
        }
        return groupInfoRdl;
    }

    // /**
    // * @Description: 组装产品订购列表
    // * @param result
    // * @author: tangjl5
    // * @Date: 2012-6-18
    // */
    // public void buildSalProductOrder(List<MdbRdl> result)
    // {
    // List<CoProd> coProds = context.getAllCacheList(CoProd.class);
    // if (!CommonUtil.isEmpty(coProds))
    // {
    // for (Iterator<CoProd> iterator = coProds.iterator(); iterator.hasNext();)
    // {
    // CoProd coProd = (CoProd) iterator.next();
    // List<CoProdBillingCycle> coProdBillCycles = context.getAllCacheList(CoProdBillingCycle.class, new CacheCondition(
    // CoProdBillingCycle.Field.productId, coProd.getProductId()), new CacheCondition(
    // CoProdBillingCycle.Field.soNbr, coProd.getSoNbr()));
    // List<CoProdPriceParam> coProdPriceParams = context.getAllCacheList(CoProdPriceParam.class, new CacheCondition(
    // CoProdPriceParam.Field.productId, coProd.getProductId()), new CacheCondition(
    // CoProdPriceParam.Field.soNbr, coProd.getSoNbr()));
    // List<CoFnCharValue> coFnCharValues = context.getAllCacheList(CoFnCharValue.class, new CacheCondition(
    // CoFnCharValue.Field.soNbr, coProd.getSoNbr()));
    // List<CoSplitCharValue> coSplitCharValues = context.getAllCacheList(CoSplitCharValue.class, new CacheCondition(
    // CoSplitCharValue.Field.soNbr, coProd.getSoNbr()));
    // List<CoProdCharValue> coProdCharValues = context.getAllCacheList(CoProdCharValue.class, new CacheCondition(
    // CoFnCharValue.Field.soNbr, coProd.getSoNbr()));
    //
    // if (CommonUtil.isNotEmpty(coFnCharValues))
    // {
    // if (CommonUtil.isNotEmpty(coProdCharValues))
    // {
    // coProdCharValues.addAll(ConvertUtil.coFnCharValues2CoProdCharValues(coFnCharValues));
    // }
    // else
    // {
    // coProdCharValues = ConvertUtil.coFnCharValues2CoProdCharValues(coFnCharValues);
    // }
    // }
    //
    // if (coProd != null)
    // {
    // PmProductOffering prodOffering = context.getComponent(ProductQuery.class).queryProdOffering(coProd.getProductOfferingId());
    // if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV || coProd.getObjectType() ==
    // EnumCodeDefine.PROD_OBJECTTYPE_GCA)
    // {
    // // 普通群订购的产品
    // if (GroupHelper.isGroupProd(coProd.getBusiFlag()))
    // {
    // // 群订购产品
    // ListMapMdbRdl groupProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUP_PROD,SReturn.class);
    // SGroupProm groupProm = getMdbSalBuildComp().buildGroupProm(coProd, prodOffering);
    // groupProdRdl.addRecord(coProd.getProductId(), groupProm);
    // result.add(groupProdRdl);
    // }
    // else if (GroupHelper.isGroupPersonProd(coProd.getBusiFlag()))
    // {
    // // 群个性化订购
    // ListMapMdbRdl groupProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUP_PROD,SReturn.class);
    // SGroupProm groupProm = getMdbSalBuildComp().buildPersonalGroupProm(coProd, prodOffering);
    // groupProdRdl.addRecord(coProd.getProductId(), groupProm);
    // result.add(groupProdRdl);
    // }
    // else
    // {
    // // 用户级产品
    // ListMapMdbRdl userProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USER_PROD,SReturn.class);
    // SUserProm userProm = getMdbSalBuildComp().buildSUserProm(coProd, prodOffering);
    // userProdRdl.addRecord(coProd.getProductId(), userProm);
    // result.add(userProdRdl);
    // }
    // }
    // else if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
    // {
    // // 账户级产品
    // ListMapMdbRdl acctProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCOUNT_PROD,SReturn.class);
    // SAcctProm acctProm = getMdbSalBuildComp().buildSAcctProm(coProd, prodOffering);
    // acctProdRdl.addRecord(coProd.getProductId(), acctProm);
    // result.add(acctProdRdl);
    //
    // }
    //
    // if (!MdbUtil.isSpecialProd(coProd.getPricingPlanId()))
    // {
    // ListMapMdbRdl prodPriceRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROMPRICE,SReturn.class);
    // SPromPrice sProdPrice = getMdbSalBuildComp().buildSPromPrice(coProd);
    // prodPriceRdl.addRecord(coProd.getProductId(), sProdPrice);
    // result.add(prodPriceRdl);
    // }
    //
    // // 增加上发用户服务状态对象 yanchuan 2011-10-18
    // // CsdlArrayList<SImUserServiceStatus> sImUserServiceStatusList = new CsdlArrayList<SImUserServiceStatus>(
    // // SImUserServiceStatus.class);
    //
    // // ljc 2011-09-24处理一个产品对应多个服务
    // List<Integer> sericeIds = context.getComponent(ProductComponent.class).queryServiceSpecIdListByOfferId(
    // coProd.getProductOfferingId().longValue());
    //
    // if (CommonUtil.isNotEmpty(sericeIds))
    // {
    // ListMapMdbRdl userServiceStsRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERSERVICESTATUS,SReturn.class);
    // for (Integer serviceId : sericeIds)
    // {
    // // @Date 2012-03-30 去掉soNbr作为查询条件
    // List<CmResServCycle> resServCycleList = context.getAllCacheList(CmResServCycle.class, new CacheCondition(
    // CmResServCycle.Field.serviceSpecId, serviceId), new CacheCondition(CmResServCycle.Field.resourceId,
    // coProd.getObjectId()));
    //
    // if (CommonUtil.isEmpty(resServCycleList))
    // continue;
    // for (CmResServCycle cmResServ : resServCycleList)
    // {
    // SUserServiceStatus userService = getMdbSalBuildComp().buildSUserServiceStatus(cmResServ);
    // userServiceStsRdl.addRecord(userService.get_servId(), userService);
    // result.add(userServiceStsRdl);
    // }
    // }
    // }
    // }
    //
    // // 账期对象
    // if (CommonUtil.isNotEmpty(coProdBillCycles))
    // {
    // ListMapMdbRdl prodBillCycleStsRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROD_BILLING_CYCLE,SReturn.class);
    // for (CoProdBillingCycle coProdBillingCycle : coProdBillCycles)
    // {
    // SPromBillCycle sProdBillCycle = getMdbSalBuildComp().buidlSPromBillCycle(coProdBillingCycle, coProd);
    // prodBillCycleStsRdl.addRecord(coProdBillingCycle.getProductId(), sProdBillCycle);
    // result.add(prodBillCycleStsRdl);
    // }
    // }
    //
    // if (CommonUtil.isNotEmpty(coSplitCharValues))
    // {
    // // 账户分账信息表
    // ListMapMdbRdl acctSplit = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCTSPLITPAY,SReturn.class);
    // acctSplit.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSAcctSplitPay(coSplitCharValues, coProd));
    // result.add(acctSplit);
    // }
    //
    // // 特征值对象
    // if (CommonUtil.isNotEmpty(coProdCharValues))
    // {
    // if (coProd.getBusiFlag() == SpecCodeDefine.REGUIDE_CHARGE)
    // {
    // // 用户资金账户分账信息表
    // ListMapMdbRdl userAcctPayInfo = new ListMapMdbRdl(MdbKVDefine.SYNC_USERACCTPAYINFO,SReturn.class);
    // userAcctPayInfo.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildCUserAcctPay(coProdCharValues, coProd));
    // result.add(userAcctPayInfo);
    //
    // // 用户资金账户分账信息明细表
    // ListMapMdbRdl userAcctPayDtl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERACCTPAYDTL,SReturn.class);
    // userAcctPayDtl.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSUserAcctPayDtl(coProdCharValues, coProd));
    // result.add(userAcctPayDtl);
    //
    // // 用户资源分账信息表
    // ListMapMdbRdl userResPay = new ListMapMdbRdl(MdbKVDefine.SYNC_USERRESPAY,SReturn.class);
    // userResPay.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSUserResPay(coProdCharValues, coProd));
    // result.add(userResPay);
    // }
    // else if (coProd.getBusiFlag() == SpecCodeDefine.BUDGET)
    // {
    // // 预算控制信息表
    // ListMapMdbRdl budget = new ListMapMdbRdl(MdbKVDefine.SYNC_BUDGETINFO,SReturn.class);
    // budget.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSBudgetInfo(coProdCharValues, coProd));
    // result.add(budget);
    //
    // // 预算控制明细表 CBudgetInfoDtl
    // ListMapMdbRdl budgetDtl = new ListMapMdbRdl(MdbKVDefine.SYNC_BUDGETDTL,SReturn.class);
    // budgetDtl.addRecord(coProd.getProductId(), getMdbSalBuildComp().budilBudgetInfoDtl(coProdCharValues, coProd));
    // result.add(budgetDtl);
    //
    // // SLA规则表CSlaRule
    // ListMapMdbRdl slaRule = new ListMapMdbRdl(MdbKVDefine.SYNC_SLARULE,SReturn.class);
    // slaRule.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSSlaRule(coProdCharValues, coProd));
    // result.add(slaRule);
    // }
    // else if (coProd.getBusiFlag() == SpecCodeDefine.REGUIDE_USAGE)
    // {
    // // 话单代付表CUsagePay
    // ListMapMdbRdl pay = new ListMapMdbRdl(MdbKVDefine.SYNC_USAGEPAY,SReturn.class);
    // pay.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSUsagePay(coProdCharValues, coProd));
    // result.add(pay);
    // }
    // else if (coProd.getBusiFlag() == SpecCodeDefine.MCS_NUMBER)
    // {
    // // 黑白名单接续控制主表CRejectList
    // ListMapMdbRdl rejectRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_REJECTLIST,SReturn.class);
    // rejectRdl.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSReject(coProdCharValues, coProd));
    // result.add(rejectRdl);
    // }
    // else if (coProd.getBusiFlag() == SpecCodeDefine.SPECIAL_NUMBER)
    // {
    // // 用户自定义控制表CCustomizedList
    // ListMapMdbRdl customizedRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CUSTOMIZEDLIST,SReturn.class);
    // customizedRdl.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildCCustomizedList(coProdCharValues, coProd));
    // result.add(customizedRdl);
    // }
    // else if (coProd.getBusiFlag() == SpecCodeDefine.FRIENDNUMBER)
    // {
    // // 用户关系号码表CUserRela
    // ListMapMdbRdl userRela = new ListMapMdbRdl(MdbKVDefine.SYNC_USERRELA,SReturn.class);
    // userRela.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSUserRela(coProdCharValues, coProd));
    // result.add(userRela);
    // }
    // else if (coProd.getBusiFlag() == SpecCodeDefine.HOME_ZONE)
    // {
    // // 用户小区信息表CUserCell
    // ListMapMdbRdl userRela = new ListMapMdbRdl(MdbKVDefine.SYNC_USERCELL,SReturn.class);
    // userRela.addRecord(coProd.getProductId(), getMdbSalBuildComp().buildSUserCell(coProdCharValues, coProd));
    // result.add(userRela);
    // }
    // else
    // {
    // ListMapMdbRdl prodCharValueRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROMCHARVALUE,SReturn.class);
    // for (CoProdCharValue coProdCharValue : coProdCharValues)
    // {
    // // exclude friend number char spec id
    // if (SpecCodeDefine.FRIENDNUMBER_COUNT == coProdCharValue.getSpecCharId())
    // {
    // continue;
    // }
    //
    // prodCharValueRdl.addRecord(coProdCharValue.getProductId(), getMdbSalBuildComp().buildSPromCharValue(coProdCharValue));
    // result.add(prodCharValueRdl);
    // }
    // }
    // }
    //
    // // 价格参数对象
    // if (null != coProdPriceParams)
    // {
    // ListMapMdbRdl prodPriceParamRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROD_PRICE_PARAM,SReturn.class);
    // for (DataObject coProdPriceParamObject : coProdPriceParams)
    // {
    // CoProdPriceParam coProdPriceParam = (CoProdPriceParam) coProdPriceParamObject;
    // if (coProd.getProductId().longValue() == coProdPriceParam.getProductId().longValue())
    // {
    // SPromPriceParam sProdPriceParam = getMdbSalBuildComp().buildSPromPriceParam(coProdPriceParam);
    //
    // prodPriceParamRdl.addRecord(sProdPriceParam.get_promNo(), sProdPriceParam);
    // result.add(prodPriceParamRdl);
    // }
    // }
    // }
    //
    // List<CoProdCellInfo> cellInfoList = context.getAllCacheList(CoProdCellInfo.class, new CacheCondition(
    // CoProdCellInfo.Field.productId, coProd.getProductId()));
    // if (CommonUtil.isNotEmpty(cellInfoList))
    // {
    // ListMapMdbRdl homeZoneRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_HOME_ZONE,SReturn.class);
    // for (CoProdCellInfo info : cellInfoList)
    // {
    // SUserHome home = getMdbSalBuildComp().buildSUserHome(info);
    // homeZoneRdl.addRecord(home.get_promNo(), home);
    // result.add(homeZoneRdl);
    // }
    // }
    //
    // // @Date 2012-5-19 增加产品有效期上发
    // CoProdValid valid = context.getSingleCache(CoProdValid.class,
    // new CacheCondition(CoProdValid.Field.productId, coProd.getProductId()));
    // //缓存中没有，直接查找数据库
    // if(valid==null){
    // valid=this.querySingle(CoProdValid.class, new DBCondition(CoProdValid.Field.productId, coProd.getProductId()));
    // }
    // if (valid != null)
    // {
    // MdbRdl prodValidRdl = new MdbRdl(MdbKVDefine.SYNC_PROD_VALID,SReturn.class);
    // CsdlArrayList<SPromValidInfo> list = new CsdlArrayList<SPromValidInfo>(SPromValidInfo.class);
    // SPromValidInfo info = getMdbSalBuildComp().buildSPromValidInfo(valid);
    // list.add(info);
    // //prodValidRdl.addRecord(info.get_promNo(), info);
    // prodValidRdl.setReqObj(list);
    // result.add(prodValidRdl);
    // }
    // }
    // }
    // }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param cmResServ
     * @return
     * @author: yangjh
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildSUserServiceStatusList()
    {
        ListMapMdbRdl userServiceStsRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERSERVICESTATUS, SReturn.class);
        List<CmResServCycle> cmResServCycList = context.getAllCacheList(CmResServCycle.class);
        if (!CommonUtil.isEmpty(cmResServCycList))
        {
            for (CmResServCycle cmResServCyc : cmResServCycList)
            {
                SUserServiceStatus sUserServiceStatus = getMdbSalBuildComp().buildSUserServiceStatus(cmResServCyc,context.isDiffException());
                userServiceStsRdl.addRecord(sUserServiceStatus.get_servId(), sUserServiceStatus);
            }
        }
        
        List<CmResServiceStatus> cmResSerStsList = context.getAllCacheList(CmResServiceStatus.class);
        if (!CommonUtil.isEmpty(cmResSerStsList))
        {
            for (CmResServiceStatus cmResSerSts : cmResSerStsList)
            {
            	SUserServiceStatus sUserServiceStatus = getMdbSalBuildComp().buildSUserServiceStatus(cmResSerSts,context.isDiffException());
                userServiceStsRdl.addRecord(sUserServiceStatus.get_servId(), sUserServiceStatus);
            }
        }
        return userServiceStsRdl;
    }

    /**
     * @Description: 构造SGroupExterior
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-18
     */
    public ListMapMdbRdl buildSGroupExteriorList()
    {
        ListMapMdbRdl groupExtRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUP_EXTERIOR, SReturn.class);
        List<CaAccountRv> caRvList = context.getAllCacheList(CaAccountRv.class);
        if (!CommonUtil.isEmpty(caRvList))
        {
            for (CaAccountRv caRv : caRvList)
            {
                SGroupExterior sGroupExterior = getMdbSalBuildComp().buildGroupExterior(caRv,context.isDiffException());
                groupExtRdl.addRecord(sGroupExterior.get_serviceAcctId(), sGroupExterior);
            }
        }
        return groupExtRdl;
    }

    /**
     * @Description: 构造SUserGroup for VPN
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-18
     */
    public void buildSUserGroup4VPNSpecNbr(List<MdbRdl> result)
    {
        ListMapMdbRdl groupExtRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERGROUP, SReturn.class);
        ListMapMdbRdl groupTierRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_LISTUSERGROUPTIER, SReturn.class);
        List<CaAccountRel> caRelList = context.getAllCacheList(CaAccountRel.class);
        if (!CommonUtil.isEmpty(caRelList))
        {
            for (CaAccountRel caRel : caRelList)
            {
                if (caRel.getRelationshipType().intValue() == EnumCodeDefine.ACCOUNT_VPN_SPECNBR_GCA_RELATION)
                {
                    SUserGroupTier sUserGroupTier = getMdbSalBuildComp().buildUserGroupTier(caRel,context.isDiffException());
                    groupTierRdl.addRecord(sUserGroupTier.get_sacctId(), sUserGroupTier);
                    // 特殊号码需要在userGroup表上发一条虚账户id和关系类型4
                 // 2012-07-31 luojb 这条数据是为了取groupType现在不需要了，CGroup里有groupType了
//                    CaAccountRv rv = new CaAccountRv();
//                    rv.setAcctId(caRel.getRelAcctId());
//                    rv.setValidDate(caRel.getValidDate());
//                    rv.setExpireDate(caRel.getExpireDate());
//                    SUserGroup suserGroup = getMdbSalBuildComp().buildVpnSpecNbrUserGroup(rv);
//                    groupExtRdl.addRecord(suserGroup.get_serviceAcctId(), suserGroup);
                }
            }
        }
        result.add(groupTierRdl);
        result.add(groupExtRdl);
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param result
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildSUserLIst()
    {
        ListMapMdbRdl userInfoRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERINFO, SReturn.class);
        List<CmResource> cmResourceList = context.getAllCacheList(CmResource.class);
        if (CommonUtil.isNotEmpty(cmResourceList))
        {
            for (CmResource res : cmResourceList)
            {
                userInfoRdl.addRecord(res.getResourceId(), getMdbSalBuildComp().buildUser(res,context.isDiffException()));
            }
        }
        return userInfoRdl;
    }

    /**
     * @author yanchuan 2012-6-18
     * @describe 构建客户的rdl对象
     * @param rdl
     */
    public ListMapMdbRdl buildSCustomerRdl()
    {
        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CUSTOMER, SReturn.class);
        List<CmCustomer> cmCustomers = context.getAllCacheList(CmCustomer.class);
        if (CommonUtil.isEmpty(cmCustomers))
        {
            return rdl;
        }
        for (CmCustomer dmCust : cmCustomers)
        {

            SCustomer sCustomer = getMdbSalBuildComp().buildCustomer(dmCust,context.isDiffException());
            if (CommonUtil.isValid(sCustomer.get_custId()))
            {
                rdl.addRecord(sCustomer.get_custId(), sCustomer);
            }
        }
        return rdl;
    }

    /**
     * @author yanchuan 2012-6-18
     * @describe 构建客户的rdl对象
     * @param rdl
     */
    public ListMapMdbRdl buildAccountRdl()
    {
        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCOUNT, SReturn.class);
        List<CaAccount> caAccounts = context.getAllCacheList(CaAccount.class);
        if (CommonUtil.isEmpty(caAccounts))
        {
            return rdl;
        }
        for (CaAccount account : caAccounts)
        {
            if (account == null)
                continue;
            SAccount sAccount = getMdbSalBuildComp().buildAccount(account,context.isDiffException());
            //@Date 2012-07-25 yangjh : story ：52730 增加spec_acct_type字段入库上发
            CaAccountExt caAccountExt = context.getSingleCache(CaAccountExt.class, new CacheCondition(CaAccountExt.Field.acctId,account.getAcctId()),
                    new CacheCondition(CaAccountExt.Field.soNbr,account.getSoNbr()));
            if(caAccountExt != null && caAccountExt.getItem8() != null){
                sAccount.set_specAcctType(CommonUtil.string2Integer(caAccountExt.getItem8()));
           }
            if (CommonUtil.isValid(sAccount.get_acctId()))
            {
                rdl.addRecord(sAccount.get_acctId(), sAccount);
            }
        }

        return rdl;
    }

    /**
     * @Description: 构造SIdentityPwd
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-18
     */
    public ListMapMdbRdl buildSIdentityPwdList()
    {
        ListMapMdbRdl identityPwdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_IDENTITYPWD, SReturn.class);
        List<DataObject> cmResourceIdentitys = context.getAllCacheList(CmResIdentity.class);
        if (!CommonUtil.isEmpty(cmResourceIdentitys))
        {
            for (DataObject cmResourceIdentityObject : cmResourceIdentitys)
            {
                CmResIdentity cmResourceIdentity = (CmResIdentity) cmResourceIdentityObject;
                SIdentityPwd sIdentityPwd = getMdbSalBuildComp().buildIdentityPwd(cmResourceIdentity,context.isDiffException());
                identityPwdRdl.addRecord(sIdentityPwd.get_servId(), sIdentityPwd);
            }
        }
        return identityPwdRdl;
    }
    
    
    public ListMapMdbRdl buildSuserSvc(){
    	ListMapMdbRdl userSvcRdl = new ListMapMdbRdl(MdbKVDefine.IVR_USER_SERV, SReturn.class);
    	List<DataObject> userSvcList = context.getAllCacheList(CmUserSvcInt.class);
    	if(!CommonUtil.isEmpty(userSvcList)){
    		for(DataObject userSvcObject : userSvcList){
    			CmUserSvcInt iUserSvc = (CmUserSvcInt)userSvcObject; 
    			SUserSvc userSvc = getMdbSalBuildComp().buildUserSvc(iUserSvc,context.isDiffException());
    			userSvcRdl.addRecord(userSvc.get_userId(), userSvc);
    		}
    	}
    	return userSvcRdl;
    }
    
    public ListMapMdbRdl buildSuserInt(){
    	 ListMapMdbRdl sUserRdl = new ListMapMdbRdl(MdbKVDefine.IVR_USER, SReturn.class);
    	 List<DataObject> userIntList = context.getAllCacheList(CmUserInt.class); 
    	 if(!CommonUtil.isEmpty(userIntList)){
    		 for(DataObject userIntObject : userIntList){
    			 CmUserInt userInt = (CmUserInt)userIntObject;
    			 SUserCust userCust = getMdbSalBuildComp().buildUserCust(userInt,context.isDiffException());
    			 sUserRdl.addRecord(userInt.getUserId(), userCust);
    		 }
    	 }
    	 
    	 return sUserRdl;
    }
    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param result
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildSIdentityList()
    {
        ListMapMdbRdl identityRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_IDENTITYINFO, SReturn.class);
        List<CmResIdentity> cmResourceIdentitys = context.getAllCacheList(CmResIdentity.class);
        if (!CommonUtil.isEmpty(cmResourceIdentitys))
        {
            for (CmResIdentity identity : cmResourceIdentitys)
            {
                identityRdl.addRecord(identity.getResourceId(), getMdbSalBuildComp().buildIdentityRdl(identity,context.isDiffException()));
            }
        }
        return identityRdl;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param result
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildSEmailInfoListForUser()
    {
        ListMapMdbRdl emailInfoRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_EMAILINFO, SReturn.class);
        List<CmResource> userEmailList = context.getAllCacheList(CmResource.class);
        if (CommonUtil.isNotEmpty(userEmailList))
        {
            for (CmResource cmRes : userEmailList)
            {
                SEmailInfo emailInfo = new SEmailInfo();
                emailInfo.set_email(cmRes.getEmail());
                emailInfo.set_expireDate(this.toMdbExpireDate(cmRes.getExpireDate()));
                emailInfo.set_validDate(this.toMdbExpireDate(cmRes.getValidDate()));
                emailInfo.set_objectId(cmRes.getResourceId());
                emailInfo.set_objectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
                emailInfoRdl.addRecord(cmRes.getResourceId(), emailInfo);
            }
        }

        return emailInfoRdl;
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param result
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildSImsiSerialNoReList()
    {
        ListMapMdbRdl imsiSerialNoRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_IMSISERIALNOREL, SReturn.class);
        List<CmResIdentity> cmResourceIdentitys = context.getAllCacheList(CmResIdentity.class);
        if (!CommonUtil.isEmpty(cmResourceIdentitys))
        {
            for (CmResIdentity identity : cmResourceIdentitys)
            {
                if (identity.getIdentityType() == EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE
                        || identity.getIdentityType() == EnumCodeDefine.RESOURCE_IDENTITYTYPE_MULTI_SIM)
                {
                    SImsiSerialNoRel imsiSerialNoRel = getMdbSalBuildComp().buildSImsiSerialNoRel(identity,context.isDiffException());
                    imsiSerialNoRdl.addRecord(imsiSerialNoRel.get_imsi(), imsiSerialNoRel);
                }
            }
        }
        return imsiSerialNoRdl;
    }

    /**
     * @Description: 构造SIdentityBound
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildSIdentityBoundList()
    {
        ListMapMdbRdl identityBoundRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_IDENTITYBOUND, SReturn.class);

        List<DataObject> cmIdentityVsImeis = context.getAllCacheList(CmIdentityVsImei.class);
        if (!CommonUtil.isEmpty(cmIdentityVsImeis))
        {
            for (DataObject cmIdentityVsImeisObject : cmIdentityVsImeis)
            {
                CmIdentityVsImei cmIdentityVsImei = (CmIdentityVsImei) cmIdentityVsImeisObject;
                SIdentityBound sIdentityBound = getMdbSalBuildComp().buildIdentityVsImei(cmIdentityVsImei,context.isDiffException());
                identityBoundRdl.addRecord(sIdentityBound.get_servId(), sIdentityBound);
            }
        }
        return identityBoundRdl;
    }

    /**
     * @Description: 构造AccountCycle
     * @param rdl
     * @author: yangjh
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildSAcctCycleList()
    {
        ListMapMdbRdl accountCycleRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCTCYCLE, SReturn.class);
        List<CaAccount> accountList = context.getAllCacheList(CaAccount.class);
        if (!CommonUtil.isEmpty(accountList))
        {
            for (CaAccount ca : accountList)
            {
                SAcctCycle sAcctCycle = getMdbSalBuildComp().buildAcctCycle(ca,context.isDiffException());
                accountCycleRdl.addRecord(sAcctCycle.get_acctId(), sAcctCycle);
            }
        }
        return accountCycleRdl;
    }

    /**
     * 从缓存中获取对象列表，包装成mdb需要的对象列表
     * 
     * @author yangjh 2012-6-19
     */
    public ListMapMdbRdl buildImAcctContactList()
    {
        ListMapMdbRdl acctContactRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCTCONTACT, SReturn.class);
        List<CmContactMedium> mediumList = new ArrayList<CmContactMedium>();
        List<CmContactMedium> mediumList1 = new ArrayList<CmContactMedium>();
        List<CmContactMedium> mediumList2 = new ArrayList<CmContactMedium>();
        mediumList1 = context.getAllCacheList(CmContactMedium.class, new CacheCondition(
                CmContactMedium.Field.objectType, EnumCodeDefine.CONTACT_GROUP));
        mediumList2 = context.getAllCacheList(CmContactMedium.class, new CacheCondition(
                CmContactMedium.Field.objectType, EnumCodeDefine.DEV_CONTACT_TYPE));
        if(CommonUtil.isNotEmpty(mediumList1)){
        	mediumList.addAll(mediumList1);
        }
        if(CommonUtil.isNotEmpty(mediumList2)){
        	mediumList.addAll(mediumList2);
        }
        if (CommonUtil.isNotEmpty(mediumList))
        {
            for (CmContactMedium medium : mediumList)
            {
                if (medium == null)
                    continue;
                SAcctContact sAcctContact = getMdbSalBuildComp().buildAcctContact(medium,context.isDiffException());
                acctContactRdl.addRecord(sAcctContact.get_objectId(), sAcctContact);
            }
        }
        return acctContactRdl;
    }

    public ListMapMdbRdl buildSpecShareDtlList()
    {
        ListMapMdbRdl sImSpecShareDtlList = new ListMapMdbRdl(MdbKVDefine.SYNC_SPECSHAREDTL, SReturn.class);
        List<CoProdShareAlloc> coProdShareAllocList = context.getAllCacheList(CoProdShareAlloc.class);
        if (CommonUtil.isNotEmpty(coProdShareAllocList))
        {
            for (CoProdShareAlloc coProdShareAlloc : coProdShareAllocList)
            {
                if (coProdShareAlloc == null)
                    continue;
                SSpecShareDtl sSpecShareDtl = getMdbSalBuildComp().buildSpecShareDtl(coProdShareAlloc,context.isDiffException());
                sImSpecShareDtlList.addRecord(sSpecShareDtl.get_promNo(), sSpecShareDtl);
            }
        }
        return sImSpecShareDtlList;
    }

    public ListMapMdbRdl buildSpecShareInfoList()
    {
        ListMapMdbRdl sImSpecShareInfoList = new ListMapMdbRdl(MdbKVDefine.SYNC_SPECSHAREINFO, SReturn.class);
        List<CoProdShareAlloc> coProdShareAllocList = context.getAllCacheList(CoProdShareAlloc.class);
        if (CommonUtil.isNotEmpty(coProdShareAllocList))
        {
            // 将相同产品相同科目的资源信息封转在一起
            List<CoProdShareAlloc> coProdShareAllocs = new ArrayList<CoProdShareAlloc>();
            coProdShareAllocs.add(coProdShareAllocList.get(0)); // 先添加第一个

            for (int i = 0; i < coProdShareAllocList.size(); i++)
            {
                int t = 0;
                for (int j = 0; j < coProdShareAllocList.size(); j++)
                {
                    if (coProdShareAllocList.get(i).getProductId().equals(coProdShareAllocList.get(j).getProductId())
                            && coProdShareAllocList.get(i).getItemId().equals(coProdShareAllocList.get(j).getItemId()))
                    {
                        t++;
                    }
                }
                if (t != 1){
//                {
//                    // 表明只有一个
//                    coProdShareAllocs.add(coProdShareAllocList.get(i));
//                }
//                else
//                {
                    boolean isExist = false;
                    for (int k = 0; k < coProdShareAllocs.size(); k++)
                    {
                        if (coProdShareAllocList.get(i).getProductId().equals(coProdShareAllocs.get(k).getProductId())
                                && coProdShareAllocList.get(i).getItemId().equals(coProdShareAllocs.get(k).getItemId()))
                        {
                            isExist = true;
                        }
                    }
                    if (!isExist)
                    {
                        coProdShareAllocs.add(coProdShareAllocList.get(i));
                    }
                }
            }

            for (CoProdShareAlloc coProdShareAlloc : coProdShareAllocs)
            {
                int amount = 0;
                IMSUtil.removeRouterParam();
                if(coProdShareAlloc.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT){
                	IMSUtil.setAcctRouterParam(coProdShareAlloc.getObjectId());
                }else{
                	IMSUtil.setUserRouterParam(coProdShareAlloc.getObjectId());
                }
                List<CoProdShareAlloc> cPShareAllList = this.query(CoProdShareAlloc.class,
                        new DBCondition(CoProdShareAlloc.Field.itemId, coProdShareAlloc.getItemId()),
                        new DBCondition(CoProdShareAlloc.Field.productId, coProdShareAlloc.getProductId()));
                List<CoProd> prodList = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.productId, coProdShareAlloc.getProductId()));
                CoProd coProd = context.getComponent(ProductQuery.class).mergeProd(prodList);
                if (coProd == null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST,
                            "product_id==" + coProdShareAlloc.getProductId());
                }
                if(CommonUtil.isNotEmpty(cPShareAllList)){
                	for (int i = 0; i < cPShareAllList.size(); i++)
                	{
                		CoProdShareAlloc tempAlloc = cPShareAllList.get(i);
                		if(tempAlloc.getBillFlag() == 2)
                			amount += CommonUtil.long2Int(tempAlloc.getAmount());
                	}
                }
                SSpecShare sSpecShareAmount = new SSpecShare();
                sSpecShareAmount.set_allocAmount(amount);
                sSpecShareAmount.set_expireDate(toMdbExpireDate(coProd.getExpireDate())); // 上发时失效时间为该产品的失效时间

                sSpecShareAmount.set_groupId(coProdShareAlloc.getObjectId());
                sSpecShareAmount.set_itemId(coProdShareAlloc.getItemId());
                sSpecShareAmount.set_promNo(coProdShareAlloc.getProductId());
                if(context.isDiffException()||context.isManualFlag()){
                	sSpecShareAmount.set_syncFlag(0);
                }else{
                sSpecShareAmount.set_syncFlag(2);
                }
                sSpecShareAmount.set_validDate(toMdbValidDate(coProd.getValidDate())); // 生效时间设置该产品的生效时间
                MdbUtil.setNull2Default(sSpecShareAmount);
                sImSpecShareInfoList.addRecord(sSpecShareAmount.get_promNo(), sSpecShareAmount);
            }
        }

        return sImSpecShareInfoList;
    }

    /**
     * @author yanchuan 2012-6-19
     * @param result
     */
    public ListMapMdbRdl buildUserAcctRelList()
    {
        ListMapMdbRdl userAcctRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERACCTREL, SReturn.class);
        // 修改获取归属帐户及支付帐户的方法 yanchuan 2012-02-09
        List<CaAccountRes> acctList = context.getAllCacheList(CaAccountRes.class, new CacheCondition(
                CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
        if (CommonUtil.isNotEmpty(acctList))
        {
            for (CaAccountRes accountRes : acctList)
            {
                if (accountRes == null)
                    continue;
                SUserAcctRel sUserAcctRel = getMdbSalBuildComp().buildUserAcctRel(accountRes,context.isDiffException());
                userAcctRdl.addRecord(sUserAcctRel.get_servId(), sUserAcctRel);
            }
        }
        return userAcctRdl;
    }

    public ListMapMdbRdl buildEmailList()
    {

        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_EMAILINFO, SReturn.class);
        List<CmContactMedium> contactMediumList = context.getAllCacheList(CmContactMedium.class);
        List<CmResource> userEmailList = context.getAllCacheList(CmResource.class);
        if (CommonUtil.isEmpty(contactMediumList) && CommonUtil.isEmpty(userEmailList))
        {
            return rdl;
        }
        if (CommonUtil.isNotEmpty(contactMediumList))
        {
            for (CmContactMedium contactMedium : contactMediumList)
            {
                SEmailInfo emailInfo = getMdbSalBuildComp().buildEmailInfo(null, contactMedium,context.isDiffException());
                rdl.addRecord(emailInfo.get_objectId(), emailInfo);
            }
        }
        if (CommonUtil.isNotEmpty(userEmailList))
        {
            for (CmResource userEmail : userEmailList)
            {
                SEmailInfo emailInfo = getMdbSalBuildComp().buildEmailInfo(userEmail, null,context.isDiffException());
                rdl.addRecord(emailInfo.get_objectId(), emailInfo);
            }
        }

        return rdl;
    }

    /**
     * @Description: 构造SUserGroup for VPN， 不考虑特殊号码
     * @param rdl
     * @author: wangyh3
     * @Date: 2012-6-20
     */
    public ListMapMdbRdl buildSUserGroup4VPN()
    {
        ListMapMdbRdl groupTierRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_LISTUSERGROUPTIER, SReturn.class);
        List<CaAccountRel> caRelList = context.getAllCacheList(CaAccountRel.class);
        if (!CommonUtil.isEmpty(caRelList))
        {
            for (CaAccountRel caRel : caRelList)
            {
                if (caRel.getRelationshipType().intValue() == EnumCodeDefine.ACCOUNT_VPN_SPECNBR_GCA_RELATION)
                {
                    SUserGroupTier sUserGroupTier = getMdbSalBuildComp().buildUserGroupTier(caRel,context.isDiffException());
                    groupTierRdl.addRecord(sUserGroupTier.get_sacctId(), sUserGroupTier);
                }
            }
        }
        return groupTierRdl;
    }

    /**
     * @author wangyh3 2012-6-20
     * @param result
     */
    public ListMapMdbRdl buildUserAcctRelListNoCondition()
    {
        ListMapMdbRdl userAcctRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERACCTREL, SReturn.class);
        List<CaAccountRes> acctList = context.getAllCacheList(CaAccountRes.class);
        if (CommonUtil.isNotEmpty(acctList))
        {
            for (CaAccountRes accountRes : acctList)
            {
                if (accountRes == null)
                    continue;
                SUserAcctRel sUserAcctRel = getMdbSalBuildComp().buildUserAcctRel(accountRes,context.isDiffException());
                userAcctRdl.addRecord(sUserAcctRel.get_servId(), sUserAcctRel);
            }
        }
        return userAcctRdl;
    }

    /**
     * @author: wangyh3 从 MgntUserMonitorSalMdbBean 分拆出来
     * @Date: 2012-6-23
     */
    public ListMapMdbRdl buildUserMonitorList()
    {
        ListMapMdbRdl userMonitorRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERMONITOR, SReturn.class);
        List<CmResourceMonitor> cmRes = context.getAllCacheList(CmResourceMonitor.class);
        if (cmRes != null && cmRes.size() > 0)
        {
            for (CmResourceMonitor res : cmRes)
            {
                SUserMonitor sim = getMdbSalBuildComp().buildUserMonitor(res,context.isDiffException());
                userMonitorRdl.addRecord(sim.get_servId(), sim);
            }
        }
        return userMonitorRdl;
    }

    /**
     * wangyh3 2012-6-23
     */
    public ListMapMdbRdl buildExemptionList()
    {
        ListMapMdbRdl exemptionRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_EXEMPTION, SReturn.class);
        List<CaNotifyExempt> exemptions = context.getAllCacheList(CaNotifyExempt.class);

        if (CommonUtil.isNotEmpty(exemptions))
        {
            for (CaNotifyExempt caNotifyExempt : exemptions)
            {
                SExemption exemption = getMdbSalBuildComp().buildExemption(caNotifyExempt,context.isDiffException());
                exemptionRdl.addRecord(exemption.get_objectId(), exemption);
            }
        }
        return exemptionRdl;
    }
    
    /**
     * wangyh3 2012-6-25
     */
    public ListMapMdbRdl buildUserOrderConfirmList()
    {
        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERORDERCONFIRM,
                SReturn.class);
        List<CmUserOrderConfirm> resList = context.getAllCacheList(CmUserOrderConfirm.class);
        if (CommonUtil.isEmpty(resList))
            return null;
        for (CmUserOrderConfirm res : resList)
        {
            SUserOrderConfirm confirm = getMdbSalBuildComp().buildUserOrderConfirm(res,context.isDiffException());
            rdl.addRecord(confirm.get_servId(), confirm);
        }
        return rdl;
    }
    /**
     * @author yanchuan 2012-6-21
     * @describe 首次激活通知扣费
     * @param cbsErrorMsg
     */
    public void deduct(CBSErrorMsg cbsErrorMsg)
    {

        List<CmResLifecycle> resListCycleList = context.getAllCacheList(CmResLifecycle.class);
        List<Long> userIdList = new ArrayList<Long>();
        if (CommonUtil.isNotEmpty(resListCycleList))
        {
            for (CmResLifecycle resLifeCycle : resListCycleList)
            {
                if (!CommonUtil.isValid(resLifeCycle.getResourceId()) || userIdList.contains(resLifeCycle.getResourceId()))
                {
                    continue;
                }
                if (resLifeCycle.getSts() != null && resLifeCycle.getSts() == EnumCodeDefine.LIFECYCLE_ACTIVE)
                {
                	/*
                    context.getComponent(AuthComponent.class).firstActivePreOrderProd4CRM(resLifeCycle.getResourceId(),
                            cbsErrorMsg);
                            */
                    userIdList.add(resLifeCycle.getResourceId());
                }

            }
        }
    }
    
    public ListMapMdbRdl buildProdBillCycleList()
    {
        ListMapMdbRdl rdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROD_BILLING_CYCLE,
                SReturn.class);
        List<CoProdBillingCycle> billCycles = context.getAllCacheList(CoProdBillingCycle.class);
        if (CommonUtil.isEmpty(billCycles))
            return null;
        for(CoProdBillingCycle billCycle:billCycles)
        {
            SPromBillCycle cycle = getMdbSalBuildComp().buildProdBillCycle(billCycle,context.isDiffException());
            rdl.addRecord(cycle.get_promNo(), cycle);
        }
        return rdl;
    }
    
    /**
     * 打包产品关系
     * luojb 2012-8-18
     * @return
     */
    public ListMapMdbRdl buildPromComposite()
    {
        ListMapMdbRdl promCompositeRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROMCOMPOSITE,SReturn.class);
        List<CoProdBundleComposite> bundleList = context.getAllCacheList(CoProdBundleComposite.class);
        if(CommonUtil.isEmpty(bundleList))
            return null;
        for(CoProdBundleComposite bundle:bundleList)
        {
            SPromComposite promComp = getMdbSalBuildComp().buildPromComposite(bundle,context.isDiffException());
            promCompositeRdl.addRecord(bundle.getObjectId(), promComp);
        }
        
        return promCompositeRdl;
    }
    public ListMapMdbRdl buildUserPbx()
    {
    	ListMapMdbRdl userPbxRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CMSUSERPBX, SReturn.class);
    	List<CmUserPbx> userPbxList = context.getAllCacheList(CmUserPbx.class);
    	if(CommonUtil.isNotEmpty(userPbxList)){
    		for(CmUserPbx cmUserPbx : userPbxList){
    			SUserPbx sUserPbx = getMdbSalBuildComp().buildSuserPbx(cmUserPbx,context.isDiffException());
    			userPbxRdl.addRecord(sUserPbx.get_servId(), sUserPbx);
    		}
    	}

        return userPbxRdl;
    }
    public ListMapMdbRdl buildUserPort()
    {
    	ListMapMdbRdl userPortRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CMUSERPORTABILITY, SReturn.class);
    	List<CmUserPortability> userPortabilitiesList = context.getAllCacheList(CmUserPortability.class);
    	if(CommonUtil.isNotEmpty(userPortabilitiesList)){
    		for(CmUserPortability cmUserPortability : userPortabilitiesList){
    			SUserPort sUserPort = getMdbSalBuildComp().buildSuserPort(cmUserPortability,context.isDiffException());
    			userPortRdl.addRecord(sUserPort.get_servId(), sUserPort);
    		}
    	}

        return userPortRdl;
    }
    public ListMapMdbRdl buildResourceMap()
    {
    	ListMapMdbRdl userMapRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CMRESOURCEMAP, SReturn.class);
    	List<CmUserMap> userMapsList = context.getAllCacheList(CmUserMap.class);
    	if(CommonUtil.isNotEmpty(userMapsList)){
    		for(CmUserMap cmUserMap : userMapsList){
    			SResourceMap sResourceMap = getMdbSalBuildComp().buildSResourceMap(cmUserMap,context.isDiffException());
    			userMapRdl.addRecord(sResourceMap.get_servId(), sResourceMap);
    		}
    	}

        return userMapRdl;
    }
    public ListMapMdbRdl buildUserMarket()
    {
    	ListMapMdbRdl userMarketRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CMREUSERMARKET, SReturn.class);
    	List<CmUserMarket> userMarketsList = context.getAllCacheList(CmUserMarket.class);
    	if(CommonUtil.isNotEmpty(userMarketsList)){
    		for(CmUserMarket cmUserMarket : userMarketsList){
    			SUserMarket sUserMarket = getMdbSalBuildComp().buildSUserMarket(cmUserMarket,context.isDiffException());
    			userMarketRdl.addRecord(sUserMarket.get_servId(), sUserMarket);
    		}
    	}

        return userMarketRdl;
    }
    
    public ListMapMdbRdl buildVaildChange(){
    	ListMapMdbRdl validChangeRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_SVALIDCHANGE, SReturn.class);
    	List<CmUserValidchange> validChangeList = context.getAllCacheList(CmUserValidchange.class);
    	if(CommonUtil.isNotEmpty(validChangeList)){
    		for(CmUserValidchange validChange : validChangeList){
    			SValidChange sValidChange = getMdbSalBuildComp().buildUserValidChange(validChange,context.isDiffException());
    			validChangeRdl.addRecord(validChange.getUserId(), sValidChange);
    		}
    	}
    	return validChangeRdl;
    }
    
    /**
     * 
     * @param result
     * @return
     */
    public SSyncIvrAllInfo createSyncIvrAllInfo(List<MdbRdl> result){
    	SSyncIvrAllInfo info = new SSyncIvrAllInfo();
    	for(MdbRdl rdl : result){
    		if (rdl == null || rdl.getReqObj() == null)
                continue;
              if (rdl.getKv().equals(MdbKVDefine.IVR_USER))
              {
                  info.set_userCust((CsdlHashMap<Long, CsdlArrayList<SUserCust>>) rdl.getReqObj());
              }else if(rdl.getKv().equals(MdbKVDefine.IVR_USER_SERV)){
            	  info.set_userSvc((CsdlHashMap<Long, CsdlArrayList<SUserSvc>>) rdl.getReqObj());
              }
    	}
    	 return info;
    	
    }
    
    /**
     * 
     * lijc3 2012-9-19 MDB事务一致性改造，组装成SSyncAllInfo然后上发
     * @param result 组装完成后的MdbRdl
     * @return
     */
    public SSyncAllInfo createSyncAllInfo(List<MdbRdl> result)
    {
        SSyncAllInfo info = new SSyncAllInfo();
        for (MdbRdl rdl : result)
        {
        	//@Date 2012-09-19 yugb :非空判断
        	if (rdl == null || rdl.getReqObj() == null)
              continue;
            if (rdl.getKv().equals(MdbKVDefine.SYNC_CUSTOMER))
            {
                info.set_custInfo((CsdlHashMap<Long, CsdlArrayList<SCustomer>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_ACCOUNT))
            {
                info.set_acctInfo((CsdlHashMap<Long, CsdlArrayList<SAccount>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_ACCOUNT_PROD))
            {
                info.set_acctProm((CsdlHashMap<Long, CsdlArrayList<SAcctProm>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_ACCTBILLCYCLE))
            {
                info.set_acctBillCycle((CsdlHashMap<Long, CsdlArrayList<SAcctBillCycle>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_ACCTCONTACT))
            {
                info.set_acctContact((CsdlHashMap<Long, CsdlArrayList<SAcctContact>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_ACCTCYCLE))
            {
                info.set_acctCycle((CsdlHashMap<Long, CsdlArrayList<SAcctCycle>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_ACCTSPLITPAY))
            {
                info.set_acctSplitPay((CsdlHashMap<Long, CsdlArrayList<SAcctSplitPay>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_BUDGETDTL))
            {
                info.set_budgetDtl((CsdlHashMap<Long, CsdlArrayList<SBudgetInfoDtl>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_BUDGETINFO))
            {
                info.set_budgetInfo((CsdlHashMap<Long, CsdlArrayList<SBudgetInfo>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_CUSTOMIZEDLIST))
            {
                info.set_customizedList((CsdlHashMap<Long, CsdlArrayList<SCustomizedList>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_EMAILINFO))
            {
                info.set_emailInfo((CsdlHashMap<Long, CsdlArrayList<SEmailInfo>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_EXEMPTION))
            {
                info.set_exemptInfo((CsdlHashMap<Long, CsdlArrayList<SExemption>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_GROUP_PROD))
            {
                info.set_groupProm((CsdlHashMap<Long, CsdlArrayList<SGroupProm>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_GROUPINFO))
            {
                info.set_groupInfo((CsdlHashMap<Long, CsdlArrayList<SGroup>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_GROUPRELA))
            {
                info.set_groupRela((CsdlHashMap<Long, CsdlArrayList<SGroupRela>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_IDENTITYBOUND))
            {
                info.set_identityBound((CsdlHashMap<Long, CsdlArrayList<SIdentityBound>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_GROUP_EXTERIOR))
            {
                info.set_groupExterior((CsdlHashMap<Long, CsdlArrayList<SGroupExterior>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_IDENTITYINFO))
            {
                info.set_identityInfo((CsdlHashMap<Long, CsdlArrayList<SIdentity>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_IDENTITYPWD))
            {
                info.set_identityPwd((CsdlHashMap<Long, CsdlArrayList<SIdentityPwd>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_IMSISERIALNOREL))
            {
                info.set_imsiSerial((CsdlHashMap<Long, CsdlArrayList<SImsiSerialNoRel>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_PROD_BILLING_CYCLE))
            {
                info.set_promBillCycle((CsdlHashMap<Long, CsdlArrayList<SPromBillCycle>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_PROD_PRICE_PARAM))
            {
                info.set_promPrice((CsdlHashMap<Long, CsdlArrayList<SPromPriceParam>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_PROMCHARVALUE))
            {
                info.set_promCharValue((CsdlHashMap<Long, CsdlArrayList<SPromCharValue>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_PROMCOMPOSITE))
            {
                info.set_promComposite((CsdlHashMap<Long, CsdlArrayList<SPromComposite>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_REJECTLIST))
            {
                info.set_rejectList((CsdlHashMap<Long, CsdlArrayList<SRejectList>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_SPECSHAREDTL))
            {
                info.set_specShareDtl((CsdlHashMap<Long, CsdlArrayList<SSpecShareDtl>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_SPECSHAREINFO))
            {
                info.set_specShare((CsdlHashMap<Long, CsdlArrayList<SSpecShare>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USAGEPAY))
            {
                info.set_usagePay((CsdlHashMap<Long, CsdlArrayList<SUsagePay>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USER_PROD))
            {
                info.set_userProm((CsdlHashMap<Long, CsdlArrayList<SUserProm>>) rdl.getReqObj());
            }

            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERACCTPAYDTL))
            {
                info.set_userAcctPayDtl((CsdlHashMap<Long, CsdlArrayList<SUserAcctPayDtl>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERACCTPAYINFO))
            {
                info.set_userAcctPay((CsdlHashMap<Long, CsdlArrayList<SUserAcctPay>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERACCTREL))
            {
                info.set_userAcctRel((CsdlHashMap<Long, CsdlArrayList<SUserAcctRel>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERCELL))
            {
                info.set_userCell((CsdlHashMap<Long, CsdlArrayList<SUserCell>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERENTERPRISE))
            {
                info.set_userEnterprise((CsdlHashMap<Long, CsdlArrayList<SUserEnterprise>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERGROUP))
            {
                info.set_userGroupInfo((CsdlHashMap<Long, CsdlArrayList<SUserGroup>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERGROUPTIER))
            {
                info.set_userGroupTier((CsdlHashMap<Long, CsdlArrayList<SUserGroupTier>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERINFO))
            {
                info.set_userInfo((CsdlHashMap<Long, CsdlArrayList<SUser>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERLIFECYCLE))
            {
                info.set_userCycle((CsdlHashMap<Long, CsdlArrayList<SUserCycle>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERMONITOR))
            {
                info.set_userMonitor((CsdlHashMap<Long, CsdlArrayList<SUserMonitor>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERORDERCONFIRM))
            {
                info.set_userOrderConfirm((CsdlHashMap<Long, CsdlArrayList<SUserOrderConfirm>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERRELA))
            {
                info.set_userRela((CsdlHashMap<Long, CsdlArrayList<SUserRela>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERRESPAY))
            {
                info.set_userResPay((CsdlHashMap<Long, CsdlArrayList<SUserResPay>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_USERSERVICESTATUS))
            {
                info.set_userService((CsdlHashMap<Long, CsdlArrayList<SUserServiceStatus>>) rdl.getReqObj());
            }else if(rdl.getKv().equals(MdbKVDefine.SYNC_HOME_ZONE)){
                info.set_userHome((CsdlHashMap<Long, CsdlArrayList<SUserHome>>) rdl.getReqObj());
            }
            else if (rdl.getKv().equals(MdbKVDefine.SYNC_CMUSERSHAREPROD))
            {
                info.set_userShare((CsdlHashMap<Long, CsdlArrayList<SUserShareProm>>) rdl.getReqObj());
            }
            else if(rdl.getKv().equals(MdbKVDefine.SYNC_CMSUSERPBX)) {
            	info.set_userPbx((CsdlHashMap<Long, CsdlArrayList<SUserPbx>>) rdl.getReqObj());
			}
            else if(rdl.getKv().equals(MdbKVDefine.SYNC_CMUSERPORTABILITY)){
            	info.set_userPort((CsdlHashMap<Long, CsdlArrayList<SUserPort>>) rdl.getReqObj());
            }
            else if(rdl.getKv().equals(MdbKVDefine.SYNC_CMRESOURCEMAP)){
            	info.set_resourceMap((CsdlHashMap<Long, CsdlArrayList<SResourceMap>>) rdl.getReqObj());
            }
            else if(rdl.getKv().equals(MdbKVDefine.SYNC_CMREUSERMARKET)){
            	info.set_userMarket((CsdlHashMap<Long, CsdlArrayList<SUserMarket>>) rdl.getReqObj());
            }else if(rdl.getKv().equals(MdbKVDefine.SYNC_USERUSERREL)){
            	info.set_userUserRel((CsdlHashMap<Long, CsdlArrayList<SUserUserRel>>) rdl.getReqObj());
            }else if(rdl.getKv().equals(MdbKVDefine.SYNC_SUSERPARAM)){
            	info.set_userParam((CsdlHashMap<Long, CsdlArrayList<SUserParam>>) rdl.getReqObj());
            }else if(rdl.getKv().equals(MdbKVDefine.SYNC_SUSERCUSTINFO)){
            	info.set_userCustInfo((CsdlHashMap<Long, CsdlArrayList<SUserCustInfo>>) rdl.getReqObj());
            }else if(rdl.getKv().equals(MdbKVDefine.SYNC_SUSERSERVICEINFO)){
            	info.set_userServiceInfo((CsdlHashMap<Long, CsdlArrayList<SUserService>>) rdl.getReqObj());
            }else if(rdl.getKv().equals(MdbKVDefine.SYNC_SVALIDCHANGE)){
            	info.set_validChange((CsdlHashMap<Long, CsdlArrayList<SValidChange>>) rdl.getReqObj());
            }
            
        }
        return info;
    }
}
