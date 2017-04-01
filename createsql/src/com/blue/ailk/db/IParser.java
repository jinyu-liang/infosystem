package com.blue.ailk.db;

import java.util.List;

public interface IParser {
	/**
	 * 获取表结构 .
	 * 
	 * @param tablename
	 *            表名
	 * @param paramter
	 *            参数
	 * @return 表结构
	 */
	public List<Table> getTables(String tablename, IParameter paramter);

	/**
	 * 获取SQUENCE .
	 * 
	 * @param paramter
	 *            参数
	 * @return SQUENCE 结构
	 */
	public List<Sequnce> getSequnces(IParameter parameter);
}
