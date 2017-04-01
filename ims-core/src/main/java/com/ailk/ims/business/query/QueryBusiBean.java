package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.BaseQueryBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.complex.AccountComplex;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.Acct3hBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.Do_queryResponse;
import com.ailk.openbilling.persistence.imsintf.entity.QueryType;
import com.ailk.openbilling.persistence.imsintf.entity.QueryTypeList;
import com.ailk.openbilling.persistence.imsintf.entity.SQueryParam;

/**
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author
 * @Date 2011-09-06 liuyang ,增加一些验证，避免继承的方法doQuery重复验证， 返回结构有错，对其进行修改 2011-9-15 liuyang,start_date 不能为空
 * @Date 2011-11-21 hunan ,修改了querytypelist的结构。并增加校验
 * @Date wukl 2012-03-21 Task #41876 Onwer_Type默认值修改
 * @Date 2012-4-3 luojb 必传信息，加验证
 * @Date 2012-05-14 wangdw5 设值前添加空校验,空才设值
 */
public class QueryBusiBean extends BaseBusiBean
{

    private QueryTypeList typeList = null;
    private SQueryParam queryParam = null;
    private UserComponent userCmp = null;
    

    @Override
    public void init(Object... input) throws IMSException
    {
        typeList = (QueryTypeList) input[0];
        queryParam = (SQueryParam) input[1];
        userCmp = context.getComponent(UserComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if (typeList == null || CommonUtil.isEmpty(typeList.getQueryType_Item()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.QUERY_TYPE_IS_INVAILD);
        }
        else
        {
            for (QueryType type : typeList.getQueryType_Item())
            {
                if (type != null && type.getQuery_type() == null)
                {
                    throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "QueryType");
                }
            }
        }
        
        // 2012-4-3 luojb 必传信息，加验证
        if(queryParam == null || queryParam.getAcct_id() == null && queryParam.getUser_id() == null && CommonUtil.isEmpty(queryParam.getPhone_id()))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"queryParam:acct_id,user_id,phone_id");
        }

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        Do_queryResponse result = new Do_queryResponse();
        for (QueryType type : typeList.getQueryType_Item())
        {
            if (type == null)
            {
                continue;
            }
            BaseQueryBean queryBean = (BaseQueryBean) BusiUtil.getQueryTypeBean(context.getServiceName(),
                    context.getMethodName(), type.getQuery_type());
            if (queryBean == null)
            {
                throw new IMSException("Query bean with type \"" + type.getQuery_type() + "\" is not defined");
            }
            queryBean.setContext(context);

            IMS3hBean hbean = context.get3hTree().load3hBean(null, queryParam.getAcct_id(), queryParam.getUser_id(),
                    queryParam.getPhone_id());
            //@Date 2012-05-14 wangdw5 设值前添加空校验,空才设值并且ACCTID取billAcctId
            if(!CommonUtil.isValid(queryParam.getUser_id()))
            	queryParam.setUser_id(hbean.getUserId());
            if(!CommonUtil.isValid(queryParam.getPhone_id()))
            	queryParam.setPhone_id(hbean.getPhoneId());
            if(!CommonUtil.isValid(queryParam.getAcct_id()) && hbean.isUser3hBean())
            {
            	User3hBean user3h = (User3hBean)hbean;
            	try{
            		//billAcctId->acctId
            		queryParam.setAcct_id(user3h.getAcctId());
            	}catch(IMS3hNotFoundException e){
            		//@Date 2012-10-26 yugb :User Story #62519 如果用户的状态是失效状态,则不需要抛异常
            		if(!user3h.isTerminalOrPool())
            			IMSUtil.throw3hBeanNotFoundException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, user3h.getUserId());
            		CaAccountRes caAccountRes  = userCmp.queryHistoryCaAcctRes(user3h.getUserId());
            		if(caAccountRes != null)
            			queryParam.setAcct_id(caAccountRes.getAcctId());
            	}
            }

            // wukl 2012-03-21 Task #41876 Onwer_Type默认值修改
            Integer ownerType = context.getComponent(AccountComponent.class).getOwnerType(queryParam.getOwner_type(),
                    queryParam.getUser_id(), queryParam.getPhone_id(), queryParam.getAcct_id());
            queryParam.setOwner_type(ownerType);
            

            queryBean.doQuery(queryParam, result);
        }
        return new Object[] { result };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        return (BaseResponse) result[0];
    }

    @Override
    public void destroy()
    {

    }

    public List<com.ailk.ims.ims3h.IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	queryParam = (SQueryParam) input[1];
    	AccountQuery aq = context.getComponent(AccountQuery.class);
    	Long acctId = queryParam.getAcct_id();
    	Long userId = queryParam.getUser_id();
    	String phoneId = queryParam.getPhone_id();
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	IMS3hBean hbean = context.get3hTree().load3hBean(null, acctId, userId, phoneId);
    	try{
    		hbean.getAcctId();
    	}catch(Exception e){
    		//@Date 2012-10-26 yugb :如果用户失效状态,则手动构造一个3hBean
    		if(hbean.isUser3hBean() && ((User3hBean)hbean).isTerminalOrPool())
    		{
    			List<CaAccountRes> resultList =  DBUtil.query(CaAccountRes.class, new OrderCondition[]{new OrderCondition(false,CaAccountRes.Field.soNbr)},
    					null,new DBCondition(CaAccountRes.Field.resourceId,hbean.getUserId()));
    			if(CommonUtil.isNotEmpty(resultList))
    			{
    				 CaAccount account = userCmp.querySingle(CaAccount.class, new DBCondition(CaAccount.Field.acctId, acctId));
    				 AccountComplex complex = new AccountComplex();
    				 complex.setAccount(account);
    				 Acct3hBean acctBean = new Acct3hBean(complex);
    				 hbean.setParent(acctBean);
        			((User3hBean)hbean).setParentId(account.getAcctId());
    			}
    		}
    	}
          list.add(hbean);
          return list;
    }
}
