package com.ailk.ims.complex;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ailk.openbilling.persistence.imsinner.entity.BatchAccountInfo;
import com.ailk.openbilling.persistence.imsinner.entity.BatchCustomerInfo;
import com.ailk.openbilling.persistence.imsinner.entity.BatchUserInfo;

/**
 *@author taoyf
 *@date 2012-3-6
 *@time 下午02:35:19
 */
public class BatchHelper {
	public static final Map<Long, Map<String, Object>> batchThreadMap = new HashMap<Long, Map<String, Object>>();
	public static final Map<Long, BatchUserInfo> infoMap = new ConcurrentHashMap<Long, BatchUserInfo>();
	public static final Map<Long, BatchAccountInfo> acctInfoMap = new ConcurrentHashMap<Long, BatchAccountInfo>();
	public static final Map<Long, BatchCustomerInfo> custInfoMap = new ConcurrentHashMap<Long, BatchCustomerInfo>();
	
	public static final String TOTALITY = "totality";
	public static final String COMPLETE = "complete";
	public static final String FAIL = "fail";
	public static final String FLAG = "flag";
	
	public static final int MAX_THREAD = 10;
	
	public static boolean isExist(BatchUserInfo info){
		for(Long id : infoMap.keySet()){
			if(infoMap.get(id).equals(info)){
				return true;
			}
		}
		return false;
	}
	public static boolean isExist(BatchAccountInfo info){
		for(Long id:acctInfoMap.keySet()){
			if(acctInfoMap.get(id).equals(info)){
				return true;
			}
		}
		return false;
	}
	public static boolean isExist(BatchCustomerInfo info){
		for(Long id : custInfoMap.keySet()){
			if(custInfoMap.get(id).equals(info)){
				return true;
			}
		}
		return false;
	}
}	
