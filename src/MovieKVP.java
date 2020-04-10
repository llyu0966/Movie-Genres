/*
 * Class: CISC 3130
 * Section: TY2
 * EmplId: 23809622
 * Name: Liyu Lin
 */
package moviekvp;
import java.io.*;
import java.util.*;

public class MovieKVP {
    
    // function to sort hashmap by values
    static HashMap<String, Integer> sortedMap(AbstractMap<String, Integer> map){
        // create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
        
        // sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2){
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        
        //put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> n : list) {
            temp.put(n.getKey(), n.getValue());
        }
        return temp;
    }
    
    // print the hashmap
    static void print(AbstractMap<String, Integer> map){
        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
    
    // the average number of movies per genre
    static int average(HashMap<String, Integer> map){
        int sum = 0;
        for(Integer a : map.values()){
            sum += a;
        }
        int ave = sum / map.size();
        return ave;
    }
    
    // split the data into more than average and less than average
    static void split(HashMap<String, Integer> map, 
            HashMap<String, Integer> map1, HashMap<String, Integer> map2, int ave){
        for(Map.Entry entry : map.entrySet()){
            if(entry.getValue().hashCode() <= ave){
                map1.put(entry.getKey().toString(), entry.getValue().hashCode());
            }
            else{
                map2.put(entry.getKey().toString(), entry.getValue().hashCode());
            }   
        }
    }
    
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        String line = "";
        String cvsSplitBy = ",";
        
        try (BufferedReader br = new BufferedReader(new FileReader("/data/movies.csv"))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] movieInfo = line.split(cvsSplitBy);
                int lastIndex = movieInfo.length - 1;
                // use pipe as separator
                String[] genreArray = movieInfo[lastIndex].split("\\|");
                
                // checking each string of genreArray
                for(String genre : genreArray){
                    if(map.containsKey(genre)){
                        // If genre is present in map,
                        // incrementing it's count by 1
                        map.put(genre, map.get(genre) + 1);
                    }
                    else{
                        // If genre is not present in map,
                        // putting this genre to map with 1 as it's value
                        map.put(genre, 1);
                    }
                }
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        map.remove("genres");
        //print(map);
        
        // sort the map by values in descending order
        HashMap<String, Integer> map1 = sortedMap(map);
        // print the sorted hashmap
        print(map1);
        
        // show the average number of movies per genre
        int ave = average(map);
        System.out.println("\nThe average number of movies per genre is: " + ave);
        
        // split the data into more than average and less than average
        HashMap<String, Integer> smallMap = new HashMap<>();
        HashMap<String, Integer> largeMap = new HashMap<>();
        
        split(map, smallMap, largeMap, ave);
        System.out.println("\nThe number of movies more than average:");
        print(largeMap);
        System.out.println("\nThe number of movies less than average:");
        print(smallMap);
        
        }
    }// end class
    
