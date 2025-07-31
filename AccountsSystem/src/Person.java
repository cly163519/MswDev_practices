import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Person implements Contact{
	private String name;
	private int age;
	private Date birthday;
	private boolean isStaff;
	private boolean isCustomer;
	private boolean gstRegistered;
	private String gstNumber;
	private List<String> transactions = new ArrayList<>();
	private double balance;
	
	public Person(String name, int age, Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return "Person [Name=" + name + ", Age=" + age + 
	               ", Staff=" + isStaff + ", Customer=" + isCustomer + 
	               ", GST=" + gstRegistered + "]";
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
		gstRegistered = registered;//啥意思,这段看不懂
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
		return true;
	}

	@Override
	public boolean hasBirthday(Date date) {
		// TODO Auto-generated method stub
		return birthday.equals(date);
	}
	
	public void setAsStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}
	
	public void setAsCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

}
