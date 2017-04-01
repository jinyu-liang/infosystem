package com.blue.ailk.ui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TblAna extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TblAna frame = new TblAna(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TblAna(String pdmTbl,String memTbl) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		setTitle("分析结果");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 573, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblpdm = new JLabel("只在PDM存在");
		lblpdm.setBounds(10, 10, 128, 15);
		contentPane.add(lblpdm);
		
		JLabel label = new JLabel("只在列表框中存在");
		label.setBounds(10, 186, 300, 25);
		contentPane.add(label);
		
		JTextArea pdmTA = new JTextArea();		
		pdmTA.setLineWrap(true);
		JScrollPane scrollPanePdm = new JScrollPane(pdmTA);
		scrollPanePdm.setBounds(10, 35, 500, 120);
		contentPane.add(scrollPanePdm);
		pdmTA.setText(pdmTbl);
		pdmTA.setCaretPosition(0);
		JTextArea memTA = new JTextArea();		
		memTA.setLineWrap(true);
		JScrollPane scrollPaneMem = new JScrollPane(memTA);
		scrollPaneMem.setBounds(10, 220, 500, 120);
		contentPane.add(scrollPaneMem);
		memTA.setText(memTbl);
		memTA.setCaretPosition(0);
	}
}
