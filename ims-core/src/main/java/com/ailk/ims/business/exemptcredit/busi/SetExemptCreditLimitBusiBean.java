package com.ailk.ims.business.exemptcredit.busi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.BusiRecordComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.AlarmCodeDefine;
import com.ailk.ims.define.BusiSpecDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imsintf.entity.Do_setExemptCreditLimitResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SSetEmptLimitReq;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;

/**
 * @Description 免催免停
 * @author liuyang8
 * @author yangjh
 * @Date 2011-9-23 2012-01-09 增加免催免停的限制条件
 *       2012-03-28 yangjh 删除后付费用户免催免停条件限制
 *@Date 2012-03-29 lijc3 同时校验userId,PhoneId
 *@Date 2012-4-3 lijc3 上海需求都可以设置 
 *@Date 2012-04-19 lijc3 [bug] #44003修复
 *@date 2012-07-07 luojb 获取告警id参考channelId #50629 
 *@Date 2012-07-24 yangjh : story：52057 免催免停 增加billing_type的入库上发
 *@Date 2012-08-20 yangjh : 增加用户级和账户级的区别
 */
public class SetExemptCreditLimitBusiBean extends BaseCancelableBusiBean
{
    private Long custId = null;
    private Long acctId = null;
    private Long userId = null;
    private String phoneId = null;
    private Date validDate = null;
    private String soNbr = null;
    private Date expireDate = null;
    private Integer billingType = null;
    private Short notify_flag = null;
    private Short operType = null;
    private AccountComponent acctcmp = null;
    private BaseComponent baseCmp = null;
    private SSetEmptLimitReq emptReq = null;
    private CaAccount acct = null;
    private CmResource user = null;
    private AccountQuery acctQuery = null;
    private Integer alarmId = null;
    private IMS3hBean bean = null;
    private Integer exemption_type = null;

