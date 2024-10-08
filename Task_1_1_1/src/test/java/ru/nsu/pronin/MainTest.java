package ru.nsu.pronin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testSmallNumbers() {
        int[] inputArray = {
                69, 87, 47, 106, 5, 46, 183, 2, 10
        };
        int[] expectedArray = {
                2, 5, 10, 46, 47, 69, 87, 106, 183
        };
        int[] outputArray = HeapSort.sortArray(inputArray);
        assertArrayEquals(expectedArray, outputArray);
    }

    @Test
    void testLargeNumbers() {
        int[] inputArray = {
                -100, -1231, 38, 103, 16, 1, 55, -6, -232, -99528, 308, 153, 5, -69
        };
        int[] expectedArray = {
                -99528, -1231, -232, -100, -69, -6, 1, 5, 16, 38, 55, 103, 153, 308
        };
        int[] outputArray = HeapSort.sortArray(inputArray);
        assertArrayEquals(expectedArray, outputArray);
    }

    @Test
    void testMainClass(){
        Main.main(null);
        assertTrue(true);
    }
}
