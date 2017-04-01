package com.blue.ailk.creator;

import org.apache.commons.lang.StringUtils;

import com.blue.ailk.constant.ConfigConst;
import com.blue.ailk.db.Column;
import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;
import com.blue.core.lang.io.TextUtils;

public class BaseSQLCreator extends AbstractBase {

	@Override
	String createSQL(Table table, ConfigTable configTable) {
		
		Log.info("Deal table:" + table.getSchem() + "." + table.getName());
		StringBuffer sb = new StringBuffer();
		sb.append("/* --- Table: ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(" --- */");
		sb.append("\r\n\r\n");
		sb.append("--drop table ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());
		sb.append(" cascade constraints purge;");
		sb.append("\r\n");
		sb.append("create table ");
		sb.append(table.getSchem());
		sb.append(".");
		sb.append(table.getName());

		sb.append("\r\n(");

		for (int k = 0; k < table.columns.size(); k++) {
			Column col = table.columns.get(k);
			columnTypes.put(col.getTypeName(), col.getTypeName());
			sb.append("\r\n  ");
			int bitSize = table.getMaxColumnNameSize() - col.getName().length();
			bitSize++;
			if (specWords.containsKey(col.getName().toLowerCase())) {
				sb.append("\"" + col.getName() + "\"");
			} else {
				sb.append(col.getName());
			}
			for (int n = 0; n < bitSize; n++) {
				sb.append(" ");
			}

			sb.append(col.getTypeName());
			if (!noLengthCloumnTypeHT.containsKey(col.getTypeName()
					.toLowerCase()) && col.getColumnSize() > 0) {
				sb.append("(");
				sb.append(col.getColumnSize());
				sb.append(")");
			}

			if (StringUtils.isNotEmpty(col.getDefaultValue())) {
				if (col.getTypeName().equalsIgnoreCase("VARCHAR2")
						|| col.getTypeName().equalsIgnoreCase("CHAR")
						|| col.getTypeName().equalsIgnoreCase("NVARCHAR2")) {
					if (col.getTypeName().startsWith("'")) {
						Log.error("默认值非法  " + table.getName());
					}
					sb.append(" default '");
					sb.append(col.getDefaultValue());
					sb.append("'");
				} else {
					if (col.getTypeName().equalsIgnoreCase("NUMBER")
							|| col.getTypeName().equalsIgnoreCase("DATE")) {

					} else {
						Log.error("存在其他类型 " + col.getTypeName());
						Log.error("table : " + table.getName());
					}
					sb.append(" default  ");
					sb.append(col.getDefaultValue());
				}
			}
			if (col.getNullable() == 0) {
				sb.append(" not null");
			}
			String checkName = "CKC_" + col.getName() + "_" + table.getName();
			if (StringUtils.isNotEmpty(col.getLowValue())
					|| StringUtils.isNotEmpty(col.getHighValue())) {
				if (checkName.length() > 30) {
					String[] cn = checkName.split("_");
					String[] newCN = new String[cn.length - 1];
					System.arraycopy(cn, 0, newCN, 0, cn.length - 1);
					checkName = StringUtils.join(newCN, "_");
					if (checkName.length() > 30) {
						checkName = checkName.substring(0, 30);
					}
				}
			}
			if (StringUtils.isNotEmpty(col.getLowValue())
					&& StringUtils.isNotEmpty(col.getHighValue())) {
				sb.append("    constraint " + checkName + " check ("
						+ col.getName() + " between " + col.getLowValue()
						+ " and " + col.getHighValue() + ")");
			} else {
				if (StringUtils.isNotEmpty(col.getLowValue())) {
					sb.append("    constraint " + checkName + " check ("
							+ col.getName() + " >= " + col.getLowValue() + ")");
				}
				if (StringUtils.isNotEmpty(col.getHighValue())) {
					sb.append("    constraint " + checkName + " check ("
							+ col.getName() + " = >" + col.getHighValue() + ")");
				}
			}

			if (k < table.columns.size() - 1) {
				sb.append(",");
			}
			if (table.getName().length() > 30) {
				Log.error("Table : " + table.getName()
						+ ": identifier is too long .Length:"
						+ table.getName().length());

			}
		}

		sb.append("\r\n)");
		if (StringUtils.isNotEmpty(table.getPartitions())) {
			sb.append("\r\n");
			sb.append(table.getPartitions());
		}
		String tablespace = ConfigConst.TABLE_SPACE;
		sb.append("\r\ntablespace " + tablespace
				+ "\n  storage\n  (\r\n    Initial 5K\r\n  );");
		sb.append("\r\n\r\n");
		return sb.toString();
	}
}
