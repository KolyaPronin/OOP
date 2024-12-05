package ru.nsu.pronin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Implementation of a graph using an Adjacency Matrix.
 */
public class AdjacencyMatrix implements Graph {
    int[][] adjMtx;
    int vertexCount;

    /**
     * Constructor.
     *
     * @param size number of vertices.
     */
    public AdjacencyMatrix(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Неправильное значение size");
        }
        vertexCount = size;
        adjMtx = new int[size + 1][size + 1];
    }


    /**
     * Adds a new vertex.
     *
     * @param vertex - new vertex.
     */
    @Override
    public void addVertex(int vertex) {
        if (vertex > vertexCount) {
            int[][] newMatrix = new int[vertex + 1][vertex + 1];
            for (int i = 0; i <= vertexCount; i++) {
                System.arraycopy(adjMtx[i], 0, newMatrix[i], 0, vertexCount + 1);
            }
            adjMtx = newMatrix;
            vertexCount = vertex;
        }
    }

    /**
     * Adds a new vertex.
     *
     * @param vertex - new vertex.
     */
    @Override
    public void deleteVertex(int vertex) {
        if (vertex > vertexCount) {
            throw new IllegalArgumentException("Нет такой вершины " + vertex);
        }
        for (int i = 1; i <= vertexCount; i++) {
            adjMtx[vertex][i] = 0;
            adjMtx[i][vertex] = 0;
        }
    }

    /**
     * Adds an edge.
     *
     * @param from - where from.
     * @param to - where.
     */
    @Override
    public void addEdge(int from, int to) {
        if (from > vertexCount || to > vertexCount) {
            throw new IllegalArgumentException("Нет одной из вершин");
        }
        adjMtx[from][to] = 1;  // Для ориентированного графа только from -> to
    }

    /**
     *Deletes an edge.
     *
     * @param from - where from.
     * @param to - where.
     */
    @Override
    public void deleteEdge(int from, int to) {
        if (from > vertexCount || to > vertexCount) {
            throw new IllegalArgumentException("Нет одной из вершин");
        }
        adjMtx[from][to] = 0;  // Удаляем направленное ребро from -> to
    }

    /**
     * Returns a list of neighbors.
     *
     * @param vertex - vertex number.
     * @return - neighbors of this vertex.
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        if (vertex > vertexCount) {
            throw new IllegalArgumentException("Нет такой вершины " + vertex);
        }

        List<Integer> neighbors = new ArrayList<>();
        for (int i = 1; i <= vertexCount; i++) {
            if (adjMtx[vertex][i] == 1) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    /**
     * Returns all edges.
     *
     * @return - all edges as a hash map.
     */
    @Override
    public Map<Integer, List<Integer>> allEdges() {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 1; i <= vertexCount; i++) {
            List<Integer> to = new ArrayList<>();
            for (int j = 1; j <= vertexCount; j++) {
                if (adjMtx[i][j] == 1) {
                    to.add(j);
                }
            }
            adjList.put(i, to);
        }
        return adjList;
    }

    /**
     * Reads a graph from a file.
     * ver1 ver2 - then this is an edge.
     * ver1 is the top.
     *
     * @param filename - file name.
     * @throws IOException - returns an exception.
     */
    @Override
    public void readFromFile(String filename) throws IOException {
        BufferedReader buf = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = buf.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 1) {
                addVertex(Integer.parseInt(parts[0]));
            } else {
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                addEdge(from, to);
            }
        }
    }

    /**
     * Converts a graph to a string.
     *
     * @return the string.
     */
    @Override
    public String toString() {
        return allEdges().toString();
    }

    /**
     * Compares two graphs.
     *
     * @param obj what to compare with.
     * @return true/false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph)) {
            return false;
        }

        return this.allEdges().equals(((Graph) obj).allEdges());
    }

    /**
     * Topological sorting.
     *
     * @return a list of sorted vertices.
     */
    @Override
    public List<Integer> topologicalSort() {
        Map<Integer, Integer> status = new HashMap<>();
        boolean hasCycle = false;
        List<Integer> topoList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= vertexCount; i++) {
            if (status.getOrDefault(i, 0) == 0) {
                hasCycle = dfs(i, status, stack);
                if (hasCycle) {
                    return new ArrayList<>();
                }
            }
        }

        while (!stack.isEmpty()) {
            topoList.add(stack.pop());
        }

        return topoList;
    }

    /**
     *DFS.
     *
     * @param vertex - vertex.
     * @param status - status.
     * @param stack - stack.
     * @return - true if there is a loop, false otherwise.
     */
    private boolean dfs(int vertex, Map<Integer, Integer> status, Stack<Integer> stack) {
        status.put(vertex, 1);

        for (int i = 1; i <= vertexCount; i++) {
            if (adjMtx[vertex][i] == 1) {
                if (status.getOrDefault(i, 0) == 0) {
                    if (dfs(i, status, stack)) {
                        return true;
                    }
                } else if (status.get(i) == 1) {
                    return true; // Цикл найден
                }
            }
        }

        status.put(vertex, 2);
        stack.push(vertex);
        return false;
    }
}
