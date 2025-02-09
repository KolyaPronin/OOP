package ru.nsu.pronin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Класс для параллельной проверки наличия составных чисел в массиве.
 */
public class ParallelCompositeChecker {

    /**
     * Разделяет массив на подмассивы для параллельной обработки.
     *
     * @param array Массив целых чисел, который нужно разделить.
     * @param countThreads Количество потоков для разделения массива.
     * @return Список подмассивов.
     */
    public List<List<Integer>> divideTheArray(ArrayList<Integer> array, int countThreads) {
        List<List<Integer>> subLists = new ArrayList<>();
        int chunkSize = (int) Math.ceil((double) array.size() / countThreads);

        for (int i = 0; i < array.size(); i += chunkSize) {
            subLists.add(array.subList(i, Math.min(i + chunkSize, array.size())));
        }
        return subLists;
    }

    /**
     * Поток для проверки массива на наличие составных чисел.
     */
    private static class CompositeCheckWorker extends Thread {
        private final List<Integer> subList;
        private final AtomicBoolean hasComposite;

        /**
         * Конструктор для создания потока.
         *
         * @param subList Подсписок чисел для проверки.
         * @param hasComposite Флаг, указывающий, было ли найдено составное число.
         */
        public CompositeCheckWorker(List<Integer> subList, AtomicBoolean hasComposite) {
            this.subList = subList;
            this.hasComposite = hasComposite;
        }

        @Override
        public void run() {
            for (int num : subList) {
                if (hasComposite.get()) {
                    return;
                }
                if (new SingleThreadCompositeChecker().isComposite(num)) {
                    hasComposite.set(true);
                    return;
                }
            }
        }
    }

    /**
     * Параллельно проверяет массив на наличие хотя бы одного составного числа.
     *
     * @param array Массив чисел для проверки.
     * @param countThreads Количество потоков, которые будут использоваться для проверки.
     * @return {@code true}, если в массиве есть составное число, иначе {@code false}.
     * @throws InterruptedException если один из потоков был прерван.
     */
    public boolean parallelHasComposite(
            ArrayList<Integer> array, int countThreads) throws InterruptedException {
        List<List<Integer>> subLists = divideTheArray(array, countThreads);
        AtomicBoolean hasComposite = new AtomicBoolean(false);
        Thread[] threads = new Thread[subLists.size()];

        for (int i = 0; i < subLists.size(); i++) {
            threads[i] = new CompositeCheckWorker(subLists.get(i), hasComposite);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return hasComposite.get();
    }
}
