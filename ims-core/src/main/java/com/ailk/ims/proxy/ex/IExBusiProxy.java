package com.ailk.ims.proxy.ex;

import java.util.Date;

import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.common.BusiRequestInfo;
import com.ailk.ims.common.IMSContext;
/**
 * @Description: 业务代理基类，所有代理类都需要继承此类            
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2011-9-27
 */
public interface IExBusiProxy {
    /**
     * 创建请求时间
     * @author wuyj 2012-9-22
     * @return
     * @throws Exception
     */
    public Date createRequestDate() throws Exception;
    /**
     * 创建流水号
     * @author wuyj 2012-9-22
     * @param requestDate
     * @return
     * @throws BaseException
     */
    public long createDoneCode(Date requestDate) throws Exception;
    
    /**
     * 创建请求信息对象
     * @author wuyj 2012-9-22
     * @param params
     * @param requestDate
     * @return
     * @throws Exception
     */
    public BusiRequestInfo createBusiRequest(IMSContext context,Object[] params) throws Exception;
    
    
    
    /**
     * 执行proxy业务流程
     * @author wuyj 2012-9-25
     * @param context
     * @return
     * @throws Exception
     */
	public Object doProxyService(IMSContext context,Object[] params) throws Exception;
	
	/**
	 * 当调用doProxyService出现异常时，需要回调处理这个异常
	 * @author wuyj 2012-9-25
	 * @param t
	 * @return
	 */
	public Object doProxyException(IMSContext context,Class<?> retrunType,Throwable t) throws Exception;
	    
}
