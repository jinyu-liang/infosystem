package com.ailk.ims.util;                                                                                                                                                                                                                                                                       

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;
import javassist.Modifier;
import jef.database.DataObject;
import com.ailk.ims.define.SysCodeDefine;
/**
 * @Description: 打印工具类，可以打印出一个对象的所有信息，建议调试时使用，在生产环境中避免使用，如果对象较大会消耗内存以及降低效率。              
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
  * @Date 2012-5-18 wuyujie 优化对sdl对象的打印
  * @Date 2012-6-14 wuyujie 增加info方法，info级别
  * @Date 2012-09-26 zengxr if no OBJECT_DUMP parameter, not print all data object
 */
public class PrintUtil {
	
	//打印sdl对象时，需要传入的正则表达式
	public static final String sdlRegex = "^(?!.*m_.+).*$";
	
	/*public static void main(String[] args){
		SEmailIntf oper = new SEmailIntf();
		oper.set_email("ddddd");
		print("dddddddddd",oper,"^(?!.*m_.+).*$");

	}*/

	/**
	 *用来打印sdl对象
	 * wukl 2012-3-31
	 * @param name
	 * @param object
	 * @param excludeReg  类中定义的sdlRegex变量
	 */
	public static void print(String name,Object object,String excludeReg){
		if(!LogUtil.getLogger(PrintUtil.class).isDebugEnabled()){
			return;//非debug模式就不打印
		}
		//
		if(! SysUtil.getBoolean(SysCodeDefine.busi.IS_DUMP, false)){
			return;//NO
		}
		long start = System.currentTimeMillis();
		try{
			StringBuffer sb = new StringBuffer("************ print \""+name+"\" : ");
			if(object == null){
				sb.append("null");
			}else{
				sb.append(object.getClass().getName()).append("\n");
				sb.append(parse(object,1,excludeReg));
			}
			sb.append(". \n****** print cost time : ").append(System.currentTimeMillis() - start).append("ms");
			LogUtil.getLogger(ClassUtil.class).debug(sb);
			
		}catch(Exception e){
			LogUtil.getLogger(ClassUtil.class).debug(e.getMessage());
		}
	}
	public static void info(String name,Object object){
		if(!LogUtil.getLogger(PrintUtil.class).isInfoEnabled()){
			return;//非debug模式就不打印
		}
		if(! SysUtil.getBoolean(SysCodeDefine.busi.IS_DUMP, false)){
			return;//NO
		}
		long start = System.currentTimeMillis();
		try{
			StringBuffer sb = new StringBuffer("************ print \""+name+"\" : ");
			if(object == null){
				sb.append("null");
			}else{
				sb.append(object.getClass().getName()).append("\n");
				sb.append(parse(object,1,null));
			}
			sb.append(". \n****** print cost time : ").append(System.currentTimeMillis() - start).append("ms");
			LogUtil.getLogger(ClassUtil.class).info(sb);
			
		}catch(Exception e){
			LogUtil.getLogger(ClassUtil.class).info(e.getMessage());
		}
	}
	
	/**
	 *  用来打印java对象，只建议在调式的时候用，不建议放在实际应用中
	 * wukl 2012-3-31
	 * @param name
	 * @param object
	 */
	public static void print(String name,Object object){
		print(name,object,null);
	}
	private static String parse(Object object,int lay,String excludeReg){
		Class clazz = object.getClass();
		if(!CommonUtil.isComplex(clazz)){
			return object.toString();//对象本身是简单类型，则直接打印
		}
		if(object instanceof Collection){
			return parseCollection((Collection)object,lay,excludeReg);
		}else if(object instanceof Map){
			return parseMap((Map)object,lay,excludeReg);
		}else if(clazz.isArray()){
			Object[] arr = null;
			Class cmpType = clazz.getComponentType();
			if(cmpType == long.class){
				arr = parse2ObjectArray((long[])object);
			}else if(cmpType == int.class){
				arr = parse2ObjectArray((int[])object);
			}else if(cmpType == short.class){
				arr = parse2ObjectArray((short[])object);
			}else if(cmpType == double.class){
				arr = parse2ObjectArray((double[])object);
			}else if(cmpType == float.class){
				arr = parse2ObjectArray((float[])object);
			}else{
				arr = (Object[])object;
			}
			return parseArray(arr,lay,excludeReg);
			
		}else{
			return parseObject(object,lay,excludeReg);
		}
		
	}
	private static String parseCollection(Collection coll,int lay,String excludeReg){
		StringBuffer sb = new StringBuffer();
		if(coll.isEmpty()){
			sb.append("[empty]");
		}else{
			Object[] arr = coll.toArray(new Object[coll.size()]);
			sb.append(parseArray(arr,lay,excludeReg));
		}
		return sb.toString();
	}
	private static String parseArray(Object[] arr,int lay,String excludeReg){
		StringBuffer sb = new StringBuffer();
		String blankStr = getPrefixBlank(lay);
		if(CommonUtil.isEmpty(arr)){
			return blankStr+"[empty]";
		}
		for(int i=0;i< arr.length;i++){
			Object item = arr[i];
			if(sb.length() > 0){
				sb.append("\n");
			}
			sb.append(blankStr);
			sb.append("["+i+"] : ");
			Class type = (item == null) ? null : item.getClass();
			sb.append(parseItem(item,type,lay,excludeReg));
		}
		return sb.toString();
	}
	private static String parseMap(Map map,int lay,String excludeReg){
		StringBuffer sb = new StringBuffer();
		String blankStr = getPrefixBlank(lay);
		if(CommonUtil.isEmpty(map)){
			return blankStr+"[empty]";
		}
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			Object value = map.get(key);
			if(sb.length() > 0){
				sb.append("\n");
			}
			sb.append(blankStr);
			sb.append("[key]"+key+" : ");
			
			Class type = value == null ? null : value.getClass();
			sb.append(parseItem(value,type,lay,excludeReg));
		}
		
