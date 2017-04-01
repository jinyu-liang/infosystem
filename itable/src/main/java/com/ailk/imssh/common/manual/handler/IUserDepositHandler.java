package com.ailk.imssh.common.manual.handler;

import org.springframework.transaction.annotation.Transactional;

import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.openbilling.persistence.jd.entity.IUserDepositNotify;

public interface IUserDepositHandler {
	
	/**
	 * 处理用户活动转换
	 * @param notify
	 * @param context
	 * @throws Exception
	 */
	@Transactional
	public void handleUserDeposit(IUserDepositNotify notify,ITableContext context) throws Exception;
}
