package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AdjacencyMatrixTest {

    adjacencyMatrix matrix = new adjacencyMatrix(3);

    @Test
    void constructorValidation() {
        assertThrows(IllegalArgumentException.class, () -> new adjacencyMatrix(0));
    }

    @Test
    void testAddingVertex() {
        matrix.addEdge(1, 2);
        matrix.addVertex(5);
        assertEquals(matrix.toString(), "{1=[2], 2=[], 3=[], 4=[], 5=[]}");
    }

    @Test
    void testRemovingVertex() {
        matrix.addVertex(5);
        matrix.addEdge(1, 2);
        matrix.addEdge(1, 3);
        matrix.addEdge(5, 2);

        matrix.deleteVertex(1);
        assertEquals(matrix.toString(), "{1=[], 2=[], 3=[], 4=[], 5=[2]}");
    }

    @Test
    void removeVertexInvalid() {
        assertThrows(IllegalArgumentException.class, () -> matrix.deleteVertex(5));
    }

    @Test
    void testAddEdge() {
        matrix.addEdge(1, 2);
        assertEquals(matrix.toString(), "{1=[2], 2=[], 3=[]}");
    }

    @Test
    void addEdgeInvalidSource() {
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(5, 2));
    }

    @Test
    void addEdgeInvalidTarget() {
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(2, 5));
    }

    @Test
    void testRemoveEdge() {
        matrix.addEdge(1, 2);
        matrix.deleteEdge(1, 2);

        assertEquals(matrix.toString(), "{1=[], 2=[], 3=[]}");
    }

    @Test
    void removeEdgeInvalidSource() {
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(5, 2));
    }

    @Test
    void removeEdgeInvalidTarget() {
        assertThrows(IllegalArgumentException.class, () -> matrix.addEdge(2, 5));
    }

    @Test
    void testNeighborList() {
        List<Integer> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(2);
        expectedNeighbors.add(3);

        matrix.addEdge(1, 2);
        matrix.addEdge(1, 3);

        List<Integer> actualNeighbors = matrix.getNeighbors(1);

        assertEquals(expectedNeighbors, actualNeighbors);
    }

    @Test
    void neighborListInvalid() {
        assertThrows(IllegalArgumentException.class, () -> matrix.getNeighbors(5));
    }

    @Test
    void testGetAllEdges() {
        matrix.addVertex(4);
        matrix.addEdge(1, 2);
        matrix.addEdge(1, 3);
        matrix.addEdge(2, 3);

        Map<Integer, List<Integer>> edges = matrix.allEdges();

        assertEquals(edges.toString(), "{1=[2, 3], 2=[3], 3=[], 4=[]}");
    }

    @Test
    void equalityWithSameInstance() {
        assertTrue(matrix.equals(matrix));
    }

    @Test
    void equalityWithNewMatrix() {
        adjacencyMatrix newMatrix = new adjacencyMatrix(3);
        newMatrix.addVertex(1);
        newMatrix.addVertex(2);
        newMatrix.addVertex(3);
        newMatrix.addEdge(3, 1);

        matrix.addEdge(3, 1);

        assertEquals(matrix, newMatrix);
    }

    @Test
    void inequalityWithDifferentClass() {
        List<Integer> sampleList = new ArrayList<>();
        assertNotEquals(matrix, sampleList);
    }

    @Test
    void equalityWithAdjList() {
        adjacencyList adjList = new adjacencyList();
        adjList.addVertex(1);
        adjList.addVertex(2);
        adjList.addVertex(3);
        adjList.addEdge(3, 1);

        matrix.addEdge(3, 1);

        assertEquals(matrix, adjList);
    }

    @Test
    void inequalityWithModifiedAdjList() {
        adjacencyList adjList = new adjacencyList();
        adjList.addVertex(1);
        adjList.addVertex(2);
        adjList.addVertex(3);
        adjList.addEdge(3, 1);
        adjList.addEdge(1, 2);

        matrix.addEdge(3, 1);

        assertNotEquals(matrix, adjList);
    }

    @Test
    void equalityWithIncidenceMatrix() {
        incidenceMatrix incMatrix = new incidenceMatrix(3);
        incMatrix.addVertex(1);
        incMatrix.addVertex(2);
        incMatrix.addVertex(3);
        incMatrix.addEdge(3, 1);

        matrix.addEdge(3, 1);

        assertEquals(matrix, incMatrix);
    }

    @Test
    void inequalityWithModifiedIncidenceMatrix() {
        incidenceMatrix incMatrix = new incidenceMatrix(3);
        incMatrix.addVertex(1);
        incMatrix.addVertex(2);
        incMatrix.addVertex(3);
        incMatrix.addEdge(3, 1);
        incMatrix.addEdge(1, 2);

        matrix.addEdge(3, 1);

        assertNotEquals(matrix, incMatrix);
    }

    @Test
    void testTopologicalOrder() {
        matrix.addVertex(4);
        matrix.addVertex(5);
        matrix.addVertex(6);
        matrix.addVertex(7);

        matrix.addEdge(1, 7);
        matrix.addEdge(2, 4);
        matrix.addEdge(2, 3);
        matrix.addEdge(3, 4);
        matrix.addEdge(3, 5);
        matrix.addEdge(3, 7);
        matrix.addEdge(4, 5);
        assertEquals(matrix.topologicalSort().toString(), "[6, 2, 3, 4, 5, 1, 7]");
    }

    @Test
    void testTopologicalOrderWithCycle() {
        matrix.addEdge(1, 2);
        matrix.addEdge(2, 3);
        matrix.addEdge(3, 1);

        assertEquals(matrix.topologicalSort().toString(), "[]");
    }

    @Test
    void testFileRead() throws IOException {
        matrix = new adjacencyMatrix(1);
        matrix.readFromFile("src/test/java/ru/nsu/pronin/testGraph.txt");

        assertEquals(matrix.toString(), "{1=[], 2=[3], 3=[], 4=[1]}");
    }
}
