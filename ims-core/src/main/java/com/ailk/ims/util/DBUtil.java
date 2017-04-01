package com.ailk.ims.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Table;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jef.common.wrapper.IntRange;
import jef.database.AbstractDbClient;
import jef.database.Batch;
import jef.database.Condition;
import jef.database.Condition.Operator;
import jef.database.DataObject;
import jef.database.DataObjectMap;
import jef.database.DbUtils;
import jef.database.DefaultSqlPopulator;
import jef.database.DefaultSqlProcessor;
import jef.database.Field;
import jef.database.IConditionField;
import jef.database.RoutableDbClient;
import jef.database.IConditionField.And;
import jef.database.IConditionField.Exists;
import jef.database.IConditionField.NotExists;
import jef.database.IConditionField.Or;
import jef.database.QB;
import jef.database.Transaction;
import jef.database.annotation.PartitionResult;
import jef.database.meta.MetaHolder;
import jef.database.meta.OracleDialect;
import jef.database.meta.TableMetadata;
import jef.database.query.NativeQuery;
import jef.database.query.Query;
import jef.database.query.RefField;
import jef.database.query.SelectItems;

import com.ailk.easyframe.web.common.dal.CommonDao;
import com.ailk.easyframe.web.common.dal.DefaultUniqueIdManager;
import com.ailk.ims.common.DBCondition;
import com.ailk.ims.common.DBExistsCond;
import com.ailk.ims.common.DBJoinCondition;
import com.ailk.ims.common.DBOrCondition;
import com.ailk.ims.common.JoinPart;
import com.ailk.ims.common.OrderCondition;
import com.ailk.ims.define.ConstantDefine;
import com.ailk.ims.exception.IMSException;

/**
 * @Description: 数据库操作类，与数据库相关的一些公用方法可以定义在这里
 * @Company: Asiainfo-Linkage Technologies(China),Inc. Hangzhou
 * @Author wuyj
 * @Date 2011-7-2 2011-08-04 wuyujie : 新增queryInnerJoin()方法
 * @Date 2012-04-07 lijc3 修改bug 不为空才设置操作符
 * @Date 2012-04-28 wuyujie ：因为直接使用query(DataObject)存在查找条件顺序无法控制的缺陷，所以废除
 * @Date 2012-05-19 wuyujie ：新增createDBCondition方法
 */
public class DBUtil {

	public static CommonDao getCommonDao() throws IMSException {
		return (CommonDao) SpringUtil.getBean(ConstantDefine.SPRING_BEAN_COMMONDAO);
	}

	public static AbstractDbClient getDBClient() throws IMSException {
		return getCommonDao().getClient();
	}

	public static long getSequence() {
		return getSequence(null);
	}

