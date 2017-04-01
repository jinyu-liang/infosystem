package com.ailk.imssh.common.flow.dispatch.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jef.common.wrapper.IntRange;
import jef.database.AbstractDbClient;
import jef.database.Batch;
import jef.database.DataObject;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;

import com.ailk.easyframe.web.common.dal.CommonDao;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexSub;

/**
 * @Description:分发数据的服务
 * @author wangjt
 */
public class DispatchService implements IDispatchService
{
    private ImsLogger imsLogger = new ImsLogger(DispatchService.class);

    /**
     * @param dataObjectList:大索引表的数据List
     */
    
    @Override
    public void dispatchToSubTables(List<DataObject> dataObjectList, AbstractDbClient dbClient, IConfig config) throws Exception
    {   
    	

        long t1 = System.currentTimeMillis();
        imsLogger.debug("****************start to dispatch to sub table ");
        AbstractDbClient client=DBUtil.getDBClient();
        // 保存小索引表分表的对象List
        List<List<DataObject>> subTableObjListList = new ArrayList<List<DataObject>>(config.getSubTableCount());
        // 一开始就初始化出来，提高效率
        for (int i = 0; i < config.getSubTableCount(); i++)
        {
            subTableObjListList.add(new ArrayList<DataObject>());
        }

        // 保存大索引表的删除对象
        List<DataObject> deleteTableObjList = new ArrayList<DataObject>();

        Map<Integer, List<String>> dispatchMap=new HashMap<Integer, List<String>>();
        Map<String, List<Long>> dispatchCustMap=new HashMap<String, List<Long>>();

        for (int i = 0; i < config.getSubTableCount(); i++)
        {
        	dispatchMap.put(i, new ArrayList<String>());
        }

        Map<Integer, List<String>> subMap=new HashMap<Integer, List<String>>();
        Map<Integer, List<DataObject>> subObjectMap=new HashMap<Integer, List<DataObject>>();
        Map<String, List<Long>> subCustMap=new HashMap<String, List<Long>>();

        //List<DataObject> totalSubList=new ArrayList<DataObject>();
       /// dispatchMap.clear();
        String packageName="com.ailk.openbilling.persistence.itable.entity.";
        String name="";
        if(config.getTableName().equals("JD.I_BATCH_DATA_INDEX")){
        	 name="IBatchDataIndexSub";
        }else{
        	name="IDataIndexSub";
        }
        int count=0;
        int sub=0;
        for (int mod = 0; mod < subTableObjListList.size(); mod++){
        	sub=mod+1;

            String subTableName = name + sub;
            String className=packageName+subTableName;
            DataObject dataObject= (DataObject) ClassUtil.instance(className);
            List<DataObject> list=(List<DataObject>) client.selectAll(dataObject.getClass());
            if(list!=null){
                if(list.size()>30000){
                    mod=-1;
                    count=0;
                    subObjectMap.clear();
                    //totalSubList.clear();
                    imsLogger.debug("子工单数据超过30000,休眠3秒");

                    ITableUtil.sleep(3000);
                }else{
                	count++;
                	//totalSubList.addAll(list);
                	subObjectMap.put(mod, list);
                	if(count==subTableObjListList.size()){
                		subMap=fixSubMap(subObjectMap, config,subCustMap);
                	}
                }
            }
            
        }
        subObjectMap.clear();
        List<DataObject> filterList=new ArrayList<DataObject>();
        for(DataObject dataObject:dataObjectList){
//        	String custId=config.getValueOfCustId(dataObject);
//        	String userId=config.getValueOfUserId(dataObject);
//        	String acctId=config.getValueOfAcctId(dataObject);
        	String key=config.getValueOfKey(dataObject, 1);
        	String[]keys=key.split(",");
            Iterator<Entry<Integer, List<String>>> it=subMap.entrySet().iterator();
            int mod=0;
            boolean flag=false;
            OK:
            while(it.hasNext()){
            	Entry<Integer, List<String>> entry=it.next();
            	mod=entry.getKey();
            	List<String> keyList=entry.getValue();
                for(String str:keys){
                	 if(keyList.contains(str)){
                		 flag=true;
                		 break OK;
                	 }
                }
            }
         if(flag){
             List<DataObject> subObjectList=subObjectMap.get(mod);
       		 if(subObjectList==null){
       			 subObjectList=new ArrayList<DataObject>();
       			 subObjectMap.put(mod, subObjectList);
       		 }
       		 subObjectList.add(dataObject); 
         }else{
        	 filterList.add(dataObject);
         }

        }
        for (DataObject dataObject : filterList)
        {
            DataObject deleteTableObj = config.getDeleteDataObject(dataObject);// 大索引表对象对应的大索引表删除对象
            deleteTableObjList.add(deleteTableObj);
            String key=config.getValueOfKey(dataObject, 1);
            String[]ids=key.split(",");
        	Long custId=Long.valueOf(config.getValueOfCustId(dataObject));
        	Long orgCustId=Long.valueOf(config.getValueOfCustId(dataObject));
        	int mod=0;
        	boolean b=false;
            Iterator<Entry<Integer, List<String>>> it=dispatchMap.entrySet().iterator();
                OK:
                while(it.hasNext()){
                	Entry<Integer, List<String>> entry=it.next();
                	List<String> keyList=entry.getValue();
                	for(String id:ids){
                		if("".equals(id)){
                			continue;
                		}
                		if(keyList.contains(id)){
                		 mod=entry.getKey();

                		 custId=dispatchCustMap.get(id).get(0);
                		 b=true;
                   		 break OK;
                   	 }
                	}
                }
            
            Iterator<Entry<Integer, List<String>>> iterator=dispatchMap.entrySet().iterator();
          if(!b){
            while(iterator.hasNext()){

            	Entry<Integer, List<String>> entry=iterator.next();
            	List<String> ll=entry.getValue();
            	int k=entry.getKey();
            	int number=0;
                Iterator<Entry<Integer, List<String>>> iteratorAllot=dispatchMap.entrySet().iterator();

            	while(iteratorAllot.hasNext()){
            		Entry<Integer, List<String>> entryTemp=iteratorAllot.next();
                	List<String> llTemp=entryTemp.getValue();
                	if(ll.size()<=llTemp.size()){
                		number++;
                	}
            	}

            	if(number==dispatchMap.size()){
            		mod=k;
            		break;
            	}
            }
       }
        	List<String> keyList=dispatchMap.get(mod);
            if(keyList==null){
            	keyList=new ArrayList<String>();
            	dispatchMap.put(mod, keyList);
            }

            for(String id:ids){
            	if("".equals(id)){
            		continue;
            	}else{
            		List<Long> custList=dispatchCustMap.get(id);
            		if(custList==null){
            			custList=new ArrayList<Long>();
            			dispatchCustMap.put(id, custList);
            		}
            		keyList.add(id);
            		custList.add(custId);
            	}
            }

            // 根据索引表数据中的cust_id、acct_id判断
           //Long busiValue = config.getValueOfBusiField(dataObject);
  
            // 把同一个业务主体下的数据，分到8个分表中的一个分表中
            
           // int	 mod = (int) (busiValue % config.getSubTableCount());// 尾号

            // 大索引表对象，转换为小索引表对象
            DataObject dataObjectSub = config.convertToSubDataObjectWithOrgCust(dataObject, custId);

            // 增加到小索引表List分组中
            subTableObjListList.get(mod).add(dataObjectSub);
         }
        Iterator<Entry<Integer, List<DataObject>>> iterator =subObjectMap.entrySet().iterator();
        while(iterator.hasNext()){
        	Entry<Integer, List<DataObject>> entry=iterator.next();
        	int mod=entry.getKey();
        	List<DataObject> list=entry.getValue();
        	if(list.size()>0){
        		for(DataObject dataObject:list){
                    DataObject deleteTableObj = config.getDeleteDataObject(dataObject);// 大索引表对象对应的大索引表删除对象
                    deleteTableObjList.add(deleteTableObj);
        			String key=config.getValueOfKey(dataObject, 1);
        			String[]keys=key.split(",");
        			Long custId=null;
        			for(String str:keys){
        				if("".equals(str)){
        					continue;
        				}
        				if(subCustMap.containsKey(str)){
                			 custId=subCustMap.get(str).get(0);
                			 break;
        				}
        				
        			}
        	           DataObject dataObjectSub = config.convertToSubDataObjectWithOrgCust(dataObject, custId);

        	            // 增加到小索引表List分组中
        	            subTableObjListList.get(mod).add(dataObjectSub);
        		}
        	}
        }
       // }
        // 删除大索引表中的记录
        Batch<DataObject> tableBatch = client.startBatchDelete(deleteTableObjList.get(0));
        tableBatch.add(deleteTableObjList);
        tableBatch.commit();
        deleteTableObjList.clear();

        Batch<DataObject> subTableBatch = null;
        for (int mod = 0; mod < subTableObjListList.size(); mod++)
        {
            List<DataObject> subTableObjList = subTableObjListList.get(mod);
            if (subTableObjList.isEmpty())
            {
                continue;
            }

            // 增加数据到对应小索引分表["CD.I_DATA_INDEX_SUB_{0-7}"]
            String subTableName = config.getSubTableNamePrefix() + mod;
            imsLogger.debug(" dispatch to sub_table: ", subTableName);
            subTableBatch = client.startBatchInsert(subTableObjList.get(0), subTableName);
            subTableBatch.add(subTableObjList);
            subTableBatch.commit();
            subTableObjList.clear();
        }
        subTableObjListList.clear();
        imsLogger.debug(new Object[]{t1, "****************end to dispatch to sub table"});
    }
    public Map<Integer, List<String>>fixSubMap(Map<Integer, List<DataObject>> map,IConfig config,Map<String, List<Long>> subCustMap){
        Map<Integer, List<String>> subMap=new HashMap<Integer, List<String>>();
        Iterator<Entry<Integer, List<DataObject>>> it=map.entrySet().iterator();
        while(it.hasNext()){
        	Entry<Integer, List<DataObject>> entry=it.next();
        	int mod=entry.getKey();
        	List<DataObject> list=entry.getValue();
        	for(DataObject dataObject:list){
        		List<String> keyList=subMap.get(mod);
        		if(keyList==null){
        			keyList=new ArrayList<String>();
        			subMap.put(mod, keyList);
        		}
        		String key=config.getValueOfKey(dataObject,2);
        		Long custId=(Long) ClassUtil.getFieldValue(dataObject, IDataIndexSub.Field.custId.toString());
        		String[] ids=key.split(",");
        		for(String id:ids){
        			if("".equals(id)){
        				continue;
        			}else{
                		keyList.add(id);
                		List<Long> custList=subCustMap.get(id);
                		if(custList==null){
                			custList=new ArrayList<Long>();
                			subCustMap.put(id, custList);
                		}
                		custList.add(custId);
        			}
        		}
        	}
        }

    	return subMap;
    }

}
