/* Daniel Chai CSE 373 Section AA
 * Assignment #2, January 27th 2017
 */

// Testing ThreeHeap.java

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class MyClient {
	public static void main(String[] args){
		ThreeHeap test = new ThreeHeap();
		List<Double> list = new ArrayList<>();
		testBuildQueue(5, list, test); // Input(number of elements, list, and ThreeHeap)
		testInsert(5, test); // Input(number of elements and ThreeHeap)
		testDeleteMin(7, test); // Input(number of elements and ThreeHeap)
		double[] array = test.getArray();
		int size = test.size();
		if(good(array, size)){
			System.out.println("Heap is Good!");
		} else {
			System.out.println("Heap is Bad!");
		}
		System.out.println("End array: " + test.toString());
		System.out.println("End size: " + test.size());
	}
	
	// Takes in an integer and a PriorityQueue as parameters
	// Tests the Insert method
	private static void testInsert(int number, PriorityQueue test){
		for(int i = 0; i < number; i++){
			double d = Math.round(Math.random() * 25 + 1);
			test.insert(d);
		}
		System.out.println("Inside Insert: " + test.toString());
		System.out.println("Total size: " + test.size());
	}
	
	// Takes in an integer, List, and a PriorityQueue as parameters
	// Tests the buildQueue method
	private static void testBuildQueue(int number, List<Double> list, PriorityQueue test){
		for(int i = 0; i < number; i++){
			double d = Math.round(Math.random() * 25 + 1);
			list.add(d);
		}
		test.buildQueue(list);
		System.out.println("Inside BuildQueue: " + test.toString());
		System.out.println("Total size: " + test.size());
	}
	
	// Takes in an integer and a PriorityQueue as parameters
	// Tests the deleteMin method
	private static void testDeleteMin(int numbers, PriorityQueue test){
		for(int i = 0; i < numbers; i++){
			System.out.println("Deleting : " + test.deleteMin());
		}
	}
	
	// Takes in an array and an integer as parameters
	// Tests the array to see if it follows the heap order structure
	private static boolean good(double[] array, int s){
		int size = s;
		for(int i = 1; i < (size + 1) / 3; i++){
			int left = i * 3 - 1;
			if(left <= size){
				if(array[left] < array[i]){
					return false;
				}
				if (left + 1 <= size){
					if(array[left + 1] < array[i]){
						return false;
					}
					if(left + 2 <= size){
						if(array[left + 2] < array[i]){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
