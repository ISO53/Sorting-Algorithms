package com.mycompany.sortingalgorithms;

/**
 *
 * @author Yusuf Ihsan Simsek
 */
public class SelectionSort {

    public static int[] selectionSort(int array[], int length) {
        for (int i = 0; i < length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
