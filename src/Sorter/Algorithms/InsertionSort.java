package Sorter.Algorithms;

import java.util.ArrayList;

/**
 * Created by arch on 3/8/17.
 */
public class InsertionSort {
    ArrayList<Integer> tempNums;
    static int p = 0;

    public void resetVars(){
        p = 0;
    }

    public ArrayList<Integer> oneStepInsertionSort(ArrayList<Integer> nums) {

        int key;
        int i;
        tempNums = nums;

        for (; p < tempNums.size(); p++) {
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
}
