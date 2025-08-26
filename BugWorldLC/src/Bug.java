import java.util.Scanner;

public class Bug implements Comparable<Bug>{
	String species;
	String name;
	char symbol;
	int x;
	int y;
	int energy;
	int id;
	
	public Bug() {
		this.species = "";
		this.name = "";
		this.symbol = '?';
		this.x = 0;
		this.y = 0;
		this.energy = 100;
		this.id = (int)(Math.random() * 10000);
	}
	
	public Bug(String species, String name, char symbol, int x, int y, int energy, int id) {
		this.species = species;
		this.name = name;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.energy = energy;
		this.id = id;
	}
	
	// 简短信息，便于打印
	String shortInfo(){ 
		return name+"["+symbol+"]@("+x+","+y+") E="+energy; 
	}
	
	public void input(Scanner sc) {
		//Bug bug4 = new Bug();
		System.out.print("species: ");  setSpecies(sc.nextLine().trim());
        System.out.print("name: ");     setName(sc.nextLine().trim());
        System.out.print("symbol: ");   setSymbol(sc.nextLine().charAt(0));
        System.out.print("x: ");        setX(Integer.parseInt(sc.nextLine()));
        System.out.print("y: ");        setY(Integer.parseInt(sc.nextLine()));
        System.out.print("energy: ");   setEnergy(Integer.parseInt(sc.nextLine()));
        System.out.print("id: ");       setId(Integer.parseInt(sc.nextLine()));
        
	}
	
	public void setSpecies(String species) {
		this.species = species;
	}
	
	public String getSpecies() {
		return species;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return species + "[" + symbol + "] name=" + name +
	               " id=" + id + " pos=(" + x + "," + y + ") E=" + energy;
	}
	
	public String toText() {
		return "Bug {\n" +
	               "  species = '" + species + "',\n" +
	               "  name    = '" + name + "',\n" +
	               "  symbol  = '" + symbol + "',\n" +
	               "  x = " + x + ",\n" +
	               "  y = " + y + ",\n" +
	               "  energy = " + energy + ",\n" +
	               "  id = " + id+ "\n" +
	               "}";
	}
	
	public void move(char dir, World w) {
		int nx = x, ny = y;
        if (dir=='N'||dir=='n') ny = y+1;
        else if (dir=='S'||dir=='s') ny = y-1;
        else if (dir=='E'||dir=='e') nx = x+1;
        else if (dir=='W'||dir=='w') nx = x-1;
        else return; // 'X' 不动

        if (!w.inBounds(nx, ny)) return;
        if (w.hasObstacleAt(nx, ny)) return;
        x = nx; y = ny;
	}
       
	
	
	 // ====== 闻食物（超简版） ======//这大段逻辑很复杂,需要再深入学习,了解其作用
    // h：找最近植物（半径3）；c：找最近 h 型虫（半径3）；f：先找植物，找不到再找 h（半径2）
	//使用Comparators/Comparable决定最近移动方向(食肉动物/食草动物/杂食动物)
    public char smellFood(World w) {
        int range = (symbol=='h'||symbol=='H')?3 : (symbol=='c'||symbol=='C')?3 : 2;

        // 1) 尝试找植物（h/f）
        if (symbol=='h' || symbol=='H' || symbol=='f' || symbol=='F') {
            int best = Integer.MAX_VALUE, tx = x, ty = y;
            for (int i = 0; i < w.getPlants().size(); i++) {
                Plant p = w.getPlants().get(i);
                int d = Math.abs(p.x - x) + Math.abs(p.y - y);
                if (d > 0 && d <= range) { 
                	if (d < best){ 
                	best = d; tx = p.x; ty = p.y; 
                	} 
                }
            }
            if (best != Integer.MAX_VALUE) return oneStepToward(tx, ty);
        }

        // 2) c 或 f：找最近的 h 型虫
        if (symbol=='c' || symbol=='C' || symbol=='f' || symbol=='F') {
            int best = Integer.MAX_VALUE, tx = x, ty = y;
            for (int i = 0; i < w.getBugs().size(); i++) {
                Bug b = w.getBugs().get(i);
                if (b == this) continue;
                char s = b.getSymbol();
                if (s!='h' && s!='H') continue;
                int d = Math.abs(b.getX() - x) + Math.abs(b.getY() - y);
                if (d > 0 && d <= range) { if (d < best) { best = d; tx = b.getX(); ty = b.getY(); } }
            }
            if (best != Integer.MAX_VALUE) return oneStepToward(tx, ty);
        }

        // 3) 闻不到 -> 不动，让世界随机一下
        return 'X';
    }

    private char oneStepToward(int tx, int ty) {
        int dx = tx - x, dy = ty - y;
        if (Math.abs(dx) >= Math.abs(dy)) return dx > 0 ? 'E' : 'W';
        return dy > 0 ? 'N' : 'S';
    }

	@Override
	public int compareTo(Bug o) {
		// TODO Auto-generated method stub
		if (this.energy != o.energy) return Integer.compare(o.energy, this.energy);
        return this.name.compareTo(o.name);
	}
}
    

