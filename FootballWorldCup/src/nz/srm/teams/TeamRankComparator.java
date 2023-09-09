package nz.srm.teams;

import java.util.Comparator;

public class TeamRankComparator implements Comparator<Team> {

	public TeamRankComparator() {
	}

	@Override
	public int compare(Team t1, Team t2) {
		return t1.getRank() - t2.getRank();
	}

}
