package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import hangman_2.Plays;
import hangman_2.WordsUtil;


public class Controller {
	

	@FXML
	private Pane alphabet;
	@FXML
    private Pane wrong1;
	@FXML
    private Line wrong2;
	@FXML
    private Line wrong3;
	@FXML
    private Line wrong4;
	@FXML
    private Line wrong5;
	@FXML
    private Line wrong6;	
	@FXML
	public Label available;	
	@FXML
	private Label score;
	@FXML
	private Label success;
	@FXML
	private AnchorPane left;
	@FXML
	private AnchorPane right;
	@FXML
	private AnchorPane bottom;
	@FXML
    private HBox hbox;
	@FXML
    private Label message;
	
	private String word;
	private List<Label> spaces;
	private List<Button> choices ;
	private HBox hboxx = new HBox();
	private Pane panee = new Pane();
	private AnchorPane bottom_box = new AnchorPane();
	private int position = -1;
	private int oldposition = -2;
	private String letter = null;
	private Label [] labels;
	private ArrayList<Boolean> letters_found ;
	private Map<Integer, Double> epiloges = new HashMap<Integer, Double>();
	private int wrongs = 0 ;
	private boolean end = false;
	private boolean end2 = false;
	private List<Integer> poss;
	private Plays player;
	private ArrayList<List<Integer>> seen;
	private List<String> valid_words;
	private int counter;
	private float percent = 0;
	private int counter_rounds = 1;
	
	
	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 * Initially only the menu bar is shown on the main window.
	 * 
	 */
	@FXML
	void initialize() {		
		hbox.setVisible(false);
		bottom.setVisible(false);
		left.setVisible(false);
		right.setVisible(false);	
		
	}
	
	/**
	 * Method that initializes all the parameters used
	 * for a single game round to start, including
	 * showing all the game's fields, initializing
	 * game variables and calling other methods.
	 * 
	 * @param event  the event type
	 * @throws IOException  If an input or output 
	 *						exception occurred
	 */
	public void NewGame(ActionEvent event) throws IOException {
			
		if(Data.is_loaded) {
			
			hbox.setVisible(true);
			bottom.setVisible(true);
			left.setVisible(true);
			right.setVisible(true);			
			wrong1.setVisible(false);
			wrong2.setVisible(false);
			wrong3.setVisible(false);
			wrong4.setVisible(false);
			wrong5.setVisible(false);
			wrong6.setVisible(false);
			bottom_box.setDisable(false);
            alphabet.setDisable(true);
            
			Data.finishGame = false;
			available.setText(Data.text);
			score.setText("0");
			success.setText("-");		
			WordsUtil w = new WordsUtil();
			word = w.pickWord(Data.words);
			Data.word = word;
			System.out.println(word);
			player = new Plays(word, Data.words, 0, 0);
			letters_found  = new ArrayList<>(word.length());
			letters_found = player.lettersFound(letters_found,-2);
			poss = new ArrayList<>();
			seen = new ArrayList<>();
			choices = new ArrayList<>();
			wrongs = 0;
			counter = 0;
			end = end2 = false;
			
			for( int j=0 ; j < word.length(); j++) {
				List<Integer> inner = new ArrayList<>(26);
				seen.add(inner);
			}
		    valid_words = new ArrayList<>();
		    valid_words = player.createList();
		    
		    
		    BtnsHandler(false);
			InitSpaces(word.length());		
			CreateBottom(word.length());
			alphabet.setDisable(true);
			BtnsHandler(true);		
			
		}		
		else {
			//System.out.println("Please choose a dictionary");
		}	
}
		
	
	/**
	 * Method that populates and shows the available
	 * positions of the picked word on the left field
	 * of the game's main window.
	 * 
	 * @param length int value of the length of the picked word
	 * @throws IOException  If an input or output 
	 *						exception occurred
	 *
	 */
	public void InitSpaces (int length) throws IOException {
		int x = 330 - (word.length()*15);
		
		spaces = new ArrayList<>(); 
		System.out.println("Initializing game");
		labels = new Label[word.length()];
		
		for (int i = 0; i<word.length();i++) {
			  
			labels[i] = new Label();
			labels[i].setTranslateX(x);
			labels[i].setTranslateY(400);
			labels[i].setText("__");
			labels[i].setPrefWidth(30);
			labels[i].setTextAlignment(TextAlignment.CENTER);     
		    spaces.add(labels[i]);      
		    x += 16;
		}
			
			hboxx.getChildren().clear(); 
		    hboxx.getChildren().addAll(spaces);
		    left.getChildren().remove(hboxx);
			left.getChildren().add(hboxx);
	
		}


