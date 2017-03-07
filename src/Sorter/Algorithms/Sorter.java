package Sorter.Algorithms;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Cyriel on 7-3-2017.
 */
public class Sorter {

    ArrayList<Integer> tempNums;

    int p = 0;
    public ArrayList<Integer> oneStepInsertionSort(ArrayList<Integer> nums) {

        int key;
        int i;
        tempNums = nums;

        for (;p < tempNums.size(); p++) {
                key = tempNums.get(p);
                for (i = p - 1; (i >= 0) && (tempNums.get(i) > key); i--) {
                    tempNums.set(i + 1, tempNums.get(i));
                }
                tempNums.set(i + 1, key);
                p++;
                break;
        }
        return tempNums;
    }

    // Bubble sort is a O(N^2) Algorithm.
    public ArrayList<Integer> oneStepBubbleSort(ArrayList<Integer> nums) {
        tempNums = nums;
        int temp = 0;
        for (int j = 1; j < (tempNums.size()); j++) {
            if (tempNums.get(j - 1) > tempNums.get(j)) {
                temp = tempNums.get(j - 1);
                tempNums.set(j - 1, tempNums.get(j));
                tempNums.set(j, temp);
            }
        }
        return tempNums;
    }

    public ArrayList<Integer> oneStepQuickSort(ArrayList<Integer> nums) {
        tempNums = nums;
        return quickSort(0, tempNums.size() - 1);
    }

    private ArrayList<Integer> quickSort(int low, int high) {
        int i = low;
        int j = high;

        // pivot is middle index
        final int pivot = tempNums.get(low + (high - low) / 2);

        // Divide into two arrays
        while (i <= j) {
            while (tempNums.get(i) < pivot) {
                i++;
            }
            while (tempNums.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;

            }
        }
        if (low < j) {
            quickSort(low, j);
        }

        if (i < high) {
            quickSort(i, high);

        }

        return tempNums;
    }

    private void swap(int i, int j) {
        int temp = tempNums.get(i);
        tempNums.set(i, tempNums.get(j));
        tempNums.set(j, temp);
    }
}
