package ru.nsu.pronin;

/**
 * Класс OpeningHours реализует симуляцию времени работы пиццерии.
 */
public class OpeningHours {
    static boolean isOpen = true; // Флаг состояния пиццерии
    static boolean isProgramRunning = true; // Флаг для завершения программы

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
        synchronized (OpeningHours.class) {
            OpeningHours.class.notifyAll();
        }
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
    public static void checkOpen() throws InterruptedException {
        synchronized (OpeningHours.class) {
            while (!isOpen) {
                OpeningHours.class.wait();
            }
        }
    }
}
