/*
* Project: SortMain.java
* Author: Joshua Markovich
* Date: September 11, 2017
* Purpose: To benchmark the Java implementation of the insertion sort.
*/
package sortmain;

public interface SortInterface {
    
    public int [] recursiveSort (int [] list);
    public int [] iterativeSort (int [] list);
    public int getCount ();
    public long getTime ();
    
}
