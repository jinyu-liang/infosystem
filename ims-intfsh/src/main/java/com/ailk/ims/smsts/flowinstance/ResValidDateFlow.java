package com.ailk.ims.smsts.flowinstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.common.log.Logger;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.BalanceShComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.EnumSmsDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsSeqConfig;
import com.ailk.ims.smsts.flowbase.BaseFlow;
import com.ailk.ims.smsts.helper.SmsHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SmsUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imscnsh.entity.SMonthlyFee;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 易通卡用户有效期到期提醒(提前三天)
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author zenglu
 * @Date 2012-08-22
 */
public class ResValidDateFlow extends BaseFlow
{
    private static final long templateId = EnumSmsDefine.IM_SH_RES_VALIDITY_ECARD_RETAIN_TIME__CODE;//101153
    private static final long templateId2 = EnumSmsDefine.IM_SH_RES_VALIDITY_ECARD_RETAIN_TIME__CODE2;  //101154
    private static final long noECardTemplate = EnumSmsDefine.IM_SH_RES_VALIDITY_NOT_ECARD_RETAIN_TIME__CODE;  //101154
    private SmsParam smsParam;
    List<Integer> listBrands = new ArrayList<Integer>();//存放具有有效期的用户品牌，配置在系统参数表SYS_PARAMETER中
    public Class<? extends DataObject> getScanTableClass()
    {
        return CmResValidity.class;
    }
    
    public Field getKeyField()
    {
        return CmResValidity.Field.resourceId;
    }

    public OrderCondition[] getOrderCond()
    {
        OrderCondition orderCondition = new OrderCondition(true, CmResValidity.Field.resourceId);
        return new OrderCondition[] { orderCondition };
    }

    public DBCondition[] getQueryConds()
    {
        DBCondition[] conds = new DBCondition[4];
        conds[0] = new DBCondition(CmResValidity.Field.effectFlag, EnumCodeShDefine.CM_VALIDITY_EFFECTIVE);
        conds[1] = new DBCondition(CmResValidity.Field.expireDate, getCurrentDate(), Operator.GREAT);
        conds[2] = new DBCondition(CmResValidity.Field.userExpireDate, getAfter3DayBegin(), Operator.GREAT_EQUALS);
        conds[3] = new DBCondition(CmResValidity.Field.userExpireDate, getAfter3Dayend(), Operator.LESS);
        return conds;
    }

    private Date getAfter3DayBegin()
    {
        return DateUtil.dayBegin(DateUtil.getCutoffDate(Calendar.DAY_OF_MONTH, 3, getCurrentDate()));
    }

