import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Individuals implements Contact{
	public String name;
	public int age;
	public int businessNumber;
	public LocalDate birthday;
	public boolean isStaff;
	public boolean isCustomer;
	public boolean isSupplier;
	public boolean gstRegistered;
	public String gstNumber;
	public List<Transaction> transactions = new ArrayList<>();
	
	public Individuals(String name, int age, int businessNumber, LocalDate birthday, boolean isStaff, boolean isCustomer, boolean isSupplier, boolean gstRegistered, String gstNumber) {
		this.name = name;
		this.age = age;
		this.businessNumber = businessNumber;
		this.birthday = birthday;
		this.isStaff = isStaff;
		this.isCustomer = isCustomer;
		this.isSupplier = isSupplier;
		this.gstRegistered = gstRegistered;
		this.gstNumber = gstNumber;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return "Business: " + name + " (#" + businessNumber + "), Supplier: " + isSupplier + ", Customer: " + isCustomer +
	               (gstRegistered ? ", GST: " + gstNumber : ", Not GST-Registered");
	}

	@Override
	public void addTransaction(Transaction t) {
		// TODO Auto-generated method stub
		transactions.add(t);//这是啥意思?
	}

	@Override
	public List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return transactions;//为啥要有一个addTransaction,还要有一个getTransaction?
	}

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		double balance = 0;
		for(int i = 0; i < transactions.size(); i++) {//这里的for循环干啥的?
			balance += transactions.get(i).getAmount();
		}
		return balance;
	}

	@Override
	public boolean isBirthdayToday() {
		// TODO Auto-generated method stub
		LocalDate today = LocalDate.now();//这两行这么大长串子,啥意思?
		return birthday.getMonth() == today.getMonth() && birthday.getDayOfMonth() == today.getDayOfMonth();
	}


	@Override
	public String getGSTNumber() {
		// TODO Auto-generated method stub
		return gstNumber;
	}

	@Override
	public boolean isGSTRegistered() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
