package com.ailk.imssh.priceParam.cmp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jef.database.Condition.Operator;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaRcData;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProdPriceParam;

/**
 * @Date 2013-11-27
 * @author dingjiang3
 */
public class ProdPriceParamCmp extends BaseCmp {
	public void create(IProdPriceParam prodPriceParam) {
		super.insert(convert(prodPriceParam, false));
	}

	public void update(IProdPriceParam prodPriceParam) {
		CoProdPriceParam coProdPriceParam = convert(prodPriceParam, true);
		// AcctCmp acctCmp = getContext().getCmp(AcctCmp.class);
		// Date beginOfMonth = DateUtils.monthBegin(context.getCommitDate());
		// java.util.Date beginOfMonth =
		// acctCmp.queryAcctCycleStart(prodPriceParam.getAcctId(),
		// prodPriceParam.getCommitDate(),
		// EnumCodeDefine.ACCT_CURRENT_BILL_CYCLE);
		this.updateMode2(coProdPriceParam,EnumCodeExDefine.OPER_TYPE_UPDATE,prodPriceParam.getValidDate(),
				new DBCondition(CoProdPriceParam.Field.productId, prodPriceParam.getProductId()),
				new DBCondition(CoProdPriceParam.Field.paramId, prodPriceParam.getParamId()));
		
	}

	public void delete(IProdPriceParam prodPriceParam) {
		CoProdPriceParam coProdPriceParam =new CoProdPriceParam();
		coProdPriceParam.setExpireDate(prodPriceParam.getExpireDate());
		this.updateMode2(coProdPriceParam,EnumCodeExDefine.OPER_TYPE_DELETE,prodPriceParam.getValidDate(),
				new DBCondition(CoProdPriceParam.Field.productId, prodPriceParam.getProductId()),
				new DBCondition(CoProdPriceParam.Field.paramId, prodPriceParam.getParamId()));
		
	}

	public CoProdPriceParam convert(IProdPriceParam prodPriceParam, boolean isUpdateOper) {
		CoProdPriceParam coProdPriceParam = new CoProdPriceParam();
		coProdPriceParam.setPriceParamId(DBUtil.getSequence(CoProdPriceParam.class));
		coProdPriceParam.setCreateDate(prodPriceParam.getCommitDate());

		coProdPriceParam.setParamValue(prodPriceParam.getParamValue());
		if (!isUpdateOper) {
			coProdPriceParam.setValidDate(prodPriceParam.getValidDate());
			coProdPriceParam.setProductId(prodPriceParam.getProductId());
			coProdPriceParam.setParamId(prodPriceParam.getParamId());
		}
		coProdPriceParam.setExpireDate(prodPriceParam.getExpireDate());
		coProdPriceParam.setSoNbr(context.getSoNbr());
		coProdPriceParam.setSoDate(prodPriceParam.getCommitDate());
		Object[] objects = getObjectIdAndType(prodPriceParam.getUserId(), prodPriceParam.getAcctId());
		coProdPriceParam.setObjectId((Long) objects[0]);
		coProdPriceParam.setObjectType((Integer) objects[1]);

		return coProdPriceParam;

	}

	private Object[] getObjectIdAndType(Long userId, Long acctId) {
		Long objectId = null;
		Integer objectType = null;
		if (userId != null) {
			objectId = userId;
			objectType = EnumCodeDefine.PROD_OBJECTTYPE_DEV;
		} else {
			objectId = acctId;
			CaAccount dmAccount = context.get3hTree().loadAcct3hBean(acctId).getAccount();
			if (dmAccount != null) {
				Integer accountClass = dmAccount.getAccountClass();
				if (accountClass.shortValue() == EnumCodeDefine.ACCOUNT_CLASS_GCA) {
					objectType = EnumCodeDefine.PROD_OBJECTTYPE_GCA;// 使用类型为虚账户
				} else if (accountClass.shortValue() == EnumCodeDefine.ACCOUNT_CLASS_CA) {
					objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;// 使用类型为账户
				}
			} else {
				objectType = EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT;
			}
		}

		return new Object[] { objectId, objectType };
	}

