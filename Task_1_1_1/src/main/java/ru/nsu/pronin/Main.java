package ru.nsu.pronin;

/**
 * The main program class that implements array sorting using the heap.
 */
public class Main {
    /**
     * The main method of the program.
     * Performs reading an array of numbers, sorting the array and outputting the sorted numbers.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] sortedArray = HeapSort.sortArray(array);
        for (int i = 0; i < 9; i++) {
            System.out.print(sortedArray[i] + " ");
        }
    }
}
