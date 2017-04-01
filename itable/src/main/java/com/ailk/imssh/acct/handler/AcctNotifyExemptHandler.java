package com.ailk.imssh.acct.handler;

import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.acct.cmp.AcctNotifyExemptCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.itable.entity.INotifyExempt;

/**
 * @Description:免催免停信息
 * @author zenglu
 * @Date 2012-09-20
 */
public class AcctNotifyExemptHandler extends BaseHandler
{

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<INotifyExempt> dataList = (List<INotifyExempt>)dataArr[0];
        if(CommonUtil.isEmpty(dataList)){
            return;
        }
        AcctNotifyExemptCmp cmp = this.getContext().getCmp(AcctNotifyExemptCmp.class);
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        for(INotifyExempt empt:dataList){
            if(empt.getValidDate().before(validDate)){
                empt.setValidDate(validDate);
            }
        }
        Long userId = context.getUserId();
        Long acctId = context.getAcctId();
        Long objectId = null;
        Integer objectType = null;
        if (userId != null)
        {
            objectId = userId;
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        }
        else
        {
            objectId=acctId;
            objectType=EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        }
        ITableUtil.setValue2ContextHolder(null, context.getAcctId(), context.getUserId());
        cmp.deleteByCondition_noInsert(CaNotifyExempt.class, validDate, new DBCondition(CaNotifyExempt.Field.objectId, objectId),
                new DBCondition(CaNotifyExempt.Field.objectType, objectType),
                new DBCondition(CaNotifyExempt.Field.expireDate, validDate,Operator.GREAT));
    }
    
    @Override
    public void handle(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<? extends DataObject> dataList = dataArr[0];

        AcctNotifyExemptCmp cmp = this.getContext().getCmp(AcctNotifyExemptCmp.class);
        RouterCmp routeCmp = context.getCmp(RouterCmp.class);
        cmp.beforeHandler(dataList);
        
        for (int i = 0; i < dataList.size(); i++)
        {
            INotifyExempt iNotifyExempt = (INotifyExempt) dataList.get(i);
            if(iNotifyExempt.getUserId() != null && iNotifyExempt.getUserId().longValue() != 0){
            	long acctId = routeCmp.queryAcctIdByUserId(iNotifyExempt.getUserId());
                ITableUtil.setValue2ContextHolder(null, acctId, null);
            }else{
                ITableUtil.setValue2ContextHolder(null,  iNotifyExempt.getAcctId(), null);
            }
            
            int busiType = iNotifyExempt.getOperType();
            switch (busiType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                cmp.insertNotifyExempt(iNotifyExempt);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                cmp.deleteNotifyExempt(iNotifyExempt);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                cmp.updateNotifyExempt(iNotifyExempt);
                break;
            default:
                break;
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
