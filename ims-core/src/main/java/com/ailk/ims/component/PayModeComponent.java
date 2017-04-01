package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ResultTable;
import com.ailk.ims.complex.CharValueHelper;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsintf.entity.SBusiService;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;

/**
 * @Description:修改付费模式组件类
 * @author wangjt
 * @Date 2012-2-23
 * @Date 2012-3-27 tangjl5 BUG #42148 后付费转为hybrid 3,接口中若传入了time_period参数，time_period的值设置在特征规格值中，没有设置为0
 * @Date 2012-3-29 tangjl5 Task #42853 hybiredRule为2时，设置时段标志为0
 * @Date 2012-03-31 zengxr 付费模式hybrid rule2存储问题修复/修改新增策略只保存一条记录
 * @Date 2012-04-03 zengxr time period default value 0.
 * @Date 2012-04-07 zengxr if new hybrid rule is different with old, delete all old hybrid service
 * @Date 2012-04-10 yangjh getTime_period->setTime_segment
 * @Date 2012-04-20 新付费模式是混合付费 则销售品付费模式不是混合付费 抛出异常
 * @Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
 * @Date 2012-06-27 zhangzj3 [49128]Hybrid Rule1Rule3 have no servie list 去掉非空判断
 * @date 2012-07-10 luojb #50229 删除co_prod_valid,  co_prod 增加prod_valid_date，prod_expire_date
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class PayModeComponent extends BaseComponent
{
    /**
     * 增加表示付费模式的产品
     */
    public CoProd insertCoProd(Integer payMode, Long userId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        int busiFlag = SpecCodeDefine.PAYMODE;

        Integer productOfferingId = prodCmp.queryOfferingId(busiFlag);
        if (!CommonUtil.isValid(productOfferingId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCTOFFERINGID_ERROR, busiFlag);
        }
        Integer pricingPlanId = prodCmp.queryPricePlanId(productOfferingId, null, userId);
        if (pricingPlanId == null)
        {
            pricingPlanId = 0;
        }

        CoProd coProd = new CoProd();
        coProd.setProductId(DBUtil.getSequence(CoProd.class));
        coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
        coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
        coProd.setProductOfferingId(productOfferingId);
        coProd.setPricingPlanId(pricingPlanId);
        coProd.setBusiFlag(busiFlag);
        coProd.setBillingType(payMode);
        coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        coProd.setObjectId(userId);
        coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        
        //2012-07-10 luojb
        coProd.setValidDate(context.getRequestDate());
        coProd.setExpireDate(IMSUtil.getDefaultExpireDate());
        coProd.setProdValidDate(coProd.getValidDate());
        coProd.setProdExpireDate(coProd.getExpireDate());
        super.insert(coProd);
        
        return coProd;
    }

    /**
     * 增加付费模式对应的产品的一组规格特征值
     */
    private void insertCoProdCharValueList(CoProd dmProd, SBusiService sBusiService, Short hybridRule)
    {
        String validDate = IMSUtil.formatDate(context.getRequestDate());
        String expireDate = IMSUtil.formatDate(IMSUtil.getDefaultExpireDate());
        Long groupId = 0l;
        if (sBusiService != null)
        {
            groupId = CommonUtil.int2Long(sBusiService.getService_id());
            validDate = sBusiService.getValid_date();
            expireDate = sBusiService.getExpire_date();
        }

        // 2011-12-15 hunan add 增加notification
        String notificationValue = context.getComponent(ConfigQuery.class).getSpecCharValueById(
                SpecCodeDefine.PAYMODE_NOTI_RULE_NOTI);
        // groupId设定为service_id，方便查询

        CharValueHelper helper = new CharValueHelper(dmProd.getProductId(), groupId, validDate, expireDate, dmProd.getObjectId(),
                dmProd.getObjectType());

        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();

        list.add(helper.getNew(SpecCodeDefine.PAYMODE_HYBRID_RULE, String.valueOf(hybridRule)));
        //
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_RATING_SENARIO, sBusiService.getService_id()));
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_TIME_PERIOD, sBusiService.getTime_period()));
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_PAYMODE1, sBusiService.getPayment_mode()));
        //
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_CHANGE_FLAG, "1"));
        //
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_SERVICE_ID, sBusiService.getService_id()));
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_THRESHOLD, sBusiService.getThreshold()));
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_THRESHOLD_UNIT, ""));
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_PAYMODE3, sBusiService.getPayment_mode()));
        switch (hybridRule)
        {
        case 1:
            list.addAll(getHybrideRule1(sBusiService, helper));
            break;
        case 2:
            list.addAll(getHybrideRule2(sBusiService, helper, dmProd.getObjectId(), dmProd.getObjectType()));
            break;
        case 3:
            list.addAll(getHybrideRule3(sBusiService, helper, dmProd.getObjectId(), dmProd.getObjectType()));
            break;
        }
        // 2011-12-15 hunan add :
        list.add(helper.getNew(SpecCodeDefine.PAYMODE_NOTI_RULE_NOTI, notificationValue));
        super.insertBatch(list);
    }

    private List<CoProdCharValue> getHybrideRule1(SBusiService sBusiService, CharValueHelper helper)
    {
        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
        //@Date 2012-06-26 zhangzj3 [49128]Hybrid Rule1Rule3 have no servie list sBusiService可以为空
        if(sBusiService != null){
        	 list.add(helper.getNew(SpecCodeDefine.PAYMODE_RATING_SENARIO, sBusiService.getService_id()));
             // 2012-04-03 zengxr time period default value 0.
             // @Date 2012-04-10 yangjh getTime_period->setTime_segment
             if (sBusiService.getTime_segment() == null){
                 list.add(helper.getNew(SpecCodeDefine.PAYMODE_TIME_PERIOD, 0));
             }else{
                 list.add(helper.getNew(SpecCodeDefine.PAYMODE_TIME_PERIOD, sBusiService.getTime_segment()));
             }
             list.add(helper.getNew(SpecCodeDefine.PAYMODE_PAYMODE, sBusiService.getPayment_mode()));
        }
        return list;
    }

    private List<CoProdCharValue> getHybrideRule2(SBusiService sBusiService, CharValueHelper helper, Long objectId,
            Integer objectType)
    {
        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
        // list.add(helper.getNew(SpecCodeDefine.PAYMODE_HYBRID_RULE, 2));
        String paymodeRatingSenario = context.getComponent(ConfigQuery.class).getSpecCharValueById(
                SpecCodeDefine.PAYMODE_RATING_SENARIO);
        list.add(helper.getNew(SpecCodeDefine.PAYMODE_RATING_SENARIO, paymodeRatingSenario));
        // @Date 2012-3-29 tangjl5 Task #42853 hybiredRule为2时，设置时段标志为0
        list.add(helper.getNew(SpecCodeDefine.PAYMODE_TIME_PERIOD, 0));
        String paymode = context.getComponent(ConfigQuery.class).getSpecCharValueById(SpecCodeDefine.PAYMODE_PAYMODE);

        list.add(helper.getNew(SpecCodeDefine.PAYMODE_PAYMODE, paymode));
        return list;
    }

    private List<CoProdCharValue> getHybrideRule3(SBusiService sBusiService, CharValueHelper helper, Long objectId,
            Integer objectType)
    {
        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
        //@Date 2012-06-26 zhangzj3 [49128]Hybrid Rule1Rule3 have no servie list sBusiService可以为空
        if(sBusiService != null){
        	list.add(helper.getNew(SpecCodeDefine.PAYMODE_RATING_SENARIO, sBusiService.getService_id()));
            list.add(helper.getNew(SpecCodeDefine.PAYMODE_PAYMODE, sBusiService.getPayment_mode()));
            list.add(helper.getNew(SpecCodeDefine.PAYMODE_THRESHOLD, sBusiService.getThreshold()));
            // @Date 2012-3-27 tangjl5 BUG #42148 后付费转为hybrid 3,接口中若传入了time_period参数，time_period的值设置在特征规格值中，没有设置为0
            // @Date 2012-04-10 yangjh getTime_period->setTime_segment
            if (null != sBusiService.getTime_segment()){
                list.add(helper.getNew(SpecCodeDefine.PAYMODE_TIME_PERIOD, sBusiService.getTime_segment()));
            }else{
                list.add(helper.getNew(SpecCodeDefine.PAYMODE_TIME_PERIOD, 0));
            }
        }
        return list;
    }

    /**
     * 增加付费模式产品4表信息
     */
    public void createPayModeInfo(CoProd dmProd, Long userId, SBusiService sBusiService, Short hybridRule)
    {
        this.insertCoProdCharValueList(dmProd, sBusiService, hybridRule);
    }

    /**
     * 修改付费模式产品 2012-04-07 zengxr if delete data not null, insert the new data
     */
    public void modifyPayModeInfo(CoProd dmProd, SBusiService svc, Short hybridRule)
    {
        if (super.deleteByCondition_noInsert(CoProdCharValue.class, null,
                new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()), new DBCondition(
                        CoProdCharValue.Field.objectId, dmProd.getObjectId()),
                new DBCondition(CoProdCharValue.Field.groupId, svc.getService_id())) != null)
            this.insertCoProdCharValueList(dmProd, svc, hybridRule);
    }

    /**
     * 根据userID，删除保存付费模式产品<br>
     */
    public void deletePayModeInfoByUserId(Long userId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = prodCmp.queryProdListByUserId(userId, SpecCodeDefine.PAYMODE);
        if (!CommonUtil.isEmpty(prodList))
        {
            for (CoProd coProd : prodList)
            {
                this.deletePayModeProdByProdId(coProd);
            }
        }
    }

    /**
     * 删除付费模式产品
     */
    public void deletePayModeProdByProdId(CoProd dmProd)
    {
        super.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, dmProd.getProductId()), new DBCondition(
                CoProd.Field.objectId, dmProd.getObjectId()));
        super.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()),
                new DBCondition(CoProdCharValue.Field.objectId, dmProd.getObjectId()));
        //@Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
        context.getComponent(BaseProductComponent.class).modifyProdValid(dmProd.getProductId(), context.getRequestDate());
    }

    /**
     * 删除付费模式产品 2012-04-07 delete by service id
     */
    public void deletePayModeProdByProdIdAndServiceId(CoProd dmProd, SBusiService sBusiService)
    {
        super.deleteByCondition_noInsert(CoProdCharValue.class, null,
                new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()), new DBCondition(
                        CoProdCharValue.Field.objectId, dmProd.getObjectId()), new DBCondition(CoProdCharValue.Field.groupId,
                        sBusiService.getService_id()));
    }

    /**
     * @Description: Hybrid -> Hybrid rule2 first delete old service list
     * @param dmProd 2012-03-31 zengxr credit 2012-04-07 zengxr if new hybrid rule is different with old, delete all old hybrid
     *            service
     */
    public List<CoProdCharValue> deleteOldBusiServicesByProdIdNoInsert(CoProd dmProd, short hybridRule)
    {
        // first delete all other rule data
        List<CoProdCharValue> delDataList = super.deleteByCondition_noInsert(CoProdCharValue.class, null, new DBCondition(
                CoProdCharValue.Field.productId, dmProd.getProductId()), new DBCondition(CoProdCharValue.Field.specCharId,
                SpecCodeDefine.PAYMODE_HYBRID_RULE), new DBCondition(CoProdCharValue.Field.value, String.valueOf(hybridRule),
                Operator.NOT_EQUALS));
        if (delDataList != null)
        {
            // if new hybrid rule is different with old, delete all old hybrid service
            super.deleteByCondition_noInsert(CoProdCharValue.class, null,
                    new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()), new DBCondition(
                            CoProdCharValue.Field.objectId, dmProd.getObjectId()));
        }
        return delDataList;
    }

    // /**
    // * @Description: Hybrid -> Hybrid rule2 first delete old service list
    // * @param dmProd 2012-03-31 zengxr credit
    // */
    // public void deleteOldBusiServicesByProdId(CoProd dmProd)
    // {
    // super.deleteByCondition_noInsert(CoProdCharValue.class, null,
    // new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()), new DBCondition(
    // CoProdCharValue.Field.objectId, dmProd.getObjectId()));
    // }

    /**
     * @Description: get the payment mode product
     * @param userId
     * @return 2012-03-31 zengxr credit 2012-04-07 zengxr for List size able to be zero.
     */
    public CoProd queryProdByUserId(Long userId)
    {
        List<CoProd> resultList = context.getComponent(BaseProductComponent.class).queryProdListByUserId(userId,
                SpecCodeDefine.PAYMODE);
        if (resultList == null || resultList.size() == 0)
            return null;
        return resultList.get(0);
    }

    /**
     * 查询用户的某个服务的付费模式产品，不存在返回null<br>
     */
    public CoProd queryProdByUserIdAndServiceId(Long userId, Long serviceId)
    {
        ResultTable<CoProd> resultTable = new ResultTable<CoProd>(CoProd.Field.productId, new DBCondition(CoProd.Field.busiFlag,
                SpecCodeDefine.PAYMODE));// 需要返回的结果集的表

        resultTable.addCondTable(CoProdCharValue.Field.productId, new DBCondition(CoProdCharValue.Field.groupId, serviceId),
                new DBCondition(CoProdCharValue.Field.objectId, userId));// 条件表2

        List<CoProd> resultList = context.getComponent(BaseProductComponent.class).mergeProdList(
                context.getComponent(ResultComponent.class).getResultList(resultTable));
        return resultList == null ? null : resultList.get(0);

        // DBExistsCond invObjCond = new DBExistsCond(CoProdInvObj.Field.productId, CoProd.Field.productId);
        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectId, userId));
        // invObjCond.addCondition(new DBCondition(CoProdInvObj.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        //
        // DBExistsCond charValueCond = new DBExistsCond(CoProdCharValue.Field.productId, CoProd.Field.productId);
        // charValueCond.addCondition(new DBCondition(CoProdCharValue.Field.groupId, serviceId));
        //
        // List<DBCondition> mainConds = new ArrayList<DBCondition>();
        // mainConds.add(new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.PAYMODE));
        //
        // List<CoProd> prodList = super.queryExists(mainConds, invObjCond, charValueCond);
        // return prodList == null ? null : prodList.get(0);
    }

    /**
     * 修改用户的除了<付费模式产品、主产品>之外所有产品的付费模式
     * 
     * @param needCreateNewMainProd:如果为true，则还需要判断这个用户的主产品是否是在本次请求中新增加的，<br>
     *            如果不是，则需要删除这个原来的主产品，根据首次激活的配置，新建一个主产品
     */
    public void changeProdPayMode4User(Long userId, Short newMode, boolean needCreateNewMainProd)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = prodCmp.queryProdListByUserId(userId);
        if (CommonUtil.isEmpty(prodList))
        {
            return;
        }

        for (int i = 0; i < prodList.size(); i++)
        {
            CoProd coProd = prodList.get(i);
            if (coProd.getBusiFlag() == null || SpecCodeDefine.isSpecialProd(coProd.getBusiFlag()))
            {
                continue;
            }
            if (coProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)// 如果是主产品，
            {
                if (needCreateNewMainProd)// 并且需要新建主产品
                {
                    // 本次请求中，删除的主产品(可能为null)
                    CoProd deletedProd = context.getSingleCache(CoProd.class,
                            new CacheCondition(CoProd.Field.soNbr, context.getSoNbr()), new CacheCondition(
                                    CoProd.Field.isMain, EnumCodeDefine.PRODUCT_MAIN), new CacheCondition(
                                    CoProd.Field.expireDate, context.getRequestDate()));

                    // 并且该主产品不是在本次请求中增加的（如果在本次请求中删除的主产品==null，则必然在本次请求中没有增加主产品）
                    if (deletedProd == null)
                    {
                        Integer productOfferingId = context.getComponent(RuleComponent.class).getChgOfferIdByUserId(userId);
                        // 实际应用中，返回的productOfferingId都是存在的
                        if (!CommonUtil.isValid(productOfferingId))
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.PAYMODE_USER_HAS_NO_DEFAULT_OFFERID, userId);
                        }
                        SProductOrder sProductOrder = new SProductOrder();
                        sProductOrder.setOffer_id(CommonUtil.int2Long(productOfferingId));
                        sProductOrder.setValid_type((short) EnumCodeDefine.PROD_VALID_IMMEDIATELY);
                        sProductOrder.setUser_id(userId);
                        checkMainProdByChangePayMode(newMode, productOfferingId);
                        // 删除这个原来的主产品(包括关联的相关信息)
                        prodCmp.deleteProdImmediate(coProd);

                        prodCmp.createProductOrder(sProductOrder);
                    }
                    else
                    {
                        checkMainProdByChangePayMode(newMode, coProd.getProductOfferingId());
                    }
                    continue;// 继续处理下一个产品
                }
                else
                {
                    checkMainProdByChangePayMode(newMode, coProd.getProductOfferingId());
                }
            }
            if (coProd.getBillingType() == null || coProd.getBillingType().shortValue() != newMode)
            {
                CoProd value = new CoProd();
                value.setBillingType(CommonUtil.short2Int(newMode));
                super.updateByCondition(value, new DBCondition(CoProd.Field.productId, coProd.getProductId()), new DBCondition(
                        CoProd.Field.objectId, coProd.getObjectId()));
            }
        }
    }

    /**
     * 用户的支付账户没有变更，那么，对于用户原来订购的产品，付费模式需要分两段， 一段为当前时间到下周期，<BR>
     * 其付费模式还是用户原来的付费模式，另一段生效时间为下周期开始时间， 失效时间为用户现有的失效时间，其付费模式，为新的付费模式。<BR>
     * liuyang8 2012-1-11 luojb 2012-3-17 预转后、混转后时 修改产品付费模式pre-post 下周期生效，修改产品账期deduct_flag下周期生效
     */

    public void changeProdBillingType(Short oldMode, Short newMode, Long userId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prodList = null;

        if (oldMode == EnumCodeDefine.USER_PAYMODE_HYBRID)
        {
            prodList = prodCmp.queryPrePaidProdList(userId);// 查询预付产品
        }
        else if (oldMode == EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            prodList = prodCmp.queryProdListByUserId(userId);
        }
        if (CommonUtil.isEmpty(prodList))
        {
            return;
        }
        for (CoProd coProd : prodList)
        {
            // 主产品会被删除或者替换
            if (coProd.getIsMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                continue;
            }
            prodCmp.changePayMode(coProd, newMode);
        }

    }

    /**
     * @Description 验证产品付费模式
     * @Author lijingcheng
     * @param payMode 新的付费模式
     * @param offerId 产品（更换了就是新的）
     */
    public void checkMainProdByChangePayMode(Short payMode, Integer offerId)
    {
        Integer billType = context.getComponent(ProductQuery.class).queryProdOfferingBillingType(offerId.longValue());
        if (billType == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_OFFERING_ATTRIBUTE_IS_NULL, offerId);
        }
        if (payMode == null)
        {
            return;
        }
        // 新付费模式是预付费 则销售品付费模式是后付费 抛出异常
        if (payMode == EnumCodeDefine.USER_PAYMODE_PREPAID)
        {
            if (billType == EnumCodeDefine.PROD_BILLTYPE_POSTPAID)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER, offerId);
            }
            // 新付费模式是后付费 则销售品付费模式是预付费 抛出异常
        }
        else if (payMode == EnumCodeDefine.USER_PAYMODE_POSTPAID)
        {
            if (billType == EnumCodeDefine.PROD_BILLTYPE_PREPAID)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER, offerId);
            }
            // 新付费模式是混合付费 则销售品付费模式不是混合付费 抛出异常
        }
        else if (payMode == EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID)
        {
            if (billType != EnumCodeDefine.PROD_BILLTYPE_ALL)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER, offerId);
            }
            // /新付费模式是混合付费 则销售品付费模式不是混合付费 抛出异常
        }
        else if (payMode == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID)
        {
            if (billType != EnumCodeDefine.PROD_BILLTYPE_ALL)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER, offerId);
            }
            // @Date 2012-04-20 新付费模式是混合付费 则销售品付费模式不是混合付费 抛出异常
        }
        else if (payMode == EnumCodeDefine.USER_PAYMODE_HYBRID)
        {
            if (billType != EnumCodeDefine.PROD_BILLTYPE_ALL)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_OWNER_PRODUCT_BILLINGTYPE_NOT_MATCHING_USER, offerId);
            }
        }
    }

}
