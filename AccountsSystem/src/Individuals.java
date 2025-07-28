
public class Individuals implements Contact{
	public String name;
	
	public Individuals(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void printDetails() {
		// TODO Auto-generated method stub
		System.out.println("Individual: " + name);
	}
	
	
}
