
public class Pair<L, R> {
	public final L left;
    public final R right;
    public Pair(L l, R r) { this.left = l; this.right = r; }
    @Override public String toString() { return "(" + left + ", " + right + ")"; }
}
