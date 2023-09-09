package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;
import nz.srm.matches.*;

public class Scheduler {

		private Queue<Match> roundRobinMatches;
		private Queue<Match> knockoutMatches;
		public static String QUALIFIER1 = "Group Qualifier 1";
		public static String QUALIFIER2 = "Group Qualifier 2";
		public static String ROUND16 = "Round of 16";
		public static String QUARTER = "Quarter-Final";
		public static String SEMI = "Semi-Final";
		public static String FINAL = "Final";
		private int nextMatchID;
	
		public Scheduler() {
				this.roundRobinMatches = new ArrayDeque<Match>();
				this.knockoutMatches = new ArrayDeque<Match>();
				this.nextMatchID = 1;
		}
		
		private int findNextAvailableTeam(boolean[] assigned, int startIndex) {
			for (int loop = 0; loop < (assigned.length) - 1; loop++) {
				int nextPos = (startIndex + loop) % assigned.length;
				if (!assigned[nextPos]) return nextPos;
			}
			return -1;
		}
		
		public record RobinPair (int idx1, int idx2) {}
		public record KnockoutPair(String idx1, String idx2) {}
		
		public List<RobinPair> scheduleRound(int roundNum, int groupSize) {
			
			List<RobinPair> matchPairs = new ArrayList<RobinPair>();
			boolean[] assigned = new boolean[groupSize];
			
			for (int loop = 0; loop < (groupSize / 2); loop++) {
					int team1idx = this.findNextAvailableTeam(assigned, loop);
					assigned[team1idx] = true;
					int team2idx = this.findNextAvailableTeam(assigned, loop + roundNum + 1);
					assigned[team2idx] = true;
					matchPairs.add(new RobinPair(team1idx, team2idx));
			}
			
			return matchPairs;
		}
		
		public int scheduleRoundRobin(int startDate, List<Group> groups, int groupSize) {
			
				int matchDay = startDate;
				for (int roundLoop = 1; roundLoop < groupSize; roundLoop++) {
					List<RobinPair> matchPairs = this.scheduleRound(roundLoop, groupSize);
					for (int groupLoop = 0; groupLoop < groups.size(); groupLoop++) {
						Group group = groups.get(groupLoop);
						List<RealTeam> realTeams = group.getTeams();
						for (int matchLoop = 0; matchLoop < matchPairs.size(); matchLoop++) {
							RobinPair robinPair = matchPairs.get(matchLoop);
							Team home = realTeams.get(robinPair.idx1);
							Team away = realTeams.get(robinPair.idx2);
							Match match = new RoundRobinMatch(home, away, group, matchDay, this.nextMatchID++);	
							this.roundRobinMatches.offer(match);
						}
						if ((groupLoop % 2) != 0) {
							matchDay++;
						}
					}
				}
			
				return matchDay;
		}
		
		public int scheduleFirstRoundKnockouts(int startDate, List<Group> groups, int num) {
			int matchDay = startDate;
			
			for (int loop = 0; loop < num; loop++) {
				PlaceholderTeam team1 = new PlaceholderTeam(Scheduler.QUALIFIER1);
				PlaceholderTeam team2 = new PlaceholderTeam(Scheduler.QUALIFIER2);
				Match match = new KnockoutMatch(team1, team2, matchDay, this.nextMatchID++);
				this.knockoutMatches.offer(match);
				if ((loop % 2) != 0) {
					matchDay++;
				}
			}
			
			return matchDay;
		}
		
		public int scheduleLaterRoundKnockouts(int startDate, int num) {
			int matchDay = startDate;
			int count = num;
			
			for (int loop = 0; loop < num; loop++) {
				PlaceholderTeam team1 = new PlaceholderTeam("Winner of " + (this.nextMatchID - (count--)));
				PlaceholderTeam team2 = new PlaceholderTeam("Winner of " + (this.nextMatchID - (count)));
				Match match = new KnockoutMatch(team1, team2, matchDay, this.nextMatchID++);
				this.knockoutMatches.offer(match);
				if ((loop % 2) != 0) {
					matchDay++;
				}
			}
			
			return matchDay;
		}
		
		public void scheduleKnockouts(int startDate, List<Group> groups, int rounds) {
			System.out.println("\nThere are " + rounds + " rounds of knockout matches.");
			
			int nextRound = 1;
			int matchesToSchedule = (int)Math.pow(2, rounds - nextRound);				

			int matchDay = this.scheduleFirstRoundKnockouts(startDate, groups, matchesToSchedule);
			
			while (nextRound <= rounds) {
				matchesToSchedule = (int)Math.pow(2, rounds - nextRound);				
				matchDay = this.scheduleLaterRoundKnockouts(matchDay + 1, matchesToSchedule);
				nextRound++;
			}
			
			return;
		}
		
		/**
		 * 
		 * @return true if there is either a round robin match or a knockout match still scheduled to be played.
		 */
		public boolean isNextMatchRoundRobin() {
			return !this.roundRobinMatches.isEmpty();
		}
		
		public boolean isNextMatchKnockout() {
			return (!this.isNextMatchRoundRobin()) && (!this.knockoutMatches.isEmpty());
		}
		
		public Match getNextRoundRobinMatch() {
			return this.roundRobinMatches.poll();
		}
		
		public void print() {
			System.out.println("\nRound Robin:\n============\n");
			for (Match m: this.roundRobinMatches) {
				m.print();
			}

			System.out.println("\nKnockouts:\n==========\n");
			for (Match m: this.knockoutMatches) {
				m.print();
			}
		}
		
}
