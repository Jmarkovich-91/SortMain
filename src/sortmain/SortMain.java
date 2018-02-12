/*
* Project: SortMain.java
* Author: Joshua Markovich
* Date: September 11, 2017
* Purpose: To benchmark the Java implementation of the insertion sort.
*/
package sortmain;

public class SortMain {

    public static void main(String[] args) {
        
        //Test sizes, larger than 23000 causes a stack overflow error from the recursive method
        int [] sizes = {5, 50, 100, 250, 500, 1000, 5000, 10000, 15000, 19500};
        
        BenchmarkSorts benchmarkSort = new BenchmarkSorts (sizes);
    }
    
}
