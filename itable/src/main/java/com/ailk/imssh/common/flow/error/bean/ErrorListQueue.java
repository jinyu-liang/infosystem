package com.ailk.imssh.common.flow.error.bean;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import com.ailk.ims.util.ImsLogger;

/**
 * @Description:错误队列[第一层]
 * @author wangjt
 * @Date 2012-11-7
 */
public class ErrorListQueue {
	private static final LinkedList<ErrorListHolder> errorQueue = new LinkedList<ErrorListHolder>();

	/**
	 * 获取队列中的第一个用户的错单列表中的第一个错单
	 */
	public static ErrorHolder getFirstErrorHolder() {
		synchronized (errorQueue) {
			if (errorQueue.isEmpty()) {
				return null;
			}
			try {
				LinkedList<ErrorHolder> list = errorQueue.getFirst().getErrorHolderList();
				Collections.sort(list, new CommonCommitDateComparator());
				return list.getFirst();
			} catch (Exception e) {
				ImsLogger log = new ImsLogger(ErrorListQueue.class);
				log.info("排序出错~~~~~~~~~~~~~~~~");
				log.error(e, e);
			}
			return errorQueue.getFirst().getErrorHolderList().getFirst();
		}
	}

	/**
	 * 判断该用户是否存在先前错单
	 */
	public static boolean isPreviousErrorExist(long busiValue) {
		synchronized (errorQueue) {
			return searchListHolder(busiValue) != null;
		}
	}

	/**
	 * 获取一个用户在错误队列中的错单列表，不存在，返回null
	 */
	private static ErrorListHolder searchListHolder(long busiValue) {
		synchronized (errorQueue) {
			Iterator<ErrorListHolder> it = errorQueue.iterator();
			while (it.hasNext()) {
				ErrorListHolder errorListHolder = it.next();
				if (errorListHolder.getBusiValue() == busiValue) {
					return errorListHolder;
				}
			}
			return null;
		}
	}

	/**
	 * 要求错单在错误队列中不存在，如果存在，不可调用该方法，只需更新错单状态即可
	 */
	public static void addErrorHolderToQueue(ErrorHolder errorHolder) {
		synchronized (errorQueue) {
			ErrorListHolder errorListHolder = searchListHolder(errorHolder.getBusiValue());
			// 判断输入的错单的用户是否已经在错单队列中
			if (errorListHolder != null)// 该用户的错单列表已经在队列中
			{
				// 加入到该用户的错单列表末尾
				errorListHolder.getErrorHolderList().addLast(errorHolder);
			} else {
				// 增加一个新的cust_id对应错单到队列末
				errorQueue.addLast(new ErrorListHolder(errorHolder.getBusiValue(), errorHolder));
			}
		}
	}

	/**
	 * 添加错误到错误队列的前面要求错单在错误队列中不存在，如果存在，不可调用该方法，只需更新错单状态即可
	 */
	public static void addErrorHolderToQueueFirst(ErrorHolder errorHolder) {
		synchronized (errorQueue) {
			ErrorListHolder errorListHolder = searchListHolder(errorHolder.getBusiValue());
			// 判断输入的错单的用户是否已经在错单队列中
			if (errorListHolder != null)// 该用户的错单列表已经在队列中
			{
				// 加入到该用户的错单列表开头
				errorListHolder.getErrorHolderList().addFirst(errorHolder);
			} else {
				// 增加一个新的cust_id对应错单到队列的末尾
				errorQueue.addLast(new ErrorListHolder(errorHolder.getBusiValue(), errorHolder));
			}
		}
	}

	/**
	 * 第一个用户的错单列表挪到队列末尾[调用方保证队列不为空]
	 */
	public static void moveFirstListToLast() {
		synchronized (errorQueue) {
			if (errorQueue.size() > 1) {
				ErrorListHolder first = errorQueue.removeFirst();
				errorQueue.addLast(first);
			}
		}
	}

	/**
	 * 删除队列中的第一个错单[调用方保证队列不为空]
	 */
	public static void removeFirstErrorHolder() {
		synchronized (errorQueue) {
			ErrorListHolder firstListHolder = errorQueue.getFirst();
			LinkedList<ErrorHolder> errorHolderList = firstListHolder.getErrorHolderList();
			errorHolderList.removeFirst();

			// 如果第一个用户的错单为空，则删除该用户的队列
			if (errorHolderList.isEmpty()) {
				errorQueue.removeFirst();
			}
		}
	}

	public static long getSize() {
		synchronized (errorQueue) {
			return errorQueue.size();
		}
	}
}
