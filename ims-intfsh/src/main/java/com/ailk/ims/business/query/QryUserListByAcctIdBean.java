package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.shinner.entity.Do_qryUserListByAcctIdResponse;
import com.ailk.openbilling.persistence.shinner.entity.QryUserListByAcctIdReq;
import com.ailk.openbilling.persistence.shinner.entity.SUserExt;

/**
 * @description:查询账户下的所有用户
 * @author caohm5
 * @date:2012-09-03 20:14:00
 *
 */

public class QryUserListByAcctIdBean extends BaseBusiBean {
	
	private QryUserListByAcctIdReq req;

    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(QryUserListByAcctIdReq)input[0];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if(null==req){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "QryUserListByAcctIdReq");
    	}
    	if(null==req.getAcct_id()){
    		throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "acct_id");
    	}
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
//    	List<IMS3hBean> bean=new ArrayList<IMS3hBean>();
//    	bean.add(context.get3hTree().loadAcct3hBean(req.getAcct_id()));
//    	return bean;
    	return null;
    	
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
    	//校验三户
    	context.getComponent(CheckComponentSH.class).check3HParam(null, req.getAcct_id(), null, null);
    	//查询用户
    	List<CmResource> cmresoucrList=context.getComponent(UserQuery.class).queryUsersByAcctID(req.getAcct_id());
    	
    	if(cmresoucrList==null||cmresoucrList.isEmpty()){
    	    return new Object[]{null};
    	}
    	List<SUserExt> userList=new ArrayList<SUserExt>();
    	for(CmResource cm:cmresoucrList){
    		if(null==cm){
    			continue;
    		}
    		SUserExt userExt=new SUserExt();
    		userExt.setResource_id(cm.getResourceId());
    		//查询手机号码
    		if(req.getIs_res_identity()!=null&&req.getIs_res_identity()==true){
    			String phoneId=context.getComponent(UserQuery.class).queryPhoneIdByUserId(cm.getResourceId());
    			userExt.setPhone_id(phoneId);
    		}
    		userList.add(userExt);
    	}
    	return new Object[]{userList};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_qryUserListByAcctIdResponse response=new Do_qryUserListByAcctIdResponse();
		List<SUserExt> userList =(List<SUserExt>)result[0];
		response.setUserExt(userList);
		return response;
    }
    @Override
    public void destroy()
    {
    }
}
