package ru.nsu.pronin;

import java.util.*;
/**
 * Реализация хэш таблицы.
 *
 * @param <K> - Тип ключей.
 * @param <V> - Тип значений.
 */
public class HashTable<K, V> implements Iterable<OneNode<K, V>> {
    private ArrayList<OneNode<K, V>> ourTable;
    private int tableSize;
    private int nodeSize;
    private int modCount;

<<<<<<< HEAD
    /**
     * Конструктор.
=======
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
>>>>>>> 47aa2b7 (Task_1_2_2)
     */
    public HashTable() {
        ourTable = new ArrayList<>(10);
        tableSize = 10;
        nodeSize = 0;
        modCount = 0;
        for (int i = 0; i < tableSize; i++) {
            ourTable.add(null);
        }
    }

    /**
     * Количество элементов в хэш таблице.
     *
     * @return - количество элементов.
     */
    public int size() {
        return nodeSize;
    }

    /**
<<<<<<< HEAD
     * Решает проблемы с коллизией.
     */
    private void resolveLoadFactor() {
        tableSize = tableSize * 2;
        ArrayList<OneNode<K, V>> oldOurTable = ourTable;
        ourTable = new ArrayList<>(tableSize * 2);
        for (int i = 0; i < tableSize; i++) {
            ourTable.add(null);
        }
        for (OneNode<K, V> node : oldOurTable) {
            while (node != null) {
                int hash = node.getKey().hashCode() % tableSize;
                var newNode = new OneNode<>(node.getKey(), node.getValue());
                newNode.setNext(ourTable.get(hash));
                ourTable.set(hash, newNode);
                node = node.getNext();
=======
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
>>>>>>> 47aa2b7 (Task_1_2_2)
            }
        }
        modCount++;
    }

    /**
<<<<<<< HEAD
     * Смотрит, содержит ли хэш таблица данный ключ.
     *
     * @param key - ключ, который смотрим.
     * @return - true если содержит, иначе false.
     */
    public boolean containsKey(K key) {
        int hash = key.hashCode() % tableSize;
        OneNode<K, V> node = ourTable.get(hash);
        while (node != null) {
            if (node.getKey() == key) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /**
     * Содержит ли какой-нибудь ключ заданное значение.
     *
     * @param value - значение, по которому смотрим.
     * @return - true если содержит, иначе false.
     */
    public boolean containsValue(V value) {
        for (var node : ourTable) {
            for (; node != null; node = node.getNext()) {
                if (node.getValue() == value) {
                    return true;
=======
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
>>>>>>> 47aa2b7 (Task_1_2_2)
                }
            }
        }
        return false;
    }

    /**
     * Ассоциирует новое значение V с ключом K.
     * Либо обновляет значение на новое.
     *
     * @param ourKey   - наш ключ.
     * @param newValue - новое значение.
     * @return возвращает предыдущее значение, если его нет то null.
     */
    public V put(K ourKey, V newValue) {
        int hash = ourKey.hashCode() % tableSize;
        V oldValue = null;

        OneNode<K, V> node = ourTable.get(hash);
        for (; node != null; node = node.getNext()) {
            if (node.getKey() == ourKey) {
                oldValue = node.getValue();
                node.setValue(newValue);
                return oldValue;
            }
        }

        node = new OneNode<>(ourKey, newValue);
        node.setNext(ourTable.get(hash));
        ourTable.set(hash, node);
        nodeSize++;
        modCount++;

        double loadFactor = (double) nodeSize / (double) tableSize;
        if (loadFactor >= 0.7) {
            resolveLoadFactor();
        }
        return oldValue;
    }

    /**
     * Удалят элемент по ключу в хэш таблице.
     *
     * @param key - ключ, который ищем.
     * @return - предыдущее значение по ключу.
     */
    public V remove(K key) {
        if (!containsKey(key)) {
            throw new IllegalArgumentException("Нет значения с таким ключом");
        }
        int hash = key.hashCode() % tableSize;
        OneNode<K, V> node = ourTable.get(hash);
        OneNode<K, V> prev = null;
        V prevValue = null;
        while (node != null) {
            if (node.getKey() == key) {
                prevValue = node.getValue();
                if (prev != null) {
                    prev.setNext(node.getNext());

                } else {
                    ourTable.set(hash, node.getNext());
                }
                nodeSize--;
                modCount++;
                break;
            }
            prev = node;
            node = node.getNext();
        }

        return prevValue;
    }

    /**
     * Выводит значение по ключу.
     *
     * @param key - ключ по, которому ищем.
     * @return значение.
     */
    public V get(K key) {
        if (!containsKey(key)) {
            throw new IllegalArgumentException("Нет значения с таким ключом");
        }
        int hash = key.hashCode() % tableSize;
        OneNode<K, V> node = ourTable.get(hash);
        while (node != null) {
            if (node.getKey() == key) {
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }

    /**
<<<<<<< HEAD
     * Возвращает set все пар ключ = значение.
     *
     * @return set.
     */
    Set<OneNode<K, V>> oneNodeSet() {
        Set<OneNode<K, V>> set = new HashSet<>();
        for (var node : this) {
            set.add(node);
=======
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
>>>>>>> 47aa2b7 (Task_1_2_2)
        }
        return set;
    }

    /**
<<<<<<< HEAD
     * Создает итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator<OneNode<K, V>> iterator() {
        return new Iterator<OneNode<K, V>>() {
            private int iterModCount = modCount;
            private int indexList = 0;
            private OneNode<K, V> cur = null;

            @Override
            public boolean hasNext() {
                if (iterModCount != modCount) {
                    throw new ConcurrentModificationException("Было произведено изменение");
                }
                while (cur == null && indexList < tableSize) {
                    cur = ourTable.get(indexList++);
                }
                return cur != null;
            }

            @Override
            public OneNode<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Нет больше элементов");
                }
                OneNode<K, V> nodeToReturn = cur;
                cur = cur.getNext();
                return nodeToReturn;
=======
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
>>>>>>> 47aa2b7 (Task_1_2_2)
            }

        };
    }

    /**
<<<<<<< HEAD
     * Переводит хэш таблицу в строку.
     *
     * @return - строку.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int counter = 0;
        for (var node : this) {
            if (counter > 0) {
                str.append("\n");
            }
            str.append(node.toString());
            counter++;

=======
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
>>>>>>> 47aa2b7 (Task_1_2_2)
        }
        return str.toString();
    }

    /**
<<<<<<< HEAD
     * Сравнивает две хэш таблицы.
     *
     * @param o - объект, с которым сравниваем.
     * @return - true если равны, иначе false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.ourTable.equals(((HashTable<?, ?>) o).ourTable);
    }
}
=======
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
>>>>>>> 47aa2b7 (Task_1_2_2)
