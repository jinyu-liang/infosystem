package com.ailk.ims.common;

import com.ailk.ims.ts.WSEngine;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.LogUtil;
import com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg;
//import com.asiainfo.obd.commoncomponents.SResultDescription;
/**
 * @Description: 基础上发webservice的同步bean
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2012-5-18
 */
public abstract class BaseWsTsBean extends BaseTsBean {/*
	public CBSErrorMsg deal(Object dbObj) {
		CBSErrorMsg error = null;
		try{
			WSEngine serviceEngine = createServiceBean(dbObj);
			imsLogger.info("webservice url : "+serviceEngine.getServiceUrl(), context);
			
			Object[] reqs = createSyncReq(dbObj);
			
			imsLogger.dump("****** request object", reqs);
			
			SResultDescription result = sync(reqs,serviceEngine.getServiceObj());
			
			imsLogger.dump("****** response object", result);
			
			error = new CBSErrorMsg();
			error.setError_msg(result.getErrorMsg());
			error.setResult_code((long)result.getResultCode());
		}catch(Exception e){
			imsLogger.error(e,e);
			error = IMSUtil.createCBSErrorMsg(context, e);
		}
		
		return error;
	}
	public abstract WSEngine createServiceBean(Object dbObj);
		
	public abstract Object[] createSyncReq(Object dbObj);
	
	public abstract SResultDescription sync(Object[] reqs,Object service);
	
	
	*//**
	 * 获取当前表的sequenceId,主要用于上发webservice中的字段。
	 * @return
	 *//*
	protected long getTransactionId(){
		return DBUtil.getSequence(this.getJavaClass());
	}
*/}
