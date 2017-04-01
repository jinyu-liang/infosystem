package com.ailk.ims.manualmdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jef.database.DataObject;
/**
 * upfield对每个表里的三户对象判断方式
 * @Description
 * @author luojb
 * @Date 2013-8-12
 */
public class UpfieldObjIdType extends UpfieldTableMainObject
{
    private String objIdFieldName;
    private String objTypeFieldName;
    private String prodIdFieldName;
    private String objType;//有的表只会是某一种三户，比如客户表，此时objTypeFieldName是空，objType为cust
    private Map<Integer, String> objTypeMap; // key:value of objType field  value:object type(cust,acct,user)
    
    public UpfieldObjIdType(Class<? extends DataObject> clazz)
    {
        super(clazz);
    }
    
    public String getObjIdFieldName()
    {
        return objIdFieldName;
    }
    public void setObjIdFieldName(String objIdFieldName)
    {
        this.objIdFieldName = objIdFieldName;
    }
    public String getObjTypeFieldName()
    {
        return objTypeFieldName;
    }
    public void setObjTypeFieldName(String objTypeFieldName)
    {
        this.objTypeFieldName = objTypeFieldName;
    }
    public String getObjType()
    {
        return objType;
    }
    public void setObjType(String objType)
    {
        this.objType = objType;
    }
    public Map<Integer, String> getObjTypeMap()
    {
        return objTypeMap;
    }
    public void setObjTypeMap(Map<Integer, String> objTypeMap)
    {
        this.objTypeMap = objTypeMap;
    }
    public void addObjTypeValue(Integer key,String value){
        if(objTypeMap==null){
            objTypeMap = new HashMap<Integer, String>();
        }
        objTypeMap.put(key, value);
    }
    public boolean keyExist(Integer key){
        if(objTypeMap==null){
            return false;
        }
        return objTypeMap.containsKey(key);
    }
    public String getField(){
        return objIdFieldName;
    }
    
    public String toString(){
        String id = "[id_field:"+objIdFieldName+"] ";
        StringBuffer type = new StringBuffer();
        if(objType!=null){
            type.append("[type:");
            type.append(objType);
            type.append("]");
        }else{
            
            type.append("[type_field:");
            type.append(objTypeFieldName);
            type.append("]\r");
            for(Integer key:objTypeMap.keySet()){
                type.append("[objTypeMap ");
                type.append(key);
                type.append(":");
                type.append(objTypeMap.get(key));
                type.append("]\r");
            }
        }
        return  id+type;
        
    }
    @Override
    public List<String> getFields()
    {
        List<String> list = new ArrayList<String>();
        list.add(objIdFieldName);
        return list;
    }

    @Override
    public String getUserIdFieldName()
    {
        return this.objIdFieldName;
    }

    @Override
    public String getAcctIdFieldName()
    {
        return this.objIdFieldName;
    }

    @Override
    public String getCustIdFieldName()
    {
        return this.objIdFieldName;
    }

    public String getProdIdFieldName()
    {
        return prodIdFieldName;
    }

    public void setProdIdFieldName(String prodIdFieldName)
    {
        this.prodIdFieldName = prodIdFieldName;
    }
    
}
