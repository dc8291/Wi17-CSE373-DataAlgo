import java.util.EmptyStackException;

public class Queue {
	private LinkedNode front;
	
	public boolean isEmpty(){
		return front == null;
	}
	
	public void enqueue(double num){
		LinkedNode temp = new LinkedNode(num);
		if(isEmpty()){
			front = temp;
		} else {
			LinkedNode index = front;
			while(index.next != null){
				index = index.next;
			}
			index.next = temp;
		}
	}
	
	public double dequeue(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
		LinkedNode temp = front;
		front = front.next;
		return temp.num;
	}
	
	private class LinkedNode{
		private LinkedNode next;
		private double num;
		
		private LinkedNode(double num){
			this.num = num;
		}
	}
}
