package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;

public abstract class TournamentStructure {

	protected Map<String, Team> teams;
	
	public TournamentStructure() {
		this.teams = new HashMap<String, Team>();
	}
	
	public abstract void addTeams(List<Team> teams);
	
	public abstract List<String> getTags();
	
	public Team getTeam(String name) {
		return this.teams.get(name);
	}
	
	public void add(String tag, Team team) {
		this.teams.put(tag, team);
	}
	
	public List<Team> getTeams(String tag) {
		List<Team> matchedTeams = new ArrayList<Team>();
		this.teams.keySet().forEach(s -> {
			if (s.startsWith(tag)) {
				matchedTeams.add(this.teams.get(s));
			}
		}); 
		return matchedTeams;
	}
	
	public int getNumberOfTeams() {
		return this.teams.size();
	}
	
}
