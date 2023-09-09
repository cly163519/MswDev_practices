package nz.srm.matches;

public class ScheduledMatch {

	private String homeTeam;
	private String awayTeam;
	private int matchDay;
	private int matchID;
	private String matchType;
	
	public ScheduledMatch(String homeTeam, String awayTeam, String matchType, int matchDay, int matchID) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchDay = matchDay;
		this.matchID = matchID;
		this.matchType = matchType;
	}

	public String getHomeTeam() {
		return this.homeTeam;
	}

	public String getAwayTeam() {
		return this.awayTeam;
	}
	
	public String getMatchType() {
		return this.matchType;
	}

	public int getMatchDay() {
		return this.matchDay;
	}

	public int getMatchID() {
		return this.matchID;
	}
	
	public void print() {
		System.out.println("(" + this.matchID + ") - Match Day " + this.matchDay + ": (" + this.matchType + ") " + this.homeTeam + " vs " + this.awayTeam);
	}
	
}
