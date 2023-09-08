package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;

public class Group {
	
	private List<LadderRow> teams;
	private int maxSize;
	
	public Group(int size) {
		this.teams = new ArrayList<LadderRow>();
		this.maxSize = size;
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
	
}
