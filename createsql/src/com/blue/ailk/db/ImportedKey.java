package com.blue.ailk.db;


public class ImportedKey {
	String pkTableCat;// 主键表的目录（可能为空）
	String pkTableSchem;// 主键表的架构（可能为空）
	String pkTableName;// 主键表名
	String pkColumnName;// 主键列名

	String fkTableCat;// 外键的表的目录（可能为空）出口（可能为null）
	String fkTableSchem;// 外键表的架构（可能为空）出口（可能为空）
	String fkTableName;// 外键表名
	String fkColumnName;// 外键列名

	String fkName;// 外键的名称（可能为空）
	String pkName;// 主键的名称（可能为空）
	int updateRule;

	public int getUpdateRule() {
		return updateRule;
	}

	public void setUpdateRule(int updateRule) {
		this.updateRule = updateRule;
	}

	public int getDelRule() {
		return delRule;
	}

	public void setDelRule(int delRule) {
		this.delRule = delRule;
	}

	int delRule;
	int keySeq;

	public int getKeySeq() {
		return keySeq;
	}

	public void setKeySeq(int keySeq) {
		this.keySeq = keySeq;
	}

	short deferRability;

	public String getPkTableCat() {
		return pkTableCat;
	}

	public void setPkTableCat(String pkTableCat) {
		this.pkTableCat = pkTableCat;
	}

	public String getPkTableSchem() {
		return pkTableSchem;
	}

	public void setPkTableSchem(String pkTableSchem) {
		this.pkTableSchem = pkTableSchem;
	}

	public String getPkTableName() {
		return pkTableName;
	}

	public void setPkTableName(String pkTableName) {
		this.pkTableName = pkTableName;
	}

	public String getPkColumnName() {
		return pkColumnName;
	}

	public void setPkColumnName(String pkColumnName) {
		this.pkColumnName = pkColumnName;
	}

	public String getFkTableCat() {
		return fkTableCat;
	}

	public void setFkTableCat(String fkTableCat) {
		this.fkTableCat = fkTableCat;
	}

	public String getFkTableSchem() {
		return fkTableSchem;
	}

	public void setFkTableSchem(String fkTableSchem) {
		this.fkTableSchem = fkTableSchem;
	}

	public String getFkTableName() {
		return fkTableName;
	}

	public void setFkTableName(String fkTableName) {
		this.fkTableName = fkTableName;
	}

	public String getFkColumnName() {
		return fkColumnName;
	}

	public void setFkColumnName(String fkColumnName) {
		this.fkColumnName = fkColumnName;
	}

	public String getFkName() {
		return fkName;
	}

	public void setFkName(String fkName) {
		this.fkName = fkName;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public short getDeferRability() {
		return deferRability;
	}

	public void setDeferRability(short deferRability) {
		this.deferRability = deferRability;
	}
}
