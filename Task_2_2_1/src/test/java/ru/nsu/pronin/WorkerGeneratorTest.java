package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.data.OrderImpl;
import ru.nsu.pronin.people.BagMan;
import ru.nsu.pronin.people.BagManWorker;
import ru.nsu.pronin.people.Baker;
import ru.nsu.pronin.people.BakerThread;
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
        List<Order> order = OrderGenerator.generateOrders(5);
        GeneralQueueOfOrders generalQueueOfOrders = new GeneralQueueOfOrders();
        generalQueueOfOrders.placeAllOrders(order);
        WorkerGenerator.generateBaker(generalQueueOfOrders,2);
        Thread.sleep(10000);
        assertDoesNotThrow(() -> WorkerGenerator.generateBaker(generalQueueOfOrders, 2));
        WorkerGenerator.workerBagManGenerator(2);
        Thread.sleep(10000);
        assertTrue(Warehouse.getQueueOfOrder().size() < 5);
    }

    @Test
    void testWorkerBakerInterrupted() throws InterruptedException {
        List<Order> orders = OrderGenerator.generateOrders(5);
        GeneralQueueOfOrders generalQueueOfOrders = new GeneralQueueOfOrders();
        generalQueueOfOrders.placeAllOrders(orders);

        // Создаём и запускаем поток пекаря
        Baker baker = new Baker(0,1000,generalQueueOfOrders);
        BakerThread bakerThread = new BakerThread(baker);
        bakerThread.start();

        // Даем немного времени на выполнение
        Thread.sleep(1000);

        // Прерываем поток
        bakerThread.interrupt();

        // Проверяем, что исключение не выбрасывается за пределы
        assertDoesNotThrow(() -> bakerThread.join());
    }

    @Test
    void testBagManWorkerInterrupted() throws InterruptedException {
        GeneralQueueOfOrders generalQueueOfOrders = new GeneralQueueOfOrders();
        BagMan bagMan = new BagMan(0,1,1000);
        BagManWorker bagManWorker = new BagManWorker(bagMan);

        // Запускаем поток курьера
        bagManWorker.start();

        // Даем немного времени на выполнение
        Thread.sleep(1000);

        // Прерываем поток
        bagManWorker.interrupt();

        // Проверяем, что исключение не выбрасывается за пределы
        assertDoesNotThrow(() -> bagManWorker.join());
    }
}
