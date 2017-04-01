package com.ailk.ims.send.util;

import java.sql.SQLException;
import java.util.List;

import jef.database.Batch;
import jef.database.DataObject;

import org.apache.log4j.Logger;

import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthSmsDetail;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthSmsSend;
import com.ailk.openbilling.persistence.jd.entity.ImsNoAuthTiImme;

public class CommonDbUtil
{
    protected static Logger logger = Logger.getLogger(CommonDbUtil.class);
    
    public static   List<DataObject> query(DataObject cond){
        try
        {
            return SendSpringUtil.getCommonDao().getClient().select(cond);
        }
        catch (SQLException e)
        {
           logger.error(e);
        }  
        return null;
    }
    public static void batchDelete(Class<? extends DataObject> claz,List<DataObject> list){
       
      
        try
        {
            Batch imsBatch = SendSpringUtil.getCommonDao().getClient().startBatchDelete(claz);
            imsBatch.add(list);
            imsBatch.commit();
        }
        catch (SQLException e)
        {
           logger.error(e);
            
        }
 
    }
    
    public static void batchInsertImsNoAuthTiImme(ImsNoAuthTiImme imsNoAuthTiImme,List<ImsNoAuthTiImme> list,String tableName){
        try
        {
            Batch imsHisBatch = SendSpringUtil.getCommonDao().getClient().startBatchInsert(imsNoAuthTiImme,tableName);
            imsHisBatch.add(list);
            imsHisBatch.commit();
            
            
        }
        catch (SQLException e)
        {
            logger.error(e);
            
        }
        
    }
    /**
     *  批量插入未认证审核明细表
     * @param imsNoAuthSmsDetail
     * @param list
     * @param tableName
     */
    public static void batchInsertImsNoAuthSmsDetail(ImsNoAuthSmsDetail imsNoAuthSmsDetail,List<ImsNoAuthSmsDetail> list,String tableName){
        try
        {
            Batch imsHisBatch =  SendSpringUtil.getCommonDao().getClient().startBatchInsert(imsNoAuthSmsDetail,tableName);
            imsHisBatch.add(list);
            imsHisBatch.commit();
        }
        catch (SQLException e)
        {
            logger.error(e);
            
        }
        
    }
    /**
     * 批量插入未认证审核总表
     * @param imsNoAuthSmsSend
     * @param list
     * @param tableName
     */
    public static void batchInsertImsNoAuthSmsSend(ImsNoAuthSmsSend imsNoAuthSmsSend,List<ImsNoAuthSmsSend> list,String tableName){
        try
        {
            Batch imsHisBatch = SendSpringUtil.getCommonDao().getClient().startBatchInsert(imsNoAuthSmsSend,tableName);
            imsHisBatch.add(list);
            imsHisBatch.commit();
        }
        catch (SQLException e)
        {
            logger.error(e);
            
        }

    }

    

}
