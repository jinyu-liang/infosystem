package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.AccountRelationComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.imsinner.entity.AcctTree;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryAcctTreesByGroupIdResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 查询集团账户树
 * 
 * @Description
 * @author ljc
 * @Date 2011-10-21
 */
public class QueryAcctTreesByGroupIdBusiBean extends BaseBusiBean
{

    private Long acctId;
    private AccountComponent acctCmp;

    public void init(Object... input) throws IMSException
    {
        acctId = (Long) input[0];
        acctCmp = context.getComponent(AccountComponent.class);
    }

    public void validate(Object... input) throws IMSException
    {
        if (acctId == null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.ACCOUNT_IS_NULL);
        }
        CaAccount acct =context.get3hTree().loadAcct3hBean(acctId).getAccount();
        
//        else if (acct.getAccountType() != EnumCodeDefine.ACCOUNT_TYPE_GROUP)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.CARELATION_NOT_CORP_CA, acctId);
//        }
    }

    public Object[] doBusiness(Object... input) throws IMSException
    {
        List<AcctTree> acctTrees = new ArrayList<AcctTree>();
        // 查询到集团虚账户
        // Long groupGca=relCmp.getCorporationGCAId(acctId);
        List<CaAccountRel> caRelList = context.getComponent(AccountRelationComponent.class).getAllAcctTreeByAcctId(acctId,EnumCodeDefine.ACCOUNT_GCA_RELATION);
        if (!CommonUtil.isEmpty(caRelList))
        {
            for (CaAccountRel rel : caRelList)
            {
                AcctTree tree = new AcctTree();
                tree.setParent_acct_id(rel.getGroupId());
                tree.setAcct_id(rel.getRelGroupId());
                acctTrees.add(tree);
            }
        }

        return new Object[] { acctTrees };
    }

    // private Long getRelAccountIdByGca(Long acctId){
    // CaAccountRel rel=new CaAccountRel();
    // rel.setRelationshipType(EnumCodeDefine.ACCOUNT_CA_GCA_RELATION);
    // rel.setAcctId(acctId);
    // rel=acctCmp.querySingle(rel);
    // return rel==null?null:rel.getRelAcctId();
    // }

    @SuppressWarnings("unchecked")
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryAcctTreesByGroupIdResponse resp = new Do_queryAcctTreesByGroupIdResponse();
        resp.setAcct_tree_list((List<AcctTree>) result[0]);
        return resp;
    }

    public void destroy()
    {
        acctCmp = null;
    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
    	List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	list.add(context.get3hTree().loadAcct3hBean(acctId));
    	return list;
	}

  
}
