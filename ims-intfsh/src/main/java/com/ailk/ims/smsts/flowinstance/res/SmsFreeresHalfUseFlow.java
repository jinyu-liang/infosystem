package com.ailk.ims.smsts.flowinstance.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.EnumSmsDefine;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.SmsBaseFlow;
import com.ailk.ims.smsts.helper.FreeresDayNotifyHelper;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.acct.entity.CaFreeresDayNotify;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * 1.从免资源表中取出科目是：6000111,6000681,6000671,6000621,6000301,6000692,6000191,6000001,6000131,6000561,6000341,6000851,6000011,6000581,
 * 6000582,6000583,6000584,6000585,6000661,6000501,6000311,6000091,6000591,6000881,6000231,6000151,6000081,6000771,6000021 的数据
 * 2.对免个科目数据，判断使用量是否小于总量的50%,如果小于，则给该用户发送短信。 3.扫描时间：每月的20号
 * 
 * @author gao
 */
public class SmsFreeresHalfUseFlow extends SmsBaseFlow
{
    private long templateId = EnumSmsDefine.IM_SH_FREERES_HALF_USE_CODE;
    private String itemSmsKeyWord = EnumCodeShDefine.IM_SH_FREERES_HALF_USE_SMS_FLOW;
    private String itemTimeKeyWord = EnumCodeShDefine.IM_SH_FREERES_HALF_USE_TIME_FLOW;
    private static final String orgMsgParam = "<6001>总量为<6002>,已使用<6003>";
    private CaFreeresDayNotify dayNotify;
    
    private SmsParam param;

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        @SuppressWarnings("unchecked")
        List<CaFreeres> list = (List<CaFreeres>) obj;
        if(CommonUtil.isEmpty(list)){
            return null;
        }
        list=packingRes(list);
        StringBuffer sb = new StringBuffer();
        Long userId = null;
        for (CaFreeres res : list)
        {
            if (isHafUse(res))
            {
                sb.append(buildMessageParam(res, userId));
            }
        }
        if (sb.length() == 0)
        {
            return null;
        }
        userId = list.get(0).getResourceId();
        String msg = getMessage(CommonUtilSh.removeLastFlag(sb.toString(), ';'));
        // 要插的短信表
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();

        if (CommonUtil.isEmpty(msg))
        {

            imsLogger.info("************************* 短信模板内容为空,不发送信息!");
        }
        else
        {
            IMSUtil.setUserRouterParam(userId);
            String phoneId = getSmsCmp().loadPhoneIdByUserId(userId);
            if(null==phoneId){
                imsLogger.info("用户[",userId,"]找不到对应的手机号码,不发送短信");
                
            }else{
            // 返回短信审核明细表
            smsList.add(createSmsSendInterfaceCheck(msg, phoneId, templateId, blockId));
            }
        }
        return smsList;
    }
    private List<CaFreeres> packingRes(List<CaFreeres> list){
        Map<Long,CaFreeres> map=new HashMap<Long, CaFreeres>();
        for(CaFreeres res:list){
            Long itemCode=res.getItemCode();
           CaFreeres temp=map.get(itemCode);
           if(null==temp){
               temp=res;
           }else{
               temp.setUnit(res.getUnit()+temp.getUnit());
               temp.setRealUnit(res.getRealUnit()+temp.getRealUnit());
           }
           map.put(itemCode, temp);
        }
        List<CaFreeres> result=new ArrayList<CaFreeres>();
        for(CaFreeres temp:map.values()){
            result.add(temp);
        }
        return result;
        
    }
    

    @Override
    protected Long getTemplateId()
    {
        return templateId;
    }


    @Override
    protected String[] getItemsKeyWords()
    {
        return new String[] { itemSmsKeyWord, itemTimeKeyWord };
    }

    @Override
    protected void beforeDo(SmsParam param)
    {
        this.param=param;
        this.dayNotify = param.getDayNotify();
    }
    
    /**
     * 允许被重写
     * 
     * @param items
     * @param freeresTableName
     * @return
     */
    @Override
    protected String buildSql(String items, String freeresTableName)
    {
        return new StringBuffer(
                "select  t.RESOURCE_ID as resourceId,t.unit as unit,t.real_unit as realUnit,")
                .append(" t.ITEM_CODE as itemCode,t.ITEM_NAME as itemName, ")
                .append(" t.MEASURE_ID as measureId ")
                .append(" from " + freeresTableName + " t")// 表名
                .append(buildCondition(items))// 条件
                .append(buildKeyWordCondition())
                .append(" order by t.RESOURCE_ID")// 排序
                .toString();

    }
    private String buildKeyWordCondition(){
        if(param.getKeywordVal()>0){
            return CommonUtil.concat(" and t.user_id >=",param.getKeywordVal()," ");
        }
        return "";
    }

    private static boolean isHafUse(CaFreeres res)
    {
        return (0.0 + res.getRealUnit()) / res.getUnit() < 0.5;
    }

    private String buildMessageParam(CaFreeres res, Long userId)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", res.getItemName());
        if (isSmsItemCode(String.valueOf(res.getItemCode())))
        {
            map.put("6002", SmsUtil.measureChangeToMinute(EnumCodeShDefine.MEASURE_ID_SEC, res.getUnit()) + "分钟");
            map.put("6003", SmsUtil.measureChangeToMinute(EnumCodeShDefine.MEASURE_ID_SEC, res.getRealUnit()) + "分钟");
        }
        else
        {
            map.put("6002", res.getUnit() + "条");
            map.put("6003", res.getRealUnit() + "条");
        }
        return SmsUtil.instanceMessage(orgMsgParam, map) + ";";

    }

    /**
     * 套餐使用提醒：截至<6001>日<6002>时<6003>分，您本月套餐内<6010>,使用量不足总量的50%。 请您放心使用，不要让资源浪费奥！中国移动
     * 
     * @return
     */
    private String getMessage(String msgParam)
    {
        int day = FreeresDayNotifyHelper.getDay(dayNotify);// cal.get(Calendar.DAY_OF_MONTH);
        int hour = FreeresDayNotifyHelper.getHour(dayNotify);// cal.get(Calendar.HOUR_OF_DAY);
        int minute = FreeresDayNotifyHelper.getMinute(dayNotify);// cal.get(Calendar.MINUTE);
        Map<String, String> map = new HashMap<String, String>();

        map.put("6001", CommonUtil.int2String(day));
        map.put("6002", CommonUtil.int2String(hour));
        map.put("6003", CommonUtil.int2String(minute));
        map.put("6010", msgParam);

        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
    }

    private SmsSendInterfaceCheck createSmsSendInterfaceCheck(String template, String phoneId, Long templateId, Long blockId)
    {
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

    private boolean isSmsItemCode(String itemCode)
    {
        return getItemsCodeByItemKeyWord(itemTimeKeyWord).contains(itemCode);
    }
    @Override
    public void commitOther()
    {
        
    }


}
