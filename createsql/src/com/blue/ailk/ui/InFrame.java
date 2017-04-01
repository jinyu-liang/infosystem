package com.blue.ailk.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
public class InFrame extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InFrame(List<JInternalFrame> objIFrams, JFrame parentFrame) {
		this.iFrams = objIFrams;
		this.frame = parentFrame;
		addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				for (int i = iFrams.size() - 1; i >= 0; i--) {
					JInternalFrame fra = iFrams.get(i);
					if (fra == null) {
						iFrams.remove(i);
						continue;
					}

					if (ui.hashCode() == fra.hashCode()) {
						iFrams.remove(i);

					}
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				for (int i = iFrams.size() - 1; i >= 0; i--) {
					JInternalFrame fra = iFrams.get(i);
					if (fra == null) {
						iFrams.remove(i);
						continue;
					}
					fra.hide();
					if (ui.hashCode() == fra.hashCode()) {
						iFrams.remove(i);
						continue;
					}

					frame.getContentPane().remove(fra);

				}

				frame.getContentPane().add(ui);
				ui.setVisible(true);

				for (JInternalFrame fra : iFrams) {
					if (fra != null) {
						fra.setVisible(true);
						frame.getContentPane().add(fra);

					}
				}
				iFrams.add(ui);

			}

		});

	}
	public InFrame ui = this;
	List<JInternalFrame> iFrams = new ArrayList<JInternalFrame>();
	JFrame frame = null;
}
