/* Daniel Chai CSE 373 Section AA
 * Assignment #1, January 17th 2017
 */

//Implementing Stack ADT with an array

import java.util.EmptyStackException;

public class ArrayStack implements DStack {
	private double[] array;
	private int index;

	//Initializes the array and the index
	public ArrayStack(){
		array = new double[1];
		index = -1; 
	}
	
	//Returns whether the array is empty
	public boolean isEmpty() {
		return index < 0; 
	}

	//Takes in a double and pushes it to the array[index]
	//doubles the size of the array when full
	public void push(double d) {
		index++;
		if(index == array.length){
			double[] newArray = new double[array.length * 2];
			for(int i = 0; i < array.length; i++){
				newArray[i] = array[i];
			}
			array = newArray;
		}
		array[index] = d;
	}
	
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
	
	
	//Throws EmptyStackException if array is empty
	//Returns the double at the top of the stack
	//Halves the size of the array if it is 75% empty
	public double pop() {
		if(isEmpty()){
			throw new EmptyStackException(); 
		}
		if(index < array.length * 1 / 4){
			double[] newArray = new double[array.length / 2];
			for(int i = 0; i <= index; i++){
				newArray[i] = array[i];
			}
			array = newArray;
		}
		index--;
		return array[index+1];
	}
	
	//Throws EmptyStackException if array is empty
	//Returns the double at the top of the stack
	public double peek() {
		if(isEmpty()){
			throw new EmptyStackException();
		}
		return array[index];
	}
}


	