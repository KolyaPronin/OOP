package ru.nsu.pronin;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс для генерации простых чисел до заданного числа с использованием алгоритма Эратосфена.
 */
public class PrimeNumberGenerator {

    /**
     * Генерирует все простые числа до заданного числа {@code n}.
     *
     * @param n Число, до которого нужно сгенерировать простые числа.
     * @return Список простых чисел, меньших или равных {@code n}.
     */
    public ArrayList<Integer> simpleNums(int n) {
        // Массив, где isPrime[i] будет true, если число i простое
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 и 1 не простые числа

        // Алгоритм Эратосфена для нахождения простых чисел
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false; // Отметить составные числа
                }
            }
        }

        // Формируем список простых чисел
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}
