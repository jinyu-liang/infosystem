package com.ailk.ims.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import jef.database.DataObject;
import jef.tools.reflect.BeanWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.ailk.ims.exception.IMSException;

/**
 * @Description:公共工具类。不与任何业务相关联的公用的方法可以定义在这里
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj <br>
 * @Date 2011-7-28 wangjt：优化了isEmpty方法<br>
 * @Date 2011-8-8 wangjt：增加了int、short、long类型之间转换的方法(int2Short()等等)<br>
 * @Date 2011-8-18 yindm：增加了IntegerToShort方法<br>
 * @Date 2011-8-19 yindm：增加了StringToLong,StringToShort方法<br>
 * @Date 2011-8-22 yindm：增加了IntegerToLong方法<br>
 * @Date 2012-3-27 xieqr: 增加了getImsFiledName ，replace_2JavaName 方法<br>
 * @Date 2012-3-27 wangjt： 删除arrayIsEmpty方法<br>
 * @Date 2012-3-27 wangjt：优化isEmpty方法<br>
 * @Date 2012-3-27 wangjt：删除isNull方法<br>
 * @Date 2012-3-28 wangjt：增加isEmpty方法对StringBuffer的支持<br>
 * @Date 2012-09-21 zengxr ClassUtil2BeanWrapper
 */
