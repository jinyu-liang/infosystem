package com.ailk.ims.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jef.database.DataObject;
import jef.database.Field;
import jef.tools.reflect.BeanUtils;

import com.ailk.easyframe.sdl.MNotificationApp.INotificationAppInt;
import com.ailk.easyframe.sdl.MNotificationDef.SNotificationIntf;
import com.ailk.easyframe.sdl.MNotificationDef.SParamIntf;
import com.ailk.easyframe.sdl.MNotificationDef.SPhoneIntf;
import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.IMSNotification;
import com.ailk.ims.common.InitBean;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.query.CacheQuery;
import com.ailk.ims.define.AlarmCodeDefine;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.Cust3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.openbilling.persistence.acct.entity.BiBusiSpecExt;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.imsintf.entity.LongList;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrder;
import com.ailk.openbilling.persistence.imsintf.entity.SProductOrderList;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description: 信息管理工具类，跟信息管理具体业务相关的一些公用方法可以定义在这里
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2 2011-07-28 wuyujie : 增加getDataObjectFieldValue，setDataObjectFieldValue方法<br>
 *       2011-08-01 wuyujie : 增加getCurrentDate() <br>
 *       2011-08-12 wuyujie : 增加instanceDataObject（）<br>
 *       2011-8-16 wangjt ： 增加mergeList()方法<br>
 *       2011-09-01 wuyujie : 增加queryBusiSpecId() 2012-02-09 wuyujie : 判断so_mode不应该用isValid 判断空指针ljc 2012-03-20
 *       2012-05-19 wuyujie ：createCBSErrorMsg从BaseMethodInvoker中移过来
 *       2012-05-24 yangjh ：isNeedCreate3hBean 去掉getParent() 改为从每个接口各自的配置去读取
 * @Date 2012-06-18 wukl 便于开发测试，将版本控制放到虚拟机参数中，若没配置则取数据库
 * @date 2012-07-07 luojb 获取告警id参考channelId #50629 
 * @Date 2012-07-16 yangjh 新增prodOrderFail2Notify 方法 用于产品订购失败发告警 
 * @Date 2012-07-27 yangjh : prodOrderFail2Notify增加exception的区分
 * @Date 2012-09-05 xieqr : 3户数据不在路由表中异常信息特殊处理 
 */
public class IMSUtil
{
    private static Date default_expireDate;
    private static ImsLogger imsLogger=new ImsLogger(IMSUtil.class);

    public static void init()
    {
        // String[] classes = IOUtils.listClassNameInPackage(IMSUtil.class, new String[] { "com.ailk.openbilling.persistence" },
        // true, false);
        //
        // URL url = IMSUtil.class.getResource("/com/ailk/openbilling/persistence");
        // IMSUtil.class.getClassLoader().loadClass(url);
    }

    public static LongList createLongList(long[] priarr)
    {
        LongList list = new LongList();

        list.setLongList_Item(CommonUtil.convertArray(priarr));
        return list;
    }

    /**
     * @Description: 根据字符串日期自动创建Date对象，如果日期字符串为空则根据后一个参数返回默认时间 适用于valid_date和create_date
     * @param strDate,需要格式化得日期
     * @param needDefault，如果日期为空，则这里返回的默认日期
     * @return
     * @throws Exception
     */
    public static Date formatDate(String strDate) throws IMSException
    {
        return formatDate(strDate, null);
    }

    /**
     * 改为最大兼容格式 luojb 2012-3-30
     */
    public static Date formatDate(String strDate, Date defaultDate) throws IMSException
    {
        if (CommonUtil.isEmpty(strDate))
        {
            return defaultDate;
        }
        return DateUtil.getFormattedDate(strDate);
    }

