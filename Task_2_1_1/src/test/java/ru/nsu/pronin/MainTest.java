package ru.nsu.pronin;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для проверки работы методов в классе {@link Main}.
 */
class MainTest {

    /**
     * Тест для первого метода на небольшом массиве с составными числами.
     */
    @Test
    void smallArrayFirstTestFirstMethod() {
        List<List<Integer>> smallArray = new GenerateArraysForTests()
                .generateArrays(1, 10, 1);
        boolean answer = new SingleThreadCompositeChecker()
                .simpleNumber(smallArray.get(0));
        boolean expectedAnswer = true;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для первого метода на небольшом массиве без составных чисел.
     */
    @Test
    void smallArraySecondTestFirstMethod() {
        List<List<Integer>> smallArray = new GenerateArraysForTests()
                .generateArrays(1, 10, 0.0);
        boolean answer = new SingleThreadCompositeChecker()
                .simpleNumber(smallArray.get(0));
        boolean expectedAnswer = false;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для первого метода на среднем массиве без составных чисел.
     */
    @Test
    void middleArrayFirstTestFirstMethod() {
        List<List<Integer>> middleArray = new GenerateArraysForTests()
                .generateArrays(1, 100, 0.0);
        boolean answer = new SingleThreadCompositeChecker()
                .simpleNumber(middleArray.get(0));
        boolean expectedAnswer = false;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для первого метода на среднем массиве с составными числами.
     */
    @Test
    void middleArraySecondTestFirstMethod() {
        List<List<Integer>> middleArray = new GenerateArraysForTests()
                .generateArrays(1, 100, 1);
        boolean answer = new SingleThreadCompositeChecker()
                .simpleNumber(middleArray.get(0));
        boolean expectedAnswer = true;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для второго метода на большом массиве с составными числами, используя 10 потоков.
     *
     * @throws InterruptedException если выполнение потоков прерывается
     */
    @Test
    void bigArrayFirstTestSecondMethod() throws InterruptedException {
        List<List<Integer>> bigArray = new GenerateArraysForTests()
                .generateArrays(1, 1000, 1.0);
        boolean answer = new ParallelCompositeChecker()
                .parallelHasComposite(bigArray.get(0), 10);
        boolean expectedAnswer = true;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для второго метода на большом массиве без составных чисел, используя 10 потоков.
     *
     * @throws InterruptedException если выполнение потоков прерывается
     */
    @Test
    void bigArraySecondTestSecondMethod() throws InterruptedException {
        List<List<Integer>> bigArray = new GenerateArraysForTests()
                .generateArrays(1, 1000, 0.0);
        boolean answer = new ParallelCompositeChecker()
                .parallelHasComposite(bigArray.get(0), 10);
        boolean expectedAnswer = false;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для третьего метода на большом массиве с составными числами.
     */
    @Test
    void bigArrayFirstTestThirdMethod() {
        List<List<Integer>> bigArray =
                new GenerateArraysForTests()
                        .generateArrays(1, 1000, 1.0);
        boolean answer = new ParallelStreamCompositeChecker()
                .parallelStreamCompositeChecker(bigArray.get(0));
        boolean expectedAnswer = true;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для третьего метода на большом массиве без составных чисел.
     */
    @Test
    void bigArraySecondTestThirdMethod() {
        List<List<Integer>> bigArray =
                new GenerateArraysForTests()
                        .generateArrays(1, 1000, 0.0);
        boolean answer = new ParallelStreamCompositeChecker()
                .parallelStreamCompositeChecker(bigArray.get(0));
        boolean expectedAnswer = false;
        assertEquals(expectedAnswer, answer);
    }

    /**
     * Тест для метода {@link Main#main(String[])}. Проверяется работа программы с вводом.
     *
     * @throws InterruptedException если выполнение потоков прерывается
     */
    @Test
    void mainTest() throws InterruptedException {

        String input = "10\n2";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            Main.main(null);
        } finally {
            System.setIn(originalSystemIn);
        }


        assertTrue(true);
    }
}
