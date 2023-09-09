package nz.srm.matches;

import nz.srm.teams.*;
import nz.srm.organisation.*;

public abstract class Match {
	
		private Team homeTeam;
		private Team awayTeam;
		private int date;
		protected Result result;
		private int ID;
		
		public Match(Team homeTeam, Team awayTeam, int date, int ID) {
			super();
			this.homeTeam = homeTeam;
			this.awayTeam = awayTeam;
			this.date = date;
			this.result = null;
			this.ID = ID;
		}

		public abstract Result simulate();
		
		public boolean hasPlayed() {
			return this.result != null;
		}

		public Team getHomeTeam() {
			return this.homeTeam;
		}

		public Team getAwayTeam() {
			return this.awayTeam;
		}

		public int getDate() {
			return this.date;
		}

		public Result getResult() {
			return this.result;
		}
		
		public int getID() {
			return this.ID;
		}
		
		public void print() {
			System.out.print("Match Day: " + this.date +
											", " + this.homeTeam.getName() +
											" vs " + this.awayTeam.getName());
			
			if (this.hasPlayed()) {
				System.out.print(", score: " + this.result.homeScore + "  - " + this.result.awayScore);
			}

			System.out.println();
			
		}
		
}
