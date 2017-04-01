package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertSplanFreersResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SplanFreers;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmPlanFreeres;

public class InsertSplanFreersBean extends BaseBusiBean {

	private SplanFreers req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_insertSplanFreersResponse resp = new Do_insertSplanFreersResponse();
		
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		PmPlanFreeres insertValue = new PmPlanFreeres();
		insertValue.setFreebieId(req.getFreebieId());
		insertValue.setPlanId(req.getPlanId());
		insertValue.setPriority(req.getPriority());
		this.context.getComponent(BaseComponent.class).insert(insertValue);
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (SplanFreers) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