    private Date getAfter3Dayend()
    {
        return DateUtil.dayBegin(DateUtil.getCutoffDate(Calendar.DAY_OF_MONTH, 4, getCurrentDate()));
    }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockIds)
    {
        CmResValidity resValid = (CmResValidity) obj;
        Long userId = resValid.getResourceId();

        IMSUtil.setUserRouterParam(userId);

        Long acctId = getSmsCmp().loadAcctIdByUserId(userId);
        String phoneId = getSmsCmp().loadPhoneIdByUserId(userId);
        if (null == acctId || null == phoneId)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_ACCTID_PHONEID_ERROR, "acctId", "phoneId");
        }

        imsLogger.info("用户:", userId, ",账户:", acctId, ",手机号码:", phoneId);
        if (isNotAcctcancelAndHalt(userId))//非预销户且非停机
        {
            if (isExemption(acctId)||isPreferential(userId))// 是免催免停用户 或者公免优惠用户
            {
                imsLogger.info("是免催免停用户，并且不是预销户，把有效期延长一个月");
                // 如果是免催免停，并且不是欲销户，用户有效期延长1个月
                Calendar userExpireCal = Calendar.getInstance();

                userExpireCal.setTime(resValid.getUserExpireDate());

                userExpireCal.add(Calendar.MONTH, 1);

//                resValid.setUserExpireDate(userExpireCal.getTime());
                //resValid.setExpireDate(userExpireCal.getTime());
                CmResValidity upValue=new CmResValidity();
                upValue.setUserExpireDate(userExpireCal.getTime());
                upValue.setSoDate(getCurrentDate());
                upValue.setSoNbr(resValid.getSoNbr());
                // 记录业务记录
                getSmsCmp().insertCmBusi(getCurrentDate());
                getSmsCmp().updateByCondition(upValue, new DBCondition(CmResValidity.Field.resourceId, userId));

                imsLogger.debug("###### user's expired date has been extended for one month");

            }
            else
            {
                //查询用户的主产品
                Integer mainOfferId = getSmsCmp().getContext().getComponent(ProductQuery.class).queryMainOfferIdByUserId(userId);
                
                //判断该用户是否属于有效期停机的品牌范畴
                if (CommonUtil.isEmpty(listBrands) || !listBrands.contains(mainOfferId)){
                    imsLogger.info("用户[",userId,"]不属于有效期停机的品牌范畴,不发送短信提醒");
                    return null;
                }
                
                if (isEwalkMainUserAndOrderMonthlyFee(acctId, userId, mainOfferId))
                {
                    imsLogger.info("用户[",userId,"]付费随E行品牌，选择了有月租费的产品或功能，不设置有效期");
                    return null;
                }
                
                //神州行易通卡品牌的判断
                boolean prodFlag = isSpecProd(userId, mainOfferId);
                imsLogger.info("不是免催免停用户，立即发短信");
                // 获取用户的失效时间
                String expireDate = DateUtil.formatDate(resValid.getUserExpireDate(), DateUtil.DATE_FORMAT_CN_YYYYMMDD);
                if (!prodFlag)
                {
                    imsLogger.info("用户[", userId, "]不是易通卡用户,发送非易通卡用户 短信提醒");
                    return getNotECardTemplate(expireDate,phoneId);
                }

                //对一个用户发送两条短信
                /**
                 * 
                 */
                String msg1 = getMessage(templateId,expireDate);
                String msg2 = getMessage(templateId2,expireDate);
                
                // 要插的短信表
                List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();
                    // 返回短信审核明细表
                    Long block1=smsParam.getTemplateIdAndBlockIdMap().get(templateId);
                    Long block2=smsParam.getTemplateIdAndBlockIdMap().get(templateId2);
                    smsList.add(createSmsSendInterfaceCheck(msg1, phoneId, templateId, block1));
                    smsList.add(createSmsSendInterfaceCheck(msg2, phoneId, templateId2, block2));
                return smsList;
            }
        }

        return null;

    }

    private List<SmsSendInterfaceCheck>  getNotECardTemplate(String expireDate,String phoneId){
        List<SmsSendInterfaceCheck> smsList = new ArrayList<SmsSendInterfaceCheck>();
        Long blockId=smsParam.getTemplateIdAndBlockIdMap().get(noECardTemplate);
        String msg = getMessage(noECardTemplate,expireDate);
        smsList.add(createSmsSendInterfaceCheck(msg, phoneId, noECardTemplate, blockId));
        return smsList;
    }
    
    
    /**
     * 判断用户的主产品是不是易通卡品牌（20000200）
     * wukl 2012-12-18
     * @param userId
     * @return
     */
    private boolean isSpecProd(Long userId, Integer mainProdutOffering)
    {
        
        if (mainProdutOffering != null && mainProdutOffering == EnumCodeShDefine.E_CARD_OFFERING_ID)
            return true;
        
        return false;
    }

    private  SmsSendInterfaceCheck createSmsSendInterfaceCheck(String msg, String phoneId, Long tpl, Long blockId)
    {
        // 返回短信审核明细表
        SmsSendInterfaceCheck smsSendInterfaceCheck = new SmsSendInterfaceCheck();
        smsSendInterfaceCheck.setMessage(msg);// 短信模板
        smsSendInterfaceCheck.setBillId(phoneId);
        smsSendInterfaceCheck.setSmsCode(tpl);// 短信模板Id
        smsSendInterfaceCheck.setBlockId(blockId);// 批次号
        smsSendInterfaceCheck.setDoneCode(SmsSeqConfig.newDoneCode());// 新的doneCode
        smsSendInterfaceCheck.setSendDate(getMessageSendDate());// 发送时间
        smsSendInterfaceCheck.setPriorityLevel(0);// 优先级
        smsSendInterfaceCheck.setRequestReport(SmsHelper.getSendTpl(tpl));// 是否回执，0：不需要，1：需要
        smsSendInterfaceCheck.setSrcAddr("10086");// 服务号码
        smsSendInterfaceCheck.setDealDate(getCurrentDate());
        smsSendInterfaceCheck.setDoneDate(getCurrentDate());
        return smsSendInterfaceCheck;
    }

    /**
     * @description:判断是否免停
     * @author zenglu
     * @date 2012-08-23 101：免催 102：信用度免停 103：信用度免催免停 104：节假日免催 105：节假日免停 106：节假日免催免停
     * @param
     */
    private boolean isExemption(Long acctId)
    {

        DBCondition[] conds = new DBCondition[5];
        conds[0] = new DBCondition(CaNotifyExempt.Field.objectId, acctId);
        conds[1] = new DBCondition(CaNotifyExempt.Field.objectType, EnumCodeShDefine.PROD_OBJECTTYPE_ACCOUNT);// 账户的免催免停
        conds[2] = new DBCondition(CaNotifyExempt.Field.validDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[3] = new DBCondition(CaNotifyExempt.Field.expireDate, getCurrentDate(), Operator.GREAT);
        conds[4] = new DBCondition(CaNotifyExempt.Field.exemptionType, new Integer[] { EnumCodeShDefine.EXEMPTION_NO_STOP,// 信用度免停
                                                                                                                          // 103
                EnumCodeShDefine.EXEMPTION_NO_CALL_AND_NO_STOP,// 信用度免催免停 104
                EnumCodeShDefine.EXEMPTION_HOLIDAYS_NO_STOP // 节假日免催 105
                }, Operator.IN);

        return DBUtil.queryCount(CaNotifyExempt.class, conds) > 0;

    }

    /**
     * @description:不是预销户且没停机
     * @author zenglu
     * @date 2012-08-23 cmResLifeCycle.getSts() 为以下值: 1001 正常 1000 预开户 1008预销户 cmResLifeCycle.getOsStsDtl() != 0 停机
     * @param
     */
    private boolean isNotAcctcancelAndHalt(Long userId)
    {

        DBCondition[] conds = new DBCondition[5];
        conds[0] = new DBCondition(CmResLifecycle.Field.resourceId, userId);
        conds[1] = new DBCondition(CmResLifecycle.Field.validDate, getCurrentDate(), Operator.LESS_EQUALS);
        conds[2] = new DBCondition(CmResLifecycle.Field.expireDate, getCurrentDate(), Operator.GREAT);
        conds[3] = new DBCondition(CmResLifecycle.Field.sts, EnumCodeDefine.LIFECYCLE_TERMINAL, Operator.NOT_EQUALS);// 不是预销户
        conds[4] = new DBCondition(CmResLifecycle.Field.osStsDtl, new Integer(0), Operator.EQUALS);// 停机位正常
        return DBUtil.queryCount(CmResLifecycle.class, conds) > 0;

    }

    /**
     * 返回信息内容 亲爱的客户: 您的手机号码将于<6030>有效期到期，届时将进入90天的保留期。 保留期届满次日起，即使卡内有余额号码也将无法使用。
     * 
     * @return
     */

    private String getMessage(long tplId,String day)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("6030", day);
        return SmsUtil.instanceMessage(getMessageByTemplateId(tplId), map);
    }

