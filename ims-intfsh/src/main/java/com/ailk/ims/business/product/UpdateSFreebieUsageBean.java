package com.ailk.ims.business.product;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_updateSFreebieUsageResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.UpdateSFreebieUsage;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pd.entity.PmFreebieUsage;

public class UpdateSFreebieUsageBean extends BaseBusiBean {

	private UpdateSFreebieUsage req;
	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] input) {
		// TODO Auto-generated method stub
		Do_updateSFreebieUsageResponse resp = new Do_updateSFreebieUsageResponse();
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
		PmFreebieUsage pmFreebieUsage = DBUtil.querySingle(PmFreebieUsage.class, conditions);
		if(pmFreebieUsage != null){
			PmFreebieUsage upValue = new PmFreebieUsage();
			if(CommonUtil.isValid(req.getConsType())){
				upValue.setConsType(req.getConsType());
			}
			if(CommonUtil.isValid(req.getPriority())){
				upValue.setPriority(req.getPriority());
			}
			if(CommonUtil.isValid(req.getConsIndicate())){
				upValue.setConsIndicate(req.getConsIndicate());
			}
			if(CommonUtil.isValid(req.getSegId())){
				upValue.setSegId(req.getSegId());
			}
			if(CommonUtil.isValid(req.getExprId())){
				upValue.setExprId(req.getExprId());
			}
			if(CommonUtil.isValid(req.getConsUnit())){
				upValue.setConsUnit(req.getConsUnit());
			}
			DBUtil.updateByCondition(upValue, conditions);
		}else{
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
		}
		return null;
	}

	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		req = (UpdateSFreebieUsage) input[0];

	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub

	}

}
