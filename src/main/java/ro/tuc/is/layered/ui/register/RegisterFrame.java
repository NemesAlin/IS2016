package ro.tuc.is.layered.ui.register;

import java.awt.Color;
import javax.swing.JFrame;

public class RegisterFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame=new JFrame();

	public RegisterFrame() {
		frame.setTitle("Register Window");// setarea titlului ferestrei
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 400);// setarea dimensiunii ferestrei
		frame.setVisible(true);
		RegisterPanel registerpanel = new RegisterPanel();
		frame.add(registerpanel);
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.BLACK);
	}

	public static void close() {
		frame.dispose();
	}
}
