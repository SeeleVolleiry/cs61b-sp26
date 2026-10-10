import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    // 类属性和实例属性
    public int dequeSize;
    public class Node {
        Node prev;
        T val;
        Node next;

        public Node(Node p, T v, Node n) {
            prev = p;
            val = v;
            next = n;
        }
    }
    Node sentinel;

    public LinkedListDeque61B() {
        dequeSize = 0;
        sentinel = new Node(null, null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public static void main(String[] args) {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0); // [0]
        lld.addLast(1); // [0, 1]
        lld.addFirst(-1); // [-1. 0, 1]
    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node tmp = new Node(this.sentinel, x, this.sentinel.next);
        this.sentinel.next.prev = tmp;
        this.sentinel.next = tmp;
        this.dequeSize += 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node tmp = new Node(this.sentinel.prev, x, this.sentinel);
        this.sentinel.prev.next = tmp;
        this.sentinel.prev = tmp;
        this.dequeSize += 1;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        //用循环或者递归，把每一个Node的val添加进列表。
        Node ptr = this.sentinel.next;
        while (ptr != sentinel) {
            returnList.add(ptr.val);
            ptr = ptr.next;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (this.dequeSize == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return this.dequeSize;
    }

    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        if (this.isEmpty()) {
            return null;
        }
        return this.sentinel.next.val;
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        if (this.isEmpty()) {
            return null;
        }
        return this.sentinel.prev.val;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        return null;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        return null;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }

        int idx = 0; Node ptr = this.sentinel.next;
        while(idx < index) {
            idx += 1;
            ptr = ptr.next;
        }
        return ptr.val;
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size()) {
            return null;
        }

        return helper(this.sentinel.next, index);
    }
    private T helper(Node starter, int idx) {
        if (idx == 0) {
            return starter.val;
        }
        return helper(starter.next, idx -1);
    }
}
