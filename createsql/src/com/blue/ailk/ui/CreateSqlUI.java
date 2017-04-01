package com.blue.ailk.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.lang.StringUtils;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.ailk.constant.ConfigConst;
import com.blue.ailk.creator.AbstractBase;
import com.blue.ailk.creator.BaseSQLCreator;
import com.blue.ailk.creator.CommentCreator;
import com.blue.ailk.creator.ConfigTable;
import com.blue.ailk.creator.ConstraintSQLCreator;
import com.blue.ailk.creator.CreatorThread;
import com.blue.ailk.creator.DropConstraintSQLCreator;
import com.blue.ailk.creator.DropFkSQLCreator;
import com.blue.ailk.creator.DropIndexSQLCreator;
import com.blue.ailk.creator.DropTblSQLCreator;
import com.blue.ailk.creator.FkSQLCreator;
import com.blue.ailk.creator.ImportTools;
import com.blue.ailk.creator.IndexSQLCreator;
import com.blue.ailk.creator.PTBaseSQLCreator;
import com.blue.ailk.creator.PTConstraintSQLCreator;
import com.blue.ailk.creator.PTDropBaseSQLCreator;
import com.blue.ailk.creator.PTDropConstraintSQLCreator;
import com.blue.ailk.creator.PTDropIndexSQLCreator;
import com.blue.ailk.creator.PTIndexSQLCreator;
import com.blue.ailk.creator.PTTruncateSQLCreator;
import com.blue.ailk.creator.SeqSQLCreator;
import com.blue.ailk.creator.TruncateSQLCreator;
import com.blue.ailk.db.DBParameter;
import com.blue.ailk.db.IParameter;
import com.blue.ailk.db.PDMParameter;
import com.blue.ailk.db.PdmTools;
import com.blue.ailk.util.Log;

public class CreateSqlUI extends InFrame {

