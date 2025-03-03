package ru.nsu.pronin;

/**
 * Класс для вычисления Z-функции строки.
 * <p>
 * Z-функция строки — это массив, где каждый элемент на позиции i
 * показывает наибольшую длину префикса строки, который совпадает с суффиксом,
 * начинающимся с позиции i.
 * </p>
 */
public class Zfunction {

    /**
     * Вычисляет Z-функцию для переданной строки.
     * <p>
     * Z-функция строится с помощью алгоритма, который за время O(n)
     * вычисляет массив Z-значений для строки. Каждый элемент массива Z
     * указывает на длину максимального префикса строки, совпадающего с суффиксом,
     * начиная с данного индекса.
     * </p>
     *
     * @param array строка, для которой нужно вычислить Z-функцию
     * @return массив Z-функции, где каждый элемент представляет длину максимального
     *         префикса, совпадающего с суффиксом начиная с индекса i
     */
    public int[] zFunk(String array) {
        // Преобразуем строку в массив символов
        char[] charArray = array.toCharArray();
        int len = charArray.length;
        int[] zArray = new int[len];

        // Алгоритм для вычисления Z-функции
        for (int i = 1, l = 0, r = 0; i < len; i++) {
            if (i <= r) {
                // Если i в пределах правого интервала, используем предыдущие вычисления
                zArray[i] = Math.min(r - i + 1, zArray[i - l]);
            }
            // Пробегаем по строке, расширяя текущий префикс
            while (i + zArray[i] < len && charArray[zArray[i]] == charArray[i + zArray[i]]) {
                zArray[i]++;
            }

            // Если нашли более длинный суффикс, обновляем границы интервала
            if (i + zArray[i] - 1 > r) {
                r = i + zArray[i] - 1;
                l = i;
            }
        }

        return zArray;
    }
}
