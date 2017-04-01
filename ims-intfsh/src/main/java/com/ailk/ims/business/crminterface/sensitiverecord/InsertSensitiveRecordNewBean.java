
/** 
 * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou
 * @Author 刘东方
 * @Date 2013-10-15
 */

package com.ailk.ims.business.crminterface.sensitiverecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.httpjson.service.HttpJsonToCrmService;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imscnsh.entity.SensitiveRecordIn;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;

/**
 * @author liudf
 *
 */
public class InsertSensitiveRecordNewBean extends BaseBusiBean {

	private SensitiveRecordIn qryIn;
	
	@Override
	public void init(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		qryIn=(SensitiveRecordIn)input[0];
	}

	@Override
	public void validate(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		//请求参数不能为空
		if(null==qryIn){
			 IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "SensitiveRecordIn");
		}
        if(CommonUtil.isEmpty(qryIn.getFun_id())){
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "fun_id");
		}
        if(CommonUtil.isEmpty(qryIn.getPhone_id())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "phone_id");
        }
        if(CommonUtil.isEmpty(qryIn.getFunc_name())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "func_name");
        }
        if(CommonUtil.isEmpty(qryIn.getSens_id())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "sens_id");
        }
        if(CommonUtil.isEmpty(qryIn.getOp_id())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "op_id");
        }
        if(CommonUtil.isEmpty(qryIn.getOrg_id())){
            IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "org_id");
        }
	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input)
			throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		// TODO Auto-generated method stub
		Map<String, String> busiParams = new HashMap<String, String>();
		busiParams.put("query_type", qryIn.getSens_id());	//敏感编号
		busiParams.put("func_id", qryIn.getFun_id());	//菜单编号
	    busiParams.put("func_name", qryIn.getFunc_name());	//菜单名称
	    busiParams.put("bill_id", qryIn.getPhone_id());	//手机号码
	    busiParams.put("oper_id", qryIn.getOp_id()+"");	//操作员编号
	    busiParams.put("org_id", qryIn.getOrg_id()+"");	//组织编号
	    busiParams.put("reason", null);	
	    busiParams.put("login_time", DateUtil.getFormatDate(DateUtil.currentDate(),
				DateUtil.DATE_FORMAT_EN_B_YYYYMMDDHHMMSS));	
	    busiParams.put("state", null);	
	    busiParams.put("notes", null);	
	    busiParams.put("ext1", null);	
	    busiParams.put("ext2", null);	
	    busiParams.put("ext3", null);	
	    busiParams.put("ext4", null);	
	    busiParams.put("ccslogdataid", null);	
	    busiParams.put("callbillid", null);	
	    
        long doneCode = this.getContext().getDoneCode();
        HttpJsonToCrmService.insertSensitiveRecordNew(String.valueOf(doneCode), busiParams);
        
        return new Object[] {};
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
