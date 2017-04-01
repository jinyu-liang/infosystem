package com.ailk.ims.send.config;

import jef.common.wrapper.IntRange;
import jef.database.DataObject;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;
import com.ailk.ims.send.init.InitInfo;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSendHis;

/**
 * @Description:审核短信同步给CRM的配置信息
 * @author wangjt
 * @Date 2012-10-15
 */
public class CheckSmsConfig implements SendConfig
{
    /**
     * 从billing侧数据库查询时的审核总表对象
     */
    public Class<? extends DataObject> getTableClass()
    {
        return CheckSmsSend.class;
    }

    /**
     * 从billing侧数据库查询时的审核总表对象-历史表
     */
    public Class<? extends DataObject> getHisTableClass()
    {
        return CheckSmsSendHis.class;
    }

    /**
     * 操作CRM侧数据库的审核总表对象-表名，如： CHECK_SMS_SEND
     */
    public String getCrmTableName()
    {
        return "CHECK_SMS_SEND";
    }

    /**
     * 从billing侧数据库查询时的审核总表对象-表名前缀，如： JD.CHECK_SMS_SEND_
     */
    public String getTablePrefix()
    {
        return "JD.CHECK_SMS_SEND_";
    }

    /**
     * 从billing侧数据库查询时的审核总表对象-历史表-表名前缀，如： JD.CHECK_SMS_SEND_HIS_
     */
    public String getHisTablePrefix()
    {
        return "JD.CHECK_SMS_SEND_HIS";
    }

    /**
     * 从billing侧数据库查询时的条件(包含排序字段)
     */
    public Query getQuery()
    {
        Query query = QueryBuilder.create(getTableClass());
        query.addOrderBy(true, CheckSmsSend.Field.blockId);// 排序字段
        return query;
    }

    /**
     * 一次查询的数据条数
     */
    public int getBatchSize()
    {
        return InitInfo.getBatchSize();
    }

    /**
     * 一次查询的数据条数的数据库对象
     */
    public IntRange getIntRange()
    {
        return new IntRange(1, getBatchSize());
    }

    /**
     * 获取实时短信处理线程数
     */
    public int getSubTableCount()
    {
        return InitInfo.getCheckSmsTableCount();
    }
    
    /**
     * 从billing侧数据库查询时的扩展表对象-表名（用于审核短信的明细表），如： SMS_SEND_INTERFACE_CHECK
     */
    public String getCrmTableExtName()
    {
        return "SMS_SEND_INTERFACE_CHECK";
    }

    /**
     * 从billing侧数据库查询时的扩展表对象-表名前缀（用于审核短信的明细表），如： JD.SMS_SEND_INTERFACE_CHECK_
     */
    public String getTableExtPrefix()
    {
        return "JD.SMS_SEND_INTERFACE_CHECK_";
    }

    /**
     * 从billing侧数据库查询时的扩展表对象-历史表-表名前缀（用于审核短信的明细表），如： JD.SMS_SEND_INTF_CHK_HIS_
     */
    public String getHisTableExtPrefix()
    {
        return "JD.SMS_SEND_INTF_CHK_HIS";
    }
}
