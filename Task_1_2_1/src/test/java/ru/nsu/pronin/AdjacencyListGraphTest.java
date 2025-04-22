package ru.nsu.pronin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для реализации графа с использованием списка смежности.
 */
class AdjacencyListGraphTest {

    private Graph graph;
    private Vertex a, b, c;

    /**
     * Инициализация данных перед каждым тестом.
     * Создаёт новый граф и вершины для тестов.
     */
    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph();
        a = new Vertex("A");
        b = new Vertex("B");
        c = new Vertex("C");
    }

    /**
     * Проверка добавления вершины в граф.
     * После добавления вершины, её список соседей должен быть пуст.
     */
    @Test
    void testAddVertex() {
        graph.addVertex(a);
        assertTrue(graph.getNeighbors(a).isEmpty(), "Список соседей вершины должен быть пустым.");
    }

    /**
     * Проверка добавления рёбер между вершинами.
     * После добавления ребра между a и b, вершина a должна содержать b в списке соседей.
     */
    @Test
    void testAddEdge() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);

        List<Vertex> nbrs = graph.getNeighbors(a);

        assertEquals(1, nbrs.size(), "Количество соседей вершины a должно быть 1.");
        assertTrue(nbrs.contains(b), "Вершина a должна иметь вершину b как соседа.");
    }

    /**
     * Проверка удаления рёбер между вершинами.
     * После удаления ребра между a и b, вершина a не должна содержать b в списке соседей.
     */
    @Test
    void testRemoveEdge() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);
        graph.removeEdge(a, b);

        assertFalse(graph.getNeighbors(a).contains(b), "После удаления ребра a -> b, список соседей a не должен содержать b.");
    }

    /**
     * Проверка удаления вершины из графа.
     * После удаления вершины, её список соседей должен быть пуст.
     * Также должны быть удалены все рёбра, содержащие эту вершину.
     */
    @Test
    void testRemoveVertex() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);

        graph.removeVertex(a);

        // После удаления a у неё не должно быть соседей:
        assertTrue(graph.getNeighbors(a).isEmpty(), "После удаления вершины a, её список соседей должен быть пустым.");

        // Вершина b всё ещё должна быть в графе, но без входящего рёбра:
        assertTrue(graph.getNeighbors(b).isEmpty(), "Вершина b должна быть в графе, но без рёбер.");
    }

    /**
     * Проверка топологической сортировки графа.
     * Для графа, содержащего вершины a, b и c с рёбрами a -> b и b -> c,
     * топологическая сортировка должна вернуть список в порядке [a, b, c].
     */
    @Test
    void testTopologicalSort() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addEdge(a, b);
        graph.addEdge(b, c);

        List<Vertex> order = graph.topologicalSort();

        assertEquals(3, order.size(), "Топологическая сортировка должна вернуть 3 вершины.");
        assertEquals(a, order.get(0), "Первая вершина в порядке должна быть a.");
        assertEquals(b, order.get(1), "Вторая вершина в порядке должна быть b.");
        assertEquals(c, order.get(2), "Третья вершина в порядке должна быть c.");
    }

    /**
     * Проверка метода equals для сравнения двух графов.
     * Если два графа идентичны по вершинам и рёбрам, метод должен вернуть true.
     */
    @Test
    void testEquals() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);

        Graph other = new AdjacencyListGraph();
        other.addVertex(a);
        other.addVertex(b);
        other.addEdge(a, b);

        assertEquals(graph, other, "Графы должны быть равными.");
    }

    /**
     * Проверка метода toString для графа.
     * Строковое представление должно содержать информацию о вершинах и рёбрах графа.
     */
    @Test
    void testToString() {
        graph.addVertex(a);
        graph.addVertex(b);
        graph.addEdge(a, b);

        String s = graph.toString();

        // Проверяем, что строка содержит ключевые фрагменты
        assertTrue(s.contains("Adjacency list representation"), "Строка должна содержать 'Adjacency list representation'.");
        assertTrue(s.contains("Vertices: [A, B]"), "Строка должна содержать список вершин.");
        assertTrue(s.contains("A -> [B]"), "Строка должна содержать описание рёбер для вершины A.");
    }
}
