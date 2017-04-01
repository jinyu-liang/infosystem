package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.blue.ailk.db.Index;
import com.blue.ailk.db.Table;

public class DropIndexSQLCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger
			.getLogger(DropIndexSQLCreator.class);

	@Override
	String createSQL(Table table, ConfigTable configTable) {
		LOG.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();
		if(table.indexs.isEmpty()){
			return sb.toString();
		}
		
		sb.append("/* --- Table: ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(" --- */");
		sb.append("\r\n\r\n");
		Set<Entry<String, List<Index>>> iter = table.indexs.entrySet();
		for (Map.Entry<String, List<Index>> entry : iter)

		{
			String key = entry.getKey();
			List<Index> value = entry.getValue();

			String[] indexs = new String[value.size()];
			for (int i = 0; i < value.size(); i++) {
				Index index = value.get(i);
				indexs[i] = index.getColumnName();

			}
			sb.append("drop index ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(key);

			sb.append(";\r\n");
		}

		sb.append("\r\n\r\n");
		return sb.toString();
	}
}