//    @Override
    protected Date getMessageSendDate()
    {
        return getCurrentDate();
    }
    @Override
    protected void beforeDo(SmsParam param)
    {
        this.smsParam=param;
        //用户有效期到期需要置有效期停的品牌初始化
        String keys = SysUtil.getString(EnumCodeShDefine.IM_SH_E_CARD_KEY);
        Logger.info("*******以下品牌需要置有效期停:" + keys);
        String[] brands = keys != null ? keys.split(",") : null;
        for (String brand : brands)
        {
            if (CommonUtil.isEmpty(brand))
            {
                continue;
            }
            listBrands.add(CommonUtil.string2Integer(brand.trim()));
        }
        
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
    public Map<Long, Long> getTemplateAndBlockIdMap()
    {
        Map<Long ,Long> map=new HashMap<Long, Long>();
        map.put(templateId,  SmsSeqConfig.newBlockId());
        map.put( templateId2,  SmsSeqConfig.newBlockId());
        map.put( noECardTemplate,  SmsSeqConfig.newBlockId());
        return map;
    }
    
    /**
     * 公免优惠的用户，判断用户是否订购4打头的销售品
     * wukl 2013-2-22
     * @param userId
     * @return
     */
    private boolean isPreferential(Long userId)
    {
        List<CoProd> listProd = getSmsCmp().getContext().getComponent(ProductQuery.class).queryProdListByUserId(userId);
        
        if(CommonUtil.isNotEmpty(listProd))
        {
            for(CoProd prod : listProd)
            {
                String offerId = String.valueOf(prod.getProductOfferingId());
                if (CommonUtil.isEmpty(offerId))
                {
                    continue;
                }
                
                if (offerId.startsWith("4"))
                {
                    return true;
                }
            }
            
        }
        
        return false;
    }
    
    /**
     * 预付费随E行品牌的号码，若选择了有月租费的产品或功能，则不设置有效期（由系统直接取消，规则等同于有月租费号码无有效期）；
     * 
     * @return
     */
    private boolean isEwalkMainUserAndOrderMonthlyFee(Long acctId, Long userId, Integer mainProdutOffering)
    {
        IMSUtil.setAcctRouterParam(acctId);
        // 预付费随e 行品牌的号码带有月租费的产品或功能 不能申请取消有效期
        // pr.getProductOfferingId() = 20000710 ismain =1
        // 把用户下所有的产品找出来 逐个查月租费
        
        if (mainProdutOffering != null && mainProdutOffering == EnumCodeShDefine.E_PRODUCT_OFFERING_ID)
        {
            List<CoProd> coProdList = getSmsCmp().getContext().getComponent(ProductQuery.class)
                    .queryProds(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
            if (coProdList != null && !coProdList.isEmpty())
            {
                for (CoProd coProdTmp : coProdList)
                {
                    List<SMonthlyFee> monthlyFeeList = getSmsCmp().getContext().getComponent(BalanceShComponent.class)
                            .queryMonthlyFee(coProdTmp.getPricingPlanId(), getSmsCmp().getContext().getRequestDate(), 0);
                    if (monthlyFeeList != null && !monthlyFeeList.isEmpty())
                    {

                        return true;
                    }
                }
            }
        }
        return false;
    }

}
