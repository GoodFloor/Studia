import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

// Mapa jako para: String, Integer. Pierwszy element jest kluczem(nie moze sie
// powtarzac)
public class HashMapEx {
  public static void main(String args[]) {
      
    // Tworzenie i dodawanie elementow  
    Map<String,Integer> names = new HashMap<String,Integer>();
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
    
    // Wypisywanie elementu niekluczowego dla Kowalski
    System.out.println("Element niekluczowy dla Kowalski:");
    System.out.println(names.get("Kowalski"));

    // Usuwanie elementu Kowalski
    System.out.println("Wypisanie po usunieciu Kowalskiego:");  
    names.remove("Kowalski"); System.out.println(names);

    // Sprawdzanie czy istnieje element o kluczu Kowalski
    System.out.println("Sprawdzanie czy jest Kowalski:"); 
    System.out.println(names.containsKey("Kowalski"));

    // Sprawdzanie czy istnieje element niekluczowy 2
    System.out.println("Sprawdzanie czy jest 2:"); 
    System.out.println(names.containsValue(2));   
  }
}
