package com.ailk.imssh.user.cmp;

import java.util.ArrayList;
import java.util.List;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;

/**
 * 用户信息查询类
 * @Description
 * @author wukl
 * @Date 2012-5-17
 */
public class UserQuery extends BaseCmp
{
	  /**
     * @Description:查询支付帐户的关联的用户
     * @author caohm5 
     * @Date 2012-05-14 
     */
    public List<CmResource> queryUserByBillAcctId(Long acctId)
    {
        List<CaAccountRes> resList = query(CaAccountRes.class, new DBCondition(CaAccountRes.Field.acctId, acctId),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
        if (CommonUtil.isEmpty(resList))
            return null;
        List<CmResource> userList = new ArrayList<CmResource>();
        for (CaAccountRes dmRes : resList)
        {
            CmResource user = queryUserByUserID(dmRes.getResourceId());
            if (user == null)
                continue;
            userList.add(user);
        }
        return userList;
    }
    /**
     * @Description:根据用户ID获取用户信息
     * @author caohm5 
     * @Date 2012-05-14 
     */
    public CmResource queryUserByUserID(Long userID)
    {
        return querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, userID));
    }
    
    /**
     * 根据用户ID查询用户资源标识<br>
     * wukl 2012-5-19
     * @param userId
     * @param iden 如：手机号码的号码串、IMSI号码串
     * @param idenAttr 标识号码类型 0：主号码、2：副号码、3：上网伴侣副号码、6：副号随意换副号码
     * @return CD.CM_RES_IDENTITY表记录
     * lijc3 修改了类型，传入是否查询主号码
     */
    public CmResIdentity queryResIdentity(Long userId, String iden, Integer idenAttr)
    {
        if (userId == null && CommonUtil.isEmpty(iden))
        {
            return null;
        }

        List<DBCondition> conList = new ArrayList<DBCondition>();
        if (userId != null)
        {
            conList.add(new DBCondition(CmResIdentity.Field.resourceId, userId));
        }

        if (CommonUtil.isValid(iden))
        {
            conList.add(new DBCondition(CmResIdentity.Field.identity, iden));
        }

        if (idenAttr != null)
        {
            conList.add(new DBCondition(CmResIdentity.Field.identityAttr, idenAttr));
        }

        return querySingle(CmResIdentity.class, conList.toArray(new DBCondition[conList.size()]));
    } 
    
    
    /**根据用户ID查询所关联的账户ID<br>
     * zenglu  2012-10-4
     * @param resourceId
     * @return
     */
    public Long queryAcctIdByUserId(Long userId)
    {
        return context.getCmp(RouterCmp.class).getAcctIdByUserIdRout(userId);
    }
    
//    /**
//     * 根据用户ID查询所关联的账户ID<br>
//     * wukl 2012-5-19
//     * @param resourceId
//     * @return
//     */
//    public CaAccountRes queryAcctResByUserId(Long userId)
//    {
//        return querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, userId), new DBCondition(
//                CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
//    }
    
    /**
     * 根据用户ID查询用户生命周期状态<br>
     * wukl 2012-5-21
     * @param userId
     * @return
     */
    public CmResLifecycle queryLifeCycleByUserId(Long userId)
    {
        return querySingle(CmResLifecycle.class,new DBCondition(CmResLifecycle.Field.resourceId,userId));
    }
    
}
