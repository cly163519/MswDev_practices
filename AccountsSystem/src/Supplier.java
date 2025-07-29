import java.util.List;

public class Supplier extends Businesses{

	public Supplier(String businessName) {
		super(businessName); //super是啥用法来着?
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {//这个和下面那个不写也行?
		// TODO Auto-generated method stub
		return businessName;
	}

	@Override
	public void printDetails() {
		// TODO Auto-generated method stub
		System.out.println();
	}
	
	public void recordPurchase(double amount) {
		balance -= amount;
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
