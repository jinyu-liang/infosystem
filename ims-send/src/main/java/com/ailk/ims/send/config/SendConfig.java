package com.ailk.ims.send.config;

import jef.common.wrapper.IntRange;
import jef.database.DataObject;
import jef.database.query.Query;

/**
 * @Description:单表数据同步给CRM的配置信息
 * @author wangjt
 * @Date 2012-10-15
 */
public interface SendConfig
{
    
    /**
     * 从billing侧数据库查询时的表对象
     */
    public Class<? extends DataObject> getTableClass();

    /**
     * 从billing侧数据库查询时的表对象-历史表
     */
    public Class<? extends DataObject> getHisTableClass();

    /**
     * 从billing侧数据库查询时的表对象-表名，如： TI_O_SMS_IMME/CHECK_SMS_SEND
     */
    public String getCrmTableName();
    
    /**
     * 从billing侧数据库查询时的表对象-表名前缀，如： JD.TI_O_SMS_IMME_/JD.CHECK_SMS_SEND_
     */
    public String getTablePrefix();
    
    /**
     * 从billing侧数据库查询时的表对象-历史表-表名前缀，如： JD.TI_O_SMS_IMME_HIS_/JD.CHECK_SMS_SEND_HIS_
     */
    public String getHisTablePrefix();
    
    /**
     * 从billing侧数据库查询时的扩展表对象-表名（用于审核短信的明细表），如： SMS_SEND_INTERFACE_CHECK
     */
    public String getCrmTableExtName();
    
    /**
     * 从billing侧数据库查询时的扩展表对象-表名前缀（用于审核短信的明细表），如： JD.SMS_SEND_INTERFACE_CHECK_
     */
    public String getTableExtPrefix();
    
    /**
     * 从billing侧数据库查询时的扩展表对象-历史表-表名前缀（用于审核短信的明细表），如： JD.SMS_SEND_INTF_CHK_HIS_
     */
    public String getHisTableExtPrefix();

    /**
     * 从billing侧数据库查询时的条件(包含排序字段)
     */
    public Query getQuery();

    /**
     * 一次查询的数据条数
     */
    public int getBatchSize();

    /**
     * 一次查询的数据条数的数据库对象
     */
    public IntRange getIntRange();

    /**
     * 获取短信处理线程数
     */
    public int getSubTableCount();
    
    /**
     * 错误处理第一次停顿时间
     */
    public static int FIRST_WAIT_SECOND = 5;
    
    /**
     * 错误处理相乘系数
     */
    public static int MULTIPLY_FACTOR = 3;
    
    /**
     * 错误处理最大重试次数
     */
    public static int MAX_RETRY_TIME = 4;
}