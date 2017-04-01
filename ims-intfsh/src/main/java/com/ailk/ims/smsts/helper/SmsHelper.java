package com.ailk.ims.smsts.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jef.database.Condition.Operator;
import jef.database.Field;

import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.smsts.bean.ScanStartInfo;
import com.ailk.ims.smsts.bean.SmsParam;
import com.ailk.ims.smsts.config.SmsConfig;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.jd.entity.SmsSendConfig;
import com.ailk.openbilling.persistence.jd.entity.SmsSendErrObj;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;
import com.ailk.openbilling.persistence.jd.entity.SmsSendLog;
import com.ailk.openbilling.persistence.jd.entity.SmsSendValue;
import com.ailk.openbilling.persistence.jd.entity.TdSSendTpl;

/**
 * @Description:流程帮助类
 * @author wangjt
 * @Date 2012-8-6
 */
public final class SmsHelper {
	private static ImsLogger imsLogger = new ImsLogger(SmsHelper.class);

	/**
	 * @return:day of month,range:[1,31]
	 */
	private static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 返回解析int数组，
	 */
	private static int[] getRunDayArr(Integer busiNum) {
		// 1、获取配置[其中包含正确性检查]
		SmsSendConfig config = SmsConfig.getSmsSendConfigByBusiNum(busiNum);
		if (config == null) {
			throw new IMSException("SMS_SEND_CONFIG表中未配置busi_num=" + busiNum
					+ "的数据！！！！");
		}
		String runDayStr = config.getRunDay();
		if (runDayStr == null || runDayStr.length() == 0) {
			throw new IMSException("SMS_SEND_CONFIG表中busi_num=" + busiNum
					+ "的数据RunDay配置错误！！！！");
		}

		// 2、如果为通配，则返回31天
		if (runDayStr.trim().equals("*")) {
			return new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
					15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
					30, 31 };
		}
		// 3、解析为数组
		String[] runDayArr = runDayStr.split("\\s*,\\s*");
		int[] runDayArrInt = new int[runDayArr.length];
		for (int i = 0; i < runDayArr.length; i++) {
			runDayArrInt[i] = Integer.parseInt(runDayArr[i]);
		}

		if (runDayArr.length > 1) {
			Arrays.sort(runDayArrInt);// 从小到大排序
		}

