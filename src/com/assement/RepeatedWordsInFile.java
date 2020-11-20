package com.assement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RepeatedWordsInFile {
	public static void main(String[] args) {

		HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader("passage.txt"));
			String currentLine = "";
			int wc = 0;
			while ((currentLine = reader.readLine()) !=null && !currentLine.isEmpty() ) {
				String[] words = currentLine.toLowerCase().split(" ");
				wc = wc + words.length;
				for (String word : words) {
					if (wordCountMap.containsKey(word)) {
						wordCountMap.put(word, wordCountMap.get(word) + 1);
					}
					else {
						wordCountMap.put(word, 1);
					}
				}
				currentLine = reader.readLine();
			}
			System.out.println("Number of words in the file:" + wc);
			Set<Entry<String, Integer>> entrySet = wordCountMap.entrySet();
			List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(entrySet);
			Collections.sort(list, new Comparator<Entry<String, Integer>>() {
				@Override
				public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
					return (e2.getValue().compareTo(e1.getValue()));
				}
			});
			System.out.println("Repeated Words In Input File Are :");
			int  i=0;
			Map<String,Integer> subMap = new LinkedHashMap<String,Integer>();
			for (Entry<String, Integer> entry : list) {
				i++;
				if (entry.getValue() > 1 && i<=10) {
					subMap.put(entry.getKey(),entry.getValue());
					System.out.println(entry.getKey() + " : " + entry.getValue());
				}
			}
		      readSentences(subMap);
		} catch (

		IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close(); // Closing the reader
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	 public static void readSentences(Map<String, Integer> subMap) throws IOException{
		   File f1=new File("passage.txt");
		     
		      int wc=0;    
		      FileReader fr = new FileReader(f1);   
		      BufferedReader br = new BufferedReader(fr);   
		      String s;
		      int count = 0;
		      
		      while((s=br.readLine())!=null)    
		      {
		    	  if(!s.isEmpty()) {
		    		  ++count;
		    	  }
		      }
		      fr.close(); 
		      
		      String[] sentences= new String[count];  
		      
		      fr = new FileReader(f1);   
		      br = new BufferedReader(fr);
		      count =0;
		      while((s=br.readLine())!=null)    
		      {
		    	  if(!s.isEmpty()) {
		    		  
		    		  sentences[count] = s;
		    		  count++;
		    	  }
		      }
		      fr.close(); 
		      String lastSentence = "";
		      for( int i=0 ; i<=sentences.length-1;i++){
		    	  String mostOccured = (String) subMap.keySet().toArray()[0];
		    	if(sentences[i] !=null && sentences[i].contains(mostOccured)){
		    		lastSentence = sentences[i];	
		    	}
		      }
		      System.out.println("Last Sentence that contains most used word:");
		      System.out.println(lastSentence);
	   }
	   
}