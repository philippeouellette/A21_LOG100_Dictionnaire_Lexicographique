package dictio;

import java.util.ArrayList;
import java.util.jar.JarException;

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
			if(subword.length() > 1 && subword.charAt(1) == child.getRepresentative_letter()){
				child.add_word(word, definition, subword.substring(1));
				child_has_been_added = true;
			}
		}
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
	public String search_words(String word){
		String found_words = "";
		//We try to find the word within our childs
		for(LexiNode child: childs)
			if(word.toLowerCase().charAt(0) == Character.toLowerCase(child.getRepresentative_letter()))
				found_words += child.search_words(word, 0);
		
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
	private String search_words(String word, int index){
		String found_words = "";
		int local_index = index +1;
		//We try to find the word within our childs
		for(LexiNode child: childs){
			if(local_index > word.length()-1 || word.toLowerCase().charAt(local_index) == Character.toLowerCase(child.getRepresentative_letter()))
				found_words += child.search_words(word, local_index);
		}
		if(current_word != null && !found_words.equals(""))
			return current_word.toLowerCase().equals(word.toLowerCase()) ? current_word +" & " + definition + "-" + found_words +"-" : current_word + "-" + found_words +"-";
		else if(current_word != null && found_words.equals(""))
			return current_word.toLowerCase().equals(word.toLowerCase()) ? current_word +" & " + definition + "-": current_word+ "-";
		else //current_word == null && found_words != null
			return found_words;
	}
	
	/**
	 * Tries to search the received word. If it can't be found,
	 * we add it as it's a new word. If it's found, we edit the
	 * definition received.
	 * @param word the word to either add or to edit the definition.
	 * @param definition The definition to edit if the word exists.
	 */
	public void edit_word(String word, String definition){
		LexiNode node = search_specific_word(word, -1);
		if(node == null)
			add_word(word, definition);
		else
			search_specific_word(word, -1).setDefinition(definition);
	}
	
	/**
	 * Recursively travel the letters of the word received
	 * in parameter and returns it if found.
	 * @param word the word we're looking for.
	 * @param index index of the word representing the letter and the node.
	 * @return the node containing the word we're looking for. Null if not found. 
	 */
	private LexiNode search_specific_word(String word, int index){
		int local_index = index + 1;
		//if we found the word, we make the change
		if(current_word != null && 
		   !current_word.isEmpty() && 
			word.toLowerCase().equals(current_word.toLowerCase()))
			return this;
		//We try to find the word within our childs
		for(LexiNode child: childs)
			if(word.toLowerCase().charAt(local_index) == Character.toLowerCase(child.getRepresentative_letter()))
				return child.search_specific_word(word, local_index);
		return null;
	}
	
	/**
	 * Return every word present in the lexinode tree.
	 * @return a string containing all words.
	 */
	public String get_all_words(){
		String found_words = "";
		//We try to find the word within our childs
		for(LexiNode child: childs){
				found_words += child.get_all_words();
		}
		if(current_word != null && !found_words.equals(""))
			return current_word + "-" + found_words +"-";
		else if(current_word != null && found_words.equals(""))
			return current_word+ "-";
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
	 * Set the representative_letter of the current value with 
	 * the received value
	 * @param representative_letter new representative_letter.
	 */
	public void SetRepresentative_letter(char representative_letter){
		this.representative_letter = representative_letter;
	}
	
	/**
	 * Get the current value of the Current_word property. 
	 * @return Value of Current_word property.
	 */
	public String getCurrent_word(){
		return current_word;
	}
	
	/**
	 * Set the current_word of the current value with 
	 * the received value
	 * @param current_word new current_word.
	 */
	public void setCurrent_word(String current_word){
		this.current_word = current_word;
	}
	
	/**
	 * Get the current value of the Definition property. 
	 * @return Value of Definition property.
	 */
	public String getDefinition(){
		return definition;
	}
	
	/**
	 * Set the definition of the current value with 
	 * the received value 
	 * @param definition new definition.
	 */
	public void setDefinition(String definition){
		this.definition = definition;
	}
	
	/**
	 * Get the current values of the Childs ArrayList.
	 * @return Values of the Childs ArrayList.
	 */
	public ArrayList<LexiNode> getChilds(){
		return childs;
	}
}
