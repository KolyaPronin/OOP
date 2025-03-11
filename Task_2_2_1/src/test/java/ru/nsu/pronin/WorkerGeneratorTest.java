package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;
import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.data.OrderImpl;
import ru.nsu.pronin.process.GeneralQueueOfOrders;
import ru.nsu.pronin.process.OrderGenerator;
import ru.nsu.pronin.process.Warehouse;
import ru.nsu.pronin.process.WorkerGenerator;

/**
 * Класс реализует тесты работников пиццерии.
 */
class WorkerGeneratorTest {

    /**
     * Метод тестирующий пекаря.
     *
     * @throws InterruptedException если выполнение потоков прерывается
     */
    @Test
    void testWorkerBaker() throws InterruptedException {
        PriorityQueue<Order> order = new GeneralQueueOfOrders().orders;
        OrderGenerator.generateOrders(order, 3);
        assertEquals(3, order.size(), "Очередь должна содержать 3 заказа");
        WorkerGenerator.generateBaker(order, 2);
        Thread.sleep(5000);
        assertTrue(order.size() < 3, "Повара должны начать забирать заказы");
        assertDoesNotThrow(() -> WorkerGenerator.generateBaker(order, 2));
    }

    /**
     * Метод тестирующий бегунка.
     *
     * @throws InterruptedException если выполнение потоков прерывается.
     */
    @Test
    void testWorkerBagMan() throws InterruptedException {
        Warehouse.queueOfOrder.clear();
        Order elementOfQueue = new OrderImpl(0, "pizza", "заказана");
        Order elementOfQueue1 = new OrderImpl(1, "pizza", "заказана");
        Order elementOfQueue2 = new OrderImpl(2, "pizza", "заказана");

        synchronized (Warehouse.queueOfOrder) {
            Warehouse.storeInWareHouse(elementOfQueue);
            Warehouse.storeInWareHouse(elementOfQueue1);
            Warehouse.storeInWareHouse(elementOfQueue2);
        }

        assertEquals(Warehouse.currentStateCapacity, Warehouse.currentStateCapacity,
                "Склад содержит 3 заказа после генерации");
        WorkerGenerator.workerBagManGenerator(2);
        Thread.sleep(1000);
        assertTrue(Warehouse.queueOfOrder.size() < 3, "Бегунки должны забрать заказы");
        assertDoesNotThrow(() -> WorkerGenerator.generateBaker(Warehouse.queueOfOrder, 2));
        Warehouse.queueOfOrder.clear();
    }

}