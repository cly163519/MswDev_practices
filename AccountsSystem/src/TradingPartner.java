
public class TradingPartner extends Business{
	public TradingPartner(String name, int businessNumber) {
        super(name, businessNumber);
    }

    @Override
    public String getDetails() {
        return "Trading Partner [Name=" + name + ", Business#=" + businessNumber + 
               ", GST=" + gstRegistered + "]";
    }
}
