package com.blue.ailk.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.HierarchicalConfiguration.Node;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.blue.ailk.constant.PDMConst;
import com.blue.ailk.util.Log;
import com.blue.core.config.Config;

public class PdmParser implements IParser {
	/**
	 * 日志.
	 */
	private static final Logger LOG = Logger.getLogger(PdmParser.class);

	@Override
	public List<Table> getTables(String tablename, IParameter parameter) {
		PDMParameter para = (PDMParameter) parameter;
		this.setFilenames(para.getFilenames());
		if (!tableMap.containsKey(tablename)) {
			List<Table> tables = getTablesInPdm(tablename);
			tableMap.put(tablename, tables);

		}
		return tableMap.get(tablename);

	}

	private static Map<String, List<Table>> tableMap = new Hashtable<String, List<Table>>();

	private List<Table> getTablesInPdm(String tablename) {

		List<Table> tables = new ArrayList<Table>();
		initTableName(tablename);

		initTableNode();

		if (this.getTableNode() == null || this.getTableNode().isEmpty()) {
			Log.error("表不存在:" + this.getTable().getSchem() + "."
					+ this.getTable().getName());
			LOG.info("表不存在:" + this.getTable().getSchem() + "."
					+ this.getTable().getName());
			this.getTable().setName(null);
			this.getTable().setSchem(null);

		} else {

			initTableColumns();

			initTableKeys();

			initTableIndexs();

			initTableFks();
			initPartitions();
			tables.add(this.getTable());
		}
		return tables;
	}

	/**
	 * 分区表SQL获取
	 */
	private void initPartitions() {
		String partSQL = this.getTableNode().getString(PDMConst.PART_SQL);

		if (StringUtils.isNotEmpty(partSQL)) {
			int startIndex = partSQL.indexOf("partition by ");
			if (startIndex > -1) {
				if (partSQL.indexOf("partitions", startIndex) > -1) {
					Log.error(this.getTable().getName()
							+ "表分区为 BY quantity,请手动处理。");
				} else {
					int lastIndexForPart = partSQL.lastIndexOf("partition");
					int index1 = partSQL.indexOf('(', lastIndexForPart);
					int index2 = partSQL.indexOf(')', lastIndexForPart);
					int endIndex = 0;
					if (index1 != -1 && index1 < index2) {
						endIndex = partSQL.indexOf(')', index2 + 1);
					} else {
						endIndex = index2;
					}
					this.getTable().setPartitions(
							partSQL.substring(startIndex, endIndex + 1));

				}
			}

		}
	}

	/**
	 * 获取域（用户名）
	 */
	private void initUserMap() {
		List<HierarchicalConfiguration> nodes = pdmConfigger
				.configurationsAt(PDMConst.USER_PATH);
		for (HierarchicalConfiguration userNode : nodes) {
			Node userRoot = userNode.getRoot();
			String id = userRoot.getAttributes("Id").get(0).getValue()
					.toString();
			String username = userNode.getString("a:Code");

			putPdmIdMap(id, username);
		}
	}

	private void initRefTable(String ref, ImportedKey importedKey) {
		List<HierarchicalConfiguration> nodes = pdmConfigger
				.configurationsAt(PDMConst.TABLE_PATH);

		nodes.addAll(pdmConfigger.configurationsAt(PDMConst.PACKAGE_TABLE_PATH));
		for (HierarchicalConfiguration node : nodes) {
			String name = node.getString("a:Name");

			Node root = node.getRoot();
			String id = root.getAttributes("Id").get(0).getValue().toString();
			if (id.equals(ref)) {
				List<HierarchicalConfiguration> owerNodes = node
						.configurationsAt("c:Owner.o:User");

				for (HierarchicalConfiguration owerNode : owerNodes) {
					Node owerRoot = owerNode.getRoot();
					String owerRef = owerRoot.getAttributes("Ref").get(0)
							.getValue().toString();
					importedKey.setPkTableName(name);
					importedKey.setPkTableSchem(getNameById(owerRef));

				}

				List<HierarchicalConfiguration> columnNodes = node
						.configurationsAt("c:Columns.o:Column");

				for (HierarchicalConfiguration columnNode : columnNodes) {

					Node columnRoot = columnNode.getRoot();
					String columnId = columnRoot.getAttributes("Id").get(0)
							.getValue().toString();
					putPdmIdMap(columnId, columnNode.getString("a:Code"));

				}
				importedKey.setPkTableName(name);

			}

		}

	}

