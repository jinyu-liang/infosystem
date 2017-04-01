package com.ailk.ims.business.imsInterface.usagerequestmngt;

import java.util.ArrayList;
import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_queryInstanceResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.InstanceInfo;
import com.ailk.openbilling.persistence.imscnsh.entity.QueryInstanceReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.ImsUsageRequest;
/**
 * @date:2012-10-15
 * @author caohm5
 * @description:INSTANCE_SYC_BBOSS_JF表的查询接口
 *
 */
public class QueryInstanceBean extends BaseBusiBean {

	private QueryInstanceReq req;
    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(QueryInstanceReq)input[0];
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
    	List<InstanceInfo> instanceInfoList=queryInstanceInfo(req);
    	return new Object[]{instanceInfoList};
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	List<InstanceInfo> instanceInfoList=(List<InstanceInfo>)result[0];
    	Do_queryInstanceResponse respn=new Do_queryInstanceResponse();
    	respn.setInstanceInfoList(instanceInfoList);
    	return respn;
    }

    @Override
    public void destroy()
    {
    	
    }
    private List<InstanceInfo> queryInstanceInfo(QueryInstanceReq req){
    	if(null==req){
    		return null;
    	}
    	List<InstanceInfo> instanceInfoList   =    	getQueryInstanceInfo(req);
    	
    	return instanceInfoList;
    	
    }

	private List<InstanceInfo> getQueryInstanceInfo(QueryInstanceReq req) {
		  	List<DBCondition> constList = new ArrayList<DBCondition>();
	        if (CommonUtil.isValid(req.getAcct_id()))
	        {
	        	constList.add(new DBCondition(ImsUsageRequest.Field.accId, req.getAcct_id()));
	        }
	        if (!CommonUtil.isEmpty(req.getBill_id()))
	        {
	        	constList.add(new DBCondition(ImsUsageRequest.Field.billId, req.getBill_id()));
	        }
	        if (!CommonUtil.isEmpty(req.getBusi_type()))
	        {
	        	constList.add(new DBCondition(ImsUsageRequest.Field.busiType, req.getBusi_type()));
	        }
	        if (CommonUtil.isValid(req.getChannel_id()))
	        {
	        	constList.add(new DBCondition(ImsUsageRequest.Field.channelId, req.getChannel_id()));
	        }
	        if (CommonUtil.isValid(req.getMonth()))
	        {
	        	constList.add(new DBCondition(ImsUsageRequest.Field.month, req.getMonth()));
	        }
	        if (CommonUtil.isValid(req.getSource()))
	        {
	        	constList.add(new DBCondition(ImsUsageRequest.Field.source, req.getSource()));
	        }
	        if (CommonUtil.isValid(req.getOp_id()))
	        {
	        	constList.add(new DBCondition(ImsUsageRequest.Field.opId, req.getOp_id()));
	        }
	        
	        List<ImsUsageRequest> imsInstanceSycBbossJfList = this.context.getComponent(BaseComponent.class).query(
	        		ImsUsageRequest.class,constList.toArray(new DBCondition[constList.size()]));
	        List<InstanceInfo> instanceInfoList=new ArrayList<InstanceInfo>();
	        if(imsInstanceSycBbossJfList!=null&&!imsInstanceSycBbossJfList.isEmpty()){
	        	for(int i=0;i<imsInstanceSycBbossJfList.size();i++){
	        		ImsUsageRequest imsInstanceSycBbossJf=imsInstanceSycBbossJfList.get(i);
	        		if(imsInstanceSycBbossJf==null){
	        			continue;
	        		}
	        		InstanceInfo instanceInfo=new InstanceInfo();
	        		instanceInfo.setAcct_id(imsInstanceSycBbossJf.getAccId());
	        		instanceInfo.setBill_id(imsInstanceSycBbossJf.getBillId());
	        		if(imsInstanceSycBbossJf.getDoneDate()!=null){
	        			instanceInfo.setDone_date(DateUtil.formatDate(imsInstanceSycBbossJf.getDoneDate(), DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));
	        		}
	        		instanceInfo.setFile_name(imsInstanceSycBbossJf.getFileName());
	        		instanceInfo.setOp_id(imsInstanceSycBbossJf.getOpId());
	        		instanceInfo.setState(imsInstanceSycBbossJf.getState());
	        		instanceInfo.setExt_1(imsInstanceSycBbossJf.getExt1());
	        		instanceInfo.setExt_2(imsInstanceSycBbossJf.getExt2());
	        		instanceInfo.setExt_3(imsInstanceSycBbossJf.getExt3());
	        		instanceInfo.setExt_4(imsInstanceSycBbossJf.getExt4());
	        		instanceInfoList.add(instanceInfo);
	        	}
	        }
	        return instanceInfoList;
	}
}
