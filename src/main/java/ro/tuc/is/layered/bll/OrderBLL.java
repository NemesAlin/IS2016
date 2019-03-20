package ro.tuc.is.layered.bll;

import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.dal.OrderDao;
import ro.tuc.is.layered.model.Order;

public class OrderBLL {
	private OrderDao orderDao;

	public OrderBLL() {
		orderDao = new OrderDao();
	}

	public List<String> addOrder(int id_c, int id_p, int cant) {
		List<String> errors = new ArrayList<String>();
		if (cant <= 0) {
			errors.add("Cant should be a positive number!");
		}

		if (errors.isEmpty()) {
			if (orderDao.insert(id_c, id_p, cant))
				errors.add("Insertion Complete!!!");
		}
		return errors;

	}

	public List<String> updateOrder(int id, int id_c, int id_p, int cant) {
		List<String> errors = new ArrayList<String>();
		if (id < 0) {
			errors.add("ID must be a pozitive number!");
		}
		if (id_c < 0) {
			errors.add("ID_C must be a pozitive number!");
		}
		if (id_p < 0) {
			errors.add("ID_P must be a pozitive number!");
		}
		if (cant < 0) {
			errors.add("CANT must be a pozitive number!");
		}
		if (errors.isEmpty()) {
			if (orderDao.update(id, id_c, id_p, cant))
				errors.add("Update Complete!!!");
		}
		return errors;
	}

	public List<String> deleteOrder(int id) {
		List<String> errors = new ArrayList<String>();
		if (id < 0) {
			errors.add("ID must be pozitive number");
		}
		if (errors.isEmpty()) {
			if (orderDao.delete(id))
				errors.add("Detele Complete!!!");
		}
		return errors;
	}

	public String[][] findAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		orders = (ArrayList<Order>) orderDao.listAllOrders();
		String aux[][] = new String[orders.size()][4];
		for (int i = 0; i < orders.size(); i++) {
			String s[] = { Integer.toString(orders.get(i).getId()),
					Integer.toString(orders.get(i).getId_c()),
					Integer.toString(orders.get(i).getId_p()),
					Integer.toString(orders.get(i).getCant()) };
			aux[i] = s;
		}
		return aux;
	}
}
