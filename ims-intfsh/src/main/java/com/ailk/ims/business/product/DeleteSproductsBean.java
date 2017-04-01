package com.ailk.ims.business.product;

import java.util.List;

import jef.database.query.NativeQuery;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.DeleteSproducts;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_deleteSproductsResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmProducts;

public class DeleteSproductsBean extends BaseBusiBean {

	private Integer prodId;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_deleteSproductsResponse resp = new Do_deleteSproductsResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub

		
		DBCondition condition =	new DBCondition(PmProducts.Field.prodId,prodId);
		List<PmProducts> list = DBUtil.query(PmProducts.class, condition);
		if(list != null && list.size() > 0){
				DBUtil.deleteByCondition(PmProducts.class, condition);
		
		}else{
			throw IMSUtil.throwBusiException("the data not exist");
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		prodId = (Integer) input[0];

	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
