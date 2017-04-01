package com.ailk.ims.init;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import jef.database.DataObject;

import com.ailk.ims.common.InitBean;
import com.ailk.ims.manualmdb.Upfield3hId;
import com.ailk.ims.manualmdb.UpfieldObjIdType;
import com.ailk.ims.manualmdb.UpfieldTableMainObject;
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
public class UpfieldInitBean implements IImsConfigInit {
	private static final String ATTR_BEAN = "bean";
	private static final String TAG_TABLE = "table";
	private static final String TAG_FIELDS = "fields";
	private static final String ATTR_FIELD = "field";
	private static final String ATTR_VALUE = "value";
	private static final String TAG_OBJ_ID = "obj_id";
	private static final String TAG_OBJ_TYPE = "obj_type";
	private static final String TAG_PROD_ID = "prod_id";
	private static final String TAG_CUST = "cust";
	private static final String TAG_ACCT = "acct";
	private static final String TAG_USER = "user";
	private static final String TAG_MDB = "mdb";
	private static final String OBJ_TYPE_VALUE_SPLIT = ",";

	private ImsLogger logger = new ImsLogger(getClass());
	private static final LinkedHashMap<Class<? extends DataObject>, UpfieldTableMainObject> config = new LinkedHashMap<Class<? extends DataObject>, UpfieldTableMainObject>();

	public void mergeNodes(BaseNode node, BaseNode nodeProj) throws Exception {
		// 合并项
		if (nodeProj != null && CommonUtil.isNotEmpty(nodeProj.getChildren())) {
			InitBean.mergeChildrenByAttr(node, nodeProj, ATTR_BEAN);
		}
	}

	public void init(BaseNode node) {
		try {
			List<BaseNode> tables = node.getChildrenByTagName(TAG_TABLE);
			if (CommonUtil.isEmpty(tables)) {
				throw IMSUtil.throwBusiException("no <table> tag under <config " + node.getAttribute(ATTR_BEAN) + ">");
			}
			for (BaseNode tableNode : tables) {
				String key = tableNode.getAttribute(ATTR_BEAN);
				logger.info("****** load table :", key);
				Class<? extends DataObject> clazz = (Class<? extends DataObject>) Class.forName(key);
				BaseNode fieldsNode = tableNode.getChildByTagName(TAG_FIELDS);
				if (fieldsNode == null) {
					throw IMSUtil.throwBusiException("no <fields> tag under <table> tag");
				}
				UpfieldTableMainObject upfieldMainObj = parseFieldsBy3hId(clazz, fieldsNode);
				if (upfieldMainObj == null) {
					upfieldMainObj = parseFieldsByObjId(clazz, fieldsNode);
				}

				parseMdbTables(upfieldMainObj, tableNode);

				config.put(clazz, upfieldMainObj);
				logger.debug(upfieldMainObj.toString());
			}
		} catch (Exception e) {
			throw IMSUtil.throwBusiException(e);
		}
	}

	private static void parseMdbTables(UpfieldTableMainObject upfieldMainObj, BaseNode tableNode) {
		List<BaseNode> mdbNodes = tableNode.getChildrenByTagName(TAG_MDB);
		if (CommonUtil.isEmpty(mdbNodes)) {
			throw IMSUtil.throwBusiException("no <mdb> tag under <table> tag");
		}
		Set<String> mdbTableSet = new HashSet<String>();
		for (BaseNode mdbNode : mdbNodes) {
			String mdbTable = mdbNode.getText();
			if (mdbTable == null) {
				throw IMSUtil.throwBusiException("no text value of <mdb> tag");
			}
			mdbTableSet.add(mdbTable);
		}
		upfieldMainObj.setMdbTables(mdbTableSet);
	}

	/**
	 * 按三户id字段解析
	 * luojb 2013-8-12
	 * 
	 * @param filedsNode
	 * @return
	 */
	private static Upfield3hId parseFieldsBy3hId(Class<? extends DataObject> clazz, BaseNode fieldsNode) {
		BaseNode custIdNode = fieldsNode.getChildByTagName(TAG_CUST);
		BaseNode acctIdNode = fieldsNode.getChildByTagName(TAG_ACCT);
		BaseNode userIdNode = fieldsNode.getChildByTagName(TAG_USER);
		String custIdFieldName = custIdNode != null ? custIdNode.getText() : null;
		String acctIdFieldName = acctIdNode != null ? acctIdNode.getText() : null;
		String userIdFieldName = userIdNode != null ? userIdNode.getText() : null;
		if (custIdFieldName == null && acctIdFieldName == null && userIdFieldName == null) {
			return null;
		}
		Upfield3hId objType = new Upfield3hId(clazz);
		objType.setCustIdFieldName(custIdFieldName);
		objType.setAcctIdFieldName(acctIdFieldName);
		objType.setUserIdFieldName(userIdFieldName);

		return objType;
	}

