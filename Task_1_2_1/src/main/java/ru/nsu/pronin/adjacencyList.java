package ru.nsu.pronin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Implementation of a graph using an adjacency list.
 */
public class adjacencyList implements Graph {

    private Map<Integer, List<Integer>> adjList;

    /**
     * Constructor.
     */
    public adjacencyList() {
        adjList = new HashMap<>();
    }

    /**
     * Adds a new vertex.
     *
     * @param vertex - new vertex.
     */
    @Override
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Adds a new vertex.
     *
     * @param vertex - new vertex.
     */
    @Override
    public void deleteVertex(int vertex) {
        if (!adjList.containsKey(vertex) || adjList.get(vertex) == null) {
            throw new IllegalArgumentException("Такой вершины не существует");
        }

        adjList.remove(vertex);
        for (List<Integer> neighbors : adjList.values()) {
            neighbors.remove(Integer.valueOf(vertex));
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
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            throw new IllegalArgumentException("Одной из вершин не существует");
        }
        List<Integer> neighbors = adjList.get(from);
        if (!neighbors.contains(to)) {
            neighbors.add(to);
        }
    }

    /**
     *Deletes an edge.
     *
     * @param from - where from.
     * @param to - where.
     */
    @Override
    public void deleteEdge(int from, int to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            throw new IllegalArgumentException("Одной из вершин не существует");
        }

        List<Integer> neighbors = adjList.get(from);
        if (neighbors != null) {
            neighbors.remove(Integer.valueOf(to));
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
        if (!adjList.containsKey(vertex) || adjList.get(vertex) == null) {
            throw new IllegalArgumentException("Такой вершины не существует");
        }
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    /**
     * Returns all edges.
     *
     * @return - all edges as a hash map.
     */
    @Override
    public Map<Integer, List<Integer>> allEdges() {
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
        return adjList.toString();
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
        List<Integer> topoList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean hasCycle = false;

        for (Integer vertex : adjList.keySet()) {
            status.put(vertex, 0);
        }


        for (Integer vertex : adjList.keySet()) {
            if (status.get(vertex) == 0) {
                hasCycle = dfs(vertex, status, stack);
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

        for (Integer neighbor : adjList.get(vertex)) {
            if (status.get(neighbor) == 0) {
                if (dfs(neighbor, status, stack)) {
                    return true;
                }
            } else if (status.get(neighbor) == 1) {
                return true;
            }
        }


        status.put(vertex, 2);
        stack.push(vertex);
        return false;
    }

}