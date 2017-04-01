package com.blue.ailk.creator;

import com.blue.ailk.db.Table;

public class PTDropBaseSQLCreator extends AbstractPt {


	@Override
	String createPTSQL(Table table, String ptTableNameSuffix,ConfigTable configTable) {
		StringBuffer sb = new StringBuffer();
		String ptTableName = table.getSchem() + "." + table.getName() + "_"
				+ ptTableNameSuffix;

		sb.append("drop table ");
		sb.append(ptTableName);
		sb.append(" cascade constraints purge;");		
		sb.append("\r\n\r\n");
		return sb.toString();
	}



}
