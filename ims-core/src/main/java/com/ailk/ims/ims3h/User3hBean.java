package com.ailk.ims.ims3h;

import jef.database.Condition.Operator;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.complex.UserComplex;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PhoneIdHeadUtil;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRes;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
/**
 * 
 * @Description
 * @author luojb
 * @Date 2012-2-23
 * @Date 2012-06-11 wangdw5 : On_Site Defect #47941 AIS版本号码头+0
 * @Date 2012-12-12 wukl 上海项目存在主副号码区分，通过IDENTITY_ATTR来标识，计费在处理上网伴侣副号码、副号随意换副号码时会用到付号码
 */
public class User3hBean extends IMS3hBean
{
    private CaAccountRes caRes;
    
    public User3hBean(Object object)
    {
        super(object);
    }

    @Override
    public boolean isMatch(IMS3hBean another)
    {
        if(another instanceof User3hBean){
            UserComplex complex = (UserComplex)another.getObject();
            if(this.getObject().getResource().getResourceId().equals(complex.getResource().getResourceId())){
                return true;
            }
        }
        return false;
    }
    
    public boolean isMatch(Long userId){
        return this.getObject().getResource().getResourceId().equals(userId);
    }

    public boolean isMatch(String phoneId){
    	//@Date 2012-06-14 wangdw5 : User Story #42479 AIS号码头处理
    	phoneId = PhoneIdHeadUtil.phoneIdParse(phoneId);
        return this.getObject().getPhoneId().equals(phoneId);
    }
    //ljc modify
    public boolean isMatch(Long userId,String phoneId){
        return (userId != null ? isMatch(userId):true) && (CommonUtil.isNotEmpty(phoneId) ? isMatch(phoneId):true);
    }
    
    @Override
    public Acct3hBean getParent()
    {
        if(this.parent != null){
            return (Acct3hBean)this.parent;
        }
        
        Long parentId = this.getParentId();
        
        
        Acct3hBean acctBean = tree.loadAcct3hBean(parentId);
        setParent(acctBean);
        return acctBean;
    }
    
    public CaAccount getAccount(){
        Acct3hBean acctBean = this.getParent();
        return acctBean.getAccount();
    }
    
    public CmCustomer getCustomer(){
        Cust3hBean custBean = this.getParent().getParent();
        return custBean.getObject();
    }

    public CmResource getUser(){
        return this.getObject().getResource();
    }
    
    public Long getUserId(){
        return this.getUser().getResourceId();
    }
    
