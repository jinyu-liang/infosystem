package com.ailk.imssh.fee.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jef.database.DataObject;

import com.ailk.easyframe.sal.client.SalClient;
import com.ailk.easyframe.sal.exceptions.SALException;
import com.ailk.easyframe.sal.route.bean.MdbRoute;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SAbmArAsset;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SAbmArUpdateIncrRet;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SAbmArUpdateIncrUp;
import com.ailk.easyframe.sdl.MAbmInterfaceArDef.SOtFreeRes;
import com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SRatingAdjust;
import com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SRatingAdjustRet;
import com.ailk.easyframe.sdl.MAbmRdlCommonDef.SAbmBalanceQueryRet;
import com.ailk.easyframe.sdl.MAbmRdlCommonDef.SAbmBalanceQueryUp;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.MdbKVDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.mapreduce.FeeToAbmMapReduce;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.ConvertUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.SpringUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.define.ErrorCodeExDefine;
import com.ailk.imssh.common.handler.BaseHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.fee.cmp.FeeCmp;
import com.ailk.openbilling.persistence.acct.entity.CaAllowance;
import com.ailk.openbilling.persistence.itable.entity.IFee;
import com.ailk.openbilling.persistence.sys.entity.SysMeasure;

/**
 * @Description:处理I_FEE接口表的数据，保存到对应的数据库表
 * @author wangyh3
 * @Date 2012-5-15
 */
public class FeeHandler extends BaseHandler {

	@Override
	public void beforeDeal(List<? extends DataObject>... dataArr) throws IMSException {
		// TODO Auto-generated method stub

	}

	@Override
	public void handle(List<? extends DataObject>... dataArr) {

		List<IFee> list = (List<IFee>) dataArr[0];
		FeeCmp cmp = this.getContext().getCmp(FeeCmp.class);
		RouterCmp routCmp = context.getCmp(RouterCmp.class);
		CsdlArrayList<SOtFreeRes> sOtFreeReslist = new CsdlArrayList<SOtFreeRes>(SOtFreeRes.class);
		CsdlArrayList<com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes> adjustList = new CsdlArrayList<com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes>(
				com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes.class);
		CaAllowance caAllowance;
		Map<Long, Long> userAcctMap = new HashMap<Long, Long>();
		for (int index = 0; index < list.size(); index++) {
			IFee iFee = list.get(index);
			if (iFee.getOperType() != EnumCodeExDefine.OPER_TYPE_INSERT) {
				// 不是新增的，进行调整
				userAcctMap.put(iFee.getUserId(), iFee.getAcctId());
			} else {
				// 新增的直接插入
				SysMeasure measure = cmp.getSysMeasure(iFee.getFeeItemId());
				caAllowance = cmp.createOneTimeFee(iFee, measure);
				sOtFreeReslist.add(cmp.convertSOtFreeRes(caAllowance, measure));
			}
		}
		SalClient client = SpringUtil.getSalClient();
		if (CommonUtil.isNotEmpty(userAcctMap)) {
			for (Entry<Long, Long> entry : userAcctMap.entrySet()) {
				
				SAbmBalanceQueryUp queryUp = new SAbmBalanceQueryUp();
				
				SAbmBalanceQueryRet queryRst =new SAbmBalanceQueryRet();
				queryUp.set_ownerId(entry.getKey());
				queryUp.set_acctId(entry.getValue());
				queryUp.set_ownerType((short)0);
				if (ITableUtil.isMdbCloud()) {
					MdbRoute route = routCmp.getAccountRoute(entry.getValue());
					com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmBalanceQueryUp> entry2 = new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SAbmBalanceQueryUp>(
							route, queryUp);
					List<com.ailk.easyframe.sal.mapreduce.Entry> list2 = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry>();
					list2.add(entry2);
					queryRst = client.get(MdbKVDefine.ABM_COMMON_QUERY, list2, queryRst);
				} else {
					queryRst = client.get(MdbKVDefine.ABM_COMMON_QUERY, queryUp, queryRst);
				}

				imsLogger.dump("一次性免费资源返回#######", queryRst);
				if (queryRst == null || CommonUtil.isEmpty(queryRst.get_otFreeresList())) {
					continue;
				}
				
				CsdlArrayList<com.ailk.easyframe.sdl.MAbmRdlCommonDef.SOtFreeRes> otFResList  = queryRst.get_otFreeresList();
				for (com.ailk.easyframe.sdl.MAbmRdlCommonDef.SOtFreeRes res : otFResList) {
					if(res.get_objectType() != EnumCodeDefine.PROD_OBJECTTYPE_DEV){
						continue;
					}
					for (IFee fee : list) {
						if (fee.getOperType() == EnumCodeExDefine.OPER_TYPE_INSERT) {
							continue;
						}
						long validDate = ConvertUtil.javaDate2SdlLong(fee.getValidDate());
						// 转换成调整的一次性免费资源
						if (fee.getAcctId() == res.get_acctId() && fee.getUserId() == res.get_objectId() && validDate == res.get_validDate()
								&& fee.getFeeItemId() == res.get_itemCode()) {
							imsLogger.debug("转换成调整");
							com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes clearRes = convertSOtFreeRes(res, fee);
							adjustList.add(clearRes);
							SysMeasure measure = cmp.getSysMeasure(fee.getFeeItemId());
							caAllowance = cmp.createOneTimeFee(fee, measure);
						}
					}
				}
			}
		}
		// SAbmTransQueryUp queryUp=

		try {
			if (CommonUtil.isNotEmpty(sOtFreeReslist)) {
				imsLogger.info("-------start to deal oneTimeFeeup2ABM");
				SAbmArAsset sAbmArAsset = new SAbmArAsset();
				SAbmArUpdateIncrUp sAbmArUpdateIncrUp = new SAbmArUpdateIncrUp();
				CsdlArrayList<SAbmArAsset> sAbmArAssetslist = new CsdlArrayList<SAbmArAsset>(SAbmArAsset.class);

				sAbmArAsset.set_otFreeresList(sOtFreeReslist);
				sAbmArAssetslist.add(sAbmArAsset);
				sAbmArUpdateIncrUp.set_arAssetList(sAbmArAssetslist);

				FeeToAbmMapReduce mapReduce = new FeeToAbmMapReduce();
				if (ITableUtil.isMdbCloud()) {
					client.post(MdbKVDefine.FEEUPTOABM, sAbmArUpdateIncrUp, new SAbmArUpdateIncrRet(), mapReduce);
				} else {
					client.post(MdbKVDefine.FEEUPTOABM, sAbmArUpdateIncrUp, new SAbmArUpdateIncrRet());
				}
				imsLogger.debug("-------end to deal oneTimeFeeup2ABM");
			}
		} catch (SALException e) {
			imsLogger.error("新增一次性免费资源处理失败", e);
			IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED,e);
		}

