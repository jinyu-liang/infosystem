package com.ailk.ims.init;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import jef.common.log.Logger;
import jef.database.DataObject;
import com.ailk.easyframe.web.common.dal.IComplexEntity;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.IMSContext;
import com.ailk.ims.common.InitBean;
import com.ailk.ims.define.EnumCodeDefine;
import com.ailk.ims.define.ErrorCodeDefine;
import com.ailk.ims.define.SysCodeDefine;
import com.ailk.ims.exception.IMSException;
import com.ailk.ims.util.ClassUtil;
import com.ailk.ims.util.CommonUtil;
import com.ailk.ims.util.DBUtil;
import com.ailk.ims.util.IMSUtil;
import com.ailk.ims.util.ImsLogger;
import com.ailk.ims.util.LogUtil;
import com.ailk.ims.util.SysUtil;
import com.ailk.ims.xml.BaseNode;
import com.ailk.openbilling.persistence.acct.entity.CaAccount;
import com.ailk.openbilling.persistence.cust.entity.CmCustomer;
import com.ailk.openbilling.persistence.cust.entity.CmIdMapping;

/**
 * 内外id映射
 * 
 * @Description
 * @author luojb
 * @Date 2012-2-23
 * @Date 2012-3-29 wangjt:修改isEmpty方法用来判断对象的问题
 */
public class IdMapInitBean implements IImsConfigInit
{
    private static final Pattern p1 = Pattern.compile("\\w*cust_id$");
    private static final Pattern p2 = Pattern.compile("\\w*acct_id$");
    private static final Pattern p3 = Pattern.compile("\\w*group_id$");

    private static Map<String, List<BaseNode>> idmap_config;
    private static Map<Integer, Class<? extends DataObject>> map_sequence;

    public static final String ATTR_CODE = "code";
    public static final String ATTR_CLASSNAME = "className";
    public static final String ATTR_OUTER = "outer";
    public static final String ATTR_INNER = "inner";
    public static final String ATTR_TYPE = "type";
    public static final String ATTR_DBFIELD = "dbfield";
    public static final String ATTR_ISPRIMARY = "isPrimary";
    private static ImsLogger imsLogger=new ImsLogger(IdMapInitBean.class);

    // 内外部id是否相同
    private static boolean innerOuterIdEqual;

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
        innerOuterIdEqual = SysUtil.getBoolean(SysCodeDefine.busi.INNER_OUTER_ID_EQUAL);

        map_sequence = new HashMap<Integer, Class<? extends DataObject>>();
        map_sequence.put(EnumCodeDefine.ID_MAP_TYPE_CUSTID, CmCustomer.class);
        map_sequence.put(EnumCodeDefine.ID_MAP_TYPE_ACCTID, CaAccount.class);
        map_sequence.put(EnumCodeDefine.ID_MAP_TYPE_GROUPID, CaAccount.class);

