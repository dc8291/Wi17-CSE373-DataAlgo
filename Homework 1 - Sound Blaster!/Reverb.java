import java.io.*;
import java.util.Scanner;

public class Reverb {
	private static int inputSample = 460; //Input sample
	private static int outputSample = 250000; //Output sample
	private static int rate = 44100; //Sample Rate
	private static double damp = 0.99; //Damping Constant
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.print("File Name: ");
		String filename = console.nextLine();
		PrintStream output = new PrintStream(new File(filename));
		
		Queue Q1 = new Queue();
		Queue Q2 = new Queue();
		for(int i = 0; i < inputSample; i++){
			double num = Math.random() * 2 - 1 ;
			Q1.enqueue(num);
		}
		Q2.enqueue(0);
		
		output.println("; Sample Rate " + rate);
		for(int i = 0; i < outputSample; i++){
			double a = Q1.dequeue();
			double b = Q2.dequeue();
			double c = damp * ((a + b) / 2);
			Q1.enqueue(c);
			Q2.enqueue(a);
			double t = (double)i / rate;
			output.println("" + t + "\t" + c);
		}
		System.out.println("Success!");
	}
}