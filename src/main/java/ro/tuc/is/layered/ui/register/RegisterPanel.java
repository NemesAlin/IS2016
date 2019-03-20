package ro.tuc.is.layered.ui.register;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ro.tuc.is.layered.bll.CustomerBLL;

public class RegisterPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// label Fields
	private JLabel nameLabel;
	private JLabel addressLabel;
	private JLabel phoneLabel;
	private JLabel emailLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	//

	// textFields
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	// 

	// REGISTER BUTTON
	private JButton registerButton;

	//

	public RegisterPanel() {

		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		// THE LABELS
		nameLabel = new JLabel();
		nameLabel.setText("Name:");// se seteaza textul label-ului
		nameLabel.setForeground(Color.WHITE);// se schimba culoarea fontului
		nameLabel.setBackground(Color.WHITE);
		nameLabel.setBounds(5, 10, 150, 30);// se seteaza coordonatele
		this.add(nameLabel);

		addressLabel = new JLabel();
		addressLabel.setText("Address:");// se seteaza textul label-ului
		addressLabel.setForeground(Color.WHITE);// se schimba culoarea fontului
		addressLabel.setBackground(Color.WHITE);
		addressLabel.setBounds(5, 50, 150, 30);// se seteaza coordonatele
		this.add(addressLabel);

		phoneLabel = new JLabel();
		phoneLabel.setText("Phone:");// se seteaza textul label-ului
		phoneLabel.setForeground(Color.WHITE);// se schimba culoarea fontului
		phoneLabel.setBackground(Color.WHITE);
		phoneLabel.setBounds(5, 90, 150, 30);// se seteaza coordonatele
		this.add(phoneLabel);

		emailLabel = new JLabel();
		emailLabel.setText("Email:");// se seteaza textul label-ului
		emailLabel.setForeground(Color.WHITE);// se schimba culoarea fontului
		emailLabel.setBackground(Color.WHITE);
		emailLabel.setBounds(5, 130, 150, 30);// se seteaza coordonatele
		this.add(emailLabel);

		usernameLabel = new JLabel();
		usernameLabel.setText("Username:");// se seteaza textul label-ului
		usernameLabel.setForeground(Color.WHITE);// se schimba culoarea fontului
		usernameLabel.setBackground(Color.WHITE);
		usernameLabel.setBounds(5, 170, 150, 30);// se seteaza coordonatele
		this.add(usernameLabel);

		passwordLabel = new JLabel();
		passwordLabel.setText("Password:");
		passwordLabel.setForeground(Color.WHITE);// se simba culoare fontului
		passwordLabel.setBackground(Color.WHITE);
		passwordLabel.setBounds(5, 210, 150, 30);// se seteaza coordonatele
		this.add(passwordLabel);
		// END OF LABELS

		// THE FIELDS
		nameField = new JTextField();
		nameField.setBounds(80, 10, 200, 30);// se seteaza coordonatele
		this.add(nameField);

		addressField = new JTextField();
		addressField.setBounds(80, 50, 200, 30);// se seteaza coordonatele
		this.add(addressField);

		phoneField = new JTextField();
		phoneField.setBounds(80, 90, 200, 30);// se seteaza coordonatele
		this.add(phoneField);

		emailField = new JTextField();
		emailField.setBounds(80, 130, 200, 30);// se seteaza coordonatele
		this.add(emailField);

		usernameField = new JTextField();
		usernameField.setBounds(80, 170, 200, 30);// se seteaza coordonatele
		this.add(usernameField);

		passwordField = new JPasswordField();// se creeaza passwordfield-ul
		passwordField.setBounds(80, 210, 200, 30);// se seteaza coordonatele
		this.add(passwordField);
		// END OF FIELDS

		// BUTTON
		registerButton = new JButton();// se creeaza butonul de login
		registerButton.setText("Register");
		registerButton.setBounds(80, 250, 100, 30);
		registerButton.setBackground(Color.YELLOW);
		registerButton.setForeground(Color.RED);
		registerButton.addActionListener(this);
		this.add(registerButton);
		//
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButton) {
			CustomerBLL addUser = new CustomerBLL();
			@SuppressWarnings("deprecation")
			List<String> validateRegistetion = addUser.addCustomer(
					nameField.getText(), addressField.getText(),
					phoneField.getText(), emailField.getText(),
					usernameField.getText(), passwordField.getText());
			if (!validateRegistetion.get(0).equals("Insertion Complete!!!")) {
				JOptionPane.showMessageDialog(this, validateRegistetion.get(0),
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(
						this,
						"Register Complete with username \""
								+ usernameField.getText() + "\"", "Succes",
						JOptionPane.INFORMATION_MESSAGE);
				RegisterFrame.close();
			}
		}
	}
}
