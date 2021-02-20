import java.util.HashMap;
import java.util.Map;

/**
 * Hashtable to store key, Node
 * Node class has: value, frequency
 * Another hashmap to store freq, LinkedList of nodes
 */
public class LFUCache {
    int capacity;
    int minFrequency;
    int numItems;

    Map<Integer, Item> store;
    Map<Integer, DoublyLinkedList> frequencyBuckets;

    private class Item {
        public int key;
        public int val;
        public int frequency;
        public boolean isDummy;
        public Item prev;
        public Item next;

        public Item(int key, int val, int frequency) {
            this.key = key;
            this.val = val;
            this.frequency = frequency;
            this.isDummy = false;
        }

        private void setDummy(boolean isDummy) {
            this.isDummy = isDummy;
        }
    }

    private class DoublyLinkedList {
        Item root;
        Item tail;

        public DoublyLinkedList() {
            root = new Item(0, 0, 0);
            root.isDummy = true;
            tail = root;
        }

        public void push(Item item) {
            item.prev = tail;
            tail.next = item;
            tail = item;
        }

        public Item poll() {
            Item first = root.next;

            if (first != null) {
                root.next = first.next;
                store.remove(first.key);
            }

            return first;
        }

        public void remove(Item item) {
            item.prev.next = item.next;
        }
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        store = new HashMap<>();
        frequencyBuckets = new HashMap<>();
        minFrequency = Integer.MAX_VALUE;
        numItems = 0;
    }

    /**
     * Ping store. If key exists:
     * - update frequency
     * - return value
     */
    public int get(int key) {
        if (!store.containsKey(key)) {
            return -1;
        }

        Item item = store.get(key);
        updateFrequency(item);

        return item.val;
    }

    // Remove item from current list
    // item.frequency++
    // Move to next frequency
    private void updateFrequency(Item item) {
        if (!frequencyBuckets.containsKey(item.frequency)) {
            throw new RuntimeException("[Error]: Missing bucket for frequency" + item.frequency); // String.format()
        }

        DoublyLinkedList items = frequencyBuckets.get(item.frequency);
        items.remove(item);

        if (item.frequency == minFrequency && items.root.next == null) {
            minFrequency++;
        }

        item.frequency++;

        if (!frequencyBuckets.containsKey(item.frequency)) {
//            DoublyLinkedList newList = new DoublyLinkedList();
//            frequencyBuckets.put(item.frequency, newList);

            initFrequencyList(item.frequency);
        }

        items = frequencyBuckets.get(item.frequency);
        items.push(item);
    }

    private DoublyLinkedList initFrequencyList(int frequency) {
        DoublyLinkedList newList = new DoublyLinkedList();
        frequencyBuckets.put(frequency, newList);

        return newList;
    }

    // If not capacity or existing item
    // - Update value
    // - Update frequency
    // If capacity and new item
    // - Evict min frequency
    // - update value
    // - add to freq 1
    public void put(int key, int value) {
        if (store.containsKey(key)) {
            Item item = store.get(key);
            item.val = value;
            updateFrequency(item);
        } else {
            if (numItems >= capacity) {
                evictLFUItem();
            }

            Item newItem = new Item(key, value, 1);
            addNewItem(key, newItem);
        }
    }

    private void evictLFUItem() {
        DoublyLinkedList items = frequencyBuckets.get(minFrequency);
//        items.remove(items.root.next);
        Item lfuITem = items.poll();
        System.out.println("Removed Item: " + lfuITem.val);

        numItems--;
    }

    private void addNewItem(int key, Item item) {
        store.put(key, item);

        DoublyLinkedList items = frequencyBuckets.get(1);
        if (items == null) {
            items = initFrequencyList(1);
        }

        items.push(item);
        minFrequency = 1;
        numItems++;
    }
}
