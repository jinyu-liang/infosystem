package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ResultTable;
import com.ailk.ims.complex.CharValueHelper;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.imsintf.entity.SPaySplitInfo;
import com.ailk.openbilling.persistence.imsintf.entity.SUserPaySplitRel;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;

/**
 * @Description:代付月租费的组件类
 * @author wangjt
 * @Date 2012-3-37 tangjl5 查询split产品时，value为产品ID时，value为产品ID时，specCharId应该为12813
 * @Date 2012-4-3 tangjl5 若传入的生效时间小于请求时间，则修改为请求时间
 * @Date 2012-4-9 xieqr 增加代付方式特征值 SPLIT_METHOD = 12815; //代付方式 0：一般代付（默认） 1：统付
 * @Date 2012-4-10 wangjt 优化： isSplit方法
 * @Date 2012-4-25 yangjh querySplit增加账户ID/用户Id的查询条件
 * @Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
 * @Date 2012-07-07 zhangzj3 [49833]取消分账关系延迟生效
 * @date 2012-07-10 luojb #50229 删除co_prod_valid, co_prod 增加prod_valid_date，prod_expire_date
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @Date 2012-08-21 lijc3  查询被代付着信息直接从db中查询
 * @Date 2012-08-28 lijc3 用户级代付设置被代付账户默认值为0
 * @Date 2012-08-30 lijc3 删除queryBeSplitedSprodByAcctId等类似无调用方法
 * @date 2012-11-19 zhangzj3 增加分表参数
 */
/**
 * @author fish_cs
 *
 */
public class SplitComponent extends BaseComponent
{
    private final String DENOMINATOR_MIN = "1";
    private final String DENOMINATOR_MAX = "100";
    private final String DEFAULT_AMOUNT = "-1";

    public SplitComponent()
    {
    }

    /**
     * 增加产品
     */
    private Long insertCoProd(SPaySplitInfo splitInfo)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        Integer offeringId = prodCmp.queryOfferingId(SpecCodeDefine.SPLIT);
        if (!CommonUtil.isValid(offeringId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCTOFFERINGID_ERROR, SpecCodeDefine.SPLIT);
        }
        Integer pricingPlanId = prodCmp.queryPricePlanId(offeringId, splitInfo.getPay_acct_id(), null);// 为账户级产品
        if (pricingPlanId == null)
        {
            pricingPlanId = 0;
        }

