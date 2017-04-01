package com.ailk.ims.common;

import java.math.BigDecimal;
import java.util.Date;

import jef.database.Condition.Operator;

import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;



/**
 * @Description: 缓存DataObject查询条件  ，也是最基本的查询条件，只支持最基本的field=value的条件      
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-7-1
 */
public class CacheCondition{
	private jef.database.Field field;// 需要查询的Field
	private Object value;// 需要查询的缓存DataObject字段对应的值
	private Operator operator;// 枚举值 ,如： Operator.EQUALS(默认)
	
	protected CacheCondition(){
	}
	
	public CacheCondition(jef.database.Field field, Object value){
		this.field = field;
		this.value = value;
		this.operator = Operator.EQUALS;
	}
	
	public CacheCondition(jef.database.Field field, Object value, Operator operator) {
		this(field,value);
		
		checkValidOperator(operator);
		this.setOperator(operator);
	}
	
	public String toString(){
		Class clazz = IMSUtil.getClassByField(field);
		String strField = clazz.getSimpleName()+"."+field.name();
		String strOper = "=";
		String strValue = value == null ? "null" : value.toString();
		return strField+" "+strOper+" "+strValue;
	}

	public jef.database.Field getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public void checkValidOperator(Operator operator){
		
	}
	
	public boolean isMatch(Object targetValue){
		if(operator == Operator.EQUALS){
			//是否targetValue == value
			if(value == null && targetValue == null){
				return true;
			}else if(value != null && targetValue != null){
				if(CommonUtil.isNumber(value.toString())&& CommonUtil.isNumber(targetValue.toString())){
					//数字类型比较
					return new BigDecimal(targetValue.toString()).compareTo(new BigDecimal(value.toString())) == 0;
				}else{
					return value.equals(targetValue);
				}
			}else{
				return false;
			}
		}else if(operator == Operator.GREAT){
			//是否targetValue > value
			if(value != null && targetValue != null){
				if(CommonUtil.isNumber(value.toString())&& CommonUtil.isNumber(targetValue.toString())){
					//数字型比较
					return new BigDecimal(targetValue.toString()).compareTo(new BigDecimal(value.toString())) > 0;
				}else if(value instanceof Date){
					//日期型比较
					return ((Date)targetValue).getTime() > ((Date)value).getTime();
				}
			}else{
				return false;
			}
		}else if(operator == Operator.GREAT_EQUALS){
			//是否targetValue >= value
			if(value != null && targetValue != null){
				if(CommonUtil.isNumber(value.toString())&& CommonUtil.isNumber(targetValue.toString())){
					//数字型比较
					return new BigDecimal(targetValue.toString()).compareTo(new BigDecimal(value.toString())) >= 0;
				}else if(value instanceof Date){
					//日期型比较
					return ((Date)targetValue).getTime() >= ((Date)value).getTime();
				}
			}else{
				return false;
			}
		}else if(operator == Operator.LESS){
			//是否targetValue < value
			if(value != null && targetValue != null){
				if(CommonUtil.isNumber(value.toString())&& CommonUtil.isNumber(targetValue.toString())){
					//数字型比较
					return new BigDecimal(targetValue.toString()).compareTo(new BigDecimal(value.toString())) < 0;
				}else if(value instanceof Date){
					//日期型比较
					return ((Date)targetValue).getTime() < ((Date)value).getTime();
				}
			}else{
				return false;
			}
		}else if(operator == Operator.LESS_EQUALS){
			//是否targetValue <= value
			if(value != null && targetValue != null){
				if(CommonUtil.isNumber(value.toString())&& CommonUtil.isNumber(targetValue.toString())){
					//数字型比较
					return new BigDecimal(targetValue.toString()).compareTo(new BigDecimal(value.toString())) <= 0;
				}else if(value instanceof Date){
					//日期型比较
					return ((Date)targetValue).getTime() <= ((Date)value).getTime();
				}
			}else{
				return false;
			}
		}else if(operator == Operator.NOT_EQUALS){
			//是否targetValue != value
			if(value == null && targetValue == null){
				return false;
			}else if(value != null && targetValue != null){
				return !value.equals(targetValue);
			}else{
				return true;
			}
		}
		
		throw IMSUtil.throwBusiException("Operator :"+operator.getKey()+" can not be supported!");
	}
}
