package com.blue.ailk.app;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.blue.ailk.config.AilkDbConfig;
import com.blue.ailk.ui.CreateSqlUI;
import com.blue.ailk.ui.DBConfigUI;
import com.blue.ailk.ui.PDMConfigUI;
import com.blue.core.lang.io.TextUtils;

public class UIMain {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIMain window = new UIMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UIMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("库表分析");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menu = new JMenu("开始");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("退出");
		menuItem.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				System.exit(0);
			}
		});
		menu.add(menuItem);

		JMenu menu_2 = new JMenu("编辑");
		menuBar.add(menu_2);

		JMenuItem menuItem_1 = new JMenuItem("生成脚本");
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				openFrame(new CreateSqlUI(iFrams, frame));
			}
		});
		menu_2.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("比较差异");
		menu_2.add(menuItem_2);

		JMenu menu_1 = new JMenu("设置");
		menuBar.add(menu_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("数据库设置");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				openFrame(new DBConfigUI(iFrams, frame));
			}

		});
		menu_1.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("PDM设置");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				openFrame(new PDMConfigUI(iFrams, frame));

			}

		});
		menu_1.add(mntmNewMenuItem_1);
		frame.getContentPane().setLayout(null);
		initParams();
	}

	private void openFrame(JInternalFrame openFrame) {
		for (int i = iFrams.size() - 1; i >= 0; i--) {
			JInternalFrame fra = iFrams.get(i);
			if (fra == null) {
				iFrams.remove(i);
				continue;
			}
			fra.hide();
			frame.getContentPane().remove(fra);

		}

		frame.getContentPane().add(openFrame);
		openFrame.setVisible(true);

		for (JInternalFrame fra : iFrams) {
			if (fra != null) {
				fra.setVisible(true);
				frame.getContentPane().add(fra);

			}
		}
		iFrams.add(openFrame);
	}

	void initParams() {
		if (!new File(AilkDbConfig.CONFIG_FILE_NAME).exists()) {
			String content = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"no\"?><root></root>";
			TextUtils.write(AilkDbConfig.CONFIG_FILE_NAME, content, "UTF-8");
		}
	}

	List<JInternalFrame> iFrams = new ArrayList<JInternalFrame>();
}