	public static UpfieldObjIdType parseFieldsByObjId(Class<? extends DataObject> clazz, BaseNode fieldsNode) {
		BaseNode objIdNode = fieldsNode.getChildByTagName(TAG_OBJ_ID);
		BaseNode objTypeNode = fieldsNode.getChildByTagName(TAG_OBJ_TYPE);
		BaseNode prodIdNode = fieldsNode.getChildByTagName(TAG_PROD_ID);
		if (objIdNode == null) {
			throw IMSUtil.throwBusiException("no <obj_id> tag under <fields> tag");
		}
		if (objTypeNode == null) {
			throw IMSUtil.throwBusiException("no <obj_type> tag under <fields> tag");
		}
		String objIdFieldName = objIdNode.getAttribute(ATTR_FIELD);
		if (objIdFieldName == null) {
			throw IMSUtil.throwBusiException("no attribute 'field' in <obj_id> tag");
		}

		UpfieldObjIdType upfieldObjectType = new UpfieldObjIdType(clazz);
		upfieldObjectType.setObjIdFieldName(objIdFieldName);
		if (prodIdNode != null) {
			String prodIdFieldName = prodIdNode.getAttribute(ATTR_FIELD);
			upfieldObjectType.setProdIdFieldName(prodIdFieldName);
		}

		String objType = objTypeNode.getAttribute(ATTR_VALUE);
		if (objType == null) {
			String objTypeFieldName = objTypeNode.getAttribute(ATTR_FIELD);
			if (objTypeFieldName == null) {
				throw IMSUtil.throwBusiException("no attribute 'field' or 'value' in <obj_type> tag");
			}
			BaseNode custNode = objTypeNode.getChildByTagName(TAG_CUST);
			BaseNode acctNode = objTypeNode.getChildByTagName(TAG_ACCT);
			BaseNode userNode = objTypeNode.getChildByTagName(TAG_USER);
			if (custNode == null && acctNode == null && userNode == null) {
				throw IMSUtil.throwBusiException("no tag <cust> or <acct> or<user> under <obj_type> tag");
			}

			upfieldObjectType.setObjTypeFieldName(objTypeFieldName);

			if (custNode != null) {
				String[] custValue = custNode.getText().split(OBJ_TYPE_VALUE_SPLIT);
				for (String value : custValue) {
					Integer v = CommonUtil.string2Integer(value);
					if (upfieldObjectType.keyExist(v)) {
						throw IMSUtil.throwBusiException("no dumplicate value[" + v + "]in <cust> tag and <"
								+ upfieldObjectType.getObjTypeMap().get(v) + "> tag");
					}
					upfieldObjectType.addObjTypeValue(v, UpfieldObjIdType.OBJ_TYPE_CUST);
				}
			}

			if (acctNode != null) {
				String[] acctValue = acctNode.getText().split(OBJ_TYPE_VALUE_SPLIT);
				for (String value : acctValue) {
					Integer v = CommonUtil.string2Integer(value);
					if (upfieldObjectType.keyExist(v)) {
						throw IMSUtil.throwBusiException("no dumplicate value[" + v + "]in <acct> tag and <"
								+ upfieldObjectType.getObjTypeMap().get(v) + "> tag");
					}
					upfieldObjectType.addObjTypeValue(v, UpfieldObjIdType.OBJ_TYPE_ACCT);
				}
			}

			if (userNode != null) {
				String[] userValue = userNode.getText().split(OBJ_TYPE_VALUE_SPLIT);
				for (String value : userValue) {
					Integer v = CommonUtil.string2Integer(value);
					if (upfieldObjectType.keyExist(v)) {
						throw IMSUtil.throwBusiException("no dumplicate value[" + v + "]in <user> tag and <"
								+ upfieldObjectType.getObjTypeMap().get(v) + "> tag");
					}
					upfieldObjectType.addObjTypeValue(v, UpfieldObjIdType.OBJ_TYPE_USER);
				}
			}

		} else {
			upfieldObjectType.setObjType(objType);
		}
		return upfieldObjectType;
	}

	public static UpfieldTableMainObject getUpFieldObject(Class<? extends DataObject> clazz) {
		return getUpfieldConfig().get(clazz);
	}

	public static List<Class<? extends DataObject>> getUpFieldClasses() {
		Iterator it = getUpfieldConfig().keySet().iterator();
		List<Class<? extends DataObject>> result = new ArrayList<Class<? extends DataObject>>();
		while (it.hasNext()) {
			result.add((Class) it.next());
		}
		return result;
	}

	public static Class<? extends DataObject> getUpFieldClass(int index) {
		return getUpFieldClasses().get(index);
	}

	public static List<String> getUpFields(Class<? extends DataObject> clazz) {
		return getUpfieldConfig().get(clazz).getFields();
	}

	public static List<Class<? extends DataObject>> parseUpField(String upfield) {
		int count = upfield.length();
		List<Class<? extends DataObject>> result = new ArrayList<Class<? extends DataObject>>();
		List<Class<? extends DataObject>> upClasses = getUpFieldClasses();
		for (int i = 0; i < count; i++) {
			char flag = upfield.charAt(i);
			if (flag == '1') {
				result.add(upClasses.get(i));
			}
		}
		return result;
	}

	public static LinkedHashMap<Class<? extends DataObject>, UpfieldTableMainObject> getUpfieldConfig() {
		return config;
	}

}
