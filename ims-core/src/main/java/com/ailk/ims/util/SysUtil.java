package com.ailk.ims.util;

import com.ailk.ims.common.CacheCondition;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.init.DBConfigInitBean;
import com.ailk.openbilling.persistence.sys.entity.SysParameter;

/**
 * @Description: 系统配置工具类。与系统参数配置相关的方法可以定义在这里
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 */
public class SysUtil
{
    private static ImsLogger logger = new ImsLogger(SysUtil.class);
    public static String getString(String key, String defaultValue)
    {
        String value = null;
        SysParameter sp = DBConfigInitBean.getSingleCached(SysParameter.class, new CacheCondition(SysParameter.Field.paramCode,
                key));
        if (sp != null)
        {
            value = sp.getParamValue();
        }
        return CommonUtil.isEmpty(value) ? defaultValue : value;
    }

    public static String getString(String key)
    {
        return getString(key, null);
    }

    public static Boolean getBoolean(String key)
    {
        String value = getString(key);
        if (CommonUtil.isEmpty(value))
            return null;
        return Boolean.valueOf(value);
    }

    public static boolean getBoolean(String key, boolean defaultValue)
    {
        String value = getString(key);
        if (CommonUtil.isEmpty(value))
            return defaultValue;
        return getBoolean(key);
    }

    public static Integer getInt(String key)
    {
        return getInt(key, null);
    }

    public static Integer getInt(String key, Integer defaultValue)
    {
        String value = getString(key);
        return CommonUtil.isEmpty(value) ? defaultValue : Integer.valueOf(value);
    }

    public static Float getFloat(String key)
    {
        return getFloat(key, null);
    }

    public static Float getFloat(String key, Float defaultValue)
    {
        String value = getString(key);
        return value == null ? null : Float.valueOf(value);
    }

    public static String[] getStringArray(String key)
    {
        String value = getString(key);
        return value == null ? null : value.split(",");
    }

    public static int[] getIntArray(String key)
    {
        try
        {
            String[] value = getStringArray(key);
            if (CommonUtil.isEmpty(value))
                return null;
            int[] result = new int[value.length];
            for (int i = 0; i < value.length; i++)
            {
                result[i] = Integer.valueOf(value[i].trim());
            }
            return result;
        }
        catch (Exception e)
        {
            logger.error(e, e);
            return null;
        }
    }

    public static float[] getFloatArray(String key)
    {
        try
        {
            String[] value = getStringArray(key);
            float[] result = new float[value.length];
            for (int i = 0; i < value.length; i++)
            {
                result[i] = Float.valueOf(value[i]);
            }
            return result;
        }
        catch (Exception e)
        {
            logger.error(e, e);
            return null;
        }
    }

    public static boolean isLocalTest()
    {
        return getBoolean(SysCodeDefine.busi.LOCAL_TEST);
    }
}
