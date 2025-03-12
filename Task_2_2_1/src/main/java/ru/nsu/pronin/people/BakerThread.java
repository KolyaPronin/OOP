package ru.nsu.pronin.people;

import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.process.PizzeriaDispatcher;

/**
 * Класс {@link BagManWorker} наследуется от {@link Thread}.
 * И реализует параллельную работу пекарей.
 */
public class BakerThread extends Thread {

    private final Baker baker;

    /**
     * Конструктор для инициализации пекаря.
     *
     * @param baker Пекарь для работы в потоке.
     */
    public BakerThread(Baker baker) {
        this.baker = baker;

    }

    /**
     * Метод, который выполняет работу пекаря в потоке.
     */
    @Override
    public void run() {
        try {
            while (PizzeriaDispatcher.isProgramRunning()) {
                PizzeriaDispatcher.checkOpen();
                Order order = baker.getOrderQueue().pollWithWait();
                baker.bake(order);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
