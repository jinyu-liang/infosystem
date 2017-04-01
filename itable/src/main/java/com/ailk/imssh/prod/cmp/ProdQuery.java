package com.ailk.imssh.prod.cmp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jef.database.Condition.Operator;
import jef.database.DataObject;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 产品查询组件
 * @author lijc3
 * @Date 2012-5-17
 */
public class ProdQuery extends ConfigQuery {


	/**
	 * 传入查询出的同一个产品的多条数据列表，合并为一条数据
	 * 合并方式为把validDate最靠前的数据的expireDate设置为最靠后的expireDate即 产品的真实expireDate luojb
	 * 2012-3-15
	 * 
	 * @param prodList
	 * @return
	 */
	public CoProd mergeProd(List<CoProd> prodList) {
		List<CoProd> mergeProdList = mergeProdList(prodList);
		return CommonUtil.isEmpty(mergeProdList) ? null : mergeProdList.get(0);
	}

	/**
	 * 传入查询出的产品列表，合并同一个产品的多条数据，
	 * 合并方式为把validDate最靠前的数据的expireDate设置为最靠后的expireDate即 产品的真实expireDate luojb
	 * 2012-3-15
	 * 
	 * @param prodList
	 * @return
	 */
	public List<CoProd> mergeProdList(List<CoProd> prodList) {
		if (prodList == null || prodList.size() <= 1)
			return prodList;
		List<CoProd> mergeCoProdList = new ArrayList<CoProd>();
		Map<Long, Date> expireDateMap = new HashMap<Long, Date>();
		Map<Long, CoProd> map = new HashMap<Long, CoProd>();
		for (CoProd prod : prodList) {
			Long prodId = prod.getProductId();
			Date valid = prod.getValidDate();
			Date expire = prod.getExpireDate();
			if (!map.containsKey(prodId)
					|| map.get(prodId).getValidDate().after(valid)) {
				map.put(prodId, prod);
			}
			if (!expireDateMap.containsKey(prodId)
					|| expireDateMap.get(prodId).before(expire))
				expireDateMap.put(prodId, expire);
		}
		Iterator<Long> it = map.keySet().iterator();
		while (it.hasNext()) {
			// @Date 2012-04-20 luojb修改逻辑
			CoProd prod = map.get(it.next());
			if (expireDateMap.containsKey(prod.getProductId())) {
				CoProd newProd = (CoProd) IMSUtil.copyDataObject(prod);
				newProd.setExpireDate(expireDateMap.get(prod.getProductId()));
				mergeCoProdList.add(newProd);
			} else {
				mergeCoProdList.add(prod);
			}
		}

		return mergeCoProdList;
	}

	public CoProd queryProd(long prodId) throws IMSException {
		return mergeProd(queryProdList(prodId));
	}

	// 查询产品表，多条记录，没有合并
	public List<CoProd> queryProdList(Long prodId) throws IMSException {
		return query(CoProd.class, new DBCondition(CoProd.Field.productId,
				prodId));
	}

