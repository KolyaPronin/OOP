package ru.nsu.pronin;

import java.util.PriorityQueue;

/**
 * Класс Warehouse (склад) реализует работу склада.
 */
public class Warehouse {
    final static int capacity = 30;
    static int currentStateCapacity = 0;
    final static PriorityQueue<Order> queueOfOrder = new GeneralQueueOfOrders().orders;

    /**
     * Метод isTherePlaceWareHouse проверяет есть ли место на складе.
     */
    public static boolean isTherePlaceWareHouse() {
        if (currentStateCapacity < capacity) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод storageInTheWareHouse реализует добавление элемента в очередь склада.
     *
     * @param element - добавляемый в очередь элемент.
     */
    public static synchronized void storageInTheWareHouse(Order element) {
        element.setState(" заказ 🍕 отправлен на склад 🏤 ");
        System.out.println(element.getId() + element.getState());
        queueOfOrder.add(element);
        currentStateCapacity++;
        queueOfOrder.notify(); // правильно ли я делаю???
    }

    /**
     * Метод возвращает очередь склада.
     *
     * @return queueOfOrder - очередь класса.
     */
    public static PriorityQueue<Order> getQueueOfOrder() {
        return queueOfOrder;
    }
}
