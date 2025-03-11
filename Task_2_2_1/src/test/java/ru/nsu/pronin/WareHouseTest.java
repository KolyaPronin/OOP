package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.data.OrderImpl;
import ru.nsu.pronin.process.OrderGenerator;
import ru.nsu.pronin.process.Warehouse;

/**
 * Тесты для склада (WareHouse).
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
        Order elementOfQueue = new OrderImpl(0, "pizza", "заказана");
        synchronized (Warehouse.queueOfOrder) {
            Warehouse.storeInWareHouse(elementOfQueue);
        }
        assertEquals(elementOfQueue, Warehouse.queueOfOrder.pollWithWait());
    }

    /**
     * Метод тестирует вместимость склада.
     * */
    @Test
    void testCapacityOfTheWarehouse() {
        OrderGenerator.generateOrders(Warehouse.queueOfOrder, 30);
        Order elementOfQueue = new OrderImpl(0, "pizza", "заказана");
        Warehouse.currentStateCapacity = 30;
        synchronized (Warehouse.queueOfOrder) {
            if (Warehouse.isTherePlaceWareHouse()) {
                Warehouse.storeInWareHouse(elementOfQueue);
            }
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
