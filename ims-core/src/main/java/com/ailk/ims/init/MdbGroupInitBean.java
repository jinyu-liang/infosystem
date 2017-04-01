package com.ailk.ims.init;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ailk.ims.common.InitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.xml.BaseNode;

/**
 * @Description MDB上发关联刷新组初始化类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lijingcheng
 * @Date 2012-2-23
 */
public class MdbGroupInitBean implements IImsConfigInit
{
    private static String ATTR_BEAN = "bean";
    private static String TAG_GROUP = "group";
    private static String TAG_TABLES = "tables";
    private static String TAG_TABLE = "table";
    private static String ATTR_CODE = "code";

    public static final String DEFAULT_KEY = "*";
    private static Map<String, List<Class[]>> config = new LinkedHashMap<String, List<Class[]>>();
    private ImsLogger imsLogger=new ImsLogger(this.getClass());
    public void mergeNodes(BaseNode node, BaseNode nodeProj) throws Exception
    {
        // 合并项
        if (nodeProj != null && CommonUtil.isNotEmpty(nodeProj.getChildren()))
        {
            InitBean.mergeChildrenByAttr(node, nodeProj, ATTR_CODE);
        }

    }

    public void init(BaseNode node)
    {
        try
        {
            List<BaseNode> groups = node.getChildrenByTagName(TAG_GROUP);
            if (CommonUtil.isEmpty(groups))
            {
                throw IMSUtil.throwBusiException("no <table> tag under <config " + node.getAttribute(ATTR_BEAN) + ">");
            }
            for (BaseNode groupNode : groups)
            {
                String strKey = groupNode.getAttribute(ATTR_CODE);
                List<BaseNode> tablesList = groupNode.getChildrenByTagName(TAG_TABLES);
                if (CommonUtil.isEmpty(tablesList))
                {
                    continue;// throw IMSUtil.throwBusiException("no <tables> tag under <group> tag");
                }
                for (BaseNode tbs : tablesList)
                {
                    List<BaseNode> tables = tbs.getChildrenByTagName(TAG_TABLE);
                    if (CommonUtil.isEmpty(tablesList))
                    {
                        throw IMSUtil.throwBusiException("no <table> tag under <table> tag");
                    }
                    Class[] clazzes = new Class[tables.size()];
                    for (int i = 0; i < tables.size(); i++)
                    {
                        String classValue = ((BaseNode) tables.get(i)).getText();
                        Class clazz = Class.forName(classValue);
                        imsLogger.info("*****load:[" + strKey + "]=" + classValue);
                        clazzes[i] = clazz;
                    }

                    List<Class[]> list = config.get(strKey);
                    if (list == null)
                    {
                        list = new ArrayList<Class[]>();
                        config.put(strKey, list);
                    }
                    list.add(clazzes);
                }
            }

        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    /**
     * @Description: 获取特定业务的关联组
     * @param soNbr
     * @return
     */
    public static List<Class[]> getRelGroups(int busiCode)
    {
        String key = String.valueOf(busiCode);

        if (!config.containsKey(key))
        {
            return config.get(DEFAULT_KEY);// 没有对应的健值，则取默认
        }

        return config.get(key);
    }

}