		try {
			if (CommonUtil.isNotEmpty(adjustList)) {
				// 开始调整免费资源
				imsLogger.debug("-------start to adjust oneTimeFeeup2ABM");
				SRatingAdjust adjust = new SRatingAdjust();
				adjust.set_otFreeresList(adjustList);
				SRatingAdjustRet ret = new SRatingAdjustRet();
				
				if(ITableUtil.isMdbCloud()){
					MdbRoute route = routCmp.getAccountRoute(adjustList.get(0).get_acctId());
					com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SRatingAdjust> entry2 = new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, SRatingAdjust>(
							route, adjust);
					List<com.ailk.easyframe.sal.mapreduce.Entry> list2 = new ArrayList<com.ailk.easyframe.sal.mapreduce.Entry>();
					list2.add(entry2);
					client.post(MdbKVDefine.ABM_ADJUST, list2, ret);
				}else{
					client.post(MdbKVDefine.ABM_ADJUST, adjust, ret);
				}

				imsLogger.debug("-------end to adjust oneTimeFeeup2ABM");
			}
		} catch (SALException e) {
			imsLogger.error("调整一次性免费资源处理失败", e);
			IMSUtil.throwBusiException(ErrorCodeExDefine.ACCESS_INTERFACE_FAILED);
		}
	}

	/**
	 * @see com.ailk.imssh.common.handler.BaseHandler#initData(java.util.List<?
	 *      extends jef.database.DataObject>[])
	 */
	@Override
	public void initData(List<? extends DataObject>... dataArr) throws IMSException {
	}

	private com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes convertSOtFreeRes(com.ailk.easyframe.sdl.MAbmRdlCommonDef.SOtFreeRes res, IFee ifee) {
		com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes rlt = new com.ailk.easyframe.sdl.MAbmInterfaceRatAdjDef.SOtFreeRes();
		rlt.set_acctId(res.get_acctId());
		rlt.set_ownerId(res.get_objectId());
		rlt.set_ownerType(res.get_objectType());
		rlt.set_productId(res.get_productId());
		rlt.set_itemCode(res.get_itemCode());
		rlt.set_validDate(res.get_validDate());
		rlt.set_expireDate(res.get_expireDate());

		rlt.set_clearFlag((short) 0);// 删除这条记录
		rlt.set_valueFlag((short) 1);

		rlt.set_amount(-1);
		rlt.set_usedValue(-1);

		rlt.set_measureId(-1);
		rlt.set_freeresType((short) -1);
		Long prodId = ifee.getProductId() == null ? 0 : ifee.getProductId();
		rlt.set_newProductId(prodId);
		rlt.set_newExpireDate(ConvertUtil.javaDate2SdlLong(ifee.getExpireDate()));
		rlt.set_newItemCode(-1L);
		rlt.set_newValidDate(-1);

		imsLogger.dump("需要删除的一次性免费资源", rlt);
		return rlt;
	}

	@Override
	public void handleDiffException(List<? extends DataObject>[] paramArr) {
		// TODO Auto-generated method stub
		
	}
}
