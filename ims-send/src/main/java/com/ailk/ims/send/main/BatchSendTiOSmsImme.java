package com.ailk.ims.send.main;

import org.apache.log4j.Logger;
import com.ailk.ims.send.config.ImmeBatchSmsConfig;
import com.ailk.ims.send.config.SendConfig;
import com.ailk.ims.send.init.CommonInit;
import com.ailk.ims.send.service.IImmeSmsSendService;
import com.ailk.ims.send.thread.BatchImmeSmsThread;
import com.ailk.ims.send.util.SendSpringUtil;
import com.ailk.ims.send.util.SendUtil;

/**
 * 单表数据从billing侧发送至crm侧的主进程类<BR>
 * 发送SendTiOSmsImme表数据
 * 
 * @author wangjt
 * @Date 2012-10-15
 */
public class BatchSendTiOSmsImme
{
    public static void main(String[] args)
    {
        // 如果有其他单表需要发送给crm，只需要修改这3个参数即可
        // 目前只支持TiOSmsImme这个表，SingleTableService中有写死的代码
        CommonInit.commonInit();

        Logger logger = Logger.getLogger(BatchSendTiOSmsImme.class);
        logger.info(" [SendTiOSmsImmeBatch] start ...");

        IImmeSmsSendService service = SendSpringUtil.getBatchTableService();

        SendConfig sendConfig = new ImmeBatchSmsConfig();
        // int batchSize = sendConfig.getBatchSize();
        int tableCount = sendConfig.getSubTableCount();

        for (int i = 0; i < tableCount; i++)
        {
            BatchImmeSmsThread thread = new BatchImmeSmsThread(sendConfig, service, i);
            thread.start();
            SendUtil.sleep(2000);
        }
        /*
         * while (true) { try { // TODO int sendCount = service.sendSingleTableData(sendConfig, 1); if (sendCount != batchSize)//
         * 表示所有数据已经发送完 { SendUtil.sleep(1000);// 停1秒继续执行 } } catch (BaseException e) { } }
         */
    }
}
