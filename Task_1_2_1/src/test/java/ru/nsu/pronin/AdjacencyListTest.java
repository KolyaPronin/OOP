package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdjacencyListTest {

    static AdjacencyList graph = new AdjacencyList();

    @BeforeEach
    void setUp() {
        graph = new AdjacencyList();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
    }

    @Test
    void testAddVertex() {
        graph.addEdge(1, 2);
        graph.addVertex(5);
        assertEquals(graph.toString(), "{1=[2], 2=[], 3=[], 5=[]}");
    }

    @Test
    void testRemoveVertex() {
        graph.addVertex(5);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(5, 2);
        graph.addEdge(5, 1);

        graph.deleteVertex(1);

        assertEquals(graph.toString(), "{2=[], 3=[], 5=[2]}");
    }

    @Test
    void testRemoveVertexException() {
        assertThrows(IllegalArgumentException.class, () -> graph.deleteVertex(5));
    }

    @Test
    void testAddEdge() {
        graph.addEdge(1, 2);
        assertEquals(graph.toString(), "{1=[2], 2=[], 3=[]}");
    }

    @Test
    void testAddEdgeExceptionNonExistentSource() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(5, 2));
    }

    @Test
    void testAddEdgeExceptionNonExistentTarget() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(2, 5));
    }

    @Test
    void testDeleteEdge() {
        graph.addEdge(1, 2);
        graph.deleteEdge(1, 2);

        assertEquals(graph.toString(), "{1=[], 2=[], 3=[]}");
    }

    @Test
    void testDeleteEdgeExceptionNonExistentSource() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(5, 2));
    }

    @Test
    void testDeleteEdgeExceptionNonExistentTarget() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(2, 5));
    }

    @Test
    void testGetNeighbors() {
        List<Integer> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(2);
        expectedNeighbors.add(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        List<Integer> actualNeighbors = graph.getNeighbors(1);

        assertEquals(expectedNeighbors, actualNeighbors);
    }

    @Test
    void testGetNeighborsException() {
        assertThrows(IllegalArgumentException.class, () -> graph.getNeighbors(5));
    }

    @Test
    void testAllEdges() {
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        Map<Integer, List<Integer>> allEdges = graph.allEdges();

        assertEquals(allEdges.toString(), "{1=[2, 3], 2=[3], 3=[], 4=[]}");
    }

    @Test
    void testEqualsSameObject() {
        assertTrue(graph.equals(graph));
    }

    @Test
    void testEqualsWithNewAdjList() {
        AdjacencyList newGraph = new AdjacencyList();
        newGraph.addVertex(1);
        newGraph.addVertex(2);
        newGraph.addVertex(3);
        newGraph.addEdge(3, 1);

        graph.addEdge(3, 1);

        assertEquals(graph, newGraph);
    }

    @Test
    void testEqualsWithDifferentClass() {
        List<Integer> exampleList = new ArrayList<>();

        assertNotEquals(graph, exampleList);
    }

    @Test
    void testEqualsWithAdjMatrixTrue() {
        AdjacencyMatrix adjMatrix = new AdjacencyMatrix(3);
        adjMatrix.addVertex(1);
        adjMatrix.addVertex(2);
        adjMatrix.addVertex(3);
        adjMatrix.addEdge(3, 1);

        graph.addEdge(3, 1);

        assertEquals(graph, adjMatrix);
    }

    @Test
    void testEqualsWithAdjMatrixFalse() {
        AdjacencyMatrix adjMatrix = new AdjacencyMatrix(3);
        adjMatrix.addVertex(1);
        adjMatrix.addVertex(2);
        adjMatrix.addVertex(3);
        adjMatrix.addEdge(3, 1);
        adjMatrix.addEdge(1, 2);

        graph.addEdge(3, 1);

        assertNotEquals(graph, adjMatrix);
    }

    @Test
    void testEqualsWithIncMatrixTrue() {
        IncidenceMatrix incMatrix = new IncidenceMatrix(3);
        incMatrix.addVertex(1);
        incMatrix.addVertex(2);
        incMatrix.addVertex(3);
        incMatrix.addEdge(3, 1);

        graph.addEdge(3, 1);

        assertTrue(graph.equals(incMatrix));
    }

    @Test
    void testEqualsWithIncMatrixFalse() {
        IncidenceMatrix incMatrix = new IncidenceMatrix(3);
        incMatrix.addVertex(1);
        incMatrix.addVertex(2);
        incMatrix.addVertex(3);
        incMatrix.addEdge(3, 1);
        incMatrix.addEdge(1, 2);

        graph.addEdge(3, 1);

        assertNotEquals(graph, incMatrix);
    }

    @Test
    void testTopologicalSort() {
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);

        graph.addEdge(1, 7);
        graph.addEdge(2, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(3, 7);
        graph.addEdge(4, 5);

        assertEquals(graph.topologicalSort().toString(), "[6, 2, 3, 4, 5, 1, 7]");
    }

    @Test
    void testTopologicalSortWithCycle() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        assertEquals(graph.topologicalSort().toString(), "[]");
    }

    @Test
    void testReadFromFile() throws IOException {
        graph = new AdjacencyList();
        graph.readFromFile("src/test/java/ru/nsu/pronin/testGraph.txt");

        assertEquals(graph.toString(), "{1=[], 2=[3], 3=[], 4=[1]}");
    }
}
