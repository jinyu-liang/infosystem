package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.blue.ailk.db.Table;
import com.blue.ailk.db.UniqueKey;
import com.blue.ailk.util.Log;

public class ConstraintSQLCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger
			.getLogger(ConstraintSQLCreator.class);



	@Override
	String createSQL(Table table, ConfigTable configTable) {
		// TODO Auto-generated method stub
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

		if (table.getPkCloumnName() == null
				|| table.getPkCloumnName().length == 0) {
			Log.error("Pk : " + table.getPkName() + " columns is null.");
		}

		sb.append("alter table ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append("\r\n");
		sb.append("  add constraint ");
		sb.append(table.getPkName());
		sb.append(" primary key (");
		sb.append(StringUtils.join(table.getPkCloumnName(), ','));
		sb.append(")\r\n  using index tablespace DATA01");
		sb.append("\r\nstorage\n  (\r\n    Initial 5K\r\n  );");
		sb.append("\r\n\r\n");

		if (table.getPkName().length() > 30) {
			Log.error("PK : " + table.getPkName()
					+ ": identifier is too long .Length:"
					+ table.getPkName().length());

		}

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
				Log.error("UniqueKey : " + key + ": identifier is too long .Length:"
						+ key.length());

			}
			
			sb.append("alter table ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(table.getName());
			sb.append("\r\n");
			sb.append("  add constraint ");
			sb.append(key);
			sb.append("  unique ");
			sb.append("(");
			sb.append(StringUtils.join(uniqueKeys, ','));
			sb.append(")\r\n  using index tablespace DATA01");
			sb.append("\r\nstorage\n  (\r\n    Initial 5K\r\n  );");
			sb.append("\r\n\r\n");

		}

		return sb.toString();
	}

}
