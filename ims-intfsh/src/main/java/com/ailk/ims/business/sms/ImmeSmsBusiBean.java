package com.ailk.ims.business.sms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Batch;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.jd.entity.TdSSendTpl;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;
import com.ailk.openbilling.persistence.shinner.entity.Do_insertImmeSmsResponse;
import com.ailk.openbilling.persistence.shinner.entity.ImmeSms;
import com.ailk.openbilling.persistence.shinner.entity.SmsMessage;
import com.ailk.openbilling.persistence.shinner.entity.SmsMessage_item;

/**
 * @Description:提供给账管的实时短信接口
 * @author wukl
 * @Date 2012-7-20
 */
public class ImmeSmsBusiBean extends BaseCancelableBusiBean
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
    public Object[] doBusiness(Object... input) throws BaseException
    {
        ArrayList<ImmeSms> listImmeSms = (ArrayList<ImmeSms>) input[0];
        ArrayList<TiOSmsImme> insertList = new ArrayList<TiOSmsImme>();
        if (CommonUtil.isEmpty(listImmeSms))
        {
            return null;
        }
        for (ImmeSms info : listImmeSms)
        {
            insertList.add(this.getTiOSmsImme(info));
        }

        if (CommonUtil.isNotEmpty(insertList))
        {
            try
            {
                Batch<TiOSmsImme> batch = DBUtil.getDBClient().startBatchInsert(TiOSmsImme.class);
                batch.add(insertList);
                batch.setGroupForPartitionTable(true);
                batch.commit();
            }
            catch (Exception e)
            {
                throw IMSUtil.throwBusiException(e);
            }
        }

        return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_insertImmeSmsResponse();
    }

    @Override
    public void destroy()
    {

    }

    private TiOSmsImme getTiOSmsImme(ImmeSms info)
    {
        if (!CommonUtil.isValid(info.getRecv_object()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "recv_object");
        }
        if (!CommonUtil.isValid(info.getRefer_time()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "refer_time");
        }
        if (!CommonUtil.isValid(info.getSms_code()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sms_code");
        }

        TiOSmsImme tiOSmsImme = new TiOSmsImme();
        long noticeId = DBUtil.getSequence(TiOSmsImme.class);
        String dateStr = DateUtil.formatDate(IMSUtil.formatDate(info.getRefer_time()), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        dateStr = dateStr.substring(2, 8);// 取yymmdd
        dateStr += noticeId;
        tiOSmsImme.setSmsNoticeId(Long.parseLong(dateStr));// YYMMDD+10位sequence
        tiOSmsImme.setPartitionId((int) (noticeId % 1000)); // SMS_NOTICE_ID的后3位
        tiOSmsImme.setEparchyCode("0021");// 固定填0021
        // tiOSmsImme.setBrandCode(info.getBrandCode());//填空
        tiOSmsImme.setInModeCode("0");// 默认0：其他接入
        // tiOSmsImme.setSendObjectCode(info.getSendObjectCode());//填空
        tiOSmsImme.setSendTimeCode(1);// 固定填1
        tiOSmsImme.setSendCountCode(1);// 固定填1
        if (CommonUtil.isNotEmpty(info.getRecv_object_type()))
        {
            tiOSmsImme.setRecvObjectType(info.getRecv_object_type());
        }
        else
        {
            tiOSmsImme.setRecvObjectType("00");
        }
        tiOSmsImme.setRecvObject(info.getRecv_object());
        tiOSmsImme.setRecvId(0L);
        tiOSmsImme.setNoticeContentType("0");

        // 2012-10-25 实时短信的内容通过短信模板翻译得到
        String instanceMessage = SmsUtil.instanceSmsMessage(info.getSms_code(), convertToMap(info.getSmsMessageList()));
        if (CommonUtil.isEmpty(instanceMessage))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "notice_content");
        }
        tiOSmsImme.setNoticeContent(instanceMessage);
        tiOSmsImme.setReferedCount(0);
        tiOSmsImme.setForceReferCount(1);
        if (CommonUtil.isNotEmpty(info.getForce_object()))
        {
            tiOSmsImme.setForceObject(info.getForce_object());
        }
        else
        {
            tiOSmsImme.setForceObject("10086");
        }
        tiOSmsImme.setReferTime(IMSUtil.formatDate(info.getRefer_time()));
        tiOSmsImme.setDealTime(IMSUtil.formatDate(info.getRefer_time()));
        tiOSmsImme.setDealState("15");
        if (CommonUtil.isValid(info.getDone_code()))
        {
            tiOSmsImme.setDoneCode(info.getDone_code());// 取外部传入的
        }
        else
        {
            tiOSmsImme.setDoneCode(context.getDoneCode());
        }
        tiOSmsImme.setSmsCode(info.getSms_code());
        if (CommonUtil.isNotEmpty(info.getRemark()))
        {
            tiOSmsImme.setRemark(info.getRemark());
        }

        Calendar c = Calendar.getInstance();
        c.setTime(tiOSmsImme.getReferTime());
        tiOSmsImme.setDay(c.get(Calendar.DAY_OF_MONTH));
        tiOSmsImme.setMonth(c.get(Calendar.MONTH) + 1);

        // 以下的参数先给予默认值，倘若模板中可以取到，则优先取模板中的值
        tiOSmsImme.setSmsNetTag("0");
        tiOSmsImme.setChanId("B001");
        tiOSmsImme.setSmsTypeCode("20");
        tiOSmsImme.setSmsKindCode("06");
        tiOSmsImme.setSmsPriority(1000);

        TdSSendTpl tpl = DBUtil.querySingle(TdSSendTpl.class, new DBCondition(TdSSendTpl.Field.smsCode, info.getSms_code()));
        if (null != tpl)
        {
            if (CommonUtil.isNotEmpty(tpl.getSmsNetTag()))
            {
                tiOSmsImme.setSmsNetTag(tpl.getSmsNetTag());
            }

            if (CommonUtil.isNotEmpty(tpl.getChanId()))
            {
                tiOSmsImme.setChanId(tpl.getChanId());
            }

            if (CommonUtil.isNotEmpty(tpl.getSmsTypeCode()))
            {
                tiOSmsImme.setSmsTypeCode(tpl.getSmsTypeCode());
            }

            if (CommonUtil.isNotEmpty(tpl.getSmsKindCode()))
            {
                tiOSmsImme.setSmsKindCode(tpl.getSmsKindCode());
            }

            if (null != tpl.getForceStartTime())
            {
                tiOSmsImme.setForceStartTime(tpl.getForceStartTime());
            }

            if (null != tpl.getForceEndTime())
            {
                tiOSmsImme.setForceEndTime(tpl.getForceEndTime());
            }

            if (CommonUtil.isValid(tpl.getPriorityLevel()))
            {
                tiOSmsImme.setSmsPriority(tpl.getPriorityLevel());
            }
            
            //2013-07-23 liming15 新增字段validity_period
            //2013-08-06 liming15 取消新增
            //if(CommonUtil.isValid(tpl.getValidityPeriod()))
            //{
            //	tiOSmsImme.setValidityPeriod(tpl.getValidityPeriod());
            //}

        }

        return tiOSmsImme;
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

}
