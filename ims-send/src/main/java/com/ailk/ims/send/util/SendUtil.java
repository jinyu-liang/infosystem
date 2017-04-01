package com.ailk.ims.send.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Table;

import jef.database.DataObject;

import org.apache.log4j.Logger;

import com.ailk.common.virtualtime.VirtualDate;
import com.ailk.easyframe.web.common.session.ContextHolder;

/**
 * @Description:包含公共方法
 * @author wangjt
 * @Date 2012-11-1
 */
public class SendUtil
{

    /**
     * 获取当前系统时间
     */
    public static Date currentDate()
    {
        return new VirtualDate();
    }

    /**
     * @param date
     * @return :yyyyMM
     */
    public static String getYYYYMM(Date date)
    {
        return new SimpleDateFormat("yyyyMM").format(date);
    }

    public static void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            Logger.getLogger(SendUtil.class).error(e.getMessage());
        }
    }
    
    public static void requestInit()
    {
        if (ContextHolder.getRequestContext() == null)
        {
            ContextHolder.requestInit();
        }
        else
        {
            ContextHolder.requestClear();
            ContextHolder.requestInit();
        }
    }
    /**
     * 返回一个对象的   域名.表明
     * @param clz
     * @return
     */
    public static String buildTableName(Class<? extends DataObject> clz)
    {
        Table anno = clz.getAnnotation(Table.class);
        if (anno == null)
            return null;
        String schema = anno.schema();
        String tableName = anno.name();
        if (null!=schema)
            tableName = schema + "." + tableName;

        return tableName;
    }
    
}
