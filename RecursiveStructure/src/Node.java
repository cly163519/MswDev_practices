
public class Node<T> {
	private T value;
	private Node<T> next;//next 变量保存了 指向下一个节点对象的引用。
	Node(T value, Node<T> next) {
		this.value = value;
		this.next = next;
	}

	/** Set new value, return the old value */
	/*
	 *  类比火车车厢： 每个 Node 就是一个车厢，里面装着 value。
		setValue 就像把车厢里的货物换掉，但你还能把旧货物拿回来。
		这样写的好处是：调用者可以知道之前存的是什么，不会丢失。
	 */
	public T setValue(T value) {
		T oldValue = this.value;
		this.value = value;
		return oldValue;
	}

	public T getValue() {
		return value;
	}

	public boolean hasNext() {
		return next != null;
	}

	public Node<T> getNext() throws NullPointerException {//获取下一个车厢，如果没有，就会报错。(如果能运行,这段不是必须的)
		if (next == null)
			throw new NullPointerException("List ended");
		return next;
	}

	protected Node<T> getNextUnsafe() {//直接返回下一个车厢（可能是空的，不安全）。(同样不是必须的)
		return next;
	}

	/** Set new next, return the old next */
	public Node<T> setNext(Node<T> newNext) {//把当前结点的「后面的车厢」换掉，返回原来连着的车厢。
		Node<T> oldNext = next;
		next = newNext;
		return oldNext;
	}
}
