package ru.nsu.pronin;

import ru.nsu.pronin.process.GeneralQueueOfOrders;
import ru.nsu.pronin.process.OrderGenerator;
import ru.nsu.pronin.process.PizzeriaDispatcher;
import ru.nsu.pronin.process.WorkerGenerator;

/**
 * Главный класс запуска программы.
 */
public class Main {

    /**
     * Метод main() реализует основную логику программы.
     *
     * @param args аргументы командной строки (не используются).
     *
     * @throws InterruptedException если выполнение потоков прерывается.
     */
    public static void main(String[] args) throws InterruptedException {

        GeneralQueueOfOrders orderQueue = new GeneralQueueOfOrders();
        orderQueue.placeAllOrders(OrderGenerator.generateOrders(10));

        WorkerGenerator.generateBaker(orderQueue, 5);
        WorkerGenerator.workerBagManGenerator(5);

        for (int i = 0; i < 5; i++) { // 5 дней 2-ое выходных
            int workingTimeOfPizzeria = 500;
            Thread.sleep(workingTimeOfPizzeria);

            PizzeriaDispatcher.closePizzeria();
            Thread.sleep(500); // работники доделывают текущие заказы и завершают рабочий день
            PizzeriaDispatcher.openPizzeria();
        }
        Thread.sleep(10000);
        PizzeriaDispatcher.stopProgram();
    }
}
