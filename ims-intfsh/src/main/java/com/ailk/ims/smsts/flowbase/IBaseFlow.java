package com.ailk.ims.smsts.flowbase;

import java.util.Date;
import java.util.List;
import java.util.Map;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description:接口类
 * @author wangjt
 * @Date 2012-8-7
 */
public interface IBaseFlow
{
    /**
     * 对应扫描表的数据库实体对象类 比如：扫描CmResource表，则返回CmResource.class<BR>
     * 默认扫描用户表
     */
    public Class<? extends DataObject> getScanTableClass();

    /**
     * 要扫描的表中的扫描字段<BR>
     * 默认为用户ID
     */
    public Field getKeyField();

    /**
     * 指定排序字段 <BR>
     * 用户表统一按照用户编号排序,
     */
    public OrderCondition[] getOrderCond();

    /**
     * 当需要发短信的用户数到达batchSize时，短信明细表提交一次<BR>
     */
    public int getBatchSize();

    /**
     * 每个提醒业务有唯一的busiNum ，在SmsSendConfig表中配置
     */
    public Integer getBusiNum();

    /**
     * 加在扫描表的查询的条件 <BR>
     * 比如：DBCondition cond1 = new DBCondition(CmResource.Field.activeDate,date,Operator.LESS_EQUALS);
     */
    public DBCondition[] getQueryConds();

    /**
     * 返回当前时间【一个子类有一个一次生成的时间】
     */
    public Date getCurrentDate();

    /**
     * 扫描流程的实现：先查询、然后针对查询的数据做业务处理、最后插入批量短信通知表
     * 
     * @param subTableName: 分表名：如cm_resource_01
     */
    public void dealProcess(SmsParam param) throws Exception;

    /**
     * 该逻辑由具体业务实现[获得用户之后，根据业务判断是否需要发送短信]<BR>
     * 进行具体的逻辑处理，返回批量审核明细短信内容.比如调用ABM的免费资源查询接口
     * 
     * @return：返回null或者空List表示不需要发短信
     * @throws Exception:可能抛出异常
     */
    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId);

    /**
     * 用于批量提交非短信类的数据<BR>
     * 有些流程提交数据库时，既有批量审核明细短信内容，又有其它表数据；由子类自己实现数据的提交
     * wukl 2012-12-16
     */
    public void commitOther();
    
    public Map<Long,Long > getTemplateAndBlockIdMap();
    
    
    
}