package nz.srm.organisation;

import nz.srm.teams.*;

public class LadderRow {

	private Team team;
	private int played;
	private int won;
	private int lost;
	private int drawn;
	private int goalsScored;
	private int goalsAgainst;
	private static int pointsWin = 3;
	private static int pointsDraw = 1;
	
	public LadderRow(Team team) {
		this.team = team;
		this.played = 0;
		this.won = 0;
		this.lost = 0;
		this.drawn = 0;
		this.goalsScored = 0;
		this.goalsAgainst = 0;
	}

	public String getName() {
		return this.team.getName();
	}
	
	public Team getTeam() {
		return this.team;
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
	
	public void print() {
		String sep = "    |    ";
		System.out.println(this.team.getName() + sep + 
										this.played + sep +
										this.won + sep + 
										this.drawn + sep +
										this.lost + sep + 
										this.goalsScored + sep + 
										this.goalsAgainst + sep + 
										(this.goalsScored - this.goalsAgainst) + sep +
										((this.won * LadderRow.pointsWin) + (this.drawn * LadderRow.pointsDraw)) + sep);
	}
	
}
