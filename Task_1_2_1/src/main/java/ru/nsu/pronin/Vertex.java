package ru.nsu.pronin;

/**
 * Класс для представления вершины графа.
 * Каждая вершина имеет уникальное имя, которое используется для её идентификации.
 */
public class Vertex {
    private String name;

    /**
     * Конструктор для создания вершины с заданным именем.
     *
     * @param name Имя вершины.
     */
    public Vertex(String name) {
        this.name = name;
    }

    /**
     * Получить имя вершины.
     *
     * @return Имя вершины.
     */
    public String getName() {
        return name;
    }

    /**
     * Сравнивает эту вершину с другой. Вершины считаются равными, если их имена совпадают.
     *
     * @param obj Объект для сравнения с текущей вершиной.
     * @return true, если вершины равны, иначе false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vertex)) return false;
        Vertex other = (Vertex) obj;
        return this.name.equals(other.name);
    }

    /**
     * Возвращает строковое представление вершины.
     * Обычно используется для вывода имени вершины.
     *
     * @return Строковое представление вершины (её имя).
     */
    @Override
    public String toString() {
        return name;
    }
}
