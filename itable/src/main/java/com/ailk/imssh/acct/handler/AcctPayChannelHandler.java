package com.ailk.imssh.acct.handler;

import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.acct.cmp.AcctPayChannelCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaBankFund;
import com.ailk.openbilling.persistence.acct.entity.CaPayChannel;
import com.ailk.openbilling.persistence.itable.entity.IAcctPayChannel;

/**
 * @Description:账户的支付渠道信息
 * @author caohm5
 * @date 2012-05-17
 */
public class AcctPayChannelHandler extends BaseHandler
{
	
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<IAcctPayChannel> dataList = (List<IAcctPayChannel>)dataArr[0];
        if(CommonUtil.isEmpty(dataList)){
            return;
        }
        AcctPayChannelCmp acctPayChannelCmp = this.getContext().getCmp(AcctPayChannelCmp.class);
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        for(IAcctPayChannel acct:dataList){
            if(acct.getValidDate().before(validDate)){
                acct.setValidDate(validDate);
            }
        }
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), context.getUserId());
        acctPayChannelCmp.deleteByCondition_noInsert(CaPayChannel.class, validDate, new DBCondition(CaPayChannel.Field.acctId, context.getAcctId()),
                new DBCondition(CaPayChannel.Field.expireDate, validDate,Operator.GREAT));
        acctPayChannelCmp.deleteByCondition_noInsert(CaBankFund.class, validDate, new DBCondition(CaBankFund.Field.acctId, context.getAcctId()),
                new DBCondition(CaBankFund.Field.expireDate, validDate,Operator.GREAT));
        
    }
    
    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        List<? extends DataObject> dataList = dataArr[0];

        AcctPayChannelCmp acctPayChannelCmp = this.getContext().getCmp(AcctPayChannelCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {
            IAcctPayChannel iAcctPayChannel = (IAcctPayChannel) dataList.get(j);
            ITableUtil.setValue2ContextHolder(null, iAcctPayChannel.getAcctId(), null);
            int operType = iAcctPayChannel.getOperType();
            // 付费渠道只有新增和修改
            if (operType == EnumCodeExDefine.OPER_TYPE_INSERT)
            {
                acctPayChannelCmp.createAcctPayChannel(iAcctPayChannel);
            }
            else if (operType == EnumCodeExDefine.OPER_TYPE_UPDATE)
            {
                acctPayChannelCmp.updatePayChannel(iAcctPayChannel);
            }
        }
    }

    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}
}
