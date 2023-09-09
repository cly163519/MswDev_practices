package nz.srm.organisation;

import nz.srm.matches.ScheduledMatch;

public abstract class TournamentScheduler {

	private int matchDay;
	private int numTeams;
	public final static int GROUP_STAGE = 1;
	public final static int KNOCKOUT = 2;
	public final static String WINNEROFTAG = "Winner of ";
	
	public TournamentScheduler(int numTeams) {
		this.matchDay = 1;
		this.numTeams = numTeams;
	}

	protected int getMatchDay() {
		return this.matchDay;
	}
	
	protected void advanceMatchDay(int numDays) {
		this.matchDay += numDays;
	}
	
	protected int getNumberOfTeams() {
		return this.numTeams;
	}
	
//	public abstract char[] getGroupCodes();
	
	public abstract void schedule();
	
	public abstract int getNumberOfMatchesScheduled();
	
	public abstract ScheduledMatch getScheduledMatch(int matchID);
		
}