	public void beforeHandle(List<IProdPriceParam> dataList) {
		if (CommonUtil.isEmpty(dataList)) {
			return;
		}
		
		Set<Long> acctIdSet = new HashSet<Long>();
		List<CaRcData> rcList = new ArrayList<CaRcData>();
		for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
			IProdPriceParam iProdPriceParam = (IProdPriceParam) iterator.next();
			if(iProdPriceParam.getParamId() == ConstantExDefine.IMMEDIALETE_RC_CHARGE){
				CaRcData data = createCaRcData(iProdPriceParam.getUserId(), iProdPriceParam.getAcctId(), acctIdSet);
				if(data != null){
					rcList.add(data);
				}
				iterator.remove();
			}
		}
		this.insertBatch(rcList);
		
		if(context.getBusiCode().intValue() == SysUtil.getInt(ConstantExDefine.GX_MANUAL_MODIFY_PROD, EnumCodeExDefine.GX_MANUAL_MODIFY_PROD)){
			for(IProdPriceParam param : dataList){
				param.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
				
				ITableUtil.setValue2ContextHolder(null, param.getAcctId(), param.getUserId());
        		deleteByCondition_noInsert(CoProdPriceParam.class, param.getValidDate(),  new DBCondition(CoProdPriceParam.Field.productId,param.getProductId()),
        				new DBCondition(CoProdPriceParam.Field.paramId,param.getParamId()),
        				new DBCondition(CoProdPriceParam.Field.expireDate,param.getValidDate(),Operator.GREAT_EQUALS));
        		DBUtil.deleteByCondition(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, param.getProductId()),
        				new DBCondition(CoProdPriceParam.Field.paramId,param.getParamId()),
                        new DBCondition(CoProdPriceParam.Field.validDate, param.getValidDate()),
                        new DBCondition(CoProdPriceParam.Field.expireDate, param.getValidDate()));
			}
		}else{
			Map<Long, Long> userMap = new HashMap<Long, Long>();
			Map<Long, Long> acctMap = new HashMap<Long, Long>();
			Map<Long, Set<Integer>> itemMap = new HashMap<Long, Set<Integer>>();
			for (IProdPriceParam param : dataList) {
				if (param.getUserId() != null) {
					userMap.put(param.getProductId(), param.getUserId());
				} else {
					acctMap.put(param.getProductId(), param.getAcctId());
				}
				Set<Integer> itemList = itemMap.get(param.getProductId());
				if (CommonUtil.isEmpty(itemList)) {
					itemList = new HashSet<Integer>();
					itemList.add(param.getParamId());
					itemMap.put(param.getProductId(), itemList);
				} else {
					itemList.add(param.getParamId());
				}
			}
			
			for (Entry<Long, Set<Integer>> entry : itemMap.entrySet()) {
				Long prodId = (Long) entry.getKey();
				ITableUtil.setValue2ContextHolder(null, acctMap.get(prodId), userMap.get(prodId));
				delete_noValid(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.productId, prodId), new DBCondition(
						CoProdPriceParam.Field.priceParamId, completeSetIntSize(entry.getValue()), Operator.IN), new DBCondition(
								CoProdPriceParam.Field.validDate, context.getCommitDate(), Operator.GREAT), new DBCondition(
										CoProdPriceParam.Field.expireDate, context.getCommitDate(), Operator.GREAT));
			}
		}

	}
	
	private CaRcData createCaRcData(Long userId,Long acctId,Set<Long> acctIdSet){
		if(acctId == null){
			acctId = context.getCmp(RouterCmp.class).getAcctIdByUserIdRout(userId);
		}
		if (acctIdSet.contains(acctId)){
			return null;
		}else{
			acctIdSet.add(acctId);
		}
		CaRcData data = new CaRcData();
		data.setCreateDate(context.getCommitDate());
		data.setAcctId(acctId);
		data.setSourceType(0);
		data.setSts(0);
		return data;
	}

}
