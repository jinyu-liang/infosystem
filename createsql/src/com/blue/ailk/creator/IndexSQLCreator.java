package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.blue.ailk.db.Index;
import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;

public class IndexSQLCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger.getLogger(IndexSQLCreator.class);

	@Override
	String createSQL(Table table, ConfigTable configTable) {

		LOG.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();
		if (table.indexs.isEmpty()) {
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

			if (key.length() > 30) {
				Log.error("Index : " + key
						+ ": identifier is too long .Length:" + key.length());

			}

			sb.append("create index ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(key);
			sb.append(" on ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(table.getName());
			sb.append("(");
			sb.append(StringUtils.join(indexs, ','));
			sb.append(")\r\ntablespace DATA01");
			sb.append("\r\nstorage\n  (\r\n    Initial 5K\r\n  );");
			sb.append("\r\n\r\n");
		}

		sb.append("\r\n\r\n");
		return sb.toString();
	}
}
