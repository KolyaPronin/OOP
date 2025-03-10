package ru.nsu.pronin;

/**
 * Класс {@link BagManParallel} наследуется от {@link Thread}
 * и реализует параллельную работу пекарей
 */
public class BakerParallel extends Thread {

    private Baker baker;

    /**
     * Конструктор для инициализации пекаря.
     *
     * @param baker Пекарь для работы в потоке.
     */
    public BakerParallel(Baker baker) {
        this.baker = baker;
    }

    /**
     * Метод, который выполняет работу пекаря в потоке.
     */
    @Override
    public void run() {
        try {
            while (true) {
                OpeningHours.checkOpen();
                if (!baker.baker()) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
