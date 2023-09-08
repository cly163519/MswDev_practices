package nz.srm.matches;

import java.util.Date;
import nz.srm.teams.*;

public abstract class Match {
	
		private Team homeTeam;
		private Team awayTeam;
		private int date;
		private Result result;
		
		public Match(Team homeTeam, Team awayTeam, int date) {
			super();
			this.homeTeam = homeTeam;
			this.awayTeam = awayTeam;
			this.date = date;
			this.result = null;
		}

		public abstract Result simulate();
		
		public boolean hasPlayed() {
			return this.result != null;
		}

		public Team getHomeTeam() {
			return homeTeam;
		}

		public Team getAwayTeam() {
			return awayTeam;
		}

		public int getDate() {
			return date;
		}

		public Result getResult() {
			return result;
		}
		
		public void print() {
			System.out.println("Match Day: " + this.date +
											" - " + this.homeTeam.getName() +
											" vs " + this.awayTeam.getName());
			
		}
		
}
