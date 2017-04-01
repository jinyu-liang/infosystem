package com.blue.ailk.db;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration.Node;
import org.apache.log4j.Logger;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.ailk.constant.ConfigConst;
import com.blue.ailk.constant.PDMConst;
import com.blue.ailk.util.Log;
import com.blue.core.config.Config;

public class PdmTools {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger.getLogger(PdmTools.class);

	public static List<String> getTableNameStartWidth(List<String> lstPrefix) {

		List<String> tableNames = new ArrayList<String>();
		String[] pdmFilenames = AilkDbConfig.getInstance().getStringArray(
				ConfigConst.PDM_FILE_NAME_KEY);
		for (String pdmFilename : pdmFilenames) {
			Config pdmConfigger = new Config(pdmFilename);
			Map<String, String> userMap = getUserMap(pdmConfigger);

			List<HierarchicalConfiguration> nodes = pdmConfigger
					.configurationsAt(PDMConst.TABLE_PATH);
			nodes.addAll(pdmConfigger
					.configurationsAt(PDMConst.PACKAGE_TABLE_PATH));
			for (HierarchicalConfiguration node : nodes) {
				String name = node.getString(PDMConst.NAME_KEY);
				for (String prefix : lstPrefix) {
					if (name.startsWith(prefix)) {
						HierarchicalConfiguration owerUserNode = node
								.configurationAt(PDMConst.OWER_USER_KEY);
						Node owerUserRoot = owerUserNode.getRoot();

						String ref = owerUserRoot.getAttributes("Ref").get(0)
								.getValue().toString();

						tableNames.add(userMap.get(ref) + "." + name);
					}
				}

			}

		}
		return tableNames;
	}

	/**
	 * 获取PDM文件中表名
	 * 
	 * @param pdmFilenames
	 *            pdm文件路径
	 * @return
	 */
	public static List<String> getTableNames(String[] pdmFilenames) {

		List<String> tableNames = new ArrayList<String>();

		for (String pdmFilename : pdmFilenames) {
			Config pdmConfigger = new Config(pdmFilename);
			Map<String, String> userMap = getUserMap(pdmConfigger);

			List<HierarchicalConfiguration> nodes = pdmConfigger
					.configurationsAt(PDMConst.TABLE_PATH);
			if (nodes.isEmpty()) {
				nodes = pdmConfigger
						.configurationsAt(PDMConst.PACKAGE_TABLE_PATH);
			}

			for (HierarchicalConfiguration node : nodes) {
				String name = node.getString(PDMConst.CODE_KEY);

				try {
					HierarchicalConfiguration owerUserNode = node
							.configurationAt(PDMConst.OWER_USER_KEY);
					Node owerUserRoot = owerUserNode.getRoot();

					String ref = owerUserRoot.getAttributes("Ref").get(0)
							.getValue().toString();
					String owerName = "";
					if (userMap.containsKey(ref)) {
						owerName = userMap.get(ref);
					} else {
						Log.error("OWER USER 不存在 Ref：" + ref);
					}
					tableNames.add(owerName + "." + name);
				} catch (Exception e) {
					Log.error("OWER USER 不存在 table：" + name);
				}
			}

		}
		return tableNames;
	}

	/**
	 * pmd存数据用户(域)名称与ID对应关系
	 * 
	 * @param pdmConfigger
	 * @return
	 */
	private static Map<String, String> getUserMap(Config pdmConfigger) {
		Map<String, String> userMap = new Hashtable<String, String>();
		List<HierarchicalConfiguration> nodes = pdmConfigger
				.configurationsAt(PDMConst.USER_PATH);
		for (HierarchicalConfiguration userNode : nodes) {
			Node userRoot = userNode.getRoot();
			String seqId = userRoot.getAttributes("Id").get(0).getValue()
					.toString();
			String username = userNode.getString("a:Code");
			userMap.put(seqId, username);

		}
		return userMap;
	}

}
