package com.ailk.ims.business.query;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseProductComponent;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.component.query.ProductQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOfferingInfoResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SProdSpecChar;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferAttribute;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferLifecycle;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductPricingPlan;
import com.ailk.openbilling.persistence.pm.entity.PmProductSpec;

/**
 * @Description: 依据销售品id查询销售品详细信息                   
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author hunan                                                                                                                                                                                                                                                                           
  * @Date 2011-10-24
 */
public class QueryOfferingInfoBusiBean extends BaseBusiBean {

	private BaseProductComponent prodCmp;

	@Override
	public void init(Object... input) throws IMSException {
		prodCmp = context.getComponent(BaseProductComponent.class);
	}

	@Override
	public void validate(Object... input) throws IMSException {
		if (input[0] == null) {
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "offering_id");
		}
	}

	@Override
	public Object[] doBusiness(Object... input) throws IMSException {
		Integer offeringId = (Integer) input[0];
		PmProductOffering offering = context.getComponent(ConfigQuery.class).queryOfferingByOfferId(offeringId.longValue());
		PmProductPricingPlan plan = (PmProductPricingPlan) prodCmp.querySingle(PmProductPricingPlan.class, new DBCondition(PmProductPricingPlan.Field.productOfferingId, offeringId));
//		PmProductSpec spec = this.queryProdSpec(offeringId);
		PmProductSpec spec = null;
		List <SProdSpecChar>  list = null;
		if(offering != null ){
			spec = (PmProductSpec) prodCmp.querySingle(PmProductSpec.class,new DBCondition(PmProductSpec.Field.prodSpecId,offering.getProdSpecId()));
		}
		if(offering != null ){
			list = context.getComponent(ProductQuery.class).queryProdSpecCharValue(offeringId);
		}
		//PmProductOffering里面没有billType字段，用priority替代 曾哥确认 add by tangkun
		if(offering != null){
			PmProductOfferAttribute offerLifeccyle = prodCmp.querySingle(PmProductOfferAttribute.class,new DBCondition(PmProductOfferAttribute.Field.productOfferingId ,offeringId));
			if(offerLifeccyle != null){
				offering.setPriority(offerLifeccyle.getBillingType());
			}else{
				offering.setPriority(null);
			}
		}
		PmProductOfferLifecycle offerLifeccyle = prodCmp.querySingle(PmProductOfferLifecycle.class,new DBCondition(PmProductOfferLifecycle.Field.productOfferingId ,offeringId));
		return new Object[] { offering,offerLifeccyle,plan ,spec,list};
	}
	

	
//	private PmProductSpec queryProdSpec(Integer offeringId) {
//		// DBJoinCondition join = new DBJoinCondition(PmProductSpecChar.class) ;
//		// join.innerJoin(table, relations)
//		//
//		List<PmProductOfferSpecChar> offerSpecCharList =  prodCmp.query(PmProductSpecChar.class, new DBCondition(PmProductOfferSpecChar.Field.productOfferingId, offeringId));
//		for(PmProductOfferSpecChar specChar :offerSpecCharList ){
//			Integer charId = specChar.getSpecCharId();
//			Integer spec = charId/100;
//			
//		}
//		return null;
//	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_queryOfferingInfoResponse resp = new Do_queryOfferingInfoResponse();
		if(result == null ){
			return resp;
		}else{
			resp.setOffering((PmProductOffering) result[0]);
			resp.setProdLifecycle((PmProductOfferLifecycle) result[1]);
			resp.setPricePlan((PmProductPricingPlan) result[2]);
			resp.setProductSpec((PmProductSpec) result[3]);
			resp.setProdSpecCharList((List<SProdSpecChar>) result[4]);
		}
		return resp;
	}

	@Override
	public void destroy() {
	}

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
