import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Train<T>{
	Wagon<T> head;
	
	public Train(Wagon<T> head){
		this.head = head;
	}
	
	public void prepend(T x) {
		//head = new Wagon<T>(x);
	}
	
	public void prepend(Wagon<T> X) {
		
	}
}





























/*public class Train<T> implements Iterable<T> {
    private Wagon<T> head; // 2) 车头，可为 null

    public Train() {}
    public Train(Wagon<T> head) { this.head = head; }

    public Wagon<T> getHead() { return head; }

    // ===== 3) prepend(T) ===== 
    public void prepend(T x) {
        head = new Wagon<>(x, head); //新建一个节点，把它连到原来的链表前面，然后更新 head 指向它。
    }

    // ===== 4) prepend(Wagon<T>) =====
    public void prepend(Wagon<T> w) {
        if (w == null) return;
        w.setNext(head); // 只接入这一节
        head = w;
    }

    // ===== 6) size()（实时遍历） =====
    public int size() {//改成递归?
        int n = 0;
        for (Wagon<T> cur = head; cur != null; cur = cur.getNext()) n++;
        return n;
    }

    // ===== 8) appendWagon(Wagon<T>) —— 仅附加这一节 =====
    public void appendWagon(Wagon<T> w) {
        if (w == null) return;
        w.setNext(null);
        if (head == null) { head = w; return; }
        Wagon<T> tail = head;
        while (tail.getNext() != null) tail = tail.getNext();
        tail.setNext(w);
    }

    // ===== 9) getWagon(i) =====
    public Wagon<T> getWagon(int i) {//这里用递归?
        checkElementIndex(i);
        Wagon<T> cur = head;
        for (int k = 0; k < i; k++) cur = cur.getNext();
        return cur;
    }

    // ===== 10) findWagon(x) =====//不明白这段什么意思//用递归和三层return
    public int findWagon(T x) {//同时在wagon类里设置一个方法?参数为(Q x)
        int idx = 0;
        for (Wagon<T> cur = head; cur != null; cur = cur.getNext(), idx++) {
            if (Objects.equals(cur.getValue(), x)) return idx;
        }
        return -1;
    }

    // ===== 11) get(i) 返回数据项 =====
    public T get(int i) {
        return getWagon(i).getValue();
    }

    // ===== 12) remove(i) 删除第 i 节 =====
    public T remove(int i) {
        checkElementIndex(i);//不要用新增方法,直接在本方法内解决
        if (i == 0) {
            T val = head.getValue();
            head = head.getNext();//啥意思??
            return val;
        }
        Wagon<T> prev = getWagon(i - 1);
        Wagon<T> target = prev.getNext();
        T val = target.getValue();
        prev.setNext(target.getNext());
        return val;
    }

    //===== 13) insert(i, x) 在位置 i 插入新车厢 =====
    public void insert(int i, T x) {
        checkPositionIndex(i); // 允许等于 size（尾插）//不要新增这种方法,直接在这个方法里解决全部问题
        if (i == 0) { prepend(x); return; }
        if (i == size()) { add(x); return; }
        Wagon<T> prev = getWagon(i - 1);
        prev.setNext(new Wagon<>(x, prev.getNext()));
    }

    // ===== 14) add(x) 尾部添加 =====
    public void add(T x) {
        appendWagon(new Wagon<>(x));
    }

    // ===== 15) set(i, x) 替换第 i 节的值 =====
    public T set(int i, T x) {
        Wagon<T> node = getWagon(i);
        return node.setValue(x);
    }

    // ===== 16) addAll(Train<T>) 把另一列接到本列尾部，并清空对方 =====
    public void addAll(Train<T> other) {
        if (other == null || other.head == null) return;
        if (other == this) throw new IllegalArgumentException("Cannot addAll to itself");
        if (this.head == null) {
            this.head = other.head;
        } else {
            Wagon<T> tail = this.head;
            while (tail.getNext() != null) tail = tail.getNext();
            tail.setNext(other.head);
        }
        other.head = null; // 清空对方，避免共享链
    }

    // ===== 17) lastIndexOf(x) =====
    public int lastIndexOf(Object x) {
        int last = -1, idx = 0;
        for (Wagon<T> cur = head; cur != null; cur = cur.getNext(), idx++) {
            if (Objects.equals(cur.getValue(), x)) last = idx;
        }
        return last;
    }

    // ===== 18) reversed() 返回新列（不共享节点） =====
    public Train<T> reversed() {//反转列表可以用递归法和迭代法//return this是什么用法?
        Train<T> r = new Train<>();
        for (Wagon<T> cur = head; cur != null; cur = cur.getNext()) r.prepend(cur.getValue());
        return r;
    }

    // ===== 19) reverse() 原地反转 ===== 
    public void reverse() {
        Wagon<T> prev = null, cur = head;
        while (cur != null) {
            Wagon<T> next = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    // ===== 20) add(i, x) 在第 i 个位置添加新车厢 =====
    public void add(int i, T x) {
        insert(i, x);
    }

    // ===== 21) remove(Object o) 移除首个等于 o 的车厢 =====
    public boolean removeObject(Object o) {
        if (head == null) return false;
        if (Objects.equals(head.getValue(), o)) {
            head = head.getNext();
            return true;
        }
        Wagon<T> prev = head, cur = head.getNext();
        while (cur != null) {
            if (Objects.equals(cur.getValue(), o)) {
                prev.setNext(cur.getNext());
                return true;
            }
            prev = cur; cur = cur.getNext();
        }
        return false;
    }

    // ===== 22) toArray / toArray(T[]) + 迭代器 =====
    public Object[] toArray() {
        int n = size();
        Object[] arr = new Object[n];
        int i = 0;
        for (Wagon<T> cur = head; cur != null; cur = cur.getNext()) arr[i++] = cur.getValue();
        return arr;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a) {
        int n = size();
        T[] out = (a.length >= n) ? a :
                (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), n);
        int i = 0;
        for (Wagon<T> cur = head; cur != null; cur = cur.getNext()) out[i++] = cur.getValue();
        if (out.length > n) out[n] = null;
        return out;
    }

    // 迭代器：支持 foreach；remove() 删除最近一次 next() 返回的那节
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Wagon<T> prev = null;       // current 的前一节
            Wagon<T> current = null;    // 最近一次 next() 的节点
            Wagon<T> next = head;       // 下一个将返回的节点
            boolean canRemove = false;

            @Override public boolean hasNext() { return next != null; }

            @Override public T next() {
                if (next == null) throw new NoSuchElementException();
                prev = current;
                current = next;
                next = next.getNext();
                canRemove = true;
                return current.getValue();
            }

            @Override public void remove() {
                if (!canRemove) throw new IllegalStateException();
                if (current == head) {
                    head = head.getNext();
                } else {
                    // prev 一定存在
                    prev.setNext(current.getNext());
                }
                current = null;
                canRemove = false;
            }
        };
    }

    // ===== 辅助 & 输出 ===== 
    public void printTrain() {
        if (head == null) { System.out.println("(empty train)"); return; }
        StringBuilder sb = new StringBuilder();
        for (Wagon<T> cur = head; cur != null; cur = cur.getNext()) {
            sb.append(cur.getValue());
            if (cur.getNext() != null) sb.append(" -> ");
        }
        System.out.println(sb);
    }

    private void checkElementIndex(int i) {
        int n = size();
        if (i < 0 || i >= n) throw new IndexOutOfBoundsException("index: " + i + ", size: " + n);
    }

    private void checkPositionIndex(int i) {
        int n = size();
        if (i < 0 || i > n) throw new IndexOutOfBoundsException("index: " + i + ", size: " + n);
    }
}
*/