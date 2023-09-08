package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;
import nz.srm.matches.*;

public class Tournament {
	
	private List<String> hosts;
	private List<Team> teams;
	private List<Group> groups;
	private boolean pauseSimulationPerMatch;
	private boolean pauseSimulationPerKnockoutMatch;
	private Scheduler scheduler;
	private static int groupSize = 4;
	
	public Tournament(List<Team> teams) {
		this.pauseSimulationPerKnockoutMatch = false;
		this.pauseSimulationPerMatch = true;
		this.groups = new ArrayList<Group>();
		this.scheduler = new Scheduler();
		this.teams = teams;
	}
	
	public void setup() {
		this.teams.sort(new TeamComparator());
		this.createStructure();
		this.createSchedule();
		this.print();
		this.simulate();
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
			Result result = match.simulate();
			match.print();
		}
	}
	
	public void print() {
		System.out.println("Groups\n======\n\n");
		for (Group g: this.groups) {
			g.print();
		}
		
		System.out.println("\n\nSchedule\n========\n\n");
		scheduler.print();
	}
	
}
