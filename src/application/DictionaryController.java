package application;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DictionaryController {
	
	@FXML
	private AnchorPane popup_dict;
	@FXML
	private Label six;
	@FXML
	private Label seven;
	@FXML
	private Label ten;
	@FXML
	private Button ok_btn;

	private Stage stage;
	@FXML
	void initialize() {		
		
		int counter6 = 0;
		int counter7 = 0;
		int counter10 = 0;
		List<String> lista = new ArrayList<>();
		lista = Data.words;
		
		for (String word : lista) {
			int a = word.length() ;
			if(a >=10) {
				counter10++;			
			}
			else if(a > 6 ) {
				counter7++;
			}
			else {
				counter6++;	
			}
		}
		
		float a = (float)(counter6 *100) / lista.size();
		float b = (float)(counter7 *100) / lista.size();
		float c = (float)(counter10 *100) / lista.size();
		six.setText(String.format("%.2f", a)+"%");
		seven.setText(String.format("%.2f", b)+"%");
		ten.setText(String.format("%.2f", c)+"%");
		
	}
	
	public void finish() {
		
		stage = (Stage) popup_dict.getScene().getWindow();		
		stage.close();
		
		
	}
}
