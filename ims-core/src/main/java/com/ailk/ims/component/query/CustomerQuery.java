package com.ailk.ims.component.query;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.cust.entity.CmCustExt;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmParty;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmPartyRole;

/**
 * @Description:客户相关的信息查询的方法类
 * @author wangjt
 * @Date 2011-12-21
 */
public class CustomerQuery extends BaseComponent
{
    public CustomerQuery()
    {
    }

    /**
     * 
     * luojb 2012-11-12
     * @param custId
     * @return
     * @deprecated 仅仅提供给上海使用
     */
    public CmCustomer queryCustomer(Long custId)
    {
        CmCustomer result = querySingle(CmCustomer.class,new DBCondition(CmCustomer.Field.custId,custId));
        return result;
    }

    /**
     * @Description: 根据partyId查询客户
     * @author : yangyang
     * @date : 2011-10-28
     */
    public CmCustomer queryCustomerByPartyId(Long partyId)
    {
        CmPartyRole cmPartyRole = querySingle(CmPartyRole.class, new DBCondition(CmPartyRole.Field.partyId, partyId));
        if (cmPartyRole == null)
        {
            return null;
        }
        else
        {

            return querySingle(CmCustomer.class, new DBCondition(CmCustomer.Field.custId, cmPartyRole.getPartyRoleId()));
        }
    }

    /**
     * 根据custId查询CmParty
     * 
     * @author yangyang
     * @date 2011-9-21
     */
    public CmParty queryCmPartyByCustId(Long custId)
    {
        CmCustomer customer = querySingle(CmCustomer.class, new DBCondition(CmCustomer.Field.custId, custId));
        if (customer == null)
        {
            return null;
        }
        CmPartyRole partyRole = querySingle(CmPartyRole.class,
                new DBCondition(CmPartyRole.Field.partyRoleId, customer.getCustId()), new DBCondition(
                        CmPartyRole.Field.partyRoleType, EnumCodeDefine.PARTY_ROLETYPE_CUSTOMER));
        if (partyRole == null)
        {
            return null;
        }
        else
        {
            return querySingle(CmParty.class, new DBCondition(CmParty.Field.partyId, partyRole.getPartyId()));
        }
    }

    /**
     * @Description: 查询客户扩展信息
     */
    public CmCustExt queryCustExt(Long custId)
    {
        if (!CommonUtil.isValid(custId))
            return null;
        return querySingle(CmCustExt.class, new DBCondition(CmCustExt.Field.custId, custId));
    }

    /**
     * 查询客户名称定义信息 liuyang 2012-2-13
     * 
     * @param custId
     * @return
     */
    public CmIndividualName queryIndividualName(Long custId)
    {
        if (!CommonUtil.isValid(custId))
            return null;
        CmPartyRole role = querySingle(CmPartyRole.class, new DBCondition(CmPartyRole.Field.partyRoleId, custId));
        if (role == null)
        {
            return null;
        }

        return querySingle(CmIndividualName.class, new DBCondition(CmIndividualName.Field.partyId, role.getPartyId()));
    }

    public CaCustomInv queryCaCustInvoice(Long acctId)
    {
        if (!CommonUtil.isValid(acctId))
            return null;
        return querySingle(CaCustomInv.class, new DBCondition(CaCustomInv.Field.acctId, acctId));
    }

    /**
     * 通过证件类型,证件号码查询CmPartyIdentity实体
     * 
     * @param identity
     * @param identity_type
     * @author xieqr
     * @2012-3-1
     * @return
     */
    public CmPartyIdentity queryPartyIdentity(String identity, Integer identity_type)
    {

        if (!CommonUtil.isEmpty(identity_type) && !CommonUtil.isEmpty(identity))
        {
            // CmPartyIdentity where = new CmPartyIdentity();
            // where.setPartyIdentificationSpec(identity);
            // where.setPartyIdentificationType(identity_type);
            CmPartyIdentity partyIdentity = querySingle(CmPartyIdentity.class, new DBCondition(
                    CmPartyIdentity.Field.partyIdentificationSpec, identity), new DBCondition(
                    CmPartyIdentity.Field.partyIdentificationType, identity_type));
            if (partyIdentity == null)
                return null;
            return partyIdentity;
        }
        else
        {
            return null;
        }
    }

    /**
     * SH 根据集团编号 查询客户编号 xieqr 2012-4-10
     * 
     * @param groupId
     * @return
     */
    public Long queryCustByGroupId(Long groupId)
    {
        CmCustomer result = querySingle(CmCustomer.class, new DBCondition(CmCustomer.Field.groupId, groupId));
        if (result == null)
        {
            return null;
        }
        return result.getCustId();
    }
}
