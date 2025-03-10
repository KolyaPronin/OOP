package ru.nsu.pronin;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Генерирует работников (пекарей и бегунков) в отдельных потоках.
 */
public class WorkerGenerator {

    /**
     * Создаёт пекарей и запускает их в потоках.
     *
     * @param order Очередь заказов.
     * @param countOfWorker Количество пекарей.
     */
    public static void workerBakerGenerator(PriorityQueue<Order> order, int countOfWorker) {
        Random random = new Random();
        int randomTime = random.nextInt(1000, 4000);
        for (int i = 0; i < countOfWorker; i++) {
            Baker baker = new Baker(i, randomTime, order);
            BakerParallel worker = new BakerParallel(baker);
            new Thread(worker).start();
        }
    }

    /**
     * Создаёт бегунков и запускает их в потоках.
     *
     * @param countOfWorker Количество бегунков.
     */
    public static void workerBagManGenerator(int countOfWorker) {
        Random random = new Random();
        int randomTime = random.nextInt(1000, 4000);
        int randomCapacity = random.nextInt(1, 3);
        for (int i = 0; i < countOfWorker; i++) {
            BagMan bagMan = new BagMan(i, randomCapacity, randomTime);
            BagManParallel bagManWorker = new BagManParallel(bagMan);
            new Thread(bagManWorker).start();
        }
    }
}
