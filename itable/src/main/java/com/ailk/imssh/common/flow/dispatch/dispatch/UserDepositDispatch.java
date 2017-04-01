package com.ailk.imssh.common.flow.dispatch.dispatch;

import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.config.UserDepositConfig;

public class UserDepositDispatch extends Dispatch {

	@Override
	protected IConfig getConfig() {
		// TODO Auto-generated method stub
		return UserDepositConfig.getInstance();
	}

	
	public static void main(String[] args)
    {
        new UserDepositDispatch().doDispatch();
    }
}
