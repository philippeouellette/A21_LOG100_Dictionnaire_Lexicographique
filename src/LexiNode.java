import java.util.ArrayList;
import java.util.jar.JarException;

import com.sun.java.swing.plaf.motif.resources.motif;

/**
 * Class representing the nodes of the lexicographic tree.
 * @author philippe.ouellette
 *
 */
public class LexiNode {
	private char representative_letter;
	private String current_word;
	private String definition;
	private ArrayList<LexiNode> childs = new ArrayList<LexiNode>();
	
	/**
	 * First method to be called when adding a new word. This method then call
	 * his overloaded version to recursively deconstruct the word.
	 * @param word Initial word to be added.
	 * @param definition The definition of the initial word.
	 */
	public void add_word(String word, String definition){
		if(word.length() > 1){
			for(LexiNode child: childs){
				if(word.charAt(0) == (child.getRepresentative_letter())){
					child.add_word(word, definition, word);
					//if we found a matching child, we don't wanna create another one
					return;
				}
			}
			LexiNode child = new LexiNode();
			childs.add(child);
			child.add_word(word, definition, word);
		}
	}
	
	/**
	 * Recursive overloaded method which takes the first letter of subword and 
	 * add it to his current Representative_letter property while sending
	 * the rest of the word to a child of himself. If the subword length
	 * is equal to 1, then we hit the last letter and we save the word
	 * and the definition in the current object.
	 * @param word Initial word to be added.
	 * @param definition The definition of the initial word.
	 * @param subword The remaining of the initial word that's still to be treated.
	 */
	private void add_word(String word, String definition, String subword){
		representative_letter = subword.charAt(0);
		boolean child_has_been_added = false;
		
		//If we already have a child with that representative_letter, we
		//send the rest of the word to him.
		for(LexiNode child: childs){
			if(subword.charAt(1) == child.getRepresentative_letter()){
				child.add_word(word, definition, subword.substring(1));
				child_has_been_added = true;
			}
		}
		//TODO we have to somehow skip this part if we added a child
		if(!child_has_been_added){
			if(subword.length() > 1){
				LexiNode child = new LexiNode();
				childs.add(child);
				try{
					child.add_word(word, definition, subword.substring(1));
				}
				catch(StringIndexOutOfBoundsException e){
					System.out.println("Error: " + e);
				}
			}
			else if (subword.length() == 1) {
				this.current_word = word;
				this.definition = definition;
			}
		}
	}
	
	/**
	 * Gets a word or a partial word and recursively search his
	 * childs to see if some of these has the word we're looking
	 * for.
	 * @param Word the word we're searching. 
	 * @return Returns the full current_word if found, an empty string otherwise.
	 */
	public String search_word(String word){
		String found_words = "";
		//We try to find the word within our childs
		for(LexiNode child: childs)
			if(word.charAt(0) == child.getRepresentative_letter())
				found_words += child.search_word(word, 0);
		
		return found_words;
	}

	/**
	 * Overloaded method which gets a word or a partial word and recursively search his
	 * childs to see if some of these has the word we're looking
	 * for.
	 * @param Word the word we're searching.
	 * @param Index the index of the string that represents the letter we're looking for. 
	 * @return Returns the full current_word if found, an empty string otherwise.
	 */
	private String search_word(String word, int index){
		String found_words = "";
		int local_index = index +1;
		//We try to find the word within our childs
		for(LexiNode child: childs){
			System.out.println("_______________________\n");
			System.out.println(local_index);
			System.out.println(word.length());
			if(local_index < word.length())
				System.out.println(word.charAt(local_index));
			System.out.println(child.getRepresentative_letter());
			if(local_index > word.length()-1 || word.charAt(local_index) == child.getRepresentative_letter())
				found_words += child.search_word(word, local_index);
		}
		if(current_word != null && found_words != null)
				return found_words +"," + current_word;
		else if(current_word != null && found_words == null)
			return current_word;
		else //current_word == null && found_words != null
			return found_words;
	}
	
	/**
	 * Get the current value of the Representative_letter property. 
	 * @return Value of Representative_letter property.
	 */
	public char getRepresentative_letter(){
		return representative_letter;
	}
	
	/**
	 * Get the current value of the Current_word property. 
	 * @return Value of Current_word property.
	 */
	public String getCurrent_word(){
		return current_word;
	}
	
	/**
	 * Get the current value of the Definition property. 
	 * @return Value of Definition property.
	 */
	public String getDefinition(){
		return definition;
	}
	
	/**
	 * Get the current values of the Childs ArrayList.
	 * @return Values of the Childs ArrayList.
	 */
	public ArrayList<LexiNode> getChilds(){
		return childs;
	}
	
	//For testing purposes
	public static void main(String[] args) {
		System.out.println("Starting main ...");
		LexiNode dictionnaire = new LexiNode();
		dictionnaire.add_word("bien", "une definition");
		dictionnaire.add_word("boreal", "une definition");
		dictionnaire.add_word("bon", "une definition");
		dictionnaire.add_word("bonne", "une definition");
		dictionnaire.add_word("bebe", "une definition");
		dictionnaire.add_word("blanc", "une definition");
		ArrayList<LexiNode> childs =  dictionnaire.getChilds();
		System.out.println(childs.size());
		System.out.println("\n\n Found the word: " + dictionnaire.search_word("b"));
		System.out.println("Finishing main ...");
	}
}
