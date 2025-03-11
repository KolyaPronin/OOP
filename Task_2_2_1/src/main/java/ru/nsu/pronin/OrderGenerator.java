package ru.nsu.pronin;

import java.util.PriorityQueue;

/**
 * Класс {@link OrderGenerator} который реализует генерацию заказов для очереди.
 */
public class OrderGenerator {

    /**
     * Метод, генерирующий заказы для очереди.
     *
     * @param order      - очередь заказов куда будут помещаться сгенерированные заказы.
     * @param orderCount - количество заказов, которые требуется сгенерировать.
     */
    public static void orderGenerator(PriorityQueue<Order> order, int orderCount) {
        for (int i = 0; i < orderCount; i++) {
            Order elementOfQueue = new OrderImpl(i, "pizza", "заказана");
            order.add(elementOfQueue);
        }
    }
}
