package com.ailk.ims.component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sal.exceptions.SALException;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAccountEx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SCustomer;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SProdInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SQueryInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SQueryPara;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserEx;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.OBBufferErrorException;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.helper.FirstActiveHelper;
import com.ailk.ims.component.helper.ProdCycleHelper;
import com.ailk.ims.component.query.CacheQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PhoneIdHeadUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.openbilling.persistence.acct.entity.BiBusiPlan;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.ImsCustProdSync;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.imsintf.entity.SFristActiveReq;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOper;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderOperList;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
import com.ailk.openbilling.persistence.imsintf.entity.SUserStatus;
import com.ailk.openbilling.persistence.imssdl.entity.SBalance;
import com.ailk.openbilling.persistence.imssdl.entity.SFirstAct;
import com.ailk.openbilling.persistence.imssdl.entity.SFirstActiveRes;
import com.ailk.openbilling.persistence.pm.entity.PmFirstActiveGprsUrl;
import com.ailk.openbilling.persistence.pm.entity.PmFirstActiveRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.sys.entity.SysCyclePatternDetail;
import com.ailk.openbilling.persistence.sys.entity.SysGroupCyclePattern;

/**
 * @Description: 首次激活相关操作
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wukl
 * @Date 2011-8-1
 * @Date 2012-3-14 tangjl5 Bug #40955 原历史数据的失效时间直接设置成原数据的生效时间
 * @Date 2012-3-30 wangjt：删除未使用的 createRewardProdcut方法
 * @Date 2012-4-4 tangjl5 On_Site Defect #43478 取默认的measureId存入account缓存中，帐管赠送资金时使用
 * @Date 2012-4-16 tangjl5 Bug #44162 首次激活时，若balance节点中没有传入user_id、acct_id，则从SFirstActiveRes获取user_id，对用户进行充值
 * @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
 * @Date 2012-04-17 wangjt isEmpty()方法修改
 * @Date 2012-5-3 tangjl5 当前状态的失效时间应该根据首次激活的时间计算
 * @Date 2012-5-7 tangjl5 返回用户生命周期状态
 * @Date 2012-05-09 wangdw5 删除:SCoProd.set_serviceId,SCoProd.set_payAcctId
 * @Date 2012-0510 wangdw5 [44318]告警信息的金额转换-调用addCurrencyParam
 * @Date 2012-5-22 tangjl5 On_Site Defect #46493 查询用户信息时添加0开头
 * @Date 2012-05-28 wangdw5:[46173]首次激活根据company发送不同的告警
 * @Date 2012-6-1 yugb [47111]FirstAct From CRM/SFF No need to sync to SFF
 * @Date 2012-06-11 wangdw5 : On_Site Defect #47941 AIS版本号码头+0
 * @Date 2012-06-14 wukl 增加生命周期状态停机位的上发
 * @Date 2012-07-01 yangjh story:45219 增加账户账本信息返回
 * @Date 2012-07-02 yangjh story:45219 getActiveUserInfo增加billing_type的返回
 * @date 2012-07-07 luojb 获取告警id参考channelId #50629
 * @date 2012-07-10 yugb 账管包升级后查询余额方式的修改 #49862
 * @Date 2012-07-11 yangjh story:51229 DCC查询MDB改SAL查询
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 * @Date 2012-08-17 yangjh : 56185 首次激活DCC 上发MDB 改用sal上发
 * @Date 2012-08-17 wukl 废弃Map Parser的使用
 * @Date 2012-09-18 yugb :[57408]API中字段清空方案
 * @date 2012-10-30 luojb On_Site Defect #63200 CT需求对88号码不加头，DCC过来的088手机会变成88，这里判断加0，AIS项目 话单里device_type 0是固话，10是手机
 */
public class FirstActiveComponent extends BaseComponent
{

    public FirstActiveComponent()
    {
    }

    /**
     * @Description: 校验MDB查询出来的数据是否正确
     * @param sQueryInfo
     * @param activeDate
     */
    public void checkAndStoreData(SQueryInfo sQueryInfo, int activeDate)
    {
        // PrintUtil.print("SQueryInfo", sQueryInfo, PrintUtil.sdlRegex);
        if (sQueryInfo == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_DATA_FROM_MDB_ERROR);
        }

