package com.ailk.ims.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jef.tools.reflect.BeanUtils;
import jef.tools.reflect.BeanWrapper;
import com.ailk.ims.exception.IMSException;

/**
 * @Description: Class操作工具类，跟Class相关的方法可以定义在这里
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2
 * @Date 2012-5-19 优化instance(String className),instance(Class clazz)
 * @Date 2012-6-12 wangjt: getFieldValue 方法改为抛出IMSException
 */
public class ClassUtil
{
    private static ImsLogger logger = new ImsLogger(ClassUtil.class);

    public static Object instance(String className)
    {
        try
        {
            if (className == null || className.length() == 0)
            {
                return null;
            }
            Class clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            return obj;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static Object instance(Class clazz)
    {
        try
        {
            Object obj = clazz.newInstance();
            return obj;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @Description 避免使用反射，用getPropertyValue方法代替
     * @author zenglu
     * @Date 2012-10-08
     */
    public static Object getFieldValue(Object obj, String fieldName)
    {
        Class clazz = obj.getClass();
        Field field = getField(clazz, fieldName);
        if (field == null)
        {
            throw new IMSException("Can not find field : \"" + fieldName + "\" during " + obj.getClass().getName());
        }
        return getPropertyValue(obj, field);
    }

    /**
     * @Description 避免使用反射，用getPropertyValue方法代替
     * @author zenglu
     * @Date 2012-10-08
     */
    public static Object getFieldValue(Object obj, Field field)
    {
        return getPropertyValue(obj, field.getName());
    }

    public static Object getPropertyValue(Object obj, String fieldName)
    {
        BeanWrapper bw = BeanWrapper.wrap(obj);
        return bw.getPropertyValue(fieldName);
    }

    public static Object getPropertyValue(Object obj, Field field)
    {
        BeanWrapper bw = BeanWrapper.wrap(obj);
        return bw.getPropertyValue(field.getName());
    }

    public static Object getBeanFieldValue(Object obj, String fieldName) throws Exception
    {
        Class clazz = obj.getClass();
        Field field = getField(clazz, fieldName);
        if (field == null)
        {
            throw new Exception("Can not find field : \"" + fieldName + "\" during " + obj.getClass().getName());
        }
        return getBeanFieldValue(obj, field);
    }

    public static Object getBeanFieldValue(Object obj, Field field) throws Exception
    {

        Class clazz = obj.getClass();
        if (field == null)
            return null;
        Method method = getBeanGetMethod(clazz, field);
        if (method == null)
            return null;
        return method.invoke(obj);
    }

    public static Object invokeMethod(Object obj, String methodName, Class[] paramTypes, Object[] params) throws Exception
    {
        Class clazz = obj.getClass();
        Method m = clazz.getMethod(methodName, paramTypes);
        if (m == null)
        {
            throw new Exception("can not find method \"" + methodName + "\" in Class \"" + clazz.getName() + "\"");
        }
        return m.invoke(obj, params);
    }

    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) throws Exception
    {
        if (obj == null)
            return;
        Class clazz = obj.getClass();
        Field field = getField(clazz, fieldName);
        if (field == null)
            throw new Exception("Can not find field : \"" + fieldName + "\" during " + obj.getClass().getName());
        setFieldValue(obj, field, fieldValue);
    }

    public static void setFieldValue(Object obj, Field field, Object fieldValue) throws Exception
    {
        if (obj == null)
            return;
        try
        {
            Class clazz = obj.getClass();
            Class fieldClazz = field.getType();
            Method method = clazz.getMethod("set" + CommonUtil.transFirstLetter(field.getName(), true), fieldClazz);
            method.invoke(obj, parseTypeValue(fieldValue, fieldClazz.getSimpleName()));
        }
        catch (Exception e)
        {
            throw new Exception("fail to set value for field \"" + field.getName() + "\":" + e.getMessage(), e);
        }
    }

    public static Field getField(Class clazz, String fieldName)
    {
        Class current = clazz;
        while (current != null)
        {
            try
            {
                Field field = current.getDeclaredField(fieldName);
                return field;
            }
            catch (Exception e)
            {
            	
            }
            current = current.getSuperclass();
        }
        return null;
    }

    public static Method getBeanGetMethod(Class clazz, Field field)
    {
        try
        {
            String prefix = field.getType().equals(boolean.class) ? "is" : "get";
            String fieldName = field.getName();
            Method method = clazz.getMethod(prefix + CommonUtil.transFirstLetter(fieldName, true));
            return method;
        }
        catch (Exception e)
        {
        	logger.error(e,e);
        }
        return null;
    }

    public static Object parseTypeValue(Object value, String type)
    {
        if (value == null)
            return null;
        if ("String".equals(type))
        {
            return value.toString();
        }
        if ("int".equals(type))
        {
            return Integer.parseInt(value.toString());
        }
        if ("Integer".equals(type))
        {
            return new Integer(value.toString());
        }
        if ("long".equals(type))
        {
            return Long.parseLong(value.toString());
        }
        if ("Long".equals(type))
        {
            return new Long(value.toString());
        }
        if ("float".equals(type))
        {
            return Float.parseFloat(value.toString());
        }
        if ("Float".equals(type))
        {
            return new Float(value.toString());
        }
        if ("double".equals(type))
        {
            return Double.parseDouble(value.toString());
        }
        if ("Double".equals(type))
        {
            return new Double(value.toString());
        }
        if ("boolean".equals(type))
        {
            return Boolean.parseBoolean(value.toString());
        }
        if ("Boolean".equals(type))
        {
            return new Boolean(value.toString());
        }
        if ("Date".equals(type) && !(value instanceof Date))
        {
            return IMSUtil.formatDate(value.toString());
        }
        return value;
    }

    public static Field[] getAllFields(Class clazz)
    {
        Class temp = clazz;
        Map fields = new LinkedHashMap();
        while (temp != null)
        {
            Field[] fs = getFields(temp);
            for (int i = 0; i < fs.length; i++)
            {
                Field f = fs[i];
                if (f.getName().startsWith("this$"))
                    continue;
                if (fields.get(f.getName()) == null)
                    fields.put(f.getName(), f);
            }
            temp = temp.getSuperclass();
        }
        return (Field[]) fields.values().toArray(new Field[fields.size()]);
    }

    public static Field[] getFields(Class clazz)
    {
        clazz.getFields();
        return clazz.getDeclaredFields();
    }

    public static ClassLoader ceateClassLoader(String[] classpaths, ClassLoader parentLoader)
    {
        List list = new ArrayList();
        for (int i = 0; i < classpaths.length; i++)
        {
            try
            {
                list.add(new URL(computeForURLClassLoader(classpaths[i])));
            }
            catch (MalformedURLException e)
            {
                logger.error(e, e);
            }
        }
        return new URLClassLoader((URL[]) list.toArray(new URL[list.size()]), ClassUtil.class.getClassLoader());
    }

    /**
     * because of URLClassLoader assumes any URL not ends with '/' to refer to a jar file. so we need to append '/' to classpath
     * if it is a folder but not ends with '/'.
     * 
     * @param classpath
     * @return
     */
    private static String computeForURLClassLoader(String classpath)
    {
        if (!classpath.endsWith("/"))
        {
            File file = new File(classpath);
            if (file.exists() && file.isDirectory())
            {
                classpath = classpath.concat("/");
            }
        }
        return classpath;
    }

    /**
     * @Description: 以base为基础拷贝两个结构
     * @param base
     * @param newObj base对象里有的字段才会拷贝，拷贝出错不报异常
     */
    public static void copy(Object base, Object newObj)
    {
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(newObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if ((!Modifier.isStatic(f.getModifiers())) && (!Modifier.isFinal(f.getModifiers())))
                {
                    try
                    {
                        BeanUtils.setFieldValue(newObj, f.getName(), ClassUtil.getPropertyValue(base, f.getName()));
                    }
                    catch (Exception ex)
                    {
                    	logger.error(ex,ex);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

}
