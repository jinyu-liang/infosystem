package com.blue.ailk.creator;

import org.apache.commons.lang.StringUtils;

import com.blue.ailk.constant.ConfigConst;
import com.blue.ailk.db.Column;
import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;

public class PTBaseSQLCreator extends AbstractPt {

	@Override
	String createPTSQL(Table table, String ptTableNameSuffix,
			ConfigTable configTable) {
		StringBuffer sb = new StringBuffer();
		String ptTableName = table.getSchem() + "." + table.getName() + "_"
				+ ptTableNameSuffix;
		// TODO Auto-generated method stub
		sb.append("/* --- Table: ");
		sb.append(ptTableName);
		if (ptTableName.substring(table.getSchem().length() + 1,
				ptTableName.length()).length() > 30) {
			Log.info(ptTableName
					+ ": identifier is too long .Length:"
					+ ptTableName.substring(table.getSchem().length() + 1,
							ptTableName.length()).length());
			sb.append(" identifier is too long");

			if (ptTableName.length() > 30) {
				Log.error("Table : " + ptTableName
						+ ": identifier is too long .Length:"
						+ ptTableName.length());

			}

		}

		sb.append(" --- */");
		sb.append("\r\n\r\n");
		sb.append("--drop table ");
		sb.append(ptTableName);
		sb.append(" cascade constraints purge;");
		sb.append("\r\n");
		sb.append("create table ");
		sb.append(ptTableName);

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
					.toLowerCase())) {
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
			String checkName = "CKC_" + col.getName() + "_" + table.getName()
					+ "_" + ptTableNameSuffix;
			checkName = checkName.replaceAll("_", "");
			if (StringUtils.isNotEmpty(col.getLowValue())
					|| StringUtils.isNotEmpty(col.getHighValue())) {
				if (checkName.length() > 30) {
					Log.error("check太长  ,table : " + table.getName());
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
		}

		sb.append("\r\n)");
		if (StringUtils.isNotEmpty(table.getPartitions())) {
			sb.append("\r\n");
			sb.append(table.getPartitions());
		}
		String tblSpace = ConfigConst.TABLE_SPACE;


		sb.append("\r\ntablespace " + tblSpace
				+ "\n  storage\n  (\r\n    Initial 5K\r\n  );");
		sb.append("\r\n\r\n");
		return sb.toString();
	}
}
