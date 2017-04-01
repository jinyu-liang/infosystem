package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jef.database.Condition.Operator;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.BillCycleComplex;
import com.ailk.ims.component.helper.ProdCycleHelper;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.CacheQuery;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.ims3h.Offering3hbean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.bill.entity.CaMdbBillCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.ImsDelayProdSync;
import com.ailk.openbilling.persistence.imsintf.entity.SBillCycle;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.pm.entity.PmCompositeDeductRule;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * 产品账期相关组件
 * 
 * @Description
 * @author ljc
 * @Date 2011-11-15
 * @Date 2012-03-20 ljc 增加括号，防止时间溢出
 * @Date 2012-04-09 lijc3 修改 by bill生效
 * @Date2012-04-16 lijc3 修改获取产品第一个账期
 * @Date 2012-04-19 lijc3 修改逻辑判断获取账户或者产品的生效时间较早的一个账期
 * @Date 2012-04-20 lijc3 修改账期 修改只有一个正常账期的时候 第一个账期的处理情况
 * @Date 2012-04-20 lijc3 创建一个镜像数组，进行移出 操作
 * @Date 2012-05-11 lijc3 简化上海解析账期操作
 * @Date 2012-05-12 lijc3 CO_PROD_BILLING_CYCLE增加CYCLE_SYNC_FLAG字段
 * @Date 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
 * @Date 2012-06-04 yangjh story:46775 增加half_cycle字段判断产品周期是否需要加上短周期
 * @Date 2012-06-06 yanchuan 避免脏数据报空指针
 * @Date 2012-06-27 yugb [47121]Fixed expire date of offering
 * @Date 2012-06-29 yangjh bug:49641 失效时间获取不正确
 * @date 2012-07-06 luojb 增加获取目标时间所在账期记录的方法
 * @date 2012-07-07 luojb 修改产品账期按普通方式update Bug #50022
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class ProductCycleComponent extends CacheQuery
{

    public ProductCycleComponent()
    {
    }

    /**
     * @Description:解析出产品账期周期
     */
    public CoProdBillingCycle parseProdBillingCycle(SProductOrder sProd)
    {
        // @Date 2012-05-11 lijc3 简化上海解析账期操作
        if (ProjectUtil.is_CN_SH())
        {
            PmProductOfferLifecycle dmLifeCycle = context.getComponent(BaseProductComponent.class).queryProdOfferLifeCycle(
                    sProd.getOffer_id());
            Date validDate = IMSUtil.formatDate(sProd.getValid_date(), context.getRequestDate());
            Date expireDate = IMSUtil.formatExpireDate(sProd.getExpire_date());
            Date cycleFirstDay = ProdCycleHelper.createFirstBillDate(new Date(validDate.getTime()), null,
                    dmLifeCycle.getCycleType(), dmLifeCycle.getCycleUnit());
            CoProdBillingCycle bc = new CoProdBillingCycle();
            bc.setProductId(sProd.getProduct_id());
            bc.setCycleType(dmLifeCycle.getCycleType());
            bc.setFirstBillDate(cycleFirstDay);
            bc.setValidDate(validDate);
            bc.setExpireDate(expireDate);
            bc.setCycleUnit(dmLifeCycle.getCycleUnit());
            Integer cycleFlag = dmLifeCycle.getCycleSyncFlag() == null ? EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT
                    : dmLifeCycle.getCycleSyncFlag();
            bc.setCycleSyncFlag(cycleFlag);
            return bc;
        }
        Long billAcctId = null;
        if (sProd.getUser_id() != null || CommonUtil.isNotEmpty(sProd.getPhone_id()))
        {
            User3hBean bean = context.get3hTree().loadUser3hBean(sProd.getUser_id(), sProd.getPhone_id());
            billAcctId = bean.getBillAcctId();
        }
        else if (sProd.getAcct_id() != null)
        {
            CaAccount dmAccount = context.get3hTree().loadAcct3hBean(sProd.getAcct_id()).getAccount();
            billAcctId = context.getComponent(AccountQuery.class).queryBillAcctIdByAcct(dmAccount);
        }
        return parseProdBillingCycle(sProd.getOffer_id(), sProd.getBill_cycle(), sProd.getValid_date(), sProd.getExpire_date(),
                sProd.getValid_type(), billAcctId, sProd);
    }

    /**
     * L
     * 
     * @Description
     * @Author lijingcheng
     * @param validType
     * @param sProd
     * @param acctId
     * @param soDate
     * @param strValidDate
     * @param cycleType
     * @param cycleUnit
     * @return
     */
    public Date getValidDateByValidType(Short validType, SProductOrder sProd, Long acctId, String strValidDate,
            Integer cycleType, Integer cycleUnit)
    {
        Date soDate = null;
        if (CommonUtil.isNotEmpty(context.getOper().getSo_date()))
        {
            soDate = IMSUtil.formatExpireDate(context.getOper().getSo_date());
        }
        else
        {
            soDate = context.getRequestDate();
        }

        Date validDate = null;
        switch (validType)
        {
        case EnumCodeDefine.PROD_VALID_IMMEDIATELY:
            validDate = context.getRequestDate();
            break;
        case EnumCodeDefine.PROD_VALID_NEXT_CYCLE:// 下个周期，下一天，下一个月生效时间处理按照soper里面的sodate来处理
            if (acctId != null && sProd != null)
            {
                validDate = getValidDateByNextCycle(sProd, acctId, soDate);
            }
            if (validDate == null)
            {
                validDate = DateUtil.dayBegin(IMSUtil.offsetDate(new Date(soDate.getTime()), cycleType, cycleUnit));
            }
            break;
        case EnumCodeDefine.PROD_VALID_NEXT_DAY:
            validDate = DateUtil.offsetDate(new Date(soDate.getTime()), Calendar.DAY_OF_MONTH, 1);
            validDate = DateUtil.dayBegin(validDate);// 第二天00:00:00生效
            break;
        case EnumCodeDefine.PROD_VALID_NEXT_MONTH:
            validDate = DateUtil.offsetDate(new Date(soDate.getTime()), Calendar.MONTH, 1);
            break;
        case EnumCodeDefine.PROD_VALID_SPECIFIC_DATE:
            if (!CommonUtil.isEmpty(strValidDate))
            {
                validDate = DateUtil.getFormattedDate(strValidDate);
            }
            else
            {
                validDate = context.getRequestDate();
            }
            break;

        default:
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "validType");
        }
        return validDate;
    }

    /**
     * 解析产品账期，加入对validType的处理
     * 
     * @param offerId 销售品id
     * @param billCycle 账期结构
     * @param strValidDate 生效时间
     * @param strExpireDate 失效时间
     * @param validType 生效类型
     * @param acctId 支付账户id
     * @param sProd 产品结构 validType 为1的时候 该结构必须传 ljc 2011-11-15
     */
    public CoProdBillingCycle parseProdBillingCycle(Long offerId, SBillCycle billCycle, String strValidDate,
            String strExpireDate, Short validType, Long acctId, SProductOrder sProd)
    {
        // ProductComponent prodCmp = context.getComponent(ProductComponent.class);
        if (CommonUtil.isEmpty(context.getOper().getSo_date()))
        {
            // 2011-12-08 wukl 非webService方式调用so_date没有传值
            context.getOper().setSo_date(DateUtil.formatDate(context.getRequestDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }
        Date cycleFirstDay = null;
        Date validDate = null;
        Date expireDate = null;
        // [47121] yanchuan 销售品配置的固定日期 2012-06-27
        Date fixed_expire_date = null;

        Date currentDate = context.getRequestDate();

        // 2011-08-18 zengxr check for billCycle, three fields must all be
        // 都要查询这个，因为需要 确认生效时间长度.
        // 2012-08-10 luojb 代码评审修改：使用offer3hbean
        Offering3hbean bean = context.get3hTree().loadOffering3hBean(offerId);
        PmProductOfferLifecycle dmLifeCycle = bean.getOfferLifeCycle();
//        if (dmLifeCycle == null)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.NEWREG_PRODCYCLE_NOTEXIST, offerId);
//        }
        // [47121] yanchuan 销售品配置的固定日期 2012-06-27
        if (dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_FIXED_EXPIRE_TYPE)
        {
            if (dmLifeCycle.getFixedExpireDate() != null)
            {
                fixed_expire_date = dmLifeCycle.getFixedExpireDate();
            }
            else
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "fixed_expire_date");
            }
        }
        // 处理账期单位和数量
        Integer cycleUnit = dmLifeCycle.getCycleUnit();
        Integer cycleType = dmLifeCycle.getCycleType();
        // [46997]修改first_bill_date获取方式 yanchuan 2012-05-30
        Integer cycleSyncFlag = dmLifeCycle.getCycleSyncFlag();
        // change by ljc 根据传入生效类型判断产品生效时间
        if (validType == null)
        {
            // 推算生效日期
            // 根据配置推算
            if (EnumCodeDefine.PROD_EFFECT_MODE_IMMEDIATELY == dmLifeCycle.getSubEffectMode())
            {
                // 立即生效
                validDate = currentDate;
            }
            else
            {
                // @Date 2012-04-09 lijc3 修改 by bill生效
                if (acctId != null)
                {
                    List<BillCycleComplex> complexList = context.getComponent(AccountQuery.class).queryBillCycle(acctId);
                    if (CommonUtil.isNotEmpty(complexList))
                    {
                        BillCycleComplex complex = complexList.get(0);
                        if (complexList.get(0).getCycleType() == EnumCodeDefine.ACCT_BILLCYCLETYPE_UNDEFINE
                                && dmLifeCycle.getSubDelayCycle() == EnumCodeDefine.PROD_DELAY_CYCLE_BILL)
                        {
                            validDate = complex.getExpireDate();
                        }
                        else
                        {
                            validDate = ProdCycleHelper.createOrderValidDate(dmLifeCycle.getSubDelayUnit(),
                                    dmLifeCycle.getSubDelayCycle(), complex.getCycleUnit(), complex.getCycleType(), currentDate);
                        }
                    }
                    else
                    {
                        validDate = ProdCycleHelper.createOrderValidDate(dmLifeCycle.getSubDelayUnit(),
                                dmLifeCycle.getSubDelayCycle(), cycleUnit, cycleType, currentDate);
                    }
                }
                else
                {
                    validDate = ProdCycleHelper.createOrderValidDate(dmLifeCycle.getSubDelayUnit(),
                            dmLifeCycle.getSubDelayCycle(), cycleUnit, cycleType, currentDate);
                }
            }
        }
        else
        {// 传入validType
            validDate = getValidDateByValidType(validType, sProd, acctId, strValidDate, cycleType, cycleUnit);
        }
        Integer first_bill_day = null;
        if (billCycle != null && billCycle.getFirst_bill_day() != null)
        {
            first_bill_day = billCycle.getFirst_bill_day();
        }
        cycleFirstDay = ProdCycleHelper.createFirstBillDate(new Date(validDate.getTime()), first_bill_day, cycleType, cycleUnit);

        // 推算失效日期
        if (!CommonUtil.isEmpty(strExpireDate))
        {

            expireDate = IMSUtil.formatDate(strExpireDate, null);// 有传入则直接返回
            PmProductOffering offering = bean.getOffering();
            // [47121] yanchuan 销售品配置的固定日期 2012-06-27
            if (dmLifeCycle != null)
            {
                if (dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_FIXED_EXPIRE_TYPE)
                {
                    if (expireDate.after(fixed_expire_date))
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_EXPIRE_DATE_AFTER_FIXED_EXPIRE_DATE, expireDate,
                                fixed_expire_date);
                    }
                }
            }
            if (expireDate.after(offering.getExpireDate()))
            {
                // 如果销售品失效期小于配置的失效期则取销售品的失效期
                expireDate = offering.getExpireDate();
            }

        }
        else
        { // 转换成账期的传入进行计算
            Integer totalCycleUnit = null;
            if (dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_CYCLETYPE_BILL && acctId != null)
            {
                List<BillCycleComplex> complexList = context.getComponent(AccountQuery.class).queryBillCycle(acctId);
                if (CommonUtil.isEmpty(complexList))
                {
                    expireDate = IMSUtil.offsetDate(validDate,
                            ProdCycleHelper.transferValidCylceType2BillCylceType(dmLifeCycle.getValidType()),
                            dmLifeCycle.getValidUnit());
                }
                else
                {
                    // 账户当前账期截止时间
                    // Date endDate = context.getComponent(AccountQuery.class).queryAcctNextCycleStart(acctId);
                    Date endDate = null;
                    if (complexList.get(0).getCycleType() == EnumCodeDefine.ACCT_BILLCYCLETYPE_UNDEFINE)
                    {
                        if (complexList.size() == 1)
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCT_CYCLE_WRONG);
                        }
                        // 短账期
                        if (endDate == null)
                        {
                            endDate = complexList.get(0).getExpireDate();
                        }
                        expireDate = IMSUtil.offsetDate(endDate, complexList.get(1).getCycleType(), dmLifeCycle.getValidUnit()
                                * complexList.get(1).getCycleUnit());
                    }
                    else
                    {
                        if (endDate == null)
                        {
                            endDate = getCurrentAcctBillCycleEndDate(validDate, complexList.get(0));
                        }
                        // 2012-06-04 yangjh story:46775 增加half_cycle字段判断产品周期是否需要加上短周期
                        if (dmLifeCycle.getHalfCycleFlag() == EnumCodeDefine.PROD_HALF_CYCLE_NO)
                        {
                            totalCycleUnit = dmLifeCycle.getValidUnit() * complexList.get(0).getCycleUnit();
                        }
                        else
                        {
                            // Date 2012-06-29 yangjh bug:49641 失效时间获取不正确
                            totalCycleUnit = (dmLifeCycle.getValidUnit() - 1) * (complexList.get(0).getCycleUnit());
                        }
                        expireDate = IMSUtil.offsetDate(endDate, complexList.get(0).getCycleType(), totalCycleUnit);
                    }
                }
            }
            // [47121] yanchuan 销售品配置的固定日期 2012-06-27
            else if (dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_FIXED_EXPIRE_TYPE)
            {
                expireDate = fixed_expire_date;
            }
            else
            {
                if ((dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_CYCLETYPE_WEEK
                        || dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_CYCLETYPE_MONTH || dmLifeCycle.getValidType() == EnumCodeDefine.PROD_VALID_CYCLETYPE_YEAR)
                        && dmLifeCycle.getHalfCycleFlag() == EnumCodeDefine.PROD_HALF_CYCLE_NO)
                {
                    totalCycleUnit = dmLifeCycle.getValidUnit() + 1;
                }
                else
                {
                    totalCycleUnit = dmLifeCycle.getValidUnit();
                }
                expireDate = IMSUtil.offsetDate(validDate,
                        ProdCycleHelper.transferValidCylceType2BillCylceType(dmLifeCycle.getValidType()), totalCycleUnit);
            }
        }
        imsLogger.debug("**************validDate=" + validDate);
        imsLogger.debug("**************expireDate=" + expireDate);
        // @Date2012-05-09 lijc3 上海不需要判断
        if (ProjectUtil.is_TH_AIS())
        {
            if (expireDate.equals(validDate) || validDate.after(expireDate))
            {

                throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_CONFIG_OF_EXPIRE_AND_VALID_DATE);
            }
            // @Date 2012-09-24 yangjh : bug59879 增加失效时间不能小于当前时间的限制
            if (expireDate.before(context.getRequestDate()))
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.CONFIG_EXPIRE_DATE_INVALID, expireDate, context.getRequestDate());
            }
            // dmLifeCycle.getCycleType()==0表示需要同步到账户账期
            if (dmLifeCycle.getCycleType() != 0 && dmLifeCycle.getCycleType() != EnumCodeDefine.MDB_DEFAULT_VALUE)
            {
                if (cycleUnit == 0)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "PM_PRODUCT_OFFER_LIFECYCLE.CYCLE_UNIT");
                }
                ProdCycleHelper.validtimeMustBiggerThanAnOfferCycleUnit(validDate, expireDate, dmLifeCycle.getCycleType(),
                        dmLifeCycle.getCycleUnit());
            }
            else
            {
                // 如果cycle_type==0但是又不是同步账户账期 报错
                if (dmLifeCycle.getCycleSyncFlag() == null
                        || (dmLifeCycle.getCycleSyncFlag() != EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_BY_ACCOUNT && dmLifeCycle
                                .getCycleSyncFlag() != EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_NO_PIROD))
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "PM_PRODUCT_OFFER_LIFECYCLE.CYCLE_TYPE");
                }
            }
        }
        if (cycleFirstDay.after(expireDate))
        {
            cycleFirstDay = expireDate;
        }
        imsLogger.debug("***********first_bill_day=" + cycleFirstDay);
        CoProdBillingCycle dmProdCycle = new CoProdBillingCycle();
        // [46997]修改first_bill_date获取方式 yanchuan 2012-05-29
        if (isUseValidDate(cycleSyncFlag, sProd.getOffer_id()))
        {
            dmProdCycle.setFirstBillDate(validDate);
        }
        else
        {
            dmProdCycle.setFirstBillDate(cycleFirstDay);
        }
        dmProdCycle.setValidDate(validDate);
        dmProdCycle.setExpireDate(expireDate);
        dmProdCycle.setSoNbr(context.getSoNbr());
        dmProdCycle.setCycleType(cycleType);
        dmProdCycle.setCycleUnit(cycleUnit);
        Integer cycleFlag = dmLifeCycle.getCycleSyncFlag() == null ? EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT : dmLifeCycle
                .getCycleSyncFlag();
        dmProdCycle.setCycleSyncFlag(cycleFlag);
        return dmProdCycle;
    }

    /**
     * @author yanchuan 2012-5-30
     * @describe 判断产品首个计费日是否取产品的生效时间，返回true表示取产品生效时间，false表示不取
     * @param cycleSyncFlag
     * @param bill_type
     * @return
     */
    public boolean isUseValidDate(Integer cycleSyncFlag, Long offer_id)
    {
        if (cycleSyncFlag == null)
            return false;
        else if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_BY_PROD)
            return true;
        else if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT)
        {
            CacheQuery cacheQry = context.getComponent(CacheQuery.class);
            Integer offeringBillingType = cacheQry.queryProdOfferingBillingType(offer_id);
            // 2012-06-06 yanchuan 避免脏数据报空指针
            if (offeringBillingType != null && offeringBillingType == EnumCodeDefine.USER_PAYMODE_PREPAID)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    /**
     * 如果是下周期、下月、自定义的延后生效，需要插入接口表同步给SCP、AIP luojb 2011-11-21
     */
    public void recordDelayValidProd(SProductOrder prodOrder, Long productId, CoProdBillingCycle billCyc)
    {
        Short validType = prodOrder.getValid_type();
        Date validDate = billCyc.getValidDate();
        long offerId = prodOrder.getOffer_id();
        boolean isDelayValid = false;
        if (validType == null || validType.intValue() == EnumCodeDefine.PROD_VALID_SPECIFIC_DATE)
        {
            Date curDate = DateUtil.currentDate();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            calendar.add(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);

            Date nextDayEnd = calendar.getTime();
            if (validDate.after(nextDayEnd))
            {
                isDelayValid = true;
            }
        }
        else if (validType.intValue() == EnumCodeDefine.PROD_VALID_NEXT_CYCLE
                || validType.intValue() == EnumCodeDefine.PROD_VALID_NEXT_MONTH)
        {
            isDelayValid = true;
        }
        if (isDelayValid)
        {
            ImsDelayProdSync dmSync = new ImsDelayProdSync();
            // @Date 2012-5-30 tangjl5 不使用oracle的自增长序列
            dmSync.setId(DBUtil.getSequence(ImsDelayProdSync.class));
            dmSync.setOfferId(offerId);
            dmSync.setProductId(productId);
            dmSync.setCreateDate(context.getRequestDate());
            dmSync.setSoNbr(context.getSoNbr());
            dmSync.setSts((int) EnumCodeDefine.SYNC_DEAL_STS_INITIAL);
            dmSync.setValidDate(validDate);

            insert(dmSync);
        }
    }

    /**
     * 修改产品的账期 ，billType为付费类型标识 yanchuan 2011-10-6
     */
    public void changeProdBillCycle(CoProd prod, Integer billType, List<BillCycleComplex> billCycleList)
    {
        List<CoProdBillingCycle> billCycles = new ArrayList<CoProdBillingCycle>();
        PmProductOfferLifecycle offerLifeCycle = context.getComponent(BaseProductComponent.class).queryProdOfferLifeCycle(
                prod.getProductOfferingId());
        if (offerLifeCycle == null || offerLifeCycle.getCycleSyncFlag() == null)
        {
            return;
        }
        List<CoProdBillingCycle> prodCycles = this.queryProdBillingCycles(prod.getProductId(), prod.getObjectId());
        if (CommonUtil.isEmpty(prodCycles))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_BILL_CYCLE_NOT_EXIST, prod.getProductId());
        }
        CoProdBillingCycle first = null;

        if (prodCycles.size() == 1)
        {
            first = prodCycles.get(0);
        }
        else
        {
            first = this.queryFirstBillCycle(prod, prodCycles);
        }
        Integer cycleType = first.getCycleType();
        Integer cycleUnit = first.getCycleUnit();

        boolean flag = false;
        short cycleSyncFlag = offerLifeCycle.getCycleSyncFlag().shortValue();
        if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT)
        {
            // 仅当billType为后付费或者后付费为主, 才修改其产品账期
            if (billType != null
                    && (billType.intValue() == EnumCodeDefine.PROD_BILLTYPE_POSTPAID || billType == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID))
            {
                flag = true;
                // billCycles = this.getBillCycle(prod, billCycleList);
            }
        }
        else if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_BY_ACCOUNT)
        {
            flag = true;
            // billCycles = this.getBillCycle(prod, billCycleList);
        }
        if (flag)
        {
            if (prod.getValidDate().before(context.getRequestDate()))
            {
                // 删除未生效的
                // List<CoProdBillingCycle> delCycles = (List<CoProdBillingCycle>) DBUtil.deleteByConditionWithReturn(
                // CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prod.getProductId()),
                // new DBCondition(CoProdBillingCycle.Field.objectId, prod.getObjectId()), new DBCondition(
                // CoProdBillingCycle.Field.validDate, context.getRequestDate(), Operator.GREAT_EQUALS));
                // if (CommonUtil.isNotEmpty(delCycles))
                // {
                // for (CoProdBillingCycle cc : delCycles)
                // {
                // cc.setExpireDate(context.getRequestDate());
                // context.cacheSingle(cc);
                // }
                // }
                // 2012-07-07 luojb 正常删除
                deleteByCondition(CoProdBillingCycle.class,
                        new DBCondition(CoProdBillingCycle.Field.productId, prod.getProductId()), new DBCondition(
                                CoProdBillingCycle.Field.objectId, prod.getObjectId()), new DBCondition(
                                CoProdBillingCycle.Field.validDate, context.getRequestDate(), Operator.GREAT_EQUALS));

                BillCycleComplex firstAcctCycle = this.getFirstAcctCycle(billCycleList);
                // @Date 2012-04-20 lijc3 修改只有一个正常账期的时候 第一个账期的处理情况
                Date billDate = null;
                Date expireDate = null;
                if (firstAcctCycle.getCycleType() != EnumCodeDefine.ACCT_BILLCYCLETYPE_UNDEFINE)
                {
                    billDate = ProdCycleHelper.createFirstBillDateByAcct(context.getRequestDate(),
                            firstAcctCycle.getFirstBillDate(), firstAcctCycle.getCycleUnit(), firstAcctCycle.getCycleType());
                    // 2012-07-19 #52414 解决产品账期日期重叠问题
                    expireDate = firstAcctCycle.getExpireDate();
                    // expireDate = prod.getExpireDate();
                }
                else
                {
                    expireDate = firstAcctCycle.getExpireDate();
                    billDate = firstAcctCycle.getExpireDate();
                }
                CoProdBillingCycle value = new CoProdBillingCycle();
                value.setFirstBillDate(billDate);
                value.setExpireDate(expireDate);
                // CoProdBillingCycle where = new CoProdBillingCycle();
                // where.setProductId(prod.getProductId());
                // where.setObjectId(prod.getObjectId());
                // List<CoProdBillingCycle> ret = (List<CoProdBillingCycle>) DBUtil.updateByConditionWithReturn(value,
                // new DBCondition(CoProdBillingCycle.Field.productId, prod.getProductId()), new DBCondition(
                // CoProdBillingCycle.Field.objectId, prod.getObjectId()));
                // if (CommonUtil.isNotEmpty(ret))
                // {
                // for (CoProdBillingCycle c : ret)
                // {
                // c.setExpireDate(expireDate);
                // c.setFirstBillDate(billDate);
                // context.cacheSingle(c);
                // }
                // }
                // 2012-07-07 luojb 正常删除
                // 目前需求只有一条数据，后续将会变更
                value.setCycleNum(context.getComponent(BaseProductComponent.class).calcuCycleNum(value.getProductId(),
                        context.getRequestDate(), first));
                updateByCondition(value, new DBCondition(CoProdBillingCycle.Field.productId, prod.getProductId()),
                        new DBCondition(CoProdBillingCycle.Field.objectId, prod.getObjectId()));

                // @Date 2012-04-20 创建一个镜像数组，进行移出 操作
                List<BillCycleComplex> billCycleListPirr = new ArrayList<BillCycleComplex>();
                billCycleListPirr.addAll(billCycleList);
                billCycleListPirr.remove(firstAcctCycle);
                for (BillCycleComplex complex : billCycleListPirr)
                {
                    billCycles.add(buildCoProdBillingCycleByAcctCycle(prod, complex, cycleType, cycleUnit));
                }
            }
            else
            {
                // 2012-07-07 luojb 正常删除
                // List<CoProdBillingCycle> delCycles = (List<CoProdBillingCycle>) DBUtil.deleteByConditionWithReturn(
                // CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prod.getProductId()),
                // new DBCondition(CoProdBillingCycle.Field.objectId, prod.getObjectId()));
                // if (CommonUtil.isNotEmpty(delCycles))
                // {
                // for (CoProdBillingCycle cc : delCycles)
                // {
                // cc.setExpireDate(context.getRequestDate());
                // context.cacheSingle(cc);
                // }
                // }
                deleteByCondition(CoProdBillingCycle.class,
                        new DBCondition(CoProdBillingCycle.Field.productId, prod.getProductId()), new DBCondition(
                                CoProdBillingCycle.Field.objectId, prod.getObjectId()));
                for (BillCycleComplex complex : billCycleList)
                {
                    billCycles.add(buildCoProdBillingCycleByAcctCycle(prod, complex, cycleType, cycleUnit));
                }
            }
        }

        if (CommonUtil.isNotEmpty(billCycles))
        {

            // 删除老账期
            // CoProdBillingCycle cycle = new CoProdBillingCycle();
            // cycle.setProductId(prod.getProductId());
            // this.deleteByCondition_noInsert(cycle);
            //
            // // 获取第一个账期，设置生效时间为当前时间
            // CoProdBillingCycle temp = this.queryFirstBillCycle(null, billCycles);
            // temp.setValidDate(value.getValidDate());
            // 增加新账期
            // 2012-09-26 linzt 增加cycleNum
            // 目前需求只有一条
            CoProdBillingCycle coProdBillingCyc = billCycles.get(0);
            coProdBillingCyc.setCycleNum(context.getComponent(BaseProductComponent.class).calcuCycleNum(
                    coProdBillingCyc.getProductId(), coProdBillingCyc.getValidDate(), first));
            super.insertBatch(billCycles);
        }
    }

    private CoProdBillingCycle buildCoProdBillingCycleByAcctCycle(CoProd dmProd, BillCycleComplex billCycle, Integer cycleType,
            Integer cycleUnit)
    {
        if (billCycle.getExpireDate().before(dmProd.getValidDate()))
        {
            return null;
        }
//        Integer coProdBillingType = dmProd.getBillingType();
//        PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class).queryPmCompsiteDeductRuleByOfferId(
//                dmProd.getProductOfferingId(), dmProd.getBillingType());
//        if (deductRule != null)
//        {
//            coProdBillingType = deductRule.getDeductFlag();
//        }
//        else
//        {
//            if (coProdBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID)
//            {// budget中存在2的付费模式，当做后付费处理
//                coProdBillingType = EnumCodeDefine.USER_PAYMODE_POSTPAID;
//            }
//        }
        CoProdBillingCycle cycle = new CoProdBillingCycle();
        cycle.setCreateDate(context.getRequestDate());
        cycle.setCycleType(cycleType);
        cycle.setCycleUnit(cycleUnit);
        // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
        // cycle.setDeductFlag(coProdBillingType);
        if (dmProd.getExpireDate().before(billCycle.getExpireDate()))
        {
            cycle.setExpireDate(dmProd.getExpireDate());
        }
        else
        {
            cycle.setExpireDate(billCycle.getExpireDate());
        }
        if (dmProd.getValidDate().after(billCycle.getValidDate()))
        {
            cycle.setValidDate(dmProd.getValidDate());
            cycle.setFirstBillDate(ProdCycleHelper.createFirstBillDateByAcct(dmProd.getValidDate(), billCycle.getFirstBillDate(),
                    billCycle.getCycleUnit(), billCycle.getCycleType()));
        }
        else
        {
            cycle.setValidDate(billCycle.getValidDate());
            cycle.setFirstBillDate(ProdCycleHelper.createFirstBillDateByAcct(billCycle.getValidDate(),
                    billCycle.getFirstBillDate(), billCycle.getCycleUnit(), billCycle.getCycleType()));

        }
        cycle.setProductId(dmProd.getProductId());
        cycle.setSoNbr(context.getSoNbr());
        if (billCycle.getCycleType() != null && billCycle.getCycleType() == 0)
        {
            // 特殊的短账期 设置为动态天
            // long expireTime = billCycle.getExpireDate().getTime();
            // long validTime = billCycle.getValidDate().getTime();
            // int unit = (int) (expireTime - validTime) / (1000 * 60 * 60 * 24);
            // cycle.setCycleUnit(unit);
            // cycleType = EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY;
            cycle.setFirstBillDate(billCycle.getExpireDate());
        }
        if (cycle.getFirstBillDate().after(cycle.getExpireDate()))
        {
            cycle.setFirstBillDate(cycle.getExpireDate());
        }
        if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH)
        {
            cycle.setFirstBillDate(DateUtil.dayBegin(cycle.getFirstBillDate()));
        }
        cycle.setObjectId(dmProd.getObjectId());
        cycle.setObjectType(dmProd.getObjectType());
        // 设置cycle_sync_flag
        PmProductOfferLifecycle dmLifeCycle = context.getComponent(ConfigQuery.class).queryProdOfferLifeCycle(
                dmProd.getProductOfferingId());
        Integer cycleFlag = dmLifeCycle.getCycleSyncFlag() == null ? EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT : dmLifeCycle
                .getCycleSyncFlag();
        cycle.setCycleSyncFlag(cycleFlag);
        cycle.setCycleNum(0);
        return cycle;
    }

    /**
     * 根据账户账期生成产品账期 ljc 2011-10-27
     */
    private CoProdBillingCycle buildCoProdBillingCycleByAcctCycle(CoProd dmProd, BillCycleComplex billCycle, Integer deductFlag)
    {
        if (billCycle.getExpireDate().before(dmProd.getValidDate()))
        {
            return null;
        }
        // 把账户账期枚举值换成产品账期枚举值
        Integer cycleType = billCycle.getCycleType();
        // PmProductOfferLifecycle
        // lifeCycle=context.getComponent(ProductComponent.class).queryProdOfferLifeCycle(dmProd.getProductOfferingId());
        CoProdBillingCycle cycle = new CoProdBillingCycle();
        cycle.setCreateDate(context.getRequestDate());
        cycle.setCycleType(cycleType);
        cycle.setCycleUnit(billCycle.getCycleUnit());
        // @Date 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
        /*
         * if (deductFlag != null) { cycle.setDeductFlag(deductFlag); } else { cycle.setDeductFlag(dmProd.getBillingType()); }
         */
        if (dmProd.getExpireDate().before(billCycle.getExpireDate()))
        {
            cycle.setExpireDate(dmProd.getExpireDate());
        }
        else
        {
            cycle.setExpireDate(billCycle.getExpireDate());
        }
        if (dmProd.getValidDate().after(billCycle.getValidDate()))
        {
            cycle.setValidDate(dmProd.getValidDate());
            cycle.setFirstBillDate(ProdCycleHelper.createFirstBillDateByAcct(dmProd.getValidDate(), billCycle.getFirstBillDate(),
                    billCycle.getCycleUnit(), billCycle.getCycleType()));
        }
        else
        {
            cycle.setValidDate(billCycle.getValidDate());
            cycle.setFirstBillDate(ProdCycleHelper.createFirstBillDateByAcct(billCycle.getValidDate(),
                    billCycle.getFirstBillDate(), billCycle.getCycleUnit(), billCycle.getCycleType()));

        }
        cycle.setProductId(dmProd.getProductId());
        cycle.setSoNbr(context.getSoNbr());
        if (cycleType != null && cycleType == 0)
        {
            // 特殊的短账期 设置为动态天
            long expireTime = billCycle.getExpireDate().getTime();
            long validTime = billCycle.getValidDate().getTime();
            // ljc 增加括号，防止时间溢出
            int cycleUnit = (int) ((expireTime - validTime) / (1000 * 60 * 60 * 24));
            cycle.setCycleUnit(cycleUnit);
            cycleType = EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY;
            cycle.setCycleType(cycleType);
            cycle.setFirstBillDate(billCycle.getExpireDate());
        }
        if (cycle.getFirstBillDate().after(cycle.getExpireDate()))
        {
            cycle.setFirstBillDate(cycle.getExpireDate());
        }
        if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH)
        {
            cycle.setFirstBillDate(DateUtil.dayBegin(cycle.getFirstBillDate()));
        }
        cycle.setObjectId(dmProd.getObjectId());
        cycle.setObjectType(dmProd.getObjectType());
        // 设置cycle_sync_flag
        PmProductOfferLifecycle dmLifeCycle = context.getComponent(ConfigQuery.class).queryProdOfferLifeCycle(
                dmProd.getProductOfferingId());
        Integer cycleFlag = dmLifeCycle.getCycleSyncFlag() == null ? EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT : dmLifeCycle
                .getCycleSyncFlag();
        cycle.setCycleSyncFlag(cycleFlag);
        cycle.setCycleNum(0);
        return cycle;
    }

    /**
     * 获取当前账期的出账日 ljc 2011-9-30 如果当前账期结束日大于当前账期失效期，取失效期
     */
    public Date getCurrentBillCycleEndDate(CoProdBillingCycle cycle)
    {
        Date cycleDate = getCurrentBillCycleEndDateNormal(cycle);
        // 如果当前账期结束日大于当前账期失效期，取失效期
        if (cycleDate.after(cycle.getExpireDate()))
        {
            return cycle.getExpireDate();
        }
        else
        {
            return cycleDate;
        }
    }

    /**
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param cycle
     * @return
     * @author: tangjl5
     * @Date: 2012-7-24
     */
    public Date getCurrentBillCycleEndDateNormal(CoProdBillingCycle cycle)
    {
        if (cycle == null)
            return null;
        Calendar cycleCalendar = Calendar.getInstance();
        cycleCalendar.setTime(cycle.getFirstBillDate());
        int firstBillDay = cycleCalendar.get(Calendar.DAY_OF_YEAR);//
        int dueDay = cycleCalendar.get(Calendar.DAY_OF_MONTH);// 获取出账日子
        Date date = new Date(context.getRequestDate().getTime());// 当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currDay = calendar.get(Calendar.DAY_OF_YEAR);
        switch (cycle.getCycleType().intValue())
        {
        case EnumCodeDefine.PROD_CYCLETYPE_DAY:
        case EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY:
        {
            if (date.before(cycle.getFirstBillDate()))
            {
                calendar.setTime(cycle.getFirstBillDate());
            }
            else
            {
                int temp = (currDay - firstBillDay) % (cycle.getCycleUnit());// 当前账期已经过去的日子
                temp = cycle.getCycleUnit() - temp;
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + temp);
            }
        }
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_WEEK:
        {
            if (date.before(cycle.getFirstBillDate()))
            {
                calendar.setTime(cycle.getFirstBillDate());
            }
            else
            {
                int temp = (currDay - firstBillDay) % (cycle.getCycleUnit() * 7);// 当前账期已经过去的日子
                temp = (cycle.getCycleUnit() * 7) - temp;
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + temp);
            }
        }
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_MONTH:
        case EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH:
        case EnumCodeDefine.PROD_CYCLETYPE_MONTH_OFFSET:
        {
            if (date.before(cycle.getFirstBillDate()))
            {
                calendar.setTime(cycle.getFirstBillDate());
            }
            else
            {
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                if (currentDay > dueDay)
                {// 当前日大于出账日 则账期结束在下一个月
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
                    calendar.set(Calendar.DAY_OF_MONTH, dueDay);
                }
                else
                {
                    calendar.set(Calendar.DAY_OF_MONTH, dueDay);
                }
            }
        }
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_YEAR:
        {
            if (date.before(cycle.getFirstBillDate()))
            {
                calendar.setTime(cycle.getFirstBillDate());
            }
            else
            {
                int currenYear = calendar.get(Calendar.YEAR);
                int firstBillYear = cycleCalendar.get(Calendar.YEAR);
                int temp = cycle.getCycleUnit() - (currenYear - firstBillYear);
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + temp);
            }
        }
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_HOUR:
        {
            if (date.before(cycle.getFirstBillDate()))
            {
                calendar.setTime(cycle.getFirstBillDate());
            }
            else
            {
                int temp = ((currDay - firstBillDay) * 24) % (cycle.getCycleUnit());
                temp = cycle.getCycleUnit() - temp;// 当前账期还剩余多少
                calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + temp);// 按剩余量偏移
            }
        }
            break;

        default:
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "bill_cycle_type");
        }

        return calendar.getTime();
    }

    /**
     * @Description: 根据新账户账期变更用户级产品账期
     * @author wangjt
     */
    public void changeProdBillCycle(CmResource user, Long oldAcctId, Long newAcctId, List<Long> notChgProdIdList)
    {
        AccountComponent acctCmp = context.getComponent(AccountComponent.class);
        List<BillCycleComplex> listBillCycle = acctCmp.getBillCycle(oldAcctId, newAcctId);

        // 若账期信息为空，表示新老账户账期一致，不进行产品账期变更
        if (CommonUtil.isEmpty(listBillCycle))
        {
            return;
        }
        // 判断用户是否为后付费用户
        if (user.getBillingType().intValue() != EnumCodeDefine.USER_PAYMODE_POSTPAID
                && user.getBillingType().intValue() != EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID)
        {
            return;
        }

        Long userId = user.getResourceId();
        List<CoProd> prods = context.getComponent(BaseProductComponent.class).queryProdListByUserId(userId);
        // ljc
        List<CoProd> newProdList = context.getAllCacheList(CoProd.class,
                new CacheCondition(CoProd.Field.soNbr, context.getSoNbr()));
        if (CommonUtil.isEmpty(notChgProdIdList))
        {
            notChgProdIdList = new ArrayList<Long>();
        }
        if (!CommonUtil.isEmpty(newProdList))
        {
            for (CoProd dmProd : newProdList)
            {
                notChgProdIdList.add(dmProd.getProductId());
            }
        }

        for (CoProd prod : prods)
        {
            // 该产品无需变更账期
            if (notChgProdIdList.contains(prod.getProductId()))
            {
                continue;
            }
            // 特殊产品
            if (!SpecCodeDefine.needChangeBillCycle(prod.getBusiFlag()))
            {
                continue;
            }
            // 账期变更
            changeProdBillCycle(prod, user.getBillingType(), listBillCycle);
        }
    }

    /**
     * wangjt 2011-10-10 产品的账期，和账户一致
     */
    public void createProdBillCycle(CoProd coProd, Long acctId)
    {
        Integer coProdBillingType = coProd.getBillingType();
        PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class).queryPmCompsiteDeductRuleByOfferId(
                coProd.getProductOfferingId(), coProd.getBillingType());
        if (deductRule != null)
        {
            coProdBillingType = deductRule.getDeductFlag();
        }
        else
        {
            if (coProdBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID)
            {// budget中存在2的付费模式，当做后付费处理
                coProdBillingType = EnumCodeDefine.USER_PAYMODE_POSTPAID;
            }
        }

        // taoyf 2012-2-23, 预算产品的账期始终跟着和账户走。
        AccountComponent acctCmp = context.getComponent(AccountComponent.class);
        List<CoProdBillingCycle> billCycles = null;

        List<BillCycleComplex> list = acctCmp.queryBillCycle(acctId);

        if (!CommonUtil.isEmpty(list))
        {
            imsLogger.debug("************count of billcycle by account :" + list.size());
            billCycles = createProdBillingCycles(coProd, list, coProdBillingType);
        }
        else
        {// 账户账期不存在
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCONT_NO_BILLCYCLE, acctId);
        }
        this.insertBatch(billCycles);
    }

    /**
     * @Description 产品下周期生效
     * @Author lijingcheng
     * @param order
     * @param billAcctId
     * @param soDate
     * @return
     */
    private Date getValidDateByNextCycle(SProductOrder order, Long billAcctId, Date soDate)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        boolean isAccountLevelProd = prodCmp.isAccountLevelOrderProduct(order);
        PmProductOfferLifecycle offerLifeCycle = context.getComponent(BaseProductComponent.class).queryProdOfferLifeCycle(
                order.getOffer_id().intValue());
        Integer cycleUnit = offerLifeCycle.getCycleUnit();
        Integer cycleType = offerLifeCycle.getCycleType();
        if (offerLifeCycle.getCycleSyncFlag() != null)
        {
            short cycleSyncFlag = offerLifeCycle.getCycleSyncFlag().shortValue();
            if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT)
            {
                if (!isAccountLevelProd)
                {
                    // 用户级产品按用户的付费类型来计算
                    User3hBean bean = context.get3hTree().loadUser3hBean(order.getUser_id(), order.getPhone_id());
                    CmResource dmUser = bean.getUser();
                    if (dmUser.getBillingType() == EnumCodeDefine.PROD_BILLTYPE_POSTPAID
                            || dmUser.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID)
                    {// 后付费
                        return context.getComponent(AccountQuery.class).queryAcctCycleStart(billAcctId, soDate,
                                EnumCodeDefine.ACCT_NEXT_BILL_CYCLE);
                    }
                    else
                    {
                        return DateUtil.dayBegin(IMSUtil.offsetDate(new Date(soDate.getTime()), cycleType, cycleUnit));
                    }
                }
                else
                {
                    // 账户级产品按账户计算
                    return context.getComponent(AccountQuery.class).queryAcctCycleStart(billAcctId, soDate,
                            EnumCodeDefine.ACCT_NEXT_BILL_CYCLE);
                }
            }
            else if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_BY_ACCOUNT)
            {
                return context.getComponent(AccountQuery.class).queryAcctCycleStart(billAcctId, soDate,
                        EnumCodeDefine.ACCT_NEXT_BILL_CYCLE);
            }
            else if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_NO_PIROD)
            {
                throw IMSUtil.throwBusiException(ErrorCodeDefine.ONE_TIME_CANNOT_NEXT_CYCLE_VALID);
            }
            else
            {
                // 其他不做调整即按照产品配置生效
                return DateUtil.dayBegin(IMSUtil.offsetDate(new Date(soDate.getTime()), cycleType, cycleUnit));
            }
        }
        else
        {
            // 按产品
            return DateUtil.dayBegin(IMSUtil.offsetDate(new Date(soDate.getTime()), cycleType, cycleUnit));
        }
    }

    /**
     * modify by ljc add CoProd dmProd, CaAccount dmAccount,CmResource dmUser involveType 使用类型 ljc 2011-10-1
     * 
     * @param acctId :填支付账户，没有则填归属账户
     */
    public List<CoProdBillingCycle> createProdBillCycle(CoProdBillingCycle dmProdCycle, CoProd dmProd, Long acctId,
            CmResource dmUser, int involveType)
    {
        AccountComponent acctCmp = context.getComponent(AccountComponent.class);
        // 新开户实例化产品账期取账户时，不能调账管接口查询 2011-11-23 luojb
        // 取新开户缓存起来的账户账期,并把该产品对应账户的账期取出
        List<BillCycleComplex> list = getNewRegBillCycle(dmProd.getObjectId());
        
        dmProdCycle.setProductId(dmProd.getProductId());
        dmProdCycle.setObjectId(dmProd.getObjectId());
        dmProdCycle.setObjectType(dmProd.getObjectType());
        dmProdCycle.setCycleNum(0);
        Integer coProdBillingType = dmProd.getBillingType();
        PmCompositeDeductRule deductRule = context.getComponent(ProdStsComponent.class).queryPmCompsiteDeductRuleByOfferId(
                dmProd.getProductOfferingId(), dmProd.getBillingType());
        if (deductRule != null)
        {
            coProdBillingType = deductRule.getDeductFlag();
        }
        else
        {
            if (coProdBillingType == EnumCodeDefine.USER_PAYMODE_HYBRID)
            {// budget中存在2的付费模式，当做后付费处理
                coProdBillingType = EnumCodeDefine.USER_PAYMODE_POSTPAID;
            }
        }
        // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
        // dmProdCycle.setDeductFlag(coProdBillingType);

        List<CoProdBillingCycle> billCycles = new ArrayList<CoProdBillingCycle>();
        PmProductOfferLifecycle offerLifeCycle = context.getComponent(BaseProductComponent.class).queryProdOfferLifeCycle(
                dmProd.getProductOfferingId().longValue());
        if (offerLifeCycle != null && offerLifeCycle.getCycleSyncFlag() != null)
        {
            short cycleSyncFlag = offerLifeCycle.getCycleSyncFlag().shortValue();
            if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_DEFAULT)
            {
                if (involveType == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
                {
                    // 用户级产品按用户的付费类型来计算
                    if (dmUser.getBillingType() == EnumCodeDefine.PROD_BILLTYPE_POSTPAID
                            || dmUser.getBillingType() == EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID)
                    {// 后付费

                        if (CommonUtil.isEmpty(list))
                        {
                            list = acctCmp.queryBillCycle(acctId);
                        }

                        if (!CommonUtil.isEmpty(list))
                        {
                            billCycles = createProdBillingCycles(dmProd, list, coProdBillingType);
                        }
                        else
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCONT_NO_BILLCYCLE, acctId);
                        }
                    }
                    else
                    {
                        billCycles.add(dmProdCycle);
                    }
                }
                else
                {
                    // 账户级产品按产品的付费方式计算
                    if (dmProd.getBillingType() == EnumCodeDefine.PROD_BILLTYPE_POSTPAID)
                    {
                        if (CommonUtil.isEmpty(list))
                        {
                            list = acctCmp.queryBillCycle(acctId);
                        }
                        if (!CommonUtil.isEmpty(list))
                        {
                            billCycles = createProdBillingCycles(dmProd, list, coProdBillingType);
                        }
                        else
                        {
                            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCONT_NO_BILLCYCLE, acctId);
                        }
                    }
                    else
                    {
                        billCycles.add(dmProdCycle);
                    }
                }
            }
            else if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_BY_ACCOUNT)
            {

                if (CommonUtil.isEmpty(list))
                {
                    list = acctCmp.queryBillCycle(acctId);
                }
                if (!CommonUtil.isEmpty(list))
                {
                    billCycles = createProdBillingCycles(dmProd, list, coProdBillingType);
                }
                else
                {// 账户账期不存在
                 // throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCONT_NO_BILLCYCLE, acctId);
                }
            }
            else if (cycleSyncFlag == EnumCodeDefine.PROD_OFFER_CYCLE_FLAG_NO_PIROD)
            {
                // 一次性产品需要把长度实例化出来
                long expireTime = dmProdCycle.getExpireDate().getTime();
                long validTime = dmProdCycle.getValidDate().getTime();
                int cycleUnit = (int) ((expireTime - validTime) / (1000 * 60 * 60 * 24));
                if (cycleUnit == 0)
                {
                    cycleUnit = 1;
                }
                dmProdCycle.setCycleUnit(cycleUnit);
                dmProdCycle.setCycleType(EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY);
                dmProdCycle.setFirstBillDate(dmProdCycle.getExpireDate());
                billCycles.add(dmProdCycle);
            }
            else
            {
                // 其他不做调整即按照产品配置生效
                billCycles.add(dmProdCycle);
            }
        }
        else
        {
            billCycles.add(dmProdCycle);
        }

        this.insertBatch(billCycles);

        return billCycles;
    }
    
    /**
     * 新开户订购产品 同步账户账期，从缓存里取账户账期
     * luojb 2012-11-5
     * @param objectId
     * @return
     */
    public List<BillCycleComplex> getNewRegBillCycle(Long objectId)
    {
        List<CaMdbBillCycle> cycleList = (List<CaMdbBillCycle>) context.getExtendParam(ConstantDefine.BILL_CYCLE_KEY_NAME);
        List<BillCycleComplex> list = null;
        if (!CommonUtil.isEmpty(cycleList))
        {
            list = new ArrayList<BillCycleComplex>();
            for (CaMdbBillCycle cyc : cycleList)
            {
                if (cyc.getAcctId().longValue() == objectId)
                {
                    BillCycleComplex cycComplex = new BillCycleComplex();
                    cycComplex.setCycleType(cyc.getCycleType());
                    cycComplex.setCycleUnit(cyc.getCycleUnit());
                    cycComplex.setExpireDate(cyc.getExpireDate());
                    cycComplex.setValidDate(cyc.getValidDate());
                    cycComplex.setFirstBillDate(cyc.getBillDateShift());
                    list.add(cycComplex);
                }
            }
        }
        return list;
    }

    /**
     * ljc 2011-9-29
     * 
     * @param dmProd 产品
     * @param dmProdCycle 产品当前账期
     * @param list 账户账期变化
     * @param objectType
     * @param objectId
     */
    public List<CoProdBillingCycle> createProdBillingCycles(CoProd dmProd, List<BillCycleComplex> list, Integer deductFlag)
    {
        List<CoProdBillingCycle> billCycs = new ArrayList<CoProdBillingCycle>();
        for (BillCycleComplex acctCycle : list)
        {
            CoProdBillingCycle cycle = buildCoProdBillingCycleByAcctCycle(dmProd, acctCycle, deductFlag);
            if (cycle != null)
            {
                billCycs.add(cycle);
            }
        }
        if (billCycs.size() == 0)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CHANGE_PROD_ACCOUNT_CYCLE_ALL_EXPIRED);
        }
        return billCycs;
    }

    /**
     * 根据帐户id修改帐户订购和所有归属用户的订购的产品的账期
     * 
     * @author yanchuan 2011-10-6
     */
    public void changeProdBillCycle(Long acctId)
    {
        BaseProductComponent prodCmp = context.getComponent(BaseProductComponent.class);
        UserComponent userCmp = context.getComponent(UserComponent.class);
        if (!CommonUtil.isValid(acctId))
        {
            return;
        }
        // 账户的账期
        List<BillCycleComplex> billCycleList = context.getComponent(AccountComponent.class).queryBillCycle(acctId);

        // 根据帐户id查询帐户订购的产品
        List<CoProd> acctProdList = prodCmp.queryProdListByAcctId(acctId);
        // 修改帐户级产品的账期
        if (!CommonUtil.isEmpty(acctProdList))
        {
            Iterator it = acctProdList.iterator();
            while (it.hasNext())
            {
                CoProd prod = (CoProd) it.next();
                if (prod == null)
                {
                    continue;
                }
                changeProdBillCycle(prod, prod.getBillingType(), billCycleList);
            }
        }
        // 查询支付帐户下的所有用户
        List<CmResource> billuserList = userCmp.queryUserByBillAcctId(acctId);
        // 修改用户级产品的账期
        if (!CommonUtil.isEmpty(billuserList))
        {
            Iterator it1 = billuserList.iterator();
            while (it1.hasNext())
            {
                CmResource resource = (CmResource) it1.next();
                if (resource == null || !CommonUtil.isValid(resource.getResourceId()))
                {
                    continue;
                }
                List<CoProd> userProdList = prodCmp.queryProdListByUserId(resource.getResourceId());
                if (!CommonUtil.isEmpty(userProdList))
                {
                    Iterator it2 = userProdList.iterator();
                    while (it2.hasNext())
                    {
                        CoProd prod = (CoProd) it2.next();
                        if (prod == null)
                        {
                            continue;
                        }
                        changeProdBillCycle(prod, resource.getBillingType(), billCycleList);
                    }
                }
            }
        }
    }

    public CoProdBillingCycle queryProdBillingCycle(Long prodId, Long objectId)
    {
        return querySingle(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prodId),
                new DBCondition(CoProdBillingCycle.Field.objectId, objectId));
    }

    /**
     * 查询产品的第一个账期 ljc 2011-11-16
     * 
     * @Date 2012-04-19 lijc3 修改逻辑判断获取账户或者产品的生效时间较早的一个账期
     */
    public CoProdBillingCycle queryFirstBillCycle(CoProd dmProd, List<CoProdBillingCycle> cycles)
    {
        if (CommonUtil.isEmpty(cycles))
        {
            cycles = this.queryProdBillingCycles(dmProd.getProductId(), dmProd.getObjectId());
        }
        if (CommonUtil.isEmpty(cycles))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_BILL_CYCLE_NOT_EXIST, dmProd.getProductId());
        }
        CoProdBillingCycle temp = null;
        for (CoProdBillingCycle complex : cycles)
        {
            if (temp == null)
            {
                temp = complex;
            }
            else
            {
                if (temp.getValidDate().after(complex.getValidDate()))
                {
                    temp = complex;
                }
            }
        }
        return temp;
        /*
         * if (CommonUtil.isEmpty(cycles)) { cycles = this.queryProdBillingCycles(dmProd.getProductId(), dmProd.getObjectId()); }
         * if (CommonUtil.isEmpty(cycles)) { throw IMSUtil.throwBusiException(ErrorCodeDefine.PRODUCT_BILL_CYCLE_NOT_EXIST,
         * dmProd.getProductId()); } if (cycles.size() == 1) { return cycles.get(0); } else if (cycles.size() == 2) { return
         * ProdCycleHelper.getBeforeCycle(cycles.get(0), cycles.get(1)); } else if (cycles.size() == 3) { CoProdBillingCycle cycle
         * = ProdCycleHelper.getBeforeCycle(cycles.get(0), cycles.get(1)); return ProdCycleHelper.getBeforeCycle(cycle,
         * cycles.get(2)); } else if (cycles.size() == 4) { CoProdBillingCycle cycle =
         * ProdCycleHelper.getBeforeCycle(cycles.get(0), cycles.get(1)); cycle = ProdCycleHelper.getBeforeCycle(cycle,
         * cycles.get(2)); return ProdCycleHelper.getBeforeCycle(cycle, cycles.get(3)); // @Date2012-04-16 lijc3 修改获取产品第一个账期 }
         * else if (cycles.size() == 5) { CoProdBillingCycle cycle = ProdCycleHelper.getBeforeCycle(cycles.get(0), cycles.get(1));
         * cycle = ProdCycleHelper.getBeforeCycle(cycle, cycles.get(2)); cycle = ProdCycleHelper.getBeforeCycle(cycle,
         * cycles.get(3)); return ProdCycleHelper.getBeforeCycle(cycle, cycles.get(4)); } else { throw
         * IMSUtil.throwBusiException(ErrorCodeDefine.COUNT_OF_BILLCYCLE_BY_PRODU_WRONG, dmProd.getProductId()); }
         */
    }

    // 获取账户的第一个账期
    public BillCycleComplex getFirstAcctCycle(List<BillCycleComplex> cycles)
    {
        BillCycleComplex temp = null;
        for (BillCycleComplex complex : cycles)
        {
            if (temp == null)
            {
                temp = complex;
            }
            else
            {
                if (temp.getValidDate().after(complex.getValidDate()))
                {
                    temp = complex;
                }
            }
        }
        return temp;
        /*
         * if (cycles.size() == 1) { return cycles.get(0); } else if (cycles.size() == 2) { return
         * ProdCycleHelper.getBeforeCycle(cycles.get(0), cycles.get(1)); } else if (cycles.size() == 3) { BillCycleComplex cycle =
         * ProdCycleHelper.getBeforeCycle(cycles.get(0), cycles.get(1)); return ProdCycleHelper.getBeforeCycle(cycle,
         * cycles.get(2)); } else if (cycles.size() == 4) { BillCycleComplex cycle = ProdCycleHelper.getBeforeCycle(cycles.get(0),
         * cycles.get(1)); cycle = ProdCycleHelper.getBeforeCycle(cycle, cycles.get(2)); return
         * ProdCycleHelper.getBeforeCycle(cycle, cycles.get(3)); } else if (cycles.size() == 5) { BillCycleComplex cycle =
         * ProdCycleHelper.getBeforeCycle(cycles.get(0), cycles.get(1)); cycle = ProdCycleHelper.getBeforeCycle(cycle,
         * cycles.get(2)); cycle = ProdCycleHelper.getBeforeCycle(cycle, cycles.get(3)); return
         * ProdCycleHelper.getBeforeCycle(cycle, cycles.get(4)); } else { throw
         * IMSUtil.throwBusiException(ErrorCodeDefine.COUNT_OF_BILLCYCLE_BY_PRODU_WRONG); }
         */
    }

    /**
     * 查询产品账期，可能有多个 ljc 2011-9-30
     */
    public List<CoProdBillingCycle> queryProdBillingCycles(Long prodId, Long objectId)
    {
        return query(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prodId), new DBCondition(
                CoProdBillingCycle.Field.objectId, objectId));
    }

    /**
     * @Description: CoProdBillingCycle根据生效时间进行升序排列
     * @param prodId
     * @return
     * @author: tangjl5
     * @Date: 2012-2-9
     */
    public List<CoProdBillingCycle> queryProdBillingCycleAsc(Long prodId)
    {
        return query(CoProdBillingCycle.class,
                new OrderCondition[] { new OrderCondition(true, CoProdBillingCycle.Field.validDate) }, null, new DBCondition(
                        CoProdBillingCycle.Field.productId, prodId));
    }

    public void deleteProdBillingCycleByProdId(Long prodId)
    {
        super.deleteByCondition(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prodId));
    }

    public void deleteProdBillingCycleByProdId(Long prodId, Long objectId)
    {
        super.deleteByCondition(CoProdBillingCycle.class, new DBCondition(CoProdBillingCycle.Field.productId, prodId),
                new DBCondition(CoProdBillingCycle.Field.objectId, objectId));
    }

    /**
     * @Description: (指定日期删除prodbilling)
     */
    public void deleteProdBillingCycleByProdId(Long prodId, Date effectiveDate)
    {
        // 首次激活 新老记录生失效时间置为激活时间 2011-12-15 wukl
        Date activeDate = context.getComponent(BaseProductComponent.class).getActiveDate();
        effectiveDate = activeDate == null ? effectiveDate : activeDate;

        CoProdBillingCycle cycle = new CoProdBillingCycle();
        cycle.setExpireDate(effectiveDate);
        // 修改付费模式中，如果原产品的付费模式变更了，那么账期中的deduct_flag也需要变更
        // 2012-05-14 zhangzj3 注释co_prod_billing_cycle.deduct_flag赋值，不再实例化该字段
        /*
         * if (context.getExtendParam(ConstantDefine.NEED_CHANGE_PAY_MODE) != null) { cycle.setDeductFlag((Integer)
         * context.getExtendParam(ConstantDefine.NEED_CHANGE_PAY_MODE)); }
         */
        // CoProdBillingCycle where = new CoProdBillingCycle();
        // where.setProductId(prodId);
        super.updateByCondition(cycle, activeDate, new DBCondition(CoProdBillingCycle.Field.productId, prodId));
    }

    /**
     * @Description 获取账户的当前账期的结束时间
     * @Author lijingcheng
     * @param startDate
     * @param cycle
     * @return
     */
    public Date getCurrentAcctBillCycleEndDate(Date startDate, BillCycleComplex cycle)
    {
        Calendar cycleCalendar = Calendar.getInstance();
        cycleCalendar.setTime(cycle.getValidDate());
        int firstBillDay = cycleCalendar.get(Calendar.DAY_OF_YEAR);//
        int dueDay = cycle.getFirstBillDate();// 获取出账日子
        Date date = new Date(startDate.getTime());// 当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currDay = calendar.get(Calendar.DAY_OF_YEAR);
        switch (cycle.getCycleType().intValue())
        {
        // [47002]帐户账期类型与产品账期类型字段枚举值已统一，需使用产品的账期类型 yanchuan 2012-06-01
        case EnumCodeDefine.PROD_CYCLETYPE_DAY:
        {
            if (date.before(cycle.getValidDate()))
            {
                calendar.setTime(cycle.getValidDate());
            }
            else
            {
                int temp = (currDay - firstBillDay) % (cycle.getCycleUnit());// 当前账期已经过去的日子
                temp = cycle.getCycleUnit() - temp;
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + temp);
            }
        }
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_WEEK:
        {
            if (date.before(cycle.getValidDate()))
            {
                calendar.setTime(cycle.getValidDate());
            }
            else
            {
                int temp = (currDay - firstBillDay) % (cycle.getCycleUnit() * 7);// 当前账期已经过去的日子
                temp = (cycle.getCycleUnit() * 7) - temp;
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + temp);
            }
        }
            break;
        // @Date 2012-09-22 yangjh : 枚举值3->5
        case EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH:
        {
            if (date.before(cycle.getValidDate()))
            {
                calendar.setTime(cycle.getValidDate());
            }
            else
            {
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                if (currentDay > dueDay)
                {// 当前日大于出账日 则账期结束在下一个月
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
                    calendar.set(Calendar.DAY_OF_MONTH, dueDay);
                }
                else
                {
                    calendar.set(Calendar.DAY_OF_MONTH, dueDay);
                }
            }
        }
            break;
        case EnumCodeDefine.PROD_CYCLETYPE_YEAR:
        {
            if (date.before(cycle.getValidDate()))
            {
                calendar.setTime(cycle.getValidDate());
            }
            else
            {
                int currenYear = calendar.get(Calendar.YEAR);
                int firstBillYear = cycleCalendar.get(Calendar.YEAR);
                int temp = cycle.getCycleUnit() - (currenYear - firstBillYear);
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + temp);
            }
        }
            break;
        case EnumCodeDefine.ACCT_BILLCYCLETYPE_UNDEFINE:
            return cycle.getExpireDate();
        default:
            throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "bill_cycle_type");
        }
        // 如果当前账期结束日大于当前账期失效期，取失效期
        if (calendar.getTime().after(cycle.getExpireDate()))
        {
            return cycle.getExpireDate();
        }
        else
        {
            return calendar.getTime();
        }
    }

    /**
     * 获取目标时间所在的产品账期记录，如果billCycles只有一条，直接返回 luojb 2012-7-6
     * 
     * @param billCycles
     * @param targetDate
     * @return
     */
    public CoProdBillingCycle getTargetBillCycle(List<CoProdBillingCycle> billCycles, Date targetDate)
    {
        if (CommonUtil.isEmpty(billCycles))
            return null;
        if (billCycles.size() == 1)
            return billCycles.get(0);
        if (targetDate == null)
            targetDate = context.getRequestDate();
        for (CoProdBillingCycle billCycle : billCycles)
        {
            if (!billCycle.getValidDate().after(targetDate) && billCycle.getExpireDate().after(targetDate))
                return billCycle;
        }
        return null;
    }
}
