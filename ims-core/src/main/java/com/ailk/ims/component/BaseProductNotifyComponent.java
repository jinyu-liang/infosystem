package com.ailk.ims.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.common.wrapper.IntRange;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.AlarmCodeDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.ImsProdCycleNotify;
import com.ailk.openbilling.persistence.cust.entity.ImsProdNotifyError;
import com.ailk.openbilling.persistence.cust.entity.ImsProdOnceNotify;
import com.ailk.openbilling.persistence.imsintf.entity.FreeResource;
import com.ailk.openbilling.persistence.imssdl.entity.SProdNotify;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferNotify;

/**
 * 产品通知组件
 * 
 * @Description
 * @author luojb
 * @Date 2011-12-9
 * @Date 2012-3-27 创建通知模板中，加入更换主产品的旧销售品id
 * @date 2012-07-23 luojb #50629 产品通知配置表增加channel_id
 */
public class BaseProductNotifyComponent extends BaseComponent
{

    public BaseProductNotifyComponent()
    {
    }

    /**
     * 从缓存读取销售品配置 luojb 2011-12-21
     * 
     * @return
     */
    private List<PmProductOfferNotify> getProdNotify(Integer notifyType)
    {
        List<DataObject> objList = DBConfigInitBean.getCached(PmProductOfferNotify.class);
        if (CommonUtil.isEmpty(objList))
        {
            return null;
        }
        List<PmProductOfferNotify> notifyList = new ArrayList<PmProductOfferNotify>();
        for (DataObject obj : objList)
        {
            PmProductOfferNotify notify = (PmProductOfferNotify) obj;
            if (notifyType.equals(notify.getNotifyType()))
            {
                notifyList.add(notify);
            }
        }
        return notifyList;
    }

    public PmProductOfferNotify queryNotifyByOfferIdAndNotifyType(Integer offerId, Integer notifyType)
    {
        return queryNotifyByOfferIdAndNotifyType(offerId,notifyType,null);
    }
    
    public PmProductOfferNotify queryNotifyByOfferIdAndNotifyType(Integer offerId, Integer notifyType,Integer channelId)
    {
        List<CacheCondition> conditions = new ArrayList<CacheCondition>();
        conditions.add(new CacheCondition(PmProductOfferNotify.Field.productOfferingId, offerId));
        if (notifyType != null)
            conditions.add(new CacheCondition(PmProductOfferNotify.Field.notifyType, notifyType));
        // 2012-07-23 luojb #50629 产品通知配置表增加channel_id
        
        CacheCondition channelIdCon = new CacheCondition(PmProductOfferNotify.Field.channelId, -1);//通配
        if(channelId != null)
            channelIdCon = new CacheCondition(PmProductOfferNotify.Field.channelId, channelId);
        conditions.add(channelIdCon);
        PmProductOfferNotify offerNotify = DBConfigInitBean.getSingleCached(PmProductOfferNotify.class, 
                conditions.toArray(new CacheCondition[conditions.size()]));
        // 如果用channelId匹配不到，使用通配
        if(offerNotify == null && channelId != null){
            conditions.remove(channelIdCon);
            conditions.add(new CacheCondition(PmProductOfferNotify.Field.channelId, -1));//通配
            offerNotify = DBConfigInitBean.getSingleCached(PmProductOfferNotify.class, 
                    conditions.toArray(new CacheCondition[conditions.size()]));
        }
        
        
        return offerNotify;
    }

    /**
     * 从缓存读取生效提醒的销售品配置 luojb 2011-12-21
     * 
     * @return
     */
    public List<PmProductOfferNotify> getProdValidNotify()
    {
        return getProdNotify(EnumCodeDefine.PROD_NOTIFY_TYPE_VALID);
    }

    /**
     * 从缓存读取失效提醒的销售品配置 luojb 2011-12-21
     * 
     * @return
     */
    public List<PmProductOfferNotify> getProdExpireNotify()
    {
        return getProdNotify(EnumCodeDefine.PROD_NOTIFY_TYPE_EXPIRE);
    }

    /**
     * 从缓存读取周期提醒的销售品配置 luojb 2011-12-21
     * 
     * @return
     */
    public List<PmProductOfferNotify> getProdCycleNotify()
    {
        return getProdNotify(EnumCodeDefine.PROD_NOTIFY_TYPE_CYCLE);
    }

