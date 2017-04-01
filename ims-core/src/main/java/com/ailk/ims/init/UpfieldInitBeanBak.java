package com.ailk.ims.init;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import jef.database.DataObject;
import com.ailk.ims.common.InitBean;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.xml.BaseNode;

/**
 * @Description MDB异步上发关联字段初始化类
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author lijingcheng
 * @Date 2012-2-23
 */
public class UpfieldInitBeanBak implements IImsConfigInit
{
    private static String ATTR_BEAN = "bean";
    private static String TAG_TABLE = "table";
    private static String TAG_FIELD = "field";

    private static LinkedHashMap<Class<? extends DataObject>, List<String>> config = new LinkedHashMap<Class<? extends DataObject>, List<String>>();
    protected ImsLogger logger = new ImsLogger(getClass());

    public void mergeNodes(BaseNode node, BaseNode nodeProj) throws Exception
    {
        // 合并项
        if (nodeProj != null && CommonUtil.isNotEmpty(nodeProj.getChildren()))
        {
            InitBean.mergeChildrenByAttr(node, nodeProj, ATTR_BEAN);
        }
    }

    public void init(BaseNode node)
    {
        try
        {
            List<BaseNode> tables = node.getChildrenByTagName(TAG_TABLE);
            if (CommonUtil.isEmpty(tables))
            {
                throw IMSUtil.throwBusiException("no <table> tag under <config " + node.getAttribute(ATTR_BEAN) + ">");
            }
            for (BaseNode tableNode : tables)
            {
                List<String> fields = new ArrayList<String>();
                String key = tableNode.getAttribute(ATTR_BEAN);
                logger.info("****** load table :", key);
                Class clazz = Class.forName(key);
                List<BaseNode> fieldNodes = tableNode.getChildrenByTagName(TAG_FIELD);
                if (CommonUtil.isEmpty(fieldNodes))
                {
                    throw IMSUtil.throwBusiException("no <field> tag under <table> tag");
                }
                for (BaseNode fieldNode : fieldNodes)
                {
                    logger.info("****** ****** up field :", fieldNode.getText());
                    fields.add(fieldNode.getText());
                }
                config.put(clazz, fields);
            }
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    public static List<Class<? extends DataObject>> getUpFieldClasses()
    {
        Iterator it = config.keySet().iterator();
        List<Class<? extends DataObject>> result = new ArrayList<Class<? extends DataObject>>();
        while (it.hasNext())
        {
            result.add((Class) it.next());
        }
        return result;
    }

    public static List<String> getUpFields(Class<? extends DataObject> clazz)
    {
        return config.get(clazz);
    }

    public static List<Class<? extends DataObject>> parseUpField(String upfield)
    {
        int count = upfield.length();
        List<Class<? extends DataObject>> result = new ArrayList<Class<? extends DataObject>>();
        List<Class<? extends DataObject>> upClasses = getUpFieldClasses();
        for (int i = 0; i < count; i++)
        {
            char flag = upfield.charAt(i);
            if (flag == '1')
            {
                result.add(upClasses.get(i));
            }
        }
        return result;
    }

}
