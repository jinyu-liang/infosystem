package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertSfreebiePropResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SfreebieProp;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmFreebieProp;

public class InsertSfreebiePropBean extends BaseBusiBean {

	private SfreebieProp req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_insertSfreebiePropResponse resp = new Do_insertSfreebiePropResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		PmFreebieProp insertValue = new PmFreebieProp();
		insertValue.setFreebieId(req.getFreebieId());
		insertValue.setPriority(req.getPriority());
		insertValue.setUnitCycle(req.getUnitCycle());
		insertValue.setUnitDes(req.getUnitDes());
		this.context.getComponent(BaseComponent.class).insert(insertValue);
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (SfreebieProp) input[0];

	}

}
