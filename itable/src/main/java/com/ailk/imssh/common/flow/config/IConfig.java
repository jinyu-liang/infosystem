package com.ailk.imssh.common.flow.config;

import java.util.Date;
import jef.database.DataObject;
import jef.database.Field;
import jef.database.query.Query;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;

/**
 * @Description:参数和转化配置类
 * @author wangjt
 * @Date 2012-9-14
 */
public interface IConfig
{
    /**
     * 扫描数据一次扫描的条数<BR>
     */
    public int getScanOneTimeCount();

    /**
     * 增加数据到队列中时 ，队列不能超过的数据总数
     */
    public int getQueueMaxCount();

    /**
     * 处理的线程数，等于队列数
     */
    public int getDealThreadCount();

    /**
     * 需要扫描的分表总数
     */
    public int getSubTableCount();

    /**
     * 大索引表名称
     */
    public String getTableName();

    /**
     * 小索引表的前缀
     */
    public String getSubTableNamePrefix();

    /**
     * 扫描大索引的查询条件，包括order by条件
     */
    public Query newTableQuery();

    /**
     * 小索引表查询时的条件
     */
    public Query newSubTableQuery();

    /**
     * 小索引表中的seqId
     */
    public Field getSubTableSeqIdField();

    /**
     * 获取对象的seqId值
     */
    public Long getValueOfSeqId(DataObject dataObject);

    /**
     * 获取对象的soNbr值
     */
    public Long getValueOfSoNbr(DataObject dataObject);
    public String getValueOfUserId(DataObject dataObject);
    public String getValueOfCustId(DataObject dataObject);
    public String getValueOfAcctId(DataObject dataObject);
    
    /**
     * 获取对象的冲突Key值
     */
    public String getValueOfKey(DataObject dataObject,int type);

    /**
     * 获取对象的dbSuccSts值
     */
    public Short getValueOfDbSuccSts(DataObject dataObject);

    /**
     * 获取业务参考字段的值
     * 
     * @param dataObject:大索引表的对象：IDataIndex
     */
    public Long getValueOfBusiField(DataObject dataObject);

    /**
     * 获取业务参考字段的值
     * 
     * @param dataObject:小索引表的对象：IDataIndexSub
     */
    public Long getValueOfBusiFieldFromSub(DataObject dataObject);

    /**
     * 获取大索引表中，要删除的对象
     */
    public DataObject getDeleteDataObject(DataObject dataObject);

    /**
     * 获取小索引表中，要删除的对象
     */
    public DataObject getDeleteDataObjectSub(DataObject dataObject);

    /**
     * 获取小索引表中，需要修改状态为 1 的对象
     */
    public DataObject getUpdateDataObjectSub(DataObject dataObject);

    /**
     * 从大索引表对象，转换为小索引表对象
     */
    public DataObject convertToSubDataObject(DataObject dataObject);
    public DataObject convertToSubDataObjectWithOrgCust(DataObject dataObject,Long custId);

    /**
     * sub表，统一转为IDataIndex ，在context中使用
     */
    public IDataIndex convertToIDataIndex(DataObject dataObject);

    /**
     * 小索引表对象，转为历史表对象
     */
    public DataObject convertToHis(DataObject dataObject, Date dealDate);

    /**
     * 小索引表对象，转为错误表对象
     */
    public DataObject convertToErr(DataObject dataObject, Date dealDate, Long errorCode, String errorMsg);

    /**
     * 小索引表对象，转为通知表对象
     */
    public DataObject convertToNotify(DataObject dataObject, Long errorCode, String errorMsg);

    /**
     * 设置为入库成功，上发mdb失败状态：1:DB_SUCC_STS_TRUE
     */
    public void setDbSuccStsTrue(DataObject dataObject);
    
    /**
     * 设置为db失败，因为之前mdb上发失败设置为成功状态
     * 后续beforecommit的时候报错，回滚数据库，会导致所有数据都回滚
     * 这时候需要设置回db失败状态
     */
    public void setDBSuccStsFalse(DataObject dataObject);
    
    /**
     * 设置每一步处理之后的状态
     * 物理库未处理0
     * 物理库成功，mdb未处理9
     * 物理库成功，mdb失败1
     * 物理库成功，mdb成功8
     * mdb处理成功后入历史表失败导致物理库回滚0
     * @param dataObject
     * @param value
     */
    public void setDealSts(DataObject dataObject,short value);
    /**
     * 获取业务的busi_code
     * @param dataObject
     * @return
     */
    public Long getValueOfBusiCode(DataObject dataObject);
    
    /**
     * 分发进程等待时间
     * @return
     */
    public Integer getSleepTime();
    
}