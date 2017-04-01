package com.blue.ailk.config;

import com.blue.core.config.Config;

public class AilkConfig extends Config {

	/**
	 * 配置文件路径.
	 */
	private static final String CONFIG_FILE_NAME = "ailk_config.xml";
	/**
	 * 静态系统配置文件读取类 .
	 */
	private static AilkConfig config = null;

	/**
	 * 构造函数,并且将配置文件路径传给父类 .
	 */
	public AilkConfig() {
		super(CONFIG_FILE_NAME);

	}

	static {
		config = new AilkConfig();
	}

	/**
	 * 获取系统配置文件读取类 .
	 * 
	 * @return 配置文件读取类.
	 */
	public static final AilkConfig getInstance() {
		return config;
	}

	/**
	 * 获取系统配置文件读取类 .
	 * 
	 * @return 配置文件读取类.
	 */
	public final AilkConfig getConfig() {
		return config;
	}

}
