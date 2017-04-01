package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUsersByAcctIdResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;
/**
 * 根据账户id查询用户列表
 * @Description
 * @author ljc
 * @Date 2011-10-19
 */
public class QueryUsersByAcctIdBusiBean extends BaseBusiBean
{
    private Long acctId;
    private UserComponent userCmp;
    @Override
    public void init(Object... input) throws IMSException
    {
        acctId=(Long) input[0];
        userCmp=context.getComponent(UserComponent.class);

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if(acctId==null){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_IS_NULL);
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {   
        List<CmResource> users=null;
        Integer relationShip=(Integer) input[1];
        if(relationShip==null){
            users=userCmp.queryUsersByAcctID(acctId);
        }else{
            users=userCmp.queryUsersByAcctID(acctId,relationShip);
        }
        return new Object[]{users};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryUsersByAcctIdResponse resp=new Do_queryUsersByAcctIdResponse();
        List<CmResource> resList=(List<CmResource>) result[0];
        List<SUser> userList=new ArrayList<SUser>();
        if(!CommonUtil.isEmpty(resList)){
            for(CmResource user:resList){
                userList.add(userCmp.querySUserByUserId(user.getResourceId()));
            }
        }
        if(userList.size()>0){
            resp.setUser_list(userList);
        }
        return resp;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadAcct3hBean(acctId));
		return list;
	}
}

