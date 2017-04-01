package com.ailk.imssh.common.flow.scandeal.scanthread;

import java.util.List;
import jef.common.wrapper.IntRange;
import jef.database.AbstractDbClient;
import jef.database.Condition.Operator;
import jef.database.QueryArg.MyTableName;
import jef.database.query.Query;
import com.ailk.easyframe.web.common.dal.CommonDao;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.imssh.common.flow.config.IConfig;
import com.ailk.imssh.common.flow.scandeal.queue.bean.Queue;
import com.ailk.imssh.common.flow.scandeal.queue.list.QueueList;
import com.ailk.imssh.common.util.ITableUtil;

/**
 * @Description:扫描一个特定分表，并增加到队列中，等待处理
 * @author wangjt
 * @Date 2012-9-13
 */
public class ScanThread extends Thread
{
    private static ImsLogger imsLogger = new ImsLogger(ScanThread.class);

    /**
     * CD.I_DATA_INDEX_SUB_[subTableIndex]
     */
    private String subTableName;
    private IConfig config;
    private QueueList queueList;

    public ScanThread(String subTableName, IConfig config, QueueList queueList)
    {
        this.subTableName = subTableName;
        this.config = config;
        this.queueList = queueList;
    }

    @Override
    public void run()
    {
        // 初始化request
        ITableUtil.requestInit();

        MyTableName myTableName = new MyTableName(subTableName);
        IntRange range = null;
        if("8".equals(subTableName.substring(subTableName.length()-1, subTableName.length()))){
            range = new IntRange(0, 20000);
        }else{
            range = new IntRange(0, config.getScanOneTimeCount());
        }
        CommonDao commonDao = DBUtil.getCommonDao();
        while (true)
        {
            try
            {
                long t1 = System.currentTimeMillis();
                imsLogger.debug(" start select and add to queue, table:", subTableName);
                Query<?> query = config.newSubTableQuery();// 按照seqId的值，从小到大读取
                if(!"8".equals(subTableName.substring(subTableName.length()-1, subTableName.length()))){
                    query.addCondition(config.getSubTableSeqIdField(), Operator.GREAT, queueList.getMaxSeqId());
                }else{
                    query.addCondition(config.getSubTableSeqIdField(),Operator.GREAT_EQUALS,0);
                }

                // 获取数据
                AbstractDbClient dbClient = commonDao.getClient();
                // i_data_index_sub_{idx}
                List list = null;
                int count=0;
                if(!"8".equals(subTableName.substring(subTableName.length()-1, subTableName.length()))){
                	list = dbClient.select(query, range, myTableName);
                }else{
                	for (int i = 0; i <= config.getDealThreadCount(); i++) {
            			Queue queue = queueList.getQueue(i);
            			if(queue.getDataHolderList().size()==0){
            				count++;
            				if(count==(config.getDealThreadCount()+1)){
            					list = dbClient.select(query, range, myTableName);
            				}
            			}else{
            				i=-1;
            				count=0;
            			}

					}
                }

                // lijc3 避免框架在第一次查询的时候，数据库本来存在数据，但是由于数据库连接的异常关闭导致返回回来的查询结果为空
                // 所以增加了一次查询。
                if (CommonUtil.isEmpty(list))
                {
                    list = dbClient.select(query, range, myTableName);
                }
                imsLogger.debug(new Object[]{t1, "end select table: ", subTableName, ", results size:", (list == null ? 0 : list.size())});

                if (list != null && !list.isEmpty())
                {
                    boolean allAueueFull = queueList.addListToQueue(list, subTableName);// 数据不为空，则增加数据到队列
                    if (allAueueFull)
                    {// 所有队列满，则等待3000ms再继续扫描
                        imsLogger.error(" all queue are full , sleep 2000ms...");
                        ITableUtil.sleep(2000);
                    }
                    else
                    {
                        imsLogger.debug(",addListToQueue succ, sleep 2000ms...");
                        ITableUtil.sleep(2000);
                    }
                }
                else
                {
                    imsLogger.debug(" this scan thread have not selected any results, sleep 2000ms....");
                    // 未扫描到数据，则停一秒继续扫描
                    ITableUtil.sleep(2000);
                }
                imsLogger.debug(new Object[]{t1, " end select and add to queue, table:", subTableName});
            }
            catch (Exception e)
            {
                imsLogger.error(e, e);
            }
        }
    }
}
