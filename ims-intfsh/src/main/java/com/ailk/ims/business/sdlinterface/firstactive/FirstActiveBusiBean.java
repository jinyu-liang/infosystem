package com.ailk.ims.business.sdlinterface.firstactive;

import java.util.Date;
import java.util.List;

import com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.component.FirstActiveComponent;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.EnumCodeShDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.User3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SFirstAct;
import com.ailk.openbilling.persistence.jd.entity.ImsCustProdSync;
import com.ailk.openbilling.persistence.jd.entity.ImsNtf;

/**
 * 上海侧首条话单激活，只进行数据入接口表操作
 * 
 * @Description
 * @author wukl
 * @Date 2012-3-29
 * @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ * @Date 2012-10-27 wukl
 *       反向表移到JD用户下的修改
 */
public class FirstActiveBusiBean extends BaseBusiBean {

	@Override
	public void init(Object... input) throws BaseException {

	}

	@Override
	public void validate(Object... input) throws BaseException {

	}

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... input) throws BaseException {
		return null;
	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {

		SFirstAct sFirstAct = (SFirstAct) input[0];
		insertImsNtfService(sFirstAct);
//		Integer zk = SysUtil.getInt("IMS_REVERSE_INTERFACE_TO_ZK");
//		if (zk != null && zk == 0) {
//		} else {
//			insertImsCustProdSync(sFirstAct);
//		}
		return null;
	}

	private void insertImsNtfService(SFirstAct firstActivation) {
		ImsNtf notify = new ImsNtf();
		notify.setQueueId(DBUtil.getSequence(ImsNtf.class));
		notify.setResourceId(firstActivation.getUserId());
		notify.setCreateDate(context.getRequestDate());
		Date activationTime = null;
		if (CommonUtil.isValid(firstActivation.getActivationTime())) {
			activationTime = ConvertUtil.sdlLong2JavaDate(firstActivation.getActivationTime());
		} else {
			activationTime = context.getRequestDate();
		}
		notify.setNotificationId(EnumCodeShDefine.FIRST_ACTIVE_NOTIFY_ID_GX);
		notify.setActionId(EnumCodeShDefine.FIRST_ACTIVE_ACTION_ID_GX);
		notify.setStateId(EnumCodeShDefine.FIRST_ACTIVE_STATE_ID_GX);
		notify.setSplitFlag(10);//IMS_NTF_OUT_SERV
		notify.setNotifyTime(activationTime);
		notify.setPhoneId(firstActivation.getPhoneId());
		notify.setRemark(firstActivation.getLocation());
		context.getComponent(FirstActiveComponent.class).insert(notify);

	}

	private void insertImsCustProdSync(SFirstAct firstActivation) {
		ImsCustProdSync imsCustProdSync = new ImsCustProdSync();
		Date activationTime = null;
		// 计费传入的时间为特殊的int64：20120101123030
		if (CommonUtil.isValid(firstActivation.getActivationTime())) {
			activationTime = ConvertUtil.sdlLong2JavaDate(firstActivation.getActivationTime());
			// imsCustProdSync.setActivationTime(ConvertUtil.sdlLong2JavaDate(firstActivation.getActivationTime()));
		} else {// 若时间未传，则取操作时间
			imsLogger.info("***** 计费没有传入激活时间，取当前请求时间为激活时间.");
			activationTime = context.getRequestDate();
			// imsCustProdSync.setActivationTime(context.getRequestDate());
		}

		activationTime = DateUtil.dayBegin(activationTime);
		imsCustProdSync.setActivationTime(activationTime);
		imsCustProdSync.setActiveCac(firstActivation.getLocation());

		if (!CommonUtil.isValid(firstActivation.getPhoneId()) && !CommonUtil.isValid(firstActivation.getUserId())) {
			IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_PARAM_ISNULL, "userId and phoneId");
		}

		User3hBean hbean = context.get3hTree().loadUser3hBean(firstActivation.getUserId(), firstActivation.getPhoneId());

		// @Date 2012-04-17 wukl IMS开头的接口表，统一取DEFAULT_GLOBAL_SEQ
		imsCustProdSync.setId(DBUtil.getSequence());
		imsCustProdSync.setSoNbr(context.getDoneCode());
		imsCustProdSync.setSts(EnumCodeDefine.SYNC_CRM_RECORD_STS_INITIAL);
		imsCustProdSync.setCreateDate(context.getRequestDate());
		imsCustProdSync.setPhoneId(hbean.getPhoneId());
		imsCustProdSync.setUserId(hbean.getUserId());
		imsCustProdSync.setAcctId(hbean.getBillAcctId());
		imsCustProdSync.setActiveCac(firstActivation.getLocation());

		context.getComponent(FirstActiveComponent.class).insert(imsCustProdSync);
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		Do_sdlResponse res = new Do_sdlResponse();
		if (!CommonUtil.isEmpty(result)) {
			CBSErrorMsg errorMsg = null;
			if (result[0] != null) {
				errorMsg = (CBSErrorMsg) result[0];
				com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg javaMsg = new com.ailk.openbilling.persistence.imsintf.entity.CBSErrorMsg();
				javaMsg.setError_msg(errorMsg.get_errorMsg());
				javaMsg.setResult_code((long) errorMsg.get_errorCode());
				res.setErrorMsg(javaMsg);
			}
		}
		return res;
	}

	@Override
	public void destroy() {
	}
}