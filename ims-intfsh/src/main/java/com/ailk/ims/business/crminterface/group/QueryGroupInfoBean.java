package com.ailk.ims.business.crminterface.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryGroupInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.GroupCustomerInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryGroupInfoReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 
 * @Description查询集团账户信息
 * @author zhangzhj
 * @Date 2013-7-19
 */
public class QueryGroupInfoBean extends BaseBusiBean
{
    QueryGroupInfoReq req;
    @Override
    public void init(Object... input) throws BaseException{
        req = (QueryGroupInfoReq)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException{
        if(req == null){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "req"); 
        }
        if(!CommonUtil.isValid(req.getGroup_id())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "group_id");
        }
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException{
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("group_no", req.getGroup_id());
        busiParams.put("cust_type", req.getCust_type());
        long doneCode = this.getContext().getDoneCode();
        GroupCustomerInfo info = HttpJsonToCrmService.queryGroupInfo(String.valueOf(doneCode), busiParams);
        return new Object[]{info};
    }

    @Override
    public BaseResponse createResponse(Object[] result){  
        GroupCustomerInfo info = (GroupCustomerInfo)result[0];
        Do_queryGroupInfoResponse respn = new Do_queryGroupInfoResponse();
        respn.setGroupCustomerInfo(info);
        return respn;
    }

    @Override
    public void destroy()
    {
    }

}
