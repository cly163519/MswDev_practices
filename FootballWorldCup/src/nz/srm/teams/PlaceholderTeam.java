package nz.srm.teams;

public class PlaceholderTeam extends Team {

	public PlaceholderTeam(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isHost() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRank() {
		// TODO Auto-generated method stub
		return 10000;
	}

}
