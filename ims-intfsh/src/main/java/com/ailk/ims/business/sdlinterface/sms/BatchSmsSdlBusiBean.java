package com.ailk.ims.business.sdlinterface.sms;

import java.util.ArrayList;
import java.util.List;
import jef.database.Batch;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SBatchSmsInfo;
import com.ailk.openbilling.persistence.imssdl.entity.SCheckSms;
import com.ailk.openbilling.persistence.imssdl.entity.SCheckSmsDetail;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.jd.entity.TdSSendTpl;

/**
 * @Description:提供给账处的批量短信插入接口
 * @author wukl
 * @Date 2012-7-20
 */
public class BatchSmsSdlBusiBean extends BaseCancelableBusiBean
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
        SBatchSmsInfo batchSmsInfo = (SBatchSmsInfo) input[0];
        if (batchSmsInfo == null)
        {
            return null;
        }
        SCheckSms checkSms = batchSmsInfo.getChecksms();
        List<SCheckSmsDetail> detailList = batchSmsInfo.getListchecksmsdetail();

        if (checkSms != null)
        {
            // 批量审批总表有数据，则插入CheckSmsSend
            this.insertCheckSmsSend(checkSms);
        }

        // 插入批量明细表
        if (CommonUtil.isNotEmpty(detailList))
        {
            ArrayList<SmsSendInterfaceCheck> insertList = new ArrayList<SmsSendInterfaceCheck>();
            for (SCheckSmsDetail detail : detailList)
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

        return null;

    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return new Do_sdlResponse();
    }

    @Override
    public void destroy()
    {

    }

    /**
     * 组装短信明细的信息
     * 
     * @Author: wukl
     * @Date: 2012-11-1
     * @param detail
     * @return
     */
    private SmsSendInterfaceCheck getSmsSendInterfaceCheck(SCheckSmsDetail detail)
    {
        if (!CommonUtil.isValid(detail.getBillId()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "bill_id");
        }
        if (null == detail.getSendDate())
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "send_date");
        }
        if (!CommonUtil.isValid(detail.getSmsCode()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sms_code");
        }
        if (!CommonUtil.isValid(detail.getMessage()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "message");
        }
        
        SmsSendInterfaceCheck smsSendInterfaceCheck = new SmsSendInterfaceCheck();
        smsSendInterfaceCheck.setDoneCode(SmsSeqConfig.newDoneCode());// 取SEQ_DONE_CODE
        smsSendInterfaceCheck.setBillId(detail.getBillId());
        if (CommonUtil.isValid(detail.getPriorityLevel()))
        {
            smsSendInterfaceCheck.setPriorityLevel(detail.getPriorityLevel());
        }

        if (CommonUtil.isNotEmpty(detail.getMessage()))
        {
            smsSendInterfaceCheck.setMessage(detail.getMessage());
        }
        else
        {
            smsSendInterfaceCheck.setMessage("");
        }
        smsSendInterfaceCheck.setSendDate(detail.getSendDate());
        smsSendInterfaceCheck.setDoneDate(detail.getSendDate());
        smsSendInterfaceCheck.setRequestReport(0);
        TdSSendTpl tpl = DBUtil.querySingle(TdSSendTpl.class, new DBCondition(TdSSendTpl.Field.smsCode, detail.getSmsCode()));
        
        if (null != tpl)
        {
            if (CommonUtil.isNotEmpty(tpl.getSmsNetTag()))
            {
            	try {
            		smsSendInterfaceCheck.setRequestReport(Integer.valueOf(tpl.getSmsNetTag()));
				} catch (Exception e) {
					
				}
            }
        }
        smsSendInterfaceCheck.setSmsCode(detail.getSmsCode());
        smsSendInterfaceCheck.setBlockId(detail.getBlockId());// 需要跟总表的匹配，由外部传入
        return smsSendInterfaceCheck;
    }

    /**
     * 组装短信审核总表
     * 
     * @Author: wukl
     * @Date: 2012-11-1
     * @param checkSms
     */
    private void insertCheckSmsSend(SCheckSms checkSms)
    {
        if (!CommonUtil.isValid(checkSms.getModCode()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "mod_code");
        }
        if (null == checkSms.getSendDate())
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "send_date");
        }
        if (null == checkSms.getDoneDate())
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "done_date");
        }
        CheckSmsSend checkSmsSend = new CheckSmsSend();
        checkSmsSend.setBlockId(checkSms.getBlockId());// 跟明细的匹配，由外部传入
        checkSmsSend.setModCode(checkSms.getModCode());// 短信模板，由外部传入
        checkSmsSend.setSendDate(checkSms.getSendDate());// 插入时间
        checkSmsSend.setDoneDate(checkSms.getDoneDate());// 插入时间
        checkSmsSend.setSendMod((int) checkSms.getSendMod());
        if (CommonUtil.isValid(checkSms.getDataSource()))
        {
            checkSmsSend.setDataSource(checkSms.getDataSource());
        }
        checkSmsSend.setCheckStatus(0);// 默认:0--未审核
        checkSmsSend.setSendNum(0L);// 默认:0，后面同步时会更新这个值
        checkSmsSend.setExt1("10086");// 默认:10086
        DBUtil.insert(checkSmsSend);
    }

}
