package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;

public class Group {
	
	private List<LadderRow> rows;
	private int maxSize;
	private char ID;
	
	public Group(int size, char ID) {
		this.rows = new ArrayList<LadderRow>();
		this.maxSize = size;
		this.ID = ID;
	}

	public void addTeam(Team t) {
		if (this.rows.size() < this.maxSize) {
			this.rows.add(new LadderRow(t));
		} else {
		}
	}
	
	/**
	 * 
	 * @param index a value between 1 and the number of teams in this group.
	 * @return
	 */
	protected List<Team> getTeams() {
		List<Team> teams = new ArrayList<Team>();
		
		for (LadderRow row: this.rows) {
			teams.add(row.getTeam());
		}
		
		return teams;
	}
	
	public void print() {
		System.out.println("GROUP " + this.ID);
		System.out.println("Name | Played | Wins | Draws | Losses | Goals For | Goals Against | Goal Difference | Points");
		for (LadderRow row: this.rows) {
			row.print();
		}
	}
	
}
