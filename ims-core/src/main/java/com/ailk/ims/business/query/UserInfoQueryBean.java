package com.ailk.ims.business.query;

import java.util.List;

import com.ailk.ims.common.BaseQueryBean;
import com.ailk.ims.component.AccountComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.CustomerComponent;
import com.ailk.ims.component.PartyComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.CustomerQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountExt;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.acct.entity.CaCustomInv;
import com.ailk.openbilling.persistence.acct.entity.CaCustomerRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIndividual;
import com.ailk.openbilling.persistence.cust.entity.CmIndividualName;
import com.ailk.openbilling.persistence.cust.entity.CmOrgName;
import com.ailk.openbilling.persistence.cust.entity.CmPartyIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsintf.entity.Do_queryResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.imsintf.entity.SContact;
import com.ailk.openbilling.persistence.imsintf.entity.SCustomer;
import com.ailk.openbilling.persistence.imsintf.entity.SQueryParam;
import com.ailk.openbilling.persistence.imsintf.entity.SUser;

/**
 * @Description: 查询三户信息
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author yindm@asiainfo-linkage.com
 * @Date 2011-8-30 yindm : 新建类
 * @Date 2011-10-18 tangjl5 : 完善查询三户信息功能 2011-11-21 hunan ：优化代码， 将封装SCustomer ，SAccount，SUser的方法提取到相应的组建中。
 * @Date 2012-4-20 tangjl5 查询接口不抛出异常
 * @Date 2012-4-20 tangjl5 查询CaAccountRes修改为调用公共方法
 */
public class UserInfoQueryBean extends BaseQueryBean
{

