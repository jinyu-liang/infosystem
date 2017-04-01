package com.ailk.ims.util;

import java.util.Properties;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
/**
 * @Description: 提示信息操作类，主要是错误信息用于国际化语言提示         
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class MsgUtil {
	public static final String PATH_CONF_MESSAGE_EXP = "/ims-conf/message/error_?.properties";
	
	private static Properties message_config;
	private static ImsLogger logger = new ImsLogger(MsgUtil.class);
	
	public static void init() throws IMSException {
		/*String language = SysUtil.getInnerString(SysCodeDefine.INNER_LANGUAGE);
		if (CommonUtil.isEmpty(language))
			throw new IMSException("\""+SysCodeDefine.INNER_LANGUAGE+"\" in file \"" + SysUtil.PATH_CONF_SYS + "\" must be defined.");
		String messageFile = PATH_CONF_MESSAGE_EXP.replace("?", language);
		try{
			message_config = CommonUtil.loadProperties(messageFile);
			if(message_config.isEmpty())
				return;
			Object[] keys = message_config.keySet().toArray();
			for(Object key : keys){
				String strKey = (String)key;
				if(CommonUtil.isEmpty(strKey))
					continue;
				LogUtil.getLogger(MsgUtil.class).info("*****     load : ["+strKey+"] = "+ message_config.getProperty(strKey));
			}	
		}catch(Exception e){
			throw new IMSException(e);
		}
		if (message_config == null) {
			throw new IMSException("Can not find config file : " + messageFile);
		}*/
	}
	
	public static String getErrorMessage(long errorCode){
		String value = CommonUtil.getPropertyValue(message_config,String.valueOf(errorCode));
		if(CommonUtil.isEmpty(value)){
		    logger.info("errorCode \""+errorCode+"\" is not defined.");
			return CommonUtil.getPropertyValue(message_config,String.valueOf(ErrorCodeDefine.UNKNOWN_ERROR));
		}
		return value;
	}
}