	private void initTableFks() {

		List<HierarchicalConfiguration> nodes = pdmConfigger
				.configurationsAt(PDMConst.REF_PATH);
		nodes.addAll(pdmConfigger.configurationsAt(PDMConst.PACKAGE_REF_PATH));

		for (HierarchicalConfiguration refNode : nodes) {

			List<HierarchicalConfiguration> childTableNodes = refNode
					.configurationsAt("c:ChildTable.o:Table");
			Node childTableNode = childTableNodes.get(0).getRoot();
			String ref = childTableNode.getAttributes("Ref").get(0).getValue()
					.toString();

			if (this.getTableId().equals(ref)) {
				List<HierarchicalConfiguration> refJoinNodes = refNode
						.configurationsAt(PDMConst.REF_JOIN);
				for (HierarchicalConfiguration refJoinNode : refJoinNodes) {
					ImportedKey importedKey = new ImportedKey();
					importedKey.setFkTableName(this.getTable().getName());
					importedKey.setFkTableSchem(this.getTable().getSchem());

					List<HierarchicalConfiguration> parentTableTableNodes = refNode
							.configurationsAt("c:ParentTable.o:Table");
					Node parentTableNode = parentTableTableNodes.get(0)
							.getRoot();
					String parentref = parentTableNode.getAttributes("Ref")
							.get(0).getValue().toString();

					initRefTable(parentref, importedKey);

					// String name = refNode.getString("a:Name");
					String name = refNode
							.getString("a:ForeignKeyConstraintName");
					if (StringUtils.isEmpty(name)) {

						StringBuffer strName = new StringBuffer();
						strName.append("FK_");
						if (importedKey.getFkTableName().length() > 8) {
							strName.append(importedKey.getFkTableName()
									.substring(0, 8));
						} else {
							strName.append(importedKey.getFkTableName());
						}

						strName.append("_REFERENCE_");
						if (importedKey.getPkTableName().length() > 8) {
							strName.append(importedKey.getPkTableName()
									.substring(0, 8));
						} else {
							strName.append(importedKey.getPkTableName());
						}
						name = strName.toString();

					}
					importedKey.setFkName(name);

					List<HierarchicalConfiguration> object1Nodes = refJoinNode
							.configurationsAt("c:Object1.o:Column");
					Node object1Node = object1Nodes.get(0).getRoot();
					String object1ref = object1Node.getAttributes("Ref").get(0)
							.getValue().toString();
					String obect1Name = getNameById(object1ref);
					importedKey.setPkColumnName(obect1Name);
					List<HierarchicalConfiguration> object2Nodes = refJoinNode
							.configurationsAt("c:Object2.o:Column");
					Node object2Node = object2Nodes.get(0).getRoot();
					String object2ref = object2Node.getAttributes("Ref").get(0)
							.getValue().toString();
					String obect2Name = getNameById(object2ref);
					importedKey.setFkColumnName(obect2Name);

					if (this.getTable().importedKeys.containsKey(importedKey
							.getFkName())) {
						List<ImportedKey> importedKeys = this.getTable().importedKeys
								.get(importedKey.getFkName());
						importedKeys.add(importedKey);

					} else {
						List<ImportedKey> importedKeys = new ArrayList<ImportedKey>();
						importedKeys.add(importedKey);
						this.getTable().importedKeys.put(
								importedKey.getFkName(), importedKeys);
					}
				}
			}

		}
	}

