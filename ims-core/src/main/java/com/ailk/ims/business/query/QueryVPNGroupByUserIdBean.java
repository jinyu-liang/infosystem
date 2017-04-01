package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;

import jef.database.Condition.Operator;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.VPNComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryVPNGroupResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryVPNGroupReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SGroup;
import com.ailk.openbilling.persistence.imsintf.entity.SGroupList;

/**
 * @Description: query vpn group info by userid or phone id
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author xieqr
 * @Date 2012-2-24
 */
public class QueryVPNGroupByUserIdBean extends BaseBusiBean {

	private VPNComponent vpnCmp;
	private String phoneId;
	private Long userId;
	private SQueryVPNGroupReq req;

	@Override
	public void init(Object... input) throws BaseException {
		vpnCmp = context.getComponent(VPNComponent.class);
		req = (SQueryVPNGroupReq) input[0];
		userId = req.getUser_id();
		phoneId = req.getPhone_id();
	}

	@Override
	public void validate(Object... input) throws BaseException {
		if(!CommonUtil.isValid(userId)&&!CommonUtil.isValid(phoneId)){
			throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"user_id or phone_id");
		}

	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {

		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId,phoneId));
        return list;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		IMS3hBean bean  = context.get3hTree().loadUser3hBean(userId, phoneId);
		userId = bean.getUserId();
		phoneId = bean.getPhoneId();
		
		List<CaAccountRv> rvList = vpnCmp.queryGroupAcctRv(userId);
		List<Long> groupIds = new ArrayList<Long>();
		if(CommonUtil.isNotEmpty(rvList)){
			for(CaAccountRv rv:rvList){
				groupIds.add(rv.getAcctId());
			}
		}		
        List<DBCondition> conditions = new ArrayList<DBCondition>();
        conditions.add(new DBCondition(CaAccount.Field.acctId,groupIds,Operator.IN));
        conditions.add(new DBCondition(CaAccount.Field.accountType,EnumCodeDefine.ACCOUNT_TYPE_VPN));
        List<CaAccount> accts = vpnCmp.query(CaAccount.class,conditions.toArray(new DBCondition[conditions.size()]));
        List<SGroup> groups = new ArrayList<SGroup>(); 
        for(CaAccount acct:accts){
        	SGroup group = vpnCmp.queryGroupInfo(acct.getAcctId());
        	if(group!=null&&group.getGroup_id()!=null){
        		groups.add(group);
        	}
        }
		return new Object[] { groups };
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_queryVPNGroupResponse resp =new Do_queryVPNGroupResponse();
		List<SGroup> groups =(List<SGroup>) result[0];
		SGroupList groupList = new SGroupList();
		groupList.setGroup_items(groups.toArray(new SGroup[groups.size()]));
		resp.setGroups(groupList);
		return resp;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
