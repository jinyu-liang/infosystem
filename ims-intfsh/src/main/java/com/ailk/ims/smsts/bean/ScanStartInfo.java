package com.ailk.ims.smsts.bean;

import com.ailk.ims.common.DBCondition;

/**
 * @Description:扫描开始信息
 * @author wangjt
 * @Date 2012-8-27
 */
public class ScanStartInfo
{
    private String taskDate = null;
    private Long blockId = null;// 如果有上次未执行完成的任务，则从sms_send_value中获取
    private int startCount = 0;// 如果有上次未执行完成的任务，则从sms_send_value中获取
    private long startValue = 0;// 如果有上次未执行完成的任务，则从sms_send_value中获取
    private DBCondition[] queryCondArr = null;

    public String getTaskDate()
    {
        return taskDate;
    }

    public void setTaskDate(String taskDate)
    {
        this.taskDate = taskDate;
    }

    public Long getBlockId()
    {
        return blockId;
    }

    public void setBlockId(Long blockId)
    {
        this.blockId = blockId;
    }

    public int getStartCount()
    {
        return startCount;
    }

    public void setStartCount(int startCount)
    {
        this.startCount = startCount;
    }

    public long getStartValue()
    {
        return startValue;
    }

    public void setStartValue(long startValue)
    {
        this.startValue = startValue;
    }

    public DBCondition[] getQueryCondArr()
    {
        return queryCondArr;
    }

    public void setQueryCondArr(DBCondition[] queryCondArr)
    {
        this.queryCondArr = queryCondArr;
    }
}
