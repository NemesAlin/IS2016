package ro.tuc.is.layered.ui;

import java.awt.Color;

import javax.swing.JFrame;


public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		
		this.setTitle("Main Window");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1366,768);
		this.setVisible(true);
		MainPanel MainPanel= new MainPanel();
		this.add(MainPanel);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