		return sb.toString();
	}
	private static String parseObject(Object object,int lay,String excludeReg){
		//复杂类型需要循环打印字段
		Field[] fs = null;
		/*if(object instanceof DataObject){
			return "[sdl object]";
		}else */if(object instanceof DataObject){
			fs = ClassUtil.getFields(object.getClass());//DataObject不需要取父级字段
		}else{
			fs = ClassUtil.getAllFields(object.getClass());
		}
		if(CommonUtil.isEmpty(fs)){
		    LogUtil.getLogger(PrintUtil.class).debug("field is empty");
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		boolean isSdl = isSdlObject(object);
		for(Field f : fs){
			
			String fName = f.getName();
			//LogUtil.getLogger(PrintUtil.class).debug("field name : "+fName);
			if(!CommonUtil.isEmpty(excludeReg) && Pattern.matches(excludeReg, fName))
				continue;
			if(isSdl && (!fName.startsWith("m_") || f.getModifiers() != Modifier.PRIVATE)){
				continue;//如果是sdl对象，只打印private且"m_"开头的字段
			}
			Class fType = f.getType();
			try {
				Object fValue = ClassUtil.getPropertyValue(object, fName);
				if(sb.length() > 0)
					sb.append("\n");
				sb.append(getPrefixBlank(lay))
				  .append(fName)
				  .append(" : ");
				
				sb.append(parseItem(fValue,fType,lay,excludeReg));
			} catch (Exception e) {
				LogUtil.getLogger(PrintUtil.class).error(e,e);
			}
		}
		return sb.toString();
	}
	private static String parseItem(Object value,Class type,int lay,String excludeReg){
		StringBuffer sb = new StringBuffer();
		StringBuffer sb_type = new StringBuffer();
		if(type != null){
			String strType = type.isArray() ? type.getComponentType().getName()+"[]" : type.getName();
			sb_type.append("                   ").append(strType);
		}
		if(value == null){
			sb.append("[null]");
			sb.append(sb_type);
		}else if(!CommonUtil.isComplex(value.getClass())){
			sb.append(value.toString());
			sb.append(sb_type);
		}else{
			sb.append(sb_type).append("\n").append(parse(value,lay+1,excludeReg));
		}
		return sb.toString();
	}
	
	private static String getPrefixBlank(int lay){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i< lay;i++){
			sb.append("    ");
		}
		return sb.toString();
	}
	
	
	private static Object[] parse2ObjectArray(long[] items){
		if(CommonUtil.isEmpty(items))
			return null;
		Object[] result = new Object[items.length];
		for(int i=0;i< items.length;i++){
			result[i] = Long.valueOf(items[i]);
		}
		return result;
	}
	private static Object[] parse2ObjectArray(int[] items){
		if(CommonUtil.isEmpty(items))
			return null;
		Object[] result = new Object[items.length];
		for(int i=0;i< items.length;i++){
			result[i] = Integer.valueOf(items[i]);
		}
		return result;
	}
	private static Object[] parse2ObjectArray(short[] items){
		if(CommonUtil.isEmpty(items))
			return null;
		Object[] result = new Object[items.length];
		for(int i=0;i< items.length;i++){
			result[i] = Short.valueOf(items[i]);
		}
		return result;
	}
	private static Object[] parse2ObjectArray(float[] items){
		if(CommonUtil.isEmpty(items))
			return null;
		Object[] result = new Object[items.length];
		for(int i=0;i< items.length;i++){
			result[i] = Float.valueOf(items[i]);
		}
		return result;
	}
	private static Object[] parse2ObjectArray(double[] items){
		if(CommonUtil.isEmpty(items))
			return null;
		Object[] result = new Object[items.length];
		for(int i=0;i< items.length;i++){
			result[i] = Double.valueOf(items[i]);
		}
		return result;
	}
	/**
	 * 判断一个对象是否sdl对象
	 * @param obj
	 * @return
	 */
	private static boolean isSdlObject(Object obj){
		return obj.getClass().getPackage().getName().startsWith("com.ailk.easyframe.sdl.");
		/*return obj instanceof CsdlStructObject 
			|| obj instanceof CsdlArrayList
			|| obj instanceof IHolder;*/
	}
}
