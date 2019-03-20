package ro.tuc.is.layered.ui;

import java.util.ArrayList;

import ro.tuc.is.layered.dal.CustomerDao;
import ro.tuc.is.layered.model.Customer;


public class ApplicationWindow {

	public static void main(String args[]) {
		//BookBLL productBll = new BookBLL();
//		// WareHouseBLL wareHouseBll = new WareHouseBLL();
//		OrderBLL orderBll = new OrderBLL();
//		CustomerBLL customerBll = new CustomerBLL();
		//List<Book> prod = null;
//		List<Order> ord = null;
		
		// productBll.addProduct("Milk", 20.0);
		// productBll.addProduct("Pepsi", 30.0);
		// productBll.addProduct("Cola", 15.0);
		// productBll.addProduct("Laptop", 15.0);
		// orderBll.addOrder(1, 2, 3);
		// customerBll.addCustomer("Alin", "calea floresti", "0751512788",
		// "nemesalinclaudiu@yahoo.com", "admin", "admin");
		// wareHouseBll.addStock(5,3);
		//productBll.addProduct("Calculator", "14.23");
		// customerBll.addCustomer("Ramona", "Grigore Alexandrescu 15",
		// "0746640979", "marinaramona15@yahoo.com", "ramonamarina",
		// "snitelas");
		// customerBll.addCustomer( "Vlad", "Floresti Popii", "0752312332",
		// "vladescu@yahoo.com", "Vlad", "Vladvlad");
		// customerBll.deleteCustomer(10);

		// //////////////////////TESTS/////////////////////////////////////////
		// List<String> errors = productBll.addProduct("", "-3.0");
		// System.out.println("~~~~~~~~~~~~~~~~~~~~ Errors: ~~~~~~~~~~~~~~~~~~~~");
		// for (String s : errors) {
		// System.out.println(s);
		// }
		// //////////////////////TESTS/////////////////////////////////////////

		// //////////////////////TESTS/////////////////////////////////////////
//		List<String> errors = customerBll.addCustomer("Vlad", "Floresti Popii",
//				"07523123", "vladescu@yahoo.com", "Vlad", "Vladvlad");
//		if (errors != null) {
//			System.out
//					.println("~~~~~~~~~~~~~~~~~~~~ Errors: ~~~~~~~~~~~~~~~~~~~~");
//			for (String s : errors) {
//				System.out.println(s);
//			}
//		}
		// //////////////////////TESTS/////////////////////////////////////////

//		System.out
//				.println("~~~~~~~~~~~~~~~~~~~~ Products: ~~~~~~~~~~~~~~~~~~~~");
//		prod = productBll.findAll();
//		for (Product p : prod) {
//			System.out.println(p.getId() + "| " + p.getName() + "| " +p.getAuthor()+ "|"+ p.getDescription() +"|"
//					+ p.getPrice());
//		}

		System.out
				.println("~~~~~~~~~~~~~~~~~~~~ Customers: ~~~~~~~~~~~~~~~~~~~~");
	
		CustomerDao customerDao=new CustomerDao();
		ArrayList<Customer> customers=(ArrayList<Customer>) customerDao.listAllCustomers();;
		String aux[][] = new String[customers.size()][7];
		for (int i = 0; i < customers.size(); i++) {
			String s[] = { Integer.toString(customers.get(i).getId()),
					customers.get(i).getName(), customers.get(i).getAddress(),
					customers.get(i).getPhone(), customers.get(i).getEmail(),
					customers.get(i).getUsername(),
					"-------" };
			System.out.println(s[0] + "." + s[1] + "." + s[2] + "." + s[3] + "." + s[4] + "." + s[5] +"."+s[6]);
			
			aux[i] = s;
		}
		System.out
		.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//
//		 System.out
//		 .println("~~~~~~~~~~~~~~~~~~~~ Orders: ~~~~~~~~~~~~~~~~~~~~");
//		 List<Order> ord = new OrderDao().listAllOrders();
//		 for (Order o : ord) {
//		 System.out.println(o.getId() + " |" + o.getId_c() + " |"
//		 + o.getId_p()+ " |" +o.getCant());
//		 }
		

		new MainFrame();/////THE BEGINING OF THE USER INTERFACE!!!!

	}

}
