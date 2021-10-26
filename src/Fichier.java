import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fichier {
	
	public String readFile;
	
	public Fichier(String file) {
		this.readFile = file;
	}
	
	public List<String> getSentence(){
		
		List<String> listSentence = new ArrayList<String>();
		String file = readFile;
		String[] wordsTab = file.split("--");
		
		for(int i=0;i<wordsTab.length;i++) {
			listSentence.add(wordsTab[i]);
		}
		
		//System.out.print(listSentence);
		
		return listSentence;	
	}
	
	
	
	
	public List<String> getWords() {
		
		List<String> listWord = new ArrayList<String>();
		
		for(int i=0;i<getSentence().size();i++) {
			listWord.add(getSentence().get(i).split(" & ")[0]);
		}
		
		//System.out.print(listWord);
		
		return listWord;
		
	}
	
	
	public List<String> getDef() {
		
		List<String> listDef = new ArrayList<String>();
		
		for(int i=0;i<getSentence().size();i++) {
			listDef.add(getSentence().get(i).split(" & ")[1]);
		}
		
		//System.out.print(listDef);
		
		return listDef;
		
	}
	

	public static void main(String[] args) {
		

	}

}
