package ru.nsu.pronin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void Test_1() {
        int[] input_array = {69, 87, 47, 106, 5, 46, 183, 2, 10};
        int[] expected_array = {2, 5, 10, 46, 47, 69, 87, 106, 183};
        int[] output_array = Main.sortArray(input_array);
        assertArrayEquals(expected_array, output_array);
    }

    @Test
    void Test_2() {
        int[] input_array = {-100, -1231, 38, 103, 16, 1, 55, -6, -232, -99528, 308, 262, 232, 153, 5, -69};
        int[] expected_array = {-99528, -1231, -232, -100, -69, -6, 1, 5, 16, 38, 55, 103, 153, 232, 262, 308};
        int[] output_array = Main.sortArray(input_array);
        assertArrayEquals(expected_array, output_array);
    }
}