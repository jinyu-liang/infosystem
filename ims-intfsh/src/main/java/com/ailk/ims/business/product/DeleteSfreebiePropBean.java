package com.ailk.ims.business.product;

import java.sql.SQLException;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.easyframe.web.common.exception.DataAccessException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.DeleteSfreebieProp;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_deleteSfreebiePropResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmFreebieProp;

public class DeleteSfreebiePropBean extends BaseBusiBean {

	private DeleteSfreebieProp req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_deleteSfreebiePropResponse resp = new Do_deleteSfreebiePropResponse();
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
		List<PmFreebieProp> list = DBUtil.query(PmFreebieProp.class, condition);
		if(list != null && list.size() > 0){
			DBUtil.deleteByCondition(PmFreebieProp.class, condition);
		}else{
			throw new DataAccessException("the data not exist ");
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (DeleteSfreebieProp) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
