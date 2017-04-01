package com.blue.ailk.creator;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.blue.ailk.db.ImportedKey;
import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;
import com.blue.core.lang.io.TextUtils;

public class FkSQLCreator extends AbstractBase {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger.getLogger(FkSQLCreator.class);

	@Override
	String createSQL(Table table, ConfigTable configTable) {

		
		LOG.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();

		if (table.importedKeys.isEmpty()) {
			return sb.toString();
		}

		sb.append("/* --- Table: ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(" --- */");
		sb.append("\r\n\r\n");
		Set<Entry<String, List<ImportedKey>>> iter = table.importedKeys
				.entrySet();
		for (Map.Entry<String, List<ImportedKey>> entry : iter)

		{
			String key = entry.getKey();
			List<ImportedKey> importedKeys = entry.getValue();
			sb.append("alter table ");
			sb.append(table.getSchem());
			sb.append(".");
			sb.append(table.getName());
			sb.append("\r\n");
			sb.append("  add constraint ");
			sb.append(key);
			sb.append(" foreign key (");
			String pkSchme = "";
			String pkTableName = "";
			String[] fkColumns = new String[importedKeys.size()];
			String[] pkColumns = new String[importedKeys.size()];
			for (int i = 0; i < importedKeys.size(); i++) {
				ImportedKey importedKey = importedKeys.get(i);
				fkColumns[i] = importedKey.getFkColumnName();
				pkColumns[i] = importedKey.getPkColumnName();
				pkSchme = importedKey.getPkTableSchem();
				pkTableName = importedKey.getPkTableName();
			}
			
			sb.append(StringUtils.join(fkColumns, ','));
			sb.append(")");
			sb.append("\r\n");
			sb.append("  references ");
			sb.append(pkSchme);
			sb.append(".");
			sb.append(pkTableName);
			sb.append(" (");
			sb.append(StringUtils.join(pkColumns, ','));
			sb.append(");");
			sb.append("\r\n");
			if (key.length() > 30) {
				Log.error("FK : " + key + ": identifier is too long .Length:"
						+ key.length());

			}
			if (!table.getSchem().equalsIgnoreCase(pkSchme)) {
				StringBuffer grantSQL = new StringBuffer();
				grantSQL.append("grant references on ");
				grantSQL.append(pkSchme + "." + pkTableName);
				grantSQL.append(" to ");
				grantSQL.append(table.getSchem());
				grantSQL.append(";");
				TextUtils.write(this.getScriptFilename() + ".grant.sql",
						grantSQL.toString(), true);
			}
		}
		sb.append("\r\n\r\n");

		return sb.toString();
	}

}
