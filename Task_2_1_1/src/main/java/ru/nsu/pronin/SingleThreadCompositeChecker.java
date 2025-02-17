package ru.nsu.pronin;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для проверки чисел на составность с использованием одного потока.
 */
public class SingleThreadCompositeChecker {

    /**
     * Проверяет, есть ли в массиве хотя бы одно составное число.
     *
     * @param array Массив целых чисел для проверки.
     * @return {@code true}, если хотя бы одно число составное, иначе {@code false}.
     */
    public boolean simpleNumber(List<Integer> array) {
        for (int num : array) {
            if (isComposite(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверяет, является ли число составным.
     *
     * @param num Число для проверки.
     * @return {@code true}, если число составное, иначе {@code false}.
     */
    public boolean isComposite(int num) {
        if (num <= 1) {
            return false;
        }

        // Генерация простых чисел до квадратного корня из num
        ArrayList<Integer> primes = new PrimeNumberGenerator().simpleNums((int) Math.sqrt(num));

        for (int prime : primes) {
            if (prime * prime > num) {
                break;
            }
            if (num % prime == 0) {
                return true; // Нашли делитель - число составное
            }
        }

        return false; // Если не нашли делителей - число простое
    }
}
