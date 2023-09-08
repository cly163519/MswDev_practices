package nz.srm.organisation;

public class LadderRow {

	private String name;
	private int played;
	private int won;
	private int lost;
	private int drawn;
	private int goalsScored;
	private int goalsAgainst;
	
	public LadderRow(String name) {
		this.name = name;
		this.played = 0;
		this.won = 0;
		this.lost = 0;
		this.drawn = 0;
		this.goalsScored = 0;
		this.goalsAgainst = 0;
	}

	public String getName() {
		return name;
	}

	public int getPlayed() {
		return played;
	}

	public int getWon() {
		return won;
	}

	public int getLost() {
		return lost;
	}

	public int getDrawn() {
		return drawn;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}
	
	
	
}
