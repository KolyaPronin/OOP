package ru.nsu.pronin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Реализация ориентированного графа через список смежности.
 * Граф представлен как список вершин и список смежности для каждой вершины.
 */
public class AdjacencyListGraph implements Graph {
    private List<Vertex> vertices = new ArrayList<>();
    private List<List<Vertex>> adjacencyList = new ArrayList<>();

    /**
     * Добавляет вершину в граф.
     *
     * @param vertex вершина для добавления в граф.
     */
    @Override
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        adjacencyList.add(new ArrayList<>());
    }

    /**
     * Удаляет вершину из графа и все рёбра, инцидентные этой вершине.
     *
     * @param vertex вершина, которую необходимо удалить.
     */
    @Override
    public void removeVertex(Vertex vertex) {
        int idx = vertices.indexOf(vertex);
        if (idx == -1) return;
        vertices.remove(idx);
        adjacencyList.remove(idx);
        // Удаляем все упоминания в остальных списках
        for (List<Vertex> nbrs : adjacencyList) {
            nbrs.remove(vertex);
        }
    }

    /**
     * Добавляет ориентированное ребро от вершины from к вершине to.
     *
     * @param from вершина-источник ребра.
     * @param to   вершина-назначение ребра.
     */
    @Override
    public void addEdge(Vertex from, Vertex to) {
        int i = vertices.indexOf(from);
        int j = vertices.indexOf(to);
        if (i != -1 && j != -1) {
            adjacencyList.get(i).add(to);
        }
    }

    /**
     * Удаляет ориентированное ребро от вершины from к вершине to.
     *
     * @param from вершина-источник ребра.
     * @param to   вершина-назначение ребра.
     */
    @Override
    public void removeEdge(Vertex from, Vertex to) {
        int i = vertices.indexOf(from);
        if (i != -1) {
            adjacencyList.get(i).remove(to);
        }
    }

    /**
     * Возвращает список соседей (вершин, смежных с данной вершиной).
     *
     * @param vertex вершина, для которой требуется список соседей.
     * @return список соседей вершины.
     */
    @Override
    public List<Vertex> getNeighbors(Vertex vertex) {
        int i = vertices.indexOf(vertex);
        return i == -1
                ? new ArrayList<>()
                : new ArrayList<>(adjacencyList.get(i));  // возвращаем копию
    }

    /**
     * Выполняет топологическую сортировку графа.
     * Если граф содержит цикл, возвращает пустой список.
     *
     * @return топологически отсортированный список вершин, или пустой список в случае цикла.
     */
    @Override
    public List<Vertex> topologicalSort() {
        int n = vertices.size();
        int[] inDeg = new int[n];
        // считаем входящие рёбра
        for (int u = 0; u < n; u++) {
            for (Vertex v : adjacencyList.get(u)) {
                int vi = vertices.indexOf(v);
                inDeg[vi]++;
            }
        }
        // находим вершины с inDeg==0
        List<Vertex> order = new ArrayList<>();
        LinkedList<Vertex> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) queue.add(vertices.get(i));
        }
        while (!queue.isEmpty()) {
            Vertex u = queue.removeFirst();
            order.add(u);
            int ui = vertices.indexOf(u);
            for (Vertex v : new ArrayList<>(adjacencyList.get(ui))) {
                int vi = vertices.indexOf(v);
                if (--inDeg[vi] == 0) {
                    queue.add(v);
                }
            }
        }
        return order.size() == n ? order : new ArrayList<>();
    }

    /**
     * Читает граф из файла.
     * Формат файла:
     * - Первая строка: количество вершин.
     * - Далее: имена вершин.
     * - Остальные строки: рёбра графа в формате "u v" (ребро от вершины u к вершине v).
     *
     * @param filename имя файла для чтения.
     * @throws RuntimeException если произошла ошибка при чтении файла.
     */
    @Override
    public void readFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int n = Integer.parseInt(br.readLine().trim());
            vertices = new ArrayList<>(n);
            adjacencyList = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                vertices.add(new Vertex(br.readLine().trim()));
                adjacencyList.add(new ArrayList<>());
            }
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\s+");
                int u = Integer.parseInt(p[0]), v = Integer.parseInt(p[1]);
                adjacencyList.get(u).add(vertices.get(v));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Сравнивает два графа на равенство.
     * Два графа считаются равными, если у них одинаковое количество вершин и рёбер,
     * и если для каждой вершины в одном графе существует соответствующая вершина в другом графе.
     *
     * @param obj объект для сравнения.
     * @return true, если графы одинаковы, иначе false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdjacencyListGraph)) return false;
        AdjacencyListGraph o = (AdjacencyListGraph) obj;
        return vertices.equals(o.vertices)
                && adjacencyList.equals(o.adjacencyList);
    }

    /**
     * Возвращает строковое представление графа.
     *
     * @return строковое представление графа.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Adjacency list representation\n");
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Adjacency:\n");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append("  ").append(vertices.get(i))
                    .append(" -> ").append(adjacencyList.get(i))
                    .append("\n");
        }
        return sb.toString();
    }
}
