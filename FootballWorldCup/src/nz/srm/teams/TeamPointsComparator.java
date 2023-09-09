package nz.srm.teams;

import java.util.Comparator;

public class TeamPointsComparator implements Comparator<Team> {

	public TeamPointsComparator() {
		
	}
	
	@Override
	public int compare(Team t1, Team t2) {
		return t2.getPoints() - t1.getPoints();
	}

}
