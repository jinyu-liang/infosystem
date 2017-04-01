package com.ailk.ims.send.thread;

import jef.common.log.Logger;
import jef.common.wrapper.IntRange;
import jef.database.query.Query;
import com.ailk.ims.send.auth.TemplateAuthService;
import com.ailk.ims.send.config.SendConfig;
import com.ailk.ims.send.service.ICheckSmsSendService;
import com.ailk.ims.send.util.SendUtil;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

public class CheckSmsThread extends Thread
{
    private SendConfig config;
    private int subTableIndex;
    private ICheckSmsSendService service;
    private TemplateAuthService templateAuthService;

    private Query query;
    IntRange singleRange = new IntRange(1, 1);// 审核表逐个查询获取

    private int batchSize;// 明细表一次发送1000条，从数据库配置读取

    IntRange batchRange ;
    private int dealCount;

    public CheckSmsThread(int subTableIndex, ICheckSmsSendService sendService, TemplateAuthService templateAuthService,SendConfig config)
    {
        this.config=config;
        this.subTableIndex = subTableIndex;
        this.service = sendService;
        this.templateAuthService = templateAuthService;
        batchSize = config.getBatchSize();
        batchRange = config.getIntRange();
        query = config.getQuery();
        this.dealCount = 1;
    }

    @Override
    public void run()
    {
        while(true){
            try
            {
                SendUtil.requestInit();
                CheckSmsSend checkSmsSend = service.selectAndUpdateCheckSmsSend(query, singleRange, subTableIndex, config);
                if (!templateAuthService.AllowSyncSms(checkSmsSend,batchSize))
                {
                    checkSmsSend = null;
                }
                if (checkSmsSend == null)
                {
                    SendUtil.sleep(3000);// 总表无数据，则停3秒继续执行
                }
                else
                {
                    SmsSendInterfaceCheck cond = new SmsSendInterfaceCheck();
                    cond.setBlockId(checkSmsSend.getBlockId());
                    
                    // 循环把blockId对应的明细表数据发送给crm，直到发送完毕，退出循环
                    while (true)
                    {
                        int sendSize = service.sendDetail(cond, batchRange, subTableIndex, config);
                        
                        if (sendSize < batchSize)
                        {
                            break;
                        }
                    }
                    
                    // 删除审核总表中的一条记录，并发送至CRM
                    service.deleteAndSendCheckSmsSend(checkSmsSend, subTableIndex, config);
                }
            }
            catch (Exception e)
            {
                Logger.error(e.getMessage());
                service.errorDeal(dealCount);
            }
            finally
            {
                dealCount ++;
            }
        }
    }
}
