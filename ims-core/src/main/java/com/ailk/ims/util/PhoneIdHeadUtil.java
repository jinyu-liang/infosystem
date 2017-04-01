package com.ailk.ims.util;

import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.phoneidhead.IPhoneIdHead;

/**
 * @Description: 号码头处理工具类,封装了根据版本获取相应实现类
  * @Author wangdw5                                                                                                                                                                                                                                                                           
  * @Date 2012-06-14
 */
public class PhoneIdHeadUtil {
    private static IPhoneIdHead headParse;

    public static IPhoneIdHead getHeadParse()
    {
	    if(headParse == null)
        {
            String project = IMSUtil.getProject();
            String className = SysUtil.getString(SysCodeDefine.busi.IMS_PHONEID_HEAD_HANDLER.concat(project));
            if(className == null){
                className = ConstantDefine.DEFAULTPHONEIDHEADHANDLER;
            }
            headParse = (IPhoneIdHead) ClassUtil.instance(className);
        }
        return headParse;
    }

    public static void setHeadParse(IPhoneIdHead headParse)
    {
        PhoneIdHeadUtil.headParse = headParse;
    }
    
    public static String phoneIdParse(String phoneId){
        phoneId = getHeadParse().phoneIdParse(phoneId);
        return phoneId;
    }
    
    public static String phoneIdParse(String phoneId,Short deviceType)
    {
        phoneId = getHeadParse().phoneIdParse(phoneId,deviceType);
        return phoneId;
    }
	
	/**
	 * @Description: 号码头校验,新装用户时调用
	 * @param phoneId	 
	 * @author: wangdw5
	 * @Date: 2012-8-16
	 */
	public static void phoneIdCheck(String phoneId){
	    getHeadParse().phoneIdCheck(phoneId);
	}
}
