package com.ailk.imssh.common.flow.error.bean;

import java.util.Date;
import com.ailk.ims.util.DateUtil;
import com.ailk.imssh.common.flow.consts.FlowConst;
import com.ailk.imssh.common.flow.scandeal.queue.holder.DataHolder;

/**
 * @Description:错误队列中的错单对象
 * @author wangjt
 * @Date 2012-11-7
 */
public class ErrorHolder extends DataHolder {
	private int dealCount = 1;// 已经处理次数，初始值为1
	private Date nextDealTime;// 下次该处理时间，不为null
	private boolean isRemove = false;// 是否已经从错误队列中移走。为了解决db成功
										// mdb失败，db提交失败的时候会移出队列两次的情况,默认没有移走
	private boolean needWaiting = true;

	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	public ErrorHolder(DataHolder dataHolder) {
		super(dataHolder.getSubTableName(), dataHolder.getDataObject(), dataHolder.getBusiValue(), dataHolder.getInfoBean());
		int delay = FlowConst.FIRST_WAIT_SECOND;// 5秒后
		nextDealTime = DateUtil.currentDate();
		nextDealTime.setTime(nextDealTime.getTime() + delay * 1000);
	}

	/**
	 * 错单再次出错
	 */
	public void failAgain() {
		// 3 的dealCount次方 * 5
		// 5次为半个小时，超过5次认则设置为5次，表示重试时间达到半个小时后，后续都只间隔半个小时就开始重试
		int count = dealCount > 5 ? 5 : dealCount;
		// int delay = (int) (Math.pow(FlowConst.MULTIPLY_FACTOR, dealCount) *
		// FlowConst.FIRST_WAIT_SECOND);
		int delay = (int) (Math.pow(FlowConst.MULTIPLY_FACTOR, count) * FlowConst.FIRST_WAIT_SECOND);
		nextDealTime = DateUtil.currentDate();
		nextDealTime.setTime(nextDealTime.getTime() + delay * 1000);

		dealCount += 1;
	}

	/**
	 * 是否超过配置的最大重试次数
	 */
	public boolean isExceedMaxRetryTime() {
		return dealCount > FlowConst.MAX_RETRY_TIME || !needWaiting;// 默认为10；
	}

	/**
	 * 当前时间小于下次该执行的时间，则返回true，表示该等待，而不执行
	 */
	public boolean isNeedWaiting(Date current) {
		return current.before(nextDealTime);
	}

	/**
	 * 返回true，表示错单
	 */
	@Override
	public boolean isError() {
		return true;
	}

	public void setNeedWaiting(boolean needWaiting) {
		this.needWaiting = needWaiting;
	}
}
