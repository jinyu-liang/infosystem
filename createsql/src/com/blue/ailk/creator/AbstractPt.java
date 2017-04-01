package com.blue.ailk.creator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;

public abstract class AbstractPt extends AbstractBase {

	@Override
	String createSQL(Table table, ConfigTable configTable) {
		StringBuffer rstSQL=new StringBuffer();
		if (configTable.getPtType().contains("&")) {
		
			String[] newPtType = configTable.getPtType().split("&");
			String[] newPtValue = configTable.getPtValue().split("&");
			for (int i = 0; i < newPtType.length; i++) {
				ConfigTable ctbl = new ConfigTable();
				ctbl.setName(configTable.getName());
				ctbl.setPtType(newPtType[i]);
				ctbl.setPtValue(newPtValue[i]);
				rstSQL.append(deal(table, ctbl));
			}

		} else {

			rstSQL.append(deal(table, configTable));
		}
		return rstSQL.toString();

	}

	private String deal(Table table, ConfigTable configTable) {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isEmpty(configTable.getPtType())) {
			return sb.toString();
		}
		String ptType = configTable.getPtType();
		String ptValue = configTable.getPtValue();
		String splitChar = "_";
		String[] arrayPtType = ptType.split(splitChar);
		String[] arrayPtValue = ptValue.split(splitChar);
		List<String> listPtName = new ArrayList<String>();

		for (int k = 0; k < arrayPtType.length; k++) {
			List<String> tmpListPtName = new ArrayList<String>();
			List<String> curListPtName = new ArrayList<String>();
			if (arrayPtType[k].equalsIgnoreCase("date")) {
				String[] dateTypeValues = arrayPtValue[k].split("\\|");
				if (Integer.parseInt(dateTypeValues[0]) > Integer
						.parseInt(dateTypeValues[1])) {
					Log.info(table.getSchem() + "." + table.getName()
							+ ":时间设置出错.");
					break;
				}

				Calendar cal = Calendar.getInstance();

				if (dateTypeValues[0].length() == 4) {
					cal.set(Calendar.YEAR, Integer.parseInt(dateTypeValues[0]));

					for (int t = 0; t < 100000000; t++) {
						String dateValue = new SimpleDateFormat("yyyy").format(
								cal.getTime()).toString();
						curListPtName.add(dateValue);
						if (dateValue.equals(dateTypeValues[1])) {
							break;
						}
						cal.add(Calendar.YEAR, 1);

					}

				} else if (dateTypeValues[0].length() == 6) {

					cal.set(Calendar.YEAR,
							Integer.parseInt(dateTypeValues[0].substring(0, 4)));
					cal.set(Calendar.MONTH,
							Integer.parseInt(dateTypeValues[0].substring(4, 6)) - 1);

					for (int t = 0; t < 100000000; t++) {
						String dateValue = new SimpleDateFormat("yyyyMM")
								.format(cal.getTime()).toString();
						curListPtName.add(dateValue);
						if (dateValue.equals(dateTypeValues[1])) {
							break;
						}
						cal.add(Calendar.MONTH, 1);

					}

				} else if (dateTypeValues[0].length() == 8) {
					cal.set(Calendar.YEAR,
							Integer.parseInt(dateTypeValues[0].substring(0, 4)));
					cal.set(Calendar.MONTH,
							Integer.parseInt(dateTypeValues[0].substring(4, 6)) - 1);
					cal.set(Calendar.DAY_OF_MONTH,
							Integer.parseInt(dateTypeValues[0].substring(6, 8)));
					for (int t = 0; t < 100000000; t++) {
						String dateValue = new SimpleDateFormat("yyyyMMdd")
								.format(cal.getTime()).toString();
						curListPtName.add(dateValue);
						if (dateValue.equals(dateTypeValues[1])) {
							break;
						}
						cal.add(Calendar.DAY_OF_YEAR, 1);

					}
				}

			} else if (arrayPtType[k].equalsIgnoreCase("array")) {
				String[] arrayValue = arrayPtValue[k].split("\\|");
				for (String array : arrayValue) {
					curListPtName.add(array);
				}

			} else if (arrayPtType[k].equalsIgnoreCase("mod")) {
				for (int n = 0; n < Integer.parseInt(arrayPtValue[k]); n++) {
					curListPtName.add(String.valueOf(n));
				}

			} else {
				curListPtName.add(arrayPtValue[k]);
			}
			if (k == 0) {
				listPtName.addAll(curListPtName);
			} else {
				for (int n1 = 0; n1 < listPtName.size(); n1++) {
					for (int n2 = 0; n2 < curListPtName.size(); n2++) {
						tmpListPtName.add(listPtName.get(n1) + splitChar
								+ curListPtName.get(n2));
					}
				}
				listPtName.clear();
				listPtName.addAll(tmpListPtName);
			}

		}

		Log.info("Deal table:" + table.getSchem() + "." + table.getName());

		for (int t = 0; t < listPtName.size(); t++) {

			sb.append(createPTSQL(table, listPtName.get(t).toString(),configTable));
		}
		return sb.toString();
	}

	/**
	 * /** 重成SQL文件里内容的格式结构
	 * 
	 * @param tbl
	 *            表结构.
	 * @param ptTableNameSuffix
	 *            分表名后缀部分.如分表名为ad.ca_bill_0_201201,那么ptTableNameSuffix=0_201201,
	 * 
	 * @return 内容
	 */
	abstract String createPTSQL(Table table, String ptTableNameSuffix,ConfigTable configTable);
}