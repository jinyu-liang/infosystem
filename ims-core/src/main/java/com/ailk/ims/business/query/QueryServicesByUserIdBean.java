package com.ailk.ims.business.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBRelation;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.component.UserComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSEnumDataUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResServCycle;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.imsinner.entity.Do_queryServicesByUserIdResponse;
import com.ailk.openbilling.persistence.imsinner.entity.SQueryServicesReq;
import com.ailk.openbilling.persistence.imsinner.entity.SServiceReturnList;
import com.ailk.openbilling.persistence.imsinner.entity.SServicesReturn;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.pm.entity.PmServiceSpec;

/**
 * 
 * @Description 查询已订购的服务状态
 * @author yanchuan
 * @Date 2011-10-20
 */
public class QueryServicesByUserIdBean extends BaseBusiBean{
    
    Long userId = null;
    String phoneId = null;
	//SOperInfo sOper,Long user_id,String phone_id,Integer busi_service_id
	@Override
	public void init(Object... input) throws IMSException {
	}

	@Override
	public void validate(Object... input) throws IMSException {
	    SQueryServicesReq req = (SQueryServicesReq)input[0];
		userId=req.getUserId();
		phoneId=(String)req.getPhoneId();
		
		if(!CommonUtil.isValid(userId)&&!CommonUtil.isValid(phoneId)){
			throw IMSUtil.throwBusiException(ErrorCodeDefine.INPUT_ONE_USERID_PHONEID);
		}
	}

	@Override
	public Object[] doBusiness(Object... input) throws IMSException {
		
		User3hBean bean = context.get3hTree().loadUser3hBean(userId, phoneId);
		
		userId = bean.getUserId();
		SServiceReturnList returnList = getServiceList(userId);
		return new Object[]{returnList};
	}
    /**
     * 通过电话号码查询服务列表
     * @author yanchuan 2011-10-20
     * @param phoneId
     * @return
     */
    private SServiceReturnList getServiceList(Long userId){
    	SServiceReturnList services = new SServiceReturnList();
        List<SServicesReturn> serviceList = new ArrayList<SServicesReturn>();
        
        UserComponent userComp = context.getComponent(UserComponent.class);
        List<CmResServCycle> cmServiceList = userComp.querySevCycleByUserIdAndSrvId(userId, null);

        for (CmResServCycle cmService : cmServiceList)
        {
            PmServiceSpec pmserviceSpec = userComp.querySingle(PmServiceSpec.class, 
            		new DBCondition(PmServiceSpec.Field.serviceSpecId, cmService.getServiceSpecId()));
            SServicesReturn serviceReturn = new SServicesReturn();
            
            serviceReturn.setService_id(cmService.getServiceSpecId());
            serviceReturn.setService_status(CommonUtil.IntegerToShort(cmService.getSts()));

            // SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date validDate = cmService.getValidDate();
            if (validDate != null)
                serviceReturn.setValid_date(DateUtil.getFormatDate(validDate, "yyyy-MM-dd hh:mm:ss"));

            Date expireDate = cmService.getExpireDate();
            if (expireDate != null)
                serviceReturn.setExpire_date(DateUtil.getFormatDate(expireDate, "yyyy-MM-dd hh:mm:ss"));
            serviceReturn.setService_name(pmserviceSpec.getName());
            //查询服务状态
            String sts_name = null;
            if(cmService.getSts() != null)
                sts_name = IMSEnumDataUtil.getEnumDispName("STS",cmService.getSts().toString(),"CM_RES_SERV_CYCLE");
            serviceReturn.setStatus_name(sts_name);
            serviceList.add(serviceReturn);
            // payment_mode, time_segment...
        }
        services.setServiceReturnList(serviceList.toArray(new SServicesReturn[serviceList.size()]));
        return services;
    }
	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_queryServicesByUserIdResponse resp=new Do_queryServicesByUserIdResponse();
		SServiceReturnList services = (SServiceReturnList)result[0];
		resp.setBusiServiceList(services);
		return resp;
	}

	@Override
	public void destroy() {
		
	}

	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		List<IMS3hBean> list = new ArrayList<IMS3hBean>();
        list.add(context.get3hTree().loadUser3hBean(userId, phoneId));
        return list;
	}

}
