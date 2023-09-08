package nz.srm.teams;

import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		boolean firstHost = o1.isHost();
		boolean secondHost = o2.isHost();
		
		if (firstHost && !secondHost) { return -1; }
		else if (!firstHost && secondHost) { return 1; }
		else return (o1.getRank() - o2.getRank());
	}

}
