package com.ailk.ims.smsts.flowinstance.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.EnumSmsDefine;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.SmsBaseFlow;
import com.ailk.ims.smsts.helper.FreeresDayNotifyHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 移动数据流量免费资源提醒(未使用提醒)
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author gaoqc5 busi_code=7504
 * @Date 2012-11-07
 * @Date 2012-11-1 提醒规条件用户有免资源而且流量使用为零
 */
public class MobileDataFlowRes extends SmsBaseFlow
{
    private static final long templateId = EnumSmsDefine.IM_SH_MOBLIE_DATA_FLOW_CODE;
    
    private static final String itemKeyWord = EnumCodeShDefine.IM_SH_MOBLIE_DATA_FLOW;
    
    private CaFreeresDayNotify dayNotify;
    
    private SmsParam param;

    protected boolean noOrderProduct(Long userId)
    {
        // 有订购不提醒
        IMSUtil.setUserRouterParam(userId);

        return DBUtil.queryCount(CoProd.class, getOrderProduct(userId)) == 0;
    }

    private DBCondition[] getOrderProduct(Long userId)
    {
        List<DBCondition> conds = new ArrayList<DBCondition>();
        conds.add(new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        conds.add(new DBCondition(CoProd.Field.productOfferingId, EnumSmsDefine.SMS_NOTIFY_MOBLIE_DATA_OFFERID));
        conds.add(new DBCondition(CoProd.Field.validDate, getCurrentDate(), Operator.LESS));
        conds.add(new DBCondition(CoProd.Field.expireDate, getCurrentDate(), Operator.GREAT));
        if (null != userId)
        {
            conds.add(new DBCondition(CoProd.Field.objectId, userId));
        }
        return (DBCondition[]) conds.toArray(new DBCondition[conds.size()]);

    }

    protected List<SmsSendInterfaceCheck> buildMessage(CaFreeres res, String phoneId, Long blockId)
    {
        String message = getMessage(EnumCodeDefine.MEASURE_ID_BYTE, res.getUnit(), res.getRealUnit());
        // 要插的短信表
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();
        if (CommonUtil.isEmpty(message))
        {

            imsLogger.info("************************* 短信模板内容为空,不发送信息!");
        }
        else
        {
            // 返回短信审核明细表
            smsList.add(createSmsSendInterfaceCheck(message, phoneId, templateId, blockId));
        }
        return smsList;
    }

    private SmsSendInterfaceCheck createSmsSendInterfaceCheck(String template, String phoneId, Long templateId, Long blockId)
    {
        // 返回短信审核明细表
        SmsSendInterfaceCheck smsSendInterfaceCheck = new SmsSendInterfaceCheck();
        smsSendInterfaceCheck.setMessage(template);// 短信模板
        smsSendInterfaceCheck.setBillId(phoneId);
        smsSendInterfaceCheck.setSmsCode(templateId);// 短信模板Id
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
     * 流量提醒：截止<6001>日<6002>时<6003>分, 您本月移动数据套餐流量共有<6010>M,还剩余<6011>M尚未使用。
     * 回复QXCMTX即可退订此项提醒服务。 为避免浪费，推荐您手机访问沪 联网
     * wap.sh.monternet.com 查余额查流量、看新闻下应用
     * 
     * @return
     */
    private String getMessage(int measureId, long amount, long used)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", CommonUtil.int2String(FreeresDayNotifyHelper.getDay(dayNotify)));
        map.put("6002", CommonUtil.int2String(FreeresDayNotifyHelper.getHour(dayNotify)));
        map.put("6003", CommonUtil.int2String(FreeresDayNotifyHelper.getMinute(dayNotify)));
        map.put("6010", SmsUtil.measureChangeToM(measureId, amount));
        map.put("6011", SmsUtil.measureChangeToM(measureId, amount - used));
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
    }

    @Override
    protected void beforeDo(SmsParam param)
    {
        this.dayNotify = param.getDayNotify();
        this.param=param;
    }

    @Override
    protected String[] getItemsKeyWords()
    {
        return new String[] { itemKeyWord };
    }

    protected boolean userAllow(CaFreeres res)
    {

        return DBUtil.queryCount(CmResource.class, getUserQueryConds(res.getResourceId())) > 0;
    }

    private DBCondition[] getUserQueryConds(Long userId)
    {
        DBCondition[] conds = new DBCondition[6];
        conds[0] = new DBCondition(CmResource.Field.resourceId, userId);
        conds[1] = new DBCondition(CmResource.Field.billingType, 1);
        conds[2] = new DBCondition(CmResource.Field.brandId, 2);//动感地带品牌的用户
        conds[3] = new DBCondition(CmResource.Field.expireDate, getCurrentDate(), Operator.GREAT);
        conds[4] = new DBCondition(CmResource.Field.activeDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[5] = new DBCondition(CmResource.Field.validDate, getCurrentDate(), Operator.LESS_EQUALS);
        return conds;
    }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        @SuppressWarnings("unchecked")
        List<CaFreeres> list = (List<CaFreeres>) obj;
        if (CommonUtil.isEmpty(list))
        {
            return null;
        }
        CaFreeres res = list.get(0);
        if (noOrderProduct(res.getResourceId()) && userAllow(res))
        {
            String phoneId = getSmsCmp().loadPhoneIdByUserId(res.getResourceId());
            if (null == phoneId)
            {
                imsLogger.info("用户[", res.getResourceId(), "]找不到对应的手机号码,不发送短信");
            }
            else
            {
                return buildMessage(res, phoneId, blockId);
            }
        }
        return null;

    }

    @Override
    protected String buildHavingCondition()
    {
        return "  having sum(real_unit)=0 and sum(unit)>0";
    }
    
    @Override
    protected String buildCondition(String items)
    {
         
        String condition= super.buildCondition(items);
        //判断是否有上次中断
        if(param.getKeywordVal()>0){
            condition=CommonUtil.concat( condition,"  and  t.RESOURCE_ID >=",param.getKeywordVal()," ");
        }
        return condition;
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

}
