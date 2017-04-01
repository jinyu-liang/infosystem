package com.ailk.ims.util;

import jef.tools.Json;
import org.apache.log4j.Logger;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.define.SysCodeDefine;

/**
 * @Description:统一的日志输出对象
 * @author wangjt
 * @Date 2012-9-21
 */
public class ImsLogger
{
    private Logger logger;
    private String prefix;

    public ImsLogger(Class clazz)
    {
        this.logger = Logger.getLogger(clazz.getName());
    }

    /**
     * 构建日志输出前缀: servicename.methodname[donecode]
     * 
     * @author wuyj 2012-9-29
     * @param context
     */
    public void buildPrefix(IMSContext context)
    {
        if (context != null)
        {
            prefix = LogUtil.buildBusiPrefix(context.getServiceConfig(), context.getMethodConfig(), context.getSoNbr());
        }
    }

    /**
     * 打印出一个复杂对象，简单类型不建议用该方法。且该方法是debug级别
     * 
     * @author wuyj 2012-9-22
     * @name 打印的名称标识
     * @param obj
     * @isDebug ,但只能传一个，true表示是debug级别，false表示是info级别
     */
    public void dump(String name, Object object)
    {
        if (!needDump())
        {
            return;
        }
        long start = System.currentTimeMillis();
        try
        {
            StringBuffer sb = new StringBuffer("dump [\"" + name + "\"] : ");
            if (object == null)
            {
                sb.append("[null]");
            }
            else
            {
                sb.append(object.getClass().getName()).append("\n");
                sb.append(LogUtil.parse(object, 1, null));

            }
            sb.append("\n").append("dump finished");
            this.debug(new Object[]{start, sb.toString()});

        }
        catch (Exception e)
        {
            logger.debug(e, e);
        }
    }

    // public static void main(String[] aa){
    // ImsLogger logger = new ImsLogger(ImsLogger.class);
    // BusiDataInfo info = new BusiDataInfo();
    // logger.dump("dddddd", null);
    // }

    /**
     * 打印debug级别日志
     * 
     * @param msgArr:多个日志信息
     */
    public void debug(Object... msgArr)
    {
        if (LogUtil.isDebugEnabled())
        {
            logger.debug(CommonUtil.concat(prefix, CommonUtil.concat(msgArr)));
        }
    }

    /**
     * 打印debug级别日志
     */
    public void debug(long start, Object... msgArr)
    {
        if (LogUtil.isDebugEnabled())
        {
            long end = System.currentTimeMillis();
            logger.debug(CommonUtil.concat(prefix, CommonUtil.concat(msgArr), LogUtil.buildTime(start, end)));
        }
    }

    /**
     * 打印error级别日志
     */
    public void error(Object... msgArr)
    {
        logger.error(CommonUtil.concat(prefix, msgArr));
    }

    /**
     * 打印error级别日志
     */
    public void error(long start, Object... msgArr)
    {
        long end = System.currentTimeMillis();
        logger.error(CommonUtil.concat(prefix, CommonUtil.concat(msgArr), LogUtil.buildTime(start, end)));
    }

    public void error(Object message, Throwable t)
    {
        logger.error(CommonUtil.concat(prefix, message), t);
    }

    public void error(Throwable t)
    {
        logger.error(t.getMessage(), t);
    }

    /**
     * 打印info级别日志
     */
    public void info(Object... msgArr)
    {
        if (LogUtil.isInfoEnabled())
        {
            logger.info(CommonUtil.concat(prefix, CommonUtil.concat(msgArr)));
        }
    }

    /**
     * 打印info级别日志
     */
    public void info(long start, Object... msgArr)
    {
        if (LogUtil.isInfoEnabled())
        {
            long end = System.currentTimeMillis();
            logger.info(CommonUtil.concat(prefix, CommonUtil.concat(msgArr), LogUtil.buildTime(start, end)));
        }
    }

    /**
     * 打印JSON对象
     */
    public void dumpJsonObject(String name, Object object)
    {
        if (object != null && needJsonDump())
        {	
        	long start = System.currentTimeMillis();
        	StringBuffer sb = new StringBuffer("dump [\"" + name + "\"] : ");

             sb.append(Json.serialize(object));

             sb.append("\n").append("dumpJsonObject finished");
        	
             this.debug(new Object[]{start, sb.toString()});
        }
    }

    private boolean needDump()
    {
        return (LogUtil.isDebugEnabled() && SysUtil.getBoolean(SysCodeDefine.busi.IS_DUMP, false));
    }
    private boolean needJsonDump()
    {
        return (LogUtil.isDebugEnabled() && SysUtil.getBoolean(SysCodeDefine.busi.IS_JSON_DUMP, false));
    }
    
    private boolean needIntfDump()
    {
        return (LogUtil.isInfoEnabled() && SysUtil.getBoolean(SysCodeDefine.busi.IS_INTF_DUMP, false));
    }
    /**
     * 打印外围接口出入参
     * @param name
     * @param object
     */
    public void intfDump(String name, Object object)
    {
        if (!needIntfDump())
        {
            return;
        }
        long start = System.currentTimeMillis();
        try
        {
            StringBuffer sb = new StringBuffer("dump [\"" + name + "\"] : ");
            if (object == null)
            {
                sb.append("[null]");
            }
            else
            {
                sb.append(object.getClass().getName()).append("\n");
                sb.append(LogUtil.parse(object, 1, null));

            }
            sb.append("\n").append("dump finished");
            this.error(new Object[]{start, sb.toString()});

        }
        catch (Exception e)
        {
            logger.debug(e, e);
        }
    }
}
