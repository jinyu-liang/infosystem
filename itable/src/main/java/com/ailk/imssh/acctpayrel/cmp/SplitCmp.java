package com.ailk.imssh.acctpayrel.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.AmountComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.acctpayrel.helper.CharValueHelper;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.define.SpecCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.imssh.prod.cmp.ProdExCmp;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoSplitPayRel;
import com.ailk.openbilling.persistence.itable.entity.IAcctPayRelation;
import com.ailk.openbilling.persistence.pm.entity.PmPayforRegulation;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;

/**
 * @Description 分账组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lijc3
 * @Date 2012-5-14
 * @Date 2012-08-04 wukl Strory #49463 split需要转换为账务侧的measure_id和amount进行存储
 */
public class SplitCmp extends BaseCmp
{

    /**
     * 插入代付产品
     */
    public CoProd insertCoProd(IAcctPayRelation rel)
    {
        ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
        Integer offeringId = prodCmp.queryOfferingId(SpecCodeDefine.SPLIT);
        if (!CommonUtil.isValid(offeringId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeExDefine.PRODUCTOFFERINGID_ERROR, SpecCodeDefine.SPLIT);
        }
        CoProd coProd = new CoProd();
        // GX处理，产品id取sequence
        // coProd.setProductId(rel.getProductId());
        coProd.setProductId(rel.getInstId());
        coProd.setProdTypeId(0);
        coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
        coProd.setProductOfferingId(Integer.valueOf(rel.getProductId().toString()));
        coProd.setPricingPlanId(0);
        coProd.setBusiFlag(SpecCodeDefine.SPLIT);
        coProd.setBillingType(EnumCodeDefine.PROD_BILLTYPE_POSTPAID);
        coProd.setValidDate(rel.getValidDate());
        coProd.setExpireDate(rel.getExpireDate());
        coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        coProd.setObjectId(rel.getUserId());
        coProd.setObjectType(0);
        coProd.setProdValidDate(rel.getValidDate());
        coProd.setProdExpireDate(rel.getExpireDate());
        super.insert(coProd);
        // context.getComponent(ProductComponent.class).createProdValid(coProd);
        return coProd;
    }

