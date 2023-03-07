package application;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class RoundsController {
	@FXML
	private AnchorPane popup_rounds;
	@FXML
	private TableView<Round> table;
	@FXML
	private TableColumn<Round, String> Word;
	@FXML
	private TableColumn<Round, String> Tries;	
	@FXML
	private TableColumn<Round, String> Winner;
	
	@FXML 
	public void initialize() { 
		
			table.setItems(Data.roundList);		
	        Word.setCellValueFactory(new PropertyValueFactory<Round,String>("Word")); 
	        Tries.setCellValueFactory(new PropertyValueFactory<Round,String>("Tries")); 
	        Winner.setCellValueFactory(new PropertyValueFactory<Round,String>("Winner"));
		}
	}
