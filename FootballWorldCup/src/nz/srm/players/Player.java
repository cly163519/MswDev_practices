package nz.srm.players;

public abstract class Player {


	private String name;
	private int age;
	private String nationality;
	
	public Player(String name, int age, String nationality) {
		super();
		this.name = name;
		this.age = age;
		this.nationality = nationality;
	}	
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getNationality() {
		return this.nationality;
	}
	
}
