package com.ailk.ims.smsts.flowinstance.res;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.PackingUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: WLAN 沉默提醒(未使用提醒),科目包括CMCC科目和CMCC-EDU科目以及 CMCC-AUTO科目，对于使用量为零的用户进行提醒 需求单号: #65216
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author gaoqc5 busi_code=7501
 * @Date 2012-11-10
 */
public class WlanCmccFlowRes extends GroupUserItemSmsBaseFlow
{
    private static final long templateId = EnumSmsDefine.IM_SH_WLAN_CMCC_CODE;

    private static final String itemIdCMCCKeyWord = EnumCodeShDefine.IM_SH_WLAN_CMCC;
    private static final String itemIdEDUKeyWord = EnumCodeShDefine.IM_SH_WLAN_EDU;
    private static final String itemIdAutoKeyWord = EnumCodeShDefine.IM_SH_WLAN_AUTO;
    private SmsParam param;
    protected List<SmsSendInterfaceCheck> buildMessage(List<CaFreeres> list, String phoneId, Long blockId)
    {
      
        
        String message = getMessage(list);
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
     * 尊敬的客户，<6001>您可发送WLAN至10658015或登录上海移动网站查询WLAN热点。中国移动
     * 
     * @return
     */
    private String getMessage(List<CaFreeres> list)
    {
        List<String>items=new ArrayList<String>();
        for(String keyword:getItemsKeyWords()){
            items.add(getItemsCodeByItemKeyWord(keyword));
        }
        if(list.size()>1){
        //合并相同的
        list=PackingUtil.mergeFreeResByItems(list,items);
        }
        
        StringBuffer sb = new StringBuffer();
        for (CaFreeres res : list)
        {
            sb.append(getDetailItemMessage(res.getItemCode(), res.getRealUnit(), res.getUnit()) + ";");
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", CommonUtilSh.removeLastFlag(sb.toString(), ';'));
        return SmsUtil.instanceSmsMessage(templateId, map);
    }

    @Override
    protected String[] getItemsKeyWords()
    {

        return new String[] { itemIdCMCCKeyWord, itemIdEDUKeyWord, itemIdAutoKeyWord };
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

        list = getAllowCaFreeres(list);
        if (CommonUtil.isEmpty(list))
        {
            return null;
        }
        String phoneId = getSmsCmp().loadPhoneIdByUserId(list.get(0).getResourceId());
        if(CommonUtil.isEmpty(phoneId)){
            imsLogger.debug("手机号码为空，不发送短信");
            return null;
        }
        return buildMessage(list, phoneId, blockId);

    }

    private List<CaFreeres> getAllowCaFreeres(List<CaFreeres> reses)
    {
        List<CaFreeres> list = new ArrayList<CaFreeres>();
        for (CaFreeres res : reses)
        {
            if (userAllow(res))
            {
                list.add(res);
            }
        }
        return list;
    }

    @Override
    protected String buildHavingCondition()
    {
        return "  having sum(unit)>0 and sum(real_unit)=0 ";
    }
     @Override
    protected String buildOtherCondition()
    {
         if(param.getKeywordVal()>0){
             return CommonUtil.concat(" and t.RESOURCE_ID >=",param.getKeywordVal()," ");
         }
         return "";
    }
     
    @Override
    protected Long getTemplateId()
    {

        return templateId;
    }


    /**
     * WLAN提醒：尊敬的客户，【您本月WLAN（CMCC）上网时长共有xx小时，截至x日还有xx小时。】 【您本月校园WLAN（CMCC-EDU）上网时长共有xx小时，截至x日还有xx小时。】
     * 【您本月WLAN自动认证（CMCC-AUTO）上网时长共有xxM，截至x日还有xxM。】 您可发送WLAN至10658015或登录上海移动网站查询WLAN热点。中国移动
     * （注，若用户有该免费资源，则短信内容包括该项内容，如果用户无该免费资源，则短信内容不包括该项内容）
     * 
     * @param itemCode
     * @param used
     * @param total
     * @return
     */
    private String getDetailItemMessage(Long itemCode, long used, long total)
    {
        int day = DateUtil.getDateField(new Date(), Calendar.DAY_OF_MONTH);
        if (getItemsCodeByItemKeyWord(itemIdAutoKeyWord).contains(String.valueOf(itemCode)))
        {
            String left = SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, total - used);
            String totalStr = SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, total);
            return CommonUtil.concat("您本月WLAN自动认证（CMCC-AUTO）上网流量共有", totalStr, "M，截至", day, "日还有", left, "M");
        }
        String orgMessage = "您本月WLAN（CMCC）上网时长共有<6001>小时，截至<6002>日还有<6003>小时";
        if (getItemsCodeByItemKeyWord(itemIdEDUKeyWord).contains(String.valueOf(itemCode)))
        {
            orgMessage = "您本月校园WLAN（CMCC-EDU）上网时长共有<6001>小时，截至<6002>日还有<6003>小时";
        }
        String left = SmsUtil.measureChangeToHuor(EnumCodeShDefine.MEASURE_ID_SEC, total - used);
        String totalStr = SmsUtil.measureChangeToHuor(EnumCodeShDefine.MEASURE_ID_SEC, total);
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", totalStr);
        map.put("6002", String.valueOf(day));
        map.put("6003", left);
        return SmsUtil.instanceMessage(orgMessage, map);
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