	public static void commit() throws IMSException {
		try {
			((Transaction) getDBClient()).commit();
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	public static List<? extends DataObject> queryByNative(String entityName, String condition, String order)
			throws Exception, SQLException {
		Class<DataObject> entityClass = (Class<DataObject>) Class.forName(entityName);
		return queryByNative(entityClass, condition, order);
	}

	public static List<? extends DataObject> queryByNative(Class<DataObject> clz, String condition, String order)
			throws Exception, SQLException {
		String tableName = DBUtil.buildTableName(clz);
		StringBuffer sql = new StringBuffer("");
		sql.append("select * from ");
		sql.append(tableName);
		if (CommonUtil.isNotEmpty(condition)) {
			sql.append(" where ");
			sql.append(condition);
		}
		if (CommonUtil.isNotEmpty(order)) {
			sql.append(" order by ");
			sql.append(order);
		}
		NativeQuery<? extends DataObject> queryResult = getDBClient().createNativeQuery(sql.toString(), clz);
		return queryResult.getResultList();
	}

	public static long getSequence(Class<? extends DataObject> dmClass) {
		DefaultUniqueIdManager seqManager = (DefaultUniqueIdManager) SpringUtil
				.getBean(ConstantDefine.SPRING_BEAN_UNIQUEIDMANAGER);
		if (dmClass == null)
			return seqManager.nextLong();
		else
			return seqManager.nextLong(dmClass);
	}

	public static jef.database.Field getJefField(Class<? extends DataObject> dmClass, String fieldName) {
		TableMetadata metadata = MetaHolder.getMeta(dmClass);
		return metadata.getField(fieldName);
	}

	/**
	 * @Description: 获取一个数据库实体的真实schema
	 * @author : wuyj
	 * @date : 2011-10-1
	 */
	public static String getEntitySchema(Class<? extends DataObject> clz) {
		// TODO 如果在jef里重定向了还需要咨询jiyi如何获取
		Table anno = clz.getAnnotation(Table.class);
		if (anno == null)
			return null;
		return anno.schema();
	}

	/**
	 * @Description: 获取数据库实体对应的数据库表名
	 * @author : wuyj
	 * @date : 2011-10-1
	 */
	public static String getEntityTableName(Class<? extends DataObject> clz) {
		Table anno = clz.getAnnotation(Table.class);
		if (anno == null)
			return null;
		return anno.name();
	}

	/**
	 * @Description: 获取一个数据库实体带schema的的完整表名，
	 * @author : wuyj
	 * @date : 2011-10-1
	 */
	public static String buildTableName(Class<? extends DataObject> clz) {
		Table anno = clz.getAnnotation(Table.class);
		if (anno == null)
			return null;
		String schema = anno.schema();
		String tableName = anno.name();
		if (!CommonUtil.isEmpty(schema))
			tableName = schema + "." + tableName;

		return tableName;
	}

	public static DBCondition[] buildQueryCondition(DataObject where) throws IMSException {
		if (where == null || CommonUtil.isEmpty(where.getUpdateValueMap())) {
			throw IMSUtil.throwBusiException("conditions to update records should not be empty!");
		}
		try {
			List<DBCondition> conditions = new ArrayList<DBCondition>();
			Iterator<Field> it = where.getUpdateValueMap().keySet().iterator();
			while (it.hasNext()) {
				Field f = it.next();
				conditions.add(new DBCondition(f, where.getUpdateValueMap().get(f)));
			}
			return conditions.toArray(new DBCondition[conditions.size()]);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/**
	 * @Description: 构造复杂的嵌套查询
	 */
	public static IConditionField buildComplexCondition(DBCondition condition, Query<?> query) throws IMSException {
		if (condition instanceof DBOrCondition) {
			Or or = new Or();
			List<DBCondition> subConds = condition.getConditions();
			for (DBCondition cond : subConds) {
				if (cond.getConditions() == null) {
					Class fClass = IMSUtil.getClassByField(cond.getField());
					jef.database.Field f = query.getType() == fClass ? cond.getField() : new RefField(cond.getField());
					if (cond.getOperator() == null) {
						or.addCondition(f, cond.getValue());
					} else {
						or.addCondition(f, cond.getOperator(), cond.getValue());
					}

				} else {
					IConditionField condF = buildComplexCondition(cond, query);
					or.addCondition(condF);
				}

			}
			return or;
		} else {
			And and = new And();
			List<DBCondition> subConds = condition.getConditions();
			for (DBCondition cond : subConds) {
				if (cond.getConditions() == null) {
					Class fClass = IMSUtil.getClassByField(cond.getField());
					jef.database.Field f = query.getType() == fClass ? cond.getField() : new RefField(cond.getField());
					if (cond.getOperator() == null) {
						and.addCondition(f, cond.getValue());
					} else {
						and.addCondition(f, cond.getOperator(), cond.getValue());
					}
				} else {
					IConditionField condF = buildComplexCondition(cond, query);
					and.addCondition(Condition.get(condF));
				}
			}
			return and;
		}
	}

	private static void setQueryCondition(Query<?> query, DBCondition... conditions) throws IMSException {
		if (CommonUtil.isEmpty(conditions)) {
			query.setAllRecordsCondition();
			return;
		}

		for (int i = 0; i < conditions.length; i++) {
			DBCondition condition = conditions[i];

			if (CommonUtil.isEmpty(condition.getConditions())) {
				// 普通查询
				// 2011-08-16 wuyujie : 除了用is null查询，其它表达式情况字段不能为null
				if (condition.getValue() == null && Operator.IS_NULL != condition.getOperator()) {
					throw new IMSException("query condition value of \"" + condition.getField().name()
							+ "\" can not be null.");
				}
				if (condition.getOperator() == null) {
					query.addCondition(condition.getField(), condition.getValue());
				} else {
					query.addCondition(condition.getField(), condition.getOperator(), condition.getValue());
				}
			} else {
				// 嵌套的复杂查询
				query.addCondition(buildComplexCondition(condition, query));
			}
		}
	}

	private static void setQueryCondition(DataObject dm, DBCondition... conditions) throws IMSException {
		setQueryCondition(dm.getQuery(), conditions);
	}

	private static void setOrderCondition(DataObject dm, OrderCondition... orders) throws IMSException {
		setOrderCondition(dm.getQuery(), orders);
	}

	private static void setOrderCondition(Query<?> query, OrderCondition... orders) throws IMSException {
		if (CommonUtil.isEmpty(orders)) {
			return;
		}
		for (OrderCondition order : orders) {
			query.addOrderBy(order.isAsc(), order.getField());
		}
	}

	/**
	 * @Description:把value所设置的值全部都更新到updatedEntities的每一个元素里
	 */
	public static void mergeUpdatedEntities(DataObject value, List<? extends DataObject> updatedEntities) {
		if (CommonUtil.isEmpty(updatedEntities))
			return;
		try {
			Set<jef.database.Field> setField = value.getUpdateValueMap().keySet();
			if (CommonUtil.isEmpty(setField)) {
				return;
			}
			for (DataObject entity : updatedEntities) {
				mergeUpdatedEntity(value, entity);
			}
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}
	
	public static void mergeUpdatedEntityNoSegment(DataObject value, DataObject updatedEntity){
		try {
			Set<jef.database.Field> setField = value.getUpdateValueMap().keySet();
			if (CommonUtil.isEmpty(setField)) {
				return;
			}
			jef.database.Field[] arrFiled = setField.toArray(new jef.database.Field[setField.size()]);
			for (jef.database.Field f : arrFiled) {
				if(!ConstantDefine.USER_SEGMENT.equals(f.name()) && !ConstantDefine.CUST_SEGMENT.equals(f.name())){
					Object fValue = value.getUpdateValueMap().get(f);
					ClassUtil.setFieldValue(updatedEntity, f.name(), fValue);
				}
			}
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	public static void mergeUpdatedEntity(DataObject value, DataObject updatedEntity) {
		try {
			Set<jef.database.Field> setField = value.getUpdateValueMap().keySet();
			if (CommonUtil.isEmpty(setField)) {
				return;
			}
			jef.database.Field[] arrFiled = setField.toArray(new jef.database.Field[setField.size()]);
			for (jef.database.Field f : arrFiled) {
				Object fValue = value.getUpdateValueMap().get(f);
				ClassUtil.setFieldValue(updatedEntity, f.name(), fValue);
			}

		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	// Query
	// ########################################################################

	/**
	 * @Description: 查询指定实体的全部数据
	 */
	public static <T extends DataObject> List<T> queryAll(Class<? extends DataObject> dmClass) throws IMSException {
		try {
			return (List<T>) getDBClient().selectAll(dmClass);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/**
	 * @Description: 根据主键查询，只需要设置好主键传入即可
	 */
	public static <T extends DataObject> T queryByPk(DataObject dm) throws IMSException {
		try {
			return (T) getDBClient().load(dm);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/**
	 * @Description: 根据设置好的实体值作为查询条件进行查询
	 * @deprecated 2012-04-28 wuyujie ：因为直接使用DataObject存在查找条件顺序无法控制的缺陷，所以废除
	 */
	public static <T extends DataObject> List<T> query(DataObject where) throws IMSException {
		try {
			DBCondition[] conditions = buildQueryCondition(where);
			return (List<T>) query(where.getClass(), conditions);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	public static Query<?> getQueryCondition(Class<? extends DataObject> dmClass, DBCondition[] conditions,
			OrderCondition[] orders) throws IMSException {

		try {
			DataObject dm = (DataObject) dmClass.newInstance();
			Query<?> query = dm.getQuery();

			setQueryCondition(query, conditions);
			if (orders != null)
				setOrderCondition(query, orders);

			return query;
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/**
	 * @Description: 根据设置好的实体值作为查询条件进行查询
	 * @deprecated 2012-04-28 wuyujie ：因为直接使用DataObject存在查找条件顺序无法控制的缺陷，所以废除
	 */
	public static <T extends DataObject> T querySingle(DataObject dm) throws IMSException {
		List result = query(dm);
		if (CommonUtil.isEmpty(result))
			return null;
		return (T) result.get(0);
	}

	/**
	 * @Description: 根据实体名称和条件进行查询
	 */
	public static <T extends DataObject> List<T> query(Class<? extends DataObject> dmClass, DBCondition... conditions)
			throws IMSException {
		return query(dmClass, null, null, conditions);
	}

	public static <T extends DataObject> List<T> query(Class<? extends DataObject> dmClass, OrderCondition[] orders,
			IntRange range, DBCondition... conditions) throws IMSException {
		try {
			DataObject dm = (DataObject) dmClass.newInstance();
			setQueryCondition(dm, conditions);
			if (orders != null)
				setOrderCondition(dm, orders);
			return (List<T>) getDBClient().select(dm, range);
		} catch (Exception e) {
			throw IMSUtil.throwBusiException(e);
		}
	}
	
	public static <T extends DataObject> List<T> queryByDbKey(Class<? extends DataObject> dmClass,String dbKey,DBCondition... conditions){
		try {
			DataObject dm = (DataObject) dmClass.newInstance();
			setQueryCondition(dm, conditions);
			return (List<T>) getDbClientByName(dbKey).select(dm);
		} catch (Exception e) {
			throw IMSUtil.throwBusiException(e);
		}
	}
	
	public static <T extends DataObject> T querySingleByDbKey(Class<? extends DataObject> dmClass, String dbKey,DBCondition... conditions)
			throws IMSException {
		List<? extends DataObject> result = queryByDbKey(dmClass,dbKey ,conditions);
		if (result == null || result.isEmpty())// 不用isEmpty方法，提高效率
		{
			return null;
		}
		return (T) result.get(0);
	}
	
	public static <T extends DataObject> T querySingle(Class<? extends DataObject> dmClass, DBCondition... conditions)
			throws IMSException {
		List<? extends DataObject> result = query(dmClass, conditions);
		if (result == null || result.isEmpty())// 不用isEmpty方法，提高效率
		{
			return null;
		}
		return (T) result.get(0);
	}

	/*
	 * @Description: 根据表名返回所有记录的条数 caohm5 2012-3-1 add
	 */
	public static int queryAllCount(Class<? extends DataObject> dmClass) throws IMSException {
		try {
			// DataObject dm = (DataObject) dmClass.newInstance();
			// return getDBClient().count(dm.getQuery());
			return getDBClient().count(QB.create(dmClass));

		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/*
	 * @Description: 根据表名和条件返回符合记录的条数 caohm5 2012-3-1 add
	 */
	public static int queryCount(Class<? extends DataObject> dmClass, DBCondition... conditions) throws IMSException {

		try {
			DataObject dm = (DataObject) dmClass.newInstance();
			setQueryCondition(dm, conditions);
			return getDBClient().count(dm.getQuery());
		} catch (Exception e) {
			throw new IMSException(e);
		}

	}

	// Insert
	// ########################################################################
	public static void insert(DataObject dm, String tableName) throws IMSException {
		if (dm == null) {
			return;
		}

		try {
			if (CommonUtil.isEmpty(tableName))
				getDBClient().insert(dm);
			else
				getDBClient().insert(dm, tableName);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	public static void insert(DataObject dm) throws IMSException {
		insert(dm, null);
	}

	
	//TODO
	/**
	 * 指定数据库的批量插入方法，后期可能删除
	 * @param dmList
	 * @param tableName
	 * @param dbkye
	 */
	public static void insertBatchWithDbkey(List<? extends DataObject> dmList, String tableName,String dbkey){
		if (CommonUtil.isEmpty(dmList))
			return;
		else if (dmList.size() == 1) {
			insertWithName(dmList.get(0),dbkey,tableName);
			return;
		}
		DataObject[] dms = dmList.toArray(new DataObject[dmList.size()]);
		try {
			Batch<DataObject> batch = null;
			if (CommonUtil.isEmpty(tableName))
				batch = getDbClientByName(dbkey).startBatchInsert(dms[0]);
			else
				batch = getDbClientByName(dbkey).startBatchInsert(dms[0], tableName);
			batch.add(dms);
			batch.commit();
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}
	
	public static void insertBatch(List<? extends DataObject> dmList, String tableName) throws IMSException {
		if (CommonUtil.isEmpty(dmList))
			return;
		else if (dmList.size() == 1) {
			insert(dmList.get(0), tableName);
			return;
		}
		DataObject[] dms = dmList.toArray(new DataObject[dmList.size()]);
		try {
			Batch<DataObject> batch = null;
			if (CommonUtil.isEmpty(tableName))
				batch = getDBClient().startBatchInsert(dms[0]);
			else
				batch = getDBClient().startBatchInsert(dms[0], tableName);
			batch.add(dms);
			batch.commit();
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	public static void insertBatch(List<? extends DataObject> dms) throws IMSException {
		insertBatch(dms, null);
	}

	// Update
	// ########################################################################

	/**
	 * @Description:根据条件和设置的值来更新
	 * @param value
	 *            ,必须是自己new出来的且值是通过自己set进去的，而不是查询出来的
	 */
	public static int updateByCondition(DataObject value, DBCondition... conditions) throws IMSException {
		try {
			setQueryCondition(value, conditions);
			return getDBClient().update(value);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}
	//TODO,CD.CD_ROUTE_IDENTITY的特殊处理方法，后期可能会删除
	/**
	 * 只用于CD.CD_ROUTE_IDENTITY
	 * @param value
	 * @param dbkey
	 * @param tableName
	 * @param conditions
	 * @return
	 * @throws IMSException
	 */
	public static int updateWithName(DataObject value,String dbkey,String tableName,DBCondition... conditions)throws IMSException{
		try {
			setQueryCondition(value, conditions);
			return getDbClientByName(dbkey).update(value,tableName);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}
	
	/**
	 * 只用于CD.CD_ROUTE_IDENTITY
	 * @param value
	 * @param dbkey
	 * @param tableName
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public static void insertWithName(DataObject value,String dbkey,String tableName){
		try {
			
			getDbClientByName(dbkey).insert(value,tableName);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}
	
	/**
	 * 只用于CD.CD_ROUTE_IDENTITY
	 * @param name
	 * @return
	 */
	public static AbstractDbClient getDbClientByName(String name) {
		AbstractDbClient dbClient = getDBClient();

		if (dbClient instanceof Transaction &&  ((Transaction) dbClient).getParent() instanceof RoutableDbClient) {
			((RoutableDbClient) ((Transaction) dbClient).getParent()).setDbKey(name);
		}
		
		return dbClient;
	}
	
	/**
	 * @Description: 功能同updateByCondition,只是返回的是更新的所有实体
	 */
	public static List<? extends DataObject> updateByConditionWithReturn(DataObject value, DBCondition... conditions)
			throws IMSException {
		try {
			List<? extends DataObject> query = query(value.getClass(), conditions);
			mergeUpdatedEntities(value, query);

			updateByCondition(value, conditions);

			return query;
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	// ########################################################################
	
	@Transactional(propagation=Propagation.REQUIRED)
	public static int deleteByDbKey(Class<? extends DataObject> dmClass,String dbKey,String tableName, DBCondition... conditions){
		try {
			DataObject dm = (DataObject) dmClass.newInstance();
			setQueryCondition(dm, conditions);
			return getDbClientByName(dbKey).delete(dm,tableName);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}
	
	/**
	 * @Description: 根据条件进行查询
	 */
	public static int deleteByCondition(Class<? extends DataObject> dmClass, DBCondition... conditions)
			throws IMSException {
		try {
			DataObject dm = (DataObject) dmClass.newInstance();
			
			setQueryCondition(dm, conditions);
//			String myTableName = MetaHolder.toSchemaAdjustedName(null);
//			PartitionResult[] sites = DbUtils
//					.toTableNames(dm, myTableName, dm.getQuery(), );//SqlProcessor
			return getDBClient().delete(dm);
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}
    

	/**
	 * @Description: 功能同deleteByCondition,只是返回的是所有删除的实体
	 */
	public static List<? extends DataObject> deleteByConditionWithReturn(Class<? extends DataObject> dmClass,
			DBCondition... conditions) throws IMSException {
		List<? extends DataObject> query = query(dmClass, conditions);

		deleteByCondition(dmClass, conditions);

		return query;
	}

	/**
	 * @param mainConds
	 *            :the conditions of the main table
	 * @return the record list of the main table
	 */
	public static <T extends DataObject> List<T> queryExists(List<DBCondition> mainConds, DBExistsCond... existsConds) {
		if (CommonUtil.isEmpty(existsConds)) {
			throw new RuntimeException("invalid parameters!!!");
		}
		DataObject mainObj = null;
		for (int i = 0; i < existsConds.length; i++) {
			DBExistsCond existsCond = existsConds[i];
			if (mainObj == null) {
				mainObj = IMSUtil.getInstanceByField(existsCond.getConnectToField());
			}
			DataObject existsObj = IMSUtil.getInstanceByField(existsCond.getConnectField());

			jef.database.Field field = existsCond.isExists() ? new Exists(existsObj) : new NotExists(existsObj);
			mainObj.getQuery().addCondition(field, null);
			existsObj.getQuery().addCondition(existsCond.getConnectField(),
					new RefField(mainObj, existsCond.getConnectToField().name()));
			List<DBCondition> existObjCond = existsCond.getCondList();
			DBUtil.setQueryCondition(existsObj, existObjCond.toArray(new DBCondition[existObjCond.size()]));
		}

		if (!CommonUtil.isEmpty(mainConds)) {
			DBUtil.setQueryCondition(mainObj, mainConds.toArray(new DBCondition[mainConds.size()]));
		}

		try {
			return (List<T>) (getDBClient().select(mainObj));
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/**
	 * 更新失效时间，并返回未更新的所有记录
	 * 过户用
	 * 
	 * @param expireDate
	 *            老记录的失效时间
	 */
	public static List<? extends DataObject> updateByConditionWithNoUpReturn(Class<? extends DataObject> dmClass,
			Date expireDate, DBCondition... conditions) throws IMSException {
		try {
			List<? extends DataObject> query = query(dmClass, conditions);
			if(CommonUtil.isEmpty(query)){
				return query;
			}
			DataObject dm = (DataObject) dmClass.newInstance();
			try {
				java.lang.reflect.Field f_expireDate = ClassUtil.getField(dmClass,
						ConstantDefine.ENTITY_FIELD_EXPIRE_DATE);
				ClassUtil.setFieldValue(dm, f_expireDate, expireDate);
			} catch (Exception e) {
				throw new IMSException(e);
			}
			updateByCondition(dm, conditions);

			// 删除生效时间比失效时间大的记录
			// 过户的时候有用
			//将失效时间的条件变成小于等于
			List<DBCondition> cons = CommonUtil.parseArray2List(conditions);
			for (DBCondition con : cons) {
				if (con.getField().name().equals(ConstantDefine.ENTITY_FIELD_EXPIRE_DATE)) {
					con.setOperator(Operator.LESS_EQUALS);
					break;
				}
			}
			cons.add(new DBCondition(DBUtil.getJefField(dm.getClass(), ConstantDefine.ENTITY_FIELD_VALID_DATE),
					expireDate, Operator.GREAT_EQUALS));
			deleteByCondition(dm.getClass(), cons.toArray(new DBCondition[cons.size()]));

			return query;
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	/**
	 * @Description:多表关联查询
	 * @param DBJoinCondition
	 *            ,查询的关联条件，具体参见DBJoinCondition说明
	 * @param dbConditions
	 *            ,具体的查询条件
	 * @param orders
	 *            ,排序,每一个order里只能设置同一张表的字段
	 * @param range
	 *            ,分页，new IntRange(1,100),查询1到100条记录
	 * @return,返回的是一个List，每条记录是一个Map， 键名为表名class，健值是表名对应的实例对象，也是按照传入顺序排列的
	 */
	public static List<Map> queryJoin(DBJoinCondition joinCondition, OrderCondition[] orders, IntRange range,
			DBCondition... dbConditions) {
		try {
			if (!CommonUtil.isEmpty(dbConditions)) {
				// 把DBCondition的值设置到对应的Query中去,因为不同的Query对象对应不同的表,条件需要匹配到对应的表
				for (int i = 0; i < dbConditions.length; i++) {
					DBCondition condition = dbConditions[i];
					if (CommonUtil.isEmpty(condition.getConditions())) {
						// 普通查询
						Class fieldClass = IMSUtil.getClassByField(condition.getField());
						Query query = joinCondition.getQuery(fieldClass);
						if (query == null)
							new IMSException("invalid database condition");
						// 2011-10-24 wuyujie : 除了用is null查询，其它表达式情况字段不能为null
						if (condition.getValue() == null && Operator.IS_NULL != condition.getOperator()) {
							throw new IMSException("query condition value of \"" + condition.getField().name()
									+ "\" can not be null.");
						}
						// @Date 2012-04-07 lijc3 修改bug 不为空才设置操作符
						if (condition.getOperator() == null) {
							query.addCondition(condition.getField(), condition.getValue());
						} else {
							query.addCondition(condition.getField(), condition.getOperator(), condition.getValue());
						}
					} else {
						// 嵌套的复杂查询
						DBCondition cond = DBCondition.getFirstIndividualCondition(condition);

						Class fieldClass = IMSUtil.getClassByField(cond.getField());
						Query query = joinCondition.getQuery(fieldClass);
						if (query == null)
							new IMSException("invalid database condition");

						IConditionField condF = buildComplexCondition(condition, query);
						query.addCondition(condF);

					}
				}
			}
			if (!CommonUtil.isEmpty(orders)) {
				for (OrderCondition order : orders) {
					Class fieldClass = IMSUtil.getClassByField(order.getField());
					Query query = joinCondition.getQuery(fieldClass);
					query.addOrderBy(order.isAsc(), order.getField());
				}

			}
			List<DataObjectMap> resultList = DBUtil.getDBClient().select(joinCondition.build(), DataObjectMap.class,
					range);
			if (CommonUtil.isEmpty(resultList))
				return null;
			List<Map> returnResult = new ArrayList<Map>();

			List<JoinPart> joinParts = joinCondition.getJoinParts();
			for (DataObjectMap resultItem : resultList) {
				Map itemMap = new LinkedHashMap();

				for (JoinPart part : joinParts) {
					Query query = part.getQuery();
					itemMap.put(part.getTable(), resultItem.get(query));
				}
				returnResult.add(itemMap);
			}
			return returnResult;
		} catch (Exception e) {
			throw new IMSException(e);
		}
	}

	public static List<Map> queryJoin(DBJoinCondition joinCondition, DBCondition... dbConditions) {
		return queryJoin(joinCondition, null, null, dbConditions);
	}

	/**
	 * 根据实体对象和字段对象，创建实体对应的查询条件
	 * 
	 * @param dbObj
	 * @param fs
	 * @return
	 * @throws Exception
	 */
	public static DBCondition[] createDBCondition(DataObject dbObj, List<jef.database.Field> fs) throws Exception {
		DBCondition[] result = new DBCondition[fs.size()];
		for (int i = 0; i < fs.size(); i++) {
			jef.database.Field f = fs.get(i);
			Object fValue = ClassUtil.getPropertyValue(dbObj, f.name());
			result[i] = new DBCondition(f, fValue);
		}
		return result;
	}

	public static <T extends DataObject> Integer queryMax(Class<T> dmClass, jef.database.Field field) {
		Query<T> query = QB.create(dmClass);
		SelectItems s = QB.selectFrom(query);
		s.column(field).max();
		List<Integer> result = null;
		try {
			result = getDBClient().select(query, Integer.class, null);
		} catch (Exception e) {
			throw new IMSException(e);
		}
		if (CommonUtil.isEmpty(result))
			return 1;
		return result.get(0) + 1;
	}

	/**
	 * @author yanchuan 判断数据库对象是否有设置值 2012-8-17
	 * @param dbObj
	 * @return
	 */
	public static boolean isSetValue(DataObject dbObj) {
		if (CommonUtil.isNotEmpty(dbObj.getUpdateValueMap())) {
			return true;
		}
		return false;
	}
}
