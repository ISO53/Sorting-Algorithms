package com.mycompany.sortingalgorithms;

/**
 *
 * @author Yusuf Ihsan Simsek
 */
public class HeapSort {

    public static int[] heapSort(int[] array, int length) {
        int temp;
        int size = length - 1;
        for (int i = length / 2; i >= 0; i--) {
            heapify(array, i, size);
        }
        for (int i = size; i >= 0; i--) {
            temp = array[0];
            array[0] = array[size];
            array[size] = temp;
            size--;
            heapify(array, 0, size);
        }
        return array;
    }

    public static void heapify(int[] array, int i, int heapSize) {
        int a = 2 * i;
        int b = 2 * i + 1;
        int largestElement;
        if (a <= heapSize && array[a] > array[i]) {
            largestElement = a;
        } else {
            largestElement = i;
        }
        if (b <= heapSize && array[b] > array[largestElement]) {
            largestElement = b;
        }
        if (largestElement != i) {
            int temp = array[i];
            array[i] = array[largestElement];
            array[largestElement] = temp;
            heapify(array, largestElement, heapSize);
        }
    }
}
