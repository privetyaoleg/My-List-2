package by.htp.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sentence {

	private List<Word> words = new ArrayList<>();
	private List<Integer> startOfWords = new ArrayList<>();

	private final String originalSentence;

	public Sentence(String sentence) {
		this.originalSentence = sentence;
		splitSentence(this);
		startOfWord(this);

	}
	
	
	/**
	 * The method prints words and amount of repeating this word in sentence.
	 */
	public void printWordsAmount() {

		for (int i = 0; i < words.size(); i++) {
			int value = 0;
			for (int j = 0; j < words.size() - 1; j++) {
				if (words.get(i).getName().equalsIgnoreCase(words.get(j).getName())) {
					value++;
				}
			}
			System.out.printf("Word: %s \t amount = %d %n", words.get(i).getName(), value);
		}
	}

	public String changeLetter(int position, char symbol) {

		position--;
		List<Word> words = new ArrayList<>();
		for (Word word : this.words) {
			String changingWord = word.changeLetter(symbol, position);
			words.add(new Word(changingWord));
		}

		return wordsInSentenceByIndex(words, startOfWords);

	}

	/**
	 * The method prints numeric value of word by alphabet, it avoids all punctuation symbols.
	 */
	public void printByAlphapetNum() {

		char[] originalSentence = this.originalSentence.toCharArray();
		String[] numWords = new String[originalSentence.length];

		for (int i = 0; i < originalSentence.length; i++) {
			if (originalSentence[i] >= 65 && originalSentence[i] <= 90) {
				int temp = (int) originalSentence[i] - 64;
				numWords[i] = String.valueOf(temp);
			} else if (originalSentence[i] >= 97 && originalSentence[i] <= 122) {
				int temp = (int) originalSentence[i] - 96;
				numWords[i] = String.valueOf(temp);
			} else {
				numWords[i] = String.valueOf(originalSentence[i]);
			}
		}

		System.out.println(fromArrayToString(numWords));
	}
	
	public int amountPunctuationMarks(){
		int amount = 0;
		String marks = "!?,.;";
		char[] sentenceInArray = originalSentence.toCharArray();
		for (char c : sentenceInArray) {
			if(marks.contains(String.valueOf(c))){
				amount++;
			}
		}
		return amount;
	}
	
	/**
	 * This method deletes area between two symbols which are indicated in parameters.
	 * The method works with ArrayList and loops;
	 * 
	 * @param border1 - start of deleting
	 * @param border2 - end of deleting
	 */

	
	public String deleteBlock(Character border1, Character border2) {

		List<Character> sentence = new LinkedList<Character>();

		for (Character ch : originalSentence.toCharArray()) {
			sentence.add(ch);
		}

		for (int i = 0; i < sentence.size(); i++) {

			if (sentence.get(i) == border1) {
				do {
					sentence.remove(i + 1);
				} while (sentence.get(i + 1) != border2);
			}
		}

		return fromListToString(sentence);

	}
	
	/**
	 * The second solution of deleting area between two delimiters.
	 * It works only for one block.
	 * There are used methods String.indexOf() and StringBuffer.delete();
	 * @param border1 - start of deleting
	 * @param border2 - end of deleting
	 */
	
	public String deleteBlock1(Character border1, Character border2){
		
		int start = originalSentence.indexOf(border1);
		int finish = originalSentence.indexOf(border2);

		StringBuilder sb = new StringBuilder(originalSentence);
		sb.delete(start + 1, finish);
		return sb.toString();
		
	}
	
	public boolean isConsonantMore(int numOfWord){
		int countConsonant = 0;
		int countVowel = 0;
		String consonantLetters = "qwrtpsdfghjklzxcvbnm";

		for (Character cha : words.get(numOfWord - 1).getSymbols()) {
			if (consonantLetters.contains(String.valueOf(cha))){
				countConsonant++;
			} else {
				countVowel++;
			}
		}	
		return countConsonant > countVowel;
	}
	
	public int amountVowelStart(){
		int amount = 0;
		String consonantLetters = "qwrtpsdfghjklzxcvbnm";
		for (Word word : words){
			if(!consonantLetters.contains(String.valueOf(word.getSymbols().get(0)))){
				amount++;
			}
		}
		return amount;
	}
	
	private String fromArrayToString(String[] array){
		StringBuilder builder = new StringBuilder();
		for (String ch : array) {
			builder.append(ch).append(" ");

		}
		return builder.toString();
	}
	
	private String wordsInSentenceByIndex(List<Word> words, List<Integer> num) {
		char[] sentenceInArray = getOriginalSentence().toCharArray();
		for (int j = 0; j < num.size(); j++) {
			char[] wordInArray = words.get(j).getName().toCharArray();

			for (int i = 0; i < wordInArray.length; i++) {
				sentenceInArray[num.get(j) + i] = wordInArray[i];
			}
		}
		return new String(sentenceInArray);

	}
	
	private void splitSentence(Sentence sentence) {
		for (String splitWord : sentence.getOriginalSentence().split("[\\s+\\d\\p{Punct}]")) {
			if (splitWord.length() != 0) {
				words.add(new Word(splitWord));
			}
		}
	}
	
	/**
	 * 
	 * This method finds position beginning each words and adds their in int[] with number of starting all words.
	 * @param sentence
	 */
	private void startOfWord(Sentence sentence) {
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
	
	private String fromListToString(List<Character> list) {
		
		StringBuilder sen = new StringBuilder();
		for (Character ch : list) {
			sen.append(ch);
		}
		return sen.toString();
	}

	public String getOriginalSentence() {
		return originalSentence;
	}
	
	
	
	public List<Word> getWords() {
		return words;
	}
	
}
