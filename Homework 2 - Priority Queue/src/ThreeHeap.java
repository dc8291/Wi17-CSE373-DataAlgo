/* Daniel Chai CSE 373 Section AA
 * Assignment #2, January 27th 2017
 */

// Implementing PriorityQueue with Ternary Heap using array

import java.util.List;

public class ThreeHeap implements PriorityQueue{
	private double[] array; //Stores data for heap
	private int size; //Number of elements inside the heap
	
	// Initializes the array and size
	public ThreeHeap() {
		array = new double[1];
		size = 0;
	}
	
	// Returns whether the heap is empty or not
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	// Returns the size of the heap
	@Override
	public int size() {
		return size;
	}

	// Returns the minimum element in the Priority Queue
	// Throws EmptyHeapException if heap is empty
	@Override
	public double findMin() {
		if(isEmpty()){
			throw new EmptyHeapException();
		}
		return array[1];
	}
	
	// Takes in a double as a parameter
	// Inserts an element into the heap
	// Internally orders the array to keep structural and heap order property
	@Override
	public void insert(double x) {
		size++;
		if(size >= array.length){
			newArray();
		}
		array[size] = x;
		percolateUp(x);
	}
	
	// Takes in a double as a parameter
	// Reorders the array in order to keep heap order property
	private void percolateUp(double x){
		int temp = size;
		while(x < array[(temp + 1) / 3]){
//			int val = (temp + 1) % 3; // Finding the right most leaf
//			double min = x;
//			int index = temp;
//			for(int i = val; i > 0; i--){
//				if (min > array[temp - i]){
//					min = array[temp - i];
//					index = temp - i;
//				}
//			}
			array[temp] = array[(temp + 1) / 3];
			array[(temp + 1) / 3] = x;
			temp = (temp + 1) / 3;
		}
	}
	
	// Creates a new array that has double the length when the current array is full
	// Copies the old array over to the new array
	private void newArray(){
		double[] newArray = new double[array.length * 2];
		for(int i = 0; i < array.length; i++){
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	// Throws EmptyHeapException if the heap is empty
	// Removes and Returns the minimum element from the Priority Queue
	@Override
	public double deleteMin() {
		if(isEmpty()){
			throw new EmptyHeapException();
		}
		double min = array[1];
		array[1] = array[size];
		size--;
		percolateDown(array[1], 1);
		return min;
	}

	// Takes in a List<Double> as a parameter
	// Builds a heap from the numbers from the list
	@Override
	public void buildQueue(List<Double> list) {
		makeEmpty();
		for(int i = 0; i < list.size(); i++){
			size++;
			if(size >= array.length){
				newArray();
			}
			array[i + 1] = list.get(i);
		}
		for(int i = (size + 1) / 3; i > 0; i--){
			double temp = array[i];
			percolateDown(temp, i);
		}
	}
	
	// Takes in a double and a integer as parameters
	// Reorders the array in order to keep heap order property
	private void percolateDown(double x, int index){
		double min = x; 
		int hole = index;
		if(index * 3 - 1 <= size){ // checking for left child
			if(min > array[index * 3 - 1]){
				min = array[index * 3 - 1];
				hole = index * 3 - 1;
			}
			if (index * 3 <= size){ // checking for middle child
				if(min > array[index * 3]){
					min = array[index * 3];
					hole = index * 3;
				}
				if(index * 3 + 1 <= size){ // checking for right child
					if(min > array[index * 3 + 1]){
						min = array[index * 3 + 1];
						hole = index * 3 + 1;
					}
				}
			}
		}
		if(min < x){
			array[index] = min; // Setting a new parent
			array[hole] = x; // Fixing the hole	
			double temp = array[hole];
			percolateDown(temp, hole); // Checking for further holes
		}
	}
	
	// Resets the heap to not contain any elements
	@Override
	public void makeEmpty() {
		size = 0;
		array = new double[1];
	}

//-------------Everything below this line is for MyClient------------------- 

	// Returns a String form of the current array
	public String toString(){
		String s = "";
		for (int i = 0; i < array.length; i++) {
	         if (i > 0) {
	            s+=", ";
	         }
	         s+=array[i];
		}
		return s;
	}
	
	// Returns current array 
	public double[] getArray(){
		return array;
	}
}
