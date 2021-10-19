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
		System.out.print(this.representative_letter +"\n");
		System.out.print(this.current_word +"\n");
		System.out.print(this.definition +"\n");
		if(word.length() > 1){
			representative_letter = word.charAt(0);
			LexiNode child = new LexiNode();
			childs.add(child);
			add_word(word, definition, word.substring(1));
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
		//If we already have a child with that representative_letter, we
		//send the rest of the word to him.
		for(LexiNode child: childs){
			if(word.substring(1,2).equals(child.getRepresentative_letter()))
				child.add_word(word, definition, subword.substring(1));
		}
		if(subword.length() > 1){
			System.out.print(this.representative_letter +"\n");
			System.out.print(this.current_word +"\n");
			System.out.print(this.definition +"\n");
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
			System.out.print(this.representative_letter +"\n");
			System.out.print(this.current_word +"\n");
			System.out.print(this.definition +"\n");
		}
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
		LexiNode dictionnaire = new LexiNode();
		dictionnaire.add_word("bien", "une definition");
	}
}
