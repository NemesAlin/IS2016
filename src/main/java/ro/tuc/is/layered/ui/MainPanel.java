package ro.tuc.is.layered.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ro.tuc.is.layered.bll.BookBLL;
import ro.tuc.is.layered.bll.OrderBLL;
import ro.tuc.is.layered.bll.WareHouseBLL;
import ro.tuc.is.layered.dal.BookDao;
import ro.tuc.is.layered.dal.CustomerDao;
import ro.tuc.is.layered.dal.WareHouseDao;
import ro.tuc.is.layered.model.Book;
import ro.tuc.is.layered.model.Customer;
import ro.tuc.is.layered.model.WareHouse;
import ro.tuc.is.layered.ui.login.LogInFrame;

public class MainPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

	private JButton LogInBtn = new JButton();
	private JButton LogOutBtn = new JButton();
	private JButton ChangePassBtn = new JButton();
	private JButton CartBtn = new JButton();

	private JLabel Tip = new JLabel(
			"TIP: To make an order first you have to log in!", JLabel.LEFT);
	private JLabel welcome = new JLabel();
	private JScrollPane scrollpane = new JScrollPane();
	private String[] title = new String[] { "ID", "NAME", "AUTHOR",
			"DESCRIPTION", "PRICE" };
	private String[][] data = null;
	public DefaultTableModel model = new DefaultTableModel(data, title);
	private JTable books = new JTable(model);

	private BookBLL bookObj = new BookBLL();
	private CustomerDao custDao = new CustomerDao();
	private BookDao bookDao = new BookDao();
	private Customer customerLogged;
	private Book BookSelected;
	private WareHouseBLL wareHoBLL = new WareHouseBLL();
	private OrderBLL orderBLL = new OrderBLL();
	private WareHouseDao wareDao = new WareHouseDao();
	private WareHouse wareHouse;

	private String UserLogged = new String();
	private int idBook;
	@SuppressWarnings("unused")
	private Book bookCart = new Book();
	private int bookid = 0;
	private float varTotal = 0;
	private boolean logged = false;
	private JLabel LogInformation = new JLabel();
	@SuppressWarnings("unused")
	private LogInFrame loginFrame;
	private ArrayList<Book> addedToCart = new ArrayList<Book>();// //lista
																// pentru
																// comenziile
																// care vor fi
																// adaugate in
																// cos

	// /procesarea comenziilor

	public MainPanel() {
		panel = this;
		panel.setVisible(true);
		panel.setLayout(null);
		panel.setBackground(Color.BLACK);

		Tip.setIcon(new ImageIcon("images/tip.png"));
		Tip.setHorizontalAlignment(SwingConstants.CENTER);
		Tip.setBounds(0, 690, 1360, 50);
		Tip.setFont(new Font("Times new Roman", Font.BOLD, 16));
		Tip.setForeground(Color.WHITE);
		Tip.setBackground(Color.WHITE);
		panel.add(Tip);

		refresh();

		scrollpane.setBounds(50, 150, 1000, 295);
		scrollpane.setViewportView(books);
		panel.add(scrollpane);

		LogInBtn.setText("Log In");
		LogInBtn.setBounds(1200, 50, 100, 30);
		LogInBtn.setBackground(Color.YELLOW);
		LogInBtn.setForeground(Color.RED);
		LogInBtn.setVisible(true);
		LogInBtn.addActionListener(this);
		panel.add(LogInBtn);

		ChangePassBtn.setText("Change Pass");
		ChangePassBtn.setBounds(1180, 50, 120, 30);
		ChangePassBtn.setBackground(Color.YELLOW);
		ChangePassBtn.setForeground(Color.RED);
		ChangePassBtn.setVisible(false);
		panel.add(ChangePassBtn);
		ChangePassBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});

		welcome.setText("Welcome to the BookShop :)");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setBounds(0, 5, 1366, 50);
		welcome.setFont(new Font("Times new Roman", Font.BOLD, 32));
		welcome.setForeground(Color.WHITE);
		welcome.setBackground(Color.WHITE);
		panel.add(welcome);

		LogOutBtn.setText("Log Out");
		LogOutBtn.setBounds(1075, 50, 100, 30);
		LogOutBtn.setBackground(Color.YELLOW);
		LogOutBtn.setForeground(Color.RED);
		LogOutBtn.setVisible(false);
		panel.add(LogOutBtn);
		LogOutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logOutMethod();
			}
		});
		CartBtn.setIcon(new ImageIcon("images/cart.png"));
		CartBtn.setBounds(1075, 100, 225, 30);
		CartBtn.setBackground(Color.YELLOW);
		CartBtn.setForeground(Color.RED);
		CartBtn.setVisible(false);
		panel.add(CartBtn);
		CartBtn.addActionListener(this);

	}

	public void refresh() {

		int rowCount = model.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			model.removeRow(i);
		}

		String aux[][] = bookObj.findAll();
		for (int i = 0; i < aux.length; i++) {
			model.insertRow(books.getRowCount(), aux[i]);
		}
		if (aux.length == 0) {
			scrollpane.setViewportView(books);
			books.setEnabled(true);
		}

		scrollpane.setViewportView(books);
		books.setEnabled(true);
		panel.add(scrollpane);

		// books.addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent e) {
		// if (e.getClickCount() == 2) {
		// JTable target = (JTable)e.getSource();
		// int row = target.getSelectedRow();
		// int column = target.getSelectedColumn();
		// // do some action if appropriate column
		// }
		// }
		// });

		books.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = books.getSelectionModel();
		cellSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// //
		// if (logged) { // /how to select a row!!!!
		cellSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						// System.out.println(books.getValueAt(
						// books.getSelectedRow(), 0).toString());
						idBook = books.getSelectedRow() + 1;
						BookSelected = bookDao.getBook(idBook);
						if (!event.getValueIsAdjusting())
							bookInformation();
						// addedToCart.add(BookSelected);
						// CartBtn.setText(addedToCart.size() +
						// " books in the Cart");
					}
				});
		// }
		// ///
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == LogInBtn) {
			loginFrame = new LogInFrame(this);
		}
		if (arg0.getSource() == CartBtn) {
			placeOrder();
		}
	}

	public String[][] listCartBooks() {

		String aux[][] = new String[addedToCart.size()][3];
		for (int i = 0; i < addedToCart.size(); i++) {
			String s[] = { addedToCart.get(i).getName(),
					addedToCart.get(i).getAuthor(),
					Float.toString(addedToCart.get(i).getPrice()) };
			aux[i] = s;
		}
		return aux;
	}

	public void placeOrder() {
		final JFrame CartFrame = new JFrame();
		JButton confirmButton;
		String[] titleCart = new String[] { "NAME", "AUTHOR", "PRICE" };
		String[][] dataCart = null;
		final DefaultTableModel dMmodelCart = new DefaultTableModel(dataCart,
				titleCart);
		final JTable booksCart = new JTable(dMmodelCart);
		JScrollPane scrollpaneCart = new JScrollPane();
		JLabel Tip2 = new JLabel(
				"TIP: To delete from cart select on the Book Name!",
				JLabel.LEFT);

		CartFrame.setTitle("Place Order");
		CartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		CartFrame.setBounds(766, 0, 600, 400);
		CartFrame.setVisible(true);
		CartFrame.getContentPane().setBackground(Color.BLACK);
		CartFrame.getContentPane().setLayout(null);

		Tip2.setIcon(new ImageIcon("images/tip.png"));
		Tip2.setHorizontalAlignment(SwingConstants.CENTER);
		Tip2.setBounds(0, 320, 600, 50);
		Tip2.setFont(new Font("Times new Roman", Font.BOLD, 16));
		Tip2.setForeground(Color.WHITE);
		Tip2.setBackground(Color.WHITE);
		CartFrame.add(Tip2);

		scrollpaneCart.setBounds(10, 10, 400, 300);
		scrollpaneCart.setViewportView(booksCart);
		CartFrame.add(scrollpaneCart);

		int rowCount = dMmodelCart.getRowCount();
		// Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i >= 0; i--) {
			dMmodelCart.removeRow(i);
		}

		String aux[][] = listCartBooks();
		for (int i = 0; i < aux.length; i++) {
			dMmodelCart.insertRow(booksCart.getRowCount(), aux[i]);
		}
		if (aux.length == 0) {
			scrollpaneCart.setViewportView(booksCart);
			booksCart.setEnabled(true);
		}

		JLabel totalPrice = new JLabel();
		JLabel varPrice = new JLabel();
		for (int i = 0; i < addedToCart.size(); i++) {
			varTotal += addedToCart.get(i).getPrice();
		}
		varPrice.setFont(new Font("Times new Roman", Font.BOLD, 18));
		varPrice.setText("" + varTotal);
		varPrice.setForeground(Color.RED);// se simba culoare fontului
		varPrice.setBackground(Color.WHITE);
		varPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		varPrice.setBounds(0, 30, 550, 60);// se seteaza coordonatele
		CartFrame.getContentPane().add(varPrice);

		totalPrice.setFont(new Font("Times new Roman", Font.BOLD, 20));
		totalPrice.setText("TOTAL:");
		totalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		totalPrice.setForeground(Color.WHITE);// se simba culoare fontului
		totalPrice.setBackground(Color.WHITE);
		totalPrice.setBounds(0, 10, 550, 60);// se seteaza coordonatele
		CartFrame.getContentPane().add(totalPrice);

		booksCart.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = booksCart.getSelectionModel();
		cellSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cellSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						bookid = booksCart.getSelectedRow() + 1;
						bookCart = bookDao.getBook(bookid);
						if (!event.getValueIsAdjusting()) {
							int row = booksCart.getSelectedRow();
							int column = booksCart.getSelectedColumn();
							if (column == 0)
								if (row != -1) {
									dMmodelCart.removeRow(row);
									addedToCart.remove(row);
									CartBtn.setText(addedToCart.size()
											+ " books in the Cart");
									varTotal=0;
									 for (int i = 0; i < addedToCart.size(); i++) {
							    			varTotal += addedToCart.get(i).getPrice();
							    		}
								}
						}
					}
				});

		confirmButton = new JButton();
		confirmButton.setText("Place Order");
		confirmButton.setHorizontalAlignment(SwingConstants.CENTER);
		confirmButton.setBounds(420, 150, 150, 30);
		confirmButton.setBackground(Color.YELLOW);
		confirmButton.setForeground(Color.RED);
		CartFrame.getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < addedToCart.size(); i++) {
					wareHouse = wareDao
							.getWareHouse(addedToCart.get(i).getId());
					wareHoBLL.updateStock(addedToCart.get(i).getId(),
							wareHouse.getStock() - 1);
					orderBLL.addOrder(customerLogged.getId(), addedToCart
							.get(i).getId(), 1);
				}

				JOptionPane.showMessageDialog(CartFrame,
						"ORDER Succesfull !!!", "Succes",
						JOptionPane.INFORMATION_MESSAGE);
				customerLogged = custDao.getCustomer(UserLogged);
				CartFrame.dispose();
				addedToCart.clear();
			}
		});

	}

	public void logOutMethod() {
		logged = false;
		LogInBtn.setVisible(true);
		loginFrame = null;
		customerLogged = null;
		LogOutBtn.setVisible(false);
		ChangePassBtn.setVisible(false);
		LogInformation.setVisible(false);
		CartBtn.setVisible(false);
		JOptionPane.showMessageDialog(panel, "Log Out Succesfull !", "Succes",
				JOptionPane.INFORMATION_MESSAGE);
		addedToCart.clear();
	}

	@SuppressWarnings("deprecation")
	public void changePass() {
		final JFrame CHPASSframe = new JFrame();
		JLabel oldPass;
		JLabel newPass;
		final JPasswordField oldPassField;
		final JPasswordField newPassField;
		JButton confirmButton;

		CHPASSframe.setTitle("Change PassWord");
		CHPASSframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		CHPASSframe.setBounds(1066, 0, 300, 200);
		CHPASSframe.setVisible(true);
		CHPASSframe.getContentPane().setBackground(Color.BLACK);
		CHPASSframe.getContentPane().setLayout(null);

		oldPass = new JLabel();
		oldPass.setText("Old PassWord:");// se seteaza textul label-ului
		oldPass.setForeground(Color.WHITE);// se schimba culoarea fontului
		oldPass.setBackground(Color.WHITE);
		oldPass.setBounds(5, 20, 150, 30);// se seteaza coordonatele
		CHPASSframe.getContentPane().add(oldPass);

		newPass = new JLabel();
		newPass.setText("New PassWord:");
		newPass.setForeground(Color.WHITE);// se simba culoare fontului
		newPass.setBackground(Color.WHITE);
		newPass.setBounds(5, 60, 150, 30);// se seteaza coordonatele
		CHPASSframe.getContentPane().add(newPass);

		oldPassField = new JPasswordField();
		oldPassField.setBounds(100, 20, 170, 30);// se seteaza coordonatele
		CHPASSframe.getContentPane().add(oldPassField);

		newPassField = new JPasswordField();// se creeaza passwordfield-ul
		newPassField.setBounds(100, 60, 170, 30);// se seteaza coordonatele
		CHPASSframe.getContentPane().add(newPassField);

		confirmButton = new JButton();// se creeaza butonul de login
		confirmButton.setText("Confirm");
		confirmButton.setBounds(100, 100, 100, 30);
		confirmButton.setBackground(Color.YELLOW);
		confirmButton.setForeground(Color.RED);
		CHPASSframe.getContentPane().add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (oldPassField.getText().equals("")
						|| newPassField.getText().equals("")) {
					JOptionPane.showMessageDialog(CHPASSframe,
							"Please provide the fields, or exit!", "Error",
							JOptionPane.ERROR_MESSAGE);
					oldPassField.setText("");
					newPassField.setText("");
				} else if (customerLogged.getPassword().equals(
						oldPassField.getText())) {
					if (custDao.update(customerLogged.getId(),
							customerLogged.getName(),
							customerLogged.getAddress(),
							customerLogged.getPhone(),
							customerLogged.getEmail(),
							customerLogged.getUsername(),
							newPassField.getText()))
						JOptionPane.showMessageDialog(CHPASSframe,
								"Changed Password Succesfull !", "Succes",
								JOptionPane.INFORMATION_MESSAGE);
					oldPassField.setText("");
					newPassField.setText("");
					customerLogged = custDao.getCustomer(UserLogged);
					CHPASSframe.dispose();
				} else {

					JOptionPane.showMessageDialog(CHPASSframe,
							"The old password doesn't match!", "Error",
							JOptionPane.ERROR_MESSAGE);
					oldPassField.setText("");
					newPassField.setText("");
				}
			}
		});

	}

	public void logInInformation() {
		if (logged) {
			customerLogged = custDao.getCustomer(UserLogged);
			LogInformation.setBounds(0, 10, 1300, 30);
			LogInformation.setFont(new Font("Times New Roman", Font.BOLD, 14));
			LogInformation.setForeground(Color.WHITE);
			LogInformation.setVisible(false);
			LogInformation.setHorizontalAlignment(SwingConstants.RIGHT);
			LogInformation.setText("You are logged as " + "["
					+ customerLogged.getName() + "]");
			panel.add(LogInformation);

			LogInBtn.setVisible(false);
			LogInformation.setVisible(true);
			ChangePassBtn.setVisible(true);
			LogOutBtn.setVisible(true);

			CartBtn.setText(addedToCart.size() + " books in the Cart");
			CartBtn.setVisible(true);

		}

	}

	public void bookInformation() {
		final JFrame bookInformationFrame = new JFrame();
		JLabel bookName;
		JLabel bookAuthor;
		JTextArea bookDescription;
		JLabel bookPrice;
		JLabel Stock;
		JButton addToCartButton;
		WareHouse wareHouse = wareDao.getWareHouse(BookSelected.getId());

		bookInformationFrame.setTitle("Book Information");
		bookInformationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		bookInformationFrame.setBounds(0, 0, 600, 400);
		bookInformationFrame.setVisible(true);
		bookInformationFrame.getContentPane().setBackground(Color.BLACK);
		bookInformationFrame.getContentPane().setLayout(null);

		bookName = new JLabel();
		bookName.setText("<html>" + BookSelected.getName() + "</html>");// se
																		// seteaza
																		// textul
																		// label-ului
		bookName.setForeground(Color.YELLOW);// se schimba culoarea fontului
		bookName.setBackground(Color.WHITE);
		bookName.setFont(new Font("Times new Roman", Font.BOLD, 20));
		bookName.setBounds(5, 20, 350, 50);// se seteaza coordonatele
		bookInformationFrame.getContentPane().add(bookName);

		bookAuthor = new JLabel();
		bookAuthor.setText("<html>" + BookSelected.getAuthor() + "</html>");// se
																			// seteaza
																			// textul
		bookAuthor.setForeground(Color.WHITE);// se schimba culoarea fontului
		bookAuthor.setBackground(Color.WHITE);
		bookAuthor.setFont(new Font("Times new Roman", Font.BOLD, 16));
		bookAuthor.setBounds(5, 70, 200, 30);// se seteaza coordonatele
		bookInformationFrame.getContentPane().add(bookAuthor);

		bookDescription = new JTextArea();
		bookDescription.setText(BookSelected.getDescription());// se seteaza
		bookDescription.setForeground(Color.WHITE);// se schimba culoarea
		bookDescription.setBackground(Color.BLACK);
		bookAuthor.setFont(new Font("Times new Roman", Font.BOLD, 12));
		bookDescription.setLineWrap(true);
		bookDescription.setBounds(5, 100, 350, 400);// se seteaza coordonatele
		bookInformationFrame.getContentPane().add(bookDescription);

		bookPrice = new JLabel();
		bookPrice.setText(String.valueOf(BookSelected.getPrice()));// se seteaza
		bookPrice.setForeground(Color.RED);// se schimba culoarea fontului
		bookPrice.setBackground(Color.WHITE);
		bookPrice.setFont(new Font("Times new Roman", Font.BOLD, 25));
		bookPrice.setBounds(450, 20, 150, 30);// se seteaza coordonatele
		bookInformationFrame.getContentPane().add(bookPrice);

		Stock = new JLabel();
		if (wareHouse.getStock() > 0) {
			Stock.setText("IN STOCK: " + wareHouse.getStock());// se seteaza
			Stock.setForeground(Color.GREEN);// se schimba culoarea fontului
			Stock.setBackground(Color.WHITE);
		} else {
			Stock.setText("OUT OF STOCK");// se seteaza
			Stock.setForeground(Color.RED);// se schimba culoarea fontului
			Stock.setBackground(Color.WHITE);

		}

		Stock.setFont(new Font("Times new Roman", Font.BOLD, 16));
		Stock.setBounds(420, 80, 150, 30);// se seteaza coordonatele
		bookInformationFrame.getContentPane().add(Stock);

		if (logged) {
			addToCartButton = new JButton();// se creeaza butonul de login
			addToCartButton.setText("ADD TO CART");
			addToCartButton.setBounds(400, 120, 150, 30);
			addToCartButton.setBackground(Color.YELLOW);
			addToCartButton.setForeground(Color.RED);
			if (wareHouse.getStock() > 0)
				bookInformationFrame.getContentPane().add(addToCartButton);
			addToCartButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					addedToCart.add(BookSelected);
					CartBtn.setText(addedToCart.size() + " books in the Cart");
				}
			});
		}

	}

	public void setUserLogged(String str, boolean cond) {
		UserLogged = str;
		logged = cond;
		panel.repaint();
		logInInformation();

	}
}
