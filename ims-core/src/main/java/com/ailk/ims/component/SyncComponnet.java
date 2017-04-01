/**
 * 
 */
package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.cust.entity.ImsSync;

/**@Description: Sync 给CRM或内部TS 组件
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2012-6-8 
 */
public class SyncComponnet extends BaseComponent
{
    public SyncComponnet()
    {
    }
    /**
     * @Description: 删除ims_sync中已经存在的初始数据
     * @param userId
     * @param acctId	 
     * @author: tangjl5
     * @Date: 2012-6-8
     */
    public void deleteDuplicateDate (Long userId, Long acctId)
    {
        List<DBCondition> conList = new ArrayList<DBCondition>();
        if (CommonUtil.isValid(acctId))
        {
            conList.add(new DBCondition(ImsSync.Field.acctId, acctId));
        }
        
        if (CommonUtil.isValid(userId))
        {
            conList.add(new DBCondition(ImsSync.Field.userId, userId));
        }
        
        List<ImsSync> syncList = null;
        if (conList.size() == 2)
        {
            conList.add(new DBCondition(ImsSync.Field.sts, EnumCodeDefine.SYNC_DEAL_STS_INITIAL));
            syncList = DBUtil.query(ImsSync.class, conList.toArray(new DBCondition[conList.size()]));
        }
        else if (conList.size() == 3)
        {
            syncList = DBUtil.query(ImsSync.class, new DBOrCondition(conList.toArray(new DBCondition[conList.size()])),
                    new DBCondition(ImsSync.Field.sts, EnumCodeDefine.SYNC_DEAL_STS_INITIAL));
        }
        
        if (CommonUtil.isNotEmpty(syncList))
        {
            List<Long> ids = new ArrayList<Long>();
            for (ImsSync sync : syncList)
            {
                ids.add(sync.getId());
            }
            
            if (CommonUtil.isNotEmpty(ids))
            {
                DBUtil.deleteByCondition(ImsSync.class, new DBCondition(ImsSync.Field.id, ids, Operator.IN));
            }
        }
    }
    
    /**
     * @Description: ims_sysnc新增数据
     * @param userId
     * @param acctId	 
     * @author: tangjl5
     * @Date: 2012-6-8
     */
    public void insertImsSync(Long userId, Long acctId)
    {
        deleteDuplicateDate(userId, acctId);
        ImsSync imsSync = new ImsSync();
        if (CommonUtil.isValid(acctId))
            imsSync.setAcctId(acctId);
        
        if (CommonUtil.isValid(userId))
            imsSync.setUserId(userId);
        
        imsSync.setId(DBUtil.queryMax(ImsSync.class, ImsSync.Field.id).longValue());
        imsSync.setSoNbr(context.getSoNbr());
        imsSync.setEventType(ConstantDefine.EVENT_TYPE_MODIFY_PROFILE);
        imsSync.setDealCount(0);
        imsSync.setSts((int) EnumCodeDefine.SYNC_DEAL_STS_INITIAL);
        insert(imsSync);
    }
}
