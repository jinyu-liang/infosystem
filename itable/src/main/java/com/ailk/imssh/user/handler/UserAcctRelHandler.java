package com.ailk.imssh.user.handler;

import java.util.List;

import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.group.handler.GroupHandler;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.imssh.user.cmp.UserAcctRelCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.itable.entity.IGroup;
import com.ailk.openbilling.persistence.itable.entity.IUserAcctRel;

/**
 * 用户账户关系（过户业务等） <br>
 * 上海过户业务，若两个账户的账期不一致，要求先进行账期变更，所以过户时不需要考虑产品账期的变更<br>
 * 用户有效期的表是否需要更新，看用户有效期保存时，是否存acct_id//TODO 过户时需要考虑分表的数据挪动
 * 
 * @author wukl
 * @Date 2012-5-11
 */
public class UserAcctRelHandler extends BaseHandler
{

    @Override
    public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void handle(List<? extends DataObject>... dataArr)
    {
        List<IUserAcctRel> dataList = (List<IUserAcctRel>) dataArr[0];

        UserAcctRelCmp userAcctRelCmp = this.getContext().getCmp(UserAcctRelCmp.class);

        userAcctRelCmp.beforeHandler(dataList);
        RouterCmp routeCmp = context.getCmp(RouterCmp.class);
        // 群组支付账号设置处理
       // dealGroupBillAcctId(dataList);

        for (int j = 0; j < dataList.size(); j++)
        {
            IUserAcctRel iUserAcctRel = dataList.get(j);
    		ITableUtil.setValue2ContextHolder(null, iUserAcctRel.getAcctId(), null);
            int operType = iUserAcctRel.getOperType();
            switch (operType)
            {
            case EnumCodeExDefine.OPER_TYPE_INSERT:
                userAcctRelCmp.createUserAcctRel(iUserAcctRel);
                break;
            case EnumCodeExDefine.OPER_TYPE_UPDATE:
                userAcctRelCmp.modifyUserAcctRel(iUserAcctRel);
                break;
            case EnumCodeExDefine.OPER_TYPE_DELETE:
                userAcctRelCmp.deleteUserAcctRel(iUserAcctRel);
                break;

            default:
                break;
            }
        }
    }

    /**
     * 群组支付账户设值问题.
     * 
     * @param dataList
    
    private void dealGroupBillAcctId(List<IUserAcctRel> dataList)
    {
        ProdCmp prodCmp = this.getContext().getCmp(ProdCmp.class);
        for (IUserAcctRel userAcctRel : dataList)
        {
            int operType = userAcctRel.getOperType();
            if (operType == EnumCodeExDefine.OPER_TYPE_INSERT || operType == EnumCodeExDefine.OPER_TYPE_UPDATE)
            {
                //判断是否有过I_Group的操作
                List<? extends DataObject>[] dataAttr = context.getIDataObjectList(GroupHandler.class);

                if (CommonUtil.isEmpty(dataAttr) || CommonUtil.isEmpty(dataAttr[0]))
                {
//                    Long groupId = ITableUtil.convertGroupId(userAcctRel.getUserId());
                	Long groupId=userAcctRel.getUserId();

                    List<CaAccountGroup> caAccountGroupList = prodCmp.query(CaAccountGroup.class, new DBCondition(
                            CaAccountGroup.Field.acctId, groupId));
                    dealWithAccountGroup(caAccountGroupList, userAcctRel, groupId);
                }
                else
                {
                    List<IGroup> iGroupList = (List<IGroup>) dataAttr[0];
                    dealWithIGroup(iGroupList, userAcctRel);
                }
            }
        }
    }
	 */
    /**
     * 如果Group来源于UserAcctRel时 更新AD.CA_ACCOUNT_GROUP表的PAY_ACCT_ID 和AD.CA_ACCOUNT_REL.REL_ACCT_ID字段的值
     * 
     * @param accountGroupList
     * @param userAcctRel
     * @author songcc
     * @date 2014-2-21
     * @param groupId
    
    private void dealWithAccountGroup(List<CaAccountGroup> accountGroupList, IUserAcctRel userAcctRel, Long groupId)
    {
        if (CommonUtil.isEmpty(accountGroupList))
        {
            return;
        }
        ProdCmp prodCmp = this.getContext().getCmp(ProdCmp.class);
        /*
        CaAccountRel caAccountRel = new CaAccountRel();
        caAccountRel.setRelAcctId(userAcctRel.getAcctId());
        prodCmp.updateByCondition(caAccountRel, new DBCondition(CaAccountRel.Field.acctId, userAcctRel.getAcctId()));
      
        CaAccountGroup accountGroup = new CaAccountGroup();
        accountGroup.setPayAcctId(userAcctRel.getAcctId());
        prodCmp.updateByCondition(accountGroup, new DBCondition(CaAccountGroup.Field.acctId, groupId));
    }
	*/
    /**
     * 同一笔工单中有对应的I_Group操作，直接更新I_GROUP.BILLABLE_ACCT_ID的值
     * 
     * @param iGroupList
     * @param iUserAcctRel
    
    private void dealWithIGroup(List<IGroup> iGroupList, IUserAcctRel iUserAcctRel)
    {
        for (IGroup iGroup : iGroupList)
        {
            if (iGroup.getGroupId().longValue() == iUserAcctRel.getUserId().longValue())
            {
                iGroup.setBillableAcctId(iUserAcctRel.getAcctId());
            }
        }
    }
	 */
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
		//发布路由不处理
		
	}
}