    @Override
    /**
     * caohm5 modify 2012-04-27
     * 账户找客户不走caCustomerRel这层关系
     */
    public void doQuery(SQueryParam queryParam, Do_queryResponse result) throws IMSException
    {
       
        // wuyj解决编译错误joinCondition.setNeedAllExpireDate(false);
        // //查询条件2-具体查询条件
//        BaseComponent baseComp = context.getComponent(BaseComponent.class);
        CmResource res = null;
        CmResLifecycle lifeCycle = null;
        CaAccountRes acctRes = null;

        CaAccount ca = null;
        CaAccountExt acctAttri = null;
        CaCustomerRel custRel = null;

        CmCustomer cust = null;
        CmIndividual individual = null;
        CmIndividualName individualName = null;
        CmOrgName orgName = null;
        CmPartyIdentity partyIdentity = null;
        CaCustomInv custInv = null;
        if(!CommonUtil.isValid(queryParam.getUser_id())&&!CommonUtil.isValid(queryParam.getPhone_id()))
        {
            throw new IMSException("both userId and phoneId is null");
        }
        try
        {
             res = context.get3hTree().loadUser3hBean(queryParam.getUser_id(), queryParam.getPhone_id()).getUser();        
        }
        catch(IMS3hNotFoundException e)
        {
        	imsLogger.error(e,e);
        }
        if (res != null && res.getResourceId() != null)
        {
//            acctRes = baseComp.querySingle(CaAccountRes.class,
//                    new DBCondition(CaAccountRes.Field.resourceId, res.getResourceId()), new DBCondition(
//                            CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
            // @Date 2012-4-20 tangjl5 查询CaAccountRes修改为调用公共方法
            acctRes = context.get3hTree().loadUser3hBean(res.getResourceId()).getCaAccountRes();
            if(ProjectUtil.is_CN_SH())
            {
                try
                {
                    lifeCycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();
                }
                catch(IMS3hNotFoundException e)
                {
                    IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, res.getResourceId());
                }
            }
            else
            {
                try
                {
                    lifeCycle = context.get3hTree().loadUser3hBean(res.getResourceId()).getUserLifeCycle();
                    if(lifeCycle.getRecSts()!=EnumCodeDefine.IS_VALID_DATA)
                    {
                     // 2012-06-11 wuyujie : 查询不到生命周期不应该抛异常
                        lifeCycle=null;
                    }
                    else if (lifeCycle.getExpireDate().before(context.getRequestDate()))
                    {
                        lifeCycle.setOsSts(CommonUtil.string2Integer(String.valueOf(lifeCycle.getNextSts()).substring(0, 2)));

                        // @Date 2012-5-24 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
                        lifeCycle.setSts(CommonUtil.string2Integer("10" + String.valueOf(lifeCycle.getNextSts()).substring(6, 8)));
                    }
                 }
                catch(IMS3hNotFoundException e)
                {
                	imsLogger.error(e,e);
                }
            }
        }
        if (acctRes != null && acctRes.getAcctId() != null)
        {
            ca = context.get3hTree().loadAcct3hBean(acctRes.getAcctId()).getAccount();
            acctAttri = context.getComponent(AccountQuery.class).queryAcctAttr(acctRes.getAcctId());
            // baseComp.querySingle(CaAccountAttribute.class,
            // new DBCondition(CaAccountAttribute.Field.acctId ,acctRes.getAcctId()));
//            custRel = baseComp.querySingle(CaCustomerRel.class, new DBCondition(CaCustomerRel.Field.acctId, acctRes.getAcctId()),
//                    new DBCondition(CaCustomerRel.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_CUST_ATTRIBUTIVE));
            custInv = context.getComponent(CustomerQuery.class).queryCaCustInvoice(acctRes.getAcctId());
            // custInv = baseComp.querySingle(CaCustomInv.class, new DBCondition(CaCustomInv.Field.acctId ,
            // acctRes.getAcctId()));
        }
//        if (custRel != null && custRel.getCustId() != null)
//        {
//            cust = context.getComponent(CustomerComponent.class).queryCustomer(custRel.getCustId());
//
//            if (null == cust)
//            {
//                throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_CUST_NOTEXIST, custRel.getCustId());
//            }
//
//            if (IMSUtil.isPersonCust(cust.getCustomerClass()))
//            {
//                individual = context.getComponent(PartyComponent.class).queryInvidualByCustId(custRel.getCustId());
//                individualName = context.getComponent(PartyComponent.class).queryInvidualNameByCustId(custRel.getCustId());
//            }
//            else
//            {
//                orgName = context.getComponent(PartyComponent.class).queryCmOrgNameByPartyId(custRel.getCustId());
//            }
//
//            partyIdentity = context.getComponent(PartyComponent.class).queryPartyByIdentity(custRel.getCustId());
//        }
        //caohm
        if(ca!=null && ca.getCustId()!=null){
        	cust = context.get3hTree().loadCust3hBean(ca.getCustId()).getCustomer();

            if (IMSUtil.isPersonCust(cust.getCustomerClass()))
            {
                individual = context.getComponent(PartyComponent.class).queryInvidualByCustId(ca.getCustId());
                individualName = context.getComponent(PartyComponent.class).queryInvidualNameByCustId(ca.getCustId());
            }
            else
            {
                orgName = context.getComponent(PartyComponent.class).queryCmOrgNameByPartyId(ca.getCustId());
            }

            partyIdentity = context.getComponent(PartyComponent.class).queryPartyByIdentity(ca.getCustId());
        }

        SCustomer sCust = context.getComponent(CustomerComponent.class).sCustTransform(cust, individual, individualName, orgName,
                partyIdentity);
        SAccount sAcct = context.getComponent(AccountComponent.class).sAccountTransform(ca, custInv, acctAttri, custRel);
        SUser sUser = context.getComponent(UserComponent.class).sUserTransform(res, queryParam.getPhone_id(), acctRes, cust, ca,
                lifeCycle);
        List<SContact> sContactList = context.getComponent(AccountComponent.class).getSContactAddress(ca);

        // @Date 2012-4-20 tangjl5 查询接口不抛出异常
//        if (sUser.getMain_promotion() == null)
//        {
//            throw IMSUtil.throwBusiException(ErrorCodeDefine.USER_HAVE_NO_MAIN_PROM);
//        }

        result.setCustomer(sCust);
        result.setAccount(sAcct);
        result.setUser(sUser);
        //@Date 2012-10-08 yugb :60590  返回账单寄送地址
        result.setSContactList(sContactList);
    }
}
