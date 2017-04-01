package com.ailk.ims.common;

/**
 * @Description: 用于查询时指定排序                
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class OrderCondition {
	public final static String ODER_DESC = "desc";
	public final static String ODER_ASC = "asc";
	
	private Boolean isAsc;//是否升序排列，默认是
	private jef.database.Field field;//需要排序的字段
	
	public OrderCondition(jef.database.Field field){
		this(true,field);
	}
	
	public OrderCondition(boolean isAsc,jef.database.Field field){
		this.isAsc = isAsc;
		this.field = field;
	}

	public boolean isAsc() {
		return isAsc == null || isAsc;
	}

	public jef.database.Field getField() {
		return field;
	}
}
