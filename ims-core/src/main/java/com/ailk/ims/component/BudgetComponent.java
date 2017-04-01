package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.ResultTable;
import com.ailk.ims.complex.CharValueHelper;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsintf.entity.BudgetControl;
import com.ailk.openbilling.persistence.imsintf.entity.Threshold;
import com.ailk.openbilling.persistence.imsintf.entity.ThresholdList;

/**
 * 预算对应组件
 * 
 * @author wangjt
 * @date 2011-12-26 ljc 修改308行不传入付费模式
 * @date 2012-04-02 zengxr for budget mode 2--> -1
 * @date 2012-04-07 yangjh BUDGET_OBJECT_TYPE = 0
 * @Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
 * @Date 2012-06-28 yangjh story:49281 budget 增加区分是bar的告警还是continue的告警
 * @Date 2012-07-06 yangjh story：50195 budget信息入CoBudgetCharValue表
 *                                      修改操作用prodCharToBudgetChar 转换
 * @date 2012-07-10 luojb #50229 删除co_prod_valid,  co_prod 增加prod_valid_date，prod_expire_date
 * @date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class BudgetComponent extends BaseComponent
{
    public BudgetComponent()
    {
    }

    private Date inputValidDate = null;
    private Date inputExpireDate = null;

    public void setInputValidDate(Date inputValidDate)
    {
        this.inputValidDate = inputValidDate;
    }

    public void setInputExpireDate(Date inputExpireDate)
    {
        this.inputExpireDate = inputExpireDate;
    }

    /**
     * 新建账户预算
     */
    public void createAcctBudget(BudgetControl budgetControl, Long acctId)
    {
        int objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        Integer payMode = CommonUtil.short2Int(budgetControl.getPayment_mode());
        CoProd coProd = this.insertCoProd(acctId, null, payMode, objectType);

        Long prodId = coProd.getProductId();
        //@date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
//        this.insertCoProdInvObj(prodId, acctId, objectType);
        this.insertBudgetCharValueList(prodId, budgetControl, acctId, objectType);

        // 增加账期
        ProductCycleComponent prodCmp = context.getComponent(ProductCycleComponent.class);
        prodCmp.createProdBillCycle(coProd, acctId);
    }

    /**
     * 新建用户或者服务预算（根据budgetControl中的service_id去区分）<br>
     * service_id:0用户预算，非0：服务预算
     */
    public void createUserOrServiceBudget(BudgetControl budgetControl, Long acctId, CmResource dmUser)
    {
        int objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        Long userId = dmUser.getResourceId();
        Integer payMode = CommonUtil.short2Int(budgetControl.getPayment_mode());

        CoProd coProd = this.insertCoProd(null, userId, payMode, objectType);// 付费模式不同
        Long prodId = coProd.getProductId();
        //@date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
        this.insertBudgetCharValueList(prodId, budgetControl, userId, objectType);

        // 增加账期
        ProductCycleComponent prodCycleCmp = context.getComponent(ProductCycleComponent.class);
        prodCycleCmp.createProdBillCycle(coProd, acctId);
    }
    
    /**
     * @Description: 根据objectId，objectType查询CoBudgetCharValue表的数据
     * @param objectId
     * @param objectType
     * @return	 
     * @author: tangjl5
     * @Date: 2012-8-28
     */
    public List<CoBudgetCharValue> queryBudgetCharValue(Long objectId, Integer objectType)
    {
        return query(CoBudgetCharValue.class, new DBCondition(CoBudgetCharValue.Field.objectId, objectId), new DBCondition(CoBudgetCharValue.Field.objectType, objectType));
    }

    /**
     * 增加一个预算产品，返回产品
     * 
     * @param objectType
     * @param acctId:账户级产品，acctId不为空，userId传空
     * @param userId:用户级和服务级产品，acctId为空，userId不传空
     * @modify xieqr 2012-3-8 process objectId ,objectType
     */
    private CoProd insertCoProd(Long acctId, Long userId, Integer payMode, Integer objectType)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        Integer productOfferingId = prodCmp.queryOfferingId(SpecCodeDefine.BUDGET);
        if (!CommonUtil.isValid(productOfferingId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCTOFFERINGID_ERROR, SpecCodeDefine.BUDGET);
        }

        // taoyf 加于2012-2-23， 将pricingPlanId始终为0，屏蔽掉bugdet产品的资费
        /*
         * Integer pricingPlanId = prodCmp.queryPricePlanId(productOfferingId, acctId, userId); if (pricingPlanId == null) {
         * pricingPlanId = 0; }
         */

        Integer pricingPlanId = 0;

        CoProd coProd = new CoProd();
        coProd.setProductId(DBUtil.getSequence(CoProd.class));
        coProd.setProdTypeId(EnumCodeDefine.PROD_TYPE_COMPONENT);
        coProd.setLifecycleStatusId(EnumCodeDefine.PROD_LIFECYCLE_ACTIVE);
        coProd.setProductOfferingId(productOfferingId);
        coProd.setPricingPlanId(pricingPlanId);
        coProd.setBusiFlag(SpecCodeDefine.BUDGET);
        // 付费模式,0,1,2
        coProd.setBillingType(payMode);

        // 增加CoProd表的objectId objectType 的插入 wukl 2012-03-08
        coProd.setObjectType(objectType);
        if (acctId == null)
        {
            coProd.setObjectId(userId);
        }
        else
        {
            coProd.setObjectId(acctId);
        }

        coProd.setValidDate(inputValidDate);
        coProd.setExpireDate(inputExpireDate);
        coProd.setIsMain(EnumCodeDefine.PRODUCT_COMMON);
        //2012-07-10 luojb
        //2012-07-19 luojb 对inputValidDate和inputExpireDate进行判断
        Date prodValidDate = inputValidDate!=null?inputValidDate:context.getRequestDate();
        Date prodExpireDate = inputExpireDate!=null?inputExpireDate:IMSUtil.getDefaultExpireDate();
        coProd.setProdValidDate(prodValidDate);
        coProd.setProdExpireDate(prodExpireDate);

        super.insert(coProd);
        
        return coProd;
    }


    /**
     * 增加一个产品的特征规格值列表
     * 
     * @param objectType
     * @param objectId
     */
    private void insertBudgetCharValueList(Long prodId, BudgetControl budgetControl, Long objectId, Integer objectType)
    {
        long groupId = 0;
        Long servId = budgetControl.getBusi_service_id();
        if (servId != null)
        {
            groupId = servId.longValue();
        }
//        Long dbAmount = AmountUtil.getDbAmount(budgetControl.getMeasure_id(), budgetControl.getBudget());
//        Integer dbMeasureId = AmountUtil.getDbMeasureId(budgetControl.getMeasure_id());

        CharValueHelper helper = new CharValueHelper(prodId, groupId, inputValidDate, inputExpireDate, objectId, objectType);
        List<CoBudgetCharValue> list = new ArrayList<CoBudgetCharValue>();
        list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_RULE, groupId)));
        
        list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_AMOUNT, budgetControl.getBudget())));
        list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_MEASURE_ID, budgetControl.getMeasure_id())));
        list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_PAYMODE, budgetControl.getPayment_mode())));// 0 1 -1
        // 2012-2-27taoyf 新增特征值10705：如果是账户的就天0，其他为默认-999；
        // 10717：默认加-999
