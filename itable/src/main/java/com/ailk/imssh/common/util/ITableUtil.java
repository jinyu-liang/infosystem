package com.ailk.imssh.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jef.database.DataObject;
import jef.tools.reflect.BeanWrapper;

import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.easyframe.route.entity.dto.RouteDimension;
import com.ailk.easyframe.route.entity.dto.RouteResult;
import com.ailk.easyframe.web.common.session.ContextHolder;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SysUtil;
import com.ailk.imssh.common.cmp.RouterCmp;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.order.cmp.UserOrderCmp;
import com.ailk.ts.TS;

/**
 * @Description:定义共用方法
 * @author wangjt
 * @Date 2012-5-22
 */
public class ITableUtil{
    private static RouterCmp routerCmp=new RouterCmp();
	private static ImsLogger imsLogger = new ImsLogger(ITableUtil.class);
	public static boolean isMdbCloud() {
		int cloudFlag = SysUtil.getInt(ConstantExDefine.MDB_CLOUD_FLAG, 0);
		return cloudFlag == 0 ? false : true;
	}

	public static boolean isManualSyncMdb() {
		int manualFlag = SysUtil.getInt(ConstantExDefine.MDB_SYNC_MANUAL, 0);
		return manualFlag == 0 ? false : true;
	}
    public static Map<Long, List<String>> deleteMap=new HashMap<Long, List<String>>();
    public static  List<String> deleteList=new ArrayList<String>();

