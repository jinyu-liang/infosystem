package com.ailk.ims.common;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.init.IImsConfigInit;
import com.ailk.ims.util.BusiUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.FileUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.ProjectUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.ims.xml.XStreamHolder;
import com.ailk.openbilling.persistence.acct.entity.CaAccountGroup;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRel;
import com.ailk.openbilling.persistence.acct.entity.CaAccountRv;
import com.ailk.openbilling.persistence.acct.entity.CaResGrpRevs;
import com.ailk.openbilling.persistence.cust.entity.CmContactMedium;
import com.ailk.openbilling.persistence.cust.entity.CmCustGroup;
import com.ailk.openbilling.persistence.cust.entity.CmCustVip;
import com.ailk.openbilling.persistence.cust.entity.CmResIdentity;
import com.ailk.openbilling.persistence.cust.entity.CmResLifecycle;
import com.ailk.openbilling.persistence.cust.entity.CmResService;
import com.ailk.openbilling.persistence.cust.entity.CmResServiceStatus;
import com.ailk.openbilling.persistence.cust.entity.CmResource;
import com.ailk.openbilling.persistence.cust.entity.CmResourceMonitor;
import com.ailk.openbilling.persistence.cust.entity.CmUserEnterprise;
import com.ailk.openbilling.persistence.cust.entity.CmUserImpu;
import com.ailk.openbilling.persistence.cust.entity.CmUserMap;
import com.ailk.openbilling.persistence.cust.entity.CmUserOrder;
import com.ailk.openbilling.persistence.cust.entity.CmUserParam;
import com.ailk.openbilling.persistence.cust.entity.CmUserRelation;
import com.ailk.openbilling.persistence.cust.entity.CmUserShareProd;
import com.ailk.openbilling.persistence.cust.entity.CoProd;
import com.ailk.openbilling.persistence.cust.entity.CoProdCharValue;
import com.ailk.openbilling.persistence.cust.entity.CoProdPriceParam;
import com.ailk.openbilling.persistence.cust.entity.CoSplitCharValue;
import com.ailk.openbilling.persistence.sys.entity.SysParameter;

import jef.database.DataObject;

/**
 * @Description: 初始化bean，TS不支持servlet加载，所以在这个bean里初始化
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-8-30
 * @Date 2012-06-08 wuyujie : init方法增加异常输出
 * @Date 2012-06-18 wukl 便于开发测试，将版本控制放到虚拟机参数中，若没配置则取数据库
 * @Date 2012-09-13 yugb :[59009 ]事务一致性改造
 */
public class InitBean implements InitializingBean
{
    private static ImsLogger imsLogger = new ImsLogger(InitBean.class);
    private static final String PATH_CONF_BEANS = "/ims-conf/boot/boot.xml";
    private static BaseNode root;
    private static String TAG_BOOT = "boot";
    private static String TAG_CONFIG = "config";
    private static String ATTR_CONFIG_NAME = "name";
    private static String ATTR_BEAN = "bean";
    // private static SalClient salClient;

    // private static final String ATTR_ISDELETE = "isDelete";

    public void afterPropertiesSet() throws Exception
    {
        init();
    }

    public static void init()
    {
        try
        {

            imsLogger.info("***** query project config...");
            String project = getProject();

            imsLogger.info("***** begin to init beans config...");
            BusiUtil.init(project);

            imsLogger.info("***** begin to init boot config...");
            init_boot(project);

            imsLogger.info("***** finish to init");
            
            imsLogger.info("***** begin to init diffList...");
//            diffList.add(CaAccountGroup.class);
//            diffList.add(CaAccountRel.class);
//            diffList.add(CaAccountRv.class);
//            diffList.add(CaResGrpRevs.class);
//            diffList.add(CmContactMedium.class);
//            diffList.add(CmCustGroup.class);
//            diffList.add(CmCustVip.class);
//            diffList.add(CmResIdentity.class);
//            diffList.add(CmResLifecycle.class);
//            diffList.add(CmResService.class);
//            diffList.add(CmResServiceStatus.class);
//            diffList.add(CmResource.class);
//            diffList.add(CmResourceMonitor.class);
//            diffList.add(CmUserEnterprise.class);
//            diffList.add(CmUserImpu.class);
//            diffList.add(CmUserMap.class);
//            diffList.add(CmUserOrder.class);
//            diffList.add(CmUserParam.class);
//            diffList.add(CmUserRelation.class);
//            diffList.add(CmUserShareProd.class);
//            diffList.add(CoProd.class);
//            diffList.add(CoProdCharValue.class);
//            diffList.add(CoProdPriceParam.class);
//            diffList.add(CoSplitCharValue.class);

            imsLogger.info("***** finish to diffList init");

            if(ProjectUtil.is_CN_SH()){
                LuaConfig.init();
            }
        }
        catch (Exception e)
        {
            imsLogger.error(e, e);// 2012-06-08 wuyujie ：增加错误堆栈的输出
            throw IMSUtil.throwBusiException(e);
        }
    }


    private static String getProject()
    {
        // @Date 2012-06-18 wukl 便于开发测试，将版本控制放到虚拟机参数中，若没配置则取数据库
        String vmParam = IMSUtil.getVMProject();
        if (vmParam != null)
            return vmParam;
        SysParameter sp = DBUtil.querySingle(SysParameter.class, new DBCondition(SysParameter.Field.paramCode,
                SysCodeDefine.busi.PROJECT), new DBCondition(SysParameter.Field.paramClass, 6));
        return sp == null ? ConstantDefine.PROJECT_TH_AIS : sp.getParamValue();
    }

