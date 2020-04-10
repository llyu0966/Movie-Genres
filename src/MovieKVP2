/*
 * Class: CISC 3130
 * Section: TY2
 * EmplId: 23809622
 * Name: Liyu Lin
 */
package moviekvp2;
import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

// count how many movies came out for each genre each year.
public class MovieKVP2 {
    
    // print the hashmap
    static void print(AbstractMap<String, Integer> map){
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    
    // print the nested hashmap
    static void print(HashMap<String, HashMap<String, Integer>> map){
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + ", " + entry.getValue().toString());
        }
    }
    
    
    public static void main(String[] args) {
        HashMap<String, Integer> myMap = new HashMap<>();
        HashMap<String, Integer> temp = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> nestedMap = new HashMap<>();
        ArrayList<String> myAL = new ArrayList<>();
        String line = "";
        String cvsSplitBy = ",";
        
        
        try (BufferedReader br = new BufferedReader(new FileReader("/data/movies.csv"))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] movieInfo = line.split(cvsSplitBy);
                int lastIndex = movieInfo.length - 1;
                
                // extract the release year
                if(movieInfo[lastIndex-1].contains("\"") || movieInfo[lastIndex-1].endsWith(")")){
                    int begin = movieInfo[lastIndex-1].lastIndexOf("(");
                    int end = movieInfo[lastIndex-1].lastIndexOf(")");
                    String releaseYear = movieInfo[lastIndex-1].substring(begin+1, end);
                    
                    // use pipe as separator
                    myAL.clear();
                    myAL.addAll(Arrays.asList(movieInfo[lastIndex].split("\\|")));
                    
                    if(!nestedMap.containsKey(releaseYear)){
                        myMap = new HashMap<>();
                       
                        // checking each string of arrayList
                        for(String genre : myAL){
                            // If genre is not present in map,
                            // putting this genre to map with 1 as it's value
                            myMap.put(genre, 1);
                         }
                            myMap.remove("genres");
                            nestedMap.put(releaseYear, myMap);
                    }
                    else{
                        temp = new HashMap<>();
                        temp = nestedMap.get(releaseYear);
                        
                        // checking each string of genreArray
                        for(String genre : myAL){
                           
                           if(temp.containsKey(genre))
                           {
                           // If genre is present in map,
                           // incrementing it's count by 1
                             
                             temp.put(genre, temp.get(genre) + 1);
                           
                           }
                           else{
                            // If genre is not present in map,
                            // putting this genre to map with 1 as it's value
                             temp.put(genre, 1);
                           }
                           temp.remove("genres");
                           nestedMap.put(releaseYear, temp);
                        }
                        
                    }
                       
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
          
          // sort the map by key
          HashMap<String, HashMap<String, Integer>> sorted = nestedMap
                  .entrySet()
                  .stream()
                  .sorted(comparingByKey())
                  .collect(
                  toMap(e -> e.getKey(), e -> e.getValue(),
                  (e1, e2) -> e2, LinkedHashMap::new));
         
          // print the sorted hashmap ascending order
          print(sorted);
    }
    
}// end class
