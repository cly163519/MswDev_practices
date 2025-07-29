import java.time.LocalDate;
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
		int day = UI.askInt("Birthday Day: ");
		int month = UI.askInt("Birthday month: ");
		boolean isStaff = UI.askBoolean("Is staff?");
		boolean isCustomer = UI.askBoolean("Is customer?");
		boolean gstReg = UI.askBoolean("Is registered?"); 
		String gstNum = gstReg ? UI.askString("GST Number: ") : "";
		int businessNumber = UI.askInt("Business number: ");
		boolean isSupplier = UI.askBoolean("Is supplier?");

		LocalDate birthday = LocalDate.of(2000, month, day);
		
		Contact individual = new Individuals(name, age, businessNumber, birthday, isStaff, isCustomer, isSupplier, gstReg, gstNum);
		contacts.add(individual);
		UI.println("Person added");
	}
	
	private void addSupplier() {
		// Add a supplier to the list of contacts
		String name = UI.askString("Name: ");
		int bn = UI.askInt("Business number: ");
		// Your code here...
		boolean gstReg = UI.askBoolean("GST registered?");
		String gstNum = gstReg ? UI.askString("GST number: ") : "";
		Contact supplier = new Businesses(name, bn, true,false, gstReg, gstNum);
		UI.println("Supplier added.");
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
		for(int i = 0; i < contacts.size(); i++) {
			UI.println(contacts.get(i).getDetails());
		}
	}
	private Contact findContact() {
		return null;
	}
	
	private Contact findContactByName(String name ) {
		// Find one of the contacts by name and report on them
		UI.askString("Name: ");
		// Your code here...
		for(int i = 0; i < contacts.size(); i++) {
			if(contacts.get(i).getName().equalsIgnoreCase(name)) {
				return contacts.get(i);
			}
		}
	   return null;
	}
	
	private void recordPurchase() {
		// Record a purchase from a supplier
		String name = UI.askString("Supplier: ");
		String product = UI.askString("Purchased: ");
		
		// Your code here...
		Contact c = findContactByName(name);
		if(c != null) {
			String item = UI.askString("Item: ");
			double price = UI.askDouble("Cost: ");
			c.addTransaction(new Transaction(item, -price));//这句看起来好高深
			UI.println("Purchase recorded.");
		}else {
			UI.println("Supplier not found.");
		}
		
	}
	
	private void recordSale() {
		// Record a sale to a customer
		String name = UI.askString("Customer: ");
		
		// Your code here...
		Contact c  = findContactByName(name);
		if(c != null) {
			String product = UI.askString("Purchased: ");
			double price = UI.askInt("Price: ");
			c.addTransaction(new Transaction(product, price));//与上面的product, 
			UI.println("Sale recorded");
		}else {
			UI.println("Client not found.");
		}
	}
	
	
	private void reportBalance() {
		// Report how much has been paid by/to a contact
		String name = UI.askString("Name: ");
		// Your code here...
		Contact c = findContactByName(name);
		if(c != null) {
			UI.println("Balance: " + c.getBalance());
		}else {
			UI.println("Contact not found.");
		}
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
/*
 * private void computeProfit() {
    double total = 0;
    for (int i = 0; i < contacts.size(); i++) {
        total += contacts.get(i).getBalance();
    }
    UI.println("Total Profit: $" + total);
}

private void listTransactions() {
    String name = UI.askString("Name (leave blank for all): ");
    if (name.trim().isEmpty()) {
        for (int i = 0; i < contacts.size(); i++) {
            List<Transaction> txs = contacts.get(i).getTransactions();
            for (int j = 0; j < txs.size(); j++) {
                UI.println(contacts.get(i).getName() + ": " + txs.get(j).getItem() + " - $" + txs.get(j).getAmount());
            }
        }
    } else {
        Contact c = findContactByName(name);
        if (c != null) {
            List<Transaction> txs = c.getTransactions();
            for (int i = 0; i < txs.size(); i++) {
                UI.println(txs.get(i).getItem() + " - $" + txs.get(i).getAmount());
            }
        } else {
            UI.println("Contact not found.");
        }
    }
}
 */
