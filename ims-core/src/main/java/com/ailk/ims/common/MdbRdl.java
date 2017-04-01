package com.ailk.ims.common;
/**
 * @Description: 直接使用SAL访问MDB的信息载体类       
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2012-6-13
 */
public class MdbRdl {
	private String kv;//kv字符串，比如kv:/user_info/syncup/syncCustomer
	private Class<?> respClass;//操作的返回类型
	protected Object reqObj;//操作的请求对象数据
	private boolean failOnError = true;//如果该rdl操作失败是否中断 后续其它rdl的上发流程，默认true,即需要中断
	
	public String getKv() {
		return kv;
	}
	public void setKv(String kv) {
		this.kv = kv;
	}
	public Class<?> getRespClass() {
		return respClass;
	}
	public void setRespClass(Class<?> respClass) {
		this.respClass = respClass;
	}
	public Object getReqObj() {
		return reqObj;
	}
	public void setReqObj(Object reqObj) {
		this.reqObj = reqObj;
	}
	
	public MdbRdl(String kv,Class<?> respClass){
		this.setKv(kv);
		this.setReqObj(reqObj);
		this.setRespClass(respClass);
	}
	public boolean isFailOnError() {
		return failOnError;
	}
	public void setFailOnError(boolean failOnError) {
		this.failOnError = failOnError;
	}
	
	public String toString(){
	    return kv+"@"+((reqObj == null) ? null : reqObj.getClass().getName());
	}
}