        SUserCycle sUserCycle = (SUserCycle) FirstActiveHelper.catchCurrentData(sQueryInfo.get_userCycle(), activeDate);
        if (null == sUserCycle)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_USERCYCLE_MDB_ERROR);
        }

        if (!String.valueOf(sUserCycle.get_userCycle()).endsWith(EnumCodeDefine.LIFECYCLE_STS_ACTIVE))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.USER_DO_NOT_ACTIVE);
        }

        SUserEx sUserEx = (SUserEx) FirstActiveHelper.catchCurrentData(sQueryInfo.get_userInfo(), activeDate);
        if (null == sUserEx)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_USERINFO_ERROR);
        }

        SAccountEx sAccountEx = (SAccountEx) FirstActiveHelper.catchCurrentData(sQueryInfo.get_acctInfo(), activeDate);
        if (null == sAccountEx)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_ACCOUNTINFO_ERROR);
        }

        SCustomer sCustomer = (SCustomer) FirstActiveHelper.catchCurrentData(sQueryInfo.get_custInfo(), activeDate);
        if (null == sCustomer)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_CUSTINFO_ERROR);
        }

        CsdlArrayList<SProdInfo> tempListProdInfo = sQueryInfo.get_prodInfo().get(sUserEx.get_servId());
        CsdlArrayList<SProdInfo> listProdInfo = new CsdlArrayList<SProdInfo>(SProdInfo.class);
        FirstActiveHelper.catchCurrentData(tempListProdInfo, activeDate, listProdInfo);
        if (CommonUtil.isEmpty(listProdInfo))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_PRODINFO_ERROR, sUserEx.get_servId());
        }

        CsdlArrayList<SPromBillCycle> tempListBillCycle = sQueryInfo.get_promBillCycle().get(sUserEx.get_servId());
        CsdlArrayList<SPromBillCycle> listBillCycle = new CsdlArrayList<SPromBillCycle>(SPromBillCycle.class);
        FirstActiveHelper.catchCurrentData(tempListBillCycle, activeDate, listBillCycle);
        // FirstActiveHelper.catchCurrentData(tempListBillCycle,activeDate);
        if (CommonUtil.isEmpty(listBillCycle))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_BILLCYCLE_ERROR, sUserEx.get_servId());
        }
        // yanchuan 2012-07-05 SPromPrice已被计费删除
        // CsdlArrayList<SPromPrice> tempListPromPrice = sQueryInfo.get_promPrice().get(sUserEx.get_servId());
        // CsdlArrayList<SPromPrice> listPromPrice = new CsdlArrayList<SPromPrice>(SPromPrice.class);
        // FirstActiveHelper.catchCurrentData(tempListPromPrice, activeDate, listPromPrice);
        // if (CommonUtil.isEmpty(listPromPrice))
        // {
        // IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_PRODUCT_PRICE_ERROR, sUserEx.get_servId());
        // }

        // 将查询到的数据放context中,供component其它地方使用(包含：PatternId)
        this.setDataInContext(sUserEx, sCustomer, sUserCycle, sAccountEx, listProdInfo, listBillCycle);

        // 将账户、客户的信息保存到线程变量中，用于reward回调查询接口使用
        this.setDataInThread(sUserEx, sAccountEx, listProdInfo);

    }

    /**
     * 将查询到的数据放context中,供component其它地方使用 wukl 2012-2-26
     * 
     * @param sUserEx
     * @param sCustomer
     * @param sUserCycle
     * @param sAccountEx
     * @param listProdInfo
     * @param listBillCycle
     * @param listPromPrice
     */
    private void setDataInContext(SUserEx sUserEx, SCustomer sCustomer, SUserCycle sUserCycle, SAccountEx sAccountEx,
            CsdlArrayList<SProdInfo> listProdInfo, CsdlArrayList<SPromBillCycle> listBillCycle)
    {
        // 保存SDL结构
        context.addExtendParam(ConstantDefine.ACTIVE_SDL_SUSEREX, sUserEx);
        context.addExtendParam(ConstantDefine.ACTIVE_SDL_SCUSTOMER, sCustomer);
        context.addExtendParam(ConstantDefine.ACTIVE_SDL_SUSERCYCLE, sUserCycle);
        context.addExtendParam(ConstantDefine.ACTIVE_SDL_SACCOUNTEX, sAccountEx);
        context.addExtendParam(ConstantDefine.ACTIVE_SDL_SPRODINFO_LIST, listProdInfo);
        context.addExtendParam(ConstantDefine.ACTIVE_SDL_SPROMBILLCYCLE_LIST, listBillCycle);
        // yanchuan 2012-07-05 SPromPrice已被计费删除
        // context.addExtendParam(ConstantDefine.ACTIVE_SDL_SPROMPRICE_LIST, listPromPrice);
        SProdInfo mainProdInfo = null;
        // 设置主产品到context中
        for (SProdInfo prodInfo : listProdInfo)
        {
            // 判断主产品是否变更
            if (prodInfo.get_isMain() == EnumCodeDefine.PRODUCT_MAIN)
            {
                mainProdInfo = prodInfo;
                break;
            }
        }

        if (mainProdInfo == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.USER_HAVE_NO_MAIN_PROM);
        }

        context.addExtendParam(ConstantDefine.ACTIVE_SDL_MAIN_PROMOTION, mainProdInfo);

        // 保存java结构
        CmResource cmResource = FirstActiveHelper.getCmResourceBySUserEx(sUserEx);
        CmCustomer cmCustomer = FirstActiveHelper.getCmCustomerBySCustomer(sCustomer);
        CaAccount caAccount = FirstActiveHelper.getCaAccountBySAccountEx(sAccountEx);
        CoProd coProd = FirstActiveHelper.getCoProdBySProdInfo(mainProdInfo, sUserEx);
        context.addExtendParam(ConstantDefine.ACTIVE_JAVA_CMRESOURCE, cmResource);
        context.addExtendParam(ConstantDefine.ACTIVE_JAVA_CMCUSTOMER, cmCustomer);
        context.addExtendParam(ConstantDefine.ACTIVE_JAVA_CAACCOUNT, caAccount);
        context.addExtendParam(ConstantDefine.ACTIVE_JAVA_MAIN_PROMOTION, coProd);

        // 保存用户所属的patternId
        Integer patternId = getPatternId(cmResource, cmCustomer, caAccount, coProd);
        context.addExtendParam(ConstantDefine.ACTIVE_PATTERN_ID, patternId);
    }

    /**
     * 获取用户对应的patternId wukl 2012-2-26
     * 
     * @param cmResource
     * @param cmCustomer
     * @param caAccount
     * @param coProd
     */
    private Integer getPatternId(CmResource cmResource, CmCustomer cmCustomer, CaAccount caAccount, CoProd coProd)
    {
        SysGroupCyclePattern groupCyclePatten = context.getComponent(BaseLifeCycleComponent.class).queryCaGroupCyclePattern(
                cmResource, cmCustomer, caAccount, coProd.getProductOfferingId().longValue());

        return groupCyclePatten.getPatternId();
    }

    /**
     * 将账户、客户的信息保存到线程变量中，用于reward回调查询接口使用 wukl 2012-2-26
     * 
     * @param sUserEx
     * @param sAcct
     * @param listProdInfo
     */
    private void setDataInThread(SUserEx sUserEx, SAccountEx sAcct, CsdlArrayList<SProdInfo> listProdInfo)
    {
        Map dataMap = new HashMap();
        CaAccount account = new CaAccount();
        account.setAccountType(sAcct.get_accountType());
        account.setAcctId(sAcct.get_acctId());

        // @Date 2012-4-4 tangjl5 On_Site Defect #43478 取默认的measureId存入account缓存中，帐管赠送资金时使用@Date 2012-4-4 tangjl5 On_Site Defect
        // #43478 取默认的measureId存入account缓存中，帐管赠送资金时使用
        account.setMeasureId(context.getComponent(AmountComponent.class).getBillingMeasureId(null, sAcct.get_acctId()));

        account.setRegionCode(sAcct.get_regionCode());
        dataMap.put(ConstantDefine.ACTIVE_SDL_SACCOUNTEX, account);

        SUser user = new SUser();
        SProdInfo prodInfo = FirstActiveHelper.getMainSProdInfoInContext(context);
        user.setUser_id(sUserEx.get_servId());
        user.setPayment_mode((short) sUserEx.get_billType());
        user.setMain_promotion(prodInfo.get_promOfferId());
        dataMap.put(ConstantDefine.ACTIVE_SDL_SUSEREX, user);

        SProductOrder prodcutOrder = new SProductOrder();
        prodcutOrder.setProduct_id(prodInfo.get_promNo());
        prodcutOrder.setOffer_id((long) prodInfo.get_promOfferId());
        prodcutOrder.setValid_date(DateUtil.formatDate(DateUtil.UTCToDate(prodInfo.get_validDate()),
                DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
        prodcutOrder.setExpire_date(DateUtil.formatDate(DateUtil.UTCToDate(prodInfo.get_expireDate()),
                DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
        dataMap.put(ConstantDefine.ACTIVE_SDL_SPRODINFO_LIST, prodcutOrder);

        IMSUtil.setRequestContextParam(ConstantDefine.THREAD_QUERY_IMS3HBEAN, dataMap);
    }

    




    /**
     * @Description: 查询主产品、reward配置
     */
    public Integer getPricePlandId4DCC(String location, Long activeTime)
    {
        CmResource userInfo = FirstActiveHelper.getCmResourceInContext(context);
        CmCustomer custInfo = FirstActiveHelper.getCmCustomerInContext(context);
        CaAccount acctInfo = FirstActiveHelper.getCaAccountInContext(context);
        CoProd mainProdInfo = FirstActiveHelper.getMainCoProdInContext(context);

        Integer pricePlanId = null;
        BiBusiPlan plan = context.getComponent(RuleComponent.class).getPricePlandId(userInfo, custInfo, acctInfo, mainProdInfo,
                location, DateUtil.UTCToDate(activeTime), null);
        if (plan != null)
            pricePlanId = plan.getPricingPlanId();
        return pricePlanId;
    }



    public void changeMainProd(com.ailk.openbilling.persistence.imssdl.entity.SProdInfo delProd,
            com.ailk.openbilling.persistence.imssdl.entity.SProdInfo inserProd, SFirstActiveRes firstActiveRes)
    {
        // 构造新增主产品结构
        SProductOrderList insertOrderList = new SProductOrderList();
        SProductOrder insertOrder = new SProductOrder();
        // 新增需要的参数
        insertOrder.setUser_id(firstActiveRes.getUserId());
        insertOrder.setPhone_id(firstActiveRes.getPhoneId());
        insertOrder.setOffer_id((long) inserProd.getProductOfferingId());
        insertOrder.setValid_date(DateUtil.UTCToString(inserProd.getValidDate()));
        insertOrder.setValid_type((short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE);
        insertOrder.setProduct_id(inserProd.getProductId());
        SProductOrder[] insertOrderArr = new SProductOrder[1];
        insertOrderArr[0] = insertOrder;
        insertOrderList.setItem(insertOrderArr);
        SProductOrderOper insertOper = new SProductOrderOper();
        insertOper.setOper_type(EnumCodeDefine.CHANGE_PROD_ADD);
        insertOper.setProd_list(insertOrderList);

        // 构造删除主产品结构
        context.addExtendParam(ConstantDefine.ACTIVE_DATE, DateUtil.UTCToDate(delProd.getExpireDate()));
        SProductOrderList delOrderList = new SProductOrderList();
        SProductOrder delOrder = new SProductOrder();
        // 删除需要的参数
        delOrder.setProduct_id(delProd.getProductId());

        SProductOrder[] delOrderArr = new SProductOrder[1];
        delOrderArr[0] = delOrder;
        delOrderList.setItem(delOrderArr);
        SProductOrderOper delOper = new SProductOrderOper();
        delOper.setOper_type(EnumCodeDefine.CHANGE_PROD_DELETE);
        delOper.setProd_list(delOrderList);

        SProductOrderOper[] operArr = new SProductOrderOper[2];
        operArr[0] = delOper;
        operArr[1] = insertOper;
        SProductOrderOperList operList = new SProductOrderOperList();
        operList.setSProductOrderOperList_Item(operArr);
        context.getComponent(BaseProductComponent.class).operateProduct(operList);
    }

    /**
     * @Description: 修改产品
     * @param prod
     * @param firstActiveRes
     * @Date 2012-3-14 tangjl5 Bug #40955 原历史数据的失效时间直接设置成原数据的生效时间
     */
    public void modProd(com.ailk.openbilling.persistence.imssdl.entity.SProdInfo prod, SFirstActiveRes firstActiveRes)
    {
        Date expireDate = DateUtil.UTCToDate(prod.getExpireDate());// 每一个产品的失效时间有可能不一样
        Date oldDataExpireDate = DateUtil.UTCToDate(prod.getValidDate());
        Date newDataValidDate = DateUtil.UTCToDate(firstActiveRes.getActivationTime());
        Long soNbr = firstActiveRes.getSoNbr();
        Long prodId = prod.getProductId();

        CoProd coProd = new CoProd();
        coProd.setExpireDate(expireDate);
        coProd.setSoNbr(soNbr);
        updateByCondition(coProd, oldDataExpireDate, newDataValidDate, new DBCondition(CoProd.Field.productId, prodId),
                new DBCondition(CoProd.Field.objectId, firstActiveRes.getUserId()));
        // Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
        // CoProdInvObj invObj = new CoProdInvObj();
        // invObj.setExpireDate(expireDate);
        // invObj.setSoNbr(soNbr);
        // updateByCondition(invObj, oldDataExpireDate, newDataValidDate, new DBCondition(CoProdInvObj.Field.productId, prodId),
        // new DBCondition(CoProdInvObj.Field.objectId, firstActiveRes.get_userId()));

        CoProdCharValue charValue = new CoProdCharValue();
        charValue.setExpireDate(expireDate);
        charValue.setSoNbr(soNbr);
        updateByCondition(charValue, oldDataExpireDate, newDataValidDate,
                new DBCondition(CoProdCharValue.Field.productId, prodId), new DBCondition(CoProdCharValue.Field.objectId,
                        firstActiveRes.getUserId()));

        CoProdBillingCycle cycle = new CoProdBillingCycle();
        ProductCycleComponent pcCmp = context.getComponent(ProductCycleComponent.class);
        CoProdBillingCycle billCycle = pcCmp.queryProdBillingCycle(prodId, firstActiveRes.getUserId());
        if (billCycle == null)
        {
            IMSUtil.throwBusiException("product [" + prodId + "] billing cycle is not exist");
        }
        Date firstBillDate = ProdCycleHelper.createFirstBillDate(new Date(newDataValidDate.getTime()), null,
                billCycle.getCycleType(), billCycle.getCycleUnit());
        // 2012-05-28 zhangzj3 修复bug[46438] 三户新装后马上首次激活，CO_PROD_BILLING_CYCLE主键冲突
        if (newDataValidDate.getTime() != billCycle.getValidDate().getTime())
        {
            cycle.setFirstBillDate(firstBillDate);// 2012-01-05 平移产品时首次账单日推算获得
            cycle.setExpireDate(expireDate);
            cycle.setSoNbr(soNbr);
            updateByCondition(cycle, oldDataExpireDate, newDataValidDate, new DBCondition(CoProdBillingCycle.Field.productId,
                    prodId), new DBCondition(CoProdBillingCycle.Field.objectId, firstActiveRes.getUserId()));
        }
        CoProdPriceParam param = new CoProdPriceParam();
        param.setExpireDate(expireDate);
        param.setSoNbr(soNbr);
        updateByCondition(param, oldDataExpireDate, newDataValidDate, new DBCondition(CoProdPriceParam.Field.productId, prodId),
                new DBCondition(CoProdPriceParam.Field.objectId, firstActiveRes.getUserId()));
    }

    /**
     * @Description: 推算出主产品的失效时间
     * @param offeringId
     * @param prodCycle
     * @param validDate
     * @return
     */
    public Date getProdExpireDate(Integer offeringId, PmProductOfferLifecycle prodCycle, int validDate)
    {
        SBillCycle billCycle = new SBillCycle();
        billCycle.setCycle_type(prodCycle.getCycleType());
        billCycle.setCycle_unit(prodCycle.getCycleUnit());
        billCycle.setFirst_bill_day(validDate);

        CoProdBillingCycle dmProdCycle = context.getComponent(ProductCycleComponent.class).parseProdBillingCycle(
                offeringId.longValue(), billCycle, DateUtil.UTCToString(validDate), null,
                (short) EnumCodeDefine.PROD_VALID_SPECIFIC_DATE, null, null);
        return dmProdCycle.getExpireDate();
    }

    /**
     * @param orgLocation
     * @Description: CRM流程构造FirstActiveTask对象的值
     */
    public SFirstActiveRes getFirstActiveTask(SFristActiveReq activeReq, PmFirstActiveRule rule, List<CoProd> prodList,
            CaAccount acct, String orgLocation)
    {
        SFirstActiveRes activeResult = new SFirstActiveRes();
        activeResult.setPhoneId(activeReq.getPhone_id());
        activeResult.setUserId(activeReq.getUser_id());
        activeResult.setOrgLocation(orgLocation);
        if (activeReq.getSms_lang() != null)
        {
            activeResult.setSmsLanguage(activeReq.getSms_lang());
        }
        if (activeReq.getIvr_lang() != null)
        {
            activeResult.setIvrLanguage(activeReq.getIvr_lang());
        }
        if (!CommonUtil.isEmpty(activeReq.getLocation()))
        {
            activeResult.setLocation(activeReq.getLocation());
        }
        if (activeReq.getUssd_lang() != null)
        {
            activeResult.setUssdLanguage(activeReq.getUssd_lang());
        }
        activeResult.setActivationTime(IMSUtil.getMdbDate(DateUtil.getFormattedDate(activeReq.getActivation_time())));

        activeResult.setAcctId(acct.getAcctId());
        activeResult.setSoNbr(context.getSoNbr());

        com.ailk.openbilling.persistence.imsintf.entity.SBalance javaBalance = activeReq.getBalance();
        if (null != javaBalance)
        {
            com.ailk.openbilling.persistence.imssdl.entity.SBalance sdlBalance = ConvertUtil.javaBalance2Sdl(javaBalance);
            activeResult.setBalance(sdlBalance);
        }
        else
        {
            activeResult.setBalance(null);
        }

        // 上海不需要
        if (ProjectUtil.is_TH_AIS())
        {
            // 2012-02-10 wukl 若配置的主产品跟用户原主产品一致，则原主产品继续使用，不做变更，只是更新产品的生失效时间
            CoProd mainPromotion = context.getComponent(ProductQuery.class).getPrimaryProduct(prodList);
            if (rule != null && CommonUtil.isValid(rule.getDestOfferingId()))
            {
                if (mainPromotion.getProductOfferingId().intValue() != rule.getDestOfferingId().intValue())
                {
                    activeResult.setDelProdList(getDelProdList(activeReq, rule, mainPromotion));
                    activeResult.setAddProdList(getAddProdList(activeReq, rule, mainPromotion));
                }
                else
                {
                    imsLogger
                            .info("****** find rule but not change product, because of change product is equals to original product,offering id = "
                                    + rule.getDestOfferingId());
                }
            }

            activeResult.setModProdList(getModProdList(activeReq, rule, mainPromotion, prodList));
        }
        return activeResult;
    }

    private CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> getDelProdList(SFristActiveReq activeReq,
            PmFirstActiveRule rule, CoProd mainPromotion)
    {
        CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> delList = new CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo>(
                com.ailk.openbilling.persistence.imssdl.entity.SProdInfo.class);
        com.ailk.openbilling.persistence.imssdl.entity.SProdInfo prodInfo = new com.ailk.openbilling.persistence.imssdl.entity.SProdInfo();
        prodInfo.setProductId(mainPromotion.getProductId());
        prodInfo.setObjectId(activeReq.getUser_id());
        prodInfo.setExpireDate(IMSUtil.getMdbDate(DateUtil.getFormattedDate(activeReq.getActivation_time())));
        prodInfo.setProductOfferingId(mainPromotion.getProductOfferingId());
        prodInfo.setBillingType(mainPromotion.getBillingType());
        prodInfo.setValidDate(IMSUtil.getMdbDate(mainPromotion.getValidDate()));
        prodInfo.setPricePlanId(mainPromotion.getPricingPlanId());
        delList.add(prodInfo);
        return delList;
    }

    private CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> getModProdList(SFristActiveReq activeReq,
            PmFirstActiveRule rule, CoProd mainPromotion, List<CoProd> prodList)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        int activeDate = IMSUtil.getMdbDate(DateUtil.getFormattedDate(activeReq.getActivation_time()));

        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        // 2012-08-13 yanchuan 查询主产品下面的打包产品
        List<Integer> offering_list = prodCmp.queryPackageSonOfferIdsByParentOfferId(mainPromotion.getProductOfferingId());

        if (CommonUtil.isEmpty(prodList))
        {
            return null;
        }
        // 是否有打包产品
        boolean isExistPackageProd = false;
        if (CommonUtil.isNotEmpty(offering_list))
        {
            isExistPackageProd = true;
        }
        // 主产品是否变更标识；若未变更，则修改的列表中包含主产品的信息
        boolean mainProdNotChangeFlag = false;
        if (rule == null || !CommonUtil.isValid(rule.getDestOfferingId())
                || mainPromotion.getProductOfferingId().intValue() == rule.getDestOfferingId())
        {
            mainProdNotChangeFlag = true;
        }

        CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> modList = new CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo>(
                com.ailk.openbilling.persistence.imssdl.entity.SProdInfo.class);
        com.ailk.openbilling.persistence.imssdl.entity.SProdInfo modProd = null;
        for (CoProd prod : prodList)
        {
            if (isExistPackageProd && offering_list.contains(prod.getProductOfferingId()))
                continue;
            if (prod.getIsMain().intValue() != EnumCodeDefine.PRODUCT_MAIN || mainProdNotChangeFlag)
            {
                modProd = new com.ailk.openbilling.persistence.imssdl.entity.SProdInfo();
                modProd.setProductId(prod.getProductId());
                int validDate = IMSUtil.getMdbDate(prod.getValidDate());
                int expireDate = IMSUtil.getMdbDate(prod.getExpireDate());
                // 此处记录的是产品的原生效时间，并非激活后的生效时间，modify by Bug #40955
                modProd.setValidDate(IMSUtil.getMdbDate(prod.getValidDate()));
                if (prod.getIsMain().intValue() != EnumCodeDefine.PRODUCT_MAIN)
                {

                    modProd.setExpireDate(expireDate + (activeDate - validDate));// 计算平移后的失效时间
                    // @Date 2012-3-14 tangjl5 Bug #40955 原历史数据的失效时间直接设置成原数据的生效时间
                    // modProd.set_expireDate(IMSUtil.getMdbDate(prod.getValidDate()));
                }
                else
                {
                    modProd.setExpireDate(IMSUtil.getMdbDate(prod.getExpireDate()));// 主产品的失效时间不变更
                }
                modProd.setBillingType(prod.getBillingType());
                modProd.setObjectId(activeReq.getUser_id());
                modProd.setProductOfferingId(prod.getProductOfferingId());
                modProd.setPricePlanId(prod.getPricingPlanId());
                modList.add(modProd);
            }
        }

        return modList;
    }

    private CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> getAddProdList(SFristActiveReq activeReq,
            PmFirstActiveRule rule, CoProd mainPromotion)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        Date validDate = DateUtil.getFormattedDate(activeReq.getActivation_time());// 获取生效时间，即激活时间
        prodCmp.queryProdOfferLifeCycle(rule.getDestOfferingId());
        // 2011-11-26 主产品的失效时间同用户的失效时间
        // Date expireDate = getProdExpireDate(rule.getDestOfferingId(),prodCycle,IMSUtil.getMdbDate(validDate));//推算出失效时间
        CmResource user = context.get3hTree().loadUser3hBean(activeReq.getUser_id()).getUser();
        Integer pricePlanId = prodCmp.queryPricePlanId(rule.getDestOfferingId(), null, activeReq.getUser_id());// 查询用户级产品价格计划
        if (pricePlanId == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_PRICINGPLAN_PROD_NOTEXIST, rule.getDestOfferingId());
        }

        CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> addList = new CsdlArrayList<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo>(
                com.ailk.openbilling.persistence.imssdl.entity.SProdInfo.class);
        com.ailk.openbilling.persistence.imssdl.entity.SProdInfo prodInfo = new com.ailk.openbilling.persistence.imssdl.entity.SProdInfo();
        prodInfo.setProductId(DBUtil.getSequence(CoProd.class));
        prodInfo.setProductOfferingId(rule.getDestOfferingId());
        prodInfo.setPricePlanId(pricePlanId);
        prodInfo.setObjectId(activeReq.getUser_id());
        prodInfo.setBillingType(EnumCodeDefine.PROD_BILLTYPE_PREPAID);
        prodInfo.setExpireDate(IMSUtil.getMdbDate(user.getExpireDate()));
        prodInfo.setValidDate(IMSUtil.getMdbDate(validDate));
        addList.add(prodInfo);

        return addList;
    }

    /**
     * @Description: 将Xdr.SFirstAct对象中的数据拷贝到对应的Sdl.SFirstAct对象中
     * @param xdrValue
     * @return
     */
    public SFirstAct convertSFirstActXdr2Sdl(com.ailk.openbilling.persistence.imsxdr.entity.SFirstAct xdrValue)
    {
        if (xdrValue == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SFirstAct");
        }
        SFirstAct sdlFirstAct = new SFirstAct();
        ConvertUtil.copyXdr2Sdl(xdrValue, sdlFirstAct);
        com.ailk.openbilling.persistence.imsxdr.entity.SBalance xdrBalance = xdrValue.getBalance();
        SBalance sdlBalance = new SBalance();
        ConvertUtil.copyXdr2Sdl(xdrBalance, sdlBalance);
        sdlFirstAct.setBalance(sdlBalance);

        if (CommonUtil.isValid(sdlFirstAct.getActivationTime())
                && DateUtil.getFormattedDate(String.valueOf(sdlFirstAct.getActivationTime())).before(context.getRequestDate()))
        {
            // 接口传入的int64时间格式为20110830595900,转成真正意义上的int64
            sdlFirstAct.setActivationTime(ConvertUtil.sdlLong2SdlTime(sdlFirstAct.getActivationTime()));
        }
        else
        {
            sdlFirstAct.setActivationTime(IMSUtil.getMdbDate(context.getRequestDate()));
        }

        return sdlFirstAct;
    }

 

   

  


    private Integer getPricePlanId2Cache(Integer destOfferingId)
    {
        CmResource userInfo = FirstActiveHelper.getCmResourceInContext(context);
        CmCustomer custInfo = FirstActiveHelper.getCmCustomerInContext(context);
        CaAccount acctInfo = FirstActiveHelper.getCaAccountInContext(context);

        Integer pricePlanId = context.getComponent(CacheQuery.class).queryPricePlanId(destOfferingId, custInfo, acctInfo,
                userInfo);
        if (pricePlanId == null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_PRICINGPLAN_PROD_NOTEXIST, destOfferingId);
        }
        return pricePlanId;
    }



    /**
     * @Description: CRM流程 修改用户基本信息
     * @param firstActiveRes
     * @Date 2012-09-18 yugb :[57408]API中字段清空方案
     */
    public void modifySubscriberBasicInfo(SFirstActiveRes firstActiveRes)
    {
        imsLogger.info("****** firstactive operate db, modify user basicInfo");
        UserComponent userCmp = context.getComponent(UserComponent.class);
        com.ailk.openbilling.persistence.imsintf.entity.SUser user = new com.ailk.openbilling.persistence.imsintf.entity.SUser();
        if (!CommonUtil.isEmpty(firstActiveRes.getPhoneId()))
            user.setPhone_id(firstActiveRes.getPhoneId());
        else if (IMSUtil.isClean(firstActiveRes.getPhoneId()))
            user.setPhone_id(null);
        if (firstActiveRes.getUserId() != 0)
        {
            if (IMSUtil.isClean(firstActiveRes.getUserId()))
                user.setUser_id(null);
            else
                user.setUser_id(firstActiveRes.getUserId());
        }
        Short ivrLang = firstActiveRes.getIvrLanguage();
        if (CommonUtil.isValid(ivrLang))
        {
            if (IMSUtil.isClean(ivrLang))
                user.setIvr_language(null);
            else
                user.setIvr_language(ivrLang);
        }
        Short smsLang = firstActiveRes.getSmsLanguage();
        if (CommonUtil.isValid(smsLang))
        {
            if (IMSUtil.isClean(smsLang))
                user.setSms_language(null);
            else
                user.setSms_language(smsLang);
        }

        Short ussdLang = firstActiveRes.getUssdLanguage();
        if (CommonUtil.isValid(ussdLang))
        {
            if (IMSUtil.isClean(ussdLang))
                user.setUssd_language(null);
            else
                user.setUssd_language(ussdLang);
        }

        user.setActive_date(DateUtil.UTCToString(firstActiveRes.getActivationTime()));
        userCmp.modifyCmResourceInfo(user);
    }

   

    // ####################################上面是DCC流程的首次激活，下面是CRM流程的首次激活######################
    public SFirstAct getSFirstAct(SFristActiveReq activeReq)
    {
        SFirstAct firstAct = new SFirstAct();
        firstAct.setActivationTime(IMSUtil.getMdbDate(DateUtil.getFormattedDate(activeReq.getActivation_time())));
        com.ailk.openbilling.persistence.imsintf.entity.SBalance javaBalance = activeReq.getBalance();
        if (javaBalance != null)
        {
            com.ailk.openbilling.persistence.imssdl.entity.SBalance sdlBalance = new SBalance();
            ConvertUtil.copyJava2Sdl(sdlBalance, javaBalance);
            firstAct.setBalance(sdlBalance);
        }
        else
        {
            firstAct.setBalance(null);
        }

        if (CommonUtil.isValid(activeReq.getDevice_type()))
        {
            firstAct.setDeviceType(Short.parseShort(activeReq.getDevice_type()));
        }
        if (activeReq.getSms_lang() != null)
        {
            firstAct.setSmsLang(activeReq.getSms_lang());
        }
        if (activeReq.getIvr_lang() != null)
        {
            firstAct.setIvrLang(activeReq.getIvr_lang());
        }
        if (!CommonUtil.isEmpty(activeReq.getLocation()))
        {
            firstAct.setLocation(activeReq.getLocation());
        }
        if (activeReq.getUssd_lang() != null)
        {
            firstAct.setUssdLang(activeReq.getUssd_lang());
        }
        firstAct.setUserId(activeReq.getUser_id());
        firstAct.setPhoneId(activeReq.getPhone_id());

        return firstAct;
    }


    /**
     * @Description: 判断首次激活是否是DCC流程
     * @return
     */
    public boolean isFirstActiveByDCC()
    {
        int busiCode = context.getOper().getBusi_code().intValue();
        if (busiCode == EnumCodeDefine.FIRST_ACTIVE_4_XDR_BUSI_BEAN || busiCode == EnumCodeDefine.FIRST_ACTIVE_4_TS_BUSI_BEAN)
        {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是首次激活业务 wukl 2011-12-31
     * 
     * @return
     */
    public boolean isFirstActvie()
    {
        int busiCode = context.getOper().getBusi_code().intValue();
        if (busiCode == EnumCodeDefine.FIRST_ACTIVE_BY_CRM || isFirstActiveByDCC())
        {
            return true;
        }
        if (ProjectUtil.is_CN_SH() && busiCode == EnumCodeDefine.FIRST_ACTIVE_BY_CRM_4_SH)
        {
            return true;
        }
        return false;
    }


   

   

   



   

   

  
   

    /**
     * 从MDB查询用户、客户、用户级产品信息 wukl 2011-12-17
     */
    public SQueryInfo queryDataFromMDB(SFirstAct sdlFirstAct, CBSErrorMsg errorMsg)
    {
        // 调用MDB的查询接口查询用户信息、客户信息和所有产品信息
        // IMdbSyncUpAppInt iMdbSyncUpAppInt = new IMdbSyncUpAppInt();
        SQueryPara sQueryPara = new SQueryPara();
        // @Date 2012-5-22 tangjl5 On_Site Defect #46493 查询用户信息时添加0开头
        // @Date 2012-06-14 wangdw5 : User Story #42479 AIS号码头处理
     // 2012-10-30 luojb On_Site Defect #63200 CT需求对88号码不加头，DCC过来的088手机会变成88，这里判断加0，AIS项目 话单里device_type 0是固话，10是手机
        String phoneId = PhoneIdHeadUtil.phoneIdParse(sdlFirstAct.getPhoneId().trim(),sdlFirstAct.getDeviceType());
        sQueryPara.set_userNumber(phoneId);
        // 2012-07-11 yangjh story:51229 DCC查询MDB改SAL查询
        SQueryInfo qeuryInfo = new SQueryInfo();
        SQueryInfo queryResult = new SQueryInfo();
        SalClient client = SpringUtil.getSalClient();
        // CsdlStructObjectHolder<SQueryInfo> sQeuryInfoHolder = new CsdlStructObjectHolder<SQueryInfo>(qeuryInfo);
        try
        {
            queryResult = client.get(MdbKVDefine.QUERY_USER_INFO, sQueryPara, qeuryInfo);
            // story:51229 DCC查询MDB改SAL查询 增加返回错误的错误判断
            if (queryResult.get_retValue() != 0)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.ACCESS_INTERFACE_FAILED, "salQueryInfo");
            }
            // 空判断移到前面
            if (CommonUtil.isEmpty(queryResult.get_userInfo()))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_DATA_FROM_MDB_ERROR);
            }
        }
        catch (OBBufferErrorException e)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_DATA_FROM_MDB_ERROR);
        }
        catch (SALException e)
        {
            imsLogger.error(e,e);
        }

        imsLogger.info("****** firstactive sycn mdb, access interface[imsync_queryInfo] to query data, phone ="
                + sdlFirstAct.getPhoneId());

        return queryResult;
    }

   

    /**
     * @param csdlArrayList
     * @Description: 调用生命周期业务处理方法
     * @param userID
     */
    public void invokeLifeCycleBusiBean(Long userId, String activeDate,
            List<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> addProdList)
    {
        imsLogger.info("****** firstactive operate db, modify user lifeCycle");
        BaseLifeCycleComponent lifeCycleCmp = context.getComponent(BaseLifeCycleComponent.class);
        User3hBean user3hBean = context.get3hTree().loadUser3hBean(userId);

        lifeCycleCmp.updateLifeCycleAndValidityByFirstActive(user3hBean.getUser(),
                user3hBean.getCustomer(), user3hBean.getAccount(),
                this.packageSUserStatus(userId, activeDate, addProdList));
    }

    private SUserStatus packageSUserStatus(Long userId, String validate,
            List<com.ailk.openbilling.persistence.imssdl.entity.SProdInfo> addProdList)
    {
        SUserStatus userStatus = new SUserStatus();
        userStatus.setUser_id(userId);
        userStatus.setNew_state((short) EnumCodeDefine.LIFECYCLE_ACTIVE);// 置为激活状态
        userStatus.setValid_date(validate);
        // 2011-01-14 若用户发生主产品变更（变更后的产品不允许是pre_match），则将unbilling_flag 置为0
        if (CommonUtil.isNotEmpty(addProdList))
            userStatus.setUnbilling_flag((short) EnumCodeDefine.LIFECYCLE_UNBILLING_FLAG_NO);
        return userStatus;
    }

    /*
     * 将接口数据存入到接口表中，用于同步给CRM
     */
    public void insertCmCustProdSync(SFirstActiveRes firstActiveRes, Long rewardSoNbr)
    {
        imsLogger.info("****** firstactive operate db, insert[CD.IMS_CUST_PROD_SYNC] for sync crm");
        if (context.getOper().getSo_mode() == EnumCodeDefine.REWARD_HANDLETYPE_MDB)
        {
            return;
        }
        ImsCustProdSync custProdSync = new ImsCustProdSync();
        // @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
        custProdSync.setId(DBUtil.getSequence());
        custProdSync.setSoNbr(this.getContext().getSoNbr());
        custProdSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        custProdSync.setCreateDate(this.getContext().getRequestDate());
        custProdSync.setActiveCac(firstActiveRes.getOrgLocation());

        custProdSync.setAcctId(firstActiveRes.getAcctId());
        custProdSync.setActChannel(context.getOper().getSo_mode().intValue());
        custProdSync.setActivationTime(DateUtil.UTCToDate(firstActiveRes.getActivationTime()));
        custProdSync.setUserId(firstActiveRes.getUserId());
        // UserComponent userCmp = context.getComponent(UserComponent.class);
        // 2012-08-28 linzt 整理组件方法 采用load3hBean
        User3hBean userBean = null;
        userBean = context.get3hTree().loadUser3hBean(firstActiveRes.getUserId());
        Long acctId = userBean.getAcctId();
        CmResource cmResource = userBean.getUser();
        CmCustomer customer = context.get3hTree().loadAcct3hBean(acctId).getCustomer();
        custProdSync.setCustId(customer.getCustId());
        custProdSync.setPhoneId(firstActiveRes.getPhoneId());
        custProdSync.setIvrLanguage(cmResource.getListeningLanguage());
        custProdSync.setSmsLanguage(cmResource.getReadingLanguage());
        custProdSync.setUssdLanguage(cmResource.getWritingLanguage());
        custProdSync.setExpireDate(cmResource.getExpireDate());
        // 用于查询赠送的信息
        custProdSync.setBosSoDate(DateUtil.UTCToDate(firstActiveRes.getActivationTime()));
        custProdSync.setBosSoNbr(rewardSoNbr);
        insert(custProdSync);
    }

   
    /**
     * @Description: 获取active状态的失效时间
     * @param cycleStatus
     * @param sFirstAct
     * @param sQueryInfo
     * @param oldExpireDate
     * @author wukl 2011-11-26
     * @param patternId
     * @return
     */
    public Date getActiveExpireDate(int cycleStatus, int oldExpireDate, Integer patternId)
    {
        SysCyclePatternDetail detail = context.getComponent(BaseLifeCycleComponent.class).querySysCyclePatternDetailByStsID2Cache(
                CommonUtil.int2Short(cycleStatus), patternId);

        Calendar newExpireDate = context.getComponent(BaseLifeCycleComponent.class).calculateExpireDateByExtendDays(
                DateUtil.UTCToDate(oldExpireDate), detail.getValidDays());

        return newExpireDate.getTime();
    }
   

    /**
     * 获取主产品变更规则、及成功失败跳转的URL（只有数据激活so_mode=17才取url）; wukl 2012-3-8
     * 
     * @param pricePlanId
     * @param utcToDate
     * @param rule
     * @param gprsUrls
     */
    public PmFirstActiveRule getRuleAndUrl(Integer pricePlanId, Date activeDate)
    {
        RuleComponent ruleCmp = context.getComponent(RuleComponent.class);

        if (context.getOper().getSo_mode() == EnumCodeDefine.DATA_ACTIVE)
        {
            // 数据首次激活，url必须配置，故加此校验
            if (!CommonUtil.isValid(pricePlanId))
                IMSUtil.throwBusiException(ErrorCodeDefine.PRICE_PLAN_ID_NOT_DEFINE);

            Integer gprsPriceRuleId = ruleCmp.getPriceRuleId2Cache(pricePlanId, EnumCodeDefine.DATA_FIRSTACTIVE_GPRS_URL,
                    activeDate);

            if (!CommonUtil.isValid(gprsPriceRuleId))
                IMSUtil.throwBusiException(ErrorCodeDefine.PRICE_RULE_ID_NOT_DEFINE);

            imsLogger.info("******  data first active, priceRuleId = " + gprsPriceRuleId);

            PmFirstActiveGprsUrl gprsUrls = ruleCmp.getPmFirstActiveGprsUrl(gprsPriceRuleId, true);

//            if (gprsUrls == null)
//            {
//                IMSUtil.throwBusiException(ErrorCodeDefine.ACTIVE_GPRS_URL_NOT_DEFINE, gprsPriceRuleId);
//            }

            // 将查询出来的数据首次激活跳转url放置到缓存中，用于过滤器中获取失败的URL
            context.addExtendParam(ConstantDefine.ACTIVE_GPRS_URL, gprsUrls);
        }

        Integer changePromPriceRuleId = ruleCmp.getPriceRuleId2Cache(pricePlanId, EnumCodeDefine.CHANGE_MAIN_PRODUCT, activeDate);
        imsLogger.info("******  voice first active, priceRuleId = " + changePromPriceRuleId);
        return ruleCmp.getChgPromRule2Cache(changePromPriceRuleId, activeDate);
    }
}