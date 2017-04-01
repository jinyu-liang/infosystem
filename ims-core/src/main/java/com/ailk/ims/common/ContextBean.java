package com.ailk.ims.common;

import java.util.Date;
import org.apache.log4j.Logger;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;

/**
 * 2011-7-28 wangjt：增加日志记录器
 */
public class ContextBean
{
    /**
     * 日志记录器
     */
    protected ImsLogger imsLogger = new ImsLogger(this.getClass());

    /**
     * @deprecated
     */
    protected Logger logger = Logger.getLogger(this.getClass());
    
    /**
     * 上下文对象，在整个请求流程中需要用到的公用信息都放在此对象中
     */
    protected IMSContext context;

    public IMSContext getContext()
    {
        return context;
    }

    public void setContext(IMSContext context)
    {
        this.context = context;
        imsLogger.buildPrefix(context);//@Date 2012-09-29 wuyujie 构造出日志前缀
    }

    /**
     * 转为mdb格式的valid_date，默认取系统默认valid_date<br>
     * wangjt 2011-12-15
     * @Date 2012-12-27 wukl 如果传入的时间早于1970-01-01 08:00:00（int值：0），则返回这个时间点；因为MDB针对早于该时间的会填空值
     */
    public int toMdbValidDate(Date validDate)
    {
        if (validDate == null)
        {
            validDate = context.getRequestDate();
        }
        long mdbDate = validDate.getTime() / 1000;
        mdbDate = mdbDate > 0 ? mdbDate : 0;
        return (mdbDate > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) mdbDate);
    }

    /**
     * 转为mdb格式的expire_date，默认取系统默认expire_date<br>
     * wangjt 2011-12-15
     */
    public int toMdbExpireDate(Date expireDate)
    {
        if (expireDate == null)
        {
            expireDate = IMSUtil.getDefaultExpireDate();
        }

        long mdbDate = expireDate.getTime() / 1000;
        mdbDate = mdbDate > 0 ? mdbDate : 0;
        return (mdbDate > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) mdbDate);
    }

}