package ru.nsu.pronin;

/**
 * Интерфейс Order используется для инициализации полей заказа (Order).
 * Используется для возможного расширения "объекта" заказа.
 * */
interface Order {
    int getId();
    String getType();
    String getState();
    void setState(String state);
}
