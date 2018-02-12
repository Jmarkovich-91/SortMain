/*
* Project: SortMain.java
* Author: Joshua Markovich
* Date: September 11, 2017
* Purpose: To benchmark the Java implementation of the insertion sort.
*/
package sortmain;

public class BenchmarkSorts {
    
    private static final int NUMBEROFSETS = 50;
    private int [][][] countData;
    private long [][][] timeData;
    private int [] sizes;
    InsertionSort insertionSort;
    
    public BenchmarkSorts (int [] sizes) {
        this.sizes = sizes;
        countData = new int [sizes.length][2][NUMBEROFSETS];
        timeData = new long [sizes.length][2][NUMBEROFSETS];
        this.insertionSort = new InsertionSort();
        runSorts ();
        displayReport ();
    }
    
    public void runSorts () {
        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < NUMBEROFSETS; j++) {
                int [] testData = generateTestData (sizes[i]);
                int [] testData2 = testData.clone();
                insertionSort.iterativeSort(testData);
                countData [i][0][j] = insertionSort.getCount();
                timeData [i][0][j] = insertionSort.getTime();
                insertionSort.recursiveSort(testData2);
                countData [i][1][j] = insertionSort.getCount();
                timeData [i][1][j] = insertionSort.getTime();
            }
        }
    }
    
    public void displayReport () {
        double [] stats;
        System.out.println("\n-----------------------------------");
        System.out.println("Insertion Sort Benchmark Data");
        System.out.println("-----------------------------------");
        System.out.printf("\n%15s|%110s|%50s", "Data Set Size n", "Iterative", "Recursive");
        System.out.printf("\n%15s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|%10s|", "", "Average Critical Operation Count",
                "Standard Deviation of Count", "Average Execution Time", "Standard Deviation of Time",
                "Average Critical Operation Count", "Standard Deviation of Count", "Average Execution Time",
                "Standard Deviation of Time");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("\n%15s|", sizes[i]);
            for (int j = 0; j < 2; j++) {
                stats = calculate (countData[i][j], timeData[i][j]);
                System.out.printf("%32s|%27s|%23s|%25s|", stats[0], stats[1], stats[2], stats[3]);
            }
        }
    }
    
    //Generate an array of random integers between 0 and 50000
    private int [] generateTestData (int size) {
        int [] testData = new int [size];
        for (int i = 0; i < size; i++) {
            testData [i] = (int) (Math.random () * 50000);
        }
        return testData;
    }
    
    private double [] calculate (int [] countData, long [] timeData) {
        double avgCount = 0;
        double avgTime = 0;
        double countDif = 0;
        double timeDif = 0;
        double countSD = 0;
        double timeSD = 0;
        
        for (int i = 0; i < countData.length; i++) {
            avgCount += countData[i];
            avgTime += timeData[i] / 1000000000.0;
        }
        
        avgCount = avgCount / countData.length;
        avgTime = avgTime / timeData.length;
        
        for (int i = 0; i < countData.length; i++) {
            countDif += Math.pow(countData[i] - avgCount, 2);
            timeDif += Math.pow((timeData[i] / 1000000000.0) - avgTime, 2);
        }
        
        countDif = countDif / countData.length;
        timeDif = timeDif / timeData.length;
        
        countSD = Math.sqrt(countDif);
        timeSD = Math.sqrt(timeDif);
        
        double [] stats = {avgCount, countSD, avgTime, timeSD};
        return stats;
    }
    
    
}
