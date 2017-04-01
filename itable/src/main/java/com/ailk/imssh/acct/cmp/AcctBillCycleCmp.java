package com.ailk.imssh.acct.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.common.wrapper.Holder;
import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.component.BillingPlanQueryComponent;
import com.ailk.ims.component.billcycle.BillCycleSpecBusiness;
import com.ailk.ims.component.billcycle.entity.ChangeBillCycleContext;
import com.ailk.ims.component.billcycle.handler.BillCycleHandler;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.openbilling.persistence.acct.entity.CaBillingCycleSpec;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpec;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpecIn;
import com.ailk.openbilling.persistence.bill.entity.CaCycleSpecOut;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.itable.entity.IAcctBillCycle;

/**
 * @Description:账户账期组件
 * @author caohm5
 * @Date 2012-5-17
 */
public class AcctBillCycleCmp extends BaseCmp
{

    /**
     * @Description:创建账户账期
     * @author caohm5
     * @Date 2012-5-17
     */
    public void createAcctBillCycle(IAcctBillCycle iAcctBillCycle)
    {
        createOrModifyBillCycle(iAcctBillCycle, EnumCodeExDefine.ACCOUNT_BILLCYCLE_ADD);
    }
    
    
    public void deleteBillCycle(IAcctBillCycle iAcctBillCycle){
    	Date expireDate = getNextMonthBegin(iAcctBillCycle.getExpireDate());
    	this.deleteMode1(CaBillingPlan.class,expireDate,new DBCondition(CaBillingPlan.Field.acctId,iAcctBillCycle.getAcctId()));
    }
  
    /**
     * @Description:修改账户账期
     * @author caohm5
     * @Date 2012-5-17
     */
    public void updateAcctBillCycle(IAcctBillCycle iAcctBillCycle)
    {
        createOrModifyBillCycle(iAcctBillCycle, EnumCodeExDefine.ACCOUNT_BILLCYCLE_MODIFY);
    }

    /**
     * @Description:ims自己实例化出账计划表
     * @author caohm5
     * @param iAcctBillCycle 帐期资料
     * @param operType 操作类型，新增(0)、修改(1)
     * @Date 2012-5-17
     */
    private void createOrModifyBillCycle(IAcctBillCycle iAcctBillCycle, short operType)
    {
        
        SBillCycle billCycle = new SBillCycle();
        billCycle.setCycle_type(iAcctBillCycle.getCycleType());
        billCycle.setCycle_unit(iAcctBillCycle.getCycleUnit());
        billCycle.setFirst_bill_day(iAcctBillCycle.getFirstBillDay());
        
        BillCycleHandler handler = ChangeBillCycleContext.instanceHandler(iAcctBillCycle.getAcctId(), null, billCycle, CommonUtil.IntegerToShort(iAcctBillCycle.getFirstBillType()), CommonUtil.IntegerToShort(iAcctBillCycle.getEffectiveType()), context,iAcctBillCycle.getValidDate(),iAcctBillCycle.getExpireDate());
        
        // 长短账期标志和延时变更标志在修改的时候才要填,创建的时候默认都填0
        if (operType == EnumCodeExDefine.ACCOUNT_BILLCYCLE_MODIFY)
        {
            handler.modifyBillCycle();
        }else{
            handler.createBillCycle();
        }
        
        /*
        
        
        // 查询账户实体
        CaAccount caAccount = context.get3hTree().loadAcct3hBean(iAcctBillCycle.getAcctId()).getAccount();
        CaBillCycleIn caBillCycleIn = new CaBillCycleIn();
        caBillCycleIn.setOpType(operType);
        caBillCycleIn.setAcctId(caAccount.getAcctId());
        caBillCycleIn.setResourceId(0L);
        caBillCycleIn.setCycleType(CommonUtil.IntegerToShort(iAcctBillCycle.getCycleType()));
        caBillCycleIn.setCycleUnit(iAcctBillCycle.getCycleUnit());
        caBillCycleIn.setStartBillDay(iAcctBillCycle.getFirstBillDay());
        caBillCycleIn.setCycleSpecId(0L);
        caBillCycleIn.setOutSoDate(context.getCommitDate());
        caBillCycleIn.setOutSoNbr(context.getDoneCode());
        // 长短账期标志和延时变更标志在修改的时候才要填,创建的时候默认都填0
        if (operType == EnumCodeExDefine.ACCOUNT_BILLCYCLE_MODIFY)
        {
            Short firtBillType = CommonUtil.IntegerToShort(iAcctBillCycle.getFirstBillType());
            if (firtBillType != null
                    && (firtBillType.shortValue() == EnumCodeExDefine.SHORT_BILL_TYPE || firtBillType.shortValue() == EnumCodeExDefine.LONG_BILL_TYPE))
            {

                caBillCycleIn.setFirstBillType(firtBillType);
            }
            Short effectiveCycle = CommonUtil.IntegerToShort(iAcctBillCycle.getEffectiveType());
            if (effectiveCycle != null
                    && (effectiveCycle.shortValue() == EnumCodeExDefine.EFFECTIVE_BY_CURRENT_CYCLE || effectiveCycle.shortValue() == EnumCodeExDefine.EFFECTIVE_BY_NEXT_CYCLE))
                caBillCycleIn.setIsDelay(effectiveCycle);
        }
        caBillCycleIn.setOpId(EnumCodeExDefine.OP_ID_DEFUALT);
        caBillCycleIn.setCustId(caAccount.getCustId());
        caBillCycleIn.setCountyCode(caAccount.getCountyCode());
        caBillCycleIn.setRegionCode(caAccount.getRegionCode());
        caBillCycleIn.setMeasureId(caAccount.getMeasureId());
        // 操作员so_mode
        // caBillCycleIn.setChannelId(iAcctBillCycle.getChannelId());
        // 创建、修改的时候都可以不填，有默认值
        // caBillCycleIn.setIsBillRun(iAcctBillCycle.getIsBillRun());
        // caBillCycleIn.setCustomerType(cmCustomer.getCustomerClass());
        // 创建、修改的时候都可以不填，有默认值
        // caBillCycleIn.setIsRepeat(iAcctBillCycle.getIsRepeat());
        // 设置为不发送短信
        caBillCycleIn.setIsNotify(EnumCodeExDefine.BILL_CYCLE_NOT_NOTIFY);
        Holder<CaBillCycleOut> holder = new Holder<CaBillCycleOut>();

        context.getComponent(BillingPlanComponent.class).opBillCycle(caBillCycleIn, holder);
*/
        // 返回0表示成功，其他表示失败，抛出异常
        /*
         * if (returnValue != null && 0 != returnValue.intValue()) { throw
         * IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_SYN_BILL_CYCLE_ERROR, new Object[] { caAccount.getAcctId() }); }
         */
    }

