package com.blue.ailk.util;

import java.io.File;

import com.blue.ailk.constant.ConfigConst;

public class TableSpaceUtils {
	/**
	 * 表示为表空间..
	 */
	public static int OBJECT_TYPE_TABLE = 0;
	/**
	 * 表示为索引空间..
	 */
	public static int OBJECT_TYPE_INDEX = 1;

	public String getTableSpace(int objectType,String tablename) {
		if (new File(ConfigConst.TABLE_SPACE_CONFIG_FILE_NAME).exists()) {
			if (objectType == OBJECT_TYPE_TABLE) {
                  
			}
		} else {
			return ConfigConst.TABLE_SPACE;
		}
		return null;
	}

}
