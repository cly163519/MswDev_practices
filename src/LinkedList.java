import java.io.PrintStream;
import java.util.Iterator;

public class LinkedList<Q> {
	private Node<Q> head;

	LinkedList(Node<Q> head) {
		this.head = head;
	}

	public Node<Q> getHead() throws NullPointerException {
		if (head == null)
			throw new NullPointerException("List has no head");
		else
			return head;
	}

	protected Node<Q> getHeadUnsafe() {
		return head;
	}

	public Node<Q> get(int i) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if (i < 0)
			throw new ArrayIndexOutOfBoundsException("Not a valid number. List starts at 0");

		return get(getHead(), i);
	}

	public Node<Q> get(Node<Q> node, int i) throws ArrayIndexOutOfBoundsException {
		if (i == 0)
			return node;
		else {
			Node<Q> next = node.getNext();
			if (next == null)
				throw new ArrayIndexOutOfBoundsException("List too short");

			return get(next, i - 1);
		}

	}

	public void insert(Q newValue, int i) throws Exception {
		Node<Q> insertPoint = get(i);

		// Change value, remember old value
		Q oldValue = insertPoint.setValue(newValue);

		// Make a new node with old vale and make it the next node.
		Node<Q> newNode = new Node<Q>(oldValue, insertPoint.getNextUnsafe());
		insertPoint.setNext(newNode);

	}

	public void printAll(PrintStream s) throws NullPointerException {
		printAll(s, getHead());
	}

	public void printAll(PrintStream s, Node<Q> node) {
		System.out.println(node.getValue());

		if (node.getNextUnsafe() != null)
			printAll(s, node.getNext());

	}}
/*
	public static void main(String[] args) {
		Train<String> t = new Train<>();
        t.prepend("C"); t.prepend("B"); t.prepend("A");
        t.printTrain(); // A -> B -> C

        Wagon<String> w = new Wagon<>("X");
        t.prepend(w);
        t.printTrain(); // X -> A -> B -> C

        w.insertAfter(new Wagon<>("Y"));
        t.appendWagon(new Wagon<>("Z"));
        t.printTrain(); // X -> Y -> A -> B -> C -> Z

        System.out.println("size=" + t.size()); // 6
        System.out.println("get(2)=" + t.get(2)); // A
        System.out.println("findWagon(B)=" + t.findWagon("B")); // 3
        t.remove(2); // 移除 A
        t.insert(2, "A2");
        t.add(0, "HEAD");
        t.add(t.size(), "TAIL");
        t.printTrain();

        t.set(1, "X*");
        t.addAll(new Train<String>() {{ add("o1"); add("o2"); }});
        System.out.println("lastIndexOf(o2)=" + t.lastIndexOf("o2"));
        t.reverse();
        t.printTrain();

        Train<String> r = t.reversed();
        System.out.print("reversed: ");
        r.printTrain();

        t.removeObject("o1");
        Object[] arr = t.toArray();
        String[] arr2 = t.toArray(new String[0]);
        System.out.println("toArray len=" + arr.length + ", typed len=" + arr2.length);

        for (var it = t.iterator(); it.hasNext();) {
            if ("Z".equals(it.next())) it.remove();
        }
        t.printTrain();
        
        System.out.println("\nPass all exam");
    }
	
}
*/