    /**
     * 根据账户id查询账户账期 ljc 2011-9-26
     */
    public List<BillCycleComplex> queryBillCycle(Long acctId)
    {
        if (CommonUtil.isNotEmpty((List<BillCycleComplex>) context.getExtendParam("acct_bill_cycle" + acctId)))
        {
            return (List<BillCycleComplex>) context.getExtendParam("acct_bill_cycle" + acctId);
        }
        Holder<CaCycleSpecOut> out = new Holder<CaCycleSpecOut>();
        CaCycleSpecIn in = new CaCycleSpecIn();
        in.setAcctId(acctId);
        // 其他三个参数设置为0
        in.setCycleType((short) 0);
        in.setCycleUnit(0);
        in.setStartBillDay(0);
        imsLogger.debug("*****begin to do query account bill cycle*****************");
        imsLogger.debug("***********CaCycleSpecIn:acctId=", acctId, ";cycleType=", in.getCycleType(), ";cycleunit=",
                in.getCycleUnit(), ";first_bill_date=", in.getStartBillDay());
        context.getComponent(BillCycleSpecBusiness.class).qryBillCycleSpec(in, out);;
        if (out != null && out.get() != null && !CommonUtil.isEmpty(out.get().getCaCycleSpecList()))
        {
            List<CaCycleSpec> cycles = out.get().getCaCycleSpecList();
            List<BillCycleComplex> list = new ArrayList<BillCycleComplex>();
            for (CaCycleSpec cycle : cycles)
            {
                BillCycleComplex complex = new BillCycleComplex();
                complex.setCycleType(cycle.getCycleType());
                complex.setCycleUnit(cycle.getCycleUnit());
                complex.setExpireDate(cycle.getExpriedate());
                complex.setFirstBillDate(cycle.getFirstBillDate());
                complex.setValidDate(cycle.getValidate());
                list.add(complex);
            }
            context.addExtendParam("acct_bill_cycle" + acctId, list);
            return list;
        }
        return null;
    }
}
