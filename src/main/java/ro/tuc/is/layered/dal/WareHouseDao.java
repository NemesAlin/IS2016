package ro.tuc.is.layered.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.model.WareHouse;

public class WareHouseDao {
	
	private DBConnect db = DBConnect.instance();
	
	//////////////////////QUERIES////////////////////////////////////////////////
	private final static String FIND_ALL = "SELECT * " + " from warehouse ";

	private static final String INSERT = "INSERT INTO warehouse (id_p,stock)"
			+ " VALUES (? ,?)";
	private static final String UPDATE= "UPDATE ON warehouse " 
			+"Set stock=?" + "WHERE id_p=?";
	private static final String DELETE= "DELETE FROm warehouse " 
			 +"WHERE id_p=?";
	private static final String getWareHouse = "SELECT * FROM warehouse WHERE id_p =?";
	//////////////////////QUERIES////////////////////////////////////////////////
	
	public List<WareHouse> listAllStocks() {

		PreparedStatement findStatement = null;
		ResultSet rs = null;

		List<WareHouse> stockList = new ArrayList<WareHouse>();

		try {
			try {
				findStatement = db.prepareStatement(FIND_ALL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = findStatement.executeQuery();
			while (rs.next()) {
				int id_p = rs.getInt("id_p");
				int stock = rs.getInt("stock");
				WareHouse warehouse= new WareHouse(id_p,stock);
				stockList.add(warehouse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stockList;
	}
	
	public boolean insert(int id_p, int stock) {
		PreparedStatement insertStatement = null;
		boolean done = false;
		try {
			insertStatement = db.prepareStatement(INSERT);
			insertStatement.setInt(1, id_p);
			insertStatement.setInt(2, stock);
			insertStatement.execute();
			done = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	
	
	public boolean update(int id_p, int stock){
		PreparedStatement updateStatement = null;
		boolean succes = false;
		try {
			updateStatement = db.prepareStatement(UPDATE);
			updateStatement.setInt(1, stock);
			updateStatement.setInt(2, id_p);
			succes = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return succes;
	}

	public boolean delete(int id_p){
		PreparedStatement deleteStatement = null;
		boolean succes = false;
		try {
			deleteStatement = db.prepareStatement(DELETE);
			deleteStatement.setInt(1, id_p);
			succes = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return succes;
	}
	
	public WareHouse getWareHouse(int id_p) {
		PreparedStatement getWareHouseStatement = null;
		ResultSet rs = null;
		WareHouse wareHouse = null;
		try {
			try {
				getWareHouseStatement = db.prepareStatement(getWareHouse);
				getWareHouseStatement.setInt(1, id_p);
				getWareHouseStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = getWareHouseStatement.executeQuery();
			while (rs.next()) {
				int idd = rs.getInt("id_p");
				int stock = rs.getInt("Stock");
				wareHouse = new WareHouse(idd, stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wareHouse;
	}
}
