package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;
import nz.srm.matches.*;

public class Group {
	
	private List<LadderRow> rows;
	private int maxSize;
	private char ID;
	
	public Group(int size, char ID) {
		this.rows = new ArrayList<LadderRow>();
		this.maxSize = size;
		this.ID = ID;
	}

	public void addTeam(RealTeam t) {
		if (this.rows.size() < this.maxSize) {
			this.rows.add(new LadderRow(t));
		} else {
		}
	}
	
	private LadderRow findLadderRow(Team team) {
		for (LadderRow row: rows) {
			if (row.getTeam().getName().equals(team.getName())) {
				return row;
			}
		}
		return null;
	}
	
	public void addResult(Match match) {
		LadderRow row1 = this.findLadderRow(match.getHomeTeam());
		LadderRow row2 = this.findLadderRow(match.getAwayTeam());
	
		Result result = match.getResult();
		int homeScore = result.getHomeScore();
		int awayScore = result.getAwayScore();
	
		if (result.isDraw()) {
			row1.incrementDraw();
			row2.incrementDraw();
		} else if (result.isHomeWin()) {
			row1.incrementWin();
			row2.incrementLoss();
		} else {
			row1.incrementLoss();
			row2.incrementWin();
		}
		
		row1.addGoalsFor(homeScore);
		row1.addGoalsAgainst(awayScore);
		row2.addGoalsFor(awayScore);
		row2.addGoalsAgainst(homeScore);
		
		rows.sort(new LadderRowComparator());
		
		return;
	}
	
	/**
	 * 
	 * @param index a value between 1 and the number of teams in this group.
	 * @return
	 */
	protected List<RealTeam> getTeams() {
		List<RealTeam> realTeams = new ArrayList<RealTeam>();
		
		for (LadderRow row: this.rows) {
			realTeams.add(row.getTeam());
		}
		
		return realTeams;
	}
	
	public List<RealTeam> getQualifiers(int num) {
		List<RealTeam> realTeams = new ArrayList<RealTeam>();
		for (int loop = 0; loop < num; loop++) {
			realTeams.add(this.rows.get(loop).getTeam());
		}
		return realTeams;
	}
	
	public RealTeam getQualifier(int num) {
		return this.rows.get(num).getTeam();
	}
	
	public void print() {
		System.out.println("\nGROUP " + this.ID);
		System.out.println("Name | Played | Wins | Draws | Losses | Goals For | Goals Against | Goal Difference | Points");
		for (LadderRow row: this.rows) {
			row.print();
		}
	}
	
}
