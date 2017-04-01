package com.blue.ailk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.lang.StringUtils;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.ailk.constant.ConfigConst;

public class DBConfigUI extends InFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public DBConfigUI(List<JInternalFrame> objIFrams, JFrame parentFrame) {
		super(objIFrams, parentFrame);

		setClosable(true);
		setBounds(100, 10, 600, 400);
		setTitle("DB配置");
		setMaximizable(true);
		setResizable(true);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 426, 124);
		getContentPane().add(scrollPane);

		table = new JTable();

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"\u5E8F\u53F7", "DB\u522B\u540D" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowindex, int colindex) {
				if (colindex == 0) {
					return false;
				}
				return true;
			}

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (StringUtils.isNotEmpty(String.valueOf(aValue))) {
					if (!aValue.equals(table.getValueAt(rowIndex, columnIndex))) {
						// 数据发生变更
						int sequence = Integer.parseInt(table.getValueAt(
								rowIndex, 0).toString());

						AilkDbConfig.getInstance().setProperty(
								ConfigConst.getDBAliasKey(sequence), aValue);

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

							txtJdbcUrl.setText(AilkDbConfig.getInstance()
									.getString(
											ConfigConst
													.getDBJdbcUrlKey(sequence)));
							txtUsername.setText(AilkDbConfig.getInstance()
									.getString(
											ConfigConst.getDBUserKey(sequence)));
							txtPassword.setText(AilkDbConfig
									.getInstance()
									.getString(
											ConfigConst
													.getDBPasswordKey(sequence)));
						}
					}

				});
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);

		firsetColumn.setMaxWidth(0);
		firsetColumn.setMinWidth(0);
		firsetColumn.setPreferredWidth(0);
		firsetColumn.setResizable(false);
		JButton btnAddDBConfig = new JButton("新增数据源");
		btnAddDBConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int dbSequnce = ConfigConst.getSequence();

				model.addRow(new String[] { String.valueOf(dbSequnce), "数据源别名" });
				table.setRowSelectionInterval(table.getRowCount() - 1,
						table.getRowCount() - 1);

				AilkDbConfig.getInstance().setProperty(
						ConfigConst.getDBAliasKey(dbSequnce),
						table.getValueAt(table.getRowCount() - 1, 1));
				AilkDbConfig.getInstance().setProperty(
						ConfigConst.getDBSequenceKey(dbSequnce),
						String.valueOf(dbSequnce));
			}

		});
		btnAddDBConfig.setBounds(437, 71, 112, 23);
		getContentPane().add(btnAddDBConfig);

		JButton btnDelDBConfig = new JButton("删除数据源");
		btnDelDBConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() > -1) {

					String sequence = table.getValueAt(table.getSelectedRow(),
							0).toString();
					AilkDbConfig.getInstance().clearPropertyAndChildren(
							ConfigConst.getDBDataSourceKey(Integer
									.valueOf(sequence)));

					model.removeRow(table.getSelectedRow());
				}
			}
		});
		btnDelDBConfig.setBounds(436, 101, 113, 23);
		getContentPane().add(btnDelDBConfig);

		comDriverClass = new JComboBox();
		comDriverClass.setModel(new DefaultComboBoxModel(
				new String[] { "oracle.jdbc.driver.OracleDriver" }));
		comDriverClass.setBounds(111, 149, 229, 21);
		getContentPane().add(comDriverClass);

		JLabel lblNewLabel = new JLabel("DriverClass");
		lblNewLabel.setBounds(24, 152, 77, 15);
		getContentPane().add(lblNewLabel);

		JLabel lblJdbcurl = new JLabel("JdbcUrl");
		lblJdbcurl.setBounds(24, 188, 54, 15);
		getContentPane().add(lblJdbcurl);

		txtJdbcUrl = new JTextField();
		txtJdbcUrl.setBounds(111, 185, 229, 21);
		getContentPane().add(txtJdbcUrl);
		txtJdbcUrl.setColumns(10);

		Document dt = txtJdbcUrl.getDocument();
		dt.addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println(txtJdbcUrl.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changeJdbcUrlValue(txtJdbcUrl.getText());

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changeJdbcUrlValue(txtJdbcUrl.getText());
			}

		});

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(24, 225, 77, 15);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(24, 260, 77, 15);
		getContentPane().add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(111, 222, 229, 21);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent e) {
						System.out.println(txtUsername.getText());
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						changeUserValue(txtUsername.getText());

					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						changeUserValue(txtUsername.getText());
					}

				});

		txtPassword = new JTextField();
		txtPassword.setBounds(111, 257, 229, 21);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.getDocument().addDocumentListener(
				new javax.swing.event.DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent e) {
						System.out.println(txtPassword.getText());
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						changePasswordValue(txtPassword.getText());

					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						changePasswordValue(txtPassword.getText());
					}

				});
		initTableData((DefaultTableModel) table.getModel());
	}

	private void changeUserValue(String value) {

		if (table.getSelectedRow() > -1) {

			String sequence = table.getValueAt(table.getSelectedRow(), 0)
					.toString();

			AilkDbConfig.getInstance().setProperty(
					ConfigConst.getDBUserKey(Integer.valueOf(sequence)), value);

		}
	}

	private void changePasswordValue(String value) {

		if (table.getSelectedRow() > -1) {

			String sequence = table.getValueAt(table.getSelectedRow(), 0)
					.toString();

			AilkDbConfig.getInstance().setProperty(
					ConfigConst.getDBPasswordKey(Integer.valueOf(sequence)),
					value);

		}
	}

	private void changeJdbcUrlValue(String value) {

		if (table.getSelectedRow() > -1) {

			String sequence = table.getValueAt(table.getSelectedRow(), 0)
					.toString();
			AilkDbConfig.getInstance().setProperty(
					ConfigConst.getDBDriverClassKey(Integer.valueOf(sequence)),
					comDriverClass.getSelectedItem().toString());

			AilkDbConfig.getInstance().setProperty(
					ConfigConst.getDBJdbcUrlKey(Integer.valueOf(sequence)),
					value);

		}
	}

	private void initTableData(DefaultTableModel model) {
		List<HierarchicalConfiguration> children = AilkDbConfig.getInstance()
				.configurationsAt(ConfigConst.DB_KEY);
		for (HierarchicalConfiguration child : children) {

			List<ConfigurationNode> childrenNodes = child.getRoot()
					.getChildren();
			for (ConfigurationNode node : childrenNodes) {
				int sequence = AilkDbConfig.getInstance()
						.getInt(ConfigConst.DB_KEY + "." + node.getName()
								+ ".sequence");
				model.addRow(new String[] {
						String.valueOf(sequence),
						AilkDbConfig.getInstance().getString(
								ConfigConst.getDBAliasKey(sequence)) });

			}
		}
	}


	private JTable table;
	private JTextField txtJdbcUrl;
	private JTextField txtUsername;
	private JTextField txtPassword;
	JComboBox comDriverClass;
}
