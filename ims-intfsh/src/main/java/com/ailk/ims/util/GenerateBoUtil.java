//package com.ailk.ims.util;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//import jef.tools.reflect.BeanUtils;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.XMLWriter;
//import com.ailk.easyframe.web.common.dal.IComplexEntity;
//import com.ailk.openbilling.persistence.outInterface.entity.BillExt;
//import com.ailk.openbilling.persistence.outInterface.entity.BillExtOut;
//import com.ailk.openbilling.persistence.outInterface.entity.PocketExtIn;
//
///**
// * @Description: 根据实体类生成实体的bo.xml 文件
// * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
// * @Author xieqr
// * @Date 2012-3-24
// */
//public class GenerateBoUtil
//{
//    private static final String PACKAGE_PREFIX = "imscnsh.";
//
//    public static void main(String args[])
//    {
//        List<Object> objs = new ArrayList<Object>();
//        BillExtOut billExtOut = new BillExtOut();
//        BillExt billExt = new BillExt();
//        List<BillExt> list = new ArrayList<BillExt>();
//        list.add(billExt);
//        billExtOut.setRealTimeBillList(list);
//        billExtOut.setHistoryBillList(list);
//        PocketExtIn pocketExtIn = new PocketExtIn();
//
//        objs.add(billExtOut);
//        objs.add(pocketExtIn);
//
//        for (Object obj : objs)
//        {
//            writeFile(obj, "d://");
//        }
//
//        /*
//         * String str="aaaEdkddE3k"; String ret = CommonUtil.getImsFiledName(str); System.out.println(ret);
//         * System.out.println(CommonUtil.replace_2JavaName(ret));
//         */
//
//    }
//
//    public static void writeFile(Object obj, String filePrex)
//    {
//
//        Class cz = obj.getClass();
//        String simpleName = cz.getSimpleName();
//        String fileName = filePrex + simpleName + ".bo.xml";
//        String str = gerenateXML(obj);
//        StringBuffer bf = new StringBuffer();
//        bf.append("<entity name='");
//        bf.append(simpleName);
//        bf.append("' transient='true'>");
//        bf.append("<fields>");
//        if (!CommonUtil.isEmpty(str))
//        {
//            bf.append(str);
//        }
//        bf.append("</fields>");
//        bf.append("</entity>");
//        string2XmlFile(bf.toString(), fileName);
//    }
//
//    public static Document String2Document(String s)
//    {
//        Document doc = null;
//        try
//        {
//            doc = DocumentHelper.parseText(s);
//        }
//        catch (DocumentException e)
//        {
//            e.printStackTrace();
//        }
//        return doc;
//    }
//
//    public static boolean doc2XmlFile(Document doc, String fileName)
//    {
//        boolean flag = true;
//        XMLWriter writer = null;
//        try
//        {
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            format.setEncoding("UTF-8");
//            writer = new XMLWriter(new FileWriter(new File(fileName)), format);
//
//            writer.write(doc);
//        }
//        catch (Exception ex)
//        {
//            flag = false;
//            ex.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//                writer.close();
//            }
//            catch (IOException e)
//            {
//                flag = false;
//                e.printStackTrace();
//            }
//        }
//        return flag;
//    }
//
//    @SuppressWarnings("deprecation")
//    public static boolean string2XmlFile(String str, String fileName)
//    {
//        boolean flag = true;
//        Document doc;
//        try
//        {
//            doc = DocumentHelper.parseText(str);
//            Element ele = doc.getRootElement();
//            String qName = "xsi:noNamespaceSchemaLocation";
//            ele.setAttributeValue(qName, "http://10.10.10.141/easyframe/schema/entity.xsd");
//            flag = doc2XmlFile(doc, fileName);
//        }
//        catch (DocumentException e)
//        {
//            flag = false;
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    public static String gerenateXML(Object imsObj)
//    {
//        Class clz = imsObj.getClass();
//        Field[] fields = clz.getDeclaredFields();
//        StringBuffer bf = new StringBuffer();
//        for (Field field : fields)
//        {
//            String imsFieldName = field.getName();
//            String type = field.getType().getName();
//            Object imsFieldValue = BeanUtils.getFieldValue(imsObj, imsFieldName);
//            if (field.getType().getName().equals("java.util.List"))
//            {
//                // list<entity> 处理
//                List imsList = (List) imsFieldValue;
//                String imsClassName = imsList.get(0).getClass().getName();
//                String className = imsClassName.substring(imsClassName.lastIndexOf(".") + 1);
//                bf.append("<complex-field name='");
//                bf.append(imsFieldName);
//                bf.append("' type='entity' ref='");
//                bf.append(PACKAGE_PREFIX);
//                bf.append(className);
//                bf.append("' collection='list' primitive='false'/>");
//
//            }
//            else if (IComplexEntity.class.isAssignableFrom(field.getType()))
//            {
//                // 单实体处理
//                String imsClassName = field.getType().getName();
//                String className = imsClassName.substring(imsClassName.lastIndexOf(".") + 1);
//                bf.append("<complex-field name='");
//                bf.append(imsFieldName);
//                bf.append("' type='entity' ref='");
//                bf.append(PACKAGE_PREFIX);
//                bf.append(className);
//                bf.append("' collection='none' primitive='false'/>");
//
//            }
//            else if (field.getType().getName().startsWith("[L"))
//            { // array处理
//                String imsClassName = field.getType().getName();
//                String classFullName = imsClassName.substring(2);
//                classFullName = classFullName.substring(0, classFullName.length() - 1);
//                try
//                {
//                    Class cz = Class.forName(classFullName);
//                    if (IComplexEntity.class.isAssignableFrom(cz))
//                    {
//                        String className = classFullName.substring(classFullName.lastIndexOf(".") + 1);
//                        bf.append("<complex-field name='");
//                        bf.append(imsFieldName);
//                        bf.append("' type='entity' ref='");
//                        bf.append(PACKAGE_PREFIX);
//                        bf.append(className);
//                        bf.append("' collection='array' primitive='false'/>");
//                    }
//                    else
//                    {
//                        bf.append("<complex-field name='");
//                        bf.append(imsFieldName);
//                        bf.append("' type='");
//                        bf.append(getDateType(classFullName));
//                        bf.append("' collection='array' primitive='false'/>");
//                    }
//                }
//                catch (ClassNotFoundException e)
//                {
//                    e.printStackTrace();
//                }
//            }
//            else
//            {
//                bf.append("<field name='");
//                bf.append(CommonUtil.getImsFiledName(imsFieldName));
//                bf.append("' type='");
//                bf.append(getDateType(type));
//                bf.append("' isPk='false' primitive='Auto' null-able='true'/>");
//            }
//        }
//        return bf.toString();
//    }
//
//    private static String getDateType(String javaType)
//    {
//        String type = "";
//        if (javaType.equals("java.lang.String"))
//        {
//            type = "String";
//        }
//        else if (javaType.equals("java.lang.Integer"))
//        {
//            type = "Integer";
//        }
//        else if (javaType.equals("java.lang.Long"))
//        {
//            type = "Long";
//        }
//        else if (javaType.equals("java.lang.Double"))
//        {
//            type = "Double";
//        }
//        else if (javaType.equals("java.lang.Float"))
//        {
//            type = "Float";
//        }
//        else if (javaType.equals("java.util.Date"))
//        {
//            type = "Date";
//        }
//        else if (javaType.equals("java.lang.Boolean"))
//        {
//            type = "Boolean";
//        }
//        else if (javaType.equals("java.sql.Time"))
//        {
//            type = "SqlTime";
//        }
//        else if (javaType.equals("java.sql.Timestamp"))
//        {
//            type = "SqlTimestamp";
//        }
//        else
//        {
//            type = javaType;
//        }
//        return type;
//    }
//}
