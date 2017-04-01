package com.ailk.imssh.common.flow.scandeal.queue.holder;

import jef.database.DataObject;
import com.ailk.imssh.common.flow.bean.SyncMdbInfoBean;

/**
 * @Description:队列中对象的数据结构
 * @author wangjt
 * @Date 2012-9-17
 */
public class DataHolder
{

    /**
     * 该对象具体所在的分表名，如cd.i_data_index_sub_{idx}
     */
    private String subTableName;
    /**
     * 具体的对象，如IDataIndexSub
     */
    private DataObject dataObject;
    /**
     * dataObject 中的业务关键字段值【cust_id或者acct_id】
     */
    private long busiValue;
    
    /**
     * 上发mdb的信息
     */
    /*
    private SSyncAllInfo sSyncAllInfo;
    private SSyncAllInfo changeOwnSyncInfo;

    private Long oldAcctId;
    private Long newAcctId;
    private Long userId;
    
    public boolean userMdbFail;
    
    public boolean abmFail;
    */
    public SyncMdbInfoBean infoBean;
    
    /**
     * 是否mdb提交失败
     */
    private boolean isMdbCommitFail=false;
    

    public boolean isMdbCommitFail()
    {
        return isMdbCommitFail;
    }

    public void setMdbCommitFail(boolean isMdbCommitFail)
    {
        this.isMdbCommitFail = isMdbCommitFail;
    }
    
    public DataHolder(String subTableName, DataObject dataObject, long busiValue)
    {
        this.subTableName = subTableName;
        this.dataObject = dataObject;
        this.busiValue = busiValue;
    }
    
    public DataHolder(String subTableName, DataObject dataObject,long busiValue,SyncMdbInfoBean infoBean)
    {
        this.subTableName = subTableName;
        this.dataObject = dataObject;
        this.busiValue=busiValue;
        this.infoBean=infoBean;
    }

    public String getSubTableName()
    {
        return subTableName;
    }

    public DataObject getDataObject()
    {
        return dataObject;
    }

    public Long getBusiValue()
    {
        return busiValue;
    }

    /**
     * 返回false，表示该工单非错单
     */
    public boolean isError()
    {
        return false;
    }

    public SyncMdbInfoBean getInfoBean()
    {
        return infoBean;
    }

    public void setInfoBean(SyncMdbInfoBean infoBean)
    {
        this.infoBean = infoBean;
    }

    
}
