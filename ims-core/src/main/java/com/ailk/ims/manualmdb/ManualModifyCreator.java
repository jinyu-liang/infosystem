package com.ailk.ims.manualmdb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jef.database.DataObject;

import com.ailk.ims.common.IMSContext;
import com.ailk.ims.exception.IMS3hNotFoundException;
import com.ailk.ims.ims3h.IMS3hBean;
import com.ailk.ims.ims3h.IMS3hTree;
import com.ailk.ims.init.UpfieldInitBean;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;

public class ManualModifyCreator
{
    private static ImsLogger logger = new ImsLogger(ManualModifyCreator.class);
    
    //key:"custId_acctId_userId_prodId" value:upField
    private Map<String,String> map = new HashMap<String, String>();
    private static final String SPLIT = "_";
    
    private static ImsManualModify getNew(Long custId,Long acctId,Long userId,String upField,Long prodId)
    {
        ImsManualModify modify = new ImsManualModify();
        modify.setCustId(custId);
        modify.setAcctId(acctId);
        modify.setUserId(userId);
        modify.setUpField(upField);
        modify.setSoDate(DateUtil.currentDate());
        modify.setProdId(prodId);
        modify.setSeqId(DBUtil.getSequence(ImsManualModify.class));
        return modify;
    }
    
    //TODO
    private static String getRouteKeyBy3hId(IMS3hTree tree,DataObject obj,Upfield3hId upfield3hId){
        logger.dump("____upfield3hId", upfield3hId);
        Long custId = null;
        Long acctId = null;
        Long userId = null;
        
        if(upfield3hId.getUserIdFieldName()!=null){
            Object fValue = ClassUtil.getPropertyValue(obj, upfield3hId.getUserIdFieldName());
            userId = (Long)fValue;
        }
        if(upfield3hId.getAcctIdFieldName() !=null){
            Object fValue = ClassUtil.getPropertyValue(obj, upfield3hId.getAcctIdFieldName());
            acctId = (Long)fValue;
        }
        if(upfield3hId.getCustIdFieldName()!=null){
            Object fValue = ClassUtil.getPropertyValue(obj, upfield3hId.getCustIdFieldName());
            custId = (Long)fValue;
        }
        
        if(userId != null){
            try{
                return getKeyAsString(tree.loadUser3hBean(userId));
            }catch (IMS3hNotFoundException e) {
                return getKeyAsString(null, null, userId);
            }
        }else if(acctId != null){
            try{
                return getKeyAsString(tree.loadAcct3hBean(acctId));
            }catch (IMS3hNotFoundException e) {
                return getKeyAsString(null, acctId, null);
            }
        }else if(custId != null){
            try{
                return getKeyAsString(tree.loadCust3hBean(custId));
            }catch (IMS3hNotFoundException e) {
                return getKeyAsString(custId, null, null);
            }
        }
        
        return null;
    }
    
