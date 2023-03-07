package application;

import java.util.ArrayList;
import java.util.List;

import hangman_2.WordsUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadController {
	@FXML
	private TextField dict_id;
	@FXML
	private AnchorPane popup;
	@FXML
	private Label message;
	@FXML
	private Button ok;
	@FXML
	private Button finish;

	
	Stage stage;
	private String id;
	private List<String> lista = new ArrayList<String>();
	private boolean is_loaded ;
	private String available_words;
	
		public void DictLookUp(ActionEvent event) {

			id = dict_id.getText();
			id = ("medialab/hangman_"+id+".txt");
			WordsUtil words = new WordsUtil();
			try {
				lista = words.lista(id);
				if(lista.get(0) != null && !lista.get(0).isEmpty()) {
					message.setText("Completed successfully");
					SetValues(String.valueOf(lista.size()));
					is_loaded = true;
				}
				else {
					throw new Exception();
				}	
			}
			catch (Exception e){
				e.printStackTrace();
				message.setText("Error loading");
			}
		}
		
		public void finish() {
			Data.is_loaded = this.is_loaded;
            Data.text = this.available_words ;
            Data.words = lista;
			stage = (Stage) popup.getScene().getWindow();		
			stage.close();
			
			
		}
		
		public void SetValues(String value) {
			this.available_words = value;		
		}
	
}

