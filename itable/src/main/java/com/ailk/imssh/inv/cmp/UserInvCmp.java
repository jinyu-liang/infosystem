package com.ailk.imssh.inv.cmp;

import java.util.List;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import com.ailk.ims.common.DBCondition;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.cust.entity.CaResourceInv;
import com.ailk.openbilling.persistence.itable.entity.IUserInv;
/**
 * #62899 用户级定制账单
 * @Date 2012-10-27
 * @author gaoqc5
 *
 */
public class UserInvCmp extends BaseCmp
{
    public void createUserInv(IUserInv userInv){
              super.insert(convert(userInv,false));
    }
    public void updateUserInv(IUserInv userInv){
        CaResourceInv resourceInv=convert(userInv,true);
        this.updateByCondition(resourceInv,new DBCondition(CaResourceInv.Field.resourceId,userInv.getUserId()));
//        DBUtil.updateByCondition(resourceInv,new DBCondition(CaResourceInv.Field.resourceId,userInv.getUserId()));
    }
    public void deleteUserInv(IUserInv userInv){
        deleteByCondition(CaResourceInv.class,new DBCondition(CaResourceInv.Field.resourceId,userInv.getUserId()));
    }
    public CaResourceInv convert(IUserInv userInv,boolean isUpdateOper){
        CaResourceInv resourceInv=new CaResourceInv();
        resourceInv.setDeliverAddr(userInv.getDeliverAddr());
        if(!isUpdateOper){//如果 是更新，validate不应该更新
             resourceInv.setValidDate(userInv.getValidDate());
        }
        resourceInv.setExpireDate(userInv.getExpireDate());
        resourceInv.setResourceId(userInv.getUserId());
        resourceInv.setSoNbr(context.getSoNbr());
        resourceInv.setSummaryDeliver(userInv.getSummaryDeliver());
        return resourceInv;
        
        
        
    }
    public void beforeHandle(List<? extends DataObject> dataList)
    {
        ITableUtil.cleanRequestParamter();
        IUserInv iUserInv = (IUserInv) dataList.get(0);
        delete_noValid(CaResourceInv.class, new DBCondition(CaResourceInv.Field.resourceId, iUserInv.getUserId()),
                new DBCondition(CaResourceInv.Field.validDate, iUserInv.getCommitDate(), Operator.GREAT), new DBCondition(
                        CaResourceInv.Field.expireDate, iUserInv.getCommitDate(), Operator.GREAT));

    }
   

}
