package nz.srm.teams;

public abstract class Team {

	protected String name;

	public Team(String name) {
		this.name = name;
	}

	public abstract boolean isHost();

	public int compareTo(RealTeam t) {
		boolean selfHost = this.isHost();
		boolean otherHost = t.isHost();
		
		if (selfHost && !otherHost) { return -1; }
		else if (!selfHost && otherHost) { return 1; }
		else return this.getRank() - t.getRank();
	}

	public String getName() {
		return this.name;
	}

	public abstract int getRank();

}