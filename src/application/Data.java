package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Data {
	public static String text = "";
	public static boolean is_loaded = false;
	public static String word;
	public static List<String> words = new ArrayList<String>();
	public static String tries;
	public static String winner;
	public static boolean finishGame;
	public static ObservableList<Round> roundList = FXCollections.observableArrayList();

}
