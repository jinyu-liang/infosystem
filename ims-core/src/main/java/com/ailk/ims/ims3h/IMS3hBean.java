package com.ailk.ims.ims3h;

import com.ailk.ims.common.ContextBean;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.PhoneIdHeadUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
/**
 * 
 * @Description
 * @author luojb
 * @Date 2012-2-23
 * 2012-06-01 wuyujie ：增加checkMatchAll3hIds方法
 * 2012-07-17 wuyujie 需求:增加isOnetimefee字段
 */
public abstract class IMS3hBean extends ContextBean
{
    protected IMS3hBean parent;
    protected Object object;
    protected Long parentId;
    protected Integer index;
    protected Boolean isOnetimefee;//该3hbean是否需收取一次性费用，默认收取
    public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	protected IMS3hTree tree = (IMS3hTree)IMSUtil.getRequestContextParam(ConstantDefine.THREAD_LOCAL_IMS3HTREE);

    public IMS3hBean(Object object){
        this.object = object;
        super.context = tree.getContext();
    }

    public abstract boolean isMatch(IMS3hBean another);
    
    public  abstract IMS3hBean getParent();
    
    protected  abstract Object getObject();
    
    
    public abstract Long getParentId();
    
    public void setParent(IMS3hBean parent)
    {
        this.parent = parent;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
    
    //2012-01-14 wuyujie : 方便业务程序判断
    public boolean isCust3hBean(){
    	return this instanceof Cust3hBean;
    }
    public boolean isAcct3hBean(){
    	return this instanceof Acct3hBean;
    }
    public boolean isGroup3hBean(){
        return this instanceof Group3hBean;
    }
    public boolean isUser3hBean(){
    	return this instanceof User3hBean;
    }
    
    public abstract Long getCustId();
    public abstract CmCustomer getCustomer();
    
    public abstract Long getAcctId();
    public abstract CaAccount getAccount();
    
    
    public abstract Long getUserId();
    public abstract String getPhoneId();
    
    
    /**
     * 检测当前user3hbean和传入的一些3hId属性是否一致
     * @param userId
     * @param phoneId
     * @param acctId
     * @param custId
     */
    public void checkMatchAll3hIds(Long custId,Long acctId,Long userId,String phoneId){
        //@Date 2012-06-14 wangdw5 : User Story #42479 AIS号码头处理
        phoneId = PhoneIdHeadUtil.phoneIdParse(phoneId);
        //如果有传入userId，则需要匹配下当前3hbean里的userId和传入的是否一致，不一致需要抛错
        if(CommonUtil.isValid(userId) && userId.longValue() != this.getUserId()){
            IMSUtil.throwBusiException("user_id["+userId+"] passed in is not matched with expected["+this.getUserId()+"]");
        }
        //如果有传入phoneId，则需要匹配下当前3hbean里的phoneId和传入的是否一致，不一致需要抛错
        if(CommonUtil.isValid(phoneId) && !phoneId.equals(this.getPhoneId())){
            IMSUtil.throwBusiException("phone_id["+phoneId+"] passed in is not matched with expected["+this.getPhoneId()+"]");
        }
        //如果有传入acctId，则需要匹配下当前3hbean里的acctId和传入的是否一致，不一致需要抛错
//      if(CommonUtil.isValid(acctId)){
            
//          boolean isTerminalOrPool = this.isUser3hBean()?((User3hBean)this).isTerminalOrPool():false;
//          if(!isTerminalOrPool && acctId.longValue() != this.getAcctId())
//          {
//              IMSUtil.throwBusiException("acct_id["+acctId+"] passed in is not matched with expected["+this.getAcctId()+"]");
//          }
//      }
        //如果有传入acctId，则需要匹配下当前3hbean里的acctId和传入的是否一致，不一致需要抛错
        if(CommonUtil.isValid(acctId)&& acctId.longValue() != this.getAcctId()){
            IMSUtil.throwBusiException("acct_id["+acctId+"] passed in is not matched with expected["+this.getAcctId()+"]");
        }
        //如果有传入custId，则需要匹配下当前3hbean里的custId和传入的是否一致，不一致需要抛错
        if(CommonUtil.isValid(custId) && custId.longValue() != this.getCustId()){
            IMSUtil.throwBusiException("cust_id["+custId+"] passed in is not matched with expected["+this.getCustId()+"]");
        }
    }

    public boolean isOnetimefee()
    {
        return isOnetimefee == null ? true : isOnetimefee ;
    }

    public void setOnetimefee(boolean isOnetimefee)
    {
        this.isOnetimefee = isOnetimefee;
    }
    
    
    
}
