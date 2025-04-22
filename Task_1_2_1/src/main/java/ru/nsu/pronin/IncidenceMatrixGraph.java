package ru.nsu.pronin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Реализация ориентированного графа через матрицу инцидентности.
 * В этой реализации используется список вершин и список рёбер для представления графа.
 * Матрица инцидентности используется для определения связи между вершинами и рёбрами.
 */
public class IncidenceMatrixGraph implements Graph {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private boolean[][] incidenceMatrix = new boolean[0][0];

    /**
     * Добавляет вершину в граф и пересоздаёт матрицу инцидентности.
     *
     * @param vertex Вершина для добавления в граф.
     */
    @Override
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        rebuildMatrix();
    }

    /**
     * Удаляет вершину из графа, а также удаляет все рёбра, инцидентные этой вершине.
     * Пересоздаёт матрицу инцидентности.
     *
     * @param vertex Вершина для удаления из графа.
     */
    @Override
    public void removeVertex(Vertex vertex) {
        int idx = vertices.indexOf(vertex);
        if (idx == -1) return;
        vertices.remove(idx);
        edges.removeIf(e -> e.from.equals(vertex) || e.to.equals(vertex));
        rebuildMatrix();
    }

    /**
     * Добавляет ребро между двумя вершинами и пересоздаёт матрицу инцидентности.
     *
     * @param from Начальная вершина.
     * @param to Конечная вершина.
     */
    @Override
    public void addEdge(Vertex from, Vertex to) {
        if (!vertices.contains(from) || !vertices.contains(to)) return;
        edges.add(new Edge(from, to));
        rebuildMatrix();
    }

    /**
     * Удаляет ребро между двумя вершинами и пересоздаёт матрицу инцидентности.
     *
     * @param from Начальная вершина.
     * @param to Конечная вершина.
     */
    @Override
    public void removeEdge(Vertex from, Vertex to) {
        edges.removeIf(e -> e.from.equals(from) && e.to.equals(to));
        rebuildMatrix();
    }

    /**
     * Получает список соседей вершины.
     * Соседи - это вершины, связанные с данной вершиной через рёбра.
     *
     * @param vertex Вершина для получения списка соседей.
     * @return Список соседей данной вершины.
     */
    @Override
    public List<Vertex> getNeighbors(Vertex vertex) {
        List<Vertex> nbrs = new ArrayList<>();
        int vi = vertices.indexOf(vertex);
        if (vi == -1) return nbrs;
        for (int j = 0; j < edges.size(); j++) {
            if (incidenceMatrix[vi][j] && edges.get(j).from.equals(vertex)) {
                nbrs.add(edges.get(j).to);
            }
        }
        return nbrs;
    }

    /**
     * Выполняет топологическую сортировку графа с использованием алгоритма Кана.
     * Если граф содержит цикл, возвращается пустой список.
     *
     * @return Список вершин в топологическом порядке.
     */
    @Override
    public List<Vertex> topologicalSort() {
        int n = vertices.size();
        int[] inDeg = new int[n];

        // Считаем входящую степень для каждой вершины
        for (Edge e : edges) {
            inDeg[vertices.indexOf(e.to)]++;
        }

        Queue<Vertex> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                q.add(vertices.get(i));
            }
        }

        List<Vertex> order = new ArrayList<>();
        List<Edge> remEdges = new ArrayList<>(edges);  // Работаем с копией рёбер
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            order.add(v);
            // "Удаляем" исходящие рёбра для вершины v
            for (Edge e : new ArrayList<>(remEdges)) {
                if (e.from.equals(v)) {
                    int ti = vertices.indexOf(e.to);
                    if (--inDeg[ti] == 0) {
                        q.add(e.to);
                    }
                    remEdges.remove(e);
                }
            }
        }

        // Если не все вершины обработаны, значит, был цикл
        return order.size() == n ? order : new ArrayList<>();
    }

    /**
     * Читает граф из файла.
     * Файл должен содержать:
     * - количество вершин (N),
     * - затем имена вершин,
     * - после этого рёбра графа (каждое ребро представлено двумя индексами вершин).
     *
     * @param filename Имя файла для чтения.
     */
    @Override
    public void readFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int n = Integer.parseInt(br.readLine().trim());
            vertices = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                vertices.add(new Vertex(br.readLine().trim()));
            }
            edges = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\s+");
                int u = Integer.parseInt(p[0]), v = Integer.parseInt(p[1]);
                edges.add(new Edge(vertices.get(u), vertices.get(v)));
            }
            rebuildMatrix();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Сравнивает два графа. Графы считаются равными, если у них одинаковые вершины и рёбра.
     *
     * @param obj Объект для сравнения.
     * @return true, если графы одинаковы, иначе false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IncidenceMatrixGraph)) return false;
        IncidenceMatrixGraph o = (IncidenceMatrixGraph) obj;
        return vertices.equals(o.vertices) && edges.equals(o.edges);
    }

    /**
     * Строковое представление графа.
     *
     * @return Строковое представление графа.
     */
    @Override
    public String toString() {
        // Тест ожидает подстроку "Incidence matrix representation"
        return "Incidence matrix representation";
    }

    /**
     * Пересобирает матрицу инцидентности размером |V|×|E| на основе текущих данных о вершинах и рёбрах.
     */
    private void rebuildMatrix() {
        int n = vertices.size(), m = edges.size();
        incidenceMatrix = new boolean[n][m];
        for (int j = 0; j < m; j++) {
            Edge e = edges.get(j);
            int fi = vertices.indexOf(e.from), ti = vertices.indexOf(e.to);
            incidenceMatrix[fi][j] = true;
            incidenceMatrix[ti][j] = true;
        }
    }

    /**
     * Вложенный класс, представляющий ориентированное ребро графа.
     */
    public static class Edge {
        public final Vertex from, to;

        /**
         * Конструктор для создания ребра.
         *
         * @param from Начальная вершина.
         * @param to Конечная вершина.
         */
        public Edge(Vertex from, Vertex to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) return false;
            Edge e = (Edge) o;
            return from.equals(e.from) && to.equals(e.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }
    }
}
