package com.blue.ailk.db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import oracle.jdbc.OracleDatabaseMetaData;
import oracle.jdbc.OracleResultSet;

import org.apache.commons.lang.StringUtils;

public class DBTableParser extends DBSupport {

	public DBTableParser() {
		super();
	}

	public DBTableParser(IParameter paramter,String tablename) {
		super(paramter,tablename);
	}

	private String tablename;

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	/**
	 * 从ResultSet中解析出表
	 * 
	 * @param tableSet
	 * @return
	 * @throws SQLException
	 */

	public List<Table> getTablesFromResultSet(ResultSet tableSet)
			throws SQLException {
		List<Table> tables = new ArrayList<Table>();
		while (tableSet.next()) {
			if (tableSet.getString("TABLE_TYPE").equalsIgnoreCase("TABLE")) {

				Table table = new Table();
				table.setSchem(tableSet.getString("TABLE_SCHEM"));
				table.setName(tableSet.getString("TABLE_NAME"));
				tables.add(table);
			}

		}
		return tables;
	}

	/**
	 * 获取所有表名
	 * 
	 * @return
	 */

	private void getTablesFromDb() {
		String[] arrTbl = null;
		String schemaPattern = "%";
		String tableNamePattern = "%";
		if (this.getTablename() != null
				&& !this.getTablename().trim().equals("")) {
			arrTbl = this.getTablename().split("\\.");
		}
		if (arrTbl != null) {
			if (arrTbl.length == 1) {
				tableNamePattern = arrTbl[0];
			} else {
				schemaPattern = arrTbl[0];
				tableNamePattern = arrTbl[1];
			}
		}

		List<Table> tables = new ArrayList<Table>();

		try {
			DatabaseMetaData databaseMetaData = conn.getMetaData();

			ResultSet tableSet = databaseMetaData.getTables(null,
					schemaPattern, tableNamePattern, new String[] { "TABLE" });

			tables = getTablesFromResultSet(tableSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTables(tables);

	}

	private void getColumnsByTable(Table table) throws SQLException {
		String schemaPattern = "%";
		if (StringUtils.isNotEmpty(table.getSchem())) {
			schemaPattern = table.getSchem();
		}

		OracleDatabaseMetaData databaseMetaData = (OracleDatabaseMetaData) conn
				.getMetaData();
		OracleResultSet columnSet = (OracleResultSet) databaseMetaData
				.getColumns(null, schemaPattern, table.getName(), "%");
		getAllColumns(table, columnSet);
		// 获取主键
		getAllPrimaryKeys(table, databaseMetaData);
		// 获取外键
		getAllImportedKeys(table, databaseMetaData);
		// 获取例的信息

		getAllIndexs(table, databaseMetaData);
	}

	/**
	 * 索引 .
	 * 
	 * @param table
	 * @param databaseMetaData
	 * @throws SQLException
	 */
	private void getAllIndexs(Table table, DatabaseMetaData databaseMetaData)
			throws SQLException {
		ResultSet rs = databaseMetaData.getIndexInfo(null, table.getSchem(),
				table.getName(), false, true);

		while (rs.next()) {

			boolean nonUnique = rs.getBoolean("NON_UNIQUE");// 非唯一索引(Can index
															// values be
															// non-unique. false
															// when TYPE is
															// tableIndexStatistic
															// )
			String indexName = rs.getString("INDEX_NAME");// 索引的名称
			short type = rs.getShort("TYPE");// 索引类型
			short ordinalPosition = rs.getShort("ORDINAL_POSITION");// 在索引列顺序号
			String columnName = rs.getString("COLUMN_NAME");// 列名
			String ascOrDesc = rs.getString("ASC_OR_DESC");// 列排序顺序:升序还是降序
			int cardinality = rs.getInt("CARDINALITY"); // 基数
			String indexQualifier = rs.getString("INDEX_QUALIFIER");// 索引目录（可能为空）
			if (!nonUnique
					&& (indexName == null || indexName
							.equalsIgnoreCase(table.pkName))) {
				continue;
			}
			Index index = new Index();
			index.setAscOrDesc(ascOrDesc);
			index.setCardinality(cardinality);
			index.setColumnName(columnName);
			index.setIndexName(indexName);
			index.setIndexQualifier(indexQualifier);
			index.setNonUnique(nonUnique);
			index.setOrdinalPosition(ordinalPosition);
			index.setType(type);

			if (table.indexs.containsKey(indexName)) {
				List<Index> indexs = table.indexs.get(indexName);
				indexs.add(index);

			} else {
				List<Index> indexs = new ArrayList<Index>();
				indexs.add(index);
				table.indexs.put(indexName, indexs);
			}

		}
		Set<Entry<String, List<Index>>> iter = table.indexs.entrySet();
		for (Map.Entry<String, List<Index>> entry : iter)

		{

			List<Index> value = entry.getValue();
			if (!value.isEmpty()) {
				Index[] arrIndexs = new Index[value.size()];

				for (Index obj : value) {
					arrIndexs[obj.getOrdinalPosition() - 1] = obj;
				}
				List<Index> newValue = new ArrayList<Index>();
				for (Index index : arrIndexs) {
					newValue.add(index);
				}
				table.indexs.put(entry.getKey(), newValue);
			}

		}
	}

	/**
	 * 外键 .
	 * 
	 * @param table
	 * @param databaseMetaData
	 * @throws SQLException
	 */
	private void getAllImportedKeys(Table table,
			DatabaseMetaData databaseMetaData) throws SQLException {
		ResultSet rs = databaseMetaData.getImportedKeys(null, table.getSchem(),
				table.getName());
		while (rs.next()) {
			ImportedKey key = new ImportedKey();
			String pkTableCat = rs.getString("PKTABLE_CAT");// 主键表的目录（可能为空）
			String pkTableSchem = rs.getString("PKTABLE_SCHEM");// 主键表的架构（可能为空）
			String pkTableName = rs.getString("PKTABLE_NAME");// 主键表名
			String pkColumnName = rs.getString("PKCOLUMN_NAME");// 主键列名
			String fkTableCat = rs.getString("FKTABLE_CAT");// 外键的表的目录（可能为空）出口（可能为null）
			String fkTableSchem = rs.getString("FKTABLE_SCHEM");// 外键表的架构（可能为空）出口（可能为空）
			String fkTableName = rs.getString("FKTABLE_NAME");// 外键表名
			String fkColumnName = rs.getString("FKCOLUMN_NAME"); // 外键列名
			short keySeq = rs.getShort("KEY_SEQ");// 序列号（外键内值1表示第一列的外键，值2代表在第二列的外键）。
			short updateRule = rs.getShort("UPDATE_RULE");
			short delRule = rs.getShort("DELETE_RULE");
			String fkName = rs.getString("FK_NAME");// 外键的名称（可能为空）
			String pkName = rs.getString("PK_NAME");// 主键的名称（可能为空）

			short deferRability = rs.getShort("DEFERRABILITY");
			key.setDeferRability(deferRability);
			key.setFkColumnName(fkColumnName);
			key.setFkName(fkName);
			key.setFkTableCat(fkTableCat);
			key.setFkTableName(fkTableName);
			key.setFkTableSchem(fkTableSchem);
			key.setPkColumnName(pkColumnName);
			key.setPkName(pkName);
			key.setPkTableCat(pkTableCat);
			key.setPkTableName(pkTableName);
			key.setPkTableSchem(pkTableSchem);
			key.setKeySeq(keySeq);
			key.setDelRule(delRule);
			key.setUpdateRule(updateRule);
			if (table.importedKeys.containsKey(key.getFkName())) {
				List<ImportedKey> importedKeys = table.importedKeys.get(key
						.getFkName());
				importedKeys.add(key);

			} else {
				List<ImportedKey> importedKeys = new ArrayList<ImportedKey>();
				importedKeys.add(key);
				table.importedKeys.put(key.getFkName(), importedKeys);
			}
		}
		Set<Entry<String, List<ImportedKey>>> iter = table.importedKeys
				.entrySet();
		for (Map.Entry<String, List<ImportedKey>> entry : iter)

		{

			List<ImportedKey> value = entry.getValue();
			if (!value.isEmpty()) {
				ImportedKey[] arrImportedKeys = new ImportedKey[value.size()];

				for (ImportedKey obj : value) {
					arrImportedKeys[obj.getKeySeq() - 1] = obj;
				}
				List<ImportedKey> newValue = new ArrayList<ImportedKey>();
				for (ImportedKey importedKey : arrImportedKeys) {
					newValue.add(importedKey);
				}
				table.importedKeys.put(entry.getKey(), newValue);
			}

		}

	}

	/**
	 * 获取所有例 .
	 * 
	 * @param table
	 * @param columnSet
	 * @throws SQLException
	 */
	private void getAllColumns(Table table, OracleResultSet columnSet)
			throws SQLException {
		while (columnSet.next()) {
			Column col = new Column();
			try {
				col.setDefaultValue(columnSet.getString(13));
			} catch (Exception e) {

			}

			col.setName(columnSet.getString("COLUMN_NAME")); // 列名

			if (StringUtils.isNotEmpty(col.getDefaultValue())) {
				if (col.getDefaultValue().trim().equalsIgnoreCase("null")) {
					col.setDefaultValue("");
				}

			}
			col.setTypeName(columnSet.getString("TYPE_NAME").split(" ")[0]); // 列类型
			col.setColumnSize(columnSet.getInt("COLUMN_SIZE")); // 整数位数
			col.setDecimalDigits(columnSet.getInt("DECIMAL_DIGITS")); // 小数位数
			col.setNullable(columnSet.getInt("NULLABLE")); // 是否允许为空

			int columnNameSize = columnSet.getString("COLUMN_NAME").length();
			if (columnNameSize > table.getMaxColumnNameSize()) {
				table.setMaxColumnNameSize(columnNameSize);
			}

			table.columns.add(col);
		}
	}

	private void getAllPrimaryKeys(Table table,
			DatabaseMetaData databaseMetaData) throws SQLException {
		ResultSet primaryKeys = databaseMetaData.getPrimaryKeys(null,
				table.getSchem(), table.getName());
		int size = 0;
		while (primaryKeys.next()) {
			size++;
		}
		primaryKeys = databaseMetaData.getPrimaryKeys(null, table.getSchem(),
				table.getName());
		String[] pkCloumnName = new String[size];
		while (primaryKeys.next()) {
			pkCloumnName[primaryKeys.getShort("KEY_SEQ") - 1] = primaryKeys
					.getString("COLUMN_NAME");
			table.setPkName(primaryKeys.getString("PK_NAME"));

		}
		table.setPkCloumnName(pkCloumnName);
	}

	public List<Table> getTables() {
		return tables;
	}

	void start() {
		// 获取表
		getTablesFromDb();
		List<Table> l = this.getTables();
		for (int i = 0; i < l.size(); i++) {
			Table table = (Table) l.get(i);
			// 获取列与主键
			try {
				getColumnsByTable(table);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 检查SEQ是否存在，不存在创建
			// createSequence(table.getName());
		}

	}

	@Override
	protected void excute() {
		// TODO Auto-generated method stub
		start();
	}

	List<Table> tables;

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	@Override
	protected void excute(Object o) {
		// TODO Auto-generated method stub
		String tablename = (String) o;
		this.setTablename(tablename);
		start();
	}

}
