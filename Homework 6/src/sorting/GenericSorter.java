package sorting;

import java.util.Comparator;

public class GenericSorter {
    public static <E> void mergeSort(E[] array, Comparator<E> comparator) {
    	if(array.length != 0 && array != null){
            sort(array, 0, array.length - 1, comparator);
    	}
    }

	private static <E> void sort(E[] array, int min, int max, Comparator<E> comparator){
		if(max - min != 0){
			if(max - min == 1){
				swap(array, min, max, comparator);
			} else {
				int mid = (max + min) / 2;
				sort(array, min, mid, comparator);
				sort(array, mid + 1, max, comparator);
				merge(array, min, max, mid, comparator);
			}
		}
	}
	
	private static <E> void merge(E[] array, int min, int max, int mid, Comparator<E> comparator){
		int i = min;
		while(i <= mid){
			swap(array, i, mid + 1, comparator);
			pushBack(array, mid + 1, max, comparator);
			i++;
		}
	}
	
	private static <E> void swap(E[] array, int min, int max, Comparator<E> comparator){
		if(comparator.compare(array[min], array[max]) > 0){
			E temp = array[max];
			array[max] = array[min];
			array[min] = temp;
		}
	}
	
	private static <E> void pushBack(E[] array, int min, int max, Comparator<E> comparator){
		for(int i = min; i < max; i++){
			swap(array, i, i + 1, comparator);
		}
	}
	
	public static <E> void selectionSort(E[] array, Comparator<E> comparator) {
	        for(int i = 0; i < array.length; i++){
	        	int nextSmall = findSmallest(array, i, comparator);
	        	swap(array, i, nextSmall, comparator);
	        }
	    }
	    
    private static <E> int findSmallest(E[] array, int startIndex, Comparator<E> comparator){
    	E temp = array[startIndex];
    	int index = startIndex;
    	for(int i = startIndex; i < array.length; i++){
    		if(comparator.compare(temp,  array[i]) > 0){
    			temp = array[i];
    			index = i;
    		}
    	}
    	
    	return index;
    }
}
