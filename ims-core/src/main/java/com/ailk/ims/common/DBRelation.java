package com.ailk.ims.common;

import jef.database.Condition.Operator;

/**
 * @Description: 设置关联查询时，两个字段的关联关系              
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-8-4
 */
public class DBRelation {
	private jef.database.Field field1;//字段1
	private jef.database.Field field2;//字段2
	
	private Object value;//左右连接的值
	private Operator operator;// 表达式，如： Operator.EQUALS(默认)
	
	public DBRelation(jef.database.Field field1,jef.database.Field field2){
		this.setField1(field1);
		this.setField2(field2);
	}
	
	public DBRelation(jef.database.Field field1,Object value,Operator operator){
		this.setField1(field1);
		this.value = value;
		this.operator = operator;
	}
	public DBRelation(jef.database.Field field1,Object value){
		this(field1,value,Operator.EQUALS);
	}

	public jef.database.Field getField1() {
		return field1;
	}

	public void setField1(jef.database.Field field1) {
		this.field1 = field1;
	}

	public jef.database.Field getField2() {
		return field2;
	}

	public void setField2(jef.database.Field field2) {
		this.field2 = field2;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}
}
