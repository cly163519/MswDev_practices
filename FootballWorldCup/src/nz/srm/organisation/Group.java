package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;

public class Group {
	
	private List<LadderRow> teams;
	private int maxSize;
	private char ID;
	
	public Group(int size, char ID) {
		this.teams = new ArrayList<LadderRow>();
		this.maxSize = size;
		this.ID = ID;
	}

	public void addTeam(Team t) {
		if (this.teams.size() < this.maxSize) {
			this.teams.add(new LadderRow(t.getName()));
		} else {
		}
	}
	
	public void addTeam(String name) {
		if (this.teams.size() < this.maxSize) {
			this.teams.add(new LadderRow(name));
		} else {
		}
	}
	
	/**
	 * 
	 * @param index a value between 1 and the number of teams in this group.
	 * @return
	 */
	protected List<String> getTeams() {
		List<String> names = new ArrayList<String>();
		
		for (LadderRow row: this.teams) {
			names.add(row.getName());
		}
		
		return names;
	}
	
	public void print() {
		System.out.println("Name | Played | Wins | Draws | Losses | Goals For | Goals Against | Goal Difference | Points");
		for (LadderRow row: this.teams) {
			row.print();
		}
	}
	
}
