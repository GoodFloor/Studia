import java.util.TreeMap;
import java.util.*;

public class MapEx {

  public static void main(String args[]) {
    // Tworzenie mapy posortowanej wedlug klucza
    TreeMap<String,Integer> names = new TreeMap<String,Integer>();
    names.put("Nowak",1); names.put("Kowalski",2); 
    names.put("Bielecki",1); names.put("Adamski",2);
    names.put("Kowalski",1);

    // Wypisywanie klucza i elementu niekluczowego
    System.out.println("Wszystkie elementy:");
    for(String s : names.keySet()) 
      System.out.println("-> "+s+" - "+names.get(s));
    
    // Wypisywanie elementow niekluczowych
    System.out.println("Elementy niekluczowe:");
    for(int s : names.values()) System.out.println("-> "+s);
    
    System.out.println("Wypisanie podzbioru:");  
    SortedMap<String,Integer> names1= names.subMap("Bielecki", "Nowak"); 
    for(String s : names1.keySet()) 
      System.out.println("-> "+s+" - "+names.get(s));
   
    
    System.out.println("Wypisanie po usunieciu Kowalskiego:");  
    names.remove("Kowalski"); System.out.println(names);
    
    System.out.println("Sprawdzanie czy jest Kowalski:");  
    System.out.println(names.containsKey("Kowalski"));

    System.out.println("Sprawdzanie czy jest 2:");  
    System.out.println(names.containsValue(2));   
  }
}
