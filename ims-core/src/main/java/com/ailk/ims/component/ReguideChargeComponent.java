package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.ResultTable;
import com.ailk.ims.complex.CharValueHelper;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.AmountUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsintf.entity.SPayReguideInfo;

/**
 * @Description:reguide charge：只有账户对账户，账户对用户两种
 * @author wangjt
 * @Date 2011-9-27
 * @Date 2012-3-31 tangjl5 修改删除用户被REGUIDE_CHARGE的产品,删除多余代码
 * @Date 2012-04-10 yangjh getTime_period->setTime_segment
 * @Date 2012-05-10 lijc3 增加处理货币单位
 * @Date 2012-5-19 yangjh task：46018 Reguide charge已生效记录只允许上调代付限额
 * @Date 2012-5-19 yangjh modifyReguide 查询语句修改
 * @Date 2012-5-23 yangjh  task：46018 Reguide charge已生效记录只允许上调代付限额 增加时间判断，这个限制只对已生效记录有效
 * @Date 2012-5-24 tangjl5 #46657
 * @Date 2012-7-18 tangkun #50785 Reguide接口变更，去掉ThresholdList
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @date 2012-08-07 luojb  #54807 参数结构变更
 */
public class ReguideChargeComponent extends ReguideComponent
{
    /**
     * 默认的分母的值
     */
    private final String DENOMINATOR_MAX = "100";
    private final String DENOMINATOR_MIN = "1";
    /**
     * 没有提醒阀值的时候，存入-1
     */
    private final String NO_NOTI_THRESHOLD = "-1";

    public ReguideChargeComponent()
    {
        super.setReguideType(EnumCodeDefine.REGUIDE_TYPE_CHARGE);
    }

    public void createReguide4Account(Long acctId, Long busiServiceId, SPayReguideInfo sPayReguideInfo, String timeSegment)
    {

        CoProd coProd = super.insertCoProd(sPayReguideInfo);
        Long prodId = coProd.getProductId();

        int objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        this.insertCharValueList(prodId, sPayReguideInfo, busiServiceId, timeSegment, objectType, acctId);

        // 增加账期
        ProductCycleComponent prodCmp = context.getComponent(ProductCycleComponent.class);
        prodCmp.createProdBillCycle(coProd, acctId);
    }

    /**
     * luojb 2012-8-7
     * @param payAcctId
     * @param userId
     * @param busiServiceId
     * @param sPayReguideInfo
     * @param timeSegment
     */
    public void createReguide4User(Long payAcctId, Long userId, Long busiServiceId, SPayReguideInfo sPayReguideInfo,
            String timeSegment)
    {

        CoProd coProd = super.insertCoProd(sPayReguideInfo);
        Long prodId = coProd.getProductId();

        int objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        this.insertCharValueList(prodId, sPayReguideInfo, busiServiceId, timeSegment, objectType, userId);

        // 增加账期(和账户账期一致)
        ProductCycleComponent prodCmp = context.getComponent(ProductCycleComponent.class);
        prodCmp.createProdBillCycle(coProd, payAcctId);
    }