    /**
	 * copy字段值
	 */
	public static void copyFieldValue(DataObject from, DataObject to) {
		java.lang.reflect.Field[] fs = ClassUtil.getFields(from.getClass());

		BeanWrapper bw = BeanWrapper.wrap(from);

		for (java.lang.reflect.Field f : fs) {
			if (!Modifier.isPrivate(f.getModifiers())) {
				continue;
			}
			String fieldName = f.getName();
			Object fieldValue = bw.getPropertyValue(fieldName);
			if (fieldValue != null) {
				try {
					ClassUtil.setFieldValue(to, fieldName, fieldValue);
				} catch (Exception e) {
					imsLogger.error(e,e);
				}
			}
		}
	}
	/**
	 * 判断实体是否包含某个属性
	 * @param clz
	 * @param fieldName
	 * @return
	 * @author lilin
	 * @Date 2016-10-4
	 */
	public static boolean existsField(Class clz,String fieldName){
	    try{
	        return clz.getDeclaredField(fieldName)!=null;
	    }
	    catch(Exception e){
	    }
	    if(clz!=Object.class){
	        return existsField(clz.getSuperclass(),fieldName);
	    }
	    return false;
	}
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			imsLogger.error(e,e);
		}
	}

	/**
	 * 获取操作环境 wukl 2012-8-11
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * lijc3 2012-8-31 往ContextHolder.getRequest里面设置相应的值
	 */
	public static void setValue2ContextHolder(Long groupId, Long acctId, Long userId) {
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_GROUP_ID);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_ACCT);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_USER);
//		RouterCmp baseCmp = context.getCmp(RouterCmp.class);
//		if(baseCmp.isUserRouted(context.getUserId())){
//			ITableUtil.setValue2ContextHolder(null, null, context.getUserId());
//		}
//		if(userId!=null){
//			routerCmp.checkUserRouter(userId);
//		}
          
		if (groupId != null) {
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_GROUP_ID, groupId);
		}
		if (acctId != null) {
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_ACCT, acctId);
		}
		if (userId != null) {
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_USER, userId);
			
		}
	}

	/**
	 * lijc3 2012-8-31 往ContextHolder.getRequest里面设置相应的值
	 */
	public static void setValue2ContextHolder(Long custId, Long acctId, Long userId, Integer regionCode) {
		cleanRequestParamter();
		if (custId != null) {
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_CUST, custId);
		}
		if (acctId != null) {
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_ACCT, acctId);
		}
		if (userId != null) {
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_USER, userId);
		}
		if (regionCode != null) {
			ContextHolder.getRequestContext().put(ConstantExDefine.ROUTER_KEY_REGION_CODE, regionCode);
		}
	}

	/**
	 * 清除ContextHolder里面的账户id和用户id,custId lijc3 2012-8-8
	 */
	public static void cleanRequestParamter() {
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_ACCT);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_USER);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_IDEN);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_REGION_CODE);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTE_KEY_OBJECT_TYPE);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTE_KEY_DB_KEY);
		ContextHolder.getRequestContext().remove(ConstantExDefine.ROUTER_KEY_GROUP_ID);
	}

	/**
	 * 初始化request，或者重置request
	 */
	public static void requestInit() {
		if (ContextHolder.getRequestContext() == null) {
			ContextHolder.requestInit();
		} else {
			ContextHolder.requestClear();
			ContextHolder.requestInit();
		}
	}

	public static void startTs() {
		ImsLogger imsLogger = new ImsLogger(ITableUtil.class);

		try {
			imsLogger.info("***** TS start begin..");
			new TS().tsRun(null);
			imsLogger.info("***** TS start end..");
		} catch (Exception e) {
			imsLogger.error("****** TS start failed..", e.getMessage());
		}
	}

	/**
	 * 序列化
	 */
	public static byte[] serialize(Serializable obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		try {
			oos = new ObjectOutputStream(bout);
			oos.writeObject(obj);
			return bout.toByteArray();
		} catch (IOException e) {
			imsLogger.error(e,e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					imsLogger.error(e,e);
				}
			}
		}
		return null;
	}

	/**
	 * 反序列化
	 */
	public static Serializable serialize(byte[] bytes) {
		ObjectInputStream ois = null;
		try {
			ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bin);
			return (Serializable) ois.readObject();
		} catch (Exception e) {
			imsLogger.error(e,e);
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					imsLogger.error(e,e);
				}
			}
		}

		return null;
	}

	public static void setExpireDate(List<? extends DataObject> list, Date expireDate) {
		if (CommonUtil.isEmpty(list)) {
			return;
		}
		try {
			for (DataObject dm : list) {
				java.lang.reflect.Field f_expireDate = ClassUtil.getField(dm.getClass(),
						ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);

				if (f_expireDate != null) {
					ClassUtil.setFieldValue(dm, f_expireDate, expireDate);
				}
			}
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/**
	 * lijc3 2013-7-16 获取失效时间最晚的记录
	 * 
	 * @param <T>
	 * @param objectList
	 * @return
	 * @throws IMSException
	 */
	public static <T extends DataObject> T getLastDataObjectByExpireDate(List<T> objectList) throws IMSException {
		if (CommonUtil.isEmpty(objectList)) {
			return null;
		}
		T obj = objectList.get(0);
		Date objExpireDate = (Date) ClassUtil.getPropertyValue(obj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);

		for (int i = 1; i < objectList.size(); i++)// 绗簩浣嶅紑濮嬪惊鐜�
		{
			T thisObj = objectList.get(i);
			Date thisExpireDate = (Date) ClassUtil.getPropertyValue(thisObj, ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
			if (thisExpireDate.after(objExpireDate) || thisExpireDate.equals(objExpireDate)) {
				obj = thisObj;
				objExpireDate = thisExpireDate;
			}
		}
		return obj;
	}

	/**
	 * lijc3 2013-7-16 获取生效时间最早的记录
	 * 
	 * @param <T>
	 * @param objectList
	 * @return
	 * @throws IMSException
	 */
	public static <T extends DataObject> T getFirstDataObjectByValidDate(List<T> objectList) throws IMSException {
		if (CommonUtil.isEmpty(objectList)) {
			return null;
		}
		T obj = objectList.get(0);
		Date objValidDate = (Date) ClassUtil.getPropertyValue(obj, ConstantDefine.ENTITY_FIELD_VALID_DATE);

		for (int i = 1; i < objectList.size(); i++)// 绗簩浣嶅紑濮嬪惊鐜�
		{
			T thisObj = objectList.get(i);
			Date thisValidDate = (Date) ClassUtil.getPropertyValue(thisObj, ConstantDefine.ENTITY_FIELD_VALID_DATE);
			if (thisValidDate.before(objValidDate) || thisValidDate.equals(objValidDate)) {
				obj = thisObj;
				objValidDate = thisValidDate;
			}
		}
		return obj;
	}
	
	/**
	 * 南基对主产品编号增加一个前缀
	 * @param prefix
	 * @param productId
	 * @return
	 */
	public static Long convertMainProductId(int prefix,Long productId){
		return Long.valueOf(ConstantExDefine.MAIN_PROD_PREFIX * prefix + productId);
	}

	
	
}
