package ro.tuc.is.layered.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.model.Customer;

public class CustomerDao {
	private DBConnect db = DBConnect.instance();

	// ////////////////////QUERIES////////////////////////////////////////////////
	private final static String FIND_ALL = "SELECT * " + " from customer";
	private static final String INSERT = "INSERT INTO customer (id, name, address, phone, email, username,password)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE customer "
			+ "Set name=?, address=?, phone=?, email=?,username=?, password=?"
			+ "WHERE id=?";
	private static final String DELETE = "DELETE FROM customer  "
			+ " WHERE id=?";
	private static final String GETUSER = "SELECT * FROM customer WHERE username =?";
	private static final String FINDLAST = "SELECT max(id) FROM customer";

	// ////////////////////QUERIES////////////////////////////////////////////////

	public List<Customer> listAllCustomers() {
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		ArrayList<Customer> customerList = new ArrayList<Customer>();

		try {
			try {
				findStatement = db.prepareStatement(FIND_ALL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Customer customer = new Customer(id, name, address, phone,
						email, username, password);
				customerList.add(customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	public boolean insert(String name, String address, String phone,
			String email, String username, String password) {
		PreparedStatement insertStatement = null;
		PreparedStatement findLastStatement = null;
		ResultSet rs = null;
		int idFind = 0;
		boolean succes = false;
		try {
			try {
				findLastStatement = db.prepareStatement(FINDLAST);
				findLastStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = findLastStatement.getResultSet();
			while (rs.next()) {
				idFind = rs.getInt(1);
			}
			idFind += 1;
			insertStatement = db.prepareStatement(INSERT);
			insertStatement.setInt(1, idFind);
			insertStatement.setString(2, name);
			insertStatement.setString(3, address);
			insertStatement.setString(4, phone);
			insertStatement.setString(5, email);
			insertStatement.setString(6, username);
			insertStatement.setString(7, password);
			insertStatement.execute();
			succes = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return succes;
	}

	public boolean update(int id, String name, String address, String phone,
			String email, String username, String password) {
		PreparedStatement updateStatement = null;
		boolean succes = false;
		try {
			updateStatement = db.prepareStatement(UPDATE);
			updateStatement.setString(1, name);
			updateStatement.setString(2, address);
			updateStatement.setString(3, phone);
			updateStatement.setString(4, email);
			updateStatement.setString(5, username);
			updateStatement.setString(6, password);
			updateStatement.setInt(7, id);
			updateStatement.execute();
			succes = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return succes;
	}

	public boolean delete(int id) {
		PreparedStatement deleteStatement = null;
		boolean succes = false;
		try {
			deleteStatement = db.prepareStatement(DELETE);
			deleteStatement.setInt(1, id);
			deleteStatement.execute();
			succes = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return succes;
	}

	public Customer getCustomer(String username) {
		PreparedStatement getUserStatement = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			try {
				getUserStatement = db.prepareStatement(GETUSER);
				getUserStatement.setString(1, username);
				getUserStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = getUserStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String user = rs.getString("username");
				String password = rs.getString("password");
				customer = new Customer(id, name, address, phone, email, user,
						password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
}
