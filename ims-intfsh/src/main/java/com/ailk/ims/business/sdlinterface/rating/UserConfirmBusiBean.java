package com.ailk.ims.business.sdlinterface.rating;

import java.util.ArrayList;
import java.util.List;

import jef.database.Batch;

import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseCancelableBusiBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imsintf.entity.CancelOrderInfo;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SDeductAlarm;
import com.ailk.openbilling.persistence.jd.entity.ImsUserConfirm;
import com.ailk.openbilling.persistence.zk.entity.IIsmpAlarm;

/**
 * @Description 提供给计费的接口，插入二次扣费确认表，通知给CRM
 * @author wukl
 * @Date 2012-9-1 * @Date 2012-10-27 wukl 反向表移到JD用户下的修改
 */
public class UserConfirmBusiBean extends BaseCancelableBusiBean {

	@Override
	public void cancel(CancelOrderInfo cancelInfo) {

	}

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

		CsdlArrayList<SDeductAlarm> sDeductAlarmList = (CsdlArrayList<SDeductAlarm>) input[0];
		/*
		 * SysParameter sysParameter = DBUtil.querySingle(SysParameter.class,
		 * new DBCondition(SysParameter.Field.paramCode,
		 * "IMS_REVERSE_INTERFACE_TO_ZK"), new
		 * DBCondition(SysParameter.Field.paramClass, 6));
		 */
			List<ImsUserConfirm> list = new ArrayList<ImsUserConfirm>();
			for (SDeductAlarm sDeductAlarm : sDeductAlarmList) {
				list.add(createImsUserOrderConfirm(sDeductAlarm));
			}
			if (list.size() > 0) {
				try {
					Batch<ImsUserConfirm> batch = DBUtil.getDBClient().startBatchInsert(ImsUserConfirm.class);
					batch.add(list);
					batch.setGroupForPartitionTable(true);
					batch.commit();
				} catch (Exception e) {
					throw IMSUtil.throwBusiException(e);
				}
			}


		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] result) {
		return new Do_sdlResponse();
	}

	@Override
	public void destroy() {

	}

	private IIsmpAlarm createIIsmpAlarm(SDeductAlarm sDeductAlarm) {
		IIsmpAlarm iIsmpAlarm = new IIsmpAlarm();
		iIsmpAlarm.setServId(sDeductAlarm.getServId());
		iIsmpAlarm.setAcctId(sDeductAlarm.getAcctId());
		iIsmpAlarm.setUserNumber(sDeductAlarm.getUserNumber());
		iIsmpAlarm.setSpCode(sDeductAlarm.getSpCode());
		iIsmpAlarm.setOperatorCode(sDeductAlarm.getOperatorCode());
		iIsmpAlarm.setBillFlag(sDeductAlarm.getBillFlag());
		iIsmpAlarm.setThirdMsisdn(sDeductAlarm.getThirdMsisdn());
		iIsmpAlarm.setBillProp(sDeductAlarm.getBillProp());
		iIsmpAlarm.setRegionCode(sDeductAlarm.getRegionCode());
		iIsmpAlarm.setStartTime(ConvertUtil.sdlLong2JavaDate(sDeductAlarm.getStartTime()));
		iIsmpAlarm.setAlarmType(sDeductAlarm.getAlarmType());
		iIsmpAlarm.setAlarmPara(sDeductAlarm.getAlarmPara());
		iIsmpAlarm.setAlarmSource(sDeductAlarm.getAlarmSource());
		iIsmpAlarm.setTotalFee(sDeductAlarm.getTotalFee());
		iIsmpAlarm.setUpdateDate(ConvertUtil.sdlLong2JavaDate(sDeductAlarm.getUpdateTime()));
		iIsmpAlarm.setSequenceNo(sDeductAlarm.getSeqNo());
		// iIsmpAlarm.setServiceCode(sDeductAlarm.getServiceCode());
		iIsmpAlarm.setDoneCode(Long.valueOf(CommonUtil.behindSubString(String.valueOf(context.getDoneCode()), 12)));
		iIsmpAlarm.setCreateTime(context.getRequestDate());

		iIsmpAlarm.setState(1);
		iIsmpAlarm.setPriority(0);
		// iIsmpAlarm.setSid(context.getDoneCode());

		return iIsmpAlarm;
	}

	private ImsUserConfirm createImsUserOrderConfirm(SDeductAlarm sDeductAlarm) {
		ImsUserConfirm imsUserConfirm = new ImsUserConfirm();
		
		imsUserConfirm.setUserId(sDeductAlarm.getServId());
		imsUserConfirm.setAcctId(sDeductAlarm.getAcctId());
		imsUserConfirm.setPhoneId(sDeductAlarm.getUserNumber());
		imsUserConfirm.setSpCode(sDeductAlarm.getSpCode());
		imsUserConfirm.setOperatorCode(sDeductAlarm.getOperatorCode());
		imsUserConfirm.setBillFlag(sDeductAlarm.getBillFlag());
		imsUserConfirm.setThirdMsisdn(sDeductAlarm.getThirdMsisdn());
		imsUserConfirm.setBillProp(sDeductAlarm.getBillProp());
		imsUserConfirm.setStartTime(ConvertUtil.sdlLong2JavaDate(sDeductAlarm.getStartTime()));
		imsUserConfirm.setAlarmType(sDeductAlarm.getAlarmType());
		imsUserConfirm.setAlarmPara(sDeductAlarm.getAlarmPara());
		imsUserConfirm.setAlarmSource(sDeductAlarm.getAlarmSource());
		imsUserConfirm.setRegionCode(sDeductAlarm.getRegionCode());
		imsUserConfirm.setTotalFee(sDeductAlarm.getTotalFee());
		imsUserConfirm.setUpdateDate(ConvertUtil.sdlLong2JavaDate(sDeductAlarm.getUpdateTime()));
		imsUserConfirm.setSequenceNo(sDeductAlarm.getSeqNo());
		imsUserConfirm.setServiceCode(sDeductAlarm.getServiceCode());
		
		imsUserConfirm.setSts(0);
		imsUserConfirm.setSoNbr(context.getDoneCode());
		imsUserConfirm.setSoDate(context.getRequestDate());
		imsUserConfirm.setCustId(sDeductAlarm.getServId());
		return imsUserConfirm;
	}
}
