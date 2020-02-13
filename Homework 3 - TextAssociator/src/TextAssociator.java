/* Daniel Chai CSE 373 Section AA
 * Assignment #3, February 10th 2017
 */

// Creating Text Associations 

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* CSE 373 Starter Code
 * @Author Kevin Quinn
 * 
 * TextAssociator represents a collection of associations between words.
 * See write-up for implementation details and hints
 * 
 */
public class TextAssociator {
	private WordInfoSeparateChain[] table;
	private int size;
	
	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}
		
		/* Adds a WordInfo object to the SeparateChain
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
			if(wi == null || chain.contains(wi)){
				return false; 
			}
			return this.chain.add(wi);
		}
		
		/* Removes the given WordInfo object from the separate chain
		 * Returns true if the WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
			if(wi == null || !chain.contains(wi)){
				return false; 
			}
			return this.chain.remove(wi);
		}
		
		// Returns the size of this separate chain
		public int size() {
			return chain.size();
		}
		
		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}
		
		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}
	}
	
	
	/*	Creates a new TextAssociator without any associations 
	 */
	public TextAssociator() {
		this.size = 0;
		this.table = new WordInfoSeparateChain[5];
	}
	
	
	/*	Adds a word with no associations to the TextAssociator 
	 *	Returns False if this word is already contained in your TextAssociator ,
	 *	Returns True if this word is successfully added
	 */
	public boolean addNewWord(String word) {
		WordInfo w = new WordInfo(word);
		if(hashing(word, this.table, w)){
			size++;
			if (size / this.table.length == 1){
				resize();
			}
			return true;
		}
		return false;
	}
	
	/* 	Resizes the WordInfoSeparateChain to the next biggest prime number on the list
	 *  Rehashes each WordInfo
	 */
	private void resize(){
		WordInfoSeparateChain[] newArray = new WordInfoSeparateChain[this.table.length * 2]; 
		for(int i = 0; i < this.table.length; i++){
			if(this.table[i] != null){
				List<WordInfo> temp = this.table[i].getElements();
				for(WordInfo w : temp){
					String word = w.getWord();
					hashing(word, newArray, w);					
				}
			}
		}
		this.table = newArray; 
	}
	
	/* 	Takes in String, WordInfoSeparateChain[], and WordInfo as parameters
	 * 	Returns whether hashing was successful or not
	 */
	private boolean hashing(String word, WordInfoSeparateChain[] array, WordInfo w){
		int index = Math.abs(word.hashCode()) % array.length;
		if (array[index] == null){
			WordInfoSeparateChain c = new WordInfoSeparateChain();
			array[index] = c;
		}
		return array[index].add(w);
	}
	
	/*	Adds an association between the given words. Returns true if association correctly added, 
	 *	returns false if first parameter does not already exist in the TextAssociator or if 
	 *	the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		List<WordInfo> temp = findList(word);
		if(temp == null){
			return false;
		}
		WordInfo wi = findWI(word, temp);
		if(wi == null){
			return false;
		}
		return wi.addAssociation(association);
	}
	
	
	/* Remove the given word from the TextAssociator, returns false if word 
	 * was not contained, returns true if the word was successfully removed.
	 * Note that only a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
		List<WordInfo> temp = findList(word);
		if(temp == null){
			return false;
		}
		WordInfo wi = findWI(word, temp);
		if(wi == null){
			return false;
		}
		size--;
		return temp.remove(wi);
	}
	
	
	/* 	Returns a set of all the words associated with the given String  
	 * 	Returns null if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
		List<WordInfo> temp = findList(word);
		if(temp == null){
			return null;
		}
		WordInfo wi = findWI(word, temp);
		if(wi == null){
			return null;
		}
		return wi.getAssociations();
	}
	
	/*	Takes a String as a parameter
	 *	Returns List<WordInfo> of where the word would be located
	 * 	Returns null if that List does not exist
	 */ 
	private List<WordInfo> findList(String word){
		int index = Math.abs(word.hashCode()) % this.table.length;
		if(this.table[index] == null){
			return null;
		}
		return this.table[index].getElements();
	}
	
	/*	Takes in a String and List<WordInfo> as parameters
	 * 	Returns the WordInfo of the word
	 *	Returns null if WordInfo does not exist
	 */
	private WordInfo findWI(String word, List<WordInfo> list){
		WordInfo w = new WordInfo(word); 
		if(!list.contains(w)){
			return null; 	
		} 
		int i = list.indexOf(w);
		return list.get(i);
	}
	
	/*	Prints the current associations between words being stored
	 *	to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);
		
		//Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}
}
