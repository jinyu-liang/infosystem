package com.ailk.ims.smsts.flowinstance.res;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 定期发送WLAN剩余提醒:对于订购了免费资源的用户，定时发送短信提醒
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author zenglu bus_code=7506
 * @Date 2012-07-31
 */
public class WlanFlowRes extends SmsBaseFlow
{
    private static final long templateId = EnumSmsDefine.IM_SH_WLAN_FLOW_CODE;
    private static final String itemKeyword = EnumCodeShDefine.IM_SH_WLAN_FLOW;
    
    private SmsParam param;

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
     * 尊敬的客户，您本月共有<6001>小时，截至<6002>日，剩余上网时长不足<6003>分钟。 仅供参考，具体以月结账单为准。为节约您的上网费用，建议您发送WLANTC到10086选择合适的WLAN套餐。
     * 
     * @return
     */
    private String getMessage(int measureId, long amount, long used)
    {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", SmsUtil.measureChangeToHuor(EnumCodeShDefine.MEASURE_ID_SEC, amount));
        map.put("6002", CommonUtil.int2String(day));
        map.put("6003", SmsUtil.measureChangeToMinute(EnumCodeShDefine.MEASURE_ID_SEC, amount - used));

        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
    }

    protected boolean hasOrderProduct(Long userId)
    {

        // 判断用户是否订购了WLAN流量定期提醒的产品,有订购才提醒
        // 有订购才提醒
        IMSUtil.setUserRouterParam(userId);
        DBCondition[] conds = new DBCondition[] { new DBCondition(CoProd.Field.objectId, userId),
                new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
                new DBCondition(CoProd.Field.productOfferingId, EnumSmsDefine.SMS_NOTIFY_WLAN_OFFERID),
                new DBCondition(CoProd.Field.validDate ,  getCurrentDate(),Operator.LESS),
                new DBCondition(CoProd.Field.expireDate, getCurrentDate(),Operator.GREAT_EQUALS)
        };
        return DBUtil.queryCount(CoProd.class, conds) > 0;
    }

    protected List<SmsSendInterfaceCheck> buildMessage(CaFreeres res, String phoneId, Long blockId)
    {
        // 获取短信
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

    @Override
    protected String[] getItemsKeyWords()
    {

        return new String[] { itemKeyword };
    }

    protected boolean userAllow(CaFreeres res)
    {

        return DBUtil.queryCount(CmResource.class, getUserQueryConds(res.getResourceId())) > 0;
    }

    private DBCondition[] getUserQueryConds(Long userId)
    {
        DBCondition[] conds = new DBCondition[5];
        conds[0] = new DBCondition(CmResource.Field.activeDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[1] = new DBCondition(CmResource.Field.billingType, 1);
        conds[2] = new DBCondition(CmResource.Field.validDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[3] = new DBCondition(CmResource.Field.expireDate, getCurrentDate(), Operator.GREAT);
        conds[4] = new DBCondition(CmResource.Field.resourceId, userId);
        return conds;
    }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        @SuppressWarnings("unchecked")
        List<CaFreeres> list = (List<CaFreeres>) obj;
        CaFreeres res =list.get(0);
        if (hasRes(res) && hasOrderProduct(res.getResourceId()) && userAllow(res))
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

    private boolean hasRes(CaFreeres res)
    {
        return res.getUnit() > 0;
    }
     @Override
    protected String buildCondition(String items)
    {
         
        String condition= super.buildCondition(items);
        if(param.getKeywordVal()>0){
            condition=CommonUtil.concat(condition," and t.RESOURCE_ID>",param.getKeywordVal());
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
    @Override
    protected void beforeDo(SmsParam param)
    {
         this.param=param;
    }

}
