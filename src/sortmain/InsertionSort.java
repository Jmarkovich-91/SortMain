/*
* Project: SortMain.java
* Author: Joshua Markovich
* Date: September 11, 2017
* Purpose: To benchmark the Java implementation of the insertion sort.
 */
package sortmain;

public class InsertionSort implements SortInterface {

    private int count;
    private long time;
    private long recStartTime;
    private int[] recursiveArray;
    private static int recursiveCounter;

    //Helper method to call recursiveInsertionSort
    public int[] recursiveSort(int[] list) {
        recStartTime = System.nanoTime();
        this.count = 0; //reset counter
        this.time = 0; //reset timer
        recursiveArray = list;
        insertionSortRecursive (recursiveArray, recursiveArray.length);
        try {
            verifySort (recursiveArray);
        } catch (UnsortedException u) {
        }
        return recursiveArray;
    }

    /*Iterative insertion sort taken from
    * http://www.java2novice.com/java-sorting-algorithms/insertion-sort/
     */
    public int[] iterativeSort(int[] list){
        long startTime = System.nanoTime();
        this.count = 0; //reset counter
        this.time = 0; //reset timer
        int temp;
        for (int i = 1; i < list.length; i++) {
            for (int j = i; j > 0; j--) {
                if (list[j] < list[j - 1]) {
                    temp = list[j];
                    list[j] = list[j - 1];
                    list[j - 1] = temp;
                    count++; //count the number of swaps
                }
            }
        }
        time = System.nanoTime() - startTime;
        try {
            verifySort (list);
        } catch (UnsortedException u) {
        }
        return list;
    }

    public int getCount() {
        return this.count;
    }

    public long getTime() {
        return this.time;
    }
    
    /* Recursive insertion sort taken from 
    * http://www.geeksforgeeks.org/recursive-insertion-sort/
    */
    private void insertionSortRecursive(int arr[], int n) {
        // Base case
        if (n <= 1) {
            time = System.nanoTime() - recStartTime;
            return;
        }

        // Sort first n-1 elements
        insertionSortRecursive(arr, n-1);
      
        // Insert last element at its correct position
        // in sorted array.
        int last = arr[n-1];
        int j = n - 2;
      
        /* Move elements of arr[0..i-1], that are
          greater than key, to one position ahead
          of their current position */
        while (j >= 0 && arr[j] > last)
        {
            arr[j+1] = arr[j];
            j--;
            count++; //count the number of swaps
        }
        arr[j+1] = last;
        time = System.nanoTime() - recStartTime;
    }
    
    private void verifySort (int[] arr) throws UnsortedException {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1]) {
                throw new UnsortedException (arr.length);
            }
        }
    }

}
