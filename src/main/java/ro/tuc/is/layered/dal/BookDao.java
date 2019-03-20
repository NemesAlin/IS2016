package ro.tuc.is.layered.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.model.Book;

public class BookDao {

	private DBConnect db = DBConnect.instance();

	// ////////////////////QUERIES////////////////////////////////////////////////
	private final static String FIND_ALL = "SELECT * " + " from book ";
	private static final String INSERT = "INSERT INTO book (id, name, author, description, price)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE book  "
			+ " Set name=?, author=?,description=?, price=?" + " WHERE id=?";
	private static final String DELETE = "DELETE FROM book  " + " WHERE id=?";
	private static final String FINDLAST = "SELECT max(id) FROM book";
	private static final String GETBOOK = "SELECT * FROM book WHERE id =?";
	// ////////////////////QUERIES////////////////////////////////////////////////

	public List<Book> listAllBooks() {

		PreparedStatement findStatement = null;
		ResultSet rs = null;

		ArrayList<Book> bookList = new ArrayList<Book>();
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
				String author = rs.getString("author");
				String description = rs.getString("description");
				float price = rs.getFloat("price");// deobicei probleme cu
													// float/int
				Book book = new Book(id, name, author, description, price);
				bookList.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

	public boolean insert(String name, String author, String description,
			float price) {
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
			insertStatement.setString(2, name);
			insertStatement.setString(3, author);
			insertStatement.setString(4, description);
			insertStatement.setFloat(5, price);
			insertStatement.execute();
			done = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}

	public boolean update(int id, String name, String author,
			String description, float price) {
		PreparedStatement updateStatement = null;
		boolean done = false;
		try {
			updateStatement = db.prepareStatement(UPDATE);
			updateStatement.setString(1, name);
			updateStatement.setString(2, author);
			updateStatement.setString(3, description);
			updateStatement.setFloat(4, price);
			updateStatement.setInt(5, id);
			updateStatement.execute();
			done = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}

	public boolean delete(int id) {
		PreparedStatement deleteStatement = null;
		boolean done = false;
		try {
			deleteStatement = db.prepareStatement(DELETE);
			deleteStatement.setInt(1, id);
			deleteStatement.execute();
			done = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return done;
	}
	
	public Book getBook(int id) {
		PreparedStatement getBookStatement = null;
		ResultSet rs = null;
		Book book = null;
		try {
			try {
				getBookStatement = db.prepareStatement(GETBOOK);
				getBookStatement.setInt(1, id);
				getBookStatement.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = getBookStatement.executeQuery();
			while (rs.next()) {
				int idd = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String description = rs.getString("description");
				float price = rs.getFloat("price");
				book = new Book(idd, name, author, description, price);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
}
