package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;


/**
 * Класс реализует тесты работников пиццерии.
 */
class WorkerGeneratorTest {

    /**
     * Метод тестирующий пекаря.
     */
    @Test
    void testWorkerBaker() {
        PriorityQueue<Order> order = new GeneralQueueOfOrders().orders;
        OrderGenerator.orderGenerator(order, 3);
        WorkerGenerator.workerBakerGenerator(order, 2);
        assertEquals(3, order.size(), "Очередь должна содержать 3 заказа");
        assertTrue(order.size() < 3, "Повара должны начать забирать заказы");
        assertDoesNotThrow(() -> WorkerGenerator.workerBakerGenerator(order, 2)); // что не падает и исключением
    }

    /**
     * Метод тестирующий бегунка.
     *
     * @throws InterruptedException если выполнение потоков прерывается
     */
    @Test
    void testWorkerBagMan() throws InterruptedException {
        Warehouse.queueOfOrder.clear();
        Order elementOfQueue = new OrderRealization(0, "pizza", "заказана");
        Order elementOfQueue1 = new OrderRealization(1, "pizza", "заказана");
        Order elementOfQueue2 = new OrderRealization(2, "pizza", "заказана");

        synchronized (Warehouse.queueOfOrder) {
            Warehouse.storageInTheWareHouse(elementOfQueue);
            Warehouse.storageInTheWareHouse(elementOfQueue1);
            Warehouse.storageInTheWareHouse(elementOfQueue2);
        }

        assertEquals(3, Warehouse.queueOfOrder.size(), "Склад содержит 3 заказа после генерации");
        WorkerGenerator.workerBagManGenerator(2);
        Thread.sleep(1000);
        assertTrue(Warehouse.queueOfOrder.size() < 3, "Бегунки должны забрать заказы");
        assertDoesNotThrow(() -> WorkerGenerator.workerBakerGenerator(Warehouse.queueOfOrder, 2));
        Warehouse.queueOfOrder.clear();
    }

}