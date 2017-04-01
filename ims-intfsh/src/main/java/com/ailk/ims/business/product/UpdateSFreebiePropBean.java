package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_deleteSfreebiePropResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_updateSfreebiePropResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UpdateSfreebieProp;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmFreebieProp;

public class UpdateSFreebiePropBean extends BaseBusiBean {
	private UpdateSfreebieProp req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_updateSfreebiePropResponse resp = new Do_updateSfreebiePropResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		DBCondition condition = new DBCondition(PmFreebieProp.Field.freebieId, req.getFreebieId());
		PmFreebieProp pmFreebieProp = DBUtil.querySingle(PmFreebieProp.class, condition);
		if(pmFreebieProp != null){
			PmFreebieProp upValue = new PmFreebieProp();
			if(CommonUtil.isValid(req.getPriority())){
				upValue.setPriority(req.getPriority());
			}
			if(CommonUtil.isValid(req.getUnitCycle())){
				upValue.setUnitCycle(req.getUnitCycle());
			}
			if(CommonUtil.isValid(req.getUnitDes())){
				upValue.setUnitDes(req.getUnitDes());
			}
			DBUtil.updateByCondition(upValue, condition);
		}else{
			PmFreebieProp insertValue = new PmFreebieProp();
			insertValue.setFreebieId(req.getFreebieId());
			insertValue.setPriority(req.getPriority());
			insertValue.setUnitCycle(req.getUnitCycle());
			insertValue.setUnitDes(req.getUnitDes());
			this.context.getComponent(BaseComponent.class).insert(insertValue);
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (UpdateSfreebieProp) input[0];

	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
