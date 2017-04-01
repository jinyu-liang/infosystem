package com.ailk.imssh.acct.handler;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.acct.cmp.AcctBillCycleCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBillingPlan;
import com.ailk.openbilling.persistence.acct.entity.CaCycleRun;
import com.ailk.openbilling.persistence.itable.entity.IAcctBillCycle;

import jef.database.DataObject;

/**
 * @Description:账户账期
 * @author caohm5
 * @Date 2012-5-11
 */
public class AcctBillCycleHandler extends BaseHandler
{
    
    
    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {

        List<? extends DataObject> dataList = dataArr[0];

        AcctBillCycleCmp billCycleCmp = this.getContext().getCmp(AcctBillCycleCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {
            IAcctBillCycle iAcctBillCycle = (IAcctBillCycle) dataList.get(j);
            ITableUtil.setValue2ContextHolder(null, iAcctBillCycle.getAcctId(), null);
            // 只有创建帐期和修改帐期
            int operType = iAcctBillCycle.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                billCycleCmp.createAcctBillCycle(iAcctBillCycle);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                billCycleCmp.updateAcctBillCycle(iAcctBillCycle);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                billCycleCmp.deleteBillCycle(iAcctBillCycle);
                break;
            default:
                break;
            }
        }

    }

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
       //按照ACCT_ID作为key删除账期相关表	
		List<IAcctBillCycle> iAcctBillCycleList = (List<IAcctBillCycle>) paramArr[0];
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		if(CommonUtil.isEmpty(iAcctBillCycleList)){
			return;
		}
		AcctBillCycleCmp billCycleCmp = this.getContext().getCmp(AcctBillCycleCmp.class);
		for (IAcctBillCycle iAcctBillCycle : iAcctBillCycleList) {
			 if(EnumCodeExDefine.OPER_TYPE_DIFF != iAcctBillCycle.getOperType()){
				continue;
			 }
			 baseCmp.checkAcctRouter(iAcctBillCycle.getAcctId());
			 ITableUtil.setValue2ContextHolder(null,iAcctBillCycle.getAcctId(),null);
				//按objectId删除
				billCycleCmp.deleteByConditionForDiff(CaCycleRun.class, new DBCondition(CaCycleRun.Field.acctId, iAcctBillCycle.getAcctId()));
				billCycleCmp.deleteByConditionForDiff(CaBillingPlan.class, new DBCondition(CaBillingPlan.Field.acctId, iAcctBillCycle.getAcctId()));
			 ITableUtil.setValue2ContextHolder(null,iAcctBillCycle.getAcctId(),null);
			 billCycleCmp.createAcctBillCycle(iAcctBillCycle);
			}
			 
		}
	 }


