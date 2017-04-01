package com.ailk.ims.send.thread;

import java.sql.SQLException;
import jef.database.QB;
import org.apache.log4j.Logger;
import com.ailk.ims.send.config.SendConfig;
import com.ailk.ims.send.service.IImmeSmsSendService;
import com.ailk.ims.send.service.ImmeSmsSendService;
import com.ailk.ims.send.util.SendSpringUtil;
import com.ailk.ims.send.util.SendUtil;
import com.ailk.openbilling.persistence.jd.entity.TiOSmsImme;


public class ImmeSmsThread extends Thread
{
    
    private SendConfig config;
    private IImmeSmsSendService service;
    private int subTableIndex;
    private int batchSize;
    private int dealCount;
    private boolean isBig = false;
    private int modIndex = -1;
    
    public ImmeSmsThread(SendConfig config,IImmeSmsSendService service,int subTableIndex){
        this.config=config;
        this.service=service;
        this.subTableIndex=subTableIndex;
        batchSize=config.getBatchSize();
        this.dealCount = 1;
    }
    
    
    @Override
    public void run()
    {
        //第一次启动判断短信的总量是不是超过某个值（20W），如果则需要按照手机号码的倒数第三位分10批处理
        if (getSmsCount() > 200000)
        {
            isBig = true;
        }
        
        while(true){
            if (isBig)
            {
                modIndex ++;
            }
            if (modIndex >= 10)
            {
                isBig = false;
            }
            //初始化上下文
            try
            {
                SendUtil.requestInit();
                int sendCount=service.sendSingleTableData(config,subTableIndex,isBig,modIndex);
                if (sendCount < batchSize)// 当数目少于1000条，则认为目前的短信量没积压，停3S再扫描处理
                {
                    SendUtil.sleep(3000);// 停3秒继续执行
                }
            }
            catch (Exception e)
            {
                service.errorDeal(dealCount);
            }
            finally
            {
                dealCount ++;
            }
        }
    }


    /**
     * 获取短信表中的数据量
     * wukl 2013-1-7
     * @return
     */
    private long getSmsCount()
    {
        try
        {
            String tableName = "JD.TI_O_SMS_IMME_" + subTableIndex;
            return SendSpringUtil.getCommonDao().getClient().count(QB.create(TiOSmsImme.class),tableName);
        }
        catch (SQLException e)
        {
            Logger logger = Logger.getLogger(ImmeSmsSendService.class);
            logger.error("SQL error at: " + e.getSQLState(), e);
        }
        
        return 0;
    }
}
