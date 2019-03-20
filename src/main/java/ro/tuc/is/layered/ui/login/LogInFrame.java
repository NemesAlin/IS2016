package ro.tuc.is.layered.ui.login;

import java.awt.Color;

import javax.swing.JFrame;

import ro.tuc.is.layered.ui.MainPanel;

public class LogInFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame = new JFrame();
	@SuppressWarnings("unused")
	private MainPanel mainp;

	public LogInFrame(MainPanel mainp) {
		this.mainp=mainp;
		frame.setTitle("Log in Window");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 400);
		frame.setVisible(true);
		LogInPanel loginpanel = new LogInPanel(mainp);
		frame.add(loginpanel);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.BLACK);

	}

	public static void close() {
		frame.dispose();
	}
}
