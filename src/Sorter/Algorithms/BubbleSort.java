package Sorter.Algorithms;

import java.util.ArrayList;

/**
 * Created by arch on 3/8/17.
 */
public class BubbleSort {
    ArrayList<Integer> tempNums;

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
}
