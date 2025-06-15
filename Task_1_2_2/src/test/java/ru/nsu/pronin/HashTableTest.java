package ru.nsu.pronin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

/**
 * Unit tests for the {@link HashTable} class.
 */
class HashTableTest {

    HashTable<Integer, String> table;

    /**
     * Initializes a new hash table before each test.
     */
    @BeforeEach
    void before() {
        table = new HashTable<>();
    }

    /**
     * Tests basic put and get functionality.
     */
    @Test
    void putOneKeyOneValue() {
        table.put(1, "one");
        assertTrue(table.containsKey(1));
    }

    /**
     * Tests updating an existing key.
     */
    @Test
    void putOneKeyTwoValue() {
        table.put(1, "one");

        assertTrue(table.containsKey(1));
        String prev = table.put(1, "one one");
        assertTrue(table.containsValue("one one"));
        assertEquals(prev, "one");
        assertFalse(table.containsValue("one"));
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
    void testEqualsSame() {
        table.put(1, "one");
        table.put(11, "two");
        table.put(2, "three");
        assertTrue(table.equals(table));
    }

    /**
     * Tests the containsKey method.
     */
    @Test
    void testEqualsNull() {
        table.put(1, "one");
        table.put(11, "two");
        table.put(2, "three");
        HashTable<Integer, String> compared = null;
        assertFalse(table.equals(compared));
    }

    /**
     * Tests equality of two hash tables with the same content.
     */
    @Test
    void testEqualsDiffTrue() {
        table.put(1, "one");
        table.put(11, "two");
        table.put(2, "three");
        HashTable<Integer, String> compared = new HashTable<>();
        compared.put(1, "one");
        compared.put(11, "two");
        compared.put(2, "three");

        assertTrue(table.equals(compared));
    }

    /**
     * Tests inequality of two hash tables with different values.
     */
    @Test
    void testEqualsDiffFalse() {
        table.put(1, "one");
        table.put(11, "two");
        table.put(2, "three");
        HashTable<Integer, String> compared = new HashTable<>();
        compared.put(1, "one");
        compared.put(11, "two");

        assertFalse(table.equals(compared));
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
