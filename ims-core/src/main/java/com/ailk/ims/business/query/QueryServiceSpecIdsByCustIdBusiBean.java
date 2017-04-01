package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jef.database.Condition.Operator;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryServiSpecIdsByCustIdResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * 查询客户下的服务
 * @Description
 * @author ljc
 * @Date 2011-10-20
 */
public class QueryServiceSpecIdsByCustIdBusiBean extends BaseBusiBean
{
    
    private Long custId;
    private UserComponent userCmp;
    private AccountComponent acctCmp;
    @Override
    public void init(Object... input) throws IMSException
    {
        custId=(Long) input[0];
        userCmp=context.getComponent(UserComponent.class);
        acctCmp=context.getComponent(AccountComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if(custId==null){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.CUST_IS_NULL);
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        List<Long> acctIds=acctCmp.queryAcctIdsByCustId(custId);
        if(CommonUtil.isEmpty(acctIds)){
            return null;
        }
        List<CmResource> users=queryUsersByAcctID(acctIds);
        if(CommonUtil.isEmpty(users)){
            return null; 
        }
        List<Long> userIds=new ArrayList<Long>();
        for(CmResource user:users){
            userIds.add(user.getResourceId());
        }
        List<Integer> specIds= queryServieIdsByUserIds(userIds);
        return new Object[]{specIds};
    }

    @SuppressWarnings("unchecked")
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryServiSpecIdsByCustIdResponse resp=new Do_queryServiSpecIdsByCustIdResponse();
        resp.setServieSpecId_list((List<Integer>) result[0]);
        return resp;
    }

    @Override
    public void destroy()
    {

    }
    
    @SuppressWarnings("rawtypes")
    private List<CmResource> queryUsersByAcctID(List<Long> acctIds)
    {
    	List<CaAccountRes> caResList = userCmp.query(CaAccountRes.class,new DBCondition(CaAccountRes.Field.acctId, acctIds, Operator.IN),
																		new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_BELONG));
    	List<CmResource> resList = new ArrayList<CmResource>();
    	for(CaAccountRes res : caResList){
//    		CmResource user = userCmp.queryUserByUserID(res.getResourceId());
//    		if(user != null){
//    			resList.add(user);
//    		}   
    		//2012-08-28 linzt 整理组件方法  采用load3hBean
    		try{
    			resList.add(context.get3hTree().loadUser3hBean(res.getResourceId()).getUser());
    		}
    		catch(IMS3hNotFoundException e)
    		{
    			imsLogger.error(e,e);
    		}
    	}	
    	return resList;
    }
    
    private List<Integer> queryServieIdsByUserIds(List<Long> userIds){
        List<CmResServCycle> servCycles=userCmp.query(CmResServCycle.class,new DBCondition(CmResServCycle.Field.resourceId, userIds,Operator.IN));
        if(CommonUtil.isEmpty(servCycles)){
            return null;
        }
        Set<Integer> set=new HashSet<Integer>();
        for(CmResServCycle serv:servCycles){
        	set.add(serv.getServiceSpecId());
        }
        List<Integer> specIds=new ArrayList<Integer>();
        for(Integer id:set){
            specIds.add(id);
        }
        return specIds;
    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadCust3hBean(custId));
        return list;
	}
}
