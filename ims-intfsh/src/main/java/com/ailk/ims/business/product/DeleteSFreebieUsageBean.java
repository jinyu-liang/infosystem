package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.exception.DataAccessException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.DeleteSFreebieUsage;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_DeleteSFreebieUsageResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmFreebieUsage;

public class DeleteSFreebieUsageBean extends BaseBusiBean {

	private DeleteSFreebieUsage req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_DeleteSFreebieUsageResponse resp = new Do_DeleteSFreebieUsageResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		DBCondition[] conditions = new DBCondition[]{new DBCondition(PmFreebieUsage.Field.freebieId, req.getFreebieId()),new DBCondition(PmFreebieUsage.Field.targetItem, req.getTargetItem())};
		List<PmFreebieUsage> list = DBUtil.query(PmFreebieUsage.class, conditions);
		if(list != null && list.size() > 0){
			DBUtil.deleteByCondition(PmFreebieUsage.class, conditions);
		}else{
			throw new DataAccessException("the data not exist");  
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (DeleteSFreebieUsage) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
