package com.ailk.ims.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ailk.easyframe.web.common.exception.BusinessException;
import com.ailk.ims.common.DBCondition;
import com.ailk.openbilling.persistence.sys.entity.SysEnumData;

/**
 * @Description 枚举值工具类
 * @author liuyang
 * @Date 2011-10-20
 */
public class IMSEnumDataUtil
{
    // 全局数据

    private static Map<String, Map<String, String>> enumDataMap = new HashMap<String, Map<String, String>>();

    public IMSEnumDataUtil()
    {
        this.init();
    }

    public void init()
    {

        List<SysEnumData> list = DBUtil.queryAll(SysEnumData.class);
        // 重新组织
        for (SysEnumData ent : list)
        {
            if(ent.getDomainFlag().equalsIgnoreCase("ad") || ent.getDomainFlag().equalsIgnoreCase("cd")){
                if (enumDataMap.containsKey(ent.getColumnName()))
                    enumDataMap.get(ent.getColumnName()).put(ent.getCodeValue(), ent.getDispName());
                else
                {
                    HashMap<String, String> newtype = new HashMap<String, String>();
                    newtype.put(ent.getCodeValue(), ent.getDispName());
                    enumDataMap.put(ent.getColumnName(), newtype);
                }
            }
           
        }
    }

    /**
     * 根据列名可以查到值对列表 liuyang 2011-10-20
     * 
     * @param colName
     * @param tableName
     * @return
     */
    public static Map getEnumMapByColName(String colName,String tableName)
    {
        if (!enumDataMap.containsKey(colName))
        {
//            SysEnumData enumData = new SysEnumData();
        	List<DBCondition> cond  = new ArrayList<DBCondition>();
        	cond.add(new DBCondition(SysEnumData.Field.columnName,colName));
//            enumData.setColumnName(colName);
            if(tableName != null){
//                enumData.setTableName(tableName);
                cond.add(new DBCondition(SysEnumData.Field.tableName,tableName));
            }
            List<SysEnumData> list = DBUtil.query(SysEnumData.class,cond.toArray(new DBCondition[cond.size()]));
            if (list.size() == 0)
            {
                throw new BusinessException("table:SYS_ENUM_DATA not exists column name:" + colName);
            }
            for (SysEnumData ent : list)
            {
                if (enumDataMap.containsKey(ent.getColumnName()))
                    enumDataMap.get(ent.getColumnName()).put(ent.getCodeValue(), ent.getDispName());
                else
                {
                    Map<String, String> newtype = new HashMap<String, String>();
                    newtype.put(ent.getCodeValue(), ent.getDispName());
                    enumDataMap.put(ent.getColumnName(), newtype);
                }
            }
        }
        return enumDataMap.get(colName);
    }

    /**
     * 根据列名和ID可以获得ID对应的文本 liuyang 2011-10-20
     * 
     * @param colName
     * @param codeValue
     * @param tableName
     * @return
     */
    public static String getEnumDispName(String colName, String codeValue,String tableName)
    {

        if (!enumDataMap.containsKey(colName))
        {
            // 尝试从数据库中获取
//          SysEnumData enumData = new SysEnumData();
        	List<DBCondition> cond  = new ArrayList<DBCondition>();
        	cond.add(new DBCondition(SysEnumData.Field.columnName,colName));
//            enumData.setColumnName(colName);
            if(tableName != null){
//                enumData.setTableName(tableName);
                cond.add(new DBCondition(SysEnumData.Field.tableName,tableName));
            }
            List<SysEnumData> list = DBUtil.query(SysEnumData.class,cond.toArray(new DBCondition[cond.size()]));
            if (list.size() == 0)
                throw new BusinessException("table:SYS_ENUM_DATA not exists column name:" + colName);

            for (SysEnumData ent : list)
            {
                if (enumDataMap.containsKey(ent.getColumnName()))
                    enumDataMap.get(ent.getColumnName()).put(ent.getCodeValue(), ent.getDispName());
                else
                {
                    Map<String, String> newtype = new HashMap<String, String>();
                    newtype.put(ent.getCodeValue(), ent.getDispName());
                    enumDataMap.put(ent.getColumnName(), newtype);
                }
            }
        }
        if (!enumDataMap.get(colName).containsKey(codeValue))
        {
            // 尝试从数据库中获取
//            SysEnumData conditiom = new SysEnumData();
//            conditiom.setColumnName(colName);
//            conditiom.setCodeValue(codeValue);
            /*conditiom.getQuery().addCondition(SysEnumData.Field.columnName, colName);
            conditiom.getQuery().addCondition(SysEnumData.Field.codeValue, codeValue);*/
            List<SysEnumData> lst = DBUtil.query(SysEnumData.class,new DBCondition(SysEnumData.Field.columnName,colName),
            		new DBCondition(SysEnumData.Field.codeValue,codeValue));
            if (lst.size() == 0)
                throw new BusinessException("table:SYS_ENUM_DATA not exists code value:" + codeValue);

            SysEnumData ent = lst.get(0);
            enumDataMap.get(ent.getColumnName()).put(ent.getCodeValue(), ent.getDispName());

            return ent.getDispName();
        }
        return enumDataMap.get(colName).get(codeValue);
    }

    /**
     * 根据描述查询枚举值 liuyang 2011-10-20
     * 
     * @param tableName
     * @param dispName
     * @param colName
     * @return
     */
    public static String getCodeValue(String dispName,String colName,String tableName)
    {
//        SysEnumData enumData = new SysEnumData();
        List<DBCondition> cond  = new ArrayList<DBCondition>();
        
//        enumData.setDispName(dispName);
//        enumData.setColumnName(colName);
        cond.add(new DBCondition(SysEnumData.Field.dispName,dispName));
        cond.add(new DBCondition(SysEnumData.Field.columnName,colName));
        if(tableName != null){
//            enumData.setTableName(tableName);
            cond.add(new DBCondition(SysEnumData.Field.tableName,tableName));
        }
        List<SysEnumData> list = DBUtil.query(SysEnumData.class,cond.toArray(new DBCondition[cond.size()]));
        if (list.size() == 0)
            throw new BusinessException("table:SYS_ENUM_DATA not exists disName :" + dispName+"colName :"+colName);
        SysEnumData ent = list.get(0);

        return ent.getCodeValue();

    }

}
