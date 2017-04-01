package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;

/**
 * @Description 服务相关组件
 * @author yanchuan
 * @Date 2012-2-1
 * @Date 2012-04-09 wangdw5 用户服务可以即时或者提前生效,根据产品账期生效时间判定
 */
public class ServiceComponent extends BaseComponent
{
    public ServiceComponent()
    {
    }

    /**
     * 获取CmResServCycle对象 yanchuan 2011-9-22
     */
    public void setResourceServiceCycle(Long resId, CoProdBillingCycle dmBillCycle, Integer servSpecId,
            List<CmResServCycle> resServList)
    {
        CmResServCycle servCycle = new CmResServCycle();
        servCycle.setResourceId(resId);
        servCycle.setServiceSpecId(servSpecId);
        servCycle.setSts(EnumCodeDefine.SERVICE_CYCLESTS_ACTIVE);
        servCycle.setUsedFlag(EnumCodeDefine.RESOURCE_SERVICE_FLAG_NO_USED);
        servCycle.setSoNbr(context.getSoNbr());
        servCycle.setCreateDate(context.getRequestDate());
        // Date valid_date = dmBillCycle.getValidDate();
        Date valid_date = context.getRequestDate();
        Date exp_date = IMSUtil.getDefaultExpireDate();
        /**
         * @Date 2012-04-09 wangdw5 用户服务可以即时或者提前生效,根据产品账期生效时间判定
         */
        if (valid_date.after(dmBillCycle.getValidDate()))
        {
            valid_date = dmBillCycle.getValidDate();
        }
        servCycle.setValidDate(valid_date);
        servCycle.setExpireDate(exp_date);
        Iterator it = resServList.iterator();
        while (it.hasNext())
        {
            CmResServCycle resServ = (CmResServCycle) it.next();
            if (resServ.getResourceId().equals(resId) && resServ.getServiceSpecId().equals(servSpecId))
            {
                // 不修改用户服务，只增加用户服务 yanchuan 2011-11-25
                // if (valid_date.before(resServ.getValidDate()))
                // {
                // resServ.setValidDate(valid_date);
                // }
                // // 如果存在的失效日期小于当前产品的失效日期，在更新成当前产品的失效日期
                // if (exp_date.after(resServ.getExpireDate()))
                // {
                // resServ.setExpireDate(exp_date);
                // }
                return;
            }
        }
        resServList.add(servCycle);

    }

    /**
     * 目前只支持插入，不支持修改 yanchuan 2011-9-26
     * lijc3 2012-7-24 多次查询合并为一次
     */
    public void updateOrInsertResServ(List<CmResServCycle> resServList)
    {
        if (CommonUtil.isEmpty(resServList))
        {
            return;
        }
        // 需要插入的服务列表
        List<CmResServCycle> insertList = new ArrayList<CmResServCycle>();
        // 当前订购的所有产品的服务列表
        Set<Integer> servSpecList = new HashSet<Integer>();
        Long resId = null;
        // 一次订购，属于同一个用户
        for (CmResServCycle serv : resServList)
        {
            resId = serv.getResourceId();
            servSpecList.add(serv.getServiceSpecId());
        }
        // 用户是否订购了当前产品的所有服务
        List<CmResServCycle> servList = query(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, resId),
                new DBCondition(CmResServCycle.Field.serviceSpecId, servSpecList, Operator.IN));
        if (CommonUtil.isNotEmpty(servList))
        {
            // 不为空，说明有些服务已经订购，需要把没有订购的服务放入插入的服务列表中
            for (CmResServCycle serv : servList)
            {
                if (!servSpecList.contains(serv.getServiceSpecId()))
                {
                    insertList.add(serv);
                }
            }
        }
        else
        {
            // 为空，则需要全部插入
            insertList = resServList;
        }
        /*
         * Iterator it = resServList.iterator(); while (it.hasNext()) { CmResServCycle resServ = (CmResServCycle) it.next(); if
         * (resServ == null) { continue; } Long resId = resServ.getResourceId(); Integer servSpecId = resServ.getServiceSpecId();
         * CmResServCycle servCycleResult = queryCmResServ(resId, servSpecId); if (servCycleResult == null) {
         * insertList.add(resServ); } }
         */
        if (insertList.size() > 0)
        {
            this.insertBatch(insertList);
        }
    }

    /**
     * 查询CM_RES_SERV_CYCLE表记录 yanchuan 2011-9-23
     */
    public CmResServCycle queryCmResServ(Long resId, Integer servSpecId)
    {
        return querySingle(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, resId), new DBCondition(
                CmResServCycle.Field.serviceSpecId, servSpecId));
    }

    /**
     * 根据条件对CM_RES_SERV_CYCLE表进行插入或修改操作 yanchuan 2011-9-23
     * 
     * @param servCycle
     */
    public void updateOrInsertResServ(CmResServCycle servCycle)
    {
        Long resourceId = servCycle.getResourceId();
        Integer serviceSpecId = servCycle.getServiceSpecId();
        // CmResServCycle cmServCycle = new CmResServCycle();
        // cmServCycle.setResourceId(resourceId);
        // cmServCycle.setServiceSpecId(serviceSpecId);
        CmResServCycle servCycleNew = new CmResServCycle();
        servCycleNew.setSoDate(context.getRequestDate());
        servCycleNew.setValidDate(servCycle.getValidDate());
        servCycleNew.setExpireDate(servCycle.getExpireDate());
        updateByCondition(servCycleNew, new DBCondition(CmResServCycle.Field.resourceId, resourceId), new DBCondition(
                CmResServCycle.Field.serviceSpecId, serviceSpecId));
    }
}
