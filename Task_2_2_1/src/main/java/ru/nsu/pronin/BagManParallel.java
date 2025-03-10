package ru.nsu.pronin;

/**
 * Класс {@link BagManParallel} наследуется от {@link Thread},
 * и реализует параллельную работу бегунков.
 */
public class BagManParallel extends Thread {
    private BagMan runner;

    /**
     * Конструктор, инициализирует поле класса {@link BagManParallel}.
     */
    public BagManParallel(BagMan runner) {
        this.runner = runner;
    }

    /**
     * Метод, который выполняет работу курьера в потоке,
     * до тех пор пока очередь склада не опустеет.
     */
    public void run() {
        while (OpeningHours.isProgramRunning()) {
            synchronized (Warehouse.queueOfOrder) {
                try {
                    OpeningHours.checkOpen();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                while (Warehouse.queueOfOrder.isEmpty()) {
                    try {
                        Warehouse.queueOfOrder.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            try {
                runner.bagMan();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
