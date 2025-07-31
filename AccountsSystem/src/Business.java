import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Business implements Contact{
	protected String name;
	protected int businessNumber;
	protected boolean gstRegistered;
	protected String gstNumber;
	protected List<String> transactions = new ArrayList<>();
	protected double balance;
	
	public Business(String name, int businessNumber) {
		this.name = name;
		this.businessNumber = businessNumber;
	}

	

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return balance;
	}

	@Override
	public void recordTransaction(String type, double amount) {
		// TODO Auto-generated method stub
		balance += ("SALE".equals(type) ? amount : -amount);
        transactions.add(type + " $" + amount);
	}

	@Override
	public List<String> getTransactions() {
		// TODO Auto-generated method stub
		return transactions;
	}

	@Override
	public void setGSTRegistration(boolean registered, String gstNumber) {
		// TODO Auto-generated method stub
		gstRegistered = registered;
        if (registered) {
            this.gstNumber = gstNumber;
        } else {
            this.gstNumber = "";
        }
	}

	@Override
	public String getGSTNumber() {
		// TODO Auto-generated method stub
		return gstNumber;
	}

	@Override
	public boolean isPerson() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasBirthday(Date date) {
		// TODO Auto-generated method stub
		return false;
	}

}