    private static String getRouteKey(IMS3hTree tree,DataObject obj,UpfieldObjIdType upfieldObj){
        Object fValue = ClassUtil.getPropertyValue(obj, upfieldObj.getObjIdFieldName());
        logger.debug("____[field:"+upfieldObj.getObjIdFieldName()+"][value:"+fValue+"]");
        String type = upfieldObj.getObjType();
        if(type == null){
            Object typeValue = ClassUtil.getPropertyValue(obj, upfieldObj.getObjTypeFieldName());
            type = upfieldObj.getObjTypeMap().get((Integer)typeValue);
        }
        String prodIdField = upfieldObj.getProdIdFieldName();
        Long productId = null;
        if(prodIdField != null){
            productId = (Long)ClassUtil.getPropertyValue(obj, prodIdField);
        }
        if(type.equals(UpfieldObjIdType.OBJ_TYPE_USER)){
            try{
                return getKeyAsString(tree.loadUser3hBean((Long)fValue),productId);
            }catch (IMS3hNotFoundException e) {
                return getKeyAsString(null, null, (Long)fValue,productId);
            }
        }else if(type.equals(UpfieldObjIdType.OBJ_TYPE_ACCT)){
            try{
                return getKeyAsString(tree.loadAcct3hBean((Long)fValue),productId);
            }catch (IMS3hNotFoundException e) {
                return getKeyAsString(null,(Long)fValue, null,productId);
            }
        }else if(type.equals(UpfieldObjIdType.OBJ_TYPE_CUST)){
            try{
                return getKeyAsString(tree.loadCust3hBean((Long)fValue),productId);
            }catch (IMS3hNotFoundException e) {
                return getKeyAsString((Long)fValue ,null, null,productId);
            }
        }
        
        return null;
    }
    /**
     * 把上发的数据按路由主体解析为upField
     * luojb 2013-8-9
     * @param context
     * @param objList
     */
    public void parseCache(final IMSContext context){
        Collection<List<DataObject>> objListColl = context.getCachedData().values();
        if(CommonUtil.isEmpty(objListColl)){
            return;
        }
        for(List<DataObject> objList: objListColl){
            if(CommonUtil.isEmpty(objList)){
                continue;
            }
            for(DataObject obj:objList){
                if(obj == null){
                    continue;
                }
                if(!getUpFieldClass().contains(obj.getClass())){
                    logger.debug("______no need sync mdb DataObject:"+obj.getClass());
                    continue;
                }
                
                logger.dump("_____dataobject:",obj);
                UpfieldTableMainObject upfieldObject = UpfieldInitBean.getUpFieldObject(obj.getClass());
                String key = null;
                if(upfieldObject.is3hId()){
                    key = getRouteKeyBy3hId(context.get3hTree(), obj, (Upfield3hId)upfieldObject);
                }else{
                    key = getRouteKey(context.get3hTree(),obj, (UpfieldObjIdType)upfieldObject);
                }
                if(key == null){
                    continue;
                }

                String upField = map.get(key);
                if(upField == null){
                    upField = getNewUpField(getUpFieldClass().size());
                }
                
                int index = getUpFieldClass().indexOf(obj.getClass());
                
                map.put(key, upField(upField,index));
            }
        }
    }
    
    private List<Class<? extends DataObject>> getUpFieldClass(){
        return UpfieldInitBean.getUpFieldClasses();
    }
    
    
    /**
     * 生成upField字段
     * luojb 2013-8-12
     * @param size 位数
     * @return
     */
    private static String getNewUpField(int size){
        StringBuffer s = new StringBuffer();
        for(int i=0;i<size;i++){
            s.append("0");
        }
        return s.toString();
    }
    
    /**
     * 把upField的某位置为1
     * luojb 2013-8-9
     * @param upField
     * @param index
     * @return
     */
    private static String upField(String upField,int index)
    {
        if(index == 0){
            return "1"+upField.substring(1);
        }
        if(upField.length()-1 < index){
            return upField;
        }
        return upField.substring(0, index)+"1"+upField.substring(index+1);
    }
    /**
     * 缓存数据全部分析完成以后，创建ImsManualModify列表
     * luojb 2013-8-9
     * @return
     */
    public List<ImsManualModify> getImsManualModifyList()
    {
        if(map.isEmpty())
        {
            return null;
        }
        List<ImsManualModify> modifyList = new ArrayList<ImsManualModify>();
        for(String key:map.keySet()){
            String[] ids = key.split(SPLIT);
            Long custId = ids[0].equals("null")?null:CommonUtil.string2Long(ids[0]);
            Long acctId = ids[1].equals("null")?null:CommonUtil.string2Long(ids[1]);
            Long userId = ids[2].equals("null")?null:CommonUtil.string2Long(ids[2]);
            Long prodId = ids[3].equals("null")?null:CommonUtil.string2Long(ids[3]);
            ImsManualModify modify = getNew(custId, acctId, userId, map.get(key), prodId);
            modifyList.add(modify);
        }
        return modifyList;
    }
    /**
     * 从ims3hbean生成key
     * luojb 2013-8-9
     * @param bean
     * @return
     */
    private static String getKeyAsString(IMS3hBean bean){
        return getKeyAsString(bean.getCustId(),bean.getAcctId(),bean.getUserId());
    }
    
    private static String getKeyAsString(IMS3hBean bean,Long prodId){
        return getKeyAsString(bean.getCustId(),bean.getAcctId(),bean.getUserId(),prodId);
    }
    
    private static String getKeyAsString(Long custId,Long acctId,Long userId){
        return getKeyAsString(custId, acctId, userId, null);
        
    }
    
    private static String getKeyAsString(Long custId,Long acctId,Long userId,Long prodId){
        return custId + SPLIT + acctId + SPLIT + userId + SPLIT +prodId;
        
    }
    
    

}
