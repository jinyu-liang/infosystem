package com.ailk.ims.ts;

import java.util.ArrayList;
import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.util.CommonUtil;

/**
 * 上发的处理类需要创建的读取条件对象
 * 
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-05-18
 */
public class TsReadCondition {
	private List<DBCondition> dbConditionList;//读取条件
	private List<OrderCondition> orderConditionList;//排序
	
	public TsReadCondition(){}
	
	public TsReadCondition(List<DBCondition> conditions,List<OrderCondition> orders){
		this.setDbConditionList(conditions);
		this.setOrderConditionList(orders);
	}
	
	public List<DBCondition> getDbConditionList() {
		return dbConditionList;
	}

	public void setDbConditionList(List<DBCondition> dbConditionList) {
		this.dbConditionList = dbConditionList;
	}

	public List<OrderCondition> getOrderConditionList() {
		return orderConditionList;
	}

	public void setOrderConditionList(List<OrderCondition> orderConditionList) {
		this.orderConditionList = orderConditionList;
	}
	
	public DBCondition[] getDbConditions(){
		if(CommonUtil.isEmpty(dbConditionList))
			return null;
		return dbConditionList.toArray(new DBCondition[dbConditionList.size()]);
	}
	public OrderCondition[] getOrderConditions(){
		if(CommonUtil.isEmpty(orderConditionList))
			return null;
		return orderConditionList.toArray(new OrderCondition[orderConditionList.size()]);
	}

	public void addDBConditions(DBCondition...conds){
		if(dbConditionList == null){
			dbConditionList = new ArrayList<DBCondition>();
		}
		for(DBCondition cond : conds){
			if(cond == null)
				continue;
			dbConditionList.add(cond);
		}
	}
	public void addOrderConditions(OrderCondition...orders){
		if(orderConditionList == null){
			orderConditionList = new ArrayList<OrderCondition>();
		}
		for(OrderCondition order : orders){
			if(order == null)
				continue;
			orderConditionList.add(order);
		}
	}
	
		
}
