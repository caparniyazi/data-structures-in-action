package codingpractice;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache implementation.
 * Head: MRU
 * Tail: LRU
 * <p>
 * <p/>
 * Capacity is limited.
 * LRU item is removed first.
 * put & get operations are O(1).
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        System.out.println(cache);

        cache.put(2, 2);
        System.out.println(cache);

        System.out.println("get(1): " + cache.get(1));
        System.out.println(cache);

        cache.put(3, 3); // evicts key 2
        System.out.println(cache);

        System.out.println("get(2): " + cache.get(2));
        System.out.println(cache);

        cache.put(4, 4); // evicts key 1
        System.out.println(cache);

        System.out.println("get(1): " + cache.get(1));
        System.out.println("get(3): " + cache.get(3));
        System.out.println("get(4): " + cache.get(4));

        System.out.println(cache);
    }

    // Data fields
    class Node {
        int key, value;
        Node prev, next;

        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    @Getter
    private Map<Integer, Node> map;
    private Node head, tail;

    // Constructor
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    // Methods
    // To achieve O(1), we need HashMap (lookup) + Double Linked List (Most recent -> front, least recent -> tail)
    // O(1)
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        // Find node in map, move node to front, return value.
        Node node = map.get(key);
        remove(node);
        insertToFront(node);

        return node.value;
    }

    // O(1)
    public void put(int key, int value) {
        // If key exists, update + move to front.
        if (map.containsKey(key)) {
            remove(map.get(key));
        }

        // If new, add to front.
        Node node = new Node(key, value);
        insertToFront(node);
        map.put(key, node);

        // If over capacity, remove tail.
        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = head.next;

        sb.append("Cache: [");

        while (curr != tail) {
            sb.append("(").append(curr.key).append(":").append(curr.value).append(")");
            curr = curr.next;
            if (curr != tail) sb.append(" -> ");
        }

        sb.append("]");
        return sb.toString();
    }
}
