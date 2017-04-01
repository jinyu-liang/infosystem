package com.ailk.ims.smsts.config;

import com.ailk.easyframe.web.common.dal.DefaultUniqueIdManager;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.util.SpringUtil;

/**
 * @Description :定义短信提醒流程中用到的SEQ
 * @author wangjt
 * @Date 2012-8-15
 */
public class SmsSeqConfig
{
    private static final String SEQ_BLOCK_ID = "JD.SEQ_BLOCK_ID";
    private static final String SEQ_SMS_SEND_DONE_CODE = "JD.SEQ_SMS_SEND_DONE_CODE";

    private static Long getSeq(String seqName)
    {
        DefaultUniqueIdManager seqManager = (DefaultUniqueIdManager) SpringUtil
                .getBean(ConstantDefine.SPRING_BEAN_UNIQUEIDMANAGER);
        return seqManager.nextLong(seqName);
    }

    public static Long newBlockId()
    {
        return getSeq(SEQ_BLOCK_ID);
    }

    public static Long newDoneCode()
    {
        return getSeq(SEQ_SMS_SEND_DONE_CODE);
    }
}
