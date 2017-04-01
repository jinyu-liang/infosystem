package com.ailk.ims.component;

import java.util.Date;

public class PmsUtils {
	public static Date getDefaultStartDate() {
		Date date = new Date();
		date.setYear(110);
		date.setMonth(0);
		date.setDate(1);
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(1);
		return date;
	}

	public static Date getDefaultEndDate() {
		Date date = new Date();
		date.setYear(130);
		date.setMonth(11);
		date.setDate(31);
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	}
}
