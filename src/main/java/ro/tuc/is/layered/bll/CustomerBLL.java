package ro.tuc.is.layered.bll;

import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.dal.CustomerDao;
import ro.tuc.is.layered.model.Customer;

public class CustomerBLL {
	private CustomerDao customerDao;

	public CustomerBLL() {
		customerDao = new CustomerDao();
	}

	public List<String> addCustomer(String name, String address, String phone,
			String email, String username, String password) {
		List<String> errors = new ArrayList<String>();
		if (name.equals("")) {
			errors.add("Name must have at least one char!");
		}
		if (address.equals("")) {
			errors.add("Adress must have at least one char!");
		}
		if (!email.contains("@")) {
			errors.add("Email must have \"@\"!\nExample: example@something.com ");
		}

		if (phone.length() < 10 || phone.length() > 10) {
			errors.add("Phone must have 10 caracters!");
		}

		if (username.equals("")) {
			errors.add("UserName must have at least one char!");
		}
		if (password.equals("")) {
			errors.add("Password must have at least one char!");
		}
		if (errors.isEmpty()) {
			if (customerDao.insert(name, address, phone, email, username,
					password))
				errors.add("Insertion Complete!!!");
		}
		return errors;

	}

	public List<String> updateCustomer(int id, String name, String address,
			String phone, String email, String username, String password) {
		List<String> errors = new ArrayList<String>();
		if (id < 0) {
			errors.add("ID must be pozitive number");
		}
		if (name == null) {
			errors.add("Name must have at least one char!");
		}
		if (address == null) {
			errors.add("Adress must have at least one char!");
		}
		if (phone.length() < 10 || phone.length() > 10) {
			errors.add("Phone must have 10 caracters");
		}

		if (username == null) {
			errors.add("UserName must have at least one char!");
		}
		if (password == null) {
			errors.add("Password must have at least one char!");
		}
		if (errors.isEmpty()) {
			if (customerDao.update(id, name, address, phone, email, username,
					password))
				errors.add("Update Complete!!!");
		}
		return errors;

	}

	public List<String> deleteCustomer(int id) {
		List<String> errors = new ArrayList<String>();
		if (id < 0) {
			errors.add("ID must be pozitive number");
		}
		if (errors.isEmpty()) {
			if (customerDao.delete(id))
				errors.add("Delete Complete!!!");
		}
		return errors;

	}

	public String[][] findAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers = (ArrayList<Customer>) customerDao.listAllCustomers();
		String aux[][] = new String[customers.size()][7];
		for (int i = 0; i < customers.size(); i++) {
			String s[] = { Integer.toString(customers.get(i).getId()),
					customers.get(i).getName(), customers.get(i).getAddress(),
					customers.get(i).getPhone(), customers.get(i).getEmail(),
					customers.get(i).getUsername(), "-----------" };
			aux[i] = s;
		}
		return aux;
	}

	public List<String> validateCustomer(String username, String password) {
		List<String> errors = new ArrayList<String>();
		Customer customer = null;
		if (username.equals("")) {
			errors.add("Username must have at least one char!");
		}
		if (password.equals("")) {
			errors.add("Password must have at least one char!");
		}
		if (errors.isEmpty()) {
			customer = customerDao.getCustomer(username);
			if (customer == null) {
				System.err.println("There was no user found for username="
						+ username);
				errors.add("unexisting username");
			} else if (customer.getPassword().equals(password)) {
				System.out.println("Password Accepted!");

				if (customer.getName().equals("Administrator")) {
					// exception from the rule;
					// exception because if is an administrator, i want to acces
					// the admin Panel from this condition
					errors.add("admin");
					return errors;
				}
			} else {
				errors.add("Wrong password!!!");
			}
		}
		return errors;
	}
}
