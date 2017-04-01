package com.ailk.ims.business.crminterface.privilege;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.business.crminterface.bo.RoleGrandInfo;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryEntitysByOpIdResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 根据操作员查询所有实体权限信息
 * 
 * @Description
 * @author xieqr
 * @Date 2012-5-7
 */
public class QueryEntitysByOpIdBean extends BaseBusiBean
{

    private String opId;

    @Override
    public void init(Object... input) throws BaseException
    {

        opId = (String) input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (CommonUtil.isEmpty(opId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "opId");
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
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("OpId", opId);
        long doneCode = this.getContext().getDoneCode();

        List<RoleGrandInfo> infoList = HttpJsonToCrmService.getEntitysByOpId(String.valueOf(doneCode), opId, busiParams);
        return new Object[] { infoList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_queryEntitysByOpIdResponse resp = new Do_queryEntitysByOpIdResponse();
        List<RoleGrandInfo> infoList = (List<RoleGrandInfo>) result[0];
        resp.setRoleGrandList(infoList);
        return resp;
    }

    @Override
    public void destroy()
    {

    }

    // /**
    // * 解析json 返回的数据 封装成List<RoleGrandInfo>
    // *
    // * @xieqr 2012-5-18
    // * @param responseInfo
    // * @return
    // */
    // private List<RoleGrandInfo> getGrandInfo(ResponseInfo responseInfo)
    // {
    // if (responseInfo == null)
    // {
    // return null;
    // }
    // List<RoleGrandInfo> grandInfoList = null;
    // Map retInfo = responseInfo.getRetInfo();
    // if (retInfo != null && retInfo.get("RoleGrandInfo") != null)
    // {
    // List<Map> infoList = (List<Map>) retInfo.get("RoleGrandInfo");
    // if (infoList != null && infoList.size() > 0)
    // {
    // grandInfoList = new ArrayList<RoleGrandInfo>();
    // for (Map map : infoList)
    // {
    // if (map != null)
    // {
    // RoleGrandInfo info = new RoleGrandInfo();
    // if (map.get("RoleId") != null)
    // info.setRoleId((String) map.get("RoleId"));
    // if (map.get("EntId") != null)
    // info.setEntId((String) map.get("EntId"));
    // if (map.get("EntType") != null)
    // info.setEntType((String) map.get("EntType"));
    // if (map.get("RoleGrantId") != null)
    // info.setRoleGrantId((String) map.get("RoleGrantId"));
    // if (map.get("PrivId") != null)
    // info.setPrivId((String) map.get("PrivId"));
    // if (map.get("Notes") != null)
    // info.setNotes((String) map.get("Notes"));
    // if (map.get("ValidDate") != null)
    // info.setValidDate((String) map.get("ValidDate"));
    // if (map.get("ExpireDate") != null)
    // info.setExpireDate((String) map.get("ExpireDate"));
    // if (map.get("DoneDate") != null)
    // info.setDoneDate((String) map.get("DoneDate"));
    // if (map.get("State") != null)
    // info.setState((String) map.get("State"));
    // grandInfoList.add(info);
    // }
    // }
    // }
    // }
    // return grandInfoList;
    // }
}
