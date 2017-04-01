package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.component.BalanceShComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryUnOrderFeeResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryUnOrderFeeReq;
import com.ailk.openbilling.persistence.imscnsh.entity.SFreeResource;
import com.ailk.openbilling.persistence.imscnsh.entity.SMonthlyFee;
import com.ailk.openbilling.persistence.imscnsh.entity.SProductFee;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.pd.entity.PmCurveSegments;
import com.ailk.openbilling.persistence.pd.entity.PmRates;
import com.ailk.openbilling.persistence.pd.entity.PmRecurringFeeDtl;
import com.ailk.openbilling.persistence.pm.entity.PmAllowanceFreeresDetails;
import com.ailk.openbilling.persistence.pm.entity.PmAllowanceFreeresSegment;
import com.ailk.openbilling.persistence.pm.entity.PmComponentProdofferPrice;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeOfferPrice;
import com.ailk.openbilling.persistence.pm.entity.PmFreeUsageProperty;
import com.ailk.openbilling.persistence.pm.entity.PmPriceEvent;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;
import com.ailk.openbilling.persistence.sd.entity.SysEntityMultiLang;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;

/**
 * 查询未订购产品的月费和免费资源 Billing侧产品未订购，需要查询本月月费和下月月费
 * 
 * @author wangyh3
 * @Date 2012-7-9
 * @date 2012-11-26 luojb 性能优化，批量查询定价
 */
public class QueryUnOrderFeeBean extends BaseBusiBean
{
    private QueryUnOrderFeeReq req;
    private List<Long> offerIdList = null;
    private ProductQuery prodQuery = null;
    private Date orderDate = null;
    private Long userId = null;
    private Integer mainOfferId = null;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (QueryUnOrderFeeReq) input[0];
        prodQuery = context.getComponent(ProductQuery.class);
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (req == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryNo3hUnOrderFeeReq");
        }

        offerIdList = req.getOfferIdList();