    public CmResLifecycle getUserLifeCycle(){
        CmResLifecycle cycle = this.getObject().getLifeCycle();
        if(cycle == null)
        {
            cycle = queryResLifeCyc(this.getUserId());
            if(cycle == null)
                IMSUtil.throw3hBeanNotFoundException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, this.getUserId());
            this.getObject().setLifeCycle(cycle);
        }
        return cycle;
    }
    
    public String getPhoneId(){
        String phoneId = this.getObject().getPhoneId();
        if(phoneId == null){
            CmResIdentity iden = queryResIdentity(this.getUserId());
            if (iden == null && ProjectUtil.is_Overseas())
            {
                // modify by caohm5 2012-3-27 上海不对phoneId 不存在的情况抛异常
                IMSUtil.throw3hBeanNotFoundException(ErrorCodeDefine.COMMON_PHONE_NOTEXIST, phoneId, this.getUserId());
            }
            phoneId = iden.getIdentity();
            this.getObject().setPhoneId(phoneId);
        }
        return phoneId;
    }
    
    public String getImsi(){
        String imsi = this.getObject().getImsi();
        if(imsi == null){
            CmResIdentity iden = queryResIdentity(this.getUserId());
            if (iden == null && ProjectUtil.is_Overseas())
            {
                // modify by caohm5 2012-3-27 上海不对phoneId 不存在的情况抛异常
                IMSUtil.throw3hBeanNotFoundException(ErrorCodeDefine.COMMON_PHONE_NOTEXIST, imsi, this.getUserId());
            }
            imsi = iden.getRelIdentity();
            this.getObject().setPhoneId(imsi);
        }
        return imsi;
    }
    
    public Long getAcctId(){
        return this.getParentId();
    }
    
    public Long getCustId(){
        return this.getCustomer().getCustId();
    }
    
    public Long getBillAcctId()throws IMSException
    {
        Long billAcctId = this.getCaAccountRes().getResAcctId();
        if(billAcctId == null)
            billAcctId = getAcctId();
        return billAcctId;
    }
    
    public Acct3hBean getBillAcct3hBean()throws IMSException
    {
        Long billAcctId = this.getBillAcctId();
        if(billAcctId == this.getAcctId()){
            return this.getParent();
        }
        Acct3hBean billAcctBean = tree.loadAcct3hBean(billAcctId);
        return billAcctBean;
    }
    
    @Override
    public UserComplex getObject()
    {
        return (UserComplex)this.object;
    }

	
	/**
	 * @Description: 当前的user3hbean更改的userid或者phoneId，需要重新载入
	  * @author : wuyj
	  * @date : 2012-1-14  
	  * @param userId
	  * @param phoneId
	  * @return
	 */
	public void reload(Long userId,String phoneId){
		//先把当前的从树种移除掉
		tree.remove3hBean(this);
		
		//重新载入一个新的
		User3hBean new3hbean = tree.loadUser3hBean(userId, phoneId);
		
		this.setObject(new3hbean.getObject());
		this.parentId = new3hbean.parentId;
		this.setCaAccountRes(new3hbean.caRes);
		this.setParent(new3hbean.parent);
	}
	
    public CaAccountRes getCaAccountRes()throws IMSException
    {
        if(caRes == null)
        {
        	CaAccountRes acctRes=context.getComponent(AccountQuery.class).querySingle(CaAccountRes.class, new DBCondition(CaAccountRes.Field.resourceId, this.getUserId()),
                    new DBCondition(CaAccountRes.Field.relationshipType, EnumCodeDefine.ACCOUNT_RELATION_RES_BELONGING));
        	if(acctRes == null)
            {
                IMSUtil.throw3hBeanNotFoundException(ErrorCodeDefine.COMMON_ACCT_FOR_USER_NOTEXIST, this.getUserId());
            }
            caRes = acctRes;
        }
        return caRes;
    }
    
    public void setCaAccountRes(CaAccountRes caRes)
    {
        this.caRes = caRes;
    }
    
    public Integer getTitleRoleId()throws IMSException
    {
        return this.getCaAccountRes().getTitleRoleId();
    }

    @Override
    public Long getParentId()throws IMSException
    {
        if(this.parentId == null){
            parentId = this.getCaAccountRes().getAcctId();
        }
        return parentId;
    }
    
    public void setParentId(Long acctId)
    {
        this.parentId = acctId;
    }
    
    private CmResIdentity queryResIdentity(Long userId) throws IMSException
    {
		if (ProjectUtil.is_CN_SH())
		{//@Date 2012-12-12 wukl 上海项目存在主副号码区分，通过IDENTITY_ATTR来标识，计费在处理上网伴侣副号码、副号随意换副号码业务时会用到付号码
			return context.getComponent(BaseComponent.class).querySingle(CmResIdentity.class,
					new DBCondition(CmResIdentity.Field.resourceId, userId),
					new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE),
					new DBCondition(CmResIdentity.Field.identityAttr, EnumCodeDefine.RESOURCE_IDENTITYATTR_MAIN_NUMBER));
		}
		else
		{
			return context.getComponent(BaseComponent.class).querySingle(CmResIdentity.class,
					new DBCondition(CmResIdentity.Field.resourceId, userId),
					new DBCondition(CmResIdentity.Field.identityType, EnumCodeDefine.RESOURCE_IDENTITYTYPE_PHONE));
		}
    }
    
    /**
     * 根据用户ID获取用户生命周期
     */
    private CmResLifecycle queryResLifeCyc(Long resourceId) throws IMSException
    {
        if (ProjectUtil.is_CN_SH())
        {// @Date 2012-07-03 wukl 上海的rec_sts字段没使用，所以不调用DBUtil的方法
            CmResLifecycle lifeCycle = context.getComponent(BaseComponent.class).querySingle(CmResLifecycle.class,
                    new DBCondition(CmResLifecycle.Field.resourceId, resourceId));
            if (lifeCycle == null)
            {
                IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, resourceId);
            }
            return lifeCycle;
        }
        else
        {
            // @Date 2012-09-04 wangdw5 : [52342]后付费支持生命周期流转 recSts = 0 or 2
            CmResLifecycle lifeCycle = DBUtil.querySingle(CmResLifecycle.class, new DBCondition(CmResLifecycle.Field.resourceId,
                    resourceId), new DBCondition(CmResLifecycle.Field.recSts, new Integer[] { EnumCodeDefine.IS_VALID_DATA,
                    EnumCodeDefine.IN_DEALING }, Operator.IN));
            if (lifeCycle == null)
            {
                // 2012-06-11 wuyujie : 查询不到生命周期不应该抛异常
                return null;// IMSUtil.throwBusiException(ErrorCodeDefine.LIFE_CYCLE_IS_NULL, resourceId);
            }

            if (lifeCycle.getExpireDate().before(context.getRequestDate()))
            {
                lifeCycle.setOsSts(CommonUtil.string2Integer(String.valueOf(lifeCycle.getNextSts()).substring(0, 2)));

                // @Date 2012-5-24 tangjl5 #45585: 生命周期状态nextsts在数据库里面的存储要改为8位
                lifeCycle.setSts(CommonUtil.string2Integer("10" + String.valueOf(lifeCycle.getNextSts()).substring(6, 8)));
            }
            return lifeCycle;
        }
    }
    
    
    public boolean isTerminalOrPool()
    {
        Integer userSts = this.getUserLifeCycle().getSts();
        return userSts == EnumCodeDefine.LIFECYCLE_TERMINAL || userSts == EnumCodeDefine.LIFECYCLE_POOL;
    }

}
