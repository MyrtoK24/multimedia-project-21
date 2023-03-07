package application;


import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import hangman_1.CreateDict;
import hangman_1.UnbalancedException;
import hangman_1.UndersizeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateController { 
	
	@FXML
	private TextField search_id;
	@FXML
	private AnchorPane popup2;
	@FXML
	private Label message2;
	@FXML
	private Button search;
	@FXML
	private Button finish2;

	Stage stage;
	public String id;
	
	
		public void DictSearch(ActionEvent event) throws IOException {
	
			id = search_id.getText();

			CreateDict dictionary1 = new CreateDict(id);
			List<String> init = dictionary1.urlToText();
			
			try {
				List<String> finalList = dictionary1.Dictionaries(init);
				
				if(!(finalList == null) && !finalList.isEmpty()) {
					String nameFile = dictionary1.CreateFile();
					dictionary1.WriteFile(finalList,nameFile);
					message2.setText("Successfully created");
				}
			}
			catch (FileAlreadyExistsException e) {
				message2.setText("File already exists");
			} 			
			catch (UnbalancedException e) {
				message2.setText("Not enough big words");
			}
			catch (UndersizeException e) {
				message2.setText("Not enough words");
			}	
			
		}
				
		public void finish() {	
			stage = (Stage) popup2.getScene().getWindow();		
			stage.close();
		}
}