	private void initTableIndexs() {
		List<HierarchicalConfiguration> indexNodes = this.getTableNode()
				.configurationsAt("c:Indexes.o:Index");
		for (HierarchicalConfiguration indexNode : indexNodes) {
			String indexName = indexNode.getString(PDMConst.NAME_KEY);
			if (StringUtils.isEmpty(indexName)) {
				Log.error(this.getTable().getName()
						+ "索引异常......................");
				LOG.info("异常......................");
			}
			List<Index> indexs = new ArrayList<Index>();

			List<HierarchicalConfiguration> indexColumnNodes = indexNode
					.configurationsAt("c:IndexColumns.o:IndexColumn");
			for (HierarchicalConfiguration indexColumnNode : indexColumnNodes) {
				List<HierarchicalConfiguration> oColumnNodes = indexColumnNode
						.configurationsAt("c:Column.o:Column");
				for (HierarchicalConfiguration oColumnNode : oColumnNodes) {
					Index index = new Index();
					index.setIndexName(indexName);
					Node root = oColumnNode.getRoot();
					String ref = root.getAttributes("Ref").get(0).getValue()
							.toString();
					String cloumnName = getNameById(ref);
					index.setColumnName(cloumnName);
					indexs.add(index);
				}

				List<HierarchicalConfiguration> exprNodes = indexColumnNode
						.configurationsAt("a:IndexColumn..Expression");
				for (HierarchicalConfiguration oColumnNode : exprNodes) {
					Index index = new Index();
					index.setIndexName(indexName);
					Node root = oColumnNode.getRoot();
					index.setColumnName(root.getValue().toString());
					indexs.add(index);
				}

			}
			// List<HierarchicalConfiguration> indexColumnNodes = indexNode
			// .configurationsAt("c:IndexColumns.o:IndexColumn.c:Column.o:Column");
			// for (HierarchicalConfiguration indexColumnNode :
			// indexColumnNodes) {
			// Node root = indexColumnNode.getRoot();
			// String ref = root.getAttributes("Ref").get(0).getValue()
			// .toString();
			// String cloumnName = getNameById(ref);
			// index.setColumnName(cloumnName);
			// }

			// if (StringUtils.isEmpty(indexName)) {
			// List<HierarchicalConfiguration> columnNodes = indexNode
			// .configurationsAt("c:IndexColumns.o:IndexColumn");
			// for (HierarchicalConfiguration columnNode : columnNodes) {
			// List<ConfigurationNode> nodes = columnNode.getRoot()
			// .getChildren();
			// for (ConfigurationNode node : nodes) {
			//
			// if (node.getName().equalsIgnoreCase(
			// "a:IndexColumn..Expression")) {
			// System.out.println(11);
			// //index.setColumnName(node.getValue().toString());
			// }
			// }
			//
			// }
			// }

			this.getTable().indexs.put(indexName, indexs);

		}

	}

	private String getPrimaryKeyRef() {
		String ref = null;
		List<HierarchicalConfiguration> keyNodes = this.getTableNode()
				.configurationsAt(PDMConst.PRI_KEY);
		for (HierarchicalConfiguration keyNode : keyNodes) {
			ref = keyNode.getRoot().getAttributes("Ref").get(0).getValue()
					.toString();
		}
		return ref;
	}

