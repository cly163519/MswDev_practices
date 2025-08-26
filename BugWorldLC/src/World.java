import java.util.ArrayList;
import java.util.Random;

//World class: stores bugs, plants, obstacles, and is responsible for drawing and updating
public class World {
	//=== The size of the world ===
	int height, width;;
	
	//=== Three array lists ===
	ArrayList<Bug> bugs = new ArrayList<>();
    ArrayList<Plant> plants = new ArrayList<>();
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    
	//=== Constructor: Ensure that the initial position is within the boundary ===
	public World(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	// Add method, put coordinator into boundary
    public void addBug(Bug b) {
        b.setX(clamp(b.getX(), 0, width - 1));
        b.setY(clamp(b.getY(), 0, height - 1));
        bugs.add(b);
    }
    public void addPlant(int x, int y, int size) {
        plants.add(new Plant(clamp(x,0,width-1), clamp(y,0,height-1), size));
    }
    public void addObstacle(int x, int y) {
        obstacles.add(new Obstacle(clamp(x,0,width-1), clamp(y,0,height-1)));
    }
	
    //=== Draw the world:frame + grid
	public void drawWorld() {
		// First prepare a two-dimensional character grid, ' ' represents an empty
        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = ' ';
            }
        }
        
        //Put plants（random number from 0-9）
        for (int i = 0; i < plants.size(); i++) {
            Plant p = plants.get(i);
            grid[p.y][p.x] = (char) ('0' + clamp(p.size, 0, 9));//这句啥意思?
        }
        // Put insects (if there are plants in the same grid, the insects will cover them)
        for (int i = 0; i < bugs.size(); i++) {
            Bug b = bugs.get(i);
            grid[b.getY()][b.getX()] = b.getSymbol();//这句啥意思?
        }
        // Put obstacles (cover everything)
        for (int i = 0; i < obstacles.size(); i++) {
            Obstacle o = obstacles.get(i);
            grid[o.y][o.x] = 'Ø';
        }

        // 打印：上边框 → 每一行（从上到下）→ 下边框
        printBorder();
        for (int y = height - 1; y >= 0; y--) {//为什么y = height - 1?
            System.out.print("|");
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);//这一行是打印空格??
            }
            System.out.println("|");
        }
        
        printBorder();
    }

    private void printBorder() {
        System.out.print("|");
        for (int i = 0; i < width; i++) System.out.print("-");
        System.out.println("|");
    }

	public void updateWorld() {
		 for (int i = 0; i < bugs.size(); i++) {
	            Bug b = bugs.get(i);
	            char dir = b.smellFood(this);
	            if (dir == 'X') dir = randomDirOrStay();
	            b.move(dir, this);  // 在bug类里面写一个move方法
	        }
	}
	
	private char randomDirOrStay() {//这段不懂,啥意思?
        int r = (int) (Math.random() * 5); // 0..4 -> 4 表示不动
        if (r == 0) return 'N';
        if (r == 1) return 'S';
        if (r == 2) return 'E';
        if (r == 3) return 'W';
        return 'X'; // 不动
    }
	
	public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    public boolean hasObstacleAt(int x, int y) {
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i).x == x && obstacles.get(i).y == y) return true;
        }
        return false;
    }
    public Bug getBugAt(int x, int y) {
        for (int i = 0; i < bugs.size(); i++) {
            Bug b = bugs.get(i);
            if (b.getX() == x && b.getY() == y) return b;
        }
        return null;
    }
    public Plant getPlantAt(int x, int y) {
        for (int i = 0; i < plants.size(); i++) {
            Plant p = plants.get(i);
            if (p.x == x && p.y == y) return p;
        }
        return null;
    }

    private static int clamp(int v, int low, int high) {//因为这个方法,跟对象没关系，它只做数值计算。
        if (v < low) return low;
        if (v > high) return high;
        return v;
    }  
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public ArrayList<Bug> getBugs(){
		return bugs;
	}
	
	public ArrayList<Plant> getPlants(){
		return plants;
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}

	public static int manhattan(int x, int y, int x2, int y2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
