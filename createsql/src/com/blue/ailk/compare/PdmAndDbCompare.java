package com.blue.ailk.compare;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.ailk.constant.ConfigConst;
import com.blue.ailk.db.Column;
import com.blue.ailk.db.IParser;
import com.blue.ailk.db.ParserFactory;
import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;
import com.blue.core.lang.io.TextUtils;

/**
 * 比较PDM与DB表差异 . <br>
 * 包括: <li>字段名</li> <li>字段类型</li> <li>字段长度</li> <li>字段必填</li>
 * 
 * @author ChrisChen
 * @version 1.0 , 2014-1-20
 */
public class PdmAndDbCompare {
	/**
	 * 日志 .
	 */
	private static final Logger LOG = Logger.getLogger(PdmAndDbCompare.class);
	Map<String, String> noLengthCloumnTypeHT = new Hashtable<String, String>();

	public void compare() {
		init();
		List<String> lstTableName = getTableNames();

		for (String tableName : lstTableName) {

			LOG.info("Deal : " + tableName);
			IParser pdmParser = ParserFactory
					.getFactory("com.blue.ailk.db.PdmParser");
			Table pdmTable = null;
			Table dbTable = null;
			List<Table> list = pdmParser.getTables(tableName,null);
			for (Table table : list) {
				pdmTable = table;

			}
			IParser dbParser = ParserFactory
					.getFactory("com.blue.ailk.db.DBParser");
			List<Table> dbList = dbParser.getTables(tableName,null);

			for (Table table : dbList) {
				dbTable = table;

			}

			if (pdmTable != null && dbTable != null) {
				compareColumns(pdmTable, dbTable);

			} else {
				if (pdmTable == null) {
					Log.compare("PDM文件中不存在 表 :" + tableName);
				}
				if (dbTable == null) {
					Log.compare("DB中不存在 表 :" + tableName);

				}
			}
		}
	}

	void init() {
		String[] noLengthCloumnType = AilkDbConfig.getInstance()
				.getStringArray(ConfigConst.NO_LEN_COLUMN);
		for (String column : noLengthCloumnType) {
			noLengthCloumnTypeHT
					.put(column.toLowerCase(), column.toLowerCase());
		}
	}

	private List<String> getTableNames() {
		List<String> lstTableName = new ArrayList<String>();
		String[] arrayDomains = AilkDbConfig.getInstance().getStringArray(
				"needCompareDomain.domainKey");
		for (String keyName : arrayDomains) {

			String[] tableNames = AilkDbConfig.getInstance().getStringArray(
					keyName + ".tables.table");
			for (int i = 0; i < tableNames.length; i++) {
				lstTableName.add(tableNames[i]);
			}

		}
		return lstTableName;
	}

	private void compareColumns(Table pdmTable, Table dbTable) {

		for (Column pdmColumn : pdmTable.columns) {
			boolean isExist = false;
			for (Column dbColumn : dbTable.columns) {
				if (pdmColumn.getName().equalsIgnoreCase(dbColumn.getName())) {
					isExist = true;
					if (pdmColumn.getTypeName().equalsIgnoreCase(
							dbColumn.getTypeName())) {
						compareColumnLength(pdmTable, pdmColumn, dbColumn);
						compareColumnNullable(pdmTable, pdmColumn, dbColumn);
						compareDefaultValue(pdmTable, pdmColumn, dbColumn);
					} else {
						Log.compare("列类型不一致 : " + pdmTable.getSchem() + "."
								+ pdmTable.getName() + "."
								+ pdmColumn.getName());
					}

				}
			}
			if (!isExist) {
				Log.compare("列 : " + pdmTable.getSchem() + "."
						+ pdmTable.getName() + "." + pdmColumn.getName()
						+ "  在DB中不存在.");
			}
		}

		for (Column dbColumn : dbTable.columns) {
			boolean isExist = false;
			for (Column pdmColumn : pdmTable.columns) {
				if (dbColumn.getName().equalsIgnoreCase(pdmColumn.getName())) {
					isExist = true;
				}
			}
			if (!isExist) {
				Log.compare("列 : " + dbTable.getSchem() + "."
						+ dbTable.getName() + "." + dbColumn.getName()
						+ "  在PDM中不存在.");

			}
		}
	}

	private void compareDefaultValue(Table pdmTable, Column pdmColumn,
			Column dbColumn) {
		if (StringUtils.isNotEmpty(pdmColumn.getDefaultValue())) {
			if (StringUtils.isEmpty(dbColumn.getDefaultValue())) {
				Log.compare("默认值不一致， db不存在 : " + pdmTable.getSchem() + "."
						+ pdmTable.getName() + "." + pdmColumn.getName());
				TextUtils.write("e:/alert.txt", "ALTER TABLE "+pdmTable.getSchem() + "."
						+ pdmTable.getName()+"　MODIFY "+pdmColumn.getName()+"　DEFAULT "+pdmColumn.getDefaultValue()+" ; ", true);
				
			}
		} else {
			if (StringUtils.isNotEmpty(dbColumn.getDefaultValue())) {
				if (StringUtils.isEmpty(pdmColumn.getDefaultValue())) {
					Log.compare("默认值不一致， pdm不存在 : " + pdmTable.getSchem() + "."
							+ pdmTable.getName() + "." + pdmColumn.getName());
				}
			}
		}

	}

	private void compareColumnNullable(Table pdmTable, Column pdmColumn,
			Column dbColumn) {
		if (pdmColumn.getNullable() != dbColumn.getNullable()) {
			Log.compare("列必填项一致 : " + pdmTable.getSchem() + "."
					+ pdmTable.getName() + "." + pdmColumn.getName());
		}
	}

	private void compareColumnLength(Table pdmTable, Column pdmColumn,
			Column dbColumn) {
		if (!noLengthCloumnTypeHT.containsKey(pdmColumn.getTypeName()
				.toLowerCase())
				&& pdmColumn.getColumnSize() != dbColumn.getColumnSize()) {
			Log.compare("列长度不一致 : " + pdmTable.getSchem() + "."
					+ pdmTable.getName() + "." + pdmColumn.getName());
		}
	}
}
