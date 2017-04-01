package com.ailk.ims.common;
/**
 * @Description: 多货币转换                
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-12-29
 */
public class MeasureBean {
	public static final short FLAG_PRECISION = 0;//存储之用
	public static final short FLAG_DISPLAY = 1;//前台页面显示之用
	
	private int measureId;//货币类型
	private String name;//货币名称
	private double amount;//金额
	private short flag;//标识存储还是前台页面显示
	
	public MeasureBean(int measureId,double amount,short flag){
		this.measureId = measureId;
		this.amount = amount;
		this.flag = flag;
	}
	public MeasureBean(int measureId,String name,double amount,short flag){
		this.measureId = measureId;
		this.name = name;
		this.amount = amount;
		this.flag = flag;
	}

	public int getMeasureId() {
		return measureId;
	}

	public double getAmount() {
		return amount;
	}

	public short getFlag() {
		return flag;
	}

	public String getName() {
		return name;
	}
}	