    public List<Date> parseDayRange(Date date)
    {
        List<Date> dateList = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dateList.add(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        dateList.add(cal.getTime());
        return dateList;
    }

    /**
     * 分页查询需要通知的产品 luojb 2011-12-16
     * 
     * @param ProdOfferId
     * @param notifyMode
     * @param offsetType
     * @param offsetUnit
     * @param start
     * @param end
     * @return
     * @throws IMSException
     */
    public List<CoProd> queryValidProd(Integer prodOfferId, List<Date> dateList, int start, int end) throws IMSException
    {
        OrderCondition[] orderCondition = new OrderCondition[] { new OrderCondition(true, CoProd.Field.productId) };

        List<CoProd> prodList = query(CoProd.class, orderCondition, new IntRange(start, end), new DBCondition(
                CoProd.Field.productOfferingId, prodOfferId), new DBCondition(CoProd.Field.validDate, dateList,
                Operator.BETWEEN_L_L));
        return prodList;
    }

    /**
     * 分页查询需要通知的产品 luojb 2011-12-16
     * 
     * @param ProdOfferId
     * @param notifyMode
     * @param offsetType
     * @param offsetUnit
     * @param start
     * @param end
     * @return
     * @throws IMSException
     */
    public List<CoProd> queryExpireProd(Integer prodOfferId, List<Date> dateList, int start, int end) throws IMSException
    {
        OrderCondition[] orderCondition = new OrderCondition[] { new OrderCondition(true, CoProd.Field.productId) };

        List<CoProd> prodList = query(CoProd.class, orderCondition, new IntRange(start, end), new DBCondition(
                CoProd.Field.productOfferingId, prodOfferId), new DBCondition(CoProd.Field.expireDate, dateList,
                Operator.BETWEEN_L_L));
        return prodList;
    }

    /**
     * 查询该offerId的所有已经生效产品
     * 
     * @param offerId
     * @param start
     * @param end
     * @return
     * @throws IMSException
     */
    public List<CoProd> queryCycleProd(Integer offerId, int start, int end) throws IMSException
    {
        OrderCondition[] orderCondition = new OrderCondition[] { new OrderCondition(true, CoProd.Field.productId) };

        List<CoProd> prodList = query(CoProd.class, orderCondition, new IntRange(start, end), new DBCondition(
                CoProd.Field.productOfferingId, offerId), new DBCondition(CoProd.Field.validDate, DateUtil.currentDate(),
                Operator.LESS_EQUALS));
        return prodList;
    }

    // /**
    // * 查询周期通知条件 luojb 2011-12-23
    // *
    // * @param prod
    // * @param notify
    // * @return 返回满足条件的产品列表
    // */
    // public List<CoProd> checkCycleConditon(List<CoProd> prodList, PmProductOfferNotify notify)
    // {
    // Long cycleCount = notify.getCycleCount();
    // Integer cycleType = notify.getCycleType();
    // Integer cycleUnit = notify.getCycleUnit();
    //
    // Integer notifyMode = notify.getNotifyMode();
    // Integer offsetType = notify.getOffsetCycleType();
    // Integer offsetUnit = notify.getOffsetCycleUnit();
    //
    // List<Long> prodIds = new ArrayList<Long>();
    // for (CoProd prod : prodList)
    // {
    // prodIds.add(prod.getProductId());
    // }
    // List<ImsProdCycleNotify> cycleList = query(ImsProdCycleNotify.class, new DBCondition(ImsProdCycleNotify.Field.productId,
    // prodIds, Operator.IN));
    // Map<Long, ImsProdCycleNotify> cycleMap = new HashMap<Long, ImsProdCycleNotify>();
    // if (CommonUtil.isNotEmpty(cycleList))
    // {
    // for (ImsProdCycleNotify cycle : cycleList)
    // {
    // cycleMap.put(cycle.getProductId(), cycle);
    // }
    // }
    // List<CoProd> notifyProdList = new ArrayList<CoProd>();
    // List<ImsProdCycleNotify> insertCycleList = new ArrayList<ImsProdCycleNotify>();
    // for (CoProd prod : prodList)
    // {
    // Long prodId = prod.getProductId();
    // Date notifyDate = null;
    // // 非首次通知
    // if (cycleMap.containsKey(prodId))
    // {
    // ImsProdCycleNotify cycle = cycleMap.get(prodId);
    // int notifyCount = cycle.getNotifyCount();
    // // (-1为不记周期数) 通知次数已满
    // if (cycleCount != null && cycleCount.intValue() != -1 && cycleCount.intValue() <= notifyCount)
    // {
    // continue;
    // }
    // else
    // {
    // notifyDate = cycle.getNextNotifyDate();
    // if (!sameDay(notifyDate, DateUtil.currentDate()))
    // {
    // continue;
    // }
    // notifyProdList.add(prod);
    //
    // Date newNextDate = calculateMoveDate(notifyDate, cycleType, cycleUnit);
    // ImsProdCycleNotify newCycle = new ImsProdCycleNotify();
    // newCycle.setNextNotifyDate(newNextDate);
    // newCycle.setNotifyCount(notifyCount + 1);
    // newCycle.setModifyDate(DateUtil.currentDate());
    // // ImsProdCycleNotify where = new ImsProdCycleNotify();
    // // where.setProductId(prodId);
    // updateByCondition(newCycle, new DBCondition(ImsProdCycleNotify.Field.productId, prodId));
    // }
    // }
    // else
    // // 首次通知
    // {
    // Date offDate = prod.getValidDate();
    // // 计算偏移日期
    // if (notifyMode.intValue() == EnumCodeDefine.PROD_NOTIFY_MODE_AFTER)
    // {
    // offDate = calculateMoveDate(offDate, offsetType, offsetUnit);
    // }
    // // 计算通知日期
    // notifyDate = offDate;
    //
    // if (!sameDay(notifyDate, DateUtil.currentDate()))
    // {
    // continue;
    // }
    // notifyProdList.add(prod);
    // Date newNextDate = calculateMoveDate(notifyDate, cycleType, cycleUnit);
    // ImsProdCycleNotify newCycle = new ImsProdCycleNotify();
    // newCycle.setFirstNotifyDate(notifyDate);
    // newCycle.setProductId(prodId);
    // newCycle.setNextNotifyDate(newNextDate);
    // newCycle.setNotifyCount(1);
    // newCycle.setModifyDate(DateUtil.currentDate());
    //
    // insertCycleList.add(newCycle);
    // }
    //
    // }
    // if (CommonUtil.isNotEmpty(insertCycleList))
    // insertBatch(insertCycleList);
    //
    // return notifyProdList;
    // }

    /**
     * 计算偏移日期 luojb 2011-12-23
     * 
     * @param offDate
     * @param cycleType
     * @param cycleUnit
     * @return
     */
    public Date calculateMoveDate(Date offDate, Integer offType, Integer offUnit, Integer notifyMode, Integer notifyType)
    {
        if (offDate == null)
            return null;
        if (notifyMode != null && notifyMode.intValue() == EnumCodeDefine.PROD_NOTIFY_MODE_IMMEDIATELY && notifyType != null)
        {
            if (notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_VALID
                    || notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_EXPIRE
                    || notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_TRIAL)
                return offDate;
        }

        int count = offUnit;
        Date date = null;
        if (notifyMode != null && notifyMode.intValue() == EnumCodeDefine.PROD_NOTIFY_MODE_BEFORE)
            count = count * -1;
        if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_DAY)
        {
            date = DateUtil.offsetDate(offDate, Calendar.DATE, count);
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_WEEK)
        {
            date = DateUtil.offsetDate(offDate, Calendar.WEEK_OF_MONTH, count);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            date = cal.getTime();
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_MONTH)
        {
            date = DateUtil.offsetDate(offDate, Calendar.MONTH, count);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            date = cal.getTime();
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_YEAR)
        {
            date = DateUtil.offsetDate(offDate, Calendar.YEAR, count);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_YEAR, 1);
            date = cal.getTime();
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_MONTH)
        {
            date = DateUtil.offsetDate(offDate, Calendar.MONTH, count);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_HOUR)
        {
            date = DateUtil.offsetDate(offDate, Calendar.HOUR, count);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_NATURE_OFF_MONTH)
        {
            date = DateUtil.offsetDate(offDate, Calendar.MONTH, count);
            date = DateUtil.dayBegin(date);
        }
        else if (offType.intValue() == EnumCodeDefine.PROD_NOTIFY_OFF_TYPE_DAY)
        {
            date = DateUtil.offsetDate(offDate, Calendar.DATE, count);
        }
        else
        {
            imsLogger.error(
                    "**********the config of offsetType/cycleType is wrong.offsetType/cycleType=[" + offType + "] notifyType=["
                            + notifyType + "]");
            return null;
        }

