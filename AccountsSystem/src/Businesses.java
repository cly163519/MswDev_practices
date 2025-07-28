
public abstract class Businesses implements Contact{
	public String businessName;
	public double balance = 0.0;
	
	public Businesses(String businessName) {
		this.businessName = businessName;
	}
	
	public String getName() {//都有构造函数了,为什么还在这再做一遍getName?
		return businessName;
	}
	
	public double getBalance() {//这个不用写成构造函数?
		return balance;
	}
	
	public void printDetails() {
		System.out.println("Business :" + businessName + "" + balance);
	}
	
}
