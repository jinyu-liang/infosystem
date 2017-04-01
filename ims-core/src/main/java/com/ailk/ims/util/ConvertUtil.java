package com.ailk.ims.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jef.database.DataObject;
import jef.tools.reflect.BeanUtils;

import com.ailk.easyframe.common.log.Logger;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.openbilling.persistence.cust.entity.CoFnCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.imsintf.entity.SOperInfo;

/**
 * @Description:转换工具类。数据结构的转换或以定义在这里。主要是处理java对象与sdl对象之间的转换，不适用CRM传值定义的java对象
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wangyh3 <br>
 *         2011-8-30 wangyh3：新增 OneTimeFee2SOneTimeFee 方法 2012-02-10 yangjh:新增java的告警对象转换成SDL告警对象用于重发失败的告警信息 2012-3-27 xieqr:
 *         增加setFieldValue getFieldValue 方法 2012-3-27 wukl :增加了 sdlLong2JavaDate方法 2012-3-29
 *         xieqr:修改copyIMSObj2AcctObj，copyAcctObj2IMSObj方法，增加 string 2 date ；date 2string转换 2012-04-28 wangyh3: 增加亲情号码相关的转换方法
 *         coFnCharValues2CoProdCharValues, coProdCharValues2CoFnCharValues等
 * @Date 2011-8-30
 * @Date 2012-5-19 新增copyJava2His
 * @Date 2012-07-20 yugb :[51610]set negative balance增加source system字段
 */
public class ConvertUtil
{
    // private static ImsLogger logger = new ImsLogger(ConvertUtil.class);


    /*
     * public static List<SOneTimeFee> oneTimeFee2SOneTimeFee(CmCustomer cust, CaAccount acct, CmResource user, OneTimeFee
     * oneTimeFee, short eventType, IMSContext context) throws IMSException { List<SOneTimeFee> returnList = new
     * ArrayList<SOneTimeFee>(); Short objectType = -1; if (user != null) { objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;//
     * 使用类型为用户 } else if (acct != null) { Integer accountClass = acct.getAccountClass(); if (accountClass.shortValue() ==
     * EnumCodeDefine.ACCOUNT_CLASS_GCA) { objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;// 使用类型为虚账户 } else if
     * (accountClass.shortValue() == EnumCodeDefine.ACCOUNT_CLASS_CA) { objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;//
     * 使用类型为账户 } } FeeItemList feeItemList = oneTimeFee.getFee_list(); FeeItem[] feeItems = feeItemList.getItem(); if
     * (CommonUtil.isEmpty(feeItems)) { throw IMSUtil.createBusiException(ErrorCodeDefine.CHARGE_ITEM_NOT_EXIST); } // 2011-09-01
     * wuyujie : 设置spec_id Integer specId = context.getBusiSpecId(); for (FeeItem feeItem : feeItems) { SCalFee sCalFee = new
     * SCalFee(); sCalFee.setAmount(feeItem.getAmount()); sCalFee.setChargeType(feeItem.getCharge_type());
     * sCalFee.setCustId(cust.getCustId()); sCalFee.setDeductType(feeItem.getBill_type().shortValue());
     * sCalFee.setProductOfferId(feeItem.getItem_code()); sCalFee.setRemake(feeItem.getRemark()); Integer billingType =
     * IMSUtil.getMainBillingType(user.getBillingType()); if (billingType != null)
     * sCalFee.setBillingType(billingType.shortValue()); // sCalFee.setMeasureId(arg0); // sCalFee.setSCalParamList(arg0);
     * SOneTimeFee sOneTimeFee = new SOneTimeFee(); sOneTimeFee.setResourceId(user.getResourceId());
     * sOneTimeFee.setAcctId(acct.getAcctId()); if (CommonUtil.isValid(oneTimeFee.getPay_acct_id())) {
     * sOneTimeFee.setPayAcctId(oneTimeFee.getPay_acct_id()); } else { sOneTimeFee.setPayAcctId(acct.getAcctId()); }
     * sOneTimeFee.setPhoneId(oneTimeFee.getPhone_id()); sOneTimeFee.setObjectType(objectType);
     * sOneTimeFee.setEventType(eventType); sOneTimeFee.setSCalFee(sCalFee); sOneTimeFee.setSpecId(specId);
     * returnList.add(sOneTimeFee); } return returnList; }
     */

