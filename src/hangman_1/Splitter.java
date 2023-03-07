package hangman_1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Splitter {

	  public List<String> Split(String text) {
		  
			String  str [] = text.split("\\W|\\d+");
			List<String> words = new LinkedList<String>(Arrays.asList(str));		
			words.removeIf(String::isEmpty);
			words.replaceAll(String::toUpperCase);
			return words;
	  }	  
}