package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.blue.ailk.db.Table;
import com.blue.ailk.db.UniqueKey;

public class DropConstraintSQLCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger
			.getLogger(DropConstraintSQLCreator.class);

	


	@Override
	String createSQL(Table table, ConfigTable configTable) {
		LOG.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();
		if (table.getPkName() == null || table.getPkName().trim().equals("")) {
			LOG.info(table.getName() + "不存在主键...");
			return sb.toString();
		}
		sb.append("/* --- Table: ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(" --- */");
		sb.append("\r\n\r\n");

		sb.append("alter table ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append("\r\n");
		sb.append("  drop constraint ");
		sb.append(table.getPkName());

		sb.append(";");

		sb.append("\r\n\r\n");
		
		Set<Entry<String, List<UniqueKey>>> iter = table.uniqueKeys.entrySet();
		for (Map.Entry<String, List<UniqueKey>> entry : iter)

		{
			String key = entry.getKey();
			
			sb.append("alter table ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(table.getName());
			sb.append("\r\n");
			sb.append("  drop constraint ");
			sb.append(key);

			sb.append(";");

			sb.append("\r\n\r\n");
			
		}
		
		
		
		
		return sb.toString();
	}

}
