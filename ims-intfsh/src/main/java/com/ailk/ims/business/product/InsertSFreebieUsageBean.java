package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertSFreebieUsageResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SFreebieUsage;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmFreebieUsage;

public class InsertSFreebieUsageBean extends BaseBusiBean {

	private SFreebieUsage req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_insertSFreebieUsageResponse resp = new Do_insertSFreebieUsageResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		PmFreebieUsage insertValue = new PmFreebieUsage();
		insertValue.setTargetItem(req.getTargetItem());
		insertValue.setFreebieId(req.getFreebieId());
		insertValue.setConsType(req.getConsType());
		insertValue.setPriority(req.getPriority());
		insertValue.setConsIndicate(req.getConsIndicate());
		insertValue.setSegId(req.getSegId());
		insertValue.setExprId(req.getExprId());
		insertValue.setConsUnit(req.getConsUnit());
		this.context.getComponent(BaseComponent.class).insert(insertValue);
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (SFreebieUsage) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
