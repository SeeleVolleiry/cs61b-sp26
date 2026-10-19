import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode { // 一个BST类的实例，存储键值对 ——> 先有一个BST类
        K key;
        V val;
        BSTNode left;
        BSTNode right;

        private BSTNode(K k, V value) {
            key = k;
            val = value;
            left = null;
            right = null;
        }
    }

    private BSTNode root;
    private int size; // size，存储大小
    // constructor function is not needed, for there is a put() method.

    public static void main(String[] args) {
        BSTMap<String, Integer> expr = new BSTMap<>();
        expr.put("d", 4);
        System.out.println(expr.root.key);
        System.out.println(expr.size);
        expr.put("a", 1);
        System.out.println(expr.root.key);
        System.out.println(expr.size);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) {
            size += 1;
            return new BSTNode(key, value);
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            node.val =value;
            return node;
        } else if (cmp < 0) {
            node.right = put(node.right, key, value);
            return node;
        } else {
            node.left =  put(node.left, key, value);
            return node;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(this.root, key);
    }

    private V get(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node.val;
        } else if (cmp < 0) {
            return get(node.right, key);
        } else {
            return get(node.left, key);
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {

    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
