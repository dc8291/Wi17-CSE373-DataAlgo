
public class Test {
	public static void main(String[] args){
		DStack stack = new ArrayStack();
		for(double i = 0; i < 10; i+=1.0){
			System.out.println(i);
			stack.push(i);
			System.out.println(stack.toString());
		}
		while(!stack.isEmpty()){
			System.out.println(stack.peek());
			System.out.println(stack.pop());
			System.out.println(stack.toString());
		}
	}
}
