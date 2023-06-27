import java.util.*;
import java.util.Set;
import java.util.HashSet;

public class SetEx {

  public static void main(String args[]) {
    // Tworzenie zbioru.  
    Set<String> names = new HashSet<String>();
        
    // Dodawanie elementow do zbioru. Elementy musza byc rozne
    names.add("Nowak"); names.add("Kowalski"); 
    names.add("Bielecki"); names.add("Adamski");
    names.add("Kowalski");
   
    System.out.println("Wszystkie elementy:");
    for(String s : names) System.out.println("-> "+s);
    
    // Min, max element zbioru
    System.out.println("Najmniejszy element:");
    System.out.println(Collections.min(names));
    
    System.out.println("Najwiekszy element:");
    System.out.println(Collections.max(names));

    // Usuwanie ze zbioru
    System.out.println("Po usunieciu Kowalskiego:");
    names.remove("Kowalski"); System.out.println(names);
    System.out.println(names.contains("Kowalski"));


  // Tworzenie zbioru.Elementy sa w kolejnosci wstawiania.
    Set<String> namesLinked = new LinkedHashSet<String>();
        
    namesLinked.add("Nowak"); namesLinked.add("Kowalski"); 
    namesLinked.add("Bielecki"); namesLinked.add("Adamski");
    namesLinked.add("Kowalski");

    System.out.println("Wszystkie elementy:");
    for(String s : namesLinked) System.out.println("---> "+s);

  }
}
