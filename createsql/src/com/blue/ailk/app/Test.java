package com.blue.ailk.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.blue.ailk.db.PdmTools;
import com.blue.core.lang.io.TextUtils;

public class Test {
	private static final Logger LOG = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		Hashtable tbl = new Hashtable();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"D:/TABLES_20141119.csv")));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				String []splitRow=s.split(",");
				System.out.println(splitRow[3]);
				tbl.put(splitRow[1].replaceAll("\"", "")+"."+splitRow[2].replaceAll("\"", ""), splitRow[3].replaceAll("\"", ""));
			}
			br.close();

		} catch (IOException e) {
			e.getStackTrace();
		}
		
		if(1==1){
			return;
		}
		
		Hashtable alltbl = new Hashtable();

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"D:/已经清理表.txt")));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				 alltbl.put(s.toUpperCase().trim(), s.toUpperCase().trim());

			}
			br.close();

		} catch (IOException e) {
			e.getStackTrace();
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"D:/疑似临时表.txt")));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				if (!alltbl.containsKey(s.toUpperCase().trim())) {
					System.out.println(s.toUpperCase());
				}

			}
			br.close();

		} catch (IOException e) {
			e.getStackTrace();
		}

		if (1 == 1) {
			return;
		}

		String[] pdmFilenames = { "d:/ailk_verisbilling_data_model_v3.pdm" };
		List<String> pdmTblnames = PdmTools.getTableNames(pdmFilenames);

		List<String> txtLst = TextUtils.readLine("d:/tbl.txt");
		for (String txt : pdmTblnames) {
			boolean exist = false;
			for (String tblname : txtLst) {
				if (txt.trim().equalsIgnoreCase(tblname.trim())) {
					exist = true;
					break;
				}
			}
			if (!exist) {
				System.out.println(txt);
			}

		}

		// for (String tblname : pdmTblnames) {
		//
		// System.out.println("<table>" + tblname + "</table>");
		//
		// }
		// try {
		//
		// XMLConfiguration xmlConfig = new XMLConfiguration(xmlFilename);
		// if (!xmlConfig.getRoot().getAttributes("table").isEmpty()) {
		// String table = xmlConfig.getRoot().getAttributes("table")
		// .get(0).getValue().toString();
		// String tblName = null;
		// if (!xmlConfig.getRoot().getAttributes("schema").isEmpty()) {
		// String schema = xmlConfig.getRoot()
		// .getAttributes("schema").get(0).getValue()
		// .toString();
		// tblName = schema + "." + table;
		// } else {
		// tblName = table;
		//
		// }
		// TextUtils.write("e:/tbl.txt", tblName, true);
		//
		// }
		//
		// } catch (ConfigurationException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }

		// LOG.info("start...............");
		// List<String> lstPrefix = new ArrayList<String>();
		// lstPrefix.add("PM_");
		// lstPrefix.add("RS_");
		// lstPrefix.add("SYS_");
		// List<String> lstResult = PdmTools.getTableNameStartWidth(lstPrefix);
		// for (String result : lstResult) {
		// //LOG.info(result);
		// System.out.println("<table>"+result+"</table>");
		// }

		// new BaseSQLCreator().create();
		// String nodeName="needCreateSQLDomain.domainKey..ABC";
		// String str = AilkDbConfig.getInstance().getString(
		// nodeName);
		// LOG.info(str);

		// List<HierarchicalConfiguration> nodes = AilkPdm.getInstance()
		// .configurationsAt(TABLE_PATH);
		// for (HierarchicalConfiguration node : nodes) {
		// String name = node.getString("a:Name");
		// boolean isExist = false;
		// for (String table : arrayTables) {
		// String[] arrTable = table.split("\\.");
		// if (name.equalsIgnoreCase(arrTable[1])) {
		// isExist = true;
		// break;
		// }
		//
		// }
		// if (!isExist) {
		// LOG.info(name);
		// }
		//
		// }
	}

	private static void getTable() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"F:/资料/亚信资料/模割检查/模割执行/JD_CA_DAILY_TXT_TRUN.txt")));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				String tbl = s.toUpperCase().replaceAll("TRUNCATE TABLE ", "")
						.replaceAll(";", "");
				String sql="INSERT INTO JD.DBADT_DATA_AUDIT(TABLE_NAME, SO_DATE)VALUES('"+tbl+"',sysdate);";
				TextUtils.write("F:/资料/亚信资料/模割检查/模割执行/统计清除数据/TEMP.txt", sql, true);
				System.out
						.println(sql);
				
				

			}
			br.close();

		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	private static void createTRUNCount() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"F:/资料/亚信资料/模割检查/模割执行/AD_CA_FREERES_DAY_TRUN.txt")));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				String tbl = s.toUpperCase().replaceAll("TRUNCATE TABLE ", "")
						.replaceAll(";", "");
				String sql="INSERT INTO JD.DBADT_DATA_AUDIT(TABLE_NAME, DATA_COUNT, SO_DATE) SELECT '"
						+ tbl
						+ "' TABLE_NAME, COUNT(*) DATA_COUNT, sysdate SO_DATE  FROM "
						+ tbl + ";";
				TextUtils.write("F:/资料/亚信资料/模割检查/模割执行/统计清除数据/AD_CA_FREERES_DAY_TRUN_COUNT.txt", sql, true);
				System.out
						.println(sql);
				
				

			}
			br.close();

		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePath
	 *            String 文件路径
	 * 
	 * 
	 */
	public static String readTxt(String filePath) {
		String rtn = null;
		File txtFile = new File(filePath);
		if (!txtFile.exists()) {
			return rtn;
		}

		try {
			BufferedReader br = new BufferedReader(new FileReader(txtFile));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.getStackTrace();
		}
		return rtn;
	}

	public static void sort(int[] myArray) {
		int index = 0;
		// 取长度最长的词组 -- 冒泡法
		for (int j = 1; j < myArray.length; j++) {
			for (int i = 0; i < myArray.length - 1; i++) {
				index++;
				System.out.println(index);

				// 如果 myArray[i] > myArray[i+1] ，则 myArray[i] 上浮一位
				if (myArray[i] > myArray[i + 1]) {
					int temp = myArray[i];
					myArray[i] = myArray[i + 1];
					myArray[i + 1] = temp;
				}
			}
		}
	}
}
