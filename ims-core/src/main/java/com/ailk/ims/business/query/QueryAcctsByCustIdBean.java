package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.AccountRelationComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctsByCustIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctInfoReq;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryAcctsByCustIdReturn;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @Description 根据客户id，查询所管关联的所有帐户及帐户与子账户的的层级关系
 * @author yanchuan
 * @Date 2011-10-22
 */
public class QueryAcctsByCustIdBean extends BaseBusiBean
{
    private Long custId;

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        SQueryAcctInfoReq req = (SQueryAcctInfoReq) input[0];
        custId = req.getCust_id();

        if (!CommonUtil.isValid(custId))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "custId");
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        AccountRelationComponent acctRelCmp = context.getComponent(AccountRelationComponent.class);
        List<SQueryAcctsByCustIdReturn> list = new ArrayList<SQueryAcctsByCustIdReturn>();
        SQueryAcctsByCustIdReturn acctsReturn = null;
        List<CaAccount> acctIds = queryAcctIds(custId);
        List<Long> acct_list = new ArrayList<Long>();
        if(CommonUtil.isEmpty(acctIds))
            return new Object[] { list };
        for (CaAccount account : acctIds)
        {
            acct_list.add(account.getAcctId());
        }
        for (CaAccount account : acctIds)
        {
            acctsReturn = new SQueryAcctsByCustIdReturn();
            // 查询账户的父账户
            Long parentId = acctRelCmp.queryFatherAcctId(account.getAcctId());
            acctsReturn.setAccount(account);
            if (acct_list.contains(parentId))
            {
                acctsReturn.setParent_id(parentId);
            }
            else
                acctsReturn.setParent_id(new Long(0));
            list.add(acctsReturn);
        }

        return new Object[] { list };
    }

    /**
     * 通过客户id查询帐户并根据帐户名称排序
     * 
     * @author yanchuan 2011-10-25
     * @param custId
     * @return
     */
    private List<CaAccount> queryAcctIds(Long custId)
    {
        AccountComponent acctCmp = context.getComponent(AccountComponent.class);        
        return acctCmp.queryAccountsByCustId(custId);
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        List<SQueryAcctsByCustIdReturn> accounts = (List<SQueryAcctsByCustIdReturn>) result[0];
        Do_queryAcctsByCustIdResponse resp = new Do_queryAcctsByCustIdResponse();
        resp.setAccount_list(accounts);
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
        list.add(context.get3hTree().loadCust3hBean(custId));
        return list;
	}

}
