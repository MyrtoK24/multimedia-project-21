package hangman_1;

import java.util.ArrayList;
import java.util.List;

public class Selector {

	List<String> wordList;
	
	public Selector(List<String> s) {
		wordList = s;
	}
	
	public List<String> valid() throws UnbalancedException, UndersizeException{
		
		List<String> words2 = new ArrayList<>();
		
		int duplicate_counter = 0;
		int less_counter = 0;
		int big_words = 0;
		
		for (String i : this.wordList) {		
			try{
				if(i.length()>=6){
					if(!words2.contains(i)){
							words2.add(i);
							if(i.length()>=9) {
								big_words++;
							}
					}
					else
						throw new InvalidCountExeception("Duplicate word found");
				}	
				else throw new InvalidRangeException("Very small word");
			}		
			catch (InvalidRangeException err) {
				less_counter ++;
			}
			catch (InvalidCountExeception err) {
				duplicate_counter ++;
			}
			
		}
		
		int num_words = wordList.size()-duplicate_counter-less_counter;

		if (num_words == words2.size()) {
			if (big_words*5 < num_words) {
				throw new UnbalancedException("Not enough big words");	
			}
			
			if (words2.size() < 20) {
				throw new UndersizeException("Not enough words");
			}
		}
		else {
			System.out.println("Something went wrong");
			}
		
		return words2;
	}	
}