package com.ailk.ims.business.crminterface.shortinterroaming;

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
import com.ailk.openbilling.persistence.imscnsh.entity.Do_checkShortInterRoamingResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.ShortInterRoamingInfo;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
/**
 * @description:短期国际漫游产品 的判断
 * @author caohm5
 * @date :2013-01-23
 *
 */
public class CheckShortInterRoamingBean extends BaseBusiBean {
    private String phoneId;
    
    @Override
    public void init(Object... input) throws BaseException
    {
    	phoneId = (String) input[0];
        
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
        if (!CommonUtil.isValid(phoneId))
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id");
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
    	context.getComponent(CheckComponentSH.class).check3HParam(null, null, null, phoneId);
    	Map<String, String> busiParams = new HashMap<String, String>();
    	busiParams.put("billId", phoneId);
    	busiParams.put("aValidType","-999");
    	busiParams.put("offerId", "350599999997");
		long doneCode = this.getContext().getDoneCode();
		List<ShortInterRoamingInfo> infoList=HttpJsonToCrmService.checkShortInterRoaming(String.valueOf(doneCode), busiParams);
		return new Object[] { infoList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {  
    	List<ShortInterRoamingInfo> infoList = (List<ShortInterRoamingInfo>) result[0];
        Do_checkShortInterRoamingResponse resp = new Do_checkShortInterRoamingResponse();
        resp.setInfoList(infoList);
        return resp;
    }

    @Override
    public void destroy()
    {
        
        
    }

}
