package com.ailk.ims.common;


/**
 * @Description:Or条件连接对象                  
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class DBOrCondition extends DBCondition {
	public DBOrCondition(DBCondition... conds) {
		super(conds);
		
	}

	/*public List<IDBCondition> getConditions() {
		return conditions;
	}
	
	*//**
	 * @Description: 把所有的Condition都平坦展开
	  * @return
	 *//*
	public List<DBCondition> getFlatConditions(){
		List<DBCondition> result = new ArrayList<DBCondition>();
		for(IDBCondition cond : conditions){
			if(cond instanceof DBCondition){
				result.add((DBCondition)cond);
			}else if(cond instanceof DBOrCondition){
				DBOrCondition or = (DBOrCondition)cond;
				List<DBCondition> sub = or.getFlatConditions();
				result.addAll(sub);
			}
		}
		return result;
	}*/

}