    /**
     * 创建代付信息
     */
    public void insertSplitProdCharValueList(Long prodId, IAcctPayRelation rel, Integer objectType, String objectId)
    {
        Long groupId = 0L;
    	//Long groupId = rel.getUserId();
        objectType= EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        List<DataObject> list = new ArrayList<DataObject>();
        CharValueHelper helper = new CharValueHelper(CoSplitCharValue.class, prodId, groupId, rel.getValidDate(),
                rel.getExpireDate(), rel.getUserId(), objectType);
        Integer payType = rel.getPayType();
        // 科目级和产品级特征值说明：产品编号、价格计划、分账类型
        // 科目级分账
        if (payType == EnumCodeExDefine.SPLIT_TYPE_ITEM)
        {
            list.add(helper.getNew(SpecCodeDefine.SPLIT_PRICE_RULE_ID, rel.getPayRuleId()));
            list.add(helper.getNew(SpecCodeDefine.SPLIT_PRODUCT_ID, null));
            list.add(helper.getNew(SpecCodeDefine.SPLIT_TYPE, EnumCodeExDefine.SPLIT_TYPE_ITEM));
        }
        // 产品级分账
        else if (payType == EnumCodeExDefine.SPLIT_TYPE_PRODUCT)
        {
            list.add(helper.getNew(SpecCodeDefine.SPLIT_TYPE, EnumCodeExDefine.SPLIT_TYPE_PRODUCT));
            list.add(helper.getNew(SpecCodeDefine.SPLIT_PRODUCT_ID, rel.getProductId()));
            list.add(helper.getNew(SpecCodeDefine.SPLIT_PRICE_RULE_ID, null));
        }
        // 百分百和数量特征值说明：限额、分子、分母

        // @Date 2012-08-04 wukl Strory #49463
        // split需要转换为账务侧的measure_id和amount进行存储
        // 接口表中没有传入货币单位，所以没做转化，默认按账户侧的measure_id处理
        // Double dbAmount =
        // context.getComponent(AmountComponent.class).parseBillingAmount(null,
        // rel.getPayThreshold().doubleValue(), rel.getPayAcctId());
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        Integer measureTypeId = amountCmp.queryMeasureTypeId(EnumCodeExDefine.MEASURE_ID_FEN);
        list.add(helper.getNew(SpecCodeDefine.SPLIT_MEASURE_ID,
                amountCmp.queryMeasureIdByTypeAndModule(measureTypeId, EnumCodeDefine.MODULE_BILLING)));
        // TODO
//        if(ProjectUtil.is_CN_GX()||ProjectUtil.is_CN_JX()||ProjectUtil.is_CN_NM()){
//        	PmPayforRegulation pmPayforRegulation = queryPmPayforRegulation(rel.getPayRuleId());
//        	
//        	//1是按金额分账 2是按比例分账
//        	if(pmPayforRegulation.getPaymentType() == EnumCodeExDefine.SPLIT_TYPE_AMOUNT)
//        	{
//        		list.add(helper.getNew(SpecCodeDefine.SPLIT_AMOUNT, rel.getPayThreshold()));
//        		list.add(helper.getNew(SpecCodeDefine.SPLIT_NUMERATOR, EnumCodeDefine.DEFAULT_NULL_VALUE));
//        		list.add(helper.getNew(SpecCodeDefine.SPLIT_DENOMINATOR, EnumCodeDefine.DEFAULT_NULL_VALUE));
//        		//2是按比例分账
//        	}else if(pmPayforRegulation.getPaymentType() == EnumCodeExDefine.SPLIT_TYPE_NUMERATOR)
//        	{
//        		list.add(helper.getNew(SpecCodeDefine.SPLIT_NUMERATOR, rel.getPayThreshold()));
//        		list.add(helper.getNew(SpecCodeDefine.SPLIT_DENOMINATOR, EnumCodeExDefine.DEFAULT_SPLIT_DENOMINATOR));
//        		list.add(helper.getNew(SpecCodeDefine.SPLIT_AMOUNT, EnumCodeExDefine.DEFAULT_SPLIT_VALUE));
//        	}
//        }else{ //湖南
        	list.add(helper.getNew(SpecCodeDefine.SPLIT_AMOUNT, rel.getPayThreshold()));
    		list.add(helper.getNew(SpecCodeDefine.SPLIT_NUMERATOR, rel.getPayNumerator() == null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getPayNumerator()));
    		list.add(helper.getNew(SpecCodeDefine.SPLIT_DENOMINATOR, rel.getPayDenominator()==null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getPayDenominator()));
//        }
        
        
        // 被分账标示和对象
        list.add(helper.getNew(SpecCodeDefine.SPLIT_OBJECT_TYPE, objectType));
        // 如果是用户级分账，则需要设置被代付对象的归属账户编号
//        if (Integer.valueOf(objectType) == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
//        {
//            list.add(helper.getNew(SpecCodeDefine.SPLIT_PAID_ACCT_ID, rel.getAcctId()));
//        }
//        else
//        {
//            // 否则设置为0
//            list.add(helper.getNew(SpecCodeDefine.SPLIT_PAID_ACCT_ID, -999));
//        }
        list.add(helper.getNew(SpecCodeDefine.SPLIT_PAID_ACCT_ID, 0));
        list.add(helper.getNew(SpecCodeDefine.SPLIT_OBJECT_ID, objectId));
        // 优先级
        list.add(helper.getNew(SpecCodeDefine.SPLIT_PRIORITY, rel.getPriority()));
        // 构建通知特征值
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI);
        idList.add(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI);
        idList.add(SpecCodeDefine.SPLIT_PAY_FAIL_BAR);
        idList.add(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR);
        List<PmProductSpecCharValue> result = getSpecCharValueListByIdList(idList);
        String notiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI, result);
        String bePaidSideNotiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI, result);
        String failNotiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_PAY_FAIL_BAR, result);
        String bePaidSideFailNotiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR, result);

        list.add(helper.getNew(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI, notiValue));
        list.add(helper.getNew(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI, bePaidSideNotiValue));
        list.add(helper.getNew(SpecCodeDefine.SPLIT_PAY_FAIL_BAR, failNotiValue));
        list.add(helper.getNew(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR, bePaidSideFailNotiValue));

        // 代付方式 0：一般代付（默认） 1：统付
        list.add(helper.getNew(SpecCodeDefine.SPLIT_METHOD, rel.getPayMethod()));
        
      //add by songcc 2014-2-12
        Long mainUserValue = rel.getMainUserId();
        if (mainUserValue==null || mainUserValue == 0)
        {
            mainUserValue = -999L; 
        }
        list.add(helper.getNew(SpecCodeDefine.SPLIT_ACCT_MAIN_USER_ID, mainUserValue));
        
        // add by yuxz 2016-03-27  
        list.add(helper.getNew(SpecCodeDefine.SPLIT_FILL_TYPE, rel.getFillType()==null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getFillType()));
        list.add(helper.getNew(SpecCodeDefine.SPLIT_BIND_TYPE, rel.getBindType()==null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getBindType()));
        list.add(helper.getNew(SpecCodeDefine.PAY_ACCT_ID,rel.getPayAcctId()));
        super.insertBatch(list);
    }

    /**
     * 查询代付产品
     */
    public Long querySplit(IAcctPayRelation rel)
    {

        List<CoSplitPayRel> payRelList = null;
        if (rel.getUserId() != null)
        {   
        	 RouterCmp baseCmp = context.getCmp(RouterCmp.class);
         	 Long acctId=baseCmp.queryAcctIdByUserId(rel.getUserId());
        	 ITableUtil.setValue2ContextHolder(null, acctId, null);
             payRelList = this.query(CoSplitPayRel.class, new DBCondition(CoSplitPayRel.Field.objectId, rel.getUserId()),
                    new DBCondition(CoSplitPayRel.Field.reguidObjectId, rel.getPayAcctId()), new DBCondition(
                            CoSplitPayRel.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        }
        else
        {
            ITableUtil.setValue2ContextHolder(null, rel.getAcctId(), null);
            payRelList = this.query(CoSplitPayRel.class, new DBCondition(CoSplitPayRel.Field.objectId, rel.getAcctId()),
                    new DBCondition(CoSplitPayRel.Field.reguidObjectId, rel.getPayAcctId()), new DBCondition(
                            CoSplitPayRel.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
        }
        imsLogger.debug("设置回正向分表 ");
        ITableUtil.setValue2ContextHolder(null, rel.getPayAcctId(), null);
        if (CommonUtil.isNotEmpty(payRelList))
        {
            List<Long> productIdList = new ArrayList<Long>();
            for (CoSplitPayRel payRel : payRelList)
            {
                productIdList.add(payRel.getProductId());
            }
            Integer splitType = rel.getPayType();
            CoSplitCharValue value = null;
            if (splitType == EnumCodeExDefine.SPLIT_TYPE_ITEM)
            {
                value = this.querySingle(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.productId, productIdList,
                        Operator.IN), new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRICE_RULE_ID),
                        new DBCondition(CoSplitCharValue.Field.value, CommonUtil.int2String(rel.getPayRuleId())));
            }
            else
            {
                value = this.querySingle(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.productId, productIdList,
                        Operator.IN), new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRODUCT_ID),
                        new DBCondition(CoSplitCharValue.Field.value, CommonUtil.long2String(rel.getProductId())));
            }
            if (value != null)
            {
                return value.getProductId();
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }

        /*
         * Long payAcctId = rel.getPayAcctId(); Long acctId = rel.getAcctId(); Long userId = rel.getUserId(); Integer splitType =
         * rel.getPayType(); ResultTable<CoProd> resultTable = new ResultTable<CoProd>(CoProd.Field.productId, new
         * DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.SPLIT), new DBCondition(CoProd.Field.objectId, payAcctId), new
         * DBCondition( CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); if (splitType ==
         * EnumCodeExDefine.SPLIT_TYPE_ITEM) { if (CommonUtil.isValid(userId)) {
         * resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
         * SpecCodeDefine.SPLIT_OBJECT_TYPE), new DBCondition(CoSplitCharValue.Field.value, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
         * new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(CoSplitCharValue.Field.objectType,
         * EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); resultTable.addCondTable(CoSplitCharValue.Field.productId, new
         * DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_ID), new
         * DBCondition(CoSplitCharValue.Field.value, userId), new DBCondition( CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
         * resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
         * SpecCodeDefine.SPLIT_PRICE_RULE_ID), new DBCondition(CoSplitCharValue.Field.value,
         * CommonUtil.long2String(rel.getPayRuleId())), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition( CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); } else if
         * (CommonUtil.isValid(acctId)) { resultTable.addCondTable(CoSplitCharValue.Field.productId, new
         * DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_TYPE), new
         * DBCondition(CoSplitCharValue.Field.value, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new
         * DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(CoSplitCharValue.Field.objectType,
         * EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); resultTable.addCondTable(CoSplitCharValue.Field.productId, new
         * DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_ID), new
         * DBCondition(CoSplitCharValue.Field.value, acctId), new DBCondition( CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
         * resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
         * SpecCodeDefine.SPLIT_PRICE_RULE_ID), new DBCondition(CoSplitCharValue.Field.value,
         * CommonUtil.long2String(rel.getPayRuleId())), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition( CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); } else { return null; } } //
         * 产品级分账 else if (splitType == EnumCodeExDefine.SPLIT_TYPE_PRODUCT) { if (CommonUtil.isValid(userId)) {
         * resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
         * SpecCodeDefine.SPLIT_OBJECT_TYPE), new DBCondition(CoSplitCharValue.Field.value, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
         * new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(CoSplitCharValue.Field.objectType,
         * EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); resultTable.addCondTable(CoSplitCharValue.Field.productId, new
         * DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_ID), new
         * DBCondition(CoSplitCharValue.Field.value, userId), new DBCondition( CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
         * resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
         * SpecCodeDefine.SPLIT_PRODUCT_ID), new DBCondition(CoSplitCharValue.Field.value,
         * CommonUtil.long2String(rel.getProductId())), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition( CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); } else if
         * (CommonUtil.isValid(acctId)) { resultTable.addCondTable(CoSplitCharValue.Field.productId, new
         * DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_TYPE), new
         * DBCondition(CoSplitCharValue.Field.value, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT), new
         * DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new DBCondition(CoSplitCharValue.Field.objectType,
         * EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); resultTable.addCondTable(CoSplitCharValue.Field.productId, new
         * DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_OBJECT_ID), new
         * DBCondition(CoSplitCharValue.Field.value, acctId), new DBCondition( CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition(CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
         * resultTable.addCondTable(CoSplitCharValue.Field.productId, new DBCondition(CoSplitCharValue.Field.specCharId,
         * SpecCodeDefine.SPLIT_PRODUCT_ID), new DBCondition(CoSplitCharValue.Field.value,
         * CommonUtil.long2String(rel.getProductId())), new DBCondition(CoSplitCharValue.Field.objectId, payAcctId), new
         * DBCondition( CoSplitCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)); } else { return null; } }
         * List<CoProd> resultList = context.getCmp(ProdCmp.class).mergeProdList(
         * context.getCmp(ResultComponent.class).getResultList(resultTable)); if (!CommonUtil.isEmpty(resultList)) { return
         * resultList.get(0).getProductId(); } return null;
         */
    }

    /**
     * 获取类型
     */
    public Integer getObjectType(IAcctPayRelation rel)
    {
        if (rel.getUserId() != null)
        {
            return EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        }
        else
        {
            return EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        }
    }

    /**
     * 获取id
     */
    public Long getObjectId(IAcctPayRelation rel)
    {
        if (rel.getUserId() != null)
        {
            return rel.getUserId();
        }
        else
        {
            return rel.getAcctId();
        }
    }

    /**
     * 更新split信息
     */
    public void updateSplit(Long prodId, IAcctPayRelation rel)
    {
        Long objectId = rel.getUserId();
        Integer objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        CoProd coProd = new CoProd();
        // Date validDate = rel.getValidDate();
        Date expireDate = rel.getExpireDate();

        coProd.setExpireDate(expireDate);
        coProd.setProdExpireDate(expireDate);

        // 更新产品表CO_PROD
        this.updateMode3(coProd, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(CoProd.Field.objectId,
                objectId), new DBCondition(CoProd.Field.objectType, objectType));
       // context.getCmp(ProdExCmp.class).modifyProdValid(prodId, expireDate);

        // 更新产品特征值表CO_SPLIT_CHAR_VALUE

        Long groupId = getGroupId(prodId);
//        CharValueHelper helper = new CharValueHelper(CoSplitCharValue.class, prodId, groupId, null, expireDate, objectId,
//                objectType);
        CharValueHelper helper = new CharValueHelper(CoSplitCharValue.class, prodId, groupId, rel.getValidDate(),
                rel.getExpireDate(), rel.getUserId(), EnumCodeDefine.PROD_OBJECTTYPE_DEV);
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI);
        idList.add(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI);
        idList.add(SpecCodeDefine.SPLIT_PAY_FAIL_BAR);
        idList.add(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR);
        List<PmProductSpecCharValue> result = getSpecCharValueListByIdList(idList);
        String objectTypeNew = String.valueOf(this.getObjectType(rel));
        String objectIdNew = String.valueOf(this.getObjectId(rel));
        String notiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI, result);
        String bePaidSideNotiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI, result);
        String failNotiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_PAY_FAIL_BAR, result);
        String bePaidSideFailNotiValue = getSpecCharValueById(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR, result);
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        Integer measureTypeId = amountCmp.queryMeasureTypeId(EnumCodeExDefine.MEASURE_ID_FEN);
        
        this.updateMode2(helper.getNew(SpecCodeDefine.PAY_ACCT_ID,rel.getPayAcctId()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.PAY_ACCT_ID));//20
        
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_PAID_ACCT_ID,0), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_PAID_ACCT_ID));//16
        
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_METHOD,rel.getPayMethod()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_METHOD));//15
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_MEASURE_ID,amountCmp.queryMeasureIdByTypeAndModule(measureTypeId, EnumCodeDefine.MODULE_BILLING)), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_MEASURE_ID));//14
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_PRODUCT_ID, rel.getProductId()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_PRODUCT_ID));//13
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_TYPE, EnumCodeExDefine.SPLIT_TYPE_ITEM), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_TYPE));//12
        
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR, bePaidSideFailNotiValue), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR));//11
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_PAY_FAIL_BAR, failNotiValue), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_PAY_FAIL_BAR));//10
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI, bePaidSideNotiValue), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI));//09
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_PAY_SUCC_NOTI, notiValue), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_PAY_SUCC_NOTI));//08
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_OBJECT_ID, objectIdNew), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_OBJECT_ID));//06
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_PRICE_RULE_ID, rel.getPayRuleId()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_PRICE_RULE_ID));//01
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_OBJECT_TYPE,objectTypeNew), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_OBJECT_TYPE));//05
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_PRIORITY, rel.getPriority()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_PRIORITY));//07
        
        this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_AMOUNT, rel.getPayThreshold()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_AMOUNT));//02
        
    	this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_NUMERATOR, rel.getPayNumerator() == null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getPayNumerator()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
            		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
            		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_NUMERATOR));//03
    		
    	this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_DENOMINATOR,rel.getPayDenominator() == null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getPayDenominator()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
            		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
            		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_DENOMINATOR));//04
    		
    	this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_FILL_TYPE,rel.getFillType() == null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getFillType()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
            		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
            		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_FILL_TYPE));//18
    	
    	this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_BIND_TYPE,rel.getBindType() == null ? EnumCodeDefine.DEFAULT_NULL_VALUE : rel.getBindType()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
            		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
            		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_BIND_TYPE));//19
        
    	this.updateMode2(helper.getNew(SpecCodeDefine.SPLIT_ACCT_MAIN_USER_ID,rel.getMainUserId()), EnumCodeExDefine.OPER_TYPE_UPDATE,rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,SpecCodeDefine.SPLIT_ACCT_MAIN_USER_ID));//17
        
        
    

        ITableUtil.setValue2ContextHolder(null, rel.getAcctId(), null);
        // 更新分账关系表CO_SPLIT_PAY_REL
        /*
        CoSplitPayRel value = new CoSplitPayRel();
        value.setExpireDate(expireDate);
        updateByCondition(value, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(CoProd.Field.objectId,
                getObjectId(rel)), new DBCondition(CoProd.Field.objectType, getObjectType(rel)));
         */

        ITableUtil.setValue2ContextHolder(null, rel.getPayAcctId(), null);
    }

    /**
     * 删除split信息 lijc3 2012-5-17
     * 
     * @param prodId
     * @param expireDate
     */
    public void deleteSplitInfoByProdId(IAcctPayRelation rel, Long prodId, Date expireDate)
    {
        ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
        prodCmp.deleteProdById(prodId, getNextMonthBegin(expireDate));
        prodCmp.modifyProdValidBySplitCharValue(prodId, expireDate);
        this.deleteProdSplitCharValueByProdId(prodId, rel);
        /*
         * CoProdBillingCycle cycle = new CoProdBillingCycle(); cycle.setExpireDate(expireDate); updateByCondition(cycle, new
         * DBCondition(CoProdBillingCycle.Field.productId, prodId)); RouterCmp routCmp = context.getCmp(RouterCmp.class); //
         * 清除分表参数 routCmp.cleanRequestParamter(); // 设置分表参数 routCmp.setUserIdOrAcctIdIntoRequest(null, rel.getAcctId());
         * ITableUtil.setValue2ContextHolder(null, rel.getAcctId(), context.getUserId());
         
        ITableUtil.setValue2ContextHolder(null, null, rel.getUserId());
        CoSplitPayRel value = new CoSplitPayRel();
        Date sysDate = new Date();
        if(expireDate.after(sysDate)){
        	value.setExpireDate(expireDate);
        }else{
        	value.setExpireDate(sysDate);
        }
        
        super.updateByCondition(value, new DBCondition(CoSplitPayRel.Field.productId, prodId));
		*/
        // 设置回原来的分表参数
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), context.getUserId());
    }

    /**
     * 删除CO_PROD_CHAR_VALUE lijc3 2012-5-17
     */
    private void deleteProdSplitCharValueByProdId(Long prodId, IAcctPayRelation rel)
    {
        // CoSplitCharValue value = new CoSplitCharValue();
        // value.setExpireDate(expireDate);
        Integer[] upExpireSplitSpecIds = new Integer[] { SpecCodeDefine.SPLIT_PRICE_RULE_ID,SpecCodeDefine.SPLIT_AMOUNT,SpecCodeDefine.SPLIT_NUMERATOR,
        		SpecCodeDefine.SPLIT_DENOMINATOR,SpecCodeDefine.SPLIT_OBJECT_TYPE,SpecCodeDefine.SPLIT_OBJECT_ID,SpecCodeDefine.SPLIT_PRIORITY,
        		SpecCodeDefine.SPLIT_TYPE,SpecCodeDefine.SPLIT_PRODUCT_ID,SpecCodeDefine.SPLIT_METHOD,SpecCodeDefine.SPLIT_MEASURE_ID,
        		SpecCodeDefine.SPLIT_PAY_SUCC_NOTI,SpecCodeDefine.SPLIT_BEPAID_SUCC_NOTI,SpecCodeDefine.SPLIT_PAY_FAIL_BAR,SpecCodeDefine.SPLIT_BEPAID_FAIL_BAR,
        		SpecCodeDefine.SPLIT_PAID_ACCT_ID,SpecCodeDefine.SPLIT_ACCT_MAIN_USER_ID,SpecCodeDefine.SPLIT_FILL_TYPE,SpecCodeDefine.SPLIT_BIND_TYPE,
        		SpecCodeDefine.PAY_ACCT_ID
        		};

        CoSplitCharValue upValue = new CoSplitCharValue();
        upValue.setExpireDate(getNextMonthBegin(rel.getExpireDate()));
        this.updateMode2(upValue, EnumCodeExDefine.OPER_TYPE_DELETE, rel.getValidDate(),
        		new DBCondition(CoSplitCharValue.Field.productId, rel.getInstId()),
        		new DBCondition(CoSplitCharValue.Field.specCharId,
                        upExpireSplitSpecIds, Operator.IN));
       

    }

    /**
     * 实例化分账关系表CO_SPLIT_PAY_REL zenglu 2012-07-25
     */
    public void insertCoSplitPayRel(Long prodId, IAcctPayRelation rel, String objectType, String objectId)
    {
    	Date sysDate = new Date();
        CoSplitPayRel value = new CoSplitPayRel();
        value.setProductId(prodId);
        value.setObjectType(CommonUtil.string2Integer(objectType));
        value.setObjectId(CommonUtil.StringToLong(objectId));
        value.setReguidObjectId(rel.getPayAcctId());
        value.setReguidObjectType(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
        
        if(rel.getValidDate().after(sysDate)){
        	 value.setValidDate(rel.getValidDate());
        }else{
        	 value.setValidDate(sysDate);
        }
        value.setValidDate(rel.getValidDate());
        value.setExpireDate(rel.getExpireDate());
        value.setSoDate(rel.getCommitDate());
        value.setSoNbr(context.getSoNbr());
        value.setCreateDate(rel.getCommitDate());
        super.insert(value);
    }

    private Long getGroupId(Long productId)
    {
        CoSplitCharValue charValue = this.querySingle(CoSplitCharValue.class, new DBCondition(CoSplitCharValue.Field.productId,
                productId), new DBCondition(CoSplitCharValue.Field.specCharId, SpecCodeDefine.SPLIT_PRICE_RULE_ID));
        //charValue != null ? charValue.getGroupId() : 0
        return charValue != null ? charValue.getGroupId() : 0;
    }

    /**
     * lijc3 2012-10-30 获取单个的代付需要的配置信息
     * 
     * @param specCharValue
     * @param result
     * @return
     */
    public String getSpecCharValueById(int specCharValue, List<PmProductSpecCharValue> result)
    {
        // 如果未配置，直接抛异常
        if (CommonUtil.isEmpty(result))
        {
            throw IMSUtil.throwBusiException(ErrorCodeExDefine.PM_PROD_CHAR_VALUE_NO_DEFAULT_VALUE, specCharValue);
        }
        for (PmProductSpecCharValue value : result)
        {
            if (value.getSpecCharId() == specCharValue)
            {
                return value.getValue();
            }
        }
        // 如果未配置，直接抛异常
        throw IMSUtil.throwBusiException(ErrorCodeExDefine.PM_PROD_CHAR_VALUE_NO_DEFAULT_VALUE, specCharValue);
    }

    /**
     * lijc3 2012-10-30 一次查出代付需要的相关配置
     * 
     * @param idList
     * @return
     */
    public List<PmProductSpecCharValue> getSpecCharValueListByIdList(List<Integer> idList)
    {
        return query(PmProductSpecCharValue.class, new DBCondition(PmProductSpecCharValue.Field.specCharId, idList, Operator.IN));
    }
    
    /**
     * 
     */
    public PmPayforRegulation queryPmPayforRegulation(Integer payRuleId)
    {
        PmPayforRegulation pmPayforRegulation = (PmPayforRegulation) querySingle(PmPayforRegulation.class, new DBCondition(PmPayforRegulation.Field.priceRuleId, payRuleId.intValue()));
        if (pmPayforRegulation == null){
            // lacking configuration data of table[{0}] where the column [{1}] = [{2}]
            IMSUtil.throwBusiException(ErrorCodeDefine.LACK_CONFIG_DATE, "PM_PAYFOR_REGULATION", "PRICE_RULE_ID", payRuleId);
        }
        
        return pmPayforRegulation;
    }

}
