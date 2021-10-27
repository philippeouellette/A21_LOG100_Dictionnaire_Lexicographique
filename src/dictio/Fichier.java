package dictio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fichier{
	
	private String readFile;
	
	public Fichier(String file) {
		this.readFile = file;
	}
	
	
	
	/**
	 * Allows access to readFile
	 * @return readFile
	 */
	public String getReadFile() {
		return this.readFile;
	}
	
	/**
	 * Take line per line of file
	 * @return a list of sentence from the file 
	 */
	public List<String> getSentence(){
		
		List<String> listSentence = new ArrayList<String>();
		String file = readFile;
		String[] wordsTab = file.split("\n");
		
		for(int i=0;i<wordsTab.length;i++) {
			listSentence.add(wordsTab[i]);
		}
		
		return listSentence;	
	}
	
	
	
	/**
	 * Extract words from their definition with the "&" separator 
	 * @return list of word
	 */
	public List<String> getWords() {
		
		List<String> listWord = new ArrayList<String>();
		
		for(int i=0;i<getSentence().size();i++) {
			listWord.add(getSentence().get(i).split(" & ")[0]);
		}
		
		
		return listWord;
		
	}
	
	/**
	 * Extract definition from their word with the "&" separator 
	 * @return list of definition
	 */
	public List<String> getDef() {
		
		List<String> listDef = new ArrayList<String>();
		
		for(int i=0;i<getSentence().size();i++) {
			listDef.add(getSentence().get(i).split(" & ")[1]);
		}
		
		return listDef;
		
	}
}
