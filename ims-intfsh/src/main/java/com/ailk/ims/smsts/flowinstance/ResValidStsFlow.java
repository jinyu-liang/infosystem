package com.ailk.ims.smsts.flowinstance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jef.common.log.Logger;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.Field;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.BalanceShComponent;
import com.ailk.ims.component.RouterComponent;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.smsts.flowbase.BaseFlow;
import com.ailk.ims.smsts.helper.FlowInstanceHelper;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.CommonUtilSh;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imscnsh.entity.SMonthlyFee;
import com.ailk.openbilling.persistence.jd.entity.ImsResStsSync;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description: 扫描用户有效期（当用户没有取消有效期业务（即：Effect_flag = 0），当天扫描，昨天到期的；
 * 当用户的有效期到期后：
    1、除“200套餐”（20000200）用户外，上述其它套餐的用户，都直接做“有效期”停；
    2、对于“200套餐”（20000200）用户，有效期到期时，先做“保留期”停，过90天后，再做“有效期”停
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wukl
 * busi_num=7511
 * @Date 2012-08-22
 */
public class ResValidStsFlow extends BaseFlow
{
    private Date retainBeginTime;// 保留期的开始时间
    private Date retainEndTime;// 保留期的结束时间
    private Date effectiveBeginTime;// 有效期的开始时间
    private Date effectiveEndTime;// 有效期的结束时间
    private List<ImsResStsSync> listStsSync = new ArrayList<ImsResStsSync>();
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
        // 初始化有效期停，保留期停的开始，结束时间
        instanceDateParam();

