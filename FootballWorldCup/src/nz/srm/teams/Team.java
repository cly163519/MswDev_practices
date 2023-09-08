package nz.srm.teams;

public class Team implements Comparable<Team> {

	private String name;
	private boolean isHost;
	private int rank;
	
	public Team(String name, int rank, boolean isHost) {
		this.name = name;
		this.isHost = isHost;
		this.rank = rank;
	}
	
	public boolean isHost() {
		return this.isHost;
	}
	
	public int compareTo(Team t) {
		boolean selfHost = this.isHost;
		boolean otherHost = t.isHost;
		
		if (selfHost && !otherHost) { return -1; }
		else if (!selfHost && otherHost) { return 1; }
		else return this.rank - t.rank;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getRank() {
		return this.rank;
	}

}
