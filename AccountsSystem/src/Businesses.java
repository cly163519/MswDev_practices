import java.util.ArrayList;
import java.util.List;

public class Businesses implements Contact{
	public String businessName;
	public int businessNumber;
	public boolean isSupplier;
	public boolean isCustomer;
	public boolean gstRegistered;
	public String gstNumber;
	public List<Transaction> transactions = new ArrayList<>();
	public double balance = 0.0;
	
	public Businesses(String businessName,int businessNumber, boolean isSupplier, boolean isCustomer, boolean gstRegistered, String gstNumber) {
		this.businessName = businessName;
		this.businessNumber = businessNumber;
		this.isSupplier = isSupplier;
		this.isCustomer = isCustomer;
		this.gstRegistered = gstRegistered;
		this.gstNumber = gstNumber;
	}
	
	public String getName() {//都有构造函数了,为什么还在这再做一遍getName?
		return businessName;
	}

	public void printDetails() {
		System.out.println("Business :" + businessName + "" + balance);
	}
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	public List<Transaction> getTransactions(){
		return transactions;
	}
		
	public double getBalance() {//这个不用写成构造函数?
		double balance = 0;
		for(int i = 0; i < transactions.size(); i++) {
			balance += transactions.get(i).getAmount();
		}
		return balance;
	}
	
	public boolean isBirthdayToday() {
		return false;
	}
	
	public boolean isGSTRegistered() {
		return gstRegistered;
	}
	
	public String gstGSTNumber() {
		return gstNumber;
	}
}
