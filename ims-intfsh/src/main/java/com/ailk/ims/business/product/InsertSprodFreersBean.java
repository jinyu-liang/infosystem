package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertSprodFreersResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SprodFreers;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmProdFreeres;

public class InsertSprodFreersBean extends BaseBusiBean {

	private SprodFreers req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_insertSprodFreersResponse resp = new Do_insertSprodFreersResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		PmProdFreeres inserValue = new PmProdFreeres();
		inserValue.setPlanId(req.getPlanId());
		inserValue.setProdId(req.getProdId());
		inserValue.setFreebieId(req.getFreebieId());
		inserValue.setValidCycle(req.getValidCycle());
		inserValue.setExpireCycle(req.getExpireCycle());
		inserValue.setResCount(req.getResCount());
		inserValue.setNumerator(req.getNumerator());
		inserValue.setDenominator(req.getDenominator());
		inserValue.setExpireMode(req.getExpireMode());
		inserValue.setHalfType(req.getHalfType());
		inserValue.setCycleType(req.getCycleType());
		inserValue.setUseUnit(req.getUseUnit());
		inserValue.setResLimit(req.getResLimit());
		inserValue.setRoleLimit(req.getRoleLimit());
		inserValue.setAutoUpgrade(req.getAutoUpgrade());
		this.context.getComponent(BaseComponent.class).insert(inserValue);
		
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (SprodFreers) input[0];

	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
