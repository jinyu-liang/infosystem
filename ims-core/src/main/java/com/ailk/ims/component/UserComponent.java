package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.UserComplex;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaNotifyExempt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIdentityVsImei;
import com.ailk.openbilling.persistence.cust.entity.CmResAuth;
import com.ailk.openbilling.persistence.cust.entity.CmResExt;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.ImsBarServiceSync;
import com.ailk.openbilling.persistence.imsintf.entity.BudgetControl;
import com.ailk.openbilling.persistence.imsintf.entity.FN;
import com.ailk.openbilling.persistence.imsintf.entity.SCallScreenNo;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SUserAuth;
import com.ailk.openbilling.persistence.imsintf.entity.ScpProfileInfo;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferSpecChar;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpecCharValue;

/**
 * @Description: 用户组件，用于定义跟用户操作相关的各种方法
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-1 2011-07-28 wuyujie :增加queryUserIdByPhone() <br>
 * @Date 2011-07-28 wukl :增加queryPhoneIdByUserId() <br>
 * @Date 2011-08-08 zengxr set res_segment for cm_resource <br>
 * @Date 2011-08-09 ljc 处理validate问题 <br>
 * @Date 2011-08-18 zengxr cache relationship between user_id and resource_id add function queryMainUserByAcctId <br>
 * @Date 2011-08-25 wuyujie : createUser（SUser）增加主产品offeringId参数<br>
 * @Date 2012-03-20 yangjh ： 增加黑白名单/设置黑白名单类型 两个方法中查询条件进行修改（object_id和object_type） 2012-03-22 lijc3 测试库开户设置标志
 * @Date 2012-3-29 wangjt: 修改isEmpty方法用来判断对象的问题
 * @Date 2012-3-29 wangjt: 上海不需要用户预算
 * @Date 2012-3-31 wangjt: 优化创建用户代码
 * @Date 2012-3-31 wangjt:上海 不需要 ： 判断支付帐户是否订购unlimited产品，如果订购其支付帐户及其子帐户下的用户数是否超过unlimited产品配置的个数
 * @Date 2012-4-3 tangjl5 创建用户时若内部ID与外部一样，判断该用户是否已经存在
 * @Date 2012-04-04 lijc3 测试卡免催免停
 * @Date 2012-04-05 zengxr If master user exist, change the old master to normal, set new master user.
 * @Date 2012-04-09 caohm5 查询用户归属账户的付费模式
 * @Date 2012-04-10 yangjh add IMS_BAR_SERVICE_SYNC.work_order_day = 5
 * @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
 * @Date 2012-04-24 lijc3 修改rout_method入参错误
 * @Date 2012-04-28 wangyh3 亲情号码资料从co_prod_char_value表独立出来，按照业务单独定表
 * @Date 2012-05-03 wangyh3 亲情号码新增，增加版本控制，上海版本不需要控制亲情号码个数
 * @Date 2012-5-17 yangjh 增加修改resource表的flh_flag和continue_flag操作
 * @Date 2012-5-18 yangjh updateCmRes方法flh_flag,continue_flag两个字段Integer转为Short
 * @Date 2012-05-18 zhangzj3  [45390]MultiSIM Serial No, 增加处理sim卡serial no
 * @Date 2012-05-28 yugb UserComponent.updateCmResExt方法完善
 * @Date 2012-05-29 wuyujie ：解决FLH_Flag和continue_flag没配置报空指针错误
 * @Date 2012-06-21 yangjh 设置信用度增加失效时间的传入
 * @Date 2012-06-27 yangjh story：49282 增加serialNo修改
 * @Date 2012-06-28 luojb 2012-06-28 补充修改email信息 #69640
 * @Date 2012-07-05 wangdw5 : On_Site #50234 传入的createCoProdCharValue的validate和expiredate顺序反了
 * @Date 2012-07-12 wangdw5 :　#51462 update时当库中字段为空才设置默认值
 * @Date 2012-07-24 yangjh : story：52057 免催免停 增加billing_type的入库上发
 * @Date 2012-07-26 wukl : 个人大客户标识修改，增加默认设值
 * @Date 2012-07-26 zhangzj3  [45883]CallScreening需要检验号码是否重复
 * @Date 2012-8-10 sunpf3 修改queryUsersByGroupId方法中的innerJoin。改为两次查询。
 */
public class UserComponent extends UserQuery
{
    public UserComponent()
    {
    }

    private CmResource createResource(SUser user, Long mainProdOfferId) throws IMSException
    {
        Date createDate = IMSUtil.formatDate(user.getCreate_date(), context.getRequestDate());
        Date validDate = IMSUtil.formatDate(user.getValid_date(), context.getRequestDate());
        Date expireDate = IMSUtil.formatExpireDate(user.getExpire_date());

        // insert cm_resource
        CmResource dmResource = new CmResource();
        Long userId = user.getUser_id();
        if (userId == null)
        {
            userId = DBUtil.getSequence(CmResource.class);
            // 2011-12-21 wuyujie : user_id为空的话需要赋值进去
            user.setUser_id(userId);
        }
        dmResource.setResourceId(userId);
        if (user.getUser_type() != null)
            dmResource.setResourceSpecId((int) user.getUser_type());// phone number
        else
            dmResource.setResourceSpecId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_USER_SPEC_ID));

        if (user.getPayment_mode() != null)
            dmResource.setPaymentMode((int) user.getPayment_mode());// postpaid,prepaid,hybrid

        // wangyh3 2012-05-10 #45434 用户表的品牌字段改为根据用户的主产品获取
        //@Date 2012-09-18 yugb :On_Site Defect #59180
        if(user.getBrand() != null && user.getBrand().intValue() != 0){
        	//@Date 2012-10-31 yugb :User Story #62320
            dmResource.setBrandId(user.getBrand().intValue());
        }else{
        	 Integer brandId = context.getComponent(ProductQuery.class).queryBrandByOffering(mainProdOfferId.intValue());
             if (brandId != null) 
             	dmResource.setBrandId(brandId);
             else
             	dmResource.setBrandId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_BRAND_ID));
        }
//        if (user.getBrand() != null)
//            dmResource.setBrandId((int) user.getBrand());
//        else
//            dmResource.setBrandId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_BRAND_ID));

        if (user.getRegion_code() != null)
            dmResource.setRegionCode((int) user.getRegion_code());
        else
            dmResource.setRegionCode(EnumCodeDefine.DEFAULT_USER_REGION_CODE);
        if (user.getCounty_code() != null)
            dmResource.setCountyCode((int) user.getCounty_code());
        else
            dmResource.setCountyCode(EnumCodeDefine.DEFAULT_USER_COUNTY_CODE);
        
        dmResource.setBillingType(EnumCodeDefine.BILLING_TYPE_END_MONTH);
        dmResource.setCreateDate(createDate);
        dmResource.setValidDate(validDate);
        dmResource.setExpireDate(expireDate);
        dmResource.setActiveDate(validDate);
        // default language
        if (user.getIvr_language() != null)
            dmResource.setListeningLanguage(user.getIvr_language().intValue());
        else
            dmResource.setListeningLanguage(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
        if (user.getSms_language() != null)
            dmResource.setReadingLanguage(user.getSms_language().intValue());
        else
            dmResource.setReadingLanguage(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
        if (user.getUssd_language() != null)
            dmResource.setWritingLanguage(user.getUssd_language().intValue());
        else
            dmResource.setWritingLanguage(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_LANGUAGE));
        // 2011-08-08 zengxr set res_segment for cm_resource
        if (user.getUser_segment() != null)
            dmResource.setResSegment(user.getUser_segment().intValue());
        else
            dmResource.setResSegment(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_USER_SEGMENT));
        //2012-5-17 yangjh 增加flh_flag和continue_flag字段入库
        //2012-05-29 wuyujie ：解决FLH_Flag和continue_flag没配置报空指针错误
        //2012-07-25 wukl 实体类更新后转化出错修改
        if(user.getFlh_flag() != null)
        	dmResource.setFlhFlag(CommonUtil.ShortToInteger(user.getFlh_flag()));
        else if(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_FEEL_LIKE_HOME_FLAG) != null)
        	dmResource.setFlhFlag(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_FEEL_LIKE_HOME_FLAG));
        if(user.getContinue_flag() != null)
        	dmResource.setContinueFlag(CommonUtil.ShortToInteger(user.getContinue_flag()));
        else if(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CONTINUE_FLAG) != null){
        	dmResource.setContinueFlag(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_CONTINUE_FLAG));
        }
        // 测试库开户设置标志 lijc3 2012-03-22 SH
        /*Short isTest = user.getIs_test_number();
        if (ProjectUtil.is_CN_SH() && isTest != null && isTest == EnumCodeDefine.IS_TEST_NUMBER_TRUE)
        {
            dmResource.setUserCode((int) isTest);
        }
        else
        {
            dmResource.setUserCode(EnumCodeDefine.IS_TEST_NUMBER_FALSE);
        }*/
        
        dmResource.setEmail(user.getAttrUserEmail());
        //@Date 2012-07-26 wukl : 个人大客户标识修改，增加默认设值
        dmResource.setIsSendCard(0);
        dmResource.setResClass(0);
        insert(dmResource);
        return dmResource;
    }

    private void createResourceIdentities(SUser user, long resourceId) throws IMSException
    {
        Date validDate = (DateUtil.getFormattedDate(user.getValid_date()));
        Date expireDate = (DateUtil.getFormattedDate(user.getExpire_date()));
        // insert cm_resource_identity as two records
        // one is phone_no
        if (!CommonUtil.isEmpty(user.getPhone_id()))
        {
            CmResIdentity dmResIdentity = new CmResIdentity();
            dmResIdentity.setResourceId(resourceId);
            dmResIdentity.setIdentity(user.getPhone_id());
            dmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);// Phone number
            if (!CommonUtil.isEmpty(user.getPassword()))
                dmResIdentity.setResourcePasswd(user.getPassword());
            dmResIdentity.setSoNbr(context.getSoNbr());
            //@Date 2012-10-29 yangjh : User Story #62889CM_RES_IDENTITY 表中新增字段REL_IDENTITY
            if(!CommonUtil.isEmpty(user.getImsi())){
                dmResIdentity.setRelIdentity(user.getImsi());
            }else{
                dmResIdentity.setRelIdentity(user.getPhone_id());
            }
            if(user.getSerial_no() != null){
                dmResIdentity.setSerialNo(user.getSerial_no());
            }
            // @Date 2012-6-23 tangjl5 On_Site Defect #48882 renew用户时，Cidentity表数据没有更新
            dmResIdentity.setValidDate(validDate == null? context.getRequestDate() : validDate);
           
            // @Date tangjl5 BUG#42827 时间应该与用户的时间一致
            dmResIdentity.setExpireDate(DateUtil.checkForNull(expireDate, IMSUtil.getDefaultExpireDate()));
            insert(dmResIdentity);
            // 新建imei
            this.createResourceIdentityIMEI(dmResIdentity, user.getIMEI());
        }

