package ru.nsu.pronin;

import java.util.List;
import java.util.stream.Stream;

/**
 * Класс для параллельной проверки массива чисел на наличие составных.
 * Использует параллельные потоки (parallelStream).
 */
public class ParallelStreamCompositeChecker {

    /**
     * Проверяет, содержит ли массив хотя бы одно составное число, используя параллельный поток.
     *
     * @param array список чисел для проверки.
     * @return {@code true}, если найдено хотя бы одно составное число, иначе {@code false}.
     */
    public boolean parallelStreamCompositeChecker(List<Integer> array) {
        Stream<Integer> streamArray = array.parallelStream();
        return streamArray.anyMatch(num -> new SingleThreadCompositeChecker().isComposite(num));
    }
}
