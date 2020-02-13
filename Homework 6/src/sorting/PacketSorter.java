package sorting;

import java.util.Comparator;

/**
 * Class full of static sorting methods. Used to sort packets received from a
 * server containing image metadata.
 * 
 * TODO: Implement mergeSort() and selectionSort().
 * 
 * insertionSort is implemented for you as an example.
 * 
 * @author pattersp
 *
 */

public class PacketSorter {
	/**
     * Sorts the given array of packets in ascending order according to the
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
     *            the packets to sort
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
	public static void mergeSort(Packet[] array, Comparator<Packet> comparator) {
    	if(array.length != 0 && array != null){
            sort(array, 0, array.length - 1, comparator);
    	}
    }

	 /* 
     * Sorts the given array 
     * 
     * @param Packet[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param Comparator<Packet>
     * 			comparator used to compare
     */
	private static void sort(Packet[] array, int min, int max, Comparator<Packet> comparator){
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
	
	/* 
     * merges the different part of the array 
     * 
     * @param Packet[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param int
     * 			middle of the index
     * @param Comparator<Packet>
     * 			comparator used to compare
     */
	private static void merge(Packet[] array, int min, int max, int mid, Comparator<Packet> comparator){
		int i = min;
		while(i <= mid){
			swap(array, i, mid + 1, comparator);
			pushBack(array, mid + 1, max, comparator);
			i++;
		}
	}
	
	/* 
     * Swap elements in two indices
     * 
     * @param Packet[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param Comparator<Packet>
     * 			comparator used to compare
     */
	private static void swap(Packet[] array, int min, int max, Comparator<Packet> comparator){
		if(comparator.compare(array[min], array[max]) > 0){
			Packet temp = array[max];
			array[max] = array[min];
			array[min] = temp;
		}
	}
	
	/* 
     * Pushes the largest element to the back 
     * 
     * @param Packet[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param Comparator<Packet>
     * 			comparator used to compare
     */
	private static void pushBack(Packet[] array, int min, int max, Comparator<Packet> comparator){
		for(int i = min; i < max; i++){
			swap(array, i, i + 1, comparator);
		}
	}
	
    /**
     * Sort the array of Packets in ascending order according to the comparator
     * using selection sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of Packet that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two Packets.
     */
    public static void selectionSort(Packet[] array, Comparator<Packet> comparator) {
    	if(array.length != 0 && array != null){
	        for(int i = 0; i < array.length; i++){
	        	int nextSmall = findSmallest(array, i, comparator);
	        	swap(array, i, nextSmall, comparator);
	        }
    	}
    }
    
    /* 
     * Finds the next smallest element in the unsorted section of array
     * 
     * @param Packet[]
     * 			integer to sort
     * @param int
     * 			left side of index
     * @param int 
     * 			right side of index
     * @param Comparator<Packet>
     * 			comparator used to compare
     */
    private static int findSmallest(Packet[] array, int startIndex, Comparator<Packet> comparator){
    	Packet temp = array[startIndex];
    	int index = startIndex;
    	for(int i = startIndex + 1; i < array.length; i++){
    		if(comparator.compare(temp,  array[i]) > 0){
    			temp = array[i];
    			index = i;
    		}
    	}
    	
    	return index;
    }
    
    /**
     * Sort the array of packets in ascending order using insertion sort.
     * 
     * A note about ascending order:
     * 
     * When the method is finished, it should be true that:
     * comparator.compare(array[i - 1], array[i]) <= 0 for all i from 1 through
     * array.length.
     * 
     * @param array
     *            the array of packets that will be sorted.
     * @param comparator
     *            The comparator the will be used to compare two packets.
     */
    public static void insertionSort(Packet[] array,
            Comparator<Packet> comparator) {
        for (int outerIndex = 1; outerIndex < array.length; outerIndex++) {
            Packet currentPacket = array[outerIndex];
            int innerIndex = outerIndex - 1;
            while (innerIndex >= 0
                    && comparator.compare(currentPacket, array[innerIndex]) < 0) {
                array[innerIndex + 1] = array[innerIndex];
                innerIndex--;
            }
            array[innerIndex + 1] = currentPacket;
        }
    }
}