	public CreateSqlUI(List<JInternalFrame> objIFrams, JFrame parentFrame) {
		super(objIFrams, parentFrame);
		setClosable(true);
		setBounds(100, 10, 600, 480);
		setTitle("生成脚本");
		setMaximizable(true);
		setResizable(true);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 471, 110);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"id", "别名" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (StringUtils.isNotEmpty(String.valueOf(aValue))) {
					if (!aValue.equals(table.getValueAt(rowIndex, columnIndex))) {
						// 数据发生变更
						int sequence = Integer.parseInt(table.getValueAt(
								rowIndex, 0).toString());

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getCreateSQLAliasKey(sequence),
								aValue);

					}
					super.setValueAt(aValue, rowIndex, columnIndex);
				}

			}

		});

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							if (table.getSelectedRow() > -1) {
								comboBox.setSelectedIndex(0);
								int sequence = Integer.parseInt(table
										.getValueAt(table.getSelectedRow(), 0)
										.toString());
								String filenameSuffixValue = AilkDbConfig
										.getInstance()
										.getString(
												ConfigConst
														.getCreateSQLFilenameSuffixKey(sequence));
								if (StringUtils.isNotEmpty(filenameSuffixValue)) {
									filenameSuffix.setText(filenameSuffixValue);
								} else {
									filenameSuffix.setText("");
								}

								String sourceType = AilkDbConfig
										.getInstance()
										.getString(
												ConfigConst
														.getCreateSQLSourceTypeKey(sequence));
								if (StringUtils.isNotEmpty(sourceType)) {
									for (int i = 0; i < comboBox.getItemCount(); i++) {
										String value = comboBox.getItemAt(i)
												.toString();
										if (value.equalsIgnoreCase(sourceType)) {
											comboBox.setSelectedIndex(i);
										}
									}
								}
								initDbTableData();
								saveDir.setText(AilkDbConfig
										.getInstance()
										.getString(
												ConfigConst
														.getCreateSQLSaveDirKey(sequence)));
							}
						}

					}

				});

		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		// firsetColumn.setPreferredWidth(60);
		firsetColumn.setMaxWidth(0);
		firsetColumn.setMinWidth(0);

		firsetColumn.setPreferredWidth(0);
		firsetColumn.setResizable(false);
		JButton btnNewButton = new JButton("创建别名");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dbSequnce = ConfigConst.getSequence();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new String[] { String.valueOf(dbSequnce), "别名" });
				table.setRowSelectionInterval(table.getRowCount() - 1,
						table.getRowCount() - 1);

				AilkDbConfig.getInstance().setProperty(
						ConfigConst.getCreateSQLAliasKey(dbSequnce),
						table.getValueAt(table.getRowCount() - 1, 1));
				AilkDbConfig.getInstance().setProperty(
						ConfigConst.getCreateSQLSequenceKey(dbSequnce),
						String.valueOf(dbSequnce));

			}
		});
		btnNewButton.setBounds(481, 48, 93, 23);
		getContentPane().add(btnNewButton);

		JButton button = new JButton("删除别名");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() > -1) {

					String sequence = table.getValueAt(table.getSelectedRow(),
							0).toString();
					AilkDbConfig.getInstance().clearPropertyAndChildren(
							ConfigConst.getCreateSQLKey(Integer
									.valueOf(sequence)));

					model.removeRow(table.getSelectedRow());

				}
			}
		});
		button.setBounds(481, 87, 93, 23);
		getContentPane().add(button);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (table.getSelectedRow() > -1) {

						if (!comboBox.getSelectedItem().toString().equals("")) {
							int sequence = Integer.parseInt(table.getValueAt(
									table.getSelectedRow(), 0).toString());
							AilkDbConfig
									.getInstance()
									.setProperty(
											ConfigConst
													.getCreateSQLSourceTypeKey(sequence),
											comboBox.getSelectedItem()
													.toString());

							comboBox_1.removeAllItems();
							comboBox_1.addItem("");
							if (comboBox.getSelectedItem().toString()
									.equalsIgnoreCase("PDM")) {
								getLstPdm();

							} else if (comboBox.getSelectedItem().toString()
									.equalsIgnoreCase("DB")) {
								getLstDB();

							}
							for (Combox combox : lstSource) {
								comboBox_1.addItem(combox.getName());
							}
							String source = AilkDbConfig
									.getInstance()
									.getString(
											ConfigConst
													.getCreateSQLSourceKey(sequence));
							if (StringUtils.isNotEmpty(source)) {
								for (int i = 1; i < comboBox_1.getItemCount(); i++) {
									Combox combox = lstSource.get(i - 1);
									String value = combox.getValue();
									if (value.equalsIgnoreCase(source)) {
										comboBox_1.setSelectedIndex(i);
									}
								}
							}

						}
					}

				}

			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "PDM",
				"DB" }));
		comboBox.setBounds(89, 120, 93, 21);
		getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel("数据来源");
		lblNewLabel.setBounds(10, 123, 62, 15);
		getContentPane().add(lblNewLabel);

		comboBox_1 = new JComboBox();
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (table.getSelectedRow() > -1) {

					if (comboBox_1.getSelectedItem() != null
							&& !comboBox_1.getSelectedItem().toString()
									.equals("")) {
						int sequence = Integer.parseInt(table.getValueAt(
								table.getSelectedRow(), 0).toString());
						Combox combox = lstSource.get(comboBox_1
								.getSelectedIndex() - 1);

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getCreateSQLSourceKey(sequence),
								combox.getValue());
					}
				}

			}
		});
		comboBox_1.setBounds(204, 120, 147, 21);
		getContentPane().add(comboBox_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 172, 471, 110);
		getContentPane().add(scrollPane_1);

		table_1 = new JTable();

		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"select", "ID", "表名", "分表格式", "分表值" }

		) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Class getColumnClass(int c) {

				Object value = getValueAt(0, c);

				if (value != null)

					return value.getClass();

				else
					return super.getClass();

			}

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (table.getSelectedRow() != -1) {
					int sqlSequence = Integer.parseInt(table.getValueAt(
							table.getSelectedRow(), 0).toString());
					int sequence = Integer.parseInt(table_1.getValueAt(
							rowIndex, 1).toString());
					switch (columnIndex) {
					case 0:

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getTableCheckKey(sqlSequence,
										sequence), aValue.toString());
						break;
					case 2:

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getTableNameKey(sqlSequence,
										sequence), aValue.toString());
						break;
					case 3:

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getTablePtTypeKey(sqlSequence,
										sequence), aValue.toString());
						break;
					case 4:

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getTablePtValueKey(sqlSequence,
										sequence), aValue.toString());
						break;
					}

				}
				super.setValueAt(aValue, rowIndex, columnIndex);
			}
		});
		TableColumn selColumn = table_1.getColumnModel().getColumn(0);
		selColumn.setMaxWidth(20);
		selColumn.setMinWidth(20);
		selColumn.setPreferredWidth(20);
		selColumn.setResizable(false);
		TableColumn idColumn = table_1.getColumnModel().getColumn(1);
		idColumn.setMaxWidth(0);
		idColumn.setMinWidth(0);
		idColumn.setPreferredWidth(0);
		idColumn.setResizable(false);
		TableColumn nameColumn = table_1.getColumnModel().getColumn(2);

		nameColumn.setPreferredWidth(200);

		final MyCheckBoxRenderer check = new MyCheckBoxRenderer();

		table_1.getColumn("select").setHeaderRenderer(check);
		table_1.getTableHeader().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (table_1.getColumnModel().getColumnIndexAtX(e.getX()) == 0) {// 如果点击的是第0列，即checkbox这一列

					JCheckBox Checkbox = (JCheckBox) check;

					boolean b = !check.isSelected();

					check.setSelected(b);

					table_1.getTableHeader().repaint();

					for (int i = 0; i < table_1.getRowCount(); i++) {

						table_1.getModel().setValueAt(b, i, 0);// 把这一列都设成和表头一样

					}

				}

			}

		});

		JButton button_1 = new JButton("新增表");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					int sqlSequence = Integer.parseInt(table.getValueAt(
							table.getSelectedRow(), 0).toString());
					int sequnce = ConfigConst.getSequence();
					DefaultTableModel model = (DefaultTableModel) table_1
							.getModel();
					model.addRow(new Object[] { false, String.valueOf(sequnce),
							"", "", "" });
					table_1.setRowSelectionInterval(table_1.getRowCount() - 1,
							table_1.getRowCount() - 1);

					AilkDbConfig.getInstance().setProperty(
							ConfigConst.getTableSequenceKey(sqlSequence,
									sequnce), String.valueOf(sequnce));

				}
			}
		});

		button_1.setBounds(481, 260, 93, 23);
		getContentPane().add(button_1);

		JButton button_2 = new JButton("删除表");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_1
						.getModel();
				if (model.getRowCount() > 0) {
					for (int i = model.getRowCount() - 1; i >= 0; i--) {
						if (table_1.getValueAt(i, 0).toString()
								.equalsIgnoreCase("true")) {
							int sequence = Integer.parseInt(table_1.getValueAt(
									i, 1).toString());
							int sqlSequence = Integer.parseInt(table
									.getValueAt(table.getSelectedRow(), 0)
									.toString());
							AilkDbConfig.getInstance()
									.clearPropertyAndChildren(
											ConfigConst.getTableKey(
													sqlSequence, sequence));

							model.removeRow(i);

						}
					}
				}

				// if (table.getSelectedRow() != -1
				// && table_1.getSelectedRow() != -1) {
				// int sqlSequence = Integer.parseInt(table.getValueAt(
				// table.getSelectedRow(), 0).toString());
				// int sequence = Integer.parseInt(table_1.getValueAt(
				// table_1.getSelectedRow(), 1).toString());
				// AilkDbConfig.getInstance().clearPropertyAndChildren(
				// ConfigConst.getTableKey(sqlSequence, sequence));
				// DefaultTableModel model = (DefaultTableModel) table_1
				// .getModel();
				// model.removeRow(table_1.getSelectedRow());
				// }
			}
		});
		button_2.setBounds(481, 227, 93, 23);
		getContentPane().add(button_2);

		JButton button_3 = new JButton("导入表");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					javax.swing.JFileChooser cf = new javax.swing.JFileChooser() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void approveSelection() {
							String filename = this.getSelectedFile()
									.getAbsolutePath().replaceAll("\\\\", "/");
							DefaultTableModel model = (DefaultTableModel) table_1
									.getModel();
							// if(filename.toLowerCase().endsWith(".all")){
							// model.getDataVector().clear();
							// table_1.repaint();
							// }
							List<ConfigTable> configTables = ImportTools
									.importTables(filename);

							int sqlSequence = Integer.parseInt(table
									.getValueAt(table.getSelectedRow(), 0)
									.toString());
							for (ConfigTable configTable : configTables) {
								int sequence = ConfigConst.getSequence();

								model.addRow(new Object[] { false,
										String.valueOf(sequence),
										configTable.getName(),
										configTable.getPtType(),
										configTable.getPtValue() });

								table_1.setRowSelectionInterval(
										table_1.getRowCount() - 1,
										table_1.getRowCount() - 1);
								AilkDbConfig.getInstance().setProperty(
										ConfigConst.getTableSequenceKey(
												sqlSequence, sequence),
										String.valueOf(sequence));
								AilkDbConfig.getInstance().setProperty(
										ConfigConst.getTableNameKey(
												sqlSequence, sequence),
										String.valueOf(configTable.getName()));
								if (StringUtils.isNotEmpty(configTable
										.getPtType())) {
									AilkDbConfig.getInstance().setProperty(
											ConfigConst.getTablePtTypeKey(
													sqlSequence, sequence),
											String.valueOf(configTable
													.getPtType()));
								}
								if (StringUtils.isNotEmpty(configTable
										.getPtValue())) {
									AilkDbConfig.getInstance().setProperty(
											ConfigConst.getTablePtValueKey(
													sqlSequence, sequence),
											String.valueOf(configTable
													.getPtValue()));
								}

							}
							this.cancelSelection();
						}

					};
					FileFilter filter = new FileNameExtensionFilter(
							"XML,TXT,PDM", "xml", "txt", "pdm");
					cf.setCurrentDirectory(new File("D:/"));
					cf.setFileFilter(filter);
					cf.setDialogTitle("请选择文件或文件夹");

					cf.setFileSelectionMode(JFileChooser.FILES_ONLY);

					cf.showOpenDialog(frame);
				}
			}
		});
		button_3.setBounds(481, 197, 93, 23);
		getContentPane().add(button_3);

		JButton button_4 = new JButton("生成表脚本");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					Log.saveDir = saveDir.getText();
					String adapterClzz = "com.blue.ailk.db.PdmParser";
					IParameter parameter = null;
					if (StringUtils.isEmpty(comboBox_1.getSelectedItem()
							.toString())) {
						return;
					}
					Combox combox = lstSource.get(comboBox_1.getSelectedIndex() - 1);

					if (StringUtils.isEmpty(comboBox.getSelectedItem()
							.toString())) {
						return;
					} else {
						String pdmSequence = combox.getValue();
						if (comboBox.getSelectedItem().toString()
								.equalsIgnoreCase("DB")) {
							DBParameter para = new DBParameter();
							String driverClass = AilkDbConfig
									.getInstance()
									.getString(
											ConfigConst
													.getDBDriverClassKey(Integer
															.parseInt(pdmSequence)));
							String jdbcUrl = AilkDbConfig.getInstance()
									.getString(
											ConfigConst.getDBJdbcUrlKey(Integer
													.parseInt(pdmSequence)));
							String username = AilkDbConfig.getInstance()
									.getString(
											ConfigConst.getDBUserKey(Integer
													.parseInt(pdmSequence)));
							String password = AilkDbConfig
									.getInstance()
									.getString(
											ConfigConst.getDBPasswordKey(Integer
													.parseInt(pdmSequence)));
							para.setDriverClass(driverClass);
							para.setJdbcUrl(jdbcUrl);
							para.setUser(username);
							para.setPassword(password);

							parameter = para;
							adapterClzz = "com.blue.ailk.db.DBParser";
						} else {

							PDMParameter para = new PDMParameter();

							List<String> filenames = new ArrayList<String>();
							List<HierarchicalConfiguration> children = AilkDbConfig
									.getInstance().configurationsAt(
											ConfigConst.getPDMFilesKey(Integer
													.parseInt(pdmSequence)));
							for (HierarchicalConfiguration child : children) {

								List<ConfigurationNode> childrenNodes = child
										.getRoot().getChildren();
								for (ConfigurationNode node : childrenNodes) {

									String filename = AilkDbConfig
											.getInstance()
											.getString(
													ConfigConst
															.getPDMFilesKey(Integer
																	.parseInt(pdmSequence))
															+ "."
															+ node.getName()
															+ ".filename");
									filenames.add(filename);

								}
							}

							para.setFilenames(filenames);
							parameter = para;
						}
					}
					String suffix = "";
					if (StringUtils.isNotEmpty(filenameSuffix.getText())) {
						suffix += filenameSuffix.getText() + ".";
					}
					String baseFilename = saveDir.getText() + "/基表/" + suffix
							+ "base.sql";
					String dropFilename = saveDir.getText() + "/基表/" + suffix
							+ "drop_tbl.sql";
					String trunFilename = saveDir.getText() + "/基表/" + suffix
							+ "truncate.sql";
					String seqFilename = saveDir.getText() + "/基表/" + suffix
							+ "seq.sql";
					String pkFilename = saveDir.getText() + "/基表/" + suffix
							+ "constraint.sql";
					String dropPkFilename = saveDir.getText() + "/基表/" + suffix
							+ "drop_constraint.sql";
					String fkFilename = saveDir.getText() + "/基表/" + suffix
							+ "fk.sql";
					String dropFkFilename = saveDir.getText() + "/基表/" + suffix
							+ "drop_fk.sql";
					String indexFilename = saveDir.getText() + "/基表/" + suffix
							+ "index.sql";
					String dropIndexFilename = saveDir.getText() + "/基表/"
							+ suffix + "drop_index.sql";

					String commentFilename = saveDir.getText() + "/基表/"
							+ suffix + "comment.sql";

					String ptBaseFilename = saveDir.getText() + "/分表/" + suffix
							+ "pt_base.sql";
					String ptDropFilename = saveDir.getText() + "/分表/" + suffix
							+ "pt_drop_base.sql";
					String ptTrunFilename = saveDir.getText() + "/分表/" + suffix
							+ "pt_truncate.sql";
					String ptPkFilename = saveDir.getText() + "/分表/" + suffix
							+ "pt_constraint_base.sql";
					String ptDropPkFilename = saveDir.getText() + "/分表/"
							+ suffix + "pt_drop_constraint_base.sql";
					String ptIndexFilename = saveDir.getText() + "/分表/"
							+ suffix + "pt_index_base.sql";
					String ptDropIndexFilename = saveDir.getText() + "/分表/"
							+ suffix + "pt_drop_index_base.sql";
					List<ConfigTable> configTables = new ArrayList<ConfigTable>();

					DefaultTableModel model = (DefaultTableModel) table_1
							.getModel();
					if (model.getRowCount() > 0) {
						for (int i = 0; i < model.getRowCount(); i++) {
							if (table_1.getValueAt(i, 0).toString()
									.equalsIgnoreCase("true")) {
								ConfigTable configTable = new ConfigTable();
								configTable.setName(table_1.getValueAt(i, 2)
										.toString());
								if (table_1.getValueAt(i, 3) != null) {
									configTable.setPtType(table_1.getValueAt(i,
											3).toString());
								}
								if (table_1.getValueAt(i, 4) != null) {
									configTable.setPtValue(table_1.getValueAt(
											i, 4).toString());
								}
								configTables.add(configTable);
							}
						}
					}
					List<AbstractBase> creators = new ArrayList<AbstractBase>();

					if (baseSQL.isSelected()) {
						BaseSQLCreator baseSQLCreator = new BaseSQLCreator();
						baseSQLCreator.setAdapterClzz(adapterClzz);
						baseSQLCreator.setScriptFilename(baseFilename);
						baseSQLCreator.setConfigTables(configTables);
						baseSQLCreator.setParameter(parameter);
						creators.add(baseSQLCreator);
						// baseSQLCreator.create();

						DropTblSQLCreator dropTblSQLCreator = new DropTblSQLCreator();
						dropTblSQLCreator.setAdapterClzz(adapterClzz);
						dropTblSQLCreator.setScriptFilename(dropFilename);
						dropTblSQLCreator.setConfigTables(configTables);
						dropTblSQLCreator.setParameter(parameter);
						// dropTblSQLCreator.create();
						creators.add(dropTblSQLCreator);
					}
					if (trunSQL.isSelected()) {
						TruncateSQLCreator truncateSQLCreator = new TruncateSQLCreator();
						truncateSQLCreator.setAdapterClzz(adapterClzz);
						truncateSQLCreator.setScriptFilename(trunFilename);
						truncateSQLCreator.setConfigTables(configTables);
						truncateSQLCreator.setParameter(parameter);

						creators.add(truncateSQLCreator);
					}
					if (seqSQL.isSelected()) {

						SeqSQLCreator seqSQLCreator = new SeqSQLCreator();
						seqSQLCreator.setAdapterClzz(adapterClzz);
						seqSQLCreator.setScriptFilename(seqFilename);
						seqSQLCreator.setParameter(parameter);
						seqSQLCreator.createSQL();
					}
					if (pkSQL.isSelected()) {
						ConstraintSQLCreator constraintSQLCreator = new ConstraintSQLCreator();
						constraintSQLCreator.setAdapterClzz(adapterClzz);
						constraintSQLCreator.setScriptFilename(pkFilename);
						constraintSQLCreator.setConfigTables(configTables);
						constraintSQLCreator.setParameter(parameter);
						creators.add(constraintSQLCreator);
						DropConstraintSQLCreator dropConstraintSQLCreator = new DropConstraintSQLCreator();
						dropConstraintSQLCreator.setAdapterClzz(adapterClzz);
						dropConstraintSQLCreator
								.setScriptFilename(dropPkFilename);
						dropConstraintSQLCreator.setConfigTables(configTables);
						dropConstraintSQLCreator.setParameter(parameter);
						creators.add(dropConstraintSQLCreator);
					}
					if (fkSQL.isSelected()) {
						FkSQLCreator fkSQLCreator = new FkSQLCreator();
						fkSQLCreator.setAdapterClzz(adapterClzz);
						fkSQLCreator.setScriptFilename(fkFilename);
						fkSQLCreator.setConfigTables(configTables);
						fkSQLCreator.setParameter(parameter);
						creators.add(fkSQLCreator);
						DropFkSQLCreator dropFkSQLCreator = new DropFkSQLCreator();
						dropFkSQLCreator.setAdapterClzz(adapterClzz);
						dropFkSQLCreator.setScriptFilename(dropFkFilename);
						dropFkSQLCreator.setConfigTables(configTables);
						dropFkSQLCreator.setParameter(parameter);
						creators.add(dropFkSQLCreator);
					}
					if (indexSQL.isSelected()) {
						IndexSQLCreator indexSQLCreator = new IndexSQLCreator();
						indexSQLCreator.setAdapterClzz(adapterClzz);
						indexSQLCreator.setScriptFilename(indexFilename);
						indexSQLCreator.setConfigTables(configTables);
						indexSQLCreator.setParameter(parameter);
						creators.add(indexSQLCreator);
						DropIndexSQLCreator dropIndexSQLCreator = new DropIndexSQLCreator();
						dropIndexSQLCreator.setAdapterClzz(adapterClzz);
						dropIndexSQLCreator
								.setScriptFilename(dropIndexFilename);
						dropIndexSQLCreator.setConfigTables(configTables);
						dropIndexSQLCreator.setParameter(parameter);
						creators.add(dropIndexSQLCreator);
					}
					if (ptBaseSQL.isSelected()) {
						PTBaseSQLCreator baseSQLCreator = new PTBaseSQLCreator();
						baseSQLCreator.setAdapterClzz(adapterClzz);
						baseSQLCreator.setScriptFilename(ptBaseFilename);
						baseSQLCreator.setConfigTables(configTables);
						baseSQLCreator.setParameter(parameter);
						creators.add(baseSQLCreator);
						PTDropBaseSQLCreator dropTblSQLCreator = new PTDropBaseSQLCreator();
						dropTblSQLCreator.setAdapterClzz(adapterClzz);
						dropTblSQLCreator.setScriptFilename(ptDropFilename);
						dropTblSQLCreator.setConfigTables(configTables);
						dropTblSQLCreator.setParameter(parameter);
						creators.add(dropTblSQLCreator);
					}
					if (ptPkSQL.isSelected()) {
						PTConstraintSQLCreator constraintSQLCreator = new PTConstraintSQLCreator();
						constraintSQLCreator.setAdapterClzz(adapterClzz);
						constraintSQLCreator.setScriptFilename(ptPkFilename);
						constraintSQLCreator.setConfigTables(configTables);
						constraintSQLCreator.setParameter(parameter);
						creators.add(constraintSQLCreator);
						PTDropConstraintSQLCreator dropConstraintSQLCreator = new PTDropConstraintSQLCreator();
						dropConstraintSQLCreator.setAdapterClzz(adapterClzz);
						dropConstraintSQLCreator
								.setScriptFilename(ptDropPkFilename);
						dropConstraintSQLCreator.setConfigTables(configTables);
						dropConstraintSQLCreator.setParameter(parameter);
						creators.add(dropConstraintSQLCreator);
					}

					if (ptIndexSQL.isSelected()) {
						PTIndexSQLCreator indexSQLCreator = new PTIndexSQLCreator();
						indexSQLCreator.setAdapterClzz(adapterClzz);
						indexSQLCreator.setScriptFilename(ptIndexFilename);
						indexSQLCreator.setConfigTables(configTables);
						indexSQLCreator.setParameter(parameter);
						creators.add(indexSQLCreator);
						PTDropIndexSQLCreator dropIndexSQLCreator = new PTDropIndexSQLCreator();
						dropIndexSQLCreator.setAdapterClzz(adapterClzz);
						dropIndexSQLCreator
								.setScriptFilename(ptDropIndexFilename);
						dropIndexSQLCreator.setConfigTables(configTables);
						dropIndexSQLCreator.setParameter(parameter);
						creators.add(dropIndexSQLCreator);
					}
					if (ptTrunSQL.isSelected()) {
						PTTruncateSQLCreator truncateSQLCreator = new PTTruncateSQLCreator();
						truncateSQLCreator.setAdapterClzz(adapterClzz);
						truncateSQLCreator.setScriptFilename(ptTrunFilename);
						truncateSQLCreator.setConfigTables(configTables);
						truncateSQLCreator.setParameter(parameter);
						creators.add(truncateSQLCreator);
					}
					if (commentSQL.isSelected()) {
						CommentCreator commentCreator = new CommentCreator();
						commentCreator.setAdapterClzz(adapterClzz);
						commentCreator.setScriptFilename(commentFilename);
						commentCreator.setConfigTables(configTables);
						commentCreator.setParameter(parameter);
						creators.add(commentCreator);
					}
					new Thread(new CreatorThread(creators)).start();
				}
			}
		});
		button_4.setBounds(462, 321, 112, 34);
		getContentPane().add(button_4);

		saveDir = new JTextField();
		saveDir.setBackground(Color.WHITE);
		saveDir.setEnabled(false);
		saveDir.setEditable(false);
		saveDir.setBounds(0, 292, 459, 21);
		getContentPane().add(saveDir);
		saveDir.setColumns(10);

		JButton button_5 = new JButton("选择保存路径");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(table_1.getRowCount());
				if (table.getSelectedRow() > -1) {

					javax.swing.JFileChooser cf = new javax.swing.JFileChooser() {
						public void approveSelection() {
							DefaultTableModel model = (DefaultTableModel) table
									.getModel();
							String rootDir = this.getSelectedFile()
									.getAbsolutePath().replaceAll("\\\\", "/");
							this.cancelSelection();
							if (table.getSelectedRow() > -1) {
								saveDir.setText(rootDir);
								int sqlSequence = Integer.parseInt(table
										.getValueAt(table.getSelectedRow(), 0)
										.toString());
								AilkDbConfig
										.getInstance()
										.setProperty(
												ConfigConst
														.getCreateSQLSaveDirKey(sqlSequence),
												rootDir);
							}

						}

					};
					cf.setDialogTitle("选择文件夹...");
					cf.setFileSelectionMode(1);
					cf.showDialog(frame, "选择");
				}
			}
		});
		button_5.setBounds(462, 292, 112, 23);
		getContentPane().add(button_5);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 365, 574, 76);
		getContentPane().add(scrollPane_2);

		logTable = new JTable();
		logTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "时间", "日志" }));
		TableColumn timeColumn = logTable.getColumnModel().getColumn(0);
		timeColumn.setMaxWidth(100);
		timeColumn.setMinWidth(100);
		timeColumn.setPreferredWidth(100);
		scrollPane_2.setViewportView(logTable);

		baseSQL.setBounds(50, 311, 55, 23);
		getContentPane().add(baseSQL);

		pkSQL.setBounds(105, 311, 55, 23);
		getContentPane().add(pkSQL);

		indexSQL.setBounds(160, 311, 55, 23);
		getContentPane().add(indexSQL);

		fkSQL.setBounds(215, 311, 55, 23);
		getContentPane().add(fkSQL);

		seqSQL.setBounds(270, 311, 55, 23);
		getContentPane().add(seqSQL);

		trunSQL.setBounds(325, 311, 55, 23);
		getContentPane().add(trunSQL);

		ptBaseSQL.setBounds(50, 336, 55, 23);
		getContentPane().add(ptBaseSQL);

		ptPkSQL.setBounds(105, 336, 55, 23);
		getContentPane().add(ptPkSQL);

		ptIndexSQL.setBounds(160, 336, 55, 23);
		getContentPane().add(ptIndexSQL);

		JLabel label = new JLabel("分表");
		label.setFont(new Font("宋体", Font.BOLD, 12));
		label.setForeground(Color.RED);
		label.setBounds(10, 340, 34, 15);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("基表");
		label_1.setFont(new Font("宋体", Font.BOLD, 12));
		label_1.setForeground(Color.RED);
		label_1.setBounds(10, 315, 34, 15);
		getContentPane().add(label_1);

		ptTrunSQL.setBounds(215, 336, 103, 23);
		getContentPane().add(ptTrunSQL);

		commentSQL.setBounds(382, 311, 55, 23);
		getContentPane().add(commentSQL);

		JLabel label_2 = new JLabel("文件名前缀");
		label_2.setBounds(361, 123, 76, 15);
		getContentPane().add(label_2);

		filenameSuffix = new JTextField();
		filenameSuffix.setBounds(434, 120, 140, 21);
		getContentPane().add(filenameSuffix);
		filenameSuffix.setColumns(10);

		JButton btnTblName = new JButton("表差异分析");
		btnTblName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_1.getSelectedIndex() < 0) {
					return;
				}
				List<String> filenames = new ArrayList<String>();
				Combox combox = lstSource.get(comboBox_1.getSelectedIndex() - 1);
				String pdmSequence = combox.getValue();
				List<HierarchicalConfiguration> children = AilkDbConfig
						.getInstance().configurationsAt(
								ConfigConst.getPDMFilesKey(Integer
										.parseInt(pdmSequence)));
				for (HierarchicalConfiguration child : children) {

					List<ConfigurationNode> childrenNodes = child.getRoot()
							.getChildren();
					for (ConfigurationNode node : childrenNodes) {

						String filename = AilkDbConfig.getInstance().getString(
								ConfigConst.getPDMFilesKey(Integer
										.parseInt(pdmSequence))
										+ "."
										+ node.getName() + ".filename");
						filenames.add(filename);

					}
				}
				String[] arrTblNames = new String[filenames.size()];
				for (int i = 0; i < filenames.size(); i++) {
					arrTblNames[i] = filenames.get(i);
				}
				StringBuffer pdmStrBuff = new StringBuffer();
				List<String> tablenames = PdmTools.getTableNames(arrTblNames);
				for (String tablename : tablenames) {
					boolean isExist = false;
					for (int i = 0; i < table_1.getRowCount(); i++) {

						String memTablename = table_1.getModel()
								.getValueAt(i, 2).toString();
						if (tablename.equalsIgnoreCase(memTablename)) {
							isExist = true;
							break;

						}

					}
					if (!isExist) {
						pdmStrBuff.append(tablename);
						pdmStrBuff.append("\n");
					}
				}
				StringBuffer memStrBuff = new StringBuffer();
				for (int i = 0; i < table_1.getRowCount(); i++) {

					String memTablename = table_1.getModel().getValueAt(i, 2)
							.toString();
					boolean isExist = false;
					for (String tablename : tablenames) {
						if (tablename.equalsIgnoreCase(memTablename)) {
							isExist = true;
							break;

						}
					}
					if (!isExist) {
						memStrBuff.append(memTablename);
						memStrBuff.append("\n");
					}
				}

				TblAna tblAna = new TblAna(pdmStrBuff.toString(), memStrBuff
						.toString());
				tblAna.setVisible(true);

			}
		});
		btnTblName.setBounds(481, 169, 93, 23);
		getContentPane().add(btnTblName);
		Document dt = filenameSuffix.getDocument();
		dt.addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println(filenameSuffix.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changeFilenameSuffixValue(filenameSuffix.getText());

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changeFilenameSuffixValue(filenameSuffix.getText());
			}

		});

		initTableData();
	}

	private void changeFilenameSuffixValue(String content) {
		if (table.getSelectedRow() != -1) {
			int sqlSequence = Integer.parseInt(table.getValueAt(
					table.getSelectedRow(), 0).toString());
			AilkDbConfig.getInstance().setProperty(
					ConfigConst.getCreateSQLFilenameSuffixKey(sqlSequence),
					content);
		}
	}

	private void initDbTableData() {
		if (table.getSelectedRow() != -1) {
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			if (model.getRowCount() > 0) {
				for (int i = model.getRowCount() - 1; i >= 0; i--) {
					model.removeRow(i);
				}
			}
			int sqlSequence = Integer.parseInt(table.getValueAt(
					table.getSelectedRow(), 0).toString());

			List<HierarchicalConfiguration> children = AilkDbConfig
					.getInstance().configurationsAt(
							ConfigConst.getCreateSQLKey(sqlSequence)
									+ ".tables");
			for (HierarchicalConfiguration child : children) {

				List<ConfigurationNode> childrenNodes = child.getRoot()
						.getChildren();
				for (ConfigurationNode node : childrenNodes) {
					int sequence = AilkDbConfig
							.getInstance()
							.getInt(ConfigConst.getCreateSQLKey(sqlSequence)
									+ ".tables." + node.getName() + ".sequence");
					boolean checked = false;
					String checkValue = AilkDbConfig.getInstance()
							.getString(
									ConfigConst.getTableCheckKey(sqlSequence,
											sequence));
					if (StringUtils.isNotEmpty(checkValue)
							&& checkValue.equalsIgnoreCase("true")) {
						checked = true;
					}
					String name = AilkDbConfig.getInstance().getString(
							ConfigConst.getTableNameKey(sqlSequence, sequence));
					String ptType = AilkDbConfig.getInstance().getString(
							ConfigConst
									.getTablePtTypeKey(sqlSequence, sequence));
					String ptValue = AilkDbConfig.getInstance().getString(
							ConfigConst.getTablePtValueKey(sqlSequence,
									sequence));
					if (ptType == null) {
						ptType = "";
					}
					if (ptValue == null) {
						ptValue = "";
					}
					model.addRow(new Object[] { checked,
							String.valueOf(sequence), name, ptType, ptValue });

				}
			}
		}
	}

	private void initTableData() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		List<HierarchicalConfiguration> children = AilkDbConfig.getInstance()
				.configurationsAt(ConfigConst.CREATE_SQL_KEY);
		for (HierarchicalConfiguration child : children) {

			List<ConfigurationNode> childrenNodes = child.getRoot()
					.getChildren();
			for (ConfigurationNode node : childrenNodes) {
				int sequence = AilkDbConfig.getInstance().getInt(
						ConfigConst.CREATE_SQL_KEY + "." + node.getName()
								+ ".sequence");
				model.addRow(new String[] {
						String.valueOf(sequence),
						AilkDbConfig.getInstance().getString(
								ConfigConst.getCreateSQLAliasKey(sequence)) });

			}
		}
	}

	private void getLstDB() {
		lstSource.clear();
		List<HierarchicalConfiguration> children = AilkDbConfig.getInstance()
				.configurationsAt(ConfigConst.DB_KEY);
		for (HierarchicalConfiguration child : children) {

			List<ConfigurationNode> childrenNodes = child.getRoot()
					.getChildren();
			for (ConfigurationNode node : childrenNodes) {
				int sequence = AilkDbConfig.getInstance()
						.getInt(ConfigConst.DB_KEY + "." + node.getName()
								+ ".sequence");

				Combox combox = new Combox();
				combox.setName(AilkDbConfig.getInstance().getString(
						ConfigConst.getDBAliasKey(sequence)));
				combox.setValue(String.valueOf(sequence));
				lstSource.add(combox);

			}
		}
	}

	private void getLstPdm() {
		lstSource.clear();
		List<HierarchicalConfiguration> children = AilkDbConfig.getInstance()
				.configurationsAt(ConfigConst.PDM_KEY);
		for (HierarchicalConfiguration child : children) {

			List<ConfigurationNode> childrenNodes = child.getRoot()
					.getChildren();
			for (ConfigurationNode node : childrenNodes) {
				int sequence = AilkDbConfig.getInstance().getInt(
						ConfigConst.PDM_KEY + "." + node.getName()
								+ ".sequence");

				Combox combox = new Combox();
				combox.setName(AilkDbConfig.getInstance().getString(
						ConfigConst.getPDMAliasKey(sequence)));
				combox.setValue(String.valueOf(sequence));
				lstSource.add(combox);

			}
		}
	}

	/**
	 * 
	 */
	JComboBox comboBox_1;
	JComboBox comboBox;
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	List<Combox> lstSource = new ArrayList<Combox>();
	private JTextField saveDir;
	public static JTable logTable;
	private JCheckBox baseSQL = new JCheckBox("创表");
	private JCheckBox pkSQL = new JCheckBox("主键");
	private JCheckBox indexSQL = new JCheckBox("索引");
	private JCheckBox fkSQL = new JCheckBox("外键");
	private JCheckBox seqSQL = new JCheckBox("序列");
	private JCheckBox trunSQL = new JCheckBox("清空");
	private JCheckBox ptBaseSQL = new JCheckBox("创表");
	private JCheckBox ptPkSQL = new JCheckBox("主键");
	private JCheckBox ptIndexSQL = new JCheckBox("索引");
	private JCheckBox ptTrunSQL = new JCheckBox("清空");
	private JCheckBox commentSQL = new JCheckBox("注释");
	private JTextField filenameSuffix;
}
