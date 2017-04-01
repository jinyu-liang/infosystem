package com.ailk.imssh.user.handler;

import java.util.Date;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserStsCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.itable.entity.IUserStatus;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

/**
 * 用户状态(生命周期)<br>
 * 当用户主动停时，需要订购停机保号的产品;从用户主动停变为正常，需退订停机保号产品
 * @author wukl
 * @Date 2012-5-11
 */
public class UserStsHandler extends BaseHandler
{
    
    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        List<IUserStatus> userStsList = (List<IUserStatus>)dataArr[0];
        Long userId=context.getUserId();
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        if(CommonUtil.isNotEmpty(userStsList)){
            for(IUserStatus user:userStsList){
                if(user.getValidDate().before(validDate)){
                    user.setValidDate(validDate);
                }
            }
        }
        UserStsCmp userStsCmp = this.getContext().getCmp(UserStsCmp.class);
        ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
        userStsCmp.deleteByCondition_noInsert(CmResLifecycle.class, validDate, new DBCondition(CmResLifecycle.Field.resourceId,
                userId), new DBCondition(CmResLifecycle.Field.expireDate,
                        validDate, Operator.GREAT));
    }
    
    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        List<? extends DataObject> dataList = dataArr[0];

        UserStsCmp userStsCmp = this.getContext().getCmp(UserStsCmp.class);
        RouterCmp cmp=this.context.getCmp(RouterCmp.class);
        for (int j = 0; j < dataList.size(); j++)
        {
            IUserStatus iUserStatus = (IUserStatus) dataList.get(j);
            Long acctId=cmp.queryAcctIdByUserId(iUserStatus.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId,null);
            int operType = iUserStatus.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                userStsCmp.createUserStatus(iUserStatus);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                userStsCmp.modifyUserStatus(iUserStatus);
                
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                userStsCmp.deleteUserStatus(iUserStatus);
                break;

            default:
                break;
            }
        }
    }

    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		 List<IUserStatus> dataList = (List<IUserStatus>)paramArr[0];
	     UserStsCmp userStsCmp = this.getContext().getCmp(UserStsCmp.class);
	     if (CommonUtil.isEmpty(dataList))
	        {
	            return;
	        }
			RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
	        for (IUserStatus iUserStatus : dataList) {
		    	 if(EnumCodeExDefine.OPER_TYPE_DIFF != iUserStatus.getOperType()){
		    		 continue;
		    	 }
	    	 baseCmp.checkUserRouter(iUserStatus.getUserId());
	    	 ITableUtil.setValue2ContextHolder(null, null, iUserStatus.getUserId());
	    		 userStsCmp.deleteByConditionForDiff(CmResLifecycle.class, 
		    			 new DBCondition(CmResLifecycle.Field.resourceId, iUserStatus.getUserId()));
	    	 ITableUtil.setValue2ContextHolder(null, null, iUserStatus.getUserId());
	    	 userStsCmp.createUserStatus(iUserStatus);
		 }
	}
}
