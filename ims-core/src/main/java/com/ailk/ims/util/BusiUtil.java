package com.ailk.ims.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.ailk.easyframe.web.common.dal.IService;
import com.ailk.ims.common.BaseBusiBean;
import com.ailk.ims.common.BaseMdbBean;
import com.ailk.ims.common.BaseQueryBean;
import com.ailk.ims.common.BaseSyncBean;
import com.ailk.ims.common.BaseTsBean;
import com.ailk.ims.common.BaseTsHandleBean;
import com.ailk.ims.common.InitBean;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.listener.action.IListenerAction;
import com.ailk.ims.xml.BaseNode;
import com.ailk.ims.xml.XStreamHolder;

/**
 * @Description
 * @author luojb
 * @Date 2011-11-1
 * @Date 2012-5-29 wuyujie 新增getServiceNode方法
 * @Date 2012-08-13 xieqr 新增getMethodNode(String serviceName, Integer busiCode) 方法
 */
public class BusiUtil
{

    private static Map<String, Class<? extends BaseBusiBean>> map_busi_beans = new HashMap<String, Class<? extends BaseBusiBean>>();
    private static Map<String, Class<? extends BaseMdbBean>> map_mdb_beans = new HashMap<String, Class<? extends BaseMdbBean>>();
    private static Map<String, Class<? extends BaseQueryBean>> map_query_beans = new HashMap<String, Class<? extends BaseQueryBean>>();
    private static Map<String, Class<? extends BaseSyncBean>> map_sync_beans = new HashMap<String, Class<? extends BaseSyncBean>>();
    private static Map<String, Class<? extends BaseTsBean>> map_ts_beans = new HashMap<String, Class<? extends BaseTsBean>>();
    private static Map<String, Class> map_flow_beans = new HashMap<String, Class>();
    private static Map<String, List<Class<IListenerAction>>> map_action_beforemdb_beans = new HashMap<String, List<Class<IListenerAction>>>();
    private static Map<String, List<Class<IListenerAction>>> map_action_aftermdb_beans = new HashMap<String, List<Class<IListenerAction>>>();
    // private static Map<Integer, String> map_busi_code_method = new HashMap<Integer, String>();
    private static Set<String> errorSet = new HashSet<String>();
    public static final String TAG_SERVICES = "services";
    public static final String TAG_SERVICE = "service";
    public static final String TAG_BUSI = "busi";
    public static final String TAG_MDB = "mdb";
    public static final String TAG_SYNC = "sync";
    public static final String TAG_QUERY = "query";
    public static final String TAG_TS = "ts";
    public static final String TAG_FLOW = "flow";
    public static final String TAG_SPEC_ID = "spec_id";
    public static final String TAG_ADD = "add";
    public static final String TAG_DELETE = "delete";
    public static final String TAG_MODIFY = "modify";
    public static final String TAG_EXTEND = "extend";
    public static final String TAG_CHGMAIN = "chgmain";
    public static final String TAG_ACTION = "action";

    public static final String ATTR_BEAN = "bean";
    public static final String ATTR_ISINNER = "isInner";
    public static final String ATTR_BUSI_CODE = "busi_code";
    public static final String ATTR_NOT_QUERY = "not_query";
    public static final String ATTR_NORMAL = "normal";
    public static final String ATTR_CANCEL = "cancel";
    public static final String ATTR_TYPE = "type";
    public static final String ATTR_ISDEFAULT = "isDefault";
    public static final String ATTR_ISNEWREG = "isNewReg";
    public static final String ATTR_URI = "uri";
    public static final String ATTR_ISRESSEV = "isResSev";
    public static final String ATTR_IS3H = "is3h";
    // 配置赠送产品订购
    public static final String ATTR_IS_REWARD_PROD_ORDER = "isRewardProdOrder";
    // 配置变更主产品标志
    public static final String ATTR_IS_CHG_MAIN_PROD = "isChgMainProd";

    public static final String PATH_CONF_BEANS = "/ims-conf/busi/busi.xml";

