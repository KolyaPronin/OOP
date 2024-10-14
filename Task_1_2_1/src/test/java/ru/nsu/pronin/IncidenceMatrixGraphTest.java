package ru.nsu.pronin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для реализации графа через матрицу инцидентности.
 */
class IncidenceMatrixGraphTest {
    private Graph graph;
    private Vertex vertexA;
    private Vertex vertexB;
    private Vertex vertexC;

    /**
     * Инициализация данных перед каждым тестом.
     * Создается новый граф и вершины для тестирования.
     */
    @BeforeEach
    void setUp() {
        graph = new IncidenceMatrixGraph();
        vertexA = new Vertex("A");
        vertexB = new Vertex("B");
        vertexC = new Vertex("C");
    }

    /**
     * Проверка добавления вершины в граф.
     * После добавления вершины её список соседей должен быть пуст.
     */
    @Test
    void testAddVertex() {
        graph.addVertex(vertexA);
        assertTrue(graph.getNeighbors(vertexA).isEmpty(), "Список соседей вершины должен быть пустым.");
    }

    /**
     * Проверка добавления рёбер между вершинами.
     * После добавления ребра между vertexA и vertexB, вершина vertexA должна содержать vertexB в списке соседей.
     */
    @Test
    void testAddEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(vertexA, vertexB);

        List<Vertex> neighbors = graph.getNeighbors(vertexA);
        assertTrue(neighbors.contains(vertexB), "Вершина A должна содержать B в качестве соседа.");
    }

    /**
     * Проверка удаления рёбер между вершинами.
     * После удаления ребра между vertexA и vertexB, вершина vertexA не должна содержать vertexB в списке соседей.
     */
    @Test
    void testRemoveEdge() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(vertexA, vertexB);
        graph.removeEdge(vertexA, vertexB);

        List<Vertex> neighbors = graph.getNeighbors(vertexA);
        assertFalse(neighbors.contains(vertexB), "После удаления ребра, вершина A не должна содержать B в списке соседей.");
    }

    /**
     * Проверка удаления вершины из графа.
     * После удаления вершины vertexA, её не должно быть в графе, а также рёбра, исходящие от неё, должны быть удалены.
     */
    @Test
    void testRemoveVertex() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(vertexA, vertexB);

        graph.removeVertex(vertexA);

        assertTrue(graph.getNeighbors(vertexA).isEmpty(), "После удаления вершины A, она не должна иметь соседей.");
    }

    /**
     * Проверка топологической сортировки графа.
     * Граф, содержащий вершины vertexA, vertexB и vertexC с рёбрами vertexA -> vertexB и vertexB -> vertexC,
     * должен быть отсортирован в порядке [vertexA, vertexB, vertexC].
     */
    @Test
    void testTopologicalSort() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addEdge(vertexA, vertexB);
        graph.addEdge(vertexB, vertexC);

        List<Vertex> sorted = graph.topologicalSort();

        assertEquals(3, sorted.size(), "Топологическая сортировка должна вернуть 3 вершины.");
        assertEquals(vertexA, sorted.get(0), "Первая вершина в порядке должна быть vertexA.");
        assertEquals(vertexB, sorted.get(1), "Вторая вершина в порядке должна быть vertexB.");
        assertEquals(vertexC, sorted.get(2), "Третья вершина в порядке должна быть vertexC.");
    }

    /**
     * Проверка метода equals для сравнения двух графов.
     * Если два графа идентичны по вершинам и рёбрам, метод должен вернуть true.
     */
    @Test
    void testEquals() {
        // Наполняем graph
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(vertexA, vertexB);

        // Создаём точно такой же anotherGraph
        Graph anotherGraph = new IncidenceMatrixGraph();
        anotherGraph.addVertex(vertexA);
        anotherGraph.addVertex(vertexB);
        anotherGraph.addEdge(vertexA, vertexB);

        assertEquals(graph, anotherGraph, "Графы должны быть равными.");
    }

    /**
     * Проверка метода toString для графа.
     * Строковое представление должно содержать имя класса "Incidence matrix representation".
     */
    @Test
    void testToString() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);

        // Проверяем, что строковое представление содержит информацию о матрице инцидентности
        assertTrue(graph.toString().contains("Incidence matrix representation"),
                "Строковое представление должно содержать 'Incidence matrix representation'.");
    }
}
