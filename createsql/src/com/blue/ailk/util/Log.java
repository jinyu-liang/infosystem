package com.blue.ailk.util;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.ailk.ui.CreateSqlUI;
import com.blue.core.lang.io.TextUtils;

public class Log {
	public static String saveDir = AilkDbConfig.TEMP_FOLDER_NAME;
	public final static int INFO = 1;
	public final static int ERROR = 2;
	public final static int COMPARE = 3;

	public static void info(String logContent) {

	

		String time = new java.text.SimpleDateFormat("hh:mm:ss.S")
				.format(new Date());
		printToTable(time, logContent);
		TextUtils.write(saveDir + "/info.log", time + "/ " + "I: "
				+ logContent, true);
	}

	public static void error(String logContent) {
		String time = new java.text.SimpleDateFormat("hh:mm:ss.S")
				.format(new Date());
		printToTable(time, logContent);
		TextUtils.write(saveDir + "/error.log", time + "/ " + "E: "
				+ logContent, true);
	}

	public static void compare(String logContent) {
		String time = new java.text.SimpleDateFormat("hh:mm:ss.S")
				.format(new Date());
		printToTable(time, logContent);
		TextUtils.write(saveDir + "/compareResult.log", time + "/ "
				+ logContent, true);
	}

	public static void printToTable(String time, String msg) {
		DefaultTableModel model = (DefaultTableModel) CreateSqlUI.logTable
				.getModel();

		model.insertRow(0, new Object[] { time, msg });

	}
}