    @Override
    public void init(Object... input) throws IMSException
    {
        emptReq = (SSetEmptLimitReq) input[0];
        custId = emptReq.getCust_id();
        acctId = emptReq.getAcct_id();
        phoneId = emptReq.getPhone_id();
        billingType = emptReq.getBilling_type();
        soNbr = emptReq.getSo_nbr();
        String strValidDate = emptReq.getValid_date();
        String strExpireDate = emptReq.getExpire_date();
        validDate = IMSUtil.formatDate(strValidDate, context.getRequestDate());
        expireDate = IMSUtil.formatExpireDate(strExpireDate);
        notify_flag = context.getOper().getNotify_flag();
        operType = emptReq.getOper_type();
        acctcmp = context.getComponent(AccountComponent.class);
        baseCmp = context.getComponent(BaseComponent.class);
        acctQuery = context.getComponent(AccountQuery.class);
        userId = emptReq.getUser_id();
        exemption_type = emptReq.getExemption_type();

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if (emptReq.getOper_type() == EnumCodeDefine.OPER_TYPE_MODIFY && !CommonUtil.isValid(soNbr)&&ProjectUtil.is_TH_AIS())
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_DE_OR_ADD_IS_NULL);
        }
        
        if (!CommonUtil.isValid(phoneId) && !CommonUtil.isValid(acctId) && !CommonUtil.isValid(userId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id or user_id or acct_id");
        }
        bean = context.get3hTree().load3hBean(custId, acctId, userId, phoneId);
        if (acctId == null)
        {
            acctId = bean.getAcctId();
        }
        //@Date 2012-10-18 yangjh : if判断有问题， 将isUser3hBean放前面
        if(bean.isUser3hBean()){
            userId = bean.getUserId();
            user = ((User3hBean)bean).getUser();
            if(billingType != null){
                if((user.getBillingType() == EnumCodeDefine.USER_PAYMODE_PREPAID) || (user.getBillingType() == EnumCodeDefine.USER_PAYMODE_POSTPAID)){
                    if( billingType != user.getBillingType()){
                        IMSUtil.throwBusiException(ErrorCodeDefine.BILLINT_TYPE_NOT_MATCH,billingType,userId);
                    }
                }
            }
        }
        acct = bean.getAccount();
        //@Date 2012-07-24 yangjh : story：52057 免催免停 增加billing_type的入库上发
        if(billingType == null){
            if(acct.getAccountType() ==  EnumCodeDefine.BALANCE_TYPE_SINGLE_BALANCE){
                billingType = EnumCodeDefine.USER_PAYMODE_PREPAID;
            }else{
                billingType = EnumCodeDefine.USER_PAYMODE_POSTPAID;
            }
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {

        List<CmResource> preUsers = new ArrayList<CmResource>();
        List<CmResource> posUsers = new ArrayList<CmResource>();
        
        // 先查被支付关系的用户，如果没有，则查询被归属的用户 liuyang8 2012-02-14 优化代码
        //@Date 2012-4-3 lijc3 上海需求都可以设置 
        if(ProjectUtil.is_TH_AIS()){
        	List<CaAccountRes> caRes = acctQuery.queryAcctResByAcctId(acctId);
        	// 如果没有支付/归属关系的用户 后面代码会有对没有预/后付费用户设置免催免停
        	for (int i = 0; i < caRes.size(); i++)
        	{
        		Long userId = caRes.get(i).getResourceId();
        		CmResource CR = new CmResource();
        		// 查询后付费用户
        		CR = baseCmp.querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, userId), new DBCondition(
        				CmResource.Field.billingType, EnumCodeDefine.USER_PAYMODE_POSTPAID));
        		// 匹配不到后付费用户则查询预付费用户
        		if (CR == null)
        		{
        			CR = baseCmp.querySingle(CmResource.class, new DBCondition(CmResource.Field.resourceId, userId), new DBCondition(
        					CmResource.Field.billingType, EnumCodeDefine.USER_PAYMODE_PREPAID));
        			if (CR != null)
        			{
        				preUsers.add(CR);
        			}
        			else
        			{
        				continue;
        			}
        		}
        		else
        		{
        			posUsers.add(CR);
        		}
        		continue;
        	}
        	if (!CommonUtil.isEmpty(preUsers) && CommonUtil.isEmpty(posUsers))
        	{
        		// 如果没有后付费用户 有预付费用 户不允许设置免催免停
        		throw IMSUtil.throwBusiException(ErrorCodeDefine.ONLY_HAVE_PREPAID);
        	}
        	if (CommonUtil.isEmpty(preUsers) && CommonUtil.isEmpty(posUsers))
        	{
        		// 没有后付费用户 也没有预付费用用户允许设置
        		setExemption();
        	}
        	//有后付费用户就设置免催免停
        	if (!CommonUtil.isEmpty(posUsers))
        	{
        		// 如果有后付费用户并且任何一个用户suspend credit limit则不允许设置
//            for (int i = 0; i < posUsers.size(); i++)
//            {
//                userlife = baseCmp.querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId, posUsers
//                        .get(i).getResourceId()), new DBCondition(CmResLifecycle.Field.sts, EnumCodeDefine.LIFECYCLE_ACTIVE));
//                if (userlife == null)
//                {
//                    LogUtil.getLogger(this.getClass()).debug(
//                            LogUtil.buildLogInfo("Postpaid user has suspend credit limit ", context));
//                    throw IMSUtil.throwBusiException(ErrorCodeDefine.POSTPAID_HAS_SUSPEND);
//                }
//                else
//                {
//                    cycles.add(userlife);
//                }
//            }
//            if (cycles.size() == posUsers.size())
//            {
        		// //允许设置
        		setExemption();
//            }
        	}
        }else{
        	setExemption();
        }
        
        return null;
    }

    private void setExemption()
    {
        List<CaNotifyExempt> notiResults = null;
        Long soNbrIms = null;
        Long objectId = null;
        Integer objectType = null;
        if(user != null){
            objectId = user.getResourceId();
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
        }else{
            objectId = acct.getAcctId();
            objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
        }
        if (operType == EnumCodeDefine.OPER_TYPE_ADD || operType == EnumCodeDefine.OPER_TYPE_MODIFY)
        {
            notiResults = acctcmp.queryCaNotiExempt(objectId,objectType, null);
            if (CommonUtil.isNotEmpty(notiResults))
            {
                for (CaNotifyExempt exemption : notiResults)
                {
                    if (operType == EnumCodeDefine.OPER_TYPE_MODIFY&&ProjectUtil.is_TH_AIS())
                    {
                        soNbrIms = context.getComponent(BusiRecordComponent.class).outSoNbr2DoneCode(soNbr);
                        if (!CommonUtil.isValid(soNbrIms))
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_OR_SO_NBR_NOTEXIST, soNbr);
                        }
                        if (soNbrIms.equals(exemption.getSoNbr()))
                        {
                            continue;
                        }
                    }
                    if(ProjectUtil.is_TH_AIS()||(operType == EnumCodeDefine.OPER_TYPE_ADD&&ProjectUtil.is_CN_SH())){
                    	if (validDate.getTime() > exemption.getValidDate().getTime()
                    			&& validDate.getTime() < exemption.getExpireDate().getTime()
                    			|| validDate.getTime() == exemption.getValidDate().getTime())
                    	{
                    		throw IMSUtil.throwBusiException(ErrorCodeDefine.SET_CREDIT_LIMIT_TIME_OVERLAP);
                    	}
                    	else if (expireDate.getTime() > exemption.getValidDate().getTime()
                    			&& expireDate.getTime() < exemption.getExpireDate().getTime()
                    			|| expireDate.getTime() == exemption.getExpireDate().getTime())
                    	{
                    		throw IMSUtil.throwBusiException(ErrorCodeDefine.SET_CREDIT_LIMIT_TIME_OVERLAP);
                    	}
                    	else if (validDate.before(exemption.getValidDate()) && expireDate.after(exemption.getExpireDate()))
                    	{
                    		throw IMSUtil.throwBusiException(ErrorCodeDefine.SET_CREDIT_LIMIT_TIME_OVERLAP);
                    	}
                    }
                }
            }
        }
        
       
        CaNotifyExempt noti = context.getComponent(UserComponent.class).saveNotification(objectType, objectId,
                exemption_type, emptReq.getExemp_channel_id(), validDate, expireDate,billingType);
        imsLogger.debug("***********operType = " + operType + "******************");
        if (operType == EnumCodeDefine.OPER_TYPE_ADD)
        {
            imsLogger.debug("**************add exemptiong notification********");
//            notiResults=acctcmp.query(CaNotifyExempt.class,new DBCondition(CaNotifyExempt.Field.objectId, objectId),
//             		new DBCondition(CaNotifyExempt.Field.objectType, objectType));
            if(ProjectUtil.is_CN_SH()){
            	if(CommonUtil.isEmpty(notiResults)){
            		acctcmp.createNotiExpt(noti);
            	}else{
            		throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCT_HAS_EXEMPT,objectId);
            	}
            }else{
            	acctcmp.createNotiExpt(noti);
            }
        	alarmId = IMSUtil.getNotificationIdByBusiSpecId(context
        			.getBusiSpecId(true, BusiSpecDefine.NOTIFY_SETEXEMPTCREDIT_ADD),context.getOper().getSo_mode());
            
        }
        else if (operType == EnumCodeDefine.OPER_TYPE_DELETE)
        {
        	if(ProjectUtil.is_CN_SH()){
//        		CaNotifyExempt notiCon = new CaNotifyExempt();
//                notiCon.setObjectId(objectId);
//                notiCon.setObjectType(objectType);
        	    if (emptReq.getExpire_date() == null)
        	    {
        	        acctcmp.deleteByCondition(CaNotifyExempt.class, new DBCondition(CaNotifyExempt.Field.objectId, objectId),
        	                new DBCondition(CaNotifyExempt.Field.objectType, objectType));
        	    }
        	    else
        	    {
        	        acctcmp.deleteByCondition(CaNotifyExempt.class,IMSUtil.formatDate(emptReq.getExpire_date()),new DBCondition(CaNotifyExempt.Field.objectId, objectId),
        	                new DBCondition(CaNotifyExempt.Field.objectType, objectType));
        	    }
        	}else{
        		notiResults = acctcmp.queryCaNotiExempt(objectId,objectType, null);
        		if (CommonUtil.isEmpty(notiResults))
        		{
        			throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTI_NOT_EXIST);
        		}
        		imsLogger.info("**************delete exemptiong notification********");
        		acctcmp.deleteNotiExpt(objectId,objectType);
        	}
            alarmId = IMSUtil.getNotificationIdByBusiSpecId(context
                    .getBusiSpecId(true, BusiSpecDefine.NOTIFY_SETEXEMPTCREDIT_DEL),context.getOper().getSo_mode());
        }
        else if (operType == EnumCodeDefine.OPER_TYPE_MODIFY)
        {
            imsLogger.info("**************modify exemptiong notification********");
            if(ProjectUtil.is_CN_SH()){
//            	notiResults=acctcmp.query(CaNotifyExempt.class,new DBCondition(CaNotifyExempt.Field.objectId, objectId),
//             		new DBCondition(CaNotifyExempt.Field.objectType, objectType));
            	CaNotifyExempt value=new CaNotifyExempt();
            	value.setExemptionType(noti.getExemptionType());
            	value.setRecType(noti.getRecType());
            	value.setExempChannelId(noti.getExempChannelId());
            	if(emptReq.getExpire_date()!=null){
            	    value.setExpireDate(IMSUtil.formatDate(emptReq.getExpire_date()));
            	}
//            	CaNotifyExempt notiCon = new CaNotifyExempt();
//                notiCon.setObjectId(objectId);
//                notiCon.setObjectType(objectType);
                acctcmp.updateByCondition(value, new DBCondition(CaNotifyExempt.Field.objectId, objectId),
                        new DBCondition(CaNotifyExempt.Field.objectType, objectType));
            }else{
	        	notiResults=acctcmp.queryCaNotiExempt(objectId,objectType, soNbrIms);
	            if (CommonUtil.isEmpty(notiResults))
	            {
	                throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_NOTI_NOT_EXIST);
	            }
            	acctcmp.modifyNotiExpt(noti, objectId, objectType, soNbrIms);
            }
            if (CommonUtil.isNotEmpty(notiResults)&&notiResults.get(0).getValidDate().after(validDate)&&ProjectUtil.is_TH_AIS())
            {
                acctcmp.createNotiExpt(noti);
            }
            alarmId = IMSUtil.getNotificationIdByBusiSpecId(context
                    .getBusiSpecId(true, BusiSpecDefine.NOTIFY_SETEXEMPTCREDIT_MOD),context.getOper().getSo_mode());
        }
        
        ImsManualModify modify=new ImsManualModify();
        modify.setSeqId(DBUtil.getSequence(ImsManualModify.class));
        if(user!=null){
            modify.setUserId(user.getResourceId());
        }else if(acct!=null) {
            modify.setAcctId(acct.getAcctId());
        }
        modify.setSoDate(context.getRequestDate());
        modify.setUpField("000010000000000000000000000000000000000000000000000000000000000");
        DBUtil.insert(modify);
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_setExemptCreditLimitResponse response = new Do_setExemptCreditLimitResponse();
        return response;
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
    }

    /**
     * 告警通知
     * 
     * @author wuyj
     */
    public List<IMSNotification> createNotifications(Object[] input, Object[] result, BaseResponse resp)
    {

        userId = bean.getUserId();
        acctId = bean.getAcctId();
        phoneId = bean.getPhoneId();

        List<IMSNotification> notifyList = new ArrayList<IMSNotification>();

        IMSNotification imsNotify = new IMSNotification();
        imsNotify.setAlarmId(alarmId);
        imsNotify.setUserId(userId);
        imsNotify.setAcctId(acctId);
        imsNotify.setCustId(custId);

        imsNotify.addParam(AlarmCodeDefine.EXEMPT_CREDIT_OPER_TYPE, String.valueOf(operType));
        imsNotify.addParam(AlarmCodeDefine.PARAM_PHONE_ID, phoneId);
        imsNotify.addDateParam(AlarmCodeDefine.PARAM_VALID_DATE,validDate);
        imsNotify.addDateParam(AlarmCodeDefine.PARAM_EXPIRE_DATE,expireDate);

//        imsNotify.addPhone(phoneId);

        notifyList.add(imsNotify);

        return notifyList;

    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();

        list.add(context.get3hTree().load3hBean(null, acctId, userId, phoneId));

        return list;
    }

}