        Integer maxDay = null;
        if (notifyType != null)
        {
            if (notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_VALID)
                maxDay = SysUtil.getInt(SysCodeDefine.busi.PROD_VALID_NOTIFY_MAX_DAY);
            else if (notifyType.intValue() == EnumCodeDefine.PROD_NOTIFY_TYPE_EXPIRE)
                maxDay = SysUtil.getInt(SysCodeDefine.busi.PROD_EXPIRE_NOTIFY_MAX_DAY);
        }
        if (maxDay != null)
        {
            long tempDay = DateUtil.getBetweenDays(offDate, date);
            if (maxDay.longValue() < tempDay)
            {
                imsLogger.error(
                        "**********configed notify offset day[" + tempDay + "] is larger than max notify offset day[" + maxDay
                                + "]");
                return null;
            }
        }

        return date;
    }

    /**
     * 计算偏移日期 luojb 2011-12-23
     * 
     * @param offDate
     * @param cycleType
     * @param cycleUnit
     * @return
     */
    public Date calculateMoveDate(Date offDate, Integer cycleType, Integer cycleUnit)
    {
        return calculateMoveDate(offDate, cycleType, cycleUnit, null, null);
    }

    // /**
    // * 判断两个date是否是同一天 luojb 2011-12-23
    // *
    // * @param date1
    // * @param date2
    // * @return
    // */
    // private boolean sameDay(Date date1, Date date2)
    // {
    // String strDate1 = DateUtil.getFormatDate(date1, DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
    // String strDate2 = DateUtil.getFormatDate(date2, DateUtil.DATE_FORMAT_EN_B_YYYYMMDD);
    // return strDate1.equals(strDate2);
    // }

    /**
     * 根据产品id和通知类型，删除对应的一次性通知数据 luojb 2012-2-13
     * 
     * @param productId
     * @param notifyType
     * @throws IMSException
     */
    public void deleteOnceNotify(Long productId, Integer notifyType) throws IMSException
    {
        // ImsProdOnceNotify notify = new ImsProdOnceNotify();
        // notify.setProductId(productId);
        // notify.setNotifyType(notifyType);
        DBUtil.deleteByCondition(ImsProdOnceNotify.class, new DBCondition(ImsProdOnceNotify.Field.productId, productId),
                new DBCondition(ImsProdOnceNotify.Field.notifyType, notifyType));
    }

    /**
     * 根据产品id删除周期性通知数据 luojb 2012-2-13
     * 
     * @param productId
     * @throws IMSException
     */
    public void deleteCycleNotify(Long productId) throws IMSException
    {
        // ImsProdCycleNotify notify = new ImsProdCycleNotify();
        // notify.setProductId(productId);
        DBUtil.deleteByCondition(ImsProdCycleNotify.class, new DBCondition(ImsProdCycleNotify.Field.productId, productId));
    }

    public ImsProdCycleNotify queryCycleNotify(Long productId) throws IMSException
    {
        return querySingle(ImsProdCycleNotify.class, new DBCondition(ImsProdCycleNotify.Field.productId, productId));
    }

    /**
     * 销售品免费试用期配置表 luojb 2012-2-14
     * 
     * @param offerId
     * @return
     * @throws IMSException
     */
    public PmProductOfferAttribute queryOfferAttr(Integer offerId) throws IMSException
    {
        return querySingle(PmProductOfferAttribute.class, new DBCondition(PmProductOfferAttribute.Field.productOfferingId,
                offerId));
    }

    public IMSNotification parseNotifyCustomerInfo(Map<String, String> phoneMap, IMSNotification notify, Long objectId,
            Integer objectType)
    {
        Long acctId = null;
        Long userId = null;
        String contactPhone = null;

        if (phoneMap != null && phoneMap.containsKey(objectId + "_" + objectType))
        {
            contactPhone = phoneMap.get(objectId + "_" + objectType);
        }

        if (objectType.intValue() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT)
        {
            acctId = objectId;
            if (contactPhone == null)
                contactPhone = context.getComponent(AccountComponent.class).queryAcctContactPhone(acctId);
        }
        else if (objectType.intValue() == EnumCodeDefine.PROD_OBJECTTYPE_GCA)
        {
//            acctId = context.getComponent(VPNComponent.class).queryBillAcctId(objectId);
          //2012-08-28 linzt 整理组件方法  采用load3hBean
            try
            {
                acctId=context.get3hTree().loadGroup3hBean(objectId).getBillAcctId();
            }
            catch(IMS3hNotFoundException e)
            {
            	imsLogger.error(e,e);
            }
            if (contactPhone == null)
            contactPhone = context.getComponent(VPNComponent.class).queryGroupContactPhone(objectId);
        }
        else if (objectType.intValue() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
        {
            userId = objectId;
        }

        IMS3hBean ims3hbean = context.get3hTree().load3hBean(null, acctId, userId, null);
        if (contactPhone == null)
            contactPhone = ims3hbean.getPhoneId();

        if (contactPhone != null)
        {
            if (phoneMap == null)
                phoneMap = new HashMap<String, String>();
            phoneMap.put(objectId + "_" + objectType, contactPhone);
        }

        notify.setCustId(ims3hbean.getCustId());
        notify.setAcctId(ims3hbean.getAcctId());
        notify.setUserId(ims3hbean.getUserId());
//        notify.addPhone(contactPhone);

        return notify;
    }

    public void sendNotify(List<IMSNotification> notifications)
    {
        imsLogger.dump("notification list : ", notifications);
        if (!CommonUtil.isEmpty(notifications))
        {
            IMSUtil.doNotification(notifications, context);
        }
        else
        {
            imsLogger.info("no need to do notifications", context);
        }
    }

    protected List<FreeResource> queryFreeResource(Long acctId, Long userId, Long productId) throws IMSException
    {
        return null;
    }

    /**
     * 通知错误数据写入错误信息表 luojb 2012-2-14
     * 
     * @param notifications
     */
    public void writeNotifyError(List<IMSNotification> notifications)
    {
        if (CommonUtil.isEmpty(notifications))
            return;
        List<ImsProdNotifyError> errorList = new ArrayList<ImsProdNotifyError>();
        for (IMSNotification notify : notifications)
        {
            ImsProdNotifyError error = new ImsProdNotifyError();
            error.setCreateDate(context.getRequestDate());
            error.setExpireDate(DateUtil.getFormatDate(notify.getParam(AlarmCodeDefine.PARAM_EXPIRE_DATE),
                    DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
            error.setValidDate(DateUtil.getFormatDate(notify.getParam(AlarmCodeDefine.PARAM_VALID_DATE),
                    DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS));
            error.setOfferId(CommonUtil.string2Integer(notify.getParam(AlarmCodeDefine.PARAM_OFFERING_ID)));
            error.setNotificationId(notify.getAlarmId());
            error.setObjectId(CommonUtil.string2Long(notify.getParam(AlarmCodeDefine.PARAM_OBJECT_ID)));
            error.setObjectType(CommonUtil.string2Integer(notify.getParam(AlarmCodeDefine.PARAM_OBJECT_TYPE)));
//            error.setPhoneId(notify.getPhones().get(0));

            errorList.add(error);
        }

        insertBatch(errorList);
    }

    public IMSNotification parseImsNotify(Map<String, String> phoneMap, Integer alarmId, SProdNotify prodNotify,
            boolean notifyFreeRes)
    {
        return parseImsNotify(phoneMap, alarmId, prodNotify, notifyFreeRes, null, null);
    }

    public IMSNotification parseImsNotify(Map<String, String> phoneMap, Integer alarmId, SProdNotify prodNotify,
            boolean notifyFreeRes, Integer freeDays, Integer restDays)
    {
        IMSNotification imsNotify = new IMSNotification();
        this.parseNotifyCustomerInfo(phoneMap, imsNotify, prodNotify.getObjectId(), prodNotify.getObjectType());

        imsNotify.addParam(AlarmCodeDefine.PARAM_OBJECT_ID, CommonUtil.long2String(prodNotify.getObjectId()));
        imsNotify.addParam(AlarmCodeDefine.PARAM_OBJECT_TYPE, CommonUtil.int2String(prodNotify.getObjectType()));
        imsNotify.addParam(AlarmCodeDefine.PARAM_OFFERING_ID, CommonUtil.int2String(prodNotify.getOfferId()));

        imsNotify.setAlarmId(alarmId);
        if (CommonUtil.isValid(prodNotify.getIrCountryName()))
        {
            imsNotify.addDateParam(AlarmCodeDefine.PARAM_VALID_DATE, prodNotify.getIrValidDate());
            imsNotify.addDateParam(AlarmCodeDefine.PARAM_EXPIRE_DATE, prodNotify.getIrExpireDate());
            imsNotify.addParam(AlarmCodeDefine.PARAM_PROD_NAME_ABBREVIATION, prodNotify.getIrCountryName());
        }
        else
        {
            imsNotify.addDateParam(AlarmCodeDefine.PARAM_VALID_DATE, prodNotify.getValidDate());
            imsNotify.addDateParam(AlarmCodeDefine.PARAM_EXPIRE_DATE, prodNotify.getExpireDate());
        }
        if (CommonUtil.isValid(prodNotify.getOldMainOfferId()))
        {
            imsNotify.addParam(AlarmCodeDefine.PARAM_OFFERING_ID_2, CommonUtil.int2String(prodNotify.getOldMainOfferId()));
        }
        imsNotify.addParam(AlarmCodeDefine.PARAM_BILL_TYPE, CommonUtil.int2String(prodNotify.getBillingType()));

        // TODO 定价id imsNotify.addParam(AlarmCodeDefine.,CommonUtil.int2String(prodNotify.));
        if (freeDays != null && restDays != null)
        {
            imsNotify.addParam(AlarmCodeDefine.PARAM_FREE_DAYS, CommonUtil.int2String(freeDays));
            imsNotify.addParam(AlarmCodeDefine.PARAM_FREE_REST_DAYS, CommonUtil.int2String(restDays));
        }

        List<FreeResource> resList = null;
        if (notifyFreeRes)
        {
            Long acctId = null;
            Long userId = null;
            Long objectId = prodNotify.getObjectId();
            Integer objectType = prodNotify.getObjectType();
            Long productId = prodNotify.getProductId();
            if (objectType.intValue() == EnumCodeDefine.PROD_OBJECTTYPE_DEV)
            {
                userId = objectId;
            }
            else
            {
                acctId = objectId;
            }
            resList = this.queryFreeResource(acctId, userId, productId);
            if (CommonUtil.isNotEmpty(resList))
            {
                String remainValue = resList.get(0).getRemain_resource().toString();
                String totalValue = resList.get(0).getResource_amount().toString();
                Integer measureId = CommonUtil.long2Int(resList.get(0).getResource_id());

                imsNotify.addParam(AlarmCodeDefine.PARAM_FREE_RESOURCE_REMAIN, remainValue, measureId);
                imsNotify.addParam(AlarmCodeDefine.PARAM_FREE_RESOURCE_TOTAL, totalValue, measureId);
            }
        }
        return imsNotify;
    }

    /**
     * 根据通知的类型，查询告警id luojb 2012-2-15
     * 
     * @param notifyType
     * @param notifyFreeRes
     * @param isIR
     * @return
     */
    public Integer getAlarmId(Integer notifyType, Integer offerId)
    {
        return getAlarmId(notifyType,offerId,null);
    }
    
    public Integer getAlarmId(Integer notifyType, Integer offerId,Integer channelId)
    {
        if (notifyType == null || offerId == null)
            return null;
        List<CacheCondition> conditions = new ArrayList<CacheCondition>();
        conditions.add(new CacheCondition(PmProductOfferNotify.Field.productOfferingId, offerId));
        conditions.add(new CacheCondition(PmProductOfferNotify.Field.notifyType,notifyType));
        // 2012-07-23 luojb #50629 产品通知配置表增加channel_id
        CacheCondition channelIdCon = new CacheCondition(PmProductOfferNotify.Field.channelId, -1);//通配
        if(channelId != null)
            channelIdCon = new CacheCondition(PmProductOfferNotify.Field.channelId, channelId);
        conditions.add(channelIdCon);
        PmProductOfferNotify offerNotify = DBConfigInitBean.getSingleCached(PmProductOfferNotify.class, 
                conditions.toArray(new CacheCondition[conditions.size()]));
        // 如果用channelId匹配不到，使用通配
        if(offerNotify == null && channelId != null){
            imsLogger.debug("----- can't find alarmId for channelId["+channelId+"]. try channelId[-1] ");
            conditions.remove(channelIdCon);
            conditions.add(new CacheCondition(PmProductOfferNotify.Field.channelId, -1));//通配
            offerNotify = DBConfigInitBean.getSingleCached(PmProductOfferNotify.class, 
                    conditions.toArray(new CacheCondition[conditions.size()]));
        }
        Integer alarmId = offerNotify != null ? offerNotify.getNotificationId():null;
        
        imsLogger.debug("----- get alarmId["+alarmId+"] for notifyType["+notifyType+"],offerId["+offerId+"]");
        return alarmId;
    }

}
