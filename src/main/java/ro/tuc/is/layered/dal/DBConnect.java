package ro.tuc.is.layered.dal;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBConnect {

	/* THE BACK UP!!
	  */
//	public static final String DRIVER = "com.mysql.jdbc.Driver";
//	public static final String DBURL = "jdbc:mysql://db4free.net:3306/";
//	private static final String DBName = "bookdb";
//	private static String USER = "alin";
//	private static String PASS = "6121993alin";
//	 
	
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String DBName = "bookdb";
	private static String USER = "root";
	private static String PASS = "6121993alin";
	
//	public static final String DRIVER = "com.mysql.jdbc.Driver";
//	public static final String DBURL = "jdbc:mysql://sql4.freemysqlhosting.net:3306/";
//	private static final String DBName = "sql496271";
//	private static String USER = "sql496271";
//	private static String PASS = "1jJ19NkwTx";
	
	// my ip : 82.137.21.178

	private Statement instruction;
	private Connection con;
	Component field = null;

	private static DBConnect oneObject = null;

	private DBConnect(String database) {
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(DBURL + database, USER, PASS);

			instruction = con.createStatement();
			if(con!=null){
			JOptionPane.showMessageDialog(field, "CONNECTION TO DATABASE SUCCESFULLY!!!", "Succes", JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(field, "ERROR CONNECTING TO DATABASE!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(field, "ERROR CONNECTING TO DATABASE!!", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(field, "ERROR CONNECTING TO DATABASE!!!" ,"Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public static DBConnect instance() {
		if (oneObject == null)
			oneObject = new DBConnect(DBName);
		return oneObject;

	}

	public void closeConnection() {
		try {
			con.close();
			JOptionPane.showMessageDialog(field, "GOODBYE!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuerry(String querry) {
		ResultSet ret = null;
		try {
			ret = instruction.executeQuery(querry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public boolean execute(String querry) {
		boolean done = false;
		try {
			done = instruction.execute(querry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return done;
	}

	public PreparedStatement prepareStatement(String query) throws Exception {
		return con.prepareStatement(query);
	}
}
