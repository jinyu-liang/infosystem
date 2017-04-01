package com.ailk.ims.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javassist.Modifier;
import jef.database.DataObject;
import jef.tools.reflect.BeanUtils;
import org.apache.log4j.Logger;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.xml.BaseNode;

/**
 * @Description: 日志工具类。
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 */
public class LogUtil
{
    /**
     * 当前是否支持debug级别日志输出
     */
    private static final boolean debugEnabled;

    /**
     * 当前是否支持info级别日志输出
     */
    private static final boolean infoEnabled;

    static
    {
        // 统一一次初始化
        Logger logger = Logger.getLogger(LogUtil.class);
        debugEnabled = logger.isDebugEnabled();
        infoEnabled = logger.isInfoEnabled();
    }

    public static boolean isDebugEnabled()
    {
        return debugEnabled;
    }

    public static boolean isInfoEnabled()
    {
        return infoEnabled;
    }

    public static String buildContext(IMSContext context)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getServicePrefix());
        sb.append(" (");
        sb.append(context.getSoNbr());
        sb.append(") ");
        return sb.toString();
    }

    public static String buildTime(long start, long end)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(",cost time[ms]:").append(end - start);
        return sb.toString();
    }
    /**
     * 用ImsLogger(Class clazz)替代
     * 
     * @deprecated
     */
    public static Logger getLogger(Class clazz)
   {
        return Logger.getLogger(clazz.getName());
    }

    /**
    * 用 ImsLogger 中的api代替
     * 
     * @deprecated
     */
    public static String buildLogInfo(String logStr, IMSContext context)
    {
        StringBuffer sb = new StringBuffer();

        if (context != null)
        {
            sb.append(context.getServicePrefix());
            sb.append(" (");
            sb.append(context.getSoNbr());
            sb.append(") ");
        }
        sb.append(" ****** ");

        if (!CommonUtil.isEmpty(logStr))
            sb.append(logStr);

        return sb.toString();
    }

    /**
     * 用 ImsLogger 中的api代替
     * 
     * @deprecated
     */
    public static String buildLogInfo(String logStr, long start, IMSContext context)
    {
        long end = System.currentTimeMillis();
       StringBuffer sb = new StringBuffer();

       if (context != null)
       {
           sb.append(context.getServicePrefix());
            sb.append(" (");
            sb.append(context.getSoNbr());
            sb.append(") ");
        }
       sb.append(" ****** ").append(logStr).append(". cost time : ").append(end - start).append("ms");
       return sb.toString();
    }

    /**
     * 构建业务请求的前缀标识
     * 
     * @author wuyj 2012-9-29
     * @param serviceConfig,服务节点
     * @param methodConfig,方法节点
     * @param doneCode,流水号
     * @return
     */
    public static String buildBusiPrefix(BaseNode serviceConfig, BaseNode methodConfig, long doneCode)
    {
        StringBuilder sb = new StringBuilder();
        if (serviceConfig != null)
        {
            sb.append(serviceConfig.getAttribute(BusiUtil.ATTR_BEAN));
            sb.append(".");
        }
        if (methodConfig != null)
        {
            sb.append(methodConfig.getTagName());
        }
        sb.append("[");
        sb.append(doneCode);
        sb.append("] ");
        return sb.toString();
    }

    public static String parse(Object object, int lay, String excludeReg)
    {
        Class clazz = object.getClass();
        if (!CommonUtil.isComplex(clazz))
        {
            return object.toString();// 对象本身是简单类型，则直接打印
        }
        if (object instanceof Collection)
        {
            return parseCollection((Collection) object, lay, excludeReg);
        }
        else if (object instanceof Map)
        {
            return parseMap((Map) object, lay, excludeReg);
        }
        else if (clazz.isArray())
        {
            Object[] arr = null;
            Class cmpType = clazz.getComponentType();
            if (cmpType == long.class)
            {
                arr = parse2ObjectArray((long[]) object);
            }
            else if (cmpType == int.class)
            {
                arr = parse2ObjectArray((int[]) object);
            }
            else if (cmpType == short.class)
            {
                arr = parse2ObjectArray((short[]) object);
            }
            else if (cmpType == double.class)
            {
                arr = parse2ObjectArray((double[]) object);
            }
            else if (cmpType == float.class)
            {
                arr = parse2ObjectArray((float[]) object);
            }
            else
            {
                arr = (Object[]) object;
            }
            return parseArray(arr, lay, excludeReg);

        }
        else
        {
            return parseObject(object, lay, excludeReg);
        }

    }

    private static String parseCollection(Collection coll, int lay, String excludeReg)
    {
        StringBuffer sb = new StringBuffer();
        if (coll.isEmpty())
        {
            sb.append("[empty]");
        }
        else
        {
            Object[] arr = coll.toArray(new Object[coll.size()]);
            sb.append(parseArray(arr, lay, excludeReg));
        }
        return sb.toString();
    }

    private static String parseArray(Object[] arr, int lay, String excludeReg)
    {
        StringBuffer sb = new StringBuffer();
        String blankStr = getPrefixBlank(lay);
        if (CommonUtil.isEmpty(arr))
        {
            return blankStr + "[empty]";
        }
        for (int i = 0; i < arr.length; i++)
        {
            Object item = arr[i];
            if (sb.length() > 0)
            {
                sb.append("\n");
            }
            sb.append(blankStr);
            sb.append("[" + i + "] : ");
            Class type = (item == null) ? null : item.getClass();
            sb.append(parseItem(item, type, lay, excludeReg));
        }
        return sb.toString();
    }

    private static String parseMap(Map map, int lay, String excludeReg)
    {
        StringBuffer sb = new StringBuffer();
        String blankStr = getPrefixBlank(lay);
        if (CommonUtil.isEmpty(map))
        {
            return blankStr + "[empty]";
        }
        Iterator it = map.keySet().iterator();
        while (it.hasNext())
        {
            Object key = it.next();
            Object value = map.get(key);
            if (sb.length() > 0)
            {
                sb.append("\n");
            }
            sb.append(blankStr);
            sb.append("[key]" + key + " : ");

            Class type = value == null ? null : value.getClass();
            sb.append(parseItem(value, type, lay, excludeReg));
        }

        return sb.toString();
    }

    private static String parseObject(Object object, int lay, String excludeReg)
    {
        
        // 复杂类型需要循环打印字段
        Field[] fs = null;
        /*
         * if(object instanceof DataObject){ return "[sdl object]"; }else
         */if (object instanceof DataObject)
        {
            fs = ClassUtil.getFields(object.getClass());// DataObject不需要取父级字段
        }
        else
        {
            fs = ClassUtil.getAllFields(object.getClass());
        }
        if (CommonUtil.isEmpty(fs))
        {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        boolean isSdl = isSdlObject(object);
        for (Field f : fs)
        {

            String fName = f.getName();
            // LogUtil.getLogger(PrintUtil.class).debug("field name : "+fName);
            if (!CommonUtil.isEmpty(excludeReg) && Pattern.matches(excludeReg, fName))
                continue;
            if (isSdl && (Modifier.isStatic(f.getModifiers()) || !Modifier.isPrivate(f.getModifiers())))
            {
                continue;// 如果是sdl对象，private或者非static字段都不打印
            }
            Class fType = f.getType();
            try
            {
                Object fValue = BeanUtils.getFieldValue(object, fName);//ClassUtil.getPropertyValue(object, fName);
                if (sb.length() > 0)
                    sb.append("\n");
                sb.append(getPrefixBlank(lay)).append(fName).append(" : ");

                sb.append(parseItem(fValue, fType, lay, excludeReg));
            }
            catch (Exception e)
            {
//                IMSUtil.throwBusiException(e);
                new ImsLogger(LogUtil.class).error(e);
            }
        }
        return sb.toString();
    }

    private static String parseItem(Object value, Class type, int lay, String excludeReg)
    {
        StringBuffer sb = new StringBuffer();
        StringBuffer sb_type = new StringBuffer();
        if (type != null)
        {
            String strType = type.isArray() ? type.getComponentType().getName() + "[]" : type.getName();
            sb_type.append("                   ").append(strType);
        }
        if (value == null)
        {
            sb.append("[null]");
            sb.append(sb_type);
        }
        else if (!CommonUtil.isComplex(value.getClass()))
        {
            sb.append(value.toString());
            sb.append(sb_type);
        }
        else
        {
            sb.append(sb_type).append("\n").append(parse(value, lay + 1, excludeReg));
        }
        return sb.toString();
    }

    private static String getPrefixBlank(int lay)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lay; i++)
        {
            sb.append("    ");
        }
        return sb.toString();
    }

    private static Object[] parse2ObjectArray(long[] items)
    {
        if (CommonUtil.isEmpty(items))
            return null;
        Object[] result = new Object[items.length];
        for (int i = 0; i < items.length; i++)
        {
            result[i] = Long.valueOf(items[i]);
        }
        return result;
    }

    private static Object[] parse2ObjectArray(int[] items)
    {
        if (CommonUtil.isEmpty(items))
            return null;
        Object[] result = new Object[items.length];
        for (int i = 0; i < items.length; i++)
        {
            result[i] = Integer.valueOf(items[i]);
        }
        return result;
    }

    private static Object[] parse2ObjectArray(short[] items)
    {
        if (CommonUtil.isEmpty(items))
            return null;
        Object[] result = new Object[items.length];
        for (int i = 0; i < items.length; i++)
        {
            result[i] = Short.valueOf(items[i]);
        }
        return result;
    }

    private static Object[] parse2ObjectArray(float[] items)
    {
        if (CommonUtil.isEmpty(items))
            return null;
        Object[] result = new Object[items.length];
        for (int i = 0; i < items.length; i++)
        {
            result[i] = Float.valueOf(items[i]);
        }
        return result;
    }

    private static Object[] parse2ObjectArray(double[] items)
    {
        if (CommonUtil.isEmpty(items))
            return null;
        Object[] result = new Object[items.length];
        for (int i = 0; i < items.length; i++)
        {
            result[i] = Double.valueOf(items[i]);
        }
        return result;
    }

    /**
     * 判断一个对象是否sdl对象
     * 
     * @param obj
     * @return
     */
    private static boolean isSdlObject(Object obj)
    {
        return obj.getClass().getSuperclass().getPackage().getName().startsWith("com.ailk.easyframe.sdl.");
        /*
         * return obj instanceof CsdlStructObject || obj instanceof CsdlArrayList || obj instanceof IHolder;
         */
    }
}