        DBCondition[] conds = new DBCondition[3];
        conds[0] = new DBCondition(CmResValidity.Field.effectFlag, EnumCodeShDefine.CM_VALIDITY_EFFECTIVE);
        conds[1] = new DBCondition(CmResValidity.Field.expireDate, getCurrentDate(), Operator.GREAT);
        conds[2] = new DBOrCondition(new DBCondition(new DBCondition(CmResValidity.Field.userExpireDate, retainEndTime,
                Operator.LESS_EQUALS),
                new DBCondition(CmResValidity.Field.userExpireDate, retainBeginTime, Operator.GREAT_EQUALS)), new DBCondition(
                new DBCondition(CmResValidity.Field.userExpireDate, effectiveEndTime, Operator.LESS_EQUALS), new DBCondition(
                        CmResValidity.Field.userExpireDate, effectiveBeginTime, Operator.GREAT_EQUALS)));
        return conds;
    }

    public List<SmsSendInterfaceCheck> judgeThenReturnSmsList(Object obj, Long blockId)
    {

        CmResValidity validity = (CmResValidity) obj;
        Long resourceId = validity.getResourceId();

        String retainStr = DateUtil.getFormatDate(retainEndTime, DateUtil.DATE_FORMAT_YYYYMMDD);
        String effectStr = DateUtil.getFormatDate(effectiveEndTime, DateUtil.DATE_FORMAT_YYYYMMDD);
        String expireStr = DateUtil.getFormatDate(validity.getUserExpireDate(), DateUtil.DATE_FORMAT_YYYYMMDD);
        
        //根据用户编号从路由中查询账户编号
        Long acctId = getSmsCmp().getContext().getComponent(RouterComponent.class).getAcctIdByUserIdRout(resourceId);
        IMSUtil.setAcctRouterParam(acctId);
        
        //查询用户的主产品
        Integer mainOfferId = getSmsCmp().getContext().getComponent(ProductQuery.class).queryMainOfferIdByUserId(resourceId);
        
        //判断该用户是否属于有效期停机的品牌范畴
        if (CommonUtil.isEmpty(listBrands) || !listBrands.contains(mainOfferId))
        	return null;
        

        //预付随e行品牌不能置有效期的判断
        if (isEwalkMainUserAndOrderMonthlyFee(acctId, resourceId, mainOfferId))
        {
            imsLogger.info("用户[",resourceId,"]付费随E行品牌，选择了有月租费的产品或功能，不设置有效期");
            return null;
        }
        
        
        //神州行易通卡品牌的判断
        boolean prodFlag = isSpecProd(resourceId, mainOfferId);
        
        //如果是公免优惠用户，不需要则有效期停机
        if (isPreferential(resourceId))
        {
            return null;
        }
        //2013-04-16 账户如果是免催的 不停机
        if(FlowInstanceHelper.isExemption(acctId)){
            imsLogger.info("*****账户免催,不置保留期停或者有效期停.");
            return null;
        }
        
        if (expireStr.equalsIgnoreCase(effectStr))
        {//昨日到期的用户
            if (!prodFlag)
            {//非神州行易通卡品牌，直接置有效期停
                listStsSync.add(getEffectiveData(validity));
            }
            else
            {//神州行易通卡品牌，先置保留期停，90天后做有效期停
                listStsSync.add(getRetainData(validity));
            }
        }
        else if (expireStr.equalsIgnoreCase(retainStr))
        {//到期有90天的用户
            CmResLifecycle cycle = getSmsCmp().getContext().getComponent(UserQuery.class).queryLifeCycleByUserId(resourceId);
            //判断用户原先是否有效期停，不是则处理；是的话则不再处理
            if (!CommonUtilSh.check(cycle, EnumCodeShDefine.STS_DTL_FIFTEEN - 1))
                listStsSync.add(getEffectiveData(validity));
        }
        return null;
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

    /**
     * 封装有效停的数据<br>
     * wukl 2012-6-11
     * 
     * @param cmResValidity
     * @return
     */
    private ImsResStsSync getEffectiveData(CmResValidity cmResValidity)
    {
        ImsResStsSync imsResStsSync = new ImsResStsSync();
        CmResIdentity idty = getSmsCmp().querySingle(CmResIdentity.class,
                new DBCondition(CmResIdentity.Field.resourceId, cmResValidity.getResourceId()));
        if (idty != null)
        {
            imsResStsSync.setPhoneId(idty.getIdentity());
        }
        else
        {
            imsResStsSync.setPhoneId("0");
        }
        CaAccountRes ares = getSmsCmp().querySingle(CaAccountRes.class,
                new DBCondition(CaAccountRes.Field.resourceId, cmResValidity.getResourceId()));
        if (ares != null)
        {
            imsResStsSync.setAcctId(ares.getAcctId());
        }
        else
        {
            imsResStsSync.setAcctId(0L);
        }
        imsResStsSync.setResourceId(cmResValidity.getResourceId());
        imsResStsSync.setCreateDate(getCurrentDate());
        imsResStsSync.setSoNbr(cmResValidity.getSoNbr());
        imsResStsSync.setNsubType(10);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setPsubType(10);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        imsResStsSync.setNotifyFlag((int) EnumCodeDefine.OS_STS_DTL_FIVE);
        imsResStsSync.setOpId(9L);
        imsResStsSync.setOrgId(0);

        return imsResStsSync;
    }

    /**
     * 封装保留停的数据<br>
     * wukl 2012-6-11
     * 
     * @param cmResValidity
     * @return
     */
    private ImsResStsSync getRetainData(CmResValidity cmResValidity)
    {

        ImsResStsSync imsResStsSync = new ImsResStsSync();
        imsResStsSync.setId(DBUtil.getSequence(ImsResStsSync.class));
        CmResIdentity idty = getSmsCmp().querySingle(CmResIdentity.class,
                new DBCondition(CmResIdentity.Field.resourceId, cmResValidity.getResourceId()));
        if (idty != null)
        {
            imsResStsSync.setPhoneId(idty.getIdentity());
        }
        else
        {
            imsResStsSync.setPhoneId("0");
        }
        CaAccountRes ares = getSmsCmp().querySingle(CaAccountRes.class,
                new DBCondition(CaAccountRes.Field.resourceId, cmResValidity.getResourceId()));
        if (ares != null)
        {
            imsResStsSync.setAcctId(ares.getAcctId());
        }
        else
        {
            imsResStsSync.setAcctId(0L);
        }
        imsResStsSync.setResourceId(cmResValidity.getResourceId());
        imsResStsSync.setNotifyFlag((int) EnumCodeDefine.OS_STS_DTL_SIX);
        imsResStsSync.setCreateDate(getCurrentDate());
        imsResStsSync.setSoNbr(cmResValidity.getSoNbr());
        imsResStsSync.setNsubType(0);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setPsubType(0);// CRM不会使用，因为表结构不允许为空，才设置默认值
        imsResStsSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
        imsResStsSync.setOpId(9L);
        imsResStsSync.setOrgId(0);

        return imsResStsSync;
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

    /**
     * 初始话有效期停，保留期停的开始，结束时间
     * 易通卡品牌的初始化
     * 
     * @Author: wukl
     * @Date: 2012-11-7
     */
    private void instanceDateParam()
    {
        // 获取保留期的扫描时间(当用户已经处于有效期停90天，则需要置成保留期停)
        // 获取保留期的扫描的配置天数
        int retainConfig = -91;
        Calendar retainDate = Calendar.getInstance();
        retainDate.setTime(getCurrentDate());

        retainDate.add(Calendar.DATE, retainConfig);
        retainBeginTime = DateUtil.dayBegin(retainDate.getTime());
        retainEndTime = DateUtil.dayEnd(retainDate.getTime());

        // 获取有效期的扫描时间（一般是凌晨4点，扫描前一天到期的时间）
        // 获取有效期的扫描的配置天数
        int effectiveConfig = -1;
        Calendar effectiveDate = Calendar.getInstance();
        effectiveDate.setTime(getCurrentDate());

        effectiveDate.add(Calendar.DATE, effectiveConfig);
        effectiveBeginTime = DateUtil.dayBegin(effectiveDate.getTime());
        effectiveEndTime = DateUtil.dayEnd(effectiveDate.getTime());
        
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
//    @Override
//    protected Date getMessageSendDate()
//    {
//        return getCurrentDate();
//    }

    @Override
    protected Long getTemplateId()
    {
        return null;
    }

    public void commitOther()
    {
        if (CommonUtil.isNotEmpty(listStsSync))
        {
            DBUtil.insertBatch(listStsSync);
            listStsSync.clear();
        }
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

}
