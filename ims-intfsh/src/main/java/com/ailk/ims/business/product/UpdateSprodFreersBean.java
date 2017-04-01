package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_UpdateSprodFreersResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_updateSproductsResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UpdateSprodFreers;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmProdFreeres;

public class UpdateSprodFreersBean extends BaseBusiBean {

	private UpdateSprodFreers req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_UpdateSprodFreersResponse resp = new Do_UpdateSprodFreersResponse();
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		DBCondition[] conditions = new DBCondition[]{new DBCondition(PmProdFreeres.Field.planId, req.getPlanId()),new DBCondition(PmProdFreeres.Field.prodId, req.getProdId()),new DBCondition(PmProdFreeres.Field.freebieId, req.getFreebieId())};
		PmProdFreeres pmProdFreeres = DBUtil.querySingle(PmProdFreeres.class, conditions);
		if(pmProdFreeres != null){
			PmProdFreeres upValue = new PmProdFreeres();
			if(CommonUtil.isValid(req.getValidCycle())){
				upValue.setValidCycle(req.getValidCycle());
			}
			if(CommonUtil.isValid(req.getExpireCycle())){
				upValue.setExpireCycle(req.getExpireCycle());
			}
			if(CommonUtil.isValid(req.getResCount())){
				upValue.setResCount(req.getResCount());
			}
			if(CommonUtil.isValid(req.getNumerator())){
				upValue.setNumerator(req.getNumerator());
			}
			if(CommonUtil.isValid(req.getDenominator())){
				upValue.setDenominator(req.getDenominator());
			}
			if(CommonUtil.isValid(req.getExpireMode())){
				upValue.setExpireMode(req.getExpireMode());
			}
			if(CommonUtil.isValid(req.getHalfType())){
				upValue.setHalfType(req.getHalfType());
			}
			if(CommonUtil.isValid(req.getCycleType())){
				upValue.setCycleType(req.getCycleType());
			}
			if(CommonUtil.isValid(req.getUseUnit())){
				upValue.setUseUnit(req.getUseUnit());
			}
			if(CommonUtil.isValid(req.getResLimit())){
				upValue.setResLimit(req.getResLimit());
			}
			if(CommonUtil.isValid(req.getRoleLimit())){
				upValue.setRoleLimit(req.getRoleLimit());
			}
			if(CommonUtil.isValid(req.getAutoUpgrade())){
				upValue.setAutoUpgrade(req.getAutoUpgrade());
			}
			DBUtil.updateByCondition(upValue, conditions);
		}else{
			PmProdFreeres inserValue = new PmProdFreeres();
			inserValue.setPlanId(req.getPlanId());
			inserValue.setProdId(req.getProdId());
			inserValue.setFreebieId(req.getFreebieId());
			inserValue.setValidCycle(req.getValidCycle());
			inserValue.setExpireCycle(req.getExpireCycle());
			inserValue.setResCount(req.getResCount());
			inserValue.setNumerator(req.getNumerator());
			inserValue.setDenominator(req.getDenominator());
			inserValue.setExpireMode(req.getExpireMode());
			inserValue.setHalfType(req.getHalfType());
			inserValue.setCycleType(req.getCycleType());
			inserValue.setUseUnit(req.getUseUnit());
			inserValue.setResLimit(req.getResLimit());
			inserValue.setRoleLimit(req.getRoleLimit());
			inserValue.setAutoUpgrade(req.getAutoUpgrade());
			this.context.getComponent(BaseComponent.class).insert(inserValue);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (UpdateSprodFreers) input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
