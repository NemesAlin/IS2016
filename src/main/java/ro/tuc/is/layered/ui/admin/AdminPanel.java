package ro.tuc.is.layered.ui.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ro.tuc.is.layered.bll.BookBLL;
import ro.tuc.is.layered.bll.CustomerBLL;
import ro.tuc.is.layered.bll.OrderBLL;
import ro.tuc.is.layered.bll.WareHouseBLL;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel adminPanelField = new JLabel();

	JScrollPane scrollpane;
	String[] title = null;
	String[][] data = null;
	DefaultTableModel model = null;

	private JComboBox<String> comboBox = new JComboBox<String>();
	private JLabel lblWhereDoYou = new JLabel("Where do you want to change?");
	BookBLL bookObj = new BookBLL();
	CustomerBLL customerObj = new CustomerBLL();
	OrderBLL orderObj = new OrderBLL();
	WareHouseBLL wareHouseObj = new WareHouseBLL();
	String msg = new String();

	private JButton addBtn = new JButton("Add");
	private JButton updateBtn = new JButton("Update");
	private JButton deleteBtn = new JButton("Delete");

	// //////BOOK LABELS and TFs/////////
	JLabel ADDbookNameLbl = new JLabel("Name:");
	JTextField ADDbookNameTF = new JTextField();
	JTextField ADDbookAuthorTF = new JTextField();
	JLabel ADDbookAuthorLbl = new JLabel("Author:");
	JTextField ADDbookDescTF = new JTextField();
	JLabel ADDbookDescLbl = new JLabel("Description:");
	JTextField ADDbookPriceTF = new JTextField();
	JLabel ADDbookPricelbl = new JLabel("Price:");

	JTextField UPbookNameTF = new JTextField();
	JLabel UPbookNameLbl = new JLabel("Name:");
	JLabel UPbookIdLbl = new JLabel("ID:");
	JTextField UPbookIdTF = new JTextField();
	JLabel UPbookAuthorLbl = new JLabel("Author:");
	JTextField UPbookAuthorTF = new JTextField();
	JLabel UPbookDescLbl = new JLabel("Description:");
	JTextField UPbookDescTF = new JTextField();
	JLabel UPbookPriceLbl = new JLabel("Price:");
	JTextField UPbookPriceTF = new JTextField();

	JLabel DELbookIdLbl = new JLabel("ID:");
	JTextField DELbookIdTF = new JTextField();
	// //////////////////////////////////////////////////

	// //////CUSTOMER LABELS and TFs///////////////////
	JLabel ADDcustomerNameLbl = new JLabel("Name:");
	JTextField ADDcustomerNameTF = new JTextField();
	JTextField ADDcustomerAddressTF = new JTextField();
	JLabel ADDcustomerAddressLbl = new JLabel("Address:");
	JTextField ADDcustomerEmailTF = new JTextField();
	JLabel ADDcustomerEmailLbl = new JLabel("Email:");
	JTextField ADDcustomerPhoneTF = new JTextField();
	JLabel ADDcustomerPhoneLbl = new JLabel("Phone:");
	JTextField ADDcustomerUserNameTF = new JTextField();
	JLabel ADDcustomerUserNameLbl = new JLabel("UserName:");
	JPasswordField ADDcustomerPasswordTF = new JPasswordField();
	JLabel ADDcustomerPasswordLbl = new JLabel("Password:");

	JTextField UPcustomerNameTF = new JTextField();
	JLabel UPcustomerNameLbl = new JLabel("Name:");
	JLabel UPcustomerIdLbl = new JLabel("ID:");
	JTextField UPcustomerIdTF = new JTextField();
	JLabel UPcustomerAddressLbl = new JLabel("Address:");
	JTextField UPcustomerAddressTF = new JTextField();
	JLabel UPcustomerEmailLbl = new JLabel("Email:");
	JTextField UPcustomerEmailTF = new JTextField();
	JLabel UPcustomerPhoneLbl = new JLabel("Phone:");
	JTextField UPcustomerPhoneTF = new JTextField();
	JTextField UPcustomerUserNameTF = new JTextField();
	JLabel UPcustomerUserNameLbl = new JLabel("UserName:");
	JPasswordField UPcustomerPasswordTF = new JPasswordField();
	JLabel UPcustomerPasswordLbl = new JLabel("Password:");

	JLabel DELcustomerIdLbl = new JLabel("ID:");
	JTextField DELcustomerIdTF = new JTextField();
	// ////////////////////////////////////////////////

	// //////////////ORDER LABELS AND TFs////////////////
	JLabel ADDorderID_CLbl = new JLabel("ID_C:");
	JTextField ADDorderID_CTF = new JTextField();
	JTextField ADDorderID_PTF = new JTextField();
	JLabel ADDorderID_PLbl = new JLabel("ID_P:");
	JTextField ADDorderCantTF = new JTextField();
	JLabel ADDorderCantLbl = new JLabel("Cant:");

	JLabel UPorderIDLbl = new JLabel("ID:");
	JTextField UPorderIDTF = new JTextField();
	JLabel UPorderID_CLbl = new JLabel("ID_C:");
	JTextField UPorderID_CTF = new JTextField();
	JTextField UPorderID_PTF = new JTextField();
	JLabel UPorderID_PLbl = new JLabel("ID_P:");
	JTextField UPorderCantTF = new JTextField();
	JLabel UPorderCantLbl = new JLabel("Cant:");

	JLabel DELorderIdLbl = new JLabel("ID:");
	JTextField DELorderIdTF = new JTextField();
	// ///////////////////////////////////////////////////

	// //////////////WAREHOUSE LABELS AND TFs///////////
	JLabel ADDwareHouseID_PLbl = new JLabel("ID_P:");
	JTextField ADDwareHouseID_PTF = new JTextField();
	JTextField ADDwareHouseStockTF = new JTextField();
	JLabel ADDwareHouseStockLbl = new JLabel("Stock:");

	JLabel UPwareHouseID_PLbl = new JLabel("ID_P:");
	JTextField UPwareHouseID_PTF = new JTextField();
	JTextField UPwareHouseStockTF = new JTextField();
	JLabel UPwareHouseStockLbl = new JLabel("Stock:");

	JLabel DELwareHouseID_PLbl = new JLabel("ID_P:");
	JTextField DELwareHouseID_PTF = new JTextField();

	// /////////////////////////////////////////////////

	public AdminPanel() {
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		adminPanelField.setText("Admin Panel");
		adminPanelField.setBounds(600, 5, 1000, 50);
		adminPanelField.setFont(new Font("Times new Roman", Font.BOLD, 32));
		adminPanelField.setForeground(Color.WHITE);
		adminPanelField.setBackground(Color.WHITE);
		this.add(adminPanelField);

		comboBox.setForeground(Color.RED);
		comboBox.setBackground(Color.YELLOW);
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 16));
		comboBox.setBackground(Color.YELLOW);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Book", "Customers", "Orders", "WareHouse" }));
		comboBox.setBounds(111, 117, 205, 38);
		comboBox.setSelectedIndex(-1);
		this.add(comboBox);
		comboBox.addActionListener(this);

		lblWhereDoYou.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblWhereDoYou.setForeground(Color.WHITE);
		lblWhereDoYou.setBounds(111, 73, 276, 25);
		this.add(lblWhereDoYou);

		scrollpane = new JScrollPane();
		scrollpane.setBounds(501, 117, 737, 298);

		this.add(addBtn);
		this.add(updateBtn);
		this.add(deleteBtn);

		addBtn.setBounds(111, 520, 100, 30);
		addBtn.setBackground(Color.YELLOW);
		addBtn.setForeground(Color.RED);
		addBtn.addActionListener(this);

		updateBtn.setForeground(Color.RED);
		updateBtn.setBackground(Color.YELLOW);
		updateBtn.setBounds(111, 580, 100, 30);
		updateBtn.addActionListener(this);

		deleteBtn.setForeground(Color.RED);
		deleteBtn.setBackground(Color.YELLOW);
		deleteBtn.setBounds(111, 640, 100, 30);
		deleteBtn.addActionListener(this);

		addBtn.setVisible(false);
		updateBtn.setVisible(false);
		deleteBtn.setVisible(false);
	}

	public void refresh(String msg) {

		if (msg == "Book") {
			removeCustomerActions();
			removeOrderActions();
			removeWareHouseActions();
			bookActions();

			title = new String[] { "ID", "NAME", "AUTHOR", "DESCRIPTION",
					"PRICE" };
			model = new DefaultTableModel(data, title);
			JTable table = new JTable(model);

			String aux[][] = bookObj.findAll();
			for (int i = 0; i < aux.length; i++) {
				model.insertRow(table.getRowCount(), aux[i]);
			}
			if (aux.length == 0) {
				scrollpane.setViewportView(table);
				table.setEnabled(true);
			}

			scrollpane.setViewportView(table);
			table.setEnabled(true);
			this.add(scrollpane);
		}
		if (msg == "Customers") {

			removeBookActions();
			removeOrderActions();
			removeWareHouseActions();
			customerActions();

			title = new String[] { "ID", "NAME", "ADDRESS", "EMAIL", "PHONE",
					"USERNAME", "PASSWORD" };
			model = new DefaultTableModel(data, title);
			JTable table = new JTable(model);

			String aux[][] = customerObj.findAll();
			for (int i = 0; i < aux.length; i++) {
				model.insertRow(table.getRowCount(), aux[i]);
			}
			if (aux.length == 0) {
				scrollpane.setViewportView(table);
				table.setEnabled(true);
			}
			scrollpane.setViewportView(table);
			table.setEnabled(true);
			this.add(scrollpane);
		}
		if (msg == "Orders") {
			removeBookActions();
			removeCustomerActions();
			removeWareHouseActions();
			orderActions();

			title = new String[] { "ID", "ID_P", "ID_C", "CANT" };
			model = new DefaultTableModel(data, title);
			JTable table = new JTable(model);

			String aux[][] = orderObj.findAll();
			for (int i = 0; i < aux.length; i++) {
				model.insertRow(table.getRowCount(), aux[i]);
			}
			if (aux.length == 0) {
				scrollpane.setViewportView(table);
				table.setEnabled(true);
			}
			scrollpane.setViewportView(table);
			table.setEnabled(true);
			this.add(scrollpane);
		}

		if (msg == "WareHouse") {
			removeBookActions();
			removeCustomerActions();
			removeOrderActions();
			wareHouseAction();

			title = new String[] { "ID_P", "STOCK" };
			model = new DefaultTableModel(data, title);
			JTable table = new JTable(model);

			String aux[][] = wareHouseObj.findAll();
			for (int i = 0; i < aux.length; i++) {
				model.insertRow(table.getRowCount(), aux[i]);
			}
			if (aux.length == 0) {
				scrollpane.setViewportView(table);
				table.setEnabled(true);
			}
			scrollpane.setViewportView(table);
			table.setEnabled(true);
			this.add(scrollpane);
		}

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == comboBox) {
			msg = (String) comboBox.getSelectedItem();
			addBtn.setVisible(true);
			updateBtn.setVisible(true);
			deleteBtn.setVisible(true);
			switch (msg) {
			case "Book":
				refresh(msg);
				break;
			case "Customers":
				refresh(msg);
				break;
			case "Orders":
				refresh(msg);
				break;
			case "WareHouse":
				refresh(msg);
				break;
			default:
				JOptionPane.showMessageDialog(this, "Something goes wrong :(");
			}
		}
		if (msg == "Book") {
			if (arg0.getSource() == addBtn) {
				JOptionPane.showMessageDialog(this, bookObj.addBook(
						ADDbookNameTF.getText(), ADDbookAuthorTF.getText(),
						ADDbookDescTF.getText(),
						Float.parseFloat(ADDbookPriceTF.getText())));
				refresh(msg);
			}
			if (arg0.getSource() == updateBtn) {
				JOptionPane.showMessageDialog(this, bookObj.updateBook(
						Integer.parseInt(UPbookIdTF.getText()),
						UPbookNameTF.getText(), UPbookAuthorTF.getText(),
						UPbookDescTF.getText(),
						Float.parseFloat(UPbookPriceTF.getText())));
				refresh(msg);
			}
			if (arg0.getSource() == deleteBtn) {
				JOptionPane.showMessageDialog(this, bookObj.deleteBook(Integer
						.parseInt(DELbookIdTF.getText())));
				refresh(msg);
			}
		}
		if (msg == "Customers") {
			if (arg0.getSource() == addBtn) {
				JOptionPane.showMessageDialog(this, customerObj.addCustomer(
						ADDcustomerNameTF.getText(),
						ADDcustomerAddressTF.getText(),
						ADDcustomerPhoneTF.getText(),
						ADDcustomerEmailTF.getText(),
						ADDcustomerUserNameTF.getText(),
						ADDcustomerPasswordTF.getText()));
				refresh(msg);
			}
			if (arg0.getSource() == updateBtn) {
				JOptionPane.showMessageDialog(this, customerObj.updateCustomer(
						Integer.parseInt(UPcustomerIdTF.getText()),
						UPcustomerNameTF.getText(),
						UPcustomerAddressTF.getText(),
						UPcustomerPhoneTF.getText(),
						UPcustomerEmailTF.getText(),
						UPcustomerUserNameTF.getText(),
						UPcustomerPasswordTF.getText()));
				refresh(msg);
			}
			if (arg0.getSource() == deleteBtn) {
				JOptionPane.showMessageDialog(this, customerObj
						.deleteCustomer(Integer.parseInt(DELcustomerIdTF
								.getText())));
				refresh(msg);
			}
		}
		if (msg == "Orders") {
			if (arg0.getSource() == addBtn) {
				JOptionPane.showMessageDialog(this,	orderObj.addOrder(Integer.parseInt(ADDorderID_CTF.getText()),
						Integer.parseInt(ADDorderID_PTF.getText()),
						Integer.parseInt(ADDorderCantTF.getText())));
				refresh(msg);
			}
			if (arg0.getSource() == updateBtn) {
				JOptionPane.showMessageDialog(this, orderObj.updateOrder(Integer.parseInt(UPorderIDTF.getText()),
						Integer.parseInt(UPorderID_CTF.getText()),
						Integer.parseInt(UPorderID_PTF.getText()),
						Integer.parseInt(UPorderCantTF.getText())));
				refresh(msg);
			}
			if (arg0.getSource() == deleteBtn) {
				JOptionPane.showMessageDialog(this, orderObj.deleteOrder(Integer.parseInt(DELorderIdTF.getText())));
				refresh(msg);
			}
		}
		if (msg == "WareHouse") {
			if (arg0.getSource() == addBtn) {
				JOptionPane.showMessageDialog(this,	wareHouseObj.addStock(
						Integer.parseInt(ADDwareHouseID_PTF.getText()),
						Integer.parseInt(ADDwareHouseStockTF.getText())));
				refresh(msg);
			}
			if (arg0.getSource() == updateBtn) {
				JOptionPane.showMessageDialog(this,wareHouseObj.updateStock(
						Integer.parseInt(UPwareHouseID_PTF.getText()),
						Integer.parseInt(UPwareHouseStockTF.getText())));
				refresh(msg);
			}
			if (arg0.getSource() == deleteBtn) {
				JOptionPane.showMessageDialog(this,wareHouseObj.deleteStock(Integer.parseInt(DELwareHouseID_PTF
						.getText())));
				refresh(msg);
			}
		}
	}

	public void orderActions() {
		ADDorderID_CLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDorderID_CLbl.setForeground(Color.WHITE);
		ADDorderID_CLbl.setBounds(221, 520, 46, 14);
		ADDorderID_CLbl.setVisible(true);
		add(ADDorderID_CLbl);

		ADDorderID_CTF.setBounds(263, 520, 32, 20);
		add(ADDorderID_CTF);
		ADDorderID_CTF.setVisible(true);
		ADDorderID_CTF.setColumns(10);

		ADDorderID_PTF.setColumns(10);
		ADDorderID_PTF.setBounds(341, 520, 32, 20);
		ADDorderID_PTF.setVisible(true);
		add(ADDorderID_PTF);

		ADDorderID_PLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ADDorderID_PLbl.setForeground(Color.WHITE);
		ADDorderID_PLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDorderID_PLbl.setBounds(303, 523, 32, 14);
		ADDorderID_PLbl.setVisible(true);
		add(ADDorderID_PLbl);

		ADDorderCantTF.setColumns(10);
		ADDorderCantTF.setBounds(425, 520, 58, 20);
		ADDorderCantTF.setVisible(true);
		add(ADDorderCantTF);

		ADDorderCantLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ADDorderCantLbl.setForeground(Color.WHITE);
		ADDorderCantLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDorderCantLbl.setBounds(383, 523, 32, 14);
		ADDorderCantLbl.setVisible(true);
		add(ADDorderCantLbl);

		UPorderID_CTF.setColumns(10);
		UPorderID_CTF.setBounds(328, 580, 32, 20);
		UPorderID_CTF.setVisible(true);
		add(UPorderID_CTF);

		UPorderID_CLbl.setForeground(Color.WHITE);
		UPorderID_CLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPorderID_CLbl.setBounds(286, 583, 46, 14);
		UPorderID_CLbl.setVisible(true);
		add(UPorderID_CLbl);

		UPorderIDLbl.setForeground(Color.WHITE);
		UPorderIDLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPorderIDLbl.setBounds(221, 583, 46, 14);
		UPorderIDLbl.setVisible(true);
		add(UPorderIDLbl);

		UPorderIDTF.setColumns(10);
		UPorderIDTF.setBounds(244, 580, 32, 20);
		UPorderIDTF.setVisible(true);
		add(UPorderIDTF);

		UPorderID_PLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		UPorderID_PLbl.setForeground(Color.WHITE);
		UPorderID_PLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPorderID_PLbl.setBounds(368, 580, 32, 14);
		UPorderID_PLbl.setVisible(true);
		add(UPorderID_PLbl);

		UPorderID_PTF.setColumns(10);
		UPorderID_PTF.setBounds(410, 580, 32, 20);
		UPorderID_PTF.setVisible(true);
		add(UPorderID_PTF);

		UPorderCantLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		UPorderCantLbl.setForeground(Color.WHITE);
		UPorderCantLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPorderCantLbl.setBounds(456, 580, 32, 14);
		UPorderCantLbl.setVisible(true);
		add(UPorderCantLbl);

		UPorderCantTF.setColumns(10);
		UPorderCantTF.setBounds(498, 580, 58, 20);
		UPorderCantTF.setVisible(true);
		add(UPorderCantTF);

		DELorderIdLbl.setForeground(Color.WHITE);
		DELorderIdLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		DELorderIdLbl.setBounds(221, 643, 46, 14);
		DELorderIdLbl.setVisible(true);
		add(DELorderIdLbl);

		DELorderIdTF.setColumns(10);
		DELorderIdTF.setBounds(244, 640, 32, 20);
		DELorderIdTF.setVisible(true);
		add(DELorderIdTF);
	}

	public void removeOrderActions() {
		ADDorderID_CLbl.setVisible(false);
		ADDorderID_CTF.setVisible(false);
		ADDorderID_PTF.setVisible(false);
		ADDorderID_PLbl.setVisible(false);
		ADDorderCantTF.setVisible(false);
		ADDorderCantLbl.setVisible(false);
		UPorderID_CTF.setVisible(false);
		UPorderID_CLbl.setVisible(false);
		UPorderIDLbl.setVisible(false);
		UPorderIDTF.setVisible(false);
		UPorderID_PLbl.setVisible(false);
		UPorderID_PTF.setVisible(false);
		UPorderCantLbl.setVisible(false);
		UPorderCantTF.setVisible(false);
		DELorderIdLbl.setVisible(false);
		DELorderIdTF.setVisible(false);
		revalidate();
	}

	public void wareHouseAction() {
		ADDwareHouseID_PLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDwareHouseID_PLbl.setForeground(Color.WHITE);
		ADDwareHouseID_PLbl.setBounds(221, 520, 46, 14);
		ADDwareHouseID_PLbl.setVisible(true);
		add(ADDwareHouseID_PLbl);

		ADDwareHouseID_PTF.setBounds(263, 520, 32, 20);
		add(ADDwareHouseID_PTF);
		ADDwareHouseID_PTF.setVisible(true);
		ADDwareHouseID_PTF.setColumns(10);

		ADDwareHouseStockTF.setColumns(10);
		ADDwareHouseStockTF.setBounds(355, 520, 32, 20);
		ADDwareHouseStockTF.setVisible(true);
		add(ADDwareHouseStockTF);

		ADDwareHouseStockLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ADDwareHouseStockLbl.setForeground(Color.WHITE);
		ADDwareHouseStockLbl
				.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDwareHouseStockLbl.setBounds(309, 523, 36, 14);
		ADDwareHouseStockLbl.setVisible(true);
		add(ADDwareHouseStockLbl);

		UPwareHouseID_PTF.setColumns(10);
		UPwareHouseID_PTF.setBounds(263, 577, 32, 20);
		UPwareHouseID_PTF.setVisible(true);
		add(UPwareHouseID_PTF);

		UPwareHouseID_PLbl.setForeground(Color.WHITE);
		UPwareHouseID_PLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPwareHouseID_PLbl.setBounds(221, 580, 46, 14);
		UPwareHouseID_PLbl.setVisible(true);
		add(UPwareHouseID_PLbl);

		UPwareHouseStockLbl.setForeground(Color.WHITE);
		UPwareHouseStockLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPwareHouseStockLbl.setBounds(313, 583, 46, 14);
		UPwareHouseStockLbl.setVisible(true);
		add(UPwareHouseStockLbl);

		UPwareHouseStockTF.setColumns(10);
		UPwareHouseStockTF.setBounds(355, 580, 32, 20);
		UPwareHouseStockTF.setVisible(true);
		add(UPwareHouseStockTF);

		DELwareHouseID_PLbl.setForeground(Color.WHITE);
		DELwareHouseID_PLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		DELwareHouseID_PLbl.setBounds(221, 643, 46, 14);
		DELwareHouseID_PLbl.setVisible(true);
		add(DELwareHouseID_PLbl);

		DELwareHouseID_PTF.setColumns(10);
		DELwareHouseID_PTF.setBounds(263, 640, 32, 20);
		DELwareHouseID_PTF.setVisible(true);
		add(DELwareHouseID_PTF);
	}

	public void removeWareHouseActions() {
		ADDwareHouseID_PLbl.setVisible(false);
		ADDwareHouseID_PTF.setVisible(false);
		ADDwareHouseStockTF.setVisible(false);
		ADDwareHouseStockLbl.setVisible(false);
		UPwareHouseID_PTF.setVisible(false);
		UPwareHouseStockTF.setVisible(false);
		UPwareHouseID_PLbl.setVisible(false);
		UPwareHouseStockLbl.setVisible(false);
		DELwareHouseID_PLbl.setVisible(false);
		DELwareHouseID_PTF.setVisible(false);
	}

	public void customerActions() {

		ADDcustomerNameLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDcustomerNameLbl.setForeground(Color.WHITE);
		ADDcustomerNameLbl.setBounds(221, 520, 46, 14);
		ADDcustomerNameLbl.setVisible(true);
		add(ADDcustomerNameLbl);

		ADDcustomerNameTF.setBounds(263, 520, 86, 20);
		ADDcustomerNameTF.setVisible(true);
		add(ADDcustomerNameTF);
		ADDcustomerNameTF.setColumns(10);

		ADDcustomerAddressTF.setColumns(10);
		ADDcustomerAddressTF.setVisible(true);
		ADDcustomerAddressTF.setBounds(426, 520, 86, 20);
		add(ADDcustomerAddressTF);

		ADDcustomerAddressLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ADDcustomerAddressLbl.setForeground(Color.WHITE);
		ADDcustomerAddressLbl
				.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDcustomerAddressLbl.setBounds(359, 520, 57, 14);
		ADDcustomerAddressLbl.setVisible(true);
		add(ADDcustomerAddressLbl);

		ADDcustomerEmailTF.setColumns(10);
		ADDcustomerEmailTF.setBounds(567, 520, 183, 20);
		ADDcustomerEmailTF.setVisible(true);
		add(ADDcustomerEmailTF);

		ADDcustomerEmailLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ADDcustomerEmailLbl.setForeground(Color.WHITE);
		ADDcustomerEmailLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDcustomerEmailLbl.setBounds(522, 520, 35, 14);
		ADDcustomerEmailLbl.setVisible(true);
		add(ADDcustomerEmailLbl);

		ADDcustomerPhoneTF.setColumns(10);
		ADDcustomerPhoneTF.setBounds(802, 520, 102, 20);
		ADDcustomerPhoneTF.setVisible(true);
		add(ADDcustomerPhoneTF);

		ADDcustomerPhoneLbl.setForeground(Color.WHITE);
		ADDcustomerPhoneLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDcustomerPhoneLbl.setBounds(760, 520, 46, 14);
		ADDcustomerPhoneLbl.setVisible(true);
		add(ADDcustomerPhoneLbl);

		ADDcustomerUserNameTF.setColumns(10);
		ADDcustomerUserNameTF.setBounds(980, 520, 86, 20);
		ADDcustomerUserNameTF.setVisible(true);
		add(ADDcustomerUserNameTF);

		ADDcustomerUserNameLbl.setForeground(Color.WHITE);
		ADDcustomerUserNameLbl.setFont(new Font("Times New Roman", Font.BOLD,
				13));
		ADDcustomerUserNameLbl.setBounds(914, 520, 61, 14);
		ADDcustomerUserNameLbl.setVisible(true);
		add(ADDcustomerUserNameLbl);

		ADDcustomerPasswordTF.setColumns(10);
		ADDcustomerPasswordTF.setBounds(1144, 520, 86, 20);
		ADDcustomerPasswordTF.setVisible(true);
		add(ADDcustomerPasswordTF);

		ADDcustomerPasswordLbl.setForeground(Color.WHITE);
		ADDcustomerPasswordLbl.setFont(new Font("Times New Roman", Font.BOLD,
				13));
		ADDcustomerPasswordLbl.setBounds(1075, 520, 66, 14);
		ADDcustomerPasswordLbl.setVisible(true);
		add(ADDcustomerPasswordLbl);

		UPcustomerNameTF.setColumns(10);
		UPcustomerNameTF.setBounds(331, 583, 86, 20);
		UPcustomerNameTF.setVisible(true);
		add(UPcustomerNameTF);

		UPcustomerNameLbl.setForeground(Color.WHITE);
		UPcustomerNameLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPcustomerNameLbl.setBounds(289, 583, 46, 14);
		UPcustomerNameLbl.setVisible(true);
		add(UPcustomerNameLbl);

		UPcustomerIdLbl.setForeground(Color.WHITE);
		UPcustomerIdLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPcustomerIdLbl.setBounds(221, 583, 17, 14);
		UPcustomerIdLbl.setVisible(true);
		add(UPcustomerIdLbl);

		UPcustomerIdTF.setColumns(10);
		UPcustomerIdTF.setBounds(244, 580, 35, 20);
		UPcustomerIdTF.setVisible(true);
		add(UPcustomerIdTF);

		UPcustomerAddressLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		UPcustomerAddressLbl.setForeground(Color.WHITE);
		UPcustomerAddressLbl
				.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPcustomerAddressLbl.setBounds(427, 583, 57, 14);
		UPcustomerAddressLbl.setVisible(true);
		add(UPcustomerAddressLbl);

		UPcustomerAddressTF.setColumns(10);
		UPcustomerAddressTF.setBounds(494, 583, 86, 20);
		UPcustomerAddressTF.setVisible(true);
		add(UPcustomerAddressTF);

		UPcustomerEmailLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		UPcustomerEmailLbl.setForeground(Color.WHITE);
		UPcustomerEmailLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPcustomerEmailLbl.setBounds(590, 583, 35, 14);
		UPcustomerEmailLbl.setVisible(true);
		add(UPcustomerEmailLbl);

		UPcustomerEmailTF.setColumns(10);
		UPcustomerEmailTF.setBounds(635, 583, 183, 20);
		UPcustomerEmailTF.setVisible(true);
		add(UPcustomerEmailTF);

		UPcustomerPhoneLbl.setForeground(Color.WHITE);
		UPcustomerPhoneLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPcustomerPhoneLbl.setBounds(828, 583, 46, 14);
		UPcustomerPhoneLbl.setVisible(true);
		add(UPcustomerPhoneLbl);

		UPcustomerPhoneTF.setColumns(10);
		UPcustomerPhoneTF.setBounds(871, 583, 86, 20);
		UPcustomerPhoneTF.setVisible(true);
		add(UPcustomerPhoneTF);

		UPcustomerUserNameLbl.setForeground(Color.WHITE);
		UPcustomerUserNameLbl
				.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPcustomerUserNameLbl.setBounds(965, 580, 61, 14);
		UPcustomerUserNameLbl.setVisible(true);
		add(UPcustomerUserNameLbl);

		UPcustomerUserNameTF.setColumns(10);
		UPcustomerUserNameTF.setBounds(1030, 583, 86, 20);
		UPcustomerUserNameTF.setVisible(true);
		add(UPcustomerUserNameTF);

		UPcustomerPasswordLbl.setForeground(Color.WHITE);
		UPcustomerPasswordLbl
				.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPcustomerPasswordLbl.setBounds(1126, 583, 66, 14);
		UPcustomerPasswordLbl.setVisible(true);
		add(UPcustomerPasswordLbl);

		UPcustomerPasswordTF.setColumns(10);
		UPcustomerPasswordTF.setBounds(1184, 583, 86, 20);
		UPcustomerPasswordTF.setVisible(true);
		add(UPcustomerPasswordTF);

		DELcustomerIdLbl.setForeground(Color.WHITE);
		DELcustomerIdLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		DELcustomerIdLbl.setBounds(221, 643, 17, 14);
		DELcustomerIdLbl.setVisible(true);
		add(DELcustomerIdLbl);

		DELcustomerIdTF.setColumns(10);
		DELcustomerIdTF.setBounds(244, 640, 35, 20);
		DELcustomerIdTF.setVisible(true);
		add(DELcustomerIdTF);
	}

	public void removeCustomerActions() {
		ADDcustomerNameLbl.setVisible(false);
		ADDcustomerAddressLbl.setVisible(false);
		ADDcustomerEmailLbl.setVisible(false);
		ADDcustomerPhoneLbl.setVisible(false);
		ADDcustomerUserNameLbl.setVisible(false);
		ADDcustomerPasswordLbl.setVisible(false);
		ADDcustomerNameTF.setVisible(false);
		ADDcustomerAddressTF.setVisible(false);
		ADDcustomerEmailTF.setVisible(false);
		ADDcustomerPhoneTF.setVisible(false);
		ADDcustomerUserNameTF.setVisible(false);
		ADDcustomerPasswordTF.setVisible(false);

		UPcustomerIdLbl.setVisible(false);
		UPcustomerIdTF.setVisible(false);
		UPcustomerNameLbl.setVisible(false);
		UPcustomerAddressLbl.setVisible(false);
		UPcustomerEmailLbl.setVisible(false);
		UPcustomerPhoneLbl.setVisible(false);
		UPcustomerUserNameLbl.setVisible(false);
		UPcustomerPasswordLbl.setVisible(false);
		UPcustomerNameTF.setVisible(false);
		UPcustomerAddressTF.setVisible(false);
		UPcustomerEmailTF.setVisible(false);
		UPcustomerPhoneTF.setVisible(false);
		UPcustomerUserNameTF.setVisible(false);
		UPcustomerPasswordTF.setVisible(false);

		DELcustomerIdLbl.setVisible(false);
		DELcustomerIdTF.setVisible(false);

		revalidate();
	}

	public void bookActions() {

		ADDbookNameLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDbookNameLbl.setForeground(Color.WHITE);
		ADDbookNameLbl.setBounds(221, 520, 46, 14);
		ADDbookNameLbl.setVisible(true);
		add(ADDbookNameLbl);

		ADDbookNameTF.setBounds(263, 520, 86, 20);
		add(ADDbookNameTF);
		ADDbookNameTF.setVisible(true);
		ADDbookNameTF.setColumns(10);

		ADDbookAuthorTF.setColumns(10);
		ADDbookAuthorTF.setBounds(415, 520, 86, 20);
		ADDbookAuthorTF.setVisible(true);
		add(ADDbookAuthorTF);

		ADDbookAuthorLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ADDbookAuthorLbl.setForeground(Color.WHITE);
		ADDbookAuthorLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDbookAuthorLbl.setBounds(359, 520, 46, 14);
		ADDbookAuthorLbl.setVisible(true);
		add(ADDbookAuthorLbl);

		ADDbookDescTF.setColumns(10);
		ADDbookDescTF.setBounds(594, 520, 530, 20);
		ADDbookDescTF.setVisible(true);
		add(ADDbookDescTF);

		ADDbookDescLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		ADDbookDescLbl.setForeground(Color.WHITE);
		ADDbookDescLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDbookDescLbl.setBounds(511, 520, 75, 14);
		ADDbookDescLbl.setVisible(true);
		add(ADDbookDescLbl);

		ADDbookPriceTF.setColumns(10);
		ADDbookPriceTF.setBounds(1182, 520, 46, 20);
		ADDbookPriceTF.setVisible(true);
		add(ADDbookPriceTF);

		ADDbookPricelbl.setForeground(Color.WHITE);
		ADDbookPricelbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		ADDbookPricelbl.setBounds(1140, 520, 46, 14);
		ADDbookPricelbl.setVisible(true);
		add(ADDbookPricelbl);

		UPbookNameTF.setColumns(10);
		UPbookNameTF.setBounds(330, 580, 86, 20);
		UPbookNameTF.setVisible(true);
		add(UPbookNameTF);

		UPbookNameLbl.setForeground(Color.WHITE);
		UPbookNameLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPbookNameLbl.setBounds(288, 583, 46, 14);
		UPbookNameLbl.setVisible(true);
		add(UPbookNameLbl);

		UPbookIdLbl.setForeground(Color.WHITE);
		UPbookIdLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPbookIdLbl.setBounds(221, 583, 17, 14);
		UPbookIdLbl.setVisible(true);
		add(UPbookIdLbl);

		UPbookIdTF.setColumns(10);
		UPbookIdTF.setBounds(244, 580, 34, 20);
		UPbookIdTF.setVisible(true);
		add(UPbookIdTF);

		UPbookAuthorLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		UPbookAuthorLbl.setForeground(Color.WHITE);
		UPbookAuthorLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPbookAuthorLbl.setBounds(426, 580, 46, 14);
		UPbookAuthorLbl.setVisible(true);
		add(UPbookAuthorLbl);

		UPbookAuthorTF.setColumns(10);
		UPbookAuthorTF.setBounds(482, 580, 86, 20);
		UPbookAuthorTF.setVisible(true);
		add(UPbookAuthorTF);

		UPbookDescLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		UPbookDescLbl.setForeground(Color.WHITE);
		UPbookDescLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPbookDescLbl.setBounds(578, 580, 75, 14);
		UPbookDescLbl.setVisible(true);
		add(UPbookDescLbl);

		UPbookDescTF.setColumns(10);
		UPbookDescTF.setBounds(661, 580, 464, 20);
		UPbookDescTF.setVisible(true);
		add(UPbookDescTF);

		UPbookPriceLbl.setForeground(Color.WHITE);
		UPbookPriceLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		UPbookPriceLbl.setBounds(1135, 580, 46, 14);
		UPbookPriceLbl.setVisible(true);
		add(UPbookPriceLbl);

		UPbookPriceTF.setColumns(10);
		UPbookPriceTF.setBounds(1177, 580, 46, 20);
		UPbookPriceTF.setVisible(true);
		add(UPbookPriceTF);

		DELbookIdLbl.setForeground(Color.WHITE);
		DELbookIdLbl.setFont(new Font("Times New Roman", Font.BOLD, 13));
		DELbookIdLbl.setBounds(221, 643, 17, 14);
		DELbookIdLbl.setVisible(true);
		add(DELbookIdLbl);

		DELbookIdTF.setColumns(10);
		DELbookIdTF.setBounds(244, 640, 34, 20);
		DELbookIdTF.setVisible(true);
		add(DELbookIdTF);
	}

	public void removeBookActions() {
		ADDbookNameLbl.setVisible(false);
		ADDbookAuthorLbl.setVisible(false);
		ADDbookDescLbl.setVisible(false);
		ADDbookPricelbl.setVisible(false);
		ADDbookNameTF.setVisible(false);
		ADDbookAuthorTF.setVisible(false);
		ADDbookDescTF.setVisible(false);
		ADDbookPriceTF.setVisible(false);

		UPbookIdLbl.setVisible(false);
		UPbookIdTF.setVisible(false);
		UPbookNameLbl.setVisible(false);
		UPbookNameTF.setVisible(false);
		UPbookAuthorLbl.setVisible(false);
		UPbookAuthorLbl.setVisible(false);
		UPbookAuthorTF.setVisible(false);
		UPbookDescLbl.setVisible(false);
		UPbookDescTF.setVisible(false);
		UPbookPriceLbl.setVisible(false);
		UPbookPriceTF.setVisible(false);

		DELbookIdLbl.setVisible(false);
		DELbookIdTF.setVisible(false);

		revalidate();
	}
}
