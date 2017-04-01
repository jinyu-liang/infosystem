package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;

import jef.database.DataObject;
import jef.database.QB;
import jef.database.query.Query;

import com.ailk.ims.util.CommonUtil;

/**
 * @Description: 每个关联查询连接的部件          
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-10-21
 */
public class JoinPart {
	public static final String TYPE_INNER = "inner";
	public static final String TYPE_LEFT = "left";
	public static final String TYPE_RIGHT = "right";
	
	
	private Class<? extends DataObject> table;//需要连接的表
	private String type;//连接类型，inner,left,right等
	private List<DBRelation> relations;//关联字段
	
	private Query query;//查询的Query对象，一张表对应的一个Query对象
	
	public JoinPart(Class<? extends DataObject> table){
		this.table = table;
		this.relations = new ArrayList<DBRelation>();
	}
	
	public JoinPart(Class<? extends DataObject> table,String type,DBRelation...relations){
		this.table = table;
		this.type = type;
		this.relations = CommonUtil.parseArray2List(relations);
	}

	public Class<? extends DataObject> getTable() {
		return table;
	}

	public String getType() {
		return type;
	}

	public List<DBRelation> getRelations() {
		return relations;
	}
	public Query getQuery() {
		if(query == null){
			query = createQuery();
		}
		return query;
	}
	
	public Query createQuery(){
		Query query = QB.create(table);
		return query;
	}
}