    /**
     * @Description: 把sdl的SOperInfo结构转换成webservice的SOperInfo结构
     * @param sdlOperInfo
     * @return
     */
    public static SOperInfo sdlOper2JavaOper(com.ailk.openbilling.persistence.imssdl.entity.SOperInfo sdlOperInfo)
    {
        SOperInfo wsOperInfo = new SOperInfo();
        // 转换SOperInfo
        if (sdlOperInfo.getAcctId() != 0)
        {
            wsOperInfo.setAcct_id(sdlOperInfo.getAcctId());
        }

        if (sdlOperInfo.getBusiCode() != 0)
        {
            wsOperInfo.setBusi_code(sdlOperInfo.getBusiCode());
        }

        if (sdlOperInfo.getChargeFlag() != 0)
        {
            wsOperInfo.setCharge_flag(sdlOperInfo.getChargeFlag());
        }

        if (sdlOperInfo.getCountyCode() != 0)
        {
            wsOperInfo.setCounty_code(sdlOperInfo.getCountyCode());
        }

        if (sdlOperInfo.getCustId() != 0)
        {
            wsOperInfo.setCust_id(sdlOperInfo.getCustId());
        }

        if (sdlOperInfo.getIsMonitor() != 0)
        {
            wsOperInfo.setIs_monitor(sdlOperInfo.getIsMonitor());
        }
        if (sdlOperInfo.getIsnormal() != 0)
        {
            wsOperInfo.setIsnormal(sdlOperInfo.getIsnormal());
        }

        if (sdlOperInfo.getOpId() != 0)
        {
            wsOperInfo.setOp_id(CommonUtil.IntegerToLong(sdlOperInfo.getOpId()));
        }

        if (sdlOperInfo.getOrgId() != 0)
        {
            wsOperInfo.setOrg_id(sdlOperInfo.getOrgId());
        }

        if (sdlOperInfo.getPhoneId() != null)
        {
            wsOperInfo.setPhone_id(sdlOperInfo.getPhoneId());
        }

        if (sdlOperInfo.getProvCode() != 0)
        {
            wsOperInfo.setProv_code(sdlOperInfo.getProvCode());
        }

        if (sdlOperInfo.getRegionCode() != 0)
        {
            wsOperInfo.setRegion_code(sdlOperInfo.getRegionCode());
        }

        if (sdlOperInfo.getRemark() != null)
        {
            wsOperInfo.setRemark(sdlOperInfo.getRemark());
        }

        if (sdlOperInfo.getRsoNbr() != null)
        {
            wsOperInfo.setRso_nbr(sdlOperInfo.getRsoNbr());
        }

        if (sdlOperInfo.getSoDate() != null)
        {
            wsOperInfo.setSo_date(DateUtil.formatDate(sdlOperInfo.getSoDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }

        if (sdlOperInfo.getSoMode() != 0)
        {
            wsOperInfo.setSo_mode(sdlOperInfo.getSoMode());
        }
        else
        {
            wsOperInfo.setSo_mode(ConstantDefine.INNSER_SO_MODE);
        }

        if (CommonUtil.isValid(sdlOperInfo.getSoNbr()))
        {
            wsOperInfo.setSo_nbr(sdlOperInfo.getSoNbr());
        }

        if (sdlOperInfo.getUserId() != 0)
        {
            wsOperInfo.setUser_id(sdlOperInfo.getUserId());
        }

        wsOperInfo.setStep_id(sdlOperInfo.getStepId());
        wsOperInfo.setNotify_flag(sdlOperInfo.getNotifyFlag());
        wsOperInfo.setSource_system(sdlOperInfo.getSourceSystem());

        return wsOperInfo;
    }

    /**
     * SOperInfo由EDL结构转成java结构 wukl 2012-7-20
     * 
     * @param sdlOperInfo
     * @return
     */
    public static SOperInfo edlOper2JavaOper(com.ailk.openbilling.persistence.imssdl.entity.SOperInfo edlOperInfo)
    {
        SOperInfo wsOperInfo = new SOperInfo();
        // 转换SOperInfo
        if (edlOperInfo.getAcctId() != 0)
        {
            wsOperInfo.setAcct_id(edlOperInfo.getAcctId());
        }

        if (edlOperInfo.getBusiCode() != 0)
        {
            wsOperInfo.setBusi_code(edlOperInfo.getBusiCode());
        }

        if (edlOperInfo.getChargeFlag() != 0)
        {
            wsOperInfo.setCharge_flag(edlOperInfo.getChargeFlag());
        }

        if (edlOperInfo.getCountyCode() != 0)
        {
            wsOperInfo.setCounty_code(edlOperInfo.getCountyCode());
        }

        if (edlOperInfo.getCustId() != 0)
        {
            wsOperInfo.setCust_id(edlOperInfo.getCustId());
        }

        if (edlOperInfo.getIsMonitor() != 0)
        {
            wsOperInfo.setIs_monitor(edlOperInfo.getIsMonitor());
        }
        if (edlOperInfo.getIsnormal() != 0)
        {
            wsOperInfo.setIsnormal(edlOperInfo.getIsnormal());
        }

        if (edlOperInfo.getOpId() != 0)
        {
            wsOperInfo.setOp_id(CommonUtil.IntegerToLong(edlOperInfo.getOpId()));
        }

        if (edlOperInfo.getOrgId() != 0)
        {
            wsOperInfo.setOrg_id(edlOperInfo.getOrgId());
        }

        if (edlOperInfo.getPhoneId() != null)
        {
            wsOperInfo.setPhone_id(edlOperInfo.getPhoneId());
        }

        if (edlOperInfo.getProvCode() != 0)
        {
            wsOperInfo.setProv_code(edlOperInfo.getProvCode());
        }

        if (edlOperInfo.getRegionCode() != 0)
        {
            wsOperInfo.setRegion_code(edlOperInfo.getRegionCode());
        }

        if (edlOperInfo.getRemark() != null)
        {
            wsOperInfo.setRemark(edlOperInfo.getRemark());
        }

        if (edlOperInfo.getRsoNbr() != null)
        {
            wsOperInfo.setRso_nbr(edlOperInfo.getRsoNbr());
        }

        if (edlOperInfo.getSoDate() != null)
        {
            wsOperInfo.setSo_date(DateUtil.formatDate(edlOperInfo.getSoDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
        }

        if (edlOperInfo.getSoMode() != 0)
        {
            wsOperInfo.setSo_mode(edlOperInfo.getSoMode());
        }
        else
        {
            wsOperInfo.setSo_mode(ConstantDefine.INNSER_SO_MODE);
        }

        if (CommonUtil.isValid(edlOperInfo.getSoNbr()))
        {
            wsOperInfo.setSo_nbr(edlOperInfo.getSoNbr());
        }

        if (edlOperInfo.getUserId() != 0)
        {
            wsOperInfo.setUser_id(edlOperInfo.getUserId());
        }

        wsOperInfo.setStep_id(edlOperInfo.getStepId());
        wsOperInfo.setNotify_flag(edlOperInfo.getNotifyFlag());
        wsOperInfo.setSource_system(edlOperInfo.getSourceSystem());

        return wsOperInfo;
    }

    /**
     * @Description: 把xdr的SOperInfo结构转换成webservice的SOperInfo结构
     * @param sdlOperInfo
     * @return
     */
    public static SOperInfo xdrOper2JavaOper(com.ailk.openbilling.persistence.imsxdr.entity.SOperInfo sdlOperInfo)
    {
        SOperInfo wsOperInfo = new SOperInfo();
        // 转换SOperInfo
        if (sdlOperInfo.getAcctId() != 0)
        {
            wsOperInfo.setAcct_id(sdlOperInfo.getAcctId());
        }

        if (sdlOperInfo.getBusiCode() != 0)
        {
            wsOperInfo.setBusi_code(sdlOperInfo.getBusiCode());
        }

        if (sdlOperInfo.getChargeFlag() != 0)
        {
            wsOperInfo.setCharge_flag(sdlOperInfo.getChargeFlag());
        }

        if (sdlOperInfo.getCountyCode() != 0)
        {
            wsOperInfo.setCounty_code(sdlOperInfo.getCountyCode());
        }

        if (sdlOperInfo.getCustId() != 0)
        {
            wsOperInfo.setCust_id(sdlOperInfo.getCustId());
        }

        if (sdlOperInfo.getIsMonitor() != 0)
        {
            wsOperInfo.setIs_monitor(sdlOperInfo.getIsMonitor());
        }

        if (sdlOperInfo.getIsnormal() != 0)
        {
            wsOperInfo.setIsnormal(sdlOperInfo.getIsnormal());
        }

        if (sdlOperInfo.getOpId() != 0)
        {
            wsOperInfo.setOp_id(CommonUtil.IntegerToLong(sdlOperInfo.getOpId()));
        }

        if (sdlOperInfo.getOrgId() != 0)
        {
            wsOperInfo.setOrg_id(sdlOperInfo.getOrgId());
        }

        if (sdlOperInfo.getPhoneId() != null)
        {
            wsOperInfo.setPhone_id(sdlOperInfo.getPhoneId());
        }

        if (sdlOperInfo.getProvCode() != 0)
        {
            wsOperInfo.setProv_code(sdlOperInfo.getProvCode());
        }

        if (sdlOperInfo.getRegionCode() != 0)
        {
            wsOperInfo.setRegion_code(sdlOperInfo.getProvCode());
        }

        if (sdlOperInfo.getRemark() != null)
        {
            wsOperInfo.setRemark(sdlOperInfo.getRemark());
        }

        if (sdlOperInfo.getRsoNbr() != null)
        {
            wsOperInfo.setRso_nbr(sdlOperInfo.getRsoNbr());
        }

        if (sdlOperInfo.getSoDate() != 0)
        {
            wsOperInfo.setSo_date(String.valueOf(sdlOperInfo.getSoDate()));
        }

        if (sdlOperInfo.getSoMode() != 0)
        {
            wsOperInfo.setSo_mode(sdlOperInfo.getSoMode());
        }

        if (sdlOperInfo.getSoNbr() != null)
        {
            wsOperInfo.setSo_nbr(sdlOperInfo.getSoNbr());
        }

        if (sdlOperInfo.getUserId() != 0)
        {
            wsOperInfo.setUser_id(sdlOperInfo.getUserId());
        }

        wsOperInfo.setStep_id((short) 0);

        return wsOperInfo;
    }

    public static com.ailk.openbilling.persistence.imssdl.entity.SOperInfo javaOper2SdlOper(SOperInfo javaOperInfo)
    {
        com.ailk.openbilling.persistence.imssdl.entity.SOperInfo wsOperInfo = new com.ailk.openbilling.persistence.imssdl.entity.SOperInfo();
        // 转换SOperInfo
        if (CommonUtil.isValid(javaOperInfo.getAcct_id()))
        {
            wsOperInfo.setAcctId(javaOperInfo.getAcct_id());
        }

        if (CommonUtil.isValid(javaOperInfo.getBusi_code()))
        {
            wsOperInfo.setBusiCode(javaOperInfo.getBusi_code());
        }

        if (CommonUtil.isValid(javaOperInfo.getCharge_flag()))
        {
            wsOperInfo.setChargeFlag(javaOperInfo.getCharge_flag());
        }

        if (CommonUtil.isValid(javaOperInfo.getCounty_code()))
        {
            wsOperInfo.setCountyCode(javaOperInfo.getCounty_code());
        }

        if (CommonUtil.isValid(javaOperInfo.getCust_id()))
        {
            wsOperInfo.setCustId(javaOperInfo.getCust_id());
        }
        if (javaOperInfo.getIs_monitor() != null)
        {
            wsOperInfo.setIsMonitor(javaOperInfo.getIs_monitor());
        }
        if (javaOperInfo.getIsnormal() != null)
        {
            wsOperInfo.setIsnormal(javaOperInfo.getIsnormal());
        }

        if (CommonUtil.isValid(javaOperInfo.getOp_id()))
        {
            wsOperInfo.setOpId(javaOperInfo.getOp_id().intValue());
        }

        if (CommonUtil.isValid(javaOperInfo.getOrg_id()))
        {
            wsOperInfo.setOrgId(javaOperInfo.getOrg_id());
        }

        if (javaOperInfo.getPhone_id() != null)
        {
            wsOperInfo.setPhoneId(javaOperInfo.getPhone_id());
        }
        // @Date 2012-07-18 yugb :修改空指针异常
        if (CommonUtil.isValid(javaOperInfo.getProv_code()))
        {
            wsOperInfo.setProvCode(javaOperInfo.getProv_code());
        }

        if (CommonUtil.isValid(javaOperInfo.getRegion_code()))
        {
            wsOperInfo.setRegionCode(javaOperInfo.getRegion_code());
        }

        if (CommonUtil.isValid(javaOperInfo.getRso_nbr()))
        {
            wsOperInfo.setRemark(javaOperInfo.getRemark());
        }

        if (CommonUtil.isValid(javaOperInfo.getRso_nbr()))
        {
            wsOperInfo.setRsoNbr(javaOperInfo.getRso_nbr());
        }

        if (javaOperInfo.getSo_date() != null)
        {
            wsOperInfo.setSoDate(DateUtil.getObdDate(javaOperInfo.getSo_date()));
        }

        if (CommonUtil.isValid(javaOperInfo.getSo_mode()))
        {
            wsOperInfo.setSoMode(javaOperInfo.getSo_mode());
        }

        if (CommonUtil.isValid(javaOperInfo.getSo_nbr()))
        {
            wsOperInfo.setSoNbr(javaOperInfo.getSo_nbr());
        }

        if (CommonUtil.isValid(javaOperInfo.getUser_id()))
        {
            wsOperInfo.setUserId(javaOperInfo.getUser_id());
        }

        if (javaOperInfo.getStep_id() != null)
            wsOperInfo.setStepId(javaOperInfo.getStep_id());
        if (javaOperInfo.getNotify_flag() != null)
            wsOperInfo.setNotifyFlag(javaOperInfo.getNotify_flag());
        else
            wsOperInfo.setNotifyFlag(EnumCodeDefine.SEND_NOTIFICATION);
        if (CommonUtil.isValid(javaOperInfo.getSource_system()))
            wsOperInfo.setSourceSystem(javaOperInfo.getSource_system());

        return wsOperInfo;
    }

    public static void copySdl2Xdr(Object sdlObj, Object xdrObj)
    {
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(sdlObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if ((!Modifier.isStatic(f.getModifiers())) && (!Modifier.isFinal(f.getModifiers())))
                {
                    String origName = getOrigFieldName(f);
                    String methodName = getSdlMethodBySdl(f);
                    try
                    {
                        Boolean nullsetted = (Boolean) BeanUtils.invokeMethod(sdlObj, "is_used", new Object[] { origName });
                        if (!nullsetted)
                            continue;
                        BeanUtils.invokeMethod(xdrObj, methodName, ClassUtil.getPropertyValue(sdlObj, f.getName()));
                    }
                    catch (Exception ex)
                    {
                    	throw new IMSException(ex);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static void copyXdr2Sdl(Object xdrObj, Object sdlObj)
    {
      //@Date 2012-11-07 wuyujie sdl改用EDL方式生成后，需要考虑null情况
        if(xdrObj == null)
            return;
        
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(xdrObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if ((!Modifier.isStatic(f.getModifiers())) && (!Modifier.isFinal(f.getModifiers())))
                {
                    String origName = getOrigFieldName(f);
                    String methodName = getSdlMethodBySdl(f);
                    try
                    {
                        Boolean nullsetted = (Boolean) BeanUtils.invokeMethod(xdrObj, "is_used", new Object[] { origName });
                        if (!nullsetted)
                            continue;
                        BeanUtils.invokeMethod(sdlObj, methodName, ClassUtil.getPropertyValue(xdrObj, f.getName()));
                    }
                    catch (Exception ex)
                    {
                    	throw new IMSException(ex);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static void copySdl2Java(Object sdlObj, Object javaObj)
    {
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(javaObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if ((!Modifier.isStatic(f.getModifiers())) && (!Modifier.isFinal(f.getModifiers())))
                {
                    String sdlFName = getSdlFieldName(f);
                    String sdlOrigName = getSdlOrigFieldName(f);
                    try
                    {
                        Boolean nullsetted = (Boolean) BeanUtils.invokeMethod(sdlObj, "is_used", new Object[] { sdlOrigName });
                        if (!nullsetted)
                            continue;
                        BeanUtils.setFieldValue(javaObj, f.getName(), ClassUtil.getPropertyValue(sdlObj, sdlFName));
                    }
                    catch (Exception ex)
                    {
                    	throw new IMSException(ex);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static void copyJava2Sdl(Object sdlObj, Object javaObj)
    {
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(javaObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if ((!Modifier.isStatic(f.getModifiers())) && (!Modifier.isFinal(f.getModifiers())))
                {
                    String methodName = getSdlMethodByJava(f);
                    try
                    {
                        Object value = ClassUtil.getPropertyValue(javaObj, f.getName());
                        if (value != null)// TODO 不符合基本类型定义的属性
                            BeanUtils.invokeMethod(sdlObj, methodName, value);
                    }
                    catch (Exception ex)
                    {
                    	throw new IMSException(ex);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * 从上发接口表的数据对象转成历史表数据对象
     * 
     * @param dbObj
     * @param hisTableClass
     * @return 2012-05-19 wuyujie
     */
    public static DataObject copyJava2His(DataObject dbObj, DataObject hisObj)
    {
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(hisObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if (!Modifier.isPrivate(f.getModifiers()))
                {
                    continue;
                }
                String fieldName = f.getName();
                ClassUtil.setFieldValue(hisObj, fieldName, ClassUtil.getPropertyValue(dbObj, fieldName));
            }
        }
        catch (Exception e)
        {
            IMSUtil.throwBusiException(e);
        }
        return hisObj;
    }

    public static String getSdlMethodByJava(Field f)
    {
        String sdlFName = CommonUtil.transFirstLetter(f.getName(), false);
        String methodName = "set_" + sdlFName;
        return methodName;
    }

    public static String getSdlFieldName(Field f)
    {
        String sdlFName = CommonUtil.transFirstLetter(f.getName(), true);
        if (f.getType().equals(Integer.class))
        {
            sdlFName = "m_i" + sdlFName;
        }
        else if (f.getType().equals(Long.class))
        {
            sdlFName = "m_ll" + sdlFName;
        }
        else if (f.getType().equals(String.class))
        {
            sdlFName = "m_str" + sdlFName;
        }
        else if (f.getType().equals(Short.class))
        {
            sdlFName = "m_n" + sdlFName;
        }
        else if (f.getType().equals(java.util.Date.class))
        {
            sdlFName = "m_date" + sdlFName;
        }
        else if (f.getType().equals(java.sql.Timestamp.class))
        {
            sdlFName = "m_ts" + sdlFName;
        }
        else if (f.getType().equals(int.class))
        {
            sdlFName = "m_i" + sdlFName;
        }
        else if (f.getType().equals(long.class))
        {
            sdlFName = "m_ll" + sdlFName;
        }
        else if (f.getType().equals(short.class))
        {
            sdlFName = "m_n" + sdlFName;
        }
        else
        {
            sdlFName = "m_s" + sdlFName;
        }
        return sdlFName;
    }

    public static String getSdlMethodBySdl(Field f)
    {
        // 去掉SDL属性的特殊开头
        String name = f.getName();
        if (name.startsWith("m_i"))
        {
            name = name.substring("m_i".length());
        }
        else if (name.startsWith("m_ll"))
        {
            name = name.substring("m_ll".length());
        }
        else if (name.startsWith("m_str"))
        {
            name = name.substring("m_str".length());
        }
        else if (name.startsWith("m_n"))
        {
            name = name.substring("m_n".length());
        }
        else if (name.startsWith("m_date"))
        {
            name = name.substring("m_date".length());
        }
        else if (name.startsWith("m_ts"))
        {
            name = name.substring("m_ts".length());
        }
        else if (name.startsWith("m_s"))
        {
            name = name.substring("m_s".length());
        }

        // 将去除后的字段名的首字母转成小写
        name = CommonUtil.transFirstLetter(name, false);

        String methodName = "set_" + name;
        return methodName;
    }

    public static String getOrigFieldName(Field f)
    {
        // 去掉SDL属性的特殊开头
        String name = f.getName();
        if (name.startsWith("m_i"))
        {
            name = name.substring("m_i".length());
        }
        else if (name.startsWith("m_ll"))
        {
            name = name.substring("m_ll".length());
        }
        else if (name.startsWith("m_str"))
        {
            name = name.substring("m_str".length());
        }
        else if (name.startsWith("m_n"))
        {
            name = name.substring("m_n".length());
        }
        else if (name.startsWith("m_date"))
        {
            name = name.substring("m_date".length());
        }
        else if (name.startsWith("m_ts"))
        {
            name = name.substring("m_ts".length());
        }
        else if (name.startsWith("m_s"))
        {
            name = name.substring("m_s".length());
        }

        // 将去除后的字段名的首字母转成小写
        name = CommonUtil.transFirstLetter(name, false);

        // 将去后的字段名中遇到大写字母就在字母前面加“_”
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < name.length(); i++)
        {
            char c = name.charAt(i);
            if (Character.isUpperCase(c))
            {
                sb.append('_');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String getSdlOrigFieldName(Field f)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < f.getName().length(); i++)
        {
            char c = f.getName().charAt(i);
            if (Character.isUpperCase(c))
            {
                sb.append('_');
            }
            sb.append(c);
        }
        return sb.toString();
    }


    /**
     * 上海账管的公共参数构造 wukl 2012-2-21
     * 
     * @param context
     * @return
     */
    /*
    public static SCommPara javaOper2SCommPara(IMSContext context)
    {
        SCommPara commPara = new SCommPara();
        SOperInfo oper = context.getOper();

        commPara.set_busiCode(oper.getBusi_code().intValue());
        if (oper.getOp_id() != null)
            commPara.set_opId(oper.getOp_id());

        if (oper.getOrg_id() != null)
            commPara.set_orgId(oper.getOrg_id());

        if (oper.getRegion_code() != null)
            commPara.set_regionCode(oper.getRegion_code());

        if (CommonUtil.isNotEmpty(oper.getSo_date()))
            commPara.set_soDate(DateUtil.getObdDate(oper.getSo_date()));

        commPara.set_soNbr(context.getDoneCode());
        return commPara;
    }
*/
    /**
     * 上海帐管CommonParam 参数封装
     * 
     * @author xieqr 2012-3-1
     * @param context
     * @return
     */
    /*
     * public static com.ailk.openbilling.persistence.outInterface.entity.CommonParam javaOper2SHCommonParam(IMSContext context){
     * com.ailk.openbilling.persistence.outInterface.entity.CommonParam param =new
     * com.ailk.openbilling.persistence.outInterface.entity.CommonParam(); SOperInfo oper = context.getOper(); if
     * (oper.getSo_mode() != null) param.setChannelId(oper.getSo_mode()); if (oper.getCounty_code() != null)
     * param.setCountyCode(oper.getCounty_code()); else
     * param.setCountyCode(SysUtil.busi.getInt(SysCodeDefine.busi.DEFAULT_COUNTRY_ID)); if (CommonUtil.isValid(oper.getOp_id()))
     * param.setOpId(oper.getOp_id()); else param.setOpId(SysUtil.busi.getInt(SysCodeDefine.busi.INFOSYSTEM_OPID).longValue()); if
     * (oper.getOrg_id() != null) param.setOrgId(oper.getOrg_id()); if (oper.getSo_date() != null)
     * param.setOutSoDate(IMSUtil.formatDate(oper.getSo_date(), null)); if (oper.getSo_nbr() != null)
     * param.setOutSoNbr(oper.getSo_nbr()); param.setRelSoNbr(context.getDoneCode());
     * param.setRelSoDate(context.getRequestDate()); if (oper.getProv_code() != null) param.setProvCode(oper.getProv_code()); if
     * (oper.getRegion_code() != null) param.setRegionCode(oper.getRegion_code()); else
     * param.setRegionCode(SysUtil.busi.getInt(SysCodeDefine.busi.DEFAULT_REGION_CODE)); if
     * (!CommonUtil.isEmpty(oper.getRemark())) param.setRemark(oper.getRemark());
     * param.setSourceType(EnumCodeDefine.OUTER_SOURCETYPE); return param; }
     */
    /**
     * @Description: sdl特殊的int64转成java的date类型， 比如long型：20110802112359 -> date类型：2011-08-02 11:23:59
     * @param sdlLong
     * @return wuyj
     */
    public static Date sdlLong2JavaDate(long sdlLong)
    {
        String time = String.valueOf(sdlLong);
        return DateUtil.getFormattedDate(time);
    }

    /**
     * @Description: java的date类型转成sdl特殊的int64， 比如date类型：2011-08-02 11:23:59 -> long型：20110802112359
     * @param date
     * @return wuyj
     */
    public static long javaDate2SdlLong(Date date)
    {
        String str = DateUtil.formatDate(date, DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
        return Long.valueOf(str);
    }

    /**
     * @Description:java的string类型转成sdl特殊的int64， 比如 字符串：2011-08-02 11:23:59 -> long型：20110802112359 只要是标准的日期格式字符串都可以
     * @param strDate
     * @return
     */
    public static long strDate2SdlLong(String strDate)
    {
        Date date = DateUtil.getFormattedDate(strDate);
        return javaDate2SdlLong(date);
    }

    /**
     * @Description:sdl的特殊的int64类型转成真正的int64， 比如 long型：20110802112359 -> 1312255439
     * @param sdlLong
     * @return
     */
    public static long sdlLong2SdlTime(long sdlLong)
    {
        String time = String.valueOf(sdlLong);
        return IMSUtil.getMdbDate(DateUtil.getFormattedDate(time));
    }

    /**
     * @Description:sdl的真正的int64类型转成特殊的int64， 比如 long型：1312255439 -> 20110802112359
     * @param sdlTime
     * @return
     */
    public static long sdlTime2SdlLong(long sdlTime)
    {
        Date date = DateUtil.UTCToDate(sdlTime);
        return javaDate2SdlLong(date);
    }

    /**
     * @Description: 把sdl的 SBalance结构转成java的 SBalance
     * @param sdlBalance
     * @return
     */
    public static com.ailk.openbilling.persistence.imsintf.entity.SBalance sdlBalance2Java(
            com.ailk.openbilling.persistence.imssdl.entity.SBalance sdlBalance)
    {
        com.ailk.openbilling.persistence.imsintf.entity.SBalance javaBalance = new com.ailk.openbilling.persistence.imsintf.entity.SBalance();
        if (sdlBalance.is_used("ACCT_ID"))
        {
            javaBalance.setAcct_id(sdlBalance.getAcctId());
        }
        if (sdlBalance.is_used("AMOUNT"))
        {
            javaBalance.setAmount(CommonUtil.long2Double(sdlBalance.getAmount()));
            // javaBalance.setMeasure_id(AmountUtil.getDefaultDbMeasureId());
        }
        if (sdlBalance.is_used("BOOK_ID"))
        {
            javaBalance.setBook_id(sdlBalance.getBookId());
        }
        if (sdlBalance.is_used("BOOK_ITEM"))
        {
            javaBalance.setBook_item(sdlBalance.getBookItem());
        }
        if (sdlBalance.is_used("DAYS"))
        {
            javaBalance.setDays(sdlBalance.getDays());
        }
        if (sdlBalance.is_used("PHONE_ID"))
        {
            javaBalance.setPhone_id(sdlBalance.getPhoneId());
        }
        if (sdlBalance.is_used("USER_ID"))
        {
            javaBalance.setUser_id(sdlBalance.getUserId());
        }
        if (sdlBalance.is_used("VALID_DATE"))
        {
            javaBalance.setValid_date(DateUtil.UTCToString(sdlBalance.getValidDate()));
        }
        if (sdlBalance.is_used("EXPIRE_DATE"))
        {
            javaBalance.setExpire_date(DateUtil.UTCToString(sdlBalance.getExpireDate()));
        }
        return javaBalance;
    }

    /**
     * @Description: 把java的 SBalance结构转成sdl的 SBalance
     * @param sdlBalance
     * @return
     */
    public static com.ailk.openbilling.persistence.imssdl.entity.SBalance javaBalance2Sdl(
            com.ailk.openbilling.persistence.imsintf.entity.SBalance javaBalance)
    {
        com.ailk.openbilling.persistence.imssdl.entity.SBalance sdlBalance = new com.ailk.openbilling.persistence.imssdl.entity.SBalance();
        if (javaBalance.getAcct_id() != null)
        {
            sdlBalance.setAcctId(javaBalance.getAcct_id());
        }
        if (javaBalance.getAmount() != null)
        {
            // 转换值
            // @Date 2012-08-22 tangjl5 :Story # 49447
            sdlBalance.setAmount(CommonUtil.double2Long(javaBalance.getAmount()));
        }
        if (javaBalance.getBook_id() != null)
        {
            sdlBalance.setBookId(javaBalance.getBook_id());
        }
        if (javaBalance.getBook_item() != null)
        {
            sdlBalance.setBookItem(javaBalance.getBook_item());
        }
        if (javaBalance.getDays() != null)
        {
            sdlBalance.setDays(javaBalance.getDays());
        }
        if (javaBalance.getPhone_id() != null)
        {
            sdlBalance.setPhoneId(javaBalance.getPhone_id());
        }
        if (javaBalance.getUser_id() != null)
        {
            sdlBalance.setUserId(javaBalance.getUser_id());
        }
        if (javaBalance.getValid_date() != null)
        {
            sdlBalance.setValidDate(DateUtil.toSecond(javaBalance.getValid_date()));
        }
        if (javaBalance.getExpire_date() != null)
        {
            sdlBalance.setExpireDate(DateUtil.toSecond(javaBalance.getExpire_date()));
        }
        return sdlBalance;
    }

   

    /*	*//**
     * 有下划线字段的实体转换成没有下划线的实体
     * 
     * @author xieqr
     * @param imsObj
     * @param acctObj
     */
    /*
     * public static void copyIMSObj2AcctObj(Object imsObj,Object acctObj){ try { java.lang.reflect.Field[] fs =
     * ClassUtil.getFields(imsObj.getClass()); Class amsObjClass = acctObj.getClass(); for (java.lang.reflect.Field f : fs) {
     * Class type = f.getType(); if(type.getName().equals("java.util.List")||type.isArray()){ continue; }else
     * if(IComplexEntity.class.isAssignableFrom(type)){ continue; } String imsName = f.getName(); String javaName =
     * CommonUtil.replace_2JavaName(imsName); try { Object value=getFieldValue(imsObj, imsName); if(value!=null){ Field amsField =
     * ClassUtil.getField(amsObjClass, javaName); if (amsField != null &&
     * amsField.getType().getName().equals("java.util.Date")&&type.getName().equals("java.lang.String")) {
     * logger.info("begin to convert Date String to Date  fieldName is :" + imsName + " *********************"); Date date =
     * IMSUtil.formatDate((String) value, null); setFieldValue(acctObj, javaName, date);
     * logger.info("end to convert Date String to Date  fieldName is :" + imsName + " *********************"); }else{
     * setFieldValue(acctObj, javaName, value); } } } catch (Exception ex) { throw new IMSException(ex); } } } catch (Exception e)
     * { throw new IMSException(e); } }
     *//**
     * 没有下划线的实体转换成有下划线的实体
     * 
     * @author xieqr
     * @param imsObj
     * @param acctObj
     */
    /*
     * public static void copyAcctObj2IMSObj(Object imsObj,Object acctObj){ try { java.lang.reflect.Field[] fs =
     * ClassUtil.getFields(imsObj.getClass()); Class amsObjClass = acctObj.getClass(); for (java.lang.reflect.Field f : fs) {
     * Class type = f.getType(); if(type.getName().equals("java.util.List")||type.isArray()){ continue; }else
     * if(IComplexEntity.class.isAssignableFrom(type)){ continue; } String imsName = f.getName(); String javaName =
     * CommonUtil.replace_2JavaName(imsName); try { Object value=getFieldValue(acctObj, javaName); if(value!=null){ Field amsField
     * = ClassUtil.getField(amsObjClass, javaName); if (amsField != null &&
     * amsField.getType().getName().equals("java.util.Date")&&type.getName().equals("java.lang.String")) { String dateStr =
     * DateUtil.getFormatDate((Date) value,DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS); setFieldValue(imsObj, imsName, dateStr);
     * }else{ setFieldValue(imsObj, imsName, value); } } } catch (Exception ex) { throw new IMSException(ex); } } } catch
     * (Exception e) { throw new IMSException(e); } }
     */

    /**
     * 不抛异常 xieqr 2012-3-26
     * 
     * @param obj
     * @param fieldName
     */
    public static Object getFieldValue(Object obj, String fieldName)
    {
        Object ret = null;
        try
        {
            ret = ClassUtil.getPropertyValue(obj, fieldName);

        }
        catch (Exception e)
        {
        	throw new IMSException(e);
        }
        return ret;
    }

    /**
     * 不抛异常
     */
    public static void setFieldValue(Object obj, String fieldName, Object value)
    {
        try
        {
            BeanUtils.setFieldValue(obj, fieldName, value);
        }
        catch (Exception e)
        {
        	throw new IMSException(e);
        }
    }

    /**
     * @description List<CoFnCharValue>转换成List<CoProdCharValue>
     * @author wangyh3
     * @date 2012-04-28
     */
    public static List<CoProdCharValue> coFnCharValues2CoProdCharValues(List<CoFnCharValue> coFnCharValues)
    {
        List<CoProdCharValue> coProdCharValues = new ArrayList<CoProdCharValue>();
        for (Iterator<CoFnCharValue> iterator2 = coFnCharValues.iterator(); iterator2.hasNext();)
        {
            CoFnCharValue value = (CoFnCharValue) iterator2.next();
            coProdCharValues.add(coFnCharValue2CoProdCharValue(value));
        }
        return coProdCharValues;
    }

    /**
     * @description List<CoProdCharValue> 转换成 List<CoFnCharValue>
     * @author wangyh3
     * @date 2012-04-28
     */
    public static List<CoFnCharValue> coProdCharValues2CoFnCharValues(List<CoProdCharValue> coProdCharValues)
    {
        List<CoFnCharValue> coFnCharValues = new ArrayList<CoFnCharValue>();
        for (Iterator<CoProdCharValue> iterator2 = coProdCharValues.iterator(); iterator2.hasNext();)
        {
            CoProdCharValue value = (CoProdCharValue) iterator2.next();
            coFnCharValues.add(coProdCharValue2CoFnCharValue(value));
        }
        return coFnCharValues;
    }

    /**
     * @description CoFnCharValue转换成 CoProdCharValue
     * @author wangyh3
     * @date 2012-04-28
     */
    private static CoProdCharValue coFnCharValue2CoProdCharValue(CoFnCharValue coFnCharValue)
    {
        CoProdCharValue coProdCharValue = null;
        if (coFnCharValue != null)
        {
            coProdCharValue = new CoProdCharValue();
            coProdCharValue.setProductId(coFnCharValue.getProductId());
            coProdCharValue.setGroupId(coFnCharValue.getGroupId());
            coProdCharValue.setSpecCharId(coFnCharValue.getSpecCharId());
            coProdCharValue.setValue(coFnCharValue.getValue());
            coProdCharValue.setValidDate(coFnCharValue.getValidDate());
            coProdCharValue.setExpireDate(coFnCharValue.getExpireDate());
            coProdCharValue.setSoNbr(coFnCharValue.getSoNbr());
            coProdCharValue.setCreateDate(coFnCharValue.getCreateDate());
            coProdCharValue.setSoDate(coFnCharValue.getSoDate());
            coProdCharValue.setObjectId(coFnCharValue.getObjectId());
            coProdCharValue.setObjectType(coFnCharValue.getObjectType());
        }
        return coProdCharValue;
    }

    /**
     * @description CoProdCharValue 转换成 CoFnCharValue
     * @author wangyh3
     * @date 2012-04-28
     */
    private static CoFnCharValue coProdCharValue2CoFnCharValue(CoProdCharValue coProdCharValue)
    {
        CoFnCharValue coFnCharValue = null;
        if (coProdCharValue != null)
        {
            coFnCharValue = new CoFnCharValue();
            coFnCharValue.setProductId(coProdCharValue.getProductId());
            coFnCharValue.setGroupId(coProdCharValue.getGroupId());
            coFnCharValue.setSpecCharId(coProdCharValue.getSpecCharId());
            coFnCharValue.setValue(coProdCharValue.getValue());
            coFnCharValue.setValidDate(coProdCharValue.getValidDate());
            coFnCharValue.setExpireDate(coProdCharValue.getExpireDate());
            coFnCharValue.setSoNbr(coProdCharValue.getSoNbr());
            if (coProdCharValue.getCreateDate() != null)
                coFnCharValue.setCreateDate(coProdCharValue.getCreateDate());
            coFnCharValue.setSoDate(coProdCharValue.getSoDate());
            coFnCharValue.setObjectId(coProdCharValue.getObjectId());
            coFnCharValue.setObjectType(coProdCharValue.getObjectType());
        }
        return coFnCharValue;
    }

}
