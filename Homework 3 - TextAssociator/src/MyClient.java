import java.util.Scanner;
import java.util.Set;

/* Daniel Chai CSE 373 Section AA
 * Assignment #3, February 10th 2017
 */

// Using TextAssociator for a limited spell checker

public class MyClient {
	//	List of all the misspelled words	
	public static String[] words = {"acceptabel", "alot", "beleive", "cemetary","conshious", "embarsment",
			"eckseed", "foriegn","greatful","garentee","haras", "hiarchy", "immidiate", "judgement"
			,"libary", "manouver", "mispell","posession", "recieve", "resturant"
	};
	
	public static void main(String[] args) throws InterruptedException {
		TextAssociator sc = new TextAssociator();
		testAddNewWords(sc);
		testAddAssociations(sc);
		//testRemove(sc); 
		//System.out.println(testGetAssociations(sc));
		//sc.prettyPrint();
		
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.print("Type in a word (\"exit\" to exit): ");
			String answer = scan.next();
			if(answer.equals("exit")){
				System.out.println("Did you mean \"continue\"?");
				Thread.sleep(2000);
				System.out.println("Just Kidding, Good Bye!");
				break;
			}
			Set<String> list = sc.getAssociations(answer);
			if(list == null){
				System.out.println("I don't know what you wrote.");
			} else {
				System.out.println("Did you mean \"" + list.toArray()[0] + "\"?");
			}
		}
	}

	//	Testing the addNewWords method
	public static void testAddNewWords(TextAssociator sc){
		for(int i = 0; i < words.length; i++){
			sc.addNewWord(words[i]);
		}
	}
	
	//	Testing the addAssociations method
	//	The associations are the correctly spelled words and are ordered in the same way as the words[]
	public static void testAddAssociations(TextAssociator sc){
		String[] associations = {"acceptable", "a lot","believe","cemetery","conscientious","embarassment",
				"exceed","foreign","grateful","guarantee","harass","hierarchy","immediate","judgment",
				"library","maneuver","misspell","possession","receive","restaurant"
				};
		for(int i = 0; i < words.length; i++){
			sc.addAssociation(words[i], associations[i]);
		}
	}
	
	//	Testing remove
	public static void testRemove(TextAssociator sc){
		for(int i = 0; i < words.length; i++){
			sc.remove(words[i]);
		}
	}
	
	//	Testing getAssociations
	public static Set<String> testGetAssociations(TextAssociator sc){
		return sc.getAssociations(words[1]);
	}
}
