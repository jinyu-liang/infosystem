/**
 * 
 */
package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SpecCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdBillingCycle;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryProdInfoHisResponse;
import com.ailk.openbilling.persistence.imsinner.entity.ProdRespComplex;
import com.ailk.openbilling.persistence.imsinner.entity.QueryProdInfoHisReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 查询用户或者账户或者群下所有产品的历史信息(三个月内),
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lijingcheng
 * @Date 2011-12-22
 * @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为信息管理侧的measure_id   
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class QueryProdInfoHisBusiBean extends BaseBusiBean {

	private Long userId;
	private Long acctId;
	private Long groupId;
	private Long productId;
	private Date startDate;
	private Date endDate;
	private String start_date;
	private String end_date;
	private String phoneId;

	@Override
	public void init(Object... input) throws IMSException {
		QueryProdInfoHisReq req = (QueryProdInfoHisReq) input[0];
		userId = req.getUser_id();
		acctId = req.getAcct_id();
		groupId = req.getGroup_id();
		productId = req.getProduct_id();
		start_date=req.getStart_date();
		end_date=req.getEnd_date();
		phoneId=req.getPhone_id();
		if(CommonUtil.isNotEmpty(phoneId)||userId!=null){
			User3hBean bean=context.get3hTree().loadUser3hBean(userId,phoneId);
			userId=bean.getUserId();
			phoneId=bean.getPhoneId();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ailk.ims.common.BaseBusiBean#validate(java.lang.Object[])
	 */
	@Override
	public void validate(Object... input) throws IMSException {
		if (userId == null && acctId == null && groupId == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID, "userId,acctId,groupId");
		}
		if(CommonUtil.isEmpty(start_date)){
			throw IMSUtil.throwBusiException(ErrorCodeDefine.PARAM_INVALID,"start_date");
		}
		startDate=IMSUtil.formatDate(start_date);
		endDate=endDate==null?context.getRequestDate():IMSUtil.formatDate(end_date);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ailk.ims.common.BaseBusiBean#doBusiness(java.lang.Object[])
	 */
	@Override
	public Object[] doBusiness(Object... input) throws IMSException {
		List<ProdRespComplex> complexs= queryProd();
		return new Object[]{complexs};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ailk.ims.common.BaseBusiBean#createResponse(java.lang.Object[])
	 */
	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_queryProdInfoHisResponse resp=new Do_queryProdInfoHisResponse();
		if(result[0]!=null){
			resp.setProdinfo_list((List<ProdRespComplex>) result[0]);
		}
		return resp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ailk.ims.common.BaseBusiBean#destroy()
	 */
	@Override
	public void destroy() {

	}
	//查询产品，历史产品，不包括非CRM产品
	private List<ProdRespComplex> queryProd(){
		List<CoProd> prod = null;
		if(productId!=null){
			if(userId!=null){
				prod = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.productId,productId),
						                          new DBCondition(CoProd.Field.objectId,userId),
						                          new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV),
						                          new DBCondition(CoProd.Field.validDate, startDate, Operator.GREAT),
						                          new DBCondition(CoProd.Field.validDate,endDate, Operator.LESS));
				if(CommonUtil.isEmpty(prod)){
					return null;
				}
			}else if(acctId!=null){
				prod = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.productId,productId),
						                          new DBCondition(CoProd.Field.objectId,acctId),
						                          new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
										          new DBCondition(CoProd.Field.validDate, startDate, Operator.GREAT),
										          new DBCondition(CoProd.Field.validDate,endDate, Operator.LESS));
				if(CommonUtil.isEmpty(prod)){
					return null;
				}
			}else{
				 prod = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.productId,productId),
						                           new DBCondition(CoProd.Field.objectId,groupId),
						                           new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_GCA),
                                                   new DBCondition(CoProd.Field.validDate, startDate, Operator.GREAT),
                                                   new DBCondition(CoProd.Field.validDate,endDate, Operator.LESS));
				if(CommonUtil.isEmpty(prod)){
					return null;
				}
			}
		}else{
			if(userId!=null){
                prod = DBUtil.query(CoProd.class,new DBCondition(CoProd.Field.objectId,userId),
                        new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_DEV),
                        new DBCondition(CoProd.Field.validDate, startDate, Operator.GREAT),
                        new DBCondition(CoProd.Field.validDate,endDate, Operator.LESS));
               if(CommonUtil.isEmpty(prod)){
                   return null;
               }
				
			}else if(acctId!=null){
			    prod = DBUtil.query(CoProd.class,new DBCondition(CoProd.Field.objectId,acctId),
                        new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_ACCOUNT),
                      new DBCondition(CoProd.Field.validDate, startDate, Operator.GREAT),
                      new DBCondition(CoProd.Field.validDate,endDate, Operator.LESS));
              if(CommonUtil.isEmpty(prod)){
                  return null;
              }
			}else{//PM_PRICE_PARAM_DEF
                prod = DBUtil.query(CoProd.class, new DBCondition(CoProd.Field.objectId,groupId),
                        new DBCondition(CoProd.Field.objectType,EnumCodeDefine.PROD_OBJECTTYPE_GCA),
                        new DBCondition(CoProd.Field.validDate, startDate, Operator.GREAT),
                        new DBCondition(CoProd.Field.validDate,endDate, Operator.LESS));
                if(CommonUtil.isEmpty(prod)){
                   return null;
                }
			}
		}
		List<ProdRespComplex> complexes=new ArrayList<ProdRespComplex>();
		for(CoProd prods:prod){
//			CoProd dmProd=(CoProd) map.get(CoProd.class);
			//非CRM产品跳过
			if(SpecCodeDefine.isSpecialProd(prods.getBusiFlag())){
				continue;
			//失效时间等于生效时间，是系统删除的时候产品的数据，页面显示没有意义 跳过
			}else if(prods.getValidDate()==prods.getExpireDate()){
				continue;
			}else{
				ProdRespComplex complex=new ProdRespComplex();
				complex.setCo_prod(prods);
				complex.setCo_prod_bill_cycle_list(queryBillCycle(prods));
				complex.setCo_prod_char_value_list(queryCharValue(prods));
				List<CoProdPriceParam> params = queryPriceParam(prods);
				// @Date 2012-07-19 tangjl5 :Story #49447 measure_id转换为信息管理侧的measure_id   
	            if (CommonUtil.isNotEmpty(params))
	            {
	                context.getComponent(BaseProductComponent.class).packgeShowPriceParam(params);
	                complex.setCo_prod_param_list(params);
	            }
				
				complexes.add(complex);
			}
		}
		return complexes;
	}
	
	private List<CoProdBillingCycle> queryBillCycle(CoProd dmProd){
		return DBUtil.query(CoProdBillingCycle.class,new DBCondition(CoProdBillingCycle.Field.objectId, dmProd.getObjectId()), new DBCondition(CoProdBillingCycle.Field.validDate, startDate, Operator.GREAT),
						new DBCondition(CoProdBillingCycle.Field.expireDate, endDate, Operator.LESS),new DBCondition(CoProdBillingCycle.Field.productId, dmProd.getProductId()));
	}
	
	private List<CoProdCharValue> queryCharValue(CoProd dmProd){
		return DBUtil.query(CoProdCharValue.class,new DBCondition(CoProdCharValue.Field.objectId, dmProd.getObjectId()), new DBCondition(CoProdCharValue.Field.validDate, startDate, Operator.GREAT),
				new DBCondition(CoProdCharValue.Field.expireDate, endDate, Operator.LESS),new DBCondition(CoProdCharValue.Field.productId, dmProd.getProductId()));
	}
	private List<CoProdPriceParam> queryPriceParam(CoProd dmProd){
		return DBUtil.query(CoProdPriceParam.class,new DBCondition(CoProdPriceParam.Field.productId, dmProd.getProductId()),new DBCondition(CoProdPriceParam.Field.objectId, dmProd.getObjectId()), new DBCondition(CoProdPriceParam.Field.validDate, startDate, Operator.GREAT),
				new DBCondition(CoProdPriceParam.Field.expireDate, endDate, Operator.LESS));
	}

	public List<com.ailk.ims.ims3h.IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().load3hBean(null,acctId,userId,phoneId));
    	return list;
	}
}
