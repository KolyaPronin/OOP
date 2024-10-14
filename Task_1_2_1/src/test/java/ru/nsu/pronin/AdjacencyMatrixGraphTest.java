package ru.nsu.pronin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Тесты для реализации графа с использованием матрицы смежности.
 */
public class AdjacencyMatrixGraphTest {
    private Graph graph;
    private Vertex vertexA;
    private Vertex vertexB;
    private Vertex vertexC;

    /**
     * Инициализация данных перед каждым тестом.
     * Создаётся новый граф и вершины для тестирования.
     */
    @BeforeEach
    public void setUp() {
        graph = new AdjacencyMatrixGraph();
        vertexA = new Vertex("A");
        vertexB = new Vertex("B");
        vertexC = new Vertex("C");
    }

    /**
     * Проверка добавления вершины в граф.
     * После добавления вершины её список соседей должен быть пуст.
     */
    @Test
    public void testAddVertex() {
        graph.addVertex(vertexA);
        assertTrue(graph.getNeighbors(vertexA).isEmpty(), "Список соседей вершины должен быть пустым.");
    }

    /**
     * Проверка добавления рёбер между вершинами.
     * После добавления ребра между vertexA и vertexB, вершина vertexA должна содержать vertexB в списке соседей.
     */
    @Test
    public void testAddEdge() {
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
    public void testRemoveEdge() {
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
    public void testRemoveVertex() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(vertexA, vertexB);

        graph.removeVertex(vertexA);

        assertFalse(graph.getNeighbors(vertexA).contains(vertexB), "После удаления вершины A, она не должна иметь соседей.");
    }

    /**
     * Проверка топологической сортировки графа.
     * Граф, содержащий вершины vertexA, vertexB и vertexC с рёбрами vertexA -> vertexB и vertexB -> vertexC,
     * должен быть отсортирован в порядке [vertexA, vertexB, vertexC].
     */
    @Test
    public void testTopologicalSort() {
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
    public void testEquals() {
        // Подготовка: добавляем вершины и рёбра в graph
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(vertexA, vertexB);

        // И в anotherGraph добавляем те же вершины и рёбра
        Graph anotherGraph = new AdjacencyMatrixGraph();
        anotherGraph.addVertex(vertexA);
        anotherGraph.addVertex(vertexB);
        anotherGraph.addEdge(vertexA, vertexB);

        assertEquals(graph, anotherGraph, "Графы должны быть равными.");
    }

    /**
     * Проверка метода toString для графа.
     * Строковое представление должно содержать имя класса, список вершин и корректное представление матрицы смежности.
     */
    @Test
    public void testToString() {
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addEdge(vertexA, vertexB);
        String repr = graph.toString();

        // Проверка, что строка начинается с имени класса
        assertTrue(repr.startsWith("AdjacencyMatrixGraph"), "Строковое представление должно начинаться с 'AdjacencyMatrixGraph'.");

        // Проверка, что строка содержит список вершин
        assertTrue(repr.contains("V: [A, B]"), "Строковое представление должно содержать список вершин.");

        // Проверка, что в строке есть ровно одна единица в матрице смежности
        long ones = repr.chars().filter(ch -> ch == '1').count();
        assertEquals(1, ones, "В строковом представлении должна быть ровно одна единица в матрице смежности.");
    }
}
