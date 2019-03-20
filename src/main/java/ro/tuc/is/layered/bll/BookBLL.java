package ro.tuc.is.layered.bll;

import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.dal.BookDao;
import ro.tuc.is.layered.model.Book;

public class BookBLL {
	private BookDao bookDao;

	public BookBLL() {
		bookDao = new BookDao();
	}

	public List<String> addBook(String name, String author, String description,
			float price) {
		List<String> errors = new ArrayList<String>();
		if (name == null || name.isEmpty()) {
			errors.add("Name should contain at least one character!");
		}
		if (author == null || author.isEmpty()) {
			errors.add("Author should contain at least one character!");
		}
		if (description == null || description.isEmpty()) {
			errors.add("Description should contain at least one character!");
		}
		if (price <= 0) {
			errors.add("Price should be a positive number!");
		}
		if (errors.isEmpty()) {
			if (bookDao.insert(name, author, description, price))
			errors.add("Insertion Complete!!!");
		}
		return errors;
	}

	public List<String> updateBook(int id, String name, String author,
			String description, float price) {
		List<String> errors = new ArrayList<String>();
		if (name == null || name.isEmpty()) {
			errors.add("Name should contain at least one character!");
		}
		if (author == null || author.isEmpty()) {
			errors.add("Author should contain at least one character!");
		}
		if (description == null || description.isEmpty()) {
			errors.add("Description should contain at least one character!");
		}
		if (price <= 0) {
			errors.add("Price should be a positive number!");
		}
		if (id <= 0) {
			errors.add("ID should be a positive number!");
		}
		if (errors.isEmpty()) {
			if (bookDao.update(id, name, author, description, price))
				errors.add("Update Complete!!!");
		}
		return errors;
	}

	public List<String> deleteBook(int id) {
		List<String> errors = new ArrayList<String>();

		if (id <= 0) {
			errors.add("ID should be a positive number!");
		}
		if (errors.isEmpty()) {
			if (bookDao.delete(id))
				errors.add("Delete Complete!!!");
		}
		return errors;
	}

	public String[][] findAll() {
		ArrayList<Book> books = new ArrayList<Book>();
		books = (ArrayList<Book>) bookDao.listAllBooks();
		String aux[][] = new String[books.size()][5];
		for (int i = 0; i < books.size(); i++) {
			String s[] = { Integer.toString(books.get(i).getId()),
					books.get(i).getName(), books.get(i).getAuthor(),
					books.get(i).getDescription(),
					Float.toString(books.get(i).getPrice()) };
			aux[i] = s;
		}
		return aux;
	}
}
