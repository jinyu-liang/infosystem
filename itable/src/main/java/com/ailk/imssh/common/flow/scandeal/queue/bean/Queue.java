package com.ailk.imssh.common.flow.scandeal.queue.bean;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import jef.database.DataObject;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;

/**
 * @Description: 队列对象
 * @author wangjt
 * @Date 2012-9-13
 */
public class Queue {
	/**
	 * 队列中的对象中，seqId最大值（即是最后一个对象的seqId）
	 */
	private long maxSeqId = 0;
	/**
	 * 队列数据对象列表
	 */
	private LinkedList<DataHolder> dataHolderList = new LinkedList<DataHolder>();

	public LinkedList<DataHolder> getDataHolderList() {
		return dataHolderList;
	}

	public void setDataHolderList(LinkedList<DataHolder> dataHolderList) {
		this.dataHolderList = dataHolderList;
	}

	/**
	 * 加入到本队列中，返回false表示队列里面数据个数超过最大值，加入失败，返回true表示加入成功，此时队列对象个数可能超过最大值
	 * 
	 * @param dataObjectList
	 *            :要求dataObjectList是按照seqId从小到大排序的
	 * @param realTableName
	 *            :该批数据所在的表名称，如:cd.i_data_index_sub_[0-7]
	 */
	public boolean addQueue(List<DataObject> dataObjectList, String realTableName, IConfig config, int index) {
		int queueMaxCount = config.getQueueMaxCount();// 增加数据到队列中时 ，队列不能超过的数据总数

		synchronized (dataHolderList) {
			// 增加对象时，如果队列达到允许的最大值，则直接返回
			if("8".equals(realTableName.substring(realTableName.length()-1, realTableName.length()))){
				if (dataHolderList.size() >= 1340&& index != (config.getDealThreadCount())) {
					return false;
				}
			}else{
				if (dataHolderList.size() >= queueMaxCount && index != (config.getDealThreadCount())) {
					return false;
				}
			}

			boolean needCheck = true;

			for (DataObject dataObject : dataObjectList) {
				long seqId = config.getValueOfSeqId(dataObject);

				// 判断该需要增加的对象，是否已经增加到队列中了，如果是，直接返回，不再增加
				if(!"8".equals(realTableName.substring(realTableName.length()-1, realTableName.length()))){
					if (needCheck) {
						if (seqId > maxSeqId)// 要求iDataIndexSubList是按照seqId从小到大排序的
						{
							Long busiValue = config.getValueOfBusiFieldFromSub(dataObject);
							dataHolderList.addLast(new DataHolder(realTableName, dataObject, busiValue));
							needCheck = false;
						}
					} else {
						Long busiValue = config.getValueOfBusiFieldFromSub(dataObject);
						dataHolderList.addLast(new DataHolder(realTableName, dataObject, busiValue));
					}
				}else{
					
						Long busiValue = config.getValueOfBusiFieldFromSub(dataObject);
						dataHolderList.addLast(new DataHolder(realTableName, dataObject, busiValue));
					

				}
	
			}
			// 修正队列中seqId的最大值
			maxSeqId = config.getValueOfSeqId(dataObjectList.get(dataObjectList.size() - 1));// 最后一个的seqId最大
			return true;
		}
	}

	/**
	 * 队列为空时，返回null，不为空，返回第一个元素
	 */
	public DataHolder getRemoveFirst() {
		synchronized (dataHolderList) {
			return dataHolderList.poll();
		}
	}

	/**
	 * 判断该用户是否存在先前存在于队列中
	 */
	public boolean isPreviousErrorExist(long busiValue) {
		synchronized (dataHolderList) {
			Iterator<DataHolder> it = dataHolderList.iterator();
			while (it.hasNext()) {
				DataHolder dataHolder = it.next();
				if (dataHolder.getBusiValue() == busiValue) {
					return true;
				}
			}
			return false;
		}
	}

}
