package com.ailk.imssh.common.flow.config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.BusiRecordUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.cust.entity.CaNotifyTaskSplit11;
import com.ailk.openbilling.persistence.cust.entity.CaNotifyTaskSplit11His;
import com.ailk.openbilling.persistence.jd.entity.TdSSendTpl;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;

/**
 * @Description:群成员提醒-配置接口类
 * @author liming15
 * @Date 2013-5-31
 */
public final class SendToMemberConfig implements ISendToMemberConfig
{
	private static final SendToMemberConfig SEND_TO_MEMBER_CONFIG = new SendToMemberConfig();
	
	public static SendToMemberConfig getInstance()
	{
		return SEND_TO_MEMBER_CONFIG;
	}
	
	private SendToMemberConfig()
	{
		
	}
	
	@Override
	public List<CaAccountRv> getGroupMember(DataObject dataObject,
			Date expireDate) {
		// TODO Auto-generated method stub
		long acctId = ((CaNotifyTaskSplit11)dataObject).getAcctId();
    	List<CaAccountRv> list = DBUtil.query(CaAccountRv.class, new DBCondition(CaAccountRv.Field.acctId,acctId),new DBCondition(CaAccountRv.Field.expireDate,expireDate,Operator.GREAT));
    	return list;
	}

	@Override
	public String getTiOSmsImmeNamePrefix() {
		// TODO Auto-generated method stub
		return "JD.TI_O_SMS_IMME_";
	}

	@Override
	public String getHisTableName() {
		// TODO Auto-generated method stub
		return "AID.CA_NOTIFY_TASK_SPLIT_11_HIS";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
        return "AID.CA_NOTIFY_TASK_SPLIT_11";
	}

	@Override
	public Query newTableQuery() {
		// TODO Auto-generated method stub
		Query<CaNotifyTaskSplit11> query = QueryBuilder.create(CaNotifyTaskSplit11.class);
        // 按照seq_id从小到大排序读取
        query.addOrderBy(true, CaNotifyTaskSplit11.Field.seqId);
        return query;
	}

	@Override
	public int getSubTableCount() {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public DataObject getDeleteDataObject(DataObject dataObject) {
		// TODO Auto-generated method stub
		Long seqId = ((CaNotifyTaskSplit11) dataObject).getSeqId();
        CaNotifyTaskSplit11 caNotifyTaskSplit = new CaNotifyTaskSplit11();
        caNotifyTaskSplit.getQuery().addCondition(CaNotifyTaskSplit11.Field.seqId,seqId);
        return caNotifyTaskSplit;
	}

	@Override
	public DataObject convertToHis(DataObject dataObject, Date dealDate) {
		// TODO Auto-generated method stub
		CaNotifyTaskSplit11 caNotifyTaskSplit = (CaNotifyTaskSplit11) dataObject;
    	CaNotifyTaskSplit11His caNotifyTaskSplitHis = new CaNotifyTaskSplit11His();
    	
    	caNotifyTaskSplitHis.setSeqId(caNotifyTaskSplit.getSeqId());
    	caNotifyTaskSplitHis.setAcctId(caNotifyTaskSplit.getAcctId());
    	caNotifyTaskSplitHis.setResourceId(caNotifyTaskSplit.getResourceId());
    	caNotifyTaskSplitHis.setNotificationId(caNotifyTaskSplit.getNotificationId());
    	caNotifyTaskSplitHis.setActionId(caNotifyTaskSplit.getActionId());
    	caNotifyTaskSplitHis.setActionType(caNotifyTaskSplit.getActionType());
    	caNotifyTaskSplitHis.setCreateDate(dealDate);
    	caNotifyTaskSplitHis.setTemplateId(caNotifyTaskSplit.getTemplateId());
    	caNotifyTaskSplitHis.setNotifyContent(caNotifyTaskSplit.getNotifyContent());
    	caNotifyTaskSplitHis.setAmount(caNotifyTaskSplit.getAmount());
    	caNotifyTaskSplitHis.setCredit(caNotifyTaskSplit.getCredit());
    	caNotifyTaskSplitHis.setAlarmSourceType(caNotifyTaskSplit.getAlarmSourceType());
    	caNotifyTaskSplitHis.setStatus(Long.valueOf(1));
    	
        return caNotifyTaskSplitHis;
	}

	@Override
	public DataObject convertToTiOSmsImmeObject(DataObject caNotifyObject,
			String phoneId) {
		// TODO Auto-generated method stub
TiOSmsImme tiOSmsImme = new TiOSmsImme();
    	
    	long noticeId = DBUtil.getSequence(TiOSmsImme.class);
        String dateStr = DateUtil.formatDate(DateUtil.currentDate(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
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
        tiOSmsImme.setRecvObjectType("00");
        tiOSmsImme.setRecvObject(phoneId);
        tiOSmsImme.setRecvId(0L);
        tiOSmsImme.setNoticeContentType("0");

        //短信内容从提醒触发表中直接取得 
        String instanceMessage = ((CaNotifyTaskSplit11)caNotifyObject).getNotifyContent();
        if (CommonUtil.isEmpty(instanceMessage))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "notice_content");
        }
        tiOSmsImme.setNoticeContent(instanceMessage);
        tiOSmsImme.setReferedCount(0);
        tiOSmsImme.setForceReferCount(1);
        tiOSmsImme.setForceObject("10086");
        tiOSmsImme.setReferTime(DateUtil.currentDate());
        tiOSmsImme.setDealTime(DateUtil.currentDate());
        tiOSmsImme.setDealState("15");
        tiOSmsImme.setDoneCode(BusiRecordUtil.getReceiveDoneCode(DateUtil.currentDate()));
        tiOSmsImme.setSmsCode(((CaNotifyTaskSplit11)caNotifyObject).getTemplateId());
        //if (CommonUtil.isNotEmpty(info.getRemark()))
        //{
        //    tiOSmsImme.setRemark(info.getRemark());
        //}
       

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

        TdSSendTpl tpl = DBUtil.querySingle(TdSSendTpl.class, new DBCondition(TdSSendTpl.Field.smsCode, ((CaNotifyTaskSplit11)caNotifyObject).getTemplateId()));
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
	
}