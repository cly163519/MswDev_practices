package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;
import nz.srm.matches.*;

public class Tournament {
	
	private List<RealTeam> realTeams;
	private List<Group> groups;
	private Map<String, Match> results;
	private boolean pauseSimulationPerMatch;
	private boolean pauseSimulationPerKnockoutMatch;
	private Scheduler scheduler;
	private static int groupSize = 4;
	private static int numGroupQualifiers = 2;
	
	public Tournament(List<RealTeam> realTeams) {
		this.pauseSimulationPerKnockoutMatch = false;
		this.pauseSimulationPerMatch = true;
		this.groups = new ArrayList<Group>();
		this.scheduler = new Scheduler();
		this.results = new HashMap<String, Match>();
		this.realTeams = realTeams;
	}
	
	public void setup() {
		this.realTeams.sort(new TeamComparator());
		this.createStructure();
		this.createSchedule();
		this.scheduler.print();
		this.simulate();
		this.print();
		
		List<RealTeam> qualifiers = new ArrayList<RealTeam>();
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
				
		int numTeams = this.realTeams.size();
		boolean[] assigned = new boolean[numTeams];
		char gID = 'A';
		int numGroups = numTeams / Tournament.groupSize;
		
		for (int gNum = 0; gNum < numGroups; gNum++) {
			Group g = new Group(Tournament.groupSize, gID++);
			
			g.addTeam(this.realTeams.get(gNum));
			
			for (int seed = 2; seed <= Tournament.groupSize; seed++) {
				while (true) {
					int pick = (int) (Math.random() * numGroups);
					int selection = pick + ((seed - 1) * numGroups);
					if (!assigned[selection]) {
						g.addTeam(this.realTeams.get(selection));
						assigned[selection] = true;
						break;
					}
				}
			}
				
			this.groups.add(g);

		}
		
	}
	
	private void createSchedule() {
		int finalRoundRobinMatchDay = this.scheduler.scheduleRoundRobin(1, groups, groupSize);
		
		int numTeams = this.groups.size() * Tournament.numGroupQualifiers;
		int numLevels = 1;
		while (numTeams > 2) {
			numLevels++;
			numTeams /= 2;
		}
		
		this.scheduler.scheduleKnockouts(finalRoundRobinMatchDay + 1, groups, numLevels);
	}
	
	private boolean isValidTournament() {
		return ((this.realTeams.size() % Tournament.groupSize) == 0) ? true : false;
	
	}
	
	public void simulate() {
		int matchID = 1;
		while (this.scheduler.isNextMatchRoundRobin()) {
			Match match = this.scheduler.getNextRoundRobinMatch();
			match.simulate();
			this.results.put(Integer.toString(matchID++), match);
		}
	}
	
	public void print() {
		System.out.println("'\nResults\n======\n");
		int matchID = 1;
		while (true) {
			Match match = this.results.get(Integer.toString(matchID++));
			if (match != null) {
				match.print();
			} else {
				break;
			}
		}
		System.out.println("\nGroups\n======\n");
		for (Group g: this.groups) {
			g.print();
		}
	}
	
}
