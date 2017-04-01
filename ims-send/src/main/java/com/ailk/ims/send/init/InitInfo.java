package com.ailk.ims.send.init;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.ailk.ims.send.util.SendSpringUtil;
import com.ailk.openbilling.persistence.sys.entity.SysParameter;

/**
 * @Description:初始化信息类
 * @author wangjt
 * @Date 2012-11-1
 */
public class InitInfo
{
    private static int BATCH_SIZE;// 批量发送给crm的数据条数，如1000
    private static boolean NEEDAUTH = false;// 是否需要短信认证功能
    private static int SMS_CHECK_TABLE_COUNT = 8;
    private static int SMS_IMME_TABLE_COUNT = 50;

    public static void init()
    {
        SysParameter sysParameter = new SysParameter();
        sysParameter.setParamClass(6);
        sysParameter.setParamCode("SMS_SEND_BATCH_NUM");

        SysParameter sp = queryParam(sysParameter);
        if (sp == null || sp.getParamValue() == null)
        {
            throw new RuntimeException("SMS_SEND_BATCH_NUM in sd.sys_parameter is null!!!!!");
        }
        BATCH_SIZE = Integer.valueOf(sp.getParamValue().trim());

        // 是否需要短信认证功能
        sysParameter = new SysParameter();
        sysParameter.setParamClass(6);
        sysParameter.setParamCode("SMS_SEND_NEED_AUTH");
        SysParameter sp2 = queryParam(sysParameter);
        if (null == sp2 || sp2.getParamValue() == null)
        {
            throw new RuntimeException("SMS_SEND_NEED_AUTH  in sd.sys_parameter is null!!!!!");
        }

        if (!sp2.getParamValue().equals("0"))
        {
            NEEDAUTH = true;
        }

        // 获取实时短信处理线程数
        /*sysParameter = new SysParameter();
        sysParameter.setParamClass(6);
        sysParameter.setParamCode("SMS_SEND_IMME_SUB");
        SysParameter sp3 = queryParam(sysParameter);
        if (null != sp3 && sp3.getParamValue() != null)
        {
            SMS_IMME_TABLE_COUNT = Integer.valueOf(sp3.getParamValue().trim());
        }*/

        // 获取审核短信处理线程数
        sysParameter = new SysParameter();
        sysParameter.setParamClass(6);
        sysParameter.setParamCode("SMS_SEND_CHECK_SUB");
        SysParameter sp4 = queryParam(sysParameter);
        if (null != sp4 && sp4.getParamValue() != null)
        {
            SMS_CHECK_TABLE_COUNT = Integer.valueOf(sp4.getParamValue().trim());
        }

    }

    public static SysParameter queryParam(SysParameter condition) throws RuntimeException
    {
        SysParameter parameter = null;
        try
        {
            parameter = SendSpringUtil.getCommonDao().getClient().loadSingle(condition);
        }
        catch (SQLException e)
        {
            Logger.getLogger(InitInfo.class).error(e, e);
            throw new RuntimeException(e);
        }
        return parameter;

    }

    public static boolean needAuth()
    {
        return NEEDAUTH;
    }

    public static int getBatchSize()
    {
        return BATCH_SIZE;
    }

    public static int getCheckSmsTableCount()
    {
        return SMS_CHECK_TABLE_COUNT;
    }

    public static int getSmsImmeTableCount()
    {
        return SMS_IMME_TABLE_COUNT;
    }
}
