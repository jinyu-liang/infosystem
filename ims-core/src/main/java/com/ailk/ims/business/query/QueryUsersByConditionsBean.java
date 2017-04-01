package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryUsersByConditionsResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUsersReq;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryUsersReturn;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmProductOffering;

/**
 * @Description 根据用户id查询产品列表
 * @author 严川
 * @Date 2011-10-20
 * @Date 2012-07-23 yanchuan #52498 : 删除co_prod_inv_obj表的处理逻辑
 */
public class QueryUsersByConditionsBean extends BaseBusiBean
{

    @Override
    public void init(Object... input) throws IMSException
    {
    }

    @Override
    public void validate(Object... input) throws IMSException
    {

    }

    @Override
    public Object[] doBusiness(Object... input) throws IMSException
    {
        SQueryUsersReq userReq = (SQueryUsersReq) input[0];
        Long cust_id = userReq.getCust_id();
        Long acct_id = userReq.getAcct_id();
        Long user_id = userReq.getUser_id();
        String identity = userReq.getIdentity();
        Integer identity_type = userReq.getIdentity_type();
        Integer offer_id = userReq.getMain_promotion();
        List<SQueryUsersReturn> returnList = new ArrayList<SQueryUsersReturn>();
        DBJoinCondition joinCondition = new DBJoinCondition(CmResource.class);
        joinCondition.leftJoin(CmResIdentity.class, new DBRelation(CmResource.Field.resourceId, CmResIdentity.Field.resourceId),
                new DBRelation(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
        joinCondition.leftJoin(CaAccountRes.class, new DBRelation(CmResIdentity.Field.resourceId, CaAccountRes.Field.resourceId));
        joinCondition.leftJoin(CaCustomerRel.class, new DBRelation(CaAccountRes.Field.acctId, CaCustomerRel.Field.acctId));
        joinCondition.leftJoin(CmCustomer.class, new DBRelation(CaCustomerRel.Field.custId, CmCustomer.Field.custId));
        joinCondition.leftJoin(CoProd.class, new DBRelation(CmResource.Field.resourceId, CoProd.Field.objectId),
                           new DBRelation(CoProd.Field.objectType, EnumCodeDefine.PROD_OBJECTTYPE_DEV));
        joinCondition.leftJoin(PmProductOffering.class, new DBRelation(CoProd.Field.productOfferingId,
                PmProductOffering.Field.productOfferingId), new DBRelation(PmProductOffering.Field.isMain,
                EnumCodeDefine.PRODUCT_MAIN));
        List<DBCondition> conds = new ArrayList<DBCondition>();
        DBCondition dbcond = null;
        if (CommonUtil.isValid(user_id))
        {
            dbcond = new DBCondition(CmResource.Field.resourceId, user_id);
            conds.add(dbcond);
        }
        if (CommonUtil.isValid(acct_id))
        {
            dbcond = new DBCondition(CaAccountRes.Field.acctId, acct_id);
            conds.add(dbcond);
        }
        if (CommonUtil.isValid(cust_id))
        {
            dbcond = new DBCondition(CaCustomerRel.Field.custId, cust_id);
            conds.add(dbcond);
        }
        if (CommonUtil.isValid(identity))
        {
            dbcond = new DBCondition(CmResIdentity.Field.identity, identity);
            conds.add(dbcond);
        }
        if (CommonUtil.isValid(identity_type))
        {
            dbcond = new DBCondition(CmResIdentity.Field.identityType, identity_type);
            conds.add(dbcond);
        }
        if (CommonUtil.isValid(offer_id))
        {
            dbcond = new DBCondition(PmProductOffering.Field.productOfferingId, offer_id);
            conds.add(dbcond);
        }
        DBCondition dbcond_arry[] = conds.toArray(new DBCondition[conds.size()]);

        BaseComponent baseComp = context.getComponent(BaseComponent.class);
        if (!CommonUtil.isEmpty(conds))
        {
            List<Map> busiServiceResult = (List<Map>) baseComp.queryJoin(joinCondition, dbcond_arry);
            List<Long> userList = new ArrayList<Long>();
            for (int i = 0; busiServiceResult != null && i < busiServiceResult.size(); i++)
            {
                SQueryUsersReturn user = new SQueryUsersReturn();
                Map itemMap = busiServiceResult.get(i);
                CmResIdentity cmRes = (CmResIdentity) itemMap.get(CmResIdentity.class);
                if(cmRes == null)
                    continue;
                Long userId = cmRes.getResourceId();
                if (userList.contains(userId))
                {
                    continue;
                }
                else
                {
                    user.setCmResIdentity(cmRes);
                    CoProd invOjb = (CoProd) itemMap.get(CoProd.class);
                    PmProductOffering pmProdOffer = (PmProductOffering)itemMap.get(PmProductOffering.class);
                    if (invOjb != null)
                        user.setProduct_id(invOjb.getProductId());
                    if (pmProdOffer != null)
                        user.setCmProdOffering(pmProdOffer);
                    userList.add(userId);
                    returnList.add(user);
                }
            }
        }
        return new Object[] { returnList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryUsersByConditionsResponse resp = new Do_queryUsersByConditionsResponse();
        List<SQueryUsersReturn> list = (List<SQueryUsersReturn>) result[0];
        resp.setUser_list(list);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		SQueryUsersReq userReq = (SQueryUsersReq) input[0];
	    Long cust_id = userReq.getCust_id();
	    Long acct_id = userReq.getAcct_id();
	    Long user_id = userReq.getUser_id();
	    if (!CommonUtil.isValid(cust_id)&& !CommonUtil.isValid(acct_id) && !CommonUtil.isValid(user_id))
        {
           return null;
        }
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().load3hBean(cust_id, acct_id, user_id, null));
        return list;
	}

}
