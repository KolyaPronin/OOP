package ru.nsu.pronin;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class IncidenceMatrixTest {

    incidenceMatrix graphMatrix = new incidenceMatrix(3);

    @Test
    void validateConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new adjacencyMatrix(0));
    }

    @Test
    void addSingleVertex() {
        graphMatrix.addVertex(5);
        assertEquals(6, graphMatrix.incMtx.length);
    }

    @Test
    void addVertexAndEdges() {
        graphMatrix.addEdge(1, 2);
        graphMatrix.addVertex(5);
        graphMatrix.addEdge(5, 1);
        assertEquals(graphMatrix.toString(), "{1=[2], 2=[], 3=[], 4=[], 5=[1]}");
    }

    @Test
    void testRemoveVertex() {
        graphMatrix.addVertex(5);
        graphMatrix.addEdge(1, 2);
        graphMatrix.addEdge(1, 3);
        graphMatrix.addEdge(5, 2);

        graphMatrix.deleteVertex(1);
        assertEquals(graphMatrix.toString(), "{1=[], 2=[], 3=[], 4=[1]}");
    }

    @Test
    void removeNonexistentVertex() {
        assertThrows(IllegalArgumentException.class, () -> graphMatrix.deleteVertex(5));
    }

    @Test
    void addEdgeBetweenVertices() {
        graphMatrix.addEdge(1, 2);
        assertEquals(graphMatrix.toString(), "{1=[2], 2=[], 3=[]}");
    }

    @Test
    void addEdgeWithInvalidSource() {
        assertThrows(IllegalArgumentException.class, () -> graphMatrix.addEdge(5, 2));
    }

    @Test
    void addEdgeWithInvalidTarget() {
        assertThrows(IllegalArgumentException.class, () -> graphMatrix.addEdge(2, 5));
    }

    @Test
    void testEdgeDeletion() {
        graphMatrix.addEdge(1, 2);
        graphMatrix.deleteEdge(1, 2);
        assertEquals(graphMatrix.toString(), "{1=[], 2=[], 3=[]}");
    }

    @Test
    void deleteEdgeInvalidSource() {
        assertThrows(IllegalArgumentException.class, () -> graphMatrix.addEdge(5, 2));
    }

    @Test
    void deleteEdgeInvalidTarget() {
        assertThrows(IllegalArgumentException.class, () -> graphMatrix.addEdge(2, 5));
    }

    @Test
    void retrieveNeighbors() {
        List<Integer> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(2);
        expectedNeighbors.add(3);

        graphMatrix.addEdge(1, 2);
        graphMatrix.addEdge(1, 3);

        List<Integer> actualNeighbors = graphMatrix.getNeighbors(1);
        assertEquals(expectedNeighbors, actualNeighbors);
    }

    @Test
    void getNeighborsNonexistent() {
        assertThrows(IllegalArgumentException.class, () -> graphMatrix.getNeighbors(5));
    }

    @Test
    void retrieveAllEdges() {
        graphMatrix.addVertex(4);
        graphMatrix.addEdge(1, 2);
        graphMatrix.addEdge(1, 3);
        graphMatrix.addEdge(2, 3);

        Map<Integer, List<Integer>> edges = graphMatrix.allEdges();
        assertEquals(edges.toString(), "{1=[2, 3], 2=[3], 3=[], 4=[]}");
    }

    @Test
    void testSelfEquality() {
        assertTrue(graphMatrix.equals(graphMatrix));
    }

    @Test
    void equalityWithNewIncMatrix() {
        incidenceMatrix newMatrix = new incidenceMatrix(3);
        newMatrix.addVertex(1);
        newMatrix.addVertex(2);
        newMatrix.addVertex(3);
        newMatrix.addEdge(3, 1);

        graphMatrix.addEdge(3, 1);
        assertEquals(graphMatrix, newMatrix);
    }

    @Test
    void testNotEqualToDifferentClass() {
        List<Integer> sampleList = new ArrayList<>();
        assertNotEquals(graphMatrix, sampleList);
    }

    @Test
    void equalityWithAdjListTrue() {
        adjacencyList adjList = new adjacencyList();
        adjList.addVertex(1);
        adjList.addVertex(2);
        adjList.addVertex(3);
        adjList.addEdge(3, 1);

        graphMatrix.addEdge(3, 1);
        assertEquals(graphMatrix, adjList);
    }

    @Test
    void inequalityWithModifiedAdjList() {
        adjacencyList adjList = new adjacencyList();
        adjList.addVertex(1);
        adjList.addVertex(2);
        adjList.addVertex(3);
        adjList.addEdge(3, 1);
        adjList.addEdge(1, 2);

        graphMatrix.addEdge(3, 1);
        assertNotEquals(graphMatrix, adjList);
    }

    @Test
    void equalityWithAdjacencyMatrixTrue() {
        adjacencyMatrix adjMatrix = new adjacencyMatrix(3);
        adjMatrix.addVertex(1);
        adjMatrix.addVertex(2);
        adjMatrix.addVertex(3);
        adjMatrix.addEdge(3, 1);

        graphMatrix.addEdge(3, 1);
        assertEquals(graphMatrix, adjMatrix);
    }

    @Test
    void inequalityWithDifferentAdjMatrix() {
        adjacencyMatrix adjMatrix = new adjacencyMatrix(3);
        adjMatrix.addVertex(1);
        adjMatrix.addVertex(2);
        adjMatrix.addVertex(3);
        adjMatrix.addEdge(3, 1);
        adjMatrix.addEdge(1, 2);

        graphMatrix.addEdge(3, 1);
        assertNotEquals(graphMatrix, adjMatrix);
    }

    @Test
    void performTopologicalSort() {
        graphMatrix.addVertex(4);
        graphMatrix.addVertex(5);
        graphMatrix.addVertex(6);
        graphMatrix.addVertex(7);

        graphMatrix.addEdge(1, 7);
        graphMatrix.addEdge(2, 4);
        graphMatrix.addEdge(2, 3);
        graphMatrix.addEdge(3, 4);
        graphMatrix.addEdge(3, 5);
        graphMatrix.addEdge(3, 7);
        graphMatrix.addEdge(4, 5);

        assertEquals(graphMatrix.topologicalSort().toString(), "[6, 2, 3, 4, 5, 1, 7]");
    }

    @Test
    void topologicalSortWithCycle() {
        graphMatrix.addEdge(1, 2);
        graphMatrix.addEdge(2, 3);
        graphMatrix.addEdge(3, 1);

        assertEquals(graphMatrix.topologicalSort().toString(), "[]");
    }

    @Test
    void fileReadingTest() throws IOException {
        graphMatrix = new incidenceMatrix(1);
        graphMatrix.readFromFile("src/test/java/ru/nsu/pronin/testGraph.txt");

        assertEquals(graphMatrix.toString(), "{1=[], 2=[3], 3=[], 4=[1]}");
    }
}
