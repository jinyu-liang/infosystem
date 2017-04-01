package com.ailk.ims.smsts.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ailk.ims.smsts.service.ISmsService;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.SpringShUtil;
import com.ailk.openbilling.persistence.jd.entity.SmsSendConfig;

/**
 * @Description :定义短信提醒流程中用到的配置
 * @author wangjt
 * @Date 2012-8-9
 */
public class SmsConfig
{
    private static ImsLogger imsLogger = new ImsLogger(SmsConfig.class);

    /**
     * 发短信的服务类（单例）
     */
    private static final ISmsService SMSS_SERVICE = (ISmsService) SpringShUtil.getBean("intfsh_sms_service");

    /**
     * 缓存配置表信息
     */
    private static final Map<Integer, SmsSendConfig> configMap = new HashMap<Integer, SmsSendConfig>();

    static
    {
        doInit();
    }

    public static void doInit()
    {
        imsLogger.info("加载SMS_SEND_CONFIG配置表信息....");
        initSmsSendConfigMap();
        imsLogger.info("发送短信提醒初始化结束");
    }

    /**
     * 初始化配置表缓存信息
     */
    private static void initSmsSendConfigMap()
    {
        List<SmsSendConfig> configList = DBUtil.query(SmsSendConfig.class);
        for (SmsSendConfig config : configList)
        {
            configMap.put(config.getBusiNum(), config);
        }
    }

    /**
     * 根据busiNum获取SmsSendConfig对象
     */
    public static SmsSendConfig getSmsSendConfigByBusiNum(Integer busiNum)
    {
        return configMap.get(busiNum);
    }

    /**
     * 获取单例的服务类
     */
    public static ISmsService getSmsService()
    {
        return SMSS_SERVICE;
    }
}
