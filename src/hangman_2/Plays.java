package hangman_2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Plays implements State{
	
	String word;
	private int wrong_counter;
	public int score ;
	List<String> listOfWords = new ArrayList<>();
	

	public Plays(String w, List<String> l, int s, int wr) {	
		word = w;
		listOfWords = l;
		s = score;
		wr = wrong_counter;
	
	}
	
	public List<String> createList(){
		List<String> newList = new ArrayList<>();
		for (String i : this.listOfWords) {
			if (i.length() == this.word.length()) {
				newList.add(i);		
			}
		}
		return  newList;				
	}
	
	
	public Map<Integer, Double> createProbs(List<String> lista, int position, List<Integer> seen) {
		
		ArrayList<ArrayList<Double>> choices = new ArrayList<>(this.word.length());
		double d;
		for( int j=0 ; j < this.word.length(); j++) {
			ArrayList<Double> inner = new ArrayList<>(26);
			choices.add(inner);
		}
		
		for (int i = 0; i < 26 ; i++) {
			if(!(seen.contains(i))){
				d = calculateProb(lista, position, (char)(i+65));
				choices.get(position).add(d);
			}
		}		
		
		
		Map<Integer, Double> pairs = new HashMap<>();
		
		List<Double> a = new ArrayList<>();
		a = choices.get(position);
		
		for(int i = 0; i <  a.size(); i++) {
			if(a.get(i) > 0) {
				pairs.put(i, a.get(i));
			}
		}
		 	 
		 WordsUtil compares = new WordsUtil();
		 pairs = compares.sortByValue(pairs);

		 return pairs;
		
	}
	public double calculateProb(List<String> words, int position, char character) {
		
		int counter = 0;
		for (String word : words ) {
			if(word.charAt(position) == character) {
				counter ++ ;
			}
		}
		
		DecimalFormat df = new DecimalFormat("#.####");
		double probability = Double.parseDouble(df.format((double)counter/words.size()));
		return  probability;
	}


	
	public double pickCharacter(char a,int position,Map<Integer, Double> pairs) {
		
		 if(a == this.word.charAt(position)){
			 double k = pairs.get((int)(a-65));
			 return k;
		 }
		 else {
			 this.wrong_counter++;
			 if(this.wrong_counter > 5) {
				 return -1;
			 }
			 return 0;
		 }
	}

	public void scoreWins(double prob) {
		if (prob >= 0.6) {
			this.score+=5;
		}
		else if (prob >= 0.4) {
			this.score +=10;
		}
		else if (prob >= 0.25) {
			this.score+=15;
		}
		else {
			this.score+=30;
		}
	}


	public void scoreLoses() {
		if(this.score >15) {
			this.score -= 15;
		}
		else {
			this.score = 0;
		}
	}

	public ArrayList<Boolean> lettersFound(ArrayList<Boolean> grammata, int position) {
		
		if (position == -2) {
			for (int i = 0 ; i < this.word.length(); i++) {
				grammata.add(false);
			}
		}
		
		else if (position != -1) {
			grammata.set(position,true);
		}
		
		return grammata;
		
	}


	public List<String> updateProbs(List<String> words,char a, int position,  boolean found, List<Integer> seen) {
		List<String> newList = new ArrayList<>();
		
		
		for (String i : words) {
			
			if ((i.charAt(position) == a) && found) {
				newList.add(i);
			}
			else if ((i.charAt(position) != a) && !seen.contains(Character.getNumericValue(a)) && !found) {
				newList.add(i);
			}
		}
	
		//System.out.println(newList);
		return newList;
	}

	//found letters are in certain positions' list
	public List<Integer> possiblePositions(List<Boolean> found) {
		List<Integer> newList = new ArrayList<>();
		for (int i = 0; i<this.word.length(); i++) {
			if (found.get(i) == true) {
				newList.add(i);
			}	
		}
		return newList;
	}

	public boolean endOfGame(List<Boolean> found) {
		 boolean flag = true;
		 for (boolean i : found) {
			 if (!i) {
				 flag = false;
			 }
		 }
		
		return flag;
	}

}
