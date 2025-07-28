
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
}
