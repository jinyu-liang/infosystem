package com.ailk.imssh.priceParam.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.priceParam.cmp.ProdPriceParamCmp;
import com.ailk.imssh.prod.cmp.ProdCmp;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.itable.entity.IProdPriceParam;

import jef.database.DataObject;

/**
 * @Description
 * @author dingjiang3
 * @Date 2013-11-26
 */
public class ProdPriceParamHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr) throws IMSException {
		List<IProdPriceParam> list = (List<IProdPriceParam>) dataArr[0];
		if (CommonUtil.isEmpty(list)) {
			return;
		}
		ProdPriceParamCmp cmp = this.getContext().getCmp(ProdPriceParamCmp.class);
		cmp.beforeHandle(list);

		if (CommonUtil.isEmpty(list)) {
			return;
		}

		for (int index = 0; index < list.size(); index++) {
			IProdPriceParam iProdPriceParam = list.get(index);
            //设置分表参数			
			setValueContext(iProdPriceParam);
			switch (iProdPriceParam.getOperType().intValue()) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				cmp.create(iProdPriceParam);
				break;

			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				cmp.update(iProdPriceParam);
				break;

			case EnumCodeExDefine.OPER_TYPE_DELETE:
				cmp.delete(iProdPriceParam);
				break;

			default:
				break;
			}
		}
	}

	/**
	 * 设置分表参数(只能适用在异常处理)
	 * @Date 20161003
	 * @param iProdPriceParam
	 */
	private void setValueContext(IProdPriceParam iProdPriceParam) {
		if (iProdPriceParam.getUserId() != null) {
			ITableUtil.setValue2ContextHolder(null, null, iProdPriceParam.getUserId());
		} else {
			ITableUtil.setValue2ContextHolder(null, iProdPriceParam.getAcctId(), null);
		}
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

		ProdCmp prodCmp = getContext().getCmp(ProdCmp.class);
		Map<Long, Long> prodIdMap = new HashMap<Long, Long>();
		List<? extends DataObject> dataList = dataArr[0];
		for (int i = 0; i < dataList.size(); i++) {
			IProdPriceParam param = (IProdPriceParam) dataList.get(i);

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
		List<IProdPriceParam> iProdPriceParamList = (List<IProdPriceParam>) paramArr[0];
		if (CommonUtil.isEmpty(iProdPriceParamList)) {
			return;
		}
		ProdPriceParamCmp cmp = this.getContext().getCmp(ProdPriceParamCmp.class);
		for (IProdPriceParam iProdPriceParam : iProdPriceParamList) {
			if (EnumCodeExDefine.OPER_TYPE_DIFF != iProdPriceParam.getOperType()) {
				continue;
			}
			isPublishRouter(iProdPriceParam);
			long objectId = getObjectId(iProdPriceParam.getUserId(),iProdPriceParam.getAcctId());
		    setValueContext(iProdPriceParam);
		    cmp.deleteByConditionForDiff(CoProdPriceParam.class, new DBCondition(CoProdPriceParam.Field.objectId, objectId));
			setValueContext(iProdPriceParam);
		    cmp.create(iProdPriceParam);
		}
		
	}

	/**
	 * 按照acct_id、user_id两种情况判断是否已发布路由
	 * 只适用于异常处理接口
	 * @Date 20161008
	 * @param iProdPriceParam
	 */
	private void isPublishRouter(IProdPriceParam iProdPriceParam) {
		RouterCmp baseCmp = this.getContext().getCmp(RouterCmp.class);
		if(iProdPriceParam.getUserId() != null){
			baseCmp.checkUserRouter(iProdPriceParam.getUserId());
		}
		if(iProdPriceParam.getAcctId() !=null){
			baseCmp.checkAcctRouter(iProdPriceParam.getAcctId());		
		}
	  }
	
	/**
	 * 返回object_id
	 * @param userId
	 * @param acctId
	 * @return
	 */
	private long getObjectId(Long userId, Long acctId) {
		return (userId !=null) ? userId:acctId; 
   }
}
