 package com.ailk.ims.util;                                                                                                                                                                                                                                                                       

import jef.jre5support.script.LuaEngine;
                                                                                                                                                                                                                                                                                              
/**@Description: 线程工具类
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wukl                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
  */
public class ThreadUtil {

	// 获取当前线程的局部变量实体
	private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
	//当前线程存放Lua引擎的线程变量
	private static ThreadLocal<LuaEngine> luaThreadLocal = new ThreadLocal<LuaEngine>();

	/**
	 * @Description: 设置当前线程所对应的线程局部变量
	 * @param obj
	 */
	public static void setThreadLocal(Object obj) {
		threadLocal.set(obj);
	}

	/**
	 * @Description: 获取当前线程所对应的线程局部变量
	 * @return
	 */
	public static Object getThreadLocal() {
		return threadLocal.get();
	}

	/**
	 * @Description: 删除当前线程局部变量的值
	 * 
	 */
	public static void removeThreadLocal() {
		threadLocal.remove();
	}
	
	
	public static void setLuaThreadLocal(LuaEngine luaEngine){
	    luaThreadLocal.set(luaEngine);
	}
	
	public static LuaEngine getLuaThreadLocal(){
	    return luaThreadLocal.get();
	}
	
	public void removeLuaThreadLocal(){
	    luaThreadLocal.remove();
	}

}
