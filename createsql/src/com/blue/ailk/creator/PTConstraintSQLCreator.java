package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.blue.ailk.constant.ConfigConst;
import com.blue.ailk.db.Table;
import com.blue.ailk.db.UniqueKey;
import com.blue.ailk.util.Log;

public class PTConstraintSQLCreator extends AbstractPt {


	@Override
	String createPTSQL(Table table, String ptTableNameSuffix,ConfigTable configTable) {
		String ptTableName = table.getSchem() + "." + table.getName() + "_"
				+ ptTableNameSuffix;
		StringBuffer sb = new StringBuffer();
		if (table.getPkName() == null || table.getPkName().trim().equals("")) {
			Log.info(table.getName() + "不存在主键...");
			return sb.toString();
		}
		String pkName = table.getPkName().replaceAll("_", "")
				+ ptTableNameSuffix.replaceAll("_", "");
		sb.append("/* --- Table: ");
		sb.append(ptTableName);

		if (pkName.length() > 30) {

			sb.append(" identifier is too long");
			Log.error("PK : " + pkName + ": identifier is too long .Length:"
					+ pkName.length());

		}
		String tblSpace = ConfigConst.TABLE_SPACE;

		sb.append(" --- */");
		sb.append("\r\n\r\n");
		sb.append("alter table ");
		sb.append(ptTableName);
		sb.append("\r\n");
		sb.append("  add constraint ");
		sb.append(pkName);
		sb.append(" primary key (");
		sb.append(StringUtils.join(table.getPkCloumnName(), ','));
		sb.append(")\r\n  using index tablespace "+tblSpace);
		sb.append("\r\nstorage\n  (\r\n    Initial 5K\r\n  );");
		sb.append("\r\n\r\n");
	
		
		
		Set<Entry<String, List<UniqueKey>>> iter = table.uniqueKeys.entrySet();
		for (Map.Entry<String, List<UniqueKey>> entry : iter)

		{
			String key = entry.getKey();
			List<UniqueKey> value = entry.getValue();
			String[] uniqueKeys = new String[value.size()];
			for (int i = 0; i < value.size(); i++) {
				UniqueKey uniqueKey = value.get(i);
				uniqueKeys[i] = uniqueKey.getColumnName();

			}
			if (key.length() > 30) {
				Log.error("UniqueKey : " + key
						+ ": identifier is too long .Length:" + key.length());

			}
			String keyName = key.replaceAll("_", "")
					+ ptTableNameSuffix.replaceAll("_", "");
			sb.append("alter table ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(table.getName());
			sb.append("\r\n");
			sb.append("  add constraint ");
			sb.append(keyName);
			sb.append("  unique ");
			sb.append("(");
			sb.append(StringUtils.join(uniqueKeys, ','));
			sb.append(")\r\n  using index tablespace "+tblSpace);
			sb.append("\r\nstorage\n  (\r\n    Initial 5K\r\n  );");
			sb.append("\r\n\r\n");

		}

		return sb.toString().replaceAll("_2014", "_2015");
	}


}
