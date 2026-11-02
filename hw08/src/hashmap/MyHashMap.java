package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author CYQ
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int N; // the number of items.
    private int M; // the number of buckets.
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        /*N = 0;
        M = DEFAULT_CAPACITY;
        loadFactor = DEFAULT_LOADFACTOR;
        buckets = new Collection[DEFAULT_CAPACITY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }*/
        this(16, 0.75);
    }

    public MyHashMap(int initialCapacity) {
        /*N = 0;
        M = initialCapacity;
        loadFactor = DEFAULT_LOADFACTOR;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }*/
        this(initialCapacity, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        N = 0;
        M = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = (Collection<Node>[]) new Collection[initialCapacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    // Your code won't compile until you do so!
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
        // if contains key, update the value.
        Node target = findHelper(key);
        if (target != null) {
            // Modify the value.
            target.value = value;
            return; // Notice: must return.
        }
        // compute index
        int index = Math.floorMod(key.hashCode(), this.M);
        // node(k, v)
        Node nodeKV = new Node(key, value);
        buckets[index].add(nodeKV);
        N += 1; // size up!
        this.resize();
    }

    private Node findHelper(K key) {
        int index = Math.floorMod(key.hashCode(), M);
        for (Node item: buckets[index]) {
            if (item.key.equals(key)) {
                return item;
            }
        }
        return null;
    }

    private void resize() {
        double curFactor = N * 1.0 / M;
        if (curFactor > loadFactor) { // resize
            // 扩容
            int newSize = M * 2;
            Collection[] newBuckets = new Collection[newSize];
            for (int i = 0; i < newBuckets.length; i++) {
                newBuckets[i] = createBucket();
            }
            // rehash and put in.
            for (int i = 0; i < buckets.length; i++) {
                for (Node item: buckets[i]) {
                    int newIndex = Math.floorMod(item.key.hashCode(), newSize);
                    newBuckets[newIndex].add(item);
                }
            }
            M = newSize;
            this.buckets = newBuckets;
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
        /*// compute the hashcode and index in buckets
        int index = Math.floorMod(key.hashCode(),this.M);
        // search the node object.
        for (Node item: buckets[index]) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }*/
        Node target = findHelper(key);
        if (target != null) {
            return target.value;
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        /*int index = Math.floorMod(key.hashCode(), this.M);
        // search for the node whose key equals the specific key.
        for (Node item: buckets[index]) {
            if (item.key.equals(key)) {
                return true;
            }
        }*/
        Node target = findHelper(key);
        if (target != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return this.N;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        this.N = 0;
        // 把每个桶变为空桶而不是null
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
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