//        if (budgetControl.getBudget_type() != null
//                && budgetControl.getBudget_type().shortValue() == EnumCodeDefine.BUDGET_BUDGETTYPE_ACCOUNT)
//        {
        //yangjh 2012-04-07 默认取0
            list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_OBJECT_TYPE, 0)));
//        }
//        else
//        {
//            list.add(helper.getNew(SpecCodeDefine.BUDGET_OBJECT_TYPE, EnumCodeDefine.MDB_DEFAULT_VALUE));
//        }
        list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_VALIDATE_CONDITION, EnumCodeDefine.MDB_DEFAULT_VALUE)));

        // 增加threshold的特征值
        list.addAll(this.getThresholdCharValueList(prodId, budgetControl, objectId, objectType));

        super.insertBatch(list);
    }

    /**
     * 获取threshold的特征值列表
     * 
     * @param objectType
     * @param objectId
     */
    private List<CoBudgetCharValue> getThresholdCharValueList(Long prodId, BudgetControl budgetControl, Long objectId,
            Integer objectType)
    {
        ConfigQuery cfgCmp = context.getComponent(ConfigQuery.class);

        // 2011-12-24 hunan add
        String notificationBar = cfgCmp.getSpecCharValueById(SpecCodeDefine.BUDGET_NOTI_RULE_BAR);// 停服务的默认提醒规则
        String notificationContinue = cfgCmp.getSpecCharValueById(SpecCodeDefine.BUDGET_NOTI_RULE_CONTINUE);//continue提醒规则
        String notification = cfgCmp.getSpecCharValueById(SpecCodeDefine.BUDGET_NOTI_RULE_NOTI);// 通知的默认提醒规则
        List<CoBudgetCharValue> list = new ArrayList<CoBudgetCharValue>();

        String action = CommonUtil.short2String(budgetControl.getAction());
        CharValueHelper helper;
        boolean needAddEqualsBudgetThreshold = true;
        long groupIdTemp = -1;

        ThresholdList thresholdList = budgetControl.getThresholdList();
        // @Date 2012-7-12 tangjl5 Story #49447 split需要转换为账务侧的measure_id和amount进行存储
        Double dbBudget = budgetControl.getBudget();

        if (thresholdList != null)
        {
            for (Threshold threshold : thresholdList.getThresholdList_Item())
            {
                Double dbThresholdValue = threshold.getThreshold().doubleValue();

                helper = new CharValueHelper(prodId, groupIdTemp, inputValidDate, inputExpireDate, objectId, objectType);
                list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_VALUE, dbThresholdValue)));
                list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_TYPE, threshold.getNotify_type())));
                list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_ADDR, threshold.getNotify_addr())));
                list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_PHONE, threshold.getNotify_phone_id())));

                if (dbBudget.equals(dbThresholdValue))
                {
                    // 把预算的action，保存到和budget相等的threshold的特征值中
                    list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_ACTION, action)));
                    //2012-06-28 yangjh story:49281 budget 增加区分是bar的告警还是continue的告警
                    short actionValue =   new Short(action).shortValue();
                    needAddEqualsBudgetThreshold = false;
                    //@Date 2012-09-13 yugb :修改界面action的显示问题
                    if(actionValue ==  EnumCodeDefine.BUDGET_ACTION_BARSERVICE){
                    	list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_NOTI_RULE_NOTI, notificationBar)));
                    }else if(actionValue ==EnumCodeDefine.BUDGET_ACTION_NOTIFY){
                    	list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_NOTI_RULE_NOTI, notificationContinue)));
                    }
                }
                else
                {
                    // 和budget不同的threshold的action特征值，填“通知”
                    list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_ACTION, EnumCodeDefine.BUDGET_ACTION_NOTIFY)));
                    // 统一填 提醒规则 nofication
                    list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_NOTI_RULE_NOTI, notification)));
                }

                groupIdTemp--;
            }
        }
        if (needAddEqualsBudgetThreshold)
        {
            helper = new CharValueHelper(prodId, groupIdTemp, inputValidDate, inputExpireDate, objectId, objectType);
            list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_VALUE, dbBudget)));
            // 默认用短信
            list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_TYPE, EnumCodeDefine.BUDGET_NOTIFY_TYPE_SMS)));
            list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_ADDR, null)));
            list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_THRESHOLD_NOTIFY_PHONE, null)));// 号码设置为空
            list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_ACTION, action)));

            // 统一填 提醒规则 nofication
            list.add(prodCharToBudgetChar(helper.getNew(SpecCodeDefine.BUDGET_NOTI_RULE_NOTI, notificationBar)));
        }

        return list;
    }

    /**
     * 根据预算产品编号，去修改对应的特征值
     * 
     * @param integer
     * @param objectId
     */
    public void modifyBudgetCharValueList(Long prodId, BudgetControl budgetControl, Long objectId, Integer objectType)
    {
        long groupId = 0;
        Long servId = budgetControl.getBusi_service_id();
        if (servId != null)
        {
            groupId = servId.longValue();
        }

        CharValueHelper helper = new CharValueHelper(prodId, groupId, inputValidDate, inputExpireDate, objectId, objectType);
//        Long dbBudget = AmountUtil.getDbAmount(budgetControl.getMeasure_id(), budgetControl.getBudget());// 数据库的值

        //2012-07-06 yangjh 原先调用getCondForDBCondition修改为getBudgetCondForDBCondition
        updateByCondition(prodCharToBudgetChar(helper.getUpdate(String.valueOf(groupId))), helper.getBudgetCondForDBCondition(SpecCodeDefine.BUDGET_RULE));
        updateByCondition(prodCharToBudgetChar(helper.getUpdate(String.valueOf(budgetControl.getBudget()))), helper.getBudgetCondForDBCondition(SpecCodeDefine.BUDGET_AMOUNT));
        updateByCondition(prodCharToBudgetChar(helper.getUpdate(budgetControl.getMeasure_id())), helper.getBudgetCondForDBCondition(SpecCodeDefine.BUDGET_MEASURE_ID));
        updateByCondition(prodCharToBudgetChar(helper.getUpdate(budgetControl.getPayment_mode())), helper.getBudgetCondForDBCondition(SpecCodeDefine.BUDGET_PAYMODE));
        updateByCondition(prodCharToBudgetChar(helper.getUpdate(budgetControl.getAction())), helper.getBudgetCondForDBCondition(SpecCodeDefine.BUDGET_ACTION));

        this.deleteThreshold(prodId, objectId);

        List<CoBudgetCharValue> list = this.getThresholdCharValueList(prodId, budgetControl, objectId, objectType);
        super.insertBatch(list);
    }

    /**
     * 查出账户预算对应的产品ID
     * 
     * @param paymode:0 1 -1
     */
    public CoProd queryBudgetProdByAcctIdPaymode(Long acctId, Integer paymode)
    {
        List<CoProd> prodList = context.getComponent(BaseProductComponent.class).queryProdListByAcctIdAndFlag(acctId, SpecCodeDefine.BUDGET);
        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        for (CoProd coProd : prodList)
        {
            // @Date 2012-08-30 tangjl5 :Bug #56979 若没有传入paymode，则不去匹配 
            if (paymode == null || paymode != null && paymode.intValue() == coProd.getBillingType())
            {
                return coProd;
            }
        }
        return null;
    }

    /**
     * 查出用户或服务预算对应的产品ID
     */
    private CoProd queryBudgetProdIdByUserIdGroupIdPaymode(Long userId, Integer paymode, Long groupId)
    {
        List<DBCondition> conditions = new ArrayList<DBCondition>();
        conditions.add(new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.BUDGET));
        conditions.add(new DBCondition(CoProd.Field.objectId, userId));
      //查询用户订购的产品时 增加object_type条件 yanchuan 2012-05-25
        conditions.add(new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        if (paymode != null)
        {
            conditions.add(new DBCondition(CoProd.Field.billingType, paymode));
//            conditions.add(new DBCondition(CoProd.Field.objectId, userId));
        }
        ResultTable<CoProd> resultTable = new ResultTable<CoProd>(CoProd.Field.productId,
                conditions.toArray(new DBCondition[conditions.size()]));

        //2012-07-06 yangjh story：50195 budget信息入CoBudgetCharValue表
        resultTable.addCondTable(CoBudgetCharValue.Field.productId, new DBCondition(CoBudgetCharValue.Field.groupId, groupId),
                new DBCondition(CoBudgetCharValue.Field.objectId, userId));

        List<CoProd> resultList = context.getComponent(BaseProductComponent.class).mergeProdList(
                context.getComponent(ResultComponent.class).getResultList(resultTable));
        if (CommonUtil.isEmpty(resultList))
        {
            return null;
        }
        return resultList.get(0);
    }

    /**
     * 查询用户预算ID
     */
    public CoProd queryUserBudgetProdIdByUserIdPaymode(Long userId, Integer paymode)
    {
        return this.queryBudgetProdIdByUserIdGroupIdPaymode(userId, paymode, 0L);
    }

    /**
     * 查询服务预算ID
     */
    public CoProd queryServiceBudgetProdIdByUserIdPaymodeServiceId(Long userId, Integer paymode, Long serviceId)
    {
        return this.queryBudgetProdIdByUserIdGroupIdPaymode(userId, paymode, serviceId);
    }

    /**
     * 删除账户对应的三表预算信息
     */
    public void deleteAccountBudgetByAcctIdPaymode(Long acctId, Integer paymode)
    {
        CoProd prod = this.queryBudgetProdByAcctIdPaymode(acctId, paymode);
        if (prod == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.SETBUT_BUDGET_NOT_EXIST);
        }
        this.deleteBudgetByProdId(prod.getProductId(), prod.getObjectId());
    }

    /**
     * 删除用户级预算
     */
    public void deleteUserBudgetByUserIdPaymode(Long userId, Integer paymode)
    {
        CoProd prod = this.queryUserBudgetProdIdByUserIdPaymode(userId, paymode);
        if (prod == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.SETBUT_BUDGET_NOT_EXIST);
        }
        this.deleteBudgetByProdId(prod.getProductId(), prod.getObjectId());
    }

    /**
     * 删除服务级预算
     */
    public void deleteServiceBudgetByUserIdPaymodeServiceId(Long userId, Integer paymode, Long serviceId)
    {
        CoProd prod = this.queryServiceBudgetProdIdByUserIdPaymodeServiceId(userId, paymode, serviceId);
        if (prod == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.SETBUT_BUDGET_NOT_EXIST);
        }

        this.deleteBudgetByProdId(prod.getProductId(), prod.getObjectId());
    }

    /**
     * 根据产品编号，删除预算三表信息<br>
     * 
     * @param acctId
     */
    private void deleteBudgetByProdId(Long prodId, Long objectId)
    {
        super.deleteByCondition(CoProd.class, new DBCondition(CoProd.Field.productId, prodId), new DBCondition(
                CoProd.Field.objectId, objectId));
//        super.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(
//                CoProd.Field.objectId, objectId));
        //2012-07-06 yangjh story：50195 budget信息入CoBudgetCharValue表
        super.deleteByCondition(CoBudgetCharValue.class, new DBCondition(CoBudgetCharValue.Field.productId,prodId), new DBCondition(
        		CoBudgetCharValue.Field.objectId, objectId));
        //@Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
        context.getComponent(BaseProductComponent.class).modifyProdValid(prodId, context.getRequestDate());
        // 删除账期信息
        super.deleteByCondition(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prodId),
                new DBCondition(CoProdBillingCycle.Field.objectId, objectId));

    }
    /**
     * @Date 2012-08-27 yugb 
     * 根据产品编号，不用先查询,直接删除预算三表信息<br>
     * 
     * @param acctId
     */
    public void deleteBudgetByProdIdDir(Long prodId, Long objectId)
    {
        super.deleteByCondition_noInsert(CoProd.class, null,new DBCondition(CoProd.Field.productId, prodId), new DBCondition(
                CoProd.Field.objectId, objectId));
        super.deleteByCondition_noInsert(CoBudgetCharValue.class,null, new DBCondition(CoBudgetCharValue.Field.productId,prodId), new DBCondition(
                CoBudgetCharValue.Field.objectId, objectId));
        // 删除账期信息
        super.deleteByCondition_noInsert(CoProdBillingCycle.class,null ,new DBCondition(CoProdBillingCycle.Field.productId, prodId),
                new DBCondition(CoProdBillingCycle.Field.objectId, objectId));

    }

    /**
     * 删除所有的threshold
     * 
     * @param objectId
     */
    public void deleteThreshold(Long prodId, Long objectId)
    {
        // delete before insert
    	//2012-07-06 yangjh story：50195 budget信息入CoBudgetCharValue表
        super.deleteByCondition_noInsert(CoBudgetCharValue.class, null, new DBCondition(CoBudgetCharValue.Field.productId, prodId),
                new DBCondition(CoBudgetCharValue.Field.objectId, objectId), new DBCondition(CoBudgetCharValue.Field.groupId, 0,
                        Operator.LESS));
    }

   

    /**
     * 删除账户预算（最多包含付费模式为0,1,2的三种）
     */
    public void deleteAccountBudgetByAcctId(Long acctId)
    {
        List<CoProd> prodList = context.getComponent(BaseProductComponent.class).queryProdListByAcctIdAndFlag(acctId, SpecCodeDefine.BUDGET);
        if (CommonUtil.isEmpty(prodList))
        {
            return;
        }

        for (CoProd coProd : prodList)
        {
            this.deleteBudgetByProdId(coProd.getProductId(), coProd.getObjectId());
        }
    }

    /**
     * 删除用户和服务级预算(三表信息)
     */
    public void deleteUserAndServiceBudgetByUserId(Long userId)
    {
        List<CoProd> prodList = context.getComponent(BaseProductComponent.class).queryProdListByUserId(userId, SpecCodeDefine.BUDGET);
        if (CommonUtil.isEmpty(prodList))
        {
            return;
        }

        for (CoProd coProd : prodList)
        {
            this.deleteBudgetByProdId(coProd.getProductId(), coProd.getObjectId());
        }
    }

    /**
     * 查询账户的预算值，没有预算返回0(付费模式为2)
     */
    public long getBudgetValueByAcctId(Long acctId)
    {
        return getBudgetValue(acctId, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
    }

    /**
     * 查询用户的预算值，没有预算返回0(付费模式为2)
     */
    public long getBudgetValueByUserId(Long userId)
    {
        return getBudgetValue(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
    }

    private long getBudgetValue(Long objectId, Integer objectType)
    {
        ResultTable<CoBudgetCharValue> resultTable = new ResultTable<CoBudgetCharValue>(CoBudgetCharValue.Field.productId,
                new DBCondition(CoBudgetCharValue.Field.specCharId, SpecCodeDefine.BUDGET_AMOUNT), new DBCondition(
                		CoBudgetCharValue.Field.objectId, objectId), new DBCondition(CoBudgetCharValue.Field.objectType, objectType));

        resultTable.addCondTable(CoProd.Field.productId, new DBCondition(CoProd.Field.busiFlag, SpecCodeDefine.BUDGET),
                new DBCondition(CoProd.Field.billingType, EnumCodeDefine.USER_PAYMODE_HYBRID), new DBCondition(
                        CoProd.Field.objectId, objectId), new DBCondition(CoProd.Field.objectType, objectType));// 混合模式预算

        List<CoBudgetCharValue> resultList = context.getComponent(ResultComponent.class).getResultList(resultTable);
        if (CommonUtil.isEmpty(resultList))
        {
            return 0;
        }
        CoBudgetCharValue CoBudgetCharValue = resultList.get(0);

        String budgetValue = CoBudgetCharValue.getValue();

        if (null != budgetValue)
        {
            return CommonUtil.string2Long(budgetValue);
        }
        return 0;
    }
    
    /**
     * @Description: 查询budget单位
     * @param objectId
     * @param objectType
     * @return	 
     * @author: tangjl5
     * @Date: 2012-8-21
     */
    public Integer queryBudgetMeasureID(Long objectId, Integer objectType)
    {
        CoBudgetCharValue coBudgetCharValue = querySingle(CoBudgetCharValue.class, 
                new DBCondition(CoBudgetCharValue.Field.objectId, objectId), new DBCondition(CoBudgetCharValue.Field.objectType, objectType),
                        new DBCondition(CoBudgetCharValue.Field.specCharId, SpecCodeDefine.BUDGET_MEASURE_ID));

        if (coBudgetCharValue != null)
        {
            return CommonUtil.string2Integer(coBudgetCharValue.getValue());
        }
        
        return null;
    }
    
    public void checkAndSetBudgetControl(BudgetControl budgetControl)
    {
        // action检查
        Short action = budgetControl.getAction();
        if (action == null)
        {
            // 默认为 do nothing
       	 throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "action", "1,2,3");

        }
        else if (action != EnumCodeDefine.BUDGET_ACTION_NOTIFY && action != EnumCodeDefine.BUDGET_ACTION_BARSERVICE)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "action", "1,2,3");
        }

        ThresholdList thresholdList = budgetControl.getThresholdList();
        Double budgetValue = budgetControl.getBudget();
        if (thresholdList != null && CommonUtil.isNotEmpty(thresholdList.getThresholdList_Item()))
        {
            for (Threshold threshold : thresholdList.getThresholdList_Item())
            {
                Short notifyType = threshold.getNotify_type();
                if (notifyType == null)
                {
                    // 如果为空，则设置为短信通知
                    threshold.setNotify_type(EnumCodeDefine.BUDGET_NOTIFY_TYPE_SMS);
                }
                else if (notifyType != EnumCodeDefine.BUDGET_NOTIFY_TYPE_SMS
                        && notifyType != EnumCodeDefine.BUDGET_NOTIFY_TYPE_EMAIL
                        && notifyType != EnumCodeDefine.BUDGET_NOTIFY_TYPE_BOTH)
                {// 不为空，必须为1,2,3之一
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "notify_type", "1,2,3");
                }
                // 阀值不能大于预算
                if (CommonUtil.isValid(budgetValue) && threshold.getThreshold() > budgetValue.longValue())
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.SETBGT_THRESHOLD_VALUE_LARGE_THAN_BUDGET);
                }
            }
        }
    }

    public List<Integer> getAllServiceSpecIdByUser(Long userId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        List<CoProd> prods = prodCmp.queryProdListByUserId(userId);
        Set<Integer> prodIds = new HashSet<Integer>();
        if (CommonUtil.isEmpty(prods))
        {
            return null;
        }
        else
        {
            for (CoProd dmProd : prods)
            {
                prodIds.add(dmProd.getProductOfferingId());
            }

        }
        List<Integer> serviceSpecIdList = context.getComponent(ConfigQuery.class).queryServiceSpecIdListByOfferIds(prodIds);
        return serviceSpecIdList;
    }
    
    /**
     * yangjh 类型转换
     */
    private CoBudgetCharValue prodCharToBudgetChar(CoProdCharValue coProdCharValue)
    {
        if (coProdCharValue == null)
        {
            return null;
        }
        CoBudgetCharValue coBudgetCh = new CoBudgetCharValue();
        if (coProdCharValue.getProductId() != null)
            coBudgetCh.setProductId(coProdCharValue.getProductId());
        if (coProdCharValue.getGroupId() != null)
            coBudgetCh.setGroupId(coProdCharValue.getGroupId());
        if (coProdCharValue.getObjectId() != null)
            coBudgetCh.setObjectId(coProdCharValue.getObjectId());
        if (coProdCharValue.getObjectType() != null)
            coBudgetCh.setObjectType(coProdCharValue.getObjectType());
        if (coProdCharValue.getSoDate() != null)
            coBudgetCh.setSoDate(coProdCharValue.getSoDate());
        if (coProdCharValue.getSoNbr() != null)
            coBudgetCh.setSoNbr(coProdCharValue.getSoNbr());
        if (coProdCharValue.getSpecCharId() != null)
            coBudgetCh.setSpecCharId(coProdCharValue.getSpecCharId());
        if (coProdCharValue.getValue() != null)
            coBudgetCh.setValue(coProdCharValue.getValue());
        if (coProdCharValue.getCreateDate() != null)
            coBudgetCh.setCreateDate(coProdCharValue.getCreateDate());
        if (coProdCharValue.getExpireDate() != null)
            coBudgetCh.setExpireDate(coProdCharValue.getExpireDate());
        if (coProdCharValue.getValidDate() != null)
            coBudgetCh.setValidDate(coProdCharValue.getValidDate());
        return coBudgetCh;
    }
}
