package com.ailk.ims.business.product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jef.tools.support.JefBase64;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.DeleteSprodFreers;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_DeleteSprodFreersResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmProdFreeres;

public class DeleteSprodFreersBean extends BaseBusiBean {

	private DeleteSprodFreers req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_DeleteSprodFreersResponse resp = new Do_DeleteSprodFreersResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
//		PmProdFreeres pmProdFreeres = new PmProdFreeres();
//		pmProdFreeres.getQuery().addCondition(PmProdFreeres.Field.planId, req.getPlanId());
//		pmProdFreeres.getQuery().addCondition(PmProdFreeres.Field.prodId, req.getProdId());
//		pmProdFreeres.getQuery().addCondition(PmProdFreeres.Field.freebieId, req.getFreebieId());
//		try {
//		List<PmProdFreeres>	list = DBUtil.getDBClient().select(pmProdFreeres);
//		System.out.println("*********"+list.toArray());
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//		
	DBCondition[] conditions = new DBCondition[]{new DBCondition(PmProdFreeres.Field.planId, req.getPlanId()),
			new DBCondition(PmProdFreeres.Field.prodId, req.getProdId()),new DBCondition(PmProdFreeres.Field.freebieId, req.getFreebieId())};
		List<PmProdFreeres> list2 = DBUtil.query(PmProdFreeres.class, conditions);
		if(list2 != null && list2.size() > 0){
			DBUtil.deleteByCondition(PmProdFreeres.class, conditions);
		}else{
			throw IMSUtil.throwBusiException("the data not exist");
		}

		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (DeleteSprodFreers) input[0];
	}

}
