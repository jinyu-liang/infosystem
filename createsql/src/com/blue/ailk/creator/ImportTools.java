package com.blue.ailk.creator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.blue.ailk.db.PdmTools;
import com.blue.core.config.Config;

public class ImportTools {
	public static void main(String[] args) {
		String filename = "e:/sql/tables.xml";
		importTables(filename);
	}

	public static List<ConfigTable> importTables(String filename) {
		List<ConfigTable> configTables = new ArrayList<ConfigTable>();
		if (filename.toLowerCase().endsWith(".xml")) {
			Config config = new Config(filename);
			List<HierarchicalConfiguration> nodes = config
					.configurationsAt("table");

			for (HierarchicalConfiguration node : nodes) {
				ConfigTable table = new ConfigTable();
				table.setName(node.getRoot().getValue().toString());
				if (!node.getRoot().getAttributes("ptType").isEmpty()) {
					String ptType = node.getRoot().getAttributes("ptType")
							.get(0).getValue().toString();
					String ptValue = node.getRoot().getAttributes("ptValue")
							.get(0).getValue().toString();
					table.setPtType(ptType);
					table.setPtValue(ptValue);

				}
				configTables.add(table);
			}

		} else if (filename.toLowerCase().endsWith(".txt")) {
		} else if (filename.toLowerCase().endsWith(".pdm")) {
			String[] pdmFilenames = { filename };
			List<String> pdmTblnames = PdmTools.getTableNames(pdmFilenames);

			for (String tblname : pdmTblnames) {
				ConfigTable table = new ConfigTable();
				table.setName(tblname);
				configTables.add(table);

			}
		}
		return configTables;

	}
}
