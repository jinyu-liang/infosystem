package com.ailk.ims.send.config;

import jef.common.wrapper.IntRange;
import jef.database.DataObject;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;
import com.ailk.ims.send.init.InitInfo;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsBatch;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsBatchHis;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;

/**
 * @Description:单表数据同步给CRM的配置信息-TiOSmsImme
 * @author wangjt
 * @Date 2012-10-15
 */
public class ImmeBatchSmsConfig implements SendConfig
{
    /**
     * 从billing侧数据库查询时的表对象
     */
    public Class<? extends DataObject> getTableClass()
    {
        return TiOSmsBatch.class;
    }

    /**
     * 从billing侧数据库查询时的表对象-历史表
     */
    public Class<? extends DataObject> getHisTableClass()
    {
        return TiOSmsBatchHis.class;
    }

    /**
     * 操作CRM侧数据库的表对象-表名，如： TI_O_SMS_IMME
     */
    public String getCrmTableName()
    {
        return "TI_O_SMS_BATCH";
    }

    /**
     * 从billing侧数据库查询时的表对象-表名前缀，如： JD.TI_O_SMS_IMME_
     */
    public String getTablePrefix()
    {
        return "JD.TI_O_SMS_BATCH_";
    }

    /**
     * 从billing侧数据库查询时的表对象-历史表-表名前缀，如： JD.TI_O_SMS_IMME_HIS
     */
    public String getHisTablePrefix()
    {
        return "JD.TI_O_SMS_BATCH_HIS";
    }

    /**
     * 从billing侧数据库查询时的条件(包含排序字段)
     */
    public Query getQuery()
    {
        Query query = QueryBuilder.create(getTableClass());
        query.addOrderBy(true, TiOSmsImme.Field.smsNoticeId);// 排序字段
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
        return InitInfo.getSmsImmeTableCount();
    }

    /**
     * 实时短信不用
     */
    public String getCrmTableExtName()
    {
        return null;
    }

    /**
     * 实时短信不用
     */
    public String getTableExtPrefix()
    {
        return null;
    }

    /**
     * 实时短信不用
     */
    public String getHisTableExtPrefix()
    {
        return null;
    }
}