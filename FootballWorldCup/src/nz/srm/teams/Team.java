package nz.srm.teams;

public class Team {

	private String name;
	private int rank;
	private int form;
	private int wins;
	private int losses;
	private int draws;
	private int goalsScored;
	private int goalsAgainst;
	
	public static int IN_FORM = 1;
	public static int BALANCED = 0;
	public static int OUT_FORM = -1;
	
	public Team(String name, int rank, int form) {
		this.name = name;
		this.rank = rank;
		this.form = form;
		this.wins = 0;
		this.losses = 0;
		this.draws = 0;
		this.goalsScored = 0;
		this.goalsAgainst = 0;
	}
	
	
	public void recordResult(int goalsScored, int goalsAgainst) {
		this.goalsScored += goalsScored;
		this.goalsAgainst += goalsAgainst;
		if (goalsScored > goalsAgainst) {
			this.wins++;
		} else if (goalsScored < goalsAgainst) {
			this.losses++;
		} else {
			this.draws++;
		}
	}

	public void recordResult(int goalsScored, int goalsAgainst, int penaltiesFor, int penaltiesAgainst) {
		this.recordResult(goalsScored, goalsAgainst);
	}

	public String getName() {
		return this.name;
	}

	public int getRank() {
		return this.rank;
	}

	public int getForm() {
		return this.form;
	}

	public int getWins() {
		return this.wins;
	}

	public int getLosses() {
		return this.losses;
	}

	public int getDraws() {
		return this.draws;
	}

	public int getGoalsScored() {
		return this.goalsScored;
	}

	public int getGoalsAgainst() {
		return this.goalsAgainst;
	}
	
	public int getGoalDifference() {
		return this.goalsScored - this.goalsAgainst;
	}
	
	public int getPoints() {
		return (this.wins * 3) + this.draws;
	}
	
	public int getPlayed() {
		return this.wins + this.draws + this.losses;
	}
	
	public void print() {
		String sep = " | ";
		System.out.println("(" + this.rank + ") " + 
							this.name + sep +
							this.getPlayed() + sep + 
							this.wins + sep + 
							this.draws + sep + 
							this.losses + sep + 
							this.goalsScored + sep +
							this.goalsAgainst + sep +
							this.getGoalDifference() + sep +
							this.getPoints());
	}

}
