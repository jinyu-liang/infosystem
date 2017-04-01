package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.List;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.component.GroupShQuery;
import com.ailk.ims.component.VPNComponent;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.imscnsh.entity.SQueryVPNGroupShReq;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryVPNGroupResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SGroup;
import com.ailk.openbilling.persistence.imsintf.entity.SGroupList;
import com.ailk.openbilling.persistence.shinner.entity.Check3HParamReturn;

public class QryVPNGroupByUserIdShBean extends BaseBusiBean 
{
    private VPNComponent vpnCmp;
    private GroupShQuery groupShQueryCmp;
    private String phoneId;
    private Long userId;
    private Short groupType;
    private SQueryVPNGroupShReq req;

    @Override
    public void init(Object... arg0) throws BaseException 
    {
        vpnCmp = context.getComponent(VPNComponent.class);
        groupShQueryCmp = context.getComponent(GroupShQuery.class);
        req = (SQueryVPNGroupShReq) arg0[0];
        userId = req.getUser_id();
        phoneId = req.getPhone_id();
        groupType = req.getGroup_type();
    }
    
    @Override
    public void validate(Object... arg0) throws BaseException 
    {
        if(!CommonUtil.isValid(userId)&&!CommonUtil.isValid(phoneId)){
            throw IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL,"user_id or phone_id");
        }
    }
    
    @Override
    public List<IMS3hBean> createMain3hBeans(Object... arg0)
            throws BaseException {
        return null;
    }
    
    @Override
    public Object[] doBusiness(Object... arg0) throws BaseException 
    {   
        Check3HParamReturn bean = context.getComponent(CheckComponentSH.class).check3HParam(null, null, req.getUser_id(), req.getPhone_id());
        userId = bean.getUserId();
        List<CaResGrpRevs> revsList = groupShQueryCmp.queryResGrpRevs(userId);
        List<CaAccount> acctList = new ArrayList<CaAccount>();
        List<SGroup> groupList = new ArrayList<SGroup>();
        if(CommonUtil.isNotEmpty(revsList)){
            for(CaResGrpRevs rv:revsList){
                List<DBCondition> conditions = new ArrayList<DBCondition>();
                conditions.add(new DBCondition(CaAccount.Field.acctId,rv.getAcctId()));
                if(null !=groupType){
                    conditions.add(new DBCondition(CaAccount.Field.accountType,groupType));
                }
                CaAccount account = vpnCmp.querySingle(CaAccount.class,conditions.toArray(new DBCondition[conditions.size()]));
                if(account != null){
                    acctList.add(account);
                }
            }
            if(CommonUtil.isNotEmpty(acctList)){
                for(CaAccount acct:acctList){
                    SGroup group = vpnCmp.queryGroupInfo(acct.getAcctId());
                    if(group!=null&&group.getGroup_id()!=null){
                        groupList.add(group);
                    }
                }
            }
        }
        return new Object[] { groupList };
    }

    @SuppressWarnings("unchecked")
    @Override
    public BaseResponse createResponse(Object[] result) 
    {
        Do_queryVPNGroupResponse resp =new Do_queryVPNGroupResponse();
        List<SGroup> groups = (List<SGroup>) result[0];
        SGroupList groupList = new SGroupList();
        if(CommonUtil.isNotEmpty(groups)){
            groupList.setGroup_items(groups.toArray(new SGroup[groups.size()]));
        }
        resp.setGroups(groupList);
        return resp;
    }

    @Override
    public void destroy() 
    {

    }
    
}
