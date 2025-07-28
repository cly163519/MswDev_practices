import java.util.ArrayList;
import java.util.List;

import ecs100.UI;

public class AccountsUI {

	// You will need to create the "Contact" type!
	private List<Contact> contacts = new ArrayList<>();
	
	public AccountsUI() {
		UI.initialise();
		UI.addButton("Add individual contact", this::addPerson);
		UI.addButton("Add business supplier contact", this::addSupplier);
		UI.addButton("Add business client contact", this::addBusinessClient);
		UI.addButton("Add trading partner contact", this::addTradingPartner);
		UI.addButton("List contacts", this::listContacts);
		UI.addButton("Find contact by name", this::findContact);
		UI.addButton("Record purchase from supplier", this::recordPurchase);
		UI.addButton("Record sale to client", this::recordSale);
		UI.addButton("Report sale/purchase balance for contact", this::reportBalance);
		UI.addButton("Compute total profit", this::computeProfit);
		UI.addButton("List all transactions with a contact", this::listTransactions);
		// Your code here?
	}
	
	private void addPerson() {
		String name = UI.askString("Name: ");
		int age = UI.askInt("Age: ");
		// Your code here...
		Individuals person1 = new Individuals("Lucy");//不需要这两行,实例化Individual类?
		Individuals person2 = new Individuals("Tom");
		
		List<Contact> contact = new ArrayList<>();//不明白这里的List<Contact>什么意思?所有的supplier/customer/both都加到这个集合里?可以这样加吗?
		contact.add(person1);
		contact.add(person2);
	}
	
	private void addSupplier() {
		// Add a supplier to the list of contacts
		String name = UI.askString("Name: ");
		int nbn = UI.askInt("Business number: ");
		// Your code here...
		
		
	}
	
	private void addBusinessClient() {
		// Add a business client to the list of contacts
		String name = UI.askString("Name: ");
		int nbn = UI.askInt("Business number: ");
		// Your code here...
	}
	
	private void addTradingPartner() {
		// Add someone who is both a buyer and a seller to the list of contacts
		String name = UI.askString("Name: ");
		// Your code here...
	}
	
	private void listContacts() {
		// List all contacts in the system
		// Your code here...
	}
	
	private void findContact() {
		// Find one of the contacts by name and report on them
		String name = UI.askString("Name: ");
		// Your code here...
	}
	
	private void recordPurchase() {
		// Record a purchase from a supplier
		String name = UI.askString("Supplier: ");
		String product = UI.askString("Purchased: ");
		double price = UI.askInt("Price: ");
		// Your code here...
	}
	
	private void recordSale() {
		// Record a sale to a customer
		String name = UI.askString("Customer: ");
		String product = UI.askString("Purchased: ");
		double price = UI.askInt("Price: ");
		// Your code here...
	}
	
	
	private void reportBalance() {
		// Report how much has been paid by/to a contact
		String name = UI.askString("Name: ");
		// Your code here...
	}
	
	private void computeProfit() {
		// Compute the total profit of the business (sales - purchases)
		// Your code here...
	}
	
	private void listTransactions() {
	}	
	
	
	public static void main(String[] args) {
		new AccountsUI();
	}

}