//        if (!CommonUtil.isEmpty(user.getImsi()))
//        {
//            // the other is imsi
//            CmResIdentity dmResIdentity = new CmResIdentity();
//            dmResIdentity = new CmResIdentity();
//            dmResIdentity.setResourceId(resourceId);
//            dmResIdentity.setIdentity(user.getImsi());
//            dmResIdentity.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_IMSI);// IMSI
//            dmResIdentity.setSoNbr(context.getDoneCode());
//            //@Date 2012-05-18 zhangzj3  [45390]MultiSIM Serial No, 增加处理sim卡serial no
//            if(user.getSerial_no() != null){
//            	dmResIdentity.setSerialNo(user.getSerial_no());
//            }
//            // @Date 2012-6-23 tangjl5 On_Site Defect #48882 renew用户时，Cidentity表数据没有更新
//            dmResIdentity.setValidDate(validDate == null? context.getRequestDate() : validDate);
//            // @Date tangjl5 BUG#42827 时间应该与用户的时间一致
//            dmResIdentity.setExpireDate(IMSUtil.formatExpireDate(user.getExpire_date()));
//            insert(dmResIdentity);
//        }
    }

    private void createResourceIdentityIMEI(CmResIdentity dmIndentity, String imei) throws IMSException
    {
        CmIdentityVsImei cmIdentityVsImei = new CmIdentityVsImei();
        if (dmIndentity.getResourceId() != null)
            cmIdentityVsImei.setResourceId(dmIndentity.getResourceId());
        if (!CommonUtil.isEmpty(dmIndentity.getIdentity()))
            cmIdentityVsImei.setIdentity(dmIndentity.getIdentity());
        if (dmIndentity.getIdentityType() != null)
            cmIdentityVsImei.setIdentityType(dmIndentity.getIdentityType());
       // cmIdentityVsImei.setImei(imei);
        cmIdentityVsImei.setForceBinding(1);
        if (dmIndentity.getSoNbr() != null)
            cmIdentityVsImei.setSoNbr(dmIndentity.getSoNbr());
        // @Date 2012-6-23 tangjl5 On_Site Defect #48882 renew用户时，Cidentity表数据没有更新
        cmIdentityVsImei.setValidDate(dmIndentity.getValidDate());
        cmIdentityVsImei.setExpireDate(dmIndentity.getExpireDate());

        insert(cmIdentityVsImei);
    }

    

    /**
     * 增加用户预算 wangjt 2011-10-11
     */
    private void createResourceBudget(SUser sUser, Long acctId, CmResource dmUser) throws IMSException
    {
        Double budget = sUser.getBudget();
        if (!CommonUtil.isValid(budget))
        {
            return;
        }
        
        AmountComponent amountCmp = context.getComponent(AmountComponent.class);
        CaAccount acct = context.get3hTree().loadAcct3hBean(acctId).getAccount();
        budget = amountCmp.parseRatingAmount(amountCmp.getImsMeasureId(acct.getMeasureId()), sUser.getBudget(), acctId);
        
        BudgetComponent bgtCmp = context.getComponent(BudgetComponent.class);
        BudgetControl budgetControl = new BudgetControl();
        budgetControl.setBudget(budget);
        budgetControl.setMeasure_id(amountCmp.getRatingMeasureId(amountCmp.getImsMeasureId(acct.getMeasureId()), acctId));
        // 公用检查和设置
        bgtCmp.checkAndSetBudgetControl(budgetControl);

        bgtCmp.setInputValidDate(IMSUtil.formatDate(sUser.getValid_date(), context.getRequestDate()));
        bgtCmp.setInputExpireDate(IMSUtil.formatExpireDate(sUser.getExpire_date()));

        bgtCmp.createUserOrServiceBudget(budgetControl, acctId, dmUser);
    }

    /**
     * @Description: 根据用户Id删除资源标识属性表数据
     * @param userId
     * @author: tangjl5
     * @Date: 2012-4-4
     */
    public void delResIdentityByUserId(Long userId)
    {
//        CmResIdentity res = new CmResIdentity();
//        res.setResourceId(userId);
        this.deleteByCondition(CmResIdentity.class, new DBCondition(CmResIdentity.Field.resourceId, userId));
    }

    /**
     * createUser（SUser）增加主产品offeringId参数
     * 
     * @author wuyujie 2011-08-25
     */
    public UserComplex createComplexUser(SUser user, Long mainProdOfferId) throws IMSException
    {
        UserComplex complex = new UserComplex();

        // @Date 2012-4-3 tangjl5 当内部ID与外部一样时，判断该用户是否已经存在
        if (SysUtil.getBoolean(SysCodeDefine.busi.INNER_OUTER_ID_EQUAL) && CommonUtil.isValid(user.getUser_id()))
        {
//            CmResource checkUser = queryUserByUserID(user.getUser_id());
//            if (checkUser != null && CommonUtil.isValid(checkUser.getResourceId()))
//            {
//                throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_USER_EXISTS, user.getUser_id());
//            }
        	//2012-08-28 linzt 整理组件方法  采用load3hBean
        	try
        	{
        		context.get3hTree().loadUser3hBean(user.getUser_id());
        		throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_USER_EXISTS, user.getUser_id());
        	}
        	catch(IMS3hNotFoundException e)
        	{
        		imsLogger.error(e,e);
        	}
        }

        BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);

        Acct3hBean acct3hbean = context.get3hTree().loadAcct3hBean(user.getAcct_id());
        CaAccount dmAccount = acct3hbean.getAccount();
        CmCustomer dmCust = acct3hbean.getCustomer();
        //  //@Date 2012-10-31 yugb :注释
        //[47113]创建用户时支持自动激活账户
//        if(dmAccount != null){
//	        if(dmAccount.getAccountStatus() == EnumCodeDefine.ACCOUNT_TERMINATE){
//	        	CaAccount accountDB = new CaAccount();
//	        	accountDB.setAccountStatus(EnumCodeDefine.ACCOUNT_ACTIVE);
//	        	updateByCondition(accountDB, new DBCondition(CaAccount.Field.acctId,dmAccount.getAcctId()));
//	        }
//        }
        
        // 新建用户相关信息
        CmResource dmResource = this.createResource(user, mainProdOfferId);

        // 新建手机号码,imei,imsi等信息
        this.createResourceIdentities(user, dmResource.getResourceId());

        // 20120302 ljc 上海需求 测试卡开户需要设置免催免停
        //2012-05-04 lijc3 测试卡不作为免催免停标识
//        if (ProjectUtil.isSH() && user.getIs_test_number() != null
//                && user.getIs_test_number() == EnumCodeDefine.IS_TEST_NUMBER_TRUE)
//        {
//            super.insert(this.saveNotification(EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT, dmAccount.getAcctId(),
//                    EnumCodeDefine.EXEMPT_TYPE_NO_PUSH_AND_STOP, 0, dmAccount.getValidDate(), dmAccount.getExpireDate()));
//        }

        Long acctId = dmAccount.getAcctId();
        long userId = dmResource.getResourceId();
        Long billAcctId = acctId;
        // yanchuan 2012-02-09 支付帐户和归属帐户存在与一条记录中
        if (CommonUtil.isValid(user.getBillable_acct_id()) && user.getBillable_acct_id() != acctId.longValue())
        {
            CaAccount dmBillableAccount = context.get3hTree().loadAcct3hBean(user.getBillable_acct_id()).getAccount();
            billAcctId = dmBillableAccount.getAcctId();
        }

        // 增加CaAccountRes（归属账户、支付账户）
        CaAccountRes caRes = insertCaAccountRes(userId, billAcctId, acctId, user.getTitle_role_id());

        if (ProjectUtil.is_TH_AIS())// 上海不需要该逻辑
        {
            // 判断支付帐户是否订购unlimited产品，如果订购其支付帐户及其子帐户下的用户数是否超过unlimited产品配置的个数 yanchuan 2012-03-10
            if (context.getComponent(ProductQuery.class).isExceedUserLimit(billAcctId))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.USERS_NOT_SATISFY_SUBSCRIBER_LIMIT, billAcctId);
            }
        }

        CmResLifecycle resLife = lifeCycleCmp.initUserLifeCycle(dmResource, dmAccount, dmCust, mainProdOfferId);

        if (ProjectUtil.is_TH_AIS())// 上海不需要预算
        {
            // 增加用户预算
            this.createResourceBudget(user, acctId, dmResource);
        }

        // 2012-01-13 wuyujie : 方便创建3hbean，这里返回符合对象
        complex.setResource(dmResource);
        complex.setPhoneId(user.getPhone_id());
        complex.setLifeCycle(resLife);
        // 2012-01-13 wuyujie : 载入3hbean
        User3hBean bean = context.get3hTree().loadUser3hBean(complex, caRes);
        context.addMain3hBean(bean);
        return complex;
    }

    /**
     * 生成CoProdCharValue
     */
    public CoProdCharValue createCoProdCharValue(long productId, long groupId, int specCode, String value, Date expireDate,
            Date validDate, Long objectId, Integer objectType) throws IMSException
    {
        CoProdCharValue charValue = new CoProdCharValue();
        charValue.setSoNbr(this.context.getSoNbr());
        charValue.setGroupId(groupId);
        charValue.setProductId(productId);
        charValue.setExpireDate(expireDate);
        charValue.setValidDate(validDate);
        charValue.setSpecCharId(specCode);
        charValue.setValue(value);
        charValue.setCreateDate(context.getRequestDate());
        charValue.setObjectId(objectId);
        charValue.setObjectType(objectType);
        return charValue;
    }
    
    /**
     * @Description 生成CoFnCharValue
     * @date 2012-04-28
     * @author wangyh3
     */
    public CoFnCharValue createCoFnCharValue(long productId, long groupId, int specCode, String value, Date expireDate,
            Date validDate, Long objectId, Integer objectType) throws IMSException
    {
        CoFnCharValue charValue = new CoFnCharValue();
        charValue.setSoNbr(this.context.getSoNbr());
        charValue.setGroupId(groupId);
        charValue.setProductId(productId);
        charValue.setExpireDate(expireDate);
        charValue.setValidDate(validDate);
        charValue.setSpecCharId(specCode);
        charValue.setValue(value);
        charValue.setCreateDate(context.getRequestDate());
        charValue.setObjectId(objectId);
        charValue.setObjectType(objectType);
        return charValue;
    }

    /**
     * 实例化CM_RES_EXT表
     * 
     * @author yanchuan 2011-09-09
     */
    public void createCmResExt(Map<String, String> map, Long userId)
    {
        CmResExt cmResExt = new CmResExt();
        if (!CommonUtil.isEmpty(map.get(ConstantDefine.EXTPARAM_USER_PHFLAG)))
            cmResExt.setProp1(map.get(ConstantDefine.EXTPARAM_USER_PHFLAG));
        if (CommonUtil.isNotEmpty(map.get(ConstantDefine.EXTPARAM_USER_VOICEMAILFLAG)))
        {
            cmResExt.setProp2(map.get(ConstantDefine.EXTPARAM_USER_VOICEMAILFLAG));
        }

        if (CommonUtil.isNotEmpty(map.get(ConstantDefine.EXTPARAM_USER_USSDCALLBACKFLAG)))
        {
            cmResExt.setProp3(map.get(ConstantDefine.EXTPARAM_USER_USSDCALLBACKFLAG));
        }

        if (userId != null)
        {
            cmResExt.setResourceId(userId);
        }

        cmResExt.setSoNbr(context.getSoNbr());
        insert(cmResExt);
    }

    /**
     * 实例化CM_RES_AUTH表
     * 
     * @author yanchuan 2011-09-16
     * @param userAuth
     */
    public void createUserAuth(SUserAuth userAuth)
    {
        if (userAuth == null || CommonUtil.isEmpty(userAuth.getPhone_id()))
            return;
        User3hBean bean = context.get3hTree().loadUser3hBean( userAuth.getPhone_id());
        CmResource resource = bean.getUser();
        if (resource == null)
            resource = this.queryUserByPhoneId(userAuth.getPhone_id());
        CmResAuth resAuth = new CmResAuth();
        if (userAuth.getFraudState() != null)
            resAuth.setFraudState(CommonUtil.ShortToInteger(userAuth.getFraudState()));
        else
            resAuth.setFraudState(0);
        if (userAuth.getFraudTimes() != null)
            resAuth.setFraudTimes(CommonUtil.ShortToInteger(userAuth.getFraudTimes()));
        else
            resAuth.setFraudTimes(0);
        if (userAuth.getHPLMN() != null)
            resAuth.setHplmn(userAuth.getHPLMN().intValue());
        else
            resAuth.setHplmn(0);
        if (userAuth.getInterIntraAuthority() != null)
            resAuth.setInterIntraAuthority(userAuth.getInterIntraAuthority().intValue());
        else
            resAuth.setInterIntraAuthority(0);
        if (userAuth.getInterIntraflag() != null)
            resAuth.setInterIntraFlag(userAuth.getInterIntraflag().intValue());
        else
            resAuth.setInterIntraFlag(0);
        if (userAuth.getInternationalRoaming() != null)
            resAuth.setInternationalRoaming(userAuth.getInternationalRoaming().intValue());
        else
            resAuth.setInternationalRoaming(0);
        if (userAuth.getIRPromptflag() != null)
            resAuth.setIrpromptFlag(userAuth.getIRPromptflag().intValue());
        else
            resAuth.setIrpromptFlag(0);
        if (userAuth.getIRSMSAuthority() != null)
            resAuth.setIrsmsAuthority(userAuth.getIRSMSAuthority().intValue());
        else
            resAuth.setIrsmsAuthority(0);
        if (userAuth.getNationalRoaming() != null)
            resAuth.setNationalRoaming(userAuth.getNationalRoaming().intValue());
        else
            resAuth.setNationalRoaming(0);
        if (userAuth.getRoamingAuthority() != null)
            resAuth.setRoamingAuthority(userAuth.getRoamingAuthority().intValue());
        else
            resAuth.setRoamingAuthority(0);
        if (resource.getResourceId() != null)
            resAuth.setResourceId(resource.getResourceId());
        resAuth.setSoNbr(context.getSoNbr());
        insert(resAuth);

    }

    /**
     * 建立支付关系
     * 
     * @author wuyujie 2011-11-16
     */
    private CaAccountRes insertCaAccountRes(long resourceId, long billAcctId, long acctId, Short titleRoleId) throws IMSException
    {
        CaAccountRes dmAccountRes = new CaAccountRes();
        dmAccountRes.setAcctId(acctId);
        dmAccountRes.setResourceId(resourceId);
        dmAccountRes.setResAcctId(billAcctId);
        dmAccountRes.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);// 2-billable
        if (titleRoleId != null)
            dmAccountRes.setTitleRoleId(titleRoleId.intValue());
        else
            dmAccountRes.setTitleRoleId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_USER_TITLE_ROLEID));

        // 2011-09-20 wuyujie : cm_resource的生效失效时间保持一致
        // 2012-09-20 linzt 从缓存中取出重开户的新用户
        CmResource user = context.getSingleCache(CmResource.class, new CacheCondition(CmResource.Field.resourceId, resourceId),new CacheCondition(CmResource.Field.expireDate,DateUtil.currentDate(),Operator.GREAT));
        dmAccountRes.setCreateDate(user.getCreateDate());
        dmAccountRes.setExpireDate(user.getExpireDate());
        dmAccountRes.setValidDate(user.getValidDate());

        // 2011-11-17 wukl:增加chg_flag,默认值0
        dmAccountRes.setChgFlag(EnumCodeDefine.ACCOUNT_CHG_FLAG_NORMAL);

        insert(dmAccountRes);
        return dmAccountRes;
    }
    
    public CaAccountRes insertCaAcctRes4ChangeOwner(long resourceId, long acctId, Integer chgFlag, CmResource user) throws IMSException
    {
        CaAccountRes dmAccountRes = new CaAccountRes();
        dmAccountRes.setAcctId(acctId);
        dmAccountRes.setResourceId(resourceId);
        dmAccountRes.setResAcctId(acctId);
        dmAccountRes.setRelationshipType(EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING);// 2-billable
        dmAccountRes.setTitleRoleId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_USER_TITLE_ROLEID));

        // 2011-09-20 wuyujie : cm_resource的生效失效时间保持一致
