package com.ailk.imssh.common.manual.main;

import java.util.List;

import jef.common.wrapper.IntRange;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import jef.database.query.QueryBuilder;

import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.DateUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.bean.ITableContext;
import com.ailk.imssh.common.flow.dispatch.dispatch.Dispatch;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.manual.handler.IHandler;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;
import com.ailk.openbilling.persistence.jd.entity.ImsManualModify;

/**
 * @Description:手工修改数据的主函数
 * @author wangjt
 * @Date 2012-9-25
 */
public class ManualModify
{
    private static ImsLogger imsLogger = new ImsLogger(Dispatch.class);

    public static void main(String[] args)
    {
        String partName = null;
        if (CommonUtil.isNotEmpty(args))
        {
            partName = args[0];
        }
        ITableUtil.startTs();
        
        // TS采用异步方式 加载Spring配置，若没有启动完则无法使用Spring容器，故采用线程sleep方式，待完全起来后再执行程序的启动
        while (!OLTPServiceContext.isInitSpringFinished())
        {
            try
            {
                imsLogger.debug("***spring context not start,sleep 5 s");
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
                imsLogger.error(e.getMessage());
            }
        }
        
        CommonInit.commonInitWithTs();

        boolean isSub = false;//程序是否分表
        String tableName = null;
        MyTableName myTableName = null;
        IntRange range = null;
        range = new IntRange(0,1000);
        if (CommonUtil.isNotEmpty(partName))
        {
            tableName = "JD.IMS_MANUAL_MODIFY_" + partName;
            myTableName = new MyTableName(tableName);
            //range = new IntRange(0, 1000);
            isSub = true;
        }
        //2013-09-04 liming15  
        else
        {
        	tableName = "JD.IMS_MANUAL_MODIFY";
            myTableName = new MyTableName(tableName);
        }
        Query<ImsManualModify> query = QueryBuilder.create(ImsManualModify.class);
        query.addOrderBy(true, ImsManualModify.Field.seqId);// 按照seqId从小到大排序读取
        
        IHandler handler = SpringExUtil.getManualHandler();

        // 初始化request
        ITableUtil.requestInit();

        while (true)
        {
            try
            {
                List<ImsManualModify> allList = DBUtil.getDBClient().select(query, range, myTableName);

                if (CommonUtil.isEmpty(allList))
                {
                    imsLogger.debug("******no data found sleep 1 min");
                    ITableUtil.sleep(30000);
                    continue;
                }

                ITableContext context = null;
                for (ImsManualModify imsManualModify : allList)
                {
                    context = new ITableContext();// 重新生成
                    context.setSync(false);// 表示异步
                    if (null == context.getRequestDate())
                    {
                        context.setRequestDate(DateUtil.currentDate());
                    }
                    handler.hander(imsManualModify, context,tableName);
                }
            }
            catch (Exception e)
            {
                imsLogger.error(e, e);
            }
        }
    }
}
