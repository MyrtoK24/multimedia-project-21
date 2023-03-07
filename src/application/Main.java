package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
              	   		  
	@Override
	public void start(Stage primaryStage) throws IOException {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGame.fxml"));
			
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());
			
			primaryStage.getIcons().add(new Image("hangman.png"));
			primaryStage.setResizable(false);
			primaryStage.setTitle("MediaLab Hangman");
			primaryStage.setScene(scene);
			primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
