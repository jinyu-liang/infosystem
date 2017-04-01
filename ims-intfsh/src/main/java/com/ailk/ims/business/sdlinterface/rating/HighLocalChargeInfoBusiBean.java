package com.ailk.ims.business.sdlinterface.rating;

import java.util.ArrayList;
import java.util.List;

import jef.database.Batch;

import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.openbilling.persistence.imsintf.entity.BaseResponse;
import com.ailk.openbilling.persistence.imssdl.entity.Do_sdlResponse;
import com.ailk.openbilling.persistence.imssdl.entity.SLocalHighChargeInfoReq;
import com.ailk.openbilling.persistence.jd.entity.LocalHighChargeInfo;

/**
 * 广西省内高额需求
 * 
 * @author lijc3
 * 
 */
public class HighLocalChargeInfoBusiBean extends BaseBusiBean {

	@Override
	public List<IMS3hBean> createMain3hBeans(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse createResponse(Object[] arg0) {
		// TODO Auto-generated method stub
		return new Do_sdlResponse();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] doBusiness(Object... input) throws BaseException {
		CsdlArrayList<SLocalHighChargeInfoReq> sHighChargeList = (CsdlArrayList<SLocalHighChargeInfoReq>) input[0];
		if (CommonUtil.isNotEmpty(sHighChargeList)) {
			List<LocalHighChargeInfo> infoList = new ArrayList<LocalHighChargeInfo>();
			for (SLocalHighChargeInfoReq req : sHighChargeList) {
				infoList.add(covertChargeInfo(req));
			}

			if (infoList.size() > 0) {
				try {
					Batch<LocalHighChargeInfo> batch = DBUtil.getDBClient().startBatchInsert(LocalHighChargeInfo.class);
					batch.add(infoList);
					batch.setGroupForPartitionTable(true);
					batch.commit();
				} catch (Exception e) {
					throw IMSUtil.throwBusiException(e);
				}
			}
		}
		return null;
	}

	@Override
	public void init(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(Object... arg0) throws BaseException {
		// TODO Auto-generated method stub

	}

	private LocalHighChargeInfo covertChargeInfo(SLocalHighChargeInfoReq req) {
		LocalHighChargeInfo info = new LocalHighChargeInfo();
		info.setDrType(req.getDrType());
		info.setEndTime(ConvertUtil.sdlLong2JavaDate(req.getEndTime()));
		info.setStartTime(ConvertUtil.sdlLong2JavaDate(req.getStartTime()));
		info.setHighDate(req.getHighDate());
		info.setHighType((int) req.getHighType());
		info.setHomeAreaCode(req.getHomeAreaCode());
		info.setImsiMin(req.getImsiMin());
		info.setProvCode(req.getProvCode());
		info.setTotalFee(req.getTotalFee());
		info.setTotalNum(req.getTotalNum());
		info.setTotalTime(req.getTotalTime());
		info.setUserNumber(Long.valueOf(req.getUserNumber()));
		info.setUserType(req.getUserType());
		info.setVisitAreaCode(req.getVisitAreaCode());
		return info;
	}

}
