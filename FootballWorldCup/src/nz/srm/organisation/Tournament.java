package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;
import nz.srm.matches.*;

public class Tournament {
	
	private List<Team> teams;
	private List<Group> groups;
	private List<Match> results;
	private boolean pauseSimulationPerMatch;
	private boolean pauseSimulationPerKnockoutMatch;
	private Scheduler scheduler;
	private static int groupSize = 4;
	private static int numGroupQualifiers = 2;
	
	public Tournament(List<Team> teams) {
		this.pauseSimulationPerKnockoutMatch = false;
		this.pauseSimulationPerMatch = true;
		this.groups = new ArrayList<Group>();
		this.scheduler = new Scheduler();
		this.results = new ArrayList<Match>();
		this.teams = teams;
	}
	
	public void setup() {
		this.teams.sort(new TeamComparator());
		this.createStructure();
		this.createSchedule();
		this.simulate();
		this.print();
		
		List<Team> qualifiers = new ArrayList<Team>();
		for (Group g: this.groups) {
			qualifiers.addAll(g.getQualifiers(Tournament.numGroupQualifiers));
		}
		
		System.out.println("\nQualifiers\n==========\n");
		for (Team t: qualifiers) {
			System.out.println("Qualifier: " + t.getName() + " (" + t.getRank() + ")");
		}
	}
 	
	private void createStructure() {
		if (!isValidTournament()) return;
				
		int numTeams = this.teams.size();
		boolean[] assigned = new boolean[numTeams];
		char gID = 'A';
		int numGroups = numTeams / Tournament.groupSize;
		
		for (int gNum = 0; gNum < numGroups; gNum++) {
			Group g = new Group(Tournament.groupSize, gID++);
			
			g.addTeam(this.teams.get(gNum));
			
			for (int seed = 2; seed <= Tournament.groupSize; seed++) {
				while (true) {
					int pick = (int) (Math.random() * numGroups);
					int selection = pick + ((seed - 1) * numGroups);
					if (!assigned[selection]) {
						g.addTeam(this.teams.get(selection));
						assigned[selection] = true;
						break;
					}
				}
			}
				
			this.groups.add(g);

		}
		
	}
	
	private void createSchedule() {
		this.scheduler.scheduleRoundRobin(1, groups, groupSize);
	}
	
	private boolean isValidTournament() {
		return ((this.teams.size() % Tournament.groupSize) == 0) ? true : false;
	
	}
	
	public void simulate() {
		while (this.scheduler.isNextMatch()) {
			Match match = this.scheduler.getNextMatch();
			match.simulate();
			this.results.add(match);
		}
	}
	
	public void print() {
		System.out.println("'\nResults\n======\n");
		for (Match m: this.results) {
			m.print();
		}
		System.out.println("\nGroups\n======\n");
		for (Group g: this.groups) {
			g.print();
		}
	}
	
}
