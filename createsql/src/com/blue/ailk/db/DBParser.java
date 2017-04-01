package com.blue.ailk.db;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DBParser implements IParser {
	private static Map<String, List<Table>> tableMap = new Hashtable<String, List<Table>>();

	@Override
	public List<Table> getTables(String tablename, IParameter parameter) {
		if (!tableMap.containsKey(tablename)) {
			DBTableParser parser = new DBTableParser( parameter,
					tablename);
			tableMap.put(tablename, parser.getTables());

		}
		return tableMap.get(tablename);
	}

	@Override
	public List<Sequnce> getSequnces(IParameter paramter) {
		// TODO Auto-generated method stub
		return null;
	}

}
