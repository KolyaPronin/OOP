package ru.nsu.pronin.people;

import ru.nsu.pronin.data.Order;
import ru.nsu.pronin.process.GeneralQueueOfOrders;
import ru.nsu.pronin.process.Warehouse;

/**
 * Класс Baker реализует отдельно взятого пекаря,
 * который в будущем будет использоваться в потоке.
 * */
public class Baker {
    private final GeneralQueueOfOrders orders;
    private final int id;
    private final int time;

    /**
    * Конструктор для инициализации полей пекаря.
     *
     * @param id - личный идентификатор пекаря.
     * @param time - время затраченное пекарем на выполнение заказа, ms.
     * @param orders - очередь заказов.
     * */
    public Baker(int id, int time, GeneralQueueOfOrders orders) {
        this.id = id;
        this.orders = orders;
        this.time = time;
    }

    /**
     * Метод baker реализует выполнение заказов поваром.
     *
     * @throws InterruptedException если выполнение потоков прерывается.
     */
    public void bake(Order order) throws InterruptedException {
        order.setState("Готовится  пекарем 👨🏿‍🍳 ");
        System.out.println("Заказ 🍕 " + order.getId() + " " + order.getState() + " номер " + id);
        System.out.println("готовлю заказ 🍕 " + order.getId());
        Thread.sleep(time);
        System.out.println("Заказ 🍕 " + order.getId() + " готов");
        Warehouse.placeOrder(order);
    }

    public GeneralQueueOfOrders getOrderQueue() {
        return orders;
    }
}
