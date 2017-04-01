package com.ailk.imssh.common.flow.error.bean;

import java.util.LinkedList;

/**
 * @Description:错误队列[第二层]，存储一个客户或者账户的错单
 * @author wangjt
 * @Date 2012-11-8
 */
public class ErrorListHolder
{
    private Long busiValue;// cust_id or acct_id
    private LinkedList<ErrorHolder> errorHolderList = new LinkedList<ErrorHolder>();

    public ErrorListHolder(Long busiValue, ErrorHolder errorHolder)
    {
        this.busiValue = busiValue;
        errorHolderList.add(errorHolder);
    }

    /**
     * 提供该方法，可以令调用方控制锁
     */
    public LinkedList<ErrorHolder> getErrorHolderList()
    {
        return errorHolderList;
    }

    public Long getBusiValue()
    {
        return busiValue;
    }
}
