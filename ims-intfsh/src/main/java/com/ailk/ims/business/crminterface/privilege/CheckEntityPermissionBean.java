package com.ailk.ims.business.crminterface.privilege;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_checkEntityPermissionResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * 根据操作员和权限编号，判断该操作员是否具备这个实体权限
 * 
 * @Description
 * @author xieqr
 * @Date 2012-5-7
 */
public class CheckEntityPermissionBean extends BaseBusiBean
{
    private String opId;
    private String entId;
    private String privId;

    @Override
    public void init(Object... input) throws BaseException
    {
        opId = (String) input[0];
        entId = (String) input[1];
        privId = (String) input[2];

    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (CommonUtil.isEmpty(opId) || CommonUtil.isEmpty(entId) || CommonUtil.isEmpty(privId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "opId or entId or privId");
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
        busiParams.put("EntId", entId);
        busiParams.put("PrivId", privId);
        long doneCode = this.getContext().getDoneCode();
        String state = HttpJsonToCrmService.checkEntityPermission(String.valueOf(doneCode), opId, busiParams);
        return new Object[] { state };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        Do_checkEntityPermissionResponse resp = new Do_checkEntityPermissionResponse();
        String state = (String) result[0];
        resp.setState(state);

        return resp;
    }

    @Override
    public void destroy()
    {

    }

}
