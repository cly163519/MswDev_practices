package nz.srm.organisation;

import nz.srm.matches.*;
import nz.srm.teams.*;
import java.util.*;

/**
 * 
 */
public class FootballTournament {
	
	private String name;
	private int year;
	private TournamentStructure structure;
	private TournamentScheduler scheduler;
	
	/**
	 * Main constructor that stores links to the key components for running the tournament.
	 * 
	 * @param name the name of the tournament, without the year included.
	 * @param year a positive integer.
	 * @param structure a non-null reference to an object that can organise the structure of the tournament.
	 * @param scheduler a non-null reference to an object that can schedule the matches in the tournament.
	 */
	public FootballTournament(String name, int year, TournamentStructure structure, TournamentScheduler scheduler) {
		this.name = name;
		this.year = year;
		this.structure = structure;
		this.scheduler = scheduler;
	}

	public String getName() {
		return this.name;
	}
	
	public String getFullName() {
		return this.year + " " + this.name;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setup(List<Team> teams) {
		this.scheduler.schedule();
		this.structure.addTeams(teams);
	}
	
	public Team simulate() {
		
		this.printSchedule();
		
		System.out.println("\nResults\n======\n");
		
		boolean groupStagePrinted = false;
		
		int numMatches = this.scheduler.getNumberOfMatchesScheduled();
		for (int loop = 0; loop < numMatches; loop++) {
			ScheduledMatch match = this.scheduler.getScheduledMatch(loop);
			String homeTeamName = match.getHomeTeam();
			String awayTeamName = match.getAwayTeam();
			int matchType = match.getMatchType();
			int matchID = match.getMatchID();
			
			Team homeTeam = this.structure.getTeam(homeTeamName);
			Team awayTeam = this.structure.getTeam(awayTeamName);
			
			switch (matchType) {
			case TournamentScheduler.GROUP_STAGE: {
				char group = homeTeamName.charAt(0);
				this.generateNormalResult(group, homeTeam, awayTeam); 
				break;
				}
			case TournamentScheduler.KNOCKOUT: {
				if (!groupStagePrinted) {
					this.printStructure();
					groupStagePrinted = true;
				}
				this.generateKnockoutResult(matchID, homeTeam, awayTeam); 
				break;
				}
			}

		}
		
		Team winner = this.structure.getTeam(TournamentScheduler.WINNEROFTAG + this.scheduler.getNumberOfMatchesScheduled());
		return winner;
	}
	
	public void printSchedule() {
		System.out.println("\nScheduled Matches\n=========\n");
		
		int numMatches = this.scheduler.getNumberOfMatchesScheduled();
		
		for (int loop = 0; loop < numMatches; loop++) {
			ScheduledMatch match = this.scheduler.getScheduledMatch(loop); 
			match.print();
		}
	}
	
	public void printStructure() {
		System.out.println("\n\n" + this.name + " " + this.year + "\n========\n");
		
		System.out.println("Teams\n=====");
		List<String> tags = this.structure.getTags();
		
		Comparator<String> stringComparator = (first, second) -> first.compareTo(second);
		tags.sort(stringComparator);
		
		tags.forEach(s -> {
			System.out.println("\nGroup " + s + "\n=======\n");
			List<Team> teams = structure.getTeams(s);
			teams.sort(new TeamPointsComparator());
			teams.forEach(t -> t.print());
		});
		
		System.out.println("--------\n\n");
		
	}
		
	private void updateGroupQualifiers(char group) {
		List<Team> teams = structure.getTeams(Character.toString(group));
		teams.sort(new TeamPointsComparator());
		String prefix = "Q" + group;
		this.structure.add(prefix + "1", teams.get(0));
		this.structure.add(prefix + "2", teams.get(1));
	}
	
	private void generateNormalResult(char group, Team t1, Team t2) {
		int rankDiff = t2.getRank() - t1.getRank();
		int homeGoals = Math.max(0, (int)(Math.random() * 5) + (rankDiff / 8));
		int awayGoals = Math.max(0, (int)(Math.random() * 5) - (rankDiff / 8));
		
		t1.recordResult(homeGoals, awayGoals);
		t2.recordResult(awayGoals, homeGoals);
		
		System.out.println(t1.getName() + " " + homeGoals + " - " + awayGoals + " " + t2.getName());
		
		this.updateGroupQualifiers(group);
	}
	
	private void generateKnockoutResult(int matchID, Team t1, Team t2) {
		int rankDiff = t2.getRank() - t1.getRank();
		int homeGoals = Math.max(0, (int)(Math.random() * 5) + (rankDiff / 4));
		int awayGoals = Math.max(0, (int)(Math.random() * 5) - (rankDiff / 4));

		if (homeGoals == awayGoals) {
			if (Math.random() <= 0.5) {
				homeGoals++;
			} else {
				awayGoals++;
			}
		}
		
		if (homeGoals > awayGoals) {
			this.structure.add(TournamentScheduler.WINNEROFTAG + Integer.toString(matchID), t1);			
		} else {
			this.structure.add(TournamentScheduler.WINNEROFTAG + Integer.toString(matchID), t2);			
		}
		
		t1.recordResult(homeGoals, awayGoals);
		t2.recordResult(awayGoals, homeGoals);

		System.out.println(matchID + ": " + t1.getName() + " " + homeGoals + " - " + awayGoals + " " + t2.getName());

	}
	
}
