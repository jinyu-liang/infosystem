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
 * @Description: 随E行免费资源提醒(已使用情况提醒)
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author gaoqc5 busi_code=7502
 * @Date 2012-11-21
 */
public class EwalkFlowRes extends SmsBaseFlow
{

    private static final long templateId = EnumSmsDefine.IM_SH_EWALK_SEND_CODE;

    private static final String itemKeyWord = EnumCodeShDefine.IM_SH_EWALK_FLOW;
    
    private SmsParam param;

    protected List<SmsSendInterfaceCheck> buildMessage(CaFreeres res, String phoneId, Long blockId)
    {
         String prodName= res.getProductName();
        // 获取短信
        String message = getMessage(EnumCodeDefine.MEASURE_ID_BYTE, res.getUnit(), res.getRealUnit(), prodName);

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
     * 温馨提示：尊敬的客户，您使用的是随e行G3笔记本业务<6001>， 目前套餐已使用量<6010>M，剩余流量为<6011>M。中国移动
     * 
     * @return
     */
    private String getMessage(int measureId, long amount, long used, String prodName)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("6001", prodName);
        map.put("6010", SmsUtil.measureChangeToM(measureId, used));
        map.put("6011", SmsUtil.measureChangeToM(measureId, amount - used));
        return SmsUtil.instanceMessage(getMessageByTemplateId(templateId), map);
    }

    /**
     * 有订购销售品为 10000019不提醒
     * 
     * @param userId
     * @return
     */
    private boolean noOrderProduct(Long userId)
    {
        IMSUtil.setUserRouterParam(userId);
        DBCondition[] conds = new DBCondition[] { new DBCondition(CoProd.Field.objectId, userId),
                new DBCondition(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV),
                new DBCondition(CoProd.Field.productOfferingId, EnumSmsDefine.SMS_NOTIFY_EWALK_OFFERID),
                new DBCondition(CoProd.Field.validDate, getCurrentDate(), Operator.LESS),
                new DBCondition(CoProd.Field.expireDate, getCurrentDate(), Operator.GREAT_EQUALS) };
        return DBUtil.queryCount(CoProd.class, conds) == 0;

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

    /**
     * @param userId
     * @return
     */
    private DBCondition[] getUserQueryConds(Long userId)
    {
        DBCondition[] conds = new DBCondition[5];
        conds[0] = new DBCondition(CmResource.Field.billingType, 1);// 后付费
        conds[1] = new DBCondition(CmResource.Field.activeDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[2] = new DBCondition(CmResource.Field.validDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[3] = new DBCondition(CmResource.Field.expireDate, getCurrentDate(), Operator.GREAT);
        // 只要订购了随e行科目的用户都可以
        // conds[4] = new DBCondition(CmResource.Field.brandId, EnumCodeShDefine.CM_RESOURCE_BRAND_ID_EWALK);// 随e行用户
        conds[4] = new DBCondition(CmResource.Field.resourceId, userId);
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
        CaFreeres res = packaging(list);
        
//        /* 查当月使用的免费资源
//         * Step1：从免费资源按天导出表中，查出免费资源。
//         * Step2：对本月1号生效前的一次性免费资源，如果在上月的历史导出表中存在，则需要减去历史导出表的部分。
//         */
//        String freeresTableName=FreeresDayNotifyHelper.getFreeresTableName(dayNotify );
//        List<CaFreeres> list = getSmsCmp().queryFreeResListForSms(freeresTableName,userId);
//        
//        String freeresLastMonthTableName =FreeresDayNotifyHelper.getLastMonthFreeresTableName(dayNotify);
//        List<CaFreeresMonth> monthList = getSmsCmp().queryLastMonthFreeResListForSms(freeresLastMonthTableName, userId);
//    
//        freeres =FreeresDayNotifyHelper.getCurrentMonthUsedFreeres(list, monthList, items);
        
        if (hasRes(res) && noOrderProduct(res.getResourceId()) && userAllow(res))
        {
            String phoneId = getSmsCmp().loadPhoneIdByUserId(res.getResourceId());
            if (null == phoneId)
            {
                imsLogger.info("用户[", res.getRealUnit(), "]找不到对应的手机号码,不发送短信");
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
    protected Long getTemplateId()
    {

        return templateId;
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
                "select  t.RESOURCE_ID as resourceId,t.unit as unit,t.real_unit as realUnit,t.acct_Id as acctId,")
                .append(" t.BILL_MONTH as billMonth ,t.ITEM_CODE as itemCode,t.ITEM_NAME as itemName ,t.PRODUCT_ID as productId,t.PRODUCT_NAME as productName, ")
                .append(" t.MEASURE_ID as measureId,t.VAILD_DATE as vaildDate,t.EXPIRE_DATE as expireDate ,t.REMARKS as remarks")
                .append(" from " + freeresTableName + " t")// 表名
                .append(buildCondition(items))// 条件
                .append(buildKeyWordCondition())
                .append(" order by t.RESOURCE_ID")// 排序
                .toString();

    }
    /**
     * 根据关键值判断上次是否有中断
     * @return
     */
    private String buildKeyWordCondition(){
        if(param.getKeywordVal()>0){
            return CommonUtil.concat(" and t.RESOURCE_ID >=",param.getKeywordVal()," ");
        }
        return "";
    }

    private CaFreeres packaging(List<CaFreeres> list)
    {
        CaFreeres res = list.get(0);
        if (list.size() > 1)
        {
            for (int index = 1; index < list.size(); index++)
            {
                CaFreeres temp = list.get(index);
                res.setUnit(temp.getUnit());
                res.setRealUnit(temp.getRealUnit());
            }
        }
        return res;
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
