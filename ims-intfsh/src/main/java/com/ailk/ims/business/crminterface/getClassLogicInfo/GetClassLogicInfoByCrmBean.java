package com.ailk.ims.business.crminterface.getClassLogicInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.openbilling.persistence.imscnsh.entity.ClassLogicInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_getClassLogicInfoByCrmResponse;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @description:通过crm获取班组信息
 * @author zhangzj3
 * @date:2012-12-4
 *
 */
public class GetClassLogicInfoByCrmBean extends BaseBusiBean {

    @Override
    public void init(Object... input) throws BaseException
    {
    }

    @Override
    public void validate(Object... input) throws BaseException
    {
    	
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
    	 List<ClassLogicInfo> infoList = HttpJsonToCrmService.getClassLogicInfoFromCrm(String.valueOf(doneCode), busiParams);
         return new Object[] { infoList };
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	List<ClassLogicInfo> classLogicInfoList=(List<ClassLogicInfo>)result[0];
    	Do_getClassLogicInfoByCrmResponse respn=new Do_getClassLogicInfoByCrmResponse();
    	respn.setClassLogicInfoList(classLogicInfoList);
        return respn;
    }
    @Override
    public void destroy()
    {
    }

}
