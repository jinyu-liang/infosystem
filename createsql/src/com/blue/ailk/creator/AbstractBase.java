package com.blue.ailk.creator;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.blue.ailk.config.AilkConfig;
import com.blue.ailk.db.IParameter;
import com.blue.ailk.db.IParser;
import com.blue.ailk.db.ParserFactory;
import com.blue.ailk.db.Table;
import com.blue.ailk.util.Log;
import com.blue.core.lang.io.TextUtils;

public abstract class AbstractBase {
	public static Map<String, String> specWords = null;
	public static Map<String, String> noLengthCloumnTypeHT = null;
	Map<String, String> columnTypes = new Hashtable<String, String>();
	String scriptFilename;
	List<ConfigTable> configTables;

	public IParameter getParameter() {
		return parameter;
	}

	public void setParameter(IParameter parameter) {
		this.parameter = parameter;
	}

	String adapterClzz;
	IParameter parameter;

	public String getAdapterClzz() {
		return adapterClzz;
	}

	public void setAdapterClzz(String adapterClzz) {
		this.adapterClzz = adapterClzz;
	}

	public List<ConfigTable> getConfigTables() {
		return configTables;
	}

	public void setConfigTables(List<ConfigTable> configTables) {
		this.configTables = configTables;
	}

	public String getScriptFilename() {
		return scriptFilename;
	}

	public void setScriptFilename(String scriptFilename) {
		this.scriptFilename = scriptFilename;
	}

	public AbstractBase() {

		init();

	}

	void init() {
		if (noLengthCloumnTypeHT == null) {
			noLengthCloumnTypeHT = new Hashtable<String, String>();
			String[] arrNoLengthCloumns = AilkConfig.getInstance()
					.getStringArray("noLengthCloumnType.columnType");
			for (String columnType : arrNoLengthCloumns) {
				noLengthCloumnTypeHT.put(columnType.toLowerCase(), columnType);
			}

		}
		if (specWords == null) {
			specWords = new Hashtable<String, String>();
			String[] arrSpecWords = AilkConfig.getInstance().getStringArray(
					"specWords.word");
			for (String word : arrSpecWords) {
				specWords.put(word.toLowerCase(), word);
			}

		}
	}

	public void create() {

		Log.info("--------------------------------------------------------------------------------");

		writeSQLFile();

		Log.info("Deal End........");

	}

	private void writeSQLFile() {

		StringBuffer sb = new StringBuffer();

		for (ConfigTable configTable : this.getConfigTables()) {
			Long sTime = System.currentTimeMillis();
			String tableName = configTable.getName();

			IParser parser = ParserFactory.getFactory(this.getAdapterClzz());

			List<Table> list = parser.getTables(tableName, this.getParameter());

			for (Table table : list) {

				sb.append(createSQL(table, configTable));

			}
			Log.info("耗时：" + (System.currentTimeMillis() - sTime));
		}
		sb.append("prompt Exec Done.");

		TextUtils.write(this.getScriptFilename(), sb.toString(), "UTF-8");
		Log.info("Created filename:" + this.getScriptFilename());
	}

	/**
	 * 重成SQL文件里内容的格式结构
	 * 
	 * @param table
	 *            表结构
	 * @param root
	 *            配置文件中对应节点结构
	 * @return 内容
	 */

	abstract String createSQL(Table table, ConfigTable configTable);

}