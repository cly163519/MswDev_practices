package nz.srm.matches;

public abstract class Result {

	protected int homeScore;
	protected int awayScore;
	
	public Result(int homeScore, int awayScore) {
		super();
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}
	
	public abstract boolean isDraw();
	
	public boolean isHomeWin() {
		return homeScore > awayScore;
	}
	
}
