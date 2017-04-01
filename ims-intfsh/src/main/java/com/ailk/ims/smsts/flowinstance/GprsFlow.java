package com.ailk.ims.smsts.flowinstance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.common.wrapper.IntRange;
import jef.database.AutoCloseIterator;
import jef.database.query.Query;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.EnumSmsDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.BaseFlow;
import com.ailk.ims.smsts.helper.FreeresDayNotifyHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 定期发送GPRS流量提醒
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author zenglu
 * @Date 2012-07-31
 * busi_code=7505
 */
public class GprsFlow extends BaseFlow
{
	private static final long templateId = EnumSmsDefine.IM_SH_GPRS_FLOW_CODE;
	private CaFreeresDayNotify dayNotify;
	private SmsParam param;
	private  final static String tempTableName="JD.IMS_GPRS_NOTICE";//需要发送短信的表名
    public DBCondition[] getQueryConds()
    {
        return  new DBCondition[0];
    }
    

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        CaFreeres rs = (CaFreeres) obj;
        Long userId = rs.getResourceId();

        //获取短信
        String template = getMessage(rs.getRealUnit());
        
        
        // 要插的短信表
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();

        if(CommonUtil.isEmpty(template)){
            imsLogger.debug("************************* 短信模板内容为空,不发送信息!");
        }else{
            IMSUtil.setAcctRouterParam(rs.getAcctId());
            String phoneId =getSmsCmp().loadPhoneIdByUserId(userId);
            if(CommonUtil.isEmpty(phoneId)){
                imsLogger.info("用户[",userId,"]没对应的手机号码，不发送短信");
                return null;
            }
            
            // 返回短信审核明细表
            smsList.add(createSmsSendInterfaceCheck(template, phoneId, templateId, blockId));
        }
        
        return smsList;
    }
    
    private SmsSendInterfaceCheck createSmsSendInterfaceCheck(String template,String phoneId,Long templateId,Long blockId){
        // 返回短信审核明细表
        SmsSendInterfaceCheck smsSendInterfaceCheck = new SmsSendInterfaceCheck();
        smsSendInterfaceCheck.setMessage(template);//短信模板
        smsSendInterfaceCheck.setBillId(phoneId);
        smsSendInterfaceCheck.setSmsCode(templateId);//短信模板Id
        smsSendInterfaceCheck.setBlockId(blockId);// 批次号
        smsSendInterfaceCheck.setDoneCode(SmsSeqConfig.newDoneCode());// 新的doneCode
        smsSendInterfaceCheck.setSendDate(DateUtil.currentDate());// 发送时间
        smsSendInterfaceCheck.setPriorityLevel(0);// 优先级
        smsSendInterfaceCheck.setRequestReport(SmsHelper.getSendTpl(templateId));// 是否回执，0：不需要，1：需要
        smsSendInterfaceCheck.setSrcAddr("10086");// 服务号码
        smsSendInterfaceCheck.setDealDate(getCurrentDate());
        smsSendInterfaceCheck.setDoneDate(getCurrentDate());
        return smsSendInterfaceCheck;
        
    }
    /**
      *流量提醒：截至<6001>日0时0分，您本月已使用的上网数据流量有<6002>M。
      * 本提醒免费，退订请回复QXLLTX。回复666可订购2元/5M流量叠加包，获得更多优惠流量！
     * @return
     */
    private String getMessage(long addAmout)
    {
        
    	Map<String,String> map=new HashMap<String,String>();
    	map.put("6001", CommonUtil.int2String(FreeresDayNotifyHelper.getDay(dayNotify)));
    	map.put("6002",SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, addAmout));//CommonUtil.long2String(amount));
    	return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
    }

    @Override
    protected void beforeDo(SmsParam param)
    {
        this.dayNotify=param.getDayNotify();
        this.param=param;
    }


    @Override
    protected Long getTemplateId()
    {
        return templateId;
    }


    @Override
    public void commitOther()
    {
        
    }
    protected  AutoCloseIterator<?>  getIterator(Query<?> query,  IntRange range, String subTableName ) throws IMSException, SQLException{
            return DBUtil.getDBClient().createNativeQuery(buildSql(), CaFreeres.class).getResultIterator();
            //转换的另一种实现
//            NativeQuery<CmResource> q=DBUtil.getDBClient().createNativeQuery(buildSql(subTableName,freeresTableName), CmResource.class);
 //           q.setStrategies(PopulateStrategy.SKIP_COLUMN_ANNOTATION);
//            return q.getResultIterator();
    }
    /**
     * 
     * @param items
     * @param freeresTableName
     * @return
     */
    private   String buildSql()
    {
        String  modVal=String.valueOf(dayNotify.getSubTableIndex());
        return new StringBuffer()
        .append(" select t.user_id  as RESOURCE_ID,  ")
        .append(" t.acct_id as ACCT_ID, ")
        .append(" t.acct_id as ACCT_ID, ")
        .append(" t.accumulate as REAL_UNIT  ")
        .append("  from "+tempTableName+" t ")
        .append("where MOD(t.acct_id,"+getThreadCount())
//        .append("where MOD(t.acct_id,0)=0")
        .append(")=")
        .append(modVal)
        .append(buildKeyWordCondition())
        .append(" order by t.user_id ")
        .toString();

    }
    private String buildKeyWordCondition(){
        if(param.getKeywordVal()>0){
            return CommonUtil.concat(" and t.user_id >=",param.getKeywordVal()," ");
        }
        return "";
    }

    private int getThreadCount()
    {
        try
        {
            return SysUtil.getInt(EnumCodeShDefine.IMS_SH_GPRS_THREAD_COUNT);
        }
        catch (Exception e)
        {
            imsLogger.error("取线程个数异常，默认启动50个线程");
        }
        return 50;
    }
    



}
