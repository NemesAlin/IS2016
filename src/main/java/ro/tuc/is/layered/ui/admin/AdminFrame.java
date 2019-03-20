package ro.tuc.is.layered.ui.admin;

import java.awt.Color;

import javax.swing.JFrame;

public class AdminFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminFrame() {
		this.setTitle("Admin Panel");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1366, 768);
		this.setVisible(true);
		AdminPanel adminpanel = new AdminPanel();
		this.add(adminpanel);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
	}

}