        if (node == null)
            return;
        idmap_config = new HashMap<String, List<BaseNode>>();
        List<BaseNode> busiNodes = node.getChildren();
        for (BaseNode busi : busiNodes)
        {
            List<BaseNode> structList = busi.getChildren();
            idmap_config.put(busi.getAttribute(ATTR_CODE), structList);
        }

    }

    public static void mapping(String moduleCode, IComplexEntity[]... structs) throws IMSException
    {
        if (idmap_config == null)
        {
            return;
        }

        List<BaseNode> structNodeList = idmap_config.get(moduleCode);
        if (CommonUtil.isEmpty(structNodeList))
        {
            throw new IMSException("can not find id map module with code : \"" + moduleCode + "\"");
        }

        try
        {
            // Map<String, Boolean> exist_info = new HashMap<String,
            // Boolean>();// 存放某个id在数据库是否已经存在
            // 存放外部id和内部id之间的映射，key是type_outerid组成,比如cust_10002
            // user有些特殊，是存放手机号码与内部resource_id之间的映射
            Map<String, Long> id_mapping = new HashMap<String, Long>();

            // 循环每个传入的数组
            for (IComplexEntity[] structArr : structs)
            {
                if (CommonUtil.isEmpty(structArr))
                    continue;
                BaseNode structNode = getStructNode(structNodeList, structArr[0].getClass());
                if (structNode == null || CommonUtil.isEmpty(structNode.getChildren()))
                    continue;

                imsLogger.info("****** begin to mapping struct : " + structArr[0].getClass());

                // 循环数组中的每个输入参数结构
                for (IComplexEntity structInput : structArr)
                {
                    mappingStruct(structInput, structNode, id_mapping);
                }
            }
        }
        catch (Exception e)
        {
            throw IMSUtil.throwBusiException(e);
        }
    }

    private static void mappingStruct(IComplexEntity structInput, BaseNode structNode, Map<String, Long> id_mapping)
            throws Exception
    {
        String structName = structInput.getClass().getSimpleName();
        List<BaseNode> mapList = structNode.getChildren();
        if (CommonUtil.isEmpty(mapList))
            return;
        // 循环配置节点里的每一个匹配规则
        for (BaseNode mapNode : mapList)
        {
            String outer = mapNode.getAttribute(ATTR_OUTER);
            String inner = mapNode.getAttribute(ATTR_INNER);
            int type = mapNode.getIntAttribute(ATTR_TYPE);
            boolean isPrimary = mapNode.getBooleanAttribute(ATTR_ISPRIMARY,false);

            String outerId = (String) ClassUtil.getPropertyValue(structInput, outer);
            Long innerId = (Long) ClassUtil.getPropertyValue(structInput, inner);

            imsLogger.info(
                    "****** begin to mapping id in " + structName + ": type=" + type + " , outer=" + outer + "[" + outerId
                            + "] , inner=" + inner + "[" + innerId + "],isPrimary=" + isPrimary);

            if (isPrimary)
            {
                // 主键字段
                if (innerId == null)
                {
                    // 如果inner_id没值，则需要自己生成
                    innerId = DBUtil.getSequence(map_sequence.get(type));
                }
                else
                {
                    // 首先在当前请求中判断同一类型是否有重复的id
                    if (id_mapping.get(type + "_" + innerId) != null)
                    {
                        throw IMSUtil.throwBusiException(ErrorCodeDefine.IDMAPPING_ID_EXIST_REQUEST, inner + " : \"" + innerId
                                + "\"", structName);
                    }

                    // 需要判断传进来的内部id是否在当前数据库中已经存在了
                    // 进入到这个分支，一般是is_equal=true

                    // group_id取配置的表中对应的字段名
                    String dbfield = mapNode.getAttribute(ATTR_DBFIELD);
                    if (dbfield == null)
                        dbfield = inner;
                    IdMapInitBean.checkInnerIdExist(type, innerId, dbfield, structName);
                    id_mapping.put(type + "_" + innerId, innerId);
                }

                if (!CommonUtil.isEmpty(outerId) && !innerOuterIdEqual)
                {
                    // 如果外部id有值且配置成内外id不一致，需要验证外部id唯一性，且创建映射关系
                    checkOuterIdExist(type, outerId, outer, structName);
                    insertIdMapping(type, outerId, innerId);
                    id_mapping.put(type + "_" + outerId, innerId);
                }

                imsLogger.debug(
                        "****** create id mapping : type=" + type + ",outerId=" + outerId + ",innerId=" + innerId);
            }
            else
            {
                // 非主键映射，如果外部id有值，并且内部id无值，则需要在当前缓存中查找
                if (CommonUtil.isNotEmpty(outerId) && innerId == null)
                {
                    // 则需要到id_mapping中查找了
                    innerId = id_mapping.get(type + "_" + outerId);
                    if (innerId == null)
                    {
                        // 在缓存中也找不到，直接报错
                        IMSUtil.throwBusiException(ErrorCodeDefine.IDMAPPING_INNERID_CANNOT_MAPPING_BYOUTERID, inner, outer + "["
                                + outerId + "]", structName

                        );
                    }
                }
            }

            imsLogger.debug(
                    "****** id mapping in " + structName + " result : type=" + type + " , outer=" + outer + "[" + outerId
                            + "] , inner=" + inner + "[" + innerId + "]");
            ClassUtil.setFieldValue(structInput, inner, innerId);
        }
    }

    /**
     * 检测内部id是否已经存在，存在则抛异常.到各自对应的业务表中查找
     * 
     * @author : wuyj
     * @date : 2011-12-8
     */
    private static void checkInnerIdExist(int type, Long innerId, String inner, String structName) throws Exception
    {
        Class<? extends DataObject> tableClass = map_sequence.get(type);
        // DataObject dataObj = tableClass.newInstance();
        // ClassUtil.setFieldValue(dataObj, CommonUtil.parse2JavaName(inner, false), innerId);
        DataObject result = DBUtil.querySingle(tableClass,
                new DBCondition(DBUtil.getJefField(tableClass, CommonUtil.parse2JavaName(inner, false)), innerId));
        if (result != null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.IDMAPPING_ID_EXIST_DB, inner + " : \"" + innerId + "\"", structName);
        }
    }

    /**
     * 检测外部id是否存在，存在则抛异常。到cm_id_mapping检测。
     * 
     * @author : wuyj
     * @date : 2011-12-8
     */
    private static void checkOuterIdExist(int type, String outerId, String outer, String structName)
    {
        // 检查同一个type的外部id是否存在 yanchuan 2011-10-19
        if (getInnerIdByOuterId(outerId, type) != null)
        {
            throw IMSUtil.throwBusiException(ErrorCodeDefine.IDMAPPING_ID_EXIST_DB, outer + " : \"" + outerId + "\"", structName);
        }
    }

    private static BaseNode getStructNode(List<BaseNode> structNodeList, Class clazz)
    {
        for (BaseNode struct : structNodeList)
        {
            if (struct.getAttribute(ATTR_CLASSNAME).equals(clazz.getName()))
                return struct;
        }
        return null;
    }

    /**
     * 获取mapType
     * 
     * @param fieldName :如cust_id ,son_cust_id,acct_id,parent_acct_id
     */
    private static Integer getMapTypeByFieldName(String fieldName)
    {
        if (p1.matcher(fieldName).matches())
        {
            return EnumCodeDefine.ID_MAP_TYPE_CUSTID;
        }

        if (p2.matcher(fieldName).matches())
        {
            return EnumCodeDefine.ID_MAP_TYPE_ACCTID;
        }

        if (p3.matcher(fieldName).matches())
        {
            return EnumCodeDefine.ID_MAP_TYPE_GROUPID;
        }

        throw IMSUtil.throwBusiException(ErrorCodeDefine.UNKNOWN_ERROR, fieldName);
    }

    /**
     * 从数据库查询内部ID
     */
    private static Long queryInnerId(String innerFieldName, String outerFieldValue)
    {
        Integer mapTypeByFieldName = getMapTypeByFieldName(innerFieldName);
        try
        {
            CmIdMapping result = DBUtil.querySingle(CmIdMapping.class, new DBCondition(CmIdMapping.Field.mapType,
                    mapTypeByFieldName), new DBCondition(CmIdMapping.Field.outerId, outerFieldValue));

            return result == null ? null : result.getInnerId();
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 从数据库查询外部ID
     */
    private static String queryOuterId(String innerFieldName, Long innerFieldValue)
    {
        // CmIdMapping where = new CmIdMapping();
        // where.setMapType(getMapTypeByFieldName(innerFieldName));
        // where.setInnerId(innerFieldValue);
        try
        {
            CmIdMapping result = DBUtil.querySingle(CmIdMapping.class, new DBCondition(CmIdMapping.Field.mapType,
                    getMapTypeByFieldName(innerFieldName)), new DBCondition(CmIdMapping.Field.innerId, innerFieldValue));
            return result == null ? null : result.getOuterId();
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 调整内部Id的值(处理请求参数)
     */
    public static void adjustInnerId(Object obj, IMSContext context)
    {
        if (obj == null || !(obj instanceof IComplexEntity))
        {
            return;
        }
        Logger.info("----start adjust outer inner id-------");
        // 调整输入参数的inner字段
        setIdMapping(obj, true, IMSUtil.isInnerSystem(context));
        Logger.info("----end adjust outer inner id and start new 3h bean-------");
    }

    /**
     * 调整外部Id的值(处理返回参数)
     */
    public static void adjustOuterId(Object obj, IMSContext context)
    {
        setIdMapping(obj, false, IMSUtil.isInnerSystem(context));
    }

    /**
     * 设置内部ID
     * 
     * @param outer2Inner :true表示从outer_字段到inner字段拷贝，false表示从inner id到outer id的拷贝
     */
    private static void setIdMapping(Object obj, boolean outer2Inner, boolean innerSystem)
    {
        if (obj == null || !(obj instanceof IComplexEntity))
        {
            return;
        }
        Class objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();
        if (fields == null)
        {
            return;
        }
        for (Field field : fields)
        {
            Class fieldType = field.getType();
            // 处理数组对象字段
            if (fieldType.isArray())
            {
                Object[] objArr = (Object[]) getFieldValue(obj, field);
                if (objArr != null && objArr.length > 0)
                {
                    for (Object o : objArr)
                    {
                        setIdMapping(o, outer2Inner, innerSystem);
                    }
                }
                continue;// 继续处理下一个字段
            }
            // 处理bo对象字段
            if (IComplexEntity.class.isAssignableFrom(fieldType))
            {
                Object o = (Object) getFieldValue(obj, field);
                if (o != null)
                {
                    setIdMapping(o, outer2Inner, innerSystem);
                }
                continue;// 继续处理下一个字段
            }

            // 处理基本类型字段
            dealPrimitiveField(obj, field, outer2Inner, innerSystem);
        }
    }

    /**
     * 处理基本类型字段
     */
    private static void dealPrimitiveField(Object obj, Field field, boolean outer2Inner, boolean innerSystem)
    {
        if (outer2Inner)
        {
            outer2Inner(obj, field, innerSystem);
        }
        else
        {
            inner2Outer(obj, field, innerSystem);
        }
    }

    /**
     * inner id 字段拷贝到 outer id
     */
    private static void inner2Outer(Object obj, Field field, boolean innerSystem)
    {
        String name = field.getName();
        if (!name.startsWith("outer_"))// 非outer_开头，返回
        {
            return;
        }
        // 因为返回的参数肯定是填内部id的，因此内部系统直接返回
        if (innerSystem)
        {
            return;
        }
        // 处理外部系统的情况
        String innerName = name.substring(6);
        try
        {
            Field innerField = obj.getClass().getDeclaredField(innerName);
            if (innerField == null)// 内部字段不存在，返回
            {
                return;
            }
            Long innerValue = (Long) getFieldValue(obj, innerField);
            if (!CommonUtil.isValid(innerValue))// 内部字段为空时，肯定无法获取外部字段，直接返回
            {
                return;
            }
            String outerValue = null;
            if (isInnerOuterIdEqual())
            {
                // 内外部id相同时，直接从内部赋值到外部
                outerValue = CommonUtil.long2String(innerValue);
            }
            else
            {
                // 内外部id不相同时，
                outerValue = queryOuterId(innerName, innerValue);// 查询内部ID对应的外部ID
            }
            // outerValue肯定不为空，设置外部ID
            ClassUtil.setFieldValue(obj, field, outerValue);
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
        }
    }

    /**
     * outer id 拷贝到 inner id
     */
    private static void outer2Inner(Object obj, Field field, boolean innerSystem)
    {
        String name = field.getName();
        if (!name.startsWith("outer_"))// 非outer_开头，返回
        {
            return;
        }
        String outerValue = (String) getFieldValue(obj, field);
        // 1:内部系统调用
        if (innerSystem)
        {
            // 要求外部字段都不能有值
            if (outerValue != null && outerValue.length() != 0)
            {
                throw new IMSException("when inner system invoke, outer parameter[" + name + "] must be empty!!!");
            }
            return;// 内部系统处理完毕
        }

        // 2:外部系统的情况
        String innerName = name.substring(6);

        Field innerField = ClassUtil.getField(obj.getClass(), innerName);
        if (innerField == null)// 内部id字段不存在，返回
        {
            return;
        }
        Long innerValue = (Long) getFieldValue(obj, innerField);
        if (innerValue != null && innerValue != 0)// 如果外部系统调用，内部id必须不能有值
        {
            throw new IMSException("when outer system invoke, inner parameter[" + innerName + "] must be empty");
        }

        if (CommonUtil.isValid(outerValue))// 外部id有值时
        {
            if (innerOuterIdEqual)// 内外部id相同时，直接取外部id的值
            {
                try
                {
                    innerValue = Long.valueOf(outerValue);
                }
                catch (Exception e)
                {
                    throw new IMSException("when inner id equals outer id, the value of outer parameter[" + name
                            + "] must be numbers");
                }
            }
            else
            {// 内外部id不同时，通过外部id查内部id
                innerValue = queryInnerId(innerName, outerValue);// 查询外部ID对应的内部ID
            }

            if (CommonUtil.isValid(innerValue))
            {
                // 设置内部ID
                IdMapInitBean.setFieldValue(obj, innerField, innerValue);
            }
        }
    }

    /**
     * 不抛异常
     */
    private static void setFieldValue(Object obj, Field innerField, Object innerValue)
    {
        try
        {
            ClassUtil.setFieldValue(obj, innerField, innerValue);
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
        }
    }

    /**
     * 不抛异常
     */
    private static Object getFieldValue(Object obj, Field field)
    {
        try
        {
            return ClassUtil.getPropertyValue(obj, field);
        }
        catch (Exception e)
        {
            Logger.error(e.getMessage());
            return null;
        }
    }

    // /**
    // * 不抛异常
    // */
    // private static Object getFieldValue(Object obj, String fieldName)
    // {
    // try
    // {
    // return ClassUtil.getFieldValue(obj, fieldName);
    // }
    // catch (Exception e)
    // {
    // Logger.error(e.getMessage());
    // return null;
    // }
    // }

    /**
     * 新建外部客户ID和内部客户ID对应关系
     */
    public static void createCustMapping(String outerCustId, Long innerCustId)
    {
        insertIdMapping(EnumCodeDefine.ID_MAP_TYPE_CUSTID, outerCustId, innerCustId);
    }

    /**
     * 新建外部用户ID和内部用户ID对应关系
     */
    public static void createAcctMapping(String outerAcctId, Long innerAcctId)
    {
        insertIdMapping(EnumCodeDefine.ID_MAP_TYPE_ACCTID, outerAcctId, innerAcctId);
    }

    /**
     * 新建外部群组ID和内部群组ID对应关系
     */
    public static void createGroupMapping(String outerGroupId, Long innerGroupId)
    {
        insertIdMapping(EnumCodeDefine.ID_MAP_TYPE_GROUPID, outerGroupId, innerGroupId);
    }

    /**
     * @param mapType ：1:CUST_ID;2:ACCT_ID;3:GROUP_ID
     * @param outerId ：crm id
     * @param innerId ： ims id
     */
    private static void insertIdMapping(Integer mapType, String outerId, Long innerId)
    {
        // 两个ID都不能为空
        if (!CommonUtil.isValid(outerId) || !CommonUtil.isValid(innerId))
        {
            throw new RuntimeException(" innerId and outerId can't be empty!!");
        }

        CmIdMapping dm = new CmIdMapping();
        dm.setMapType(mapType);
        dm.setOuterId(outerId);
        dm.setInnerId(innerId);

        DBUtil.insert(dm);
    }

    /**
     * 通过内部ID获取外部ID
     */
    private static String getOuterIdByInnerId(Long innerId, Integer mapType)
    {
        if (innerOuterIdEqual)
            return CommonUtil.long2String(innerId);

        CmIdMapping mapping = DBUtil.querySingle(CmIdMapping.class, new DBCondition(CmIdMapping.Field.mapType, mapType),
                new DBCondition(CmIdMapping.Field.innerId, innerId));
        if (mapping == null)
        {
            return null;
        }
        return mapping.getOuterId();
    }

    /**
     * 通过内部ID获取外部ID
     */
    public static String getOuterCustId(Long custId)
    {
        return getOuterIdByInnerId(custId, EnumCodeDefine.ID_MAP_TYPE_CUSTID);
    }

    /**
     * 通过内部ID获取外部ID
     */
    public static String getOuterGroupId(Long groupId)
    {
        return getOuterIdByInnerId(groupId, EnumCodeDefine.ID_MAP_TYPE_GROUPID);
    }

    /**
     * 通过内部ID获取外部ID
     */
    public static String getOuterAcctId(Long acctId)
    {
        return getOuterIdByInnerId(acctId, EnumCodeDefine.ID_MAP_TYPE_ACCTID);
    }

    /**
     * 通过外部ID获取内部ID Administrator 2011-12-1
     */
    private static Long getInnerIdByOuterId(String outerId, Integer mapType)
    {
        if (innerOuterIdEqual)
            return CommonUtil.string2Long(outerId);
        CmIdMapping mapping = DBUtil.querySingle(CmIdMapping.class, new DBCondition(CmIdMapping.Field.mapType, mapType),
                new DBCondition(CmIdMapping.Field.outerId, outerId));
        if (mapping == null)
        {
            return null;
        }
        return mapping.getInnerId();
    }

    /**
     * 通过外部custID获取内部ID
     */
    public static Long getInnerCustId(String outCustId)
    {
        return getInnerIdByOuterId(outCustId, EnumCodeDefine.ID_MAP_TYPE_CUSTID);
    }

    /**
     * 通过外部groupID获取内部ID
     */
    public static Long getInnerGroupId(String outGroupId)
    {
        return getInnerIdByOuterId(outGroupId, EnumCodeDefine.ID_MAP_TYPE_GROUPID);
    }

    /**
     * 通过外部acctID获取内部ID
     */
    public static Long getInnerAcctId(String outAcctId)
    {
        return getInnerIdByOuterId(outAcctId, EnumCodeDefine.ID_MAP_TYPE_ACCTID);
    }

    /**
     * 内外部id是否相同
     */
    public static boolean isInnerOuterIdEqual()
    {
        return innerOuterIdEqual;
    }

}