//        CmResource user = context.getSingleCache(CmResource.class, new CacheCondition(CmResource.Field.resourceId, resourceId));
        dmAccountRes.setCreateDate(user.getCreateDate());
        dmAccountRes.setExpireDate(user.getExpireDate());
        dmAccountRes.setValidDate(user.getValidDate());

        // 2011-11-17 wukl:增加chg_flag,默认值0
        dmAccountRes.setChgFlag(chgFlag);

        insert(dmAccountRes);
        return dmAccountRes;
    }

    /**
     * @Description: modify user
     * @param user
     * @throws IMSException 2011-08-19 wukl 首次激活时，需要将老记录的失效时间置为 指定的时间，而不是请求的时间
     *  @Date 2012-09-19 yugb :[57408]API中字段清空方案
     */
    public void modifyResource(SUser user) throws IMSException
    {
        CmResource res = new CmResource();
        boolean flag = false;
        if(IMSUtil.isClean(user.getActive_date()))
        {
        	flag = true;
        	res.setActiveDate(null);
        	
        }
        else if (!CommonUtil.isEmpty(user.getActive_date()))
        {
            Date d = IMSUtil.formatDate(user.getActive_date());
            // CmResource dmUser=this.queryUserByUserID(user.getUser_id());
            if (!d.after(context.getRequestDate()))
            {
                res.setActiveDate(d);
                flag = true;
            }
        }
      //@Date 2012-09-18 yangjh : 更新3Hbean中的数据，避免后续set negative balance 取的是老数据
        User3hBean userBean=context.get3hTree().loadUser3hBean(user.getUser_id(), user.getPhone_id());
        if (user.getUssd_language() != null)
        {
        	flag = true;
        	if(IMSUtil.isClean(user.getUssd_language()))
        		res.setWritingLanguage(null);
        	else
        		res.setWritingLanguage((int) user.getUssd_language());
        	
        }
        if (user.getIvr_language() != null)
        {
            flag = true;
            if(IMSUtil.isClean(user.getIvr_language()))
            	res.setListeningLanguage(null);
            else
            	res.setListeningLanguage((int) user.getIvr_language());
        }
        if (user.getSms_language() != null)
        {
            flag = true;
            if(IMSUtil.isClean(user.getSms_language()))
            	 res.setReadingLanguage(null);
            else
            	res.setReadingLanguage((int) user.getSms_language());
        }
        if (user.getRegion_code() != null)
        {
            flag = true;
            if(IMSUtil.isClean(user.getRegion_code()))
            	res.setRegionCode(null);
            else
            	res.setRegionCode((int) user.getRegion_code());
        }
        if (user.getCounty_code() != null)
        {
            flag = true;
            if(IMSUtil.isClean(user.getCounty_code() ))
            	res.setCountyCode(null);
            else
            	res.setCountyCode((int) user.getCounty_code());
        }
        // if (!CommonUtil.isEmpty(user.getValid_date()))
        // {
        // flag = true;
        // res.setValidDate(DateUtil.getFormatDate(user.getValid_date()));
        // }
        if (user.getUser_segment() != null)
        {// 2011-08-09 ljc 新增修改segment
            flag = true;
            if(IMSUtil.isClean(user.getUser_segment()))
            {
            	res.setResSegment(null);
                userBean.getUser().setResSegment(null);
            }
            else
            {
        	   res.setResSegment((int) user.getUser_segment());
               userBean.getUser().setResSegment((int) user.getUser_segment());
            }
         
        }
        if (user.getBrand() != null)
        {//
            flag = true;
            if(IMSUtil.isClean(user.getBrand()))
            {
            	 res.setBrandId(null);
                 userBean.getUser().setBrandId(null);
            }
            else
            {
            	 res.setBrandId((int) user.getBrand());
                 userBean.getUser().setBrandId((int) user.getBrand());
            }
        }
        // luojb 2012-06-28 补充修改email信息 #69640
        if(IMSUtil.isClean(user.getAttrUserEmail()))
        {
        	 flag = true;
             res.setEmail(null);
        }
        else if(CommonUtil.isValid(user.getAttrUserEmail()))
        {
            flag = true;
            res.setEmail(user.getAttrUserEmail());
        }
        if (flag)
        {
            res.setSoDate(context.getRequestDate());
            // 首次激活时会设置老记录的失效时间
            Date oldExpireDate = null;
            if (context.getExtendParam(ConstantDefine.ACTIVE_DATE) != null)
            {
                oldExpireDate = (Date) context.getExtendParam(ConstantDefine.ACTIVE_DATE);
            }
            this.updateByCondition(res, oldExpireDate, new DBCondition(CmResource.Field.resourceId, user.getUser_id()));// 更新CmResource
        }
    }

    /**
     * @Description: 修改CmResourceIdentity
     */
    private void modifyResIdentity(SUser user) throws IMSException
    {
        CmResIdentity cmResIdentity = new CmResIdentity();
        CmResIdentity resIdenWhere = this.querySingle(CmResIdentity.class, 
                new DBCondition(CmResIdentity.Field.resourceId, user.getUser_id()),
                new DBCondition(CmResIdentity.Field.identityType,EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
        Boolean flag = false;
        if (!CommonUtil.isEmpty(user.getPassword()))
        {
            if (!user.getPassword().equals(resIdenWhere.getResourcePasswd()))
            {// 密码不一样才修改
                flag = true;
                cmResIdentity.setResourcePasswd(user.getPassword());

                // if (!CommonUtil.isEmpty(user.getValid_date()))
                // {
                // flag = true;
                // cmResIdentity.setValidDate(DateUtil.getFormatDate(user.getValid_date()));
                // }
            }
        }
        if (flag)
        {
            cmResIdentity.setSoDate(context.getRequestDate());
            this.updateByCondition(cmResIdentity, new DBCondition(CmResIdentity.Field.resourceId, user.getUser_id()),
                    new DBCondition(CmResIdentity.Field.identity, user.getPhone_id()));
            // ljc , new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE)
        }
    }

    /**
     * @Description: 修改用户IMEI
     */
    private void modifyUserIMEI(SUser user) throws IMSException
    {
        if (!CommonUtil.isEmpty(user.getIMEI()))
        {
            CmIdentityVsImei cmIdentityVsImei = this.querySingle(CmIdentityVsImei.class, new DBCondition(
                    CmIdentityVsImei.Field.resourceId, user.getUser_id()),
                    new DBCondition(CmIdentityVsImei.Field.identity, user.getPhone_id()));
            if (cmIdentityVsImei == null)
            {
                // 如果没有就新建
                cmIdentityVsImei = new CmIdentityVsImei();
                cmIdentityVsImei.setIdentity(user.getPhone_id());
                cmIdentityVsImei.setIdentityType(EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE);
                //cmIdentityVsImei.setImei(user.getIMEI());
                cmIdentityVsImei.setForceBinding(EnumCodeDefine.RESOURCE_IMEI_BIND);// default
                cmIdentityVsImei.setResourceId(user.getUser_id());
                cmIdentityVsImei.setSoNbr(this.context.getSoNbr());
                cmIdentityVsImei.setCreateDate(context.getRequestDate());
                cmIdentityVsImei.setExpireDate(IMSUtil.formatExpireDate(user.getExpire_date()));
                this.insert(cmIdentityVsImei);
            }
            else
            {
                if (!user.getIMEI().equals(cmIdentityVsImei.getImei()))
                {// 不一样才修改
                    cmIdentityVsImei = new CmIdentityVsImei();

                    //cmIdentityVsImei.setImei(user.getIMEI());
                    cmIdentityVsImei.setSoDate(context.getRequestDate());
                    this.updateByCondition(cmIdentityVsImei,
                            new DBCondition(CmIdentityVsImei.Field.resourceId, user.getUser_id()), new DBCondition(
                                    CmIdentityVsImei.Field.identity, user.getPhone_id()));
                }
            }
        }
    }

    /**
     * 2011-08-03 ljc 新加 修改用户的支付账户 2012-01-16 yangjh逻辑修改
     */
    public void modifyCaAccountRes(SUser user) throws IMSException
    {
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(user.getUser_id());
        CaAccountRes value = new CaAccountRes();
        value.setResAcctId(user3hBean.getAcctId());
        //@Date 2012-10-11 yugb :删除relationshipType=0的条件，匹配所有
        this.updateByCondition(value, new DBCondition(CaAccountRes.Field.resourceId, user.getUser_id()));
    }

    //@Date 2012-10-29 yangjh : User Story #62685 imsi和phone合成一条记录
    public void changeMSISDNSIM(Long userId, String newResIden, int idenType,String serialNo, String newImsi) throws IMSException
    {
        CmResIdentity resIden = new CmResIdentity();
        if(newResIden != null){
            resIden.setIdentity(newResIden);
            
            User3hBean bean = context.get3hTree().loadUser3hBean(userId);
            // 2012-07-30 luojb #53832更新群成员表(pbx 号码不用更新)
            CaAccountRv rv = new CaAccountRv();
            rv.setPhoneId(newResIden);
            DBUtil.updateByCondition(rv, new DBCondition(CaAccountRv.Field.resourceId,userId),
                    new DBCondition(CaAccountRv.Field.phoneId,bean.getPhoneId()));
            // 2012-07-31 luojb #53832更新fn
            CoFnCharValue fnChar = new CoFnCharValue();
            fnChar.setValue(newResIden);
            updateByCondition(fnChar, 
                    new DBCondition(CoFnCharValue.Field.specCharId,SpecCodeDefine.FRIENDNUMBER_NUMBER),
                    new DBCondition(CoFnCharValue.Field.value,bean.getPhoneId()));
        }
        resIden.setSoNbr(context.getSoNbr());
        resIden.setValidDate(context.getRequestDate());
        resIden.setSoDate(context.getRequestDate());
        //2012-06-27 yangjh story：49282 增加serialNo修改
        if(CommonUtil.isValid(serialNo)){
        	resIden.setSerialNo(serialNo);
        }
        //@Date 2012-10-29 yangjh : userStory #62685  IMSI和电话号码合成一条记录
        if(newImsi != null){
            resIden.setRelIdentity(newImsi);
        }
        updateByCondition(resIden, new DBCondition(CmResIdentity.Field.resourceId, userId),
                new DBCondition(CmResIdentity.Field.identityType, idenType));
    }

   
    /**
     * 修改用户的归属账户为newAccId
     */
    public void changeBelongAcct4User(Long userId, Long newAccId)
    {
        CaAccountRes caAccountRes = new CaAccountRes();
        caAccountRes.setAcctId(newAccId);
        super.updateByCondition(caAccountRes, new DBCondition(CaAccountRes.Field.resourceId, userId), new DBCondition(
                CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
    }

    /**
     * 修改用户的付费模式
     * 
     * @return:是否修改了付费模式
     */
    public boolean changePayMode4User(Long userId, Integer newPayMode)
    {
        CmResource cmResource = new CmResource();
        cmResource.setBillingType(newPayMode);
        List<CmResource> list = super.updateByCondition(cmResource, new DBCondition(CmResource.Field.resourceId, userId));
        return !CommonUtil.isEmpty(list);
    }

    /**
     * 修改用户信息
     */
    public void modifyCmResourceInfo(SUser user) throws IMSException
    {
        modifyResource(user);// modify user

        modifyResIdentity(user);// modify identity

        modifyUserIMEI(user);// modify IMEI
        if (user.getTitle_role_id() != null)
        {
            changeUserRole(user);// modify user_role
        }
    }

    /**
     * @Description: 批量删除用户
     */
    public void deleteBathUser(List<Long> userIds)
    {
        deleteByCondition(CmResource.class, new DBCondition(CmResource.Field.resourceId, userIds, Operator.IN));
    }

    /*
     * 修改用户角色
     */
    private void changeUserRole(SUser user) throws IMSException
    {
         CaAccountRes res = this.querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, user.getUser_id()),
                new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
        if (res == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CUS_NOT_FAMILY_CUS);
        }
        Long acctId = res.getAcctId();
        if (user.getTitle_role_id().intValue() == EnumCodeDefine.TITLEROLEID_SINGLE_BALANCE_MASTER_NUMBER)
        {
            CaAccountRes caAcctRes = this.querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.acctId, acctId),
                    new DBCondition(CaAccountRes.Field.titleRoleId, EnumCodeDefine.TITLEROLEID_SINGLE_BALANCE_MASTER_NUMBER));
            // 2012-04-04 zengxr If master user exist, change the old master to normal, set new master user.
            if (caAcctRes != null)
            {
//                CaAccountRes crCond = new CaAccountRes();
//                crCond.setResourceId(caAcctRes.getResourceId());
                CaAccountRes upRole = new CaAccountRes();
                upRole.setTitleRoleId(SysUtil.getInt(SysCodeDefine.busi.DEFAULT_USER_TITLE_ROLEID));// title role to the
                                                                                                         // default.
                this.updateByCondition(upRole, new DBCondition(CaAccountRes.Field.resourceId, caAcctRes.getResourceId()));
            }
        }
        CaAccountRes resValue = new CaAccountRes();
        resValue.setTitleRoleId(user.getTitle_role_id().intValue());
//        CaAccountRes resWhere = new CaAccountRes();
//        resWhere.setResourceId(res.getResourceId());
//        resWhere.setRelationshipType(res.getRelationshipType());
        this.updateByCondition(resValue, new DBCondition(CaAccountRes.Field.resourceId, res.getResourceId()),
                new DBCondition(CaAccountRes.Field.relationshipType, res.getRelationshipType()));
    }

    /**
     * modify user fns by lijc3
     */
    public void modifyUserFNs(Long prodId, FN[] fns)
    {
        for (int i = 0; i < fns.length; i++)
        {
            FN fn = fns[i];
            CoFnCharValue charValue = this.querySingle(CoFnCharValue.class, new DBCondition(CoFnCharValue.Field.productId,
                    prodId), new DBCondition(CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_NUMBER),
                    new DBCondition(CoFnCharValue.Field.value, fn.getOld_phone_id()));
            if (charValue == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.PHONE_NOT_USER_FN, fn.getOld_phone_id());
            }
            // 验证要增加的是否已经是亲情号码了
            charValue = this.querySingle(CoFnCharValue.class, new DBCondition(CoFnCharValue.Field.productId, prodId),
                    new DBCondition(CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_NUMBER), new DBCondition(
                    		CoFnCharValue.Field.value, fn.getPhone_id()));
            if (charValue != null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.UESR_FN_EXIST_PHONE);
            }
            charValue = new CoFnCharValue();
            charValue.setValue(fn.getPhone_id());
            this.updateByCondition(charValue, new DBCondition(CoFnCharValue.Field.productId, prodId), new DBCondition(
            		CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_NUMBER), new DBCondition(
            				CoFnCharValue.Field.value, fn.getOld_phone_id()));
        }
    }

    /**
     * add user friend numbers
     */
    public void addUserFNs(CoProd prod, FN[] fns) throws IMSException
    {
        Long prodId = prod.getProductId();
        Integer prodOfferId = prod.getProductOfferingId();
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
    
        Integer fnCount = SysUtil.getInt(SysCodeDefine.busi.DEFAULT_FNS,10);
        
        if (ProjectUtil.is_TH_AIS())	// 上海版本不需要控制总数  modify by wangyh3 2012-05-03
        {
            PmProductOfferSpecChar prop = prodCmp.getOfferSpecCharBySpecCharIdAndOfferId(SpecCodeDefine.FRIENDNUMBER_NUMBER,
                    prodOfferId);
            if (prop != null)
            {
                try
                {
                    fnCount = Integer.parseInt(prop.getValue());
                }
                catch (NumberFormatException e)
                {
                    //销售品上配置的数据不对，则还是使用配置的默认值
                	imsLogger.error(e,e);
                }
            }
            
	        if (fns.length > fnCount)
	        {
	            IMSUtil.throwBusiException(ErrorCodeDefine.COUNT_NOT_ENOUGH, prodId);
	        }
        }

        List<CoFnCharValue> charValueList = prodCmp.queryFnCharValue(prodId, SpecCodeDefine.FRIENDNUMBER_COUNT);
        
        if (charValueList != null && charValueList.size() > 0)
        {
        	CoFnCharValue charValue = charValueList.get(0);// 一个亲情产品只有一个数量
            int temp = 0;
            try
            {
                temp = Integer.parseInt(charValue.getValue());
            }
            catch (Exception e)
            {
                temp = 0;
            }
            
            if (ProjectUtil.is_TH_AIS())	// 上海版本不需要控制总数  modify by wangyh3 2012-05-03
            {
	            if ((fns.length + temp) > fnCount)
	            {
	                throw IMSUtil.throwBusiException(ErrorCodeDefine.COUNT_NOT_ENOUGH, prodId);
	            }
            }
            
            // 更新数量
            charValue = new CoFnCharValue();
            charValue.setValue(String.valueOf(fns.length + temp));
            this.updateByCondition(charValue, new DBCondition(CoFnCharValue.Field.productId, prodId), new DBCondition(
                    CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_COUNT));

        }
        else
        {// 插入数量
            CoFnCharValue cv = createCoFnCharValue(prodId, 0, SpecCodeDefine.FRIENDNUMBER_COUNT,
                    String.valueOf(fns.length), IMSUtil.getDefaultExpireDate(), context.getRequestDate(), prod.getObjectId(),
                    prod.getObjectType());
            this.insert(cv);
        }
        List<CoFnCharValue> fnCharValues = new ArrayList<CoFnCharValue>();
        for (int i = 0; i < fns.length; i++)
        {
            FN fn = fns[i];
            if (CommonUtil.isEmpty(fn.getPhone_id()))
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_ADD_FN_NULL);
            }
            fnCharValues.add(addUserFN(fn, prod));
        }
        insertBatch(fnCharValues);
    }

    private CoFnCharValue addUserFN(FN fn, CoProd prod) throws IMSException
    {
        long groupId = DBUtil.getSequence();
        Long prodId = prod.getProductId();
        CoFnCharValue charValue = this.querySingle(CoFnCharValue.class, new DBCondition(CoFnCharValue.Field.productId,
                prodId), new DBCondition(CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_NUMBER), new DBCondition(
                		CoFnCharValue.Field.value, fn.getPhone_id()));
        if (charValue != null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.UESR_FN_EXIST_PHONE);
        }

        Date validDate = IMSUtil.formatDate(fn.getValid_date(), context.getRequestDate());
        Date expireDate = DateUtil.checkForNull(DateUtil.getFormattedDate(fn.getExpire_date()), IMSUtil.getDefaultExpireDate());// 传入的expireDate
        if (expireDate.getTime() > prod.getExpireDate().getTime())
        {// 如果传入的expireDate比产品的expireDate大
         // 设置为小的
            expireDate = prod.getExpireDate();
        }

        CoFnCharValue coFnCharValue = createCoFnCharValue(prodId, groupId, SpecCodeDefine.FRIENDNUMBER_NUMBER, fn.getPhone_id(),
                expireDate, validDate, prod.getObjectId(), prod.getObjectType());

        return coFnCharValue;
    }
    
    /**
     * @Description: 根据用户Id删除用户
     * @param userId
     */
    public void deleteUserByUserId(Long userId)
    {
//        CmResource res = new CmResource();
//        res.setResourceId(userId);
        deleteByCondition(CmResource.class, new DBCondition(CmResource.Field.resourceId, userId));
    }

    /**
     * delete user friend numbers
     */
    public void deleteUserFNs(Long prodId, FN[] fns) throws IMSException
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        for (int i = 0; i < fns.length; i++)
        {
            FN fn = fns[i];
            deleteUserFN(fn, prodId);
        }
        List<CoFnCharValue> charValueList = prodCmp.queryFnCharValue(prodId, SpecCodeDefine.FRIENDNUMBER_COUNT);
        if (charValueList != null && charValueList.size() > 0)
        {
            CoFnCharValue charValue = charValueList.get(0);// 一个亲情产品只有一个数量
            int temp = 0;
            try
            {
                temp = Integer.parseInt(charValue.getValue());
            }
            catch (Exception e)
            {
                temp = 0;
            }
            charValue = new CoFnCharValue();
            charValue.setValue(String.valueOf(temp - fns.length));
            this.updateByCondition(charValue, new DBCondition(CoFnCharValue.Field.productId, prodId), new DBCondition(
            		CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_COUNT));
        }
    }
    
    private void deleteUserFN(FN fn, long productId) throws IMSException
    {
        long groupId = 0;
        CoFnCharValue charValue = this.querySingle(CoFnCharValue.class, new DBCondition(CoFnCharValue.Field.productId,
                productId), new DBCondition(CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_NUMBER),
                new DBCondition(CoFnCharValue.Field.value, fn.getOld_phone_id()));
        if (charValue == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PHONE_NOT_USER_FN, fn.getOld_phone_id());
        }
        groupId = charValue.getGroupId();// 获取groupId

        this.deleteByCondition(CoFnCharValue.class, 
        		new DBCondition(CoFnCharValue.Field.specCharId, SpecCodeDefine.FRIENDNUMBER_NUMBER),
        		new DBCondition(CoFnCharValue.Field.productId, productId),
        		new DBCondition(CoFnCharValue.Field.groupId, groupId));
    }


    /**
     * 删除黑白名单
     */
    public void deleteCallScreen(SCallScreenNo callScreenNo, long productId)
    {
     // caohm5 add 2012-03-08
        String phoneId = callScreenNo.getPhone_id();
        // yangjh add 2012-03-20
        CoProd prod = context.getComponent(BaseProductComponent.class).loadProd(productId);
        Long objectId = prod.getObjectId();
        // @Date 2012-07-26 zhangzj3  [45883]CallScreening需要检验号码是否重复,
        // 删除时，如果只传入号码，就把所有未失效的记录删除，如果还有其他的条件传入，需要一一匹配，匹配不到则报错。
        // 因为我们在对已生效的记录操作时，会把一条记录拆分为两条记录，所以外面传进生效日期也不加进去判断
        List<CoProdCharValue> charValueList = DBUtil.query(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, productId), new DBCondition(
                CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_NUMBER), new DBCondition(
                CoProdCharValue.Field.value, callScreenNo.getPhone_id()), new DBCondition(CoProdCharValue.Field.objectId,
                        objectId),new DBCondition(CoProdCharValue.Field.expireDate,context.getRequestDate(),Operator.GREAT));
        if(CommonUtil.isEmpty(charValueList)){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_PHONE_NOT_USERS_CALLSCREEN, phoneId);
        }else{
            List<Long> delGroupIdList = new ArrayList<Long>();
            for(CoProdCharValue coProdCharValue : charValueList){
                Long groupId = coProdCharValue.getGroupId();
                List<CoProdCharValue> temList = DBUtil.query(CoProdCharValue.class, 
                        new DBCondition(CoProdCharValue.Field.productId, productId),
                        new DBCondition(CoProdCharValue.Field.groupId, groupId),
                        new DBCondition(CoProdCharValue.Field.expireDate,context.getRequestDate(),Operator.GREAT));
                if(CommonUtil.isNotEmpty(temList)){
                    for(CoProdCharValue charValue : temList){
                        if(callScreenNo.getExpire_date() != null){
                            if(coProdCharValue.getExpireDate().getTime() != IMSUtil.formatExpireDate(callScreenNo.getExpire_date()).getTime()){
                                continue;
                            }
                        }
                        if(callScreenNo.getWeek_start() != null && charValue.getSpecCharId().intValue() ==  SpecCodeDefine.MCS_NUMBER_WEEK_START){
                            if(CommonUtil.short2String(callScreenNo.getWeek_start()) != charValue.getValue()){
                                continue;
                            }
                        }
                        if(callScreenNo.getWeek_stop() != null && charValue.getSpecCharId().intValue() ==  SpecCodeDefine.MCS_NUMBER_WEEK_END){
                            if(CommonUtil.short2String(callScreenNo.getWeek_stop()) != charValue.getValue()){
                                continue;
                            }
                        }
                        if(callScreenNo.getEffect_time() != null && charValue.getSpecCharId().intValue() == SpecCodeDefine.MCS_NUMBER_EFFECT_TIME){
                            if(callScreenNo.getEffect_time() != charValue.getValue()){
                                continue;
                            }
                        }
                        if(callScreenNo.getExpire_time() != null && charValue.getSpecCharId().intValue() == SpecCodeDefine.MCS_NUMBER_EXPIRE_TIME){
                            if(callScreenNo.getExpire_time() != charValue.getValue()){
                                continue;
                            }
                        }
                        if(callScreenNo.getList_type() != null && charValue.getSpecCharId().intValue() == SpecCodeDefine.MCS_NUMBER_TYPE){
                            if(CommonUtil.short2String(callScreenNo.getList_type()) != charValue.getValue()){
                                continue;
                            }
                        }
                        if(callScreenNo.getRouting_method() != null && charValue.getSpecCharId().intValue() == SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD){
                            if(CommonUtil.short2String(callScreenNo.getRouting_method()) != charValue.getValue()){
                                continue;
                            }
                        }
                        delGroupIdList.add(groupId);
                        break;
                    }
                }
            }
            if(CommonUtil.isNotEmpty(delGroupIdList)){
                this.deleteByCondition(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, productId),
                        new DBCondition(CoProdCharValue.Field.groupId, delGroupIdList,Operator.IN));
            }else{
                throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_PHONE_NOT_USERS_CALLSCREEN, phoneId);
            }
        }
    }

    /**
     * 增加黑白名单
     * 2012-08-17 luojb 代码评审修改：userId改为objectId
     */
    public void addCallScreen(SCallScreenNo callScreenNo, CoProd prod)
    {
        // yangjh add 2012-03-20
        Long objectId = prod.getObjectId();
        int objectType = prod.getObjectType();

        List<CoProdCharValue> list = new ArrayList<CoProdCharValue>();
        long productId = prod.getProductId();
        Long groupId = DBUtil.getSequence();
        
        // caohm5 add 2012-03-08
        CoProdCharValue charValue = null;
        //@Date 2012-07-26 zhangzj3  [45883]CallScreening需要检验号码是否重复
        Date validDate = IMSUtil.formatDate(callScreenNo.getValid_date(), context.getRequestDate());
        Date expireDate = IMSUtil.formatExpireDate(callScreenNo.getExpire_date());
        // 如果传入的expireDate比产品的expireDate大, 设置为小的
        if (expireDate.getTime() > prod.getExpireDate().getTime()){
            expireDate = prod.getExpireDate();
        }
        List<CoProdCharValue> charValueList = DBUtil.query(CoProdCharValue.class, 
                new DBCondition(CoProdCharValue.Field.productId, productId), 
                new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_NUMBER), 
                new DBCondition(CoProdCharValue.Field.value, callScreenNo.getPhone_id()), 
                new DBCondition(CoProdCharValue.Field.objectId,objectId),
                new DBCondition(CoProdCharValue.Field.expireDate,context.getRequestDate(),Operator.GREAT));
        //如果传入的号码已经存在，就判断它的生失效时间:如果传进来的生效区间与未失效记录的生效区间有合集则报错。
        if(CommonUtil.isNotEmpty(charValueList)){
            for(CoProdCharValue charValue2 : charValueList){
                if (charValue2.getExpireDate() != null && charValue2.getValidDate() != null){
                    if(!(charValue2.getValidDate().getTime() > expireDate.getTime() || charValue2.getExpireDate().getTime() < validDate.getTime()) ){
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_NEW_ACCT_IS_UNDER_CUS, callScreenNo.getPhone_id());
                    }
                }
            }
        }
        
        Short weekStart = callScreenNo.getWeek_start();
        Short weekStop = callScreenNo.getWeek_stop();
        if (weekStart == null)
        {
            weekStart = EnumCodeDefine.MCS_WEEK_SUNDAY;
        }
        if (weekStop == null)
        {
            weekStop = EnumCodeDefine.MCS_WEEK_SATURDAY;
        }
        // 默认语音服务
        list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_SEVICE, String.valueOf(50001), expireDate,
                validDate, objectId, objectType));
        list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_RULE, String.valueOf(objectId), expireDate,
                validDate, objectId, objectType));
        list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_NUMBER,
                String.valueOf(callScreenNo.getPhone_id()), expireDate, validDate, objectId, objectType));
        if (callScreenNo.getIr_route_flag() != null)
        {
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_ROUTE_FLAG,
                    String.valueOf(callScreenNo.getIr_route_flag()), expireDate, validDate, objectId, objectType));
        }
        else
        {
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_ROUTE_FLAG,
                    String.valueOf(SpecCodeDefine.MCS_NUMBER_ROUTE_FLAG_DEFAULT_VALUE), expireDate, validDate, objectId, objectType));
        }
        if (callScreenNo.getRouting_method() != null)
        {
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD,
                    String.valueOf(callScreenNo.getRouting_method()), expireDate, validDate, objectId, objectType));
        }
        /*if (callScreenNo.getList_type() != null)
        {
            // caohm5 add 2012-03-08
            charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, productId),
                    new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_TYPE), new DBCondition(
                            CoProdCharValue.Field.groupId, SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE), new DBCondition(
                            CoProdCharValue.Field.objectId, objectId), new DBCondition(CoProdCharValue.Field.objectType,
                            EnumCodeDefine.PROD_OBJECTTYPE_DEV));
            if (charValue != null)
            { // 用户的黑白名单类型是黑和白 直接添加
                if (charValue.getValue().equals(String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_BLACK_AND_WHITELIST)))
                {
                    list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_TYPE,
                            String.valueOf(callScreenNo.getList_type()), expireDate, validDate, objectId, objectType));
                }
                else
                {// 否则判断类型是否对应
                    if (!charValue.getValue().equals(String.valueOf(callScreenNo.getList_type())))
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_LIST_TYPE_NOT_FIX);
                    }
                    else
                    {
                        list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_TYPE,
                                String.valueOf(callScreenNo.getList_type()), expireDate, validDate, objectId, objectType));
                    }
                }
            }
            else
            {
                list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_TYPE,
                        String.valueOf(callScreenNo.getList_type()), expireDate, validDate, objectId, objectType));
            }

        }
        else
        {
            // 获取用户黑白名单类型
            charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId, productId),
                    new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_TYPE), new DBCondition(
                            CoProdCharValue.Field.groupId, SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE), new DBCondition(
                            CoProdCharValue.Field.objectId, objectId), new DBCondition(CoProdCharValue.Field.objectType,
                            EnumCodeDefine.PROD_OBJECTTYPE_DEV));
            if (charValue != null)
            {
                if (charValue.getValue().equals(String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_BLACK_AND_WHITELIST)))
                {// 用户黑白名单类型为
                 // 黑and白，则设置名单列表默认为黑
                    list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_TYPE,
                            String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_BLACKLIST), expireDate, validDate, objectId, objectType));
                }
                else
                {
                    list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_TYPE,
                            String.valueOf(charValue.getValue()), expireDate, validDate, objectId, objectType));
                }
            }
            else
            {
                list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_TYPE,
                        String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_BLACKLIST), expireDate, validDate, objectId, objectType));// 默认设置为黑
            }
        }*/
        list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_TYPE,
                String.valueOf(callScreenNo.getList_type()), expireDate, validDate, objectId, objectType));
        list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_WEEK_END, String.valueOf(weekStop),
                expireDate, validDate, objectId, objectType));
        list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_WEEK_START, String.valueOf(weekStart),
                expireDate, validDate, objectId, objectType));
        if (!CommonUtil.isEmpty(callScreenNo.getRoute_number()))
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_ROUTE_NUMBER,
                    String.valueOf(callScreenNo.getRoute_number()), expireDate, validDate, objectId, objectType));
        // ljc add 2012-02-08
        if (CommonUtil.isNotEmpty(callScreenNo.getEffect_time()))
        {
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_EFFECT_TIME,
                    callScreenNo.getEffect_time(), expireDate, validDate, objectId, objectType));
        }
        else
        {
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_EFFECT_TIME, "000000", expireDate,
                    validDate, objectId, objectType));
        }
        if (CommonUtil.isNotEmpty(callScreenNo.getExpire_time()))
        {
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_EXPIRE_TIME,
                    callScreenNo.getExpire_time(), expireDate, validDate, objectId, objectType));
        }
        else
        {
            list.add(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_EXPIRE_TIME, "235959", expireDate,
                    validDate, objectId, objectType));
        }
        this.insertBatch(list);
    }

    /**
     * 
     * yangjh 2012-9-27 获取新增用户的黑白名单类型
     * @param callScreenNo
     * @param prod
     * @return
     */
    public short getListType(SCallScreenNo callScreenNo, CoProdCharValue charValue)
    {
        if (callScreenNo.getList_type() != null)
        {
            if (charValue != null)
            { // 用户的黑白名单类型是黑和白 直接添加
                if (charValue.getValue().equals(String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_BLACK_AND_WHITELIST)))
                {
                    return callScreenNo.getList_type();
                }
                else
                {// 否则判断类型是否对应
                    if (!charValue.getValue().equals(String.valueOf(callScreenNo.getList_type())))
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_LIST_TYPE_NOT_FIX);
                    }
                    else
                    {
                        return callScreenNo.getList_type();
                    }
                }
            }
            else
            {
                return callScreenNo.getList_type();
            }
        }else{
            if (charValue != null)
            {
                if (charValue.getValue().equals(String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_BLACK_AND_WHITELIST)))
                {// 用户黑白名单类型为
                 // 黑and白，则设置名单列表默认为黑
                    return EnumCodeDefine.MCS_CS_NOTYPE_BLACKLIST;
                }
                else
                {
                    //与用户的黑白名单类型一致
                    return Short.valueOf(charValue.getValue());
                }
            }
            else
            {
                // 默认设置为黑
                return EnumCodeDefine.MCS_CS_NOTYPE_BLACKLIST;
            }
        }
    }
    
    /**
     * 设置callScreen类型 caohm5 2012-03-08 add 用户编号userId 查询的时候增加objectId和objectType
     */
    public void setCallScreenType(CoProd coProd, long call_screen_no_type, Long userId) throws IMSException
    {
        // yangjh add 2012-03-20
        userId = coProd.getObjectId();
        int objectType = coProd.getObjectType();
        if (call_screen_no_type != EnumCodeDefine.MCS_CS_NOTYPE_BLACK_AND_WHITELIST)
        {
            boolean whiteFlag = false;
            boolean blackFlag = false;
            BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
            List<CoProdCharValue> charValues = prodCmp.queryProdCharValue(coProd.getProductId());
            if (!CommonUtil.isEmpty(charValues))
            {
                for (CoProdCharValue value : charValues)
                {
                    if (value.getSpecCharId().intValue() == SpecCodeDefine.MCS_NUMBER_NUMBER)
                    {// 获取号码
                        Long groupId = value.getGroupId();
                        for (CoProdCharValue charValue : charValues)
                        {
                            if (charValue.getGroupId().longValue() == groupId)
                            {
                                if (charValue.getSpecCharId() == SpecCodeDefine.MCS_NUMBER_TYPE)
                                {
                                    if (charValue.getValue().equals(String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_WHITEIST)))
                                    {
                                        whiteFlag = true;
                                        break;
                                    }
                                    else if (charValue.getValue().equals(String.valueOf(EnumCodeDefine.MCS_CS_NOTYPE_BLACKLIST)))
                                    {
                                        blackFlag = true;
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }
            }
            if (whiteFlag)
            {// 有白名单存在，不能变成黑名单服务
                if (call_screen_no_type == EnumCodeDefine.MCS_CS_NOTYPE_BLACKLIST)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_CANNOT_SET_BLACK_TYPE);
                }
            }
            if (blackFlag)
            {// 有黑名单存在，不能变成白名单服务
                if (call_screen_no_type == EnumCodeDefine.MCS_CS_NOTYPE_WHITEIST)
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.MCS_CANNOT_SET_WHITE_TYPE);
            }
        }

        CoProdCharValue charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_TYPE),
                new DBCondition(CoProdCharValue.Field.groupId, SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE), new DBCondition(
                        CoProdCharValue.Field.objectId, userId), new DBCondition(CoProdCharValue.Field.objectType, objectType));
        if (charValue == null)
        {// 如果没有就新增 有就修改
            charValue = createCoProdCharValue(coProd.getProductId(), SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE,
                    SpecCodeDefine.MCS_NUMBER_TYPE, String.valueOf(call_screen_no_type), null, null, coProd.getObjectId(),
                    coProd.getObjectType());// groupId为0
            this.insert(charValue);
        }
        else
        {
            charValue = new CoProdCharValue();
            charValue.setValue(String.valueOf(String.valueOf(call_screen_no_type)));
            this.updateByCondition(charValue, new DBCondition(CoProdCharValue.Field.productId, coProd.getProductId()),
                    new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_TYPE), new DBCondition(
                            CoProdCharValue.Field.groupId, SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE), new DBCondition(
                            CoProdCharValue.Field.objectId, userId),
                    new DBCondition(CoProdCharValue.Field.objectType, objectType));
        }
    }

    /**
     * 开启CLIQ
     */
    public void openUserCLIQ(CoProd coProd) throws IMSException
    {
        // caohm add2012-03-08
        CoProdCharValue charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_CLIR),
                new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                        CoProdCharValue.Field.objectType, coProd.getObjectType()));
        if (charValue == null)
        {// 如果没有就新增 有就修改
            charValue = createCoProdCharValue(coProd.getProductId(), SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE,
                    SpecCodeDefine.MCS_NUMBER_CLIR, String.valueOf(EnumCodeDefine.MCS_CILR_OPEN), coProd.getExpireDate(),
                    coProd.getValidDate(), coProd.getObjectId(), coProd.getObjectType());// groupId为0
            this.insert(charValue);
        }
        else
        {
            charValue = new CoProdCharValue();
            charValue.setValue(String.valueOf(String.valueOf(EnumCodeDefine.MCS_CILR_OPEN)));
            this.updateByCondition(charValue, new DBCondition(CoProdCharValue.Field.productId, coProd.getProductId()),
                    new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_CLIR), new DBCondition(
                            CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                            CoProdCharValue.Field.objectType, coProd.getObjectType()));
        }
    }

    /**
     * 关闭CLIQ
     */
    public void closeUserCLIQ(CoProd coProd) throws IMSException
    {
        // caohm add 2012-03-08
        CoProdCharValue charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_CLIR),
                new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                        CoProdCharValue.Field.objectType, coProd.getObjectType()));
        if (charValue == null)
        {// 如果没有就新增 有就修改
            charValue = createCoProdCharValue(coProd.getProductId(), SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE,
                    SpecCodeDefine.MCS_NUMBER_CLIR, String.valueOf(EnumCodeDefine.MCS_CILR_CLOSE), coProd.getExpireDate(),
                    coProd.getValidDate(), coProd.getObjectId(), coProd.getObjectType());// groupId为0
            this.insert(charValue);
        }
        else
        {
            charValue = new CoProdCharValue();
            charValue.setValue(String.valueOf(String.valueOf(EnumCodeDefine.MCS_CILR_CLOSE)));
            this.updateByCondition(charValue, new DBCondition(CoProdCharValue.Field.productId, coProd.getProductId()),
                    new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_CLIR), new DBCondition(
                            CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                            CoProdCharValue.Field.objectType, coProd.getObjectType()));
        }
    }

    /**
     * 开启notify ljc caohm5 add 2012-03-08 objectId and objectType
     */
    public void openUserBlackListCallNotify(CoProd coProd) throws IMSException
    {

        CoProdCharValue charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdCharValue.Field.specCharId,
                SpecCodeDefine.MCS_NUMBER_NOTIFICATION_FLAG),
                new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                        CoProdCharValue.Field.objectType, coProd.getObjectType()));
        if (charValue == null)
        {// 如果没有就新增 有就修改
            charValue = createCoProdCharValue(coProd.getProductId(), SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE,
                    SpecCodeDefine.MCS_NUMBER_NOTIFICATION_FLAG, String.valueOf(EnumCodeDefine.MCS_NOTIFY_OPEN),
                    coProd.getExpireDate(), coProd.getValidDate(), coProd.getObjectId(), coProd.getObjectType());// groupId为0
            this.insert(charValue);
        }
        else
        {
            charValue = new CoProdCharValue();
            charValue.setValue(String.valueOf(String.valueOf(EnumCodeDefine.MCS_NOTIFY_OPEN)));
            this.updateByCondition(charValue, new DBCondition(CoProdCharValue.Field.productId, coProd.getProductId()),
                    new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_NOTIFICATION_FLAG),
                    new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                            CoProdCharValue.Field.objectType, coProd.getObjectType()));
        }
        CoProdCharValue notifyIdValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdCharValue.Field.specCharId,
                SpecCodeDefine.MCS_NUMBER_NOTIFICATION_ID),
                new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                        CoProdCharValue.Field.objectType, coProd.getObjectType()));
        if (notifyIdValue == null)
        {// 没有就需要新建一个，有就不做操作
            PmProductSpecCharValue pscv = this.querySingle(PmProductSpecCharValue.class, new DBCondition(PmProductSpecCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_NOTIFICATION_ID));
            if (pscv != null)
            {
                charValue = createCoProdCharValue(coProd.getProductId(), SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE,
                        SpecCodeDefine.MCS_NUMBER_NOTIFICATION_ID, pscv.getValue(), coProd.getExpireDate(),
                        coProd.getValidDate(), coProd.getObjectId(), coProd.getObjectType());// groupId为0
                this.insert(charValue);
            }
        }
    }

    /**
     * 开启notify ljc caohm5 add 2012-03-08 objectId and objectType
     */
    public void closeUserBlackListCallNotify(CoProd coProd) throws IMSException
    {
        CoProdCharValue charValue = this.querySingle(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.productId,
                coProd.getProductId()), new DBCondition(CoProdCharValue.Field.specCharId,
                SpecCodeDefine.MCS_NUMBER_NOTIFICATION_FLAG),
                new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                        CoProdCharValue.Field.objectType, coProd.getObjectType()));
        if (charValue == null)
        {// 如果没有就新增 有就修改
            charValue = createCoProdCharValue(coProd.getProductId(), SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE,
                    SpecCodeDefine.MCS_NUMBER_NOTIFICATION_FLAG, String.valueOf(EnumCodeDefine.MCS_NOTIFY_CLOSE),
                    coProd.getExpireDate(), coProd.getValidDate(), coProd.getObjectId(), coProd.getObjectType());// groupId为0
            this.insert(charValue);
        }
        else
        {
            charValue = new CoProdCharValue();
            charValue.setValue(String.valueOf(String.valueOf(EnumCodeDefine.MCS_NOTIFY_CLOSE)));
            this.updateByCondition(charValue, new DBCondition(CoProdCharValue.Field.productId, coProd.getProductId()),
                    new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_NOTIFICATION_FLAG),
                    new DBCondition(CoProdCharValue.Field.objectId, coProd.getObjectId()), new DBCondition(
                            CoProdCharValue.Field.objectType, coProd.getObjectType()));
        }
    }

    /**
     * 设置转移
     */
    public void routCallScreenNum(SCallScreenNo callScreenNo, CoProd prod)
    {
        if (callScreenNo.getRouting_method() == null)
        {
            callScreenNo.setRouting_method((short) 2);
        }
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        Long productId = prod.getProductId();
        // add yangjh 2012-02-09
        if (CommonUtil.isValid(callScreenNo.getPhone_id()))
        {
            Long groupId = DBUtil.getSequence(CoProdCharValue.class);
            //@Date 2012-04-24 lijc3 修改rout_method入参错误
            this.insert(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_ROUTE_NUMBER,
                    callScreenNo.getPhone_id(), prod.getExpireDate(), context.getRequestDate(), prod.getObjectId(),
                    prod.getObjectType()));
            this.insert(createCoProdCharValue(productId, groupId, SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD,
                    CommonUtil.short2String(callScreenNo.getRouting_method()),  prod.getExpireDate(),context.getRequestDate(),
                    prod.getObjectId(), prod.getObjectType()));
        }
        else
        {
            CoProdCharValue charValue = prodCmp.queryCharValue(productId, SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD,
                    (long) SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE);// 获取转移号码
            if (charValue != null)
            {
                charValue = new CoProdCharValue();
                charValue.setValue(String.valueOf(callScreenNo.getRouting_method()));
//                CoProdCharValue valueWhere = new CoProdCharValue();
//                valueWhere.setProductId(productId);
//                valueWhere.setSpecCharId(SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD);
//                valueWhere.setGroupId((long) SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE);
                this.updateByCondition(charValue, new DBCondition(CoProdCharValue.Field.productId, productId),
                        new DBCondition(CoProdCharValue.Field.specCharId, SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD),
                        new DBCondition(CoProdCharValue.Field.groupId, SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE));
            }
            else
            {
            	//@Date 2012-07-05 wangdw5 : On_Site #50234 传入的createCoProdCharValue的validate和expiredate顺序反了
                this.insert(createCoProdCharValue(productId, SpecCodeDefine.MCS_NUMBER_GROUP_DEFAULT_VALUE,
                        SpecCodeDefine.MCS_NUMBER_ROUTE_METHOD, String.valueOf(callScreenNo.getRouting_method()),
                        prod.getExpireDate(), context.getRequestDate(),  prod.getObjectId(), prod.getObjectType()));
            }
        }

    }

    /**
     * 修改用户扩展信息表CM_RES_EXT
     * 
     * @author yanchuan 2011-09-16
     * @Date 2012-09-19 yugb :[57408]API中字段清空方案
     */
    public void updateCmResExt(long userId, ScpProfileInfo profileInfo)
    {
        // 查询用户扩展表
        CmResExt cmResExt = queryCmResExt(userId);
        if (profileInfo == null)
            return;
        //yugb 如果找不到对应的扩展字段记录，则需要新插入一条记录进去,否则更新记录
        Map<String, String> paramMap = new HashMap<String, String>();
        if(cmResExt == null){
       	 paramMap.put(ConstantDefine.EXTPARAM_USER_PHFLAG, CommonUtil.short2String(profileInfo.getFph_flag()));
            paramMap.put(ConstantDefine.EXTPARAM_USER_USSDCALLBACKFLAG,  CommonUtil.short2String(profileInfo.getUssd_callback_flag()));
            paramMap.put(ConstantDefine.EXTPARAM_USER_ICFFLAG,  CommonUtil.short2String(profileInfo.getIcf_flag()));
            paramMap.put(ConstantDefine.EXTPARAM_USER_ICFNUMBER, profileInfo.getIcf_number());
       	 this.createCmResExt(paramMap, userId);
        }else{
        CmResExt resExt = new CmResExt();
        //@Date 2012-07-12 wangdw5 :　#51462 update时当库中字段为空才设置默认值
        if (profileInfo.getFph_flag() != null){
        	if(IMSUtil.isClean(profileInfo.getFph_flag()))
        		resExt.setProp1(null);
        	else
        		resExt.setProp1(profileInfo.getFph_flag().toString());
        }
        else if(cmResExt.getProp1() == null)
            resExt.setProp1("0");
        if (profileInfo.getUssd_callback_flag() != null){
        	if(IMSUtil.isClean(profileInfo.getUssd_callback_flag()))
        		resExt.setProp3(null);
        	else
        		resExt.setProp3(profileInfo.getUssd_callback_flag().toString());
        }
        else if(cmResExt.getProp3() == null)
            resExt.setProp3("0");
        if (profileInfo.getIcf_flag() != null)
        {
        	if(IMSUtil.isClean(profileInfo.getIcf_flag()))
        		 resExt.setProp4(null);
        	else
        		resExt.setProp4(profileInfo.getIcf_flag().toString());
            if (profileInfo.getIcf_flag().shortValue() == EnumCodeDefine.SCP_ICF_FLAG_OPEN && profileInfo.getIcf_number() != null)
                resExt.setProp6(CommonUtil.short2String(EnumCodeDefine.SCP_NUMBER_STATUE_ACTIVE));
            else if (profileInfo.getIcf_flag().shortValue() == EnumCodeDefine.SCP_ICF_FLAG_ACTIVE
                    && (cmResExt != null && cmResExt.getProp5() != null))
            {
                resExt.setProp6(CommonUtil.short2String(EnumCodeDefine.SCP_NUMBER_STATUE_ACTIVE));
            }
            else if (profileInfo.getIcf_flag().shortValue() == EnumCodeDefine.SCP_ICF_FLAG_CLOSE)
                resExt.setProp6(CommonUtil.short2String(EnumCodeDefine.SCP_NUMBER_STATUE_INVALID));
        }
        if (!CommonUtil.isEmpty(profileInfo.getIcf_number()))
        {
            resExt.setProp5(profileInfo.getIcf_number());
        }
        else if(IMSUtil.isClean(profileInfo.getIcf_number()))
        	resExt.setProp5(null);
//        CmResExt dmResExt = new CmResExt();
//        dmResExt.setResourceId(userId);
        this.updateByCondition(resExt, new DBCondition(CmResExt.Field.resourceId, userId));
        }
    }

    /**
     * 修改CM_RES_AUTH表
     * 
     * @author yanchuan 2011-09-16
     * @Date 2012-09-19 yugb :[57408]API中字段清空方案
     */
    public void updateCmResAuth(SUserAuth userAuth)
    {
        if (queryUserAuth(userAuth.getUser_id()) == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.CMRESAUTH_NOT_EXIST, userAuth.getUser_id());
        CmResAuth resAuth = new CmResAuth();
        if (userAuth.getHPLMN() != null){
        	if(IMSUtil.isClean(userAuth.getHPLMN()))
        		resAuth.setHplmn(null);
        	else
        		resAuth.setHplmn(userAuth.getHPLMN().intValue());
        }
        if (userAuth.getInterIntraAuthority() != null){
        	if(IMSUtil.isClean(userAuth.getInterIntraAuthority() ))
        		resAuth.setInterIntraAuthority(null);
        	else
        		resAuth.setInterIntraAuthority(userAuth.getInterIntraAuthority().intValue());
        }
        if (userAuth.getInterIntraflag() != null){
        	if(IMSUtil.isClean(userAuth.getInterIntraflag()))
        		resAuth.setInterIntraFlag(null);
        	else
        		resAuth.setInterIntraFlag(userAuth.getInterIntraflag().intValue());
        }
        if (userAuth.getInternationalRoaming() != null){
        	if(IMSUtil.isClean(userAuth.getInternationalRoaming()))
        		resAuth.setInternationalRoaming(null);
        	else
        		resAuth.setInternationalRoaming(userAuth.getInternationalRoaming().intValue());
        }
        if (userAuth.getIRPromptflag() != null){
        	if(IMSUtil.isClean(userAuth.getIRPromptflag()))
        		resAuth.setIrpromptFlag(null);
        	else
        		resAuth.setIrpromptFlag(userAuth.getIRPromptflag().intValue());
        }
        if (userAuth.getIRSMSAuthority() != null){
        	if(IMSUtil.isClean(userAuth.getIRSMSAuthority()))
        		resAuth.setIrsmsAuthority(null);
        	else
        		resAuth.setIrsmsAuthority(userAuth.getIRSMSAuthority().intValue());
        }
        if (userAuth.getNationalRoaming() != null){
        	if(IMSUtil.isClean(userAuth.getNationalRoaming()))
        		resAuth.setNationalRoaming(null);
        	else
        		resAuth.setNationalRoaming(userAuth.getNationalRoaming().intValue());
        }
        if (userAuth.getRoamingAuthority() != null){
        	if(IMSUtil.isClean(userAuth.getRoamingAuthority()))
        		resAuth.setRoamingAuthority(null);
        	else
        		resAuth.setRoamingAuthority(userAuth.getRoamingAuthority().intValue());
        }	
//        CmResAuth cmResAuth = new CmResAuth();
//        cmResAuth.setResourceId(userAuth.getUser_id());
        if(DBUtil.isSetValue(resAuth))
        {
           this.updateByCondition(resAuth, new DBCondition(CmResAuth.Field.resourceId, userAuth.getUser_id()));
        }
    }

    /**
     * 设置用户服务首次使用时间 luojb 2011-10-17
     */
    public void setResServCycleFirstUsedDate(Long userId, Integer specId, Date firstUsedDate) throws IMSException
    {

        CmResServCycle where = new CmResServCycle();
        where.setResourceId(userId);
        where.setServiceSpecId(specId);

        CmResServCycle dmResServCycle = new CmResServCycle();
        dmResServCycle.setUsedFlag(EnumCodeDefine.RESOURCE_SERVICE_FLAG_USED);
        dmResServCycle.setFirstUsedDate(firstUsedDate);
        dmResServCycle.setSoDate(context.getRequestDate());
        dmResServCycle.setSoNbr(context.getSoNbr());
        updateByCondition(dmResServCycle, new DBCondition(CmResServCycle.Field.resourceId, userId),
                new DBCondition(CmResServCycle.Field.serviceSpecId, specId));
    }

    /**
     * 根据枚举值删除资源标识属性表
     * 
     * @author yanchuan 2011-10-27
     * @param phone_id
     */
    public void delResIdentity(String identity, int identity_type)
    {
//        CmResIdentity res = new CmResIdentity();
        List<DBCondition> con = new ArrayList<DBCondition>();
        con.add(new DBCondition(CmResIdentity.Field.identity, identity));
//        res.setIdentity(identity);
        // ljc add 如果是0不添加类型 表示删除电话号码
        if (identity_type != 0)
        {
//            res.setIdentityType(identity_type);
            con.add(new DBCondition(CmResIdentity.Field.identityType, identity_type));
        }
        this.deleteByCondition(CmResIdentity.class, con.toArray(new DBCondition[con.size()]));
    }
    
    //@Date 2012-09-17 yugb :删除资源标识和设备标识关系表
    public void deleteIdentityVsImei(Long userId){
    	 List<DBCondition> con = new ArrayList<DBCondition>();
    	 con.add(new DBCondition(CmIdentityVsImei.Field.resourceId, userId));
    	 this.deleteByCondition(CmIdentityVsImei.class, con.toArray(new DBCondition[con.size()]));
    }

    /**
     * 当phoneId已存在且其用户的生命周期状态未terminate时需要将对应资源标识属性表的记录置为失效
     * 
     * @author yanchuan 2011-11-28
     */
    public void modifyResIdentity(CmResource res, String phoneId, Date expireDate)
    {
        this.deleteByCondition_noInsert(CmResource.class, expireDate, new DBCondition(CmResource.Field.resourceId, res.getResourceId()));
        this.deleteByCondition_noInsert(CmResIdentity.class, expireDate,
                new DBCondition(CmResIdentity.Field.resourceId, res.getResourceId()));
    }

    /**
     * 创建bar service 同步表信息
     * 
     * @Description
     * @Author lijingcheng
     */
    public ImsBarServiceSync createImsBarServiceSync(IMS3hBean bean, Integer busiServiceId, int action, int reasonCode,
            Date actionDate)
    {
        ImsBarServiceSync serviceSync = new ImsBarServiceSync();
        // @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
        serviceSync.setId(DBUtil.getSequence());
        serviceSync.setAcctId(bean.getAcctId());
        serviceSync.setAction((int) action);
        // yangjh 2012-04-10 #43914
        serviceSync.setWorkOrderType((int) 5);
        if (actionDate == null)
        {
            serviceSync.setActionDate(context.getRequestDate());
        }
        else
        {
            serviceSync.setActionDate(actionDate);
        }
        serviceSync.setBusiServiceId(busiServiceId);
        serviceSync.setServiceName(queryServiceName(busiServiceId));
        serviceSync.setCreateDate(context.getRequestDate());
        serviceSync.setPhoneId(bean.getPhoneId());
        serviceSync.setUserId(bean.getUserId());
        serviceSync.setReasonCode(reasonCode);
        serviceSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        serviceSync.setSoNbr(context.getSoNbr());
        return serviceSync;
    }

    /**
     * 删除barservice同步表信息
     * 
     * @Description
     * @Author lijingcheng
     * @param bean
     * @param busiServiceId
     * @param action
     */
    public List deleteBarServiceSync(IMS3hBean bean, Integer busiServiceId, int action)
    {
//        ImsBarServiceSync sync = new ImsBarServiceSync();
//        sync.setUserId(bean.getUserId());
//        sync.setPhoneId(bean.getPhoneId());
//        sync.setAction(action);
//        List list = DBUtil.query(sync);
        DBCondition condition1 = new DBCondition(ImsBarServiceSync.Field.userId,bean.getUserId());
        DBCondition condition2 = new DBCondition(ImsBarServiceSync.Field.phoneId,bean.getPhoneId());
        DBCondition condition3 = new DBCondition(ImsBarServiceSync.Field.action,action);
        List<ImsBarServiceSync> list = DBUtil.query(ImsBarServiceSync.class,condition1,condition2,condition3);
        if (CommonUtil.isNotEmpty(list))
        {
//            DBUtil.deleteByCondition(sync);
            DBUtil.deleteByCondition(ImsBarServiceSync.class, condition1,condition2,condition3);
        }
        return list;
    }

    /**
     * @Description 生效时间大于当前的是特定服务
     * @Author lijingcheng
     * @param userId
     * @param serviceId
     * @return
     */
    public CmResServCycle queryServCycleByUserAddValidDate(Long userId, Integer serviceId)
    {
        return this.querySingle(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, userId), new DBCondition(
                CmResServCycle.Field.serviceSpecId, serviceId),
                new DBCondition(CmResServCycle.Field.validDate, context.getRequestDate(), Operator.LESS_EQUALS));
    }
    /**
     * @Description 生效时间大于当前的是所有服务
     * @Author linzt
     * @param userId
     * @param serviceId
     * @return
     */
    public List<CmResServCycle> queryServCycleByUserAddValidDate(Long userId)
    {
        return this.query(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId, userId),
                new DBCondition(CmResServCycle.Field.validDate, context.getRequestDate(), Operator.LESS_EQUALS));
    }

    /**
     * @Description unbar service 的时候进行服务状态修改 该服务可能是2条分段的数据，需要特殊处理 存在的第一条是suspend 后面一条是active 修改的时候 把第一条的失效时间和状态进行修改，第二天直接无插入删除
     * @Author lijingcheng
     * @param bean
     * @param busiServiceId
     */
    public void changeUserServStsByUnBarService(Long userId, Integer busiServiceId)
    {
        List<CmResServCycle> servList = querySevCycleByUserIdAndSrvId(userId, busiServiceId);
        if (servList.size() == 1)
        {
            CmResServCycle value = new CmResServCycle();
            value.setSts(EnumCodeDefine.SERVICE_CYCLESTS_ACTIVE);
//            CmResServCycle where = new CmResServCycle();
//            where.setResourceId(userId);
//            where.setServiceSpecId(busiServiceId);
            this.updateByCondition(value, new DBCondition(CmResServCycle.Field.resourceId,userId),
            		new DBCondition(CmResServCycle.Field.serviceSpecId,busiServiceId));
        }
        else
        {
            CmResServCycle activeCycle = null;
            if (servList.get(0).getSts() == EnumCodeDefine.SERVICE_CYCLESTS_ACTIVE)
            {
                activeCycle = servList.get(0);
            }
            else
            {
                activeCycle = servList.get(1);
            }
            // 删除激活的那条数据
//            CmResServCycle where = new CmResServCycle();
//            where.setResourceId(userId);
//            where.setServiceSpecId(busiServiceId);
//            where.setValidDate(activeCycle.getValidDate());
            DBUtil.deleteByCondition(CmResServCycle.class,new DBCondition(CmResServCycle.Field.resourceId,userId),
            		new DBCondition(CmResServCycle.Field.serviceSpecId,busiServiceId),
            		new DBCondition(CmResServCycle.Field.validDate,activeCycle.getValidDate()));
            // 修改suspend的数据位激活状态，失效时间更改为激活那条的失效时间
            CmResServCycle value = new CmResServCycle();
            value.setSts(EnumCodeDefine.SERVICE_CYCLESTS_ACTIVE);
            value.setExpireDate(activeCycle.getExpireDate());
//            where = new CmResServCycle();
//            where.setResourceId(userId);
//            where.setServiceSpecId(busiServiceId);
            this.updateByCondition(value, new DBCondition(CmResServCycle.Field.resourceId,userId),
            		new DBCondition(CmResServCycle.Field.serviceSpecId,busiServiceId));
        }
    }

    // liuyang8 2012-01-06 通过虚拟账户查询虚拟账户下的用户

    public List<CmResource> queryUsersByGroupId(Long acctId)
    {
        //sunpf3 2012-8-10 修改innerJoin。改为两次查询。
        List<CaAccountRv> caAccountRvList = DBUtil.query(CaAccountRv.class, 
                new DBCondition(CaAccountRv.Field.acctId, acctId));
        List<CmResource> resources = new ArrayList<CmResource>();
        if (!CommonUtil.isEmpty(caAccountRvList))
        {
            for (CaAccountRv caAccountRv : caAccountRvList)
            {
                Long resourceId = caAccountRv.getResourceId();
                List<CmResource> resourceList = DBUtil.query(CmResource.class, 
                        new DBCondition(CmResource.Field.resourceId, resourceId));
                if (!CommonUtil.isEmpty(resourceList))
                {
                    resources.addAll(resourceList);
                }
            }
        }
        else
        {
            return null;
        }
        return resources;
    }

    public CmResAuth queryUserAuth(Long userId)
    {
        if (!CommonUtil.isValid(userId))
        {
            return null;
        }

        return this.querySingle(CmResAuth.class, new DBCondition(CmResAuth.Field.resourceId, userId));
    }

    /**
     * @Description 创建免催免停
     * @Author lijingcheng
     */
    public CaNotifyExempt saveNotification(Integer objectType, Long objectId, Integer exemptionType,
            Integer exemptChannelId, Date validDate, Date expireDate,Integer billingType)
    {
        CaNotifyExempt CaNotifyExempt = new CaNotifyExempt();
        CaNotifyExempt.setObjectId(objectId);
        CaNotifyExempt.setObjectType(objectType);

        CaNotifyExempt.setValidDate(validDate);
        CaNotifyExempt.setCreateDate(context.getRequestDate());
        CaNotifyExempt.setExpireDate(expireDate);
        //Sh写死为后付费  add by zenglu  2012-10-08
        if (ProjectUtil.is_CN_SH())
        {
        	CaNotifyExempt.setBillingType(EnumCodeDefine.USER_PAYMODE_POSTPAID);
        }
        else
        {
        	// Date 2012-07-24 yangjh : story：52057 免催免停 增加billing_type的入库上发
        	if(billingType != null){
        		CaNotifyExempt.setBillingType(billingType);
        	}
        }
        // 通过系统表获取crm设置的免催免停标志
        if (ProjectUtil.is_CN_SH())
        {
            if (exemptionType == null)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "exemption_type");
            }
            CaNotifyExempt.setExemptionType(exemptionType);
            if (exemptionType == EnumCodeDefine.EXEMPT_TYPE_NO_PUSH)
            {
                CaNotifyExempt.setExemptionType(SysUtil.getInt(SysCodeDefine.busi.EXEMPT_TYPE_NO_PUSH));
            }
            else if (exemptionType == EnumCodeDefine.EXEMPT_TYPE_NO_STOP)
            {
                CaNotifyExempt.setExemptionType(SysUtil.getInt(SysCodeDefine.busi.EXEMPT_TYPE_NO_STOP));
            }
            else if (exemptionType == EnumCodeDefine.EXEMPT_TYPE_NO_PUSH_AND_STOP)
            {
                CaNotifyExempt.setExemptionType(SysUtil.getInt(SysCodeDefine.busi.EXEMPT_TYPE_NO_STOP_AND_PUSH));
            }
            else if (exemptionType == EnumCodeDefine.EXEMPT_TYPE_PUSH_AND_STOP)
            {
                CaNotifyExempt.setExemptionType(0);
            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "exemption_type", "0,1,2,3");
            }
        }
        else
        {
            // 如果为空报错提示，不允许设置免催免停
            if (exemptionType == null)
            {
                Integer exemptType = SysUtil.getInt(ConstantDefine.EXEMPT_CREDIT_LIMIT);
                if (exemptType == null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.NOT_CONFIG_FLAG);
                }
                CaNotifyExempt.setExemptionType(exemptType);
            }
            else
            {
                CaNotifyExempt.setExemptionType(exemptionType);
            }
        }

        CaNotifyExempt.setRecType((int) EnumCodeDefine.EXEMPTCREDITLIMIT_BUSINESS);
        // 0代表所有渠道不需要通知
        CaNotifyExempt.setExempChannelId(0);

        if(!ProjectUtil.is_CN_SH())
        {
        	if (CommonUtil.isValid(context.getOper().getOp_id()))
        	{
        		CaNotifyExempt.setStaffId(context.getOper().getOp_id().longValue());
        	}
        }

        return CaNotifyExempt;
    }
    /**
     * @Description: 判断是否是single balance下的主用户或家庭网中的主号码
     * @param titleRoleId
     * @return	 
     * @author: tangjl5
     * @Date: 2012-5-3
     * @Date 2012-5-31 tangjl5 master number的判断添加群的主用户的判断
     */
    public boolean isMasterNum(Integer titleRoleId)
    {
        if (titleRoleId != null && (titleRoleId == EnumCodeDefine.TITLEROLEID_SINGLE_BALANCE_MASTER_NUMBER || 
                titleRoleId == EnumCodeDefine.TITLEROLEID_MASTER_NUMBER))
        {
            return true;
        }
        return false;
    }

    /**
     * description:查询用户归属账户的付费模式
     * 
     * @author caohm5
     * @return 归属账户的付费模式
     */
    public Integer getUserBillingType(Long userId, String phoneId)
    {
        // CheckedComplex complex=null;
        Integer billType = null;
        if (!CommonUtil.isValid(userId) && !CommonUtil.isValid(phoneId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "userId ,phoneId");
        }
        // //上海优先校验userId
        // if(CommonUtil.isValid(userId)){
        // complex = context.getComponent(CheckComponent.class).checkUserId(userId);
        // if (CommonUtil.isValid(phoneId) && !complex.getPhoneId().equals(phoneId))
        // {
        // throw IMSUtil.throwBusiException(ErrorCodeDefine.CHECK_PHONEID_USERID_ERROR, userId, phoneId);
        // }
        // billType= complex.getAccount().getCreditSignControl();
        // return billType;
        // }
        // complex =context.getComponent(CheckComponent.class).checkPhoneId(phoneId);
        // billType=complex.getAccount().getCreditSignControl();
        // return billType;
        User3hBean userBean = context.get3hTree().loadUser3hBean(userId, phoneId);
        billType = userBean.getAccount().getCreditSignControl();
        return billType;
    }
    
    /**
     * description:修改用户的Feel like home和continue_flag标志
     * @param userId  
     * @param  flh_flag
     * @param  continue_flag
     * @param  brand_id
     * @return	
     * @author yangjh
     * @Date 2012-5-17
     * @Date 2012-5-18 flh_flag,continue_flag两个字段Integer转为Short
     * @date 2012-08-28 gaoqc5 允许修改brand_id字段，并上发到mdb
     */
    public void updateCmRes(Long userId,Short flh_flag,Short continue_flag,Short brand_id)
    {
    	CmResource res = new CmResource();
    	//2012-07-25 wukl 实体类更新后转化出错修改
    	if(flh_flag != null){
    		res.setFlhFlag(CommonUtil.ShortToInteger(flh_flag));
    	}
    	if(continue_flag != null){
    		res.setContinueFlag(CommonUtil.ShortToInteger(continue_flag));
    	}
    	if(null!=brand_id){
    		res.setBrandId(CommonUtil.ShortToInteger(brand_id));
    	}
    	if(DBUtil.isSetValue(res))
    	{
            this.updateByCondition(res, new DBCondition(CmResource.Field.resourceId,userId));
    	}
    }
    
    public List<CmResServCycle> queryResServCycle(Long resId)
    {
       return query(CmResServCycle.class, new DBCondition(CmResServCycle.Field.resourceId,resId));
    }

    /**
     * @Date 2012-10-26 yugb :User Story #62519
     * 查询失效的账户用户关系并取最新的一条数据
     * @param userId
     * @return
     */
    public CaAccountRes queryHistoryCaAcctRes(Long userId)
    {
    	List<CaAccountRes> caAccountResList = DBUtil.query(CaAccountRes.class, new OrderCondition[]{new OrderCondition(false,CaAccountRes.Field.soNbr)},
				null,new DBCondition(CaAccountRes.Field.resourceId,userId));
    	if(CommonUtil.isNotEmpty(caAccountResList))
    	{
    		return caAccountResList.get(0);
    	}
    	return  null;
    }
    
}
