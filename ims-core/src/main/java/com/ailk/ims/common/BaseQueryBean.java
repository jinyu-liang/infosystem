 package com.ailk.ims.common;                                                                                                                                                                                                                                                                       

import com.ailk.ims.exception.IMSException;
import com.ailk.openbilling.persistence.imsintf.entity.Do_queryResponse;
import com.ailk.openbilling.persistence.imsintf.entity.SQueryParam;
/**
 * @Description: 查询基类，适用于busi_code=6023大查询接口                 
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public abstract class BaseQueryBean extends ContextBean {
	/**
	 * @Description:执行具体的查询操作，需要把查询结果设置到result对象中
	  * @param acct_id
	  * @param user_id
	  * @param phone_id
	  * @param invoice_no
	  * @param start_date
	  * @param end_date
	  * @param result
	  * @throws IMSException
	 */
	public abstract void doQuery(SQueryParam queryParam,Do_queryResponse result) throws IMSException;
}
