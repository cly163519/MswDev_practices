import java.sql.Date;
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
		UI.addButton("Compute total profit", this::computeProfit1);
		UI.addButton("List all transactions with a contact", this::listTransactions);
		// Your code here?
	}
	
	private void addPerson() {
		String name = UI.askString("Name: ");
		int age = UI.askInt("Age: ");
		// Your code here...
		Date birthday = new Date(0);
		Person person = new Person(name, age, birthday);
		if (UI.askBoolean("Is this person staff?")) {
            person.setAsStaff(true);
        }
        
        if (UI.askBoolean("Is this person a customer?")) {
            person.setAsCustomer(true);
        }
        
        if (UI.askBoolean("Is GST registered?")) {
            String gst = UI.askString("GST number: ");
            person.setGSTRegistration(true, gst);
        }
        
        contacts.add(person);
        UI.println("Person added: " + name);
	}
	
	private void addSupplier() {
		// Add a supplier to the list of contacts
		String name = UI.askString("Name: ");
		int nbn = UI.askInt("Business number: ");
		// Your code here...
		Supplier supplier = new Supplier(name, nbn);
        
        if (UI.askBoolean("Is GST registered?")) {
            String gst = UI.askString("GST number: ");
            supplier.setGSTRegistration(true, gst);
        }
        
        contacts.add(supplier);
        UI.println("Supplier added: " + name);
	}
	
	private void addBusinessClient() {
		// Add a business client to the list of contacts
		String name = UI.askString("Name: ");
		int nbn = UI.askInt("Business number: ");
		// Your code here...
		Customer customer = new Customer(name, nbn);
        
        if (UI.askBoolean("Is GST registered?")) {
            String gst = UI.askString("GST number: ");
            customer.setGSTRegistration(true, gst);
        }
        
        contacts.add(customer);
        UI.println("Customer added: " + name);
	}
	
	private void addTradingPartner() {
		// Add someone who is both a buyer and a seller to the list of contacts
		String name = UI.askString("Name: ");
		// Your code here...
		int nbn = UI.askInt("Business number: ");
        
        TradingPartner partner = new TradingPartner(name, nbn);
        
        if (UI.askBoolean("Is GST registered?")) {
            String gst = UI.askString("GST number: ");
            partner.setGSTRegistration(true, gst);
        }
        
        contacts.add(partner);
        UI.println("Trading partner added: " + name);
	}
	
	private void listContacts() {
		// List all contacts in the system
		// Your code here...
		UI.clearText();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);//接口不是不能new吗?但是可以这样写?
            UI.println(contact.getDetails());
        }
	}
	
	private void findContact() {
		// Find one of the contacts by name and report on them
		String name = UI.askString("Name: ");
		// Your code here...
		Contact found = null;
        
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName().equalsIgnoreCase(name)) {
                found = contact;
                break;
            }
        }
        
        if (found != null) {
            UI.println("Contact found:");
            UI.println(found.getDetails());
            UI.println("Balance: $" + found.getBalance());
        } else {
            UI.println("Contact not found: " + name);
        }
	}
	
	private void recordPurchase() {
		// Record a purchase from a supplier
		String name = UI.askString("Supplier: ");
		String product = UI.askString("Purchased: ");
		double price = UI.askInt("Price: ");
		// Your code here...
		Contact supplier = findContactByName(name);//这个方法实在本主类里的,不是在接口里的
        
        if (supplier == null) {
            UI.println("Supplier not found: " + name);
            return;
        }
        supplier.recordTransaction("PURCHASE", price);
        UI.println("Purchase recorded: $" + price + " from " + name);
	}
	
	private Contact findContactByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	private void recordSale() {
		// Record a sale to a customer
		String name = UI.askString("Customer: ");
		String product = UI.askString("Purchased: ");
		double price = UI.askInt("Price: ");
		// Your code here...
		Contact customer = findContactByName(name);
        
        if (customer == null) {
            UI.println("Customer not found: " + name);
            return;
        }
        customer.recordTransaction("SALE", price);
        double totalProfit = price;
        
        UI.println("Sale recorded: $" + price + " to " + name);
	}
	
	
	private void reportBalance() {
		// Report how much has been paid by/to a contact
		String name = UI.askString("Name: ");
		// Your code here...
	}
	
	private void computeProfit1() {
		String name = null;
		// Compute the total profit of the business (sales - purchases)
		// Your code here...
		Contact contact = findContactByName(name);
        
        if (contact != null) {
            UI.println("Balance for " + name + ": $" + contact.getBalance());
        } else {
            UI.println("Contact not found: " + name);
        }
	}
	
	private void computeProfit() {
        String totalProfit = null;
		UI.println("Total profit: $" + totalProfit);
    }
    
    private void listTransactions() {
        String name = UI.askString("Contact name (leave blank for all): ");
        
        if (name.trim().isEmpty()) {
            UI.println("ALL TRANSACTIONS:");
            for (int i = 0; i < contacts.size(); i++) {
                Contact contact = contacts.get(i);
                List<String> transactions = contact.getTransactions();
                for (int j = 0; j < transactions.size(); j++) {
                    UI.println(contact.getName() + ": " + transactions.get(j));
                }
            }
        } else {
            Contact contact = findContactByName(name);//接口可以直接这样写吗?跟new不同?
            if (contact != null) {
                UI.println("TRANSACTIONS FOR " + name + ":");
                List<String> transactions = contact.getTransactions();
                for (int j = 0; j < transactions.size(); j++) {
                    UI.println(transactions.get(j));
                }
            } else {
                UI.println("Contact not found: " + name);
            }
        }
    }	
    @SuppressWarnings("unused")
	private void checkBirthdays() {
        Date today = new Date(0); // Simplified - should use current date
        UI.println("Today's birthdays:");
        
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.isPerson() && contact.hasBirthday(today)) {
                UI.println("Happy birthday, " + contact.getName() + "!");
            }
        }
    }
    
    @SuppressWarnings("unused")
	private Contact findContactByName1(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return (Contact) contacts;
    }
	
	public static void main(String[] args) {
		new AccountsUI();
	}

}
