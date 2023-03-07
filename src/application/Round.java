package application;

import javafx.beans.property.SimpleStringProperty;

public class Round {
	 
	private SimpleStringProperty word; 
	private  SimpleStringProperty tries; 
	private  SimpleStringProperty winner; 
	 
	    public Round() {} 
	 
	    public Round(String w, String t, String wi) { 
	        this.word = new  SimpleStringProperty(w);
	        this.tries = new  SimpleStringProperty(t);
	        this.winner = new  SimpleStringProperty(wi);
	    } 
	 
	    public String getWord() { 
	        return word.get();
	    } 
	    
	    public void setWord(String wor) { 
	        word.set(wor);
	    } 
	    
	    public String getTries() { 
	        return tries.get(); 
	    } 
	    public void setTries(String trys) { 
	        tries.set(trys); 
	    } 
	   
	    public String getWinner() { 
	        return winner.get(); 
	    } 
	 
	    public void setWinner(String winn) { 
	    	winner.set(winn);
	    } 
}