	/**
	 * Method that corresponds to the menu bar's Load option clicked
	 * by the player that initializes an FXMLLoader with the correct
	 * fxml file.
	 * 
	 * @param event the event type
	 * @throws IOException If an input or output 
	 *						exception occurred
	 *
	 */
	public void Load(ActionEvent event) throws IOException{		
		 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoadGame.fxml"));
		MakePopupWindow(loader);
         
        }

	/**
	 * Method that corresponds to the menu bar's Create option clicked
	 * by the player that initializes an FXMLLoader with the correct
	 * fxml file.
	 * 
	 * @param event the event type
	 * @throws IOException If an input or output 
	 *						exception occurred
	 *
	 */
	public void Create(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Create.fxml"));
		MakePopupWindow(loader);
        
	}
	
	/**
	 * Method that corresponds to the menu bar's Dictionary option clicked
	 * by the player that initializes an FXMLLoader with the correct
	 * fxml file.
	 * 
	 * @param event the event type
	 * @throws IOException If an input or output 
	 *						exception occurred
	 *
	 */
	public void Dictionary(ActionEvent event) throws IOException {		

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dictionary.fxml"));
        MakePopupWindow(loader);
    
    }
	
	/**
	 * Method that corresponds to the menu bar's Rounds option clicked
	 * by the player that initializes an FXMLLoader with the correct
	 * fxml file.
	 * 
	 * @param event the event type
	 * @throws IOException If an input or output 
	 *						exception occurred
	 *
	 */
	public void Rounds(ActionEvent event) throws IOException{
		
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("Rounds.fxml"));
		 MakePopupWindow(loader);
     	 
	}
	
	
	/**
	 * Creates a Popup window.
	 * 
	 * @param loader FXML loader that corresponds to a certain file
	 * @throws IOException  If an input or output 
	 *						exception occurred
	 *
	 */
	public void MakePopupWindow(FXMLLoader loader) throws IOException {
		
		 Parent layout;
         layout = loader.load();
         Scene scene = new Scene(layout);
         Stage popupStage = new Stage();
         
         popupStage.initModality(Modality.WINDOW_MODAL);
         popupStage.setScene(scene);
         popupStage.showAndWait();
	}
	
	/**
	 * Populates and shows the bottom field of the game window,
	 * that has to be created dynamically. Specifically, it
	 * initializes the button choices each of which is
	 * given an int value and corresponds to a position/letter 
	 * of the word to be guessed.
	 * 
	 * @param length int value of picked word's length
	 * @throws IOException If an input or output 
	 *						exception occurred
	 *
	 */
	public void CreateBottom(int length) throws IOException {

		int x = 262;
		
		for (int j = 0 ; j<length ; j++) {
		
			Button r = new Button();		
			r.setTranslateX(x);
			r.setTranslateY(12);
			r.setText(Integer.toString(j+1));
			r.setId(Integer.toString(j));
			r.getStyleClass().clear();
		    r.getStyleClass().add("button1");
			choices.add(r);
		    x+=38;
		    
		}
		
		bottom_box.getChildren().clear(); 	
	    bottom_box.getChildren().addAll(choices);  
	    bottom.getChildren().remove(bottom_box); 
	    bottom.getChildren().add(bottom_box); 	
		}

	
	/**
	 * Populates and shows the Right field of the game window,
	 * that contains the possible letter choices for the player
	 * in descending order according to their probability of
	 * being the most suitable for the current position.
	 *  
	 */
	public void CreateRight() {
		
		int x = 80;
		int y = 50;
		int j = 0;
		List<Text> texts = new ArrayList<>(); 
		
		for (int a : epiloges.keySet()) {
		
				Text t = new Text();		
				t.setTranslateX(x);
				t.setTranslateY(y);
				t.setText(Character.toString((char)a+65));
				t.getStyleClass().add("fancytext");
				texts.add(t);
			     
			    if (j % 2 == 0) {	
			    	x += 70;
			    }		    
			    else {
			    	x -= 70;
			    	y += 40;
			    }	    
			    j++; 
		
		}
			panee.getChildren().clear(); 
		    panee.getChildren().addAll(texts);
		    right.getChildren().remove(panee);
		    right.getChildren().add(panee); 	
	}

	/**
	 * Sets the current letter position value  which is
	 * chosen by the player.
	 * 
	 * @param string String value of the current position
	 */
	public void SetCurrentPos(String string) {
		this.position = Integer.valueOf(string);
	}
	
	/**
	 * Sets the current letter value which is chosen by 
	 * the player.
	 * 
	 * @param obj String value of the letter chosen
	 */
	public void SetCurrentLetter(String obj) {
		this.letter = obj;
	}
	
	/**
	 * Handler method concerning the alphabet pane of the
	 * bottom field that gets the id of the letter clicked 
	 * by the player and sets the current round to continue.
	 * 
	 * @param e the event type
	 */
	public void GetLetter(ActionEvent e) {

		SetCurrentLetter(((Node) e.getSource()).getId());			
		PlayRound();
		
	}
	
	/**
	 * Shows the letter, if correctly guessed,
	 * in the correct position.
	 * 
	 * @param letter String value of letter guessed
	 * 
	 */
	public void SetLetter(String letter) {
		labels[this.position].setText(letter);
	}
	
	
	/**
	 * Shows the correct instance of the hangman 
	 * graphic on the left field of the game window,
	 * if a wrong guess is made.
	 * 
	 * @param wrongs int value of wrong guesses
	 */
	public void SetHangman(int wrongs) {
		switch (wrongs){
		case 1:
			wrong1.setVisible(true);
			break;
		case 2:
			wrong2.setVisible(true);
			break;
		case 3:
			wrong3.setVisible(true);
			break;
		case 4:
			wrong4.setVisible(true);
			break;
		case 5:
			wrong5.setVisible(true);
			break;
		case 6:
			wrong6.setVisible(true);
			break;
		}

	}
	
	/**
	 * Installs filters for alphabet buttons and position buttons
	 * in the bottom field of the screen, in a way that the player 
	 * can only choose one position or one letter each time, if the 
	 * flag is true. If the flag is false, it removes them. It also 
	 * "deactivates" interaction with the player if the game has finished.
	 * 
	 * @param flag boolean value which shows whether to install or remove the filters
	 * 
	 */
	public void BtnsHandler(boolean flag) {
		
		EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent mouseEvent) {
                if (end || end2) {
                    // disable mouse events for all children
                    mouseEvent.consume();     
                }
            }
        };
        EventHandler<MouseEvent> handler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent mouseEvent) {
            	if (position != -1) {
            		alphabet.setDisable(true);
            		bottom_box.setDisable(false);
            	}
                if (position == oldposition) { 
                    mouseEvent.consume();
                }
            }
        };
      
        if (flag) {
        	
			bottom.addEventFilter(MouseEvent.MOUSE_CLICKED,handler1);
			for (char i = 0; i < 26 ; i ++) {
				alphabet.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, handler2);
			}
		
		for (int  i =0 ; i<word.length();i++) {				
			((Button)(choices.get(i))).setOnAction((EventHandler<ActionEvent>) e -> {
				SetCurrentPos(((Node) e.getSource()).getId());
				bottom_box.setDisable(true);
                alphabet.setDisable(false);
				Playing();
	        	}); 
        	}
        }
        
		else {
			bottom.removeEventFilter(
	                MouseEvent.ANY, handler1);
			
			alphabet.removeEventFilter(
	                MouseEvent.ANY, handler2);			
		}		
	}
	
	
	/**
	 * Sets the game parameters according to the
	 * chosen position by the player and continues
	 * the current round.
	 * 
	 */
	public void Playing() {
		
		if(!poss.contains(this.position)) {
			message.setText("");
			epiloges.clear();
			labels[this.position].setStyle(" -fx-text-fill: darkorange;");
			poss = player.possiblePositions(letters_found);
			epiloges = player.createProbs(valid_words, this.position, seen.get(this.position));		
		
			CreateRight();
			oldposition = -2;
		}				
		else {
			//System.out.println("Already found");
		}

	}
	
	/**
	 * The main implementation of the game. According to the
	 * letter chosen by the player, it sets some game parameters
	 * such as the score, the success rate, the new set of
	 * available words and counts the correct and wrong guesses.
	 * Also checks if the game is completed.
	 * 
	 */
	public void PlayRound() {
		
		char ltr = this.letter.charAt(0);
		
		//System.out.println("letter : " + ltr);

		if (!seen.get(this.position).contains(Character.getNumericValue(ltr)-65)){
			
				double round = player.pickCharacter(ltr, this.position, epiloges);
				
				if (round == 0) {
					message.setText("Guess again");
					player.scoreLoses();
					seen.get(this.position).add(Character.getNumericValue(ltr)-65);
					letters_found = player.lettersFound(letters_found,-1);
					SetHangman(++wrongs);
				}
				
				else if(round >= 0){
					message.setText("Correct");
					player.scoreWins(round);
					SetLetter(this.letter);
					letters_found = player.lettersFound(letters_found,this.position);
					((Button)(choices.get(this.position))).setDisable(true);			

				}
				else {	
					end2 = true;		
				}
				
				score.setText(Integer.toString(player.score));
				valid_words = player.updateProbs(valid_words,ltr,this.position,letters_found.get(this.position),seen.get(this.position));
				right.getChildren().clear();
				//System.out.println(valid_words);
				available.setText(Integer.toString(valid_words.size()));
				end = player.endOfGame(letters_found);
				
				if(end) {	
					
					FinishGame(true);
					message.setText("You win!");
					return;
				}					
				else if(end2) {
					
					SetHangman(++wrongs);
					FinishGame(false);
					message.setText("You lose");	
					return;
				}
				else {
					
					oldposition = position;
					counter ++ ; 
					percent = (float)(((counter - wrongs) * 100) / counter ) ;
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					labels[this.position].setStyle(" -fx-text-fill: white;");
					success.setText(df.format(percent) + "%");
				}
		}				
		else {
			message.setText("'" + ltr + "' already picked \nfor this position");
			labels[this.position].setStyle(" -fx-text-fill: white;");

		}
	}

	/**
	 * Sets the game parameters when the game has finished.
	 * It shows the corresponding messages, and saves the
	 * result to the history of games played.
	 * 
	 * @param flag boolean value of whether the player chose to 
	 * 			   reveal the word or not
	 * 
	 */
	public void FinishGame(boolean flag) {
		
		position = -1;
		letter = null;
		Data.tries = Integer.toString(counter);		
		Data.finishGame = true;
		alphabet.setDisable(true);
		bottom.getChildren().remove(bottom_box); 
		message.setText("");
		
		if(flag) {
			for(int i = 0; i<word.length();i++) {
				labels[i].setTextFill(Color.web("#7CFC00"));
				Data.winner = "You";
			
			}
		}
		else {		
			for(int i = 0; i<word.length();i++) {	
				labels[i].setText(Character.toString(word.charAt(i)));
				labels[i].setTextFill(Color.web("#B22222"));
				Data.winner = "Computer";
				message.setText("You lose");	
				flag = false;
			}
		}
		
	
		if(counter_rounds <= 5) {	
			Data.roundList.add(new Round(Data.word,Data.tries,Data.winner));
		}
		else {
			Data.roundList.remove(0);
			Data.roundList.add(new Round(Data.word,Data.tries,Data.winner));
		}
		
		counter_rounds ++;

		}	

	
	/**
	 * Gives the Solution to the game only
	 * if the game has not yet finished.
	 * 
	 */
	public void Solution() {
		if (!Data.finishGame) {
			FinishGame(false);
		}
	}	
	
	
	/**
	 * Terminates the application.
	 * 
	 * @param event the event type
	 */
	public void exit(ActionEvent event) {
		  Platform.exit();
		}
}