	// 返回同一个DataObject失效时间最晚的一条记录
	public <T extends DataObject> T getLastDataObjectByExpireDate(
			List<T> objectList) throws IMSException {
		if (CommonUtil.isEmpty(objectList)) {
			return null;
		}
		T obj = null;
		for (T o : objectList) {
			if (obj == null) {
				obj = o;
			} else {
				try {
					Date objExpireDate = (Date) ClassUtil.getFieldValue(obj,
							ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
					Date oExpireDate = (Date) ClassUtil.getFieldValue(o,
							ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
					if (oExpireDate.after(objExpireDate)) {
						obj = o;
					}
				} catch (Exception e) {
					throw IMSUtil.throwBusiException(e);
				}
			}
		}
		return obj;
	}

	/**
	 * @Description: 根据用户ID查找用户下的所有产品列表
	 * @update yanchuan 2011-10-06 修改为调用方法queryProdList获取产品列表
	 * @param userId
	 * @return
	 */
	public List<CoProd> queryProdListByUserId(Long userId) {
		return queryProdList(userId, EnumCodeDefine.PROD_OBJECTTYPE_DEV);
	}

	/**
	 * @Description: 根据帐户ID查找帐户下的所有产品列表
	 * @author yanchuan 2011-10-06
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<CoProd> queryProdListByAcctId(Long acctId) {
		return queryProdList(acctId, EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT);
	}

	/**
	 * 查询用户下对应业务的产品列表<br>
	 * wukl 2012-5-22
	 * 
	 * @param userId
	 * @param busiFlag
	 * @return
	 */
	public List<CoProd> queryProdListByUserId(Long userId, Integer busiFlag) {
		List<CoProd> prodList = query(CoProd.class, new DBCondition(
				CoProd.Field.objectId, userId), new DBCondition(
				CoProd.Field.busiFlag, busiFlag), new DBCondition(
				CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
		return mergeProdList(prodList);
	}

	/**
	 * 查询用户或帐户订购的产品的公用方法 yanchuan 2011-10-6
	 */
	public List<CoProd> queryProdList(Long userIdOrAcctId, int objectType) {
		List<CoProd> prodList = query(CoProd.class, new DBCondition(
				CoProd.Field.objectId, userIdOrAcctId), new DBCondition(
				CoProd.Field.objectType, objectType));
		return mergeProdList(prodList);
	}

	/**
	 * lijc3 2012-5-17 获取价格计划
	 */
	public Integer queryPricePlanId(long offeringId,
			List<CoProdPriceParam> param, Integer overrideOfferId,
			List<CoProdCharValue> values, Integer mainOfferId, Object list,
			Long parentOffringId) throws IMSException {
		Integer pricePlanId = queryPricePlanId(offeringId, null, null, null,
				param, overrideOfferId, values, mainOfferId, list,
				parentOffringId);
		if (pricePlanId == null) {
			return 0;
		} else {
			return pricePlanId;
		}
	}

	/**
	 * 增加价格扩展参数 最底层参数 offeringId 销售品id cust 客户 account 账户 user 用户 param 价格扩展参数
	 * overrideOfferId <BR>
	 * 有override关系的销售品id values 产品特征值
	 */
	// caohm5 2012-02-17 add Object list用来存储组合产品下的销售品列表
	// caohm5 2012-02-22 add Long parentOffringId 用来存储子销售的父销售品ID
	public Integer queryPricePlanId(long offeringId, CmCustomer cust,
			CaAccount account, CmResource user, List<CoProdPriceParam> param,
			Integer overrideOfferId, List<CoProdCharValue> values,
			Integer mainOfferId, Object list, Long parentOffringId)
			throws IMSException {
		Map<Class, Object> inputMap = new HashMap<Class, Object>();
		inputMap.put(CmCustomer.class, cust);
		inputMap.put(CaAccount.class, account);
		// when an account order an promotion, maybe there are no subscriber.
		if (user != null) {
			inputMap.put(CmResource.class, user);
		}
		// 增加价格扩展参数
		if (!CommonUtil.isEmpty(param))
			inputMap.put(CoProdPriceParam.class, param);
		if (!CommonUtil.isEmpty(values))
			inputMap.put(CoProdCharValue.class, values);
		// inputMap.put(PmProductOffering.class, prodOffer);
		// 只加入override对方的销售品id作为值传入policy
		if (overrideOfferId != null) {
			inputMap.put(PmProductOffering.class, context.get3hTree()
					.loadOffering3hBean(overrideOfferId.longValue())
					.getOffering());
		}

		Map map = buildPricingPlanLuaMap(inputMap);
		if (mainOfferId != null) {
			map.put(ConstantDefine.LUA_PRICEINGPLAN_MAIN_OFFER_ID, mainOfferId);
		}
		if (list != null) {
			map.put(ConstantDefine.PACKET_PRODUCT_OFFERING_ID, list);
		}
		if (parentOffringId != null) {
			map.put(ConstantDefine.SON_PRODUCT_IN_PARCKET, parentOffringId);
		}
		// return queryPricePlanId(offeringId, map);
		return super.queryPricePlanId(offeringId, map);
	}

	/**
	 * 查询用户已订购的主产品信息
	 * 
	 * @author yangyang
	 * @date 2011-10-25
	 */
	public CoProd queryPrimaryProductByUserId(Long userId) {
		return querySingle(CoProd.class, new DBCondition(CoProd.Field.objectId,
				userId), new DBCondition(CoProd.Field.isMain,
				EnumCodeDefine.PRODUCT_MAIN), new DBCondition(
				CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
	}

	/**
	 * 构建lua参数 lijc3 2012-5-17
	 * 
	 * @param inputData
	 * @return
	 */
	public Map buildPricingPlanLuaMap(Map<Class, Object> inputData) {
		CmCustomer cust = (CmCustomer) inputData.get(CmCustomer.class);
		CaAccount acct = (CaAccount) inputData.get(CaAccount.class);
		CmResource user = (CmResource) inputData.get(CmResource.class);
		PmProductOffering offer = (PmProductOffering) inputData
				.get(PmProductOffering.class);
		CmIndividual individual = (CmIndividual) inputData
				.get(CmIndividual.class);
		List<CoProdPriceParam> params = (List<CoProdPriceParam>) inputData
				.get(CoProdPriceParam.class);
		List<CoProdCharValue> values = (List<CoProdCharValue>) inputData
				.get(CoProdCharValue.class);
		Map map = new HashMap();
		if (!CommonUtil.isEmpty(values)) {
			for (CoProdCharValue pp : values) {
				try {
					map.put("SID" + pp.getSpecCharId(),
							Long.parseLong(pp.getValue()));
				} catch (Exception e) {
					// 报异常，Long.parseLong(pp.getValue()) 直接存储pp.getValue()
					map.put("SID" + pp.getSpecCharId(), pp.getValue());
				}
			}
		}
		if (!CommonUtil.isEmpty(params)) {
			for (CoProdPriceParam pp : params) {
				if (pp.getParamId() == SpecCodeDefine.UPSELLING_FLAG) {// upsell
					map.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_UP_SELL, 1);
				} else if (pp.getParamId() == SpecCodeDefine.GROUP_TYPE_PROD_PARAM_ID) {// group
																						// type
					map.put(ConstantDefine.LUA_PRICINGPLAN_GROUP_TYPE,
							Long.parseLong(pp.getParamValue()));
				} else {
					try {
						map.put("PID" + pp.getParamId(),
								Long.parseLong(pp.getParamValue()));
					} catch (Exception e) {
						// 报异常，Long.parseLong(pp.getValue()) 直接存储pp.getValue()
						map.put("PID" + pp.getParamId(), pp.getParamValue());
					}
				}
			}

		}
		if (map.get(ConstantDefine.LUA_PRICEINGPLAN_PROD_UP_SELL) == null) {
			map.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_UP_SELL, 0);
		}
		// map.put(ConstantDefine.LUA_PRICEINGPLAN_PROD_FIRST_ORDER, 1);
		if (cust != null) {
			map.put(ConstantDefine.LUA_PRRICINGPLAN_CUST_TYPE,
					cust.getCustomerType());
			map.put(ConstantDefine.LUA_PRRICINGPLAN_CUST_SEGMENT,
					cust.getCustomerSegment());
		}
		if (individual != null)
			map.put(ConstantDefine.LUA_PRRICINGPLAN_CUST_GENDER,
					individual.getGender());
		if (acct != null)
			map.put(ConstantDefine.LUA_PRRICINGPLAN_ACCT_SEGMENT,
					acct.getAccountSegment());
		if (user != null) {
			map.put(ConstantDefine.LUA_PRRICINGPLAN_USER_SEGMENT,
					user.getResSegment());
			map.put(ConstantDefine.LUA_PRICEINGPLAN_USER_REGION_CODE,
					user.getRegionCode());
		}
		if (offer != null) {
			map.put(ConstantDefine.LUA_PRRICINGPLAN_PROD_OVERRIDE_OFFER_ID,
					offer.getProductOfferingId());
		} else {
			map.put(ConstantDefine.LUA_PRRICINGPLAN_PROD_OVERRIDE_OFFER_ID, 0);
		}
		return map;
	}

	/**
	 * 查询用户或者账户下订购某销售品的产品列表 按产品实例id升序排列 ljc 2011-11-18
	 */
	public List<CoProd> queryProdByUserIdAndOfferId(Long objectId,
			Integer invType, Integer offerId) {
		List<CoProd> prodList = query(CoProd.class, new DBCondition(
				CoProd.Field.objectId, objectId), new DBCondition(
				CoProd.Field.objectType, invType), new DBCondition(
				CoProd.Field.productOfferingId, offerId));
		return mergeProdList(prodList);
	}

	/**
	 * lijc3 2012-7-30 查询用户或者账户下订购销售品列表的产品列表
	 * 
	 * @return
	 */
	public List<CoProd> queryProdByUserIdAndOfferId(Long objectId,
			Integer invType, List<Integer> offerIdList) {
		List<CoProd> prodList = query(CoProd.class, new DBCondition(
				CoProd.Field.objectId, objectId), new DBCondition(
				CoProd.Field.objectType, invType), new DBCondition(
				CoProd.Field.productOfferingId, offerIdList, Operator.IN));
		return mergeProdList(prodList);
	}

	/**
	 * 查询产品二次议价参数 lijc3 2012-5-17
	 * 
	 * @param prodId
	 * @return
	 */
	public List<CoProdPriceParam> queryProdPriceParam(Long prodId) {
		return query(CoProdPriceParam.class, new DBCondition(
				CoProdPriceParam.Field.productId, prodId));
	}

	/**
	 * 查询产品特征值 lijc3 2012-5-17
	 * 
	 * @param prodId
	 * @return
	 */
	public List<CoProdCharValue> queryProdCharValue(Long prodId) {
		return query(CoProdCharValue.class, new OrderCondition[] {
				new OrderCondition(CoProdCharValue.Field.validDate),
				new OrderCondition(CoProdCharValue.Field.expireDate) }, null,
				new DBCondition(CoProdCharValue.Field.productId, prodId));
	}

	/**
	 * 查询charValues
	 */
	public List<CoProdCharValue> queryCharValueAndObjectId(Long prodId,
			int specCode, Long obejctId) throws IMSException {
		return this.query(CoProdCharValue.class, new DBCondition(
				CoProdCharValue.Field.productId, prodId), new DBCondition(
				CoProdCharValue.Field.specCharId, specCode));
	}
	/**************** 上海PCC功能，目前废弃 *******************/
/*  
	public void beforePCCWlan() {

		Map<Short, Map<Long, List<ProdComplex>>> allMap = context
				.getAllProdMap();
		if (CommonUtil.isEmpty(allMap)) {
			return;
		}
		Map<Long, List<ProdComplex>> addMap = allMap
				.get(EnumCodeDefine.CHANGE_PROD_ADD);
		Map<Long, List<ProdComplex>> delMap = allMap
				.get(EnumCodeDefine.CHANGE_PROD_DELETE);
		Map<Long, List<ProdComplex>> upMap = allMap
				.get(EnumCodeDefine.CHANGE_PROD_DELETE);
		if (CommonUtil.isNotEmpty(addMap)) {
			for (Long objectId : addMap.keySet()) {
				if (objectId != EnumCodeExDefine.ACCT_PROD_ID) {
					doPccWlan(addMap.get(objectId),
							EnumCodeExDefine.PCC_ORDER_FLAG_ADD, objectId);
				}
			}
		}

		if (CommonUtil.isNotEmpty(delMap)) {
			for (Long objectId : delMap.keySet()) {
				if (objectId != EnumCodeExDefine.ACCT_PROD_ID) {
					doPccWlan(delMap.get(objectId),
							EnumCodeExDefine.PCC_ORDER_FLAG_DELETE, objectId);
				}
			}
		}

		if (CommonUtil.isNotEmpty(upMap)) {
			for (Long objectId : upMap.keySet()) {
				if (objectId != EnumCodeExDefine.ACCT_PROD_ID) {
					doPccWlan(upMap.get(objectId),
							EnumCodeExDefine.PCC_ORDER_FLAG_CHANGE, objectId);
				}
			}
		}
	}

	public void doPccWlan(List<ProdComplex> complexList, int orderFlag,
			Long userId) {
		if (CommonUtil.isEmpty(complexList)) {
			return;
		}
		ITableUtil.setValue2ContextHolder(null, null, userId);
		String phoneId = null;

		for (ProdComplex complex : complexList) {
			CoProd dmProd = complex.getDmProd();
			if (dmProd.getBusiFlag() != EnumCodeExDefine.PCC_SLA) {
				return;
			}
			if (CommonUtil.isEmpty(phoneId)) {
				phoneId = context.get3hTree().loadUser3hBean(userId)
						.getPhoneId();
			}
			String value = null;
			if (orderFlag == EnumCodeExDefine.PCC_ORDER_FLAG_DELETE) {
				CoProdCharValue charValue = context.getSingleCache(
						CoProdCharValue.class,
						new CacheCondition(CoProdCharValue.Field.productId,
								dmProd.getProductId()), new CacheCondition(
								CoProdCharValue.Field.specCharId,
								EnumCodeExDefine.PCC_SLA_VALUE));
				if (charValue != null) {
					value = charValue.getValue();
				}
			} else {
				List<IProdCharValue> valueList = complex.getValueList();
				if (CommonUtil.isNotEmpty(valueList)) {
					for (IProdCharValue charValue : valueList) {
						value = charValue.getParamValue();
						break;
					}
				}
			}
			imsLogger.debug("***value = ", value);
			// 删除的时候没有TODO
			if (orderFlag == EnumCodeExDefine.PCC_ORDER_FLAG_DELETE
					&& CommonUtil.isNotEmpty(value)) {
				value = String.valueOf(0);
			}
			if (CommonUtil.isEmpty(value)) {
				return;
			}
			SUserInfo userInfo = new SUserInfo();
			SOperInfo operInfo = new SOperInfo();
			operInfo.set_phoneId(phoneId);
			CsdlHashMap<String, String> extMap = new CsdlHashMap<String, String>(
					String.class, String.class);
			extMap.put(ConstantExDefine.PCC_VALUE_KEY, String.valueOf(value));
			extMap.put(ConstantExDefine.PCC_ORDER_FLAG,
					String.valueOf(orderFlag));
			userInfo.set_extraMap(extMap);
			SXdr xdr = new SXdr();
			SManagerInfo info = new SManagerInfo();
			info.set_operInfo(operInfo);
			info.set_userInfo(userInfo);
			xdr.set_managerInfo(info);
			imsLogger.info("###begin to do pcc_wlan ....", phoneId, "&", value,
					"&", orderFlag);
			long time = System.currentTimeMillis();
			ISessionClientInt client = new ISessionClientInt();
			CsdlArrayList<SXdr> xdrList = new CsdlArrayList<SXdr>(SXdr.class);
			xdrList.add(xdr);
			CsdlArrayListHolder<SXdr> holder = new CsdlArrayListHolder<SXdr>(
					null);
			holder.set(xdrList);
			CBSErrorMsg msg = new CBSErrorMsg();
			imsLogger.dump("list", xdrList);
			// int result = client.do_pccInform(holder, msg);
			int result = 0;
			imsLogger.info("###end to do pcc_wlan ....result ", result,
					", cost ", System.currentTimeMillis() - time, " ms");
		}

		ITableUtil.setValue2ContextHolder(null, context.getAcctId(), null);
	}
*/
}
