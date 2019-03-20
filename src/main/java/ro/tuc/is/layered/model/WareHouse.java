package ro.tuc.is.layered.model;

public class WareHouse {
	private int id_p;
	private int stock;
	
	
	public WareHouse(int id_p, int stock) {
		
		this.id_p = id_p;
		this.stock = stock;
	}
	public int getId_p() {
		return id_p;
	}
	public void setId_p(int id_p) {
		this.id_p = id_p;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
