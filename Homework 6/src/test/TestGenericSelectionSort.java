package test;

import java.util.Comparator;

import sorting.GenericSorter;

public class TestGenericSelectionSort extends TestIntegerSortBase {
	
	protected void specificSort(Integer[] testArray,
            Comparator<Integer> comparator) {
        GenericSorter.selectionSort(testArray, comparator);
        
    }
}
