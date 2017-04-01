package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jef.database.DataObject;

import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAcctProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SBudgetInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SBudgetInfoDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SGroupProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromPriceParam;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SRejectList;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturn;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SReturnEx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUsagePay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserAcctPayDtl;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCell;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserHome;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserProm;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserRela;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserResPay;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserServiceStatus;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ListMapMdbRdl;
import com.ailk.ims.common.MdbRdl;
import com.ailk.ims.component.helper.GroupHelper;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.MdbUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCellInfo;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * MDB Sal方式组件
 * 
 * @Description
 * @author wuyj
 * @Date 2012-6-16
 * 
 */
public class ProdMdbSalComponent extends BaseComponent
{

    private MdbSalBuildComponent getMdbSalBuildComp()
    {
        return context.getComponent(MdbSalBuildComponent.class);
    }

    /**
     * @Description: 组装产品订购列表
     * @param result
     * @author: tangjl5
     * @Date: 2012-6-18
     */
    public void buildSalProductOrder(List<MdbRdl> result)
    {
        Map<String, ListMapMdbRdl> tempMap = new HashMap<String, ListMapMdbRdl>();
        
        //产品表
        List<CoProd> coProds = context.getAllCacheList(CoProd.class);
        if (CommonUtil.isNotEmpty(coProds))
        {
            for (CoProd coProd:coProds)
            {
                if (coProd == null)
                {
                    continue;
                }
                PmProductOffering prodOffering = context.get3hTree()
                        .loadOffering3hBean(coProd.getProductOfferingId().longValue()).getOffering();
                // @Date 2012-09-13 wuyujie ： callscreen未上发,需要把用户级和群级区分开来
                if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_GCA)
                {
                    // 普通群订购的产品
                    // @Date 2012-09-26 yangjh : 增加群间关系的上发
                    if (GroupHelper.isGroupCanOrderAllProd(coProd.getBusiFlag()))
                    {
                        // 群订购产品
                        //buildGroupPromRdl(coProd, prodOffering, tempMap);
                    }
                    else if (GroupHelper.isGroupPersonProd(coProd.getBusiFlag()))
                    {
                        // 群个性化订购
                       // buildPersonalGroupPromRdl(coProd, prodOffering, tempMap);
                    }

                    }
                    else if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                    {
                        // 用户级产品
                        if (GroupHelper.isGroupPersonProd(coProd.getBusiFlag()))
                        {
                            // 群个性化订购
                            buildPersonalGroupPromRdl(coProd, prodOffering, tempMap);
                        }else if(GroupHelper.isGroupCanOrderAllProd(coProd.getBusiFlag())){
                        	//buildGroupPromRdl(coProd, prodOffering, tempMap);
                        }else{
                            // 用户级产品
                           // buildSUserPromRdl(coProd, prodOffering, tempMap);
                        }
                    }
                    else if (coProd.getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
                    {
                        // 2012-10-18 luojb #61470 账户级产品 分账产品不上发MDB
                        if (coProd.getBusiFlag() != SpecCodeDefine.SPLIT)
                        {
                            //buildSAcctPromRdl(coProd, prodOffering, tempMap);
                        }
                    }
            }
        }
        
        //产品周期表
        List<CoProdBillingCycle> coProdBillCycles = context.getAllCacheList(CoProdBillingCycle.class);
        if (CommonUtil.isNotEmpty(coProdBillCycles))
        {
            buidlSPromBillCycleRdl(coProdBillCycles, tempMap);
        }
        
        
        //用户服务
        List<CmResServCycle> resServCycleList = context.getAllCacheList(CmResServCycle.class);
        if (CommonUtil.isNotEmpty(resServCycleList))
        {
            buildSUserServiceStatusRdl(resServCycleList, tempMap);
        }
        
        
        //gaoqc5 2013-2-21 亲情号码上发不需关联产品
        List<CoFnCharValue> coFnCharValues = context.getAllCacheList(CoFnCharValue.class);
        if (CommonUtil.isNotEmpty(coFnCharValues))
        {
            buildSUserRelaRdl(coFnCharValues, tempMap);
        }
        
        // 二次议价参数
        List<CoProdPriceParam> coProdPriceParams = context.getAllCacheList(CoProdPriceParam.class);
        if (CommonUtil.isNotEmpty(coProdPriceParams))
        {
            buildSPromPriceParamRdl(coProdPriceParams, tempMap);
        }
        
        // 账户分账信息表
        List<CoSplitCharValue> coSplitCharValues = context.getAllCacheList(CoSplitCharValue.class);
        buildSAcctSplitPayRdl(coSplitCharValues, tempMap);
        
        //预算
        List<CoBudgetCharValue> coBudgetCharValue = context.getAllCacheList(CoBudgetCharValue.class);
        buildBudgetInfoRdl(coBudgetCharValue, tempMap);

        
        List<CoProdCellInfo> cellInfoList = context.getAllCacheList(CoProdCellInfo.class);
        if (CommonUtil.isNotEmpty(cellInfoList))
        {
            buildSUserHomeRdl(cellInfoList, tempMap);
        }
        
        // 特征值对象
        List<CoProdCharValue> coProdCharValues = context.getAllCacheList(CoProdCharValue.class);
        if (CommonUtil.isNotEmpty(coProdCharValues))
        {
            //先按产品id分组
            Map<Long,List<? extends DataObject>> map = MdbUtil.getMapByProductIds(coProdCharValues);
            
            for(Entry<Long,List<? extends DataObject>> en:map.entrySet())
            {
                Long productId = en.getKey();
                CoProd coProd = context.getSingleCache(CoProd.class, 
                        new CacheCondition(CoProd.Field.productId,productId));
                if(coProd == null)
                {
                    coProd = DBUtil.querySingle(CoProd.class,new DBCondition(CoProd.Field.productId,productId));
                }
                if(coProd == null)
                { 
                    continue;
                }
                //按group_id分组
                //@Date 2013-09-10 yugb :wukl手工上发不填产品编号时，导致特征值重复
                Map<Long,List<? extends DataObject>> groupMap = MdbUtil.getMapByGroupIds(en.getValue());
                for(List<? extends DataObject> list:groupMap.values())
                {
                    // 按so_nbr分组
                    Map<Long,List<? extends DataObject>> soNbrMap = MdbUtil.getMapBySoNbr(list);
                    for(List l:soNbrMap.values())
                    {
                        List<CoProdCharValue> charValues = (List<CoProdCharValue>)l;
                        if (coProd.getBusiFlag() == SpecCodeDefine.REGUIDE_CHARGE)
                        {
                            // reguid相关表上发
                            buildReguidInfo(charValues, tempMap);
                        }
                        else if (coProd.getBusiFlag() == SpecCodeDefine.REGUIDE_USAGE)
                        {
                            // 话单代付表CUsagePay
                            buildSUsagePayRdl(charValues, tempMap);
                        }
                        else if (coProd.getBusiFlag() == SpecCodeDefine.MCS_NUMBER)
                        {
                            // 黑白名单接续控制主表CRejectList
                            buildSRejectRdl(charValues, tempMap);
                        }
                        else if (coProd.getBusiFlag() == SpecCodeDefine.SPECIAL_NUMBER)
                        {
                            // 用户自定义控制表CCustomizedList
                            buildCCustomizedRdl(charValues, coProd.getProductOfferingId(), tempMap);
                        }
                        //不需要实例化和上发，计费直接把销售品里配置的加载到xc里
                        else if (coProd.getBusiFlag() == SpecCodeDefine.HOME_ZONE)
                        {
                            // 用户小区信息表CUserCell
                            buildSUserCellRdl(charValues, tempMap);
                        }
                        else if (coProd.getBusiFlag() == SpecCodeDefine.INTER_GROUP_PROMOTION)
                        {
                            // 群间关系CGroupRela
                            buildSGroupRelaRdl(charValues, coProd.getBusiFlag(), tempMap);
                        }
                        else if (coProd.getBusiFlag() == SpecCodeDefine.OUTNET_PHONE)
                        {
                            // 群特殊号码产品关系
                            buildSGroupRelaRdl(charValues, coProd.getBusiFlag(), tempMap);
                        }
                        else
                        {
                            buildSPromCharValueRdl(charValues, coProd, tempMap);
                        }
                    }
                }
                }
            }
            //全部放入返回的结果中
            if(CommonUtil.isNotEmpty(tempMap)){
                for(Entry<String, ListMapMdbRdl> entry:tempMap.entrySet()){
                    result.add(entry.getValue());
            }
        }
        
    }
    
   

    
    /**
     * @Description: 预算相关信息上发
     * @param coProdCharValues
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public void buildBudgetInfoRdl(List<CoBudgetCharValue> coBudgetCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        if (CommonUtil.isEmpty(coBudgetCharValues))
        {
            return;
        }
        //根据产品分组处理
        Map<Long,List<? extends DataObject>> map = MdbUtil.getMapByProductIds(coBudgetCharValues);
        for(List charList:map.values())
        {
            Map<Long,List<? extends DataObject>> soNbrMap = MdbUtil.getMapBySoNbr(charList);
            for(List soNbrList:soNbrMap.values())
            {
                // 2012-07-06 yangjh story：50195 budget信息入CoBudgetCharValue表
                buildSBudgetInfoRdl(soNbrList, tempMap);
                buildSBudgetInfoDtlRdl(soNbrList, tempMap);
            }
        }
    }

    /**
     * @Description: SLA规则表CSlaRule
     * @param coProdCharValues
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    /*
     * public ListMapMdbRdl buildSSLARdl(List<CoProdCharValue> coProdCharValues, CoProd coProd) { // SLA规则表CSlaRule ListMapMdbRdl
     * slaRule = new ListMapMdbRdl(MdbKVDefine.SYNC_SLARULE,SReturn.class); slaRule.addRecord(coProd.getProductId(),
     * getMdbSalBuildComp().buildSSlaRule(coProdCharValues, coProd)); return slaRule; }
     */

