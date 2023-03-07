package hangman_1;


import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class CreateDict {

	private String dict_id;
	
	public CreateDict(String id) {
		dict_id = id;
	}
	
	public List<String> urlToText() throws IOException{
		
		URL a = new URL("https://openlibrary.org/works/" + this.dict_id + ".json");
		JSONReader result = new JSONReader(a);
		String text = result.getString();
		List<String> listOfWords = result.WordList(text);
		//System.out.println("Found url");
		return listOfWords;
	}
	
	public List<String> Dictionaries(List<String> listWords) throws  UnbalancedException, UndersizeException{
		
		Selector lista = new Selector(listWords);
		List<String> finalList = lista.valid();
		return finalList;
	}	
		

	public String CreateFile() throws FileAlreadyExistsException, IOException {
				
		String nameFile = "medialab/hangman_" + this.dict_id + ".txt";

		File myObj = new File(nameFile);
		
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} 
			else {
				throw new FileAlreadyExistsException(this.dict_id);
			}
		   
		return nameFile;
	  }
		
	public void WriteFile(List<String> lista, String fileName) {
		 try {
			 
		      FileWriter myWriter = new FileWriter(fileName);
		      for(String str: lista) {
		    	  myWriter.write(str + System.lineSeparator());
		      }
		      myWriter.close();
		      //System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		    	System.out.println("An error occurred");
		    	e.printStackTrace();
		    }
		}

		
}