    /**
     * @Description: 把Date类型转成当前项目需要的字符串格式
     * @author : wuyj
     * @date : 2011-10-28
     */
    public static String formatDate(Date date) throws IMSException
    {
        if (date == null)
            return null;
        return DateUtil.getFormatDate(date, DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
    }

    /**
     * @Description: 功能与formatDate()类似，区别在于仅适用于expire_date，如果字符串为空，则自动返回一个配置的失效时间
     */

    public static Date formatExpireDate(String strDate) throws IMSException
    {
        if (CommonUtil.isEmpty(strDate))
        {
            return getDefaultExpireDate();
        }
        else
        {
            return formatDate(strDate, null);
        }
    }

    /**
     * @Description: 取默认的失效日期
     * @return
     * @throws Exception
     */
    public static Date getDefaultExpireDate() throws IMSException
    {
        if (default_expireDate == null)
            default_expireDate = DateUtil.getFormatDate(SysUtil.getString(SysCodeDefine.busi.BUSI_DEFAULT_EXPIRE_DATE),
                    DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS);
        return default_expireDate;
    }

    /**
     * @Description:根据错误信息创建IMS异常对象，此时errorcode=259999
     * @author : wuyj
     * @date : 2011-9-23
     * @param errorMsg
     * @return
     */
    public static IMSException throwBusiException(String errorMsg)
    {
        throw new IMSException(errorMsg);
    }

    /**
     * @Description:根据异常对象创建IMS异常对象，如果本身已经是异常对象了则直接返回
     * @author : wuyj
     * @date : 2011-9-23
     * @param errorMsg
     * @return
     */
    public static BaseException throwBusiException(Exception e)
    {
        if (e instanceof BaseException)
        {
            throw (BaseException) e;
        }
        else
        {
            throw new IMSException(e);
        }
    }

    /**
     * @Description: 根据busiCode和占位符数组创建IMSException
     */
    public static IMSException throwBusiException(long errorCode, Object... params)
    {
        if (!CommonUtil.isEmpty(params))
        {
            for (int i = 0; i < params.length; i++)
            {
                Object param = params[i];
                if (param != null && !(param instanceof String))
                    params[i] = param.toString();// 都转成string，否则会被格式化
            }
        }
        throw new IMSException(errorCode, params);
    }
    
    /**
     * @Description: 只给load3hbean系列方法用的异常
     */
    public static IMS3hNotFoundException throw3hBeanNotFoundException(long errorCode, Object... params)
    {
        if (!CommonUtil.isEmpty(params))
        {
            for (int i = 0; i < params.length; i++)
            {
                Object param = params[i];
                if (param != null && !(param instanceof String))
                    params[i] = param.toString();// 都转成string，否则会被格式化
            }
        }
        throw new IMS3hNotFoundException(errorCode, params);
    }

    /**
     * @Description：根据账期类型和账期数量，推算出偏移后的日期 只提供给账期有关计算
     * @param date
     * @param cycleType ,账期类型,如day,week,month,year，对应EnumCodeDefine.PROD_CYCLETYPE_* 枚举值
     * @param cycleUnit
     * @return
     */
    public static Date offsetDate(Date date, int cycleType, int cycleUnit)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // int offsetType = -1;
        int offset = cycleUnit;

        if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_DAY)// 自然天
        {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + offset);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_MONTH)// 自然月 3.1 00:00:00
        {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offset);
            return DateUtil.dayBegin(calendar.getTime());

        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_WEEK)// 自然周
        {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + offset);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_YEAR)// 自然年
        {
            // offsetType = Calendar.YEAR;
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + offset);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_MONTH)
        {// 动态月 3.5 00:00:00
         // calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offset);
            calendar.add(Calendar.MONTH, offset);
            return DateUtil.dayBegin(calendar.getTime());
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_HOUR)// 按小时偏移
        {
            // offsetType = Calendar.HOUR_OF_DAY;
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + offset);
            return calendar.getTime();
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_MONTH_OFFSET)
        {// 按自然月偏移 3.5 08:08:08
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offset);
            return calendar.getTime();
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_DAY)
        {// 动态日
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + offset);
            return calendar.getTime();
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_WEEK)
        {// 动态周
            calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + offset);
            return calendar.getTime();
        }
        else if (cycleType == EnumCodeDefine.PROD_CYCLETYPE_RUN_YEAR)
        {// 动态年
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + offset);
            return calendar.getTime();
        }

        return date;
    }

    /**
     * 根据ims系统中的Date，返回mdb系统需要的时间 （以秒为单位，如果超过int的最大值，则返回int的最大值：2147483647）
     * @Date 2012-12-27 wukl  如果传入的时间早于1970-01-01 08:00:00（int值：0），则返回这个时间点；因为MDB针对早于该时间的会填空值
     */
    public static int getMdbDate(Date imsDate)
    {
        if (imsDate == null)
        {
            return 0;
        }
        long mdbDate = imsDate.getTime() / 1000;
        mdbDate = mdbDate > 0 ? mdbDate : 0;
        return (mdbDate > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) mdbDate);
    }

    /**
     * 根据DataObject中的枚举，产生一个DataObject的实例
     */
    public static DataObject getInstanceByField(Field field)
    {
        try
        {
            return (DataObject) getClassByField(field).newInstance();
        }
        catch (Exception e)
        {
            throw new IMSException("create instance by field fail!!!");
        }
    }

    /**
     * 根据DataObject中的枚举，返回所属的Class
     */
    public static Class getClassByField(Field field)
    {
        String className = field.getClass().getName().replaceFirst("\\$.+", "");
        try
        {
            return Class.forName(className);
        }
        catch (Exception e)
        {
            throw new IMSException("get class by field fail!!!");
        }
    }

    public static String getMessageSavePath(IMSContext context)
    {
        SOperInfo oper = context.getOper();
        Long doneCode = context.getDoneCode();
        if (doneCode == null)
            doneCode = System.currentTimeMillis();

        String path_pattern = SysUtil.getString(SysCodeDefine.busi.BUSI_MESSAGE_FILEPATH_PATTERN);

        if (CommonUtil.isEmpty(path_pattern))
        {
            // 未配置，直接取当前目录,文件名则用：${donecode}.xml
            return new StringBuffer(System.getProperty("user.dir")).append("\\").append(doneCode).append(".xml").toString();
        }

        // 解析占位符，可以取SOperInfo结构中的任意字段作为目录或者文件名称，用${field_name}方式配置,比如：d:/temp/${busi_code}/
        // 占位符还有系统内置的，比如:
        // ${year}：当前年份，yyyy格式 ; ${month}当前月份,yy格式 ; ${day}当前日期，dd格式

        path_pattern = path_pattern.replace("\\", "/");
        try
        {
            Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
            Matcher matcher = pattern.matcher(path_pattern);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(context.getRequestDate());

            while (matcher.find())
            {
                String fieldName = matcher.group(1);
                Object value = null;
                if ("year".equals(fieldName))
                {
                    value = calendar.get(Calendar.YEAR);
                }
                else if ("month".equals(fieldName))
                {
                    value = calendar.get(Calendar.MONTH) + 1;
                }
                else if ("day".equals(fieldName))
                {
                    value = calendar.get(Calendar.DAY_OF_MONTH);
                }
                else
                {
                    value = ClassUtil.getFieldValue(oper, fieldName);
                }
                if (value == null)
                    value = "";
                path_pattern = path_pattern.replaceAll("\\$\\{" + fieldName + "\\}", value.toString());
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }

        StringBuffer result = new StringBuffer(path_pattern);
        if (path_pattern.endsWith("/"))
        {
            // 如果以/或者\结尾，则说明配置的是目录，各个文件以${donecode}.xml命名
            result.append(doneCode).append(".xml");
        }
        else
        {
            // 配置的不是目录，说明是前缀，各个文件以${前缀}.${donecode}.xml命名
            result.append(".").append(doneCode).append(".xml").toString();
        }
        return result.toString();
    }

    /**
     * @Description: 获取当前时间，采用的是虚拟时间
     * @return
     */
    /*
     * public static Date getCurrentDate() { return new VirtualDate(); }
     */

    /**
     * @Description: 获取当前时间的字符串形式，用yyyy-MM-dd HH:mm:ss
     * @return
     */
    /*
     * public static String getCurrentStringDate() { Date date = getCurrentDate(); return DateUtil.formatDate(date,
     * DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS); }
     */

    /**
     * @Description: 构建一份相同的DataObject
     * @param orig
     * @return
     * @throws IMSException
     */
    public static DataObject copyDataObject(DataObject orig) throws IMSException
    {
      //使用jef的工具类代替反射 lijc3
        try
        {
            DataObject newObj = orig.getClass().newInstance();
            BeanUtils.copyProperties(orig,newObj);
            return newObj;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * 合并多个list
     */
    public static <T extends Object> List<T> mergeList(List<? extends Object>... listArr)
    {
        if (CommonUtil.isEmpty(listArr))
        {
            return null;
        }
        List<Object> allList = new ArrayList<Object>();
        for (int i = 0; i < listArr.length; i++)
        {
            if (!CommonUtil.isEmpty(listArr[i]))
            {
                allList.addAll(listArr[i]);
            }
        }
        return (CommonUtil.isEmpty(allList) ? null : (List<T>) allList);
    }

    /**
     * @Description: 获取主付费模式， 0-预付费，返回0 ; 1-后付费，返回1 10 --- prepaid-hybrid，预付费为主的hybrid，则返回0, 11
     *               ---postpaid-hybrid，后付费为主的hybrid，则返回1
     * @return wuyjgetMainBillingTypegetMainBillingTypegetMainBillingTypegetMainBillingTypeCM_MDB_ERROR
     */
    public static Integer getMainBillingType(Integer billingType)
    {
        if (EnumCodeDefine.USER_PAYMODE_PREPAID == billingType || EnumCodeDefine.USER_PAYMODE_HYBRID_PREPAID == billingType)
            return EnumCodeDefine.USER_PAYMODE_PREPAID;
        if (EnumCodeDefine.USER_PAYMODE_POSTPAID == billingType || EnumCodeDefine.USER_PAYMODE_HYBRID_POSTPAID == billingType)
            return EnumCodeDefine.USER_PAYMODE_POSTPAID;
        return null;
    }

    /**
     * @Description:在产生上下文产生之前使用这个方法设置参数，后面流转过程中可以获取
     * @param key
     * @param value wuyj
     */
    public static void setRequestContextParam(String key, Object value)
    {
        if (ContextHolder.getRequestContext() == null)
        {
            throw IMSUtil.throwBusiException("ContextHolder is null.");
        }
        ContextHolder.getRequestContext().put(key, value);
    }

    /**
     * @Description:在产生上下文产生之前获取参数
     * @param key
     * @param value wuyj
     */
    public static Object getRequestContextParam(String key)
    {
        if (ContextHolder.getRequestContext() == null)
            return null;
        return ContextHolder.getRequestContext().get(key);
    }

    /**
     * @Description: 判断是否内部busicode,通过sys.properties中的inner_busi_code配置项来决定
     * @param busiCode
     * @return wuyj
     */
    public static boolean isInnerBusiCode(Integer busiCode)
    {
        if (busiCode == null)
            return false;
        if (busiCode >= 9000)
            return true;
        else
            return false;
        /*
         * int[] innerBusiCodeArr = SysUtil.getInnerIntArray(SysCodeDefine.INNER_BUSI_CODE); return
         * CommonUtil.isIn(innerBusiCodeArr, busiCode.intValue());
         */
    }

    /**
     * @Description: 告警
     * @param notifications
     * @return wuyj
     */
    public static Integer doNotification(List<IMSNotification> notifications, IMSContext context)
    {
        INotificationAppInt appInt =  new INotificationAppInt();
        return doNotification(notifications,context,appInt);
    }

    /**
     * @Description: 告警
     * @param notifications
     * @return yangjh
     */
    public static Integer doNotification(List<IMSNotification> notifications, IMSContext context,INotificationAppInt appInt){
        if(CommonUtil.isEmpty(notifications))
            return null;
        imsLogger.dump("****** notificaton list", notifications);

        CsdlArrayList<SNotificationIntf> notifyList = new CsdlArrayList<SNotificationIntf>(SNotificationIntf.class);
        for (IMSNotification notif : notifications)
        {
            SNotificationIntf sNotif = new SNotificationIntf();
            
            if(notif.getAlarmId() == null)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "alarmId");
            }
            sNotif.set_notificationId(notif.getAlarmId());
            // sNotif.set_notificationFlag(0);
            sNotif.set_soNbr(context.getSoNbr());
            sNotif.set_soDate(ConvertUtil.javaDate2SdlLong(context.getRequestDate()));

            Long userId = notif.getUserId();
            Long acctId = notif.getAcctId();
            Long custId = notif.getCustId();

            // 2012-02-09 wukl 若已经传入则不再去查数据
            if (userId == null || acctId == null || custId == null)
            {
                IMS3hBean hbean = context.get3hTree().load3hBean(custId, acctId, userId, null);
                // ljc 修改空指针
                if (hbean.isUser3hBean())
                {
                    userId = ((User3hBean) hbean).getUserId();
                    acctId = ((User3hBean) hbean).getAcctId();
                    custId = ((User3hBean) hbean).getCustId();
                }
                else if (hbean.isAcct3hBean())
                {
                    userId = null;
                    acctId = ((Acct3hBean) hbean).getAcctId();
                    custId = ((Acct3hBean) hbean).getCustId();
                }
                else
                {
                    acctId = null;
                    acctId = null;
                    custId = ((Cust3hBean) hbean).getCustId();
                }
            }

            // 判断空指针ljc 2012-03-20
            if (userId != null)
                sNotif.set_servId(userId);
            if (acctId != null)
                sNotif.set_acctId(acctId);
            if (custId != null)
                sNotif.set_custId(custId);

            if (notif.getRegionCode() != null)
                sNotif.set_regionCode(notif.getRegionCode());

            // 添加参数
            List<IMSNotification.IMSNotifyParam> params = notif.getParams();

            // 添加公用参数，比如cust_title,first_name等
            PartyComponent partyCmp = context.getComponent(PartyComponent.class);
            CmIndividualName individualName = partyCmp.queryInvidualNameByCustId(custId);

            if (individualName != null)
            {
                if (!notif.containsParam(AlarmCodeDefine.PARAM_CUST_TITLE))
                    notif.addParam(AlarmCodeDefine.PARAM_CUST_TITLE, String.valueOf(individualName.getFormOfAddress()));
                if (!notif.containsParam(AlarmCodeDefine.PARAM_FIRST_NAME))
                    notif.addParam(AlarmCodeDefine.PARAM_FIRST_NAME, individualName.getPreferredGivenName());
                if (!notif.containsParam(AlarmCodeDefine.PARAM_FAMILY_NAME))
                    notif.addParam(AlarmCodeDefine.PARAM_FAMILY_NAME, individualName.getFamilyNames());
            }

            CsdlArrayList<SParamIntf> sParamList = new CsdlArrayList<SParamIntf>(SParamIntf.class);
            for (IMSNotification.IMSNotifyParam param : params)
            {
                int key = param.getId();
                String value = param.getValue();
                SParamIntf sParam = new SParamIntf();
                sParam.set_paramId(String.valueOf(key));
                sParam.set_paramValue(value);
                if (param.getMeasureId() != null)
                    sParam.set_measureId(param.getMeasureId());
                sParamList.add(sParam);
            }
            sNotif.set_listParam(sParamList);

            // 添加手机号码
            CsdlArrayList<SPhoneIntf> phoneList = new CsdlArrayList<SPhoneIntf>(SPhoneIntf.class);
            if (!CommonUtil.isEmpty(notif.getPhones()))
            {
                for (String phone : notif.getPhones())
                {
                    SPhoneIntf sPhone = new SPhoneIntf();
                    sPhone.set_phoneId(phone);
                    phoneList.add(sPhone);
                }
            }
            sNotif.set_listPhone(phoneList);

            notifyList.add(sNotif);
        }

        try
        {
            CBSErrorMsg errorMsg = new CBSErrorMsg();
            //appInt.denotif_upload(notifyList, errorMsg);
            imsLogger.info("****** " + context.getOper().getBusi_code() + " notification result : error_code="
                            + errorMsg.get_errorCode() + ",error_msg=" + errorMsg.get_errorMsg() + ",hint=" + errorMsg.get_hint());
            return errorMsg.get_errorCode();
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
        
    }

    /**
     * @Description: 产品订购失败告警
     * @param SProductOrderList
     * @param Exception
     * @param context
     * @return yangjh
     */
    public static void prodOrderFail2Notify(SProductOrderList prodList,Exception e,IMSContext context, Integer alarmId)
    {
        //2012-07-17 yangjh prodOrderFail2Notify方法修改 传入alarmId
        String errorMsg = null;
        //@Date 2012-07-27 yangjh : prodOrderFail2Notify增加exception的区分
        if(e instanceof BaseException)
        {
            errorMsg = ((BaseException) e).getFormattedMessage();
        }else{
            errorMsg = "other message";
        }
        List<IMSNotification> notifyList = new ArrayList<IMSNotification>();
        for (SProductOrder prod : prodList.getItem())
        {
             if (prod == null)
                 continue;
             IMSNotification imsNotify = new IMSNotification();
              //增加告警对象的传入
             //2012-07-25 yangjh 重新load 避免后面发告警找不到cust/user/acct ID
             if(CommonUtil.isValid(prod.getUser_id()) || CommonUtil.isValid(prod.getPhone_id())){
                 User3hBean bean = context.get3hTree().loadUser3hBean(prod.getUser_id(), prod.getPhone_id());
                 imsNotify.setUserId(bean.getUserId());
                 imsNotify.setAcctId(bean.getAcctId());
                 imsNotify.setCustId(bean.getCustId());
             }else if(CommonUtil.isValid(prod.getAcct_id())){
                 Acct3hBean bean = context.get3hTree().loadAcct3hBean(prod.getAcct_id());
                 imsNotify.setAcctId(bean.getAcctId());
                 imsNotify.setCustId(bean.getCustId());
             }else if(CommonUtil.isValid(prod.getCust_id())){
                 Cust3hBean bean = context.get3hTree().loadCust3hBean(prod.getCust_id());
                 imsNotify.setCustId(bean.getCustId());
             }
             PmProductOffering offer = context.getComponent(CacheQuery.class).queryPmProductOfferingByOfferId(
                   prod.getOffer_id().intValue());
             String offerName = offer.getName();
             //alarmID 获取的busi_spec_id为业务产品订购的spec_id
             imsNotify.setAlarmId(alarmId);
             imsNotify.addDateParam(AlarmCodeDefine.PARAM_PROD_FAIL_DATE,context.getRequestDate());
             imsNotify.addParam(AlarmCodeDefine.PARAM_PROD_NAME, offerName);
             imsNotify.addParam(AlarmCodeDefine.PARAM_PROD_FAIL_REASON, errorMsg);
             notifyList.add(imsNotify);
         }
        imsLogger.dump("***notification infomation***", notifyList);
        if(CommonUtil.isNotEmpty(notifyList)){
            INotificationAppInt appInt = new INotificationAppInt();
            IMSUtil.doNotification(notifyList, context, appInt);
            appInt.processThreadCachedUploads();
        }
    }
    
    /**
     * 根据枚举值判断是否为个人客户
     * 
     * @author yanchuan 2011-10-26
     */
    public static boolean isPersonCust(Integer type)
    {
        if (type == null)
            return false;
        int[] custTypes = SysUtil.getIntArray(SysCodeDefine.busi.PERSON_CUST_NAME);
        for (int custType : custTypes)
        {
            // 个人客户
            if (type == custType)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是客户的枚举类型，为空则表示个人客户
     * 
     * @author yanchuan 2011-11-5 <BR>
     *         wangjt： 2012-3-26 14:19:40 优化方法
     */
    public static boolean isCustType(Short cust_type)
    {
        return (cust_type == null || cust_type == EnumCodeDefine.CUSTOMER_CLASS_BUSINESS
                || cust_type == EnumCodeDefine.CUSTOMER_CLASS_EXCLUSIVE || cust_type == EnumCodeDefine.CUSTOMER_CLASS_GOVERNMENT
                || cust_type == EnumCodeDefine.CUSTOMER_CLASS_INHOUSE || cust_type == EnumCodeDefine.CUSTOMER_CLASS_RESIDENTIAL);
    }

    /**
     * 判断是否为内部系统
     */
    public static boolean isInnerSystem(IMSContext context)
    {
        SOperInfo sOperInfo = context.getOper();
        if (sOperInfo == null)
            return true;
        Short soMode = sOperInfo.getSo_mode();
        // 2012-02-09 wuyujie : 判断so_mode不应该用isValid
        if (soMode == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "so_mode");
        }
        // 二者有一个就代表true
        return context.getMethodConfig().getParent().getBooleanAttribute(BusiUtil.ATTR_ISINNER)
                || CommonUtil.isIn(SysUtil.getIntArray(SysCodeDefine.busi.INNER_SO_MODE_STR), soMode.intValue());
        // return CommonUtil.isIn(SysUtil.inner.getIntArray(SysCodeDefine.inner.INNER_SO_MODE_STR), soMode.intValue());
    }
    
    public static boolean isNeedCreate3hBean(IMSContext context)
    {
        //2012-05-24 yangjh 去掉getParent  不从service总节点去判断
        Boolean is3h = context.getMethodConfig().getBooleanAttribute(BusiUtil.ATTR_IS3H);
        return is3h == null || is3h;
    }

    /**
     * 根据业务编号和渠道编号获取告警编号
     * luojb 2012-7-24
     * @param busiSpecId
     * @param channelId
     * @return
     */
    public static Integer getNotificationIdByBusiSpecId(Integer busiSpecId, Short channelId)
    {
        return getNotifyId(busiSpecId,channelId,null,null);
    }
    
    /**
     * 根据业务编号和渠道编号获取告警编号
     * luojb 2012-7-24
     * @param busiSpecId
     * @param channelId
     * @return
     */
    public static Integer getNotificationIdByBusiSpecId(Integer busiSpecId, Short channelId,Integer busiPlanId)
    {
        return getNotifyId(busiSpecId,channelId,null,busiPlanId);
    }
    
    /**
     * 根据业务编号获取业务失败的告警编号
     * luojb 2012-7-24
     * @param busiSpecId
     * @return
     */
    public static Integer getNotificationIdByBusiSpecIdAndFailedResult(Integer busiSpecId)
    {
        return getNotifyId(busiSpecId,null,EnumCodeDefine.BUSI_RESULT_FAILED,null);
    } 
    

    /**
     * 获取告警id
     * luojb 2012-7-24
     * @param busiSpecId
     * @param channelId
     * @param busiResult
     * @return
     */
    private static Integer getNotifyId(Integer busiSpecId, Short channelId, Short busiResult,Integer busiPlanId)
    {
        if (busiSpecId == null)
        {
            return null;
        }
        List<CacheCondition> conditions = new ArrayList<CacheCondition>();
        conditions.add(new CacheCondition(BiBusiSpecExt.Field.busiSpecId, busiSpecId));
        if (busiResult == null)
            busiResult = EnumCodeDefine.BUSI_RESULT_SUCCESS; // 默认是业务成功
        conditions.add(new CacheCondition(BiBusiSpecExt.Field.busiResult, busiResult));

        List<BiBusiSpecExt> dmSpecExts = DBConfigInitBean.getCached(BiBusiSpecExt.class,
                conditions.toArray(new CacheCondition[conditions.size()]));
        if(CommonUtil.isNotEmpty(dmSpecExts))
        {
            for (BiBusiSpecExt biBusiSpecExt : dmSpecExts)
            {
                if(busiPlanId != null && biBusiSpecExt.getBusiPlanId() != null && biBusiSpecExt.getBusiPlanId() != -1 && biBusiSpecExt.getBusiPlanId() != busiPlanId.intValue())
                    continue;
                if(channelId != null && biBusiSpecExt.getChannelId() != null && biBusiSpecExt.getChannelId() != -1 && biBusiSpecExt.getChannelId() != channelId.intValue())
                    continue;
                return biBusiSpecExt.getNotificationId();
            }
        }
        return null;
    
    }
    
    /**
     * @Description: 根据传入的条件和数据列表，找出匹配的数据
     * @author : wuyj
     * @date : 2012-1-9
     * @param data
     * @param conds
     * @return
     * @throws Exception
     */
    public static <T extends DataObject> List<T> matchDataObject(List<T> data, CacheCondition... conds) throws Exception
    {
        if (CommonUtil.isEmpty(data))
            return null;

        if (null == conds || conds.length == 0)
            return data;

        List<T> result = new ArrayList<T>();
        for (T dm : data)
        {
            boolean valid = true;
            for (CacheCondition param : conds)
            {
                String fieldName = param.getField().name();
                Object cacheValue = ClassUtil.getFieldValue(dm, fieldName);

                if (!param.isMatch(cacheValue))
                {
                    valid = false;
                    break;
                }
            }
            if (valid)
                result.add(dm);
        }
        return result;
    }

    /**
     * 获取系统使用版本
     * 
     * @Author lijc3
     */
    public static String getProject()
    {
        // @Date 2012-06-18 wukl 便于开发测试，将版本控制放到虚拟机参数中，若没配置则取数据库
        String project = getVMProject();
        if(project == null)
            project = SysUtil.getString(SysCodeDefine.busi.PROJECT);
        return project;
    }
    
    public static String getVMProject()
    {
        String vmParam = System.getProperty (SysCodeDefine.busi.PROJECT) ;
        if(CommonUtil.isNotEmpty(vmParam))
        {
            return vmParam.toUpperCase().trim();
        }
        return null;
    }


    /**
     * 查看合并后的配置文件 luojb 2012-3-5
     */
    public static String viewConfigFile(int type)
    {
        if (type == 1)// busi.xml
        {
            return BusiUtil.getBusiXML();
        }
        else if (type == 2)// boot.xml
        {
            return InitBean.getRootXML();
        }

        return null;
    }

    /**
     * 是否记录业务记录 luojb 2012-3-5
     * 
     * @param context
     * @return
     */
    public static boolean isBusiRecord(IMSContext context)
    {
        // DCC首次激活不记录
        // BaseNode methodNode = context.getMethodConfig();
        // Boolean checkOper = methodNode.getBooleanAttribute("checkOper");// DCC首次激活需要跳过checkOper
        // if (checkOper != null && !checkOper)
        // return false;
        // 内部查询接口和sdl接口不记录
        if (!isInnerSystem(context))// 外部接口要记录
            return true;
        else if (isSDL(context))// sdl接口不记录
            return false;
        else if (context.isNotQueryBusi())// inner ws服务非查询接口，记录
            return true;
        else
            // inner ws服务查询接口，不记录
            return false;
    }

    /**
     * 是否是sdl接口 luojb 2012-3-9
     * 
     * @param context
     * @return
     */
    public static boolean isSDL(IMSContext context)
    {
        Boolean isSdl = context.getMethodConfig().getParent().getBooleanAttribute("isSdl");
        return isSdl == null ? false : isSdl;
    }
    
    public static com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg createCBSErrorMsg(IMSContext context, Throwable t)
    {
        com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg cbsErrorMsg = new com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg();
        try
        {
            // 创建应答错误信息
            if (context != null)
            {
                if (context.getOper() != null)
                    cbsErrorMsg.setOrig_so_nbr(context.getOper().getSo_nbr());
                if (context.getSoNbr() != 0)
                {
                    cbsErrorMsg.setSo_nbr(context.getSoNbr());
                }
            }
            if (t == null)
            {
                cbsErrorMsg.setResult_code((long) 0);// 默认0是表示成功
            }
            else if (t instanceof BaseException)
            {
                cbsErrorMsg.setResult_code((long) ((BaseException) t).getCodeAsLong());
                cbsErrorMsg.setError_msg(((BaseException) t).getFormattedMessage());

            }
            else
            {
                //2012-07-13 wuyujie 需求:修改空指针的提示方式
                if(t instanceof NullPointerException){
                    cbsErrorMsg.setError_msg("unknown exception");
                }else{
                    cbsErrorMsg.setError_msg(t.getClass().getName() + " : " + t.getMessage());
                }
                cbsErrorMsg.setResult_code(ErrorCodeDefine.UNKNOWN_ERROR);
            }

            cbsErrorMsg.setFinish_date(IMSUtil.formatDate(context.getRequestDate()));
            context.setCbsErrorMsg(cbsErrorMsg);
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);
        }
        return cbsErrorMsg;
    }
    /**
     * 
     * @param obj
     * @return
     * //@Date 2012-09-18 yugb :[57408]API中字段清空方案
     */
    public static boolean  isClean(Object obj){
        if (obj instanceof Integer) {
            int value = ((Integer) obj).intValue();
            if(value==EnumCodeDefine.DEFAULT_NULL_VALUE){
                return true;
            }
        }
        else if (obj instanceof Long) {
            long value = ((Long) obj).longValue();
            if(value==EnumCodeDefine.DEFAULT_NULL_VALUE){
                return true;
            }
        }
        else if (obj instanceof Short) {
            short value = ((Short) obj).shortValue();
            if(value==EnumCodeDefine.DEFAULT_NULL_VALUE){
                return true;
            }
        }
        else if (obj instanceof String) {
            String str = (String) obj;
            if("".equals(str))
                return true;
            }
        return false;
    }
    
    /**
     * 删除ContextHolder中的路由参数值
     * @Author: wukl 
     * @Date: 2012-10-30
     */
    public static void removeRouterParam()
    {
        ContextHolder.getRequestContext().remove(ConstantDefine.ROUTER_KEY_CUST);
        ContextHolder.getRequestContext().remove(ConstantDefine.ROUTER_KEY_ACCT);
        ContextHolder.getRequestContext().remove(ConstantDefine.ROUTER_KEY_USER);
        ContextHolder.getRequestContext().remove(ConstantDefine.ROUTER_KEY_IDEN);
    }
    
    /**
     * 删除ContextHolder中的SO_DATE参数值
     * @Author: wukl 
     * @Date: 2012-10-30
     */
    public static void removeSoDateRouterParam()
    {
        ContextHolder.getRequestContext().remove(ConstantDefine.ROUTER_KEY_SO_DATE);
    }
    
    /**
     * 往ContextHolder中设置SO_DATE路由参数值
     * @Author: wukl 
     * @Date: 2012-10-30
     * @param acctId
     */
    public static void setSoDateRouterParam(Date soDate)
    {
        ContextHolder.getRequestContext().put(ConstantDefine.ROUTER_KEY_SO_DATE, soDate);
    }
    /**
     * 往ContextHolder中设置ACCT_ID路由参数值
     * @Author: wukl 
     * @Date: 2012-10-30
     * @param acctId
     */
    public static void setAcctRouterParam(Long acctId)
    {
        ContextHolder.getRequestContext().put(ConstantDefine.ROUTER_KEY_ACCT, acctId);
    }
    
    /**
     * 往ContextHolder中设置RESOURCE_ID路由参数值
     * @Author: wukl 
     * @Date: 2012-10-30
     * @param resourceId
     */
    public static void setUserRouterParam(Long resourceId)
    {
        ContextHolder.getRequestContext().put(ConstantDefine.ROUTER_KEY_USER, resourceId);
    }
    
    /**
     * 往ContextHolder中设置ACCT_ID路由参数值
     * @Author: wukl 
     * @Date: 2012-10-30
     * @param identity
     */
    public static void setIdenRouterParam(String identity)
    {
        ContextHolder.getRequestContext().put(ConstantDefine.ROUTER_KEY_IDEN, identity);
    }
    
    /**
     * 往ContextHolder中设置ACCT_ID路由参数值
     * @Author: wukl 
     * @Date: 2012-10-30
     * @param custId
     */
    public static void setCustRouterParam(Long custId)
    {
        ContextHolder.getRequestContext().put(ConstantDefine.ROUTER_KEY_CUST, custId);
    }
    
    public static boolean isMdbCloud(){
    	int cloudFlag = SysUtil.getInt("MDB_CLOUD_FLAG", 0);
		return cloudFlag == 0 ? false : true;
    }

}
