package ru.nsu.pronin;

import java.util.ArrayList;
import java.util.Random;

/**
 * Класс для генерации тестовых массивов чисел.
 * Создает несколько массивов случайных простых чисел с возможностью добавления составных чисел.
 */
public class GenerateArraysForTests {

    private static final int MAX_PRIME = 30_000_000;

    /**
     * Генерирует список массивов, заполненных случайными простыми числами.
     * В каждый массив с вероятностью compositeProbability вставляется одно составное число.
     *
     * @return список из arrayCount массивов случайных чисел.
     */
    public ArrayList<ArrayList<Integer>> generateArrays(
            int arrayCount, int arraySize,double compositeProbability) {
        Random random = new Random();
        ArrayList<ArrayList<Integer>> testArrays = new ArrayList<>();
        ArrayList<Integer> primes = new PrimeNumberGenerator().simpleNums(MAX_PRIME);

        for (int i = 0; i < arrayCount; i++) {
            ArrayList<Integer> testArray = new ArrayList<>();

            // Заполняем массив случайными простыми числами
            for (int j = 0; j < arraySize; j++) {
                int randomIndex = random.nextInt(primes.size());
                testArray.add(primes.get(randomIndex));
            }

            // С вероятностью COMPOSITE_PROBABILITY заменяем одно число на составное
            if (random.nextDouble() < compositeProbability) {
                int replaceIndex = random.nextInt(testArray.size());
                testArray.set(replaceIndex, random.nextInt(999_998) + 2);
            }

            testArrays.add(testArray);
        }

        return testArrays;
    }
}
