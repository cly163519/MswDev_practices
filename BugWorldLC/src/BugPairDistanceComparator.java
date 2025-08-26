import java.util.Comparator;

public class BugPairDistanceComparator implements Comparator<Pair<Bug, Bug>>{

	@Override
	public int compare(Pair<Bug, Bug> p1, Pair<Bug, Bug> p2) {
		// TODO Auto-generated method stub
		 int d1 = World.manhattan(p1.left.getX(), p1.left.getY(), p1.right.getX(), p1.right.getY());
	        int d2 = World.manhattan(p2.left.getX(), p2.left.getY(), p2.right.getX(), p2.right.getY());
	        return Integer.compare(d1, d2);
	}

}
