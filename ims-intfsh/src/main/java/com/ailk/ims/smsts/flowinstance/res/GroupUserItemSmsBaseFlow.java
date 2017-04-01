package com.ailk.ims.smsts.flowinstance.res;

import com.ailk.ims.smsts.flowbase.SmsBaseFlow;
import com.ailk.ims.smsts.flowbase.group.IGrouper;
import com.ailk.ims.smsts.flowbase.group.ResourceIdResGrouper;
/**
 * 获取免费资源或，根据用户Id进行分组，即相同的用户将会分到同一组，子类得到的将是一个用相同用户ID的免费资源list
 * 适用范围：如果一个用户订购了多个科目，但是不同科目短信内容部一样，而且短信内容需要合并成统一短信里面。
 * @author gaoqc5  2012-12-06
 *
 */
public abstract class GroupUserItemSmsBaseFlow extends SmsBaseFlow
{
    
    @Override
    protected IGrouper getGrouper()
    {
        return new ResourceIdResGrouper();
    }
    /**
     * @param items
     * @param freeresTableName
     * @return
     */
    @Override
    protected String buildSql(String items, String freeresTableName)
    {
        return new StringBuffer("select "+buildSelect())
        .append(" from " + freeresTableName+" t")// 表名
        .append(buildCondition(items))// 条件
        .append(buildOtherCondition())//额外条件
        .append(" GROUP BY "+buildGroupBy())// 根据用户，科目分组
        .append(buildHavingCondition())
        .append(" ORDER BY t.RESOURCE_ID")// 排序
        .toString();
    }
    protected String buildSelect(){
        return new StringBuffer("  t.RESOURCE_ID as resourceId,")
        .append(" SUM(CASE measure_id    WHEN 104 THEN 1024*unit   WHEN 105 THEN 1024*1024*unit  WHEN 101 THEN unit/1000   WHEN 106 THEN unit*60 WHEN 107 THEN unit*3600  else unit END) as unit ")
        .append(" ,SUM(CASE measure_id    WHEN 104 THEN 1024*real_unit   WHEN 105 THEN 1024*1024*real_unit  WHEN 101 THEN real_unit/1000   WHEN 106 THEN real_unit*60 WHEN 107 THEN real_unit*3600  else real_unit END) as realUnit ")
        .append(" , t. ITEM_CODE as itemCode")
        .toString();
    }
    
    protected String buildGroupBy(){
        return "t.RESOURCE_ID ,t. ITEM_CODE";
    }
    protected String buildHavingCondition(){
        return "";
    }
    /**
     * 子类如果要加上额外条件，则覆盖该方法
     * @return
     */
    protected String buildOtherCondition(){
        return "";
    }

    
    

}
