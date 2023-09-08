package nz.srm.organisation;

import java.util.*;
import nz.srm.teams.*;
import nz.srm.matches.*;

public class Scheduler {

		private Queue<Match> roundRobinMatches;
		private Queue<Match> knockoutMatches;
	
		public Scheduler() {
				this.roundRobinMatches = new ArrayDeque<Match>();
				this.knockoutMatches = new ArrayDeque<Match>();
		}
		
		private int findNextAvailableTeam(boolean[] assigned, int startIndex) {
			for (int loop = 0; loop < (assigned.length) - 1; loop++) {
				int nextPos = (startIndex + loop) % assigned.length;
				if (!assigned[nextPos]) return nextPos;
			}
			return -1;
		}
		
		public record Pair (int idx1, int idx2) {}
		
		public List<Pair> scheduleRound(int roundNum, int groupSize) {
			
			List<Pair> matchPairs = new ArrayList<Pair>();
			boolean[] assigned = new boolean[groupSize];
			
			for (int loop = 0; loop < (groupSize / 2); loop++) {
					int team1idx = this.findNextAvailableTeam(assigned, loop);
					assigned[team1idx] = true;
					int team2idx = this.findNextAvailableTeam(assigned, loop + roundNum + 1);
					assigned[team2idx] = true;
					matchPairs.add(new Pair(team1idx, team2idx));
			}
			
			return matchPairs;
		}
		
		public void scheduleRoundRobin(int startDate, List<Group> groups, int groupSize) {
			
				int matchDay = startDate;
				for (int roundLoop = 1; roundLoop < groupSize; roundLoop++) {
					List<Pair> matchPairs = this.scheduleRound(roundLoop, groupSize);
					for (int groupLoop = 0; groupLoop < groups.size(); groupLoop++) {
						Group group = groups.get(groupLoop);
						List<Team> teams = group.getTeams();
						for (int matchLoop = 0; matchLoop < matchPairs.size(); matchLoop++) {
							Pair pair = matchPairs.get(matchLoop);
							Team home = teams.get(pair.idx1);
							Team away = teams.get(pair.idx2);
							Match match = new RoundRobinMatch(home, away, matchDay);	
							this.roundRobinMatches.offer(match);
						}
						if ((groupLoop % 2) != 0) {
							matchDay++;
						}
					}
				}
			
				return;
		}
		
		/**
		 * 
		 * @return true if there is either a round robin match or a knockout match still scheduled to be played.
		 */
		public boolean isNextMatch() {
			return (!this.roundRobinMatches.isEmpty() || !this.knockoutMatches.isEmpty());
		}
		
		public Match getNextMatch() {
			return this.roundRobinMatches.poll();
		}
		
		public void print() {
			for (Match m: this.roundRobinMatches) {
				m.print();
			}
		}
		
}
