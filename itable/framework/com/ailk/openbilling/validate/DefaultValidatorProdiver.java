package com.ailk.openbilling.validate;

import com.ailk.easyframe.web.common.invoker.ValidatorProdiver;
import com.ailk.easyframe.web.common.invoker.AbstractValidator.Regex;
import jef.codegen.support.NotModified;
@NotModified
public class DefaultValidatorProdiver extends ValidatorProdiver{

	public void addUserDefineValidator(){
		add("com.ailk.phonenumber", new Regex("^1[3,5,8]{1}[0-9]{1}[0-9]{8}$","com.ailk.phonenumber.invalidText"));

		add("com.ailk.notempty", new Regex(".{1,}","com.ailk.notempty.invalidText"));

		add("com.ailk.email", new Regex("^|(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)$","com.ailk.email.invalidText"));

		add("com.ailk.url", new Regex("[a-zA-z]+://[^\\s]*","com.ailk.url.invalidText"));

		add("com.ailk.userAccount", new Regex("^[a-zA-Z][a-zA-Z0-9_]{4,15}$","com.ailk.userAccount.invalidText"));

		add("com.ailk.landPhone", new Regex("\\d{3}-\\d{8}|\\d{4}-\\d{7}","com.ailk.landPhone.invalidText"));

		add("com.ailk.chinaPostCode", new Regex("[1-9]\\d{5}(?!\\d)","com.ailk.chinaPostCode.invalidText"));

		add("com.ailk.chinaIdCard", new Regex("\\d{15}|\\d{18}","com.ailk.chinaIdCard.invalidText"));

		add("com.ailk.ipAddr", new Regex("^\\d+\\.\\d+\\.\\d+\\.\\d+$","com.ailk.ipAddr.invalidText"));

		add("com.ailk.userAccount", new Regex("^[a-zA-Z][a-zA-Z0-9_]{4,15}$","com.ailk.userAccount.invalidText"));

		add("com.ailk.chineseChar", new Regex("\\u4e00-\\u9fa5","com.ailk.chineseChar.invalidText"));

	}


}