    private void insertCharValueList(Long prodId, SPayReguideInfo info, Long busiServiceId, String timeSegment, int objectType,
            Long objectId)
    {
        String validDate = info.getValid_date();
        String expireDate = info.getExpire_date();

        CharValueHelper helper = new CharValueHelper(prodId, busiServiceId, validDate, expireDate, info.getPay_acct_id(),
                EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);

        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();

        list.add(helper.getNew(SpecCodeDefine.RC_BUSI_SERVICE_ID, busiServiceId));
        list.add(helper.getNew(SpecCodeDefine.RC_TIME_PERIOD, timeSegment));
        list.add(helper.getNew(SpecCodeDefine.RC_OBJECT_TYPE, objectType));
        list.add(helper.getNew(SpecCodeDefine.RC_OBJECT_ID, objectId));

        list.add(helper.getNew(SpecCodeDefine.RC_PRIORITY, info.getPriority()));
        list.add(helper.getNew(SpecCodeDefine.RC_B_NUMBER, info.getB_number()));
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        
        Integer orgMeasureId = getMeasureIdByObjectId(info.getMeasure_id(), objectType, objectId);
        Integer targetMeasureId = amountCmp.getRatingMeasureId(info.getMeasure_id(), getAcctIdByObjectType(objectType, objectId));
        // taoyf 加于2012-2-20 用于储存Measure_id。
        //@Date 2012-05-10 lijc3 增加处理货币单位
        list.add(helper.getNew(SpecCodeDefine.RC_MEASURE_ID, targetMeasureId));
        
        Long partType = info.getPart_type();
        if (partType == EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE)
        {
            Double maxValue = info.getMax_value();
            Long maxValueDb = -1L;// 默认值为-1，表示无穷大
            if (CommonUtil.isValid(maxValue))
            {
                maxValueDb = CommonUtil.double2Long(amountCmp.parseAmount(maxValue, orgMeasureId, targetMeasureId));// 转换后的值
            }
            list.add(helper.getNew(SpecCodeDefine.RC_AMOUNT, maxValueDb));
            // 2012-02-21 yangjh add
            if (info.getPart_value() > 100)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.PERCENTAGE_VALUE_LARGER_THAN_100);
            }
            list.add(helper.getNew(SpecCodeDefine.RC_NUMERATOR, info.getPart_value()));// 按比例时，填输入值
            list.add(helper.getNew(SpecCodeDefine.RC_DENOMINATOR, DENOMINATOR_MAX));
        }
        else if (partType == EnumCodeDefine.PAY_PART_TYPE_AMOUNT)
        {
            Long partValueDb = CommonUtil.double2Long(amountCmp.parseAmount(info.getPart_value(), orgMeasureId, targetMeasureId));
            list.add(helper.getNew(SpecCodeDefine.RC_AMOUNT, partValueDb));// 转换后的值
            list.add(helper.getNew(SpecCodeDefine.RC_NUMERATOR, DENOMINATOR_MIN));
            list.add(helper.getNew(SpecCodeDefine.RC_DENOMINATOR, DENOMINATOR_MIN));
        }
        // 2011-12-15 hunan add :从配置表中读取增加的提醒值
        ConfigQuery cfgCmp = context.getComponent(ConfigQuery.class);
        String notiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RC_PAY_SUCC_NOTI);
        String bePaidSideNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RC_BEPAID_SUCC_NOTI);
        String failNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RC_PAY_FAIL_BAR);
        String bePaidSideFailNotiValue = cfgCmp.getSpecCharValueById(SpecCodeDefine.RC_BEPAID_FAIL_BAR);

        list.add(helper.getNew(SpecCodeDefine.RC_PAY_SUCC_NOTI, notiValue));
        list.add(helper.getNew(SpecCodeDefine.RC_BEPAID_SUCC_NOTI, bePaidSideNotiValue));
        list.add(helper.getNew(SpecCodeDefine.RC_PAY_FAIL_BAR, failNotiValue));
        list.add(helper.getNew(SpecCodeDefine.RC_BEPAID_FAIL_BAR, bePaidSideFailNotiValue));
        
        //@Date 2012-7-18 tangkun #50785 Reguide接口变更，去掉ThresholdList
        // 2011-12-15 hunan modify
        Double threshold = info.getThreshold();
        if(CommonUtil.isValid(threshold))
        {
            Long thresholdDb = CommonUtil.double2Long(amountCmp.parseAmount(threshold, orgMeasureId, targetMeasureId));
            list.add(helper.getNew(SpecCodeDefine.RC_WARN_MAXVALUE, thresholdDb));// 转换后的值
            list.add(helper.getNew(SpecCodeDefine.RC_TARGET_WARN_MAXVALUE, threshold));
        }
        else
        {
            list.add(helper.getNew(SpecCodeDefine.RC_WARN_MAXVALUE, NO_NOTI_THRESHOLD));
            list.add(helper.getNew(SpecCodeDefine.RC_TARGET_WARN_MAXVALUE, NO_NOTI_THRESHOLD));
        }

        super.insertBatch(list);
    }
    
    public Integer getMeasureIdByObjectId(Integer measureId, Integer objectType, Long objectId)
    {
        if (measureId != null)
        {
            return measureId;
        }
        if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            User3hBean user3hBean = context.get3hTree().loadUser3hBean(objectId);
            return user3hBean.getAccount().getMeasureId();
        }
        else
        {
            Acct3hBean acct3hBean = context.get3hTree().loadAcct3hBean(objectId);
            return acct3hBean.getAccount().getMeasureId();
        }
    }
    
    public Long getAcctIdByObjectType(Integer objectType, Long objectId)
    {
        if (objectType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            User3hBean user3hBean = context.get3hTree().loadUser3hBean(objectId);
            return user3hBean.getAcctId();
        }
        else
        {
            return objectId;
        }
    }

    public void modifyReguide(Long prodId, Long busiServiceId, SPayReguideInfo info, String timeSegment)
    {
        // modify by yangjh 2012-02-23
        Date validDate = IMSUtil.formatDate(info.getValid_date(), context.getRequestDate());
        Date expireDate = IMSUtil.formatExpireDate(info.getExpire_date());
        Long objectId = info.getPay_acct_id();
        Integer objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        CoProd prod = super.querySingle(CoProd.class, new DBCondition(CoProd.Field.productId,prodId));
        Date oldValidDate = prod.getValidDate();
        CoProd coProd = new CoProd();
        coProd.setValidDate(validDate);
        coProd.setExpireDate(expireDate);
//        CoProd coProdWhere = new CoProd();
//        coProdWhere.setProductId(prodId);
//        coProdWhere.setObjectId(objectId);
//        coProdWhere.setObjectType(objectType);

        // 更新生失效时间
        updateByCondition(coProd, new DBCondition(CoProd.Field.productId, prodId),
                new DBCondition(CoProd.Field.objectId, objectId),
                new DBCondition(CoProd.Field.objectType, objectType));
        
        //@Date 2012-06-07 lijc3 增加处理CO_PROD_VALID
        context.getComponent(BaseProductComponent.class).modifyProdValid(prodId, expireDate);
        Long groupId = busiServiceId;

        CharValueHelper helper = new CharValueHelper(prodId, groupId, validDate, expireDate, objectId, objectType);

        updateByCondition(helper.getUpdate(busiServiceId), helper.getCondForDBCondition(SpecCodeDefine.RC_BUSI_SERVICE_ID));
        updateByCondition(helper.getUpdate(timeSegment), helper.getCondForDBCondition(SpecCodeDefine.RC_TIME_PERIOD));
        updateByCondition(helper.getUpdate(info.getPriority()), helper.getCondForDBCondition(SpecCodeDefine.RC_PRIORITY));
        updateByCondition(helper.getUpdate(info.getB_number()), helper.getCondForDBCondition(SpecCodeDefine.RC_B_NUMBER));
        // taoyf 加于2012-2-20 用于修改Measure_id,不传我就表示不要修改。
        if (info.getMeasure_id() != null)
        {
            updateByCondition(helper.getUpdate(info.getMeasure_id()), helper.getCondForDBCondition(SpecCodeDefine.RC_MEASURE_ID));
        }

        Long partType = info.getPart_type();
        
        //@Date 2012-5-19 yangjh task：46018 Reguide charge已生效记录只允许上调代付限额 增加时间判断，这个限制只对已生效记录有效
        modifyReguideInfo(partType,oldValidDate,helper,info,groupId,objectId,objectType,prodId);
        
        context.getComponent(BudgetComponent.class).deleteThreshold(prodId, objectId);
        
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        Integer orgMeasureId = getMeasureIdByObjectId(info.getMeasure_id(), objectType, objectId);
        Integer targetMeasureId = amountCmp.getRatingMeasureId(info.getMeasure_id(), getAcctIdByObjectType(objectType, objectId));
        
        //@Date 2012-7-18 tangkun #50785 Reguide接口变更，去掉ThresholdList
        Double threshold = info.getThreshold();
        if(CommonUtil.isValid(threshold))
        {
            Long thresholdDb = CommonUtil.double2Long(amountCmp.parseAmount(threshold, orgMeasureId, targetMeasureId));
            // modify by yangjh 2012-02-22
            updateByCondition(helper.getUpdate(thresholdDb), helper.getCondForDBCondition(SpecCodeDefine.RC_WARN_MAXVALUE));
            updateByCondition(helper.getUpdate(threshold),helper.getCondForDBCondition(SpecCodeDefine.RC_TARGET_WARN_MAXVALUE));
        }
        else
        {
            // modify by yangjh 2012-02-22
            updateByCondition(helper.getUpdate(NO_NOTI_THRESHOLD), helper.getCondForDBCondition(SpecCodeDefine.RC_WARN_MAXVALUE));
            updateByCondition(helper.getUpdate(NO_NOTI_THRESHOLD), helper.getCondForDBCondition(SpecCodeDefine.RC_TARGET_WARN_MAXVALUE));
        }
    }

    private void modifyReguideInfo(Long partType,Date validDate,CharValueHelper helper,SPayReguideInfo info, Long groupId, 
    		Long objectId, Integer objectType, Long prodId) 
    {
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        Integer orgMeasureId = getMeasureIdByObjectId(info.getMeasure_id(), objectType, objectId);
        Integer targetMeasureId = amountCmp.getRatingMeasureId(info.getMeasure_id(), getAcctIdByObjectType(objectType, objectId));
    	if(validDate.before(context.getRequestDate()))
        {
        	if (partType == EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE)
            {
            	Long amount = null;
            	Long numerator = null;
            	Long denominator = null;
            	if (info.getPart_value() > 100)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.PERCENTAGE_VALUE_LARGER_THAN_100);
                }
            	//2012-5-19 yangjh modify
            	List<CoProdCharValue> values = this.query(CoProdCharValue.class,new DBCondition(CoProdCharValue.Field.productId, prodId),
    			            new DBCondition(CoProdCharValue.Field.groupId, groupId),
    			            new DBCondition(CoProdCharValue.Field.objectId, objectId),
    			            new DBCondition(CoProdCharValue.Field.objectType, objectType),
            			    new DBOrCondition(new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RC_NUMERATOR),
            					            new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RC_DENOMINATOR),
            					            new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeDefine.RC_AMOUNT)));
                if(CommonUtil.isEmpty(values))
                	return;
                for(CoProdCharValue value:values)
                {
                	if(value.getSpecCharId() == SpecCodeDefine.RC_AMOUNT){
                		amount = CommonUtil.StringToLong(value.getValue());
                	}
                	if(value.getSpecCharId() == SpecCodeDefine.RC_NUMERATOR){
                		numerator = CommonUtil.StringToLong(value.getValue());
                	}
                	if(value.getSpecCharId() == SpecCodeDefine.RC_DENOMINATOR){
                		denominator = CommonUtil.StringToLong(value.getValue());
                	}
                }
            	Double maxValue = info.getMax_value();
                Double partValue = info.getPart_value();
                Long maxValueDb = -1L;// 默认值为-1，表示无穷大
                if (CommonUtil.isValid(maxValue))
                {
                    maxValueDb = CommonUtil.double2Long(amountCmp.parseAmount(maxValue, orgMeasureId, targetMeasureId));// 转换后的值
                }
                //10608的值只有1和100两种情况，1表示固定金额代付，100表示分母，是按比例代付
                if(denominator == 1L)
            	{
                	//10608的值为1 表示原先是固定金额代付
                	//如果没传maxValue，那么10606 就取maxValueDb=-1，表示无穷大，可以修改
                	if(!CommonUtil.isValid(maxValue)){
                        updateByCondition(helper.getUpdate(maxValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                        updateByCondition(helper.getUpdate(info.getPart_value()), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
                	}else {
                		//如果传了maxValue 那么新的最大值必须大于原先的代付金额，否则报错
                		if(amount > maxValueDb )
                    	{
                	 		throw IMSUtil.throwBusiException(ErrorCodeDefine.REGUIDE_CHARGE_VALUE_ERROR,maxValueDb,amount);
                    	}else{
                    		 updateByCondition(helper.getUpdate(maxValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                             updateByCondition(helper.getUpdate(info.getPart_value()), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
                    	}
                	}
            	}
                else{
                	//原先代付关系是按比例代付并且原先就是最大值，则现在修改只能是最大值，也就是报文中不能传最大值字段，并且代付比例要比原先大才能修改
                	if(partValue < numerator)
    				{
    					throw IMSUtil.throwBusiException(ErrorCodeDefine.REGUIDE_CHARGE_VALUE_ERROR,partValue,numerator);
    				}
            		if(amount == -1L)
            		{
            			if(CommonUtil.isValid(maxValue))
            			{
            				throw IMSUtil.throwBusiException(ErrorCodeDefine.REGUIDE_CHARGE_MAXVALUE_MUST_BE_NULL);
            			}else{
                            updateByCondition(helper.getUpdate(maxValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                            updateByCondition(helper.getUpdate(info.getPart_value()), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
            			}
            		}else{
        				if(CommonUtil.isValid(maxValue) && maxValueDb >= amount)
        				{
                            updateByCondition(helper.getUpdate(maxValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                            updateByCondition(helper.getUpdate(info.getPart_value()), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
                            //@Date 2012-08-18 yangjh : 删除updateByCondition最后会有一个update操作  主键冲突
//                            updateByCondition(helper.getUpdate(DENOMINATOR_MAX), helper.getCondForDBCondition(SpecCodeDefine.RC_DENOMINATOR));
        			    }
        	            else if(!CommonUtil.isValid(maxValue))
        				{
                             updateByCondition(helper.getUpdate(maxValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                             updateByCondition(helper.getUpdate(info.getPart_value()), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
        				}else{
        				     throw IMSUtil.throwBusiException(ErrorCodeDefine.REGUIDE_CHARGE_VALUE_ERROR,maxValueDb,amount);
        				}
        			}
            	}
                // 分母填100
                updateByCondition(helper.getUpdate(DENOMINATOR_MAX), helper.getCondForDBCondition(SpecCodeDefine.RC_DENOMINATOR));
            }
            else if (partType == EnumCodeDefine.PAY_PART_TYPE_AMOUNT)
            {
            	Long amount = null;
            	Long denominator = null;
            	//2012-5-19 yangjh modify
            	List<CoProdCharValue> values = this.query(CoProdCharValue.class,new DBCondition(CoProdCharValue.Field.productId, prodId),
    		            new DBCondition(CoProdCharValue.Field.groupId, groupId),
    		            new DBCondition(CoProdCharValue.Field.objectId, objectId),
    		            new DBCondition(CoProdCharValue.Field.objectType, objectType),
        			    new DBOrCondition(new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RC_DENOMINATOR),
        					            new DBCondition(CoProdCharValue.Field.specCharId,SpecCodeDefine.RC_AMOUNT)));
    	        if(CommonUtil.isEmpty(values))
    	        	return;
    	        for(CoProdCharValue value:values)
    	        {
    	        	if(value.getSpecCharId() == SpecCodeDefine.RC_AMOUNT){
    	        		amount = CommonUtil.StringToLong(value.getValue());
    	        	}
    	        	if(value.getSpecCharId() == SpecCodeDefine.RC_DENOMINATOR){
    	        		denominator = CommonUtil.StringToLong(value.getValue());
    	        	}
    	        }
    	        
                Long partValueDb = CommonUtil.double2Long(amountCmp.parseAmount(info.getPart_value(), orgMeasureId, targetMeasureId));// 转换后的值
            	if(denominator == 1L)
            	{
            		//原先也是固定金额代付，只需判断代付金额是否大于原先值
                    if(partValueDb < amount){
                    	throw IMSUtil.throwBusiException(ErrorCodeDefine.REGUIDE_CHARGE_VALUE_ERROR,partValueDb,amount);
                    }
                    // 数量，填part_value
                    updateByCondition(helper.getUpdate(partValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                }else{
                	//原先是比例代付，如果原先的代付最大值是-1 也就是无穷大，则不允许修改代付金额,因为part_value是必填的 所以这里肯定要报错
                	if(amount == -1L){
                		throw IMSUtil.throwBusiException(ErrorCodeDefine.REGUIDE_CHARGE_AMOUNT_ERROR);
                	}else{
                		if(partValueDb < amount)
                		{
                			throw IMSUtil.throwBusiException(ErrorCodeDefine.REGUIDE_CHARGE_VALUE_ERROR,partValueDb,amount);
                		}
                		updateByCondition(helper.getUpdate(partValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                	}
                }
            	// 分子分母 填 1
            	updateByCondition(helper.getUpdate(DENOMINATOR_MIN), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
                updateByCondition(helper.getUpdate(DENOMINATOR_MIN), helper.getCondForDBCondition(SpecCodeDefine.RC_DENOMINATOR));
            }
        }else{
        	if (partType == EnumCodeDefine.PAY_PART_TYPE_PERCENTAGE)
            {
                Double maxValue = info.getMax_value();
                Long maxValueDb = -1L;// 默认值为-1，表示无穷大
                if (CommonUtil.isValid(maxValue))
                {
                    maxValueDb = CommonUtil.double2Long(amountCmp.parseAmount(maxValue, orgMeasureId, targetMeasureId));// 转换后的值
                }

                // 数量，填最大值
                updateByCondition(helper.getUpdate(maxValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                // 分子，填part_value
                updateByCondition(helper.getUpdate(info.getPart_value()), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
                // 分母填100
                updateByCondition(helper.getUpdate(DENOMINATOR_MAX), helper.getCondForDBCondition(SpecCodeDefine.RC_DENOMINATOR));
            }
            else if (partType == EnumCodeDefine.PAY_PART_TYPE_AMOUNT)
            {
                Long partValueDb = CommonUtil.double2Long(amountCmp.parseAmount(info.getPart_value(), orgMeasureId, targetMeasureId));// 转换后的值

                // 数量，填part_value
                updateByCondition(helper.getUpdate(partValueDb), helper.getCondForDBCondition(SpecCodeDefine.RC_AMOUNT));
                // 分子分母 填 1
                updateByCondition(helper.getUpdate(DENOMINATOR_MIN), helper.getCondForDBCondition(SpecCodeDefine.RC_NUMERATOR));
                updateByCondition(helper.getUpdate(DENOMINATOR_MIN), helper.getCondForDBCondition(SpecCodeDefine.RC_DENOMINATOR));
            }
        }
	}

	public boolean isAccountReguideExist(Long payAcctId, Long acctId, Long busiServiceId)
    {
        return queryReguide4Account(payAcctId, acctId, busiServiceId) != null;
    }

    public boolean isUserReguideExist(Long payAcctId, Long userId, Long busiServiceId)
    {
        return queryReguide4User(payAcctId, userId, busiServiceId) != null;
    }

    public Long queryReguide4Account(Long payAcctId, Long acctId, Long busiServiceId)
    {
        return queryReguide(payAcctId, busiServiceId, acctId, null);
    }

    public Long queryReguide4User(Long payAcctId, Long userId, Long busiServiceId)
    {
        return queryReguide(payAcctId, busiServiceId, null, userId);
    }

    private Long queryReguide(Long payAcctId, Long busiServiceId, Long acctId, Long userId)
    {
        // 修改联表查询为单表查询 yanchuan 2012-01-11
        ResultTable<CoProd> resultTable = new ResultTable<CoProd>(CoProd.Field.productId, 
                new DBCondition(CoProd.Field.objectId,payAcctId),
                new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
                new DBCondition(CoProd.Field.busiFlag, super.getBusiFlag())
                );
        if (CommonUtil.isValid(acctId))
        {
            resultTable.addCondTable(CoProdCharValue.Field.productId, new DBCondition(CoProdCharValue.Field.specCharId,
                    SpecCodeDefine.RC_OBJECT_TYPE), new DBCondition(CoProdCharValue.Field.value,
                    String.valueOf(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)), new DBCondition(CoProdCharValue.Field.groupId, busiServiceId),
                    new DBCondition(CoProdCharValue.Field.objectId, payAcctId), new DBCondition(CoProdCharValue.Field.objectType,
                            EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

            resultTable.addCondTable(CoProdCharValue.Field.productId, new DBCondition(CoProdCharValue.Field.specCharId,
                    SpecCodeDefine.RC_OBJECT_ID), new DBCondition(CoProdCharValue.Field.value, String.valueOf(acctId)), new DBCondition(
                    CoProdCharValue.Field.groupId, busiServiceId), new DBCondition(CoProdCharValue.Field.objectId, payAcctId),
                    new DBCondition(CoProdCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

        }
        else if (CommonUtil.isValid(userId))
        {
            resultTable.addCondTable(CoProdCharValue.Field.productId, new DBCondition(CoProdCharValue.Field.specCharId,
                    SpecCodeDefine.RC_OBJECT_TYPE), new DBCondition(CoProdCharValue.Field.value,
                            String.valueOf(EnumCodeDefine.PROD_OBJECTTYPE_DEV)), new DBCondition(CoProdCharValue.Field.groupId, busiServiceId),
                    new DBCondition(CoProdCharValue.Field.objectId, payAcctId), new DBCondition(CoProdCharValue.Field.objectType,
                            EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));

            resultTable.addCondTable(CoProdCharValue.Field.productId, new DBCondition(CoProdCharValue.Field.specCharId,
                    SpecCodeDefine.RC_OBJECT_ID), new DBCondition(CoProdCharValue.Field.value, String.valueOf(userId)), new DBCondition(
                    CoProdCharValue.Field.groupId, busiServiceId), new DBCondition(CoProdCharValue.Field.objectId, payAcctId),
                    new DBCondition(CoProdCharValue.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
        }

        List<CoProd> resultList = context.getComponent(BaseProductComponent.class).mergeProdList(
                context.getComponent(ResultComponent.class).getResultList(resultTable));
        if (CommonUtil.isEmpty(resultList))
        {
            return null;
        }
        return resultList.get(0).getProductId();

    }

    /*
     * beObjectType必须是PROD_OBJECTTYPE_ACCOUNT或者PROD_OBJECTTYPE_DEV 其中一个！
     */
    public List<CoProd> queryProdListByBepayed(Long objId, int beObjectType, Long payAcctId)
    {
        if(beObjectType != EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT && beObjectType != EnumCodeDefine.PROD_OBJECTTYPE_DEV ){
            beObjectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        }
        
        List<CoProd> prodList = new ArrayList<CoProd>();
        List<CoProdCharValue> charValueList = query(
                CoProdCharValue.class, 
                new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RC_OBJECT_ID),
                new DBCondition(CoProdCharValue.Field.value, String.valueOf(objId)),
//              new DBCondition(CoProdCharValue.Field.objectId,payAcctId),
                new DBCondition(CoProdCharValue.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
        
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        for(CoProdCharValue ccv : charValueList){
        // @Date 2012-3-31 tangjl5 修改删除用户被REGUIDE_CHARGE的产品,删除多余代码
//          CoProdCharValue type = querySingle(
//                  CoProdCharValue.class, 
//                  new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RC_OBJECT_TYPE),
//                  new DBCondition(CoProdCharValue.Field.productId, ccv.getProductId()),
//                    new DBCondition(CoProdCharValue.Field.objectId,payAcctId),
//                    new DBCondition(CoProdCharValue.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
//          if(beObjectType == Integer.parseInt(type.getValue())){
                CoProd prod = prodCmp.queryProd(ccv.getProductId());
                if(prod == null || prod.getBusiFlag().intValue() != SpecCodeDefine.REGUIDE_CHARGE || prod.getObjectType().intValue() != EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT){
                    continue;
                }
                prodList.add(prod);             
//          }
        }
                
        return prodList;

    }

    /*
     * beObjectType必须是PROD_OBJECTTYPE_ACCOUNT或者PROD_OBJECTTYPE_DEV 其中一个！
     */
    public List<CoProd> queryProdListByBepayed(Long objId, int beObjectType)
    {
        if(beObjectType != EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT && beObjectType != EnumCodeDefine.PROD_OBJECTTYPE_DEV ){
            beObjectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        }
        
        List<CoProd> prodList = new ArrayList<CoProd>();
        List<CoProdCharValue> charValueList = query(
                CoProdCharValue.class, 
                new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RC_OBJECT_ID),
                // @Date 2012-5-24 tangjl5 #46657
                new DBCondition(CoProdCharValue.Field.value, String.valueOf(objId)),
                new DBCondition(CoProdCharValue.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
        
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        for(CoProdCharValue ccv : charValueList){
           // @Date 2012-3-31 tangjl5 修改删除用户被REGUIDE_CHARGE的产品,删除多余代码
//          CoProdCharValue type = querySingle(
//                  CoProdCharValue.class, 
//                  new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.RC_OBJECT_TYPE),
//                  new DBCondition(CoProdCharValue.Field.productId, ccv.getProductId()),
//                    new DBCondition(CoProdCharValue.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT));
//          if(beObjectType == Integer.parseInt(type.getValue())){
            CoProd prod = prodCmp.queryProd(ccv.getProductId());
            if(prod == null || prod.getBusiFlag().intValue() != SpecCodeDefine.REGUIDE_CHARGE ||  prod.getObjectType().intValue() != EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT){
                continue;
            }
            prodList.add(prod);             
//          }
        }
                
        return prodList;

    }

}
