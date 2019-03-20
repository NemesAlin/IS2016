package ro.tuc.is.layered.ui.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ro.tuc.is.layered.bll.CustomerBLL;
import ro.tuc.is.layered.ui.MainPanel;
import ro.tuc.is.layered.ui.admin.AdminFrame;
import ro.tuc.is.layered.ui.register.RegisterFrame;

public class LogInPanel extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel register;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private MainPanel mainp;

	public LogInPanel(MainPanel mainp) {
		this.mainp = mainp;
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.BLACK);// culoarea de fundal

		usernameLabel = new JLabel();
		usernameLabel.setText("Username:");// se seteaza textul label-ului
		usernameLabel.setForeground(Color.WHITE);// se schimba culoarea fontului
		usernameLabel.setBackground(Color.WHITE);
		usernameLabel.setBounds(5, 100, 150, 30);// se seteaza coordonatele
		this.add(usernameLabel);

		passwordLabel = new JLabel();
		passwordLabel.setText("Password:");
		passwordLabel.setForeground(Color.WHITE);// se simba culoare fontului
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setBounds(5, 140, 150, 30);// se seteaza coordonatele
		this.add(passwordLabel);

		usernameField = new JTextField();
		usernameField.setBounds(80, 100, 200, 30);// se seteaza coordonatele
		this.add(usernameField);

		passwordField = new JPasswordField();// se creeaza passwordfield-ul
		passwordField.setBounds(80, 140, 200, 30);// se seteaza coordonatele
		this.add(passwordField);

		loginButton = new JButton();// se creeaza butonul de login
		loginButton.setText("Login");
		loginButton.setBounds(80, 180, 100, 30);
		loginButton.setBackground(Color.YELLOW);
		loginButton.setForeground(Color.RED);
		loginButton.addActionListener(this);
		this.add(loginButton);

		register = new JLabel();
		register.setText("<HTML><U>New? Register Here!</U></HTML>");
		register.setBounds(80, 200, 100, 100);
		register.setForeground(Color.RED);
		register.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		register.setBackground(Color.white);
		register.addMouseListener(this);
		this.add(register);

	}

	@SuppressWarnings({ "deprecation" })
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			CustomerBLL customerBll = new CustomerBLL();
			List<String> validateCustomer = customerBll.validateCustomer(
					usernameField.getText(), passwordField.getText());
			if (usernameField.getText().equals("")
					|| passwordField.getText().equals("")) {
				JOptionPane.showMessageDialog(this,
						"Please provide a username and a password!", "Error",
						JOptionPane.ERROR_MESSAGE);// /cazul in care nu s-a
													// introdus nimic in campuri
				usernameField.setText("");
				passwordField.setText("");
			} else if (validateCustomer.contains("unexisting username")) {
				JOptionPane.showMessageDialog(this,
						"There was no user found for username \""
								+ usernameField.getText()
								+ "\" !\nPlease try again", "Error",
						JOptionPane.ERROR_MESSAGE);// //mesaj de eroare in cazul
													// in care nu exista acest
													// cont in baza de date
				usernameField.setText("");
				passwordField.setText("");
			}
			if (validateCustomer.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Log In Succesfull !",
						"Succes", JOptionPane.INFORMATION_MESSAGE);
				mainp.setUserLogged(usernameField.getText(), true);
				LogInFrame.close();
				usernameField.setText("");
				passwordField.setText("");
			}
			if (validateCustomer.contains("admin")) {
				usernameField.setText("");
				passwordField.setText("");
				System.out.println("Admin logged in!");
				new AdminFrame(); // instanta pentru panoul de administrare
			} else if (validateCustomer.contains("Wrong password!!!")) {
				JOptionPane.showMessageDialog(this,
						"Wrong password!!!\nPlease try again", "Error",
						JOptionPane.ERROR_MESSAGE);// /mesaj de eroare, parola
													// incorecta
				passwordField.setText("");
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == register) {
			new RegisterFrame();
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