    /**
     * @Description: 预算控制信息表
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public void buildSBudgetInfoRdl(List<CoBudgetCharValue> budgetCharValueList,
            Map<String, ListMapMdbRdl> tempMap)
    {
        // 预算控制信息表
        SBudgetInfo info = getMdbSalBuildComp().buildSBudgetInfo(budgetCharValueList,context.isDiffException());
//        if(info == null)
//        {
//            return;
//        }
        ListMapMdbRdl budget = tempMap.get(MdbKVDefine.SYNC_BUDGETINFO);
        if (budget == null)
        {
            budget = new ListMapMdbRdl(MdbKVDefine.SYNC_BUDGETINFO, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_BUDGETINFO, budget);
        }
        budget.addRecord(info.get_promNo(), info);
    }

    /**
     * @Description: 预算控制明细表 CBudgetInfoDtl
     * @param charValueList
     * @param prod
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public void buildSBudgetInfoDtlRdl(List<CoBudgetCharValue> budgetProdCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        List<SBudgetInfoDtl> dtls = getMdbSalBuildComp().budilBudgetInfoDtl(budgetProdCharValues,context.isDiffException());
        if(CommonUtil.isEmpty(dtls))
        {
            return;
        }
        ListMapMdbRdl budgetDtl = tempMap.get(MdbKVDefine.SYNC_BUDGETDTL);
        if (budgetDtl == null)
        {
            budgetDtl = new ListMapMdbRdl(MdbKVDefine.SYNC_BUDGETDTL, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_BUDGETDTL, budgetDtl);
        }
        for (SBudgetInfoDtl sBudgetInfoDtl : dtls)
        {
            budgetDtl.addRecord(sBudgetInfoDtl.get_promNo(), sBudgetInfoDtl);
        }
    }

    /**
     * @param coProdCharValues
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public List<ListMapMdbRdl> buildReguidInfo(List<CoProdCharValue> coProdCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        List<ListMapMdbRdl> rdlList = new ArrayList<ListMapMdbRdl>();
        rdlList.add(buildCUserAcctPayRdl(coProdCharValues, tempMap));
        rdlList.add(buildSUserAcctPayDtlRdl(coProdCharValues, tempMap));
        rdlList.add(buildSUserResPayRdl(coProdCharValues, tempMap));
        return (rdlList);
    }

    /**
     * @Description: 用户资源分账信息表
     * @param coProdCharValues
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buildSUserResPayRdl(List<CoProdCharValue> coProdCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl userResPay = tempMap.get(MdbKVDefine.SYNC_USERRESPAY);
        if (userResPay == null)
        {
            userResPay = new ListMapMdbRdl(MdbKVDefine.SYNC_USERRESPAY, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_USERRESPAY, userResPay);
        }
        SUserResPay resPay = getMdbSalBuildComp().buildSUserResPay(coProdCharValues,context.isDiffException());
        userResPay.addRecord(resPay.get_promNo(), resPay);
        return userResPay;
    }

    /**
     * @Description: 用户资金账户分账信息明细表
     * @param coProdCharValues
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buildSUserAcctPayDtlRdl(List<CoProdCharValue> coProdCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl userAcctPayDtl = tempMap.get(MdbKVDefine.SYNC_USERACCTPAYDTL);
        if (userAcctPayDtl == null)
        {
            userAcctPayDtl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERACCTPAYDTL, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_USERACCTPAYDTL, userAcctPayDtl);
        }
        SUserAcctPayDtl dtl = getMdbSalBuildComp().buildSUserAcctPayDtl(coProdCharValues,context.isDiffException());
        userAcctPayDtl.addRecord(dtl.get_promNo(),dtl);
        return userAcctPayDtl;
    }

    /**
     * @Description: 用户资金账户分账信息表
     * @param coProdCharValues
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buildCUserAcctPayRdl(List<CoProdCharValue> coProdCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl userAcctPayInfo = tempMap.get(MdbKVDefine.SYNC_USERACCTPAYINFO);
        if (userAcctPayInfo == null)
        {
            userAcctPayInfo = new ListMapMdbRdl(MdbKVDefine.SYNC_USERACCTPAYINFO, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_USERACCTPAYINFO, userAcctPayInfo);
        }
        SUserAcctPay acctPay = getMdbSalBuildComp().buildCUserAcctPay(coProdCharValues,context.isDiffException());
        userAcctPayInfo.addRecord(acctPay.get_promNo(), acctPay);
        return userAcctPayInfo;
    }

    /**
     * @param coSplitCharValues
     * @param coProd
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public void buildSAcctSplitPayRdl(List<CoSplitCharValue> coSplitCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        if (CommonUtil.isEmpty(coSplitCharValues))
        {
            return;
        }
        ListMapMdbRdl acctSplit = tempMap.get(MdbKVDefine.SYNC_ACCTSPLITPAY);
        if (acctSplit == null)
        {
            acctSplit = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCTSPLITPAY, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_ACCTSPLITPAY, acctSplit);
        }
        Map<Long,List<? extends DataObject>> map = MdbUtil.getMapByProductIds(coSplitCharValues);
        long objectId = (Long)ClassUtil.getFieldValue(coSplitCharValues.get(0), "objectId");
       // long groupId = (Long)ClassUtil.getFieldValue(coSplitCharValues.get(0), "groupId");
        for(Entry<Long,List<? extends DataObject>> en:map.entrySet())
        {
            Long productId = en.getKey();
            Map<Long,List<? extends DataObject>> groupMap = MdbUtil.getMapByGroupIds(en.getValue());
            for(List<? extends DataObject> groupList:groupMap.values())
            {
                Map<Long,List<? extends DataObject>> soNbrMap = MdbUtil.getMapBySoNbr(groupList);
                for(List soNbrList:soNbrMap.values())
                { 
                	if(context.isDiffException()||context.isManualFlag()){
                     acctSplit.addRecord(objectId, getMdbSalBuildComp().buildSAcctSplitPay(soNbrList,context.isDiffException()));
                	}else{
                    acctSplit.addRecord(productId, getMdbSalBuildComp().buildSAcctSplitPay(soNbrList,context.isDiffException()));
                	}
                }
            }
        }
    }

    /**
     * @Description: 普通群订购的产品
     * @param coProd
     * @param prodOffering
     * @return
     * @author: tangjl5
     * @Date: 2012-6-19
     */
    public ListMapMdbRdl buildGroupPromRdl(CoProd coProd, Map<String, ListMapMdbRdl> tempMap,CoProdCharValue charValue)
    {
        ListMapMdbRdl groupProdRdl = tempMap.get(MdbKVDefine.SYNC_GROUP_PROD);
        if (groupProdRdl == null)
        {
            groupProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUP_PROD, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_GROUP_PROD, groupProdRdl);
        }
        SGroupProm prom = getMdbSalBuildComp().buildGroupProm(coProd,charValue,context.isDiffException());
        if(context.isDiffException()||context.isManualFlag()){
            groupProdRdl.addRecord(prom.get_servId(), prom);

        }else{
        groupProdRdl.addRecord(prom.get_promNo(), prom);
        }
        return groupProdRdl;
    }

    /**
     * @Description: 个性化群订购产品
     * @param coProd
     * @param prodOffering
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buildPersonalGroupPromRdl(CoProd coProd, PmProductOffering prodOffering,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl groupProdRdl = tempMap.get(MdbKVDefine.SYNC_GROUP_PROD);
        if (groupProdRdl == null)
        {
            groupProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUP_PROD, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_GROUP_PROD, groupProdRdl);
        }
        SGroupProm prom = getMdbSalBuildComp().buildPersonalGroupProm(coProd, prodOffering,context.isDiffException());
        groupProdRdl.addRecord(prom.get_promNo(), prom);
        return groupProdRdl;
    }

    /**
     * @param coProd
     * @param prodOffering
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buildSUserPromRdl(CoProd coProd, Map<String, ListMapMdbRdl> tempMap)
    {
        // 2012-07-17 yangjh SReturn->SReturnEx
        ListMapMdbRdl userProdRdl = tempMap.get(MdbKVDefine.SYNC_USER_PROD);
        if (userProdRdl == null)
        {
            userProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USER_PROD, SReturnEx.class);
            tempMap.put(MdbKVDefine.SYNC_USER_PROD, userProdRdl);
        }
        SUserProm userProm = getMdbSalBuildComp().buildSUserProm(coProd,context.isDiffException());
        if(context.isDiffException()||context.isManualFlag()){
            userProdRdl.addRecord(userProm.get_servId(), userProm);
        }else{
            userProdRdl.addRecord(userProm.get_promNo(), userProm);
        }
        return userProdRdl;
    }

    /**
     * @Description: 账户级产品
     * @param coProd
     * @param prodOffering
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buildSAcctPromRdl(CoProd coProd, Map<String, ListMapMdbRdl> tempMap)
    {
        // 2012-07-17 yangjh SReturn->SReturnEx
        ListMapMdbRdl acctProdRdl = tempMap.get(MdbKVDefine.SYNC_ACCOUNT_PROD);
        if (acctProdRdl == null)
        {
            acctProdRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_ACCOUNT_PROD, SReturnEx.class);
            tempMap.put(MdbKVDefine.SYNC_ACCOUNT_PROD, acctProdRdl);
        }
        SAcctProm acctProm = getMdbSalBuildComp().buildSAcctProm(coProd,context.isDiffException());
        if(context.isDiffException()||context.isManualFlag()){
            acctProdRdl.addRecord(acctProm.get_acctId(), acctProm);

        }else{
            acctProdRdl.addRecord(acctProm.get_promNo(), acctProm);

        }
        return acctProdRdl;
    }

    /**
     * @param coProd
     * @param prodOffering
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    /*
     * public ListMapMdbRdl buildSPromPriceRdl(CoProd coProd, PmProductOffering prodOffering) { ListMapMdbRdl prodPriceRdl = new
     * ListMapMdbRdl(MdbKVDefine.SYNC_PROMPRICE,SReturn.class); SPromPrice sProdPrice =
     * getMdbSalBuildComp().buildSPromPrice(coProd); prodPriceRdl.addRecord(coProd.getProductId(), sProdPrice); return
     * prodPriceRdl; }
     */

    /**
     * @param coProd
     * @param prodOffering
     * @param sericeIds
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buildSUserServiceStatusRdl(List<CmResServCycle> resServCycleList,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl userServiceStsRdl = tempMap.get(MdbKVDefine.SYNC_USERSERVICESTATUS);
        if (userServiceStsRdl == null)
        {
            userServiceStsRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERSERVICESTATUS, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_USERSERVICESTATUS, userServiceStsRdl);
        }
        for (CmResServCycle cmResServ : resServCycleList)
        {
            SUserServiceStatus userService = getMdbSalBuildComp().buildSUserServiceStatus(cmResServ,context.isDiffException());
            userServiceStsRdl.addRecord(userService.get_servId(), userService);
        }
        return userServiceStsRdl;
    }

    /**
     * @param coProd
     * @param coProdBillCycles
     * @return
     * @author: tangjl5
     * @Date: 2012-6-22
     */
    public ListMapMdbRdl buidlSPromBillCycleRdl(List<CoProdBillingCycle> coProdBillCycles,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl prodBillCycleStsRdl = tempMap.get(MdbKVDefine.SYNC_PROD_BILLING_CYCLE);
        if (prodBillCycleStsRdl == null)
        {
            prodBillCycleStsRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROD_BILLING_CYCLE, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_PROD_BILLING_CYCLE, prodBillCycleStsRdl);
        }
        for (CoProdBillingCycle coProdBillingCycle : coProdBillCycles)
        {
            SPromBillCycle sProdBillCycle = getMdbSalBuildComp().buidlSPromBillCycle(coProdBillingCycle,context.isDiffException());
            prodBillCycleStsRdl.addRecord(coProdBillingCycle.getProductId(), sProdBillCycle);
        }
        return prodBillCycleStsRdl;
    }

    public ListMapMdbRdl buildSUsagePayRdl(List<CoProdCharValue> coProdCharValues,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl pay = tempMap.get(MdbKVDefine.SYNC_USAGEPAY);
        if (pay == null)
        {
            pay = new ListMapMdbRdl(MdbKVDefine.SYNC_USAGEPAY, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_USAGEPAY, pay);
        }
        SUsagePay usagePay = getMdbSalBuildComp().buildSUsagePay(coProdCharValues,context.isDiffException());
        pay.addRecord(usagePay.get_promNo(),usagePay);
        return pay;
    }

    public ListMapMdbRdl buildSRejectRdl(List<CoProdCharValue> coProdCharValues, Map<String, ListMapMdbRdl> tempMap)
    {

        ListMapMdbRdl rejectRdl = tempMap.get(MdbKVDefine.SYNC_REJECTLIST);
        if (rejectRdl == null)
        {
            rejectRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_REJECTLIST, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_REJECTLIST, rejectRdl);
        }
        SRejectList reject = getMdbSalBuildComp().buildSReject(coProdCharValues,context.isDiffException());
        rejectRdl.addRecord(coProdCharValues.get(0).getProductId(), reject);
        return rejectRdl;
    }

    public ListMapMdbRdl buildCCustomizedRdl(List<CoProdCharValue> coProdCharValues,Integer offerId,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl customizedRdl = tempMap.get(MdbKVDefine.SYNC_CUSTOMIZEDLIST);
        if (customizedRdl == null)
        {
            customizedRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_CUSTOMIZEDLIST, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_CUSTOMIZEDLIST, customizedRdl);
        }
        customizedRdl.addRecord(coProdCharValues.get(0).getProductId(), getMdbSalBuildComp().buildCCustomizedList(coProdCharValues, offerId,context.isDiffException()));
        return customizedRdl;
    }

    public ListMapMdbRdl buildSUserRelaRdl(List<CoFnCharValue> coProdCharValues, 
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl userRela = tempMap.get(MdbKVDefine.SYNC_USERRELA);
        if (userRela == null)
        {
            userRela = new ListMapMdbRdl(MdbKVDefine.SYNC_USERRELA, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_USERRELA, userRela);
        }
        List<SUserRela> relas = getMdbSalBuildComp().buildSUserRela(coProdCharValues,context.isDiffException());
        for (SUserRela rela : relas)
        {
            userRela.addRecord(rela.get_servId(), rela);
        }
        return userRela;
    }

    public void buildSGroupRelaRdl(List<CoProdCharValue> coProdCharValues, Integer busiFlag,
            Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl groupRela = tempMap.get(MdbKVDefine.SYNC_GROUPRELA);
        if (groupRela == null)
        {
            groupRela = new ListMapMdbRdl(MdbKVDefine.SYNC_GROUPRELA, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_GROUPRELA, groupRela);
        }
        groupRela.addRecord(coProdCharValues.get(0).getObjectId(), getMdbSalBuildComp().buildSGroupRela(coProdCharValues, busiFlag,context.isDiffException()));
    }

    public void buildSUserCellRdl(List<CoProdCharValue> charValueList, Map<String, ListMapMdbRdl> tempMap)
    {
        ListMapMdbRdl userCellRdl = tempMap.get(MdbKVDefine.SYNC_USERCELL);
        if (userCellRdl == null)
        {
            userCellRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_USERCELL, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_USERCELL, userCellRdl);
        }
        SUserCell userCell = new SUserCell();
        userCell.set_promNo(charValueList.get(0).getProductId());
        // @Date 2012-09-13 yangjh : 增加用户servId上发
        userCell.set_servId(charValueList.get(0).getObjectId());
        userCell.set_validDate(this.toMdbValidDate(charValueList.get(0).getValidDate()));
        userCell.set_expireDate(this.toMdbExpireDate(charValueList.get(0).getExpireDate()));
        userCell.set_syncFlag(context.getSyncFlag(charValueList.get(0).getValidDate()));
        for (CoProdCharValue charValue : charValueList)
        {
            if (charValue.getSpecCharId() == SpecCodeDefine.HOME_ZONE_CELL)
            {
                userCell.set_cellCode(CommonUtil.string2Integer(charValue.getValue()));
                //上海对端小区设置为-1
                if(ProjectUtil.is_CN_SH()){
                    userCell.set_oppCellCode(-1);
                }
            }
            else if (charValue.getSpecCharId() == SpecCodeDefine.HOME_ZONE_CELL_OPPOSITE)
            {
                userCell.set_oppCellCode(CommonUtil.string2Integer(charValue.getValue()));
            }
        }
        //key 为product_id
        //userCellRdl.addRecord(charValueList.get(0).getProductId(), userCell);
        userCellRdl.addRecord(charValueList.get(0).getObjectId(), userCell);
    }

    public ListMapMdbRdl buildSPromCharValueRdl(List<CoProdCharValue> coProdCharValues, CoProd coProd,
            Map<String, ListMapMdbRdl> tempMap)
    {

        ListMapMdbRdl prodCharValueRdl = tempMap.get(MdbKVDefine.SYNC_PROMCHARVALUE);
        if (prodCharValueRdl == null)
        {
            prodCharValueRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROMCHARVALUE, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_PROMCHARVALUE, prodCharValueRdl);
        }
        for (CoProdCharValue coProdCharValue : coProdCharValues)
        {
            // exclude friend number char spec id
            if (SpecCodeDefine.FRIENDNUMBER_COUNT == coProdCharValue.getSpecCharId())
            {
                continue;
            }
            if(context.isDiffException()||context.isManualFlag()){
            prodCharValueRdl.addRecord(coProdCharValue.getObjectId(), getMdbSalBuildComp().buildSPromCharValue(coProdCharValue,context.isDiffException()));
            }else{
            prodCharValueRdl.addRecord(coProdCharValue.getProductId(), getMdbSalBuildComp().buildSPromCharValue(coProdCharValue,context.isDiffException()));
            }
           }
        return prodCharValueRdl;
    }

    public ListMapMdbRdl buildSPromPriceParamRdl(List<CoProdPriceParam> coProdPriceParams,
            Map<String, ListMapMdbRdl> tempMap)
    {

        ListMapMdbRdl prodPriceParamRdl = tempMap.get(MdbKVDefine.SYNC_PROD_PRICE_PARAM);
        if (prodPriceParamRdl == null)
        {
            prodPriceParamRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_PROD_PRICE_PARAM, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_PROD_PRICE_PARAM, prodPriceParamRdl);
        }
        for (CoProdPriceParam coProdPriceParam : coProdPriceParams)
        {
        		
        	
            SPromPriceParam sProdPriceParam = getMdbSalBuildComp().buildSPromPriceParam(coProdPriceParam,context.isDiffException());
        	if(context.isDiffException()||context.isManualFlag()){
                prodPriceParamRdl.addRecord(sProdPriceParam.get_objectId(), sProdPriceParam);
        	}else{
                prodPriceParamRdl.addRecord(sProdPriceParam.get_promNo(), sProdPriceParam);
        	}
        }
        return prodPriceParamRdl;
    }

    public ListMapMdbRdl buildSUserHomeRdl(List<CoProdCellInfo> cellInfoList, Map<String, ListMapMdbRdl> tempMap)
    {

        ListMapMdbRdl homeZoneRdl = tempMap.get(MdbKVDefine.SYNC_HOME_ZONE);
        if (homeZoneRdl == null)
        {
            homeZoneRdl = new ListMapMdbRdl(MdbKVDefine.SYNC_HOME_ZONE, SReturn.class);
            tempMap.put(MdbKVDefine.SYNC_HOME_ZONE, homeZoneRdl);
        }
        for (CoProdCellInfo info : cellInfoList)
        {
            SUserHome home = getMdbSalBuildComp().buildSUserHome(info,context.isDiffException());
            homeZoneRdl.addRecord(home.get_promNo(), home);
        }
        return homeZoneRdl;
    }
    public void buildProdBillCycleList(List<MdbRdl> result) {
        List<CoProdBillingCycle> coProdBillingCycles = context
                .getAllCacheList(CoProdBillingCycle.class);
        List<Long> prodId_list = new ArrayList<Long>();
        if (CommonUtil.isNotEmpty(coProdBillingCycles)) {
            ListMapMdbRdl prodBillCycleStsRdl = new ListMapMdbRdl(
                    MdbKVDefine.SYNC_PROD_BILLING_CYCLE, SReturn.class);
            for (CoProdBillingCycle coProdBillingCycle : coProdBillingCycles) {
                if (coProdBillingCycle == null
                        || !CommonUtil.isValid(coProdBillingCycle
                                .getProductId())) {
                    continue;
                }
                //@Date 2012-09-18 wangdw5 : 只上发了一条,导致更新记录无法上发
//              if (prodId_list.contains(coProdBillingCycle.getProductId())) {
//                  continue;
//              }
                prodId_list.add(coProdBillingCycle.getProductId());

                
                SPromBillCycle sProdBillCycle = getMdbSalBuildComp().buidlSPromBillCycle(coProdBillingCycle,context.isDiffException());
                prodBillCycleStsRdl.addRecord(
                        coProdBillingCycle.getProductId(), sProdBillCycle);
                result.add(prodBillCycleStsRdl);
            }
        }
    }
}
