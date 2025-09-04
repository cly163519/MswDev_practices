
public class Supplier extends Business{

	public Supplier(String name, int businessNumber) {
		super(name, businessNumber);
		// TODO Auto-generated constructor stub
	}
	@Override
    public String getDetails() {
        return "Supplier [Name=" + name + ", Business#=" + businessNumber + 
               ", GST=" + gstRegistered + "]";
    }
	
}