    private static BaseNode root;
    private static ImsLogger imsLogger=new ImsLogger(BusiUtil.class);
    public static void init(String project) throws IMSException
    {
        try
        {
            XStreamHolder holder = new XStreamHolder(TAG_SERVICES);
            root = (BaseNode) holder.parseFromFile(PATH_CONF_BEANS);

            // 加载分项目版本配置文件
            String pathConfProj = ProjectUtil.parseProjectConfPath(PATH_CONF_BEANS, project);
            imsLogger.info(">>>>>>>>project config file:" + pathConfProj);
            XStreamHolder projHolder = new XStreamHolder(TAG_SERVICES);
            BaseNode rootProj = null;
            if (CommonUtil.isValid(pathConfProj))
            {
                if (FileUtil.isFileExist(pathConfProj))
                    rootProj = (BaseNode) projHolder.parseFromFile(pathConfProj);
            }
            InitBean.parseConfProj(root, rootProj);

            int count = 0;
            List<BaseNode> children = root.getChildren();
            if (CommonUtil.isEmpty(children))
                return;
            // service级
            for (BaseNode serviceNode : children)
            {
                if (!TAG_SERVICE.equalsIgnoreCase(serviceNode.getTagName()))
                {
                    throw IMSUtil.throwBusiException("invalid node \"" + serviceNode.getTagName() + "\" under node \""
                            + TAG_SERVICES + "\". expected : " + TAG_SERVICE);
                }
                String serviceName = serviceNode.getAttribute(ATTR_BEAN);
                if (CommonUtil.isEmpty(serviceName))
                {
                    throw IMSUtil.throwBusiException("value of \"bean\" is empty for <service> node");
                }
                // 验证服务类
                Class serviceClass = Class.forName(serviceName);
                if (!IService.class.isAssignableFrom(serviceClass))
                {
                    throw IMSUtil.throwBusiException("<service bean=" + serviceName + " is not an inheritance of IService");
                }

                Method[] serviceMethods = serviceClass.getMethods();
                Set<String> methodsSet = new HashSet<String>();
                for (Method m : serviceMethods)
                {
                    methodsSet.add(m.getName());
                }
                // 验证该服务下是否有方法重复定义
                checkDuplicateMethod(serviceNode);

                // 合并方法
                if (rootProj != null)
                    InitBean.mergeChildrenByTag(serviceNode, rootProj.getChildByAttribute(ATTR_BEAN, serviceName));

                // method级
                List<BaseNode> methodNodes = serviceNode.getChildren();
                if (CommonUtil.isEmpty(methodNodes))
                    continue;

                for (BaseNode methodNode : methodNodes)
                {
                    String busiCode = methodNode.getAttribute(ATTR_BUSI_CODE);
                    String methodName = methodNode.getTagName();
                    if (busiCode == null)
                    {
                        throw IMSUtil.throwBusiException("value of \"busi_code\" is empty for <" + methodName + "> node");
                    }
                    // 验证method是否在该服务里
                    if (!methodsSet.contains(methodName))
                    {
                        imsLogger.error(
                                "method <" + methodName + "> does not exist in service [" + serviceName + "]");
                        throw IMSUtil.throwBusiException("method <" + methodName + "> does not exist in service [" + serviceName
                                + "]");
                    }

                    // BeanClass 级，method下的子节点为空，报错
                    List<BaseNode> beanNodes = methodNode.getChildren();
                    if (CommonUtil.isEmpty(beanNodes))
                    {
                        throw IMSUtil.throwBusiException("child node of node <" + methodName + "> is null.");
                    }

                    // 验证method下bean和specid的配置
                    if (checkDuplicateBeans(methodNode))
                        continue;
                    boolean isBeforeMdb = true;
                    for (BaseNode bean : beanNodes)
                    {
                        String beanType = bean.getTagName();
                        String beanClassName = bean.getAttribute(ATTR_BEAN);
                        if (TAG_SPEC_ID.equalsIgnoreCase(beanType))
                        {
                            /*
                             * if(CommonUtil.isEmpty(bean.getChildren())){
                             * System.out.println(methodName+"\t"+busiCode+"\t"+bean.getAttribute("normal")); }else{
                             * List<BaseNode> specList = bean.getChildren(); for(BaseNode spec : specList){
                             * System.out.println(methodName
                             * +"\t"+busiCode+"\t"+spec.getAttribute("normal")+"\t"+spec.getAttribute("remark")); } }
                             */

                            specIdControl(serviceName, methodName, bean);
                            continue;
                        }
                        if (CommonUtil.isEmpty(beanClassName))
                            continue;
                        beanClassName = beanClassName.trim();
                        Class beanClass = Class.forName(beanClassName);
                        String key = serviceName + "_" + methodName;
                        if (TAG_BUSI.equalsIgnoreCase(beanType))
                        {
                            if (!BaseBusiBean.class.isAssignableFrom(beanClass))
                                throw new IMSException(beanClass.getName() + " is not a subclass of "
                                        + BaseBusiBean.class.getName());
                            // checkDuplicateKey(key,map_busi_beans);
                            map_busi_beans.put(key, beanClass);
                        }
                        else if (TAG_MDB.equalsIgnoreCase(beanType))
                        {
                            if (!BaseMdbBean.class.isAssignableFrom(beanClass))
                                throw new IMSException(beanClass.getName() + " is not a subclass of "
                                        + BaseMdbBean.class.getName());
                            // checkDuplicateKey(key,map_mdb_beans);
                            map_mdb_beans.put(key, beanClass);
                            isBeforeMdb = false;// 需要更新这个值，表示已经在mdb之后了，后续解析action需要用到
                        }
                        else if (TAG_SYNC.equalsIgnoreCase(beanType))
                        {
                            if (!BaseSyncBean.class.isAssignableFrom(beanClass))
                                continue;
                            // checkDuplicateKey(key,map_sync_beans);
                            map_sync_beans.put(key, beanClass);
                        }
                        else if (TAG_TS.equalsIgnoreCase(beanType))
                        {
                            if (!BaseTsBean.class.isAssignableFrom(beanClass))
                                continue;
                            // checkDuplicateKey(key,map_sync_beans);
                            map_ts_beans.put(key, beanClass);
                        }
                        else if (TAG_FLOW.equalsIgnoreCase(beanType))
                        {
                            if (!BaseTsBean.class.isAssignableFrom(beanClass))
                                continue;
                            // checkDuplicateKey(key,map_sync_beans);
                            map_flow_beans.put(key, beanClass);
                        }
                        else if (TAG_QUERY.equalsIgnoreCase(beanType))
                        {
                            if (!BaseQueryBean.class.isAssignableFrom(beanClass))
                                throw new IMSException(beanClass.getName() + " is not a subclass of "
                                        + BaseQueryBean.class.getName());
                            // checkDuplicateKey(key,map_query_beans);
                            map_query_beans.put(key, beanClass);
                        }
                        else if (TAG_ACTION.equalsIgnoreCase(beanType))
                        {
                            if (!IListenerAction.class.isAssignableFrom(beanClass))
                                throw new IMSException(beanClass.getName() + " is not an implementation of "
                                        + IListenerAction.class.getName());
                            // checkDuplicateKey(key,map_query_beans);
                            if (isBeforeMdb)
                            {
                                List actionList = map_action_beforemdb_beans.get(key);
                                if (actionList == null)
                                {
                                    actionList = new ArrayList();
                                    map_action_beforemdb_beans.put(key, actionList);
                                }
                                actionList.add(beanClass);
                            }
                            else
                            {
                                List actionList = map_action_aftermdb_beans.get(key);
                                if (actionList == null)
                                {
                                    actionList = new ArrayList();
                                    map_action_aftermdb_beans.put(key, actionList);
                                }
                                actionList.add(beanClass);
                            }
                        }
                        else
                        {
                            throw IMSUtil.throwBusiException("unknown node <" + beanType + "> under node <" + methodName + ">");
                        }
                        imsLogger.info(
                                "*****load:[" + busiCode + "][" + methodName + "][" + beanType + "]=" + beanClassName);
                        count++;
                    }
                }
            }
            if (!errorSet.isEmpty())
            {
                StringBuffer sb = new StringBuffer("<spec_id> unconfig under the methods below:\r\n");
                for (String s : errorSet)
                {
                    sb.append(s + "\r\n");
                }
                imsLogger.error(sb.toString());
                throw IMSUtil.throwBusiException(sb.toString());
            }
            imsLogger.info("number of method：" + count);

        }
        catch (Exception e)
        {
            imsLogger.error("failed to init config file : " + PATH_CONF_BEANS);
            throw IMSUtil.throwBusiException(e);
        }

    }

