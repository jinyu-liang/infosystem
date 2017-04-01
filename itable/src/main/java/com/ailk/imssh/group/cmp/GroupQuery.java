package com.ailk.imssh.group.cmp;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;

/**
 * @Description:群组查询类
 * @author wangjt
 * @Date 2012-5-21
 */
public class GroupQuery extends BaseCmp
{
    /**
     * 获取群付费账户 luojb 2011-9-28
     
    public Long queryBillAcctId(Long groupId) throws IMSException
    {
        CaAccountGroup group = querySingle(CaAccountGroup.class, new DBCondition(CaAccountGroup.Field.acctId, groupId));
        if (group == null)
        {
            return null;
        }
        return group.getPayAcctId();
    }
    */
}
