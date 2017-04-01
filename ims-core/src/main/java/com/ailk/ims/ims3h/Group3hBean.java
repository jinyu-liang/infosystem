package com.ailk.ims.ims3h;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.complex.GroupComplex;
import com.ailk.ims.component.AccountRelationComponent;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.query.GroupQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
/**
 * 
 * @Description
 * @author luojb
 * @Date 2012-7-9
 */
public class Group3hBean extends Acct3hBean
{
    private Long billAcctId;
    private Long specAcctId;

    public Group3hBean(GroupComplex complex)
    {
        super(complex);
    }
    
    public GroupComplex getGroupComplex()
    {
        return this.getObject();
    }

    @Override
    public boolean isMatch(IMS3hBean another)
    {
        if (another instanceof Group3hBean)
        {
            Long anotherGroupId = ((Group3hBean) another).getGroupId();
            if (this.getGroupId().equals(anotherGroupId))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isMatch(Long groupId){
        return this.getGroupId().equals(groupId);
    }

    /**
     * groupId就是acctId
     * luojb 2012-7-9
     * @return
     */
    public Long getGroupId(){
        return getGroup().getAcctId();
    }
    
    public CaAccountGroup getGroup(){
        return this.getObject().getGroup();
    }
    
    @Override
    protected GroupComplex getObject()
    {
        return (GroupComplex)this.object;
    }
    
    /**
     * 没有客户
     */
    @Override
    public Cust3hBean getParent(){
        return null;
    }
    
    public Group3hBean getParentGroupBean()
    {
        if(!hasParentGroup())
            return null;
        if(parent == null){
            Long parentId = this.getParentGroupId();
            Group3hBean parentGroupBean = tree.loadGroup3hBean(parentId);
            setParent(parentGroupBean);
        }
        return (Group3hBean)parent;
    }

    private boolean hasParentGroup()
    {
        return this.getGroupType().shortValue() == EnumCodeDefine.ACCOUNT_TYPE_CUG; 
    }
    
    private boolean hasBillAccount(){
        return this.getGroupType().shortValue() != EnumCodeDefine.ACCOUNT_TYPE_SPECNBR_GCA &&
        this.getGroupType().shortValue() != EnumCodeDefine.ACCOUNT_TYPE_AUTO_GROUP;
    }
    
    /**
     * 特殊号码虚账户
     * luojb 2012-7-9
     * @return
     */
    private boolean hasSpecAccount(){
        return this.getGroupType().shortValue() != EnumCodeDefine.ACCOUNT_TYPE_SPECNBR_GCA &&
        this.getGroupType().shortValue() != EnumCodeDefine.ACCOUNT_TYPE_AUTO_GROUP;
    }
    
    
    /**
     * 群没有客户
     */
    @Override
    public Long getParentId()throws IMSException
    {
       return null;
    }
    
    /**
     * cug必须有父群
     */
    public Long getParentGroupId()throws IMSException
    {
        if(!hasParentGroup())
            return null;
        if(this.parentId == null){
            CaAccountRel rel = context.getComponent(AccountRelationComponent.class).queryCaAccountRel(this.getGroupId(),EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING );
            if(rel == null)
                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARENT_GROUP_NOTEXIST, this.getGroupId());
            this.parentId = rel.getGroupId();
        }
        return parentId;
    }
    
    /**
     * 必须有支付账户的群，没有则报错
     * luojb 2012-7-9
     * @return
     */
    public Long getBillAcctId(){
        if(!hasBillAccount())
            return null;
//        if(this.billAcctId == null){
//            Long billAcctId = context.getComponent(GroupQuery.class).queryBillAcctId(this.getGroupId());
//            if(billAcctId == null)
//                IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_BILLACCT_FOR_GROUP_NOTEXIST, this.getGroupId());
//            this.billAcctId = billAcctId;
//        }
        //2012-08-28 linzt 整理组件方法
        if(this.billAcctId == null){
//        	Long billAcctId=context.get3hTree().loadGroup3hBean(getGroupId()).getBillAcctId();
//        	this.billAcctId=billAcctId;
        	CaAccountRel caRel=context.getComponent(GroupQuery.class).querySingle(CaAccountRel.class,new DBCondition(CaAccountRel.Field.groupId,getGroupId()),
            		new DBCondition(CaAccountRel.Field.relationshipType,EnumCodeDefine.ACCOUNT_CA_GCA_RELATION));
        	this.billAcctId=caRel.getRelGroupId();
        }
        return this.billAcctId;
    }

    /**
     * 没有特殊号码虚账户 不报错
     * luojb 2012-7-9
     * @return
     */
    public Long getSpecAcctId(){
        if(!hasSpecAccount())
            return null;
        if(specAcctId == null){
            specAcctId = context.getComponent(GroupQuery.class).querySpecNbrGCAId(this.getGroupId());
        }
        return specAcctId;
    }
    
    public Integer getGroupType(){
        return this.getGroup().getAccountType();
    }
    
    @Override
    public Long getCustId()
    {
        return null;
    }

    @Override
    public CmCustomer getCustomer()
    {
        return null;
    }

    @Override
    public Long getAcctId()
    {
        return getAccount().getAcctId();
    }

    @Override
    public CaAccount getAccount()
    {
        CaAccount account = getObject().getAccount();
        if(account == null)
            account = queryAccountById(this.getGroupId());
        if(account == null)
            IMSUtil.throw3hBeanNotFoundException(ErrorCodeDefine.COMMON_ACCT_NOTEXIST, this.getGroupId());
        this.getObject().setAccount(account);
        return account;
    }
    
    @Override
    public boolean isSingleBalance()
    {
        return false;
    }

    
    private CaAccount queryAccountById(Long acctId)
    {
        return context.getComponent(BaseComponent.class).querySingle(CaAccount.class,
                new DBCondition(CaAccount.Field.acctId, acctId));
    }
}
