
public class Wagon<Q> {
	
	
	
	private Q value;
	private Wagon<Q> next;
	
	public Wagon(Q val, Wagon<Q> n) {
		this.setValue(val);
		this.setNext(n);
	}

	public Wagon<Q> getNext() {
		return next;
		
	
	}

	public void setNext(Wagon<Q> next) {
		this.next = next;
	}

	public Q getValue() {
		return value;
	}

	public void setValue(Q value) {
		this.value = value;
	}
	
	public int size() {
		if (next == null) {
			return 1;
		}
		return 1 + next.size();
	}
	
	public int iterativeSize() {
		Wagon<Q> current = this;
		int size = 0;
		while (current != null) {
			size = size + 1;
			current = current.getNext();
		}
		return size;
	}
	
	public void insertAfter(Wagon<Q> w) {
		w.next = next;
		next = w;
	}
	
	public Wagon<Q> getWagon(int i) {
		if (i == 0) return this;
		return next.getWagon(i - 1);
	}
	
	public int findWagon(Q x) {
		if (this.value.equals(x))
			return 0;
		if (next != null) {
			int tmp = next.findWagon(x);
			if (tmp == -1)
				return tmp;
			return tmp + 1;
		}
		return -1;
	}
	
	public int findWagon(Q x, int acc) {
		if (value.equals(x)) {
			return acc;
		}
		if (next == null)
			return -1;
		return next.findWagon(x, acc + 1);
	}
	
	public Wagon<Q> reversed() {
		return reversed(null);
	}
	
	private Wagon<Q> reversed(Wagon<Q> n) {
		if (next == null) {
			return new Wagon<Q>(value, n);
		}
		return next.reversed(new Wagon<Q>(value, n));
	}
	
	public Wagon<Q> reverse(Wagon<Q> before) {
		if (next == null) {
			this.next = before;
			return this;
		}
		Wagon<Q> ret = next.reverse(this);
		this.next = before;
		return ret;
	}

	
}




























/*

public class Wagon<Q> {
    private Q value;
    private Wagon<Q> next;

    public Wagon(Q value) {
        this(value, null);
    }

    public Wagon(Q value, Wagon<Q> next) {
        this.value = value;
        this.next = next;
    }

    public Q getValue() {
        return value;
    }

    public Q setValue(Q newValue) {
        Q old = this.value;
        this.value = newValue;
        return old;
    }

    public Wagon<Q> getNext() {
        return next;
    }

    public Wagon<Q> setNext(Wagon<Q> newNext) {
        Wagon<Q> old = this.next;
        this.next = newNext;
        return old;
    }

    public boolean hasNext() {
        return next != null;
    }

    // 7) 在当前车厢之后插入一节 newWagon
    public void insertAfter(Wagon<Q> newWagon) {
        if (newWagon == null) return;
        newWagon.next = this.next;//可否把问号/this去掉?
        this.next = newWagon;
    }

    // 5) 从当前车厢起的链长//这里要用recursion
    public int size() {
        int n = 0;
        for (Wagon<Q> cur = this; cur != null; cur = cur.next) n++;
        return n;
    }
    
    /*
     * public int size() {//递归解法
    		if (next == null) {
        		return 1;
    		}
    	return 1 + next.size();
	}

     
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Wagon<Q> cur = this;
        while (cur != null) {
            sb.append(cur.value);
            cur = cur.next;
            if (cur != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

*/

