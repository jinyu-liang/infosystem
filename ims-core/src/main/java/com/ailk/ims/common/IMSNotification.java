package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ailk.ims.util.AmountUtil;
import com.ailk.ims.util.DateUtil;
/**
 * @Description: 告警通知包装对象，在信息管理侧用此对象，最后可以调用IMSUtil.doNotification() 
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public class IMSNotification{
	private Integer alarmId;//告警编号
	
	private Long custId;//客户id
	private Long acctId;//账户id
	private Long userId;//用户id，如果是账户级的通知，这个不必填
	
	private Short regionCode;//账户级的通知填账户的regionCode,用户级的填用户的regionCode
	
	private List<IMSNotifyParam> params;//告警 参数
	private List<String> phones;//告警手机号码

	

    public void setParams(List<IMSNotifyParam> params)
    {
        this.params = params;
    }

	public Integer getAlarmId() {
		return alarmId;
	}


	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}


	public Long getCustId() {
		return custId;
	}


	public void setCustId(Long custId) {
		this.custId = custId;
	}


	public Long getAcctId() {
		return acctId;
	}


	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Short getRegionCode() {
		return regionCode;
	}


	public void setRegionCode(Short regionCode) {
		this.regionCode = regionCode;
	}
	
	/**
	 * @Description: 用于添加日期类型的参数,帐处那边规定时间格式用yyyymmddhhmmss,所以这个方法会自动转换格式
	  * @author : wuyj
	  * @date : 2011-9-29  
	  * @param key
	  * @param date
	 */
	public void addDateParam(int key,Date date){
		String value = null;
		if(date != null)
			value = DateUtil.getFormatDate(date, DateUtil.DATE_FORMAT_YYYYMMDDHHMMSS);
		addParam(key,value,null);
	}
	public void addParam(int key,String value){
		addParam(key,value,null);
	}
	public void addParam(int key,String value,Integer measureId){
		if(params == null)
			params = new ArrayList<IMSNotifyParam>();
		
		params.add(new IMSNotifyParam(key,value,measureId));
	}
	
	/**
	 * @Description: 根据传入的金额转换为显示金额单位对应的数量
	 * 	 
	 * @author: tangjl5
	 * @Date: 2012-5-9
	 */
	public void addCurrencyParam(int key, Long value)
	{
	    if(params == null)
            params = new ArrayList<IMSNotifyParam>();
	    Double amount = AmountUtil.getDisplayAmount(null, value);
        params.add(new IMSNotifyParam(key, String.valueOf(amount), AmountUtil.getDisplayMeasureId(null)));
	}
	
	public String getParam(int key){
		if(params == null)
			return null;
		
		for(IMSNotifyParam param : params){
			if(key == param.id)
				return param.value;
		}
		return null;
	}
	
	public boolean containsParam(int key){
		if(params == null)
			return false;
		
		for(IMSNotifyParam param : params){
			if(key == param.id)
				return true;
		}
		return false;
	}

	public List<IMSNotifyParam> getParams() {
		return params;
	}
	
	
	public void addPhone(String phoneId){
		if(phones == null)
			phones = new ArrayList<String>();
		phones.add(phoneId);
	}
	public List<String> getPhones() {
		return phones;
	}


	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
	public class IMSNotifyParam{
		private int id;
		private String value;
		private Integer measureId;
		
		public void setId(int id)
        {
            this.id = id;
        }
        public void setValue(String value)
        {
            this.value = value;
        }
        public void setMeasureId(Integer measureId)
        {
            this.measureId = measureId;
        }
        protected IMSNotifyParam(int id,String value){
			this.id = id;
			this.value = value;
		}
		protected IMSNotifyParam(int id,String value,Integer measureId){
			this.id = id;
			this.value = value;
			this.measureId = measureId;
		}
		public int getId() {
			return id;
		}
	
		public String getValue() {
			return value;
		}
		
		public Integer getMeasureId() {
			return measureId;
		}
		
	}
}

