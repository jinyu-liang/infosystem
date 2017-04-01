package com.ailk.imssh.user.handler;

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
import com.ailk.imssh.user.cmp.UserShareRelCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserShareRel;

public class UserShareRelHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<IUserShareRel> shareRelList = (List<IUserShareRel>) dataArr[0];
		UserShareRelCmp relCmp = context.getCmp(UserShareRelCmp.class);
		RouterCmp baseCmp = context.getCmp(RouterCmp.class);
		Map<Long, Set<Long>> splitMap = new HashMap<Long, Set<Long>>();
		for (IUserShareRel rel : shareRelList) {
			ITableUtil.setValue2ContextHolder(null, null, rel.getUserId());
			addSplitToMap(rel.getUserId(), rel.getViceUserId(),splitMap);
			int operType = rel.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				relCmp.createUserShareRel(rel);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				relCmp.updateUserShareRel(rel);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				relCmp.deleteUserShareRel(rel);
				break;
			default:
				break;
			}
		}
		baseCmp.publicUserShareProdRoute(splitMap);
       
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}
	private void addSplitToMap(Long payAcctId, Long userId, Map<Long, Set<Long>> splitMap)
    {
        if (userId == null)
        {
            return;
        }
        Set<Long> userSet = splitMap.get(payAcctId);
        if (userSet == null)
        {
            userSet = new HashSet<Long>();
            splitMap.put(payAcctId, userSet);
        }
        userSet.add(userId);
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}
}
