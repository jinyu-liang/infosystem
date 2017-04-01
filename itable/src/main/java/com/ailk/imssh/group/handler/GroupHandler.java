package com.ailk.imssh.group.handler;

import java.util.Date;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.group.cmp.GroupCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.itable.entity.IGroup;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

/**
 * @Description:处理群组信息
 * @author wangyh3
 * @Date 2012-5-14
 */
public class GroupHandler extends BaseHandler
{

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
         List<IGroup> dataList = (List<IGroup>) dataArr[0];
//        List<IGroup> dataList = init(dataArr[0]);
        GroupCmp groupCmp = this.getContext().getCmp(GroupCmp.class);

        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        for (IGroup group : dataList)
        {
            if (group.getValidDate().before(validDate))
            {
                group.setValidDate(validDate);
            }
        }
        Long acctId = context.getAcctId();
        ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
        groupCmp.deleteByCondition_noInsert(CaAccountGroup.class, validDate,
        		new DBCondition(CaAccountGroup.Field.acctId, acctId), new DBCondition(CaAccountGroup.Field.expireDate, validDate,
        				Operator.GREAT));
        /*
        // 删除虚账户
        groupCmp.deleteByCondition_noInsert(CaAccount.class, validDate, new DBCondition(CaAccount.Field.acctId, acctId),
                new DBCondition(CaAccount.Field.expireDate, validDate, Operator.GREAT));
        // 删除群组信息
        // 删除虚账户与支付账户的关联关系
        groupCmp.deleteByCondition_noInsert(CaAccountRel.class, validDate, new DBCondition(CaAccountRel.Field.acctId, acctId),
                new DBCondition(CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_CA_GCA_RELATION), new DBCondition(
                        CaAccountRel.Field.expireDate, validDate, Operator.GREAT));
		*/
    }

    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        List<? extends DataObject> dataList = dataArr[0];

        GroupCmp groupCmp = this.getContext().getCmp(GroupCmp.class);
        RouterCmp routeCmp = context.getCmp(RouterCmp.class);
        //groupCmp.beforeHandle(dataList);

        for (int j = 0; j < dataList.size(); j++)
        {
            IGroup iGroup = (IGroup) dataList.get(j);
            Long acctId=routeCmp.queryAcctIdByUserId(iGroup.getGroupId());
            ITableUtil.setValue2ContextHolder(acctId, null,null);

            int operType = iGroup.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT: // 新增
                groupCmp.createGroup(iGroup);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE: // 修改
                groupCmp.modifyGroup(iGroup);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE: // 删除
                groupCmp.deleteGroup(iGroup);
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
    	/*
        List<? extends DataObject> dataList = dataArr[0];
        for (int i = 0; i < dataList.size(); i++)
        {
            IGroup iGroup = (IGroup) dataList.get(i);
            iGroup.setGroupId(ITableUtil.convertGroupId(iGroup.getGroupId()));

        }
        */
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		List<IGroup> dataList = (List<IGroup>)paramArr[0];
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		if(CommonUtil.isEmpty(dataList)){
	        return;			
		}
		 GroupCmp groupCmp = this.getContext().getCmp(GroupCmp.class);
		for (IGroup iGroup : dataList) {
			if(EnumCodeExDefine.OPER_TYPE_DIFF != iGroup.getOperType()){
				continue;
			}
			  // 设置分表参数
			    baseCmp.checkUserRouter(iGroup.getGroupId());
				ITableUtil.setValue2ContextHolder(null, null, iGroup.getGroupId());
				groupCmp.deleteByConditionForDiff(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId, iGroup.getGroupId()));							;
				ITableUtil.setValue2ContextHolder(null, null, iGroup.getGroupId());
				groupCmp.createGroup(iGroup);
		}
	}
}
