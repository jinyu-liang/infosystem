package com.ailk.ims.business.product;

import java.util.List;

import oracle.net.ns.Communication;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_updateSproductsResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UpdateSproducts;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmProducts;
//import com.ctc.wstx.util.DataUtil;

public class UpdateSproductsBean extends BaseBusiBean {
	private UpdateSproducts req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_updateSproductsResponse resp = new Do_updateSproductsResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		DBCondition condition = new DBCondition(PmProducts.Field.prodId, req.getProdId());
		PmProducts pmProducts = DBUtil.querySingle(PmProducts.class, condition);
		if(pmProducts != null){
			PmProducts upValue = new PmProducts();
			if(CommonUtil.isValid(req.getIsProm())){
				upValue.setIsProm(req.getIsProm());
			}
			if(CommonUtil.isValid(req.getProdName())){
				upValue.setProdName(req.getProdName());
			}
			if(CommonUtil.isValid(req.getServiceId())){
				upValue.setServiceId(req.getServiceId());
			}
			if(CommonUtil.isValid(req.getExprId())){
				upValue.setExprId(req.getExprId());
			}
			if(CommonUtil.isValid(req.getSubscrParam())){
				upValue.setSubscrParam(req.getSubscrParam());
			}
			if(CommonUtil.isValid(req.getDescription())){
				upValue.setDescription(req.getDescription());
			}
			if(CommonUtil.isValid(req.getPrivEntity())){
				upValue.setPrivEntity(req.getPrivEntity());
			}
			if(CommonUtil.isValid(req.getSumType())){
				upValue.setSumType(req.getSumType());		
			}
			if(CommonUtil.isValid(req.getProdClass())){
				upValue.setProdClass(req.getProdClass());
			}
			if(CommonUtil.isValid(req.getSpCode())){
				upValue.setSpCode(req.getSpCode());
			}
			if(CommonUtil.isValid(req.getStdProdId())){
				upValue.setStdProdId(req.getStdProdId());
			}
			if(CommonUtil.isValid(req.getProdType())){
				upValue.setProdType(req.getProdType());
			}
			if(CommonUtil.isValid(req.getProdTempId())){
				upValue.setProdTempId(req.getProdTempId());
			}
			if(CommonUtil.isValid(req.getOrderTempId())){
				upValue.setOrderTempId(req.getOrderTempId());
			}
			if(CommonUtil.isValid(req.getSubscrMode())){
				upValue.setSubscrMode(req.getSubscrMode());
			}
			if(CommonUtil.isValid(req.getExpireMode())){
				upValue.setExpireMode(req.getExpireMode());
			}
			if(CommonUtil.isValid(req.getValidCycle())){
				upValue.setValidCycle(req.getValidCycle());
			}
		
				upValue.setExpireDate(req.getExpireDate());
				upValue.setValidDate(req.getValidDate());
		
			DBUtil.updateByCondition(upValue, condition);
		}else{
			PmProducts insertValue = new PmProducts();
			insertValue.setProdId(req.getProdId());
			insertValue.setIsProm(req.getIsProm());
			insertValue.setProdName(req.getProdName());
			insertValue.setServiceId(req.getServiceId());
			insertValue.setExprId(req.getExprId());
			insertValue.setSubscrParam(req.getSubscrParam());
			insertValue.setValidDate(req.getValidDate());
			insertValue.setExpireDate(req.getExpireDate());
			insertValue.setDescription(req.getDescription());
			insertValue.setPrivEntity(req.getPrivEntity());
			insertValue.setSumType(req.getSumType());
			insertValue.setProdClass(req.getProdClass());
			insertValue.setSpCode(req.getSpCode());
			insertValue.setStdProdId(req.getStdProdId());
			insertValue.setProdType(req.getProdType());
			insertValue.setProdTempId(req.getProdTempId());
			insertValue.setOrderTempId(req.getOrderTempId());
			insertValue.setSubscrMode(req.getSubscrMode());
			insertValue.setExpireMode(req.getExpireMode());
			insertValue.setValidCycle(req.getValidCycle());
			this.context.getComponent(BaseComponent.class).insert(insertValue);
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (UpdateSproducts) input[0];

	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
