package com.ailk.ims.smsts.flowinstance.res;

import java.util.ArrayList;
import java.util.Collection;
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
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PackingUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.openbilling.persistence.acct.entity.CaFreeres;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 低流量提醒: 非GPRS套餐，提醒的阀值是：6M，而且总量大于30M才触发提醒 GPRS套餐，提醒的阀值是：已使用量的20%
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author gaoqc5 busi_code=7512
 * @Date 2012-07-31
 */
public class GprsLowFlowRes extends GroupUserItemSmsBaseFlow
{
    private static final long templateDataId = EnumSmsDefine.IM_SH_GPRS_LOW_FLOW_DATA_CODE;
    private static final long templateGprsId = EnumSmsDefine.IM_SH_GPRS_LOW_FLOW__GPRS_CODE;
    
    private static final String itemDataKeyword = EnumCodeShDefine.IM_SH_GPRS_LOW__DATA_FLOW;// 6000031
    private static final String itemGprsKeyword = EnumCodeShDefine.IM_SH_GPRS_LOW_GPRS_FLOW;// 6000611,6000141,6000321,6000211,6000201,6000451,6000841

    private long relTemplateId = EnumSmsDefine.IM_SH_GPRS_LOW_FLOW_DATA_CODE;
    
    private static final long minAmout = 31457280l;// 30M
    private static final long useTap = 6291456l;// 6M
    private SmsParam smsParam;

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {
        @SuppressWarnings("unchecked")
        List<CaFreeres> list = (List<CaFreeres>) obj;
        if (CommonUtil.isEmpty(list))
        {// 如果是空 ,返回
            return null;
        }
        Long userId = list.get(0).getResourceId();

        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();
        smsList.addAll(dealDataResource(list, userId, blockId));
        smsList.addAll(dealOtherGprsesource(list, userId, blockId));
        return smsList;
    }

    private List<SmsSendInterfaceCheck> dealOtherGprsesource(List<CaFreeres> list, Long userId, Long blockId)
    {
      
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();
        StringBuffer otherSb = new StringBuffer();
        for (CaFreeres res : packagingOtherCaFreeres(list))
        {
            if (isLowUse(res, false))
            {// 符合低流量条件
                otherSb.append(buildOtherParam(res));

            }
        }
        if (otherSb.length() > 0)
        {
            if (otherSb.charAt(otherSb.length() - 1) == ';')
            {
                otherSb.deleteCharAt(otherSb.length() - 1);
            }
            // 为了提高性能，订购产品的判断放到后面判断
            if (hasOrderProduct(userId))
            {
                imsLogger.info("用户[", userId, "]订购了产品，不提醒!");
                return smsList;
            }
            String phoneId = getSmsCmp().loadPhoneIdByUserId(userId);
            if (null == phoneId)
            {
                imsLogger.info("用户[", userId, "]找不到对应的手机号码,不发送短信");
            }
            else
            {
                relTemplateId = templateGprsId;
                blockId=smsParam.getTemplateIdAndBlockIdMap().get(templateGprsId);
                smsList.add(createSmsSendInterfaceCheck(builldOtherGprsMessage(otherSb.toString()).toString(), phoneId,
                        templateGprsId, blockId));
            }
        }
        return smsList;
    }
    
