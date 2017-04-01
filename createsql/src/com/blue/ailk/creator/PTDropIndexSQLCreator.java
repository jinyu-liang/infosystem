package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.blue.ailk.db.Index;
import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;

public class PTDropIndexSQLCreator extends AbstractPt {


	@Override
	String createPTSQL(Table table, String ptTableNameSuffix,ConfigTable configTable) {
		String ptTableName = table.getSchem() + "." + table.getName() + "_"
				+ ptTableNameSuffix;
		StringBuffer sb = new StringBuffer();

		if(table.indexs.isEmpty()){
			return sb.toString();
		}
		sb.append("/* --- Table: ");
		sb.append(ptTableName);
		sb.append(" --- */");
		sb.append("\r\n\r\n");
		Set<Entry<String, List<Index>>> iter = table.indexs.entrySet();
		for (Map.Entry<String, List<Index>> entry : iter)

		{
			String key = entry.getKey();

			String indexKey = key.replaceAll("_", "")
					+ ptTableNameSuffix.replaceAll("_", "");
			if (indexKey.length() > 30) {
				Log.info(indexKey + ": identifier is too long .Length:"
						+ indexKey.length());
				
				sb.append("/* ---");
				sb.append(indexKey);
				sb.append(" identifier is too long --- */");
				sb.append("\r\n");
			}

			List<Index> value = entry.getValue();

			String[] indexs = new String[value.size()];
			for (int i = 0; i < value.size(); i++) {
				Index index = value.get(i);
				indexs[i] = index.getColumnName();

			}
			sb.append("drop index ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(indexKey);
			sb.append(";");
			sb.append("\r\n");
		}

		sb.append("\r\n");
		return sb.toString();
	}



}
