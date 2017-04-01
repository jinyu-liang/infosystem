package com.ailk.imssh.common.flow.consts;

import com.ailk.ims.util.DBUtil;
import com.ailk.openbilling.persistence.itable.entity.IBatchDataIndexSub;
import com.ailk.openbilling.persistence.itable.entity.IDataIndexSub;

/**
 * @Description
 * @author lijc3
 * @Date 2012-11-9
 */
public class SeqIdGenerate
{
    private static long end = 0;
    private static long start = 0L;

    /**
     * 分发线程取小索引表的seq,从数据库取一次，当做1000个来使用<BR>
     */
    public static long getIDataIndexSubSeq()
    {
        if (start < end)
        {
            return start++;
        }
        else
        {
            // 取到的数据库正在的seq
            long seq = DBUtil.getSequence(IDataIndexSub.class);
            // 取一次为1000个
            start = seq * 1000;
            end = (seq + 1) * 1000;
            return start++;
        }
    }
    
    public static long getIBatchDataIndexSubSeq()
    {
        if (start < end)
        {
            return start++;
        }
        else
        {
            // 取到的数据库正在的seq
            long seq = DBUtil.getSequence(IBatchDataIndexSub.class);
            // 取一次为1000个
            start = seq * 1000;
            end = (seq + 1) * 1000;
            return start++;
        }
    }

}
