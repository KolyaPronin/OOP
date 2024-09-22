package ru.nsu.pronin;

import java.util.Scanner;


import static ru.nsu.pronin.HeapSort.sortArray;

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
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = in.nextInt();
        }
        int[] sortedArray = sortArray(array);

        for (int i = 0; i < N; i++) {
            System.out.print(sortedArray[i] + " ");
        }
    }
}
