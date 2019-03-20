package ro.tuc.is.layered.bll;

import java.util.ArrayList;
import java.util.List;

import ro.tuc.is.layered.dal.WareHouseDao;
import ro.tuc.is.layered.model.WareHouse;

public class WareHouseBLL {
	private WareHouseDao wareHouseDao;

	public WareHouseBLL() {
		wareHouseDao = new WareHouseDao();
	}

	public List<String> addStock(int id_p, int stock) {
		List<String> errors = new ArrayList<String>();
		if (id_p == 0 || stock < 0) {
			errors.add("ID must be positive!");
		}
		if (stock < 0) {
			errors.add("Stock should be a positive number!");
		}
		if (errors.isEmpty()) {
			if (wareHouseDao.insert(id_p, stock))
				errors.add("Insertion Complete!!!");
		}
		return errors;
	}

	public List<String> deleteStock(int id_p) {
		List<String> errors = new ArrayList<String>();
		if (id_p < 0) {
			errors.add("ID_P should be a positive number!");
		}
		if (errors.isEmpty()) {
			if (wareHouseDao.delete(id_p))
				errors.add("Delete Complete!!!");
		}
		return errors;

	}

	public List<String> updateStock(int id_p, int stock) {
		List<String> errors = new ArrayList<String>();
		if (id_p < 0) {
			errors.add("ID_P should be a positive number!");
		}
		if (stock < 0) {
			errors.add("STOCK should be a positive number!");
		}
		if (errors.isEmpty()) {
			if (wareHouseDao.update(id_p, stock))
				errors.add("Update Complete!!!");
		}
		return errors;
	}

	public String[][] findAll() {
		ArrayList<WareHouse> wareHouse = new ArrayList<WareHouse>();
		wareHouse = (ArrayList<WareHouse>) wareHouseDao.listAllStocks();
		String aux[][] = new String[wareHouse.size()][2];
		for (int i = 0; i < wareHouse.size(); i++) {
			String s[] = { Integer.toString(wareHouse.get(i).getId_p()),
					Integer.toString(wareHouse.get(i).getStock()) };
			aux[i] = s;
		}
		return aux;
	}
}
