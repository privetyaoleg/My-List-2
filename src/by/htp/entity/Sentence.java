package by.htp.entity;
import java.util.ArrayList;
import java.util.List;


public class Sentence {

	
	private ArrayList<Word> words = new ArrayList<>();
	private ArrayList<Integer> startOfWords = new ArrayList<>();
	
	private final String originalSentence;

	
	
	public String deleteBlock(Character border1, Character border2){
		
		int start = 0;
		int finish = 0;
		
		ArrayList<Character> sentence = new ArrayList<Character>();
		
		
		for (Character ch : originalSentence.toCharArray()){
			sentence.add(ch);
		}
		
		for (int i = 0; i < sentence.size(); i++) {
			
			if (sentence.get(i) == border1){
				do{
					sentence.remove(i+1);
				}
				while(sentence.get(i+1) != border2);
				
				
			}
		}
		
		 StringBuilder sen = new StringBuilder();
		 for(Character ch: sentence){
			 	
		        sen.append(ch);
		    }
		
		return sen.toString();

	}
	
	
	public void printWordsAmount(){
		
		for (int i = 0; i < words.size(); i++){
			int value = 0;
			for (int j = 0; j < words.size() - 1; j++){
				if(words.get(i).getName().equalsIgnoreCase(words.get(j).getName())){
					value++;
				}	
			}
			System.out.printf("Word: %s \t amount = %d %n", words.get(i).getName(), value);
		}
	}
	
	
	

	
	
	public String changeLetter(int position, char symbol){
		
		position--;
		List<Word> words = new ArrayList<>();
		for (Word word : this.words) {
			String changingWord = word.changeLetter(symbol, position);
			words.add(new Word(changingWord));
		}
		
		return wordsInSentenceByIndex(words, startOfWords);
		
	}
	
	
	public Sentence (String sentence){
		this.originalSentence = sentence;
		splitSentence(this);
		startOfWord(this);
		
	}
	
	public void splitSentence (Sentence sentence){
		for (String splitWord: sentence.getOriginalSentence().split("[\\s+\\d\\p{Punct}]")){
			if (splitWord.length() != 0) {		
				words.add(new Word(splitWord));
			}
		} 
	}
	
	
	public void startOfWord (Sentence sentence) {
		char[] sentenceInArray = sentence.getOriginalSentence().toCharArray(); 
		boolean startOfWord = false;  
		for (int i = 0; i < sentenceInArray.length; i++) {
			if (sentenceInArray[i] < 32 || sentenceInArray[i] > 64) { 
					if (startOfWord == false) { 
						startOfWord = true; 
						startOfWords.add(i); 
					} 
			} else { 
				startOfWord = false; 
			} 
		} 
	}
	
	
	public String wordsInSentenceByIndex(List<Word> words, List<Integer> num) {
		char[] sentenceInArray = getOriginalSentence().toCharArray();
		for (int j = 0; j < num.size(); j++ ) {
			char[] wordInArray = words.get(j).getName().toCharArray();
			
				for (int i = 0; i < wordInArray.length; i++){
					sentenceInArray[num.get(j)+i] = wordInArray[i];
				}
		}
		return new String(sentenceInArray);
		
	}
	

	public void printByAlphapetNum(){
		
		char[] originalSentence = this.originalSentence.toCharArray();
		String[] numWords = new String[originalSentence.length];
		
		for(int i=0; i<originalSentence.length; i++){
			if(originalSentence[i] >= 65 && originalSentence[i] <= 90){
				int temp = (int)originalSentence[i] - 64;
				numWords[i] = String.valueOf(temp);
			} else if (originalSentence[i] >= 97 && originalSentence[i] <= 122){
				int temp = (int)originalSentence[i] - 96;
				numWords[i] = String.valueOf(temp);
			} else {
				numWords[i] = String.valueOf(originalSentence[i]);
			}
		}
		
		StringBuilder builder = new StringBuilder();
	    for(String ch: numWords){
	        builder.append(ch).append(" ");
	        
	    }

	    System.out.println(builder);
	}

	public String getOriginalSentence() {
		return originalSentence;
	}

}
