package com.ailk.ims.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.xml.BaseNode;

/**
 * 关联刷新表字段关系
 * 
 * @Description
 * @author luojb
 * @Date 2012-2-23
 */
public class DBRelInitBean implements IImsConfigInit
{
    private static Map<Class<? extends DataObject>, List<BaseNode>> db_rel_config;
    public static String ATTR_ROOT = "root";
    public static String ATTR_REL = "rel";
    public static String ATTR_RELVALUE = "relValue";
    public static String ATTR_ROOTVALUE = "rootValue";
    public static String ATTR_TYPE = "type";

    private static String TAG_TABLE = "table";
    private static String TAG_FIELD = "field";

    public static String TYPE_MANY_2_MANY = "many-2-many";
    public static String TYPE_MANY_2_ONE = "many-2-one";
    public static String TYPE_ONE_2_ONE = "one-2-one";
    public static String TYPE_ONE_2_MANY = "one-2-many";

    public void mergeNodes(BaseNode node, BaseNode nodeProj) throws Exception
    {
        // 关联刷新表字段映射的config项，直接替换
        if (nodeProj != null)
        {
            node = nodeProj;
        }
    }

    public void init(BaseNode node)
    {
        db_rel_config = new HashMap<Class<? extends DataObject>, List<BaseNode>>();
        List<BaseNode> tableNodes = node.getChildren();
        if (CommonUtil.isEmpty(tableNodes))
            return;
        for (BaseNode table : tableNodes)
        {
            Class<? extends DataObject> rootClass = getClass(table.getAttribute(ATTR_ROOT));
            Class<? extends DataObject> relClass = getClass(table.getAttribute(ATTR_REL));

            List<BaseNode> tableList = db_rel_config.get(rootClass);
            if (tableList == null)
            {
                tableList = new ArrayList<BaseNode>();
                db_rel_config.put(rootClass, tableList);
            }
            tableList.add(table);

            tableList = db_rel_config.get(relClass);
            if (tableList == null)
            {
                tableList = new ArrayList<BaseNode>();
                db_rel_config.put(relClass, tableList);
            }
            tableList.add(swap(table));
        }
    }

    private static Class<? extends DataObject> getClass(String className)
    {
        Class<DataObject> dmClass = null;
        try
        {
            dmClass = (Class<DataObject>) Class.forName(className);
        }
        catch (ClassNotFoundException e)
        {
            IMSUtil.throwBusiException(className + " is not a DataObject");
        }
        return dmClass;
    }

    public static Class<? extends DataObject> getRootClass(BaseNode node)
    {
        return getClass(node.getAttribute(ATTR_ROOT));
    }

    public static Class<? extends DataObject> getRelClass(BaseNode node)
    {
        return getClass(node.getAttribute(ATTR_REL));
    }

    private static BaseNode swap(BaseNode node)
    {
        String root = node.getAttribute(ATTR_ROOT);
        String rel = node.getAttribute(ATTR_REL);

        // String relValue = node.getAttribute(ATTR_RELVALUE);
        // String rootValue = node.getAttribute(ATTR_ROOTVALUE);

        String type = node.getAttribute(ATTR_TYPE);

        BaseNode newTable = new BaseNode(TAG_TABLE);
        Map<String, String> newTableAttrs = new HashMap<String, String>();
        newTableAttrs.put(ATTR_ROOT, rel);
        newTableAttrs.put(ATTR_REL, root);
        if (TYPE_MANY_2_MANY.equals(type) || TYPE_ONE_2_ONE.equals(type))
        {
            newTableAttrs.put(ATTR_TYPE, type);
        }
        else if (TYPE_MANY_2_ONE.equals(type))
        {
            newTableAttrs.put(ATTR_TYPE, TYPE_ONE_2_MANY);
        }
        else if (TYPE_ONE_2_MANY.equals(type))
        {
            newTableAttrs.put(ATTR_TYPE, TYPE_MANY_2_ONE);
        }

        newTable.setAttributes(newTableAttrs);

        if (!CommonUtil.isEmpty(node.getChildren()))
        {
            List<BaseNode> fields = node.getChildren();
            List<BaseNode> newFields = new ArrayList<BaseNode>();
            for (BaseNode f : fields)
            {
                BaseNode newField = new BaseNode(TAG_FIELD);
                Map<String, String> newFieldAttrs = new HashMap<String, String>();
                String root_f = f.getAttribute(ATTR_ROOT);
                String rel_f = f.getAttribute(ATTR_REL);
                String root_v = f.getAttribute(ATTR_ROOTVALUE);
                String rel_v = f.getAttribute(ATTR_RELVALUE);

                if (CommonUtil.isNotEmpty(root_f) && CommonUtil.isNotEmpty(rel_f))
                {
                    newFieldAttrs.put(ATTR_ROOT, rel_f);
                    newFieldAttrs.put(ATTR_REL, root_f);
                }
                else if (CommonUtil.isNotEmpty(root_f) && CommonUtil.isNotEmpty(root_v))
                {
                    newFieldAttrs.put(ATTR_REL, root_f);
                    newFieldAttrs.put(ATTR_RELVALUE, root_v);
                }
                else if (CommonUtil.isNotEmpty(rel_f) && CommonUtil.isNotEmpty(rel_v))
                {
                    newFieldAttrs.put(ATTR_ROOT, rel_f);
                    newFieldAttrs.put(ATTR_ROOTVALUE, rel_v);
                }

                // newFieldAttrs.put(ATTR_ROOT, f.getAttribute(ATTR_REL));
                // newFieldAttrs.put(ATTR_REL, f.getAttribute(ATTR_ROOT));
                newField.setAttributes(newFieldAttrs);
                newFields.add(newField);
            }
            newTable.setChildren(newFields);
        }

        return newTable;
    }

