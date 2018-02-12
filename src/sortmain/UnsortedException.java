/*
* Project: SortMain.java
* Author: Joshua Markovich
* Date: September 11, 2017
* Purpose: To benchmark the Java implementation of the insertion sort.
*/
package sortmain;

public class UnsortedException extends Exception {
    
    public UnsortedException (int size) {
        super ("Array of size: " + size + " failed to sort.");
    }
}
