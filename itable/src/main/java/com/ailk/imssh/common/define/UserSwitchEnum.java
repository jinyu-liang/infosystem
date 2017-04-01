package com.ailk.imssh.common.define;

public enum UserSwitchEnum {
	$_0(0,487002106),  
	$_11(11,487002100),
	$_12(12,487002101),
	$_13(13,487002102),
	$_14(14,487002103);

	private Integer serviceCode;
	private Integer offerId;
	
	private UserSwitchEnum(Integer serviceCode, Integer offerId) {
		this.serviceCode = serviceCode;
		this.offerId = offerId;
	}
	public static Integer getOfferId(Integer serviceCode){
		for(UserSwitchEnum use:UserSwitchEnum.values()){
			if(use.serviceCode==serviceCode){
				return use.offerId;
			}
		}
		return EnumCodeExDefine.USER_SWITCH_ERROR_OFFERID;
	}
	
	public Integer getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(Integer serviceCode) {
		this.serviceCode = serviceCode;
	}
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	
	
}
