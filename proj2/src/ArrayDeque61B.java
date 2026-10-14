import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B {
    public int dequeSize;
    public int maxSize; // 最大容量
    public int nextFirst;
    public int nextLast;
    public T[] items;

    public ArrayDeque61B() {
        dequeSize = 0;
        nextFirst = 3;
        nextLast = 4;
        items = (T[]) new Object[8];
        maxSize = 8;
    }

    public static void main(String[] args) {

    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(Object x) { // need to judge whether it can be added ?
        this.items[this.nextFirst] = (T)x;
        nextFirst -= 1; // 记得修改为取模。暂未修改！
        nextFirst = nextFirst % maxSize;
        this.dequeSize += 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(Object x) { // need to judge whether it can be added ?
        this.items[this.nextLast] = (T)x;
        nextLast += 1; // 记得修改为取模。暂未修改！
        nextLast = nextLast % maxSize;
        this.dequeSize += 1;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List toList() {
        List<T> resultList = new ArrayList<>();
        // 循环取值添加
        int index = 0;
        while (index < this.dequeSize) {
            resultList.addLast((T) this.get(index));
            index += 1;
        }
        return resultList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (this.size() == 0) {
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
    public Object getFirst() {
        if (this.isEmpty()) {
            return null;
        }
        return this.items[nextFirst + 1]; // 注意首尾衔接。module operate?
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public Object getLast() {
        if (this.isEmpty()) {
            return null;
        }
        return this.items[nextLast- 1]; // 注意首尾衔接。module operate?
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public Object removeFirst() {
        return null;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public Object removeLast() {
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
    public Object get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        if (index < 0 || index > this.size() - 1) {
            return null;
        }
        return this.items[nextFirst + 1 + index];
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
//    @Override
//    public Object getRecursive(int index) {
//        return null;
//    }
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }
}