public class CommonUtil
{
    public static boolean isEmpty(Collection obj)
    {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(Map obj)
    {
        return obj == null || obj.isEmpty();
    }

    public static boolean isEmpty(long[] obj)
    {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(int[] obj)
    {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(short[] obj)
    {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(double[] obj)
    {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(float[] obj)
    {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(Object[] obj)
    {
        return obj == null || obj.length == 0;
    }

    public static boolean isEmpty(String obj)
    {
        return obj == null || obj.trim().length() == 0;
    }

    public static boolean isEmpty(StringBuffer obj)
    {
        return obj == null || obj.toString().trim().length() == 0;
    }

    public static boolean isEmpty(StringBuilder obj)
    {
        return obj == null || obj.toString().trim().length() == 0;
    }

    public static boolean isNotEmpty(Collection obj)
    {
        return obj != null && !obj.isEmpty();
    }

    public static boolean isNotEmpty(Map obj)
    {
        return obj != null && !obj.isEmpty();
    }

    public static boolean isNotEmpty(long[] obj)
    {
        return obj != null && obj.length != 0;
    }

    public static boolean isNotEmpty(int[] obj)
    {
        return obj != null && obj.length != 0;
    }

    public static boolean isNotEmpty(short[] obj)
    {
        return obj != null && obj.length != 0;
    }

    public static boolean isNotEmpty(double[] obj)
    {
        return obj != null && obj.length != 0;
    }

    public static boolean isNotEmpty(float[] obj)
    {
        return obj != null && obj.length != 0;
    }

    public static boolean isNotEmpty(Object[] obj)
    {
        return obj != null && obj.length != 0;
    }

    public static boolean isNotEmpty(String obj)
    {
        return obj != null && obj.trim().length() != 0;
    }

    public static boolean isNotEmpty(StringBuffer obj)
    {
        return obj != null && obj.toString().trim().length() != 0;
    }

    public static boolean isNotEmpty(StringBuilder obj)
    {
        return obj != null && obj.toString().trim().length() != 0;
    }

    /**
     * 对于对象的判断，不推荐使用这个方法，直接用== null判断即可，<br>
     * 保留该方法，是为了兼容原来的模式，后续逐步废弃
     * 
     * @author wangjt 2012-3-27
     * @deprecated
     */
    public static boolean isEmpty(Object obj)
    {
        return obj == null;
    }

    static Logger logger = Logger.getLogger(CommonUtil.class);

    public static void copyFile(File sourceFile, File targetFile) throws IOException
    {
        if (!targetFile.exists())
        {
            targetFile.createNewFile();
        }
        // �½��ļ������������л���
        FileInputStream input = new FileInputStream(sourceFile);
        BufferedInputStream inBuff = new BufferedInputStream(input);

        // �½��ļ�����������л��ￄ1�7
        FileOutputStream output = new FileOutputStream(targetFile);
        BufferedOutputStream outBuff = new BufferedOutputStream(output);

        // ��������
        byte[] b = new byte[1024 * 5];
        int len;
        while ((len = inBuff.read(b)) != -1)
        {
            outBuff.write(b, 0, len);
        }
        // ˢ�´˻���������
        outBuff.flush();

        // �ر���
        inBuff.close();
        outBuff.close();
        output.close();
        input.close();
    }

    public static String getDate(String format)
    {
        if (format == null || format.length() == 0)
            format = "yyyy-MM-dd HH:mm:ss";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String str = formatter.format(date);
        return str;
    }

    public static boolean checkExist(String path)
    {
        File f = new File(path);
        return f.exists();
    }

    public static File createFile(String filePath) throws Exception
    {
        filePath = filePath.replace("\\", "/");
        String dir = filePath.substring(0, filePath.lastIndexOf("/"));
        File fold = new File(dir);
        File file = new File(filePath);
        if (!fold.exists())
        {
            fold.mkdirs();
        }
        if (!file.exists())
        {
            file.createNewFile();
        }
        return file;
    }

    public static List parseArray2List(Object[] arr)
    {
        if (arr == null)
            return null;
        List result = new ArrayList();
        for (Object item : arr)
        {
            result.add(item);
        }
        return result;
    }

    public static boolean isIn(Object item, Object[] arr)
    {
        if (arr == null)
            return false;
        for (int i = 0; i < arr.length; i++)
        {
            if (item.equals(arr[i]) || item == arr[i])
                return true;
        }
        return false;
    }

    public static boolean isIn(Object item, List list)
    {
        if (list == null)
            return false;
        for (int i = 0; i < list.size(); i++)
        {
            if (item.equals(list.get(i)) || item == list.get(i))
                return true;
        }
        return false;
    }

    public static boolean isIn(int item, int[] arr)
    {
        if (arr == null)
            return false;
        for (int i = 0; i < arr.length; i++)
        {
            if (item == arr[i])
                return true;
        }
        return false;
    }
    
    public static boolean isIn(Object[] arr, Object item)
    {
        if (arr == null)
            return false;
        for (int i = 0; i < arr.length; i++)
        {
            if (item.equals(arr[i]) || item == arr[i])
                return true;
        }
        return false;
    }
    

    public static boolean isIn(List list, Object item)
    {
        if (list == null)
            return false;
        for (int i = 0; i < list.size(); i++)
        {
            if (item.equals(list.get(i)) || item == list.get(i))
                return true;
        }
        return false;
    }

    public static boolean isIn(int[] arr, int item)
    {
        if (arr == null)
            return false;
        for (int i = 0; i < arr.length; i++)
        {
            if (item == arr[i])
                return true;
        }
        return false;
    }

    public static boolean parse2boolean(Object value)
    {
        if (value == null)
            return false;
        try
        {
            return Boolean.parseBoolean(value.toString());
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public static String parseThrowable2String(Throwable th)
    {
        StringBuffer sb = new StringBuffer(th.toString() + "\n\n");
        StackTraceElement[] elements = th.getStackTrace();
        for (int i = 0; i < elements.length; i++)
        {
            StackTraceElement element = elements[i];
            sb.append("        ").append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    public static String getFileName(String path)
    {
        if (path == null)
            return "";
        path = path.replace("\\", "/");
        if (path.indexOf("/") == -1)
            return path;
        if (path.endsWith("/"))
            return "*.*";
        return path.substring(path.lastIndexOf("/") + 1);
    }

    public static String[] getAllFiles(String dirPath)
    {
        File dir = new File(dirPath);
        if (!dir.exists() || !dir.isDirectory())
        {
            return null;
        }
        return dir.list();

    }

    public static void checkDirWithCreate(String dirPath) throws Exception
    {
        File dirFile = new File(dirPath);
        if (!dirFile.exists())
            dirFile.mkdirs();
    }

    public static String getLastPath(String path)
    {
        path = replacePath2R(path);
        if (path.indexOf("\\") == -1)
            return null;
        return path.substring(0, path.lastIndexOf("\\"));
    }

    public static boolean isComplex(Class clazz)
    {
        if (clazz.isArray())
            return true;
        if (Date.class.isAssignableFrom(clazz))
            return false;// Date当做简单类型处理
        return clazz.getPackage() != null && !clazz.getName().startsWith("java.lang.");
    }

    public static LinkedHashMap copyMap(Map map)
    {
        LinkedHashMap newMap = new LinkedHashMap();
        Iterator it = map.keySet().iterator();
        while (it.hasNext())
        {
            String key = (String) it.next();
            newMap.put(key, map.get(key));
        }
        return newMap;
    }

    // ��������ĸ��isUpper=true��д��false��Сд
    public static String transFirstLetter(String str, boolean isUpper)
    {
        String firstWord = str.substring(0, 1);
        String leftWords = str.substring(1);
        firstWord = (isUpper) ? firstWord.toUpperCase() : firstWord.toLowerCase();
        return firstWord + leftWords;
    }

    // ����ݿ��е����ת��java��Ӧ����ƣ�����ȥ���»��ߣ������һ����ĸ��д,����ĸ�Ƿ��д��ݲ���ￄ1�7
    public static String parse2JavaName(String name, boolean isFirstUpper)
    {
        String[] arr = name.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++)
        {
            String item = arr[i];
            String firstWord = item.substring(0, 1);
            String leftWords = item.substring(1);
            if (i == 0)
            {
                firstWord = (isFirstUpper) ? firstWord.toUpperCase() : firstWord.toLowerCase();
            }
            else
            {
                firstWord = firstWord.toUpperCase();
            }

            sb.append(firstWord).append(leftWords);

        }
        return sb.toString();
    }

    public static int[] parse2IntArray(Object[] arr) throws Exception
    {
        if (arr == null || arr.length == 0)
            return null;
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            result[i] = Integer.parseInt(arr[i].toString());
        }
        return result;
    }

    public static String[] parse2StringsArray(Object[] arr)
    {
        if (arr == null || arr.length == 0)
            return null;
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            result[i] = String.valueOf(arr[i]);
        }
        return result;
    }

    public static String replacePath2R(String orig)
    {
        if (orig.indexOf("/") == -1)
            return orig;
        return orig.replaceAll("/", "\\\\");
    }

    public static String replacePath2L(String orig)
    {
        if (orig.indexOf("\\") == -1)
            return orig;
        return orig.replaceAll("\\\\", "/");
    }

    public static boolean isNumber(String str)
    {
        return Pattern.matches("(-)?\\d+(\\.\\d+)?", str);
    }

    public static boolean isInteger(String str)
    {
        return Pattern.matches("(-)?\\d+", str);
    }

    public static String join(Object[] arr)
    {
        return join(arr, null);
    }

    public static String join(Object[] arr, String separator)
    {
        if (separator == null)
            separator = "";
        String result = ",";
        if (arr == null || arr.length == 0)
            return result;
        for (int i = 0; i < arr.length; i++)
        {
            result += separator + arr[i].toString();
        }
        return result.substring(separator.length());
    }

    public static String join(List list, String separator)
    {
        if (list == null)
            return null;
        return join(list.toArray(), separator);
    }

    public static String[] split(String str)
    {
        return split(str, ",");
    }

    public static String[] split(String str, String separator)
    {
        if (CommonUtil.isEmpty(str))
            return null;
        return str.split(separator);
    }

    public static boolean isAll(Class clazz, Object[] arr)
    {
        if (arr == null || arr.length == 0)
            return false;
        for (int i = 0; i < arr.length; i++)
        {
            if (!(clazz == arr[i].getClass()))
                return false;
        }
        return true;
    }

    /*
     * public static void debug(String name, Object object) { Class clazz = object.getClass(); try { StringBuffer sb = new
     * StringBuffer("************ debug info \"" + name + "\" : " + object); Field[] fs = ClassUtil.getFields(clazz); if
     * (CommonUtil.isEmpty(fs)) { return; } for (Field f : fs) { sb.append("\n    ").append(f.getName() + ": " +
     * ClassUtil.getFieldValue(object, f)); } LogUtil.getLogger(CommonUtil.class).debug(sb); } catch (Exception e) {
     * LogUtil.getLogger(CommonUtil.class).debug(e.getMessage()); } }
     */

    public static Long[] convertArray(long[] priarr)
    {
        if (priarr == null)
            return null;
        Long[] retarr = new Long[priarr.length];
        for (int i = 0; i < priarr.length; i++)
        {
            retarr[i] = new Long(priarr[i]);
        }
        return retarr;
    }

    public static Properties loadProperties(String cpPath) throws Exception
    {
        InputStream is = CommonUtil.class.getResourceAsStream(cpPath);
        if (is == null)
        {
            return null;
        }
        Properties prop = new Properties();
        prop.load(is);
        is.close();
        return prop;
    }

    public static String getPropertyValue(Properties prop, String key)
    {
        if (prop == null)
            return null;
        String value = prop.getProperty(key);
        if (CommonUtil.isEmpty(value))
            return null;
        try
        {
            value = new String(value.getBytes("ISO8859-1"), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            new ImsLogger(CommonUtil.class).error(e.getMessage());
        }
        return value;
    }

    public static boolean isValid(Double o)
    {
        return (o != null && o.doubleValue() > 0);
    }

    public static boolean isValid(Long o)
    {
        return (o != null && o.longValue() > 0);
    }

    public static boolean isValid(Short o)
    {
        return (o != null && o.shortValue() > 0);
    }

    public static boolean isValid(Integer o)
    {
        return (o != null && o.intValue() > 0);
    }

    public static boolean isValid(String o)
    {
        return (o != null && o.trim().length() > 0);
    }

    public static int double2Int(double v)
    {
        return (int) v;
    }

    public static long double2Long(double v)
    {
        return (long) v;
    }

    public static long double2Long(Double v)
    {
        return v == null ? (long) 0 : v.longValue();
    }

    public static int long2Int(long v)
    {
        return (int) v;
    }

    public static int long2Int(Long v)
    {
        return v == null ? (int) 0 : v.intValue();
    }

    public static double long2Double(Long v)
    {
        return v == null ? (double) 0 : (double) v;
    }

    public static long int2Long(int v)
    {
        return (long) v;
    }

    public static long int2Long(Integer v)
    {
        return v == null ? (long) 0 : v.longValue();
    }

    public static int short2Int(short v)
    {
        return (int) v;
    }

    public static int short2Int(Short v)
    {
        return v == null ? (int) 0 : v.intValue();
    }

    public static short int2Short(int v)
    {
        return (short) v;
    }

    public static short int2Short(Integer v)
    {
        return v == null ? (short) 0 : v.shortValue();
    }

    public static String short2String(Short v)
    {
        return v == null ? "" : v.toString();
    }

    public static String int2String(Integer v)
    {
        return v == null ? "" : v.toString();
    }

    public static String long2String(Long v)
    {
        return v == null ? "" : v.toString();
    }

    public static String double2String(Double v)
    {
        return v == null ? "" : v.toString();
    }

    public static Long string2Long(String v)
    {
        return (StringUtils.isBlank(v) ? null : Long.valueOf(v));
    }

    public static Double string2Double(String v)
    {
        return (StringUtils.isBlank(v) ? null : Double.valueOf(v));
    }

    public static Short string2Short(String v)
    {
        return (StringUtils.isBlank(v) ? null : Short.valueOf(v));
    }

    public static Integer string2Integer(String v)
    {
        return (StringUtils.isBlank(v) ? null : Integer.valueOf(v));
    }

    /**
     * Integer转Long
     * 
     * @param Int
     * @return
     */
    public static Long IntegerToLong(Integer Int)
    {
        return Int == null ? null : (Int.longValue());
    }

    /**
     * Integer转Short
     * 
     * @param Int
     * @return
     */
    public static Short IntegerToShort(Integer Int)
    {
        return Int == null ? null : (Int.shortValue());
    }

    /**
     * @Description: Short装Integer
     * @param sht
     * @return
     * @author: tangjl5
     * @Date: 2011-10-31
     */
    public static Integer ShortToInteger(Short sht)
    {
        return sht == null ? null : (new Integer(sht));
    }

    /**
     * String转Long
     * 
     * @param str
     * @return
     */
    public static Long StringToLong(String str)
    {
        return (str == null || str.equals("")) ? null : (new Long(str));
    }

    /**
     * String转Short
     * 
     * @param str
     * @return
     */
    public static Short StringToShort(String str)
    {
        return (str == null || str.equals("")) ? null : (new Short(str));
    }

    /*
     * public static Object[] callWebservice(String service,String method,Object[] params,Class[] returnTypes) throws AxisFault {
     * // 使用RPC方式调用WebService RPCServiceClient serviceClient = new RPCServiceClient(); Options options =
     * serviceClient.getOptions(); // 指定调用WebService的URL EndpointReference targetEPR = new EndpointReference(service);
     * options.setTo(targetEPR); // 指定要调用的getGreeting方法及WSDL文件的命名空间 QName opAddEntry = new
     * QName("http://imsintf.service.openbilling.ailk.com", method); // 调用getGreeting方法并输出该方法的返回值 return
     * serviceClient.invokeBlocking(opAddEntry, params, returnTypes); }
     */
    /**
     * @Description:解析表达式，支持占位符
     * @param exp ,如my name is {0},my age is {1}
     * @param values ,按顺序传入，values[0] = 'jack',values[1] = 20
     * @return
     */
    public static String parseExpression(String exp, Object... values)
    {
        if (!CommonUtil.isEmpty(values))
        {
            for (int i = 0; i < values.length; i++)
            {
                String replace = values[i] == null ? "" : values[i].toString();
                exp = exp.replaceAll("\\{" + i + "\\}", replace);
            }
        }
        return exp;
    }

    /**
     * @Description: 根据key=value;key=value的字符串构建map
     * @param keyVals
     * @return
     */
    public static List<String[]> buildExtParam(String keyVals)
    {
        List<String[]> rlt = new ArrayList<String[]>();
        if (!CommonUtil.isValid(keyVals))
        {
            return rlt;
        }
        String[] exs = keyVals.split(";");
        for (int i = 0; i < exs.length; i++)
        {
            if (!CommonUtil.isValid(exs[i]))
            {
                continue;
            }
            String[] keyval = exs[i].split("=");
            if (keyval.length == 2 && CommonUtil.isValid(keyval[0]) && CommonUtil.isValid(keyval[1]))
            {
                rlt.add(keyval);
            }
            else
            {
                logger.warn(exs[i] + " is invalid key=value format");
            }
        }
        return rlt;
    }

    /**
     * @Description: 两个对象之间相同字段的赋值
     * @param base ,基类对象，以该对象中的属性为基础来遍历
     * @param newObj ，需要赋值的空对象
     * @throws IMSException 2012-09-21 zengxr ClassUtil2BeanWrapper
     */
    public static void assign(Object base, Object newObj) throws IMSException
    {
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(base.getClass());
            BeanWrapper wrapper = BeanWrapper.wrap(newObj);
            for (java.lang.reflect.Field f : fs)
            {
                Object value = ClassUtil.getPropertyValue(base, f);
                wrapper.setPropertyValue(f.getName(), value);
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    /**
     * @Description: 判断参数是否为空，若为空则返回0
     * @param value
     * @return
     */
    public static long Long2long(Long value)
    {
        if (value == null)
        {
            return 0;
        }
        else
        {
            return value;
        }
    }

    /**
     * 根据传入信息列表获取Long型字段列表 wukl 2012-1-9
     * 
     * @param objs
     * @param fildName
     * @return
     */
    public static List<Long> getFieldValueList(List<? extends DataObject> objs, String fildName)
    {
        if (CommonUtil.isEmpty(objs))
            return null;

        List<Long> values = new ArrayList<Long>();
        try
        {
            for (DataObject obj : objs)
            {
                Field[] fields = ClassUtil.getFields(obj.getClass());

                for (Field field : fields)
                {
                    if (field.getName().equalsIgnoreCase(fildName))
                    {
                        Object value = ClassUtil.getPropertyValue(obj, field.getName());
                        if (CommonUtil.isNumber(value.toString()))
                        {
                            values.add(Long.parseLong(value.toString()));
                        }
                    }
                }

            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            throw new IMSException(e);
        }
        return values;
    }

    /**
     * 获取两个列表的交集 wukl 2012-1-9
     * 
     * @param oldList
     * @param newList
     * @return
     */
    public static List<Long> getAcctIdListBetween2List(List<Long> oldList, List<Long> newList)
    {
        List<Long> result = new ArrayList<Long>();
        if (CommonUtil.isEmpty(oldList) && CommonUtil.isEmpty(newList))
        {
            return null;
        }

        if (CommonUtil.isEmpty(oldList) && !CommonUtil.isEmpty(newList))
        {
            return newList;
        }

        if (!CommonUtil.isEmpty(oldList) && CommonUtil.isEmpty(newList))
        {
            return oldList;
        }

        if (!CommonUtil.isEmpty(oldList) && !CommonUtil.isEmpty(newList))
        {
            for (Long obj : oldList)
            {
                if (newList.contains(obj))
                {
                    result.add(obj);
                }
            }
        }

        return result;
    }
    
    /**
     * @Description 将fireEventType转为String，只支持5位,不足的前面补0
     * @Author wangdw5
     * @return
     */
    public static String string2String5Bit(String str)
    {
        if (str.length() == 1)
        {
            str = "0000" + str;
        }
        else if (str.length() == 2)
        {
            str = "000" + str;
        }
        else if (str.length() == 3)
        {
            str = "00" + str;
        }
        else if (str.length() == 4)
        {
            str = "0" + str;
        }
        return str;
    }

    /**
     * @Description 将数字转为String，只支持3位,不足的前面补0
     * @Author lijingcheng
     * @param number
     * @return
     */
    public static String integer2String3Bit(Integer number)
    {
        String str = String.valueOf(number);
        if (str.length() == 1)
        {
            str = "00" + str;
        }
        else if (str.length() == 2)
        {
            str = "0" + str;
        }
        return str;
    }

    /**
     * @Description 将数字转为String，只支持2位,不足的前面补0
     * @Author wangyh3
     * @param number
     * @return
     */
    public static String short2String2Bit(Short number)
    {
        if (number == null)
            return null;
        String str = String.valueOf(number);
        if (str.length() == 1)
        {
            str = "0" + str;
        }
        return str;
    }

    /**
     * 替换字符串中的"_",且单词手字母大写
     * 
     * @author xieqr
     * @return
     */
    public static String replace_2JavaName(String s)
    {
        StringBuilder sb = new StringBuilder(s);
        int flag = -1;
        while ((flag = sb.indexOf("_")) != -1)
        {
            sb.replace(flag, flag + 2, String.valueOf(sb.charAt(flag + 1)).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将fieldName 转换成 field_name 这种格式的字符串 xieqr 2012-3-27
     * 
     * @param javaFieldName
     * @return
     */
    public static String getImsFiledName(String javaFieldName)
    {
        // 将去后的字段名中遇到大写字母就在字母前面加“_”
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < javaFieldName.length(); i++)
        {
            char c = javaFieldName.charAt(i);
            if (Character.isUpperCase(c))
            {
                sb.append('_');
            }
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 多个参数组成字符串
     */
    public static String concat(Object... args)
    {
        int n = 0;
        for (Object s : args)
        {
            if (s == null)
            {
                continue;
            }
            n += s.toString().length();
        }
        StringBuilder sb = new StringBuilder(n);
        for (Object s : args)
        {
            if (s == null)
            {
                continue;
            }
            sb.append(s);
        }
        return sb.toString();
    }
    
    
    /*
     * 截取字符串后num位
     */
    public static String  behindSubString(String str,int num){
        
        if(str.length()>num){
           str = str.substring(str.length()-num, str.length());
        }
        
        return str;
    }
}
