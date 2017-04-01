package com.ailk.imssh.group.handler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.DataObject;

import com.ailk.ims.exception.IMSException;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.group.cmp.GroupRelCmp;
import com.ailk.openbilling.persistence.itable.entity.IAcctRel;

public class GroupRelHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<IAcctRel> groupList = (List<IAcctRel>) dataArr[0];
		GroupRelCmp relCmp = context.getCmp(GroupRelCmp.class);
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		Map<Long, Set<Long>> relMap = new HashMap<Long, Set<Long>>();
		for (IAcctRel rel : groupList) {
			addGroupRelToMap(rel, relMap);
			// 按主群组对应分表
			
			Long acctId=baseCmp.queryAcctIdByUserId(rel.getGroupId());
			ITableUtil.setValue2ContextHolder(null, acctId, null);
			int operType = rel.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				relCmp.createGroupRel(rel);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				relCmp.updateGroupRel(rel);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				relCmp.deleteGroupRel(rel);
				break;
			default:
				break;
			}
		}
		
		context.getCmp(RouterCmp.class).publishGroupRelations(relMap);

	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	private void addGroupRelToMap(IAcctRel rel, Map<Long, Set<Long>> memberMap) {
		Long groupId = rel.getGroupId();
		Long relGroupId = rel.getRelGroupId();

		Set<Long> relGroupSet = memberMap.get(groupId);
		if (relGroupSet == null) {
			relGroupSet = new HashSet<Long>();
			memberMap.put(groupId, relGroupSet);
		}
		relGroupSet.add(relGroupId);
		context.setValidDate(rel.getValidDate());
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
