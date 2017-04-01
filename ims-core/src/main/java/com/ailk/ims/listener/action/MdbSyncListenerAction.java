package com.ailk.ims.listener.action;

import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.ims.common.BaseMdbBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;

public class MdbSyncListenerAction implements IListenerAction {
	private BaseMdbBean mdbBean;
	protected ImsLogger imsLogger=new ImsLogger(this.getClass());
	public BaseMdbBean getMdbBean() {
		return mdbBean;
	}

	public void setMdbBean(BaseMdbBean mdbBean) {
		this.mdbBean = mdbBean;
	}



	public Object doAction(IMSContext context) {
		CBSErrorMsg mdbError = null;
		imsLogger.info("["+context.getOper().getBusi_code()+"]begin to sync", context);
		try {
			mdbError = mdbBean.doSyncronization(null);
		} catch (Exception e) {
			// mdb同步失败不影响前面的业务操作，存入错单表，后续异步上发
        	imsLogger.error(e,e);
            mdbError = new CBSErrorMsg((int) ErrorCodeDefine.UNKNOWN_ERROR, e.getMessage(), null);
		}
		imsLogger.info("["+context.getOper().getBusi_code()+"]sync mdb result", context, mdbError);
		if (mdbError != null && mdbError.get_errorCode() != 0)
        {
            // Mdb异常处理,存入mdb错误表以供后续异步上发,直接抛出异常
//            new MdbUPBean(context).handleError(mdbError);
		    //GX版本改为先抛出异常
		    IMSUtil.throwBusiException("******* user mdb return error["+mdbError.get_errorCode()+":"+mdbError.get_errorMsg()+"]");
        }
		return mdbError;
	}
}