    public static List<BaseNode> getRelTables(Class<? extends DataObject> dmClass)
    {
        if (CommonUtil.isEmpty(db_rel_config))
            return null;
        return db_rel_config.get(dmClass);
    }

    /**
     * @Description: 判断两张表是否能直接关联
     * @param from
     * @param target
     * @return
     */
    public static boolean canDirectRel(Class<? extends DataObject> from, Class<? extends DataObject> target)
    {
        List<BaseNode> list = getRelTables(from);
        if (CommonUtil.isEmpty(list))
            return false;
        for (BaseNode node : list)
        {
            if (getClass(node.getAttribute(ATTR_REL)) == target)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @Description: 获取从一张表到另一张表之间的关联路径，因为有可能是间接关联，比如cm_customer和cm_party
     * @param from
     * @param to
     * @return
     */
    public static List<Class> getRelPath(Class from, Class to)
    {
        List<BaseNode> tables = getRelTablePath(from, to);
        if (CommonUtil.isEmpty(tables))
            return null;
        List<Class> result = new ArrayList();
        for (BaseNode node : tables)
        {
            result.add(getClass(node.getAttribute(ATTR_ROOT)));
        }
        return result;

    }

    public static List<BaseNode> getRelTablePath(Class from, Class to)
    {
        return getRelTablePath(from, to, new ArrayList());
    }

    private static List<BaseNode> getRelTablePath(Class from, Class to, List<Class> filter)
    {
        List<BaseNode> rels = getRelTables(from);// 获取和出发表所有关联的表
        if (CommonUtil.isEmpty(rels))
            return null;

        List<List<BaseNode>> temp = new ArrayList<List<BaseNode>>();

        for (BaseNode rel : rels)
        {
            List<BaseNode> path = new ArrayList<BaseNode>();
            Class relClass = getClass(rel.getAttribute(ATTR_REL));
            if (CommonUtil.isIn(relClass, filter))
            {
                continue;
            }
            path.add(rel);
            if (relClass != to)
            {
                filter.add(from);
                List<BaseNode> sub = getRelTablePath(relClass, to, filter);
                if (CommonUtil.isEmpty(sub))
                    continue;//
                path.addAll(sub);
            }
            temp.add(path);
        }
        List<BaseNode> result = null;
        for (List<BaseNode> path : temp)
        {
            if (result == null || path.size() < result.size())
            {
                result = path;
            }
        }
        return result;
    }

    /**
     * @Description: 从给定的表中，找出一条最优路径关联目标表，默认策略是间隔的表数最少
     * @param target,目标表
     * @param targets，给定的表列表
     * @return
     */
    public static List<Class> getOptimizeRelPath(List<Class> existList, Class target)
    {
        List<BaseNode> tables = getOptimizeRelTablePath(existList, target);
        if (CommonUtil.isEmpty(tables))
            return null;
        List<Class> result = new ArrayList();
        for (BaseNode node : tables)
        {
            result.add(getClass(node.getAttribute(ATTR_ROOT)));
        }
        return result;
    }

    public static List<BaseNode> getOptimizeRelTablePath(List<Class> existList, Class target)
    {
        List<BaseNode> result = null;
        for (Class exist : existList)
        {
            if (exist == target)
                continue;
            List<BaseNode> path = getRelTablePath(exist, target);
            if (CommonUtil.isEmpty(path))
                continue;
            if (result == null)
            {
                result = path;
            }
            else if (path.size() < result.size())
            {
                result = path;
            }
        }
        return result;
    }

    public static List<DBCondition> getRootDBConditions(List<BaseNode> fields, DataObject rootObj)
    {
        List<DBCondition> result = new ArrayList<DBCondition>();
        try
        {
            for (int i = 0; i < fields.size(); i++)
            {
                BaseNode fNode = fields.get(i);
                if (CommonUtil.isEmpty(fNode.getAttribute(ATTR_ROOT)))
                    continue;
                String root_f_name = CommonUtil.parse2JavaName(fNode.getAttribute(ATTR_ROOT), false);

                Object root_f_value = ClassUtil.getPropertyValue(rootObj, root_f_name);
                result.add(new DBCondition(DBUtil.getJefField(rootObj.getClass(), root_f_name), root_f_value));
            }
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
        return result;
    }

    /**
     * @Description: 根据前一个对象列表，构造关联对象的条件
     * @param node
     * @param rootList
     * @return
     */
    public static List<DBCondition> buildRelConditions(BaseNode node, List<DataObject> rootList)
    {
        try
        {
            List<DBCondition> result = new ArrayList<DBCondition>();
            List<BaseNode> fields = node.getChildren();
            for (int i = 0; i < fields.size(); i++)
            {
                BaseNode fNode = fields.get(i);
                String fName = fNode.getAttribute(ATTR_REL);
                if (CommonUtil.isEmpty(fName))
                    continue;
                String rel_f_name = CommonUtil.parse2JavaName(fName, false);

                jef.database.Field ref_field = DBUtil.getJefField(getClass(node.getAttribute(ATTR_REL)), rel_f_name);
                if (ref_field == null)
                {
                    IMSUtil.throwBusiException("field [" + fNode.getAttribute(ATTR_REL) + "] not found in Struct ["
                            + node.getAttribute(ATTR_REL) + "]");
                }
                Operator oper = null;
                Object value = null;

                if (fNode.getAttribute(ATTR_RELVALUE) != null)
                {
                    value = fNode.getAttribute(ATTR_RELVALUE);
                    result.add(new DBCondition(ref_field, value, oper));
                    continue;
                }

                Vector<Object> ref_field_values = new Vector<Object>();

                for (DataObject root : rootList)
                {
                    String root_f_name = CommonUtil.parse2JavaName(fNode.getAttribute(ATTR_ROOT), false);
                    Object root_f_value = ClassUtil.getPropertyValue(root, root_f_name);
                    if (!ref_field_values.contains(root_f_value))
                        ref_field_values.add(root_f_value);
                }
                if (ref_field_values.size() > 1)
                {
                    oper = Operator.IN;
                    String[] arrValue = new String[ref_field_values.size()];
                    for (int k = 0; k < ref_field_values.size(); k++)
                    {
                        arrValue[k] = ref_field_values.get(k).toString();
                    }
                    value = arrValue;
                }
                else
                {

                    value = ref_field_values.get(0);
                }
                result.add(new DBCondition(ref_field, value, oper));
            }
            return result;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static DataObject instanceRelObject(BaseNode node, DataObject rootObj)
    {
        try
        {
            List<BaseNode> fList = node.getChildren();
            List<DBCondition> values = getRootDBConditions(fList, rootObj);// 获取主对象的字段值
            return instanceRelObject(node, values);
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static DataObject instanceRelObject(BaseNode node, List<DBCondition> values)
    {
        try
        {
            Class<? extends DataObject> relClass = getClass(node.getAttribute(ATTR_REL));
            DataObject relObj = relClass.newInstance();

            List<BaseNode> fields = node.getChildren();
            for (int i = 0; i < fields.size(); i++)
            {
                String rel_f_name = CommonUtil.parse2JavaName(fields.get(i).getAttribute(ATTR_REL), false);
                ClassUtil.setFieldValue(relObj, rel_f_name, values.get(i).getValue());// 主对象的字段值设置到对应的关联对象字段中
            }
            return relObj;
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static String getStringPath(List<BaseNode> path)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(path.get(0).getAttribute(ATTR_ROOT)).append(" " + getStringRelFields(path.get(0)) + " ")
                .append(path.get(0).getAttribute(ATTR_REL));
        for (int i = 1; i < path.size(); i++)
        {
            BaseNode node = path.get(i);
            sb.append(" " + getStringRelFields(node) + " ").append(path.get(i).getAttribute(ATTR_REL));
        }
        return sb.toString();
    }

    public static String getStringRelFields(BaseNode node)
    {
        StringBuffer sb = new StringBuffer("<");
        List<BaseNode> fields = node.getChildren();
        for (int i = 0; i < fields.size(); i++)
        {
            BaseNode f = fields.get(i);
            if (i != 0)
                sb.append(",");
            sb.append(f.getAttribute(ATTR_ROOT)).append("=").append(f.getAttribute(ATTR_REL));
        }
        return sb.append(">").toString();
    }

    public static String getStringCondition(DataObject dm)
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            java.lang.reflect.Field[] fs = ClassUtil.getFields(dm.getClass());
            for (java.lang.reflect.Field f : fs)
            {
                Object value = ClassUtil.getPropertyValue(dm, f);
                if (value == null)
                    continue;
                sb.append(",").append(f.getName()).append("=").append(value.toString());
            }
            return sb.substring(1);
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

    public static String getStringCondition(List<DBCondition> conds)
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            for (DBCondition cond : conds)
            {
                sb.append(",").append(cond.toString());
            }
            return sb.substring(1);
        }
        catch (Exception e)
        {
            throw new IMSException(e);
        }
    }

}
