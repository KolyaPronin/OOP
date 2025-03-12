package ru.nsu.pronin.process;

import ru.nsu.pronin.data.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Класс Warehouse (склад) реализует работу склада.
 */
public class Warehouse {
    private static final int capacity = 30;
    private static int currentStateCapacity = 0;
    private static final PriorityQueue<Order> queueOfOrder = new PriorityQueue<>(Comparator.comparingInt(Order::getId));

    /**
     * Метод isTherePlaceWareHouse проверяет есть ли место на складе.
     */
    public static synchronized boolean isTherePlaceWareHouse() {
        return currentStateCapacity < capacity;
    }

    /**
     * Метод storageInTheWareHouse реализует добавление элемента в очередь склада.
     *
     * @param element - добавляемый в очередь элемент.
     */
    public static synchronized void storeInWareHouse(Order element) {
        element.setState(" заказ 🍕 отправлен на склад 🏤 ");
        System.out.println(element.getId() + element.getState());
        queueOfOrder.add(element);
        currentStateCapacity++;
        Warehouse.class.notifyAll();
    }

    public static synchronized void placeOrder(Order order) throws InterruptedException {
        if (isTherePlaceWareHouse()) {
            storeInWareHouse(order);
        } else {
            while (!isTherePlaceWareHouse()) {
                System.out.println("Склад полон, пекарь ждет... 🕐 ");
                Thread.sleep(1000);
            }
            System.out.println("Место освободилось и пицца отправилась на склад 🎉🎉🎉");
        }
    }

    public static synchronized List<Order> waitOrder(int bagCapacity) throws InterruptedException {
        PizzeriaDispatcher.checkOpen();
        while (queueOfOrder.isEmpty()) {
            Warehouse.class.wait();
        }
        List<Order> bag = new ArrayList<>();
        for (int i = 0; i < bagCapacity; i++) {
            Order order = Warehouse.takeOrder();
            if (order == null) {
                break;
            }
            bag.add(order);
        }
        return bag;
    }

    public static PriorityQueue<Order> getQueueOfOrder() { // ??
        return queueOfOrder;
    }


    private static Order takeOrder() throws InterruptedException {
        if (Warehouse.currentStateCapacity != 0) {
            Warehouse.currentStateCapacity--;
            Order order;
            while ((order = queueOfOrder.poll()) == null) {
                Warehouse.class.wait();
            }
            return order;
        } else {
            System.out.println("склад пустой");
            return null;
        }
    }
}
