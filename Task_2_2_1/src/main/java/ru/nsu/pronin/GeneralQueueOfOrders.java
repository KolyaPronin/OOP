package ru.nsu.pronin;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Интерфейс Order используется для инициализации полей заказа (Order)
 * Используется для возможного расширения "объекта" заказа
 * */
interface Order{
    int getId();
    String getType();
    String getState();
    void setState(String state);
}

/**
 * Класс GeneralQueueOfOrders инициализирует и определяет очередь,
 * которая используется для очереди заказов и для очереди склада
 * */
public class GeneralQueueOfOrders {
    PriorityQueue<Order> orders = new PriorityQueue<>(Comparator.comparingInt(Order::getId));
}

/**
 * Класс OrderRealization реализует интерфейс Order, используя геттеры и сеттеры.
 * */
class OrderRealization implements Order{
    private final int id;
    private final String type;
    private String state ;

    /**
     * метод setState заполняет поле state интерфейса Order
     * @param state - состояние заказа:
     *             [заказан, готовится пекарем, готов, отправлен на склад, в пути, доставлен]
     * */
    public void setState(String state){
        this.state = state;
    }

    /**
     * метод getId возвращает id пекаря.
     * */
    public int getId(){
        return id;
    }

    /**
     * метод getType возвращает type пекаря.
     * */
    public String getType(){ // doesn't used
        return type;
    }

    /**
     * метод getState возвращает state пекаря.
     * */
    public String getState(){
        return state;
    }

    /**
     * Конструктор класса OrderRealization который определяет поля.
     * @param id - id повара
     * @param type - type (тип заказа)
     * @param state - state (состояние заказа)
     * */
    OrderRealization(int id, String type, String state){
        this.id = id;
        this.type = type;
        this.state = state;
    }

