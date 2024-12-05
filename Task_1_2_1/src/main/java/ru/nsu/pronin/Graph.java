package ru.nsu.pronin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface for implementing an undirected graph, without multiple edges.
 */
public interface Graph {
    /**
     * Adds a new vertex.
     *
     * @param vertex - new vertex.
     */
    void addVertex(int vertex);

    /**
     * Removes a vertex.
     *
     * @param vertex - vertex number.
     */
    void deleteVertex(int vertex);

    /**
     * Adds an edge.
     *
     * @param from - where from.
     * @param to - where.
     */
    void addEdge(int from, int to);

    /**
     *Deletes an edge.
     *
     * @param from - where from.
     * @param to - where.
     */
    void deleteEdge(int from, int to);

    /**
     * Returns a list of neighbors.
     *
     * @param vertex - vertex number.
     * @return - neighbors of this vertex.
     */
    List<Integer> getNeighbors(int vertex);

    /**
     * Returns all edges.
     *
     * @return - all edges as a hash map.
     */
    Map<Integer, List<Integer>> allEdges();

    /**
     * Reads a graph from a file.
     * ver1 ver2 - then this is an edge.
     * ver1 is the top.
     *
     * @param filename - file name.
     * @throws IOException - returns an exception.
     */
    void readFromFile(String filename) throws IOException;

    /**
     * Converts a graph to a string.
     *
     * @return the string.
     */
    String toString();

    /**
     * Compares two graphs.
     *
     * @param obj what to compare with.
     * @return true/false.
     */
    boolean equals(Object obj);

    /**
     * Topological sorting.
     *
     * @return a list of sorted vertices.
     */
    List<Integer> topologicalSort();
}
