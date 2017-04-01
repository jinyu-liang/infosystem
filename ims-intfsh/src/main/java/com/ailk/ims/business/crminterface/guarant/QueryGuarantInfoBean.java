package com.ailk.ims.business.crminterface.guarant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.CheckComponentSH;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryGuarantInfoResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.SGuarantInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @根据用户编号查询担保人信息
 * @Description
 * @author xieqr
 * @Date 2012-6-26
 * @date 2012-11-5 zhangzj3 性能优化
 */
public class QueryGuarantInfoBean extends BaseBusiBean
{
    private Long resourceId;
    
    @Override
    public void init(Object... input) throws BaseException
    {
        resourceId = (Long) input[0];
        
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (!CommonUtil.isValid(resourceId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "resource_id");
        }
        
    }

    @Override
    public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException
    {
    	/*List<IMS3hBean> list = new ArrayList<IMS3hBean>();
    	if(CommonUtil.isValid(resourceId)){
    		list.add(context.get3hTree().loadUser3hBean(resourceId));
    	}
        return list;*/
        return null;
    }

    @Override
    public Object[] doBusiness(Object... input) throws BaseException
    {   
        //@Date 2012-11-5 zhangzj3 性能优化,公共校验
        context.getComponent(CheckComponentSH.class).check3HParam(null, null, resourceId, null);
        Map<String, String> busiParams = new HashMap<String, String>();
        busiParams.put("resource_id", resourceId.toString());
        long doneCode = this.getContext().getDoneCode();
        SGuarantInfo info = HttpJsonToCrmService.getGuarantInfo(String.valueOf(doneCode), busiParams);
        return new Object[] { info };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {   
        SGuarantInfo info = (SGuarantInfo)result[0];
        Do_queryGuarantInfoResponse resp = new Do_queryGuarantInfoResponse();
        resp.setSGuarantInfo(info);
        return resp;
    }

    @Override
    public void destroy()
    {
        
        
    }

}
