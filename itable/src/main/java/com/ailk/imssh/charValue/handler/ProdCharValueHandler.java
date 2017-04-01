package com.ailk.imssh.charValue.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.charValue.cmp.ProdCharValueCmp;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CoBudgetCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.itable.entity.IProdCharValue;

import jef.database.Condition.Operator;
import jef.database.DataObject;

/**
 * @Description
 * @author dingjiang3
 * @Date 2013-11-26
 */
public class ProdCharValueHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<? extends DataObject> list = dataArr[0];
		ProdCharValueCmp cmp = this.getContext().getCmp(ProdCharValueCmp.class);
		cmp.operateProduct((List<IProdCharValue>) list);
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<?
	 *      extends jef.database.DataObject>[])
	 */
	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
		if (FlowConst.MAN_PRODUCT_ID_PREFIX == 0) {
			return;
		}
		Map<Long, Long> prodIdMap = new HashMap<Long, Long>();
		ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
		List<? extends DataObject> dataList = dataArr[0];
		for (int i = 0; i < dataList.size(); i++) {
			IProdCharValue param = (IProdCharValue) dataList.get(i);
			Long prodId = prodIdMap.get(param.getProductId());
			if (prodId == null) {
				prodId = prodCmp.convertMainProductId(param.getProductId(), param.getAcctId(), param.getUserId());
				prodIdMap.put(param.getProductId(), prodId);
			}
			param.setProductId(prodId);
		}
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		List<IProdCharValue> ProdCharValuelist = (List<IProdCharValue>) paramArr[0];
		if (CommonUtil.isEmpty(ProdCharValuelist)) {
			return;
		}
		
		List<Long> productIdList = new ArrayList<Long>();
		for (IProdCharValue iProdCharValue : ProdCharValuelist) {
			if (EnumCodeExDefine.OPER_TYPE_DIFF != iProdCharValue.getOperType()) {
				return;
			}
			productIdList.add(iProdCharValue.getProductId());
			iProdCharValue.setOperType(EnumCodeExDefine.OPER_TYPE_INSERT);
		}

		ProdCharValueCmp prodCharValueCmp = this.getContext().getCmp(ProdCharValueCmp.class);
		RouterCmp baseCmp = context.getCmp(RouterCmp.class);
		/** 第一条记录*/
		IProdCharValue prodCharValue = ProdCharValuelist.get(0);
		Long userId = prodCharValue.getUserId();
		
		//返回coProdList
		List<CoProd> coProdList = baseCmp.query(CoProd.class,new DBCondition
				              (CoProd.Field.productId,baseCmp.completeListSize(productIdList),Operator.IN));
		if(coProdList.isEmpty() || coProdList.size() <= 0){
			 imsLogger.info("*******not found CoProd for productIdList,return!");
			 return;
		}
		Long objectId = coProdList.get(0).getObjectId();
			// 如果dmProd.getBusiFlag()==107 需要删除CD.CO_BUDGET_CHAR_VALUE
			List<Integer> busiFlagList = new ArrayList<Integer>();
			for (CoProd coProd : coProdList) {
				if (coProd.getBusiFlag() == SpecCodeDefine.BUDGET) {
					busiFlagList.add(coProd.getBusiFlag());
				}
			}
			
			if (busiFlagList.size() > 0) {
				//判断路由,删除CoBudgetCharValue（busiFlag()==107）
				ITableUtil.setValue2ContextHolder(null, null, userId);
				baseCmp.checkUserRouter(userId);
				baseCmp.deleteByConditionForDiff(CoBudgetCharValue.class,new DBCondition(CoBudgetCharValue.Field.objectId, objectId));
				//全量新增CoBudgetCharValue
				ITableUtil.setValue2ContextHolder(null, null, userId);
				  
			} else {
				//账户路由
				if (coProdList.get(0).getObjectType() == EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT) {
					baseCmp.checkAcctRouter(objectId);
					imsLogger.info(new Object[]{"***objectType ==1,use route value acctid:**********", coProdList.get(0).getObjectType()});
					ITableUtil.setValue2ContextHolder(null,objectId, null);
			    //用户路由
				} else {
					baseCmp.checkUserRouter(objectId);
					imsLogger.info(new Object[]{"***objectType !=1,use route value userid:**********",objectId});
					ITableUtil.setValue2ContextHolder(null, null, objectId);
				}
	    			baseCmp.deleteByConditionForDiff(CoProdCharValue.class, new DBCondition(CoProdCharValue.Field.objectId,objectId));
	    			imsLogger.info(new Object[]{"***end to delete CoProdCharValue!**************************"});
			}
		 prodCharValueCmp.operateProduct(ProdCharValuelist);
	 }

 }
