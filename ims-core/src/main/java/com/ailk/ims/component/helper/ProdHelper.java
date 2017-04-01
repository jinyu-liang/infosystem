package com.ailk.ims.component.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.DataObject;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description:产品相关的静态方法
 * @author wangjt
 * @Date 2012-2-3
 * @Date 2012-10-11 zengxr bug #61094 皇室号码不收取一次性费用
 */
public class ProdHelper
{
    /**
     * 得出主产品 yanchuan 2011-10-1
     */
    public static SProductOrder parseMainPromotion(SUser user, SProductOrderList prodList)
    {
        if (prodList == null || CommonUtil.isEmpty(prodList.getItem()))
            return null;
        for (SProductOrder prod : prodList.getItem())
        {
            boolean flag = false;
            if (prod.getUser_id() != null && user.getUser_id() != null
                    && prod.getUser_id().longValue() == user.getUser_id().longValue())
                flag = true;// 过滤不是当前用户的产品
            else if (prod.getPhone_id() != null && prod.getPhone_id().equals(user.getPhone_id()))
                flag = true;
            else
                continue;
            if (flag && EnumCodeDefine.PROD_TYPE_MAIN == prod.getProduct_type().intValue())
                return prod;
        }
        return null;
    }

    /**
     * 返回同一个DataObject失效时间最晚的一条记录
     */
    public static <T extends DataObject> T getLastDataObjectByExpireDate(List<T> objectList) throws IMSException
    {
        if (CommonUtil.isEmpty(objectList))
        {
            return null;
        }
        T obj = objectList.get(0);
        Date objExpireDate = (Date) ClassUtil.getPropertyValue(obj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);

        for (int i = 1; i < objectList.size(); i++)// 第二位开始循环
        {
            T thisObj = objectList.get(i);
            Date thisExpireDate = (Date) ClassUtil.getPropertyValue(thisObj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
            if (thisExpireDate.after(objExpireDate)||thisExpireDate.equals(objExpireDate))
            {
                obj = thisObj;
                objExpireDate = thisExpireDate;
            }
        }
        return obj;
    }

    /**
     * 传入产品列表，返回第一个订购的产品 luojb 2012-8-7
     * 
     * @param prodList
     * @return
     */
    public static CoProd getFirstOrdered(List<CoProd> prodList)
    {
        if (CommonUtil.isEmpty(prodList))
            return null;
        CoProd firstProd = null;
        for (CoProd prod : prodList)
        {
            if (firstProd == null || firstProd.getProdValidDate().after(prod.getProdValidDate()))
                firstProd = prod;
        }
        return firstProd;
    }

    /**
     * 传入产品列表，返回最后一个订购的产品 luojb 2012-8-7
     * 
     * @param prodList
     * @return
     */
    public static CoProd getLastOrdered(List<CoProd> prodList)
    {
        if (CommonUtil.isEmpty(prodList))
            return null;
        CoProd lastProd = null;
        for (CoProd prod : prodList)
        {
            if (lastProd == null || lastProd.getProdExpireDate().before(prod.getProdExpireDate()))
                lastProd = prod;
        }
        return lastProd;
    }

    public static Map buildPricingPlanLuaMap(Map<Class, Object> inputData)
    {
        CmCustomer cust = (CmCustomer) inputData.get(CmCustomer.class);
        CaAccount acct = (CaAccount) inputData.get(CaAccount.class);
//        CaAccountExt acctExt = (CaAccountExt) inputData.get(CaAccountExt.class);
        
        CmResource user = (CmResource) inputData.get(CmResource.class);
        PmProductOffering offer = (PmProductOffering) inputData.get(PmProductOffering.class);
        CmIndividual individual = (CmIndividual) inputData.get(CmIndividual.class);
        
        List<CoProdPriceParam> params = (List<CoProdPriceParam>) inputData.get(CoProdPriceParam.class);
        List<CoProdCharValue> values = (List<CoProdCharValue>) inputData.get(CoProdCharValue.class);
        Map map = new HashMap();
        if (!CommonUtil.isEmpty(values))
        {
            for (CoProdCharValue pp : values)
            {
                map.put("SID" + pp.getSpecCharId(), pp.getValue());
            }
        }
        if (!CommonUtil.isEmpty(params))
        {
            for (CoProdPriceParam pp : params)
            {
                if (pp.getParamId() == SpecCodeDefine.UPSELLING_FLAG)
                {// upsell
                    map.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_UP_SELL, 1);
                }
                else if (pp.getParamId() == SpecCodeDefine.GROUP_TYPE_PROD_PARAM_ID)
                {// group type
                    map.put(ConstantDefine.LUA_PRICINGPLAN_GROUP_TYPE, Long.parseLong(pp.getParamValue()));
                }
                else
                {
                    map.put("PID" + pp.getParamId(), Long.parseLong(pp.getParamValue()));
                }
            }

        }
        if (map.get(ConstantDefine.LUA_PRICEINGPLAN_PROD_UP_SELL) == null)
        {
            map.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_UP_SELL, 0);
        }
        // map.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_FIRST_ORDER, 1);
        if (cust != null)
        {
            map.put(ConstantDefine.LUA_PRRICINGPLAN_CUST_TYPE, cust.getCustomerType());
            map.put(ConstantDefine.LUA_PRRICINGPLAN_CUST_SEGMENT, cust.getCustomerSegment());
        }
        if (individual != null)
            map.put(ConstantDefine.LUA_PRRICINGPLAN_CUST_GENDER, individual.getGender());
        if (acct != null)
            map.put(ConstantDefine.LUA_PRRICINGPLAN_ACCT_SEGMENT, acct.getAccountSegment());
        //2012-10-11 zengxr bug #61094 皇室号码不收取一次性费用
        //input special account into the ruleParam
        //@Date 2012-10-22 yugb :皇室成员不收取一次性费用
        if(acct != null && acct.getSpecialAccount() != null){           
            map.put("SPECIAL_ACCOUNT", acct.getSpecialAccount());
        }
        if (user != null)
        {
            map.put(ConstantDefine.LUA_PRRICINGPLAN_USER_SEGMENT, user.getResSegment());
            map.put(ConstantDefine.LUA_PRICEINGPLAN_USER_REGION_CODE, user.getRegionCode());
        }
        if (offer != null)
        {
            map.put(ConstantDefine.LUA_PRRICINGPLAN_PROD_OVERRIDE_OFFER_ID, offer.getProductOfferingId());
        }
        else
        {
            map.put(ConstantDefine.LUA_PRRICINGPLAN_PROD_OVERRIDE_OFFER_ID, 0);
        }
        return map;
    }
}