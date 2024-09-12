package ru.nsu.pronin;

import java.util.Scanner;

public class Main {
        public static class Heap {
            int size;
            int capacity;
            int[] heap;

            public Heap(int capacity){
                this.size = 0;
                this.capacity = capacity;
                this.heap = new int[capacity];
            }
        }

        static Heap createHeap(int capacity){
            Heap temp = new Heap(capacity); // так память выделяется. Ключевое слово new
            temp.size = 0;
            temp.capacity = capacity;
            return temp;
        }

        static void swap(Heap obj,int x,int y){
            int temp = obj.heap[x];
            obj.heap[x] = obj.heap[y];
            obj.heap[y] = temp;
        }

        static int parent(int v){

            return (v-1)/2;
        }

        static int left_child(Heap obj, int v){

            if (obj.size < v * 2 + 1)
                return -1;
            return v*2 +1;
        }

        static int right_child(Heap obj, int v){
            if (obj.size < v * 2 + 2)
                return -1;
            return v * 2 + 2;
        }

        static void siftUp(Heap obj, int v) {
            if (v == 0) {
                return;
            }
            if (obj.heap[v] < obj.heap[parent(v)]) {
                swap(obj, v, parent(v));
                siftUp(obj, parent(v));
            }
        }

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



        static int extractMin(Heap obj) {
            int answer = obj.heap[0];
            obj.heap[0] = obj.heap[--obj.size];
            siftDown(obj, 0);
            return answer;
        }

        static void add(Heap obj, int value) {
            obj.heap[obj.size] = value;
            obj.size++;
            siftUp(obj, obj.size - 1);
        }

        static void deleteHeap(Heap obj) {
            obj.heap = null;
        }

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
        public static void main(String[] args) {

            Scanner in = new Scanner(System.in);

            int N = in.nextInt();
            int[] array = new int[N];
            for(int i = 0;i<N;i++){
                array[i] = in.nextInt();
            }
            int[] sortedArray = sortArray(array);

            for (int i = 0; i < N; i++) {
                System.out.print(sortedArray[i] + " ");
            }
        }

}