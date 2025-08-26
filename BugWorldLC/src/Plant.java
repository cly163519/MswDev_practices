
public class Plant{
	int size;
	int x;
	int y;
	
	public Plant(int size, int x, int y) {
		this.size = Math.max(0, Math.min(9, size));;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	@Override 
	public String toString() { 
		return "Plant{" + x + "," + y + ", size=" + size + "}"; 
	}
	
}
