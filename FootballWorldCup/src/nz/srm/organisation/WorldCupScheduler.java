package nz.srm.organisation;

import nz.srm.matches.KnockoutMatch;
import nz.srm.matches.Match;
import nz.srm.matches.RoundRobinMatch;
import nz.srm.matches.ScheduledMatch;
import nz.srm.organisation.Scheduler.RobinPair;
import nz.srm.teams.PlaceholderTeam;
import nz.srm.teams.RealTeam;
import nz.srm.teams.Team;

import java.util.*;

public class WorldCupScheduler extends TournamentScheduler {

	private int numGroups;
	private int groupSize;
	private List<ScheduledMatch> matches;
	private char[] groupCodes;
	private static char GROUP1 = 'A';
	
	public WorldCupScheduler(int numTeams, int groupSize) {
		super(numTeams);
		this.groupSize = groupSize;
		this.numGroups = numTeams / groupSize;
		this.groupCodes = new char[numGroups];
		this.matches = new ArrayList<ScheduledMatch>();
		
		for (int loop = 0; loop < numGroups; loop++) {
			this.groupCodes[loop] = (char)(WorldCupScheduler.GROUP1 + loop);
		}
	}

	@Override
	public void schedule() {
		this.scheduleRoundRobin();
		this.scheduleKnockouts(4);
		
		this.matches.forEach(s -> s.print());
	}

	@Override
	public int getNumberOfMatchesScheduled() {
		return this.matches.size();
	}

	@Override
	public ScheduledMatch getScheduledMatch(int matchID) {
		if ((matchID >= 0) && (matchID < this.matches.size())) {
			return this.matches.get(matchID);
		} else {
			return null;
		}
	}

	@Override
	public char[] getGroupCodes() {
		return this.groupCodes;
	}
	
	private record TeamNamePair (int idx1, int idx2) {}
	
	private int findNextAvailableTeam(boolean[] assigned, int startIndex) {
		for (int loop = 0; loop < (assigned.length) - 1; loop++) {
			int nextPos = (startIndex + loop) % assigned.length;
			if (!assigned[nextPos]) return nextPos;
		}
		return -1;
	}
	
	private List<TeamNamePair> scheduleRound(int roundNum, int groupSize) {
		
		List<TeamNamePair> matchPairs = new ArrayList<TeamNamePair>();
		boolean[] assigned = new boolean[groupSize];
		
		for (int loop = 0; loop < (groupSize / 2); loop++) {
			int team1idx = this.findNextAvailableTeam(assigned, loop);
			assigned[team1idx] = true;
			int team2idx = this.findNextAvailableTeam(assigned, loop + roundNum + 1);
			assigned[team2idx] = true;
			matchPairs.add(new TeamNamePair(team1idx, team2idx));
		}
		
		return matchPairs;
	}
	
	private void scheduleRoundRobin() {
		for (int round = 1; round < this.groupSize; round++) {
			List<TeamNamePair> matchPairs = this.scheduleRound(round, groupSize);
			for (int group = 0; group < this.groupSize; group++) {
				String groupName = Character.toString(WorldCupScheduler.GROUP1 + group);
				for (int matchLoop = 0; matchLoop < matchPairs.size(); matchLoop++) {
					TeamNamePair teamNamePair = matchPairs.get(matchLoop);
					String home = groupName + (teamNamePair.idx1 + 1);
					String away = groupName + (teamNamePair.idx2 + 1);
					ScheduledMatch match = new ScheduledMatch(home, away, "round robin", this.getMatchDay(), this.matches.size() + 1);	
					this.matches.add(match);
				}
				if ((group % 2) != 0) {
					this.advanceMatchDay(1);
				}
			}
		}
	}
	
	private void scheduleFirstRoundKnockouts(int num) {
		
		char group1 = WorldCupScheduler.GROUP1;
		char group2 = (char)(WorldCupScheduler.GROUP1 + 1);
		for (int loop = 0; loop < num / 2; loop++) {
			String team1 = Character.toString(group1) + "1";
			String team2 = Character.toString(group2) + "2";
			String team3 = Character.toString(group2) + "1";
			String team4 = Character.toString(group1) + "2";
			ScheduledMatch match1 = new ScheduledMatch(team1, team2, "knockout", this.getMatchDay(), this.matches.size());
			this.matches.add(match1);
			ScheduledMatch match2 = new ScheduledMatch(team3, team4, "knockout", this.getMatchDay(), this.matches.size());
			this.matches.add(match2);

			this.advanceMatchDay(1);
			group1 += 2;
			group2 += 2;
			
		}

	}
	
	public void scheduleLaterRoundKnockouts(int num) {
		int count = num * 2;
		
		for (int loop = 0; loop < num; loop++) {
			String team1 = "Winner of " + (this.matches.size() - (count--));
			String team2 = "Winner of " + (this.matches.size() - (count));
			ScheduledMatch match = new ScheduledMatch(team1, team2, "knockout", this.getMatchDay(), this.matches.size());
			this.matches.add(match);
			if ((loop % 2) != 0) {
				this.advanceMatchDay(1);
			}
		}
	}
	
	private void scheduleKnockouts(int rounds) {
		System.out.println("\nThere are " + rounds + " rounds of knockout matches.");
		
		int nextRound = 1;
		int matchesToSchedule = (int)Math.pow(2, rounds - nextRound);
		
		this.scheduleFirstRoundKnockouts(matchesToSchedule);
		nextRound++;
		
		while (nextRound <= rounds) {
			matchesToSchedule = (int)Math.pow(2, rounds - nextRound);				
			this.scheduleLaterRoundKnockouts(matchesToSchedule);
			nextRound++;
		}
	}

}
