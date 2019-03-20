package ro.tuc.is.layered.model;

public class Order {
	private int id;
	private int id_c;
	private int id_p;
	private int cant;

	public Order(int id, int id_c, int id_p, int cant) {
		this.id = id;
		this.id_c = id_c;
		this.id_p = id_p;
		this.cant = cant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_c() {
		return id_c;
	}

	public void setId_c(int id_c) {
		this.id_c = id_c;
	}

	public int getId_p() {
		return id_p;
	}

	public void setId_p(int id_p) {
		this.id_p = id_p;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}
	
}

