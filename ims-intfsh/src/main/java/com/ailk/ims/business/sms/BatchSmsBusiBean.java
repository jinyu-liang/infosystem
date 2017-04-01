package com.ailk.ims.business.sms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Batch;
import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.shinner.entity.BatchSmsInfo;
import com.ailk.openbilling.persistence.shinner.entity.CheckSms;
import com.ailk.openbilling.persistence.shinner.entity.CheckSmsDetail;
import com.ailk.openbilling.persistence.shinner.entity.Do_insertBatchSmsResponse;
import com.ailk.openbilling.persistence.shinner.entity.SmsMessage;
import com.ailk.openbilling.persistence.shinner.entity.SmsMessage_item;

/**
 * @Description:提供给账管的批量短信接口
 * @author wukl
 * @Date 2012-7-20
 */
public class BatchSmsBusiBean extends BaseCancelableBusiBean
{

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {

    }

    @Override
    public void init(Object... input) throws BaseException
    {

    }

    @Override
    public void validate(Object... input) throws BaseException
    {

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    @Transactional
    public Object[] doBusiness(Object... input) throws BaseException
    {
        BatchSmsInfo batchSmsInfo = (BatchSmsInfo) input[0];
        if (batchSmsInfo == null)
        {
            return null;
        }
        CheckSms checkSms = batchSmsInfo.getCheckSms();
        List<CheckSmsDetail> detailList = batchSmsInfo.getListCheckSmsDetail();

        // 插入批量明细表
        if (CommonUtil.isNotEmpty(detailList))
        {
            ArrayList<SmsSendInterfaceCheck> insertList = new ArrayList<SmsSendInterfaceCheck>();
            for (CheckSmsDetail detail : detailList)
            {
                insertList.add(this.getSmsSendInterfaceCheck(detail));
            }

            if (CommonUtil.isNotEmpty(insertList))
            {
                try
                {
                    Batch<SmsSendInterfaceCheck> batch = DBUtil.getDBClient().startBatchInsert(SmsSendInterfaceCheck.class);
                    batch.add(insertList);
                    batch.setGroupForPartitionTable(true);
                    batch.commit();
                }
                catch (Exception e)
                {
                    throw IMSUtil.throwBusiException(e);
                }
            }
        }
        if (checkSms != null)
        {
            // 批量审批总表有数据，则插入CheckSmsSend
            this.insertCheckSmsSend(checkSms);
        }
        return null;

    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_insertBatchSmsResponse();
    }

    @Override
    public void destroy()
    {

    }

    /**
     * 组装短信明细的信息
     * @Author: wukl 
     * @Date: 2012-11-1
     * @param detail
     * @return
     */
    private SmsSendInterfaceCheck getSmsSendInterfaceCheck(CheckSmsDetail detail)
    {
        if (!CommonUtil.isValid(detail.getBill_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bill_id");
        }
        if (!CommonUtil.isValid(detail.getSend_date()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "send_date");
        }
        if (!CommonUtil.isValid(detail.getSms_code()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sms_code");
        }

        SmsSendInterfaceCheck smsSendInterfaceCheck = new SmsSendInterfaceCheck();
        smsSendInterfaceCheck.setDoneCode(SmsSeqConfig.newDoneCode());// 取SEQ_DONE_CODE
        smsSendInterfaceCheck.setBillId(detail.getBill_id());
        if (detail.getPriority_level() != null)
        {
            smsSendInterfaceCheck.setPriorityLevel(detail.getPriority_level());
        }
        smsSendInterfaceCheck.setSendDate(IMSUtil.formatDate(detail.getSend_date()));
        smsSendInterfaceCheck.setDoneDate(IMSUtil.formatDate(detail.getSend_date()));
        if (null != detail.getRequest_report())
        {
            smsSendInterfaceCheck.setRequestReport(detail.getRequest_report().intValue());
        }
        else
        {
            smsSendInterfaceCheck.setRequestReport(0);
        }
        smsSendInterfaceCheck.setSmsCode(detail.getSms_code());
        smsSendInterfaceCheck.setBlockId(detail.getBlock_id());//需要跟总表的匹配，由外部传入

        // 实例化短信内容
        String instanceMessage = SmsUtil.instanceSmsMessage(detail.getSms_code(), convertToMap(detail.getSmsMessageList()));
        if (!CommonUtil.isValid(instanceMessage))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "message");
        }
        smsSendInterfaceCheck.setMessage(instanceMessage);

        return smsSendInterfaceCheck;
    }

    private static Map<String, String> convertToMap(SmsMessage_item items)
    {
        Map<String, String> map = new HashMap<String, String>();
        if (null != items && null != items.getSmsMessage())
        {
            for (SmsMessage message : items.getSmsMessage())
            {
                map.put(message.getKey(), message.getValue());
            }
        }
        return map;
    }

    /**
     * 组装短信审核总表
     * @Author: wukl 
     * @Date: 2012-11-1
     * @param checkSms
     */
    private void insertCheckSmsSend(CheckSms checkSms)
    {
        if (!CommonUtil.isValid(checkSms.getMod_code()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "mod_code");
        }
        if (!CommonUtil.isValid(checkSms.getSend_date()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "send_date");
        }
        if (!CommonUtil.isValid(checkSms.getDone_date()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "done_date");
        }
        
        CheckSmsSend checkSmsSend = new CheckSmsSend();
        checkSmsSend.setBlockId(checkSms.getBlock_id());//跟明细的匹配，由外部传入
        checkSmsSend.setModCode(checkSms.getMod_code());//短信模板，由外部传入
        checkSmsSend.setSendDate(IMSUtil.formatDate(checkSms.getSend_date()));//插入时间
        checkSmsSend.setDoneDate(IMSUtil.formatDate(checkSms.getDone_date()));//插入时间
        if (CommonUtil.isValid(checkSms.getSend_mod()))
        {
            checkSmsSend.setSendMod(checkSms.getSend_mod().intValue());
        }
        else
        {
            checkSmsSend.setSendMod(1);
        }
        if (CommonUtil.isNotEmpty(checkSms.getData_source()))
        {
            checkSmsSend.setDataSource(checkSms.getData_source());
        }
        checkSmsSend.setCheckStatus(0);// 默认:0--未审核
        checkSmsSend.setSendNum(0L);//默认:0，后面同步时会更新这个值
        checkSmsSend.setExt1("10086");//默认:10086
        DBUtil.insert(checkSmsSend);
    }
}
