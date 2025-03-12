package ru.nsu.pronin.data;

/**
 * Интерфейс Order используется для инициализации полей заказа (Order).
 * Используется для возможного расширения "объекта" заказа.
 * */
public interface Order {
    int getId();
    String getType();
    String getState();
    void setState(String state);
}
