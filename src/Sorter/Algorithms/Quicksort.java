package Sorter.Algorithms;

import java.util.ArrayList;

/**
 * Created by arch on 3/8/17.
 */
public class Quicksort {

    ArrayList<Integer> tempNums;

    public ArrayList<Integer> oneStepQuickSort(ArrayList<Integer> nums) {
        tempNums = nums;
        return quickSort(0, tempNums.size() - 1);
    }


    int i ;
    int j ;

    private ArrayList<Integer> quickSort(int low, int high) {
        i = low;
        j = high;
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
                break;
            }
        }

        if (low < j ) {
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
