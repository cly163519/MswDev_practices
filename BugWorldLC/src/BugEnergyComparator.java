import java.util.Comparator;

public class BugEnergyComparator implements Comparator<Bug>{

	@Override
	public int compare(Bug a, Bug b) {
		// TODO Auto-generated method stub
		return Integer.compare(a.getEnergy(), b.getEnergy());
	}
	//比较两个bug能量场
	

}
