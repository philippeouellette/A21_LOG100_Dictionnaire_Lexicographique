import java.util.ArrayList;
import java.util.jar.JarException;

import com.sun.java.swing.plaf.motif.resources.motif;

public class LexiNode {
	char representative_letter;
	String current_word;
	String definition;
	ArrayList<LexiNode> childs = new ArrayList<LexiNode>();
	
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
	
	public char getRepresentative_letter(){
		return representative_letter;
	}
	
	public String getCurrent_word(){
		return current_word;
	}
	
	public String getDefinition(){
		return definition;
	}
	
	public ArrayList<LexiNode> getChilds(){
		return childs;
	}
	
	public static void main(String[] args) {
		LexiNode dictionnaire = new LexiNode();
		dictionnaire.add_word("bien", "une definition");
	}
}
