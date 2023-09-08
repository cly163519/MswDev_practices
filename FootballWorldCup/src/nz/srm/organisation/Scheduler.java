package nz.srm.organisation;

import java.util.*;

import nz.srm.matches.Match;

public class Scheduler {

		private Queue<Match> roundRobinMatches;
		private Queue<Match> knockoutMatches;
	
		public Scheduler() {
				this.roundRobinMatches = new ArrayDeque<Match>();
		}
		
		public void scheduleRoundRobin() {
				return;
		}
		
		/**
		 * 
		 * @return true if there is either a round robin match or a knockout match still scheduled to be played.
		 */
		public boolean isNextMatch() {
				return (!this.roundRobinMatches.isEmpty() || !this.knockoutMatches.isEmpty());
		}
		
}
