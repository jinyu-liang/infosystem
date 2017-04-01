package com.ailk.imssh.user.handler;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jef.database.DataObject;
import jef.tools.DateUtils;

import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.user.cmp.UserPortabilityCmp;
import com.ailk.openbilling.persistence.itable.entity.IUserPortability;
/**
 * 携号跨区资料新增、删除、修改,包括CmUserPortability,CmAcctPortability两个实体 .
 * 
 * @author chenxd
 * @date  2013-12-05
 */
public class UserPortabilityHandler extends BaseHandler {

	@Override
	public void handle(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub
		List<? extends DataObject> dataList = dataArr[0];

		UserPortabilityCmp userPortabilityCmp = this.getContext().getCmp(
				UserPortabilityCmp.class);
		for (int j = 0; j < dataList.size(); j++) {
			IUserPortability iUserPortability = (IUserPortability) dataList
					.get(j);
			
			isChangeRegionImmediate(iUserPortability);
			
			ITableUtil.setValue2ContextHolder(null, null, iUserPortability.getUserId());
			int operType = iUserPortability.getOperType();
			switch (operType) {
			case EnumCodeExDefine.OPER_TYPE_INSERT:
				userPortabilityCmp.createUserPortability(iUserPortability);
				break;
			case EnumCodeExDefine.OPER_TYPE_UPDATE:
				userPortabilityCmp.modifyUserPortability(iUserPortability);
				break;
			case EnumCodeExDefine.OPER_TYPE_DELETE:
				userPortabilityCmp.deleteUserPortability(iUserPortability);
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr)
			throws IMSException {
		// TODO Auto-generated method stub

	}

    /**
     * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<? extends jef.database.DataObject>[])
     */
    @Override
    public void initData(List<? extends DataObject>... dataArr) throws IMSException
    {
    }
    
    private void isChangeRegionImmediate(IUserPortability iUserPortability){
    	Date validDate = DateUtils.monthBegin(context.getCommitDate());
    	validDate = DateUtil.offsetDate(validDate, Calendar.MONTH, 1);
    	if(iUserPortability.getValidDate().getTime() != (validDate.getTime())){
    		context.addExtendParam(ConstantExDefine.CHANGE_REGION_IMMEDIATE, true);
    	}
    }

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}

}
