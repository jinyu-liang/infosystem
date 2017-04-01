package com.ailk.ims.send.service;

import org.springframework.transaction.annotation.Transactional;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.send.config.SendConfig;

/**
 * @Description:数据从billing侧发送至crm侧
 * @author wangjt
 * @Date 2012-10-15
 */
public interface IImmeSmsSendService
{
    /**
     * 涉及两个数据源 <BR>
     * 返回发送给crm的数据条数 ， <BR>
     * 对于单表数据同步，统一采用该方法
     * @param isBig 
     */
    @Transactional
    public int sendSingleTableData(SendConfig sendConfig,int subTableIndex, boolean isBig,int modIndex) throws BaseException;

    /**
     *  异常错误处理<BR>
     *  当发现异常时,sleep线程，sleep时间由重处理的次数决定<BR>
     * @param dealCount 
     */
    public void errorDeal(int dealCount);
    
    /**
     * 根据处理次数获取 对应的SLEEP时间
     */
    public int getDelayTime(int dealCount);
    
    /**
     * 构造查询SQL
     * wukl 2013-1-7
     * @param subTableIndex
     * @return
     */
    public String buildQuerySql(int subTableIndex);
    
    /**
     * 构造删除SQL
     * wukl 2013-1-7
     * @param subTableIndex
     * @return
     */
    public String buildDeleteSql(int subTableIndex);
}