    /**
     * 对于新增加的大类，直接增加到基础版本上 luojb 2012-2-22
     * 
     * @param project
     * @return
     */
    public static void parseConfProj(BaseNode root, BaseNode rootProj)
    {
        if (rootProj == null)
            return;
        List<BaseNode> children = rootProj.getChildren();
        if (CommonUtil.isEmpty(children))
            return;
        List<BaseNode> addServiceNode = new ArrayList<BaseNode>();
        for (BaseNode service : children)
        {
            String serviceName = service.getAttribute(ATTR_BEAN);

            // 如果整个服务节点都是新增的，直接添加到基础版本里
            if (root.getChildByAttribute(ATTR_BEAN, serviceName) == null)
            {
                addServiceNode.add(service);
                continue;
            }
        }
        if (CommonUtil.isNotEmpty(addServiceNode))
        {
            root.addChildren(addServiceNode);
            children.removeAll(addServiceNode);
        }

        return;
    }

    /**
     * 合并第二层节点 luojb 2012-2-22
     * 
     * @param serviceNode
     * @param methodMap
     */
    public static void mergeChildrenByTag(BaseNode serviceNode, BaseNode serviceProj)
    {

        if (serviceProj == null)
            return;
        List<BaseNode> methods = serviceProj.getChildren();
        if (CommonUtil.isEmpty(methods))
            return;
        List<BaseNode> addList = new ArrayList<BaseNode>();
        for (BaseNode method : methods)
        {
            String methodName = method.getTagName();
            List<BaseNode> controlList = serviceProj.getChildrenByTagName(methodName);
            if (controlList != null && controlList.size() > 1)
            {
                throw IMSUtil.throwBusiException("******duplicate node <" + methodName + "> under <" + serviceProj.getTagName()
                        + " bean=" + serviceProj.getAttribute(ATTR_BEAN) + ">");
            }
            BaseNode orgMethod = serviceNode.getChildByTagName(methodName);
            if (orgMethod != null)
                serviceNode.getChildren().remove(orgMethod); // 移除

            // Boolean isDelete = method.getBooleanAttribute(ATTR_ISDELETE);
            // if(isDelete == null || !isDelete)//替换
            addList.add(method);
        }
        if (CommonUtil.isNotEmpty(addList))
        {
            serviceNode.addChildren(addList);
        }
    }

    /**
     * 合并分版本的方法 luojb 2012-2-22
     * 
     * @param serviceNode
     * @param methodMap
     */
    public static void mergeChildrenByAttr(BaseNode serviceNode, BaseNode serviceProj, String attr)
    {
        if (serviceProj == null)
            return;
        List<BaseNode> methods = serviceProj.getChildren();
        if (CommonUtil.isEmpty(methods))
            return;
        List<BaseNode> addList = new ArrayList<BaseNode>();
        for (BaseNode method : methods)
        {
            String methodName = method.getAttribute(attr);
            List<BaseNode> controlList = serviceProj.getChildrenByAttribute(attr, methodName);
            if (controlList != null && controlList.size() > 1)
            {
                throw IMSUtil.throwBusiException("******duplicate node <" + methodName + "> under <" + serviceProj.getTagName()
                        + " bean=" + serviceNode.getAttribute(ATTR_BEAN) + ">");
            }
            BaseNode orgMethod = serviceNode.getChildByAttribute(attr, methodName);
            if (orgMethod != null)
                serviceNode.getChildren().remove(orgMethod); // 移除

            // Boolean isDelete = method.getBooleanAttribute(ATTR_ISDELETE);
            // if(isDelete == null || !isDelete)//替换
            addList.add(method);
        }
        if (CommonUtil.isNotEmpty(addList))
        {
            serviceNode.addChildren(addList);
        }
    }

    public static String getRootXML()
    {
        return new XStreamHolder(TAG_BOOT).parse2Xml(root);
    }

    public static void init_boot(String project)
    {
        // 载入配置项，内部配置项和业务配置项
        try
        {
            XStreamHolder holder = new XStreamHolder(TAG_BOOT);
            root = (BaseNode) holder.parseFromFile(PATH_CONF_BEANS);

            // 加载分项目版本配置文件
            String pathConfProj = ProjectUtil.parseProjectConfPath(PATH_CONF_BEANS, project);
            imsLogger.info(">>>>>>>>project config file:" + pathConfProj);
            XStreamHolder projHolder = new XStreamHolder(TAG_BOOT);
            BaseNode rootProj = null;
            if (CommonUtil.isValid(pathConfProj))
            {
                if (FileUtil.isFileExist(pathConfProj))
                    rootProj = (BaseNode) projHolder.parseFromFile(pathConfProj);
            }
            parseConfProj(root, rootProj);

            List<BaseNode> children = root.getChildren();
            if (CommonUtil.isEmpty(children))
            {
                IMSUtil.throwBusiException("no <config> tag under root");
            }
            // config
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
                    IMSUtil.throwBusiException("config bean '" + configBeanName + "' must be implement of "
                            + IImsConfigInit.class.getName());
                }

                BaseNode configNodeProj = rootProj == null ? null : rootProj.getChildByAttribute(ATTR_BEAN, configBeanName);

                IImsConfigInit initBean = (IImsConfigInit) beanClass.newInstance();
                imsLogger.info("+++++++++++begin to init config : " + configName);
                initBean.mergeNodes(configNode, configNodeProj);
                initBean.init(configNode);
            }
        }
        catch (Exception e)
        {
            imsLogger.error("****** failed to init config file : " + PATH_CONF_BEANS);
            throw IMSUtil.throwBusiException(e);
        }
    }

}
