package ro.tuc.is.layered.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.model.Order;


public class OrderDao {
	private DBConnect db = DBConnect.instance();
	
	
	//////////////////////QUERIES////////////////////////////////////////////////
	private final static String FIND_ALL = "SELECT * FROM `order`";
	private static final String INSERT = "INSERT INTO `order` (id, id_c, id_p, cant)"
			+ " VALUES(?, ?, ?, ?)";
	private static final String UPDATE= "UPDATE order " 
			+"Set id_c=?, id_p=?, cant=?" + "WHERE id=?";
	private static final String DELETE= "DELETE FROM order "+" WHERE id=?";
	private static final String FINDLAST = "SELECT max(id) FROM `order`";
	//////////////////////QUERIES////////////////////////////////////////////////

	
	
	public List<Order> listAllOrders() {

		PreparedStatement findStatement = null;
		ResultSet rs = null;

		List<Order> orderList = new ArrayList<Order>();

		try {
			try {
				findStatement = db.prepareStatement(FIND_ALL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int id=rs.getInt("id");
				int id_c= rs.getInt("id_c");
				int id_p= rs.getInt("id_p");
				int cant = rs.getInt("cant");
				Order order= new Order(id, id_c, id_p, cant);
				orderList.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public boolean insert(int id_c, int id_p, int cant) {
		PreparedStatement insertStatement = null;
		PreparedStatement findLastStatement = null;
		ResultSet rs = null;
		int idFind = 0;
		boolean done = false;
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
			insertStatement.setInt(2, id_c);
			insertStatement.setInt(3, id_p);
			insertStatement.setInt(4, cant);
			insertStatement.execute();
			done = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	
	
	
	
	public boolean update(int id,int id_c, int id_p, int cant){
		PreparedStatement updateStatement = null;
		boolean succes = false;
		try {
			updateStatement = db.prepareStatement(UPDATE);
			updateStatement.setInt(1,id_c);
			updateStatement.setInt(2,id_p);
			updateStatement.setInt(3,cant);
			updateStatement.setInt(4, id);
			succes = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return succes;
	}
	
	public boolean delete(int id){
		boolean sucess=false;
		PreparedStatement deleteStatement = null;
		try{
			deleteStatement=db.prepareStatement(DELETE);
			deleteStatement.setInt(1,id);
			deleteStatement.executeQuery(DELETE);
			sucess=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return sucess;
	}
	
}
