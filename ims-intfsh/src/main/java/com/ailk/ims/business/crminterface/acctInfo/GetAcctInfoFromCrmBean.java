package com.ailk.ims.business.crminterface.acctInfo;

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
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_getAcctInfoFromCRMResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.GetAcctInfoFromCRMReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:通过企业编号到CRM获取账户信息
 * @author zhangzj3
 * @date:2013-2-25
 *
 */
public class GetAcctInfoFromCrmBean extends BaseBusiBean {

	private GetAcctInfoFromCRMReq req;
    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(GetAcctInfoFromCRMReq)input[0];
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	if (req==null){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "GetAcctInfoByCRMReq");
        }
    	if(!CommonUtil.isValid(req.getPhone_id())){
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
    	 long doneCode = this.getContext().getDoneCode();
    	 Map<String, String> busiParams = new HashMap<String, String>();
         busiParams.put("ServiceNum",req.getPhone_id());
         CaAccount account = HttpJsonToCrmService.getAcctInfoFromCRM(String.valueOf(doneCode), busiParams);
         return new Object[] {account};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
        CaAccount account=(CaAccount)result[0];
    	Do_getAcctInfoFromCRMResponse respn=new Do_getAcctInfoFromCRMResponse();
    	if(account != null){
    	    respn.setAcct_id(account.getAcctId());
    	    respn.setAcct_type(account.getCreditSignControl());
    	}
        return respn;
    }
    @Override
    public void destroy()
    {
    }

}
