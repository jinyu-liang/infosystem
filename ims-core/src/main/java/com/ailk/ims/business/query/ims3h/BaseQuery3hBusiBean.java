package com.ailk.ims.business.query.ims3h;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imsinner.entity.AcctQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.BaseQueryRet;
import com.ailk.openbilling.persistence.imsinner.entity.CustQueryReqHolder;
import com.ailk.openbilling.persistence.imsinner.entity.UserQueryReqHolder;

/**
 * @Description 优化内部接口后，三户信息查询接口类的的父类
 * @author yanchuan
 * @Date 2012-2-23
 * @Date 2012-6-25 wuyujie : 批量查询的时候如果找不到3hbean不影响下一个查询，所以加上try catch
 */
public abstract class BaseQuery3hBusiBean extends BaseBusiBean
{

    @Override
    public void init(Object... input) throws BaseException
    {
  
    }

    @Override
    public void validate(Object... input) throws BaseException
    {

    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	BaseQueryReqHolder req = (BaseQueryReqHolder)input[0];
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	if(req instanceof UserQueryReqHolder){
    		UserQueryReqHolder userReq = (UserQueryReqHolder)req;
    		List<Long> user_ids = userReq.getUserReq().getUserIds();
    		List<String> phone_ids = userReq.getUserReq().getPhoneIds();
    		if(user_ids != null)
    		{
    			for(Long id : user_ids){
    			    //2012-6-25 wuyujie : 批量查询的时候如果找不到3hbean不影响下一个查询，所以加上try catch
    			    try{
        			    if(id!=null){
        			        list.add(context.get3hTree().loadUser3hBean(id));
        			    }
    			    }catch(IMS3hNotFoundException e){
    			        continue;
    			    }
        		}
    		}
    		else if(phone_ids != null)
    		{
    			for(String id : phone_ids){
    			    //2012-6-25 wuyujie : 批量查询的时候如果找不到3hbean不影响下一个查询，所以加上try catch
    			    try{
    			        list.add(context.get3hTree().loadUser3hBean(id));
    			    }catch(IMS3hNotFoundException e){
                        continue;
                    }
        		}
    		}
    		
    	}
    	else if(req instanceof AcctQueryReqHolder){
    		AcctQueryReqHolder acctReq = (AcctQueryReqHolder)req;
    		List<Long> ids = acctReq.getAcctReq().getAcctIds();
    		for(Long id : ids){
    		    //2012-6-25 wuyujie : 批量查询的时候如果找不到3hbean不影响下一个查询，所以加上try catch
    		    try{
    		        list.add(context.get3hTree().loadAcct3hBean(id));
    		    }catch(IMS3hNotFoundException e){
                    continue;
                }
    		}
    	}
    	else if(req instanceof CustQueryReqHolder){
    		CustQueryReqHolder custReq = (CustQueryReqHolder)req;
    		List<Long> ids = custReq.getCustReq().getCustIds();
    		for(Long id : ids){
    		    //2012-6-25 wuyujie : 批量查询的时候如果找不到3hbean不影响下一个查询，所以加上try catch
    		    try{
    		        list.add(context.get3hTree().loadCust3hBean(id));
    		    }catch(IMS3hNotFoundException e){
                    continue;
                }
    		}
    		
    	}
    	return list;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
        List<BaseQueryRet> ret = createRet();
        
        setMain((BaseQueryReqHolder)input[0]);
        
        query((BaseQueryReqHolder)input[0],ret);
        
        return new Object[]{ret};
    }
     
    public abstract <T extends BaseQueryRet> void query(BaseQueryReqHolder req,List<T> retList);

    public abstract <T extends BaseQueryRet>List<T> createRet();
    
    public abstract void setMain(BaseQueryReqHolder reqHolder);
    

    @Override
    public void destroy()
    {

    }

}
