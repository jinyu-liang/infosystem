package com.blue.ailk.creator;

import org.apache.log4j.Logger;

import com.blue.ailk.db.Table;

public class DropTblSQLCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger.getLogger(DropTblSQLCreator.class);



	@Override
	String createSQL(Table table, ConfigTable configTable) {
		LOG.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();
		sb.append("/* --- Table: ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(" --- */");
		sb.append("\r\n\r\n");
		sb.append("drop table ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(" cascade constraints purge;");
		sb.append("\r\n\r\n");
		return sb.toString();

	}
}
