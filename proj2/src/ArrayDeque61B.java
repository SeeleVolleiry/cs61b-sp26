import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T> implements Deque61B<T> {
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

    // If the ArrayDeque is full,return true.Otherwise, false
    public boolean isFull() {
        if (dequeSize == maxSize) {
            return true;
        }
        return false;
    }

    // resizing up the ArrayDeque.
    private void resizeUp() {
        T[] newItems = (T[]) new Object[maxSize * 2];
        int incrementSpace = maxSize;
        int half = incrementSpace / 2; // 相当于新数组中第一个元素的索引

        for (int index = 0; index < this.size(); index++) { // 复制已有元素进新数组。
            newItems[half + index] = this.get(index);
        }
        this.nextFirst = half - 1;
        this.maxSize *= 2;
        this.nextLast = this.nextFirst + this.size() + 1;
        this.items = newItems;
    }

    // If the ArrayDeque should be resizeDown(),return true.Otherwise, false
    public boolean needDown() {
        if (dequeSize * 4 <= maxSize && maxSize >= 16) {
            return true;
        }
        return false;
    }

    // resizing down the ArrayDeque.
    private void resizeDown() {
        T[] newItems = (T[]) new Object[maxSize / 2];
        int leftSpace = maxSize / 2;
        int half = leftSpace / 2 - 1; // 相当于新数组中第一个元素的索引

        for (int index = 0; index < this.size(); index++) { // 复制已有元素进新数组。
            newItems[half + index] = this.get(index);
        }
        this.nextFirst = half - 1;
        this.maxSize /= 2;
        this.nextLast = (this.nextFirst + this.size() + 1) % maxSize;
        this.items = newItems;
    }

    public static void main(String[] args) {

    }

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) { // need to judge whether it can be added ?
        if (this.isFull()) {
            this.resizeUp();
        }
        this.items[this.nextFirst] = x;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = (nextFirst + maxSize) % maxSize;
        }
        this.dequeSize += 1;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) { // need to judge whether it can be added ?
        if (this.isFull()) {
            this.resizeUp();
        }
        this.items[this.nextLast] = x;
        nextLast += 1;
        if (nextLast >= maxSize) {
            nextLast = nextLast % maxSize;
        }
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
            resultList.addLast(this.get(index));
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
    public T getFirst() {
        if (this.isEmpty()) {
            return null;
        }
        int idx = (nextFirst + 1) % maxSize;
        return this.items[idx]; // 注意首尾衔接。module operate?
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
        int idx = nextLast -1;
        if (idx < 0 ) {
            idx = (idx + maxSize) % maxSize;
        }
        return this.items[idx]; // 注意首尾衔接。module operate?
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.needDown()) {
            this.resizeDown();
        }
        this.dequeSize -= 1;
        int firstIdx = (nextFirst + 1) % maxSize;
        T firstVal = this.items[firstIdx];
        this.nextFirst = firstIdx;
        this.items[nextFirst] = null;
        return firstVal;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.needDown()) {
            this.resizeDown();
        }
        this.dequeSize -= 1;
        int lastIdx = nextLast - 1;
        if (lastIdx < 0) {
            lastIdx = (lastIdx + maxSize) % maxSize;
        }
        T lastVal = this.items[lastIdx];
        this.nextLast = lastIdx;
        this.items[nextLast] = null;
        return lastVal;
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
        if (this.isEmpty()) {
            return null;
        }
        if (index < 0 || index > this.size() - 1) {
            return null;
        }
        int idx = (nextFirst + 1 + index) % maxSize;
        return this.items[idx];
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

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() { // 这个Iterator是方法
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> { // 这个Iterator是接口/基类
        private int ptr = 0;
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return ptr < dequeSize;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (! this.hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            T val = get(ptr);
            ptr += 1;
            return val;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayDeque61B objNew) {
            if (this.size() == objNew.size()) {
                for(int idx = 0; idx < this.size(); idx++) {
                    if ( !this.get(idx).equals(objNew.get(idx)) ) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int idx = 0; idx < this.size()-1; idx++) {
            result.append(this.get(idx));
            result.append(", ");
        }
        result.append("]");
        return result.toString();
    }
}
