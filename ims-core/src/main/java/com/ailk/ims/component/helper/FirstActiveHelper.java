package com.ailk.ims.component.helper;

import java.util.List;
import javax.management.ReflectionException;

import jef.common.log.Logger;
import jef.tools.reflect.BeanUtils;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SAccountEx;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SCustomer;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SProdInfo;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SPromBillCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserCycle;
import com.ailk.easyframe.sdl.MMdbSyncUpDef.SUserEx;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObject;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.pm.entity.PmFirstActiveGprsUrl;

/**
 * @Description:首次激活相关静态方法类
 * @author wukl
 * @Date 2012-1-10
 */
public class FirstActiveHelper
{
    private FirstActiveHelper()
    {
    }

    /**
     * @Description: get the current valid record from a obj
     * @param obj
     * @param actDate
     * @return
     */
    public static Object catchCurrentData(Object obj, int actDate)
    {
        int exp = getIntDate(obj, "get_expireDate");
        int vld = getIntDate(obj, "get_validDate");
        if (exp > vld && exp > actDate && vld <= actDate)
        {
            return obj;
        }
        return null;
    }

    /**
     * @Description: get the current valid record from a list
     * @param list
     * @param actDate, first activate date
     * @return
     * @author tangjl5
     */
    public static <T extends Object>  void catchCurrentData(List<T> list, int actDate, List<T> outList)
    {
        for (int i = 0; i < list.size(); i++)
        {
            T obj = list.get(i);
            int exp = getIntDate(obj, "get_expireDate");
            int vld = getIntDate(obj, "get_validDate");
            if (exp > vld && exp > actDate && vld <= actDate)
            {
                outList.add(obj);
            }
        }
    }
    
    /**
     * @Description: get the current valid record from a list
     * @param list
     * @param actDate, first activate date
     * @return
     */
    public static Object catchCurrentData(List<? extends Object> list, int actDate)
    {
        for (int i = 0; i < list.size(); i++)
        {
            Object obj = list.get(i);
            int exp = getIntDate(obj, "get_expireDate");
            int vld = getIntDate(obj, "get_validDate");
            if (exp > vld && exp > actDate && vld <= actDate)
            {
                return obj;
            }
        }
        return null;
    }

    /**
     * @Description: 平移数据
     * @param obj
     * @param actDate, first activate date
     */
    public static void moveTimeSection(Object obj, int actDate, boolean mainPromFlag)
    {

        int exp = getIntDate(obj, "get_expireDate");
        int vld = getIntDate(obj, "get_validDate");
        exp = exp + (actDate - vld);

        // 若是主产品失效时间不做变更
        if (mainPromFlag != true)
            setIntDate(obj, "set_expireDate", exp);// expire date seted to new exp date

        setIntDate(obj, "set_validDate", actDate);// valid_date seted to actDate
    }

    /**
     * 将sync_flag 置为0 wukl 2011-12-20
     * 
     * @param <E>
     * @param list
     */
    public static <E extends CsdlStructObject> void setSyncFlagToZero(E obj)
    {
        if (obj == null)
            return;

        int sync = getIntDate(obj, "get_syncFlag");

        if (sync != 0)
            setIntDate(obj, "set_syncFlag", 0);
    }

    /**
     * 将sync_flag 置为0 wukl 2011-12-20
     * 
     * @param <E>
     * @param list
     */
    public static <E extends CsdlStructObject> void setSyncFlagToZero(CsdlArrayList<E> list)
    {
        if (list == null || list.size() <= 0)
            return;

        for (int i = 0; i < list.size(); i++)
        {
            Object obj = list.get(i);
            int sync = getIntDate(obj, "get_syncFlag");

            if (sync != 0)
                setIntDate(obj, "set_syncFlag", 0);

        }
    }

