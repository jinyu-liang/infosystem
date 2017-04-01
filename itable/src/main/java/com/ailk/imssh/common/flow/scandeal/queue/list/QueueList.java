package com.ailk.imssh.common.flow.scandeal.queue.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import jef.database.DataObject;

import com.ailk.imssh.common.define.EnumCodeExDefine;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexSub;

/**
 * @Description: 队列列表对象[子类的实例应该为单例类]
 * @author wangjt
 * @Date 2012-9-13
 */
public class QueueList {
	private static final Map<Class<? extends IConfig>, QueueList> queueListMap = new HashMap<Class<? extends IConfig>, QueueList>();

	public static QueueList getQueueListByConfig(IConfig config) {
		synchronized (queueListMap) {
			QueueList queueList = queueListMap.get(config.getClass());
			if (queueList == null) {
				queueList = new QueueList(config);
				queueListMap.put(config.getClass(), queueList);
			}
			return queueList;
		}
	}

	private QueueList(IConfig config) {
		this.config = config;

		int dealThreadCount = config.getDealThreadCount();// 处理的线程数，等于队列数 ，如30
		queueList = new ArrayList<Queue>(dealThreadCount + 1);

		// 构造队列（30个）
		for (int i = 0; i <= dealThreadCount; i++) {
			queueList.add(new Queue());
		}
	}

	/**
	 * 所有加入到队30个列中的对象中的seqId的最大值[即下次查询，seqId从>该值开始]<BR>
	 * 该变量只在单线程下操作
	 */
	private long maxSeqId = 0;

	/**
	 * 队列列表（30个）
	 */
	private List<Queue> queueList = null;

	/**
	 * 由子类控制具体的配置信息
	 */
	private IConfig config;

	/**
	 * 获取第index个队列对象
	 */
	public Queue getQueue(int index) {
		return queueList.get(index);
	}

	/**
	 * 把小索引表中的数据分发到30个队列中
	 * 
	 * @return allAueueFull: 如果dataObjectList中的对象都没有加入到队列中，return true，表示队列都满；<br>
	 *         其余return false，表示队列还未满
	 */
	public boolean addListToQueue(List<DataObject> dataObjectList, String realTableName) {
		boolean allQueueFull = true;// 默认所有队列都满

		Map<Integer, List<DataObject>> tempMap = new HashMap<Integer, List<DataObject>>();

		int allThread = config.getDealThreadCount() * config.getSubTableCount();// 30*8
		// 大客户队列
		int bigCustThread = config.getDealThreadCount();
		// 第一步：分成30组

		Set<Long> bigBusiValueSet = new HashSet<Long>();

		for (DataObject dataObject : dataObjectList) {
			// 获得分组参考字段
			Long busiValue = config.getValueOfBusiFieldFromSub(dataObject);
			Long busiCode = config.getValueOfBusiCode(dataObject);
			if (Arrays.asList(EnumCodeExDefine.USER_ID_BUSI_CODE).contains(busiCode.toString())) {
				IDataIndexSub iDataIndexSub = (IDataIndexSub) dataObject;
				busiValue = iDataIndexSub.getUserId();
			}
			int index = 0;
			Queue bigCustQueue = queueList.get(bigCustThread);
			if (bigCustQueue.isPreviousErrorExist(busiValue) || busiCode == FlowConst.BIG_CUST_BUSI_CODE) {// 存在于大客户队列或者是低优先级的数据
				index = bigCustThread;
				bigBusiValueSet.add(busiValue);
			} else if (bigBusiValueSet.contains(busiValue)) {
				index = bigCustThread;
			} else {
				index = ((int) (busiValue % allThread)) / config.getSubTableCount();
			}

			// 分成30组 [id%(30*8)]/8 index:[0-29]
			List<DataObject> tempList = tempMap.get(index);
			if (tempList == null) {
				tempList = new ArrayList<DataObject>();
				tempMap.put(index, tempList);
			}
			tempList.add(dataObject);
		}

		// 列表中最后一个对象的seqId最大
		long maxSeqIdTemp = config.getValueOfSeqId(dataObjectList.get(dataObjectList.size() - 1));

		// 第二步，把30组数据列表加入到对应的队列中去
		Set<Entry<Integer, List<DataObject>>> entrySet = tempMap.entrySet();
		Iterator<Entry<Integer, List<DataObject>>> it = entrySet.iterator();
		while (it.hasNext()) {
			Entry<Integer, List<DataObject>> next = it.next();
			Integer index = next.getKey();
			List<DataObject> tempList = next.getValue();
			Queue queue = queueList.get(index);
			boolean addSucc = queue.addQueue(tempList, realTableName, config, index);
			// 根据是否增加到队列的结果重新计算maxSeqId
			if (!addSucc) {// 该组数据未加入到队列中
				DataObject tempMin = tempList.get(0);// seqId最小的对象

				long tempSeq = config.getValueOfSeqId(tempMin) - 1;
				if (tempSeq < maxSeqIdTemp) {
					maxSeqIdTemp = tempSeq;
				}
			} else if (allQueueFull) {
				allQueueFull = false;// 如果有数据add到队列中，表示有队列未满
			}
		}
		maxSeqId = maxSeqIdTemp;

		return allQueueFull;
	}

	public long getMaxSeqId() {
		return maxSeqId;
	}
}
