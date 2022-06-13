package com.mycompany.sortingalgorithms;

/**
 *
 * @author Yusuf Ihsan Simsek
 */
public class BubbleSort {

    public static int[] bubbleSort(int array[], int length) {
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
