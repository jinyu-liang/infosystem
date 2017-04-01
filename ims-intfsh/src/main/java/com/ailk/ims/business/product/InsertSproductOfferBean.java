package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.cbossinterface.bo.UmpayQryBindRsp;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_insertSproductOfferResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.RsSysIntlOperInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.SproductOffer;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;


public class InsertSproductOfferBean extends BaseBusiBean {

	private SproductOffer req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... arg0)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] arg0) {
		// TODO Auto-generated method stub
		Do_insertSproductOfferResponse resp = new Do_insertSproductOfferResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
		RsSysIntlOperInfo insertValue = new RsSysIntlOperInfo();
		insertValue.setId(req.getId());
		insertValue.setCountryId(req.getCountryId());
		insertValue.setDescription(req.getDescription());
		insertValue.setEnglishName(req.getEnglishName());
		insertValue.setImsiHead(req.getImsiHead());
		insertValue.setMeasureTypeId(req.getMeasureTypeId());
		insertValue.setOperatorCode(req.getOperatorCode());
		insertValue.setTapVersion(req.getTapVersion());
		insertValue.setTaxTreatment(req.getTaxTreatment());
		insertValue.setReportErrorType(req.getReportErrorType());
		this.context.getComponent(BaseComponent.class).insert(insertValue);
		
		return null;
	}

	@Override
	public void init(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
		req = (SproductOffer)arg0[0];

	}

	@Override
	public void validate(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
	
	}

}
