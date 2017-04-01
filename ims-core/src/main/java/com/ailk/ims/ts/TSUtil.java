package com.ailk.ims.ts;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jef.database.DataObject;
import jef.database.Field;
import jef.tools.reflect.BeanUtils;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.ailk.easyframe.sdl.MImsTsApp.IImsTsAppInt;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.openbilling.persistence.imsts.entity.STsParam;
import com.ailk.openbilling.persistence.imsts.entity.STsRecord;

/**
 * 同步CRM获取URL工具类
 * 
 * @Description
 * @author wukl
 * @Date 2012-2-23
 * @Date 2012-5-20 新增方法getUploadInvokeMethodName，getUploadInvokeObject
 */
public class TSUtil
{
	private static List<WSEngine> services;
	public static String getChangeCustomerstatusUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_CHANGECUSTOMERSTATUS_SERVICE_URL);
    }

    public static String getSyncInfo2CSRUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_INFO2CSR_SERVICE_URL);
    }

    public static String getSyncLowBalance2ETopUpUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_LOWBALANCE2ETOPUP_SERVICE_URL);
    }

    public static String getSyncOneTimePromotionUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_ONETIMEPROMOTION_SERVICE_URL);
    }

    public static String getSyncProductOrderUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_PRODUCTORDER_SERVICE_URL);
    }

    public static String getSyncProductStatus2CRMUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_PRODUCTSTATUS2CRM_SERVICE_URL);
    }

    public static String getFirstActiveUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.FIRST_ACTIVE_URL);
    }

    public static String getSyncustomerinfo2CRMUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_CUSTOMERINFO2CRM_SERVICE_URL);
    }

    public static String getSystemBarServiceUrl()
    {
        return SysUtil.getString(SysCodeDefine.busi.SYNC_SYSTEMBARSERVICE_SERVICE_URL);
    }
    /**
	 * 获取读取数据需要上传的方法名称，如果是本地测试，则直接调用生成的服务端类中的对应deal_*方法；如果是正规运行，则调用对应的deal_*_upload方法
	 * @param isLocalTest，是否本地测试
	 * @return
	 * wuyj
	 */
	public static String getUploadInvokeMethodName(String method,boolean isLocalTest){
		String dealMethod = null;
		if(method.startsWith("read_")){
			dealMethod = method.replaceFirst("read_", "deal_");
		}else if(method.startsWith("deal_")){
			dealMethod = method;
		}else{
			IMSUtil.throwBusiException("invalid param \"method\".");
		}
		return isLocalTest ? dealMethod : dealMethod + "_upload";
	}
	
	/**
	 * 获取读取数据需要上传的服务对象，如果是本地测试，则直接调用生成的服务端类；如果是正规运行，则调用对应的客户端类
	 * @param isLocalTest
	 * @return
	 * 2012-09-04 gaoqc5 使用edl方式代替sdl方式生成代码时 server 端和client端合并成 xxxInt一个文件,模块名也去掉
	 */
	public static Object getUploadInvokeObject(String serviceName,boolean isLocalTest){
    	String lastName = serviceName.substring(serviceName.lastIndexOf(".")+1);
//    	String packageName = serviceName.substring(0,serviceName.lastIndexOf("."));
    	lastName = lastName.replace("ServiceImpl", "");//去除ServiceImpl
    	
    	String appName = lastName.substring(1);//得到当前应用名称，如ImsTsApp
    	
    	//获取模块名称，如"imsts"
//    	String moduleName = packageName.substring(packageName.lastIndexOf(".")+1);
    	
    	StringBuffer className = new StringBuffer("com.ailk.easyframe.sdl")
//    					.append(moduleName)
    					.append(".M")
    					.append(appName)
    					.append(".I")
    					.append(appName)
    					
    					.append("Int");
    	//2012-09-04 gaoqc5 使用edl方式代替sdl方式生成代码时 server 端和client端合并成 xxxInt一个文件
//    	if(isLocalTest){
//    		className.append("Imp");
//    	}else{
//    		className.append("Int");
//    	}
		
		return ClassUtil.instance(className.toString());
	}
	
	/**
     * 创建servicebean
     * @param serviceUrl
     * @param serviceClazz
     * @return
     */
    public static WSEngine createServiceBean(String serviceUrl, Class<?> serviceClazz)
    {
    	WSEngine result = null;
    	//@Date 2012-09-04 yangjh : !isvalid
    	if(!CommonUtil.isValid(serviceUrl)){
    	    IMSUtil.throwBusiException(ErrorCodeDefine.COMMON_SERVICE_URL_NULL);
    	}
    	if (CommonUtil.isNotEmpty(services))
        {
            for (WSEngine serviceBean : services)
            {
                if (serviceBean.getServiceClass() == serviceClazz && serviceBean.getServiceUrl().equals(serviceUrl))
                {
                	result = serviceBean;// 找到之前已创建的，直接返回
                	break;
                }
            }
        }
        if(result == null){
	        if (services == null)
	            services = new ArrayList<WSEngine>();
	
	        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	        factory.setAddress(serviceUrl + "?wsdl");
	        factory.setServiceClass(serviceClazz);
	        Object service = factory.create();
	        result = new WSEngine(serviceUrl, serviceClazz, service);
	        services.add(result);
        }
        return result;
    }
    
    public static DBCondition getIdCondition(DataObject dbObj,Field idField) throws Exception{
		List<Field> list = new ArrayList<Field>();
		list.add(idField);
		return DBUtil.createDBCondition(dbObj, list)[0];
	}
    
    public static long getId(DataObject dbObj,Field idField) throws Exception{
    	return (Long)ClassUtil.getPropertyValue(dbObj, idField.name());
    }
    
    /**
	 * 把从TB中取出SDL对象再次转换回对应的数据库实体对象。
	 * @param tsRecord
	 * @param dbClass
	 * @return
	 */
    public static DataObject trans2DataObject(STsRecord tsRecord,Class<? extends DataObject> dbClass){
    	try
        {
    		DataObject dbObj = (DataObject)ClassUtil.instance(dbClass);
    		
    		for(STsParam param : tsRecord.getParamList()){
            	String name = param.getName();
            	String value = param.getValue();
            	ClassUtil.setFieldValue(dbObj, name, value);
            }
    		
    		/*if(logger.isDebugEnabled()){
    			PrintUtil.print("****** [sdl->db]sdl object", tsRecord);
    			PrintUtil.print("****** [sdl->db]data object", dbObj);
    		}*/
    		
            return dbObj;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }
    
    /**
	 * 把读取出来的数据库对象转换成上发到TB需要的SDL对象。
	 * @param dbObj
	 * @return
	 */
    public static STsRecord trans2TsRecord(DataObject dbObj){
    	try
        {
    		STsRecord tsRecord = new STsRecord();
    		CsdlArrayList<STsParam> paramList = new CsdlArrayList<STsParam>(STsParam.class);
    		
    		
    		java.lang.reflect.Field[] fs = ClassUtil.getFields(dbObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                String fName = f.getName();
            	Object value = ClassUtil.getPropertyValue(dbObj, f.getName());
                if (value != null){
                	STsParam param = new STsParam();
                	param.setName(fName);
                	String str = null;
                	if(value instanceof Date){
                		str = IMSUtil.formatDate((Date)value);
                	}else{
                		str = value.toString();
                	}
                	param.setValue(str);
                	paramList.add(param);
                	
                }
            }
            tsRecord.setParamList(paramList);
            
            /*if(logger.isDebugEnabled()){
    			PrintUtil.print("****** [db->sdl]data object", dbObj);
    			PrintUtil.print("****** [db->sdl]sdl object", tsRecord);
    		}*/
            return tsRecord;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }
    
    public static Date getTimeOutDate(Date requestDate)
    {
        // 这里可以设置超时时间
        int second = -1 * SysUtil.getInt(SysCodeDefine.busi.TIMEOUT_SECOND_VALUE, 60);//默认是60s超时
        Calendar cal = Calendar.getInstance();
        cal.setTime(requestDate);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }
    
    /**
     * 从上发接口表的数据对象转成历史表数据对象
     * @param dbObj
     * @param hisTableClass
     * @return
     * 2012-05-19 wuyujie
     */
    public static DataObject copyJava2His(DataObject dbObj,DataObject hisObj){
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(dbObj.getClass());

            for (java.lang.reflect.Field f : fs)
            {
                if (!Modifier.isPrivate(f.getModifiers()))
                {
                    continue;
                }
                String fieldName = f.getName();
                ClassUtil.setFieldValue(hisObj, fieldName, ClassUtil.getPropertyValue(dbObj, fieldName));
            }
        }
        catch (Exception e)
        {
            IMSUtil.throwBusiException(e);
        }
        return hisObj;
    }
    /**
     * 上发数据接口，根据isLocalTest参数，如果是true则直接调用服务端dealMethodName方法，
     * 如果是false则说明是正规流程，会调用dealMethodName_upload方法上发到TB
     * @param serviceName,调用上发流程的服务名称
     * @param dealMethodName,调用上发的方法名称，注意不能带upload
     * @param dataList，上发的对象
     * @throws Exception
     */
    public static void uploadData(String serviceName,String dealMethodName,CsdlArrayList<STsRecord> dataList) throws Exception{
    	boolean isLocalTest = SysUtil.isLocalTest();
    	Object dealAppObj = getUploadInvokeObject(serviceName,isLocalTest);
        String dealUploadMethodName = getUploadInvokeMethodName(dealMethodName,isLocalTest);
        
        Class[] paramTypes = { CsdlArrayList.class, com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg.class };
        Object[] params = { dataList, new com.ailk.easyframe.sdl.sdlbuffer.CBSErrorMsg() };
		ClassUtil.invokeMethod(dealAppObj, dealUploadMethodName, paramTypes, params);
    }
}
