package com.ailk.imssh.acct.cmp;

import java.util.Date;
import com.ailk.ims.component.billcycle.QueryBillCycleDate;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.bill.entity.QryBillingPlanIn;
import com.ailk.openbilling.persistence.bill.entity.QryBillingPlanOut;

/**
 * 账户信息查询类
 * @Description
 * @author wukl
 * @Date 2012-5-17
 */
public class AcctQuery extends BaseCmp
{
    /**
     * 获取帐户的下个账期开始日<br>
     * wukl 2012-5-22
     * @param acctId
     * @return
     */
    public Date queryAcctNextCycleStart(Long acctId)
    {
        return queryAcctCycleStart(acctId, context.getCommitDate(),EnumCodeDefine.ACCT_NEXT_BILL_CYCLE);
    }
    
    
    
    /**
     * 获取账户的账期开始日<br>
     * wukl 2012-5-22
     * @param acctId
     * @param soDate
     * @param type
     * @return
     */
    public Date queryAcctCycleStart(Long acctId, Date soDate,int type)
    {
        
        Acct3hBean bean = context.get3hTree().loadAcct3hBean(acctId);
        CaAccount account = bean.getAccount();
        if (account != null && account.getBillSts() != null && account.getBillSts() == EnumCodeDefine.BILL_STS_ACTIVE)
        {
            QryBillingPlanIn plan = new QryBillingPlanIn();
            plan.setAcctId(acctId);
            plan.setBillingPlanId(null);
            plan.setNum(type);
            plan.setOutDate(soDate);
            plan.setIsSupress(EnumCodeDefine.SUSPEND_MONTH_START);
            QryBillingPlanOut qryBillingPlanOut = context.getComponent(QueryBillCycleDate.class).queryBillCycleDate(plan);
            Date startDate = null;
            if(qryBillingPlanOut!=null){
                startDate=qryBillingPlanOut.getStartDate();
            }
            return startDate;
        }
        return null;
    }
    /**
     * 获取账户的账期开始时间和结束时间<br>
     * 2012-10-04 gaoqc5 
     * @param acctId
     * @param soDate
     * @param type
     * @return
     */
    public Date[] queryAcctCycleStartAndEnd(Long acctId, Date soDate,int type)
    {
        Date[] dates=new Date[2];
        // 调用帐管接口，查询该帐户账期编号
        Acct3hBean bean = context.get3hTree().loadAcct3hBean(acctId);
        CaAccount account = bean.getAccount();
        if (account != null && account.getBillSts() != null && account.getBillSts() == EnumCodeDefine.BILL_STS_ACTIVE)
        {
            QryBillingPlanIn plan = new QryBillingPlanIn();
            plan.setAcctId(acctId);
            plan.setBillingPlanId(null);
            plan.setNum(type);
            plan.setOutDate(soDate);
            plan.setIsSupress(EnumCodeDefine.SUSPEND_MONTH_START);
            QryBillingPlanOut qryBillingPlanOut = this.context.getComponent(QueryBillCycleDate.class).queryBillCycleDate(plan);
            if(qryBillingPlanOut!=null){
                dates[0]=qryBillingPlanOut.getStartDate();
                dates[1]=qryBillingPlanOut.getEndDate();
            }
        }
        return dates;
    }
}
