package com.caparniyazi.ds.maps;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.StringJoiner;

/**
 * Class that implements a skip-list.
 * A skip-list is a list of lists. Each node in a list contains an Entry that contains a key-value pair.
 * The elements in each list ar in increasing order by key.
 * Performance of a skip-list search:
 * <p/>
 * Because the first list we search has the fewest elements (generally one or two) and each lower level
 * list we search has approximately half as many elements as the current list, the search
 * performance is similar to that of a binary search: O(log n), where n is the number of nodes
 * in the skip-list.
 * <p/>
 * To search a skip-list, we start by looking for our target in the highest-level list. This list
 * always has the fewest elements. If the target is in this list, the search is successful.
 * If not, we stop the search in the current list at the element that is the predecessor of the target.
 * Then we continue the search in the list with level one less than the current list, starting where we left
 * off (the predecessor to the target). We continue this process until we either find the target or
 * reach the level 1 list. If the target is not in level 1 list (the list of all elements), then it is
 * not present.
 * <p/>
 * A level m skip-list can effectively hold up to 2^m − 1 items.
 * If we inserted more items without increasing the level, then the O(log n) performance would not be achieved.
 *
 * @param <K> Type of key
 * @param <V> Type of value
 */
public class SkipList<K extends Comparable<K>, V> {
    // Data fields
    private static class Entry<K, V> {
        // Data fields
        private final K key;    // The key
        private V value;    // The value

        /**
         * Creates a new key-value pair.
         *
         * @param key   The key
         * @param value The value.
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key
         *
         * @return The key.
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         *
         * @return The value.
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         *
         * @param value The new value.
         * @return The old value.
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         * Returns a string representation of this entry.
         *
         * @return "(key, value)"
         */
        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    /**
     * Static class to contain the data and the links in a skip-list.
     *
     * @param <K> Type of key.
     * @param <V> Type of value.
     */
    private static class SLNode<K, V> {
        private SLNode<K, V>[] links;
        private final Entry<K, V> item;

        // Create a node of level m.
        @SuppressWarnings("unchecked")
        public SLNode(int m, K key, V value) {
            item = new Entry<>(key, value);
            links = (SLNode<K, V>[]) new SLNode[m]; // Create links.
        }
    }

    private int size;   // The current size of the skip-list.
    private int maxLevel;   // Maximum level, 0-based.
    private int maxCap; // Nominal maximum capacity.
    private final SLNode<K, V> head;    // Dummy(sentinel) node, it contains no data.
    /**
     * RAND is an instance of a Random number generator
     */
    private static final Random RAND = new Random();
    /**
     * LOG2 is the natural Log of 2
     */
    private static final double LOG2 = Math.log(2.0);

    // Constructors
    // Construct an initially empty SkipList.
    public SkipList() {
        size = 0;
        maxLevel = 1;
        maxCap = 1;
        head = new SLNode<>(maxLevel, null, null);
    }

    /**
     * Search for an item in the list.
     * <p/>
     * Since insertion involves the same algorithm as searching to find the insertion point,
     * we define a common method, search, which will return an array pred of references to the SLNodes at
     * each level that was last examined in the search.
     *
     * @param target The key being sought.
     * @return A SLNode array which references the predecessors of the target at each level (in each list).
     */
    @SuppressWarnings("unchecked")
    private SLNode<K, V>[] search(K target) {
        var pred = (SLNode<K, V>[]) new SLNode[maxLevel];
        var current = head;

        // The for loop ensures that each list is processed beginning with the highest-level list.
        for (int i = current.links.length - 1; i >= 0; i--) {

            // The while loop advances the current down the level "i" list until current
            // references the last node in the list or current references a node
            // that is linked to either target or to the first element greater than target.
            while (current.links[i] != null &&
                    current.links[i].item.key.compareTo(target) < 0) {
                current = current.links[i];
            }

            pred[i] = current;
        }
        return pred;
    }

    /**
     * Retrieve the value field of the object in the skip-list with key matching target.
     *
     * @param target The key of the item being sought.
     * @return The old value of the object in the skip-list whose key matches target.
     * If not found, return null.
     */
    public V get(K target) {
        // Cal search() to get array of predecessors.
        var pred = search(target);

        if (pred[0].links[0] != null && pred[0].links[0].item.key.compareTo(target) == 0) {
            return pred[0].links[0].item.getValue();
        } else {
            return null;
        }
    }

    /**
     * Inserts an item in the skip-list.
     * If the key is found in the skip-list, change its value.
     * <p/>
     * On average, the time for search and insertion will be O(log n).
     *
     * @param target The key being sought.
     * @param value  The value for the key.
     * @return old value if key found; else, return null.
     */
    public V put(K target, V value) {
        Objects.requireNonNull(target, "Key must not be null");
        // Cal search() to get array of predecessors.
        var pred = search(target);

        // Check the item reached in the level 1 list
        if (pred[0].links[0] != null
                && pred[0].links[0].item.key.compareTo(target) == 0) { // target matches a key in the list
            // update its value and return the old value
            return pred[0].links[0].item.setValue(value);
        } else {
            /* The target was not found, insert new item into the skip list.
             * Before inserting the new item, the value of size is incremented.
             * Recall that a skip-list of level m has a max. capacity(maxCap) of 2^m-1.
             */
            SLNode<K, V> newNode;   // New node to be inserted.
            int levelNewNode; // Level of new node.

            // Increment size and check for a full skip-list.
            size++;

            if (size > maxCap) {
                maxLevel++;
                maxCap = computeMaxCap(maxLevel);
                head.links = Arrays.copyOf(head.links, maxLevel);
                pred = Arrays.copyOf(pred, levelNewNode = maxLevel);
                pred[maxLevel - 1] = head;  // The last element of pred is set to reference head.
            } else {
                levelNewNode = logRandom(); // Set level randomly.
            }

            // Next, we store the new node's data and splice it into the skip list.
            newNode = new SLNode<>(levelNewNode, target, value);
            for (int i = 0; i < levelNewNode; i++) {
                newNode.links[i] = pred[i].links[i];
                pred[i].links[i] = newNode;
            }

            return null;    // New node inserted.
        }   // else end.
    }   // end put.

    /**
     * Method to generate a logarithmic distributed integer
     * between 1 and maxLevel. i.e., 1/2 of the values returned
     * are 1, 1/4 are 2, 1/8 are 3, etc.
     *
     * @return a random logarithmic distributed int between 1 and maxLevel
     */
    private int logRandom() {
        // Returns a uniformly distributed random int from 0 up to, but not
        // including, n.
        int r = RAND.nextInt(maxCap);

        int k = (int) (Math.log(r + 1) / LOG2);

        if (k > maxLevel - 1) {
            k = maxLevel - 1;
        }
        return maxLevel - k;
    }

    /**
     * Method to compute the maximum capacity, given the maximum
     * level. It computes Math.pow(2, maxLevel) - 1, using shift.
     *
     * @return Math.pow(2, maxLevel + 1) - 1;
     */
    private int computeMaxCap(int maxLevel) {
        return ~(~0 << maxLevel);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Return a simple string representation (level 0 traversal).
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        SLNode<K, V> curr = head.links[0];

        while (curr != null) {
            sj.add(curr.item.key + "=" + curr.item.value);
            curr = curr.links[0];
        }
        return sj.toString();
    }

    /**
     * Prints all levels.
     */
    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        for (int i = maxLevel - 1; i >= 0; i--) {
            sb.append("Level ").append(i).append(": ");
            SLNode<K, V> curr = head.links[i];

            while (curr != null) {
                sb.append(curr.item.key).append(" ");
                curr = curr.links[i];
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
