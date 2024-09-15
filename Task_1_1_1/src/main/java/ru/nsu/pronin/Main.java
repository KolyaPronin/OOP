package ru.nsu.pronin;

import java.util.Scanner;

/**
 * The main program class that implements array sorting using the heap.
 */

public class Main {
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
        static void swap(Heap obj,int x,int y) {
            int temp = obj.heap[x];
            obj.heap[x] = obj.heap[y];
            obj.heap[y] = temp;
        }

        /**
         * Вычисляет индекс родительского элемента для узла кучи.
         *
         * @param v индекс узла.
         * @return индекс родителя.
         */
        static int parent(int v) {
            return (v-1)/2;
        }

        /**
         * Вычисляет индекс левого потомка узла в куче.
         *
         * @param obj объект кучи.
         * @param v индекс узла.
         * @return индекс левого потомка или -1, если потомок отсутствует.
         */
        static int left_child(Heap obj, int v) {
            if (obj.size < v * 2 + 1)
                return -1;
            return v*2 +1;
        }

        /**
         * Вычисляет индекс правого потомка узла в куче.
         *
         * @param obj объект кучи.
         * @param v индекс узла.
         * @return индекс правого потомка или -1, если потомок отсутствует.
         */
        static int right_child(Heap obj, int v) {
            if (obj.size < v * 2 + 2)
                return -1;
            return v * 2 + 2;
        }

        /**
         * Поднимает элемент вверх по куче для сохранения свойства минимальной кучи.
         *
         * @param obj объект кучи.
         * @param v индекс элемента для подъема.
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
         * Опускает элемент вниз по куче для сохранения свойства минимальной кучи.
         *
         * @param obj объект кучи.
         * @param ind индекс элемента для опускания.
         */
        static void siftDown(Heap obj, int ind) {
            int leftChild = left_child(obj, ind);
            int rightChild = right_child(obj, ind);
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
         * Deletes a heap.
         *
         * @param obj heap object
         */
        static void deleteHeap(Heap obj) {
            obj.heap = null;
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

            deleteHeap(tmp);
            return res;
        }

        /**
         * The main method of the program.
         * Performs reading an array of numbers, sorting the array and outputting the sorted numbers.
         *
         * @param args command line arguments (not used).
         */
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int N = in.nextInt();
            int[] array = new int[N];
            for(int i = 0; i < N; i++){
                array[i] = in.nextInt();
            }
            int[] sortedArray = sortArray(array);

            for (int i = 0; i < N; i++) {
                System.out.print(sortedArray[i] + " ");
            }
        }

}