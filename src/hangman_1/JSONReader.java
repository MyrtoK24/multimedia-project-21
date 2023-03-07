package hangman_1;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class JSONReader {

	private URL url;
	
	public JSONReader(URL u) {
		url = u;
	}
	
	public String getString() {
	
		String result = null;
		
		try {
	   
	    InputStream is = this.url.openStream();
	    JsonReader rdr = Json.createReader(is);
	    JsonObject obj = rdr.readObject();
	    
	    result = obj.getJsonObject("description").getString("value"); 
	    
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
		return result;
	}
	
	public List<String> WordList(String text) {

		Splitter some = new Splitter();
		List<String> b = some.Split(text);
		
		return b;
	}
}
