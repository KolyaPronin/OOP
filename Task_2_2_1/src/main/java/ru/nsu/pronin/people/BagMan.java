package ru.nsu.pronin.people;

import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.process.Warehouse;

import java.util.List;

/**
 * Класс BagMan реализует отдельно взятого курьера,
 * который в будущем будет использоваться в потоке.
 */
public class BagMan {
    private final int id;
    private final int bagCapacity;
    private final int time;

    /**
     * Конструктор для инициализации полей курьера.
     *
     * @param id          - идентификатор курьера.
     * @param bagCapacity - вместимость рюкзака курьера.
     * @param time        - время, затрачиваемое курьером на доставку, ms.
     */
    public BagMan(int id, int bagCapacity, int time) {
        this.id = id;
        this.bagCapacity = bagCapacity;
        this.time = time;
    }

    /**
     * Метод bagMan()  реализует доставку пиццы курьером до клиента.
     *
     * @throws InterruptedException если выполнение потоков прерывается
     */
    public void deliver(List<Order> bag) throws InterruptedException {
        for (Order order : bag) {
            order.setState("в пути 🚲");
            System.out.println(" заказ 🍕 " + order.getId()
                    + " взят курьером 👨🏿‍🎓 " + id + " и он уже " + order.getState());
            Thread.sleep(time);
            order.setState(" заказ доставлен 🎉");
            System.out.println(order.getId() + order.getState()
                    + " курьером под номером " + id);
        }
    }

    public int getCapacity() {
        return bagCapacity;
    }
}
