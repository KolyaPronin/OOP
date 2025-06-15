package ru.nsu.pronin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link HashTable} class.
 */
class HashTableTest {
    private HashTable<String, Number> hashTable;

    /**
     * Initializes a new hash table before each test.
     */
    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>();
    }

    /**
     * Tests basic put and get functionality.
     */
    @Test
    void testPutAndGet() {
        hashTable.put("one", 1);
        assertEquals(1, hashTable.get("one"));
    }

    /**
     * Tests updating an existing key.
     */
    @Test
    void testUpdate() {
        hashTable.put("pi", 3);
        hashTable.update("pi", 3.14);
        assertEquals(3.14, hashTable.get("pi"));
    }

    /**
     * Tests that updating a non-existent key throws an exception.
     */
    @Test
    void testUpdateNonExistingKeyThrows() {
        assertThrows(NoSuchElementException.class, () -> hashTable.update("missing", 42));
    }

    /**
     * Tests removing an entry by key.
     */
    @Test
    void testRemove() {
        hashTable.put("a", 10);
        hashTable.remove("a");
        assertNull(hashTable.get("a"));
    }

    /**
     * Tests the containsKey method.
     */
    @Test
    void testContainsKey() {
        hashTable.put("x", 100);
        assertTrue(hashTable.containsKey("x"));
        assertFalse(hashTable.containsKey("y"));
    }

    /**
     * Tests equality of two hash tables with the same content.
     */
    @Test
    void testEquals() {
        HashTable<String, Number> table1 = new HashTable<>();
        HashTable<String, Number> table2 = new HashTable<>();

        table1.put("one", 1);
        table2.put("one", 1);

        assertEquals(table1, table2);
    }

    /**
     * Tests inequality of two hash tables with different values.
     */
    @Test
    void testNotEquals() {
        hashTable.put("one", 1);
        HashTable<String, Number> other = new HashTable<>();
        other.put("one", 2);
        assertNotEquals(hashTable, other);
    }

    /**
     * Tests the output format of toString.
     */
    @Test
    void testToStringFormat() {
        hashTable.put("key", 123);
        String str = hashTable.toString();
        assertTrue(str.contains("key=123"));
        assertTrue(str.startsWith("{"));
        assertTrue(str.endsWith("}"));
    }

    /**
     * Tests iteration over the entries in the hash table.
     */
    @Test
    void testIteration() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);

        int count = 0;
        for (Map.Entry<String, Number> entry : hashTable) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            count++;
        }
        assertEquals(2, count);
    }

    /**
     * Tests that modification during iteration throws ConcurrentModificationException.
     */
    @Test
    void testConcurrentModificationThrows() {
        hashTable.put("x", 1);
        hashTable.put("y", 2);

        Iterator<Map.Entry<String, Number>> it = hashTable.iterator();
        hashTable.put("z", 3);

        assertThrows(ConcurrentModificationException.class, it::next);
    }

    /**
     * Tests that all values remain after resizing.
     */
    @Test
    void testResizeMaintainsValues() {
        for (int i = 0; i < 100; i++) {
            hashTable.put("key" + i, i);
        }

        for (int i = 0; i < 100; i++) {
            assertEquals(i, hashTable.get("key" + i));
        }

        assertEquals(100, hashTable.size());
    }
}
