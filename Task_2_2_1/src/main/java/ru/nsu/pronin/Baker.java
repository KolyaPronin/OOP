package ru.nsu.pronin;

import java.util.PriorityQueue;

/**
 * Класс Baker реализует отдельно взятого пекаря,
 * который в будущем будет использоваться в потоке.
 * */
public class Baker {
    private PriorityQueue<Order> orders;
    private int id;
    private int time;

    /**
    * Конструктор для инициализации полей пекаря.
     *
     * @param id - личный идентификатор пекаря.
     * @param time - время затраченное пекарем на выполнение заказа.
     * @param orders - очередь заказов.
     * */
    Baker(int id, int time, PriorityQueue<Order> orders) {
        this.id = id;
        this.orders = orders;
        this.time = time;
    }

    /**
     * Метод baker реализует выполнение заказов поваром.
     *
     * @throws InterruptedException если выполнение потоков прерывается.
     */
    public boolean baker() throws InterruptedException {
        Order order;
        synchronized (orders) {
            if (orders.isEmpty()) {
                System.out.println("Очередь пуста и пекарь откисает 💤💤💤");
                orders.wait();
            }
            order = orders.poll(); // синхронизация по взятию элемента из очереди
        }

        order.setState("Готовится  пекарем 👨🏿‍🍳 ");
        System.out.println("Заказ 🍕 " + order.getId() + " " + order.getState() + " номер " + id);
        System.out.println("готовлю заказ 🍕 " + order.getId());
        Thread.sleep(time);
        System.out.println("Заказ 🍕 " + order.getId() + " готов");
        synchronized (Warehouse.queueOfOrder) {
            if (Warehouse.isTherePlaceWareHouse()) {
                Warehouse.storageInTheWareHouse(order);
            } else {
                while (!Warehouse.isTherePlaceWareHouse()) {
                    System.out.println("Склад полон, пекарь ждет... 🕐 ");
                    Thread.sleep(1000);
                }
                System.out.println("Место освободилось и пицца отправилась на склад 🎉🎉🎉");
            }
        }
        return true;
    }
}