	/**
	 * 初始化主键名与列 .
	 */
	private void initTableKeys() {

		String primaryKeyRef = getPrimaryKeyRef();

		List<HierarchicalConfiguration> keyNodes = this.getTableNode()
				.configurationsAt(PDMConst.PK_KEY);

		for (HierarchicalConfiguration keyNode : keyNodes) {

			String pkName = keyNode.getString(PDMConst.CODE_KEY);
			if (StringUtils.isNotEmpty(pkName)) {
				String pkId = keyNode.getRoot().getAttributes("Id").get(0)
						.getValue().toString();
				String constraintName = keyNode.getString(PDMConst.PK_NAME_KEY);
				if (StringUtils.isNotEmpty(constraintName)) {
					pkName = constraintName;
				} else {
					if (StringUtils.isNotEmpty(primaryKeyRef)
							&& primaryKeyRef.equalsIgnoreCase(pkId)) {
						pkName = "PK_" + this.getTable().getName();

					} else {
						pkName = "AK_" + pkName;
						if (this.getTable().getName().length() > 8) {
							pkName = pkName + "_"
									+ this.getTable().getName().substring(0, 8);
						} else {
							pkName = pkName + "_" + this.getTable().getName();
						}
					}

				}
				if (pkName.length() > 30) {
					pkName = pkName.substring(0, 30);

				}

				List<HierarchicalConfiguration> columnNodes = keyNode
						.configurationsAt(PDMConst.PK_COLUMN_KEY);
				String[] pkCloumnName = new String[columnNodes.size()];
				for (int i = 0; i < columnNodes.size(); i++) {
					HierarchicalConfiguration columnNode = columnNodes.get(i);
					String ref = getAttribute(PDMConst.REF, columnNode);
					String cloumnName = getNameById(ref);
					pkCloumnName[i] = cloumnName;
				}
				if (StringUtils.isNotEmpty(primaryKeyRef)
						&& primaryKeyRef.equals(pkId)) {
					this.getTable().pkCloumnName = pkCloumnName;
					this.getTable().setPkName(pkName);
				} else {
					List<UniqueKey> lstUniqueKeys = new ArrayList<UniqueKey>();

					for (String pkColName : pkCloumnName) {
						UniqueKey uniqueKey = new UniqueKey();
						uniqueKey.setColumnName(pkColName);
						lstUniqueKeys.add(uniqueKey);
					}
					this.getTable().uniqueKeys.put(pkName, lstUniqueKeys);

				}

			}

		}

	}

	/**
	 * 获取节点属性值
	 * 
	 * @param name
	 *            属性名称
	 * @param node
	 *            节点
	 * @return
	 */
	private String getAttribute(String name, HierarchicalConfiguration node) {
		return node.getRoot().getAttributes(name).get(0).getValue().toString();
	}

	private void initTableColumns() {

		List<HierarchicalConfiguration> columnNodes = this.getTableNode()
				.configurationsAt("c:Columns.o:Column");
		List<Column> columns = new ArrayList<Column>();
		for (HierarchicalConfiguration columnNode : columnNodes) {
			columns.add(getColumns(columnNode));
		}
		this.getTable().columns = columns;
	}

	private com.blue.ailk.db.Column getColumns(
			HierarchicalConfiguration columnNode) {
		Node root = columnNode.getRoot();
		String id = root.getAttributes("Id").get(0).getValue().toString();

		com.blue.ailk.db.Column column = new com.blue.ailk.db.Column();
		column.setName(columnNode.getString(PDMConst.CODE_KEY));
		if (columnNode.getString(PDMConst.LENGTH_KEY) != null) {
			column.setColumnSize(columnNode.getInt(PDMConst.LENGTH_KEY));
		}
		putPdmIdMap(id, column.getName());

		// column.setDecimalDigits()
		column.setNullable(1);
		if (columnNode.getString("a:Mandatory") != null
				&& columnNode.getString("a:Mandatory").equals("1")) {
			column.setNullable(0);
		} else {

			if (columnNode.getString("a:Column..Mandatory") != null
					&& columnNode.getString("a:Column..Mandatory").equals("1")) {
				column.setNullable(0);
			}

		}
		column.setLowValue(columnNode.getString(PDMConst.LOW_VALUE_KEY));
		column.setHighValue(columnNode.getString(PDMConst.HIGH_VALUE_KEY));
		column.setDefaultValue(columnNode.getString("a:DefaultValue"));
		String typeName = columnNode.getString("a:DataType");
		if (typeName.indexOf("(") == -1) {
			column.setTypeName(typeName);
		} else {
			column.setTypeName(typeName.substring(0, typeName.indexOf("(")));
		}
		int columnNameSize = column.getName().length();
		if (columnNameSize > table.getMaxColumnNameSize()) {
			table.setMaxColumnNameSize(columnNameSize);
		}
		String commentValue = columnNode.getString(PDMConst.COMMENT_KEY);
		column.setComment("");
		if (StringUtils.isNotEmpty(commentValue)) {
			column.setComment(commentValue);
		}
		String desc = columnNode.getString(PDMConst.NAME_KEY);
		if (StringUtils.isNotEmpty(desc)) {
			if (desc.trim().equalsIgnoreCase(column.getName())) {

				if (StringUtils.isNotEmpty(column.getComment())) {
					column.setComment(desc);
				} else {
					column.setComment(desc + ";" + column.getComment());
				}

			}

		}

		return column;
	}