        if (offerIdList==null||offerIdList.isEmpty())
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "offerIdList");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {   
        if(CommonUtil.isValid(req.getPhone_id())|| CommonUtil.isValid(req.getUser_id())){
            Check3HParamReturn info = context.getComponent(CheckComponentSH.class).check3HParam(null, null, req.getUser_id(), req.getPhone_id());
            userId = info.getUserId();
            //用户的首账日；如果没有传，去查询一把
            if(req.getBill_cycle() == null || !CommonUtil.isValid(req.getBill_cycle().getFirst_bill_day())){
                List<BillCycleComplex> billCyCleListr=context.getComponent(AccountQuery.class).queryBillCycle(info.getAcctId());
                if(billCyCleListr==null||billCyCleListr.isEmpty()){
                    IMSUtil.throwBusiException(ErrorCodeDefine.BILL_CYCLE_NOT_EXIST);
                }
                BillCycleComplex billCycle=billCyCleListr.get(0);
                if(billCycle==null){
                    IMSUtil.throwBusiException(ErrorCodeDefine.BILL_CYCLE_NOT_EXIST);
                }
                SBillCycle  temBillCycle = new SBillCycle();
                temBillCycle.setFirst_bill_day(billCycle.getFirstBillDate());
                req.setBill_cycle(temBillCycle);
            }
        }
        BalanceShComponent balanceCmp = context.getComponent(BalanceShComponent.class);

        mainOfferId = getMainOfferId(offerIdList);
        if (!CommonUtil.isValid(mainOfferId) && CommonUtil.isValid(userId))
        {
            // 已有三户资料，未传主销售品，根据用户编号查主销售品。
            mainOfferId = prodQuery.queryMainOfferIdByUserId(userId);
        }
        imsLogger.debug("####主产品id:::::::::::####",mainOfferId);
        // 获得订购产品后的当月使用天数
        int prorateDate = 0;
        //如果订购时间没传，就默认为当前时间
        orderDate = IMSUtil.formatDate(req.getOrder_date(), context.getRequestDate());
        if(req.getBill_cycle() != null && CommonUtil.isValid(req.getBill_cycle().getFirst_bill_day())&& CommonUtil.isValid(req.getOrder_date())){
            prorateDate = balanceCmp.getOrderSumDay(req.getBill_cycle(), orderDate);
        }
        imsLogger.debug("####获得订购产品后的当月使用天数prorateDate:::::::::::####",prorateDate);
        
        Map<Integer,Integer> offerPlanMap = queryPricePlanId(offerIdList,mainOfferId);
        List<SProductFee> rtnList = new ArrayList<SProductFee>();
        
        Map<Integer,Map<Integer, SMonthlyFee>> monthlyFeeMap = null;
        Map<Integer,List<SFreeResource>> offerFreeResMap = null;
        if(CommonUtil.isNotEmpty(offerPlanMap)){
            monthlyFeeMap = queryMonthlyFee(new ArrayList<Integer>(offerPlanMap.values()), orderDate, prorateDate);
            offerFreeResMap = queryUnOrderFreeResList(offerPlanMap, orderDate, prorateDate);
        }
        for (Long offeringId : offerIdList/*offerPlanMap.keySet()*/){
            Integer offerId = CommonUtil.long2Int(offeringId);
            Integer pricePlanId = offerPlanMap.get(offerId);
            imsLogger.debug("####销售品编号[", offeringId, "]对应的定价计划为::::::####",pricePlanId);
            SProductFee pfee = new SProductFee();
            pfee.setOffer_id(offeringId);
            // 月费
            if(monthlyFeeMap != null){
                Map<Integer,SMonthlyFee> itemFeeMap = monthlyFeeMap.get(pricePlanId);
                if(itemFeeMap != null){
                    pfee.setMonthlyFeeList(new ArrayList<SMonthlyFee>(itemFeeMap.values()));
                }
            }
            // 免费资源
            if(offerFreeResMap != null){
                pfee.setFreeResourceList(offerFreeResMap.get(offerId));
            }
            rtnList.add(pfee);
        }
        return new Object[] { rtnList };
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryUnOrderFeeResponse resp = new Do_queryUnOrderFeeResponse();
        resp.setProductFeeList((List<SProductFee>) result[0]);

        return resp;
    }

    @Override
    public void destroy()
    {
    }
    /**
     * 根据销售品idList与主产品id获取相应的定价计划
     * @author zhangzhj 2012-11-28
     * @param offerIds
     * @param mainOfferId
     * @return
     */
    private Map<Integer,Integer> queryPricePlanId(List<Long> offerIds,Integer mainOfferId){
        List<Integer> offerIdList = new ArrayList<Integer>();
        for(Long offerId:offerIds)
        {
            offerIdList.add(CommonUtil.long2Int(offerId));
        }
        return queryPricePlanId4SH(offerIdList, mainOfferId);
    }
    /**
     * 通过传进来的销售品id查询是否有主产品
     * @author zhangzhj 2012-11-28
     * @param offerIdList
     * @return 主产品id
     */
    private Integer getMainOfferId(List<Long> offerIdList)
    {   
        Long[] idArr = offerIdList.toArray(new Long[offerIdList.size()]);
        List<PmProductOffering> offerList = prodQuery.query(PmProductOffering.class,
                new DBCondition(PmProductOffering.Field.productOfferingId,idArr,Operator.IN),
                new DBCondition(PmProductOffering.Field.isMain,EnumCodeShDefine.PRODUCT_OFFERING_IS_MAIN));
        if(CommonUtil.isNotEmpty(offerList)){
            return offerList.get(0).getProductOfferingId();
        }
        return null;
    }
   /**
    * 
    * @author zhangzhj 2012-11-28
    * @param offeringIds
    * @param mainOfferId
    * @return每个销售品对应主产品的定价计划MAp
    */
    private Map<Integer,Integer> queryPricePlanId4SH(List<Integer> offeringIds, Integer mainOfferId)
    {
        List<PmProductPricingPlan> plans =  queryOfferPricingPlan(offeringIds, mainOfferId);
        Map<Integer,Integer> offerPlanMap = new HashMap<Integer, Integer>();
        for(Integer offerId:offeringIds){
            List<PmProductPricingPlan> offerPlans = null;
            try{
                offerPlans = IMSUtil.matchDataObject(plans, new CacheCondition(PmProductPricingPlan.Field.productOfferingId, offerId));
            }catch (Exception e) {
                imsLogger.error(e);
                continue;
            }
            if (CommonUtil.isEmpty(offerPlans)){
                offerPlans = queryOfferPricingPlanWithNormal(offerId);
                if(CommonUtil.isEmpty(offerPlans)){
                    imsLogger.error("****** not find PmProductPricingPlan where productOfferingId = ", offerId);
                    continue;
                }
            }         
            Integer default_planId = null;
            //用POLICY_ID=0取得的pricing_plan_id用于展示
            for (PmProductPricingPlan plan : offerPlans){
                if (plan.getPolicyId().intValue() == 0){
                    default_planId = plan.getPricingPlanId();
                    break;
                }
            }
            //如果POLICY_ID=0的pricing_plan_id不存在，则忽略POLICY的条件，取对应的第一条记录展示；如果还是没有，则无展示的pricing_plan_id
            if(default_planId == null && offerPlans.get(0) != null){
                default_planId = offerPlans.get(0).getPricingPlanId();
            }
            if(default_planId != null)
                offerPlanMap.put(offerId, default_planId);
        }
        return offerPlanMap;
    }
    
    
    /**
     * zhangzj3 2012-11-28 根据销售品id与主产品获取定价计划
     * 如果有传主产品就通过主产品跟offerId去查询；没有传主产品就productOfferingId下取dispFlag=1的来显示
     * @param offerId
     * @param mainPromotion
     * @return
     */
    public List<PmProductPricingPlan> queryOfferPricingPlan(List<Integer> offerIds, Integer mainPromotion)
    {   
        
        if (mainPromotion != null){
            return DBUtil.query(PmProductPricingPlan.class,
                      new DBCondition(PmProductPricingPlan.Field.productOfferingId,offerIds,Operator.IN),
                      new DBCondition(PmProductPricingPlan.Field.mainPromotion,mainPromotion));
        }else{
            return DBUtil.query(PmProductPricingPlan.class,
                      new DBCondition(PmProductPricingPlan.Field.dispFlag,1),
                      new DBCondition(PmProductPricingPlan.Field.productOfferingId,offerIds,Operator.IN));
        }
    }
    
    /**
     * 如果有传主产品并且查询不到定价计划，则按主产品为-1进行匹配
     * @param offerId
     * @param mainPromotion
     * @return
     */
    public List<PmProductPricingPlan> queryOfferPricingPlanWithNormal(Integer offerId){   
        return DBUtil.query(PmProductPricingPlan.class,
                new DBCondition(PmProductPricingPlan.Field.productOfferingId,offerId),
                new DBCondition(PmProductPricingPlan.Field.mainPromotion,-1));
    }
    
    /**
     * Map<pricePlanId,Map<itemCode, SMonthlyFee>>
     * luojb 2012-11-27
     * @param pricePlanIds
     * @param orderDate
     * @param prorateDate
     * @return
     */
    @SuppressWarnings("rawtypes")
    private  Map<Integer,Map<Integer, SMonthlyFee>> queryMonthlyFee(List<Integer> pricePlanIds, Date orderDate, int prorateDate)
    {
        imsLogger.debug("#####query product monthly fee begin...#####");

        DBJoinCondition join = new DBJoinCondition(PmCompositeOfferPrice.class);
        join.innerJoin(PmComponentProdofferPrice.class, new DBRelation(PmCompositeOfferPrice.Field.priceId,
                PmComponentProdofferPrice.Field.priceId));
        join.innerJoin(PmRecurringFeeDtl.class, new DBRelation(PmComponentProdofferPrice.Field.priceId,
                PmRecurringFeeDtl.Field.priceId));
        join.innerJoin(PmRates.class, new DBRelation(PmRecurringFeeDtl.Field.rateId, PmRates.Field.rateId));
        join.innerJoin(PmCurveSegments.class, new DBRelation(PmRates.Field.curveId, PmCurveSegments.Field.curveId));
        join.innerJoin(PmPriceEvent.class, new DBRelation(PmRecurringFeeDtl.Field.itemCode, PmPriceEvent.Field.itemId));
        List<Map> result = context.getComponent(BaseComponent.class).queryJoin(join, new DBCondition(PmCompositeOfferPrice.Field.pricingPlanId, pricePlanIds,Operator.IN),
                new DBCondition(PmCompositeOfferPrice.Field.offerSts, new Integer[] { -1, 0 }, Operator.IN), new DBCondition(
                        PmCompositeOfferPrice.Field.validDate, orderDate, Operator.LESS_EQUALS), new DBCondition(
                        PmCompositeOfferPrice.Field.expireDate, orderDate, Operator.GREAT), new DBCondition(
                        PmComponentProdofferPrice.Field.priceType, 7));

        imsLogger.debug("#####query product monthly fee finished.#####");

        if (!CommonUtil.isEmpty(result))
        {
            Map<Integer,Map<Integer, SMonthlyFee>> pricePlanFeeMap = new HashMap<Integer, Map<Integer, SMonthlyFee>>();
            for (Map item : result)
            {
                PmCompositeOfferPrice pm1 = (PmCompositeOfferPrice) item.get(PmCompositeOfferPrice.class);
                PmRecurringFeeDtl pm3 = (PmRecurringFeeDtl) item.get(PmRecurringFeeDtl.class);
                PmRates pm4 = (PmRates) item.get(PmRates.class);
                PmCurveSegments pm5 = (PmCurveSegments) item.get(PmCurveSegments.class);
                PmPriceEvent pm6 = (PmPriceEvent) item.get(PmPriceEvent.class);

                Integer pricePlanId = pm1.getPricingPlanId();
                Map<Integer, SMonthlyFee> tmpMap = pricePlanFeeMap.get(pricePlanId);
                if(tmpMap == null){
                    tmpMap = new HashMap<Integer, SMonthlyFee>();
                    pricePlanFeeMap.put(pricePlanId, tmpMap);
                }
                Integer itemCode = pm3.getItemCode();
                SMonthlyFee cacheFee = tmpMap.get(itemCode);
                // 对每个itemcode取最大的baseVal
                if (cacheFee == null || CommonUtil.Long2long(cacheFee.getAmount()) < CommonUtil.Long2long(pm5.getBaseVal())){
                    SMonthlyFee mfee = new SMonthlyFee();
                    mfee.setPrice_id(pm1.getPriceId());
                    mfee.setItem_code(pm3.getItemCode());
                    mfee.setItem_name(pm6.getName());
                    Long amount = pm5.getBaseVal();
                    mfee.setAmount(amount);
                    if (prorateDate > 0){   
                        double temAmount = Math.round(amount*12.0/365)*prorateDate;
                        mfee.setProrated_amount(CommonUtil.double2Long(temAmount));
                    }else{
                        mfee.setProrated_amount(amount);
                    }
                    mfee.setMeasure_id(pm4.getMeasureId());
                    // 设置度量单位名称
                    @SuppressWarnings("deprecation")
                    SysMeasure measure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                            SysMeasure.Field.measureId, mfee.getMeasure_id()));
                    mfee.setMeasure_name(measure != null ? measure.getDescription() : null);
                    tmpMap.put(itemCode, mfee);
                }
            }
           return pricePlanFeeMap;
        }
        return null;
    }
    
    /**
     * 查询免费资源 wangyh3 2012-7-13
     * 
     * @param prorateDate 当月折算 
     */
    private Map<Integer,List<SFreeResource>> queryUnOrderFreeResList(Map<Integer,Integer> offerPricePlanMap, Date orderDate, int prorateDate)
    {
        imsLogger.debug("#####query free resource begin...#####");

        DBJoinCondition join = new DBJoinCondition(PmCompositeOfferPrice.class);
        join.innerJoin(PmComponentProdofferPrice.class, new DBRelation(PmCompositeOfferPrice.Field.priceId,
                PmComponentProdofferPrice.Field.priceId));
        join.innerJoin(PmAllowanceFreeresDetails.class, new DBRelation(PmComponentProdofferPrice.Field.priceId,
                PmAllowanceFreeresDetails.Field.priceId));
        join.innerJoin(PmAllowanceFreeresSegment.class, new DBRelation(PmAllowanceFreeresDetails.Field.priceId,
                PmAllowanceFreeresSegment.Field.priceId));
        join.innerJoin(SysEntityMultiLang.class, new DBRelation(PmAllowanceFreeresDetails.Field.freeresItem,
                SysEntityMultiLang.Field.entityValue));
        join.innerJoin(PmFreeUsageProperty.class, new DBRelation(SysEntityMultiLang.Field.entityValue,
                PmFreeUsageProperty.Field.assetItemId));

        List<Map> result = context.getComponent(BaseComponent.class).queryJoin(join, 
                new DBCondition(PmCompositeOfferPrice.Field.pricingPlanId,
                new ArrayList<Integer>(offerPricePlanMap.values()),Operator.IN),
                new DBCondition(PmCompositeOfferPrice.Field.offerSts, 
                new Integer[] { -1, 0 }, Operator.IN),
                new DBCondition(PmCompositeOfferPrice.Field.validDate, orderDate, Operator.LESS_EQUALS),
                new DBCondition(PmCompositeOfferPrice.Field.expireDate, orderDate, Operator.GREAT),
                new DBCondition(PmComponentProdofferPrice.Field.priceType, 3),
                new DBCondition(PmAllowanceFreeresSegment.Field.startValue, 1),
                new DBCondition(SysEntityMultiLang.Field.entityId,4003),
                new DBCondition(SysEntityMultiLang.Field.entityType,8006)
                );

        imsLogger.debug("#####query free resource finished.#####");

        Map<Integer,List<SFreeResource>> map = new HashMap<Integer, List<SFreeResource>>();
        if (!CommonUtil.isEmpty(result))
        {   
            imsLogger.debug("#####begin to query prorateType 半月折算率#####");
            Map<Integer,Integer> offerProrateMap = context.getComponent(BalanceShComponent.class).queryProrateTypeByOfferId(new ArrayList<Integer>(offerPricePlanMap.keySet()),mainOfferId);
            imsLogger.debug("#####end to query prorateType 半月折算率.#####");
            for (Map item : result)
            {
                PmCompositeOfferPrice pm1 = (PmCompositeOfferPrice) item.get(PmCompositeOfferPrice.class);
                // PmComponentProdofferPrice pm2 = (PmComponentProdofferPrice)
                // item.get(PmComponentProdofferPrice.class);
                PmAllowanceFreeresDetails pm3 = (PmAllowanceFreeresDetails) item.get(PmAllowanceFreeresDetails.class);
                PmAllowanceFreeresSegment pm4 = (PmAllowanceFreeresSegment) item.get(PmAllowanceFreeresSegment.class);
                SysEntityMultiLang pm5 = (SysEntityMultiLang) item.get(SysEntityMultiLang.class);
                PmFreeUsageProperty pm6 = (PmFreeUsageProperty) item.get(PmFreeUsageProperty.class);

                Integer pricePlanId = pm1.getPricingPlanId();
                Integer offerId = null;
                for(Integer key:offerPricePlanMap.keySet())
                {
                    Integer tempPricePlanId = offerPricePlanMap.get(key);
                    if(tempPricePlanId.intValue() == pricePlanId)
                        offerId = key;
                }
                SFreeResource resource = new SFreeResource();

                resource.setPrice_id(pm1.getPriceId());
                resource.setFreeres_item(pm3.getFreeresItem());
                resource.setFreeres_name(pm5.getShortName());
                resource.setAmount(pm4.getAmount());
                resource.setMeasure_id(pm6.getMeasureId());

                // 设置度量单位名称
                SysMeasure measure = DBConfigInitBean.getSingleCached(SysMeasure.class, new CacheCondition(
                        SysMeasure.Field.measureId, resource.getMeasure_id()));
                resource.setMeasure_name(measure != null ? measure.getDescription() : null);

                // 计算订购月折算后资源
                Long amount = pm4.getAmount();
                resource.setProrated_amount(amount);
                if(CommonUtil.isNotEmpty(offerProrateMap) && offerProrateMap.get(offerId) != null){
                    Integer prorateType = offerProrateMap.get(offerId);
                    imsLogger.debug("####销售品编号[", offerId, "]对应的半月折算规则为::::::#####",prorateType);
                    if (CommonUtil.int2Short(prorateType) == 1) // 1：表示半周期折扣（上半月订购送全部免费资源、下半月订购送一半）
                    {   
                        int prorateRate = prorateDate*12/365;
                        if (prorateRate >= 0.5)
                            resource.setProrated_amount(amount);
                        else
                            resource.setProrated_amount(amount / 2);
                    }
                    else if (CommonUtil.int2Short(prorateType) == 3) // 3:用于中国移动规范的按天折 国内使用)
                    {   
                        if(prorateDate > 0){
                            double temAmount = Math.ceil(amount*12.0/365);
                            resource.setProrated_amount(CommonUtil.double2Long(temAmount * prorateDate));
                        }else{
                            resource.setProrated_amount(amount);
                        }
                        
                    }
                }
                List<SFreeResource> freeResourceList = map.get(offerId);
                if(freeResourceList == null)
                {
                    freeResourceList = new ArrayList<SFreeResource>();
                    map.put(offerId, freeResourceList);
                }
                freeResourceList.add(resource);
            }
        }
        return map;
    }
}
