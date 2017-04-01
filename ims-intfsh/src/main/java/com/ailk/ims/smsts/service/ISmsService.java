package com.ailk.ims.smsts.service;

import java.util.Iterator;
import jef.database.AutoCloseIterator;
import com.ailk.ims.smsts.bean.ScanStopInfo;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.flowbase.IBaseFlow;
import com.ailk.ims.smsts.flowbase.group.IGrouper;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.SmsSendLog;

/**
 * @Description:接口类，实现类中需要指定事务控制方式
 * @author wangjt
 * @Date 2012-8-7
 */
public interface ISmsService
{
    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    // public void commitDetalList(List<SmsSendInterfaceCheck> smsSendInterfaceCheckList, List<SmsSendErrObj> smsSendErrObjList,
    // SmsSendValue smsSendValue);

    /**
     * [需要单独事务控制]
     */
    public void commitTotal(CheckSmsSend checkSmsSend, SmsSendLog smsSendLog);
    /**
     * 审核总表
     * @param checkSmsSend
     */
    public  void commitCheckSmsSend(CheckSmsSend checkSmsSend);
    /**
     * 往批量短信审核明细表中插数据 ，同时记录其中处理错误的信息<br>
     * [需要单独事务控制]
     * 
     * @return:notToEnd:true：表示游标还未到末尾,false表示到了末尾
     */
    public boolean commitDetalBatch(AutoCloseIterator<?> iter, IBaseFlow flowInstance, String subTableName, Long blockId,
            String taskDate, ScanStopInfo scanStopInfo,int counter,SmsParam smsParam);
    
//    public boolean commitResDetalBatch(Iterator<?> iter, IBaseFlow flowInstance, String subTableName, Long blockId,
//            String taskDate, ScanStopInfo scanStopInfo);

    public boolean commitGroupDetalBatch(Iterator<?> iter, IBaseFlow flowInstance, String subTableName, Long blockId,
            String taskDate, ScanStopInfo scanStopInfo,IGrouper resGrouper,int counter,SmsParam smsParam);
}