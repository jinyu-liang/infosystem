package com.ailk.ims.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.FileUtil;
import com.ailk.ims.util.IMSUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
/**
 * @Description: XStream的包装类
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class XStreamHolder {
	private XStream xstream;
	
	private String rootName;
	
	
	public static void main(String[] args){
		BaseNode root = new BaseNode("services");
		
		root.addAttribute("name", "wuyujie");
		root.addAttribute("sex", "F");
		
		BaseNode child = new BaseNode("service");
		child.addAttribute("name", "wuyujie1");
		child.addAttribute("sex", "FF");
		
		root.addChild(child);
		root.addChild(new BaseNode("service"));
		
		String aa = new XStreamHolder("services_11").parse2Xml(root);
		System.out.println(aa);
	}
	
	/**
	 * @deprecated
	  * <p>Title: </p>                                                                                                                                                                                                                                                                
	  * <p>Description:</p>
	 */
	public XStreamHolder(){
		xstream = new XStream();
	}
	
	public XStreamHolder(String rootName){
		xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("-_", "_")));
		xstream.registerConverter(new IMSXmlConverter());
		this.rootName = rootName;
		this.setClassAlias(BaseNode.class, rootName);
	}
	
	/**
	 * @Description: 设置类别名
	  * @param clazz,需要设置的类
	  * @param alias，需要设置类对应的别名
	 */
	public void setClassAlias(Class clazz,String alias){
		xstream.alias(alias, clazz);
	}
	/**
	 * @Description:省略字段
	  * @param clazz,需要设置省略字段的类
	  * @param fieldName，需要省略的字段名
	 */
	public void setOmit(Class clazz,String fieldName){
		xstream.omitField(clazz, fieldName);
	}
	/**
	 * @Description: 设置类中的字段作为属性
	  * @param clazz,需要设置属性的类
	  * @param field,需要设置作为属性的字段名
	 */
	public void setAttribute(Class clazz,String fieldName){
		xstream.useAttributeFor(clazz, fieldName);
	}
	/**
	 * @Description:设置一个class中的所有字段都作为属性
	  * @param clazz
	 */
	public void setAttributes(Class clazz){
		Field[] fields = clazz.getDeclaredFields();
		if(fields == null || fields.length == 0)
			return;
		for(int i=0;i< fields.length;i++){
			Field field = fields[i];
			setAttribute(clazz, field.getName());
		}
	}
	
	/**
	 * @Description: 把一个Collection类型的字段显式声明
	  * @param clazz
	  * @param fieldName
	 */
	public void setImplicitCollection(Class clazz,String fieldName) throws IMSException{
		xstream.addImplicitCollection(clazz, fieldName);
	}
	
	/**
	 * @Description: 注册转换器
	  * @author : wuyj
	  * @date : 2011-10-1  
	  * @param converter
	  * @throws IMSException
	 */
	public void registerConverter(Converter converter) throws IMSException{
		xstream.registerConverter(converter);
	}
	
	//########################具体操作方法
	public String parse2Xml(Object obj) throws IMSException{
		return xstream.toXML(obj);
	}
	public Object parseFromXml(String xml) throws IMSException{
		return xstream.fromXML(xml);
	}
	

	public Object parseFromFile(String filePath) throws IMSException{
		try{
			InputStream is = FileUtil.getInputStream(filePath);
			return parseFromStream(is);
		}catch(Exception e){
			throw new IMSException(e);
		}
	}
	public Object parseFromResource(String filePath) throws IMSException{
		try{
			InputStream is = XStreamHolder.class.getResourceAsStream(filePath);
			return parseFromStream(is);
		}catch(Exception e){
			throw new IMSException(e);
		}
	}
	public Object parseFromStream(InputStream is) throws IMSException{
		InputStreamReader reader = null;
		try{
			if(is.available() == 0){
				return null;
			}
			reader = new InputStreamReader(is, Charset.forName("UTF-8"));
			return xstream.fromXML(reader);
		}catch(Exception e){
			throw IMSUtil.throwBusiException(e);
		}finally{
			try{
				if(reader != null)
					reader.close();
				if(is != null)
					is.close();
			}catch(Exception e){
				throw new IMSException(e);
			}
		}
	}
	
	
	public void parse2File(Object obj,String filePath) throws IMSException{
		OutputStream os = null;
		OutputStreamWriter writer = null;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				file = FileUtil.createFile(filePath);
			}
			os = new FileOutputStream(file);
			writer = new OutputStreamWriter(os, Charset.forName("UTF-8"));
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
			xstream.toXML(obj, writer);
		} catch (Exception e) {
			throw IMSUtil.throwBusiException(e);
		}finally{
			try{
				if(writer != null){
					writer.flush();
					writer.close();
				}
				if(os != null)
					os.close();
			}catch(Exception e){
				throw new IMSException(e);
			}
		}
	}

	public String getRootName() {
		return rootName;
	}
}
