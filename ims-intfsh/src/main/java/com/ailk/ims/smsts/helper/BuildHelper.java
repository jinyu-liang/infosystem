package com.ailk.ims.smsts.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.SmsSendErrObj;
import com.ailk.openbilling.persistence.jd.entity.SmsSendLog;
import com.ailk.openbilling.persistence.jd.entity.SmsSendValue;

/**
 * @Description:构建对象
 * @author wangjt
 * @Date 2012-8-9
 */
public class BuildHelper
{
    private static  ImsLogger imsLogger = new ImsLogger(BuildHelper.class); // 子类可以直接使用
    /**
     * 构建： SmsSendLog
     */
    public static SmsSendLog buildSmsSendLog(String subTableName, Integer busiNum, String taskDate, Date currentDate)
    {
        SmsSendLog smsSendLog = new SmsSendLog();
        smsSendLog.setTableName(subTableName);
        smsSendLog.setBusiNum(busiNum);
        smsSendLog.setTaskDate(taskDate);
        smsSendLog.setSoDate(currentDate);
        return smsSendLog;
    }

    /**
     * 构建： CheckSmsSend
     */
    public static CheckSmsSend buildCheckSmsSend(Long blockId, Long modCode, Date currentDate, long count)
    {
        // 明细表没有数据，总表返回null，表示不需要insert总表记录
        if (count == 0)
        {
            imsLogger.info("明细表数据为空,审核总表不插入数据");
            return null;
        }
        CheckSmsSend checkSmsSend = new CheckSmsSend();
        checkSmsSend.setBlockId(blockId);
        checkSmsSend.setModCode(modCode);
        checkSmsSend.setSendDate(currentDate);
        checkSmsSend.setDoneDate(currentDate);
        checkSmsSend.setSendMod(1);
        checkSmsSend.setSendNum(0L);
        checkSmsSend.setExt1("10086");//默认:10086
        return checkSmsSend;
    }

    /**
     * 构建： SmsSendValue
     */
    public static List<SmsSendValue> buildSmsSendValue(Integer busiNum, String tableName, Long currentValue,
            Integer currentCount, Date currentDate,SmsParam param)
    {
        List<SmsSendValue> vals = new ArrayList<SmsSendValue>();

        for (Long smsCode : param.getHasMsgSmsCode())
        {
            Long blockId=param.getTemplateIdAndBlockIdMap().get(smsCode);
            vals.add(createSmsSendValue(blockId,busiNum,tableName,currentValue,currentCount,currentDate,smsCode));
        }
       //如果没有，则也应该补充一个临时的
        if(vals.isEmpty()){
            vals.add(createSmsSendValue(param.getFisrtBlockId(), busiNum, tableName, currentValue, currentCount, currentDate, param.getFisrtSmsCode()));
        }
        return vals;
    }
    private static SmsSendValue createSmsSendValue(Long blockId,Integer busiNum,String tableName,Long currentValue,Integer currentCount,Date currentDate,Long smsCode){
        SmsSendValue smsSendValue = new SmsSendValue();
        smsSendValue.setBlockId(blockId);
        smsSendValue.setBusiNum(busiNum);
        smsSendValue.setTableName(tableName);
        smsSendValue.setCurrentValue(currentValue);
        smsSendValue.setCurrentCount(currentCount);
        smsSendValue.setSoDate(currentDate);
        smsSendValue.setSmsCode(smsCode);
        return smsSendValue;
    }

    /**
     * 构建一个错误用户记录对象
     */
    public static SmsSendErrObj buildSmsSendErrObj(String tableName, Integer busiNum, String taskDate, Long errorValue,
            String errorDesc, Date soDate)
    {
        SmsSendErrObj smsSendErrObj = new SmsSendErrObj();
        smsSendErrObj.setTableName(tableName);
        smsSendErrObj.setBusiNum(busiNum);
        smsSendErrObj.setTaskDate(taskDate);
        if(errorDesc !=null && errorDesc.length()>=256){
        	smsSendErrObj.setErrorDesc(errorDesc.substring(0, 256));
        }else
        {
        	smsSendErrObj.setErrorDesc(errorDesc);
        }
        smsSendErrObj.setErrorValue(errorValue);
        smsSendErrObj.setSoDate(soDate);
        return smsSendErrObj;
    }

}
