package ru.nsu.pronin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Implementation of a graph using an Incidence Matrix.
 */
public class IncidenceMatrix implements Graph {
    int[][] incMtx;
    private int verCount;
    private int edgeCount;

    /**
     * Constructor.
     *
     * @param newVerCount - number of vertices.
     */
    public IncidenceMatrix(int newVerCount) {
        if (newVerCount <= 0) {
            throw new IllegalArgumentException("Некорректное количество вершин.");
        }
        this.verCount = newVerCount;
        this.edgeCount = 0;
        this.incMtx = new int[newVerCount + 1][0];
    }

    /**
     * Adds a new vertex.
     *
     * @param vertex - new vertex.
     */
    @Override
    public void addVertex(int vertex) {
        if (vertex > verCount) {
            int[][] newMatrix = new int[vertex + 1][edgeCount];
            for (int i = 0; i <= verCount; i++) {
                System.arraycopy(incMtx[i], 0, newMatrix[i], 0, edgeCount);
            }
            incMtx = newMatrix;
            verCount = vertex;
        }
    }

    /**
     * Removes a vertex.
     *
     * @param vertex - vertex number.
     */
    @Override
    public void deleteVertex(int vertex) {
        if (vertex > verCount) {
            throw new IllegalArgumentException("Вершина не существует.");
        }

        int[][] newMatrix = new int[verCount][edgeCount];
        int l = 0;
        for (int i = 0; i <= verCount; i++) {
            if (i != vertex) {
                System.arraycopy(incMtx[i], 0, newMatrix[l++], 0, edgeCount);
            }
        }
        incMtx = newMatrix;
        verCount--;
    }

    /**
     * Adds an edge.
     *
     * @param from - where from.
     * @param to - where.
     */
    @Override
    public void addEdge(int from, int to) {
        if (from > verCount || to > verCount) {
            throw new IllegalArgumentException("Вершина вне диапазона.");
        }

        int[][] newMatrix = new int[verCount + 1][edgeCount + 1];
        for (int i = 0; i <= verCount; i++) {
            System.arraycopy(incMtx[i], 0, newMatrix[i], 0, edgeCount);
        }

        // Для ориентированного графа добавляем ребро только от `from` к `to`
        newMatrix[from][edgeCount] = 1;
        newMatrix[to][edgeCount] = -1;

        incMtx = newMatrix;
        edgeCount++;
    }

    /**
     *Deletes an edge.
     *
     * @param from - where from.
     * @param to - where.
     */
    @Override
    public void deleteEdge(int from, int to) {
        if (from > verCount || to > verCount) {
            throw new IllegalArgumentException("Вершина вне диапазона.");
        }

        for (int i = 0; i < edgeCount; i++) {
            if (incMtx[from][i] == 1 && incMtx[to][i] == -1) {
                int[][] newMatrix = new int[verCount + 1][edgeCount - 1];
                for (int j = 0; j <= verCount; j++) {
                    System.arraycopy(incMtx[j], 0, newMatrix[j], 0, i);
                    if (i < edgeCount - 1) {
                        System.arraycopy(incMtx[j], i + 1, newMatrix[j], i, edgeCount - i - 1);
                    }
                }
                incMtx = newMatrix;
                edgeCount--;
                return;
            }
        }
    }

    /**
     * Returns a list of neighbors.
     *
     * @param vertex - vertex number.
     * @return - neighbors of this vertex.
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        if (vertex > verCount) {
            throw new IllegalArgumentException("Вершина не существует.");
        }
        for (int i = 0; i < edgeCount; i++) {
            if (incMtx[vertex][i] == 1) {
                for (int j = 0; j <= verCount; j++) {
                    if (incMtx[j][i] == -1) {
                        neighbors.add(j);
                    }
                }
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
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 1; i <= verCount; i++) {
            edges.put(i, getNeighbors(i));
        }
        return edges;
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
        List<Integer> topoList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean hasCycle = false;

        for (int i = 1; i <= verCount; i++) {
            status.put(i, 0);
        }

        for (int i = 1; i <= verCount; i++) {
            if (status.get(i) == 0) {
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

        for (int i = 0; i < edgeCount; i++) {
            if (incMtx[vertex][i] == 1) {
                for (int j = 1; j <= verCount; j++) {
                    if (incMtx[j][i] == -1) {
                        if (status.get(j) == 0) {
                            if (dfs(j, status, stack)) {
                                return true;
                            }
                        } else if (status.get(j) == 1) {
                            return true;
                        }
                    }
                }
            }
        }

        status.put(vertex, 2);
        stack.push(vertex);
        return false;
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
}
