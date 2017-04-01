package com.ailk.ims.smsts.bean;

import java.util.ArrayList;
import java.util.List;
import com.ailk.openbilling.persistence.jd.entity.SmsSendErrObj;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description:用来保存任务停止时，还未提交到数据库的信息
 * @author wangjt
 * @Date 2012-8-24
 */
public class ScanStopInfo
{
    private int stopCount;// 本次迭代完成，总共的需要发送的短信总数
    private List<SmsSendInterfaceCheck> detailList;// 用来保存需要发送短信的临时记录
    private List<SmsSendErrObj> errorObjList;// 用来保存出错记录的临时列表

    public ScanStopInfo(int size)
    {
        detailList = new ArrayList<SmsSendInterfaceCheck>(size);
        errorObjList = new ArrayList<SmsSendErrObj>();
    }

    public int getStopCount()
    {
        return stopCount;
    }

    public void setStopCount(int stopCount)
    {
        this.stopCount = stopCount;
    }

    public List<SmsSendInterfaceCheck> getDetailList()
    {
        return detailList;
    }

    public void setDetailList(List<SmsSendInterfaceCheck> detailList)
    {
        this.detailList = detailList;
    }

    public List<SmsSendErrObj> getErrorObjList()
    {
        return errorObjList;
    }

    public void setErrorObjList(List<SmsSendErrObj> errorObjList)
    {
        this.errorObjList = errorObjList;
    }
}
