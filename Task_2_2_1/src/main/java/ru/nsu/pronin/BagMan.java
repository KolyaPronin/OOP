package ru.nsu.pronin;

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
     * @param time        - время, затрачиваемое курьером на доставку.
     */
    BagMan(int id, int bagCapacity, int time) {
        this.id = id;
        this.bagCapacity = bagCapacity;
        this.time = time;
    }

    /**
     * Метод bagMan()  реализует доставку пиццы курьером до клиента.
     *
     * @throws InterruptedException если выполнение потоков прерывается
     */
    public void bagMan() throws InterruptedException {
        int standardBagCapacity = bagCapacity;
        Order[] bag = new Order[standardBagCapacity];
        for (int i = 0; i < standardBagCapacity; i++) {
            synchronized (Warehouse.queueOfOrder) {
                if (Warehouse.currentStateCapacity != 0) {
                    bag[i] = Warehouse.getQueueOfOrder().poll();
                    Warehouse.currentStateCapacity--;
                } else {
                    System.out.println("склад пустой");
                    break;
                }
            }
            if (bag[i] != null) {
                bag[i].setState("в пути 🚲");
                System.out.println(" заказ 🍕 " + bag[i].getId()
                        + " взят курьером 👨🏿‍🎓 " + id + " и он уже " + bag[i].getState());
                Thread.sleep(time);
                bag[i].setState(" заказ доставлен 🎉");
                System.out.println(bag[i].getId() + bag[i].getState()
                        + " курьером под номером " + id);
            }
        }
    }
}
