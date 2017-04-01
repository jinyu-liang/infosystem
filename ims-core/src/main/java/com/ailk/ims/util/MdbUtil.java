package com.ailk.ims.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jef.common.log.Logger;
import jef.database.DataObject;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObject;
import com.ailk.easyframe.sdl.sdlbuffer.MemberTypeInfo;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.innerclass.SImsOrderInfo;
import com.ailk.ims.innerclass.SImsProductOrder;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;

/**
 * @Description:提取mdb对象操作的工具方法
 * @author wangjt
 * @Date 2012-5-24
 */
public class MdbUtil
{

    /**
     * 产品列表，根据busiFlag重新分类
     */
    public static List<SImsProductOrder> rebuildProductOrderList(List<SImsProductOrder> sProductOrderList)
    {
        if (CommonUtil.isEmpty(sProductOrderList))
        {
            return sProductOrderList;
        }
        Set<Integer> busiFlagSet = new HashSet<Integer>();
        for (SImsProductOrder order : sProductOrderList)
        {
            Integer busiFlag = order.getOrder_flag();
            busiFlagSet.add(busiFlag);
        }
        List<SImsProductOrder> newSProductOrderList = new ArrayList<SImsProductOrder>();
        for (Integer busiFlag : busiFlagSet)
        {
            SImsProductOrder sProductOrder = new SImsProductOrder();
            sProductOrder.setOrder_flag(busiFlag);
            List<SImsOrderInfo> infoList = new ArrayList<SImsOrderInfo>();
            for (SImsProductOrder order : sProductOrderList)
            {
                if (order.getOrder_flag() == busiFlag.intValue())
                {
                    infoList.addAll(order.getList_order_info());
                }
            }
            sProductOrder.setList_order_info(infoList);
            newSProductOrderList.add(sProductOrder);
        }
        return newSProductOrderList;
    }

    public static int parseGroupAuthCode(CaAccountGroup vpn)
    {
        Integer shareFlag=vpn.getFreeresShareFlag();
        if(shareFlag==null){
            shareFlag=1;
        }
        Integer inIn = vpn.getIngroupIncomingcall();
        Integer inOut = vpn.getIngroupOutgoingcall();
        Integer outIn = vpn.getOutgroupIncomingcall();
        Integer outOut = vpn.getOutgroupOutgoingcall();
        Integer crossIn = vpn.getCrossgroupIncomingcall();
        Integer crossOut = vpn.getCrossgroupOutgoingcall();
        Integer specIn = vpn.getSpecIncomingcall();
        Integer specOut = vpn.getSpecOutgoingcall();

        int authCode = 0;
        authCode =shareFlag*100000000+ inOut * 10000000 + inIn * 1000000 + crossOut * 100000 + crossIn * 10000 + specOut * 1000 + specIn * 100
                + outOut * 10 + outIn;

        return authCode;
    }

    public static int parseGroupAuthCode(CaAccountRv rv)
    {
        Integer inIn = rv.getIngroupIncomingcall();
        Integer inOut = rv.getIngroupOutgoingcall();
        Integer outIn = rv.getOutgroupIncomingcall();
        Integer outOut = rv.getOutgroupOutgoingcall();
        Integer crossIn = rv.getCrossgroupIncomingcall();
        Integer crossOut = rv.getCrossgroupOutgoingcall();
        Integer specIn = rv.getSpecIncomingcall();
        Integer specOut = rv.getSpecOutgoingcall();

        if (inIn == null)
            inIn = 1;
        if (inOut == null)
            inOut = 1;
        if (outIn == null)
            outIn = 1;
        if (outOut == null)
            outOut = 1;
        if (crossIn == null)
            crossIn = 1;
        if (crossOut == null)
            crossOut = 1;
        if (specIn == null)
            specIn = 1;
        if (specOut == null)
            specOut = 1;

        int authCode = 0;
        authCode = inOut * 10000000 + inIn * 1000000 + crossOut * 100000 + crossIn * 10000 + specOut * 1000 + specIn * 100
                + outOut * 10 + outIn;

        return authCode;
    }

    /**
     * @author yanchuan 判断是否为预算，reguid等特殊产品 2012-2-28
     */
    public static boolean isSpecialProd(Integer pricingPlanId)
    {
        if (pricingPlanId == null)
            return false;
        if (pricingPlanId == EnumCodeDefine.BUDGET_PRICING_PLAN_ID)
            return true;
        if (pricingPlanId == EnumCodeDefine.REGUID_PRICING_PLAN_ID)
            return true;
        return false;
    }

    public static Integer parseMdbGroupType(Integer accountType)
    {
        if (accountType == null)
            return null;
        Integer mdbGroupType = null;
        if (accountType.shortValue() == EnumCodeDefine.ACCOUNT_TYPE_VPN)
            mdbGroupType = EnumCodeDefine.MDB_GROUP_TYPE_VPN;
        else if (accountType.shortValue() == EnumCodeDefine.ACCOUNT_TYPE_CUG)
            mdbGroupType = EnumCodeDefine.MDB_GROUP_TYPE_CUG;
        else if (accountType.shortValue() == EnumCodeDefine.ACCOUNT_TYPE_COMMUNITY)
            mdbGroupType = EnumCodeDefine.MDB_GROUP_TYPE_COMMUNITY;
        else if (accountType.shortValue() == EnumCodeDefine.ACCOUNT_TYPE_VPBX)
            mdbGroupType = EnumCodeDefine.MDB_GROUP_TYPE_VPBX;
        else if (accountType.shortValue() == EnumCodeDefine.ACCOUNT_TYPE_SPECNBR_GCA)
            mdbGroupType = EnumCodeDefine.MDB_GROUP_TYPE_SPEC_GCA;
        return mdbGroupType;
    }

