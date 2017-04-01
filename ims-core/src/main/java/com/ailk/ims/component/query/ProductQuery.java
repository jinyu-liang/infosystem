package com.ailk.ims.component.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jef.common.wrapper.IntRange;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.ProductOrderComplex;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.ProdStsComponent;
import com.ailk.ims.component.helper.ProdHelper;
import com.ailk.ims.define.ColCodeDefine;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.IMS3hTree;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaCredit;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.ImsFirstOrderInfo;
import com.ailk.openbilling.persistence.imsinner.entity.FreeItem4GUI;
import com.ailk.openbilling.persistence.imsinner.entity.ProdOfferInfo;
import com.ailk.openbilling.persistence.imsinner.entity.SProdSpecChar;
import com.ailk.openbilling.persistence.imsinner.entity.SQryProdOfferInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParam;
import com.ailk.openbilling.persistence.imsintf.entity.ExtendParamList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParam;
import com.ailk.openbilling.persistence.imsintf.entity.SProductParamList;
import com.ailk.openbilling.persistence.pm.entity.PmAllowanceFreeUsage;
import com.ailk.openbilling.persistence.pm.entity.PmAllowanceFreeresDetails;
import com.ailk.openbilling.persistence.pm.entity.PmAllowanceFreeresSegment;
import com.ailk.openbilling.persistence.pm.entity.PmAssetItem;
import com.ailk.openbilling.persistence.pm.entity.PmBudgetItemLimit;
import com.ailk.openbilling.persistence.pm.entity.PmComponentProdofferPrice;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeOfferPrice;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeRulePrice;
import com.ailk.openbilling.persistence.pm.entity.PmEnumLifecycleStatus;
import com.ailk.openbilling.persistence.pm.entity.PmEnumProdOfferTypeDef;
import com.ailk.openbilling.persistence.pm.entity.PmFreeUsageProperty;
import com.ailk.openbilling.persistence.pm.entity.PmOfferingBrandRel;
import com.ailk.openbilling.persistence.pm.entity.PmOneTimeFreeUsage;
import com.ailk.openbilling.persistence.pm.entity.PmPriceEvent;
import com.ailk.openbilling.persistence.pm.entity.PmPriceParamDef;
import com.ailk.openbilling.persistence.pm.entity.PmPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProdBusiLimit;
import com.ailk.openbilling.persistence.pm.entity.PmProdSubNumLimit;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAvailable;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecialZone;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharUse;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecTypeGroups;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;

/**
 * @Description:产品相关的信息查询的方法类
 * @author wangjt
 * @Date 2011-12-21
 * @Date 2012-04-16 lijc3 查询用户生效时间晚的主产品
 * @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
 * @Date 2012-04-17 lijc3查询用户生效时间早的主产品
 * @Date 2012-04-18 lijc3 错误码增加参数输出
 * @Date 2012-04-20 luojb修改逻辑
 * @Date 2012-04-20 wangjt 删除queryMainProdByUserId方法，统一改用queryPrimaryProductByUserId
 * @Date 2012-04-28 wangdw5 增加queryMaxFreeItems方法
 * @Date 2012-05-10 wangyh3 增加根据销售品编号查询品牌编号的方法 queryBrandByOffering
 * @Date 2012-07-06 yangjh 新增queryBudgetCharValue
 * @Date 2012-07-06 yangjh story：50195 budget信息入CoBudgetCharValue表
 * @date 2012-07-10 luojb #50229 删除co_prod_valid, co_prod 增加prod_valid_date，prod_expire_date
 * @date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @date 2012-08-08 tangkun #55132 : 界面根据产品的offering_id刷不出产品来
 * @Date 2012-08-17 wukl 废弃Map Parser的使用
 * @date 2012-08-21 sunpf3 bug48424查询账户可订购的产品。
 * @Date 2012-10-11 zengxr bug #61094 皇室号码不收取一次性费用
 */
public class ProductQuery extends ConfigQuery
{

    /**
     * 传入查询出的同一个产品的多条数据列表，合并为一条数据 合并方式为把validDate最靠前的数据的expireDate设置为最靠后的expireDate即 产品的真实expireDate luojb 2012-3-15
     */
    public CoProd mergeProd(List<CoProd> prodList)
    {
        List<CoProd> mergeProdList = mergeProdList(prodList);
        return CommonUtil.isEmpty(mergeProdList) ? null : mergeProdList.get(0);
    }

    /**
     * 传入查询出的产品列表，合并同一个产品的多条数据， 合并方式为把validDate最靠前的数据的expireDate设置为最靠后的expireDate即 产品的真实expireDate luojb 2012-3-15
     */
    public List<CoProd> mergeProdList(List<CoProd> prodList)
    {
        if (prodList == null || prodList.size() <= 1)
            return prodList;
        List<CoProd> mergeCoProdList = new ArrayList<CoProd>();
        Map<Long, Date> expireDateMap = new HashMap<Long, Date>();
        Map<Long, CoProd> map = new HashMap<Long, CoProd>();
        for (CoProd prod : prodList)
        {
            Long prodId = prod.getProductId();
            Date valid = prod.getValidDate();
            Date expire = prod.getExpireDate();
            if (!map.containsKey(prodId) || map.get(prodId).getValidDate().after(valid))
            {
                map.put(prodId, prod);
            }
            if (!expireDateMap.containsKey(prodId) || expireDateMap.get(prodId).before(expire))
                expireDateMap.put(prodId, expire);
        }
        Iterator<Long> it = map.keySet().iterator();
        while (it.hasNext())
        {
            // @Date 2012-04-20 luojb修改逻辑
            CoProd prod = map.get(it.next());
            if (expireDateMap.containsKey(prod.getProductId()))
            {
                CoProd newProd = (CoProd) IMSUtil.copyDataObject(prod);
                newProd.setExpireDate(expireDateMap.get(prod.getProductId()));
                mergeCoProdList.add(newProd);
            }
            else
            {
                mergeCoProdList.add(prod);
            }
        }

        return mergeCoProdList;
    }