    private String buildOtherParam(CaFreeres res)
    {

        Map<String, String> param = new HashMap<String, String>();
        param.put("101", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, res.getUnit()));
        param.put("102", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, res.getRealUnit()));
        return SmsUtil.instanceMessage(getMessageParam(res), param) + ";";
    }

    /**
     *     /**
     * 低流量提醒：截至19日0时，您本月移动数据套餐流量共有<6004>M，仅使用<6005>M（1M=1024K）。 本提醒免费，退订请回复QXLLTX。为避免浪费，推荐您手机访问沪联网 wap.sh.monternet.com
     * 查余额查流量、看新闻下应用
     * 低流量提醒：截至19日0时，您本月<6004>。本提醒免费， 退订请回复QXLLTX。为避免浪费，推荐您手机访问沪联网 wap.sh.monternet.com 查余额查流量、看新闻下应用
     * 
     * @param message
     * @return
     */
    private String builldOtherGprsMessage(String message)
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("6004", message);
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateGprsId), param);
    }

    private Collection<CaFreeres> packagingOtherCaFreeres(List<CaFreeres> orgiList)
    {
        Map<String, CaFreeres> groupByItemRes = new HashMap<String, CaFreeres>();
        String otherItems = getItemsCodeByItemKeyWord(itemGprsKeyword);
        for (CaFreeres res : orgiList)
        {
            String currentItemCode = String.valueOf(res.getItemCode());
            if (!otherItems.contains(currentItemCode))
            {// 不是同一品牌的，过虑掉
                continue;
            }
            CaFreeres packe = groupByItemRes.get(currentItemCode);
            if (null == packe)
            {
                groupByItemRes.put(currentItemCode, res);
            }
            else
            {
                packe.setUnit(packe.getUnit() + res.getUnit());
                packe.setRealUnit(packe.getRealUnit() + res.getRealUnit());

            }
        }
        return groupByItemRes.values();

    }

    /**
     * 移动数据流量 处理
     * 
     * @param list
     * @param phoneId
     * @param blockId
     * @return
     */
    private List<SmsSendInterfaceCheck> dealDataResource(List<CaFreeres> list, Long userId, Long blockId)
    {
        
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();
        CaFreeres dataRes = PackingUtil.mergeFreeResByItems(list, getItemsCodeByItemKeyWord(itemDataKeyword));
        if (null != dataRes && isLowUse(dataRes, true))
        {
            // 为了提高性能，订购产品的判断放到后面判断
            if (hasOrderProduct(userId))
            {
                imsLogger.info("用户[", userId, "]订购了产品，不提醒!");
                return smsList;
            }
            String message = buildDataMessage(dataRes);
            if (CommonUtil.isEmpty(message))
            {

                imsLogger.info("************************* 短信模板[" + templateDataId + "[内容为空,不发送信息!");
            }
            String phoneId = getSmsCmp().loadPhoneIdByUserId(userId);
            if (null == phoneId)
            {
                imsLogger.info("用户[", userId, "]找不到对应的手机号码,不发送短信");
            }
            else
            {
                relTemplateId = templateDataId;
                blockId=smsParam.getTemplateIdAndBlockIdMap().get(templateDataId);
                smsList.add(createSmsSendInterfaceCheck(message, phoneId, templateDataId, blockId));
            }
        }
        return smsList;
    }

    /**
     * 低流量提醒：截至19日0时，您本月移动数据套餐流量共有<6004>M，仅使用<6005>M（1M=1024K）。 本提醒免费，退订请回复QXLLTX。为避免浪费，推荐您手机访问沪联网 wap.sh.monternet.com
     * 查余额查流量、看新闻下应用
     * 
     * @param freeres
     * @return
     */
    private String buildDataMessage(CaFreeres freeres)
    {
        Map<String, String> param = new HashMap<String, String>();
        param.put("6004", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, freeres.getUnit()));
        param.put("6005", SmsUtil.measureChangeToM(EnumCodeDefine.MEASURE_ID_BYTE, freeres.getRealUnit()));
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateDataId), param);
    }

    @Override
    protected String buildHavingCondition()
    {
         return " having sum(real_unit)>0";
    }
    @Override
    protected String buildOtherCondition()
    {
        if(smsParam.getKeywordVal()>0){
            return CommonUtil.concat(" and t.RESOURCE_ID >=",smsParam.getKeywordVal()," ");
        }
        return "";
    }

    private boolean hasOrderProduct(Long userId)
    {
        // 有订购不提醒
        IMSUtil.setUserRouterParam(userId);
        DBCondition[] conds = new DBCondition[] { new DBCondition(CoProd.Field.objectId, userId),
                new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
                new DBCondition(CoProd.Field.productOfferingId, EnumSmsDefine.SMS_NOTIFY_GPRS_LOWFLOW_OFFERID),
                new DBCondition(CoProd.Field.validDate, getCurrentDate(), Operator.LESS),
                new DBCondition(CoProd.Field.expireDate, getCurrentDate(), Operator.GREAT_EQUALS) };
        return DBUtil.queryCount(CoProd.class, conds) > 0;

    }

    private boolean isLowUse(CaFreeres res, boolean dataCode)
    {
        if (res.getUnit() == 0 || res.getRealUnit() == 0)// 使用量为0的不发送短信
        {
            return false;
        }

        long amout = res.getUnit();
        long used = res.getRealUnit();
        // imsLogger.info("用户[", res.getResourceId(), "] 免资源总流量是:", amout, "(byte),已使用:", used, "(byte)");
        if (dataCode)
        {// GPRS套餐，提醒的阀值是：已使用量的20%
            return (used + 0.0) / amout <= 0.2;
        }
        else
        {// 非GPRS套餐，提醒的阀值是：6M，而且还要在总量大于30M才触发提醒
            return amout >= minAmout && used < useTap;
        }

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

    protected String[] getItemsKeyWords()
    {

        return new String[] { itemDataKeyword, itemGprsKeyword };
    }

    @Override
    protected Long getTemplateId()
    {

        return relTemplateId;
    }

    public String getMessageParam(CaFreeres res)
    {
        StringBuffer sb = new StringBuffer();
        switch (CommonUtil.long2Int(res.getItemCode()))
        {
        case 6000611:
            sb.append("随e行G3上网本套餐流量");
            break;
        case 6000141:
            sb.append("移动数据套餐国内漫出流量");
            break;
        case 6000321:
            sb.append("移动数据套餐本地流量");
            break;
        case 6000211:
            sb.append("BlackBerry套餐国内流量");
            break;
        case 6000201:
            sb.append("手机邮箱套餐流量");
            break;
        case 6000451:
            sb.append("G3服务关怀赠送流量");
            break;
        case 6000841:
            sb.append("飞信会员专属免费流量");
            break;
        default:
            break;
        }
        sb.append("共有<101>M,仅使用<102>M（1M=1024K）");
        return sb.toString();
    }

    @Override
    public void commitOther()
    {
        
    }
    
    @Override
    public Map<Long, Long> getTemplateAndBlockIdMap()
    {
        
        Map<Long ,Long> map=new HashMap<Long, Long>();
        map.put(templateDataId,  SmsSeqConfig.newBlockId());
        map.put( templateGprsId,  SmsSeqConfig.newBlockId());
        return map;
    }
    @Override
    protected void beforeDo(SmsParam param)
    {
        this.smsParam=param;
    }
    

}
