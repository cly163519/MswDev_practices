
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
	
	

}
