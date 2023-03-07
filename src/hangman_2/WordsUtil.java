package hangman_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.*;

public class WordsUtil {
	
	public int getRandomNumber(int min, int max) {
	    Random random = new Random();
	    return random.nextInt(max - min) + min;
	}
	
	 public Map<Integer,Double> sortByValue(Map<Integer,Double> hm)
	    {
	        // Create a list from elements of HashMap
	        List<Map.Entry<Integer,Double> > list = new LinkedList<Map.Entry<Integer,Double> >(hm.entrySet());
	 
	        // Sort the list using lambda expression
	        Collections.sort(list,(i1,i2) -> i2.getValue().compareTo(i1.getValue()));
	 
	        // put data from sorted list to hashmap
	        HashMap<Integer,Double> temp = new LinkedHashMap<Integer,Double>();
	        for (Map.Entry<Integer,Double> aa : list) {
	        		temp.put(aa.getKey(), aa.getValue());
	        }
	        return temp;
	    }
	 
	public  String usingBufferedReader(String filePath) throws IOException
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (FileNotFoundException e) 
        {
        	System.out.println("file not found");
        	//e.printStackTrace();
        }
        return contentBuilder.toString();
    }
	
	
	
	public List<String> lista(String fileName) throws IOException{
		String filePath = fileName;
		WordsUtil some = new WordsUtil();
        String data = some.usingBufferedReader( filePath );
        List<String> listt = new ArrayList<>(Arrays.asList(data.split("\\n")));
    	
    	return listt;
	}
	
	public String pickWord(List<String> listt){

		
    	WordsUtil lista = new WordsUtil();
		String pickedWord = lista.randWord(listt);
		
		return pickedWord;
	
	}
	
	public String randWord(List<String> listt) {

	    Random rand = new Random();
	    String randomElem = listt.get(rand.nextInt(listt.size()));
		return randomElem;
	}
	
}
