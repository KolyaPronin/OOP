package ru.nsu.pronin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Реализация ориентированного графа через матрицу смежности.
 * Граф представлен как список вершин и матрица смежности для каждого ребра.
 */
public class AdjacencyMatrixGraph implements Graph {
    private List<Vertex> vertices;       // список вершин
    private boolean[][] adjacencyMatrix; // матрица смежности (adjacencyMatrix[i][j] = true, если есть ребро i→j)

    /**
     * Конструктор по умолчанию, создающий пустой граф.
     */
    public AdjacencyMatrixGraph() {
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = new boolean[0][0];
    }

    /**
     * Добавляет вершину в граф и перестраивает матрицу смежности.
     *
     * @param vertex вершина, которую нужно добавить.
     */
    @Override
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
        rebuildMatrix();
    }

    /**
     * Удаляет вершину из графа и пересоздаёт матрицу смежности, сдвигая все строки и столбцы.
     *
     * @param vertex вершина, которую нужно удалить.
     */
    @Override
    public void removeVertex(Vertex vertex) {
        int idx = vertices.indexOf(vertex);
        if (idx == -1) return;
        vertices.remove(idx);

        int n = vertices.size() + 1; // старый размер
        boolean[][] old = adjacencyMatrix;
        if (n <= 1) {
            adjacencyMatrix = new boolean[0][0];
            return;
        }
        boolean[][] next = new boolean[n - 1][n - 1];
        for (int i = 0, ni = 0; i < n; i++) {
            if (i == idx) continue;
            for (int j = 0, nj = 0; j < n; j++) {
                if (j == idx) continue;
                next[ni][nj] = old[i][j];
                nj++;
            }
            ni++;
        }
        adjacencyMatrix = next;
    }

    /**
     * Добавляет ребро от вершины from к вершине to в матрице смежности.
     *
     * @param from вершина-источник.
     * @param to   вершина-назначение.
     */
    @Override
    public void addEdge(Vertex from, Vertex to) {
        int i = vertices.indexOf(from), j = vertices.indexOf(to);
        if (i != -1 && j != -1) {
            adjacencyMatrix[i][j] = true;
        }
    }

    /**
     * Удаляет ребро от вершины from к вершине to из матрицы смежности.
     *
     * @param from вершина-источник.
     * @param to   вершина-назначение.
     */
    @Override
    public void removeEdge(Vertex from, Vertex to) {
        int i = vertices.indexOf(from), j = vertices.indexOf(to);
        if (i != -1 && j != -1) {
            adjacencyMatrix[i][j] = false;
        }
    }

    /**
     * Возвращает список соседей для заданной вершины.
     *
     * @param vertex вершина, для которой нужно получить соседей.
     * @return список соседей.
     */
    @Override
    public List<Vertex> getNeighbors(Vertex vertex) {
        List<Vertex> res = new ArrayList<>();
        int i = vertices.indexOf(vertex);
        if (i == -1) return res;
        for (int j = 0; j < adjacencyMatrix.length; j++) {
            if (adjacencyMatrix[i][j]) {
                res.add(vertices.get(j));
            }
        }
        return res;
    }

    /**
     * Выполняет топологическую сортировку графа с использованием алгоритма Кана.
     * Если граф не является ациклическим направленным графом (DAG),
     * возвращает пустой список.
     *
     * @return топологически отсортированный список вершин, или пустой список, если граф имеет цикл.
     */
    @Override
    public List<Vertex> topologicalSort() {
        int n = vertices.size();
        int[] inDeg = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[j][i]) inDeg[i]++;
            }
        }

        Queue<Vertex> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) q.add(vertices.get(i));
        }

        List<Vertex> order = new ArrayList<>();
        while (!q.isEmpty()) {
            Vertex v = q.poll();
            order.add(v);
            int vi = vertices.indexOf(v);
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[vi][j] && --inDeg[j] == 0) {
                    q.add(vertices.get(j));
                }
            }
        }

        return order.size() == n ? order : new ArrayList<>();
    }

    /**
     * Читает граф из файла.
     * Формат файла:
     * N
     * name0
     * name1
     * ...
     * u v      // ребро от u к v (индексы)
     *
     * @param filename имя файла для чтения.
     */
    @Override
    public void readFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int n = Integer.parseInt(br.readLine().trim());
            vertices = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                vertices.add(new Vertex(br.readLine().trim()));
            }
            adjacencyMatrix = new boolean[n][n];
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\s+");
                int u = Integer.parseInt(p[0]);
                int v = Integer.parseInt(p[1]);
                adjacencyMatrix[u][v] = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сравнивает два графа на равенство. Графы считаются равными, если:
     * 1. У них одинаковое количество вершин.
     * 2. У них одинаковые рёбра.
     *
     * @param obj объект для сравнения.
     * @return true, если графы равны, иначе false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdjacencyMatrixGraph)) return false;
        AdjacencyMatrixGraph o = (AdjacencyMatrixGraph) obj;
        return vertices.equals(o.vertices)
                && Arrays.deepEquals(adjacencyMatrix, o.adjacencyMatrix);
    }

    /**
     * Возвращает строковое представление графа в формате:
     * "AdjacencyMatrixGraph\nV: [список вершин]\nM:\n[матрица смежности]".
     *
     * @return строковое представление графа.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AdjacencyMatrixGraph\nV: " + vertices + "\nM:\n");
        for (boolean[] row : adjacencyMatrix) {
            for (boolean b : row) {
                sb.append(b ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Перестраивает матрицу смежности на текущее количество вершин, сохраняя старые рёбра.
     */
    private void rebuildMatrix() {
        int n = vertices.size();
        boolean[][] old = adjacencyMatrix;
        adjacencyMatrix = new boolean[n][n];
        for (int i = 0; i < old.length && i < n; i++) {
            for (int j = 0; j < old.length && j < n; j++) {
                adjacencyMatrix[i][j] = old[i][j];
            }
        }
    }
}
