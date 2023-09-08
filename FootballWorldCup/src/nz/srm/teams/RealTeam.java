package nz.srm.teams;

public class RealTeam extends Team implements Comparable<RealTeam> {

	private boolean isHost;
	private int rank;
	
	public RealTeam(String name, int rank, boolean isHost) {
		super(name);
		this.isHost = isHost;
		this.rank = rank;
	}

	@Override
	public boolean isHost() {
		// TODO Auto-generated method stub
		return this.isHost;
	}

	@Override
	public int getRank() {
		// TODO Auto-generated method stub
		return this.rank;
	}

}
