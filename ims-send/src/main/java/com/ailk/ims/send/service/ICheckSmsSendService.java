package com.ailk.ims.send.service;

import jef.common.wrapper.IntRange;
import jef.database.query.Query;
import com.ailk.easyframe.web.common.exception.BaseException;
import com.ailk.ims.send.config.SendConfig;
import com.ailk.openbilling.persistence.jd.entity.CheckSmsSend;
import com.ailk.openbilling.persistence.jd.entity.SmsSendInterfaceCheck;

/**
 * @Description:审核总表和明细表数据发送至CRM(实现类中，每个方法需要标注控制事务)
 * @author wangjt
 * @Date 2012-10-16
 */
public interface ICheckSmsSendService
{

    /**
     * 获取一条审核总表中的数据，并且统计明细表对应的条数，update到审核总表
     */
    public CheckSmsSend selectAndUpdateCheckSmsSend(Query<CheckSmsSend> query, IntRange intRange,int subTableIndex,SendConfig sendConfig) throws BaseException;

    /**
     * 短信明细表中的数据分批次发送给CRM<BR>
     * 返回数据发送给CRM数据条数 <BR>
     * 发送多个表，表之间有关联
     */
    public int sendDetail(SmsSendInterfaceCheck cond, IntRange intRange,int subTableIndex,SendConfig sendConfig) throws BaseException;

    /**
     * 删除审核总表中的一条记录，并发送至CRM
     */
    public void deleteAndSendCheckSmsSend(CheckSmsSend checkSmsSend,int subTableIndex,SendConfig sendConfig) throws BaseException;

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
}