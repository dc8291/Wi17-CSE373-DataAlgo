package test;

import java.util.Comparator;

import sorting.GenericSorter;

public class TestGenericMergeSort extends TestIntegerSortBase {
	
	protected void specificSort(Integer[] testArray,
            Comparator<Integer> comparator) {
        GenericSorter.mergeSort(testArray, comparator);
        
    }
}
