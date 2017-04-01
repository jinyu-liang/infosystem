package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.DeleteSplanFreers;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_deleteSplanFreersResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmPlanFreeres;

public class DeleteSplanFreersBean extends BaseBusiBean {

	private DeleteSplanFreers req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_deleteSplanFreersResponse resp = new Do_deleteSplanFreersResponse();
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
		List<PmPlanFreeres> list = DBUtil.query(PmPlanFreeres.class, conditions);
		if(list != null && list.size() > 0){
			DBUtil.deleteByCondition(PmPlanFreeres.class, conditions);
		}else{
			throw IMSUtil.throwBusiException("the data not exist");
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (DeleteSplanFreers) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
