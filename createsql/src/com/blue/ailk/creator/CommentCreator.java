package com.blue.ailk.creator;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.blue.ailk.db.Column;
import com.blue.ailk.db.Table;

public class CommentCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger.getLogger(CommentCreator.class);



	@Override
	String createSQL(Table table, ConfigTable configTable) {
		LOG.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();
		String fullTableName = table.getSchem() + "." + table.getName();
		if (StringUtils.isNotEmpty(table.getComment())) {
			sb.append("comment on table ");
			sb.append(fullTableName);
			sb.append(" is '");
			sb.append(StringEscapeUtils.escapeSql(table.getComment()));
			sb.append("';");
			sb.append("\r\n");
		}

		for (int k = 0; k < table.columns.size(); k++) {
			Column col = table.columns.get(k);
			if (StringUtils.isNotEmpty(col.getComment())) {
				sb.append("comment on column ");
				sb.append(fullTableName);
				sb.append(".");
				sb.append(col.getName());
				sb.append(" is '");
				sb.append(StringEscapeUtils.escapeSql(col.getComment()));
				sb.append("';");
				sb.append("\r\n");
			}
		}
		return sb.toString();
	}
}
