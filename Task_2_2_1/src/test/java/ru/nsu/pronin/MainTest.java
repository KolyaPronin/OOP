package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Тест для Main.main().
 */
class MainTest {

    /**
     * Тест для main()
     */
    @Test
    void mainTest() {

        try {
            Main.main(null);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        assertTrue(true);
    }
}