    private static int getIntDate(Object obj, String method)
    {
        int iDate = 0;
        try
        {
            Integer bIDate = (Integer) BeanUtils.invokeMethod(obj, method, new Object[] {});
            if (bIDate != null)
            {
                iDate = bIDate.intValue();
            }
        }
        catch (ReflectionException e)
        {
            // logger.error("get expireDate or validDate error", e);
        }
        return iDate;
    }

    private static void setIntDate(Object obj, String method, int iDate)
    {
        try
        {
            BeanUtils.invokeMethod(obj, method, new Object[] { Integer.valueOf(iDate) });
        }
        catch (ReflectionException e)
        {
            // logger.error("get expireDate or validDate error", e);
        	
        }
    }

    /**
     * 根据SDL结构(SUserEx)获取CmResource wukl 2012-2-26
     * 
     * @param sUserEx
     * @return
     */
    public static CmResource getCmResourceBySUserEx(SUserEx sUserEx)
    {
        CmResource userInfo = new CmResource();
        userInfo.setBrandId(sUserEx.get_brand());
        userInfo.setBillingType(sUserEx.get_billType());
        userInfo.setRegionCode(sUserEx.get_regionCode());
        userInfo.setResourceId(sUserEx.get_servId());
        // userInfo.setResourceSpecId(sUserEx.get);
        userInfo.setResSegment(sUserEx.get_userSegment());
        userInfo.setValidDate(DateUtil.UTCToDate(sUserEx.get_validDate()));
        userInfo.setExpireDate(DateUtil.UTCToDate(sUserEx.get_expireDate()));
        return userInfo;
    }

    /**
     * 根据SDL结构(SCustomer)获取CmCustomer wukl 2012-2-26
     * 
     * @param sCustomer
     * @return
     */
    public static CmCustomer getCmCustomerBySCustomer(SCustomer sCustomer)
    {
        CmCustomer custInfo = new CmCustomer();
        custInfo.setCustId(sCustomer.get_custId());
        custInfo.setCustomerClass(sCustomer.get_custClass());
        custInfo.setCustomerSegment(sCustomer.get_custSegment());
        custInfo.setCustomerType(sCustomer.get_custType());
        custInfo.setRegionCode(sCustomer.get_regionCode());
        return custInfo;
    }

    /**
     * 根据SDL结构(SAccountEx)获取CaAccount wukl 2012-2-26
     * 
     * @param sAccountEx
     * @return
     */
    public static CaAccount getCaAccountBySAccountEx(SAccountEx sAccountEx)
    {
        CaAccount acctInfo = new CaAccount();
        acctInfo.setAcctId(sAccountEx.get_acctId());
        acctInfo.setAccountType(sAccountEx.get_accountType());
        acctInfo.setRegionCode(sAccountEx.get_regionCode());
        acctInfo.setOperatorId(sAccountEx.get_operatorId());
      //@Date 2012-11-08 wuyujie :加上balance_type,后续查找加个计划需要用到
        acctInfo.setBalanceType(EnumCodeDefine.BALANCE_TYPE_USER);
        return acctInfo;
    }

    /**
     * 根据SDL结构(SProdInfo)获取主产品CoProd wukl 2012-2-26
     * @param sUserEx 
     * 
     * @param prodInfo
     * @return
     */
    public static CoProd getCoProdBySProdInfo(SProdInfo sProdInfo, SUserEx sUserEx)
    {
        CoProd coProd = new CoProd();
        coProd.setIsMain(sProdInfo.get_isMain());
        coProd.setObjectId(sUserEx.get_servId());
        coProd.setProductId(sProdInfo.get_promNo());
        coProd.setProductOfferingId(sProdInfo.get_promOfferId());
        coProd.setBillingType((int) sProdInfo.get_billMode());
        return coProd;
    }

    public static SUserEx getSUserExInContext(IMSContext context)
    {
        SUserEx data = (SUserEx) context.getExtendParam(ConstantDefine.ACTIVE_SDL_SUSEREX);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "UserInfo");

