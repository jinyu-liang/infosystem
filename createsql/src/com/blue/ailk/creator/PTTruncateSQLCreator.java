package com.blue.ailk.creator;

import com.blue.ailk.db.Table;

public class PTTruncateSQLCreator extends AbstractPt {


	@Override
	String createPTSQL(Table table, String ptTableNameSuffix,ConfigTable configTable) {
		StringBuffer sb = new StringBuffer();
		String ptTableName = table.getSchem() + "." + table.getName() + "_"
				+ ptTableNameSuffix;

		sb.append("truncate table ");
		sb.append(ptTableName);
		sb.append(";");		
		sb.append("\r\n");
		return sb.toString();
	}



}
