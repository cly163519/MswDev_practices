
public class Customer extends Business{

	public Customer(String name, int businessNumber) {
		super(name, businessNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
    public String getDetails() {
        return "Customer [Name=" + name + ", Business#=" + businessNumber + 
               ", GST=" + gstRegistered + "]";
    }
}
