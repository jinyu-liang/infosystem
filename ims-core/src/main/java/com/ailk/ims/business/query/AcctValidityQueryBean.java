package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.AccountComplex;
import com.ailk.ims.component.BaseLifeCycleComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.LifeCycleQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmResValidity;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctValidityResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctValidityReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;

/**
 * @Description: 查询账户有效期
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author tangjl5
 * @Date 2011-12-6
 */
public class AcctValidityQueryBean extends BaseCancelableBusiBean
{
	private UserComponent userCmp = null;
    @Override
    public void cancel(CancelOrderInfo cancelInfo)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(Object... input) throws IMSException
    {
    	userCmp = context.getComponent(UserComponent.class);
        // TODO Auto-generated method stub

    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryAcctValidityReq req = (SQueryAcctValidityReq)input[0];
        
        if (req.getQuery_type() == null || (req.getQuery_type() != EnumCodeDefine.VALIDITY_QUERY_ACCOUNT && req.getQuery_type() != EnumCodeDefine.VALIDITY_QUERY_USER))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_INVALID, "query_type", "1,0");
        }
        if (!CommonUtil.isValid(req.getAcct_id()) && !CommonUtil.isValid(req.getUser_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAMS_ALL_ISNULL, "acct_id,user_id");
        }
        
        if (req.getQuery_type() == EnumCodeDefine.VALIDITY_QUERY_ACCOUNT && !CommonUtil.isValid(req.getAcct_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "acct_id");
        }
        
        if (req.getQuery_type() == EnumCodeDefine.VALIDITY_QUERY_USER && !CommonUtil.isValid(req.getUser_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "user_id");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQueryAcctValidityReq req = (SQueryAcctValidityReq)input[0];
        CmResValidity validity = null;
        LifeCycleQuery lifeCycleQry = context.getComponent(LifeCycleQuery.class);
        if (req.getQuery_type() == EnumCodeDefine.VALIDITY_QUERY_ACCOUNT)
        {
            validity = context.getComponent(BaseLifeCycleComponent.class).queryValidityByAcctId(req.getAcct_id());
        }
        else
        {
        	//@Date 2012-10-26 yugb :User Story #62519 pool或terminal,idle时允许查询用户状态
        	Integer userSts = context.get3hTree().loadUser3hBean(req.getUser_id()).getUserLifeCycle().getSts();
            if (userSts != EnumCodeDefine.LIFECYCLE_TERMINAL &&  userSts != EnumCodeDefine.LIFECYCLE_POOL)
            {
            	validity = context.getComponent(BaseLifeCycleComponent.class).queryUserValidity(req.getUser_id());
            }
            else
            {
            	validity = lifeCycleQry.queryUserTerminalValidity(req.getUser_id());
            }
        }
        
        return new Object[]{validity};
    }
    
    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryAcctValidityResponse response = new Do_queryAcctValidityResponse();
        if (CommonUtil.isNotEmpty(result))
        {
            CmResValidity validity = (CmResValidity)result[0];
            response.setAcctValidity(validity);
        }
        
        return response;
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SQueryAcctValidityReq req = (SQueryAcctValidityReq)input[0];
		List<IMS3hBean> beans = new ArrayList<IMS3hBean>();
		AccountQuery aq = context.getComponent(AccountQuery.class);
        IMS3hBean bean = context.get3hTree().load3hBean(null,req.getAcct_id(),req.getUser_id(),null);
        try{
        	bean.getAcctId();
        }catch(IMS3hNotFoundException e){
        	//@Date 2012-10-26 yugb :如果用户失效状态,则手动构造一个3hBean
            if(bean.isUser3hBean() && ((User3hBean)bean).isTerminalOrPool())
    		{
    			List<CaAccountRes> resultList =  DBUtil.query(CaAccountRes.class, new OrderCondition[]{new OrderCondition(false,CaAccountRes.Field.soNbr)},
    					null,new DBCondition(CaAccountRes.Field.resourceId,req.getUser_id()));
    			if(CommonUtil.isNotEmpty(resultList))
    			{
    				CaAccount account = userCmp.querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, resultList.get(0).getAcctId()));
    				AccountComplex complex = new AccountComplex();
    				complex.setAccount(account);
    				Acct3hBean acct3hBean = new Acct3hBean(complex);
    				bean.setParent(acct3hBean);
        			((User3hBean)bean).setParentId(account.getAcctId());
    			}
    		}
        }
        beans.add(bean);
        return beans;
	}
}