    private static void checkDuplicateMethod(BaseNode serviceNode)
    {
        List<BaseNode> methodNodes = serviceNode.getChildren();
        for (BaseNode node : methodNodes)
        {
            String methodName = node.getTagName();
            List<BaseNode> controlList = serviceNode.getChildrenByTagName(methodName);
            if (controlList != null && controlList.size() > 1)
            {
                throw IMSUtil.throwBusiException("duplicate method node <" + methodName + "> under <service bean="
                        + serviceNode.getAttribute(ATTR_BEAN) + ">");
            }
        }
    }

    private static boolean checkDuplicateBeans(BaseNode methodNode)
    {
        List<BaseNode> BeansNodes = methodNode.getChildren();
        for (BaseNode node : BeansNodes)
        {
            String beanName = node.getTagName();
            if (beanName.equals(TAG_ACTION))
            {
                continue;
            }
            List<BaseNode> controlList = methodNode.getChildrenByTagName(beanName);
            if (controlList != null && controlList.size() > 1)
            {
                throw IMSUtil
                        .throwBusiException("duplicate bean node <" + beanName + "> under <" + methodNode.getTagName() + ">");
            }
        }
        BaseNode specNode = methodNode.getChildByTagName(TAG_SPEC_ID);
        if (specNode == null)
        {
            errorSet.add("<" + methodNode.getTagName() + ">");
            return true;
        }
        return false;
    }

