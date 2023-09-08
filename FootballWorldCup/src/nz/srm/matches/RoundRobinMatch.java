package nz.srm.matches;

import nz.srm.teams.Team;
import nz.srm.organisation.*;

public class RoundRobinMatch extends Match {

	private Group group;

	public RoundRobinMatch(Team homeTeam, Team awayTeam, Group group, int date) {
		super(homeTeam, awayTeam, date);
		this.group = group;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Result simulate() {
		Team homeTeam = this.getHomeTeam();
		Team awayTeam = this.getAwayTeam();
		int rankingDiff = homeTeam.getRank() - awayTeam.getRank();
		int homeScore = Math.max(0, (int)(Math.random() * 3) - (int)(Math.random() * (rankingDiff / 6)));
		int awayScore = Math.max(0, (int)(Math.random() * 3) + (int)(Math.random() * (rankingDiff / 6)));
		this.result = new RoundRobinResult(homeScore, awayScore);
		this.group.addResult(this);
		return this.result;
	}

}
