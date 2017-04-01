package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.blue.ailk.db.Table;
import com.blue.ailk.db.UniqueKey;
import com.blue.ailk.util.Log;

public class PTDropConstraintSQLCreator extends AbstractPt {


	@Override
	String createPTSQL(Table table, String ptTableNameSuffix,ConfigTable configTable) {
		String ptTableName = table.getSchem() + "." + table.getName() + "_"
				+ ptTableNameSuffix;
		StringBuffer sb = new StringBuffer();
		if (table.getPkName() == null || table.getPkName().trim().equals("")) {
			Log.info(table.getName() + "不存在主键...");
			return sb.toString();
		}
		sb.append("/* --- Table: ");
		sb.append(ptTableName);

		sb.append(" --- */");

		String pkName = table.getPkName().replaceAll("_", "")
				+ ptTableNameSuffix.replaceAll("_", "");
		if (pkName.length() > 30) {
			Log.info(pkName + ": identifier is too long .Length:"
					+ pkName.length());
			sb.append(" identifier is too long");
		}
		sb.append("\r\n\r\n");
		sb.append("alter table ");
		sb.append(ptTableName);
		sb.append("\r\n");
		sb.append("  drop constraint ");
		sb.append(pkName);
		sb.append(";");

		sb.append("\r\n\r\n");
		Set<Entry<String, List<UniqueKey>>> iter = table.uniqueKeys.entrySet();
		for (Map.Entry<String, List<UniqueKey>> entry : iter)

		{
			String key = entry.getKey();
			String keyName = key.replaceAll("_", "")
					+ ptTableNameSuffix.replaceAll("_", "");
			sb.append("alter table ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(table.getName());
			sb.append("\r\n");
			sb.append("  drop constraint ");
			sb.append(keyName);

			sb.append(";");

			sb.append("\r\n\r\n");
			
		}
		
		
		
		return sb.toString();
	}



}
