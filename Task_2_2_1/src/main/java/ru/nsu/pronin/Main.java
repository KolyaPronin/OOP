package ru.nsu.pronin;

import java.util.PriorityQueue;

/**
 * Главный класс запуска программы
 */
public class Main {

    /**
     * @param args аргументы командной строки (не используются)
     * @throws InterruptedException если выполнение потоков прерывается
     */
    public static void main(String[] args) throws InterruptedException {

        PriorityQueue<Order> order = new GeneralQueueOfOrders().orders;
        OrderGenerator.orderGenerator(order, 10);

        WorkerGenerator.workerBakerGenerator(order, 5);
        WorkerGenerator.workerBagManGenerator(5);

        for (int i = 0; i < 5; i++) { // 5 дней 2-ое выходных
            int workingTimeOfPizzeria = 10000;
            Thread.sleep(workingTimeOfPizzeria);

            OpeningHours.closePizzeria();
            Thread.sleep(10000); // работники доделывают текущие заказы и завершают рабочий день
            OpeningHours.openPizzeria();
        }
    }
}
