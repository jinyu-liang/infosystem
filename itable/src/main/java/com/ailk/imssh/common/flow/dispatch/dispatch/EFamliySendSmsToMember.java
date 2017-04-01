package com.ailk.imssh.common.flow.dispatch.dispatch;

import com.ailk.imssh.common.flow.config.ISendToMemberConfig;
import com.ailk.imssh.common.flow.config.SendToMemberConfig;


/**
 * @Description:生成e家亲成员提醒短信
 * @author liming15
 * @Date 2013-5-31
 */
public class EFamliySendSmsToMember extends SendSmsToMember
{

	/**
     * 启动成员短信生成线程
     */
    public static void main(String[] args)
    {
        new EFamliySendSmsToMember().doSendSmsToMember();
    }

	@Override
	protected ISendToMemberConfig getConfig() {
		// TODO Auto-generated method stub
		return SendToMemberConfig.getInstance();
	}


}
