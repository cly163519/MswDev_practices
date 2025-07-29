import java.util.List;

public class Both extends Businesses {

	public Both(String businessName) {
		super(businessName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return businessName;
	}

	@Override
	public void printDetails() {
		// TODO Auto-generated method stub
		
	}
	
	public void recordPurchase(double amount) {
		balance -= amount;
	}
	
	public void recordSale(double amount) {
		balance += amount;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTransaction(Transaction t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBirthdayToday() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getGSTNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGSTRegistered() {
		// TODO Auto-generated method stub
		return false;
	}
}
