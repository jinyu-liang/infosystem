package com.blue.ailk.db;

import java.util.Hashtable;
import java.util.Map;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.core.exception.BaseException;

public class ParserFactory {
	/**
	 * .
	 */
	private static Map<String, Class<?>> parserMap = new Hashtable<String, Class<?>>();

	public static IParser getFactory() {

		String clzzName = AilkDbConfig.getInstance().getString(
				"adapter.className");

		return getFactory(clzzName);
	}

	public static IParser getFactory(String clzzName) {
		IParser parser = null;
		try {
			if (!parserMap.containsKey(clzzName)) {
				Class<?> clzz = Class.forName(clzzName);
				parserMap.put(clzzName, clzz);

			}
			parser = (IParser) parserMap.get(clzzName).newInstance();

		} catch (InstantiationException e) {
			throw new BaseException(e);
		} catch (IllegalAccessException e) {
			throw new BaseException(e);
		} catch (ClassNotFoundException e) {
			throw new BaseException(e);
		}

		return parser;
	}
}
