package com.ailk.imssh.common.flow.dispatch.dispatch;

import java.util.List;
import jef.common.wrapper.IntRange;
import jef.database.AbstractDbClient;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.flow.config.ISendToMemberConfig;
import com.ailk.imssh.common.flow.dispatch.service.ISendToMemberService;
import com.ailk.imssh.common.init.CommonInit;
import com.ailk.imssh.common.util.ITableUtil;
import com.ailk.imssh.common.util.SpringExUtil;
import com.ailk.openbilling.persistence.cust.entity.CaNotifyTaskSplit11;

/**
 * @Description: 给所有群成员发送剩余流量提醒短信基类
 * @author liming15
 * @Date 2013-5-31
 */
public abstract class SendSmsToMember 
{
    private ImsLogger imsLogger = new ImsLogger(this.getClass());
    
    /**
     * 由子类控制具体的配置信息
     */
    protected abstract ISendToMemberConfig getConfig();

    public void doSendSmsToMember()
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
        
        ISendToMemberConfig config = this.getConfig();

        MyTableName myTableName = new MyTableName(config.getTableName());

        IntRange range = new IntRange(0,50);
        Query<CaNotifyTaskSplit11> query = config.newTableQuery();

        AbstractDbClient dbClient = DBUtil.getDBClient();
        ISendToMemberService sendToMemberService = SpringExUtil.getSendToMemberService();
        

        // 初始化request
        ITableUtil.requestInit();
        
        while (true)
        {
            try
            {
                long t1 = System.currentTimeMillis();
                
                imsLogger.debug(" start select and make SMS for member:", config.getTableName());// 打印日志
                // 从信控通知表中获取群ID
                List list = dbClient.select(query, range, myTableName);
                imsLogger.debug(new Object[]{t1, " end query table:", config.getTableName(), ",size:", (list == null ? 0 : list.size())});

                if (list != null && !list.isEmpty())
                {
                	//为每个成员组成一条提醒短信
                	sendToMemberService.makeSmsToTiOSmsImme(list, config);
                	list.clear();
                }
                else
                {
                    // 未扫描到数据 ，停一秒继续扫描
                    imsLogger.debug(" thread had not selected any results, sleep 1000ms....");
                    ITableUtil.sleep(1000);
                }
                // 打印日志
                imsLogger.debug(new Object[]{t1, " end select and make SMS for member:", myTableName});
                ITableUtil.sleep(30000);
            }
            catch (Exception e)
            {
                imsLogger.error(e, e);
            }
        }
    }
    
}
