package ru.nsu.pronin.process;

import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.people.BagMan;
import ru.nsu.pronin.people.BagManWorker;
import ru.nsu.pronin.people.Baker;
import ru.nsu.pronin.people.BakerParallel;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Генерирует работников (пекарей и бегунков) в отдельных потоках.
 */
public class WorkerGenerator {
    private static final Random random = new Random();

    /**
     * Создаёт пекарей и запускает их в потоках.
     *
     * @param orderQueue         Очередь заказов.
     * @param countOfWorker Количество пекарей.
     */
    public static void generateBaker(GeneralQueueOfOrders orderQueue, int countOfWorker) {
        int randomTime = random.nextInt(1000, 4000);
        for (int i = 0; i < countOfWorker; i++) {
            Baker baker = new Baker(i, randomTime, orderQueue);
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
            BagManWorker bagManWorker = new BagManWorker(bagMan);
            new Thread(bagManWorker).start();
        }
    }
}
