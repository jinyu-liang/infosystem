package com.ailk.imssh.pbx.cmp;

import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.common.DBCondition;
import com.ailk.imssh.common.cmp.BaseCmp;
import com.ailk.openbilling.persistence.cust.entity.CmUserPbx;
import com.ailk.openbilling.persistence.itable.entity.IUserPbx;

/**
 * @Date 2013-11-27
 * @author dingjiang3
 * 
 */
public class UserPbxCmp extends BaseCmp {
	public void createUserPbx(IUserPbx iUserPbx) {
		CmUserPbx pbx = convert(iUserPbx, false);
		super.insert(pbx);
	}

	public void updateUserPbx(IUserPbx iUserPbx) {
		CmUserPbx cmUserPbx = convert(iUserPbx, true);
		this.updateByCondition(cmUserPbx, new DBCondition(CmUserPbx.Field.resourceId, iUserPbx.getUserId()), new DBCondition(
				CmUserPbx.Field.accessNumber, iUserPbx.getAccessNumber()));
	}

	public void deleteUserPbx(IUserPbx iUserPbx) {
		deleteByCondition(CmUserPbx.class, new DBCondition(CmUserPbx.Field.resourceId, iUserPbx.getUserId()), new DBCondition(
				CmUserPbx.Field.accessNumber, iUserPbx.getAccessNumber()));
	}

	public CmUserPbx convert(IUserPbx iUserPbx, boolean isUpdateOper) {
		CmUserPbx cmUserPbx = new CmUserPbx();
		cmUserPbx.setResourceId(iUserPbx.getUserId());
		cmUserPbx.setMscId(iUserPbx.getMscId());
		cmUserPbx.setAreaCode(iUserPbx.getAreaCode());
		String inTrunk = iUserPbx.getInTrunk();
		if (inTrunk.equals("0000"))
			inTrunk = Integer.toString(Integer.parseInt(inTrunk));
		cmUserPbx.setInTrunk(inTrunk);
		String outTrunk = iUserPbx.getInTrunk();
		if (outTrunk.equals("0000"))
			outTrunk = Integer.toString(Integer.parseInt(outTrunk));
		cmUserPbx.setOutTrunk(outTrunk);
		cmUserPbx.setAccessNumber(iUserPbx.getAccessNumber());
		cmUserPbx.setMatchingAccessNumber(iUserPbx.getMatchingAccessNumber());
		cmUserPbx.setSoDate(iUserPbx.getCommitDate());
		cmUserPbx.setSoNbr(context.getSoNbr());

		if (!isUpdateOper) {
			cmUserPbx.setValidDate(iUserPbx.getValidDate());
			cmUserPbx.setCreateDate(iUserPbx.getCommitDate());
		}
		cmUserPbx.setRegionCode(iUserPbx.getRegionCode());
		cmUserPbx.setExpireDate(iUserPbx.getExpireDate());
		return cmUserPbx;

	}

	public void beforeHandle(List<? extends DataObject> dataList) {
		// ITableUtil.cleanRequestParamter();
		// IUserPbx iUserPbx = (IUserPbx) dataList.get(0);
		// delete_noValid(CmUserPbx.class, new
		// DBCondition(CmUserPbx.Field.resourceId, iUserPbx.getUserId()),
		// new DBCondition(CaResourceInv.Field.validDate,
		// iUserInv.getCommitDate(), Operator.GREAT), new DBCondition(
		// CaResourceInv.Field.expireDate, iUserInv.getCommitDate(),
		// Operator.GREAT));

	}

}