    // private static void checkDuplicateKey(String key, Map map)
    // {
    // if (map.containsKey(key))
    // {
    // throw IMSUtil.throwBusiException("duplicate bean definition : " + key);
    // }
    // }

    public static void checkDuplicateSpecType(String serviceName, String methodName, BaseNode specIdNode)
    {
        List<BaseNode> TypeNodes = specIdNode.getChildren();
        for (BaseNode node : TypeNodes)
        {
            String type = node.getTagName();
            String normal = node.getAttribute(ATTR_NORMAL);
            // String cancel = node.getAttribute(ATTR_CANCEL);
            if (!type.startsWith(ATTR_TYPE))
            {
                throw IMSUtil.throwBusiException("<" + methodName + "> errors! the nodeName under <spec> must start with type");
            }
            String typeNumber = type.substring(4, type.length());
            try
            {
                Integer.parseInt(typeNumber);
            }
            catch (Exception e)
            {
                throw IMSUtil.throwBusiException("<" + methodName
                        + "> errors! the nodeName under <spec> must be type + an integer such as type1");
            }
            if (normal == null)
            {
                throw IMSUtil.throwBusiException("<" + methodName + "> <spec_id " + type + "normal == null!error");
            }
            // if(cancel==null){
            // throw IMSUtil.createBusiException("<"+methodName+"> <spec_id "+type+"cancel == null!error");
            // }
            List<BaseNode> controlList = specIdNode.getChildrenByTagName(type);
            if (controlList != null && controlList.size() > 1)
            {
                throw IMSUtil.throwBusiException("duplicate spec type node <" + type + "> under method <" + methodName
                        + "> of <service bean=" + serviceName + ">");
            }
        }
    }

    private static void specIdControl(String serviceName, String methodName, BaseNode specIdNode) throws Exception
    {
        List<BaseNode> children = specIdNode.getChildren();
        // 单独specId
        if (children == null)
        {
            String normal = specIdNode.getAttribute(ATTR_NORMAL);
            // String cancel = specIdNode.getAttribute(ATTR_CANCEL);
            if (normal == null)
            {
                throw IMSUtil.throwBusiException("<" + methodName + "> <spec_id  normal == null!error");
            }
            // if(cancel==null){
            // throw IMSUtil.createBusiException("<"+methodName+"> <spec_id  cancel == null!error");
            // }
            return;
        }
        // 多个specId
        checkDuplicateSpecType(serviceName, methodName, specIdNode);
        // do_query,read_imsSyncTs 特殊处理:子类bean映射
        if (methodName.equalsIgnoreCase("read_imsSyncTs") || methodName.equalsIgnoreCase("do_query"))
        {
            for (BaseNode node : children)
            {
                String bean = node.getAttribute(ATTR_BEAN);
                String type = node.getTagName();
                if (bean == null)
                {
                    throw IMSUtil.throwBusiException("<" + methodName + "> <spec_id " + type + " bean == null!error");
                }
                Class beanClass = Class.forName(bean.trim());

                if (methodName.equalsIgnoreCase("read_imsSyncTs"))
                {
                    if (!BaseTsHandleBean.class.isAssignableFrom(beanClass))
                        throw new IMSException(beanClass.getName() + " is not a subclass of " + BaseTsHandleBean.class.getName());
                    map_ts_beans.put(serviceName + "_" + methodName + "_" + node.getTagName(), beanClass);
                }
                else
                {
                    if (!BaseQueryBean.class.isAssignableFrom(beanClass))
                        throw new IMSException(beanClass.getName() + " is not a subclass of " + BaseQueryBean.class.getName());
                    map_query_beans.put(serviceName + "_" + methodName + "_" + node.getTagName(), beanClass);
                }
            }
        }
    }

