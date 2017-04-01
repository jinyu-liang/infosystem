package com.ailk.ims.business.query4inner;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.query.ConfigQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryOfferingInfoByBusiFlagResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryParamExReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;
import com.ailk.openbilling.persistence.pm.entity.PmProductOfferingInfo;
/**
 * @description:根据busi_flag 查询销售品信息
 * @author caohm5
 * @date 2012-05-31
 *
 */
public class QryOfferingInfoBusiBean extends BaseBusiBean {

	@Override
	public void init(Object... input) throws BaseException {

	}

	@Override
	public void validate(Object... input) throws BaseException {
		SQueryParamExReq sQueryParamExReq=(SQueryParamExReq)input[0];
		Integer busiFlag=sQueryParamExReq.getBusi_flag();
		if(!CommonUtil.isValid(busiFlag)){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "busi_flag");
		}
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		return null;
	}
	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		//入参
		SQueryParamExReq sQueryParamExReq=(SQueryParamExReq)input[0];
		//出参
		PmProductOfferingInfo info=new PmProductOfferingInfo ();
		PmProductOffering pmProductOffering=context.getComponent(ConfigQuery.class).queryOfferingByBusiFlag(sQueryParamExReq.getBusi_flag());
		info=transForm(pmProductOffering);
		return new Object[]{info};
	}
	@Override
	public BaseResponse createResponse(Object[] result) {
		PmProductOfferingInfo info=(PmProductOfferingInfo)result[0];
		if(info==null){
			return null;
		}
		Do_queryOfferingInfoByBusiFlagResponse respn=new Do_queryOfferingInfoByBusiFlagResponse();
		respn.setPmProductOfferingInfo(info);
		return respn;
	}
	@Override
	public void destroy() {

	}
	/**
	 * @description：把销售品数据库实体属性转化为自己定义的实体属性
	 * @author caohm5 
	 * @date 2012-05-31
	 * @param pmProductOffering 销售品数据库实体
	 */
	private PmProductOfferingInfo transForm(PmProductOffering pmProductOffering){
		if(pmProductOffering==null){
			return null;
		}
		PmProductOfferingInfo info=new PmProductOfferingInfo ();
		info.setBilling_priority(pmProductOffering.getBillingPriority());
		info.setDescription(pmProductOffering.getDescription());
		info.setExpire_date(pmProductOffering.getExpireDate());
		info.setIs_global(pmProductOffering.getIsGlobal());
		info.setIs_main(pmProductOffering.getIsMain());
		info.setLifecycle_status_id(pmProductOffering.getLifecycleStatusId());
		info.setName(pmProductOffering.getName());
		info.setOffer_class(pmProductOffering.getOfferClass());
		info.setOffer_type_id(pmProductOffering.getOfferTypeId());
		info.setOpertor_id(pmProductOffering.getOperatorId());
		info.setPriority(pmProductOffering.getPriority());
		info.setProd_spec_id(pmProductOffering.getProdSpecId());
		info.setProduct_offering_id(pmProductOffering.getProductOfferingId());
		info.setValid_date(pmProductOffering.getValidDate());
		return info;
	}
}
