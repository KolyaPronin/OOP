package ru.nsu.pronin.process;

import ru.nsu.pronin.data.Order;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Класс GeneralQueueOfOrders инициализирует и определяет очередь,
 * которая используется для очереди заказов и для очереди склада.
 * */
public class GeneralQueueOfOrders {
    private final PriorityQueue<Order> orders = new PriorityQueue<>(Comparator.comparingInt(Order::getId));

    public synchronized void placeAllOrders(List<Order> orderList) {
        orders.addAll(orderList);
    }

    public synchronized Order pollWithWait() throws InterruptedException {
        Order order;
        while ((order = orders.poll()) == null) {
            System.out.println("Очередь пуста и пекарь отдыхает 💤💤💤");
            wait();
        }
        return order;
    }

    public int getSize(){
        return orders.size();
    }
}
