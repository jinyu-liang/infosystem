package com.ailk.imssh.user.handler;

import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserCmp;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.itable.entity.IUser;

/**
 * @Description:用户基本信息
 * @author wukl
 * @Date 2012-5-11
 * @Date 2012-10-27 gaoqc5 #62685 上海项目该表中，只会存在IDENTITY_TYPE=0的数据记录（IDENTITY存放计费号码，REL_IDENTITY存放IMSI；
 *       如果当前数据没有IMSI，则REL_IDENTITY填计费号码），不会存在IDENTITY_TYPE为其他值的数据记录
 */
public class UserHandler extends BaseHandler
{

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {

        UserCmp userCmp = this.getContext().getCmp(UserCmp.class);

        List<IUser> userList = (List<IUser>) dataArr[0];
        Long userId = context.getUserId();
        Date validDate = DateUtils.monthBegin(context.getCommitDate());
        if (CommonUtil.isNotEmpty(userList))
        {
            for (IUser user : userList)
            {
                if (user.getValidDate().before(validDate))
                {
                    user.setValidDate(validDate);
                }
            }
        }
        ITableUtil.setValue2ContextHolder(context.getCustId(), context.getAcctId(), context.getUserId());
        userCmp.deleteByCondition_noInsert(CmResource.class, validDate, new DBCondition(CmResource.Field.resourceId, userId),
                new DBCondition(CmResource.Field.expireDate, validDate, Operator.GREAT));
       /** userCmp.deleteByCondition_noInsert(CmResIdentity.class, validDate,
                new DBCondition(CmResIdentity.Field.resourceId, userId), new DBCondition(CmResIdentity.Field.expireDate,
                        validDate, Operator.GREAT));
        */
    }

    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        List<? extends DataObject> dataList = dataArr[0];
        RouterCmp routeCmp = context.getCmp(RouterCmp.class);
        UserCmp userCmp = this.getContext().getCmp(UserCmp.class);

//        List<CmResIdentity> idenList = null;
//        // 如果数据来自账管，只为更新星极值，那么无需删除未来生效的数据
//        // 来自CRM数据，针对下周期生效的数据未生效前又进行变更的场景；此时先删除未来生效的数据
//        if (!userCmp.isOnlyUpdateUserSegment())
//        {
//            userCmp.beforeHandle(dataList);
//        }
        for (int j = 0; j < dataList.size(); j++)
        {
            IUser iUser = (IUser) dataList.get(j);
            Long acctId=routeCmp.queryAcctIdByUserId(iUser.getUserId());
            ITableUtil.setValue2ContextHolder(null, acctId,null);
            int operType = iUser.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                userCmp.createUser(iUser);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                // 只为更新星极值
                if (userCmp.isOnlyUpdateUserSegment())
                {
                    userCmp.updateCmResourceResSegment(iUser);
                }
                else
                {
                    userCmp.modifyUser(iUser);
                }
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                userCmp.deleteUser(iUser);
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
		// TODO Auto-generated method stub
		
	}

}
