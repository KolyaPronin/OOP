package ru.nsu.pronin;

/**
 * This class implements the heap sort functionality.
 */
public class HeapSort {
    /**
     * Heap data structure.
     */
    public static class Heap {
        int size;
        int capacity;
        int[] heap;

        /**
         * Constructor for creating a heap object with a given capacity.
         *
         * @param capacity the initial capacity of the heap.
         */
        public Heap(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            this.heap = new int[capacity];
        }
    }

    /**
     * Creates a new heap with the given capacity.
     *
     * @param capacity the capacity of the heap to be created.
     * @return the new heap object.
     */
    static Heap createHeap(int capacity) {
        Heap temp = new Heap(capacity);
        temp.size = 0;
        temp.capacity = capacity;
        return temp;
    }

    /**
     * Swaps two heap elements.
     *
     * @param obj heap object.
     * @param x index of the first element.
     * @param y index of the second element.
     */
    static void swap(Heap obj, int x, int y) {
        int temp = obj.heap[x];
        obj.heap[x] = obj.heap[y];
        obj.heap[y] = temp;
    }

    /**
     * Calculates the index of the parent element for a heap node.
     *
     * @param v node index.
     * @return index of the parent.
     */
    static int parent(int v) {
        return (v - 1) / 2;
    }

    /**
     * Calculates the index of the left child of a node in the heap.
     *
     * @param obj heap object.
     * @param v node index.
     * @return the index of the left child, or -1 if there is no child.
     */
    static int leftChild(Heap obj, int v) {
        if (obj.size < v * 2 + 1) {
            return -1;
        }
        return v * 2 + 1;
    }

    /**
     * Calculates the index of the right child of a node in the heap.
     *
     * @param obj heap object.
     * @param v node index.
     * @return the index of the right child, or -1 if there is no child.
     */
    static int rightChild(Heap obj, int v) {
        if (obj.size < v * 2 + 2) {
            return -1;
        }
        return v * 2 + 2;
    }

    /**
     * Moves an element up the heap to preserve the minimum heap property.
     *
     * @param obj heap object.
     * @param v index of the element to lift.
     */
    static void siftUp(Heap obj, int v) {
        if (v == 0) {
            return;
        }

        if (obj.heap[v] < obj.heap[parent(v)]) {
            swap(obj, v, parent(v));
            siftUp(obj, parent(v));
        }
    }

    /**
     * Moves an element down the heap to preserve the minimum heap property.
     *
     * @param obj heap object.
     * @param ind index of the element to omit.
     */
    static void siftDown(Heap obj, int ind) {
        int leftChild = leftChild(obj, ind);
        int rightChild = rightChild(obj, ind);
        int min = ind;

        if (leftChild != -1 && leftChild < obj.size && obj.heap[leftChild] < obj.heap[min]) {
            min = leftChild;
        }

        if (rightChild != -1 && rightChild < obj.size && obj.heap[rightChild] < obj.heap[min]) {
            min = rightChild;
        }

        if (min != ind) {
            swap(obj, min, ind);
            siftDown(obj, min);
        }
    }

    /**
     * Retrieves the minimum element from the heap.
     *
     * @param obj heap object
     * @return minimum element
     */
    static int extractMin(Heap obj) {
        int answer = obj.heap[0];
        obj.heap[0] = obj.heap[--obj.size];
        siftDown(obj, 0);
        return answer;
    }

    /**
     * Adds an element to the heap.
     *
     * @param obj heap object
     * @param value the value to add
     */
    static void add(Heap obj, int value) {
        obj.heap[obj.size] = value;
        obj.size++;
        siftUp(obj, obj.size - 1);
    }

    /**
     * Sorts an array of numbers using a heap data structure.
     *
     * @param numbers an array of numbers to sort
     * @return sorted array of numbers
     */
    static int[] sortArray(int[] numbers) {
        int n = numbers.length;
        Heap tmp = createHeap(n);
        for (int num : numbers) {
            add(tmp, num);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = extractMin(tmp);
        }

        return res;
    }
}
