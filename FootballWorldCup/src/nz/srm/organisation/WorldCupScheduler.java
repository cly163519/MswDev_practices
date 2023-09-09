package nz.srm.organisation;

import nz.srm.matches.ScheduledMatch;

import java.util.*;

public class WorldCupScheduler extends TournamentScheduler {

	private int numGroups;
	private int groupSize;
	private List<ScheduledMatch> matches;
	private char firstgroup;
	
	public WorldCupScheduler(int numTeams, int numGroups, char groupID) {
		super(numTeams);
		this.groupSize = numTeams / numGroups;
		this.firstgroup = groupID;
		this.numGroups = numGroups;
		this.matches = new ArrayList<ScheduledMatch>();
	}

	@Override
	public void schedule() {
		this.scheduleRoundRobin();
		this.scheduleKnockouts(4);
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
			for (int group = 0; group < this.numGroups; group++) {
				String groupName = Character.toString(this.firstgroup + group);
				for (int matchLoop = 0; matchLoop < matchPairs.size(); matchLoop++) {
					TeamNamePair teamNamePair = matchPairs.get(matchLoop);
					String home = groupName + (teamNamePair.idx1 + 1);
					String away = groupName + (teamNamePair.idx2 + 1);
					ScheduledMatch match = new ScheduledMatch(home, away, TournamentScheduler.GROUP_STAGE, this.getMatchDay(), this.matches.size() + 1);	
					this.matches.add(match);
				}
				if ((group % 2) != 0) {
					this.advanceMatchDay(1);
				}
			}
		}
	}
	
	private void scheduleFirstRoundKnockouts(int num) {
		
		char group1 = this.firstgroup;
		char group2 = (char)(this.firstgroup + 1);
		for (int loop = 0; loop < num / 2; loop++) {
			String team1 = "Q" + group1 + "1";
			String team2 = "Q" + group2 + "2";
			String team3 = "Q" + group2 + "1";
			String team4 = "Q" + group1 + "2";
			ScheduledMatch match1 = new ScheduledMatch(team1, team2, TournamentScheduler.KNOCKOUT, this.getMatchDay(), this.matches.size() + 1);
			this.matches.add(match1);
			ScheduledMatch match2 = new ScheduledMatch(team3, team4, TournamentScheduler.KNOCKOUT, this.getMatchDay(), this.matches.size() + 1);
			this.matches.add(match2);

			this.advanceMatchDay(1);
			group1 += 2;
			group2 += 2;
			
		}

	}
	
	public void scheduleLaterRoundKnockouts(int num) {
		int count = num * 2;
		
		for (int loop = 0; loop < num; loop++) {
			String team1 = TournamentScheduler.WINNEROFTAG + (this.matches.size() + 1 - (count--));
			String team2 = TournamentScheduler.WINNEROFTAG + (this.matches.size() + 1 - count);
			ScheduledMatch match = new ScheduledMatch(team1, team2, TournamentScheduler.KNOCKOUT, this.getMatchDay(), this.matches.size() + 1);
			this.matches.add(match);
			if ((loop % 2) != 0) {
				this.advanceMatchDay(1);
			}
		}
	}
	
	private void scheduleKnockouts(int rounds) {
		
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
