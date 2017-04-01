package com.blue.ailk.db;

public class Index {
    boolean nonUnique ;//非唯一索引(Can index values be non-unique. false when TYPE is  tableIndexStatistic   )   
    String indexQualifier;//索引目录（可能为空）   
    String indexName ;//索引的名称   
    short type;//索引类型   
    short ordinalPosition ;//在索引列顺序号   
    String columnName ;//列名   
    String ascOrDesc ;//列排序顺序:升序还是降序   
    int cardinality ;   //基数   
	public boolean isNonUnique() {
		return nonUnique;
	}
	public void setNonUnique(boolean nonUnique) {
		this.nonUnique = nonUnique;
	}
	public String getIndexQualifier() {
		return indexQualifier;
	}
	public void setIndexQualifier(String indexQualifier) {
		this.indexQualifier = indexQualifier;
	}
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public short getOrdinalPosition() {
		return ordinalPosition;
	}
	public void setOrdinalPosition(short ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getAscOrDesc() {
		return ascOrDesc;
	}
	public void setAscOrDesc(String ascOrDesc) {
		this.ascOrDesc = ascOrDesc;
	}
	public int getCardinality() {
		return cardinality;
	}
	public void setCardinality(int cardinality) {
		this.cardinality = cardinality;
	}
}
