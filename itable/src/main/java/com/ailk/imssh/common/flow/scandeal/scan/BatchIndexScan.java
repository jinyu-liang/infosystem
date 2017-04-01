package com.ailk.imssh.common.flow.scandeal.scan;

import com.ailk.easyframe.sdl.OLTPServiceContext;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.define.ConstantExDefine;
import com.ailk.imssh.common.flow.config.BatchDataIndexConfig;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.util.ITableUtil;

/**
 * @Description:启动i_batch_data_index_sub_[0-7] 的8个表的扫描线程，以及30个队列的处理线程
 * @author wangjt
 * @Date 2012-9-13
 */
public class BatchIndexScan extends Scan
{
    /**
     * 需要接收一个分表序号，取值范围[0-7]，分别对应8个分表
     */
    public static void main(String[] args)
    {
        ImsLogger imsLogger = new ImsLogger(BatchIndexScan.class);
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
        
        String subTableIndex = args[0];
        new BatchIndexScan().startScanDeal(Integer.parseInt(subTableIndex));
    }

    @Override
    protected IConfig getConfig()
    {
        return BatchDataIndexConfig.getInstance();
    }
    
    public void dbmStart(){
        ImsLogger imsLogger = new ImsLogger(BatchIndexScan.class);
        
        imsLogger.debug("***************************itable dbm start***********************");
        /*
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
        */
		String batch = OLTPServiceContext.getInstance().getConfigText(ConstantExDefine.XPATH + "/batch_table", "false");
        
        imsLogger.debug("subTable:"+batch);
        
        new DataIndexScan().startScanDeal(Integer.parseInt(batch));
    }
}
