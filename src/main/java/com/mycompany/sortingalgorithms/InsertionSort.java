package com.mycompany.sortingalgorithms;

/**
 *
 * @author Yusuf Ihsan Simsek
 */
public class InsertionSort {

    public static void insertionSort(int array[], int length) {
        for (int i = 1; i < length; ++i) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
