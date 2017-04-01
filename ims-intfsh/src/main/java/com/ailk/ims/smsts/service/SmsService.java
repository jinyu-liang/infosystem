package com.ailk.ims.smsts.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jef.database.AutoCloseIterator;
import jef.database.DataObject;
import jef.database.Field;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.smsts.bean.FreeresBaseInfo;
import com.ailk.ims.smsts.bean.ScanStopInfo;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.flowbase.IBaseFlow;
import com.ailk.ims.smsts.flowbase.group.IGrouper;
import com.ailk.ims.smsts.helper.BuildHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.SmsSendErrObj;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.jd.entity.SmsSendLog;
import com.ailk.openbilling.persistence.jd.entity.SmsSendValue;

/**
 * @Description:短信的数据库操作类，单独控制事务
 * @author wangjt
 * @Date 2012-8-7
 */
public class SmsService implements ISmsService
{
    private ImsLogger imsLogger = new ImsLogger(SmsService.class);


    @Transactional
    public boolean commitDetalBatch(AutoCloseIterator<?> iter, IBaseFlow flowInstance, String subTableName, Long blockId,
            String taskDate, ScanStopInfo scanStopInfo,int counter,SmsParam smsParam)
    {
        boolean notToEnd = false;// 默认到了末尾

        int batchSize = flowInstance.getBatchSize();
        Date currentDate = flowInstance.getCurrentDate();
        Field keyField = flowInstance.getKeyField();
        Integer busiNum = flowInstance.getBusiNum();

        int currentCount = scanStopInfo.getStopCount();
        List<SmsSendInterfaceCheck> detailList = scanStopInfo.getDetailList(); // 用来存放批量审核明细短信
        List<SmsSendErrObj> errorObjList = scanStopInfo.getErrorObjList();

        int dealCount = 0;
        // 当前的key值
        Long currentValue = null;
        while (iter.hasNext())
        {
            DataObject obj = (DataObject) iter.next();
            dealCount++;
            List<SmsSendInterfaceCheck> list = null;
            try
            {
                currentValue = (Long) ClassUtil.getPropertyValue(obj, keyField.name());
                // 具体业务中，处理是否需要发短信的判断，然后返回发送的短信信息
                list = flowInstance.judgeThenReturnSmsList(obj, blockId);

                if (list != null && !list.isEmpty())
                {
                    detailList.addAll(list);
                    currentCount += list.size();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                imsLogger.error(e, e);// 记录详细信息
                SmsSendErrObj smsSendErrObj = BuildHelper.buildSmsSendErrObj(subTableName, busiNum, taskDate, currentValue,
                        e.toString(), currentDate);
                errorObjList.add(smsSendErrObj);
            }

            // 当处理完batchSize个用户，则执行insertBatch指令
            if (dealCount == batchSize)
            {
                notToEnd = true;// 表示游标还未到末尾
                break;// 跳出循环，表示处理完了{batchSize}条数据，需要提交事务
            }
        }
        // 代码执行到此处，有两种情况： 1、迭代到了需要发短信的记录条数到达batchSize条数据，break到此，此时游标还未到末尾；
        // 2、游标到了末尾，正常执行到此;

        // 对于游标到了末尾，但未进行while循环的情况，直接返回
        if (currentValue == null)
        {
            return false;// notToEnd = fasle: 到了末尾
        }
        smsParam.judeSmsCode(detailList);
        imsLogger.info(CommonUtil.concat("提交(", (counter), ")次,审核短信明细表:", detailList.size(), "(条)"));
        // 其他情况需要记录处理结果到表中【detal, errorObj, sendValue 】
        List<SmsSendValue> smsSendValues = BuildHelper.buildSmsSendValue(busiNum, subTableName, currentValue, currentCount,
                currentDate,smsParam);
        SmsHelper.insertDetalList(detailList, errorObjList, smsSendValues);
      
        //@Data 2012-12-16 wukl 短信明细提交后，再提交其它数据
        flowInstance.commitOther();
        detailList.clear();
        errorObjList.clear();
        scanStopInfo.setStopCount(currentCount);// 记录总共记录总数

        return notToEnd;// 返回游标是否到了末尾的信息
    }

    @Transactional
    public boolean commitGroupDetalBatch(Iterator<?> iter, IBaseFlow flowInstance, String subTableName, Long blockId,
            String taskDate, ScanStopInfo scanStopInfo, IGrouper resGrouper,int counter,SmsParam smsParam)
    {

        boolean notToEnd = false;// 默认到了末尾

        int batchSize = flowInstance.getBatchSize();
        Date currentDate = flowInstance.getCurrentDate();
        Field keyField = flowInstance.getKeyField();
        Integer busiNum = flowInstance.getBusiNum();

        int currentCount = scanStopInfo.getStopCount();
        List<SmsSendInterfaceCheck> detailList = scanStopInfo.getDetailList(); // 用来存放批量审核明细短信
        List<SmsSendErrObj> errorObjList = scanStopInfo.getErrorObjList();

        int dealCount = 0;
        // 当前的key值
        Long currentValue = null;
        List<Object> group = new ArrayList<Object>();
        while (iter.hasNext())
        {
            DataObject obj =convert((FreeresBaseInfo) iter.next());
            dealCount++;

        
            
            List<SmsSendInterfaceCheck> list = null;
            try
            {
                if (group.size() == 0 || resGrouper.isSameGroup(group.get(0), obj))
                {
                    group.add(obj);
                    continue;
                }
                currentValue = (Long) ClassUtil.getPropertyValue(group.get(0), keyField.name());
                // 具体业务中，处理是否需要发短信的判断，然后返回发送的短信信息
                list = flowInstance.judgeThenReturnSmsList(group, blockId);

                if (list != null && !list.isEmpty())
                {
                    detailList.addAll(list);
                    currentCount += list.size();
                }
  
            }
            catch (Exception e)
            {
                imsLogger.error(e, e);// 记录详细信息
                SmsSendErrObj smsSendErrObj = BuildHelper.buildSmsSendErrObj(subTableName, busiNum, taskDate, currentValue,
                        e.toString(), currentDate);
                errorObjList.add(smsSendErrObj);
            }
            group.clear();
            group.add(obj);
            // 当处理完batchSize个用户，则执行insertBatch指令
            if (dealCount >=batchSize)
            {
                notToEnd = true;// 表示游标还未到末尾
                break;// 跳出循环，表示处理完了{batchSize}条数据，需要提交事务
            }
        }
        //group 还有数据,做该批次的最后一次处理
        if(CommonUtil.isNotEmpty(group)){
            List<SmsSendInterfaceCheck> list=null;
            try{
                 list =flowInstance.judgeThenReturnSmsList(group, blockId);
            }catch (Exception e) {
                imsLogger.error(e, e);// 记录详细信息
                SmsSendErrObj smsSendErrObj = BuildHelper.buildSmsSendErrObj(subTableName, busiNum, taskDate, currentValue,
                        e.toString(), currentDate);
                errorObjList.add(smsSendErrObj);
            }
            if(CommonUtil.isNotEmpty(list)){
                currentCount+=list.size();
                detailList.addAll(list);
            }
        }
        
        
        // 代码执行到此处，有两种情况： 
        //1、迭代到了需要发短信的记录条数到达batchSize条数据，break到此，此时游标还未到末尾；
        // 2、游标到了末尾，正常执行到此;

        // 对于游标到了末尾，但未进行while循环的情况，直接返回
        if (currentValue == null)
        {
            return false;// notToEnd = fasle: 到了末尾
        }
        smsParam.judeSmsCode(detailList);        
        imsLogger.info(CommonUtil.concat("提交(", (counter), ")次,审核短信明细表:", detailList.size(), "(条)"));
        // 其他情况需要记录处理结果到表中【detal, errorObj, sendValue 】
        List<SmsSendValue> smsSendValues = BuildHelper.buildSmsSendValue(busiNum, subTableName, currentValue, currentCount,
                currentDate,smsParam);

        SmsHelper.insertDetalList(detailList, errorObjList, smsSendValues);
        

        //@Data 2012-12-16 wukl 短信明细提交后，再提交其它数据
        flowInstance.commitOther();
        
        detailList.clear();
        errorObjList.clear();

        scanStopInfo.setStopCount(currentCount);// 记录总共记录总数

        return notToEnd;// 返回游标是否到了末尾的信息
    }

    private CaFreeres convert(FreeresBaseInfo info)
    {
        CaFreeres res = new CaFreeres();
        res.setResourceId(info.getResourceId());
        res.setRealUnit(info.getRealUnit());
        res.setUnit(info.getUnit());
        res.setItemCode(info.getItemCode());
        res.setAcctId(info.getAcctId());
        res.setBeginDate(info.getBeginDate());
        res.setBillMonth(info.getBillMonth());
        res.setEndDate(info.getEndDate());
        res.setExpireDate(info.getExpireDate());
        res.setItemName(info.getItemName());
        res.setMeasureId(info.getMeasureId());
        res.setProductId(info.getProductId());
        res.setProductName(info.getProductName());
        res.setRemarks(info.getRemarks());
        res.setVaildDate(info.getVaildDate());
        if (null == res.getUnit())
        {
            res.setUnit(0L);
        }
        if (null == res.getRealUnit())
        {
            res.setRealUnit(0L);
        }
        return res;
    }
    
    /**
     * 短信总表
     * @param checkSmsSend
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public  void commitCheckSmsSend(CheckSmsSend checkSmsSend){
        if (checkSmsSend != null)
        {
            // 记录总表
            DBUtil.insert(checkSmsSend);
 
        }
    }

    /**
     * [需要单独事务控制]
     */

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void commitTotal(CheckSmsSend checkSmsSend, SmsSendLog smsSendLog)
    {

       
        // 日志表
        DBUtil.insert(smsSendLog);
        imsLogger.info("insert CheckSmsSend end,subTableName=", smsSendLog.getTableName(), ",busiNum=", smsSendLog.getBusiNum(),
                ",taskDate=", smsSendLog.getTaskDate());

        // 删除该业务该子表中的记录
        DBUtil.deleteByCondition(SmsSendValue.class, new DBCondition(SmsSendValue.Field.tableName, smsSendLog.getTableName()),
                new DBCondition(SmsSendLog.Field.busiNum, smsSendLog.getBusiNum()));
        imsLogger.info("delete SmsSendValue end,subTableName=", smsSendLog.getTableName(), ",busiNum=", smsSendLog.getBusiNum());
    }

}
