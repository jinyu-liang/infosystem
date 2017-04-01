package com.blue.ailk.creator;

import org.apache.log4j.Logger;

import com.blue.ailk.db.Table;

public class TruncateSQLCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger
			.getLogger(TruncateSQLCreator.class);


	@Override
	String createSQL(Table table, ConfigTable configTable) {
		LOG.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();
		sb.append("truncate table ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(";");
		sb.append("\r\n");
		return sb.toString();
	}
}