	public void initTableNode() {

		for (String pdmFilename : this.filenames) {

			initPdmFile(pdmFilename);

			List<HierarchicalConfiguration> nodes = pdmConfigger
					.configurationsAt(PDMConst.TABLE_PATH);
			nodes.addAll(pdmConfigger
					.configurationsAt(PDMConst.PACKAGE_TABLE_PATH));
			for (HierarchicalConfiguration node : nodes) {
				String name = node.getString(PDMConst.CODE_KEY);
				if (this.getTable().getName().trim()
						.equalsIgnoreCase(name.trim())) {
					this.setTableNode(node);
					Node root = node.getRoot();
					String id = root.getAttributes("Id").get(0).getValue()
							.toString();
					this.setTableId(id);
					String commentValue = node.getString(PDMConst.COMMENT_KEY);
					if (StringUtils.isNotEmpty(commentValue)) {
						this.getTable().setComment(commentValue);
					}
					String desc = node.getString(PDMConst.DESC_KEY);
					if (StringUtils.isNotEmpty(desc)) {
						this.getTable().setComment(
								this.getTable().getComment() + "\n Note : "
										+ desc);
					}
					return;
				}

			}

		}

	}

	private void initTableName(String tablename) {
		String[] arrTbl = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		if (tablename != null && !tablename.trim().equals("")) {
			arrTbl = tablename.split("\\.");
		}
		if (arrTbl != null) {
			if (arrTbl.length == 1) {
				tableNamePattern = arrTbl[0];
			} else {
				schemaPattern = arrTbl[0];
				tableNamePattern = arrTbl[1];
			}
		}
		this.getTable().setSchem(schemaPattern);
		this.getTable().setName(tableNamePattern);
	}

	private String tableId;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	HierarchicalConfiguration tableNode;

	public HierarchicalConfiguration getTableNode() {
		return tableNode;
	}

	public void setTableNode(HierarchicalConfiguration tableNode) {
		this.tableNode = tableNode;
	}

	Table table = new Table();

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	private static Map<String, Config> pdmMap = new Hashtable<String, Config>();

	public Config getPdmConfigger() {
		return pdmConfigger;
	}

	public void setPdmConfigger(Config pdmConfigger) {
		this.pdmConfigger = pdmConfigger;
	}

	private Config pdmConfigger;
	String pdmFilename;

	public String getPdmFilename() {
		return pdmFilename;
	}

	public void setPdmFilename(String pdmFilename) {
		this.pdmFilename = pdmFilename;
	}

	List<String> filenames;

	public List<String> getFilenames() {
		return filenames;
	}

	public void setFilenames(List<String> filenames) {
		this.filenames = filenames;
	}

