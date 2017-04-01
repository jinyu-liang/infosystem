package com.blue.ailk.db;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Table {
	String name;

	public String getSeqSchem() {
		return seqSchem;
	}

	public void setSeqSchem(String seqSchem) {
		this.seqSchem = seqSchem;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	String comment;

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	String schem;
	int maxColumnNameSize;
	String pkName;
	String[] pkCloumnName;
	String seqSchem;
	String seq;
	public Map<String, List<ImportedKey>> importedKeys = new Hashtable<String, List<ImportedKey>>();
	public Map<String, List<Index>> indexs = new Hashtable<String, List<Index>>();
	public Map<String, List<UniqueKey>> uniqueKeys = new Hashtable<String, List<UniqueKey>>();
	public List<Column> columns = new ArrayList<Column>();

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String[] getPkCloumnName() {
		return pkCloumnName;
	}

	public void setPkCloumnName(String[] pkCloumnName) {
		this.pkCloumnName = pkCloumnName.clone();
	}

	public int getMaxColumnNameSize() {
		return maxColumnNameSize;
	}

	public void setMaxColumnNameSize(int maxColumnNameSize) {
		this.maxColumnNameSize = maxColumnNameSize;
	}

	public String getSchem() {
		return schem;
	}

	public void setSchem(String schem) {
		this.schem = schem;
	}

	public String getPartitions() {
		return partitions;
	}

	public void setPartitions(String partitions) {
		this.partitions = partitions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	String partitions; // 分表区SQL
}
