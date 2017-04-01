package com.ailk.imssh.common.bean;

import java.util.Calendar;
import java.util.Date;

import com.ailk.ims.common.ContextBean;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.util.DateUtil;

/**
 * @Description:公共信息对象
 * @author wangjt
 * @Date 2012-5-11
 */
public class ContextExBean extends ContextBean {
	protected ITableContext context;

	@Override
	public ITableContext getContext() {
		return context;
	}

	@Override
	public void setContext(IMSContext context) {
		this.context = (ITableContext) context;
	}

	/**
	 * 湖南项目专用，时间减少1s
	 * @param date
	 * @return
	 */
	public Date adjustDate(Date date) {
		return DateUtil.offsetDate(date, Calendar.SECOND, -1);
	}
	
}