        CoProd coProd = new CoProd();
        coProd.setProductId(DBUtil.getSequence(CoProd.class));
        coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);// ProductComponent
        coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);// Active
        coProd.setProductOfferingId(offeringId);
        coProd.setPricingPlanId(pricingPlanId);
        coProd.setBusiFlag(SpecCodeDefine.SPLIT);
        coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_PREPAID);
        coProd.setValidDate(IMSUtil.formatDate(splitInfo.getValid_date(), context.getRequestDate()));
        coProd.setExpireDate(IMSUtil.formatExpireDate(splitInfo.getExpire_date()));
        // ljc add 2011-10.05
        coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);// 非主产品
        coProd.setObjectId(splitInfo.getPay_acct_id());
        coProd.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        // 2012-07-10 luojb
        coProd.setProdValidDate(coProd.getValidDate());
        coProd.setProdExpireDate(coProd.getExpireDate());

        super.insert(coProd);
        return coProd.getProductId();
    }

    /**
     * caohm5 add 2012-04-28 优化后split特征值存储在CO_SPLIT_CHAR_VALUE表里面
     * 
     * @param prodId 产品编号
     * @param sPaySplitInfo 分账信息实体
     * @param objectType 被分账标示
     * @param objectId 被分账目标
     * @param sUserPaySplitRel 分账关系实体
     */
    private void insertSplitProdCharValueList(Long prodId, SPaySplitInfo sPaySplitInfo, String objectType, String objectId,
            SUserPaySplitRel sUserPaySplitRel)
    {
        String validDate = sPaySplitInfo.getValid_date();
        String expireDate = sPaySplitInfo.getExpire_date();
        Short splitMethod = sPaySplitInfo.getSplit_method();
        Long groupId = 0L;
        List<CoSplitCharValue> list = new ArrayList<CoSplitCharValue>();
        CharValueHelper helper = new CharValueHelper(prodId, groupId, validDate, expireDate, sPaySplitInfo.getPay_acct_id(),
                EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        Short partType = sPaySplitInfo.getPart_type();
        Short splitType = sUserPaySplitRel.getSplit_type();
        // 科目级和产品级特征值说明：产品编号、价格计划、分账类型
        // 科目级分账
        if (splitType == EnumCodeDefine.SPLIT_TYPE_ITEM)
        {
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PRICE_RULE_ID, sUserPaySplitRel.getPrice_rule_id())));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PRODUCT_ID, null)));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_TYPE, EnumCodeDefine.SPLIT_TYPE_ITEM)));
        }
        // 产品级分账
        else if (splitType == EnumCodeDefine.SPLIT_TYPE_PRODUCT)
        {
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_TYPE, EnumCodeDefine.SPLIT_TYPE_PRODUCT)));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PRODUCT_ID, sUserPaySplitRel.getProduct_sequence_id())));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PRICE_RULE_ID, null)));
        }
        // 百分百和数量特征值说明：限额、分子、分母
        // 分账百分比
        if (partType == EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE)
        {
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_AMOUNT, DEFAULT_AMOUNT)));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_NUMERATOR, sPaySplitInfo.getPart_value())));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_DENOMINATOR, DENOMINATOR_MAX)));
        }
        // 分账数量
        else if (partType == EnumCodeDefine.PAY_PART_TYPE_AMOUNT)
        {
            // 货币单位转换
            Integer measureId = sPaySplitInfo.getMeasure_id();
            // @Date 2012-5-11 tangjl5 根据传入的measure_id转化为数据库中存在的measure，必须传入源measure_id
            // measureId = AmountUtil.getDbMeasureId(measureId);
            Double amount = sPaySplitInfo.getPart_value();
            // @Date 2012-7-12 tangjl5 Story #49447 split需要转换为账务侧的measure_id和amount进行存储
            Double dbAmount = context.getComponent(AmountComponent.class).parseBillingAmount(measureId, amount,
                    sPaySplitInfo.getPay_acct_id());
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_AMOUNT, dbAmount)));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_MEASURE_ID,
                    context.get3hTree().loadAcct3hBean(sPaySplitInfo.getPay_acct_id()).getAccount().getMeasureId())));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_NUMERATOR, DENOMINATOR_MIN)));
            list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_DENOMINATOR, DENOMINATOR_MIN)));
        }
        //@Date 2012-08-28 lijc3 用户级代付设置被代付账户默认值为0
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PAID_ACCT_ID, 0)));
        // 被分账标示和对象
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_OBJECT_TYPE, objectType)));
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_OBJECT_ID, objectId)));
        // 优先级
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PRIORITY, sPaySplitInfo.getPriority())));
        // 构建通知特征值
        ConfigQuery cfgCmp = context.getComponent(ConfigQuery.class);
        String notiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI);
        String bePaidSideNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI);
        String failNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.SPLIT_PAY_FAIL_BAR);
        String bePaidSideFailNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR);

        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI, notiValue)));
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI, bePaidSideNotiValue)));
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_PAY_FAIL_BAR, failNotiValue)));
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR, bePaidSideFailNotiValue)));

        // 代付方式 0：一般代付（默认） 1：统付
        list.add(prodCharToSplitChar(helper.getNew(SpecCodeDefine.SPLIT_METHOD, splitMethod)));

        super.insertBatch(list);
    }

    /**
     * zenglu add 2012-07-02 实例化分账关系表 CO_SPLIT_PAY_REL
     * 
     * @param prodId 产品编号
     * @param sPaySplitInfo 分账信息实体
     * @param objectType 被分账标示
     * @param objectId 被分账目标
     */
    private void insertCoSplitPayRel(Long prodId, Long acctId, Long userId, SPaySplitInfo sPaySplitInfo, String objectType,
            String objectId)
    {
        CoSplitPayRel splitRelObj = new CoSplitPayRel();
        splitRelObj.setProductId(prodId);
        if (acctId != null)
        {
            splitRelObj.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
            splitRelObj.setObjectId(acctId);
        }
        else if (userId != null)
        {
            splitRelObj.setObjectType(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
            splitRelObj.setObjectId(userId);
        }
        splitRelObj.setReguidObjectId(sPaySplitInfo.getPay_acct_id());
        splitRelObj.setReguidObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        splitRelObj.setValidDate(IMSUtil.formatDate(sPaySplitInfo.getValid_date(), context.getRequestDate()));
        splitRelObj.setExpireDate(IMSUtil.formatExpireDate(sPaySplitInfo.getExpire_date()));

        super.insert(splitRelObj);

    }

    /**
     * 增加账户代付信息
     */
    public void createSplit4Account(Long acctId, SPaySplitInfo sPaySplitInfo, SUserPaySplitRel sUserPaySplitRel)
    {
        Long prodId = this.insertCoProd(sPaySplitInfo);

        String objectType = String.valueOf(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        String objectId = acctId.toString();
        this.insertCoSplitPayRel(prodId, acctId, null, sPaySplitInfo, objectType, objectId);
        this.insertSplitProdCharValueList(prodId, sPaySplitInfo, objectType, objectId, sUserPaySplitRel);
    }

    /**
     * 增加用户代付信息
     */
    public void createSplit4User(Long userId, SPaySplitInfo sPaySplitInfo, SUserPaySplitRel sUserPaySplitRel)
    {
        Long prodId = this.insertCoProd(sPaySplitInfo);

        String objectType = String.valueOf(EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        String objectId = userId.toString();
        this.insertCoSplitPayRel(prodId, null, userId, sPaySplitInfo, objectType, objectId);
        this.insertSplitProdCharValueList(prodId, sPaySplitInfo, objectType, objectId, sUserPaySplitRel);

    }

    /**
     * caohm5 add 2012-04-28 优化后的更新方法
     */
    public void updateSplit(Long prodId, SPaySplitInfo sPaySplitInfo)
    {
        String validDate = sPaySplitInfo.getValid_date();
        String expireDate = sPaySplitInfo.getExpire_date();

        Long objectId = sPaySplitInfo.getPay_acct_id();
        Integer objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        CoProd coProd = new CoProd();
        CoSplitPayRel coSplitPayRel = new CoSplitPayRel();
        // 生效时间取“当前时候”、“结构中生效时间”较早者
        if (CommonUtil.isValid(validDate))
        {
            if (DateUtil.getFormattedDate(validDate).before(context.getRequestDate()))
            {
                validDate = DateUtil.formatDate(context.getRequestDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
            }
            coProd.setValidDate(DateUtil.getFormattedDate(validDate));
            coSplitPayRel.setValidDate(DateUtil.getFormattedDate(validDate));
        }
      //2012-09-26 分账查询报productid为空
        coProd.setProductId(prodId);
        if (CommonUtil.isValid(expireDate))
        {
            coProd.setExpireDate(IMSUtil.formatExpireDate(expireDate));
            coSplitPayRel.setExpireDate(IMSUtil.formatExpireDate(expireDate));
            // @Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
            context.getComponent(BaseProductComponent.class).modifyProdValid(coProd.getProductId(),
                    IMSUtil.formatExpireDate(expireDate));
        }
        // CoProd coProdWhere = new CoProd();
        // coProdWhere.setProductId(prodId);
        // coProdWhere.setObjectId(objectId);
        // 更新产品表CO_PROD
        // updateByCondition(coProd, coProdWhere);
        updateByCondition(coProd, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(CoProd.Field.objectId,
                objectId), new DBCondition(CoProd.Field.objectType, objectType));
        // 更新分账关系表CO_SPLIT_PAY_REL
        updateByCondition(coSplitPayRel, new DBCondition(CoSplitPayRel.Field.productId, prodId), new DBCondition(
                CoSplitPayRel.Field.reguidObjectId, objectId), new DBCondition(CoSplitPayRel.Field.reguidObjectType, objectType));
        // 更新产品特征值表CO_SPLIT_CHAR_VALUE
        Long groupId = 0L;
        CharValueHelper helper = new CharValueHelper(prodId, groupId, validDate, expireDate, objectId, objectType);
        // 优先级
        updateByCondition(prodCharToSplitChar(helper.getUpdate(sPaySplitInfo.getPriority())), new DBCondition(
                CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.groupId, groupId),
                new DBCondition(CoSplitCharValue.Field.objectId, objectId), new DBCondition(CoSplitCharValue.Field.objectType,
                        objectType), new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRIORITY));
        // updateByCondition(prodCharToSplitChar(helper.getUpdate(sPaySplitInfo.getPriority())),
        // prodCharToSplitChar(helper.getCond(SpecCodeDefine.SPLIT_PRIORITY)));
        Short partType = sPaySplitInfo.getPart_type();
        // 百分百分账
        if (partType == EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE)
        {
            updateByCondition(prodCharToSplitChar(helper.getUpdate(DEFAULT_AMOUNT)), new DBCondition(
                    CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.groupId, groupId),
                    new DBCondition(CoSplitCharValue.Field.objectId, objectId), new DBCondition(
                            CoSplitCharValue.Field.objectType, objectType), new DBCondition(CoSplitCharValue.Field.specCharId,
                            SpecCodeDefine.SPLIT_AMOUNT));
            updateByCondition(prodCharToSplitChar(helper.getUpdate(sPaySplitInfo.getPart_value())), new DBCondition(
                    CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.groupId, groupId),
                    new DBCondition(CoSplitCharValue.Field.objectId, objectId), new DBCondition(
                            CoSplitCharValue.Field.objectType, objectType), new DBCondition(CoSplitCharValue.Field.specCharId,
                            SpecCodeDefine.SPLIT_NUMERATOR));
            updateByCondition(prodCharToSplitChar(helper.getUpdate(DENOMINATOR_MAX)), new DBCondition(
                    CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.groupId, groupId),
                    new DBCondition(CoSplitCharValue.Field.objectId, objectId), new DBCondition(
                            CoSplitCharValue.Field.objectType, objectType), new DBCondition(CoSplitCharValue.Field.specCharId,
                            SpecCodeDefine.SPLIT_DENOMINATOR));
            // updateByCondition(prodCharToSplitChar(helper.getUpdate(DEFAULT_AMOUNT)),
            // prodCharToSplitChar(helper.getCond(SpecCodeDefine.SPLIT_AMOUNT)));
            // updateByCondition(prodCharToSplitChar(helper.getUpdate(sPaySplitInfo.getPart_value())),
            // prodCharToSplitChar(helper.getCond(SpecCodeDefine.SPLIT_NUMERATOR)));
            // updateByCondition(prodCharToSplitChar(helper.getUpdate(DENOMINATOR_MAX)),
            // prodCharToSplitChar(helper.getCond(SpecCodeDefine.SPLIT_DENOMINATOR)));
        }
        // 金额分账
        else if (partType == EnumCodeDefine.PAY_PART_TYPE_AMOUNT)
        {
            // @Date 2012-7-12 tangjl5 Story #49447 split需要转换为账务侧的measure_id和amount进行存储
            Double dbAmount = context.getComponent(AmountComponent.class).parseBillingAmount(sPaySplitInfo.getMeasure_id(),
                    sPaySplitInfo.getPart_value(), sPaySplitInfo.getPay_acct_id());
            updateByCondition(prodCharToSplitChar(helper.getUpdate(dbAmount)), new DBCondition(CoSplitCharValue.Field.productId,
                    prodId), new DBCondition(CoSplitCharValue.Field.groupId, groupId), new DBCondition(
                    CoSplitCharValue.Field.objectId, objectId), new DBCondition(CoSplitCharValue.Field.objectType, objectType),
                    new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_AMOUNT));
            updateByCondition(prodCharToSplitChar(helper.getUpdate(DENOMINATOR_MIN)), new DBCondition(
                    CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.groupId, groupId),
                    new DBCondition(CoSplitCharValue.Field.objectId, objectId), new DBCondition(
                            CoSplitCharValue.Field.objectType, objectType), new DBCondition(CoSplitCharValue.Field.specCharId,
                            SpecCodeDefine.SPLIT_NUMERATOR));
            updateByCondition(prodCharToSplitChar(helper.getUpdate(DENOMINATOR_MIN)), new DBCondition(
                    CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.groupId, groupId),
                    new DBCondition(CoSplitCharValue.Field.objectId, objectId), new DBCondition(
                            CoSplitCharValue.Field.objectType, objectType), new DBCondition(CoSplitCharValue.Field.specCharId,
                            SpecCodeDefine.SPLIT_DENOMINATOR));
            // updateByCondition(prodCharToSplitChar(helper.getUpdate(sPaySplitInfo.getPart_value())),
            // prodCharToSplitChar(helper.getCond(SpecCodeDefine.SPLIT_AMOUNT)));
            // updateByCondition(prodCharToSplitChar(helper.getUpdate(DENOMINATOR_MIN)),
            // prodCharToSplitChar(helper.getCond(SpecCodeDefine.SPLIT_NUMERATOR)));
            // updateByCondition(prodCharToSplitChar(helper.getUpdate(DENOMINATOR_MIN)),
            // prodCharToSplitChar(helper.getCond(SpecCodeDefine.SPLIT_DENOMINATOR)));
        }
    }

    /**
     * 判断账户代付是否存在
     */
    public boolean isAccountSplitExist(Long payAcctId, Long acctId, SUserPaySplitRel sUserPaySplitRel)
    {
        return querySplit4Account(payAcctId, acctId, sUserPaySplitRel) != null;
    }

    /**
     * 判断用户代付是否存在
     */
    public boolean isUserSplitExist(Long payAcctId, Long userId, SUserPaySplitRel sUserPaySplitRel)
    {
        return querySplit4User(payAcctId, userId, sUserPaySplitRel) != null;
    }

    /**
     * 查询账户代付信息
     */
    public Long querySplit4Account(Long payAcctId, Long acctId, SUserPaySplitRel sUserPaySplitRel)
    {
        return querySplit(payAcctId, sUserPaySplitRel, acctId, null);
    }

    /**
     * 查询用户代付信息
     */
    public Long querySplit4User(Long payAcctId, Long userId, SUserPaySplitRel sUserPaySplitRel)
    {
        return querySplit(payAcctId, sUserPaySplitRel, null, userId);
    }

    /**
     * 删除split信息
     */
    public void deleteSplitInfoByProdId(Long prodId)
    {
        /**
         * @Date 2012-07-07 zhangzj3 [49833]取消分账关系延迟生效 1) 若是预扣产品，则该删除关系下周期生效。即取产品的下个first bill date做为split的失效时间。 2)
         *       若是后付产品或者不扣，则该删除关系立即生效。
         */
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        // 先获取操作类型是产品还是科目
        CoSplitCharValue splitTypeCharValue = querySingle(CoSplitCharValue.class, new DBCondition(
                CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.specCharId,
                SpecCodeDefine.SPLIT_TYPE), new DBCondition(CoSplitCharValue.Field.value, EnumCodeDefine.SPLIT_TYPE_PRODUCT));
        if (splitTypeCharValue != null && splitTypeCharValue.getValue() != null)
        {
            // 获取分账的产品id
            CoSplitCharValue prodCharValue = querySingle(CoSplitCharValue.class, new DBCondition(
                    CoSplitCharValue.Field.productId, prodId), new DBCondition(CoSplitCharValue.Field.specCharId,
                    SpecCodeDefine.SPLIT_PRODUCT_ID));
            if (prodCharValue != null && prodCharValue.getValue() != null)
            {
                CoProd prod = prodCmp.loadProd(CommonUtil.string2Long(prodCharValue.getValue()));
                PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class)
                        .queryPmCompsiteDeductRuleByOfferId(prod.getProductOfferingId(), prod.getBillingType());
                if (deductRule != null)
                {
                    if (deductRule.getDeductFlag() == EnumCodeDefine.BILLING_CYCLE_DEDUCT_FLAG_PREPAID)
                    {
                        // 获取当前账期的结束时间
                        ProductCycleComponent prodCycleCmp = this.context.getComponent(ProductCycleComponent.class);
                        CoProdBillingCycle billCycle = prodCycleCmp.queryFirstBillCycle(prod, null);
                        Date next_first_bill_date = prodCycleCmp.getCurrentBillCycleEndDate(billCycle);
                        if (next_first_bill_date != null)
                        {
                            // 更新产品表
                            CoProd coProd = new CoProd();
                            coProd.setExpireDate(next_first_bill_date);
                            updateByCondition(coProd, new DBCondition(CoProd.Field.productId, prodId));
                            // 修改产品有效期
                            context.getComponent(BaseProductComponent.class).modifyProdValid(prodId, next_first_bill_date);
                            // 更新分账关系表CO_SPLIT_PAY_REL
                            CoSplitPayRel coSplitPayRel = new CoSplitPayRel();
                            coSplitPayRel.setExpireDate(next_first_bill_date);
                            updateByCondition(coSplitPayRel, new DBCondition(CoSplitPayRel.Field.productId, prodId));
                            // 更新产品特征值表CO_SPLIT_CHAR_VALUE
                            CoSplitCharValue coSplitCharValue = new CoSplitCharValue();
                            coSplitCharValue.setExpireDate(next_first_bill_date);
                            updateByCondition(coSplitCharValue, new DBCondition(CoSplitCharValue.Field.productId, prodId));
                        }
                    }
                    else if (deductRule.getDeductFlag() == EnumCodeDefine.BILLING_CYCLE_DEDUCT_FLAG_POSTPAID
                            || deductRule.getDeductFlag() == EnumCodeDefine.BILLING_CYCLE_DEDUCT_FLAG_NO_DEDUCT)
                    {
                        prodCmp.deleteProdById(prodId);
                        // @Date 2012-07-03 zenglu 增加处理CO_SPLIT_PAY_REL
                        prodCmp.deleteSplitPayRel(prodId);
                        // @Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
                        prodCmp.modifyProdValid(prodId, context.getRequestDate());
                        this.deleteProdSplitCharValueByProdId(prodId);
                    }
                }
            }
        }
        else
        {
            prodCmp.deleteProdById(prodId);
            // @Date 2012-07-03 zenglu 增加处理CO_SPLIT_PAY_REL
            prodCmp.deleteSplitPayRel(prodId);
            // @Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
            prodCmp.modifyProdValid(prodId, context.getRequestDate());
            this.deleteProdSplitCharValueByProdId(prodId);
        }
    }

    /**
     * 根据objectId、object_type 查询其订购的分账产品，查分账关系表CO_SPLIT_PAY_REL
     * 
     * @author zenglu
     * @return
     * @date 2012-07-09
     */
    public List<CoProd> queryProdsByObjectSplit(Long objectId, Long objectType)
    {
        List<CoSplitPayRel> splitRelList = query(CoSplitPayRel.class, new DBCondition(CoSplitPayRel.Field.objectId, objectId),
                new DBCondition(CoSplitPayRel.Field.objectType, objectType));

        List<CoProd> coProdList = new ArrayList<CoProd>();

        if (CommonUtil.isNotEmpty(splitRelList))
        {
            BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
            for (int i = 0; i < splitRelList.size(); i++)
            {   
                //@date 2012-11-19 zhangzj3 增加分表参数
                CoSplitPayRel rel = splitRelList.get(i);
                Long prodId = rel.getProductId();
                IMSUtil.removeRouterParam();
                if(rel.getReguidObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT){
                    IMSUtil.setAcctRouterParam(rel.getReguidObjectId());
                }else if(rel.getReguidObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_DEV){
                    IMSUtil.setUserRouterParam(rel.getReguidObjectId());
                }
                coProdList.add(prodCmp.queryProd(prodId));
            }
        }
        return coProdList;
    }
    
    /**
     * 根据objectId、object_type  查分账关系表CO_SPLIT_PAY_REL
     * @author zenglu
     * @param objectId
     * @param objectType
     * @return
     */
    public List<CoSplitPayRel> querySplitPayRel(Long objectId, Long objectType)
    {
        return query(CoSplitPayRel.class, new DBCondition(CoSplitPayRel.Field.objectId, objectId),
                new DBCondition(CoSplitPayRel.Field.objectType, objectType));
    }

    /**
     * 根据objectId、object_type、时间点date 查分账关系表CO_SPLIT_PAY_REL
     * 
     * @author zenglu
     * @return
     * @date 2012-07-04
     */
    public List<CoSplitPayRel> querySplitPayRelByDate(Long objectId, Long objectType, Date date)
    {
        return query(CoSplitPayRel.class, new DBCondition(CoSplitPayRel.Field.objectId, objectId),
                new DBCondition(CoSplitPayRel.Field.objectType, objectType), new DBCondition(CoSplitPayRel.Field.expireDate,
                        date, Operator.GREAT), new DBCondition(CoSplitPayRel.Field.validDate, date, Operator.LESS_EQUALS));
    }

    /**
     * @author yanchuan 查询帐户或用户是否存在代付关系 2012-2-10
     * @return 该帐户存在代付关系，存在则返回true，不存在则返回false
     */
    public boolean isSplit(int objectType, Long objectId)
    {
        List<CoProd> prod_list = context.getComponent(ProductQuery.class).queryProdList(objectId, objectType,
                SpecCodeDefine.SPLIT);
        return CommonUtil.isNotEmpty(prod_list);
    }

    /**
     * @author yanchuan 查询帐户或用户是否存在被代付关系 2012-2-10
     * @return 该帐户存在被代付关系，存在则返回true，不存在则返回false
     */
    public boolean isBeSplit(int objectType, Long ObjectId)
    {

        List<CoProd> coProdList = null;
        coProdList = queryProdsByObjectSplit(ObjectId, new Long(objectType));
        return CommonUtil.isNotEmpty(coProdList);
    }

    /**
     * caohm5 类型转换
     */
    private CoSplitCharValue prodCharToSplitChar(CoProdCharValue coProdCharValue)
    {
        if (coProdCharValue == null)
        {
            return null;
        }
        CoSplitCharValue coSplitCh = new CoSplitCharValue();
        if (coProdCharValue.getProductId() != null)
            coSplitCh.setProductId(coProdCharValue.getProductId());
        if (coProdCharValue.getGroupId() != null)
            coSplitCh.setGroupId(coProdCharValue.getGroupId());
        if (coProdCharValue.getObjectId() != null)
            coSplitCh.setObjectId(coProdCharValue.getObjectId());
        if (coProdCharValue.getObjectType() != null)
            coSplitCh.setObjectType(coProdCharValue.getObjectType());
        if (coProdCharValue.getSoDate() != null)
            coSplitCh.setSoDate(coProdCharValue.getSoDate());
        if (coProdCharValue.getSoNbr() != null)
            coSplitCh.setSoNbr(coProdCharValue.getSoNbr());
        if (coProdCharValue.getSpecCharId() != null)
            coSplitCh.setSpecCharId(coProdCharValue.getSpecCharId());
        if (coProdCharValue.getValue() != null)
            coSplitCh.setValue(coProdCharValue.getValue());
        if (coProdCharValue.getCreateDate() != null)
            coSplitCh.setCreateDate(coProdCharValue.getCreateDate());
        if (coProdCharValue.getExpireDate() != null)
            coSplitCh.setExpireDate(coProdCharValue.getExpireDate());
        if (coProdCharValue.getValidDate() != null)
            coSplitCh.setValidDate(coProdCharValue.getValidDate());
        return coSplitCh;
    }

    /**
     * caohm5 特征值删除 只有在split时候用;所以不把它放在产品组件里面
     */
    public void deleteProdSplitCharValueByProdId(Long prodId)
    {
        super.deleteByCondition(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.productId, prodId));
    }

    /**
     * @author caohm5
     * @date 2012-05-01
     * @param payAcctId支付账户编号
     * @param sUserPaySplitRel分账关系实体
     * @param acctId被分账账户编号
     * @param userId被分账用户编号
     * @return
     */
    private Long querySplit(Long payAcctId, SUserPaySplitRel sUserPaySplitRel, Long acctId, Long userId)
    {
        Short splitType = sUserPaySplitRel.getSplit_type();
        ResultTable<CoProd> resultTable = new ResultTable<CoProd>(CoProd.Field.productId, new DBCondition(CoProd.Field.busiFlag,
                SpecCodeDefine.SPLIT), new DBCondition(CoProd.Field.objectId, payAcctId), new DBCondition(
                CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

        if (splitType == EnumCodeDefine.SPLIT_TYPE_ITEM)
        {
            if (CommonUtil.isValid(acctId))
            {
                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_TYPE), new DBCondition(CoSplitCharValue.Field.value,
                        EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                        new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_ID), new DBCondition(CoSplitCharValue.Field.value, acctId), new DBCondition(
                        CoSplitCharValue.Field.groupId, 0L), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId),
                        new DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
                resultTable
                        .addCondTable(
                                CoSplitCharValue.Field.productId,
                                new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRICE_RULE_ID),
                                new DBCondition(CoSplitCharValue.Field.value, CommonUtil.long2String(sUserPaySplitRel
                                        .getPrice_rule_id())), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                                new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                        CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

            }
            else if (CommonUtil.isValid(userId))
            {
                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_TYPE), new DBCondition(CoSplitCharValue.Field.value,
                        EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                        new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_ID), new DBCondition(CoSplitCharValue.Field.value, userId), new DBCondition(
                        CoSplitCharValue.Field.groupId, 0L), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId),
                        new DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
                resultTable
                        .addCondTable(
                                CoSplitCharValue.Field.productId,
                                new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRICE_RULE_ID),
                                new DBCondition(CoSplitCharValue.Field.value, CommonUtil.long2String(sUserPaySplitRel
                                        .getPrice_rule_id())), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                                new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                        CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

            }
            else
            {
                return null;
            }
        }
        // 产品级分账
        else if (splitType == EnumCodeDefine.SPLIT_TYPE_PRODUCT)
        {
            if (CommonUtil.isValid(acctId))
            {
                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_TYPE), new DBCondition(CoSplitCharValue.Field.value,
                        EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                        new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_ID), new DBCondition(CoSplitCharValue.Field.value, acctId), new DBCondition(
                        CoSplitCharValue.Field.groupId, 0L), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId),
                        new DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
                resultTable.addCondTable(
                        CoSplitCharValue.Field.productId,
                        new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRODUCT_ID),
                        new DBCondition(CoSplitCharValue.Field.value, CommonUtil.long2String(sUserPaySplitRel
                                .getProduct_sequence_id())), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                        new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
            }
            else if (CommonUtil.isValid(userId))
            {
                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_TYPE), new DBCondition(CoSplitCharValue.Field.value,
                        EnumCodeDefine.PROD_OBJECTTYPE_DEV), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                        new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

                resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
                        SpecCodeDefine.SPLIT_OBJECT_ID), new DBCondition(CoSplitCharValue.Field.value, userId), new DBCondition(
                        CoSplitCharValue.Field.groupId, 0L), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId),
                        new DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
                resultTable.addCondTable(
                        CoSplitCharValue.Field.productId,
                        new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRODUCT_ID),
                        new DBCondition(CoSplitCharValue.Field.value, CommonUtil.long2String(sUserPaySplitRel
                                .getProduct_sequence_id())), new DBCondition(CoSplitCharValue.Field.groupId, 0L),
                        new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(
                                CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
            }
            else
            {
                return null;
            }
        }
        List<CoProd> resultList = context.getComponent(BaseProductComponent.class).mergeProdList(
                context.getComponent(ResultComponent.class).getResultList(resultTable));
        if (!CommonUtil.isEmpty(resultList))
        {
            return resultList.get(0).getProductId();
        }
        return null;

    }

    /**
     * caohm5 根据产品编号和使用者编号查询分账特征值
     * 
     * @param prodId产品编号
     * @return
     */
    public List<CoSplitCharValue> querySplitProdCharValue(Long prodId, Long objectId)
    {
        DBCondition db1 = new DBCondition(CoSplitCharValue.Field.productId, prodId);
        DBCondition db2 = new DBCondition(CoSplitCharValue.Field.objectId, objectId);
        return query(CoSplitCharValue.class, db1, db2);
    }

    /**
     * @Description: 根据prodId,specCode,value,objectId查询CoSplitCharValue List
     * @author xieqr
     * @date 20120507
     */
    public List<CoSplitCharValue> queryCharValue(Long prodId, int specCode, String value, Long objectId) throws IMSException
    {   
        DBCondition db1 = new DBCondition(CoSplitCharValue.Field.productId, prodId);
        DBCondition db2 = new DBCondition(CoSplitCharValue.Field.objectId, objectId);
        DBCondition db3 = new DBCondition(CoSplitCharValue.Field.specCharId, specCode);
        DBCondition db4 = new DBCondition(CoSplitCharValue.Field.value, value);
        return query(CoSplitCharValue.class, db1, db2, db3, db4);
    }

    /**
     * caohm5 根据产品编号查询分账特征值
     * 
     * @param prodId产品编号
     * @return
     */
    public List<CoSplitCharValue> querySplitProdCharValue(Long prodId)
    {
        return query(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.productId,prodId));
    }
    
    /**
     * @Description: 根据objectId，objectType查询表co_split_char_value的数据
     * @param objectId
     * @param objectType
     * @return   
     * @author: tangjl5
     * @Date: 2012-8-28
     */
    public List<CoSplitCharValue> querySplitProdCharValues(Long objectId, Integer objectType)
    {
        return query(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.objectId, objectId), new DBCondition(CoSplitCharValue.Field.objectType, objectType));
    }

    /**
     * @Description: 产品级分账删除
     * @param prodId
     * @author: wangdw5
     * @Date: 2012-6-27
     */
    public void deleteSplitTypeProd(Long prodId)
    {
        List<CoSplitCharValue> list = query(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.specCharId,
                SpecCodeDefine.SPLIT_PRODUCT_ID), new DBCondition(CoSplitCharValue.Field.value, prodId));
        if (CommonUtil.isNotEmpty(list))
        {
            for (CoSplitCharValue coSplitCharValue : list)
            {
                deleteProdSplitCharValueByProdId(coSplitCharValue.getProductId());
            }
        }
    }

    /**
     * 根据userID，判断是否为统付类付号码 xieqr 2012-4-9
     * 
     * @param resourceId
     * @return
     */
    public boolean checkTongFuViceNum(Long resourceId)
    {
        boolean bl = false;
        List<CoProd> splitProds = queryProdsByObjectSplit(resourceId, new Long(EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        //zhangzj3 增加生效时间判断。必须是已经生效的产品才能确定该用户是统付类副号码
        List<CoProd> prodList = new ArrayList<CoProd>();
        if (!CommonUtil.isEmpty(splitProds)){
            for(CoProd coProd : splitProds){
                if(coProd == null){
                    continue;
                }
                if(coProd.getValidDate().before(context.getRequestDate())){
                    prodList.add(coProd);
                }
            }
        }
        List<Long> prodIdList = new ArrayList<Long>();
        if(CommonUtil.isNotEmpty(prodList)){
            for (CoProd split : prodList)
            {   
                if(split == null){
                    continue;
                }
                Long prodId = split.getProductId();
                Long payAcctId = split.getObjectId();
                IMSUtil.setAcctRouterParam(payAcctId);
                List<CoSplitCharValue> list = queryCharValue(prodId, SpecCodeDefine.SPLIT_METHOD,
                        String.valueOf(EnumCodeDefine.SPLIT_METHOD_TONGFU), payAcctId);
                if (!CommonUtil.isEmpty(list))
                {
                    prodIdList.add(prodId);
                }
            }
        }
        if(prodIdList.size() > 0){
            bl = true;
        }
        return bl;
    }
}
