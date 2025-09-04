import java.util.Comparator;

public class PlantSizeComparator implements Comparator<Plant>{

	@Override
	public int compare(Plant a, Plant b) {
		// TODO Auto-generated method stub
		return Integer.compare(a.getSize(), b.getSize());
	}
	//用sort()查找最大plant
	
}
