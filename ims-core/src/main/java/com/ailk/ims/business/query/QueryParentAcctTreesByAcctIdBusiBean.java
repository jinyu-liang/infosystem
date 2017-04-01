package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.AccountRelationComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.imsinner.entity.AcctTree;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryParentAcctTreesResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 向上查询层级树
 * 
 * @Description
 * @author luojb
 * @Date 2011-11-24
 */
public class QueryParentAcctTreesByAcctIdBusiBean extends BaseBusiBean
{
    private Long acctId = null;
    private AccountRelationComponent arc = null;

    @Override
    public void init(Object... input) throws IMSException
    {
        acctId = (Long) input[0];
        arc = context.getComponent(AccountRelationComponent.class);
    }

    @Override
    public void validate(Object... input) throws IMSException
    {
        if (!CommonUtil.isValid(acctId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, acctId);
        }
    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        List<CaAccountRel> caRelList = context.getComponent(AccountRelationComponent.class).getAllParentAcctTreeByAcctId(acctId);
        if (CommonUtil.isEmpty(caRelList))
        {
            return null;
        }

        List<AcctTree> parentAcctTrees = new ArrayList<AcctTree>();

        // 对每一个上级账户取其直接下级所有账户
        for (CaAccountRel ca : caRelList)
        {
            List<CaAccountRel> relList = arc.query(CaAccountRel.class,
                    new DBCondition(CaAccountRel.Field.groupId, ca.getGroupId()), new DBCondition(
                            CaAccountRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_GCA_RELATION));
            if (CommonUtil.isEmpty(relList))
            {
                continue;
            }
            for (CaAccountRel caRel : relList)
            {
                AcctTree acctTree = new AcctTree();
                acctTree.setParent_acct_id(caRel.getGroupId());
                acctTree.setAcct_id(caRel.getRelGroupId());
                parentAcctTrees.add(acctTree);
            }
        }
        return new Object[] { parentAcctTrees };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryParentAcctTreesResponse resp = new Do_queryParentAcctTreesResponse();
        if (!CommonUtil.isEmpty(result))
        {
            resp.setParentAcctTrees((List<AcctTree>) result[0]);
        }
        return resp;
    }

    @Override
    public void destroy()
    {
        acctId = null;
        arc = null;
    }

    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadAcct3hBean(acctId));
        return list;
    }

}
