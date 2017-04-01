package com.ailk.imssh.common.flow.dispatch.dispatch;

import java.util.Calendar;
import java.util.List;
import jef.common.wrapper.IntRange;
import jef.database.AbstractDbClient;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.dispatch.service.IDispatchService;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;
import com.ailk.openbilling.persistence.itable.entity.IDataIndex;
import com.ailk.openbilling.persistence.sys.entity.SysParameter;

/**
 * @Description: 数据分发基类
 * @author wangjt
 * @Date 2012-9-13
 */
public abstract class Dispatch implements IDispatch
{
    private ImsLogger imsLogger = new ImsLogger(this.getClass());

    /**
     * 由子类控制具体的配置信息
     */
    protected abstract IConfig getConfig();
    
    public void doDbmStartDispatch(){
    	IConfig config = this.getConfig();
 
        MyTableName myTableName = new MyTableName(config.getTableName());

        IntRange range = new IntRange(0, config.getScanOneTimeCount());
        Query<IDataIndex> query = null;

        AbstractDbClient dbClient = DBUtil.getDBClient();
        IDispatchService dispatchService = SpringExUtil.getDispatchService();

        // 初始化request
        ITableUtil.requestInit();
        
        //liming15 2013-05-27 查配置缓存，取MDB dump的时间
        long dumpTime = 0;
    	SysParameter sysParameter = DBUtil.querySingle(SysParameter.class,
    		new DBCondition(SysParameter.Field.paramCode,"MDB_DUMP_TIME"),new DBCondition(SysParameter.Field.paramClass,6));
    	
    	//默认MDB dump时间为4:00
    	if(sysParameter == null || sysParameter.getParamValue() == null)
    	{
    		dumpTime =  14400;		//4 * 60 * 60秒
    	}
    	else
    	{
    		dumpTime = Long.valueOf(sysParameter.getParamValue());
    	}
        
        while (true)
        {
            try
            {
                long t1 = System.currentTimeMillis();
                
                //liming15 2013-05-27 增加时间点判断，避开凌晨四点MDB dump时MDB不可用 
                long sleepTime = 30000;
                if (isMdbDump(dumpTime))
                {
                	imsLogger.debug("MDB is dumping,wait!");
                	ITableUtil.sleep(sleepTime);
                	continue;
                }
                
                imsLogger.debug(" start select and dispatch table:", config.getTableName());// 打印日志
                query = config.newTableQuery();
                // 从大索引表中获取数据
                List list = dbClient.select(query, range, myTableName);
                imsLogger.debug(new Object[]{t1, " end query table:", config.getTableName(), ",size:", (list == null ? 0 : list.size())});

                if (list != null && !list.isEmpty())
                {
                    // 分发大索引表数据到大索引表对应的8个分表
                    dispatchService.dispatchToSubTables(list, dbClient, config);
                    list.clear();
                }
                else
                {
                    // 未扫描到数据 ，停一秒继续扫描
                    imsLogger.debug(" dispatch thread had not selected any results, sleep 1000ms....");
                }
                //强制睡至少10s
                ITableUtil.sleep(config.getSleepTime());
                // 打印日志
                imsLogger.debug(new Object[]{t1, " end select and dispatch table:", myTableName});
            }
            catch (Exception e)
            {
                imsLogger.error(e, e);
            }
        }
    }

    @Override
    public void doDispatch()
    {
        ITableUtil.startTs();
        
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
        
        doDbmStartDispatch();
        
    }
    
    /**
     * 目前每天凌晨4:00 MDB做dump，MDB会持续几分钟处于不可用状态
     * 检测MDB是否可用 
     * liming15 2013-05-27
     * */
    public boolean isMdbDump(long dumpTime)
    {
    	//取当前时分秒,精确到秒
    	Calendar calendar = Calendar.getInstance();
    	long curTime = calendar.get(Calendar.HOUR_OF_DAY) * 3600 + 
    				   calendar.get(Calendar.MINUTE) * 60 + 
    				   calendar.get(Calendar.SECOND);
    	long diffTime = curTime - dumpTime;
    	
    	//diffTime 在[-1,4](min)之间，认为MDB处于不可用
    	if ((diffTime >= -60) && (diffTime <= 240))
    	{
    		return true;
    	}
    	
    	return false;
    }
}
