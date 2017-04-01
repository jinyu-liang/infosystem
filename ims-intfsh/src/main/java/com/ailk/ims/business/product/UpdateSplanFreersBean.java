package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_updateSplanFreersResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UpdateSplanFreers;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmPlanFreeres;

public class UpdateSplanFreersBean extends BaseBusiBean {

	private UpdateSplanFreers req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_updateSplanFreersResponse resp = new Do_updateSplanFreersResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		DBCondition[] conditions = new DBCondition[]{new DBCondition(PmPlanFreeres.Field.freebieId, req.getFreebieId()),new DBCondition(PmPlanFreeres.Field.planId, req.getPlanId())};
		PmPlanFreeres pmPlanFreeres = DBUtil.querySingle(PmPlanFreeres.class, conditions);
		if(pmPlanFreeres != null){
			PmPlanFreeres upValue = new PmPlanFreeres();
			if(CommonUtil.isValid(req.getPriority())){
				upValue.setPriority(req.getPriority());
			}
			DBUtil.updateByCondition(upValue,conditions);
		}else{
			PmPlanFreeres insertValue = new PmPlanFreeres();
			insertValue.setFreebieId(req.getFreebieId());
			insertValue.setPlanId(req.getPlanId());
			insertValue.setPriority(req.getPriority());
			this.context.getComponent(BaseComponent.class).insert(insertValue);
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (UpdateSplanFreers) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
