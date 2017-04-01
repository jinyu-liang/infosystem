package com.blue.ailk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.lang.StringUtils;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.ailk.constant.ConfigConst;

public class PDMConfigUI extends InFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public PDMConfigUI(List<JInternalFrame> objIFrams, JFrame parentFrame) {
		super(objIFrams, parentFrame);
		setClosable(true);
		setBounds(100, 10, 600, 400);
		setTitle("PDM配置");
		setMaximizable(true);
		setResizable(true);
		table = new JTable();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"\u5E8F\u53F7", "PMD集合\u522B\u540D" }) {
			private static final long serialVersionUID = 1L;

			// public boolean isCellEditable(int rowindex, int colindex) {
			// if (colindex == 0) {
			// return false;
			// }
			// return true;
			// }

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (StringUtils.isNotEmpty(String.valueOf(aValue))) {
					if (!aValue.equals(table.getValueAt(rowIndex, columnIndex))) {
						// 数据发生变更
						int sequence = Integer.parseInt(table.getValueAt(
								rowIndex, 0).toString());

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getPDMAliasKey(sequence), aValue);

					}
					super.setValueAt(aValue, rowIndex, columnIndex);
				}

			}
		});
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (table.getSelectedRow() > -1) {
							int sequence = Integer.parseInt(table.getValueAt(
									table.getSelectedRow(), 0).toString());
							initTablePDMData(sequence);
						}
					}

				});
		getContentPane().setLayout(null);
		table.setRowHeight(20);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 426, 124);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		// firsetColumn.setPreferredWidth(60);
		firsetColumn.setMaxWidth(0);
		firsetColumn.setMinWidth(0);

		firsetColumn.setPreferredWidth(0);
		firsetColumn.setResizable(false);

		JButton btnAddDBConfig = new JButton("新增集合");
		btnAddDBConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int dbSequnce = ConfigConst.getSequence();

				model.addRow(new String[] { String.valueOf(dbSequnce), "集合别名" });
				table.setRowSelectionInterval(table.getRowCount() - 1,
						table.getRowCount() - 1);

				AilkDbConfig.getInstance().setProperty(
						ConfigConst.getPDMAliasKey(dbSequnce),
						table.getValueAt(table.getRowCount() - 1, 1));
				AilkDbConfig.getInstance().setProperty(
						ConfigConst.getPDMSequenceKey(dbSequnce),
						String.valueOf(dbSequnce));

			}
		});
		btnAddDBConfig.setBounds(437, 71, 112, 23);
		getContentPane().add(btnAddDBConfig);
		JButton btnDelDBConfig = new JButton("删除集合");
		btnDelDBConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() > -1) {

					String sequence = table.getValueAt(table.getSelectedRow(),
							0).toString();
					AilkDbConfig.getInstance().clearPropertyAndChildren(
							ConfigConst.getPDMKey(Integer.valueOf(sequence)));

					model.removeRow(table.getSelectedRow());
					DefaultTableModel modelPdm = (DefaultTableModel) tablePdm
							.getModel();
					if (modelPdm.getRowCount() > 0) {
						for (int i = modelPdm.getRowCount() - 1; i >= 0; i--) {
							modelPdm.removeRow(i);
						}
					}
				}
			}
		});
		btnDelDBConfig.setBounds(436, 101, 113, 23);
		getContentPane().add(btnDelDBConfig);

		JScrollPane scrollPanePdm = new JScrollPane();
		scrollPanePdm.setBounds(0, 164, 426, 135);
		getContentPane().add(scrollPanePdm);

		tablePdm = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;

			}
		};
		tablePdm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePdm.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u5E8F\u53F7", "PDM\u6587\u4EF6" }));
		scrollPanePdm.setViewportView(tablePdm);
		TableColumn pdmFirsetColumn = tablePdm.getColumnModel().getColumn(0);

		pdmFirsetColumn.setMaxWidth(0);
		pdmFirsetColumn.setMinWidth(0);

		pdmFirsetColumn.setPreferredWidth(0);
		pdmFirsetColumn.setResizable(false);
		JButton btnDelPdm = new JButton("删除PDM");
		btnDelPdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tablePdm
						.getModel();
				if (tablePdm.getSelectedRow() > -1) {

					String sequence = tablePdm.getValueAt(
							tablePdm.getSelectedRow(), 0).toString();
					int pdmSequence = Integer.parseInt(table.getValueAt(
							table.getSelectedRow(), 0).toString());
					String filesKey = ConfigConst.getPDMFilesKey(pdmSequence);

					AilkDbConfig.getInstance().clearPropertyAndChildren(
							filesKey + ".file" + String.valueOf(sequence));

					model.removeRow(tablePdm.getSelectedRow());
				}
			}
		});
		btnDelPdm.setBounds(437, 276, 106, 23);
		getContentPane().add(btnDelPdm);

		JButton btnSelPdm = new JButton("选择PDM");
		btnSelPdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > -1) {
					javax.swing.JFileChooser cf = new javax.swing.JFileChooser() {
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						public void approveSelection() {
							int pdmSequence = Integer.parseInt(table
									.getValueAt(table.getSelectedRow(), 0)
									.toString());
							String filesKey = ConfigConst
									.getPDMFilesKey(pdmSequence);
							String filename = this.getSelectedFile()
									.getAbsolutePath().replaceAll("\\\\", "/");
							int fileSequece = ConfigConst.getSequence();
							AilkDbConfig.getInstance().setProperty(
									filesKey + ".file"
											+ String.valueOf(fileSequece)
											+ ".filename", filename);
							AilkDbConfig.getInstance().setProperty(
									filesKey + ".file"
											+ String.valueOf(fileSequece)
											+ ".sequence",
									String.valueOf(fileSequece));
							DefaultTableModel model = (DefaultTableModel) tablePdm
									.getModel();
							model.addRow(new String[] {
									String.valueOf(fileSequece), filename });
							this.cancelSelection();
						}

					};
					FileFilter filter = new FileNameExtensionFilter("PDM文件",
							"pdm");
					cf.setCurrentDirectory(new File("D:/"));
					cf.setFileFilter(filter);
					cf.setDialogTitle("请选择文件或文件夹");

					cf.setFileSelectionMode(JFileChooser.FILES_ONLY);

					cf.showOpenDialog(frame);
				}
			}
		});
		btnSelPdm.setBounds(437, 239, 106, 23);
		getContentPane().add(btnSelPdm);
		initTableData((DefaultTableModel) table.getModel());
	}

	private void initTablePDMData(int pdmSequence) {

		String filesKey = ConfigConst.getPDMFilesKey(pdmSequence);
		DefaultTableModel model = (DefaultTableModel) tablePdm.getModel();
		if (model.getRowCount() > 0) {
			for (int i = model.getRowCount() - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
		List<HierarchicalConfiguration> children = AilkDbConfig.getInstance()
				.configurationsAt(filesKey);
		for (HierarchicalConfiguration child : children) {
			List<ConfigurationNode> childrenNodes = child.getRoot()
					.getChildren();
			for (ConfigurationNode node : childrenNodes) {
				int sequence = AilkDbConfig.getInstance().getInt(
						filesKey + "." + node.getName() + ".sequence");
				model.addRow(new String[] {
						String.valueOf(sequence),
						AilkDbConfig.getInstance().getString(
								filesKey + "." + node.getName() + ".filename") });

			}
		}
	}

	private void initTableData(DefaultTableModel model) {
		List<HierarchicalConfiguration> children = AilkDbConfig.getInstance()
				.configurationsAt(ConfigConst.PDM_KEY);
		for (HierarchicalConfiguration child : children) {

			List<ConfigurationNode> childrenNodes = child.getRoot()
					.getChildren();
			for (ConfigurationNode node : childrenNodes) {
				int sequence = AilkDbConfig.getInstance().getInt(
						ConfigConst.PDM_KEY + "." + node.getName()
								+ ".sequence");
				model.addRow(new String[] {
						String.valueOf(sequence),
						AilkDbConfig.getInstance().getString(
								ConfigConst.getPDMAliasKey(sequence)) });

			}
		}
	}

	private JTable table;

	private JTable tablePdm;
}