        return data;
    }

    public static SCustomer getSCustomerInContext(IMSContext context)
    {
        SCustomer data = (SCustomer) context.getExtendParam(ConstantDefine.ACTIVE_SDL_SCUSTOMER);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "CustomerInfo");

        return data;
    }

    public static SUserCycle getSUserCycleInContext(IMSContext context)
    {
        SUserCycle data = (SUserCycle) context.getExtendParam(ConstantDefine.ACTIVE_SDL_SUSERCYCLE);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "UserCycle");

        return data;
    }

    public static SAccountEx getSAccountExInContext(IMSContext context)
    {
        SAccountEx data = (SAccountEx) context.getExtendParam(ConstantDefine.ACTIVE_SDL_SACCOUNTEX);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "AccountInfo");

        return data;
    }

    public static SProdInfo getMainSProdInfoInContext(IMSContext context)
    {
        SProdInfo data = (SProdInfo) context.getExtendParam(ConstantDefine.ACTIVE_SDL_MAIN_PROMOTION);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "MainPromotion");

        return data;
    }

    public static CsdlArrayList<SProdInfo> getSProdInfoListInContext(IMSContext context)
    {
        CsdlArrayList<SProdInfo> data = (CsdlArrayList<SProdInfo>) context
                .getExtendParam(ConstantDefine.ACTIVE_SDL_SPRODINFO_LIST);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "ProductInfoList");

        return data;
    }

    public static CsdlArrayList<SPromBillCycle> getSPromBillCycleListInContext(IMSContext context)
    {
        CsdlArrayList<SPromBillCycle> data = (CsdlArrayList<SPromBillCycle>) context
                .getExtendParam(ConstantDefine.ACTIVE_SDL_SPROMBILLCYCLE_LIST);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "ProductBillCycleList");

        return data;
    }
    //yanchuan 2012-07-05 SPromPrice已被计费删除 
//    public static CsdlArrayList<SPromPrice> getSPromPriceListInContext(IMSContext context)
//    {
//        CsdlArrayList<SPromPrice> data = (CsdlArrayList<SPromPrice>) context
//                .getExtendParam(ConstantDefine.ACTIVE_SDL_SPROMPRICE_LIST);
//        if (data == null)
//            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "ProductPriceList");
//
//        return data;
//    }

    public static CmResource getCmResourceInContext(IMSContext context)
    {
        CmResource data = (CmResource) context.getExtendParam(ConstantDefine.ACTIVE_JAVA_CMRESOURCE);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "CmResource");

        return data;
    }

    public static CmCustomer getCmCustomerInContext(IMSContext context)
    {
        CmCustomer data = (CmCustomer) context.getExtendParam(ConstantDefine.ACTIVE_JAVA_CMCUSTOMER);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "CmCustomer");

        return data;
    }

    public static CaAccount getCaAccountInContext(IMSContext context)
    {
        CaAccount data = (CaAccount) context.getExtendParam(ConstantDefine.ACTIVE_JAVA_CAACCOUNT);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "CaAccount");

        return data;
    }

    public static CoProd getMainCoProdInContext(IMSContext context)
    {
        CoProd data = (CoProd) context.getExtendParam(ConstantDefine.ACTIVE_JAVA_MAIN_PROMOTION);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "CoProd");

        return data;
    }

    public static Integer getPatternIdInContext(IMSContext context)
    {
        Integer data = (Integer) context.getExtendParam(ConstantDefine.ACTIVE_PATTERN_ID);
        if (data == null)
            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "Patternid");

        return data;
    }
    
    public static PmFirstActiveGprsUrl getGprsUrlsInContext(IMSContext context)
    {
        PmFirstActiveGprsUrl data = (PmFirstActiveGprsUrl) context.getExtendParam(ConstantDefine.ACTIVE_GPRS_URL);
        //当客户信息都没有的时候，无法匹配到urls，这里允许缓存中为空
//        if (data == null)
//            IMSUtil.throwBusiException(ErrorCodeDefine.NOT_DATA_IN_CONTEXT, "PM_FIRST_ACTIVE_GPRS_URL");
        
        return data;
    }
}
