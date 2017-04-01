package com.ailk.ims.business.query;

import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.RouterComponent;
import com.ailk.ims.component.query.AccountQuery;
import com.ailk.ims.component.query.UserQuery;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SAccount;
import com.ailk.openbilling.persistence.innerqry.entity.Do_queryAcctInRouteResponse;
import com.ailk.openbilling.persistence.innerqry.entity.IdMap;
import com.ailk.openbilling.persistence.innerqry.entity.QryAcctInRouteReq;

/**
 * 根据用户编号,手机好,查询账户编号
 * @Description
 * @author xieqr
 * @Date 2012-10-9
 */
public class QryAcctInRouteByUseridOrPhoneIdBean extends BaseBusiBean
{

    private QryAcctInRouteReq req;

    Boolean flag;
    private Long acctId;
    private Long userId;
    private String phoneId;
    private Long custId;
    
    private SAccount sacct;
    private CaAccount acct;

    @Override
    public void init(Object... input) throws BaseException
    {
        req = (QryAcctInRouteReq) input[0];
        if(req.getIs_accountInfo()==null){
        	req.setIs_accountInfo(false);
        }
        if(req.getIs_custId()==null){
        	req.setIs_custId(false);
        }
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (!CommonUtil.isValid(req.getUser_id()) && CommonUtil.isEmpty(req.getPhone_id())&&!CommonUtil.isValid(req.getAcct_id()))
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "userId or phoneId");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {

        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {
    	flag=true;
		try {
			if (CommonUtil.isValid(req.getUser_id())) {
				userId = req.getUser_id();
				acctId = context.getComponent(RouterComponent.class)
						.getAcctIdByUserIdRout(req.getUser_id());
				// @Date 2012-10-18 yugb :修改为3hBean来获取
				// @date 2012-10-29 zhangzj3:增加判断
				if(!CommonUtil.isValid(phoneId)){
				    User3hBean bean = context.get3hTree().loadUser3hBean(userId);
	                phoneId = bean.getPhoneId(); 
				}
			} else if (CommonUtil.isNotEmpty(req.getPhone_id())) {
				phoneId = req.getPhone_id();
				acctId = context.getComponent(RouterComponent.class)
						.getAcctIdByPhoneIdRout(req.getPhone_id());
				CmResIdentity identity = context.getComponent(UserQuery.class)
						.queryIdentityByPhoneId(req.getPhone_id());
				if (identity != null) {
					userId = identity.getResourceId();
				}
			} else if (CommonUtil.isValid(req.getAcct_id())) {
				CaAccount account = context.getComponent(BaseComponent.class)
						.querySingle(
								CaAccount.class,
								new DBCondition(CaAccount.Field.acctId, req
										.getAcct_id()));
				if (account == null) {
					IMSUtil.throw3hBeanNotFoundException(
							ErrorCodeDefine.COMMON_ACCT_NOTEXIST,
							req.getAcct_id());
				}
				acct = account;
				acctId = account.getAcctId();
			}
			if (req.getIs_accountInfo() == true) {
				if(acct==null){
					CaAccount account = context.getComponent(
							BaseComponent.class).querySingle(CaAccount.class,
							new DBCondition(CaAccount.Field.acctId, acctId));
					if (account == null) {
						IMSUtil.throw3hBeanNotFoundException(
								ErrorCodeDefine.COMMON_ACCT_NOTEXIST, acctId);
					}
					acct = account;
				}
				sacct = context.getComponent(AccountQuery.class)
						.sAccountTransform4Billing(acct);
			}
			if(req.getIs_custId()==true){
				if(acct==null){
					CaAccount account = context.getComponent(
							BaseComponent.class).querySingle(CaAccount.class,
							new DBCondition(CaAccount.Field.acctId, acctId));
					if (account == null) {
						IMSUtil.throw3hBeanNotFoundException(
								ErrorCodeDefine.COMMON_ACCT_NOTEXIST, acctId);
					}
					custId = account.getCustId();
				}else{
					custId=acct.getCustId();
				}
			}
		}catch(Exception e){
            imsLogger.error(e, e);
            flag =false;
        }
		return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	if(flag){
    		Do_queryAcctInRouteResponse resp = new Do_queryAcctInRouteResponse();
    		IdMap map = new IdMap();
    		map.setAcct_id(acctId);
    		map.setPhone_id(phoneId);
    		map.setUser_id(userId);
    		map.setCust_id(custId);
    		resp.setIdMap(map);
    		resp.setSAcct(sacct);
    		return resp;
    	}else {
    		return null;
    	}
    }

    @Override
    public void destroy()
    {

    }

}
