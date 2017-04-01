package com.ailk.imssh.common.flow.dispatch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jef.database.AbstractDbClient;
import jef.database.Batch;
import jef.database.DataObject;

import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.flow.config.ISendToMemberConfig;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;

/**
 * @Description:生成提醒短信的服务
 * @author liming15
 * @Date 2013-05-31
 */
public class SendSmsToMemberService implements ISendToMemberService {
	private ImsLogger imsLogger = new ImsLogger(this.getClass());

	/**
	 * 根据提醒触发记录为群内所有成员生成提醒短信
	 */
	@Override
	public void makeSmsToTiOSmsImme(List<DataObject> dataObjectList, ISendToMemberConfig config) throws Exception {
		// TODO Auto-generated method stub
		long t1 = System.currentTimeMillis();
		imsLogger.debug("****************start to make SMS for members ");

		AbstractDbClient dbClient = DBUtil.getDBClient();
		// 保存TiOSmsImme表分表的对象List
		List<List<DataObject>> tiOSmsImmeObjectListList = new ArrayList<List<DataObject>>(config.getSubTableCount());
		// 一开始就初始化出来，提高效率
		for (int i = 0; i < config.getSubTableCount(); i++) {
			tiOSmsImmeObjectListList.add(new ArrayList<DataObject>());
		}

		// 保存提醒触发表的删除对象,需要移历史表
		List<DataObject> deleteTableObjList = new ArrayList<DataObject>();
		// 历史对象
		List<DataObject> toHisObjectList = new ArrayList<DataObject>();
		for (DataObject dataObject : dataObjectList) {
			// 提醒触发表对象对应的提醒表删除对象
			DataObject deleteTableObj = config.getDeleteDataObject(dataObject);
			deleteTableObjList.add(deleteTableObj);

			// 插入历史表的对象
			Date dealDate = DateUtil.currentDate();
			DataObject toHisObject = config.convertToHis(dataObject, dealDate);
			toHisObjectList.add(toHisObject);

			// 获取群成员
			List<CaAccountRv> memberObjectList = config.getGroupMember(dataObject, dealDate);
			imsLogger.debug(new Object[]{t1, " end query table:", "CA_ACCOUNT_RV", ",size:", (memberObjectList == null ? 0 : memberObjectList.size())});
			if (CommonUtil.isNotEmpty(memberObjectList)) {
				for (CaAccountRv memberObject : memberObjectList) {
					// 按手机号取分表号
					int mod = 0;
					String phoneId = memberObject.getPhoneId();
					if (phoneId == null) {
						continue;
					} else {
						try {
							mod = (int) (Long.parseLong(phoneId) % config.getSubTableCount());
						} catch (Exception e) {
							mod = 0;
						}
					}

					// 得到短信内容对象
					DataObject TiOSmsImmeObject = config.convertToTiOSmsImmeObject(dataObject, phoneId);

					// 增加短信实体到List分组中
					tiOSmsImmeObjectListList.get(mod).add(TiOSmsImmeObject);
				}
			}
		}

		// 删除提醒触发表中的记录
		Batch<DataObject> delTableBatch = dbClient.startBatchDelete(deleteTableObjList.get(0));
		delTableBatch.add(deleteTableObjList);
		delTableBatch.commit();
		deleteTableObjList.clear();
		// 将记录移到历史表中
		String hisTableName = config.getHisTableName();
		Batch<DataObject> hisTableBatch = dbClient.startBatchInsert(toHisObjectList.get(0), hisTableName);
		hisTableBatch.add(toHisObjectList);
		hisTableBatch.commit();
		toHisObjectList.clear();

		// 将提醒短信插入到短信表
		Batch<DataObject> subTableBatch = null;
		for (int mod = 0; mod < tiOSmsImmeObjectListList.size(); mod++) {
			List<DataObject> subTableObjList = tiOSmsImmeObjectListList.get(mod);
			if (subTableObjList.isEmpty()) {
				continue;
			}

			// 增加数据到对应短信分表["JD.TI_O_SMS_IMME_(0-49}"]
			String subTableName = config.getTiOSmsImmeNamePrefix() + mod;
			imsLogger.debug("insert SMS to Ti_O_SMS_table: ", subTableName);
			subTableBatch = dbClient.startBatchInsert(subTableObjList.get(0), subTableName);
			subTableBatch.add(subTableObjList);
			subTableBatch.commit();
			subTableObjList.clear();
		}
		tiOSmsImmeObjectListList.clear();
		imsLogger.debug(new Object[]{t1, "****************end to make SMS for members"});

	}
}
