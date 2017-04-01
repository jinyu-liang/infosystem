package com.blue.ailk.util;

import java.util.Date;

import com.blue.core.lang.io.TextUtils;

public class LogThread implements Runnable {

	public LogThread(String logContent, int logLevel) {
		this.logContent = logContent;
		this.logLevel = logLevel;
	}

	@Override
	public void run() {
		String time = new java.text.SimpleDateFormat("hh:mm:ss.S")
				.format(new Date());
		switch (this.logLevel) {
		case Log.INFO:

			Log.printToTable(time, this.logContent);
			TextUtils.write(Log.saveDir + "/info.log", time + "/ " + "I: "
					+ this.logContent, true);
			break;
		case Log.ERROR:

			Log.printToTable(time, this.logContent);
			TextUtils.write(Log.saveDir + "/error.log", time + "/ " + "E: "
					+ this.logContent, true);
			break;
		case Log.COMPARE:

			Log.printToTable(time, this.logContent);
			TextUtils.write(Log.saveDir + "/compareResult.log", time + "/ "
					+ this.logContent, true);
			break;
		default:
		}
	}

	int logLevel;

	public int getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	private String logContent;

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

}
