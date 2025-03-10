package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для склада (WareHouse)
 * */
class WareHouseTest {
    /**
     * Метод очищающий очередь склада.
     * */
    @BeforeEach
    void setUp() {
        Warehouse.queueOfOrder.clear(); // Очищаем перед каждым тестом
        Warehouse.currentStateCapacity = 0;
    }

    /**
     * Метод тестирует добавление элемента на склад.
     * */
    @Test
    void testAddElementToWareHouse() {
        Order elementOfQueue = new OrderRealization(0, "pizza", "заказана");
        synchronized (Warehouse.queueOfOrder) {
            Warehouse.storageInTheWareHouse(elementOfQueue);
        }
        assertEquals(elementOfQueue, Warehouse.queueOfOrder.poll());
    }

    /**
     * Метод тестирует вместимость склада.
     * */
    @Test
    void testCapacityOfTheWarehouse() {
        OrderGenerator.orderGenerator(Warehouse.queueOfOrder, 30);
        Order elementOfQueue = new OrderRealization(0, "pizza", "заказана");
        Warehouse.currentStateCapacity = 30;
        synchronized (Warehouse.queueOfOrder) {
            if (Warehouse.isTherePlaceWareHouse())
                Warehouse.storageInTheWareHouse(elementOfQueue);
        }
        assertFalse(Warehouse.isTherePlaceWareHouse());
    }

    /**
     * Метод, который тестирует получение очереди склада.
     * */
    @Test
    void testGetQueueOfOrder() {
        assertEquals(Warehouse.queueOfOrder, Warehouse.getQueueOfOrder());
    }
}
