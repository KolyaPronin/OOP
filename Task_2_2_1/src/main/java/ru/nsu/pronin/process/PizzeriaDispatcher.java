package ru.nsu.pronin.process;

/**
 * Класс OpeningHours реализует симуляцию времени работы пиццерии.
 */
public class PizzeriaDispatcher {
    private static boolean isOpen = true; // Флаг состояния пиццерии
    private static volatile boolean isProgramRunning = true; // Флаг для завершения программы

    /**
     * Метод closePizzeria при вызове устанавливает флаг isOpen в false.
     */
    public static synchronized void closePizzeria() {
        isOpen = false;
        System.out.println("⛔ Пиццерия закрывается!");
    }

    /**
     * Метод openPizzeria при вызове устанавливает флаг isOpen в true и будит потоки (notifyAll()),
     * которые засыпают (wait()) при закрытии пиццерии.
     */
    public static synchronized void openPizzeria() {
        isOpen = true;
        System.out.println("✅ Пиццерия открылась!");
        PizzeriaDispatcher.class.notifyAll();
    }
    /**
     * Метод для остановки программы
     * */
    public static void stopProgram() {
        isProgramRunning = false;  // Завершаем программу
    }

    /**
     * Возвращает флаг остановки программы
     * */
    public static boolean isProgramRunning() {
        return isProgramRunning;
    }
    /**
     * Метод checkOpen закрывает пиццерию (заставляет потоки ждать),
     * до тех пор пока флаг (isOpen) не будет true.
     *
     * @throws InterruptedException если выполнение потоков прерывается.
     */
    public static synchronized void checkOpen() throws InterruptedException {
        while (!isOpen) {
            PizzeriaDispatcher.class.wait();
        }
    }
}