    public CoProd queryProd(long prodId) throws IMSException
    {
        // CoProd dmProd = new CoProd();
        // dmProd.setProductId(prodId);
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId));
        return mergeProd(prodList);
    }

    // 查询产品表，多条记录，没有合并
    public List<CoProd> queryProdList(Long prodId) throws IMSException
    {
        // CoProd dmProd = new CoProd();
        // dmProd.setProductId(prodId);
        return query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId));
    }

    /**
     * @Description: 根据prodId,specCode,objectId查询co_prod_char_value数据
     * @param prodId
     * @param specCode
     * @param objectId
     * @return
     * @throws IMSException
     * @author: tangjl5
     * @Date: 2012-7-27
     */
    public CoProdCharValue queryCharValueByProdIdAndSpecId(Long prodId, int specCode, Long objectId) throws IMSException
    {
        return this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.specCharId, specCode), new DBCondition(CoProdCharValue.Field.objectId, objectId));
    }

    public List<SProdSpecChar> queryProdSpecCharValue(Integer offeringId)
    {
        DBJoinCondition join = new DBJoinCondition(PmProductOffering.class);
        join.innerJoin(PmProductSpecCharUse.class, new DBRelation(PmProductOffering.Field.prodSpecId,
                PmProductSpecCharUse.Field.prodSpecId));
        join.innerJoin(PmProductSpecChar.class, new DBRelation(PmProductSpecCharUse.Field.specCharId,
                PmProductSpecChar.Field.specCharId));
        // join.innerJoin(PmProductSpecCharValue.class ,
        // new DBRelation(PmProductSpecCharUse.Field.charValueId,PmProductSpecCharValue.Field.charValueId));
        List<Map> maps = queryJoin(join, new DBCondition(PmProductOffering.Field.productOfferingId, offeringId));
        if (maps == null)
        {
            return null;
        }
        List<SProdSpecChar> list = new ArrayList<SProdSpecChar>();

        for (Map map : maps)
        {
            SProdSpecChar sSpecChar = new SProdSpecChar();
            PmProductSpecCharUse specCharUse = (PmProductSpecCharUse) map.get(PmProductSpecCharUse.class);
            PmProductSpecChar specChar = (PmProductSpecChar) map.get(PmProductSpecChar.class);
            sSpecChar.setSpec_char_name(specChar.getName());
            sSpecChar.setSpec_char_id(specChar.getSpecCharId());
            List<PmProductSpecCharValue> result = query(PmProductSpecCharValue.class, new DBCondition(
                    PmProductSpecCharValue.Field.specCharId, specCharUse.getSpecCharId()), new DBCondition(
                    PmProductSpecCharValue.Field.charValueId, specCharUse.getCharValueId()));

            if (!CommonUtil.isEmpty(result))
            {
                List<String> enumList = new ArrayList<String>();
                for (PmProductSpecCharValue charValue : result)
                {
                    if (charValue == null)
                    {
                        continue;
                    }
                    // char value is not a enum
                    if (charValue.getIsDefault() == 0)
                    {
                        sSpecChar.setChar_value(charValue.getValue());

                    }
                    if (charValue.getIsDefault() == 1)
                    {
                        enumList.add(charValue.getValue());
                    }
                    sSpecChar.setValue_type(charValue.getValueTypeId());
                }
                sSpecChar.setChar_value_list(enumList);
            }
            list.add(sSpecChar);
        }
        return list;
    }

    public ProductOrderComplex queryProductOrder(long prodId) throws IMSException
    {
        CoProd dmProd = queryProd(prodId);
        if (dmProd == null)
            return null;
        // 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表
        // CoProdInvObj obj = queryInvObj(prodId);
        ProductOrderComplex complex = new ProductOrderComplex();
        complex.setCoProd(dmProd);

        // 查询规格特征
        List<CoProdCharValue> coProdCharValueList = query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                prodId), new DBCondition(CoProdCharValue.Field.objectId, dmProd.getObjectId()));
        complex.setCoProdCharValueList(coProdCharValueList);

        // 查询使用关系
        // CoProdInvObj coProdInvolveObject = querySingle(CoProdInvObj.class, new DBCondition(CoProdInvObj.Field.productId,
        // prodId));
        // complex.setCoProdInvolveObject(obj);

        // 产品账期
        List<CoProdBillingCycle> coProdBillingCycleList = query(CoProdBillingCycle.class, new DBCondition(
                CoProdBillingCycle.Field.productId, prodId),
                new DBCondition(CoProdBillingCycle.Field.objectId, dmProd.getObjectId()));
        complex.setCoProdBillingCycleList(coProdBillingCycleList);

        // 产品二次议价参数
        List<CoProdPriceParam> coProdPriceParamList = query(CoProdPriceParam.class, new DBCondition(
                CoProdPriceParam.Field.productId, prodId), new DBCondition(CoProdPriceParam.Field.objectId, dmProd.getObjectId()));
        complex.setCoProdPriceParamList(coProdPriceParamList);

        return complex;
    }

    public PmProductOffering queryProdOffering(Integer offeringId)
    {
        // PmProductOffering pmProductOffering = new PmProductOffering();
        // pmProductOffering.setProductOfferingId(offeringId);

        return querySingle(PmProductOffering.class, new DBCondition(PmProductOffering.Field.productOfferingId, offeringId));
    }

    public List<CoProdCharValue> queryProdCharValue(Long prodId)
    {
        return query(CoProdCharValue.class, new OrderCondition[] { new OrderCondition(CoProdCharValue.Field.validDate),
                new OrderCondition(CoProdCharValue.Field.expireDate) }, null, new DBCondition(CoProdCharValue.Field.productId,
                prodId));
    }

    /**
     * @Description: 查询亲情号码特征值
     * @author wangyh3
     * @date: 2012-04-28
     */
    public List<CoFnCharValue> queryFnCharValue(Long prodId)
    {
        return query(CoFnCharValue.class, new OrderCondition[] { new OrderCondition(CoFnCharValue.Field.validDate),
                new OrderCondition(CoFnCharValue.Field.expireDate) }, null,
                new DBCondition(CoFnCharValue.Field.productId, prodId));
    }

    public List<CoProdCharValue> queryProdCharValue(Long prodId, Long objectId)
    {
        // CoProdCharValue dm = new CoProdCharValue();
        // dm.setProductId(prodId);
        // dm.setObjectId(objectId);
        return query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.objectId, objectId));
    }

    /**
     * @Description: 查询co_split_char_value表中的measure_id
     * @param prodId
     * @param objectId
     * @return
     * @author: tangjl5
     * @Date: 2012-7-19
     */
    public Integer querySplitMeasureId(Long prodId, Long objectId)
    {
        CoSplitCharValue charVauel = querySingle(CoSplitCharValue.class,
                new DBCondition(CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.objectId,
                        objectId), new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_MEASURE_ID));
        if (charVauel != null)
        {
            return CommonUtil.string2Integer(charVauel.getValue());
        }
        return null;
    }

    /**
     * @Description: 查询reguide的measureId
     * @param prodId
     * @param objectId
     * @return
     * @author: tangjl5
     * @Date: 2012-7-19
     */
    public Integer queryReguideMeasureId(Long prodId, Long objectId)
    {
        CoSplitCharValue charVauel = querySingle(CoSplitCharValue.class,
                new DBCondition(CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.objectId,
                        objectId), new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.RC_MEASURE_ID));
        if (charVauel != null)
        {
            return CommonUtil.string2Integer(charVauel.getValue());
        }
        return null;
    }

    /**
     * @Description: 根据groupId,busiFlag 查询订购的产品
     * @param groupId
     * @param busiFlag
     * @return
     * @author: tangjl5
     * @Date: 2012-4-28
     */
    public List<CoProd> queryProdByGrpIdAndBusiFlag(Long groupId, Integer busiFlag)
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, groupId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_GCA), new DBCondition(CoProd.Field.busiFlag, busiFlag));
        return prodList;
    }

    // modify by ljc not only one
    public List<CoProdPriceParam> queryProdPriceParam(Long prodId)
    {
        // CoProdPriceParam dm = new CoProdPriceParam();
        // dm.setProductId(prodId);
        return query(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, prodId));
    }

    public List<CoProdPriceParam> queryProdPriceParam(Long prodId, Long objectId)
    {
        // CoProdPriceParam dm = new CoProdPriceParam();
        // dm.setProductId(prodId);
        // dm.setObjectId(objectId);
        return query(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, prodId), new DBCondition(
                CoProdPriceParam.Field.objectId, objectId));
    }

    public CoProdPriceParam queryProdPriceParam(Long prodId, Integer paramId)
    {
        // CoProdPriceParam dm = new CoProdPriceParam();
        // dm.setParamId(paramId);
        // dm.setProductId(prodId);
        return querySingle(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.paramId, paramId), new DBCondition(
                CoProdPriceParam.Field.productId, prodId));
    }

    /**
     * 查询账户下对应业务的产品列表
     */
    public List<CoProd> queryProdListByAcctIdAndFlag(Long acctId, int busiFlag)
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, acctId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
                new DBCondition(CoProd.Field.busiFlag, busiFlag));

        return mergeProdList(prodList);
    }

    /**
     * 获取账户下所有产品id列表 luojb 2011-9-26
     * 
     * @2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
     */
    public List<Long> queryProdIdsByAcctId(Long acctId)
    {
        List<Long> prodIds = new ArrayList<Long>();
        List<CoProd> prod_list = this.queryProdListByAcctId(acctId);
        if (prod_list == null)
            return prodIds;
        for (CoProd prod : prod_list)
        {
            prodIds.add(prod.getProductId());
        }
        return prodIds;
    }

    /**
     * 获取用户下所有产品id列表 yangjh 2012-01-12
     * 
     * @2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
     */
    public List<Long> queryProdIdsByUserId(Long userId)
    {
        List<Long> prodIds = new ArrayList<Long>();
        List<CoProd> prod_list = this.queryProdListByUserId(userId);
        if (prod_list == null)
            return prodIds;
        for (CoProd prod : prod_list)
        {
            prodIds.add(prod.getProductId());
        }
        return prodIds;
    }

    /**
     * @Description: 获取预扣产品
     * @param userId
     * @return
     * @author: tangjl5
     * @Date: 2012-2-22
     * @Date：2012-3-9 tangjl5 获取预扣产品和主动停机产品
     */
    public List<CoProd> queryPreDeductAndUserReqProds(Long userId)
    {
        List<CoProd> result = new ArrayList<CoProd>();
        List<CoProd> prods = queryProdListByUserId(userId);

        // 过滤后扣费产品
        if (CommonUtil.isNotEmpty(prods))
        {
            for (CoProd prod : prods)
            {
                PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class)
                        .queryPmCompsiteDeductRuleByOfferId(prod.getProductOfferingId(), prod.getBillingType().intValue());
                if (deductRule != null && deductRule.getDeductFlag() != null
                        && (deductRule.getDeductFlag() == EnumCodeDefine.FEE_PREPAID_DEDUCT))
                {
                    result.add(prod);
                }

                // 后扣的主动停机产品
                int busiFlag = queryBusiflag(prod.getProductOfferingId());
                if (busiFlag == SpecCodeDefine.USER_SUSPEND_REQUEST)
                {
                    result.add(prod);
                }
            }
        }

        return result;
    }

    /**
     * 查询订购群产品接口可以操作的所有群产品 luojb 2012-8-3
     * 
     * @param groupId
     * @return
     */
    public List<CoProd> queryChgGroupProdsByGroupId(Long groupId)
    {
        return this.queryProdListByGroupId(groupId, ColCodeDefine.CHG_GROUP_PROD_BUSIFLAGS);
    }

    /**
     * 根据群id和busiFlag查询群产品 luojb 2011-9-23 ,没传入busiFlag则查询全部
     */
    public List<CoProd> queryProdListByGroupId(Long groupId, int... busiFlag)
    {
        List<DBCondition> conditions = new ArrayList<DBCondition>();
        conditions.add(new DBCondition(CoProd.Field.objectId, groupId));
        conditions.add(new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_GCA));
        if (CommonUtil.isNotEmpty(busiFlag))
        {
            if (busiFlag.length == 1)
                conditions.add(new DBCondition(CoProd.Field.busiFlag, busiFlag[0]));
            else if (busiFlag.length > 1)
                conditions.add(new DBCondition(CoProd.Field.busiFlag, busiFlag, Operator.IN));
        }

        List<CoProd> prodList = query(CoProd.class, conditions.toArray(new DBCondition[conditions.size()]));
        return mergeProdList(prodList);
    }

    /**
     * 查询用户下对应业务的产品列表
     */
    public List<CoProd> queryProdListByUserId(Long userId, Integer busiFlag)
    {
        // CoProd value = new CoProd();
        // value.setObjectId(userId);
        // value.setBusiFlag(busiFlag);
        // value.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                CoProd.Field.busiFlag, busiFlag), new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        return mergeProdList(prodList);
    }

    /**
     * 查询用户对应的busi_flag列表下产品列表(多个busi_flag) ljc 2011-10-31
     */
    public List<CoProd> queryProdListByUserIdAndBusiFlags(Long userId, List<Integer> busiFlags)
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoProd.Field.busiFlag, busiFlags,
                Operator.IN));
        return mergeProdList(prodList);
    }

    /**
     * 查询多个用户下面相应的群个性化产品(busi_flag=130,131,132) ljc 2011-10-31
     * 
     * @2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
     */
    public List<ProductOrderComplex> queryProdListByUserIdsAndBusiFlags(List<Long> userIds, List<Integer> busiFlags)
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userIds, Operator.IN),
                new DBCondition(CoProd.Field.busiFlag, busiFlags, Operator.IN), new DBCondition(CoProd.Field.objectType,
                        EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        if (CommonUtil.isEmpty(prodList))
            return null;
        prodList = mergeProdList(prodList);
        List<ProductOrderComplex> prodComplexList = new ArrayList<ProductOrderComplex>();
        for (CoProd prod : prodList)
        {
            ProductOrderComplex complex = new ProductOrderComplex();
            complex.setCoProd(prod);
            // complex.setCoProdInvolveObject(objMap.get(prod.getProductId()));
            prodComplexList.add(complex);
        }
        return prodComplexList;
    }

    /**
     * @Description: 根据用户ID查找用户下的所有产品列表
     * @update yanchuan 2011-10-06 修改为调用方法queryProdList获取产品列表
     * @param userId
     * @return
     */
    public List<CoProd> queryProdListByUserId(Long userId)
    {
        return queryProdList(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
    }
    
    /**
     * 查询crm受理订购的产品（changeProduct接口订购的）
     * luojb 2012-10-24
     * @param userId
     * @return
     */
    public List<CoProd> queryCrmOrderedProd(Long objectId,Integer objectType)
    {
        List<CoProd> prodList = query(CoProd.class, 
                new DBCondition(CoProd.Field.objectId, objectId), 
                new DBCondition(CoProd.Field.objectType, objectType),
                new DBCondition(CoProd.Field.busiFlag, ColCodeDefine.SPECIAL_BUSI_FLAG,Operator.NOT_IN));
        return mergeProdList(prodList);
    }

    /**
     * @Description: 根据帐户ID查找帐户下的所有产品列表
     * @author yanchuan 2011-10-06
     * @param userId
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List<CoProd> queryProdListByAcctId(Long acctId)
    {
        return queryProdList(acctId, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
    }

    /**
     * 查询用户或帐户订购的产品的公用方法 yanchuan 2011-10-6
     */
    public List<CoProd> queryProdList(Long userIdOrAcctId, int objectType)
    {
        // CoProd value = new CoProd();
        // value.setObjectId(userIdOrAcctId);
        // value.setObjectType(objectType);
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userIdOrAcctId), new DBCondition(
                CoProd.Field.objectType, objectType));
        return mergeProdList(prodList);
    }

    /**
     * 查询用户或帐户订购的某种busi_flag产品的公用方法 yanchuan 2011-10-6
     */
    public List<CoProd> queryProdList(Long userIdOrAcctId, int objectType, int busi_flag)
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userIdOrAcctId), new DBCondition(
                CoProd.Field.busiFlag, busi_flag), new DBCondition(CoProd.Field.objectType, objectType));
        return mergeProdList(prodList);
    }

    /**
     * 查询用户当前为预付费的产品 expire_date为从当前记录起 预付费的截止时间 luojb 2012-3-17
     */
    public List<CoProd> queryPrePaidProdList(Long userId)
    {
        // CoProd value = new CoProd();
        // value.setObjectId(userId);
        // value.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        // value.setBillingType(EnumCodeDefine.PROD_BILLTYPE_PREPAID);
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoProd.Field.billingType,
                EnumCodeDefine.PROD_BILLTYPE_PREPAID));
        return mergeProdList(prodList);
    }

    /**
     * @description 根据账号查找该账号的信用度值
     * @param acctId
     * @Date 2011-08-09
     * @author fangyw
     */
    public Integer queryCaCreditByAcctId(Long acctId)
    {
        CaCredit caCredit = querySingle(CaCredit.class, new DBCondition(CaCredit.Field.acctId, acctId));
        return caCredit.getCreditAmount().intValue();
    }

    /**
     * @Description: 查询价格计划
     * @param offeringId ，销售品id
     * @param acctId ，账户id，如果是用户级产品这个可以不用输入
     * @param userId ，用户编号，如果是账户级产品这个可以不用输入
     * @return
     * @throws IMSException
     */
    public Integer queryPricePlanId(long offeringId, Long acctId, Long userId) throws IMSException
    {
        // 2012-01-14 wuyujie : 新3hbean方案
        CmCustomer cmCustomer = null;
        CaAccount caAccount = null;
        CmResource cmResource = null;
        IMS3hTree tree = context.get3hTree();
        if (userId != null)
        {
            User3hBean hbean = tree.loadUser3hBean(userId);
            cmResource = hbean.getUser();
            caAccount = hbean.getAccount();
            cmCustomer = hbean.getCustomer();
        }
        else
        {
            Acct3hBean hbean = tree.loadAcct3hBean(acctId);
            caAccount = hbean.getAccount();
            cmCustomer = hbean.getCustomer();
        }

        Integer mainOfferId = null;
        if (cmResource != null)
        {
            mainOfferId = queryMainOfferIdByUserId(cmResource.getResourceId());
        }

        return queryPricePlanId(offeringId, cmCustomer, caAccount, cmResource, null, null, null, mainOfferId, null, null);
    }

    /**
     * 增加价格扩展参数 最底层参数 offeringId 销售品id cust 客户 account 账户 user 用户 param 价格扩展参数 overrideOfferId <BR>
     * 有override关系的销售品id values 产品特征值
     */
    // caohm5 2012-02-17 add Object list用来存储组合产品下的销售品列表
    // caohm5 2012-02-22 add Long parentOffringId 用来存储子销售的父销售品ID
    public Integer queryPricePlanId(long offeringId, CmCustomer cust, CaAccount account, CmResource user,
            List<CoProdPriceParam> param, Integer overrideOfferId, List<CoProdCharValue> values, Integer mainOfferId,
            Object list, Long parentOffringId) throws IMSException
    {
        // PmProductOffering prodOffer =
        // this.queryOfferingByOfferId(offeringId);
        CmIndividual individual = null;
        if (cust != null)
        {// 群产品是没有客户的 luojb 2011-11-08
            context.getSingleCache(CmIndividual.class, new CacheCondition(CmIndividual.Field.partyId, cust.getCustId()),
                    new CacheCondition(CmIndividual.Field.soNbr, cust.getSoNbr()));
            if (individual == null)
            {
                individual = context.getComponent(PartyComponent.class).queryInvidualByCustId(cust.getCustId());
            }
        }

        Map<Class, Object> inputMap = new HashMap<Class, Object>();
        inputMap.put(CmCustomer.class, cust);
        inputMap.put(CaAccount.class, account);
        //2012-10-11 zengxr bug #61094 皇室号码不收取一次性费用
        //@Date 2012-10-22 yugb :#61088 皇室成员不收取一次性费用(注释)
        //input special account into the ruleParam
        //    	if(account != null && account.getAcctId() != null){
        //    		CaAccountExt ext = context.getComponent(AccountQuery.class).queryAcctAttr(account.getAcctId());
        //    		if(ext != null)
        //    			inputMap.put(CaAccountExt.class, ext);
        //    	}
        // when an account order an promotion, maybe there are no subscriber.
        if (user != null)
        {
            inputMap.put(CmResource.class, user);
        }
        // 增加价格扩展参数
        if (!CommonUtil.isEmpty(param))
            inputMap.put(CoProdPriceParam.class, param);
        if (!CommonUtil.isEmpty(values))
            inputMap.put(CoProdCharValue.class, values);
        // inputMap.put(PmProductOffering.class, prodOffer);
        // 只加入override对方的销售品id作为值传入policy
        if (overrideOfferId != null)
        {
            inputMap.put(PmProductOffering.class, super.queryPmProductOfferingByOfferId(overrideOfferId));
        }
        if (individual != null)
        {
            inputMap.put(CmIndividual.class, individual);
        }
        Map map = ProdHelper.buildPricingPlanLuaMap(inputMap);
        if (mainOfferId != null)
        {
            map.put(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID, mainOfferId);
        }
        if (list != null)
        {
            map.put(ConstantDefine.PACKET_PRODUCT_OFFERING_ID, list);
        }
        if (parentOffringId != null)
        {
            map.put(ConstantDefine.SON_PRODUCT_IN_PARCKET, parentOffringId);
        }
        // return queryPricePlanId(offeringId, map);
        return context.getComponent(CacheQuery.class).queryPricePlanId(offeringId, map);
    }

    /**
     * 根据用户id查询主产品id
     * 
     * @Author lijingcheng
     */
    public Integer queryMainOfferIdByUserId(Long userId)
    {
        CoProd mainProd = this.queryPrimaryProductByUserId(userId);
        if (mainProd != null)
        {
            return mainProd.getProductOfferingId();
        }
        else
        {
            // throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_HAVE_NO_MAIN_PROM);
            return null;
        }
    }

    /*
     * 没有价格扩展参数 供upsell调用
     */
    public Integer queryPricePlanId(long offeringId, CmCustomer cust, CaAccount account, CmResource user,
            List<CoProdPriceParam> values) throws IMSException
    {
        Integer mainOfferId = null;
        if (user != null)
        {
            mainOfferId = queryMainOfferIdByUserId(user.getResourceId());
        }
        return queryPricePlanId(offeringId, cust, account, user, values, null, null, mainOfferId, null, null);
    }

    // 首次订购
    public void doFirstOrder(long offeringId, Map valueMap)
    {
        // 2012-01-14 wuyujie : 新3hbean方案
        if (context.getMain3hBean() != null)
        {
            IMS3hBean bean = context.getMain3hBean();
            List<DBCondition> cond = new ArrayList<DBCondition>();
            // ImsFirstOrderInfo info = new ImsFirstOrderInfo();
            // info.setOfferId((int) offeringId);
            cond.add(new DBCondition(ImsFirstOrderInfo.Field.offerId, offeringId));
            Long objectId = null;
            Integer objectType = null;
            if (bean.isUser3hBean())
            {
                objectId = ((User3hBean) bean).getUserId();
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
            }
            else
            {
                objectId = ((Acct3hBean) bean).getAcctId();
                objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
            }
            // info.setObjectId(objectId);
            cond.add(new DBCondition(ImsFirstOrderInfo.Field.objectId, objectId));
            // info.setObjectType(objectType);
            cond.add(new DBCondition(ImsFirstOrderInfo.Field.objectType, objectType));
            ImsFirstOrderInfo info = this.querySingle(ImsFirstOrderInfo.class, cond.toArray(new DBCondition[cond.size()]));
            cond.add(new DBCondition(ImsFirstOrderInfo.Field.objectId, objectId));
            if (info == null)
            {// 空的 首次订购
                valueMap.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_FIRST_ORDER, 1);
                // 插入数据
                info = new ImsFirstOrderInfo();
                info.setBusiFlag(queryBusiflag((int) offeringId));
                info.setFirstOrderTime(context.getRequestDate());
                // @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
                info.setId(DBUtil.getSequence());
                info.setObjectId(objectId);
                info.setObjectType(objectType);
                info.setSoNbr(context.getSoNbr());
                info.setOfferId((int) offeringId);
                this.insert(info);
            }
            else
            {
                valueMap.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_FIRST_ORDER, 0);
            }
        }
    }

    /**
     * @throws IMSException query products by user id and (offering id or product id) busi flag can be null
     */
    public List<CoProd> queryProds(Long userId, Long offerId, Long prodId, Integer busiFlag) throws IMSException
    {
        if (userId == null || ((offerId == null || offerId.intValue() == 0) && (prodId == null || prodId.intValue() == 0)))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_QUERY_COND_NOT_VALID);
        }

        List<CoProd> prodList = this.queryProdList(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        List<CoProd> resultList = new ArrayList<CoProd>();
        try
        {
            if (busiFlag != null)
            {
                if (prodId != null)
                {
                    resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.busiFlag, busiFlag),
                            new CacheCondition(CoProd.Field.productId, prodId));
                }
                else
                {
                    resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.busiFlag, busiFlag),
                            new CacheCondition(CoProd.Field.productOfferingId, offerId.intValue()));
                }
            }
            else
            {
                if (prodId != null)
                {
                    resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.productId, prodId));
                }
                else
                {
                    resultList = IMSUtil.matchDataObject(prodList,
                            new CacheCondition(CoProd.Field.productOfferingId, offerId.intValue()));
                }
            }

        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }

        return resultList.size() == 0 ? null : resultList;
    }

    /**
     * @Description: 根据销售品ID和busi_Flag查询产品列表
     * @throws IMSException 2011-08-09 ljc
     */
    public List<CoProd> queryProdsByOfferAndFlag(Long offerId, Integer busiFlag) throws IMSException
    {
        // CoProd prod = new CoProd();
        List<DBCondition> cond = new ArrayList<DBCondition>();
        // 2011-08-22 zengxr No busi flag means query all products
        if (busiFlag != null && busiFlag.intValue() != 0)
        {
            // prod.setBusiFlag(busiFlag);
            cond.add(new DBCondition(CoProd.Field.busiFlag, busiFlag));
        }
        // prod.setProductOfferingId((int) offerId.longValue());
        cond.add(new DBCondition(CoProd.Field.productOfferingId, offerId));
        List<CoProd> prodList = query(CoProd.class, cond.toArray(new DBCondition[cond.size()]));
        return mergeProdList(prodList);
    }

    /**
     * 根据销售品ID查询主产品列表 wukl 2012-1-9
     * 
     * @param offerId
     * @return
     * @throws IMSException
     */
    public List<CoProd> queryPrimaryProdsByOffer(Long offerId) throws IMSException
    {
        // CoProd prod = new CoProd();
        // prod.setProductOfferingId((int) offerId.longValue());
        // prod.setIsMain(EnumCodeDefine.PRODUCT_MAIN);
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.productOfferingId, offerId), new DBCondition(
                CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN));
        return mergeProdList(prodList);
    }

    /**
     * 2011-08-10 ljc 根据userId查询黑白名单产品 从userComponent移入
     */
    public CoProd queryCoProdByUserId(long userId) throws IMSException
    {
        List<CoProd> prodList = this.queryProdListByUserId(userId, SpecCodeDefine.MCS_NUMBER);
        if (!CommonUtil.isEmpty(prodList))
        {
            return prodList.get(0);
        }
        return null;
    }

    /**
     * @Description: 根据prodId,specCode 查询CoProdCharValue List
     */
    public List<CoProdCharValue> queryCharValue(Long prodId, int specCode) throws IMSException
    {
        // CoProdCharValue charValue = new CoProdCharValue();
        // charValue.setProductId(prodId);
        // charValue.setSpecCharId(specCode);
        return this.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.specCharId, specCode));
    }

    /**
     * @Description: 查询亲情号码特征值
     * @author wangyh3
     * @date 2012-04-28
     */
    public List<CoFnCharValue> queryFnCharValue(Long prodId, int specCode) throws IMSException
    {
        return this.query(CoFnCharValue.class, new DBCondition(CoFnCharValue.Field.productId, prodId), new DBCondition(
                CoFnCharValue.Field.specCharId, Integer.valueOf(specCode)));
    }

    public List<CoProdCharValue> queryCharValueAndObjectId(Long prodId, int specCode, Long objectId) throws IMSException
    {
        // CoProdCharValue charValue = new CoProdCharValue();
        // charValue.setProductId(prodId);
        // charValue.setSpecCharId(specCode);
        // charValue.setObjectId(obejctId);
        return this.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.specCharId, specCode), new DBCondition(CoProdCharValue.Field.objectId, objectId));
    }

    /**
     * @Description: 根据prodId,specCode groupId查询CoProdCharValue
     */
    public CoProdCharValue queryCharValue(Long prodId, int specCode, Long groupId) throws IMSException
    {
        // CoProdCharValue charValue = new CoProdCharValue();
        // charValue.setProductId(prodId);
        // charValue.setSpecCharId(specCode);
        // charValue.setGroupId(groupId);
        return this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.specCharId, specCode), new DBCondition(CoProdCharValue.Field.groupId, groupId));
    }

    /**
     * @Description: 根据prodId,specCode groupId查询CoBudgetCharValue
     */
    public CoBudgetCharValue queryBudgetCharValue(Long prodId, int specCode, Long groupId) throws IMSException
    {
        // coprodcharvalue-》cobudgetcharvalue
        return this.querySingle(CoBudgetCharValue.class, new DBCondition(CoBudgetCharValue.Field.productId, prodId),
                new DBCondition(CoBudgetCharValue.Field.specCharId, specCode), new DBCondition(CoBudgetCharValue.Field.groupId,
                        groupId));
    }

    /**
     * @Description: 根据prodId,specCode groupId,objectId查询CoProdCharValue
     */
    public CoProdCharValue queryCharValue(Long prodId, int specCode, Long groupId, Long objectId) throws IMSException
    {
        // CoProdCharValue charValue = new CoProdCharValue();
        // charValue.setProductId(prodId);
        // charValue.setSpecCharId(specCode);
        // charValue.setObjectId(objectId);
        // charValue.setGroupId(groupId);
        return this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.specCharId, specCode), new DBCondition(CoProdCharValue.Field.objectId, objectId),
                new DBCondition(CoProdCharValue.Field.groupId, groupId));
    }

    /**
     * @Description: 根据prodId,specCode,value查询CoProdCharValue List
     */
    public List<CoProdCharValue> queryCharValue(Long prodId, int specCode, String value) throws IMSException
    {
        // CoProdCharValue charValue = new CoProdCharValue();
        // charValue.setProductId(prodId);
        // charValue.setSpecCharId(specCode);
        // charValue.setValue(value);
        return this.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.specCharId, specCode), new DBCondition(CoProdCharValue.Field.value, value));
    }

    /**
     * @Description: 根据prodId,specCode,value,objectId查询CoProdCharValue List
     */
    public List<CoProdCharValue> queryCharValue(Long prodId, int specCode, String value, Long objectId) throws IMSException
    {
        // CoProdCharValue charValue = new CoProdCharValue();
        // charValue.setProductId(prodId);
        // charValue.setSpecCharId(specCode);
        // charValue.setValue(value);
        // charValue.setObjectId(objectId);
        return this.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
                CoProdCharValue.Field.specCharId, specCode), new DBCondition(CoProdCharValue.Field.value, value),
                new DBCondition(CoProdCharValue.Field.objectId, objectId));
    }

    /**
     * @Description: 根据prodId,groupId查询CoProdCharValue List
     * @throws IMSException 2011-08-11 ljc
     */
    public List<CoProdCharValue> queryCharValue(Long prodId, Long groupId) throws IMSException
    {
        List<DBCondition> cond = new ArrayList<DBCondition>();
        // CoProdCharValue charValue = new CoProdCharValue();
        cond.add(new DBCondition(CoProdCharValue.Field.productId, prodId));
        // charValue.setProductId(prodId);
        // 2011-11-21 hunan add : if groupid = null
        if (groupId != null)
        {
            // charValue.setGroupId(groupId);
            cond.add(new DBCondition(CoProdCharValue.Field.groupId, groupId));
        }

        return this.query(CoProdCharValue.class, cond.toArray(new DBCondition[cond.size()]));
    }

    /**
     * @Description: 根据prodId,groupId,objectId查询CoProdCharValue List
     * @throws IMSException 2011-08-11 ljc
     */
    public List<CoProdCharValue> queryCharValue(Long prodId, Long groupId, Long objectId) throws IMSException
    {
        // CoProdCharValue charValue = new CoProdCharValue();
        List<DBCondition> cond = new ArrayList<DBCondition>();
        // charValue.setProductId(prodId);
        cond.add(new DBCondition(CoProdCharValue.Field.productId, prodId));
        // charValue.setObjectId(objectId);
        cond.add(new DBCondition(CoProdCharValue.Field.objectId, objectId));
        // 2011-11-21 hunan add : if groupid = null
        if (groupId != null)
        {
            // charValue.setGroupId(groupId);
            cond.add(new DBCondition(CoProdCharValue.Field.groupId, groupId));
        }

        return this.query(CoProdCharValue.class, cond.toArray(new DBCondition[cond.size()]));
    }

    /**
     * 根据群ID查询网外号码产品
     */
    public CoProd queryOutnetNbrByGroupId(long groupId)
    {
        List<CoProd> prodList = queryCoProdByObjId(groupId, EnumCodeDefine.PROD_OBJECTTYPE_GCA, SpecCodeDefine.OUTNET_PHONE);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        return prodList.get(0);
    }

    /**
     * 根据使用对象ID，使用对象类型，产品类型busiflag查询产品列表
     */
    public List<CoProd> queryCoProdByObjId(long objId, int objType, int busiflag) throws IMSException
    {
        // CoProd prod = new CoProd();
        // prod.setObjectId(objId);
        // prod.setObjectType(objType);
        // prod.setBusiFlag(busiflag);
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, objId), new DBCondition(
                CoProd.Field.objectType, objType), new DBCondition(CoProd.Field.busiFlag, busiflag));
        return mergeProdList(prodList);
    }

    /**
     * 查询产品订购状态
     * 
     * @author yangyang
     * @date 2011-08-15
     */
    public List<Map> queryProdStatusBySubscriber(Long prodId, Integer offeringId, Long userId)
    {
        List<CoProd> prodList = this.queryProdList(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        List<Map> mapList = new ArrayList<Map>();
        List<CoProd> resultList = null;

        try
        {
            if (CommonUtil.isValid(prodId) && CommonUtil.isValid(offeringId))
            {
                resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.productId, prodId),
                        new CacheCondition(CoProd.Field.productOfferingId, offeringId));
            }
            else if (!CommonUtil.isValid(prodId) && !CommonUtil.isValid(offeringId))
            {
                resultList = prodList;
            }
            else if (CommonUtil.isValid(prodId) && !CommonUtil.isValid(offeringId))
            {
                resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.productId, prodId));
            }
            else if (!CommonUtil.isValid(prodId) && CommonUtil.isValid(offeringId))
            {
                resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.productOfferingId, offeringId));
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            IMSUtil.throwBusiException(e);
        }
        if (CommonUtil.isEmpty(resultList))
        {
            return null;
        }
        for (CoProd dmProd : resultList)
        {
            Map map = new HashMap();
            map.put(CoProd.class, dmProd);
            mapList.add(map);
        }
        return mapList;
    }

    public List<CoProdCharValue> queryInterGroupProds(Long groupId, Integer specId) throws IMSException
    {
        // , SpecCodeDefine.INTER_GROUP_AUTH
        List<CoProd> prodList = this.queryProdListByGroupId(groupId, SpecCodeDefine.INTER_GROUP_PROMOTION);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        List<Long> prodIds = new ArrayList<Long>();
        for (CoProd dmProd : prodList)
        {
            prodIds.add(dmProd.getProductId());
        }
        List<CoProdCharValue> valueList = this.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                prodIds, Operator.IN), new DBCondition(CoProdCharValue.Field.objectId, groupId), new DBCondition(
                CoProdCharValue.Field.specCharId, specId));
        if (CommonUtil.isEmpty(valueList))
        {
            return null;
        }

        return valueList;
    }

    /**
     * 查询群间产品
     * 
     * @param groupId
     * @return 返回产品id与对端群号映射表Map<prodId,interGroupId>
     * @throws IMSException
     * @author luojb
     */
    public Map<Long, Long> queryInterGroupIdMap(Long groupId) throws IMSException
    {

        List<CoProdCharValue> charValueList = queryInterGroupProds(groupId, SpecCodeDefine.INTER_GROUP_ID);
        if (CommonUtil.isEmpty(charValueList))
        {
            return null;
        }
        Map<Long, Long> interGroupProdId = new HashMap<Long, Long>();
        for (CoProdCharValue charValue : charValueList)
        {
            interGroupProdId.put(charValue.getProductId(), CommonUtil.string2Long(charValue.getValue()));
        }

        return interGroupProdId;
    }

    /**
     * 通过产品ID，查询价格规则ID
     */
    public Integer queryPriceRuleIdByProdId(Long prodId)
    {
        CoProd coProd = this.queryProd(prodId);
        if (coProd == null)
        {
            return null;
        }
        Integer pricingPlanId = coProd.getPricingPlanId();
        if (!CommonUtil.isValid(pricingPlanId))
        {
            return null;
        }
        PmCompositeRulePrice pmCompositeRulePrice = super.querySingle(PmCompositeRulePrice.class, new DBCondition(
                PmCompositeRulePrice.Field.pricingPlanId, pricingPlanId));
        if (pmCompositeRulePrice == null)
        {
            return null;
        }
        return pmCompositeRulePrice.getPriceRuleId();
    }

    /**
     * @Description:查询群下订购的所有产品
     * @author hunan
     * @Date 2011-09-08
     */
    public SProductOrderList queryGroupProdOrdersByGroupId(Long groupId)
    {
        List<SProductOrder> orderList = new ArrayList<SProductOrder>();
        if (groupId != null)
        {// 账户上的共享促销(sharing promotion)
         // 查询条件2-具体查询条件
            List<CoProd> prodList = this.queryProdList(groupId, EnumCodeDefine.PROD_OBJECTTYPE_GCA);
            // 结果
            if (CommonUtil.isNotEmpty(prodList))
            {
                for (CoProd coProd : prodList)
                {
                    // Map itemMap = orderInfoResult.get(i);
                    // coProd = (CoProd) itemMap.get(CoProd.class);

                    SProductOrder productOrder = sProductOrderTransform(coProd);
                    if (productOrder != null)
                    {
                        productOrder.setAcct_id(groupId);
                        orderList.add(productOrder);
                    }
                }
            }
        }
        SProductOrderList resultList = new SProductOrderList();
        resultList.setItem(orderList.toArray(new SProductOrder[orderList.size()]));
        return resultList;
    }

    /**
     * 将内部实体CoProd转换为接口实体SProductOrder返回给外部接口
     */
    public SProductOrder sProductOrderTransform(CoProd coProd)
    {
        SProductOrder productOrder = new SProductOrder();
        if (coProd == null)
        {
            return null;
        }
        // SimpleDateFormat dateFormat = new
        // SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        productOrder.setProduct_id(coProd.getProductId());// 产品订购实例编号
        productOrder.setOffer_id(CommonUtil.IntegerToLong(coProd.getProductOfferingId()));// 产品编号
        // hunan 增加如果是主产品 product_type= 2；
        if (coProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
        {
            productOrder.setProduct_type(EnumCodeDefine.PROD_TYPE_MAIN);
        }
        else
        {
            productOrder.setProduct_type(coProd.getProdTypeId());

        }
        productOrder.setBilling_type(CommonUtil.IntegerToShort(coProd.getBillingType()));
        productOrder.setProd_state(CommonUtil.IntegerToShort(coProd.getLifecycleStatusId()));
        Date createDate = coProd.getCreateDate();
        if (createDate != null)
            productOrder.setCreate_date(DateUtil.formatDate(createDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        Date validDate = coProd.getValidDate();
        if (validDate != null)
            productOrder.setValid_date(DateUtil.formatDate(validDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        Date expireDate = coProd.getExpireDate();
        if (expireDate != null)
            productOrder.setExpire_date(DateUtil.formatDate(expireDate, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        List<CoProdCharValue> charValues = this.queryCharValue(coProd.getProductId(), null, coProd.getObjectId());
        // _product_param_list CoProdCharValue表中
        SProductParamList paramList = new SProductParamList();
        List<SProductParam> params = new ArrayList<SProductParam>();
        for (CoProdCharValue val : charValues)
        {
            if (val == null)
            {
                continue;
            }
            SProductParam param = new SProductParam();
            param.setParam_id(val.getSpecCharId());
            param.setParam_value(String.valueOf(val.getValue()));
            params.add(param);
        }
        paramList.setSProductParamList_Item(params.toArray(new SProductParam[params.size()]));
        productOrder.setParam_list(paramList);

        // 2011-12-07 如果二次议价存在，也要返回
        List<CoProdPriceParam> priceParams = this.queryProdPriceParam(coProd.getProductId(), coProd.getObjectId());
        List<ExtendParam> extendParams = new ArrayList<ExtendParam>();
        if (priceParams != null)
        {
            for (CoProdPriceParam param : priceParams)
            {
                if (param == null)
                {
                    continue;
                }
                ExtendParam extendParam = new ExtendParam();
                extendParam.setParam_name(String.valueOf(param.getParamId()));
                extendParam.setParam_value(param.getParamValue());
                extendParams.add(extendParam);
            }
            ExtendParamList extendParamList = new ExtendParamList();
            extendParamList.setExtendParamList_Item(extendParams.toArray(new ExtendParam[extendParams.size()]));
            productOrder.setReguid_price_param(extendParamList);
        }

        if (CommonUtil.isValid(coProd.getPricingPlanId()))
        {
            PmPricingPlan plan = queryPmPricingPlanByPlanId(coProd.getPricingPlanId());
            if (plan != null)
            {
                productOrder.setPrice_description(plan.getPricingPlanDesc());
            }
        }

        return productOrder;
    }

    /**
     * @Description: 根据价格计划Id查询定价计划定义
     * @author: tangjl5
     * @Date: 2012-1-13
     */
    public PmPricingPlan queryPmPricingPlanByPlanId(Integer pricePlanId)
    {
        // PmPricingPlan plan = new PmPricingPlan();
        // plan.setPricingPlanId(pricePlanId);
        return querySingle(PmPricingPlan.class, new DBCondition(PmPricingPlan.Field.pricingPlanId, pricePlanId));
    }

    /**
     * @Description: 根据产品实例id查询销售品
     * @author wuyj
     */
    public PmProductOffering queryOfferingByProdId(Long prodId)
    {
        CoProd dmProd = this.queryProd(prodId);
        if (dmProd == null)
        {
            return null;
        }
        return this.queryOfferingByOfferId(dmProd.getProductOfferingId().longValue());
    }

    /**
     * @Description: 根据产品实例id查询销售品 名称
     * @author wuyj
     */
    public String queryOfferingNameByProdId(Long prodId)
    {
        PmProductOffering offering = queryOfferingByProdId(prodId);

        return offering == null ? null : offering.getName();
    }

    /**
     * 根据soNbr查询产品id ljc 2011-10-10
     */
    public List<CoProd> queryProdListBySoNbr(Long soNbr)
    {
        // CoProd dmProd = new CoProd();
        // dmProd.setSoNbr(soNbr);
        List<CoProd> prodList = this.query(CoProd.class, new DBCondition(CoProd.Field.soNbr, soNbr));
        return mergeProdList(prodList);
    }

    /**
     * 查询用户当前生效的主产品
     * 
     * @author yangyang
     * @date 2011-10-25
     * @date 2012-08-10 luojb 代码评审修改：查询当前生效的这条主产品
     */
    public CoProd queryPrimaryProductByUserId(Long userId)
    {
        // List<CoProd> prodList = query(CoProd.class,
        // new DBCondition(CoProd.Field.objectId, userId),
        // new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
        // new DBCondition(CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN));
        // CoProd value = mergeProd(prodList);
        // return value;
        return queryFirstPrimaryProductByUserId(userId);
    }

    /**
     * @Description 查询用户生效时间晚的主产品
     * @Author lijingcheng
     * @param userId
     * @return
     * @Date 2012-04-16
     */
    public CoProd queryLastPrimaryProductByUserId(Long userId)
    {
        // CoProd value = new CoProd();
        // value.setObjectId(userId);
        // value.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        // value.setIsMain(EnumCodeDefine.PRODUCT_MAIN);
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoProd.Field.isMain,
                EnumCodeDefine.PRODUCT_MAIN));
        prodList = mergeProdList(prodList);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        else
        {
            CoProd temp = null;
            for (CoProd dmProd : prodList)
            {
                if (temp == null)
                {
                    temp = dmProd;
                }
                else
                {
                    if (temp.getValidDate().before(dmProd.getValidDate()))
                    {
                        temp = dmProd;
                    }
                }
            }
            return temp;
        }
    }

    /**
     * @Description 查询用户生效时间早的主产品
     * @Author lijingcheng
     * @Date 2012-04-16
     */
    public CoProd queryFirstPrimaryProductByUserId(Long userId)
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, userId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoProd.Field.isMain,
                EnumCodeDefine.PRODUCT_MAIN));
        prodList = mergeProdList(prodList);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        else
        {
            CoProd temp = null;
            for (CoProd dmProd : prodList)
            {
                if (temp == null)
                {
                    temp = dmProd;
                }
                else
                {
                    if (temp.getProdValidDate().after(dmProd.getProdValidDate()))
                    {
                        temp = dmProd;
                    }
                }
            }
            return temp;
        }
    }

    /**
     * 查询销售品可进行分页处理 liuyang 2011-10-25 特殊说明 当根据设备类型查询销售品时 根据预付费/后付费查询时需要把通用的设备类型也要查出来 billtype = -1 代表通用类型
     */
    @SuppressWarnings("deprecation")
    public List<ProdOfferInfo> queryProdOffers(SQryProdOfferInfoReq req)
    {
        List<ProdOfferInfo> prodOffers = new ArrayList<ProdOfferInfo>();
        StringBuffer sql = new StringBuffer();
        // 查询的列
        sql.append("select  t1.product_offering_id productOfferingId,t1.name productOfferingName,t1.is_main isMain,t1.description description, ");
        sql.append("t2.offer_type_id offerTypeId,t2.name offerTypeName,");
        sql.append("t3.lifecycle_status_id lifecycleStatusId,t3.name lifecycleStatusName,");
        // @date 2012-08-08 tangkun #55132 : 界面根据产品的offering_id刷不出产品来
        // if (req.getOrderType() != 1)
        // {
        // sql.append("t4.brand_id brandId,t4.name brandName,");
        // }
        sql.append("t5.priceCount priceNum");
        // 连接的表
        sql.append(" from ");
        sql.append(DBUtil.buildTableName(PmProductOffering.class));
        sql.append(" t1 left join ");
        sql.append(DBUtil.buildTableName(PmEnumProdOfferTypeDef.class));
        sql.append(" t2 on t1.offer_type_id = t2.offer_type_id left join ");
        sql.append(DBUtil.buildTableName(PmEnumLifecycleStatus.class));
        sql.append(" t3 on t1.lifecycle_status_id = t3.lifecycle_status_id left join ");

        sql.append(" (select product_offering_id,count(*) priceCount from ");
        sql.append(DBUtil.buildTableName(PmProductPricingPlan.class));
        sql.append(" group by product_offering_id) t5 on t1.product_offering_id = t5.product_offering_id ");
        sql.append(" left join ");
        sql.append(DBUtil.buildTableName(PmProductSpecTypeGroups.class));
        sql.append(" t6 on t1.prod_spec_id = t6.prod_spec_id left join ");
        sql.append(DBUtil.buildTableName(PmProductOfferLifecycle.class));
        sql.append(" t8 on t1.product_offering_id = t8.product_offering_id ");
        // @date 2012-08-08 tangkun #55132 : 界面根据产品的offering_id刷不出产品来
        // if (req.getOrderType() != 1)
        // {
        // sql.append(" left join ");
        // sql.append(DBUtil.buildTableName(PmBrand.class));
        // sql.append(" t4 on t1.brand_id = t4.brand_id ");
        // }

        // 添加查询条件
        if (req.getRegionCode() != null)
        {
            sql.append(" right join (");
            sql.append(" select product_offering_id,region_code from ");
            sql.append(DBUtil.buildTableName(PmProductOfferAvailable.class));
            sql.append(" where region_code = ");
            sql.append(req.getRegionCode());
            sql.append(" and expire_date > sysdate) t6 on t1.product_offering_id = t6.product_offering_id ");
        }
        if (req.getBillType() != null && req.getOrderType() == 2)// 不同付费模式用户所能订购的销售品
        {
            sql.append(" right join (");
            sql.append(" select * from ");
            sql.append(DBUtil.buildTableName(PmProductOfferAttribute.class));
            sql.append(" where billing_type in ( ");
            sql.append(req.getBillType());
            sql.append(" ,-1)) t7 on t1.product_offering_id = t7.product_offering_id ");

        }
        sql.append(" where 1=1 ");
        if (req.getCodeName() != null)
        {
            int prodId = 0;
            try
            {
                prodId = Integer.parseInt(req.getCodeName());
                sql.append(" and (t1.product_offering_id like '%");
                sql.append(prodId);
                sql.append("%'");
                sql.append(" or t1.name like '%");
                sql.append(prodId);
                sql.append("%')");
            }
            catch (Exception e)
            {
                sql.append(" and t1.name like '%");
                sql.append(req.getCodeName());
                sql.append("%'");
            }
        }
        if (req.getOfferTypeId() != null)
        {
            sql.append(" and t2.offer_type_id = ");
            sql.append(req.getOfferTypeId());
        }
        if (req.getSts() != null)
        {
            sql.append(" and t3.lifecycle_status_id in (");
            sql.append(req.getSts());
            sql.append(")");
        }
        // if (req.getBrandId() != null && req.getOrderType() != 1)
        // {
        // sql.append(" and t4.brand_id = ");
        // sql.append(req.getBrandId());
        // }
        if (req.getIsMain() != null)
        {
            sql.append(" and t1.is_main = ");
            sql.append(req.getIsMain());
        }
        if (req.getOrderType() == 1)// 1代表查询群产品
        {
            // sql.append(" and t6.spec_type_id in(135,136,137,103,114)");// 群产品 ,103
            sql.append(" and t1.offer_class in ('001','011','111','101') ");
            // 属于特殊产品
            // 也可以订购
        }
        else if (req.getOrderType() == 3)// 用户级
        {
            // sql.append(" and t6.spec_type_id not in(135,136,137,103,104)");
            sql.append(" and t1.offer_class in ('100','110','111','101') ");
        }
        else if (req.getOrderType() == 2)// 2 代表查询账户级
        {
            // sql.append(" and t6.spec_type_id not in(103,104)");// 账户级
            sql.append(" and t1.offer_class in ('010','011','111','110') ");

        }
        sql.append(" and t1.expire_date > sysdate ");
        // @date 2012-08-08 tangkun #55132 : 界面根据产品的offering_id刷不出产品来
        // if (req.getOrderType() != 1)
        // {
        // sql.append(" and  t4.expire_date > sysdate ");
        // }
        sql.append(" order by t1.product_offering_id desc");
        IntRange range = new IntRange(req.getStart() + 1, req.getStart() + req.getLimit());
        try
        {
            prodOffers = DBUtil.getDBClient().selectBySql(sql.toString(), ProdOfferInfo.class, range);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }

        return prodOffers;
    }

    /**
     * 查询销售品数量 liuyang 2011-10-26
     */
    public Integer queryProdOffersCount(SQryProdOfferInfoReq req)
    {
        StringBuffer sql = new StringBuffer();

        sql.append("select count(*)");
        // 连接的表
        sql.append(" from ");
        sql.append(DBUtil.buildTableName(PmProductOffering.class));
        sql.append(" t1 left join ");
        sql.append(DBUtil.buildTableName(PmEnumProdOfferTypeDef.class));
        sql.append(" t2 on t1.offer_type_id = t2.offer_type_id left join ");
        sql.append(DBUtil.buildTableName(PmEnumLifecycleStatus.class));
        sql.append(" t3 on t1.lifecycle_status_id = t3.lifecycle_status_id left join ");

        sql.append("  (select product_offering_id,count(*) priceCount from ");
        sql.append(DBUtil.buildTableName(PmProductPricingPlan.class));
        sql.append(" group by product_offering_id) t5 on t1.product_offering_id = t5.product_offering_id ");
        sql.append(" left join ");
        sql.append(DBUtil.buildTableName(PmProductSpecTypeGroups.class));
        sql.append(" t6 on t1.prod_spec_id = t6.prod_spec_id left join ");
        sql.append(DBUtil.buildTableName(PmProductOfferLifecycle.class));
        sql.append(" t8 on t1.product_offering_id = t8.product_offering_id ");

        // @date 2012-08-08 tangkun #55132 : 界面根据产品的offering_id刷不出产品来
        // if (req.getOrderType() != 1)
        // {
        // sql.append(" left join ");
        // sql.append(DBUtil.buildTableName(PmBrand.class));
        // sql.append(" t4 on t1.brand_id = t4.brand_id ");
        // }
        // 添加查询条件
        if (req.getRegionCode() != null)
        {
            sql.append(" right join (");
            sql.append(" select product_offering_id,region_code from ");
            sql.append(DBUtil.buildTableName(PmProductOfferAvailable.class));
            sql.append(" where region_code = ");
            sql.append(req.getRegionCode());
            sql.append(") t6 on t1.product_offering_id = t6.product_offering_id ");
        }
        if (req.getBillType() != null && req.getOrderType() == 2)
        {
            sql.append(" right join (");
            sql.append(" select * from ");
            sql.append(DBUtil.buildTableName(PmProductOfferAttribute.class));
            sql.append(" where billing_type in ( ");
            sql.append(req.getBillType());
            sql.append(" ,-1)) t7 on t1.product_offering_id = t7.product_offering_id ");
        }
        sql.append(" where 1=1");
        if (req.getCodeName() != null)
        {
            int prodId = 0;
            try
            {
                prodId = Integer.parseInt(req.getCodeName());
                sql.append(" and (t1.product_offering_id like '%");
                sql.append(prodId);
                sql.append("%'");
                sql.append(" or t1.name like '%");
                sql.append(prodId);
                sql.append("%')");
            }
            catch (Exception e)
            {
                sql.append(" and t1.name like '%");
                sql.append(req.getCodeName());
                sql.append("%'");
            }
        }
        if (req.getOfferTypeId() != null)
        {
            sql.append(" and t2.offer_type_id = ");
            sql.append(req.getOfferTypeId());
        }
        if (req.getSts() != null)
        {
            sql.append(" and t3.lifecycle_status_id in (");
            sql.append(req.getSts());
            sql.append(")");
        }
        // if (req.getBrandId() != null && req.getOrderType() != 1)
        // {
        // sql.append(" and t4.brand_id = ");
        // sql.append(req.getBrandId());
        // }
        if (req.getIsMain() != null)
        {
            sql.append(" and t1.is_main = ");
            sql.append(req.getIsMain());
        }
        if (req.getOrderType() == 1)// 群
        {
            // sql.append(" and t6.spec_type_id in(135,136,137,103,114)");//
            sql.append(" and t1.offer_class in ('001','011','111','101') ");
        }
        else if (req.getOrderType() == 3)// 用户级
        {
            // sql.append(" and t6.spec_type_id not in(135,136,137,103,104)");
            sql.append(" and t1.offer_class in ('100','110','111','101') ");
        }
        else if (req.getOrderType() == 2)// 2 代表查询账户级
        {
            // sql.append(" and t6.spec_type_id not in(103,104)");// 账户级
            sql.append(" and t1.offer_class in ('010','011','111','110') ");

        }
        sql.append(" and t1.expire_date > sysdate  ");
        // @date 2012-08-08 tangkun #55132 : 界面根据产品的offering_id刷不出产品来
        // if (req.getOrderType() != 1)
        // {
        // sql.append(" and  t4.expire_date > sysdate ");
        // }
        int count = 0;
        try
        {
            count = DBUtil.getDBClient().countBySql(sql.toString());
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }

        return count;
    }

    /**
     * 查询账户的自动充值产品 luojb 2011-10-29
     * 
     * @param acctId
     * @return
     * @throws IMSException
     */
    public CoProd queryAutoTopupProd(Long acctId) throws IMSException
    {
        List<CoProd> prodList = this.queryProdListByAcctId(acctId);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        for (CoProd dmProd : prodList)
        {
            if (dmProd.getBusiFlag() == SpecCodeDefine.E_TOP_UP)
            {
                return dmProd;
            }
            else
            {
                continue;
            }
        }
        return null;
    }

   

    /**
     * 查询用户或者账户下订购某销售品的产品列表 按产品订购时间升序排列 ljc 2011-11-18 2012-08-10 luojb 代码评审修改：增加排序条件
     */
    public List<CoProd> queryProdByUserIdAndOfferId(Long objectId, Integer invType, Integer offerId)
    {
        List<CoProd> prodList = query(CoProd.class,
                new OrderCondition[] { new OrderCondition(true, CoProd.Field.prodValidDate) }, null, new DBCondition(
                        CoProd.Field.objectId, objectId), new DBCondition(CoProd.Field.objectType, invType), new DBCondition(
                        CoProd.Field.productOfferingId, offerId));
        return mergeProdList(prodList);
    }

    /**
     * 不考虑产品是否失效
     */
    public List<CoProd> queryAllProdsByObtIdAndBusiFlag(Long object_id, int objectType, int busiFlag, String valid_date,
            String expire_date)
    {
        if (object_id == null)
        {
            return null;
        }
        Date validDate = null;
        Date expireDate = null;
        if (CommonUtil.isEmpty(valid_date))
        {
            validDate = DateUtil.getFormatDate(valid_date, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
        }
        if (CommonUtil.isEmpty(expire_date))
        {
            expireDate = DateUtil.getFormatDate(expire_date, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
        }
        // 中间的
        List<CoProd> prodList = null;
        // 返回的
        List<CoProd> resultList = null;

        // List<Long> prodIds = new ArrayList<Long>();
        // List<CoProdInvObj> objList = DBUtil.query(CoProdInvObj.class, new DBCondition(CoProdInvObj.Field.objectId, object_id),
        // new DBCondition(CoProdInvObj.Field.objectType, objectType));
        // if (CommonUtil.isEmpty(objList))
        // {
        // return null;
        // }
        // for (CoProdInvObj obj : objList)
        // {
        // prodIds.add(obj.getProductId());
        // }
        prodList = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.objectId, object_id), new DBCondition(
                CoProd.Field.objectType, objectType), new DBCondition(CoProd.Field.busiFlag, busiFlag));
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        try
        {
            if (validDate != null && expireDate != null)
            {
                resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.busiFlag, busiFlag),
                        new CacheCondition(CoProd.Field.validDate, validDate, Operator.GREAT_EQUALS), new CacheCondition(
                                CoProd.Field.expireDate, expireDate, Operator.LESS));
            }
            else if (validDate != null)
            {
                resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.busiFlag, busiFlag),
                        new CacheCondition(CoProd.Field.validDate, validDate, Operator.GREAT_EQUALS));
            }
            else if (expireDate != null)
            {
                resultList = IMSUtil.matchDataObject(prodList, new CacheCondition(CoProd.Field.busiFlag, busiFlag),
                        new CacheCondition(CoProd.Field.expireDate, expireDate, Operator.LESS));
            }
            else
            {
                resultList = prodList;
            }
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
        return resultList;
        /*
         * DBJoinCondition joinCondition = new DBJoinCondition(CoProd.class); joinCondition.innerJoin(CoProdInvObj.class, new
         * DBRelation(CoProd.Field.productId, CoProdInvObj.Field.productId), new DBRelation(CoProd.Field.validDate,
         * CoProdInvObj.Field.validDate)); List<Map> result = null; // 日期两个都传，相交就查出来 if (valid_date != null && expire_date !=
         * null) { if (DateUtil.getFormatDate(valid_date, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS).after(
         * DateUtil.getFormatDate(expire_date, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS))) { return null; } result =
         * DBUtil.queryJoin( joinCondition, new DBCondition(CoProdInvObj.Field.objectType, objectType), new
         * DBCondition(CoProdInvObj.Field.objectId, object_id), new DBCondition(CoProd.Field.busiFlag, busiFlag), new
         * DBCondition(CoProd.Field.expireDate, DateUtil.getFormatDate(valid_date, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS),
         * Operator.GREAT_EQUALS), new DBCondition(CoProd.Field.validDate, DateUtil.getFormatDate(expire_date,
         * DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), Operator.LESS_EQUALS)); } else if (valid_date != null) {//
         * 只传一个valid_date，valid_date以前 result = DBUtil.queryJoin(joinCondition, new DBCondition(CoProdInvObj.Field.objectType,
         * objectType), new DBCondition( CoProdInvObj.Field.objectId, object_id), new DBCondition(CoProd.Field.busiFlag,
         * busiFlag), new DBCondition( CoProd.Field.validDate, DateUtil.getFormatDate(valid_date,
         * DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), Operator.GREAT_EQUALS)); } else if (expire_date != null) {//
         * 只传一个expire_date，expire_date result = DBUtil.queryJoin(joinCondition, new DBCondition(CoProdInvObj.Field.objectType,
         * objectType), new DBCondition( CoProdInvObj.Field.objectId, object_id), new DBCondition(CoProd.Field.busiFlag,
         * busiFlag), new DBCondition( CoProd.Field.expireDate, DateUtil.getFormatDate(expire_date,
         * DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS), Operator.LESS_EQUALS)); } else {// 不错查询所有 result =
         * DBUtil.queryJoin(joinCondition, new DBCondition(CoProdInvObj.Field.objectType, objectType), new DBCondition(
         * CoProdInvObj.Field.objectId, object_id), new DBCondition(CoProd.Field.busiFlag, busiFlag)); } List<CoProd> servSet =
         * new ArrayList<CoProd>(); if (!CommonUtil.isEmpty(result)) { for (Map itemMap : result) { CoProd prod = (CoProd)
         * itemMap.get(CoProd.class); if (null != prod) { if (prod.getValidDate().equals(prod.getExpireDate())) { continue; }
         * servSet.add(prod); } } } return servSet;
         */
    }

    public CmResServCycle queryResServCycle(Integer resSerCycleId)
    {
        // CmResServCycle dm = new CmResServCycle();
        // dm.setServiceSpecId(resSerCycleId);
        return querySingle(CmResServCycle.class, new DBCondition(CmResServCycle.Field.serviceSpecId, resSerCycleId));
    }

    /**
     * 查询用户除主产品以外的所有产品
     * 
     * @Description
     * @Author lijingcheng
     * @param userId
     * @return
     */
    public List<CoProd> queryUserNotMainProducts(Long userId)
    {
        List<CoProd> resultList = new ArrayList<CoProd>();
        List<CoProd> prodList = this.queryProdListByUserId(userId);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        for (CoProd dmProd : prodList)
        {
            if (dmProd.getIsMain() == EnumCodeDefine.PRODUCT_COMMON)
            {
                resultList.add(dmProd);
            }
        }
        return resultList;
    }

    /**
     * 查询产品上的免费资源量
     * 
     * @author liuyang
     * @param productId
     * @param acctId
     */

    public long queryProductMaxFreeSource(Long productId, Integer feeSourceItem)
    {
        long maxFreeSource = 0;

        PmProductOffering productOffer = this.queryOfferingByProdId(productId);
        if (productOffer == null)
        {
            return maxFreeSource;
        }
        List<PmProductPricingPlan> pricingPlans = this.query(PmProductPricingPlan.class, new DBCondition(
                PmProductPricingPlan.Field.productOfferingId, productOffer.getProductOfferingId()));
        if (CommonUtil.isEmpty(pricingPlans))
        {
            return maxFreeSource;
        }
        for (PmProductPricingPlan pricingPlan : pricingPlans)
        {
            List<PmCompositeOfferPrice> prodofferPrices = this.query(PmCompositeOfferPrice.class, new DBCondition(
                    PmCompositeOfferPrice.Field.pricingPlanId, pricingPlan.getPricingPlanId()));
            if (CommonUtil.isEmpty(prodofferPrices))
            {
                return maxFreeSource;
            }
            for (PmCompositeOfferPrice offerPrice : prodofferPrices)
            {
                List<PmComponentProdofferPrice> prices = this.query(PmComponentProdofferPrice.class, new DBCondition(
                        PmComponentProdofferPrice.Field.priceId, offerPrice.getPriceId()));
                if (CommonUtil.isEmpty(prices))
                {
                    return maxFreeSource;
                }
                for (PmComponentProdofferPrice price : prices)
                {
                    // 查询一次性免费资源总量
                    if (price.getPriceType() == EnumCodeDefine.ONE_TIME_FEE_RES)
                    {
                        imsLogger.info("***************begin to query one time fee resource **********");
                        List<PmOneTimeFreeUsage> timeFreeUsages = this.query(PmOneTimeFreeUsage.class, new DBCondition(
                                PmOneTimeFreeUsage.Field.priceId, price.getPriceId()));
                        if (CommonUtil.isNotEmpty(timeFreeUsages))
                        {
                            for (PmOneTimeFreeUsage timeFreeUsage : timeFreeUsages)
                            {
                                //
                                List<PmAllowanceFreeUsage> freeUsages = this.query(PmAllowanceFreeUsage.class, new DBCondition(
                                        PmAllowanceFreeUsage.Field.allowanceRuleId, timeFreeUsage.getAllowanceRuleId()),
                                        new DBCondition(PmAllowanceFreeUsage.Field.freeresItem, feeSourceItem));
                                if (CommonUtil.isNotEmpty(freeUsages))
                                {
                                    for (PmAllowanceFreeUsage freeUsage : freeUsages)
                                    {
                                        maxFreeSource += freeUsage.getAmount();
                                    }
                                }
                            }
                        }
                        imsLogger.info("***************max Free Source = " + maxFreeSource);
                    }
                    // 查询周期性免费资源量
                    if (price.getPriceType() == EnumCodeDefine.CYCLE_RESOURCE)
                    {
                        imsLogger.info("*************************begin to query cycle resource *********");
                        List<PmAllowanceFreeresDetails> freeresDetails = this.query(PmAllowanceFreeresDetails.class,
                                new DBCondition(PmAllowanceFreeresDetails.Field.priceId, price.getPriceId()), new DBCondition(
                                        PmAllowanceFreeresDetails.Field.freeresItem, feeSourceItem));
                        if (CommonUtil.isNotEmpty(freeresDetails))
                        {
                            for (PmAllowanceFreeresDetails details : freeresDetails)
                            {
                                List<PmAllowanceFreeresSegment> segments = this.query(PmAllowanceFreeresSegment.class,
                                        new DBCondition(PmAllowanceFreeresSegment.Field.priceId, details.getPriceId()));
                                if (CommonUtil.isNotEmpty(segments))
                                {
                                    for (PmAllowanceFreeresSegment segment : segments)
                                    {
                                        maxFreeSource += segment.getAmount().longValue();
                                    }
                                }
                            }
                        }

                        imsLogger.info("***************max Free Source = " + maxFreeSource);
                    }
                }
            }
        }
        return maxFreeSource;

    }

    /**
     * 前台展示的产品可配置的最大免费资源列表 {"item_id", "item_name", "amount", "measure_name"}) 该方法参考SetMaxFreeResBusiBean.checkFreeSource
     * 
     * @param productId
     * @return
     */
    public List<FreeItem4GUI> queryMaxFreeItems(Long productId)
    {
        List<FreeItem4GUI> freeItems = new ArrayList<FreeItem4GUI>();
        FreeItem4GUI freeItem = null;
        PmProductOffering productOffer = this.queryOfferingByProdId(productId);
        List<PmProductPricingPlan> pricingPlans = this.query(PmProductPricingPlan.class, new DBCondition(
                PmProductPricingPlan.Field.productOfferingId, productOffer.getProductOfferingId()));
        if (CommonUtil.isEmpty(pricingPlans))
            return freeItems;
        for (PmProductPricingPlan pricingPlan : pricingPlans)
        {
            List<PmCompositeOfferPrice> prodofferPrices = this.query(PmCompositeOfferPrice.class, new DBCondition(
                    PmCompositeOfferPrice.Field.pricingPlanId, pricingPlan.getPricingPlanId()));
            if (CommonUtil.isEmpty(prodofferPrices))
                continue;
            for (PmCompositeOfferPrice offerPrice : prodofferPrices)
            {
                List<PmComponentProdofferPrice> prices = this.query(PmComponentProdofferPrice.class, new DBCondition(
                        PmComponentProdofferPrice.Field.priceId, offerPrice.getPriceId()));
                if (CommonUtil.isEmpty(prices))
                    continue;
                for (PmComponentProdofferPrice price : prices)
                {
                    // 查询一次性免费资源总量
                    if (price.getPriceType() == EnumCodeDefine.ONE_TIME_FEE_RES)
                    {
                        List<PmOneTimeFreeUsage> timeFreeUsages = this.query(PmOneTimeFreeUsage.class, new DBCondition(
                                PmOneTimeFreeUsage.Field.priceId, price.getPriceId()));
                        if (CommonUtil.isNotEmpty(timeFreeUsages))
                        {
                            for (PmOneTimeFreeUsage timeFreeUsage : timeFreeUsages)
                            {
                                //
                                List<PmAllowanceFreeUsage> freeUsages = this.query(PmAllowanceFreeUsage.class, new DBCondition(
                                        PmAllowanceFreeUsage.Field.allowanceRuleId, timeFreeUsage.getAllowanceRuleId()));
                                if (CommonUtil.isEmpty(freeUsages))
                                    continue;
                                for (PmAllowanceFreeUsage pmAllowanceFreeUsage : freeUsages)
                                {
                                    int freeItemId = pmAllowanceFreeUsage.getFreeresItem();
                                    long amount = pmAllowanceFreeUsage.getAmount();
                                    freeItem = new FreeItem4GUI();
                                    freeItem.setItem_id(freeItemId);
                                    freeItem.setAmount(amount);
                                    PmAssetItem pmAssetItem = this.querySingle(PmAssetItem.class, new DBCondition(
                                            PmAssetItem.Field.assetItemId, freeItemId));
                                    if (pmAssetItem != null)
                                        freeItem.setItem_name(pmAssetItem.getName());
                                    PmFreeUsageProperty pmFreeUsageProperty = this.querySingle(PmFreeUsageProperty.class,
                                            new DBCondition(PmFreeUsageProperty.Field.assetItemId, freeItemId));
                                    if (pmFreeUsageProperty != null)
                                    {
                                        SysMeasure orig_measure = DBConfigInitBean
                                                .getSingleCached(SysMeasure.class, new CacheCondition(SysMeasure.Field.measureId,
                                                        pmFreeUsageProperty.getMeasureId()));
                                        if (orig_measure != null)
                                            freeItem.setMeasure_name(orig_measure.getName());
                                    }
                                    freeItems.add(freeItem);
                                }
                            }
                        }
                    }
                    // 查询周期性免费资源量
                    if (price.getPriceType() == EnumCodeDefine.CYCLE_RESOURCE)
                    {
                        List<PmAllowanceFreeresDetails> freeresDetails = this.query(PmAllowanceFreeresDetails.class,
                                new DBCondition(PmAllowanceFreeresDetails.Field.priceId, price.getPriceId()));
                        if (CommonUtil.isNotEmpty(freeresDetails))
                        {
                            for (PmAllowanceFreeresDetails details : freeresDetails)
                            {
                                long amount = 0;
                                List<PmAllowanceFreeresSegment> segments = this.query(PmAllowanceFreeresSegment.class,
                                        new DBCondition(PmAllowanceFreeresSegment.Field.priceId, details.getPriceId()));
                                if (CommonUtil.isNotEmpty(segments))
                                {
                                    for (PmAllowanceFreeresSegment segment : segments)
                                    {
                                        if (segment.getAmount().longValue() > amount)
                                            amount = segment.getAmount().longValue();
                                    }
                                }
                                int freeItemId = details.getFreeresItem();
                                freeItem = new FreeItem4GUI();
                                freeItem.setItem_id(freeItemId);
                                freeItem.setAmount(amount);
                                PmAssetItem pmAssetItem = this.querySingle(PmAssetItem.class, new DBCondition(
                                        PmAssetItem.Field.assetItemId, freeItemId));
                                if (pmAssetItem != null)
                                    freeItem.setItem_name(pmAssetItem.getName());
                                PmFreeUsageProperty pmFreeUsageProperty = this.querySingle(PmFreeUsageProperty.class,
                                        new DBCondition(PmFreeUsageProperty.Field.assetItemId, freeItemId));
                                if (pmFreeUsageProperty != null)
                                {
                                    SysMeasure orig_measure = DBConfigInitBean.getSingleCached(SysMeasure.class,
                                            new CacheCondition(SysMeasure.Field.measureId, pmFreeUsageProperty.getMeasureId()));
                                    if (orig_measure != null)
                                        freeItem.setMeasure_name(orig_measure.getName());
                                }
                                freeItems.add(freeItem);
                            }
                        }
                    }
                }
            }
        }
        return freeItems;
    }

    /**
     * 根据预算规则id查询到对应的服务id
     * 
     * @Description
     * @Author lijingcheng
     * @param ruleId
     * @return
     */
    public Long queryServceSpecIdByBudgetRuleId(Integer ruleId)
    {
        DBJoinCondition join = new DBJoinCondition(PmBudgetItemLimit.class);
        join.innerJoin(PmPriceEvent.class, new DBRelation(PmPriceEvent.Field.itemId, PmBudgetItemLimit.Field.itemId));

        List<Map> result = this.queryJoin(join, new DBCondition(PmBudgetItemLimit.Field.priceRuleId, ruleId));
        if (CommonUtil.isEmpty(result))
        {
            return null;
        }
        for (Map map : result)
        {
            PmPriceEvent event = (PmPriceEvent) map.get(PmPriceEvent.class);
            if (event.getServiceSpecId() == null)
            {
                continue;
            }
            else
            {
                return event.getServiceSpecId().longValue();
            }
        }
        return null;
    }

    /**
     * 根据定价计划id查询价格描述 luojb 2012-2-10
     * 
     * @param pricePlanId
     * @return
     * @throws IMSException
     */
    public String queryPriceDescByPricePlanId(Integer pricePlanId) throws IMSException
    {
        // PmPriceParamDef pmPrice = new PmPriceParamDef();
        // pmPrice.setParamId(pricePlanId);
        PmPriceParamDef pmPrice = querySingle(PmPriceParamDef.class, new DBCondition(PmPriceParamDef.Field.paramId, pricePlanId));
        return pmPrice == null ? null : pmPrice.getDescription();
    }

    /**
     * 查询产品状态 luojb 2012-2-17
     * 
     * @param productId
     * @return
     * @throws IMSException
     */
    public Integer queryProdSts(Long productId) throws IMSException
    {
        CoProd prod = queryProd(productId);
        if (prod == null)
            return null;
        Integer lifecycleSts = prod.getLifecycleStatusId();// 状态位变成3位后需要修改这里
        return lifecycleSts;
    }

    /**
     * 通过销售品ID分页查询产品列表 xieqr 2012-3-3
     * 
     * @param offerId
     * @param ObjType
     * @param start
     * @param limit
     */
    public List<CoProd> queryProdByOfferId(Integer offerId, int objType, int start, int limit)
    {
        // DBJoinCondition joinCondition = new DBJoinCondition(CoProd.class);
        // joinCondition.innerJoin(CoProdInvObj.class, new DBRelation(CoProd.Field.productId, CoProdInvObj.Field.productId),
        // new DBRelation(CoProdInvObj.Field.objectType, objType));
        // OrderCondition[] orders = new OrderCondition[] { new OrderCondition(false,CoProd.Field.productId) };
        // IntRange range= new IntRange(start + 1, start + limit);
        // DBCondition dbConditions = new DBCondition(CoProd.Field.productOfferingId, offerId);
        // List<Map> maps = super.queryJoin(joinCondition, orders, range, dbConditions);
        OrderCondition[] orders = new OrderCondition[] { new OrderCondition(false, CoProd.Field.productId) };
        DBCondition[] cons = new DBCondition[] { new DBCondition(CoProd.Field.productOfferingId, offerId),
                new DBCondition(CoProd.Field.objectType, objType) };
        IntRange range = new IntRange(start + 1, start + limit);
        List<CoProd> list = super.query(CoProd.class, orders, range, cons);
        return mergeProdList(list);
    }

    /**
     * 统计销售品ID 对应的用户订购产品记录数 xieqr 2012-3-7
     * 
     * @param offerId
     * @param objType
     * @return
     */
    public int queryProdCountByOfferId(Integer offerId, int objType)
    {
        DBCondition[] cons = new DBCondition[] { new DBCondition(CoProd.Field.productOfferingId, offerId),
                new DBCondition(CoProd.Field.objectType, objType) };
        int count = super.queryCount(CoProd.class, cons);
        return count;
    }

    /**
     * @yanchuan 2012-3-9
     * @describe 通过object_id,object_type查询产品信息
     * @param object_id
     * @param object_type
     * @return
     * @throws IMSException
     */
    public List<CoProd> queryProds(Long object_id, int object_type) throws IMSException
    {
        List<CoProd> prodList = query(CoProd.class, new DBCondition(CoProd.Field.objectId, object_id), new DBCondition(
                CoProd.Field.objectType, object_type));
        return mergeProdList(prodList);
    }

    /**
     * 查询账户产品上配置的用户数限制条件
     * luojb 2012-10-16
     * @param acctId
     * @return
     */
    public List<PmProdSubNumLimit> querySubLimit(Integer... offerIds)
    {
        List<PmProdSubNumLimit> limits = query(PmProdSubNumLimit.class, 
                new DBCondition(PmProdSubNumLimit.Field.productOfferingId, offerIds,Operator.IN));
        return limits;
    }
    
   
    /**
     * @author yanchuan 2012-3-8
     * @describe 判断支付账户如果订购了unlimited产品，其用户数是否超过配置的限制用户数,超过则返回true，否则返回false
     */
    public boolean isExceedUserLimit(Long acctId)
    {
        return this.isExceedUserLimit(acctId,null);
    }
    
    /**
     * 检查账户下的用户数是否满足限制条件
     * luojb 2012-10-16
     * @param subLimit
     * @param resList
     * @return true不匹配，false满足
     */
    public boolean checkLimit(List<PmProdSubNumLimit> subLimit,List<CmResource> resList)
    {
        int preNum = 0;
        int postNum = 0;
        int hybridNum = 0;
        int all = 0;
        if(CommonUtil.isNotEmpty(resList))
        {
            for(CmResource res:resList)
            {
                all ++;
                int billingType = res.getBillingType().intValue();
                if(billingType == EnumCodeDefine.USER_PAYMODE_PREPAID)
                    preNum ++;
                else if(billingType == EnumCodeDefine.USER_PAYMODE_POSTPAID)
                    postNum ++;
                else if(billingType == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID
                        || billingType == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID)
                {
                    preNum ++;
                    postNum ++;
                    hybridNum ++;
                }   
            }
        }
        
        for(PmProdSubNumLimit limit:subLimit)
        {
            // 配置枚举为：-1：所以类型 0：预付费 1：后付费 2：混合
            int limitBillType = limit.getBillingType();
            int max = limit.getMaxSubLimit();
            int min = limit.getMinSubLimit();
            int targetSubNum = 0;
            if(limitBillType == EnumCodeDefine.USER_PAYMODE_PREPAID)
                targetSubNum = preNum;
            else if(limitBillType == EnumCodeDefine.USER_PAYMODE_POSTPAID)
                targetSubNum = postNum;
            else if(limitBillType == EnumCodeDefine.USER_PAYMODE_HYBRID)
                targetSubNum = hybridNum;
            else if(limitBillType == -1)
                targetSubNum = all;
            
            if(min != -1 && targetSubNum < min || max != -1 && targetSubNum > max)
                return true;
        }
        return false;
    }

    /**
     * @author linzt 2012-9-8
     * @describe 判断支付账户如果订购了unlimited产品，其用户数是否超过配置的限制用户数,超过则返回true，否则返回false
     * @param acctId
     * @param offerId
     * @return
     */
    public boolean isExceedUserLimit(Long acctId, Integer offerId)
    {
        if (!CommonUtil.isValid(acctId))
            return false;
        List<Integer> offerIds = new ArrayList<Integer>();
        if(offerId != null)
            offerIds.add(offerId);
        else{
            // 查询产品
            List<CoProd> prods = this.queryProdListByAcctId(acctId);
            if (CommonUtil.isEmpty(prods))
                return false;
            
            
            for (CoProd prod : prods)
            {
                offerIds.add(prod.getProductOfferingId());
            }
        }
        // 查询限制条件
        List<PmProdSubNumLimit> subLimit = this.querySubLimit(offerIds.toArray(new Integer[offerIds.size()]));
        if(subLimit == null)
            return false;
        // 有配置限制条件，则把用户先查出来
        List<CmResource> resList = context.getComponent(UserQuery.class).queryUsersByAcctID(acctId);
        
        return checkLimit(subLimit, resList);
    }



    /**
     * 查询低余额通知告警编号 liuyang 2012-3-22
     * 
     * @param prodId
     * @param
     * @return
     */
    public Long queryDefaultNotiFyForLowBalance(Long prodId, Integer specId)
    {
        PmProductOffering offering = queryOfferingByProdId(prodId);
        if (offering != null)
        {
            PmProductOfferSpecChar charValue = querySingle(PmProductOfferSpecChar.class, new DBCondition(
                    PmProductOfferSpecChar.Field.productOfferingId, offering.getProductOfferingId()), new DBCondition(
                    PmProductOfferSpecChar.Field.specCharId, specId));
            if (charValue != null)
            {
                return Long.valueOf(charValue.getValue());
            }
        }

        return null;
    }

    /**
     * caohm5 add 2012-05-07
     * 
     * @param prodId产品编号
     * @param objectId产品使用编号
     * @param objectType产品使用标识
     * @return
     */
    public List<CoProd> queryProd(Long prodId, Integer objectType, Long objectId)
    {
        return this.query(CoProd.class, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(CoProd.Field.objectType,
                objectType), new DBCondition(CoProd.Field.objectId, objectId));
    }

    /**
     * 根据销售品编号查询品牌编号
     * 
     * @author wangyh3
     * @Date 2012-05-10
     */
    public Integer queryBrandByOffering(Integer prodOfferingId)
    {
        PmOfferingBrandRel rel = super.querySingle(PmOfferingBrandRel.class, new DBCondition(
                PmOfferingBrandRel.Field.productOfferingId, prodOfferingId));
        return rel != null ? rel.getBrandId() : null;
    }

    /**
     * 根据offer_id查询小区编号
     * 
     * @author yangjh
     * @Date 2012-05-29
     */
    public List<Integer> queryCellCode(Integer offer_id)
    {
        List<Integer> result = new ArrayList<Integer>();
        List<PmProductOfferSpecialZone> queryList = query(PmProductOfferSpecialZone.class, new DBCondition(
                PmProductOfferSpecialZone.Field.productOfferingId, offer_id));
        if (CommonUtil.isEmpty(queryList))
            return null;
        for (PmProductOfferSpecialZone cellCode : queryList)
        {
            result.add(cellCode.getCellCode());
        }
        return result;
    }

    /**
     * @Description: 根据产品ID查询订购该产品的用户
     * @param productId
     * @return
     * @author: tangjl5
     * @Date: 2012-7-26
     */
    public CmResource queryUserByProdId(Long productId)
    {
        Long resId = queryUserIdByProductId(productId);
        if (resId != null)
        {
            // return context.getComponent(UserQuery.class).queryUserByUserID(resId);
            // 2012-08-28 linzt 整理组件方法 采用load3hBean
            try
            {
                return context.get3hTree().loadUser3hBean(resId).getUser();
            }
            catch (IMS3hNotFoundException e)
            {
            	imsLogger.error(e,e);
            }
        }
        return null;
    }

    /**
     * @author yanchuan 2012-7-23
     * @desc 根据产品id查询用户id
     * @param productId
     * @return
     */
    public Long queryUserIdByProductId(Long productId)
    {
        CoProd prod = this.queryProd(productId);
        if (prod == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PROD_NOTEXIST, productId);
        }

        return prod.getObjectId();
    }

    /**
     * @authour yanchuan 在产品列表中找出主产品 2012-8-20
     * @param prodList
     * @return
     */
    public CoProd getPrimaryProduct(List<CoProd> prodList)
    {
        if (CommonUtil.isEmpty(prodList))
            return null;
        for (CoProd prod : prodList)
        {
            if (prod.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                return prod;
            }
        }
        return null;
    }
    
    public PmProdBusiLimit queryProdBusiLimit(Integer offerId)
    {
        return querySingle(PmProdBusiLimit.class, new DBCondition(PmProdBusiLimit.Field.productOfferingId,offerId));
    }

}
