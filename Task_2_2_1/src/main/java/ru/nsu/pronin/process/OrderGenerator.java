package ru.nsu.pronin.process;

import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.data.OrderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.StreamSupport;

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
    public static List<Order> generateOrders(int orderCount) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < orderCount; i++) {
            Order elementOfQueue = new OrderImpl(i, "pizza", "заказана");
            orders.add(elementOfQueue);
        }
        return orders;
    }
}
