package hangman_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface State {
	
	public List<String> createList();
	public Map<Integer, Double> createProbs(List<String> lista, int position,List<Integer> seen);
	public double pickCharacter(char a,int position, Map<Integer, Double> pairs);
	public double calculateProb(List<String> letters, int position,char character);
	public void scoreWins(double prob);
	public void scoreLoses();
	public ArrayList<Boolean> lettersFound(ArrayList<Boolean> grammata,int position);
	public List<String> updateProbs(List<String> valid_words,char a, int position, boolean found,List<Integer> seen);
	public List<Integer> possiblePositions(List<Boolean> found);
	public boolean endOfGame(List<Boolean> found);
}
