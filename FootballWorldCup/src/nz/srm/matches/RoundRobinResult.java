package nz.srm.matches;

public class RoundRobinResult extends Result {

	public RoundRobinResult(int homeScore, int awayScore) {
		super(homeScore, awayScore);
	}

	@Override
	public boolean isDraw() {
		// TODO Auto-generated method stub
		return this.homeScore == this.awayScore;
	}

}
