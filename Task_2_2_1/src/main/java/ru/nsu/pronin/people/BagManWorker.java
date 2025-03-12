package ru.nsu.pronin.people;

import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.process.PizzeriaDispatcher;
import ru.nsu.pronin.process.Warehouse;

import java.util.List;

/**
 * Класс {@link BagManWorker} наследуется от {@link Thread},
 * и реализует параллельную работу бегунков.
 */
public class BagManWorker extends Thread {
    private final BagMan runner;

    /**
     * Конструктор, инициализирует поле класса {@link BagManWorker}.
     */
    public BagManWorker(BagMan runner) {
        this.runner = runner;
    }

    /**
     * Метод, который выполняет работу курьера в потоке,
     * до тех пор пока очередь склада не опустеет.
     */
    public void run() {
        try {
            while (PizzeriaDispatcher.isProgramRunning()) {
                List<Order> bag = Warehouse.waitOrder(runner.getCapacity());
                runner.deliver(bag);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
