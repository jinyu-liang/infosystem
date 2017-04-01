package com.ailk.imssh.common.init;

import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import com.ailk.ims.init.IImsConfigInit;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;
import com.ailk.ims.xml.XStreamHolder;

/**
 * @Description: 初始化bean，TS不支持servlet加载，所以在这个bean里初始化
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-30
 */
public class InitBean implements InitializingBean
{
    private static ImsLogger imsLogger = new ImsLogger(InitBean.class);
    private static final String PATH_CONF_BEANS = "/itable-conf/boot/boot.xml";
    private static BaseNode root;
    private static String TAG_BOOT = "boot";
    private static String TAG_CONFIG = "config";
    private static String ATTR_CONFIG_NAME = "name";
    private static String ATTR_BEAN = "bean";

    @Override
    public void afterPropertiesSet() throws Exception
    {
        init_boot();
        imsLogger.info("***** finish to init");
    }

    private static void init_boot()
    {
        // 载入配置项，内部配置项和业务配置项
        try
        {
            XStreamHolder holder = new XStreamHolder(TAG_BOOT);
            root = (BaseNode) holder.parseFromFile(PATH_CONF_BEANS);

            List<BaseNode> children = root.getChildren();
            if (CommonUtil.isEmpty(children))
            {
                IMSUtil.throwBusiException("no <config> tag under root");
            }
            for (BaseNode configNode : children)
            {
                if (!TAG_CONFIG.equalsIgnoreCase(configNode.getTagName()))
                {
                    IMSUtil.throwBusiException("invalid node <" + configNode.getTagName() + "> under node <" + TAG_BOOT
                            + ">. expected : " + TAG_CONFIG);
                }
                String configName = configNode.getAttribute(ATTR_CONFIG_NAME);
                String configBeanName = configNode.getAttribute(ATTR_BEAN);
                if (!CommonUtil.isValid(configName))
                {
                    IMSUtil.throwBusiException("'name' attribute of <config> can't be null");
                }
                if (!CommonUtil.isValid(configBeanName))
                {
                    IMSUtil.throwBusiException("'bean' attribute of <config> can't be null");
                }

                Class beanClass = Class.forName(configBeanName);
                if (!IImsConfigInit.class.isAssignableFrom(beanClass))
                {
                    IMSUtil.throwBusiException("config bean '" + configBeanName
                            + "' must be implement of com.ailk.ims.init.IImsConfigInit");
                }
                IImsConfigInit initBean = (IImsConfigInit) beanClass.newInstance();
                initBean.init(configNode);
            }
        }
        catch (Exception e)
        {
            imsLogger.error("****** failed to init config file : " , PATH_CONF_BEANS);
            throw IMSUtil.throwBusiException(e);
        }
    }

}
