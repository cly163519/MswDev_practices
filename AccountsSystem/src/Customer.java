
public class Customer extends Businesses{

	public Customer(String businessName) {
		super(businessName);
		// TODO Auto-generated constructor stub
	}
	/*
	 *  
    	public Customer(String name) {
        super(name);
    	}
    	
	 */

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return businessName;
	}

	@Override
	public void printDetails() {
		// TODO Auto-generated method stub
		System.out.println();//如何选择打印的变量?
	}

	public void recordSale(double amount) {
		balance += amount;
	}
	
	public void recordPurchase(double amount) {
		balance -= amount;
	}
	
}
