package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort Integers.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class IntegerSorter {
    /**
     * Sorts the given array of integers in ascending order according to the
     * comparator using mergesort. You may create as many private helper
     * functions as you wish to implement this method.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the integers to sort
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void mergeSort(Integer[] array, Comparator<Integer> comparator) {
    	if(array.length != 0 && array != null){
            sort(array, 0, array.length - 1, comparator);
    	}
    }
    
    /* 
     * Sorts the given array 
     * 
     * @param Integer[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param Comparator<Integer>
     * 			comparator used to compare
     */
	private static void sort(Integer[] array, int min, int max, Comparator<Integer> comparator){
		if(max - min != 0){
			if(max - min == 1){
				swap(array, min, max, comparator);
			} else {
				int mid = (max + min) / 2;
				sort(array, min, mid, comparator); // Sort the left side
				sort(array, mid + 1, max, comparator); // Sort the right side
				merge(array, min, max, mid, comparator); // Merge the two sides together
			}
		}
	}
	
	/* 
     * Merges the different sections of the array
     * 
     * @param Integer[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param int
     * 			middle of the array
     * @param Comparator<Integer>
     * 			comparator used to compare
     */
	private static void merge(Integer[] array, int min, int max, int mid, Comparator<Integer> comparator){
		int i = min;
		while(i <= mid){
			swap(array, i, mid + 1, comparator); 
			pushBack(array, mid + 1, max, comparator);
			i++;
		}
	}
	
	/*
     * Swaps two indices
     * 
     * @param Integer[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param Comparator<Integer>
     * 			comparator used to compare
     */
	private static void swap(Integer[] array, int min, int max, Comparator<Integer> comparator){
		if(comparator.compare(array[min], array[max]) > 0){ 
			Integer temp = array[max];
			array[max] = array[min];
			array[min] = temp;
		}
	}
	
	/* 
     * Pushes the biggest number to the end
     * 
     * @param Integer[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param Comparator<Integer>
     * 			comparator used to compare
     */
	private static void pushBack(Integer[] array, int min, int max, Comparator<Integer> comparator){
		for(int i = min; i < max; i++){
			swap(array, i, i + 1, comparator);
		}
	}
	
    /**
     * Sort the array of integers in ascending order according to the comparator
     * using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integer that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integers.
     */
    public static void selectionSort(Integer[] array, Comparator<Integer> comparator) {
    	if(array.length != 0 && array != null){
	        for(int i = 0; i < array.length; i++){
	        	int nextSmall = findSmallest(array, i, comparator); // find the index with next smallest index
	        	swap(array, i, nextSmall, comparator); // Swap current element with the element above
	        }
    	}
    }
    
    /* 
     * Finds the next smallest element in the unsorted section of array
     * 
     * @param Integer[]
     * 			integer to sort
     * @param int
     * 			lowest index where elements are already sorted
     * @param Comparator<Integer>
     * 			comparator used to compare
     * @return int
     * 			where the next smallest element is located
     */
    private static int findSmallest(Integer[] array, int startIndex, Comparator<Integer> comparator){
    	Integer temp = array[startIndex];
    	int index = startIndex;
    	for(int i = startIndex + 1; i < array.length; i++){ // Look for every element after the already sorted array
    		if(comparator.compare(temp,  array[i]) > 0){
    			temp = array[i];
    			index = i;
    		}
    	}
    	
    	return index;
    }

    /**
     * Sort the array of integers in ascending order according to the comparator
     * using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of integers that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two integer.
     */
    public static void insertionSort(Integer[] array,
            Comparator<Integer> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Integer currentInt = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0
                    && comparator.compare(currentInt, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentInt;
        }
    }
}
