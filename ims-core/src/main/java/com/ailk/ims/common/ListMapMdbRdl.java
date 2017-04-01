package com.ailk.ims.common;

import com.ailk.easyframe.sdl.sdlbuffer.CsdlArrayList;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlHashMap;
import com.ailk.easyframe.sdl.sdlbuffer.CsdlStructObject;
/**
 * @Description: 继承 MdbRdl,适用于请求参数对象是一个MapList的业务
  * @Company: Asiainfo-Linkage Technologies(China),Inc.  Hangzhou                                                                                                                                                                                                                             
  * @Author wuyj                                                                                                                                                                                                                                                                           
  * @Date 2012-6-13
 */
public class ListMapMdbRdl extends MdbRdl {
	public ListMapMdbRdl(String kv,Class<?> respClass) {
		super(kv,respClass);
	}
	
	/**
	 * 增加一条请求数据
	 * @param <T>
	 * @param key,对象的键值，比如SCustomer是cust_id,SAccount是acct_id，同一个主键的对象可能会上发多条，比如数据库中被update过的分段数据
	 * @param record,对象
	 */
	public <T>void addRecord(T key,CsdlStructObject record){
		CsdlHashMap<T,CsdlArrayList<CsdlStructObject>> map = (CsdlHashMap<T,CsdlArrayList<CsdlStructObject>>)reqObj;
		if(map == null){
			reqObj = new CsdlHashMap<T,CsdlArrayList<CsdlStructObject>>(key.getClass(),CsdlArrayList.class);
			map = (CsdlHashMap<T,CsdlArrayList<CsdlStructObject>>)reqObj;
		}
		CsdlArrayList<CsdlStructObject> list = map.get(key);
		if(list == null){
			list = new CsdlArrayList<CsdlStructObject>(record.getClass());
			map.put(key, list);
		}
		list.add(record);
	}
	
}
