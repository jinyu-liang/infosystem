package com.ailk.ims.business.product;

import java.awt.Insets;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertSproductsResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Sproducts;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmProducts;

public class InsertSproductsBean extends BaseBusiBean {

	private Sproducts req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... arg0)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] arg0) {
		// TODO Auto-generated method stub
		Do_insertSproductsResponse resp = new Do_insertSproductsResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public void init(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
		req = (Sproducts) arg0[0];
	}

	@Override
	public void validate(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub

	}

}
