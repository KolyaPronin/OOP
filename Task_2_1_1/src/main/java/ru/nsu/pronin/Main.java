package ru.nsu.pronin;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Главный класс для запуска программы.
 */
public class Main {
 /**
 * @param args аргументы командной строки (не используются)
 * @throws InterruptedException если выполнение потоков прерывается
 */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите число массивов: ");
        Scanner scanner = new Scanner(System.in);
        int arrayCount = scanner.nextInt();
        int arraySize = 1000;
        double compositeProbability = 1;
        ArrayList<ArrayList<Integer>> testArrays = new GenerateArraysForTests()
                .generateArrays(arrayCount, arraySize, compositeProbability);
        // Последовательный метод
        double startTimeMethod1 = System.currentTimeMillis();
        boolean answer = new SingleThreadCompositeChecker().simpleNumber(testArrays.get(0));
        System.out.println(answer);
        double endTimeMethod1 = System.currentTimeMillis();
        System.out.printf("%.5f%n", (endTimeMethod1 - startTimeMethod1) / 1000.0);
        // Параллельный метод Threads
        System.out.println("Введите число потоков");
        int numThreads = scanner.nextInt();
        double startTimeMethod2 = System.currentTimeMillis();
        ParallelCompositeChecker parallelDecision = new ParallelCompositeChecker();
        boolean hasComposite = parallelDecision
                .parallelHasComposite(testArrays.get(1), numThreads);
        System.out.println("Threads: " + hasComposite);
        double endTimeMethod2 = System.currentTimeMillis();
        System.out.printf("%.5f%n", (endTimeMethod2 - startTimeMethod2) / 1000.0);
        // Параллельный метод с использованием ParallelStream
        double startTimeMethod3 = System.currentTimeMillis();
        boolean answer1 = new ParallelStreamCompositeChecker()
                .parallelStreamCompositeChecker(testArrays.get(2));
        System.out.println("parallel stream:" + answer1);
        double endTimeMethod3 = System.currentTimeMillis();
        System.out.printf("%.5f%n", (endTimeMethod3 - startTimeMethod3) / 1000.0);
    }
}
