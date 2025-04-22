package ru.nsu.pronin;

import java.util.*;

/**
 * A simple implementation of a hash table that supports constant-time access by key.
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class HashTable<K, V> implements Iterable<Map.Entry<K, V>> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private int capacity;
    private int modCount = 0;

    /**
     * Creates an empty hash table.
     */
    public HashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.table = new LinkedList[capacity];
        this.size = 0;
    }

    private int index(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Inserts a key-value pair into the table. Replaces value if key exists.
     *
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        int idx = index(key);
        if (table[idx] == null) {
            table[idx] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[idx]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        table[idx].add(new Entry<>(key, value));
        size++;
        modCount++;

        if ((float) size / capacity >= LOAD_FACTOR) {
            resize();
        }
    }

    /**
     * Returns the value associated with the key, or null if not found.
     *
     * @param key the key
     * @return the associated value or null
     */
    public V get(K key) {
        int idx = index(key);
        if (table[idx] != null) {
            for (Entry<K, V> entry : table[idx]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    /**
     * Updates the value for an existing key.
     *
     * @param key   the key
     * @param value the new value
     * @throws NoSuchElementException if key is not found
     */
    public void update(K key, V value) {
        int idx = index(key);
        if (table[idx] != null) {
            for (Entry<K, V> entry : table[idx]) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    modCount++;
                    return;
                }
            }
        }
        throw new NoSuchElementException("Key not found: " + key);
    }

    /**
     * Checks if a key is present in the table.
     *
     * @param key the key
     * @return true if the key exists
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Removes a key and its value from the table.
     *
     * @param key the key to remove
     */
    public void remove(K key) {
        int idx = index(key);
        if (table[idx] != null) {
            Iterator<Entry<K, V>> it = table[idx].iterator();
            while (it.hasNext()) {
                Entry<K, V> entry = it.next();
                if (entry.key.equals(key)) {
                    it.remove();
                    size--;
                    modCount++;
                    return;
                }
            }
        }
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return the number of entries
     */
    public int size() {
        return size;
    }

    private void resize() {
        capacity *= 2;
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[capacity];
        size = 0;

        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    /**
     * Returns an iterator over the table entries.
     * Throws ConcurrentModificationException if modified during iteration.
     *
     * @return iterator over entries
     */
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<>() {
            int bucketIdx = 0;
            Iterator<Entry<K, V>> bucketIterator = null;
            final int expectedModCount = modCount;

            private void advance() {
                while ((bucketIterator == null || !bucketIterator.hasNext()) && bucketIdx < table.length) {
                    bucketIterator = table[bucketIdx] != null ? table[bucketIdx].iterator() : null;
                    bucketIdx++;
                }
            }

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                advance();
                return bucketIterator != null && bucketIterator.hasNext();
            }

            @Override
            public Map.Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return bucketIterator.next();
            }
        };
    }

    /**
     * Checks equality with another hash table.
     *
     * @param o the object to compare
     * @return true if tables are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HashTable)) {
            return false;
        }
        HashTable<?, ?> other = (HashTable<?, ?>) o;
        if (this.size != other.size) {
            return false;
        }
        for (Map.Entry<K, V> entry : this) {
            if (!Objects.equals(entry.getValue(), ((HashTable<K, V>) other).get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string representation of the table.
     *
     * @return string of entries
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        Iterator<Map.Entry<K, V>> it = iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.append("}").toString();
    }

    /**
     * Entry holds a key-value pair.
     *
     * @param <K> key type
     * @param <V> value type
     */
    private static class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }

    /**
     * Example usage.
     *
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        HashTable<String, Number> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.update("one", 1.0);
        System.out.println(hashTable.get("one")); // 1.0
    }
}
