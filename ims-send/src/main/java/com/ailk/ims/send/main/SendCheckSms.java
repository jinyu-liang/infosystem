package com.ailk.ims.send.main;

import org.apache.log4j.Logger;
import com.ailk.ims.send.auth.TemplateAuthService;
import com.ailk.ims.send.config.CheckSmsConfig;
import com.ailk.ims.send.config.SendConfig;
import com.ailk.ims.send.config.ImmeSmsConfig;
import com.ailk.ims.send.init.CommonInit;
import com.ailk.ims.send.init.InitInfo;
import com.ailk.ims.send.service.ICheckSmsSendService;
import com.ailk.ims.send.thread.CheckSmsThread;
import com.ailk.ims.send.util.SendSpringUtil;

/**
 * 数据从billing侧发送至crm侧的主进程类<BR>
 * 【发送SendCheckSms/SmsSendInterfaceCheck】
 * 
 * @author wangjt
 * @Date 2012-10-15
 */
public class SendCheckSms
{
    public static void main(String[] args)
    {
        CommonInit.commonInit();
        Logger logger = Logger.getLogger(SendCheckSms.class);
        // logger.info(" send [CheckSmsSend/SmsSendInterfaceCheck] start ...");

        ICheckSmsSendService service = SendSpringUtil.getCheckSmsSendService();

        TemplateAuthService templateAuthService = SendSpringUtil.getTemplateAuthService();
        // Query query = QueryBuilder.create(CheckSmsSend.class);
        // query.addOrderBy(true, CheckSmsSend.Field.blockId);// 排序字段
        // IntRange singleRange = new IntRange(1, 1);// 审核表逐个查询获取

        // int batchSize = InitInfo.getBatchSize();// 明细表一次发送1000条，从数据库配置读取

        // IntRange batchRange = new IntRange(1, batchSize);
        SendConfig sendConfig = new CheckSmsConfig();
        int tableCount = sendConfig.getSubTableCount();

        for (int i = 0; i < tableCount; i++)
        {
            CheckSmsThread thread = new CheckSmsThread(i, service, templateAuthService, sendConfig);
            thread.start();
        }
        logger.info("######send checkSmsSend/SmsSendInterfaceCheck] start...");

        /*
         * //TODO while (true) { try { // 获取一条审核总表中的数据，并且统计明细表对应的条数，update到审核总表 CheckSmsSend checkSmsSend =
         * service.selectAndUpdateCheckSmsSend(query, singleRange,0); // 如果认证失败(短信审核表和对应的明细表将会移到未认证的表中),下面的流程跳过 if
         * (!templateAuthService.AllowSyncSms(checkSmsSend)) { checkSmsSend = null; } if (checkSmsSend == null) {
         * SendUtil.sleep(1000);// 总表无数据，则停1秒继续执行 continue; } SmsSendInterfaceCheck cond = new SmsSendInterfaceCheck();
         * cond.setBlockId(checkSmsSend.getBlockId()); // 循环把blockId对应的明细表数据发送给crm，直到发送完毕，退出循环 while (true) { int sendSize =
         * service.sendDetail(cond, batchRange,0); if (sendSize < batchSize) { break; } } // 删除审核总表中的一条记录，并发送至CRM
         * service.deleteAndSendCheckSmsSend(checkSmsSend,0); } catch (BaseException e) { } }
         */
    }
}
