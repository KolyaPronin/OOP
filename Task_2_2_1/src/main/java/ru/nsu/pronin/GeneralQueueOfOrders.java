package ru.nsu.pronin;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Класс GeneralQueueOfOrders инициализирует и определяет очередь,
 * которая используется для очереди заказов и для очереди склада.
 * */
public class GeneralQueueOfOrders {
    PriorityQueue<Order> orders = new PriorityQueue<>(Comparator.comparingInt(Order::getId));
}
