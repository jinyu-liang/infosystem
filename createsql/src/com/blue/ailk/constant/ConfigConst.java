package com.blue.ailk.constant;

import com.blue.ailk.config.AilkDbConfig;

public class ConfigConst {
	/**
	 * 数据库表列不计算长度列的类型的KEY路径 .
	 */
	public final static String NO_LEN_COLUMN = "dataSource.noLengthCloumnType.columnType";
	public final static String PDM_FILE_NAME_KEY = "pdm.filename";

	public final static String DATA_SOURCE_SEQUENCE_KEY = "DBSEQUENCE";
	public final static String DB_KEY = "db";
	public final static String PDM_KEY = "pmd";
	public final static String CREATE_SQL_KEY = "createSQL";

	public final static String getDBPasswordKey(int sequece) {
		return getDBDataSourceKey(sequece) + ".password";
	}

	public final static String getDBUserKey(int sequece) {
		return getDBDataSourceKey(sequece) + ".user";
	}

	public final static String getDBJdbcUrlKey(int sequece) {
		return getDBDataSourceKey(sequece) + ".jdbcUrl";
	}

	public final static String getDBDriverClassKey(int sequece) {
		return getDBDataSourceKey(sequece) + ".driverClass";
	}

	public final static String getDBAliasKey(int sequece) {
		return getDBDataSourceKey(sequece) + ".alias";
	}

	public final static String getDBSequenceKey(int sequece) {
		return getDBDataSourceKey(sequece) + ".sequence";
	}

	public final static String getDBDataSourceKey(int sequece) {

		return DB_KEY + ".dataSource" + String.valueOf(sequece);
	}

	public final static String getTableKey(int createSQLSequece, int sequence) {

		return getCreateSQLKey(createSQLSequece) + ".tables.table"
				+ String.valueOf(sequence);
	}

	public final static String getTableCheckKey(int createSQLSequece,
			int sequence) {

		return getCreateSQLKey(createSQLSequece) + ".tables.table"
				+ String.valueOf(sequence) + ".check";
	}

	public final static String getTableNameKey(int createSQLSequece,
			int sequence) {

		return getCreateSQLKey(createSQLSequece) + ".tables.table"
				+ String.valueOf(sequence) + ".name";
	}

	public final static String getTablePtTypeKey(int createSQLSequece,
			int sequence) {

		return getCreateSQLKey(createSQLSequece) + ".tables.table"
				+ String.valueOf(sequence) + ".ptType";
	}

	public final static String getTablePtValueKey(int createSQLSequece,
			int sequence) {

		return getCreateSQLKey(createSQLSequece) + ".tables.table"
				+ String.valueOf(sequence) + ".ptValue";
	}

	public final static String getTableSequenceKey(int createSQLSequece,
			int sequence) {

		return getCreateSQLKey(createSQLSequece) + ".tables.table"
				+ String.valueOf(sequence) + ".sequence";
	}

	public final static String getCreateSQLSaveDirKey(int sequence) {
		return getCreateSQLKey(sequence) + ".saveDir";
	}

	public final static String getCreateSQLAliasKey(int sequence) {
		return getCreateSQLKey(sequence) + ".alias";
	}

	public final static String getCreateSQLFilenameSuffixKey(int sequence) {
		return getCreateSQLKey(sequence) + ".filenameSuffix";
	}

	public final static String getCreateSQLKey(int sequence) {

		return CREATE_SQL_KEY + ".createSQL" + String.valueOf(sequence);
	}

	public final static String getCreateSQLSequenceKey(int sequence) {
		return getCreateSQLKey(sequence) + ".sequence";
	}

	public final static String getCreateSQLSourceTypeKey(int sequence) {
		return getCreateSQLKey(sequence) + ".sourceType";
	}

	public final static String getCreateSQLSourceKey(int sequence) {
		return getCreateSQLKey(sequence) + ".source";
	}

	public final static String getPDMAliasKey(int sequence) {
		return getPDMKey(sequence) + ".alias";
	}

	public final static String getPDMFilesKey(int sequence) {
		return getPDMKey(sequence) + ".files";
	}

	public final static String getPDMSequenceKey(int sequence) {
		return getPDMKey(sequence) + ".sequence";
	}

	public final static String getPDMKey(int sequence) {

		return PDM_KEY + ".pmd" + String.valueOf(sequence);
	}

	public final static int getSequence() {
		int dbSequnce = 0;
		if (!AilkDbConfig.getInstance()
				.configurationsAt(ConfigConst.DATA_SOURCE_SEQUENCE_KEY)
				.isEmpty()) {
			dbSequnce = AilkDbConfig.getInstance().getInt(
					ConfigConst.DATA_SOURCE_SEQUENCE_KEY);
		}
		dbSequnce++;
		AilkDbConfig.getInstance().setProperty(
				ConfigConst.DATA_SOURCE_SEQUENCE_KEY, dbSequnce);
		return dbSequnce;
	}

	/**
	 * 默认表空间 ..
	 */
	public final static String TABLE_SPACE = "DATA01";
	/**
	 * 表空间配置信息存放的文件名 ..
	 */
	public final static String TABLE_SPACE_CONFIG_FILE_NAME = "D:/table_space.xlsx";
}
