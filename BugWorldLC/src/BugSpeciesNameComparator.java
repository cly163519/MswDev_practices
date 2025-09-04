import java.util.Comparator;

public class BugSpeciesNameComparator implements Comparator<Bug> {

	@Override
	public int compare(Bug a, Bug b) {
		// TODO Auto-generated method stub
		int c1 = a.getSpecies().compareTo(b.getSpecies());
		if(c1 != 0) {
			return c1;
		}
		return a.getName().compareTo(b.getName());
	}
	//比较虫子,先按种类比较,再按名称比较
	//compareTo(String other){}
		
	

}