	/**
	 * 获取Sequnce
	 */
	@Override
	public List<Sequnce> getSequnces(IParameter parameter) {
		List<Sequnce> lstSequnce = new ArrayList<Sequnce>();
		PDMParameter para = (PDMParameter) parameter;
		this.setFilenames(para.getFilenames());

		for (String pdmFilename : this.filenames) {
			initPdmFile(pdmFilename);
			lstSequnce.addAll(getSequence());
		}

		return lstSequnce;
	}

	/**
	 * 初始化PDM文件 .
	 * 
	 * @param pdmFilename
	 *            .
	 */
	private void initPdmFile(String pdmFilename) {
		this.setPdmFilename(pdmFilename);
		Config pdmConfigger = null;
		if (!pdmMap.containsKey(pdmFilename)) {
			this.setPdmFilename(pdmFilename);
			pdmConfigger = new Config();
			pdmConfigger.getXmlConfig().setDelimiterParsingDisabled(true);
			pdmConfigger.getXmlConfig().setFileName(pdmFilename);
			pdmConfigger.load();
			pdmMap.put(pdmFilename, pdmConfigger);
			this.setPdmConfigger(pdmConfigger);
			initUserMap();
		}

		pdmConfigger = pdmMap.get(pdmFilename);
		this.setPdmConfigger(pdmConfigger);
	}

	/**
	 * 存放PDM中域（用户）信息. <br>
	 * 第一层HashMap的KEY为PDM文件路径名，VALUE为域的集合 <br>
	 * 第二层HashMap为KEY为PDM中对应的ID,VALUE为名称 .
	 */
	private static HashMap<String, HashMap<String, String>> allPdmIdMap = new HashMap<String, HashMap<String, String>>();

	/**
	 * 获取sequnce .
	 * 
	 * @return .
	 */
	private List<Sequnce> getSequence() {
		List<Sequnce> lstSequnce = new ArrayList<Sequnce>();
		List<HierarchicalConfiguration> nodes = pdmConfigger
				.configurationsAt(PDMConst.SEQ_PATH);
		nodes.addAll(pdmConfigger.configurationsAt(PDMConst.PACKAGE_SEQ_PATH));

		for (HierarchicalConfiguration seqNode : nodes) {
			Node seqRoot = seqNode.getRoot();
			Sequnce sequnce = new Sequnce();
			sequnce.setName(seqNode.getString("a:Code"));
			sequnce.setPhysicalOptions(seqNode.getString(PDMConst.PART_SQL));
			List<ConfigurationNode> children = seqRoot.getChildren();
			for (ConfigurationNode child : children) {
				if (child.getName().equalsIgnoreCase("c:Sequence.Owner")) {
					List<ConfigurationNode> userChildren = child.getChildren();
					for (ConfigurationNode userChild : userChildren) {
						if (userChild.getName().equalsIgnoreCase("o:User")) {
							String ref = userChild.getAttributes("Ref").get(0)
									.getValue().toString();
							String schem = getNameById(ref);
							sequnce.setSchem(schem);

							break;
						}
					}
					break;
				}
			}
			lstSequnce.add(sequnce);
		}
		return lstSequnce;
	}

	private String getNameById(String id) {
		if (allPdmIdMap.containsKey(this.getPdmFilename())) {
			HashMap<String, String> pdmIdMap = allPdmIdMap.get(this
					.getPdmFilename());
			if (pdmIdMap.containsKey(id)) {
				return pdmIdMap.get(id);
			}
		}
		Log.error("Pdm文件中不存在 id:" + id);
		return null;
	}

	private void putPdmIdMap(String id, String name) {
		if (allPdmIdMap.containsKey(this.getPdmFilename())) {
			HashMap<String, String> pdmIdMap = allPdmIdMap.get(this
					.getPdmFilename());
			pdmIdMap.put(id, name);
		} else {
			HashMap<String, String> pdmIdMap = new HashMap<String, String>();
			pdmIdMap.put(id, name);
			allPdmIdMap.put(this.getPdmFilename(), pdmIdMap);
		}
	}
}