    public static BaseMdbBean getMdbBean(String serviceName, String methodName) throws IMSException
    {
        try
        {
            Class beanClass = map_mdb_beans.get(serviceName + "_" + methodName);
            if (beanClass == null)
                return null;
            return (BaseMdbBean) beanClass.newInstance();
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseBusiBean getBusiBean(String serviceName, String methodName) throws IMSException
    {
        try
        {
            Class beanClass = map_busi_beans.get(serviceName + "_" + methodName);
            if (beanClass == null)
                return null;
            return (BaseBusiBean) beanClass.newInstance();
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseBusiBean getBusiBean(int busiCode) throws IMSException
    {
        try
        {
            BaseNode node = getMethodNode(busiCode);
            if (node == null)
                return null;
            BaseNode busiNode = node.getChildByTagName(BusiUtil.TAG_BUSI);
            if (busiNode == null)
                return null;
            String className = busiNode.getAttribute(BusiUtil.ATTR_BEAN);
            return (BaseBusiBean) ClassUtil.instance(className);
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseMdbBean getMdbBean(int busiCode) throws IMSException
    {
        try
        {
            BaseNode node = getMethodNode(busiCode);
            if (node == null)
                return null;
            BaseNode busiNode = node.getChildByTagName(BusiUtil.TAG_MDB);
            if (busiNode == null)
                return null;
            String className = busiNode.getAttribute(BusiUtil.ATTR_BEAN);
            return (BaseMdbBean) ClassUtil.instance(className);
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseTsBean getTsBean(int busiCode) throws IMSException
    {
        try
        {
            BaseNode node = getMethodNode(busiCode);
            if (node == null)
                return null;
            BaseNode busiNode = node.getChildByTagName(BusiUtil.TAG_TS);
            if (busiNode == null)
                return null;
            String className = busiNode.getAttribute(BusiUtil.ATTR_BEAN);
            return (BaseTsBean) ClassUtil.instance(className);
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseSyncBean getSyncBean(String serviceName, String methodName) throws IMSException
    {
        try
        {
            Class beanClass = map_sync_beans.get(serviceName + "_" + methodName);
            if (beanClass == null)
                return null;
            return (BaseSyncBean) beanClass.newInstance();
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseTsBean getTsBean(String serviceName, String methodName) throws IMSException
    {
        try
        {
            Class beanClass = map_ts_beans.get(serviceName + "_" + methodName);
            if (beanClass == null)
                return null;
            return (BaseTsBean) beanClass.newInstance();
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    private static BaseQueryBean getQueryBean(String serviceName, String methodName, Short type) throws IMSException
    {
        try
        {
            String key = serviceName + "_" + methodName;
            if (type != null)
            {
                key = key + "_" + parseSpecType(type);
            }
            Class beanClass = map_query_beans.get(key);
            if (beanClass == null)
                return null;
            return (BaseQueryBean) beanClass.newInstance();
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseTsHandleBean getTsHandleBean(String serviceName, String methodName, Integer type) throws IMSException
    {
        try
        {
            String key = serviceName + "_" + methodName;
            if (type != null)
            {
                key = key + "_" + parseSpecType(type);
            }
            Class beanClass = map_ts_beans.get(key);
            if (beanClass == null)
                return null;
            return (BaseTsHandleBean) beanClass.newInstance();
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseQueryBean getQueryBean(String serviceName, String methodName) throws IMSException
    {
        return getQueryBean(serviceName, methodName);
    }

    public static BaseQueryBean getQueryTypeBean(String serviceName, String methodName, Short type) throws IMSException
    {

        return getQueryBean(serviceName, methodName, type);
    }

    public static Integer getSpecId(String serviceName, String methodName, boolean isNormal, Integer type) throws IMSException
    {
        BaseNode methodNode = getMethodNode(serviceName, methodName);
        BaseNode specNode = methodNode.getChildByTagName(TAG_SPEC_ID);
        String attr = null;
        Integer specId = null;
        if (isNormal)
        {
            attr = ATTR_NORMAL;
        }
        else
        {
            attr = ATTR_CANCEL;
        }
        if (type == null)
        {
            specId = getDefaultSpecId(specNode, attr);
        }
        else
        {
            String attrType = parseSpecType(type);
            specId = getSpecId(specNode, attr, attrType);
        }

        return specId;
    }

    private static Integer getSpecId(BaseNode specNode, String attr, String attrType)
    {
        BaseNode specTypeNode = specNode.getChildByTagName(attrType);
        if (specTypeNode == null)
        {
            return null;
        }
        return Integer.parseInt(specTypeNode.getAttribute(attr));

    }

    private static Integer getDefaultSpecId(BaseNode specNode, String attr)
    {
        BaseNode defaultSpecNode = specNode.getChildByAttribute(ATTR_ISDEFAULT, "true");
        if (defaultSpecNode == null)
        {
            String specId = specNode.getAttribute(attr);
            if (specId == null)
            {
                specId = specNode.getChildren().get(0).getAttribute(attr);
            }
            return Integer.parseInt(specId);
        }
        return Integer.parseInt(defaultSpecNode.getAttribute(attr));
    }

    private static String parseSpecType(int type)
    {
        return "type" + type;
    }

    public static BaseNode getServiceNode(String serviceName)
    {
        BaseNode serviceNode = root.getChildByAttribute(ATTR_BEAN, serviceName);

        return serviceNode;
    }

    public static BaseNode getMethodNode(String serviceName, String methodName)
    {
        BaseNode serviceNode = root.getChildByAttribute(ATTR_BEAN, serviceName);
        if (serviceNode == null)
        {
            return null;
        }
        return serviceNode.getChildByTagName(methodName);
    }

    public static BaseNode getMethodNode(int busiCode)
    {
        List<BaseNode> services = root.getChildren();
        if (CommonUtil.isEmpty(services))
            return null;
        String busiCodeStr = String.valueOf(busiCode);
        for (BaseNode service : services)
        {
            List<BaseNode> methods = service.getChildren();
            if (CommonUtil.isEmpty(methods))
                continue;
            for (BaseNode method : methods)
            {
                if (busiCodeStr.equals(method.getAttribute(ATTR_BUSI_CODE)))
                {
                    return method;
                }
            }
        }
        return null;
    }

    public static BaseNode getMethodNode(String serviceName, Integer busiCode)
    {
        BaseNode serviceNode = root.getChildByAttribute(ATTR_BEAN, serviceName);
        if (serviceNode == null)
        {
            return null;
        }
        List<BaseNode> methods = serviceNode.getChildren();
        if (CommonUtil.isEmpty(methods))
            return null;
        String busiCodeStr = String.valueOf(busiCode);
        for (BaseNode method : methods)
        {
            if (busiCodeStr.equals(method.getAttribute(ATTR_BUSI_CODE)))
            {
                return method;
            }
        }
        return null;
    }

    public static String getBusiBeanPath(int busiCode)
    {
        BaseNode node = getMethodNode(busiCode);

        if (node != null)
        {
            BaseNode childNode = node.getChildByTagName(TAG_BUSI);
            String path = childNode.getAttribute(ATTR_BEAN);
            return path;
        }

        return null;
    }

    public static String getBusiXML()
    {
        return new XStreamHolder(TAG_SERVICES).parse2Xml(root);
    }

    public static List<Class<IListenerAction>> getBeforeMdbActionList(String serviceName, String methodName) throws IMSException
    {
        try
        {
            List<Class<IListenerAction>> classList = map_action_beforemdb_beans.get(serviceName + "_" + methodName);
            if (CommonUtil.isEmpty(classList))
                return null;
            return classList;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static List<Class<IListenerAction>> getAfterMdbActionList(String serviceName, String methodName) throws IMSException
    {
        try
        {
            List<Class<IListenerAction>> classList = map_action_aftermdb_beans.get(serviceName + "_" + methodName);
            if (CommonUtil.isEmpty(classList))
                return null;
            return classList;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static BaseNode getMethodNodeByBusiCode(Integer busiCode) throws IMSException
    {
        if (busiCode == null)
            return null;
        List<BaseNode> services = root.getChildren();
        if (CommonUtil.isEmpty(services))
            return null;
        for (BaseNode service : services)
        {
            List<BaseNode> methods = service.getChildren();
            if (CommonUtil.isEmpty(methods))
                continue;
            for (BaseNode method : methods)
            {
                Integer attrBusiCode = method.getIntAttribute(ATTR_BUSI_CODE);
                if (attrBusiCode != null && busiCode.intValue() == attrBusiCode)
                    return method;
            }
        }
        return null;
    }

    public static Integer getSpecIdByBusiCode(Integer busiCode, Integer type)
    {
        BaseNode methodNode = getMethodNodeByBusiCode(busiCode);
        if (methodNode == null)
            return null;
        return getSpecId(methodNode.getParent().getAttribute(ATTR_BEAN), methodNode.getTagName(), true, type);
    }
}