		return runDayArrInt;
	}

	/**
	 * @param runDayArr
	 *            ：任务需要被执行的日期数组
	 * @param currentDate
	 *            :需要判断的日期
	 * @return:该日期是否需要执行定时任务
	 */
	private static boolean isRunDay(int[] runDayArr, Date currentDate) {
		int dayOfMonth = SmsHelper.getDayOfMonth(currentDate);// 当天的日期，范围：[1,31]
		int binarySearch = Arrays.binarySearch(runDayArr, dayOfMonth);
		return binarySearch >= 0;
	}

	/**
	 * 前置条件：dayOfMonth 不包含在 runDayArr中
	 * 
	 * @param runDayArr
	 *            :{11,21}
	 * @param date
	 *            :
	 * @return:yyyyMMdd
	 */
	private static String getPreviousConfigDate(int[] runDayArr, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);// 要检查的日期 [1,31]
		int len = runDayArr.length;

		if (dayOfMonth < runDayArr[0]) {
			cal.add(Calendar.MONTH, -1);
			int maximum = cal.getMaximum(Calendar.DAY_OF_MONTH);
			for (int i = len - 1; i >= 0; i--) {
				if (runDayArr[i] <= maximum) {
					cal.set(Calendar.DAY_OF_MONTH, runDayArr[i]);
					break;
				}
			}
		} else if (dayOfMonth > runDayArr[len - 1]) {
			cal.set(Calendar.DAY_OF_MONTH, runDayArr[len - 1]);
		} else {
			for (int i = 0; i < runDayArr.length; i++) {
				if (dayOfMonth < runDayArr[i + 1]) {
					cal.set(Calendar.DAY_OF_MONTH, runDayArr[i]);
					break;
				}
			}
		}

		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}

	/**
	 * 判断本次任务是否需要执行，需要，返回执行的任务编号，不需要，返回null
	 */
	private static String getTaskDate(Integer busiNum, String subTableName,
			Date currentDate) {
		int[] runDayArr = SmsHelper.getRunDayArr(busiNum);// 需要执行的日期，如{11,21}

		/**
		 * 本次执行的任务，属于配置中的日期(上一次应该执行的日期)<BR>
		 * 比如配置每月11和21号22点执行， <br>
		 * 如果2012-8-21 22:00:00执行任务，taskDate=20120821<br>
		 * 如果2012-8-21 22:00:00执行任务失败，2012-8-22
		 * 22:00:00继续执行任务，taskDate还是20120821<br>
		 */
		String taskDate = null;
		if (SmsHelper.isRunDay(runDayArr, currentDate))// 配置的当天执行的任务
		{
			taskDate = new SimpleDateFormat("yyyyMMdd").format(currentDate);
		} else {// 本次任务的执行时间，非配置的当天
				// 任务配置的日期(yyyyMMdd)，即本天的上一次应该执行任务的具体日期，格式：yyyyMMdd
			taskDate = SmsHelper.getPreviousConfigDate(runDayArr, currentDate);
		}

		// 判断配置的任务是否执行完成(当天也需要判断，防止一天多次执行)
		SmsSendLog smsSendLog = DBUtil.querySingle(SmsSendLog.class,
				new DBCondition(SmsSendLog.Field.tableName, subTableName),
				new DBCondition(SmsSendLog.Field.busiNum, busiNum),
				new DBCondition(SmsSendLog.Field.taskDate, taskDate));

		if (smsSendLog != null)// 日志表有记录，表示上次任务已经正确执行完毕
		{
			imsLogger.debug("busiNum:", busiNum, ",subTableName:",
					subTableName, ",taskDate:", taskDate,
					" has already been executed!");
			return null;// 本次任务无需执行
		}
		return taskDate;
	}

	/**
	 * 初始化任务起始信息
	 */
	public static ScanStartInfo initStartInfo(String subTableName,
			Integer busiNum, DBCondition[] queryCondArr, Field keyField,
			Date currentDate, SmsParam param) {
		String taskDate = SmsHelper.getTaskDate(busiNum, subTableName,
				currentDate);

		if (taskDate == null) {
			return null;// 本次任务无需执行
		}

		// 记录此次扫描需要发送的短信条数
		int count = 0;
		Long value = 0L;

		DBCondition[] queryCondArrNew = queryCondArr;// 具体查询条件

		// 查询是否有上次任务未执行完成的信息
		List<SmsSendValue> values = DBUtil.query(SmsSendValue.class,
				new OrderCondition[] { new OrderCondition(true,
						SmsSendValue.Field.currentValue) }, null,
				new DBCondition[] {
						new DBCondition(SmsSendValue.Field.tableName,
								subTableName),
						new DBCondition(SmsSendValue.Field.busiNum, busiNum) });

		if (CommonUtil.isNotEmpty(values)) {
			for (SmsSendValue val : values) {
				if (isCurrentDay(val.getSoDate()))// 如果是同一天，上次任务中断，之后开始的流程，如果不是同一天，就不必补上次的任务了
				{
					Long nowVal = val.getCurrentValue();// 上次扫描完成的userId
					if (nowVal > value) {
						value = nowVal;
						count = val.getCurrentCount();
					}
					imsLogger.info("上次有异常中断的数据，blockId为", val.getBlockId(),
							",SmsCode为:", val.getSmsCode(), "开始使用此映射关系!");
					param.getTemplateIdAndBlockIdMap().put(val.getSmsCode(),
							val.getBlockId());
					if (val.getCurrentCount() > 0) {// 如果上次中断已经产生值，应该要创建审核总表
						imsLogger.info("标志", val.getSmsCode(), "为创建审核总表");
						param.addHasMsgSmsCode(val.getSmsCode());
					}

				}

			}
			if (value > 0) {
				imsLogger.info("查询条件加上关键值:", value);
				// 查询条件中，加上用户ID>上次执行完成的用户ID的条件
				queryCondArrNew = new DBCondition[queryCondArr.length + 1];
				System.arraycopy(queryCondArr, 0, queryCondArrNew, 0,
						queryCondArr.length);
				queryCondArrNew[queryCondArr.length] = new DBCondition(
						keyField, value, Operator.GREAT);

				param.setKeywordVal(value);
			}
		}

		ScanStartInfo info = new ScanStartInfo();
		info.setBlockId(param.getFisrtBlockId());
		info.setStartCount(count);
		info.setStartValue(value);
		info.setQueryCondArr(queryCondArrNew);
		info.setTaskDate(taskDate);
		return info;
	}

	/**
	 * 判断是否是同一天
	 * 
	 * @param lastDate
	 * @return
	 */
	private static boolean isCurrentDay(Date lastDate) {
		String lastDateStr = DateUtil.formatDate(lastDate,
				DateUtil.DATE_FORMAT_YYYYMMDD);
		String nowDateStr = DateUtil.formatDate(new Date(),
				DateUtil.DATE_FORMAT_YYYYMMDD);
		return lastDateStr.equals(nowDateStr);
	}

	/**
	 * 往批量短信审核明细表中插数据 ，同时记录其中处理错误的信息<br>
	 */
	public static void insertDetalList(
			List<SmsSendInterfaceCheck> smsSendInterfaceCheckList,
			List<SmsSendErrObj> smsSendErrObjList,
			List<SmsSendValue> smsSendValues) {
		if (smsSendInterfaceCheckList != null
				&& !smsSendInterfaceCheckList.isEmpty()) {
			for (List<SmsSendInterfaceCheck> list : groupbyBlockId(
					smsSendInterfaceCheckList).values()) {
				DBUtil.insertBatch(list);
			}
			imsLogger.debug("insert SmsSendInterfaceCheck list, size=",
					smsSendInterfaceCheckList.size());
		}

		if (smsSendErrObjList != null && !smsSendErrObjList.isEmpty()) {
			DBUtil.insertBatch(smsSendErrObjList);
			imsLogger.debug("insert SmsSendErrObj list, error size=",
					smsSendErrObjList.size());
		}

		List<SmsSendValue> temps = DBUtil.query(SmsSendValue.class,
				new DBCondition(SmsSendValue.Field.tableName, smsSendValues
						.get(0).getTableName()), new DBCondition(
						SmsSendValue.Field.busiNum, smsSendValues.get(0)
								.getBusiNum()));
		if (temps == null) {
			DBUtil.insertBatch(smsSendValues);
		} else {
			for (SmsSendValue val : smsSendValues) {
				SmsSendValue oldVal = getSmsSendValueBySmscode(temps,
						val.getSmsCode());
				if (null == oldVal) {
					DBUtil.insert(val);
				} else {
					SmsSendValue temp = new SmsSendValue();
					temp.setCurrentCount(oldVal.getCurrentCount());
					temp.setCurrentValue(oldVal.getCurrentValue());
					temp.setSoDate(oldVal.getSoDate());
					DBUtil.updateByCondition(
							temp,
							new DBCondition(SmsSendValue.Field.tableName,
									oldVal.getTableName()),
							new DBCondition(SmsSendValue.Field.busiNum, oldVal
									.getBusiNum()),
							new DBCondition(SmsSendValue.Field.smsCode, oldVal
									.getSmsCode()));
				}
			}
		}
	}

	private static SmsSendValue getSmsSendValueBySmscode(
			List<SmsSendValue> vals, Long smsCode) {
		for (SmsSendValue val : vals) {
			if (smsCode.equals(val.getSmsCode())) {
				return val;
			}
		}
		return null;
	}

	/**
	 * 不用的blockId需要分开插入到不同的分表
	 * 
	 * @param list
	 * @return
	 */
	private static Map<Long, List<SmsSendInterfaceCheck>> groupbyBlockId(
			List<SmsSendInterfaceCheck> list) {
		Map<Long, List<SmsSendInterfaceCheck>> map = new HashMap<Long, List<SmsSendInterfaceCheck>>();
		for (SmsSendInterfaceCheck check : list) {
			Long blockId = check.getBlockId();
			List<SmsSendInterfaceCheck> groupList = map.get(blockId);
			if (null == groupList) {
				groupList = new ArrayList<SmsSendInterfaceCheck>();
			}
			groupList.add(check);
			map.put(blockId, groupList);
		}
		return map;
	}

	/**
	 * 获取短信回执的配置
	 * 
	 */
	public static Integer getSendTpl(Long smsCode) {
		TdSSendTpl tpl = DBUtil.querySingle(TdSSendTpl.class, new DBCondition(
				TdSSendTpl.Field.smsCode, smsCode));
		int smsNetFlag = 0;
		try {
			smsNetFlag = tpl == null ? 0 : Integer.valueOf(tpl.getSmsNetTag());
		} catch (Exception e) {
			imsLogger.error(e,e);
		}

		return smsNetFlag;
	}

}
