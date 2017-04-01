package com.blue.ailk.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;

import com.blue.core.config.Config;
import com.blue.core.exception.BaseException;

/**
 * 将系统config.xml的值读取本类中 .
 * 
 * @author ChrisChen
 * @version 1.0 , 2013-4-23
 */
public class AilkDbConfig extends Config {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger.getLogger(AilkDbConfig.class);
	/**
	 * 配置文件路径.
	 */
	// public static final String CONFIG_FILE_NAME = "ailk_db_config.xml";
	public static final String CONFIG_FILE_NAME = "E:/建表脚本/config/ailk_db_config.xml";
	public static final String TEMP_FOLDER_NAME = "c:/blue";
	/**
	 * 静态系统配置文件读取类 .
	 */
	private static AilkDbConfig config = null;

	/**
	 * 构造函数,并且将配置文件路径传给父类 .
	 */
	public AilkDbConfig() {
		super(CONFIG_FILE_NAME);

	}

	static {
		config = new AilkDbConfig();
	}

	/**
	 * 获取系统配置文件读取类 .
	 * 
	 * @return 配置文件读取类.
	 */
	public static final AilkDbConfig getInstance() {
		return config;
	}

	/**
	 * 获取系统配置文件读取类 .
	 * 
	 * @return 配置文件读取类.
	 */
	public final AilkDbConfig getConfig() {
		return config;
	}

	/**
	 * 入口程序.
	 * 
	 * @param args
	 *            .
	 */
	public static void main(final String[] args) {
		LOG.info(AilkDbConfig.getInstance().getString("createSQL.createSQL223.tables.table1110.sequence"));
	}



	/**
	 * 保存 配置文件.
	 * 
	 */
	public void save() {
		try {
			config.getXmlConfig().save();
		} catch (ConfigurationException e) {

			throw new BaseException(e);

		}
	}

	/**
	 * 设置文件key与value
	 * 
	 * @param key
	 * @param value
	 */
	public void setProperty(String key, Object value) {
		getXmlConfig().setProperty(key, value);
		save();
	}

}
