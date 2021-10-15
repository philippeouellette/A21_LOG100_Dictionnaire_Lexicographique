import java.util.ArrayList;

public class LexiNode {
	char representative_letter;
	String current_word;
	String definition;
	ArrayList<LexiNode> childs = new ArrayList<LexiNode>();
	
	public void add_word(String word, String definition){
		if(word.length() > 1){
			LexiNode child = new LexiNode();
			childs.add(child);
			add_word(word, definition, word.substring(1));
		}
	}
	public void add_word(String word, String definition, String subword){
		representative_letter = subword.charAt(0);
		if(word.length() > 1){
			LexiNode child = new LexiNode();
			childs.add(child);
			add_word(word, definition, word.substring(1));
		}
		else if (word.length() == 1) {
			this.current_word = word;
			this.definition = definition;
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
}
