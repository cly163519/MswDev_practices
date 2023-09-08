package nz.srm.organisation;

import java.util.Comparator;

public class LadderRowComparator implements Comparator<LadderRow> {

	public LadderRowComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(LadderRow o1, LadderRow o2) {
		// TODO Auto-generated method stub
		int pointsDiff = o2.getPoints() - o1.getPoints();
		if (pointsDiff != 0) return pointsDiff;
		
		int goalsDiff = o2.getGoalDifference() - o1.getGoalDifference();
		if (goalsDiff != 0) return goalsDiff;
		
		int winsDiff = o2.getWon() - o1.getWon();
		if (winsDiff != 0) return winsDiff;
		
		int rankDiff= o1.getTeam().getRank() - o2.getTeam().getRank();
		return rankDiff;
	}

}
