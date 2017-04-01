package com.ailk.ims.business.crminterface.getauthid;

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
import com.ailk.openbilling.persistence.imscnsh.entity.AuthInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_getAuthIdByCrmResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.GetAuthIdReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:通过crm获取授权码
 * @author caohm5
 * @date:2012-11-23
 *
 */
public class GetAuthIdByCrmBean extends BaseBusiBean {

	private GetAuthIdReq req;
	private List<String> phoneList = null;
    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(GetAuthIdReq)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if (req==null)
        {
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "GreenChannelReq");
        }
    	phoneList = req.getPhoneIdList();
    	if(phoneList==null||phoneList.isEmpty()){
    		 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phoneIdList");
    	}
    	for(String phoneId:phoneList){
    		if(!CommonUtil.isValid(phoneId)){
    			 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phoneId");
    		}
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
    	 long doneCode = this.getContext().getDoneCode();
    	 Map<String, String[]> busiParams = new HashMap<String, String[]>();
    	 String[] ids = phoneList.toArray(new String[phoneList.size()]);
         busiParams.put("phoneIdList",ids);
    	 List<AuthInfo> infoList = HttpJsonToCrmService.getAuthIdByPhoneIdFromCrm(String.valueOf(doneCode), busiParams);
         return new Object[] { infoList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	List<AuthInfo> authInfoList=(List<AuthInfo>)result[0];
    	Do_getAuthIdByCrmResponse respn=new Do_getAuthIdByCrmResponse();
    	respn.setAuthInfoList(authInfoList);
        return respn;
    }
    @Override
    public void destroy()
    {
    }

}
