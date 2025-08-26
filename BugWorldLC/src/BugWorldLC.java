import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * 	List<Bug> = 装虫子的容器
	Comparator<Bug> = 比较两只虫子的规则
	两者都用泛型 <Bug>，但意义完全不同：一个是“存什么”，一个是“比较什么”。
 */

public class BugWorldLC {
	
	public static void main(String[] args) throws Exception {
		
		Scanner scanner = new Scanner(System.in);
		Bug bug1 = new Bug();
		Bug bug2 = new Bug("jerry", "mouse", 'j', 200, 300, 20, 101);
		System.out.println("bug1.toString(): " + bug1.toString());
		System.out.println("bug2.toText():" + bug2.toText());
		
		System.out.println("Enter bug:");
		Bug bug3 = new Bug();
		bug3.input(scanner);
		System.out.println("You created: \n" + bug3.toText());
		System.out.println("You created: \n" + bug3.toString());
		
		List<Bug> bugs = new ArrayList<>();
		bugs.add(bug1);
		bugs.add(bug2);
		bugs.add(bug3);
		
		System.out.println("Bugs in List: ");
		for(int i = 0; i < bugs.size(); i++) {
			System.out.println(bugs.get(i).toString());
		}
	
		Bug bug4 = new Bug("lucy", "beauty", 'b', 10, 20, 50, 102);
		System.out.println("Move 100 times..");
		
		for(int i = 0; i < 100; i++) {
			int r = (int)(Math.random() * 4);
			char dir;
			if(r == 0) dir = 'N';
			else if(r == 1) dir = 'S';
			else if(r == 2) dir = 'W';
			else dir = 'E';
			
		}
		System.out.println("After 100 random moves: " + bug4.getName() + " at (" + bug4.getX() + "," + bug4.getY() + ")");
		scanner.close();
		
		World world = new World(100, 100);
		// 加虫子（h=草食，c=肉食，f=杂食）
		world.addBug(new Bug("herb", "harry",  'h', 7,  1, 50, 1));
		world.addBug(new Bug("carn", "coco",   'c', 14, 2, 50, 2));
		world.addBug(new Bug("herb", "helen",  'h', 20, 4, 50, 3));
		world.addBug(new Bug("omni", "frank",  'f', 10, 0, 50, 4));

        // 加植物（数字0-9）
		world.addPlant(12, 3, 9);
		world.addPlant(3,  2, 4);
		world.addPlant(22, 0, 2);
		world.addPlant(6,  1, 0);

        // 加障碍
		world.addObstacle(7,  3);
		world.addObstacle(16, 3);
		world.addObstacle(18, 2);
		world.addObstacle(2,  1);
		world.addObstacle(15, 1);

        // 动画：更新→绘制
        for (int step = 0; step < 20; step++) {
            System.out.println("Step " + step);
            world.drawWorld();
            Thread.sleep(300);//显示动画效果
            world.updateWorld();
        }
		world.drawWorld();
	
	
	// ====== 3) 比较器与排序演示 ======
	System.out.println("\n== Comparator demos ==");
	List<Plant> plantsCopy = new ArrayList<>(world.getPlants());
	plantsCopy.sort(new PlantSizeComparator());
	if(!plantsCopy.isEmpty()) {
		Plant largest = plantsCopy.get(plantsCopy.size() - 1);
		System.out.println("Largest plant: " + largest);
	}
	//Bug不同排序方式
	List<Bug> bugsCopy = new ArrayList<>(world.getBugs());
	bugsCopy.sort(new BugEnergyComparator());
	System.out.println("Bugs by energy: " + toShortInfos(bugsCopy));
	
	Collections.sort(bugsCopy); // 使用 Bug 的自然序（Comparable）
    System.out.println("Bugs by natural order (energy desc, then name): " +
    		toShortInfos(bugsCopy));
    
    bugsCopy.sort(new BugSpeciesNameComparator());
    System.out.println("Bugs by species+name: " + toShortInfos(bugsCopy));
    
	// ====== 4) 泛型 Pair 演示 ======
    System.out.println("\n== Pair demos ==");
    List<Pair<String, Integer>> nameEnergyPairs = buildBugNameEnergyPairs(world);
    System.out.println("Name-Energy pairs: " + nameEnergyPairs);
    
    Pair<List<String>, List<Integer>> splitted = splitPairs(nameEnergyPairs);
    System.out.println("Split into Pair<names, energies>: " + splitted);
    
	// ====== 5) 生成所有 Bug 对并按曼哈顿距离排序，输出最近/最远/中位 ======
    System.out.println("\n== All bug pairs by Manhattan distance ==");
    Pair<Bug, Bug>[] arr = allBugPairs(world.getBugs());
    Arrays.sort(arr, new BugPairDistanceComparator());
    if (arr.length > 0) {
        Pair<Bug, Bug> nearest  = arr[0];
        Pair<Bug, Bug> farthest = arr[arr.length - 1];
        Pair<Bug, Bug> median   = arr[arr.length / 2];
        System.out.println("Nearest : " + pairWithDistance(nearest));
        System.out.println("Farthest: " + pairWithDistance(farthest));
        System.out.println("Median  : " + pairWithDistance(median));
    }
    scanner.close();
    System.out.println("\nDone.");
}

	// 辅助展示
	static List<String> toShortInfos(List<Bug> list){
	List<String> out = new ArrayList<>();
	for (Bug b: list) out.add(b.shortInfo());
	return out;
	}


	static List<Pair<String,Integer>> buildBugNameEnergyPairs(World w){
	List<Pair<String,Integer>> res = new ArrayList<>();
	for (Bug b: w.getBugs()) res.add(new Pair<>(b.getName(), b.getEnergy()));
	return res;
	}


	static Pair<List<String>, List<Integer>> splitPairs(List<Pair<String,Integer>> src){
	List<String> names = new ArrayList<>();
	List<Integer> energies = new ArrayList<>();
	for (Pair<String,Integer> p: src){ names.add(p.left); energies.add(p.right); }
	return new Pair<>(names, energies);
	}


	@SuppressWarnings("unchecked")
	static Pair<Bug,Bug>[] allBugPairs(List<Bug> bugs){
	int n = bugs.size();
	int m = n*(n-1)/2;
	Pair<Bug,Bug>[] arr = (Pair<Bug,Bug>[]) new Pair[m];
	int k=0; for (int i=0;i<n;i++) for (int j=i+1;j<n;j++) arr[k++] = new Pair<>(bugs.get(i), bugs.get(j));
	return arr;
	}


	static String pairWithDistance(Pair<Bug,Bug> p){
	int d = World.manhattan(p.left.getX(), p.left.getY(), p.right.getX(), p.right.getY());
	return p.left.shortInfo() + " - " + p.right.shortInfo() + ", d=" + d;
	}
	}