    /**
     * mdb 的对象，如果有未设置值的long, short ,int字段，设置为默认值-999<br>
     * 支持多层结构处理<br>
     * wangjt 2011-12-15
     * yangjh 增加String 默认值设置
     */
    public static void setNull2Default(CsdlStructObject sdlObj)
    {
        List<MemberTypeInfo> list = sdlObj.getMemberInfoList();

        for (MemberTypeInfo field : list)
        {
            int sdlTypeId = field.getSdlTypeId();
            if (sdlTypeId == CsdlStructObject.SDL_INT32 || sdlTypeId == CsdlStructObject.SDL_INT64)
            {
                String fieldName = field.getFieldName();
                if (!sdlObj.is_used(fieldName))
                {
                    setValue(field, sdlObj, EnumCodeDefine.MDB_DEFAULT_VALUE);
                }
            }
            else if (sdlTypeId == CsdlStructObject.SDL_INT16)
            {
                String fieldName = field.getFieldName();
                if (!sdlObj.is_used(fieldName))
                {
                    setValue(field, sdlObj, EnumCodeDefine.MDB_DEFAULT_VALUE);
                }
            }
            else if (sdlTypeId == CsdlStructObject.SDL_STRUCT)
            {
                CsdlStructObject value = (CsdlStructObject) getValue(field, sdlObj);
                if (value != null)
                {
                    setNull2Default(value);
                }
            }
            else if (sdlTypeId == CsdlStructObject.SDL_LIST)
            {
                CsdlArrayList value = (CsdlArrayList) getValue(field, sdlObj);
                if (value != null && !value.isEmpty())
                {
                    for (Object obj : value)
                    {
                        setNull2Default((CsdlStructObject) obj);
                    }
                }
            }
            //yangjh 增加String 默认值设置
            else if (sdlTypeId == CsdlStructObject.SDL_STRING)
            {
                String value = (String) getValue(field, sdlObj);
                if (CommonUtil.isEmpty(value))
                {
                    setValue(field, sdlObj, EnumCodeDefine.MDB_DEFAULT_VALU_STRING);
                }
            }
        }
    }

    private static void setValue(MemberTypeInfo field, Object bean, Object value)
    {
        try
        {
            field.setValue(bean, value);
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
        }
    }

    private static Object getValue(MemberTypeInfo field, Object sdlObj)
    {
        try
        {
            return field.getValue(sdlObj);
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            return null;
        }
    }
    
    
    /**
     * 将传入的实体列表按产品id返回，传入的实体必须有product_id字段
     * luojb 2013-3-12
     * @param list
     * @return
     */
    public static Map<Long,List<? extends DataObject>> getMapByProductIds(List<? extends DataObject> list)
    {
      //根据产品分组处理
        Map<Long,List<? extends DataObject>> map = new HashMap<Long, List<? extends DataObject>>();
        for(DataObject obj:list)
        {
            Long productId = (Long)ClassUtil.getFieldValue(obj, "productId");
            List<DataObject> charValues = (List)map.get(productId);
            if(charValues == null)
            {
                charValues = new ArrayList<DataObject>();
                map.put(productId, charValues);
            }
            charValues.add(obj);
        }
        return map;
    }
    
    /**
     * 根据group_id分组
     * luojb 2013-3-12
     * @param list
     * @return
     */
    public static Map<Long,List<? extends DataObject>> getMapByGroupIds(List<? extends DataObject> list)
    {
        Map<Long,List<? extends DataObject>> map = new HashMap<Long, List<? extends DataObject>>();
        for(DataObject obj:list)
        {
            Long groupId = (Long)ClassUtil.getFieldValue(obj, "groupId");
            List<DataObject> charValues = (List)map.get(groupId);
            if(charValues == null)
            {
                charValues = new ArrayList<DataObject>();
                map.put(groupId, charValues);
            }
            charValues.add(obj);
        }
        return map;
    }
    
    /**
     * 根据so_nbr分组
     * luojb 2013-3-12
     * @param list
     * @return
     */
    public static Map<Long,List<? extends DataObject>> getMapBySoNbr(List<? extends DataObject> list)
    {
        Map<Long,List<? extends DataObject>> map = new HashMap<Long, List<? extends DataObject>>();
        for(DataObject obj:list)
        {
            Long soNbr = (Long)ClassUtil.getFieldValue(obj, "soNbr");
            List<DataObject> charValues = (List)map.get(soNbr);
            if(charValues == null)
            {
                charValues = new ArrayList<DataObject>();
                map.put(soNbr, charValues);
            }
            charValues.add(obj);
        }
        return map;
    }
    
    /**
     * 根据objectId_objectType分组
     * luojb 2013-3-12
     * @param list
     * @return
     */
    public static Map<String,List<? extends DataObject>> getMapByObject(List<? extends DataObject> list)
    {
        Map<String,List<? extends DataObject>> map = new HashMap<String, List<? extends DataObject>>();
        for(DataObject obj:list)
        {
            Long objectId = (Long)ClassUtil.getFieldValue(obj, "objectId");
            Integer objectType = (Integer)ClassUtil.getFieldValue(obj, "objectType");
            String key = objectId+"_"+objectType;
            List<DataObject> charValues = (List)map.get(key);
            if(charValues == null)
            {
                charValues = new ArrayList<DataObject>();
                map.put(key, charValues);
            }
            charValues.add(obj);
        }
        return map;
    }
    
    

}
