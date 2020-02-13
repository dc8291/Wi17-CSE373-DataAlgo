/* Daniel Chai CSE 373 Section AA
 * Assignment #1, January 17th 2017
 */

//Implementing Stack ADT with a linked list

import java.util.EmptyStackException;

public class ListStack implements DStack {
	private LinkedNode front;
	
	@Override
	//Returns whether the list is empty
	public boolean isEmpty() {
		return front == null;
	}
	
	@Override
	//Takes in a double and pushes it to the top
	public void push(double d) {
		LinkedNode temp = new LinkedNode(d);
		temp.next = front;
		front = temp;
	}

	//Throws an EmptyStackException if the list is empty
	//Returns the top double and removes it from the stack
	@Override
	public double pop() {
		if(isEmpty()){
			throw new EmptyStackException();
		}
		LinkedNode temp = front;
		front = temp.next;
		return temp.num;
	}

	//Throws an EmptyStackException if the list is empty
	//Returns the top double
	@Override
	public double peek() {
		if(isEmpty()){
			throw new EmptyStackException();
		}
		return front.num;
	}
	
	private class LinkedNode{
		private LinkedNode next;
		private double num;
		
		private LinkedNode(double num){
			this.num = num;
		}
	}
}
