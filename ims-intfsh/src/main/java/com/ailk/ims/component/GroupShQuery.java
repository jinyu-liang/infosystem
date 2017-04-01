package com.ailk.ims.component;

import java.util.List;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;

/**
 * @Description:群组相关的信息查询的方法类
 * @author zenglu
 * @Date 2012-09-10
 */
public class GroupShQuery extends BaseComponent {
	
	public GroupShQuery(){
		
	}
	
	public  List<CaResGrpRevs> queryResGrpRevs(Long userId)
	{ 
		
		return query(CaResGrpRevs.class, new DBCondition(CaResGrpRevs.Field.resourceId, userId),
				new DBCondition(CaResGrpRevs.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RV_GROUP));
	}
}
