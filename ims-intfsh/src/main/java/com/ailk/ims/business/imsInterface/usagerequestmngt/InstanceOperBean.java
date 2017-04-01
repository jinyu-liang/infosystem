package com.ailk.ims.business.imsInterface.usagerequestmngt;

import java.util.List;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.BaseComponent;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.Do_instanceOperResponse;
import com.ailk.openbilling.persistence.imscnsh.entity.InstanceOperReq;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.jd.entity.ImsUsageRequest;

/**
 * @date:2012-10-15
 * @author caohm5
 * @description:INSTANCE_SYC_BBOSS_JF表的插入接口
 *
 */
public class InstanceOperBean extends BaseBusiBean {
	
	private InstanceOperReq req;


    @Override
    public void init(Object... input) throws BaseException
    {
    	req=(InstanceOperReq)input[0];
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
    	insertImsInstanceSycBbossJf(req);
    	return null;
    }

    @Override
    public BaseResponse createResponse(Object[] result)
    {
    	Do_instanceOperResponse respn=new Do_instanceOperResponse();
        return respn;
    }
    @Override
    public void destroy()
    {
    }
    private void insertImsInstanceSycBbossJf(InstanceOperReq req){
    	ImsUsageRequest insertValue=new ImsUsageRequest();
    	insertValue.setSeqId(DBUtil.getSequence(ImsUsageRequest.class));
    	insertValue.setAccId(req.getAcct_id());
    	insertValue.setBillId(req.getBill_id());
    	insertValue.setBusiType(req.getBusi_type());
    	insertValue.setChannelId(req.getChannel_id());
    	insertValue.setExt1(req.getExt_1());
    	insertValue.setExt2(req.getExt_2());
    	insertValue.setExt3(req.getExt_3());
    	insertValue.setExt4(req.getExt_4());
    	insertValue.setFileName(req.getFile_name());
    	insertValue.setMonth(req.getMonth());
    	insertValue.setNotes(req.getNotes());
    	insertValue.setOpId(req.getOp_id());
    	insertValue.setOrgId(req.getOrg_id());
    	insertValue.setSource(req.getSource());
    	insertValue.setState(req.getState());
    	//日期
    	if(CommonUtil.isNotEmpty(req.getInput_date())){
    		insertValue.setInputDate(IMSUtil.formatDate(req.getInput_date(), null));
    	}
    	if(CommonUtil.isNotEmpty(req.getStart_date())){
    		insertValue.setStartDate(IMSUtil.formatDate(req.getStart_date(), null));
    	}
    	if(CommonUtil.isNotEmpty(req.getDone_date())){
    		insertValue.setDoneDate(IMSUtil.formatDate(req.getDone_date(), null));
    	}
    	this.context.getComponent(BaseComponent.class).insert(insertValue);
    }

}
